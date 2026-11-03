package tt.g.p.f.n;

import com.bytedance.JProtect;
import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
@JProtect
/* loaded from: classes10.dex */
public class bl {
    public static String a() {
        String str = "";
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int i2 = 0;
            String str2 = "";
            String str3 = str2;
            for (int i3 = 5; i3 < stackTrace.length; i3++) {
                try {
                    str2 = stackTrace[i3].getClassName();
                    str3 = stackTrace[i3].getMethodName();
                    i2 = stackTrace[i3].getLineNumber();
                } catch (Throwable unused) {
                }
                str = str + str2 + c.a.DOT + str3 + CertificateUtil.DELIMITER + i2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
            }
        } catch (Throwable unused2) {
        }
        return str;
    }
}
