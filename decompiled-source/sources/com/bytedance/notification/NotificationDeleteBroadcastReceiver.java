package com.bytedance.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.android.service.manager.PushServiceManager;
import com.bytedance.push.utils.h;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class NotificationDeleteBroadcastReceiver extends BroadcastReceiver {
    public static final String DELETE_ACTION = ".pushsdk.notification.delete.action";
    public static final String KEY_EXTRA = "extra";
    public static final String KEY_GROUP = "group";
    public static final String KEY_MSG_ID = "msg_id";
    public static final String KEY_MSG_TYPE = "msg_type";
    public static final String VALUE_MSG_TYPE_NOTIFICATION = "notification";
    public static final String VALUE_MSG_TYPE_SUMMARY = "summary_notification";
    private final String TAG = "NotificationDeleteBroadcastReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle extras;
        if (!TextUtils.equals(intent.getAction(), context.getPackageName() + DELETE_ACTION) || (extras = intent.getExtras()) == null) {
            return;
        }
        String string = extras.getString(KEY_MSG_TYPE, VALUE_MSG_TYPE_NOTIFICATION);
        if (TextUtils.equals(string, VALUE_MSG_TYPE_NOTIFICATION)) {
            String stringExtra = intent.getStringExtra("group");
            if (!TextUtils.isEmpty(stringExtra)) {
                com.bytedance.notification.helper.a.a().b(stringExtra);
            }
            long longExtra = intent.getLongExtra("msg_id", -1L);
            JSONObject jSONObject = null;
            try {
                String stringExtra2 = intent.getStringExtra("extra");
                if (!TextUtils.isEmpty(stringExtra2)) {
                    jSONObject = new JSONObject(stringExtra2);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            h.d("NotificationDeleteBroadcastReceiver", "on notification delete , msgId is " + longExtra);
            PushServiceManager.get().getPushExternalService().onNotificationDelete(longExtra, jSONObject);
        } else if (TextUtils.equals(string, VALUE_MSG_TYPE_SUMMARY)) {
            String stringExtra3 = intent.getStringExtra("group");
            h.d("NotificationDeleteBroadcastReceiver", "on summary notification delete , group is " + stringExtra3);
            com.bytedance.notification.helper.a.a().c(stringExtra3);
        }
    }
}
