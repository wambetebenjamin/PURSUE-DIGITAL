package gsdk.impl.main.DEFAULT;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.channel.account.ErrorConstants;
import com.bytedance.ttgame.module.gameinfo.api.common.GameInfoErrorCode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: GameInfoInnerTools.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/GameInfoInnerTools;", "", "()V", "Companion", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class u {

    /* renamed from: a  reason: collision with root package name */
    public static final a f20511a = new a(null);

    /* renamed from: b  reason: collision with root package name */
    private static final String f20512b = "success";

    /* renamed from: c  reason: collision with root package name */
    private static final String f20513c = "参数错误";

    /* renamed from: d  reason: collision with root package name */
    private static final String f20514d = "token错误";

    /* renamed from: e  reason: collision with root package name */
    private static final String f20515e = "区服列表为空";

    /* renamed from: f  reason: collision with root package name */
    private static final String f20516f = "sdk_open_id错误";

    /* renamed from: g  reason: collision with root package name */
    private static final String f20517g = "请求被风控";

    /* renamed from: h  reason: collision with root package name */
    private static final String f20518h = ag.f20408d;

    /* renamed from: i  reason: collision with root package name */
    private static final String f20519i = "未知错误";

    /* renamed from: j  reason: collision with root package name */
    private static final String f20520j = "js api 异常";
    private static final String k = ag.f20406b;

    /* compiled from: GameInfoInnerTools.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u001fR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006¨\u0006 "}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/GameInfoInnerTools$Companion;", "", "()V", "JS_ERROR_MSG", "", "getJS_ERROR_MSG", "()Ljava/lang/String;", "NETWORK_ERROR_MSG", "getNETWORK_ERROR_MSG", "OPEN_ID_ERROR_MSG", "getOPEN_ID_ERROR_MSG", "PARAM_ERROR_MSG", "getPARAM_ERROR_MSG", "RISK_CONTROL_MSG", "getRISK_CONTROL_MSG", "SERVER_ERROR_MSG", "getSERVER_ERROR_MSG", "SERVER_LIST_EMPTY_MSG", "getSERVER_LIST_EMPTY_MSG", "SUCCESS_MSG", "getSUCCESS_MSG", "TOKEN_INVALID_MSG", "getTOKEN_INVALID_MSG", "UNKNOWN_ERROR_MSG", "getUNKNOWN_ERROR_MSG", "convert", "Lcom/bytedance/ttgame/base/GSDKError;", "extraCode", "", "extraMsg", ErrorConstants.ADDITIONAL_INFO, "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/bytedance/ttgame/base/GSDKError;", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private a() {
        }

        public final String a() {
            return u.f20512b;
        }

        public final String b() {
            return u.f20513c;
        }

        public final String c() {
            return u.f20514d;
        }

        public final String d() {
            return u.f20515e;
        }

        public final String e() {
            return u.f20516f;
        }

        public final String f() {
            return u.f20517g;
        }

        public final String g() {
            return u.f20518h;
        }

        public final String h() {
            return u.f20519i;
        }

        public final String i() {
            return u.f20520j;
        }

        public final String j() {
            return u.k;
        }

        public final GSDKError a(Integer num, String str, String str2) {
            String j2;
            String h2 = h();
            int i2 = GameInfoErrorCode.JS_ERROR;
            int i3 = 0;
            if (num != null && num.intValue() == 0) {
                j2 = a();
            } else {
                if (num != null && num.intValue() == -1001) {
                    i2 = GameInfoErrorCode.PARAM_ERROR;
                    h2 = b();
                } else {
                    if ((num != null && num.intValue() == -1002) || (num != null && num.intValue() == -1003)) {
                        i2 = GameInfoErrorCode.TOKEN_INVALID;
                        h2 = c();
                    } else if (num != null && num.intValue() == -1601) {
                        i2 = GameInfoErrorCode.SERVER_LIST_EMPTY;
                        h2 = d();
                    } else if (num != null && num.intValue() == -2016) {
                        i2 = GameInfoErrorCode.OPEN_ID_ERROR;
                        h2 = e();
                    } else if (num != null && num.intValue() == -4001) {
                        i2 = GameInfoErrorCode.RISK_CONTROL;
                        h2 = f();
                    } else {
                        if (((num != null && num.intValue() == -5000) || (num != null && num.intValue() == 111)) || (num != null && num.intValue() == 112)) {
                            i2 = GameInfoErrorCode.SERVER_ERROR;
                            h2 = g();
                        } else {
                            if ((num != null && num.intValue() == -133001) || (num != null && num.intValue() == -30001)) {
                                i3 = 1;
                            }
                            if (i3 != 0) {
                                j2 = j();
                                i3 = -133001;
                            } else if (num != null && num.intValue() == -138001) {
                                h2 = i();
                            } else {
                                i2 = GameInfoErrorCode.UNKNOWN_ERROR;
                            }
                        }
                    }
                }
                j2 = h2;
                i3 = i2;
            }
            return new GSDKError(i3, j2, num != null ? num.intValue() : -1, str, str2);
        }
    }
}
