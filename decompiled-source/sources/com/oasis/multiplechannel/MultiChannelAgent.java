package com.oasis.multiplechannel;

import android.content.Context;
import android.util.Log;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public class MultiChannelAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static MultiChannelAgent f17552a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17553b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17554c = true;

    public MultiChannelAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static MultiChannelAgent createInstance(Context context) {
        if (f17554c || f17552a == null) {
            try {
                String string = context.getString(R.string.multiple_channel_agent_class);
                if (string.length() > 0) {
                    Log.i("MultiChannelAgent", "Create the MultiChannelAgent:" + string);
                    f17552a = (MultiChannelAgent) Class.forName(string).newInstance();
                }
            } catch (Exception e2) {
                Log.e("MultiChannelAgent", "Create Multiple Channel agent failed.", e2);
            }
            if (f17552a == null) {
                Log.i("MultiChannelAgent", "Create default MultiChannelAgent.");
                f17552a = new a();
                f17553b = true;
            }
            return f17552a;
        }
        throw new AssertionError();
    }

    public static MultiChannelAgent getInstance() {
        if (f17554c || f17552a != null) {
            return f17552a;
        }
        throw new AssertionError();
    }

    public void createNewRole(String str) {
    }

    public void enterGame(String str) {
    }

    public void exitGame() {
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public boolean isAvailable(String str) {
        return false;
    }

    public boolean isUseDefault() {
        return f17553b;
    }

    public void roleExit(String str) {
    }

    public void roleLevelUp(String str) {
    }

    public void setAccountListener(MultiChannelAccountListener multiChannelAccountListener) {
    }

    public void showPrivacy(int i2) {
    }
}
