package com.bytedance.pipo.payment.common.lib.support.service;

import android.content.Context;
import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.Metadata;
/* compiled from: ISecService.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J<\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003H&J\b\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0014H&J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0003H&¨\u0006\u0018"}, d2 = {"Lcom/bytedance/pipo/payment/common/lib/support/service/ISecService;", "", com.bytedance.pipo.payment.common.lib.support.impl.g.f10978j, "", "encryptedData", com.bytedance.pipo.payment.common.lib.support.impl.g.f10977i, Constants.MessagePayloadKeys.RAW_DATA, "getEnvCode", "getKid", "init", "", "context", "Landroid/content/Context;", RemoteConfigConstants.RequestFieldKey.APP_ID, "region", "Lcom/bytedance/pipo/payment/common/lib/enums/Region;", "did", "iid", com.bytedance.bdturing.setting.g.HOST_KEY, "isEnable", "", "isEnableDynamicKey", "reportRiskInfo", "scene", "payment_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface g {
    void a(Context context, String str, com.bytedance.pipo.payment.common.lib.enums.a aVar, String str2, String str3, String str4);

    void a(String str);

    boolean a();

    String b();

    String b(String str);

    String c(String str);

    boolean c();

    String d();

    /* compiled from: ISecService.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class a {
        public static /* synthetic */ void a(g gVar, Context context, String str, com.bytedance.pipo.payment.common.lib.enums.a aVar, String str2, String str3, String str4, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: init");
            }
            if ((i2 & 32) != 0) {
                str4 = null;
            }
            gVar.a(context, str, aVar, str2, str3, str4);
        }
    }
}
