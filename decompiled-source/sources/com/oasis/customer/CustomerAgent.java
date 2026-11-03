package com.oasis.customer;

import android.content.Context;
import android.util.Log;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class CustomerAgent implements ApplicationListener, ActivityListener {
    protected static String TAG = "CustomerAgent";

    /* renamed from: a  reason: collision with root package name */
    private static CustomerAgent f17523a = null;

    /* renamed from: b  reason: collision with root package name */
    static final /* synthetic */ boolean f17524b = true;

    public CustomerAgent() {
        Log.i(TAG, "CustomerAgent registerListener");
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static CustomerAgent createInstance(Context context) {
        if (f17524b || f17523a == null) {
            try {
                String string = context.getString(R.string.customer_agent_class);
                Log.i(TAG, "Start to create the CustomerAgent: " + string);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Log.i(TAG, "Create the CustomerAgent: " + string);
                    f17523a = (CustomerAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Log.e(TAG, "Create CustomerAgent failed.", e2);
            }
            if (f17523a == null) {
                Log.i(TAG, "Create default CustomerAgent.");
                f17523a = new a();
            }
            return f17523a;
        }
        throw new AssertionError();
    }

    public static CustomerAgent getInstance() {
        if (f17524b || f17523a != null) {
            return f17523a;
        }
        throw new AssertionError();
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public void showConversation(String str) {
        Log.i(TAG, "showConversation,customParams=" + str);
    }

    public void showFAQ(String str) {
        Log.i(TAG, "showFAQ,customParams=" + str);
    }
}
