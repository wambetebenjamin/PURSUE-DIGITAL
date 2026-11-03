package com.oasis.gameserverlist;

import android.content.Context;
import android.util.Log;
import com.oasis.Logger.Logger;
import com.oasis.android.R;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class ServerListAgent {
    public static String Tag = null;

    /* renamed from: a  reason: collision with root package name */
    private static ServerListAgent f17531a = null;

    /* renamed from: b  reason: collision with root package name */
    static final /* synthetic */ boolean f17532b = true;
    public static boolean isUseDefaultAgent;

    public static ServerListAgent createInstance(Context context) {
        if (f17532b || f17531a == null) {
            isUseDefaultAgent = false;
            try {
                String string = context.getString(R.string.game_server_list_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Log.i("ServerListAgent", "Create the ServerListAgent:" + string);
                    f17531a = (ServerListAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Log.e("ServerListAgent", "Create push agent failed.", e2);
            }
            if (f17531a == null) {
                Log.i("ServerListAgent", "Create default ServerListAgent.");
                f17531a = new ServerListAgent();
                isUseDefaultAgent = true;
            }
            Tag = f17531a.getClass().getName();
            return f17531a;
        }
        throw new AssertionError();
    }

    public static ServerListAgent getInstance() {
        if (f17532b || f17531a != null) {
            return f17531a;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getErrorInfo(int i2, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("error_code", i2);
            jSONObject.put("error_msg", str);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public void getRoleList(IRoleListListener iRoleListListener, double d2) {
        Logger.d(Tag, "getRoleList()");
        if (!isUseDefaultAgent || iRoleListListener == null) {
            return;
        }
        iRoleListListener.onGetResult(false, "{}");
    }

    public void getServerAndRoleList(String str, IServerRoleListListener iServerRoleListListener, double d2) {
        Logger.d(Tag, String.format("getServerAndRoleList(gameVersion: %s)", str));
        if (!isUseDefaultAgent || iServerRoleListListener == null) {
            return;
        }
        iServerRoleListListener.onGetResult(false, "{}");
    }

    public void getServerList(String str, IServerListListener iServerListListener, double d2) {
        Logger.d(Tag, String.format("getServerList(gameVersion: %s)", str));
        if (!isUseDefaultAgent || iServerListListener == null) {
            return;
        }
        iServerListListener.onGetResult(false, "{}");
    }

    public boolean isUseDefault() {
        return isUseDefaultAgent;
    }

    public void pingServerList(String str, IPingServerListener iPingServerListener, double d2) {
        Logger.d(Tag, String.format("pingServerList(paramsJsonStr: %s)", str));
        if (!isUseDefaultAgent || iPingServerListener == null) {
            return;
        }
        iPingServerListener.onUpdatePing(false, "{}", true);
    }
}
