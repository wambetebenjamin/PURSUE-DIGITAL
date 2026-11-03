package com.bytedance.ttgame.module.share.api.entity;

import android.graphics.Bitmap;
import com.bytedance.ttgame.module.share.api.callback.TTShareEventCallback;
import com.bytedance.ttgame.module.share.api.panel.TTShareItemType;
import java.util.ArrayList;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class TTShareModel {
    private TTDYAnchorAppInfo mDyAnchorAppInfo;
    private TTDYMicroAppInfo mDyMicroAppInfo;
    private TTShareEventCallback mEventCallBack;
    private String mExtraParams;
    private String mGroupLabelId;
    private ArrayList<String> mHashTag;
    private String mHiddenImageUrl;
    private Bitmap mImage;
    private String mImageUrl;
    private boolean mIsDisableGetShareInfo;
    private boolean mIsForceUpdate;
    private boolean mIsOnlyShareH5;
    private boolean mIsOnlyShareImage;
    private boolean mIsOnlyShareText;
    private boolean mIsOnlyShareVideo;
    private boolean mIsVideo;
    private String mPanelId;
    private String mQrcodeImageUrl;
    private JSONObject mRequestData;
    private String mResourceId;
    private TTShareContentType mShareContentType;
    private String mShareStrategy;
    private TTShareItemType mShareType;
    private String mTargetUrl;
    private String mText;
    private String mTitle;
    private TTTokenShareInfo mTokenShareInfo;
    private String mVideoUrl;
    private TTWBExtra mWBExtra;

    public TTWBExtra getWBExtra() {
        return this.mWBExtra;
    }

    public void setWBExtra(TTWBExtra tTWBExtra) {
        this.mWBExtra = tTWBExtra;
    }

    private TTShareModel() {
        this.mShareContentType = TTShareContentType.ALL;
    }

    public boolean isOnlyShareImage() {
        return this.mIsOnlyShareImage;
    }

    public void setIsOnlyShareImage(boolean z) {
        this.mIsOnlyShareImage = z;
        this.mShareContentType = TTShareContentType.IMAGE;
    }

    public boolean isOnlyShareText() {
        return this.mIsOnlyShareText;
    }

    public void setIsOnlyShareText(boolean z) {
        this.mIsOnlyShareText = z;
        this.mShareContentType = TTShareContentType.TEXT;
    }

    public boolean isOnlyShareVideo() {
        return this.mIsOnlyShareVideo;
    }

    public boolean isOnlyShareH5() {
        return this.mIsOnlyShareH5;
    }

    public TTShareEventCallback getEventCallBack() {
        return this.mEventCallBack;
    }

    public void setEventCallBack(TTShareEventCallback tTShareEventCallback) {
        this.mEventCallBack = tTShareEventCallback;
    }

    public String getExtraParams() {
        return this.mExtraParams;
    }

    public ArrayList<String> getHashTag() {
        return this.mHashTag;
    }

    public void setHashTag(ArrayList<String> arrayList) {
        this.mHashTag = arrayList;
    }

    public TTDYMicroAppInfo getDyMicroAppInfo() {
        return this.mDyMicroAppInfo;
    }

    public void setDyMicroAppInfo(TTDYMicroAppInfo tTDYMicroAppInfo) {
        this.mDyMicroAppInfo = tTDYMicroAppInfo;
    }

    public TTDYAnchorAppInfo getDyAnchorAppInfo() {
        return this.mDyAnchorAppInfo;
    }

    public void setDyAnchorAppInfo(TTDYAnchorAppInfo tTDYAnchorAppInfo) {
        this.mDyAnchorAppInfo = tTDYAnchorAppInfo;
    }

    public Bitmap getImage() {
        return this.mImage;
    }

    public void setImage(Bitmap bitmap) {
        this.mImage = bitmap;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getTargetUrl() {
        return this.mTargetUrl;
    }

    public void setLinkUrl(String str) {
        this.mTargetUrl = str;
    }

    public String getText() {
        return this.mText;
    }

    public void setText(String str) {
        this.mText = str;
    }

    public String getShareStrategy() {
        return this.mShareStrategy;
    }

    public void setShareStrategy(String str) {
        this.mShareStrategy = str;
    }

    public TTTokenShareInfo getTokenShareInfo() {
        return this.mTokenShareInfo;
    }

    public void setTokenShareInfo(TTTokenShareInfo tTTokenShareInfo) {
        this.mTokenShareInfo = tTTokenShareInfo;
    }

    public TTShareItemType getShareType() {
        return this.mShareType;
    }

    public void setShareType(TTShareItemType tTShareItemType) {
        this.mShareType = tTShareItemType;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public String getThumbnailUrl() {
        return this.mImageUrl;
    }

    public void setThumbnailUrl(String str) {
        this.mImageUrl = str;
    }

    public void setImageUrl(String str) {
        this.mImageUrl = str;
    }

    public String getVideoUrl() {
        return this.mVideoUrl;
    }

    public void setVideoUrl(String str) {
        this.mVideoUrl = str;
    }

    public String getQrcodeImageUrl() {
        return this.mQrcodeImageUrl;
    }

    public void setQrcodeImageUrl(String str) {
        this.mQrcodeImageUrl = str;
    }

    public String getHiddenImageUrl() {
        return this.mHiddenImageUrl;
    }

    public void setHiddenImageUrl(String str) {
        this.mHiddenImageUrl = str;
    }

    public JSONObject getRequestData() {
        return this.mRequestData;
    }

    public void setRequestData(JSONObject jSONObject) {
        this.mRequestData = jSONObject;
    }

    public String getPanelId() {
        return this.mPanelId;
    }

    public void setPanelId(String str) {
        this.mPanelId = str;
    }

    public boolean isDisableGetShareInfo() {
        return this.mIsDisableGetShareInfo;
    }

    public void setIsDisableGetShareInfo(boolean z) {
        this.mIsDisableGetShareInfo = z;
    }

    public boolean isForceUpdate() {
        return this.mIsForceUpdate;
    }

    public void setIsForceUpdate(boolean z) {
        this.mIsForceUpdate = z;
    }

    public String getResourceId() {
        return this.mResourceId;
    }

    public void setResourceId(String str) {
        this.mResourceId = str;
    }

    public TTShareContentType getShareContentType() {
        return this.mShareContentType;
    }

    public void setShareContentType(TTShareContentType tTShareContentType) {
        this.mShareContentType = tTShareContentType;
    }

    public String getGroupLabelId() {
        return this.mGroupLabelId;
    }

    public void setGroupLabelId(String str) {
        this.mGroupLabelId = str;
    }

    /* loaded from: classes7.dex */
    public static class Builder {
        private TTShareModel shareModel = new TTShareModel();

        public Builder setIsOnlyShareImage(boolean z) {
            this.shareModel.mIsOnlyShareImage = z;
            this.shareModel.mShareContentType = TTShareContentType.IMAGE;
            return this;
        }

        public Builder setIsOnlyShareText(boolean z) {
            this.shareModel.mIsOnlyShareText = z;
            this.shareModel.mShareContentType = TTShareContentType.TEXT;
            return this;
        }

        public Builder setIsOnlyShareVideo(boolean z) {
            this.shareModel.mIsOnlyShareVideo = z;
            this.shareModel.mShareContentType = TTShareContentType.VIDEO;
            return this;
        }

        public Builder setIsOnlyShareH5(boolean z) {
            this.shareModel.mIsOnlyShareH5 = z;
            this.shareModel.mShareContentType = TTShareContentType.H5;
            return this;
        }

        public Builder setEventCallBack(TTShareEventCallback tTShareEventCallback) {
            this.shareModel.mEventCallBack = tTShareEventCallback;
            return this;
        }

        public Builder setImage(Bitmap bitmap) {
            this.shareModel.mImage = bitmap;
            return this;
        }

        public Builder setTitle(String str) {
            this.shareModel.mTitle = str;
            return this;
        }

        public Builder setLinkUrl(String str) {
            this.shareModel.mTargetUrl = str;
            return this;
        }

        public Builder setText(String str) {
            this.shareModel.mText = str;
            return this;
        }

        public Builder setShareStrategy(String str) {
            this.shareModel.mShareStrategy = str;
            return this;
        }

        public Builder setTokenShareInfo(TTTokenShareInfo tTTokenShareInfo) {
            this.shareModel.mTokenShareInfo = tTTokenShareInfo;
            return this;
        }

        public Builder setShareType(TTShareItemType tTShareItemType) {
            this.shareModel.mShareType = tTShareItemType;
            return this;
        }

        public Builder setThumbnailUrl(String str) {
            this.shareModel.mImageUrl = str;
            return this;
        }

        public Builder setImageUrl(String str) {
            this.shareModel.mImageUrl = str;
            return this;
        }

        public Builder setVideoUrl(String str) {
            this.shareModel.mVideoUrl = str;
            return this;
        }

        public Builder setQrcodeImageUrl(String str) {
            this.shareModel.mQrcodeImageUrl = str;
            return this;
        }

        public Builder setHiddenImageUrl(String str) {
            this.shareModel.mHiddenImageUrl = str;
            return this;
        }

        public Builder setExtraParams(String str) {
            this.shareModel.mExtraParams = str;
            return this;
        }

        public Builder setHashTag(ArrayList<String> arrayList) {
            this.shareModel.mHashTag = arrayList;
            return this;
        }

        public Builder setTTDYMicroAppInfo(TTDYMicroAppInfo tTDYMicroAppInfo) {
            this.shareModel.mDyMicroAppInfo = tTDYMicroAppInfo;
            return this;
        }

        public Builder setTTDYAnchorAppInfo(TTDYAnchorAppInfo tTDYAnchorAppInfo) {
            this.shareModel.mDyAnchorAppInfo = tTDYAnchorAppInfo;
            return this;
        }

        public Builder setResourceId(String str) {
            this.shareModel.mResourceId = str;
            return this;
        }

        public Builder setIsForceUpdate(boolean z) {
            this.shareModel.mIsForceUpdate = z;
            return this;
        }

        public Builder setIsDisableGetShareInfo(boolean z) {
            this.shareModel.mIsDisableGetShareInfo = z;
            return this;
        }

        public Builder setPanelId(String str) {
            this.shareModel.mPanelId = str;
            return this;
        }

        public Builder setRequestData(JSONObject jSONObject) {
            this.shareModel.mRequestData = jSONObject;
            return this;
        }

        public Builder setShareContentType(TTShareContentType tTShareContentType) {
            this.shareModel.mShareContentType = tTShareContentType;
            return this;
        }

        public Builder setWBExtra(TTWBExtra tTWBExtra) {
            this.shareModel.mWBExtra = tTWBExtra;
            return this;
        }

        public Builder setGroupLabelId(String str) {
            this.shareModel.mGroupLabelId = str;
            return this;
        }

        public TTShareModel build() {
            return this.shareModel;
        }
    }

    public String toString() {
        return "TTShareModel{mShareType=" + this.mShareType + ", mImageUrl='" + this.mImageUrl + "', mVideoUrl='" + this.mVideoUrl + "', mIsVideo=" + this.mIsVideo + ", mQrcodeImageUrl='" + this.mQrcodeImageUrl + "', mHiddenImageUrl='" + this.mHiddenImageUrl + "', mTitle='" + this.mTitle + "', mTargetUrl='" + this.mTargetUrl + "', mText='" + this.mText + "', mShareStrategy='" + this.mShareStrategy + "', mTokenShareInfo=" + this.mTokenShareInfo + ", mImage=" + this.mImage + ", mIsOnlyShareImage=" + this.mIsOnlyShareImage + ", mIsOnlyShareText=" + this.mIsOnlyShareText + ", mIsOnlyShareVideo=" + this.mIsOnlyShareVideo + ", mIsOnlyShareH5=" + this.mIsOnlyShareH5 + ", mEventCallBack=" + this.mEventCallBack + ", mExtraParams='" + this.mExtraParams + "', mHashTag=" + this.mHashTag + ", mDyMicroAppInfo=" + this.mDyMicroAppInfo + ", mDyAnchorAppInfo=" + this.mDyAnchorAppInfo + ", mRequestData=" + this.mRequestData + ", mPanelId='" + this.mPanelId + "', mIsDisableGetShareInfo=" + this.mIsDisableGetShareInfo + ", mIsForceUpdate=" + this.mIsForceUpdate + ", mResourceId='" + this.mResourceId + "', mShareContentType=" + this.mShareContentType + ", mGroupLabelId='" + this.mGroupLabelId + "'}";
    }
}
