package com.ttnet.org.chromium.base.task;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.base.task.PostTask;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class PostTaskJni implements PostTask.Natives {
    public static final JniStaticTestMocker<PostTask.Natives> TEST_HOOKS = new JniStaticTestMocker<PostTask.Natives>() { // from class: com.ttnet.org.chromium.base.task.PostTaskJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(PostTask.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                PostTask.Natives unused = PostTaskJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static PostTask.Natives testInstance;

    PostTaskJni() {
    }

    @Override // com.ttnet.org.chromium.base.task.PostTask.Natives
    public void postDelayedTask(int i2, boolean z, boolean z2, byte b2, byte[] bArr, Runnable runnable, long j2, String str) {
        GEN_JNI.com_ttnet_org_chromium_base_task_PostTask_postDelayedTask(i2, z, z2, b2, bArr, runnable, j2, str);
    }

    public static PostTask.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            PostTask.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.task.PostTask.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new PostTaskJni();
    }
}
