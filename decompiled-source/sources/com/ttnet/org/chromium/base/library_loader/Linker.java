package com.ttnet.org.chromium.base.library_loader;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.bytedance.librarian.c;
import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.StreamUtil;
import com.ttnet.org.chromium.base.TimeUtils;
import com.ttnet.org.chromium.base.metrics.RecordHistogram;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public abstract class Linker {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String BASE_LOAD_ADDRESS = "com.ttnet.org.chromium.base.android.linker.base_load_address";
    protected static final boolean DEBUG = false;
    protected static final String LINKER_JNI_LIBRARY = "chromium_android_linker";
    static final String SHARED_RELROS = "com.ttnet.org.chromium.base.android.linker.shared_relros";
    private static final String TAG = "Linker";
    private static Linker sLinkerForAssert;
    private static Natives sNativesInstance;
    private boolean mLinkerWasWaitingSynchronously;
    protected LibInfo mLocalLibInfo;
    protected LibInfo mRemoteLibInfo;
    WebViewReservationSearchResult mWebviewReservationSearchResult;
    protected final Object mLock = new Object();
    boolean mRelroProducer = true;
    protected int mState = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        void findMemoryRegionAtRandomAddress(LibInfo libInfo, boolean z);

        boolean findRegionReservedByWebViewZygote(LibInfo libInfo);

        void reserveMemoryForLibrary(LibInfo libInfo);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface PreferAddress {
        public static final int FIND_RESERVED = 0;
        public static final int RESERVE_HINT = 1;
        public static final int RESERVE_RANDOM = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    protected @interface RelroSharingMode {
        public static final int CONSUME = 2;
        public static final int NO_SHARING = 0;
        public static final int PRODUCE = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    protected @interface State {
        public static final int DONE = 3;
        public static final int DONE_PROVIDE_RELRO = 2;
        public static final int INITIALIZED = 1;
        public static final int UNINITIALIZED = 0;
    }

    protected void atomicReplaceRelroLocked(boolean z) {
    }

    protected abstract boolean keepMemoryReservationUntilLoad();

    protected abstract void loadLibraryImplLocked(String str, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setApkFilePath(String str) {
    }

    void pretendLibraryIsLoadedForTesting() {
        synchronized (this.mLock) {
            this.mState = 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Linker() {
        sLinkerForAssert = this;
    }

    private String preferAddressToString(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? String.valueOf(i2) : "RESERVE_RANDOM" : "RESERVE_HINT" : "FIND_RESERVED";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class WebViewReservationSearchResult {
        private final long mDurationMs;
        private final boolean mSuccess;

        WebViewReservationSearchResult(boolean z, long j2) {
            this.mSuccess = z;
            this.mDurationMs = j2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void recordHistograms(String str) {
            RecordHistogram.recordTimesHistogram("ChromiumAndroidLinker.TimeToFindWebViewReservation." + (this.mSuccess ? "Found" : "NotFound") + c.a.DOT + str, this.mDurationMs);
        }
    }

    boolean isNonZeroLoadAddress(LibInfo libInfo) {
        return (libInfo == null || libInfo.mLoadAddress == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void ensureInitialized(boolean z, int i2, long j2) {
        synchronized (this.mLock) {
            if (this.mState != 0) {
                return;
            }
            chooseAndReserveMemoryRange(z, i2, j2);
            this.mState = 1;
        }
    }

    private void chooseAndReserveMemoryRange(boolean z, int i2, long j2) {
        this.mLocalLibInfo = new LibInfo();
        this.mRelroProducer = z;
        loadLinkerJniLibraryLocked();
        boolean keepMemoryReservationUntilLoad = keepMemoryReservationUntilLoad();
        if (i2 == 0) {
            TimeUtils.UptimeMillisTimer uptimeMillisTimer = new TimeUtils.UptimeMillisTimer();
            boolean findRegionReservedByWebViewZygote = getLinkerJni().findRegionReservedByWebViewZygote(this.mLocalLibInfo);
            saveWebviewReservationSearchStats(findRegionReservedByWebViewZygote, uptimeMillisTimer.getElapsedMillis());
            if (findRegionReservedByWebViewZygote && (j2 == 0 || j2 == this.mLocalLibInfo.mLoadAddress)) {
                return;
            }
        } else if (i2 != 1) {
            if (i2 != 2) {
                return;
            }
            getLinkerJni().findMemoryRegionAtRandomAddress(this.mLocalLibInfo, keepMemoryReservationUntilLoad);
        }
        this.mLocalLibInfo.mLoadAddress = j2;
        if (j2 != 0) {
            if (!keepMemoryReservationUntilLoad) {
                return;
            }
            getLinkerJni().reserveMemoryForLibrary(this.mLocalLibInfo);
            if (this.mLocalLibInfo.mLoadAddress != 0) {
                return;
            }
        }
        getLinkerJni().findMemoryRegionAtRandomAddress(this.mLocalLibInfo, keepMemoryReservationUntilLoad);
    }

    private void saveWebviewReservationSearchStats(boolean z, long j2) {
        this.mWebviewReservationSearchResult = new WebViewReservationSearchResult(z, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordHistograms(String str) {
        synchronized (this.mLock) {
            WebViewReservationSearchResult webViewReservationSearchResult = this.mWebviewReservationSearchResult;
            if (webViewReservationSearchResult == null) {
                return;
            }
            webViewReservationSearchResult.recordHistograms(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long extractLoadAddressFromBundle(Bundle bundle) {
        return bundle.getLong(BASE_LOAD_ADDRESS, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void putLoadAddressToBundle(Bundle bundle) {
        synchronized (this.mLock) {
            LibInfo libInfo = this.mLocalLibInfo;
            if (libInfo != null && libInfo.mLoadAddress != 0) {
                bundle.putLong(BASE_LOAD_ADDRESS, this.mLocalLibInfo.mLoadAddress);
            }
        }
    }

    private boolean shouldAtomicallyReplaceRelroAfterLoad() {
        return (this.mLinkerWasWaitingSynchronously || this.mRemoteLibInfo == null || this.mState != 3 || this.mLocalLibInfo.mLoadAddress == 0) ? false : true;
    }

    private void attemptLoadLibraryLocked(String str, int i2) {
        try {
            loadLibraryImplLocked(str, i2);
            if (shouldAtomicallyReplaceRelroAfterLoad()) {
                atomicReplaceRelroLocked(true);
            }
        } finally {
            this.mLinkerWasWaitingSynchronously = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void loadLibrary(String str) {
        synchronized (this.mLock) {
            try {
                ensureInitializedImplicitlyAsLastResort();
                attemptLoadLibraryLocked(str, this.mRelroProducer ? 1 : 2);
            } catch (UnsatisfiedLinkError unused) {
                Log.w(TAG, "Failed to load native library with shared RELRO, retrying without", new Object[0]);
                try {
                    this.mLocalLibInfo.mLoadAddress = 0L;
                    attemptLoadLibraryLocked(str, 0);
                } catch (UnsatisfiedLinkError e2) {
                    Log.w(TAG, "Failed to load native library without RELRO sharing", new Object[0]);
                    throw e2;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void putSharedRelrosToBundle(Bundle bundle) {
        synchronized (this.mLock) {
            bundle.putBundle(SHARED_RELROS, this.mState == 2 ? this.mLocalLibInfo.toBundle() : null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void takeSharedRelrosFromBundle(Bundle bundle) {
        LibInfo fromBundle;
        Bundle bundle2 = bundle.getBundle(SHARED_RELROS);
        if (bundle2 == null || (fromBundle = LibInfo.fromBundle(bundle2)) == null) {
            return;
        }
        synchronized (this.mLock) {
            LibInfo libInfo = this.mRemoteLibInfo;
            if (libInfo == null || libInfo.mRelroFd == -1) {
                this.mRemoteLibInfo = fromBundle;
                int i2 = this.mState;
                if (i2 == 3) {
                    atomicReplaceRelroLocked(false);
                } else if (i2 != 2) {
                    this.mLock.notifyAll();
                }
            }
        }
    }

    void loadLinkerJniLibraryLocked() {
        LibraryLoader.setEnvForNative();
        System.loadLibrary(LINKER_JNI_LIBRARY);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ensureInitializedImplicitlyAsLastResort() {
        ensureInitialized(true, 2, 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void waitForSharedRelrosLocked() {
        this.mLinkerWasWaitingSynchronously = true;
        while (this.mRemoteLibInfo == null) {
            try {
                this.mLock.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class LibInfo implements Parcelable {
        public static final Parcelable.Creator<LibInfo> CREATOR = new Parcelable.Creator<LibInfo>() { // from class: com.ttnet.org.chromium.base.library_loader.Linker.LibInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LibInfo createFromParcel(Parcel parcel) {
                return new LibInfo(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LibInfo[] newArray(int i2) {
                return new LibInfo[i2];
            }
        };
        private static final String EXTRA_LINKER_LIB_INFO = "libinfo";
        public String mLibFilePath;
        public long mLoadAddress;
        public long mLoadSize;
        public int mRelroFd;
        public long mRelroSize;
        public long mRelroStart;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 1;
        }

        LibInfo() {
            this.mRelroFd = -1;
        }

        LibInfo(Parcel parcel) {
            this.mRelroFd = -1;
            this.mLibFilePath = parcel.readString();
            this.mLoadAddress = parcel.readLong();
            this.mLoadSize = parcel.readLong();
            this.mRelroStart = parcel.readLong();
            this.mRelroSize = parcel.readLong();
            if (parcel.readInt() != 0) {
                ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                if (parcelFileDescriptor != null) {
                    this.mRelroFd = parcelFileDescriptor.detachFd();
                    return;
                }
                return;
            }
            this.mRelroFd = -1;
        }

        public void close() {
            int i2 = this.mRelroFd;
            if (i2 >= 0) {
                StreamUtil.closeQuietly(ParcelFileDescriptor.adoptFd(i2));
                this.mRelroFd = -1;
            }
        }

        public static LibInfo fromBundle(Bundle bundle) {
            bundle.setClassLoader(Linker.class.getClassLoader());
            return (LibInfo) bundle.getParcelable(EXTRA_LINKER_LIB_INFO);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(EXTRA_LINKER_LIB_INFO, this);
            return bundle;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.mLibFilePath);
            parcel.writeLong(this.mLoadAddress);
            parcel.writeLong(this.mLoadSize);
            parcel.writeLong(this.mRelroStart);
            parcel.writeLong(this.mRelroSize);
            parcel.writeInt(this.mRelroFd >= 0 ? 1 : 0);
            int i3 = this.mRelroFd;
            if (i3 >= 0) {
                try {
                    ParcelFileDescriptor fromFd = ParcelFileDescriptor.fromFd(i3);
                    fromFd.writeToParcel(parcel, 0);
                    fromFd.close();
                } catch (IOException e2) {
                    Log.e(Linker.TAG, "Can't write LibInfo file descriptor to parcel", e2);
                }
            }
        }
    }

    static void setNativesForTesting(Natives natives) {
        sNativesInstance = natives;
        sLinkerForAssert = null;
    }

    static Natives getLinkerJni() {
        Natives natives = sNativesInstance;
        return natives != null ? natives : new LinkerJni();
    }
}
