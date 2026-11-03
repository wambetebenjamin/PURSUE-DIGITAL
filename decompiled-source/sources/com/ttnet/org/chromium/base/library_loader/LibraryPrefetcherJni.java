package com.ttnet.org.chromium.base.library_loader;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.library_loader.LibraryPrefetcher;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
@MainDex
/* loaded from: classes9.dex */
public class LibraryPrefetcherJni implements LibraryPrefetcher.Natives {
    public static final JniStaticTestMocker<LibraryPrefetcher.Natives> TEST_HOOKS = new JniStaticTestMocker<LibraryPrefetcher.Natives>() { // from class: com.ttnet.org.chromium.base.library_loader.LibraryPrefetcherJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(LibraryPrefetcher.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                LibraryPrefetcher.Natives unused = LibraryPrefetcherJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static LibraryPrefetcher.Natives testInstance;

    LibraryPrefetcherJni() {
    }

    @Override // com.ttnet.org.chromium.base.library_loader.LibraryPrefetcher.Natives
    public void forkAndPrefetchNativeLibrary() {
        GEN_JNI.com_ttnet_org_chromium_base_library_1loader_LibraryPrefetcher_forkAndPrefetchNativeLibrary();
    }

    @Override // com.ttnet.org.chromium.base.library_loader.LibraryPrefetcher.Natives
    public int percentageOfResidentNativeLibraryCode() {
        return GEN_JNI.com_ttnet_org_chromium_base_library_1loader_LibraryPrefetcher_percentageOfResidentNativeLibraryCode();
    }

    @Override // com.ttnet.org.chromium.base.library_loader.LibraryPrefetcher.Natives
    public void periodicallyCollectResidency() {
        GEN_JNI.com_ttnet_org_chromium_base_library_1loader_LibraryPrefetcher_periodicallyCollectResidency();
    }

    public static LibraryPrefetcher.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            LibraryPrefetcher.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.library_loader.LibraryPrefetcher.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(true);
        return new LibraryPrefetcherJni();
    }
}
