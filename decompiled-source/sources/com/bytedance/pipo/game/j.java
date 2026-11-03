package com.bytedance.pipo.game;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: OnQueryFromGoogleListener.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0016J \u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007H\u0016¨\u0006\u000b"}, d2 = {"Lcom/bytedance/pipo/game/OnQueryFromGoogleListener;", "", "onQueryInApp", "", "result", "Lcom/bytedance/pipo/game/api/GooglePayResult;", "details", "", "Lcom/bytedance/pipo/game/api/ProductDetails;", "onQuerySubs", "Lcom/bytedance/pipo/game/api/SubscriptionDetails;", "library_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface j {

    /* compiled from: OnQueryFromGoogleListener.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class a {
        public static void a(j jVar, com.bytedance.pipo.game.api.b result, List<com.bytedance.pipo.game.api.p> list) {
            Intrinsics.checkNotNullParameter(result, "result");
        }

        public static void b(j jVar, com.bytedance.pipo.game.api.b result, List<com.bytedance.pipo.game.api.s> list) {
            Intrinsics.checkNotNullParameter(result, "result");
        }
    }

    void a(com.bytedance.pipo.game.api.b bVar, List<com.bytedance.pipo.game.api.p> list);

    void b(com.bytedance.pipo.game.api.b bVar, List<com.bytedance.pipo.game.api.s> list);
}
