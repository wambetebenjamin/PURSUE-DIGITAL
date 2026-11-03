package com.bytedance.ttgame.module.push;

import com.fcm.service.SSGcmListenerService;
import com.google.firebase.messaging.RemoteMessage;
import timber.log.Timber;
/* loaded from: classes6.dex */
public class PushFirebaseMessagingService extends SSGcmListenerService {
    @Override // com.fcm.service.SSGcmListenerService, com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    @Override // com.fcm.service.SSGcmListenerService, com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
        super.onNewToken(str);
        Timber.tag("MyFirebaseMsgService").d("Refreshed token: " + str, new Object[0]);
    }
}
