package org.chromium;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.frameworks.baselib.network.http.storeregion.b;
import com.bytedance.frameworks.baselib.network.http.util.n;
import com.bytedance.ttnet.TTNetInit;
import com.facebook.internal.security.CertificateUtil;
import com.ttnet.org.chromium.net.TTAppInfoProvider;
import java.net.CookieHandler;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes10.dex */
public class CronetAppInfoProvider extends TTAppInfoProvider {
    private static final String COOKIE_HEADER_KEY = "Cookie";
    private static final int MAX_ABSDK_VERSION_LENGTH = 5;
    private static CronetAppInfoProvider sInstance;
    private TTAppInfoProvider.AppInfo mAppInfo;
    private Context mContext;

    public static CronetAppInfoProvider inst(Context context) {
        if (sInstance == null) {
            synchronized (CronetAppInfoProvider.class) {
                if (sInstance == null) {
                    sInstance = new CronetAppInfoProvider(context);
                }
            }
        }
        return sInstance;
    }

    private CronetAppInfoProvider(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private String getCookieHeaderForTncRequest(String str) {
        CookieHandler cookieHandler = CookieHandler.getDefault();
        if (!TextUtils.isEmpty(str) && cookieHandler != null) {
            try {
                Map<String, List<String>> map = cookieHandler.get(n.b("https://" + str), null);
                if (map != null && map.size() > 0) {
                    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                        if ("Cookie".equalsIgnoreCase(entry.getKey()) && !entry.getValue().isEmpty()) {
                            StringBuilder sb = new StringBuilder();
                            int i2 = 0;
                            for (String str2 : entry.getValue()) {
                                if (i2 > 0) {
                                    sb.append("; ");
                                }
                                sb.append(str2);
                                i2++;
                            }
                            return sb.toString();
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    @Nullable
    private static String toLowerCase(String str) {
        return TextUtils.isEmpty(str) ? str : str.toLowerCase();
    }

    @Override // com.ttnet.org.chromium.net.TTAppInfoProvider
    public TTAppInfoProvider.AppInfo getAppInfo() {
        try {
            synchronized (CronetAppInfoProvider.class) {
                if (this.mAppInfo == null) {
                    this.mAppInfo = new TTAppInfoProvider.AppInfo();
                }
            }
            this.mAppInfo.setAppId(CronetAppProviderManager.inst().getAppId());
            this.mAppInfo.setAppName(CronetAppProviderManager.inst().getAppName());
            this.mAppInfo.setSdkAppID(CronetAppProviderManager.inst().getSdkAppId());
            this.mAppInfo.setSdkVersion(CronetAppProviderManager.inst().getSdkVersion());
            this.mAppInfo.setChannel(CronetAppProviderManager.inst().getChannel());
            this.mAppInfo.setDeviceId(CronetAppProviderManager.inst().getDeviceId());
            this.mAppInfo.setIsDropFirstTnc(CronetAppProviderManager.inst().getIsDropFirstTnc());
            this.mAppInfo.setAbi(CronetAppProviderManager.inst().getAbi());
            this.mAppInfo.setDevicePlatform(CronetAppProviderManager.inst().getDevicePlatform());
            this.mAppInfo.setDeviceType(CronetAppProviderManager.inst().getDeviceType());
            this.mAppInfo.setDeviceBrand(CronetAppProviderManager.inst().getDeviceBrand());
            this.mAppInfo.setDeviceModel(CronetAppProviderManager.inst().getDeviceModel());
            this.mAppInfo.setNetAccessType(CronetAppProviderManager.inst().getNetAccessType());
            this.mAppInfo.setOSApi(CronetAppProviderManager.inst().getOSApi());
            this.mAppInfo.setOSVersion(CronetAppProviderManager.inst().getOSVersion());
            this.mAppInfo.setUserId(CronetAppProviderManager.inst().getUserId());
            this.mAppInfo.setVersionCode(CronetAppProviderManager.inst().getVersionCode());
            this.mAppInfo.setVersionName(CronetAppProviderManager.inst().getVersionName());
            this.mAppInfo.setUpdateVersionCode(CronetAppProviderManager.inst().getUpdateVersionCode());
            this.mAppInfo.setManifestVersionCode(CronetAppProviderManager.inst().getManifestVersionCode());
            this.mAppInfo.setStoreIdc(CronetAppProviderManager.inst().getStoreIdc());
            this.mAppInfo.setRegion(toLowerCase(CronetAppProviderManager.inst().getRegion()));
            this.mAppInfo.setSysRegion(toLowerCase(CronetAppProviderManager.inst().getSysRegion()));
            this.mAppInfo.setCarrierRegion(toLowerCase(CronetAppProviderManager.inst().getCarrierRegion()));
            this.mAppInfo.setInitRegion(CronetAppProviderManager.inst().getInitRegion());
            this.mAppInfo.setTNCRequestFlags(CronetAppProviderManager.inst().getTNCRequestFlags());
            this.mAppInfo.setHttpDnsRequestFlags(CronetAppProviderManager.inst().getHttpDnsRequestFlags());
            this.mAppInfo.setIsDomestic(b.i() ? "1" : "0");
            Map<String, String> getDomainDependHostMap = CronetAppProviderManager.inst().getGetDomainDependHostMap();
            if (getDomainDependHostMap != null && !getDomainDependHostMap.isEmpty()) {
                this.mAppInfo.setHostFirst(getDomainDependHostMap.get("first"));
                this.mAppInfo.setHostSecond(getDomainDependHostMap.get("second"));
                this.mAppInfo.setHostThird(getDomainDependHostMap.get("third"));
                this.mAppInfo.setDomainHttpDns(getDomainDependHostMap.get(TTNetInit.DOMAIN_HTTPDNS_KEY));
                this.mAppInfo.setDomainBoe(getDomainDependHostMap.get("boe"));
                this.mAppInfo.setDomainBoeHttps(getDomainDependHostMap.get(TTNetInit.DOMAIN_BOE_HTTPS_KEY));
            }
            Map<String, String> tNCRequestHeader = CronetAppProviderManager.inst().getTNCRequestHeader();
            String str = "";
            if (tNCRequestHeader != null && !tNCRequestHeader.isEmpty()) {
                for (Map.Entry<String, String> entry : tNCRequestHeader.entrySet()) {
                    str = entry.getKey() + CertificateUtil.DELIMITER + entry.getValue() + "\r\n" + str;
                }
            }
            String str2 = "";
            if (getDomainDependHostMap != null && !getDomainDependHostMap.isEmpty()) {
                str2 = getCookieHeaderForTncRequest(getDomainDependHostMap.get("first"));
            }
            if (!TextUtils.isEmpty(str2)) {
                str = "Cookie:" + str2 + "\r\n" + str;
            }
            this.mAppInfo.setTNCRequestHeader(str);
            Map<String, String> tNCRequestQuery = CronetAppProviderManager.inst().getTNCRequestQuery();
            String str3 = "";
            if (tNCRequestQuery != null && !tNCRequestQuery.isEmpty()) {
                for (Map.Entry<String, String> entry2 : tNCRequestQuery.entrySet()) {
                    str3 = entry2.getKey() + CertificateUtil.DELIMITER + entry2.getValue() + "\r\n" + str3;
                }
            }
            this.mAppInfo.setTNCRequestQuery(str3);
            List<String> abSdkVersion = CronetAppProviderManager.inst().getAbSdkVersion();
            String str4 = "";
            if (abSdkVersion != null) {
                if (abSdkVersion.size() > 5 && CronetDependManager.inst().loggerDebug()) {
                    CronetDependManager.inst().loggerD("CronetAppInfoProvider", "ab sdk version count must less than 5");
                }
                int i2 = 0;
                for (String str5 : abSdkVersion) {
                    i2++;
                    if (i2 > 5) {
                        break;
                    } else if (!TextUtils.isEmpty(str5)) {
                        str4 = str4 + str5 + ",";
                    }
                }
            }
            this.mAppInfo.setAbSdkVersion(str4);
            if (CronetDependManager.inst().loggerDebug()) {
                CronetDependManager.inst().loggerD("CronetAppInfoProvider", "get appinfo = " + ("AppInfo{, mUserId='" + this.mAppInfo.getUserId() + "', mAppId='" + this.mAppInfo.getAppId() + "', mOSApi='" + this.mAppInfo.getOSApi() + "', mDeviceId='" + this.mAppInfo.getDeviceId() + "', mNetAccessType='" + this.mAppInfo.getNetAccessType() + "', mVersionCode='" + this.mAppInfo.getVersionCode() + "', mDeviceType='" + this.mAppInfo.getDeviceType() + "', mAppName='" + this.mAppInfo.getAppName() + "', mSdkAppID='" + this.mAppInfo.getSdkAppID() + "', mSdkVersion='" + this.mAppInfo.getSdkVersion() + "', mChannel='" + this.mAppInfo.getChannel() + "', mOSVersion='" + this.mAppInfo.getOSVersion() + "', mAbi='" + this.mAppInfo.getAbi() + "', mDevicePlatform='" + this.mAppInfo.getDevicePlatform() + "', mDeviceBrand='" + this.mAppInfo.getDeviceBrand() + "', mDeviceModel='" + this.mAppInfo.getDeviceModel() + "', mVersionName='" + this.mAppInfo.getVersionName() + "', mUpdateVersionCode='" + this.mAppInfo.getUpdateVersionCode() + "', mManifestVersionCode='" + this.mAppInfo.getManifestVersionCode() + "'}"));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.mAppInfo;
    }
}
