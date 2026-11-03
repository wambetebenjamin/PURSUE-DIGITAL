package com.bytedance.ttgame.module.share.api.entity;

import com.bytedance.ttgame.base.ErrorCode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: ShareErrorCode.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/bytedance/ttgame/module/share/api/entity/ShareErrorCode;", "Lcom/bytedance/ttgame/base/ErrorCode;", "()V", "Companion", "share_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class ShareErrorCode extends ErrorCode {
    public static final Companion Companion = new Companion(null);
    public static final int ERROR_APP_INIT_FAILED = -412004;
    public static final int ERROR_APP_NOT_INSTALL = -410002;
    public static final int ERROR_APP_VERSION_NOT_SUPPORT = -412003;
    public static final int ERROR_AWE_SHARE_VIDEO_PARAM_INVALID = -414000;
    public static final int ERROR_AWE_SHARE_VIDEO_SERVER_ERROR = -414001;
    public static final int ERROR_BEHIND_AUTH_ERROR = -412010;
    public static final int ERROR_BEHIND_NOT_LOGIN_DOUYIN = -412014;
    public static final int ERROR_BEHIND_NOT_TOUTIAO = -412013;
    public static final int ERROR_BEHIND_OPEN_CREATE = -412011;
    public static final int ERROR_BEHIND_OPEN_UPLOAD = -412012;
    public static final int ERROR_CHANNEL_NOT_IMPORTED = -410014;
    public static final int ERROR_CONTEXT_NULL = -412002;
    public static final int ERROR_DOUYIN_UNAUTHORIZED = -410013;
    public static final int ERROR_FILE_TOO_LARGE = -410012;
    public static final int ERROR_GET_SCREENSHOT_FAILED = -413001;
    public static final int ERROR_IMAGE_EMPTY = -410007;
    public static final int ERROR_IMAGE_LOCAL_URL_ERROR = -412007;
    public static final int ERROR_IMAGE_RESP_DATA_EMPTY = -412006;
    public static final int ERROR_IMAGE_TOO_LARGE = -410011;
    public static final int ERROR_IMAGE_URL_NOT_SUPPORT = -412008;
    public static final int ERROR_INVALID_CONTENT = -410003;
    public static final int ERROR_SHARE_CANCEL = -410001;
    public static final int ERROR_SHARE_FAIL = -412001;
    public static final int ERROR_SHARE_TYPE_NOT_SUPPORT = -410004;
    public static final int ERROR_SOURCE_FETCH_FAILED = -412005;
    public static final int ERROR_SUPER_GROUP_NOT_SUPPORT = -412015;
    public static final int ERROR_SUPER_GROUP_TITLE_EMPTY = -412016;
    public static final int ERROR_TITLE_EMPTY = -410005;
    public static final int ERROR_UNKNOWN = -419999;
    public static final int ERROR_URL_EMPTY = -410006;
    public static final int ERROR_VIDEO_FILE_NULL = -410009;
    public static final int ERROR_VIDEO_TARGET_URL_EMPTY = -412009;
    public static final int ERROR_VIDEO_TOO_LARGE = -410010;
    public static final int ERROR_VIDEO_URL_EMPTY = -410008;

    /* compiled from: ShareErrorCode.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\"\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/bytedance/ttgame/module/share/api/entity/ShareErrorCode$Companion;", "", "()V", "ERROR_APP_INIT_FAILED", "", "ERROR_APP_NOT_INSTALL", "ERROR_APP_VERSION_NOT_SUPPORT", "ERROR_AWE_SHARE_VIDEO_PARAM_INVALID", "ERROR_AWE_SHARE_VIDEO_SERVER_ERROR", "ERROR_BEHIND_AUTH_ERROR", "ERROR_BEHIND_NOT_LOGIN_DOUYIN", "ERROR_BEHIND_NOT_TOUTIAO", "ERROR_BEHIND_OPEN_CREATE", "ERROR_BEHIND_OPEN_UPLOAD", "ERROR_CHANNEL_NOT_IMPORTED", "ERROR_CONTEXT_NULL", "ERROR_DOUYIN_UNAUTHORIZED", "ERROR_FILE_TOO_LARGE", "ERROR_GET_SCREENSHOT_FAILED", "ERROR_IMAGE_EMPTY", "ERROR_IMAGE_LOCAL_URL_ERROR", "ERROR_IMAGE_RESP_DATA_EMPTY", "ERROR_IMAGE_TOO_LARGE", "ERROR_IMAGE_URL_NOT_SUPPORT", "ERROR_INVALID_CONTENT", "ERROR_SHARE_CANCEL", "ERROR_SHARE_FAIL", "ERROR_SHARE_TYPE_NOT_SUPPORT", "ERROR_SOURCE_FETCH_FAILED", "ERROR_SUPER_GROUP_NOT_SUPPORT", "ERROR_SUPER_GROUP_TITLE_EMPTY", "ERROR_TITLE_EMPTY", "ERROR_UNKNOWN", "ERROR_URL_EMPTY", "ERROR_VIDEO_FILE_NULL", "ERROR_VIDEO_TARGET_URL_EMPTY", "ERROR_VIDEO_TOO_LARGE", "ERROR_VIDEO_URL_EMPTY", "share_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
