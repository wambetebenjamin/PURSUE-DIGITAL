package com.bytedance.crash.dumper;

import android.app.Activity;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: ViewTree.java */
/* loaded from: classes2.dex */
public class t {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6603a = "viewTree.txt";

    public static void a(File file) {
        JSONArray a2 = a();
        if (a2 != null) {
            try {
                com.bytedance.crash.util.m.writeFile(new File(file, f6603a), a2, false);
            } catch (Throwable unused) {
            }
        }
    }

    private static JSONArray a() {
        Window window;
        View peekDecorView;
        JSONArray jSONArray = new JSONArray();
        ArrayList<WeakReference<Activity>> a2 = com.bytedance.crash.tracker.a.a();
        if (a2 == null || a2.size() <= 0) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        Iterator<WeakReference<Activity>> it = a2.iterator();
        while (it.hasNext()) {
            Activity activity = it.next().get();
            if (activity != null && (window = activity.getWindow()) != null && (peekDecorView = window.peekDecorView()) != null) {
                JSONObject jSONObject = new JSONObject();
                linkedList.add(new Pair(peekDecorView, jSONObject));
                jSONArray.put(jSONObject);
            }
        }
        while (!linkedList.isEmpty()) {
            Pair pair = (Pair) linkedList.pop();
            View view = (View) pair.first;
            JSONObject jSONObject2 = (JSONObject) pair.second;
            com.bytedance.crash.util.q.a(jSONObject2, "cls", (Object) String.valueOf(view.getClass()));
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                JSONArray jSONArray2 = new JSONArray();
                com.bytedance.crash.util.q.a(jSONObject2, "children", jSONArray2);
                for (int i2 = 0; i2 < childCount; i2++) {
                    try {
                        View childAt = viewGroup.getChildAt(i2);
                        if (childAt != null) {
                            JSONObject jSONObject3 = new JSONObject();
                            jSONArray2.put(jSONObject3);
                            linkedList.push(new Pair(childAt, jSONObject3));
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
        }
        return jSONArray;
    }
}
