package com.ttnet.org.chromium.base.library_loader;

import com.bytedance.librarian.c;
import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.TimeUtils;
import com.ttnet.org.chromium.base.library_loader.Linker;
import com.ttnet.org.chromium.base.metrics.RecordHistogram;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class ModernLinker extends Linker {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String DETAILED_LOAD_TIME_HISTOGRAM_PREFIX = "ChromiumAndroidLinker.ModernLinkerDetailedLoadTime.";
    private static final String DETAILED_LOAD_TIME_HISTOGRAM_PREFIX_BLKIO_CGROUP = "ChromiumAndroidLinker.ModernLinkerDetailedLoadTimeByBlkioCgroup.";
    private static final String SELF_CGROUP_FILE_NAME = "/proc/self/cgroup";
    private static final String SUFFIX_UNKNOWN = "Unknown";
    private static final String TAG = "ModernLinker";
    private static Natives sNativesInstance;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        int getRelroSharingResult();

        boolean loadLibrary(String str, Linker.LibInfo libInfo, boolean z);

        boolean useRelros(long j2, Linker.LibInfo libInfo);
    }

    @Override // com.ttnet.org.chromium.base.library_loader.Linker
    protected boolean keepMemoryReservationUntilLoad() {
        return true;
    }

    private static String extractBlkioCgroupFromLine(String str) {
        int indexOf = str.indexOf(":blkio:");
        return indexOf == -1 ? "" : str.substring(indexOf + 7);
    }

    private String readBackgroundStateFromCgroups() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(SELF_CGROUP_FILE_NAME)));
            String str = null;
            do {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str = extractBlkioCgroupFromLine(readLine);
            } while (str.equals(""));
            if (str == null || str.equals("")) {
                bufferedReader.close();
                return "Unknown";
            }
            bufferedReader.close();
            if (str.equals(c.a.SEPARATOR)) {
                return "Foreground";
            }
            if (str.equals("/background")) {
                return "Background";
            }
            Log.e(TAG, "blkio cgroup with unexpected name: '%s'", str);
            return "Unknown";
        } catch (IOException unused) {
            Log.e(TAG, "IOException while reading %s", SELF_CGROUP_FILE_NAME);
            return "Unknown";
        }
    }

    @Override // com.ttnet.org.chromium.base.library_loader.Linker
    protected void loadLibraryImplLocked(String str, int i2) {
        boolean z = false;
        if (!"monochrome".equals(str)) {
            Log.i(TAG, "loadLibraryImplLocked: %s, relroMode=%d", str, Integer.valueOf(i2));
        }
        String readBackgroundStateFromCgroups = readBackgroundStateFromCgroups();
        TimeUtils.UptimeMillisTimer uptimeMillisTimer = new TimeUtils.UptimeMillisTimer();
        String mapLibraryName = System.mapLibraryName(str);
        if (i2 == 0) {
            this.mState = 3;
        } else {
            if (i2 == 1) {
                loadAndProduceSharedRelro(mapLibraryName);
                this.mState = 2;
            } else {
                loadWithoutProducingRelro(mapLibraryName);
                this.mState = 3;
            }
            z = true;
        }
        String readBackgroundStateFromCgroups2 = readBackgroundStateFromCgroups();
        if (!readBackgroundStateFromCgroups.equals(readBackgroundStateFromCgroups2)) {
            readBackgroundStateFromCgroups = (readBackgroundStateFromCgroups.equals("Unknown") || readBackgroundStateFromCgroups2.equals("Unknown")) ? "Unknown" : "Mixed";
        }
        if (z) {
            recordDetailedLoadTimeSince(uptimeMillisTimer, i2 == 1 ? "Produce" : "Consume", readBackgroundStateFromCgroups);
        }
        TimeUtils.UptimeMillisTimer uptimeMillisTimer2 = new TimeUtils.UptimeMillisTimer();
        try {
            System.loadLibrary(str);
        } catch (UnsatisfiedLinkError unused) {
            resetAndThrow("Failed at System.loadLibrary()");
        }
        recordDetailedLoadTimeSince(uptimeMillisTimer2, z ? "Second" : "NoSharing", readBackgroundStateFromCgroups);
    }

    private void recordDetailedLoadTimeSince(TimeUtils.UptimeMillisTimer uptimeMillisTimer, String str, String str2) {
        long elapsedMillis = uptimeMillisTimer.getElapsedMillis();
        RecordHistogram.recordTimesHistogram(DETAILED_LOAD_TIME_HISTOGRAM_PREFIX + str, elapsedMillis);
        RecordHistogram.recordTimesHistogram(DETAILED_LOAD_TIME_HISTOGRAM_PREFIX_BLKIO_CGROUP + str + c.a.DOT + str2, elapsedMillis);
    }

    private void loadWithoutProducingRelro(String str) {
        if (getModernLinkerJni().loadLibrary(str, this.mLocalLibInfo, false)) {
            return;
        }
        resetAndThrow(String.format("Unable to load library: %s", str));
    }

    private void loadAndProduceSharedRelro(String str) {
        this.mLocalLibInfo.mLibFilePath = str;
        if (!getModernLinkerJni().loadLibrary(str, this.mLocalLibInfo, true)) {
            Log.e(TAG, "Unable to load with ModernLinker, using the system linker instead", new Object[0]);
            this.mLocalLibInfo.mRelroFd = -1;
        }
        RecordHistogram.recordBooleanHistogram("ChromiumAndroidLinker.RelroProvidedSuccessfully", this.mLocalLibInfo.mRelroFd != -1);
    }

    @Override // com.ttnet.org.chromium.base.library_loader.Linker
    protected void atomicReplaceRelroLocked(boolean z) {
        if (this.mRemoteLibInfo.mRelroFd == -1 || this.mLocalLibInfo == null) {
            return;
        }
        getModernLinkerJni().useRelros(this.mLocalLibInfo.mLoadAddress, this.mRemoteLibInfo);
        RecordHistogram.recordBooleanHistogram("ChromiumAndroidLinker.RelroAvailableImmediately", z);
        RecordHistogram.recordEnumeratedHistogram("ChromiumAndroidLinker.RelroSharingStatus2", getModernLinkerJni().getRelroSharingResult(), 9);
    }

    private void resetAndThrow(String str) {
        this.mState = 1;
        Log.e(TAG, str, new Object[0]);
        throw new UnsatisfiedLinkError(str);
    }

    public static void reportDlopenExtTime(long j2) {
        RecordHistogram.recordTimesHistogram("ChromiumAndroidLinker.ModernLinkerDlopenExtTime", j2);
    }

    public static void reportIteratePhdrTime(long j2) {
        RecordHistogram.recordTimesHistogram("ChromiumAndroidLinker.ModernLinkerIteratePhdrTime", j2);
    }

    static void setModernLinkerNativesForTesting(Natives natives) {
        sNativesInstance = natives;
    }

    static Natives getModernLinkerJni() {
        Natives natives = sNativesInstance;
        return natives != null ? natives : new ModernLinkerJni();
    }
}
