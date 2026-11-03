package com.bytedance.common.jato.memory.LeakRepair.debug;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.common.jato.memory.LeakRepair.RenderNodeReclaimer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DebugReceiver.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4839a = "ActivityLeakReceiver";

    /* renamed from: b  reason: collision with root package name */
    public static final String f4840b = "com.jato.debug.reclaim_activity";

    /* renamed from: c  reason: collision with root package name */
    public static final String f4841c = "com.jato.debug.dump_activity";

    /* renamed from: d  reason: collision with root package name */
    public static final String f4842d = "com.jato.debug.reclaim_leaked_activity";

    /* renamed from: f  reason: collision with root package name */
    private HashMap<Integer, Activity> f4844f = new HashMap<>();

    /* renamed from: e  reason: collision with root package name */
    RenderNodeReclaimer f4843e = RenderNodeReclaimer.a();

    /* renamed from: g  reason: collision with root package name */
    private BroadcastReceiver f4845g = new BroadcastReceiver() { // from class: com.bytedance.common.jato.memory.LeakRepair.debug.DebugReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            HashMap hashMap;
            HashMap hashMap2;
            if (intent.getAction().equals(a.f4840b)) {
                int intExtra = intent.getIntExtra("act_id", 0);
                hashMap2 = a.this.f4844f;
                Activity activity = (Activity) hashMap2.get(Integer.valueOf(intExtra));
                if (activity != null) {
                    Log.e(a.f4839a, "Reclaim Activity:" + intExtra);
                    a.this.f4843e.a(com.bytedance.common.jato.memory.LeakRepair.b.a(activity), activity.toString());
                }
            } else if (intent.getAction().equals(a.f4841c)) {
                Log.e(a.f4839a, "Dump Activity:");
                hashMap = a.this.f4844f;
                for (Map.Entry entry : hashMap.entrySet()) {
                    Log.e(a.f4839a, String.format("Dump Activity: act_id=%d, name=%s", entry.getKey(), entry.getValue()));
                }
            } else {
                intent.getAction().equals(a.f4842d);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DebugReceiver.java */
    /* renamed from: com.bytedance.common.jato.memory.LeakRepair.debug.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0062a {
        void a(View view);
    }

    public HashMap<Integer, Activity> a() {
        return this.f4844f;
    }

    public a(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(f4840b);
        intentFilter.addAction(f4841c);
        intentFilter.addAction(f4842d);
        context.registerReceiver(this.f4845g, intentFilter);
    }

    private static void a(ViewGroup viewGroup, InterfaceC0062a interfaceC0062a) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt != null) {
                interfaceC0062a.a(childAt);
                if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt, interfaceC0062a);
                }
            }
        }
    }

    private void a(Activity activity) {
        final HashMap hashMap = new HashMap();
        a((ViewGroup) activity.getWindow().getDecorView(), new InterfaceC0062a() { // from class: com.bytedance.common.jato.memory.LeakRepair.debug.a.1
            @Override // com.bytedance.common.jato.memory.LeakRepair.debug.a.InterfaceC0062a
            public void a(View view) {
                String name = view.getClass().getName();
                Integer num = (Integer) hashMap.get(name);
                if (num == null) {
                    hashMap.put(name, 1);
                } else {
                    hashMap.put(name, Integer.valueOf(num.intValue() + 1));
                }
            }
        });
        ArrayList<Map.Entry> arrayList = new ArrayList(hashMap.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() { // from class: com.bytedance.common.jato.memory.LeakRepair.debug.a.2
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry.getValue());
            }
        });
        for (Map.Entry entry : arrayList) {
            Log.e(f4839a, String.format("view count: %s = %d", entry.getKey(), entry.getValue()));
        }
    }
}
