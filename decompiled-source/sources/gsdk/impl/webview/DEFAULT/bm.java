package gsdk.impl.webview.DEFAULT;

import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
/* compiled from: IH5ChooseFilePresenter.java */
/* loaded from: classes10.dex */
public interface bm {
    void a(int i2, int i3, Intent intent);

    void a(ValueCallback<Uri[]> valueCallback);

    void a(ValueCallback<Uri[]> valueCallback, String str);

    void a(ValueCallback<Uri[]> valueCallback, String str, String str2);

    void b(int i2, int i3, Intent intent);

    void b(ValueCallback<Uri> valueCallback);

    void b(ValueCallback<Uri> valueCallback, String str, String str2);

    void c(int i2, int i3, Intent intent);
}
