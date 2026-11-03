package com.bytedance.common.component;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.push.instrumentation.b;
import com.bytedance.push.utils.h;
import com.bytedance.push.utils.t;
import java.lang.reflect.Field;
/* compiled from: CommonInstrumentation.java */
/* loaded from: classes.dex */
public class a extends Instrumentation {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4405a = "instrumentation_type";

    /* renamed from: b  reason: collision with root package name */
    public static final String f4406b = "instrumentation_type_alliance";

    /* renamed from: c  reason: collision with root package name */
    public static final String f4407c = "instrumentation_type_ka";

    /* renamed from: d  reason: collision with root package name */
    private final String f4408d = "BaseInstrumentation";

    /* renamed from: e  reason: collision with root package name */
    private Bundle f4409e;

    @Override // android.app.Instrumentation
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f4409e = bundle;
        h.d("BaseInstrumentation", "CommonInstrumentation#onCreate,init mArguments:" + this.f4409e);
    }

    @Override // android.app.Instrumentation
    public Application newApplication(ClassLoader classLoader, String str, Context context) {
        h.d("BaseInstrumentation", "CommonInstrumentation#newApplication");
        a(this);
        return super.newApplication(classLoader, str, context);
    }

    private void a(Instrumentation instrumentation) {
        a("mWatcher", instrumentation);
        a("mUiAutomationConnection", instrumentation);
    }

    private void a(String str, Instrumentation instrumentation) {
        try {
            Field b2 = t.b(Instrumentation.class, str);
            b2.setAccessible(true);
            if (b2.get(instrumentation) != null) {
                Log.e("BaseInstrumentation", "Field " + str + " was set, do clean!");
                b2.set(this, null);
            }
            Log.i("BaseInstrumentation", "Clean field=" + str);
        } catch (IllegalAccessException e2) {
            Log.e("BaseInstrumentation", "Failed to get object of " + str, e2);
        }
    }

    @Override // android.app.Instrumentation
    public void callApplicationOnCreate(Application application) {
        com.bytedance.push.instrumentation.a a2;
        super.callApplicationOnCreate(application);
        Bundle bundle = this.f4409e;
        if (bundle == null) {
            h.e("BaseInstrumentation", "arguments is null");
            return;
        }
        String string = bundle.getString(f4405a);
        h.d("BaseInstrumentation", "CommonInstrumentation#callApplicationOnCreate,instrumentationType is " + string);
        if (TextUtils.isEmpty(string) || (a2 = b.a().a(string)) == null) {
            return;
        }
        a2.a(getContext(), this.f4409e);
    }
}
