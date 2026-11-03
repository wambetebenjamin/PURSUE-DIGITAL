package com.bytedance.ttgame.module.share.api.callback;

import com.bytedance.ttgame.module.share.api.entity.TTShareDownloadStatus;
import com.bytedance.ttgame.module.share.api.entity.TTShareModel;
import com.bytedance.ttgame.module.share.api.entity.TTSharePermissionType;
import com.bytedance.ttgame.module.share.api.entity.TTShareResult;
import com.bytedance.ttgame.module.share.api.entity.TTShareStatus;
/* loaded from: classes7.dex */
public interface TTShareEventCallback {

    /* loaded from: classes7.dex */
    public static abstract class EmptyShareEventCallBack implements TTShareEventCallback {
        @Override // com.bytedance.ttgame.module.share.api.callback.TTShareEventCallback
        public void onDownloadEvent(TTShareDownloadStatus tTShareDownloadStatus, String str, TTShareModel tTShareModel) {
        }

        @Override // com.bytedance.ttgame.module.share.api.callback.TTShareEventCallback
        public void onPermissionGrantedEvent(TTSharePermissionType tTSharePermissionType, TTShareModel tTShareModel, String str) {
        }

        @Override // com.bytedance.ttgame.module.share.api.callback.TTShareEventCallback
        public void onShareResultEvent(TTShareResult tTShareResult) {
        }

        @Override // com.bytedance.ttgame.module.share.api.callback.TTShareEventCallback
        public void onStatusEvent(TTShareStatus tTShareStatus) {
        }
    }

    void onDownloadEvent(TTShareDownloadStatus tTShareDownloadStatus, String str, TTShareModel tTShareModel);

    void onPermissionGrantedEvent(TTSharePermissionType tTSharePermissionType, TTShareModel tTShareModel, String str);

    void onShareResultEvent(TTShareResult tTShareResult);

    default void onStatusEvent(TTShareStatus tTShareStatus) {
    }
}
