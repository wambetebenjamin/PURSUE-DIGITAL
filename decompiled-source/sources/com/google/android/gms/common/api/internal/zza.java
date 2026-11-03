package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class zza extends Fragment implements LifecycleFragment {
    private static final WeakHashMap zza = new WeakHashMap();
    private final zzc zzb = new zzc();

    public static zza zza(Activity activity) {
        zza zzaVar;
        WeakHashMap weakHashMap = zza;
        WeakReference weakReference = (WeakReference) weakHashMap.get(activity);
        if (weakReference == null || (zzaVar = (zza) weakReference.get()) == null) {
            try {
                zza zzaVar2 = (zza) activity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
                if (zzaVar2 == null || zzaVar2.isRemoving()) {
                    zzaVar2 = new zza();
                    activity.getFragmentManager().beginTransaction().add(zzaVar2, "LifecycleFragmentImpl").commitAllowingStateLoss();
                }
                weakHashMap.put(activity, new WeakReference(zzaVar2));
                return zzaVar2;
            } catch (ClassCastException e2) {
                throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", e2);
            }
        }
        return zzaVar;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final void addCallback(String str, LifecycleCallback lifecycleCallback) {
        this.zzb.zzb(str, lifecycleCallback);
    }

    @Override // android.app.Fragment
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        this.zzb.zzl(str, fileDescriptor, printWriter, strArr);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final <T extends LifecycleCallback> T getCallbackOrNull(String str, Class<T> cls) {
        return (T) this.zzb.zza(str, cls);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final Activity getLifecycleActivity() {
        return getActivity();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isCreated() {
        return this.zzb.zzc();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isStarted() {
        return this.zzb.zzd();
    }

    @Override // android.app.Fragment
    public final void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        this.zzb.zzh(i2, i3, intent);
    }

    @Override // android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzb.zze(bundle);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.zzb.zzk();
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        this.zzb.zzg();
    }

    @Override // android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.zzb.zzi(bundle);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        this.zzb.zzf();
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        this.zzb.zzj();
    }
}
