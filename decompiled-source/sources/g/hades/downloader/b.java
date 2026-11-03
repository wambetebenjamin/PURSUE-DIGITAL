package g.hades.downloader;

import com.bytedance.hades.downloader.impl.DownloaderImpl;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class b {
    public static void a(String str, JSONObject jSONObject) {
        DownloaderImpl.a().onEvent(str, jSONObject.toString());
    }
}
