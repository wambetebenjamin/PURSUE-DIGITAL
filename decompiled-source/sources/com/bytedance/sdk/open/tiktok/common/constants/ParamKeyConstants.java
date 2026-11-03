package com.bytedance.sdk.open.tiktok.common.constants;
/* loaded from: classes3.dex */
public interface ParamKeyConstants {
    public static final int AUTH_REQUEST_CODE = 100;
    public static final String AWEME_EXTRA_MEDIA_MESSAGE_IMAGE_PATH = "AWEME_EXTRA_IMAGE_MESSAGE_PATH";
    public static final String AWEME_EXTRA_MEDIA_MESSAGE_VIDEO_PATH = "AWEME_EXTRA_VIDEO_MESSAGE_PATH";
    public static final String META_PLATFORM_SDK_VERSION = "BD_PLATFORM_SDK_VERSION";
    public static final int META_PLATFORM_SDK_VERSION_ERROR = -1;
    public static final String OAUTH_PATH = "/oauth/authorize/";
    public static final String REDIRECT_URL_PATH = "/oauth/authorize/callback/";
    public static final int SHARE_TO_IM_CODE = 101;

    /* loaded from: classes3.dex */
    public interface AuthParams {
        public static final String AUTH_CODE = "_bytedance_params_authcode";
        public static final String CLIENT_KEY = "_bytedance_params_client_key";
        public static final String GRANTED_PERMISSION = "_bytedance_params_granted_permission";
        public static final String OPTIONAL_SCOPE0 = "_bytedance_params_optional_scope0";
        public static final String OPTIONAL_SCOPE1 = "_bytedance_params_optional_scope1";
        public static final String REDIRECT_URI = "_bytedance_params_redirect_uri";
        public static final String SCOPE = "_bytedance_params_scope";
        public static final String STATE = "_bytedance_params_state";
        public static final String WAP_REQUESETED_ORIENTATION = "wap_requested_orientation";
    }

    /* loaded from: classes3.dex */
    public interface BaseParams {
        public static final String CALLER_BASE_OPEN_SDK_COMMON_NAME = "_aweme_params_caller_open_sdk_common_name";
        public static final String CALLER_BASE_OPEN_SDK_COMMON_VERSION = "_aweme_params_caller_open_sdk_common_version";
        public static final String CALLER_BASE_OPEN_SDK_NAME = "_aweme_params_caller_open_sdk_name";
        public static final String CALLER_BASE_OPEN_SDK_VERSION = "_aweme_params_caller_open_sdk_version";
        public static final String CALLER_BASE_OPEN_VERSION = "__bytedance_base_caller_version";
        public static final String CALLER_PKG = "_bytedance_params_type_caller_package";
        public static final String ERROR_CODE = "_bytedance_params_error_code";
        public static final String ERROR_MSG = "_bytedance_params_error_msg";
        public static final String EXTRA = "_bytedance_params_extra";
        public static final String FROM_ENTRY = "_bytedance_params_from_entry";
        public static final String TYPE = "_bytedance_params_type";
    }

    /* loaded from: classes3.dex */
    public interface REQUIRED_API_VERSION {
        public static final int AUTHORIZE_FOR_TIKTOK_LITE = 6;
        public static final int AUTH_REQUIRE_API = 1;
        public static final int MIN_SDK_NEW_VERSION_API = 3;
        public static final int SHARE_REQUIRED_MIN_VERSION = 2;
        public static final int SHARE_SUPPORT_FILEPROVIDER = 5;
    }

    /* loaded from: classes3.dex */
    public interface SdkVersion {
        public static final String VERSION = "1";
    }

    /* loaded from: classes3.dex */
    public interface ShareOptions {
        public static final String TIKTOK_VIDEOKIT_DISABLE_MUSIC_SELECTION = "tiktok_videokit_disable_music_selection";
    }

    /* loaded from: classes3.dex */
    public interface ShareParams {
        public static final String ANCHOR_SOURCE_TYPE = "anchor_source_type";
        public static final String CALLER_LOCAL_ENTRY = "_aweme_open_sdk_params_caller_local_entry";
        public static final String CALLER_PKG = "_aweme_open_sdk_params_caller_package";
        public static final String CALLER_SDK_VERSION = "_aweme_open_sdk_params_caller_sdk_version";
        public static final String CLIENT_KEY = "_aweme_open_sdk_params_client_key";
        public static final String ERROR_CODE = "_aweme_open_sdk_params_error_code";
        public static final String ERROR_MSG = "_aweme_open_sdk_params_error_msg";
        public static final String EXTRA_SHARE_OPTIONS = "_aweme_open_sdk_extra_share_options";
        public static final String OPENPLATFORM_EXTRA = "extra";
        public static final String SHARE_ANCHOR_INFO = "_aweme_open_sdk_params_anchor_info";
        public static final String SHARE_DEFAULT_HASHTAG = "_aweme_open_sdk_params_target_scene";
        public static final String SHARE_HASHTAG_LIST = "_aweme_open_sdk_params_hashtag_list";
        public static final String SHARE_MICROAPP_INFO = "_aweme_open_sdk_params_micro_app_info";
        public static final String SHARE_SUB_ERROR_CODE = "_aweme_open_sdk_params_sub_error_code";
        public static final String SHARE_TARGET_SCENE = "_aweme_open_sdk_params_target_landpage_scene";
        public static final String STATE = "_aweme_open_sdk_params_state";
        public static final String TYPE = "_aweme_open_sdk_params_type";
    }

    /* loaded from: classes3.dex */
    public interface TargetSceneType {
        public static final int LANDPAGE_SCENE_CUT = 2;
        public static final int LANDPAGE_SCENE_DEFAULT = 0;
        public static final int LANDPAGE_SCENE_EDIT = 1;
    }

    /* loaded from: classes3.dex */
    public interface WebViewConstants {
        public static final String QUERY_CLIENT_KEY = "client_key";
        public static final String QUERY_ENCRIPTION_PACKAGE = "app_identity";
        public static final String QUERY_FROM = "from";
        public static final String QUERY_OPTIONAL_SCOPE = "optionalScope";
        public static final String QUERY_PLATFORM = "device_platform";
        public static final String QUERY_REDIRECT_URI = "redirect_uri";
        public static final String QUERY_RESPONSE_TYPE = "response_type";
        public static final String QUERY_SCOPE = "scope";
        public static final String QUERY_SIGNATURE = "signature";
        public static final String QUERY_STATE = "state";
        public static final String REDIRECT_QUERY_CODE = "code";
        public static final String REDIRECT_QUERY_ERROR_CODE = "errCode";
        public static final String REDIRECT_QUERY_SCOPE = "scopes";
        public static final String REDIRECT_QUERY_STATE = "state";
        public static final String SCHEMA_HTTPS = "https";
        public static final String VALUE_FROM_OPENSDK = "opensdk";
        public static final String VALUE_RESPONSE_TYPE_CODE = "code";
    }
}
