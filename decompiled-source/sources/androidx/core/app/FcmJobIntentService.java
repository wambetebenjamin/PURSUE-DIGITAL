package androidx.core.app;

import androidx.core.app.JobIntentService;
/* loaded from: classes.dex */
public abstract class FcmJobIntentService extends JobIntentService {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.core.app.JobIntentService
    public JobIntentService.GenericWorkItem dequeueWork() {
        try {
            return super.dequeueWork();
        } catch (SecurityException unused) {
            return null;
        }
    }
}
