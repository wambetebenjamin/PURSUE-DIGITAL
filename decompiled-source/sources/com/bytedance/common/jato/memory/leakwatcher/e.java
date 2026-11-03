package com.bytedance.common.jato.memory.leakwatcher;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* compiled from: FragmentWatcher.java */
/* loaded from: classes2.dex */
public class e implements f {

    /* renamed from: d  reason: collision with root package name */
    private static final String f4994d = " received Fragment#onDestroy()";

    /* renamed from: a  reason: collision with root package name */
    private final Application f4995a;

    /* renamed from: b  reason: collision with root package name */
    private final c f4996b;

    /* renamed from: c  reason: collision with root package name */
    private final a f4997c = new a();

    /* renamed from: e  reason: collision with root package name */
    private final Set<String> f4998e;

    public e(Application application, c cVar, String[] strArr) {
        this.f4995a = application;
        this.f4996b = cVar;
        this.f4998e = a(strArr);
    }

    private static Set<String> a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(strArr)));
    }

    @Override // com.bytedance.common.jato.memory.leakwatcher.f
    public void a() {
        this.f4995a.registerActivityLifecycleCallbacks(this.f4997c);
    }

    @Override // com.bytedance.common.jato.memory.leakwatcher.f
    public void b() {
        this.f4995a.unregisterActivityLifecycleCallbacks(this.f4997c);
    }

    /* compiled from: FragmentWatcher.java */
    /* loaded from: classes2.dex */
    private class a extends d {
        private a() {
        }

        @Override // com.bytedance.common.jato.memory.leakwatcher.d, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (Build.VERSION.SDK_INT >= 26) {
                activity.getFragmentManager().registerFragmentLifecycleCallbacks(new b(), true);
            }
            if (activity instanceof FragmentActivity) {
                e.this.a((FragmentActivity) activity);
            }
        }
    }

    public void a(FragmentActivity fragmentActivity) {
        fragmentActivity.getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() { // from class: com.bytedance.common.jato.memory.leakwatcher.e.1
            @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
            public void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment) {
                e.this.a(fragment);
            }
        }, true);
    }

    /* compiled from: FragmentWatcher.java */
    /* loaded from: classes2.dex */
    private class b extends FragmentManager.FragmentLifecycleCallbacks {
        private b() {
        }

        @Override // android.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentDestroyed(android.app.FragmentManager fragmentManager, android.app.Fragment fragment) {
            e.this.a(fragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object obj) {
        if (obj == null) {
            return;
        }
        String name = obj.getClass().getName();
        if (this.f4998e.isEmpty() || this.f4998e.contains(name)) {
            this.f4996b.addForLeakDetection(obj, name + f4994d);
        }
    }
}
