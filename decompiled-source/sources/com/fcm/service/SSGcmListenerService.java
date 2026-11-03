package com.fcm.service;

import android.content.Intent;
import android.text.TextUtils;
import com.bytedance.push.i;
import com.fcm.FcmPushAdapter;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
/* loaded from: classes8.dex */
public class SSGcmListenerService extends FirebaseMessagingService {
    public static final String TAG = "SSGcmListenerService";

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {
            i.pushHandler().handlePassThroughMsg(remoteMessage.getData().get(i.thirdService().getFcmPayloadName()), FcmPushAdapter.getFcmPush(), (String) null);
        } catch (Exception e2) {
            i.logger().e(TAG, e2.getMessage());
        }
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
        super.onNewToken(str);
        sendToken(str);
    }

    private void sendToken(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Intent intent = new Intent(this, FcmRegistrationJobIntentService.class);
            intent.putExtra(FcmRegistrationJobIntentService.EXTRA_TOKEN, str);
            FcmRegistrationJobIntentService.enqueueWork(this, intent);
        } catch (Throwable th) {
            i.logger().e(TAG, th.getMessage());
        }
    }
}
