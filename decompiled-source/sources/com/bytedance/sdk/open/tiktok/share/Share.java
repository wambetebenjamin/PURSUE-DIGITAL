package com.bytedance.sdk.open.tiktok.share;

import android.os.Bundle;
import android.util.Log;
import com.bytedance.sdk.open.tiktok.base.AnchorObject;
import com.bytedance.sdk.open.tiktok.base.MediaContent;
import com.bytedance.sdk.open.tiktok.base.MicroAppInfo;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.tiktok.common.model.BaseReq;
import com.bytedance.sdk.open.tiktok.common.model.BaseResp;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes3.dex */
public class Share {
    public static final int IMAGE = 1;
    private static final String TAG = "Aweme.OpenSDK.Share";
    public static final int VIDEO = 0;

    /* loaded from: classes3.dex */
    public static class Request extends BaseReq {
        public HashMap<String, Integer> extraShareOptions;
        public AnchorObject mAnchorInfo;
        public String mAnchorSourceType;
        public String mCallerPackage;
        public String mClientKey;
        public String mExtra;
        public ArrayList<String> mHashTagList;
        public MediaContent mMediaContent;
        public MicroAppInfo mMicroAppInfo;
        public String mState;
        public int mTargetSceneType = 0;

        @Override // com.bytedance.sdk.open.tiktok.common.model.BaseReq
        public int getType() {
            return 3;
        }

        public Request() {
        }

        public Request(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.bytedance.sdk.open.tiktok.common.model.BaseReq
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.mCallerPackage = bundle.getString(ParamKeyConstants.ShareParams.CALLER_PKG);
            this.callerLocalEntry = bundle.getString(ParamKeyConstants.ShareParams.CALLER_LOCAL_ENTRY);
            this.mState = bundle.getString(ParamKeyConstants.ShareParams.STATE);
            this.mClientKey = bundle.getString(ParamKeyConstants.ShareParams.CLIENT_KEY);
            this.mTargetSceneType = bundle.getInt(ParamKeyConstants.ShareParams.SHARE_TARGET_SCENE, 0);
            this.mHashTagList = bundle.getStringArrayList(ParamKeyConstants.ShareParams.SHARE_HASHTAG_LIST);
            this.mMediaContent = MediaContent.Builder.fromBundle(bundle);
            this.mMicroAppInfo = MicroAppInfo.unserialize(bundle);
            this.mAnchorInfo = AnchorObject.unserialize(bundle);
        }

        @Override // com.bytedance.sdk.open.tiktok.common.model.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString(ParamKeyConstants.ShareParams.CALLER_LOCAL_ENTRY, this.callerLocalEntry);
            bundle.putString(ParamKeyConstants.ShareParams.CLIENT_KEY, this.mClientKey);
            bundle.putString(ParamKeyConstants.ShareParams.CALLER_PKG, this.mCallerPackage);
            bundle.putString(ParamKeyConstants.ShareParams.STATE, this.mState);
            bundle.putAll(MediaContent.Builder.toBundle(this.mMediaContent));
            bundle.putInt(ParamKeyConstants.ShareParams.SHARE_TARGET_SCENE, this.mTargetSceneType);
            ArrayList<String> arrayList = this.mHashTagList;
            if (arrayList != null && arrayList.size() > 0) {
                bundle.putString(ParamKeyConstants.ShareParams.SHARE_DEFAULT_HASHTAG, this.mHashTagList.get(0));
                bundle.putStringArrayList(ParamKeyConstants.ShareParams.SHARE_HASHTAG_LIST, this.mHashTagList);
            }
            MicroAppInfo microAppInfo = this.mMicroAppInfo;
            if (microAppInfo != null) {
                microAppInfo.serialize(bundle);
            }
            AnchorObject anchorObject = this.mAnchorInfo;
            if (anchorObject == null || anchorObject.getAnchorBusinessType() != 10) {
                return;
            }
            this.mAnchorInfo.serialize(bundle);
        }

        @Override // com.bytedance.sdk.open.tiktok.common.model.BaseReq
        public boolean checkArgs() {
            MediaContent mediaContent = this.mMediaContent;
            if (mediaContent == null) {
                Log.e(Share.TAG, "checkArgs fail ,mediaContent is null");
                return false;
            }
            return mediaContent.checkArgs();
        }
    }

    /* loaded from: classes3.dex */
    public static class Response extends BaseResp {
        public String state;
        public int subErrorCode;

        @Override // com.bytedance.sdk.open.tiktok.common.model.BaseResp
        public int getType() {
            return 4;
        }

        public Response() {
        }

        public Response(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.bytedance.sdk.open.tiktok.common.model.BaseResp
        public void fromBundle(Bundle bundle) {
            this.errorCode = bundle.getInt(ParamKeyConstants.ShareParams.ERROR_CODE);
            this.errorMsg = bundle.getString(ParamKeyConstants.ShareParams.ERROR_MSG);
            this.extras = bundle.getBundle(ParamKeyConstants.BaseParams.EXTRA);
            this.state = bundle.getString(ParamKeyConstants.ShareParams.STATE);
            this.subErrorCode = bundle.getInt(ParamKeyConstants.ShareParams.SHARE_SUB_ERROR_CODE, -1000);
        }

        @Override // com.bytedance.sdk.open.tiktok.common.model.BaseResp
        public void toBundle(Bundle bundle) {
            bundle.putInt(ParamKeyConstants.ShareParams.ERROR_CODE, this.errorCode);
            bundle.putString(ParamKeyConstants.ShareParams.ERROR_MSG, this.errorMsg);
            bundle.putInt(ParamKeyConstants.ShareParams.TYPE, getType());
            bundle.putBundle(ParamKeyConstants.BaseParams.EXTRA, this.extras);
            bundle.putString(ParamKeyConstants.ShareParams.STATE, this.state);
            bundle.putInt(ParamKeyConstants.ShareParams.SHARE_SUB_ERROR_CODE, this.subErrorCode);
        }
    }
}
