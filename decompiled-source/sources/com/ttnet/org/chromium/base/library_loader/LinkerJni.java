package com.ttnet.org.chromium.base.library_loader;

import com.ttnet.org.chromium.base.library_loader.Linker;
/* loaded from: classes9.dex */
class LinkerJni implements Linker.Natives {
    private static native void nativeFindMemoryRegionAtRandomAddress(Linker.LibInfo libInfo, boolean z);

    private static native boolean nativeFindRegionReservedByWebViewZygote(Linker.LibInfo libInfo);

    private static native void nativeReserveMemoryForLibrary(Linker.LibInfo libInfo);

    @Override // com.ttnet.org.chromium.base.library_loader.Linker.Natives
    public void findMemoryRegionAtRandomAddress(Linker.LibInfo libInfo, boolean z) {
        nativeFindMemoryRegionAtRandomAddress(libInfo, z);
    }

    @Override // com.ttnet.org.chromium.base.library_loader.Linker.Natives
    public void reserveMemoryForLibrary(Linker.LibInfo libInfo) {
        nativeReserveMemoryForLibrary(libInfo);
    }

    @Override // com.ttnet.org.chromium.base.library_loader.Linker.Natives
    public boolean findRegionReservedByWebViewZygote(Linker.LibInfo libInfo) {
        return nativeFindRegionReservedByWebViewZygote(libInfo);
    }
}
