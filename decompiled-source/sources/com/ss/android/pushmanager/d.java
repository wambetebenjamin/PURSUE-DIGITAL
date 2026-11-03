package com.ss.android.pushmanager;

import java.util.HashSet;
import java.util.Set;
/* compiled from: MessageConstants.java */
/* loaded from: classes9.dex */
public class d {
    public static final String BUNDLE_FROM_BANNER_NOTIFICATION = "from_banner_notification";
    public static final String BUNDLE_FROM_NOTIFICATION = "from_notification";
    public static final String BUNDLE_MESSAGE_BODY = "push_body";
    public static final String BUNDLE_OPEN_URL = "open_url";
    public static final String KEY_MESSAGE_EXTRA = "message_extra";
    public static final String KEY_MESSAGE_FROM = "message_from";
    public static final String KEY_PUSH_TOKEN_SENT = "push_token_sent";
    public static final String LOG_TYPE = "ss_push";
    public static final String MESSAGE_ACTION = "com.ss.android.message";
    public static final String MESSAGE_KEY_DATA = "message_data";
    public static final String MSG_FROM = "msg_from";
    public static final int MSG_FROM_MESSAGE = 1;
    public static final String MSG_ID = "msg_id";
    public static final String NOTIFY_ACTION = "com.ss.android.message.action.PUSH_SERVICE";
    public static final String TAG = "MessageAppManager";
    public static final int TYPE_MSG_FROM_NOTIFY = 1;
    public static final int TYPE_MSG_FROM_WINDOW = 2;
    public static final int TYPE_REGISTER_FROM_HEART_BEAT = 4;
    public static final int TYPE_REGISTER_FROM_INIT = 2;
    public static final int TYPE_REGISTER_FROM_RECEIVER = 1;
    public static final int TYPE_REGISTER_FROM_SCHEDULED = 3;
    public static final Set<Integer> TYPE_REGISTER_SET;
    public static final String UNINSTALL_QUESTION_URL_MD5_SALT = "device_info_url_salt_650fa7";

    /* renamed from: a  reason: collision with root package name */
    private static final String f18482a = "/service/1/update_token/";

    /* renamed from: b  reason: collision with root package name */
    private static final String f18483b = "/service/1/app_notice_status/";

    /* renamed from: c  reason: collision with root package name */
    private static final String f18484c = "/cloudpush/fetch_business_status/";

    /* renamed from: d  reason: collision with root package name */
    private static final String f18485d = "/cloudpush/update_sender/";

    /* renamed from: e  reason: collision with root package name */
    private static final String f18486e = "/cloudpush/update_frontier_setting/";

    /* renamed from: f  reason: collision with root package name */
    private static final String f18487f = "/service/1/device_link/";

    /* renamed from: g  reason: collision with root package name */
    private static final String f18488g = "/service/settings/v3/";

    /* renamed from: h  reason: collision with root package name */
    private static final String f18489h = "/cloudpush/event_sync/";

    /* renamed from: i  reason: collision with root package name */
    private static final String f18490i = "/cloudpush/callback/client_show/";

    /* renamed from: j  reason: collision with root package name */
    private static final String f18491j = "/cloudpush/callback/client_click/";

    static {
        HashSet hashSet = new HashSet();
        TYPE_REGISTER_SET = hashSet;
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
    }

    public static String getSendPushTokenUrl() {
        return a.i(f18482a);
    }

    public static String getSendNotifyEnableUrl() {
        return a.i(f18483b);
    }

    public static String getNotifyStatusUrl() {
        return a.i(f18484c);
    }

    public static String getUpdateSenderUrl() {
        return a.i(f18485d);
    }

    public static String getUpdateFrontierSettingUrl() {
        return a.i(f18486e);
    }

    public static String getUploadHwDeviceInfoUrl() {
        return a.i(f18487f);
    }

    public static String getRequestSettingsUrl() {
        return a.i(f18488g);
    }

    public static String a() {
        return a.i(f18489h);
    }

    public static String b() {
        return a.i(f18490i);
    }

    public static String c() {
        return a.i(f18491j);
    }
}
