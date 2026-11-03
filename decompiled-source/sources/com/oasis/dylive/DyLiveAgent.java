package com.oasis.dylive;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class DyLiveAgent implements ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static DyLiveAgent f17527a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17528b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17529c = true;
    protected static Context mContext;

    public DyLiveAgent() {
        ActivityManager.registerListener(this);
    }

    public static DyLiveAgent createInstance(Context context) {
        if (f17529c || f17527a == null) {
            mContext = context;
            try {
                String string = context.getString(R.string.dylive_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("DyLiveAgent", "Create the DyLiveAgent:" + string);
                    f17527a = (DyLiveAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("DyLiveAgent", "Create dyLive agent failed.", e2);
            }
            if (f17527a == null) {
                Logger.i("DyLiveAgent", "Create default DyLiveAgent.");
                f17527a = new DefaultDyLiveAgent();
                f17528b = true;
            }
            return f17527a;
        }
        throw new AssertionError();
    }

    public static DyLiveAgent getInstance() {
        if (f17529c || f17527a != null) {
            return f17527a;
        }
        throw new AssertionError();
    }

    public String currentBroadcastingRoomInfo() {
        return "";
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
    }

    public void initialize(Context context) {
    }

    public boolean isAvailable() {
        return false;
    }

    public boolean isGameLiveRecording() {
        return false;
    }

    public boolean isLiving() {
        return false;
    }

    public boolean isPausing() {
        return false;
    }

    public boolean isUseDefault() {
        return f17528b;
    }

    public void pauseLive() {
    }

    public void resumeLive() {
    }

    public void revokeAccountAuth(RevokeAuthListener revokeAuthListener) {
    }

    public void setDebug(boolean z) {
    }

    public void setErrorListener(ErrorListener errorListener) {
    }

    public void setInitListener(InitListener initListener) {
    }

    public void setStateListener(StateChangeListener stateChangeListener) {
    }

    public void setUpdateTokenListener(UpdateTokenListener updateTokenListener) {
    }

    public void startLive() {
    }

    public void startLiveRecord(String str, GameLiveRecordStateListener gameLiveRecordStateListener) {
    }

    public void startLiveWithConfig(String str) {
    }

    public void stopLive() {
    }

    public void stopLiveRecord() {
    }

    public void updateUserInfo(String str) {
    }
}
