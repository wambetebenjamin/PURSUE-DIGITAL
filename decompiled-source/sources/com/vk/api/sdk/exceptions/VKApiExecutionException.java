package com.vk.api.sdk.exceptions;

import android.os.Bundle;
import com.meituan.robust.Constants;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: VKApiExecutionException.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0016\u0018\u0000 82\u00020\u0001:\u00018BQ\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000eJ\u0013\u00100\u001a\u00020\u00072\b\u00101\u001a\u0004\u0018\u000102H\u0096\u0002J\u000e\u00103\u001a\u00020\u00072\u0006\u00104\u001a\u00020\u0003J\u0006\u00105\u001a\u00020\u0007J\b\u00106\u001a\u00020\u0003H\u0016J\b\u00107\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0013\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010 \u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b \u0010\u001eR\u0011\u0010!\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b!\u0010\u001eR\u0011\u0010\"\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\"\u0010\u001eR\u0011\u0010#\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b#\u0010\u001eR\u0011\u0010$\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b$\u0010\u001eR\u0011\u0010%\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b%\u0010\u001eR\u0011\u0010&\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b&\u0010\u001eR\u0011\u0010'\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b'\u0010\u001eR\u0013\u0010(\u001a\u0004\u0018\u00010)8F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0011\u0010,\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b-\u0010\u0010R\u0011\u0010.\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b/\u0010\u0010¨\u00069"}, d2 = {"Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "Lcom/vk/api/sdk/exceptions/VKApiException;", "code", "", "apiMethod", "", "hasLocalizedMessage", "", "detailMessage", "extra", "Landroid/os/Bundle;", "executeErrors", "", "errorMsg", "(ILjava/lang/String;ZLjava/lang/String;Landroid/os/Bundle;Ljava/util/List;Ljava/lang/String;)V", "getApiMethod", "()Ljava/lang/String;", "captchaImg", "getCaptchaImg", "captchaSid", "getCaptchaSid", "getCode", "()I", "getDetailMessage", "getErrorMsg", "getExecuteErrors", "()Ljava/util/List;", "getExtra", "()Landroid/os/Bundle;", "getHasLocalizedMessage", "()Z", "isAccessError", "isCaptchaError", "isCompositeError", "isInternalServerError", "isInvalidCredentialsError", "isTokenConfirmationRequired", "isTooManyRequestsError", "isUserConfirmRequired", "isValidationRequired", "userBanInfo", "Lorg/json/JSONObject;", "getUserBanInfo", "()Lorg/json/JSONObject;", "userConfirmText", "getUserConfirmText", "validationUrl", "getValidationUrl", "equals", "other", "", "hasError", "errorCode", "hasExtra", "hashCode", "toString", "Companion", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public class VKApiExecutionException extends VKApiException {
    public static final Companion Companion = new Companion(null);
    public static final long serialVersionUID = 7524047853274172872L;
    private final String apiMethod;
    private final int code;
    private final String detailMessage;
    private final String errorMsg;
    private final List<VKApiExecutionException> executeErrors;
    private final Bundle extra;
    private final boolean hasLocalizedMessage;

    public VKApiExecutionException(int i2, String str, boolean z, String str2) {
        this(i2, str, z, str2, null, null, null, 112, null);
    }

    public VKApiExecutionException(int i2, String str, boolean z, String str2, Bundle bundle) {
        this(i2, str, z, str2, bundle, null, null, 96, null);
    }

    public VKApiExecutionException(int i2, String str, boolean z, String str2, Bundle bundle, List<? extends VKApiExecutionException> list) {
        this(i2, str, z, str2, bundle, list, null, 64, null);
    }

    public final int getCode() {
        return this.code;
    }

    public final String getApiMethod() {
        return this.apiMethod;
    }

    public final boolean getHasLocalizedMessage() {
        return this.hasLocalizedMessage;
    }

    public final String getDetailMessage() {
        return this.detailMessage;
    }

    public /* synthetic */ VKApiExecutionException(int i2, String str, boolean z, String str2, Bundle bundle, List list, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, str, z, str2, (i3 & 16) != 0 ? Bundle.EMPTY : bundle, (i3 & 32) != 0 ? null : list, (i3 & 64) != 0 ? null : str3);
    }

    public final Bundle getExtra() {
        return this.extra;
    }

    public final List<VKApiExecutionException> getExecuteErrors() {
        return this.executeErrors;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public VKApiExecutionException(int i2, String apiMethod, boolean z, String detailMessage, Bundle bundle, List<? extends VKApiExecutionException> list, String str) {
        super(detailMessage);
        Intrinsics.checkParameterIsNotNull(apiMethod, "apiMethod");
        Intrinsics.checkParameterIsNotNull(detailMessage, "detailMessage");
        this.code = i2;
        this.apiMethod = apiMethod;
        this.hasLocalizedMessage = z;
        this.detailMessage = detailMessage;
        this.extra = bundle;
        this.executeErrors = list;
        this.errorMsg = str;
    }

    public final String getErrorMsg() {
        return this.errorMsg;
    }

    public final boolean isCompositeError() {
        return this.code == Integer.MIN_VALUE;
    }

    public final boolean isAccessError() {
        int i2 = this.code;
        return i2 == 15 || i2 == 30 || i2 == 203 || i2 == 200 || i2 == 201;
    }

    public final boolean isInternalServerError() {
        int i2 = this.code;
        return i2 == 1 || i2 == 10 || i2 == 13;
    }

    public final boolean isTooManyRequestsError() {
        return this.code == 6;
    }

    public final boolean isInvalidCredentialsError() {
        int i2 = this.code;
        return i2 == 4 || i2 == 5;
    }

    public final boolean isUserConfirmRequired() {
        return this.code == 24;
    }

    public final boolean isTokenConfirmationRequired() {
        return this.code == 25;
    }

    public final boolean isValidationRequired() {
        return this.code == 17;
    }

    public final boolean isCaptchaError() {
        return this.code == 14;
    }

    public final String getCaptchaSid() {
        String string;
        Bundle bundle = this.extra;
        return (bundle == null || (string = bundle.getString(VKApiCodes.EXTRA_CAPTCHA_SID, "")) == null) ? "" : string;
    }

    public final String getCaptchaImg() {
        String string;
        Bundle bundle = this.extra;
        return (bundle == null || (string = bundle.getString(VKApiCodes.EXTRA_CAPTCHA_IMG, "")) == null) ? "" : string;
    }

    public final String getValidationUrl() {
        String string;
        Bundle bundle = this.extra;
        return (bundle == null || (string = bundle.getString(VKApiCodes.EXTRA_VALIDATION_URL, "")) == null) ? "" : string;
    }

    public final String getUserConfirmText() {
        String string;
        Bundle bundle = this.extra;
        return (bundle == null || (string = bundle.getString("confirmation_text", "")) == null) ? "" : string;
    }

    public final JSONObject getUserBanInfo() {
        String string;
        Bundle bundle = this.extra;
        if (bundle == null || (string = bundle.getString(VKApiCodes.EXTRA_USER_BAN_INFO)) == null) {
            return null;
        }
        return new JSONObject(string);
    }

    public final boolean hasExtra() {
        Bundle bundle = this.extra;
        return bundle != null && (Intrinsics.areEqual(bundle, Bundle.EMPTY) ^ true);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VKApiExecutionException) {
            VKApiExecutionException vKApiExecutionException = (VKApiExecutionException) obj;
            if (this.code == vKApiExecutionException.code) {
                Bundle bundle = this.extra;
                Bundle bundle2 = vKApiExecutionException.extra;
                if (!(bundle != null ? !Intrinsics.areEqual(bundle, bundle2) : bundle2 != null)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.code * 31;
        Bundle bundle = this.extra;
        return i2 + (bundle != null ? bundle.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder append = new StringBuilder().append("VKApiExecutionException{").append("code=").append(this.code).append(", extra=").append(this.extra).append(", method=").append(this.apiMethod).append(", executeErrors=");
        List<VKApiExecutionException> list = this.executeErrors;
        return append.append(list != null ? CollectionsKt.joinToString$default(list, null, Constants.ARRAY_TYPE, "]", 0, null, null, 57, null) : null).append(", super=").append(super.toString()).append("}").toString();
    }

    public final boolean hasError(int i2) {
        boolean z;
        if (this.code == i2) {
            return true;
        }
        List<VKApiExecutionException> list = this.executeErrors;
        Object obj = null;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (((VKApiExecutionException) next).code == i2) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    obj = next;
                    break;
                }
            }
            obj = (VKApiExecutionException) obj;
        }
        return obj != null;
    }

    /* compiled from: VKApiExecutionException.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/vk/api/sdk/exceptions/VKApiExecutionException$Companion;", "", "()V", "serialVersionUID", "", "parse", "Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "json", "Lorg/json/JSONObject;", "methodName", "", "extra", "Landroid/os/Bundle;", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Companion {
        public final VKApiExecutionException parse(JSONObject jSONObject) {
            return parse$default(this, jSONObject, null, null, 6, null);
        }

        public final VKApiExecutionException parse(JSONObject jSONObject, String str) {
            return parse$default(this, jSONObject, str, null, 4, null);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ VKApiExecutionException parse$default(Companion companion, JSONObject jSONObject, String str, Bundle bundle, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                str = null;
            }
            if ((i2 & 4) != 0) {
                bundle = null;
            }
            return companion.parse(jSONObject, str, bundle);
        }

        public final VKApiExecutionException parse(JSONObject json, String str, Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            if (str == null) {
                str = json.optString("method");
            }
            String str2 = str != null ? str : "";
            int i2 = json.getInt("error_code");
            String optString = json.optString("error_msg");
            String str3 = optString != null ? optString : "";
            if (json.has("error_text")) {
                String optString2 = json.optString("error_text");
                return new VKApiExecutionException(i2, str2, true, optString2 != null ? optString2 : "", bundle, null, str3, 32, null);
            }
            String optString3 = json.optString("error_msg");
            String str4 = optString3 != null ? optString3 : "";
            return new VKApiExecutionException(i2, str2, false, str4 + " | by [" + str2 + ']', bundle, null, str4, 32, null);
        }
    }
}
