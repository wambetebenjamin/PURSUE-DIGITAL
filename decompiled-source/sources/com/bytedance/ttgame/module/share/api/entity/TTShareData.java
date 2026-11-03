package com.bytedance.ttgame.module.share.api.entity;

import android.app.Activity;
import com.bytedance.ttgame.module.share.api.callback.TTShareEventCallback;
import com.bytedance.ttgame.module.share.api.panel.OnTTPanelActionCallback;
/* loaded from: classes7.dex */
public class TTShareData {
    private Activity mActivity;
    private TTShareEventCallback mEventCallBack;
    private String mExtraParams;
    private OnTTPanelActionCallback mPanelActionCallback;
    private String mPanelId;
    private TTShareContentModel mShareContentModel;
    private String mShareStrategy;

    public String toString() {
        return "TTShareData{mActivity=" + this.mActivity + ", mPanelId='" + this.mPanelId + "', mShareStrategy='" + this.mShareStrategy + "', mShareContentModel=" + this.mShareContentModel + '}';
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public String getPanelId() {
        return this.mPanelId;
    }

    public void setPanelId(String str) {
        this.mPanelId = str;
    }

    public String getShareStrategy() {
        return this.mShareStrategy;
    }

    public void setShareStrategy(String str) {
        this.mShareStrategy = str;
    }

    public TTShareContentModel getShareContentModel() {
        return this.mShareContentModel;
    }

    public void setShareContentModel(TTShareContentModel tTShareContentModel) {
        this.mShareContentModel = tTShareContentModel;
    }

    public TTShareEventCallback getEventCallBack() {
        return this.mEventCallBack;
    }

    public void setEventCallBack(TTShareEventCallback tTShareEventCallback) {
        this.mEventCallBack = tTShareEventCallback;
    }

    public OnTTPanelActionCallback getPanelActionCallback() {
        return this.mPanelActionCallback;
    }

    public void setPanelActionCallback(OnTTPanelActionCallback onTTPanelActionCallback) {
        this.mPanelActionCallback = onTTPanelActionCallback;
    }

    public String getExtraParams() {
        return this.mExtraParams;
    }

    public void setExtraParams(String str) {
        this.mExtraParams = str;
    }

    /* loaded from: classes7.dex */
    public static class Builder {
        private TTShareData mShareContent = new TTShareData();

        public Builder setActivity(Activity activity) {
            this.mShareContent.mActivity = activity;
            return this;
        }

        public Builder setPanelId(String str) {
            this.mShareContent.mPanelId = str;
            return this;
        }

        public Builder setShareStrategy(String str) {
            this.mShareContent.mShareStrategy = str;
            return this;
        }

        public Builder setShareContentModel(TTShareContentModel tTShareContentModel) {
            this.mShareContent.mShareContentModel = tTShareContentModel;
            return this;
        }

        public Builder setEventCallback(TTShareEventCallback tTShareEventCallback) {
            this.mShareContent.mEventCallBack = tTShareEventCallback;
            return this;
        }

        public Builder setPanelActionCallback(OnTTPanelActionCallback onTTPanelActionCallback) {
            this.mShareContent.mPanelActionCallback = onTTPanelActionCallback;
            return this;
        }

        public Builder setExtraParams(String str) {
            this.mShareContent.mExtraParams = str;
            return this;
        }

        public TTShareData build() {
            return this.mShareContent;
        }
    }
}
