package com.bytedance.ttgame.module.database.api;

import android.text.TextUtils;
import com.bytedance.ttgame.library.luffy.db.PrimaryKeyName;
import com.bytedance.ttgame.library.luffy.db.TableName;
import com.bytedance.ttgame.module.account.api.Constant;
import com.bytedance.ttgame.module.compliance.impl.realname.RealNameService;
import com.bytedance.ttgame.sdk.module.account.pojo.ProtocolAddressData;
import com.bytedance.ttgame.sdk.module.skin.constants.SkinConfig;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
@TableName(Constant.TABLE_NAME_REQUEST_CLOUD)
/* loaded from: classes5.dex */
public class RequestCloudData implements Serializable {
    public static final long serialVersionUID = 4558784423616411766L;
    @SerializedName("age_limit")
    public int ageLimit;
    @SerializedName("bind_limit")
    public int bindLimit;
    @SerializedName("gate_cd")
    public int gateCD;
    @SerializedName("gate_type")
    public int gateType;
    @SerializedName("hide_login_way")
    public List<Integer> hide_login_way;
    @PrimaryKeyName
    public int id;
    @SerializedName("identity_type")
    public int identityType;
    @SerializedName("little_helper")
    public String little_helper;
    @SerializedName("login_list")
    public List<Integer> loginList;
    @SerializedName("logo_url")
    public String logoUrl;
    @SerializedName("one_key_conf")
    public String oneKeyConf;
    @SerializedName("policy_url")
    public String policyUrl;
    @SerializedName(ProtocolAddressData.POLICY_URL_VERSION)
    public String policyUrlVersion;
    @SerializedName("protocol_check")
    public boolean protocolCheck;
    @SerializedName("protocol_url")
    public String protocolUrl;
    @SerializedName(ProtocolAddressData.PROTOCOL_URL_VERSION)
    public String protocolUrlVersion;
    @SerializedName(SkinConfig.SKIN)
    public String skin;
    @SerializedName("to_resolve_domains")
    public List<String> to_resolve_domains;
    @SerializedName("zb_protocol_url")
    public String zbProtocolUrl;
    @SerializedName("display_visitor")
    public boolean display_visitor = true;
    @SerializedName("unlimited_visitor_status")
    public boolean unlimited_visitor_status = false;
    @SerializedName("multi_bind_status")
    public boolean multi_bind_status = false;
    @SerializedName("game_net_quality_report")
    public boolean game_net_quality_report = false;
    @SerializedName("pass_emulator")
    public boolean pass_emulator = true;
    @SerializedName("is_can_unbind")
    public boolean isCanUnbind = true;
    @SerializedName("display_visitor_conf")
    public boolean display_visitor_conf = true;
    @SerializedName("sensitive_feature_age_rule")
    public String sensitiveFeatureAgeRule = "";
    @SerializedName("login_priority")
    public String loginPriority = "";
    @SerializedName("pass_emulator_payment")
    public boolean pass_emulator_payment = true;
    @SerializedName(RealNameService.CLOUD_KEY_IDENTITY_VERIFY_TYPE)
    public int identityVerifyType = 0;
    @SerializedName(RealNameService.CLOUD_KEY_IDENTITY_IS_CAN_SKIP)
    public boolean identityIsCanSkip = true;
    @SerializedName("SMS_channel_id")
    public String SMS_channel_id = "";
    @SerializedName("authentication_url")
    public String authentication_url = "";
    @SerializedName("children_privacy_protection_url")
    public String children_privacy_protection_url = "";
    @SerializedName("sms_template_id")
    public String sms_template_id = "";
    @SerializedName("change_mobile_sms_template_id")
    public String change_mobile_sms_template_id = "";

