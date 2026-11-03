package com.oasis.udp;

import android.app.Application;
import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class GameNetExpAgent implements ApplicationListener {

    /* renamed from: a  reason: collision with root package name */
    private static GameNetExpAgent f17628a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17629b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17630c = true;
    protected Application application;

    public GameNetExpAgent() {
        OasisApplication.registerListener(this);
    }

    public static GameNetExpAgent createInstance(Context context) {
        if (f17630c || f17628a == null) {
            try {
                String string = context.getString(R.string.game_net_experience_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("GameNetExpAgent", "Create the GameNetExpAgent:" + string);
                    f17628a = (GameNetExpAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("GameNetExpAgent", "Create GameNetExpAgent failed.", e2);
            }
            if (f17628a == null) {
                Logger.i("GameNetExpAgent", "Create default GameNetExpAgent.");
                f17628a = new a();
                f17629b = true;
            }
            return f17628a;
        }
        throw new AssertionError();
    }

    public static GameNetExpAgent getInstance() {
        if (f17630c || f17628a != null) {
            return f17628a;
        }
        throw new AssertionError();
    }

    public abstract String createRequest(int i2, String str, int i3, int i4, NetDiagnosisListener netDiagnosisListener);

    public abstract void doDiagnosisDuringGaming(String str);

    public void finalize() {
        OasisApplication.unregisterListener(this);
    }

    public abstract void initMpaService(INetListener iNetListener);

    public abstract boolean isMnaEnabled();

    public boolean isUseDefault() {
        return f17629b;
    }

    public abstract void monitorBegin(String str, String str2);

    public abstract void monitorEnd();

    public abstract void reportUserLog(String str);

    public abstract void requestCancel(String str);

    public abstract void requestDoExtraCommand(String str, String str2, String str3);

    public abstract void requestSetUserExtraInfo(String str, String str2);

    public abstract void requestStart(String str);

    public abstract void setMpaAccAddress(String str, INetListener iNetListener);

    public abstract void setMpaAccAddressWithContext(String str, String str2, INetListener iNetListener);

    public abstract void startMna(String str, INetListener iNetListener);

    public abstract void startMpa(String str);

    public abstract void stopMna(INetListener iNetListener);

    public abstract void stopMpa(String str, INetListener iNetListener);

    public abstract void tryInitGna(String str);
}
