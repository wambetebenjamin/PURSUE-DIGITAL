package okhttp3;

import com.facebook.internal.security.CertificateUtil;
import java.nio.charset.Charset;
import okhttp3.internal.Util;
import okio.ByteString;
/* loaded from: classes10.dex */
public final class Credentials {
    private Credentials() {
    }

    public static String basic(String str, String str2) {
        return basic(str, str2, Util.ISO_8859_1);
    }

    public static String basic(String str, String str2, Charset charset) {
        return "Basic " + ByteString.encodeString(str + CertificateUtil.DELIMITER + str2, charset).base64();
    }
}
