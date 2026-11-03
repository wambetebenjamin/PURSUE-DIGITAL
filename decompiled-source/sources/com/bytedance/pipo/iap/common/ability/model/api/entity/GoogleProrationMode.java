package com.bytedance.pipo.iap.common.ability.model.api.entity;

import com.bytedance.pipo.iap.common.ability.model.PIPOContextHelper;
import com.bytedance.pipo.payment.common.lib.support.a;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GoogleProrationMode.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0001\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/api/entity/GoogleProrationMode;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "SERVER_ERROR", "UNKNOWN_REPLACEMENT_MODE", "WITH_TIME_PRORATION", "CHARGE_PRORATED_PRICE", "WITHOUT_PRORATION", "CHARGE_FULL_PRICE", "DEFERRED", "Companion", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public enum GoogleProrationMode {
    SERVER_ERROR(-1),
    UNKNOWN_REPLACEMENT_MODE(0),
    WITH_TIME_PRORATION(1),
    CHARGE_PRORATED_PRICE(2),
    WITHOUT_PRORATION(3),
    CHARGE_FULL_PRICE(5),
    DEFERRED(6);
    
    public static final Companion Companion = new Companion(null);
    private final int value;

    @JvmStatic
    public static final int parseGoogleChangeType(PIPOContextHelper.PIPOContext pIPOContext) {
        return Companion.parseGoogleChangeType(pIPOContext);
    }

    GoogleProrationMode(int i2) {
        this.value = i2;
    }

    public final int getValue() {
        return this.value;
    }

    /* compiled from: GoogleProrationMode.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/api/entity/GoogleProrationMode$Companion;", "", "()V", "parseGoogleChangeType", "", "context", "Lcom/bytedance/pipo/iap/common/ability/model/PIPOContextHelper$PIPOContext;", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final int parseGoogleChangeType(PIPOContextHelper.PIPOContext context) {
            int value;
            Intrinsics.checkNotNullParameter(context, "context");
            String str = context.GP_changeType;
            if (Intrinsics.areEqual(str, GoogleProrationMode.UNKNOWN_REPLACEMENT_MODE.name())) {
                value = GoogleProrationMode.UNKNOWN_REPLACEMENT_MODE.getValue();
            } else if (Intrinsics.areEqual(str, GoogleProrationMode.WITH_TIME_PRORATION.name())) {
                value = GoogleProrationMode.WITH_TIME_PRORATION.getValue();
            } else if (Intrinsics.areEqual(str, GoogleProrationMode.CHARGE_PRORATED_PRICE.name())) {
                value = GoogleProrationMode.CHARGE_PRORATED_PRICE.getValue();
            } else if (Intrinsics.areEqual(str, GoogleProrationMode.WITHOUT_PRORATION.name())) {
                value = GoogleProrationMode.WITHOUT_PRORATION.getValue();
            } else if (Intrinsics.areEqual(str, GoogleProrationMode.CHARGE_FULL_PRICE.name())) {
                value = GoogleProrationMode.CHARGE_FULL_PRICE.getValue();
            } else if (Intrinsics.areEqual(str, GoogleProrationMode.DEFERRED.name())) {
                value = GoogleProrationMode.DEFERRED.getValue();
            } else {
                a.a().e().c("GoogleProrationMode", "parse error, pipoOrderId: " + ((Object) context.PipoTradeOrderId) + " changeType: " + ((Object) context.GP_changeType));
                value = GoogleProrationMode.SERVER_ERROR.getValue();
            }
            a.a().e().b("GoogleProrationMode", "changeType: " + ((Object) context.GP_changeType) + ", result: " + value);
            return value;
        }
    }
}
