package gsdk.impl.pay.DEFAULT;

import android.app.Activity;
import android.content.Context;
import com.bytedance.ttgame.channelapi.IChannelCallback;
import com.bytedance.ttgame.rocketapi.minors.IMinorsCallback;
import com.bytedance.ttgame.rocketapi.pay.IPayCallback;
import com.bytedance.ttgame.rocketapi.pay.IQueryCountryCodeCallback;
import com.bytedance.ttgame.rocketapi.pay.IQueryProductsCallback;
import com.bytedance.ttgame.rocketapi.pay.IQueryPurchaseCallback;
import com.bytedance.ttgame.rocketapi.pay.QueryGoodsParams;
import com.bytedance.ttgame.rocketapi.pay.QueryPurchaseReason;
import com.bytedance.ttgame.rocketapi.pay.RocketGoods;
import com.bytedance.ttgame.rocketapi.pay.RocketPayInfo;
import com.bytedance.ttgame.rocketapi.pay.RocketPayResult;
import com.bytedance.ttgame.rocketapi.pay.RocketProductInfo;
import com.bytedance.ttgame.rocketapi.pay.RocketPurchaseRecord;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
/* compiled from: IPayDelegate.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J\b\u0010\b\u001a\u00020\u0003H&J$\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\nj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b`\fH&J\n\u0010\r\u001a\u0004\u0018\u00010\u000eH&J\u001e\u0010\u000f\u001a\u00020\u00032\u0014\u0010\u0010\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0012\u0018\u00010\u0011H&J\u001a\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0016H&J*\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cH&J,\u0010\u001e\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\u001cH&J\u001a\u0010$\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 2\b\u0010\u0010\u001a\u0004\u0018\u00010%H&J\"\u0010&\u001a\u00020\u00032\u000e\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00122\b\u0010\u0010\u001a\u0004\u0018\u00010(H&J&\u0010)\u001a\u00020\u00032\b\u0010*\u001a\u0004\u0018\u00010\u00052\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010\u0010\u001a\u0004\u0018\u00010-H&J,\u0010.\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010/\u001a\u0004\u0018\u00010\u001b2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cH&J\u0018\u00100\u001a\u00020\u00032\u000e\u00101\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cH&J\u001c\u00102\u001a\u00020\u00032\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00120\u0011H&J\"\u00103\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0016H&¨\u00065"}, d2 = {"Lcom/bytedance/ttgame/api/IPayDelegate;", "", "compensate", "", "openId", "", "roleId", "serverId", "fetchProducts", "getNoConsumableMap", "Ljava/util/HashMap;", "Lcom/bytedance/ttgame/rocketapi/pay/RocketPurchaseRecord;", "Lkotlin/collections/HashMap;", "getPayBridge", "Lcom/bytedance/ttgame/api/IPayBridge;", "getProductsList", com.bytedance.bdturing.methods.l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/channelapi/IChannelCallback;", "", "Lcom/bytedance/ttgame/rocketapi/pay/RocketProductInfo;", "getV2MinorLimit", "country", "Lcom/bytedance/ttgame/rocketapi/minors/IMinorsCallback;", "pay", "activity", "Landroid/app/Activity;", "rocketPayInfo", "Lcom/bytedance/ttgame/rocketapi/pay/RocketPayInfo;", "Lcom/bytedance/ttgame/rocketapi/pay/IPayCallback;", "Lcom/bytedance/ttgame/rocketapi/pay/RocketPayResult;", "queryGoodsWithActivity", "context", "Landroid/content/Context;", "queryGoodsParams", "Lcom/bytedance/ttgame/rocketapi/pay/QueryGoodsParams;", "Lcom/bytedance/ttgame/rocketapi/pay/RocketGoods;", "queryGooglePlayCountryCodeAsync", "Lcom/bytedance/ttgame/rocketapi/pay/IQueryCountryCodeCallback;", "queryProductDetails", "productIds", "Lcom/bytedance/ttgame/rocketapi/pay/IQueryProductsCallback;", "queryPurchaseByProductId", "productId", "queryReason", "Lcom/bytedance/ttgame/rocketapi/pay/QueryPurchaseReason;", "Lcom/bytedance/ttgame/rocketapi/pay/IQueryPurchaseCallback;", "receiveRewards", "payInfo", "registerExtraPayCallback", "payResultICallback", "registerRewardsOutside", "setV2MinorLimit", "content", "pay_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public interface b {
    void a();

    void a(Activity activity, RocketPayInfo rocketPayInfo, IPayCallback<RocketPayResult> iPayCallback);

    void a(Context context, IQueryCountryCodeCallback iQueryCountryCodeCallback);

    void a(Context context, QueryGoodsParams queryGoodsParams, IPayCallback<RocketGoods> iPayCallback);

    void a(Context context, RocketPayInfo rocketPayInfo, IPayCallback<RocketPayResult> iPayCallback);

    void a(IChannelCallback<List<RocketProductInfo>> iChannelCallback);

    void a(IPayCallback<RocketPayResult> iPayCallback);

    void a(String str, IMinorsCallback iMinorsCallback);

    void a(String str, QueryPurchaseReason queryPurchaseReason, IQueryPurchaseCallback iQueryPurchaseCallback);

    void a(String str, String str2, IMinorsCallback iMinorsCallback);

    void a(String str, String str2, String str3);

    void a(List<String> list, IQueryProductsCallback iQueryProductsCallback);

    HashMap<String, RocketPurchaseRecord> b();

    void b(IChannelCallback<List<String>> iChannelCallback);

    a c();
}
