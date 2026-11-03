package gsdk.impl.main.DEFAULT;

import com.bytedance.retrofit2.mime.TypedFile;
import com.bytedance.retrofit2.mime.TypedOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import kotlin.text.Typography;
/* compiled from: MonitorFiledPart.java */
/* loaded from: classes10.dex */
class r implements TypedOutput {

    /* renamed from: a  reason: collision with root package name */
    private static final char f20505a = '\r';

    /* renamed from: b  reason: collision with root package name */
    private static final char f20506b = '\n';

    /* renamed from: c  reason: collision with root package name */
    private final TypedFile f20507c;

    /* renamed from: d  reason: collision with root package name */
    private final Map<String, String> f20508d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(TypedFile typedFile, Map<String, String> map) {
        this.f20507c = typedFile;
        this.f20508d = map;
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String fileName() {
        return a(this.f20507c.fileName(), this.f20508d);
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String mimeType() {
        return this.f20507c.mimeType();
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public long length() {
        return this.f20507c.length();
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public void writeTo(OutputStream outputStream) throws IOException {
        this.f20507c.writeTo(outputStream);
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String md5Stub() {
        return this.f20507c.md5Stub();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str, Map<String, String> map) {
        StringBuilder append = new StringBuilder(a(str)).append(Typography.quote);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            append.append("; ").append(b(entry.getKey())).append("=\"").append(a(entry.getValue())).append(Typography.quote);
        }
        if (append.length() > 0 && append.charAt(append.length() - 1) == '\"') {
            append.deleteCharAt(append.length() - 1);
        }
        return append.toString();
    }

    static String a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("multipart disposition value is null");
        }
        if (str.indexOf(13) >= 0 || str.indexOf(10) >= 0) {
            throw new IllegalArgumentException("multipart disposition value contains CR/LF");
        }
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    static String b(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("multipart disposition attribute name is empty");
        }
        if (str.indexOf(13) >= 0 || str.indexOf(10) >= 0 || str.indexOf(61) >= 0 || str.indexOf(34) >= 0 || str.indexOf(59) >= 0) {
            throw new IllegalArgumentException("multipart disposition attribute name is invalid");
        }
        return str;
    }
}
