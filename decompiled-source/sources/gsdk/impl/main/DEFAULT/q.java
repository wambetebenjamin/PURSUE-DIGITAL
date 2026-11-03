package gsdk.impl.main.DEFAULT;

import com.bytedance.retrofit2.mime.TypedOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
/* compiled from: MonitorAdditionalPart.java */
/* loaded from: classes10.dex */
class q implements TypedOutput {

    /* renamed from: a  reason: collision with root package name */
    private final String f20502a;

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, String> f20503b;

    /* renamed from: c  reason: collision with root package name */
    private final String f20504c;

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String md5Stub() {
        return null;
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String mimeType() {
        return "text/plain";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(String str, String str2, Map<String, String> map) {
        this.f20502a = str;
        this.f20503b = map;
        this.f20504c = str2 == null ? "" : str2;
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String fileName() {
        return r.a(this.f20502a, this.f20503b);
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public long length() {
        return this.f20504c.getBytes().length;
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.f20504c.getBytes());
    }
}