    public String toString() {
        return "RequestCloudData{id=" + this.id + ", logoUrl='" + this.logoUrl + "', protocolUrl='" + this.protocolUrl + "', policyUrl='" + this.policyUrl + "', protocolUrlVersion='" + this.protocolUrlVersion + "', policyUrlVersion='" + this.policyUrlVersion + "', protocolCheck=" + this.protocolCheck + ", identityType=" + this.identityType + ", little_helper='" + this.little_helper + "', display_visitor=" + this.display_visitor + ", hide_login_way=" + this.hide_login_way + ", loginList=" + this.loginList + ", oneKeyConf='" + this.oneKeyConf + "', unlimited_visitor_status=" + this.unlimited_visitor_status + ", multi_bind_status=" + this.multi_bind_status + ", to_resolve_domains=" + this.to_resolve_domains + ", game_net_quality_report=" + this.game_net_quality_report + ", zbProtocolUrl='" + this.zbProtocolUrl + "', pass_emulator=" + this.pass_emulator + ", display_visitor_conf=" + this.display_visitor_conf + ", isCanUnbind=" + this.isCanUnbind + ", bindLimit=" + this.bindLimit + ", gateType=" + this.gateType + ", ageLimit=" + this.ageLimit + ", gateCD=" + this.gateCD + ", pass_emulator_payment=" + this.pass_emulator_payment + ", skin=" + this.skin + ", identity_verify_type=" + this.identityVerifyType + ", identity_is_can_skip=" + this.identityIsCanSkip + ", SMS_channel_id=" + this.SMS_channel_id + ", authentication_url=" + this.authentication_url + ", children_privacy_protection_url=" + this.children_privacy_protection_url + ", sms_template_id=" + this.sms_template_id + ", change_mobile_sms_template_id=" + this.change_mobile_sms_template_id + ", sensitive_feature_age_rule=" + this.sensitiveFeatureAgeRule + ", login_priority=" + this.loginPriority + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RequestCloudData) {
            RequestCloudData requestCloudData = (RequestCloudData) obj;
            return this.id == requestCloudData.id && this.protocolCheck == requestCloudData.protocolCheck && this.identityType == requestCloudData.identityType && this.display_visitor == requestCloudData.display_visitor && this.unlimited_visitor_status == requestCloudData.unlimited_visitor_status && this.multi_bind_status == requestCloudData.multi_bind_status && this.game_net_quality_report == requestCloudData.game_net_quality_report && this.pass_emulator == requestCloudData.pass_emulator && this.isCanUnbind == requestCloudData.isCanUnbind && this.bindLimit == requestCloudData.bindLimit && this.gateType == requestCloudData.gateType && this.ageLimit == requestCloudData.ageLimit && this.display_visitor_conf == requestCloudData.display_visitor_conf && this.gateCD == requestCloudData.gateCD && this.pass_emulator_payment == requestCloudData.pass_emulator_payment && this.identityVerifyType == requestCloudData.identityVerifyType && this.identityIsCanSkip == requestCloudData.identityIsCanSkip && TextUtils.equals(this.logoUrl, requestCloudData.logoUrl) && TextUtils.equals(this.protocolUrl, requestCloudData.protocolUrl) && TextUtils.equals(this.children_privacy_protection_url, requestCloudData.children_privacy_protection_url) && TextUtils.equals(this.authentication_url, requestCloudData.authentication_url) && TextUtils.equals(this.policyUrl, requestCloudData.policyUrl) && TextUtils.equals(this.protocolUrlVersion, requestCloudData.protocolUrlVersion) && TextUtils.equals(this.policyUrlVersion, requestCloudData.policyUrlVersion) && TextUtils.equals(this.little_helper, requestCloudData.little_helper) && isListEquals(this.hide_login_way, requestCloudData.hide_login_way) && isListEquals(this.loginList, requestCloudData.loginList) && TextUtils.equals(this.oneKeyConf, requestCloudData.oneKeyConf) && isListEquals(this.to_resolve_domains, requestCloudData.to_resolve_domains) && TextUtils.equals(this.zbProtocolUrl, requestCloudData.zbProtocolUrl) && TextUtils.equals(this.skin, requestCloudData.skin) && TextUtils.equals(this.SMS_channel_id, requestCloudData.SMS_channel_id) && TextUtils.equals(this.sms_template_id, requestCloudData.sms_template_id) && TextUtils.equals(this.sensitiveFeatureAgeRule, requestCloudData.sensitiveFeatureAgeRule) && TextUtils.equals(this.loginPriority, requestCloudData.loginPriority);
        }
        return false;
    }

    private <T> boolean isListEquals(List<T> list, List<T> list2) {
        if ((list == null || list.size() == 0) && (list2 == null || list2.size() == 0)) {
            return true;
        }
        return list.equals(list2);
    }
}
