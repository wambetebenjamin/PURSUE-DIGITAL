package com.fcm;

import android.content.Context;
import com.bytedance.push.utils.a;
import com.bytedance.push.utils.i;
import com.google.firebase.iid.ServiceStarter;
import java.util.Arrays;
import java.util.Collections;
/* compiled from: ManifestChecker.java */
/* loaded from: classes8.dex */
class a {
    private static boolean b(String str, Context context) {
        return true;
    }

    a() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(String str, Context context) {
        return a(context, str) & b(str, context);
    }

    private static boolean a(Context context, String str) {
        return i.checkService(context, str, "Fcm Push Error", Arrays.asList(a.C0171a.create("com.fcm.service.SSGcmListenerService").setProcess(context.getPackageName()).addIntentFilter(new a.b(Collections.singletonList(ServiceStarter.ACTION_MESSAGING_EVENT))).build(), a.C0171a.create("com.fcm.service.FcmRegistrationJobIntentService").setProcess(context.getPackageName()).setPermission("android.permission.BIND_JOB_SERVICE").build()));
    }
}
