package com.ss.android.common.applog;
/* loaded from: classes9.dex */
public class UrlConfig {
    public static final UrlConfig AMERICA;
    public static final String AMERICA_HOST_ACTIVE = "ichannel.isnssdk.com";
    public static final String AMERICA_HOST_DEVICE = "log.isnssdk.com";
    public static final String AMERICA_HOST_LOG = "log.isnssdk.com";
    public static final String AMERICA_HOST_LOG_SETTINGS = "log.isnssdk.com";
    public static final String AMERICA_HOST_TIMELY = "rtlog.isnssdk.com";
    public static final String AMERICA_USER_PROFILE = "vaali-dpprofile.byteoversea.com";
    public static final UrlConfig DEFAULT;
    public static final String HTTPS = "https://";
    public static final String PATH_ACTIVE = "/service/2/app_alert_check/";
    public static final String PATH_APP_LOG = "/service/2/app_log/";
    public static final String PATH_DEVICE_REGISTER = "/service/2/device_register/";
    public static final String PATH_LOG_SETTINGS = "/service/2/log_settings/";
    public static final UrlConfig SIG_ALIYUN;
    public static final UrlConfig SIG_AWS;
    public static final String SIG_A_BACKUP_HOST_LOG = "log15.byteoversea.com";
    public static final String SIG_A_HOST_ACTIVE = "i.byteoversea.com";
    public static final String SIG_A_HOST_DEVICE = "log.byteoversea.com";
    public static final String SIG_A_HOST_LOG = "log.byteoversea.com";
    public static final String SIG_A_HOST_LOG_SETTINGS = "log.byteoversea.com";
    public static final String SIG_A_HOST_TIMELY = "rtlog.byteoversea.com";
    public static final String SIG_A_USER_PROFILE = "sgali-dpprofile.byteoversea.com";
    public static final String SIG_HOST_ACTIVE = "ichannel.sgsnssdk.com";
    public static final String SIG_HOST_DEVICE = "log.sgsnssdk.com";
    public static final String SIG_HOST_DEVICE_2 = "log15.byteoversea.com";
    public static final String SIG_HOST_LOG = "log.sgsnssdk.com";
    public static final String SIG_HOST_LOG_SETTINGS = "log.sgsnssdk.com";
    public static final String SIG_HOST_TIMELY = "rtlog.sgsnssdk.com";
    public static final String SIG_USER_PROFILE = "sgali-dpprofile.byteoversea.com";
    public static final String URL_EVENT_VERIFY = "";
    final String[] mAppActiveUrl;
    final String[] mApplogFallbackUrl;
    final String mApplogSettingsFallbackUrl;
    final String mApplogSettingsUrl;
    final String[] mApplogTimelyUrl;
    final String[] mApplogURL;
    final String[] mDeviceRegisterUrl;
    final String mUserProfileUrl;

    static {
        UrlConfig urlConfig = new UrlConfig(new String[]{"https://log.isnssdk.com/service/2/app_log/"}, new String[]{"https://rtlog.isnssdk.com/service/2/app_log/"}, new String[]{"https://log.isnssdk.com/service/2/device_register/", "https://log.isnssdk.com/service/2/device_register/"}, new String[]{"https://ichannel.isnssdk.com/service/2/app_alert_check/"}, "https://log.isnssdk.com/service/2/log_settings/", new String[]{"https://log.isnssdk.com/service/2/app_log/"}, "https://log.isnssdk.com/service/2/log_settings/", "https://vaali-dpprofile.byteoversea.com");
        AMERICA = urlConfig;
        SIG_AWS = new UrlConfig(new String[]{"https://log.sgsnssdk.com/service/2/app_log/"}, new String[]{"https://rtlog.sgsnssdk.com/service/2/app_log/"}, new String[]{"https://log.sgsnssdk.com/service/2/device_register/", "https://log15.byteoversea.com/service/2/device_register/", "https://log.sgsnssdk.com/service/2/device_register/", "https://log15.byteoversea.com/service/2/device_register/"}, new String[]{"https://ichannel.sgsnssdk.com/service/2/app_alert_check/"}, "https://log.sgsnssdk.com/service/2/log_settings/", new String[]{"https://log.sgsnssdk.com/service/2/app_log/"}, "https://log.sgsnssdk.com/service/2/log_settings/", "https://sgali-dpprofile.byteoversea.com");
        SIG_ALIYUN = new UrlConfig(new String[]{"https://log.byteoversea.com/service/2/app_log/", "https://log15.byteoversea.com/service/2/app_log/"}, new String[]{"https://rtlog.byteoversea.com/service/2/app_log/"}, new String[]{"https://log.byteoversea.com/service/2/device_register/", "https://log.byteoversea.com/service/2/device_register/"}, new String[]{"https://i.byteoversea.com/service/2/app_alert_check/"}, "https://log.byteoversea.com/service/2/log_settings/", new String[]{"https://log.byteoversea.com/service/2/app_log/", "https://log15.byteoversea.com/service/2/app_log/"}, "https://log.byteoversea.com/service/2/log_settings/", "https://sgali-dpprofile.byteoversea.com");
        DEFAULT = urlConfig;
    }

    public UrlConfig(String[] strArr, String[] strArr2, String[] strArr3, String str, String str2, String[] strArr4, String str3, String str4) {
        this.mApplogURL = strArr;
        this.mApplogSettingsUrl = str2;
        this.mApplogTimelyUrl = strArr2;
        this.mAppActiveUrl = new String[]{str};
        this.mDeviceRegisterUrl = strArr3;
        this.mApplogFallbackUrl = strArr4;
        this.mApplogSettingsFallbackUrl = str3;
        this.mUserProfileUrl = str4;
    }

    public UrlConfig(String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4, String str, String[] strArr5, String str2, String str3) {
        this.mApplogURL = strArr;
        this.mApplogSettingsUrl = str;
        this.mApplogTimelyUrl = strArr2;
        this.mAppActiveUrl = strArr4;
        this.mDeviceRegisterUrl = strArr3;
        this.mApplogFallbackUrl = strArr5;
        this.mApplogSettingsFallbackUrl = str2;
        this.mUserProfileUrl = str3;
    }

    public String toString() {
        return super.toString() + ":\nmApplogURL : " + this.mApplogURL + "\nmApplogTimelyUrl : " + this.mApplogTimelyUrl + "\nmDeviceRegisterUrl : " + this.mDeviceRegisterUrl + "\nmAppActiveUrl : " + this.mAppActiveUrl + "\nmApplogSettingsUrl : " + this.mApplogSettingsUrl + "\n\nmApplogFallbackUrl : " + this.mApplogFallbackUrl + "\nmApplogSettingsFallbackUrl : " + this.mApplogSettingsFallbackUrl + "\nmUserProfileUrl : " + this.mUserProfileUrl + "\n\n\n\n";
    }
}
