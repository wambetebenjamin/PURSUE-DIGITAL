package gsdk.impl.pay.DEFAULT;

import kotlin.Metadata;
import org.json.JSONObject;
/* compiled from: IPayBridge.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/bytedance/ttgame/api/IPayBridge;", "", "getPayResult", "Lorg/json/JSONObject;", "isBlockedCloseWeb", "", "isPaySuccessful", "pay_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public interface a {
    JSONObject getPayResult();

    boolean isBlockedCloseWeb();

    boolean isPaySuccessful();
}
