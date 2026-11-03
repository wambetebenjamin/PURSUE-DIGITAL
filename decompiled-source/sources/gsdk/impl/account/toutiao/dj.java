package gsdk.impl.account.toutiao;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.text.Regex;
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007J \u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/utils/StringFormatVerifyUtils;", "", "()V", "SUCCESSION_CODE_FORMAT_PATTERN", "", "verifyStringFormat", "", "pattern", "content", "verifyStringLength", "min", "", "max", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class dj {

    /* renamed from: a  reason: collision with root package name */
    public static final dj f19565a = new dj();

    /* renamed from: b  reason: collision with root package name */
    public static final String f19566b = "(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[a-z])(?=.*[A-Z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[0-9])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[0-9])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[a-z])(?=[0-9]))|((?=.*[A-Z])(?=.*[0-9])(?=[a-z]))|((?=.*[a-z])(?=.*[A-Z])(?=[0-9]))|((?=.*[a-z])(?=.*[0-9])(?=[A-Z]))|((?=.*[0-9])(?=.*[A-Z])(?=[a-z]))|((?=.*[0-9])(?=.*[a-z])(?=[A-Z])))^[a-zA-Z0-9]{8,20}$";

    private dj() {
    }

    @JvmStatic
    public static final boolean a(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return new Regex(str).matches(str2);
    }

    public final boolean a(int i2, int i3, String str) {
        int length = str != null ? str.length() : 0;
        return i2 <= length && length <= i3;
    }
}
