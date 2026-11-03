package com.google.android.play.core.assetpacks;

import android.content.Context;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private static a f16645a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized a a(Context context) {
        a aVar;
        synchronized (d.class) {
            if (f16645a == null) {
                cd cdVar = new cd(null);
                cdVar.b(new p(com.google.android.play.core.assetpacks.internal.ag.a(context)));
                f16645a = cdVar.a();
            }
            aVar = f16645a;
        }
        return aVar;
    }
}
