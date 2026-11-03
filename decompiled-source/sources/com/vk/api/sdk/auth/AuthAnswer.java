package com.vk.api.sdk.auth;

import com.bytedance.sdk.account.platform.base.i;
import gsdk.impl.account.toutiao.f;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AuthAnswer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u001d\u0018\u0000 72\u00020\u0001:\u00017B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B½\u0001\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000e\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u001b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0006¢\u0006\u0002\u0010\u001dR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0011\u0010\u0016\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0011\u0010\u0017\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u0011\u0010\u0014\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u001a\u0010\u000e\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010)\"\u0004\b*\u0010+R\u0011\u0010\u0013\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0011\u0010\u001c\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001fR\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001fR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b3\u0010'R\u0011\u0010\u0012\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b5\u00106¨\u00068"}, d2 = {"Lcom/vk/api/sdk/auth/AuthAnswer;", "", "jo", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", i.CT_KEY_ACCESS_TOKEN, "", "secret", f.v, "", "expiresIn", "httpsRequired", "", "trustedHash", "isSecretNeeded", "redirectUrl", "validationType", "Lcom/vk/api/sdk/auth/ValidationType;", "validationSid", "phoneMask", "errorType", "email", "error", "errorDescription", "banInfo", "Lcom/vk/api/sdk/auth/BanInfo;", "restoreRequestId", "", "restoreHash", "(Ljava/lang/String;Ljava/lang/String;IIZLjava/lang/String;ZLjava/lang/String;Lcom/vk/api/sdk/auth/ValidationType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/vk/api/sdk/auth/BanInfo;JLjava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getBanInfo", "()Lcom/vk/api/sdk/auth/BanInfo;", "getEmail", "getError", "getErrorDescription", "getErrorType", "getExpiresIn", "()I", "getHttpsRequired", "()Z", "setSecretNeeded", "(Z)V", "getPhoneMask", "getRedirectUrl", "getRestoreHash", "getRestoreRequestId", "()J", "getSecret", "getTrustedHash", "getUserId", "getValidationSid", "getValidationType", "()Lcom/vk/api/sdk/auth/ValidationType;", "Companion", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class AuthAnswer {
    public static final Companion Companion = new Companion(null);
    public static final String ERROR_INVALID_CLIENT = "invalid_client";
    public static final String ERROR_INVALID_REQUEST = "invalid_request";
    public static final String ERROR_INVALID_TOKEN = "invalid_token";
    public static final String ERROR_NEED_AUTH_CHECK = "need_authcheck";
    public static final String ERROR_NEED_VALIDATE = "need_validation";
    public static final String ERROR_TYPE_CANCEL_BY_OWNER_NEEDED = "cancel_by_owner_needed";
    public static final String ERROR_TYPE_FACEBOOK_EMAIL_REGISTERED = "facebook_email_already_registered";
    public static final String ERROR_TYPE_FACEBOOK_EMAIL_USED = "facebook_email_used";
    public static final String ERROR_TYPE_TOO_MUCH_TRIES = "too_much_tries";
    public static final String ERROR_TYPE_WRONG_CODE = "wrong_otp";
    public static final String ERROR_TYPE_WRONG_CODE_FORMAT = "otp_format_is_incorrect";
    private final String accessToken;
    private final BanInfo banInfo;
    private final String email;
    private final String error;
    private final String errorDescription;
    private final String errorType;
    private final int expiresIn;
    private final boolean httpsRequired;
    private boolean isSecretNeeded;
    private final String phoneMask;
    private final String redirectUrl;
    private final String restoreHash;
    private final long restoreRequestId;
    private final String secret;
    private final String trustedHash;
    private final int userId;
    private final String validationSid;
    private final ValidationType validationType;

    public AuthAnswer() {
        this(null, null, 0, 0, false, null, false, null, null, null, null, null, null, null, null, null, 0L, null, 262143, null);
    }

    public AuthAnswer(String accessToken, String secret, int i2, int i3, boolean z, String trustedHash, boolean z2, String redirectUrl, ValidationType validationType, String validationSid, String phoneMask, String errorType, String str, String error, String errorDescription, BanInfo banInfo, long j2, String restoreHash) {
        Intrinsics.checkParameterIsNotNull(accessToken, "accessToken");
        Intrinsics.checkParameterIsNotNull(secret, "secret");
        Intrinsics.checkParameterIsNotNull(trustedHash, "trustedHash");
        Intrinsics.checkParameterIsNotNull(redirectUrl, "redirectUrl");
        Intrinsics.checkParameterIsNotNull(validationType, "validationType");
        Intrinsics.checkParameterIsNotNull(validationSid, "validationSid");
        Intrinsics.checkParameterIsNotNull(phoneMask, "phoneMask");
        Intrinsics.checkParameterIsNotNull(errorType, "errorType");
        Intrinsics.checkParameterIsNotNull(error, "error");
        Intrinsics.checkParameterIsNotNull(errorDescription, "errorDescription");
        Intrinsics.checkParameterIsNotNull(restoreHash, "restoreHash");
        this.accessToken = accessToken;
        this.secret = secret;
        this.userId = i2;
        this.expiresIn = i3;
        this.httpsRequired = z;
        this.trustedHash = trustedHash;
        this.isSecretNeeded = z2;
        this.redirectUrl = redirectUrl;
        this.validationType = validationType;
        this.validationSid = validationSid;
        this.phoneMask = phoneMask;
        this.errorType = errorType;
        this.email = str;
        this.error = error;
        this.errorDescription = errorDescription;
        this.banInfo = banInfo;
        this.restoreRequestId = j2;
        this.restoreHash = restoreHash;
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final String getSecret() {
        return this.secret;
    }

    public final int getUserId() {
        return this.userId;
    }

    public final int getExpiresIn() {
        return this.expiresIn;
    }

    public final boolean getHttpsRequired() {
        return this.httpsRequired;
    }

    public final String getTrustedHash() {
        return this.trustedHash;
    }

    public final boolean isSecretNeeded() {
        return this.isSecretNeeded;
    }

    public final void setSecretNeeded(boolean z) {
        this.isSecretNeeded = z;
    }

    public final String getRedirectUrl() {
        return this.redirectUrl;
    }

    public /* synthetic */ AuthAnswer(String str, String str2, int i2, int i3, boolean z, String str3, boolean z2, String str4, ValidationType validationType, String str5, String str6, String str7, String str8, String str9, String str10, BanInfo banInfo, long j2, String str11, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? "" : str, (i4 & 2) != 0 ? "" : str2, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) == 0 ? i3 : 0, (i4 & 16) != 0 ? true : z, (i4 & 32) != 0 ? "" : str3, (i4 & 64) == 0 ? z2 : true, (i4 & 128) != 0 ? "" : str4, (i4 & 256) != 0 ? ValidationType.URL : validationType, (i4 & 512) != 0 ? "" : str5, (i4 & 1024) != 0 ? "" : str6, (i4 & 2048) != 0 ? "" : str7, (i4 & 4096) != 0 ? "" : str8, (i4 & 8192) != 0 ? "" : str9, (i4 & 16384) != 0 ? "" : str10, (i4 & 32768) != 0 ? null : banInfo, (i4 & 65536) != 0 ? 0L : j2, (i4 & 131072) != 0 ? "" : str11);
    }

    public final ValidationType getValidationType() {
        return this.validationType;
    }

    public final String getValidationSid() {
        return this.validationSid;
    }

    public final String getPhoneMask() {
        return this.phoneMask;
    }

    public final String getErrorType() {
        return this.errorType;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getError() {
        return this.error;
    }

    public final String getErrorDescription() {
        return this.errorDescription;
    }

    public final BanInfo getBanInfo() {
        return this.banInfo;
    }

    public final long getRestoreRequestId() {
        return this.restoreRequestId;
    }

    public final String getRestoreHash() {
        return this.restoreHash;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AuthAnswer(org.json.JSONObject r25) {
        /*
            r24 = this;
            r0 = r25
            java.lang.String r1 = "jo"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r1)
            java.lang.String r1 = "error"
            java.lang.String r1 = r0.optString(r1)
            java.lang.String r2 = "jo.optString(\"error\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.String r2 = "error_description"
            java.lang.String r15 = r0.optString(r2)
            java.lang.String r2 = "jo.optString(\"error_description\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r15, r2)
            java.lang.String r2 = "error_type"
            java.lang.String r14 = r0.optString(r2)
            java.lang.String r2 = "jo.optString(\"error_type\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r14, r2)
            java.lang.String r2 = "email"
            java.lang.String r16 = r0.optString(r2)
            java.lang.String r2 = "redirect_uri"
            java.lang.String r10 = r0.optString(r2)
            java.lang.String r2 = "jo.optString(\"redirect_uri\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r2)
            java.lang.String r2 = "validation_type"
            java.lang.String r2 = r0.optString(r2)
            com.vk.api.sdk.auth.ValidationType$Companion r3 = com.vk.api.sdk.auth.ValidationType.Companion
            java.lang.String r4 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r4)
            com.vk.api.sdk.auth.ValidationType r11 = r3.parse(r2)
            java.lang.String r2 = "validation_sid"
            java.lang.String r12 = r0.optString(r2)
            java.lang.String r2 = "jo.optString(\"validation_sid\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r2)
            java.lang.String r2 = "phone_mask"
            java.lang.String r13 = r0.optString(r2)
            java.lang.String r2 = "jo.optString(\"phone_mask\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r2)
            java.lang.String r2 = "access_token"
            java.lang.String r3 = r0.optString(r2)
            java.lang.String r2 = "jo.optString(\"access_token\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r2)
            java.lang.String r2 = "secret"
            java.lang.String r4 = r0.optString(r2)
            java.lang.String r2 = "jo.optString(\"secret\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r2)
            java.lang.String r2 = "user_id"
            int r5 = r0.optInt(r2)
            java.lang.String r2 = "expires_in"
            int r6 = r0.optInt(r2)
            java.lang.String r2 = "https_required"
            java.lang.String r7 = "1"
            java.lang.String r2 = r0.optString(r2, r7)
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r7)
            java.lang.String r2 = "trusted_hash"
            java.lang.String r8 = r0.optString(r2)
            java.lang.String r2 = "jo.optString(\"trusted_hash\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r2)
            java.lang.String r2 = "ban_info"
            org.json.JSONObject r2 = r0.optJSONObject(r2)
            if (r2 == 0) goto Lac
            com.vk.api.sdk.auth.BanInfo$Companion r9 = com.vk.api.sdk.auth.BanInfo.Companion
            com.vk.api.sdk.auth.BanInfo r2 = r9.parse(r2)
            goto Lad
        Lac:
            r2 = 0
        Lad:
            r18 = r2
            java.lang.String r2 = "restore_request_id"
            long r19 = r0.optLong(r2)
            java.lang.String r2 = "restore_hash"
            java.lang.String r0 = r0.optString(r2)
            r21 = r0
            java.lang.String r2 = "jo.optString(\"restore_hash\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            r22 = 64
            r23 = 0
            r9 = 0
            r2 = r24
            r0 = r15
            r15 = r16
            r16 = r1
            r17 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r21, r22, r23)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vk.api.sdk.auth.AuthAnswer.<init>(org.json.JSONObject):void");
    }

    /* compiled from: AuthAnswer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/vk/api/sdk/auth/AuthAnswer$Companion;", "", "()V", "ERROR_INVALID_CLIENT", "", "ERROR_INVALID_REQUEST", "ERROR_INVALID_TOKEN", "ERROR_NEED_AUTH_CHECK", "ERROR_NEED_VALIDATE", "ERROR_TYPE_CANCEL_BY_OWNER_NEEDED", "ERROR_TYPE_FACEBOOK_EMAIL_REGISTERED", "ERROR_TYPE_FACEBOOK_EMAIL_USED", "ERROR_TYPE_TOO_MUCH_TRIES", "ERROR_TYPE_WRONG_CODE", "ERROR_TYPE_WRONG_CODE_FORMAT", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
