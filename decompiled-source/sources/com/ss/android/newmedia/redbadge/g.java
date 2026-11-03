package com.ss.android.newmedia.redbadge;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
/* compiled from: RedbadgeServiceConnection.java */
/* loaded from: classes9.dex */
public class g implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    private Intent f18394a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f18395b;

    /* renamed from: c  reason: collision with root package name */
    private Context f18396c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(Intent intent, boolean z, Context context) {
        this.f18394a = intent;
        this.f18395b = z;
        this.f18396c = context;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:18:0x0041
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1234)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1018)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
        */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0042 -> B:20:0x0045). Please submit an issue!!! */
    @Override // android.content.ServiceConnection
    public void onServiceConnected(android.content.ComponentName r6, android.os.IBinder r7) {
        /*
            r5 = this;
            java.lang.String r6 = "unbindService"
            java.lang.String r0 = "RedbadgeServiceConnection"
            android.os.Messenger r1 = new android.os.Messenger
            r1.<init>(r7)
            android.os.Message r7 = new android.os.Message
            r7.<init>()
            r2 = 101(0x65, float:1.42E-43)
            r7.what = r2     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            android.os.Bundle r2 = r7.getData()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            java.lang.String r3 = "intent"
            android.content.Intent r4 = r5.f18394a     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            r2.putParcelable(r3, r4)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            r1.send(r7)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            com.bytedance.push.utils.h.d(r0, r6)
            boolean r6 = r5.f18395b     // Catch: java.lang.Throwable -> L41
            if (r6 == 0) goto L45
            android.content.Context r6 = r5.f18396c     // Catch: java.lang.Throwable -> L41
            r6.unbindService(r5)     // Catch: java.lang.Throwable -> L41
            goto L45
        L2e:
            r7 = move-exception
            goto L46
        L30:
            r7 = move-exception
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L2e
            com.bytedance.push.utils.h.d(r0, r6)
            boolean r6 = r5.f18395b     // Catch: java.lang.Throwable -> L41
            if (r6 == 0) goto L45
            android.content.Context r6 = r5.f18396c     // Catch: java.lang.Throwable -> L41
            r6.unbindService(r5)     // Catch: java.lang.Throwable -> L41
            goto L45
        L41:
            r6 = move-exception
            r6.printStackTrace()
        L45:
            return
        L46:
            com.bytedance.push.utils.h.d(r0, r6)
            boolean r6 = r5.f18395b     // Catch: java.lang.Throwable -> L53
            if (r6 == 0) goto L57
            android.content.Context r6 = r5.f18396c     // Catch: java.lang.Throwable -> L53
            r6.unbindService(r5)     // Catch: java.lang.Throwable -> L53
            goto L57
        L53:
            r6 = move-exception
            r6.printStackTrace()
        L57:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.newmedia.redbadge.g.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        try {
            this.f18396c.unbindService(this);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
