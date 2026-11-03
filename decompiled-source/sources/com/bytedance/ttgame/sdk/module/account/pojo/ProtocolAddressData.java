package com.bytedance.ttgame.sdk.module.account.pojo;
/* loaded from: classes8.dex */
public class ProtocolAddressData {
    public static final String POLICY_URL_VERSION = "policy_url_version";
    public static final String PRIVACY_POLICY_URL = "privacy_policy_url";
    public static final String PROTOCOL_URL_VERSION = "protocol_url_version";
    public static final String USER_AGREEMENT_URL = "user_agreement_url";
    public String policyUrlVersion;
    public String privacyPolicy;
    public String protocolUrlVersion;
    public String userAgreementUrl;

    public ProtocolAddressData(String str, String str2, String str3, String str4) {
        this.userAgreementUrl = str;
        this.privacyPolicy = str2;
        this.protocolUrlVersion = str3;
        this.policyUrlVersion = str4;
    }

    public ProtocolAddressData() {
    }
}
