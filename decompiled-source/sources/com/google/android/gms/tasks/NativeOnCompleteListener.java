package com.google.android.gms.tasks;
/* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
/* loaded from: classes5.dex */
public class NativeOnCompleteListener implements OnCompleteListener<Object> {
    private final long zza;

    public NativeOnCompleteListener(long j2) {
        this.zza = j2;
    }

    public static void createAndAddCallback(Task<Object> task, long j2) {
        task.addOnCompleteListener(new NativeOnCompleteListener(j2));
    }

    public native void nativeOnComplete(long j2, Object obj, boolean z, boolean z2, String str);

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public void onComplete(Task<Object> task) {
        Object obj;
        String str;
        Exception exception;
        if (task.isSuccessful()) {
            obj = task.getResult();
            str = null;
        } else if (task.isCanceled() || (exception = task.getException()) == null) {
            obj = null;
            str = null;
        } else {
            str = exception.getMessage();
            obj = null;
        }
        nativeOnComplete(this.zza, obj, task.isSuccessful(), task.isCanceled(), str);
    }
}
