package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.annotations.JNINamespace;
@JNINamespace("cronet")
/* loaded from: classes9.dex */
public abstract class TTAppInfoProvider {
    public abstract AppInfo getAppInfo();

    /* loaded from: classes9.dex */
    public static class AppInfo {
        private String mAbSdkVersion;
        private String mAbi;
        private String mAppId;
        private String mAppName;
        private String mCarrierRegion;
        private String mChannel;
        private String mDeviceBrand;
        private String mDeviceId;
        private String mDeviceModel;
        private String mDevicePlatform;
        private String mDeviceType;
        private String mDomainBoe;
        private String mDomainBoeHttps;
        private String mDomainHttpDns;
        private String mHostFirst;
        private String mHostSecond;
        private String mHostThird;
        private String mHttpDnsRequestFlags;
        private String mInitRegion;
        private String mIsDomestic;
        private String mIsDropFirstTnc;
        private String mManifestVersionCode;
        private String mNetAccessType;
        private String mOSApi;
        private String mOSVersion;
        private String mRegion;
        private String mSdkAppID;
        private String mSdkVersion;
        private String mStoreIdc;
        private String mSysRegion;
        private String mTNCRequestFlags;
        private String mTNCRequestHeader;
        private String mTNCRequestQuery;
        private String mUpdateVersionCode;
        private String mUserId;
        private String mVersionCode;
        private String mVersionName;

        public String getUserId() {
            return this.mUserId;
        }

        public void setUserId(String str) {
            this.mUserId = str;
        }

        public String getAppId() {
            return this.mAppId;
        }

        public void setAppId(String str) {
            this.mAppId = str;
        }

        public String getOSApi() {
            return this.mOSApi;
        }

        public void setOSApi(String str) {
            this.mOSApi = str;
        }

        public String getDeviceId() {
            return this.mDeviceId;
        }

        public void setDeviceId(String str) {
            this.mDeviceId = str;
        }

        public String getNetAccessType() {
            return this.mNetAccessType;
        }

        public void setNetAccessType(String str) {
            this.mNetAccessType = str;
        }

        public String getVersionCode() {
            return this.mVersionCode;
        }

        public void setVersionCode(String str) {
            this.mVersionCode = str;
        }

        public String getDeviceType() {
            return this.mDeviceType;
        }

        public void setDeviceType(String str) {
            this.mDeviceType = str;
        }

        public String getAppName() {
            return this.mAppName;
        }

        public void setAppName(String str) {
            this.mAppName = str;
        }

        public String getSdkAppID() {
            return this.mSdkAppID;
        }

        public void setSdkAppID(String str) {
            this.mSdkAppID = str;
        }

        public String getSdkVersion() {
            return this.mSdkVersion;
        }

        public void setSdkVersion(String str) {
            this.mSdkVersion = str;
        }

        public String getChannel() {
            return this.mChannel;
        }

        public void setChannel(String str) {
            this.mChannel = str;
        }

        public String getOSVersion() {
            return this.mOSVersion;
        }

        public void setOSVersion(String str) {
            this.mOSVersion = str;
        }

        public String getDevicePlatform() {
            return this.mDevicePlatform;
        }

        public void setDevicePlatform(String str) {
            this.mDevicePlatform = str;
        }

        public String getDeviceBrand() {
            return this.mDeviceBrand;
        }

        public void setDeviceBrand(String str) {
            this.mDeviceBrand = str;
        }

        public void setDeviceModel(String str) {
            this.mDeviceModel = str;
        }

        public String getDeviceModel() {
            return this.mDeviceModel;
        }

        public String getVersionName() {
            return this.mVersionName;
        }

        public void setVersionName(String str) {
            this.mVersionName = str;
        }

        public String getUpdateVersionCode() {
            return this.mUpdateVersionCode;
        }

        public void setUpdateVersionCode(String str) {
            this.mUpdateVersionCode = str;
        }

        public String getManifestVersionCode() {
            return this.mManifestVersionCode;
        }

        public void setManifestVersionCode(String str) {
            this.mManifestVersionCode = str;
        }

        public String getAbi() {
            return this.mAbi;
        }

        public void setAbi(String str) {
            this.mAbi = str;
        }

        public String getHostFirst() {
            return this.mHostFirst;
        }

        public void setHostFirst(String str) {
            this.mHostFirst = str;
        }

        public String getHostSecond() {
            return this.mHostSecond;
        }

        public void setHostSecond(String str) {
            this.mHostSecond = str;
        }

        public String getHostThird() {
            return this.mHostThird;
        }

        public void setHostThird(String str) {
            this.mHostThird = str;
        }

        public String getDomainHttpDns() {
            return this.mDomainHttpDns;
        }

        public void setDomainHttpDns(String str) {
            this.mDomainHttpDns = str;
        }

        public String getDomainBoe() {
            return this.mDomainBoe;
        }

        public void setDomainBoe(String str) {
            this.mDomainBoe = str;
        }

        public String getDomainBoeHttps() {
            return this.mDomainBoeHttps;
        }

        public void setDomainBoeHttps(String str) {
            this.mDomainBoeHttps = str;
        }

        public String getIsDropFirstTnc() {
            return this.mIsDropFirstTnc;
        }

        public void setIsDropFirstTnc(String str) {
            this.mIsDropFirstTnc = str;
        }

        public String getStoreIdc() {
            return this.mStoreIdc;
        }

        public void setStoreIdc(String str) {
            this.mStoreIdc = str;
        }

        public String getRegion() {
            return this.mRegion;
        }

        public void setRegion(String str) {
            this.mRegion = str;
        }

        public String getSysRegion() {
            return this.mSysRegion;
        }

        public void setSysRegion(String str) {
            this.mSysRegion = str;
        }

        public String getCarrierRegion() {
            return this.mCarrierRegion;
        }

        public void setCarrierRegion(String str) {
            this.mCarrierRegion = str;
        }

        public String getInitRegion() {
            return this.mInitRegion;
        }

        public void setInitRegion(String str) {
            this.mInitRegion = str;
        }

        public String getTNCRequestHeader() {
            return this.mTNCRequestHeader;
        }

        public void setTNCRequestHeader(String str) {
            this.mTNCRequestHeader = str;
        }

        public String getTNCRequestQuery() {
            return this.mTNCRequestQuery;
        }

        public void setTNCRequestQuery(String str) {
            this.mTNCRequestQuery = str;
        }

        public String getTNCRequestFlags() {
            return this.mTNCRequestFlags;
        }

        public void setTNCRequestFlags(String str) {
            this.mTNCRequestFlags = str;
        }

        public String getHttpDnsRequestFlags() {
            return this.mHttpDnsRequestFlags;
        }

        public void setHttpDnsRequestFlags(String str) {
            this.mHttpDnsRequestFlags = str;
        }

        public String getIsDomestic() {
            return this.mIsDomestic;
        }

        public void setIsDomestic(String str) {
            this.mIsDomestic = str;
        }

        public String getAbSdkVersion() {
            return this.mAbSdkVersion;
        }

        public void setAbSdkVersion(String str) {
            this.mAbSdkVersion = str;
        }
    }
}
