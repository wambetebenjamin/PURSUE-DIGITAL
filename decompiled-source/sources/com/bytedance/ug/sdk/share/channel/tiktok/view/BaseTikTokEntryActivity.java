package com.bytedance.ug.sdk.share.channel.tiktok.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.bytedance.sdk.open.tiktok.TikTokOpenApiFactory;
import com.bytedance.sdk.open.tiktok.api.TikTokOpenApi;
import com.bytedance.sdk.open.tiktok.common.handler.IApiEventHandler;
import com.bytedance.sdk.open.tiktok.common.model.BaseReq;
import com.bytedance.sdk.open.tiktok.common.model.BaseResp;
import com.bytedance.sdk.open.tiktok.share.Share;
import com.bytedance.ug.sdk.share.api.callback.l;
import com.bytedance.ug.sdk.share.impl.manager.d;
/* loaded from: classes8.dex */
public class BaseTikTokEntryActivity extends Activity implements IApiEventHandler {
    public TikTokOpenApi mTTOpenApi;

    @Override // com.bytedance.sdk.open.tiktok.common.handler.IApiEventHandler
    public void onErrorIntent(Intent intent) {
    }

    @Override // com.bytedance.sdk.open.tiktok.common.handler.IApiEventHandler
    public void onReq(BaseReq baseReq) {
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TikTokOpenApi create = TikTokOpenApiFactory.create(this);
        this.mTTOpenApi = create;
        create.handleIntent(getIntent(), this);
        Intent launchIntentForPackage = isTaskRoot() ? getLaunchIntentForPackage(this, getPackageName()) : null;
        finish();
        if (launchIntentForPackage != null) {
            try {
                startActivity(launchIntentForPackage);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // com.bytedance.sdk.open.tiktok.common.handler.IApiEventHandler
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == 4) {
            Share.Response response = (Share.Response) baseResp;
            l shareEventCallback = d.getInstance().getShareEventCallback();
            if (shareEventCallback == null) {
                finish();
                return;
            }
            com.bytedance.ug.sdk.share.api.entity.l lVar = new com.bytedance.ug.sdk.share.api.entity.l(10014, d.getInstance().getCurrentShareChannelType());
            int i2 = response.errorCode;
            if (i2 == 0) {
                lVar.errorCode = 10000;
            } else if (i2 == -2) {
                lVar.errorCode = 10001;
            } else {
                lVar.errorCode = 10002;
            }
            lVar.detailErrorCode = i2;
            lVar.extraBundle = response.extras;
            lVar.errorMsg = response.errorMsg;
            shareEventCallback.onShareResultEvent(lVar);
            d.getInstance().resetShareEventCallback();
        }
    }

    private Intent getLaunchIntentForPackage(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return launchIntentForPackage;
        }
        if (!launchIntentForPackage.hasCategory("android.intent.category.LAUNCHER")) {
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
        }
        launchIntentForPackage.setPackage(null);
        launchIntentForPackage.addFlags(2097152);
        return launchIntentForPackage;
    }
}
