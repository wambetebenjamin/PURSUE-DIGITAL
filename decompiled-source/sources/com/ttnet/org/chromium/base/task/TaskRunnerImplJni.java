package com.ttnet.org.chromium.base.task;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.base.task.TaskRunnerImpl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class TaskRunnerImplJni implements TaskRunnerImpl.Natives {
    public static final JniStaticTestMocker<TaskRunnerImpl.Natives> TEST_HOOKS = new JniStaticTestMocker<TaskRunnerImpl.Natives>() { // from class: com.ttnet.org.chromium.base.task.TaskRunnerImplJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(TaskRunnerImpl.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                TaskRunnerImpl.Natives unused = TaskRunnerImplJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static TaskRunnerImpl.Natives testInstance;

    TaskRunnerImplJni() {
    }

    @Override // com.ttnet.org.chromium.base.task.TaskRunnerImpl.Natives
    public long init(int i2, int i3, boolean z, boolean z2, byte b2, byte[] bArr) {
        return GEN_JNI.com_ttnet_org_chromium_base_task_TaskRunnerImpl_init(i2, i3, z, z2, b2, bArr);
    }

    @Override // com.ttnet.org.chromium.base.task.TaskRunnerImpl.Natives
    public void destroy(long j2) {
        GEN_JNI.com_ttnet_org_chromium_base_task_TaskRunnerImpl_destroy(j2);
    }

    @Override // com.ttnet.org.chromium.base.task.TaskRunnerImpl.Natives
    public void postDelayedTask(long j2, Runnable runnable, long j3, String str) {
        GEN_JNI.com_ttnet_org_chromium_base_task_TaskRunnerImpl_postDelayedTask(j2, runnable, j3, str);
    }

    @Override // com.ttnet.org.chromium.base.task.TaskRunnerImpl.Natives
    public boolean belongsToCurrentThread(long j2) {
        return GEN_JNI.com_ttnet_org_chromium_base_task_TaskRunnerImpl_belongsToCurrentThread(j2);
    }

    public static TaskRunnerImpl.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            TaskRunnerImpl.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.task.TaskRunnerImpl.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new TaskRunnerImplJni();
    }
}
