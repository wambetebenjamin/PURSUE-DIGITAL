package com.bytedance.push.notification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.bytedance.push.interfaze.q;
import com.bytedance.push.interfaze.v;
import com.bytedance.push.settings.PushOnlineSettings;
import com.bytedance.push.settings.SettingsManager;
import com.bytedance.push.utils.o;
import com.bytedance.push.utils.r;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class PushActivity extends Activity {
    private static final String EXTRA_SIG = "sig";
    private static final String PUBLIC_KEY = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEul5lp8sfpVIXgDHaLBjSYt4h0vSF\nSxh0kbpM5nXdkrz8UxQuIOYKO2PzySqmzxL5Ts3rHbMzG8wV+QzS0DVKBQ==";
    private static final String TAG = "PushActivity";

    protected boolean isPassThough() {
        return false;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        parseIntent();
        finish();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent == null) {
            onPushActivityCreateEvent(true, null, false, -1, false);
            com.bytedance.push.i.logger().e(TAG, "intent is null");
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            for (String str : extras.keySet()) {
                com.bytedance.push.i.logger().d(TAG, "onCreate: key = " + str + " , val = " + extras.get(str));
            }
        }
        boolean booleanExtra = com.ss.android.push.c.getBooleanExtra(intent, com.ss.android.pushmanager.d.BUNDLE_FROM_NOTIFICATION, false);
        String stringExtra = intent.getStringExtra(EXTRA_SIG);
        String stringExtra2 = intent.getStringExtra("push_body");
        if (!booleanExtra) {
            com.bytedance.push.i.logger().e(TAG, "fromNotification = false");
            onPushActivityCreateEvent(false, stringExtra2, booleanExtra, -1, false);
        } else if (TextUtils.isEmpty(stringExtra2)) {
            com.bytedance.push.i.logger().e(TAG, "msgBody is null");
            onPushActivityCreateEvent(false, stringExtra2, booleanExtra, -1, false);
        } else {
            int checkSign = ((PushOnlineSettings) SettingsManager.obtain(getApplicationContext(), PushOnlineSettings.class)).checkSign();
            boolean checkSource = checkSource(stringExtra2, stringExtra, checkSign);
            int intExtra = com.ss.android.push.c.getIntExtra(intent, com.ss.android.pushmanager.d.KEY_MESSAGE_FROM, 0);
            try {
                if (com.bytedance.push.third.f.inst(getApplicationContext()).isDirectOpenActivitySender(intExtra)) {
                    if (com.bytedance.push.third.f.inst(getApplicationContext()).isNeedUnzip(intExtra)) {
                        stringExtra2 = r.uncompressToString(Base64.decode(stringExtra2, 8));
                        com.bytedance.push.utils.h.d(TAG, "Unzipped msgBody is : " + stringExtra2);
                    }
                    JSONObject jSONObject = new JSONObject(stringExtra2);
                    jSONObject.put(com.bytedance.push.g.KEY_PASS_THROUGH, 0);
                    stringExtra2 = jSONObject.toString();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            String str2 = stringExtra2;
            com.bytedance.push.interfaze.k pushHandler = com.bytedance.push.i.pushHandler();
            onPushActivityCreateEvent(false, str2, booleanExtra, checkSign, checkSource);
            if (checkSource) {
                pushHandler.onClickMsg(this, str2, intExtra);
                return;
            }
            v vVar = com.bytedance.push.i.get().getConfiguration().verifyFailedListener;
            if (vVar != null) {
                try {
                    vVar.onVerifyFailed(this, new com.bytedance.push.g(new JSONObject(str2)), intExtra);
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    private boolean checkSource(String str, String str2, int i2) {
        com.bytedance.push.utils.h.v(TAG, "checkSource " + str);
        if (i2 <= 0) {
            return true;
        }
        if (isPassThough() || ((com.bytedance.push.safe.a) com.ss.android.ug.bus.b.getService(com.bytedance.push.safe.a.class)).isFromPassThough(str)) {
            com.bytedance.push.utils.h.v(TAG, "checkSource true " + str);
            return true;
        }
        long d2 = com.ss.android.message.util.b.d();
        if (!TextUtils.isEmpty(str2)) {
            o.a verifySign = com.bytedance.push.utils.o.verifySign(str, PUBLIC_KEY, str2);
            monitorVerifyResult(verifySign.result, verifySign.msg, com.ss.android.message.util.b.d() - d2, str, str2);
            if (verifySign.result) {
                com.bytedance.push.utils.h.v(TAG, "sign success" + str);
            } else {
                com.bytedance.push.utils.h.e(TAG, "verify sign failed");
            }
            if (i2 < 2) {
                return true;
            }
            return verifySign.result;
        }
        com.bytedance.push.utils.h.e(TAG, "sign is null or isn't string");
        monitorVerifyResult(false, "sign is empty", com.ss.android.message.util.b.d() - d2, str, str2);
        return i2 < 2;
    }

    private void monitorVerifyResult(boolean z, String str, long j2, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put(com.bytedance.pipo.payment.common.lib.c.de, str);
            jSONObject.put("duration", j2);
            jSONObject2.put("msg_body", str2);
            jSONObject2.put(EXTRA_SIG, str3);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        ((q) com.ss.android.ug.bus.b.getService(q.class)).monitorStatusAndDuration("push_source_verify_sign_v2", z ? 1 : 0, jSONObject, jSONObject2);
    }

    private void onPushActivityCreateEvent(boolean z, String str, boolean z2, int i2, boolean z3) {
        com.bytedance.push.interfaze.h hVar = com.bytedance.push.i.get().getConfiguration().mEventCallback;
        if (hVar != null) {
            JSONObject jSONObject = new JSONObject();
            int i3 = 1;
            try {
                jSONObject.put("intent_is_null", z ? 1 : 0);
                jSONObject.put(com.ss.android.pushmanager.d.BUNDLE_FROM_NOTIFICATION, z2 ? 1 : 0);
                jSONObject.put("sign_flag", i2);
                if (!z3) {
                    i3 = 0;
                }
                jSONObject.put("verify_success", i3);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            hVar.onEventV3("push_activity_on_create", jSONObject);
        }
    }
}
