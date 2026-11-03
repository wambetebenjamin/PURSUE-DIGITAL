package com.oasis.purchase;

import android.content.Context;
import android.util.Log;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
import e.a;
/* loaded from: classes9.dex */
public abstract class PurchaseAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static PurchaseAgent f17587a = null;

    /* renamed from: b  reason: collision with root package name */
    static final /* synthetic */ boolean f17588b = true;

    public PurchaseAgent() {
        Log.i("OasisPlayerActivity", "PurchaseAgent registerListener");
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static PurchaseAgent createInstance(Context context) {
        if (f17588b || f17587a == null) {
            try {
                String string = context.getString(R.string.purchase_agent_class);
                Log.i("PurchaseAgent", "Start to create the PurchaseAgent: " + string);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Log.i("PurchaseAgent", "Create the PurchaseAgent: " + string);
                    f17587a = (PurchaseAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Log.e("PurchaseAgent", "Create PurchaseAgent failed.", e2);
            }
            if (f17587a == null) {
                Log.i("PurchaseAgent", "Create default PurchaseAgent.");
                f17587a = new a();
            }
            return f17587a;
        }
        throw new AssertionError();
    }

    public static PurchaseAgent getInstance() {
        if (f17588b || f17587a != null) {
            return f17587a;
        }
        throw new AssertionError();
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public abstract String getRiskControlInfo();

    public abstract void pay(String str, PayListener payListener);

    public abstract void queryGoods(String str, QueryGoodsListener queryGoodsListener);
}
