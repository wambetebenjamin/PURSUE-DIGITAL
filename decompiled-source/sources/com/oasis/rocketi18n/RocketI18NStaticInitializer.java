package com.oasis.rocketi18n;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.security.ProviderInstaller;
import com.oasis.listenermanager.ListenerController;
import com.oasis.listenermanager.listeners.BaseOasisApplicationEventsListener;
import java.lang.ref.WeakReference;
import javax.net.ssl.SSLContext;
/* loaded from: classes9.dex */
public class RocketI18NStaticInitializer {
    private static final String Tag = "com.oasis.rocketi18n.RocketI18NStaticInitializer";

    static {
        String name = RocketI18NStaticInitializer.class.getName();
        Log.d(name, "static code segment.");
        ListenerController.register(new BaseOasisApplicationEventsListener() { // from class: com.oasis.rocketi18n.RocketI18NStaticInitializer.1
            @Override // com.oasis.listenermanager.protocol.IOasisApplicationEvents
            public void onAttachBaseContext(WeakReference<Context> weakReference) {
                Log.d(this.Tag, "Android api version: " + Build.VERSION.SDK_INT);
                if (Build.VERSION.SDK_INT < 20) {
                    Log.d(this.Tag, "attachBaseContext: Android system api version < 20, now add TLSv1.2 support.");
                    try {
                        ProviderInstaller.installIfNeeded(weakReference.get());
                        SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
                        sSLContext.init(null, null, null);
                        sSLContext.createSSLEngine();
                        Log.d(this.Tag, "attachBaseContext: add TLSv1.2 support success.");
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        Log.e(this.Tag, "attachBaseContext: add TLSv1.2 support has an exception: \n" + e2.toString());
                        return;
                    }
                }
                Log.d(this.Tag, "attachBaseContext: Android system api version >= 20, don't need add TLSv1.2 support.");
            }
        });
        Log.d(name, "init onAttachBaseContext listener finish.");
    }
}
