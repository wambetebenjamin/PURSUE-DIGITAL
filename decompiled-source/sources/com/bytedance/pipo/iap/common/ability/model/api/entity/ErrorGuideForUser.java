package com.bytedance.pipo.iap.common.ability.model.api.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.json.JSONObject;
/* compiled from: ErrorGuideForUser.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0010R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0011"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/api/entity/ErrorGuideForUser;", "", "errorCode", "", "errorMessage", "guideLink", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getErrorCode", "()Ljava/lang/String;", "setErrorCode", "(Ljava/lang/String;)V", "getErrorMessage", "setErrorMessage", "getGuideLink", "setGuideLink", "toJson", "Lorg/json/JSONObject;", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ErrorGuideForUser {
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("error_message")
    private String errorMessage;
    @SerializedName("guide_link")
    private String guideLink;

    public ErrorGuideForUser() {
        this(null, null, null, 7, null);
    }

    public ErrorGuideForUser(String str, String str2, String str3) {
        this.errorCode = str;
        this.errorMessage = str2;
        this.guideLink = str3;
    }

    public /* synthetic */ ErrorGuideForUser(String str, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3);
    }

    public final String getErrorCode() {
        return this.errorCode;
    }

    public final void setErrorCode(String str) {
        this.errorCode = str;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public final String getGuideLink() {
        return this.guideLink;
    }

    public final void setGuideLink(String str) {
        this.guideLink = str;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("error_code", this.errorCode);
            jSONObject.put("error_message", this.errorMessage);
            jSONObject.put("guide_link", this.guideLink);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }
}
