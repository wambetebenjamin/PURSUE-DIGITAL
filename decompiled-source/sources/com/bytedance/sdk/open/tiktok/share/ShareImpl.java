package com.bytedance.sdk.open.tiktok.share;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.librarian.c;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.tiktok.common.handler.IDataHandler;
import com.bytedance.sdk.open.tiktok.share.Share;
import com.bytedance.sdk.open.tiktok.utils.AppUtil;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class ShareImpl {
    private List<IDataHandler> handlers;
    private String mClientKey;
    private Context mContext;

    public ShareImpl(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        this.handlers = arrayList;
        this.mContext = context;
        this.mClientKey = str;
        arrayList.add(new ShareDataHandler());
    }

    public boolean share(String str, String str2, String str3, Share.Request request, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str2) || request == null || this.mContext == null || !request.checkArgs()) {
            return false;
        }
        Bundle bundle = new Bundle();
        if (AppUtil.getPlatformSDKVersion(this.mContext, str2, str4) >= 3) {
            request.toBundle(bundle);
        }
        bundle.putString(ParamKeyConstants.ShareParams.CLIENT_KEY, this.mClientKey);
        bundle.putString(ParamKeyConstants.ShareParams.CALLER_PKG, this.mContext.getPackageName());
        bundle.putString(ParamKeyConstants.ShareParams.CALLER_SDK_VERSION, "1");
        if (TextUtils.isEmpty(request.callerLocalEntry)) {
            bundle.putString(ParamKeyConstants.ShareParams.CALLER_LOCAL_ENTRY, this.mContext.getPackageName() + c.a.DOT + str);
        }
        if (request.extras != null) {
            bundle.putBundle(ParamKeyConstants.BaseParams.EXTRA, request.extras);
        }
        bundle.putString(ParamKeyConstants.BaseParams.CALLER_BASE_OPEN_SDK_NAME, str5);
        bundle.putString(ParamKeyConstants.BaseParams.CALLER_BASE_OPEN_SDK_VERSION, str6);
        bundle.putString("extra", request.mExtra);
        bundle.putString(ParamKeyConstants.ShareParams.ANCHOR_SOURCE_TYPE, request.mAnchorSourceType);
        bundle.putSerializable(ParamKeyConstants.ShareParams.EXTRA_SHARE_OPTIONS, request.extraShareOptions);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str2, buildComponentClassName(str2, str3)));
        intent.putExtras(bundle);
        if (!(this.mContext instanceof Activity)) {
            intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        }
        intent.addFlags(32768);
        try {
            this.mContext.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private String buildComponentClassName(String str, String str2) {
        return "com.ss.android.ugc.aweme." + str2;
    }
}
