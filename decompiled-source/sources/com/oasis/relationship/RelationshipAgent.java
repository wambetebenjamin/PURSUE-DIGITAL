package com.oasis.relationship;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
import f.a;
/* loaded from: classes9.dex */
public abstract class RelationshipAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static RelationshipAgent f17601a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17602b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17603c = true;

    public RelationshipAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static RelationshipAgent createInstance(Context context) {
        if (f17603c || f17601a == null) {
            try {
                String string = context.getString(R.string.relationship_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("RelationshipAgent", "Create the RelationshipAgent:" + string);
                    f17601a = (RelationshipAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("RelationshipAgent", "Create notice agent failed.", e2);
            }
            if (f17601a == null) {
                Logger.i("RelationshipAgent", "Create default RelationshipAgent.");
                f17601a = new a();
                f17602b = true;
            }
            return f17601a;
        }
        throw new AssertionError();
    }

    public static RelationshipAgent getInstance() {
        if (f17603c || f17601a != null) {
            return f17601a;
        }
        throw new AssertionError();
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public abstract void getFriendChainInfo(int i2, boolean z, RelationshipListener relationshipListener);

    public void hasOpenFriendChainPermission(int i2, RelationshipListener relationshipListener) {
    }

    public boolean isUseDefault() {
        return f17602b;
    }

    public void openDouyinAuthRequest(RelationshipListener relationshipListener) {
    }
}
