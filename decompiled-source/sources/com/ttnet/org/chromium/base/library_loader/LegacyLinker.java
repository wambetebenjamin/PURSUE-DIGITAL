package com.ttnet.org.chromium.base.library_loader;

import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.library_loader.Linker;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class LegacyLinker extends Linker {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "LegacyLinker";

    private static native boolean nativeAddZipArchivePath(String str);

    private static native boolean nativeCreateSharedRelro(String str, long j2, Linker.LibInfo libInfo);

    private static native boolean nativeLoadLibrary(String str, long j2, Linker.LibInfo libInfo);

    private static native boolean nativeUseSharedRelro(String str, Linker.LibInfo libInfo);

    @Override // com.ttnet.org.chromium.base.library_loader.Linker
    protected boolean keepMemoryReservationUntilLoad() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.ttnet.org.chromium.base.library_loader.Linker
    public void setApkFilePath(String str) {
        ensureInitializedImplicitlyAsLastResort();
        synchronized (this.mLock) {
            nativeAddZipArchivePath(str);
        }
    }

    @Override // com.ttnet.org.chromium.base.library_loader.Linker
    protected void loadLibraryImplLocked(String str, int i2) {
        String mapLibraryName = System.mapLibraryName(str);
        if (!nativeLoadLibrary(mapLibraryName, this.mLocalLibInfo.mLoadAddress, this.mLocalLibInfo)) {
            String str2 = "Unable to load library: " + mapLibraryName;
            Log.e(TAG, str2, new Object[0]);
            throw new UnsatisfiedLinkError(str2);
        }
        this.mLocalLibInfo.mLibFilePath = mapLibraryName;
        if (i2 == 1 || i2 == 0) {
            if (!nativeCreateSharedRelro(mapLibraryName, this.mLocalLibInfo.mLoadAddress, this.mLocalLibInfo)) {
                Log.w(TAG, "Could not create shared RELRO for %s at %x", mapLibraryName, Long.valueOf(this.mLocalLibInfo.mLoadAddress));
                this.mLocalLibInfo.mRelroFd = -1;
            }
            useSharedRelrosLocked(this.mLocalLibInfo);
            this.mState = 2;
            return;
        }
        waitForSharedRelrosLocked();
        useSharedRelrosLocked(this.mRemoteLibInfo);
        this.mRemoteLibInfo.close();
        this.mRemoteLibInfo = null;
        this.mState = 3;
    }

    private static void useSharedRelrosLocked(Linker.LibInfo libInfo) {
        String str = libInfo.mLibFilePath;
        if (nativeUseSharedRelro(str, libInfo)) {
            return;
        }
        Log.w(TAG, "Could not use shared RELRO section for %s", str);
    }
}
