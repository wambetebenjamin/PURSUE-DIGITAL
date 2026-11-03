package com.bytedance.ttgame.module.pay.api;

import android.content.Context;
import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import java.util.List;
/* loaded from: classes6.dex */
public interface ITTPayService extends IModuleApi {
    public static final String TAG = "{PayService}";

    default void compensate(String str, String str2, String str3) {
    }

    void init(Context context, PayConfig payConfig);

    @InternalApi
    default boolean isFeatureSupported(String str) {
        return false;
    }

    @InternalApi
    default int isProductDetailSupported() {
        return 0;
    }

    @InternalApi
    default boolean isServiceConnected() {
        return false;
    }

    void pay(Context context, PayInfo payInfo, ICallback<PayResult> iCallback);

    default void queryProductDetails(String str, int i2, List<String> list, ICallback<ProductList> iCallback) {
    }

    default void setCompensateCallback(ICallback<PayResult> iCallback) {
    }
}
