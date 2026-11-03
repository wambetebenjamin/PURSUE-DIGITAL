package com.bytedance.push.interfaze;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.util.Pair;
import org.json.JSONObject;
/* compiled from: IThirdSupportService.java */
/* loaded from: classes2.dex */
public interface u extends com.bytedance.push.e {
    public static final String KEY_PUSH_TOKEN_SENT = "push_token_sent";
    public static final String LOG_TYPE = "ss_push";
    public static final String SEND_PUSH_TOKEN_URL = com.ss.android.pushmanager.d.getSendPushTokenUrl();

    boolean allowPullUp(String str);

    boolean canKeepPackageAlive(Context context, String str);

    String getAdmPayloadName();

    String getFcmPayloadName();

    JSONObject getMessage(byte[] bArr, boolean z);

    String getMessageV2(int i2, byte[] bArr, boolean z);

    Pair<String, String> getPushConfig(int i2);

    String getToken(Context context, int i2);

    com.ss.android.push.e<String, String, String> getUmConfig();

    void hackJobHandler(Service service);

    boolean isMainProcess(Context context);

    void onClickNotPassThroughNotification(Context context, String str, int i2, String str2);

    void registerSenderFailed(int i2, String str, String str2);

    void registerSenderSuccessAndUploadToken(Context context, int i2, String str);

    void sendMonitor(Context context, String str, JSONObject jSONObject);

    void sendToken(Context context, com.bytedance.push.third.d dVar);

    boolean shouldShowUpgradeDialog(Activity activity);
}
