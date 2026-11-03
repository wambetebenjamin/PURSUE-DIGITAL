package com.bytedance.ttgame.main.internal.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: GLogPathData.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u00014B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004Ba\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011J\t\u0010$\u001a\u00020\u0006HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0010HÆ\u0003Js\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÆ\u0001J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00101\u001a\u000202HÖ\u0001J\b\u00103\u001a\u00020\u0006H\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0016\u0010\u0018\u001a\n \u0019*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u00065"}, d2 = {"Lcom/bytedance/ttgame/main/internal/log/GLogPathData;", "", "builder", "Lcom/bytedance/ttgame/main/internal/log/GLogPathData$Builder;", "(Lcom/bytedance/ttgame/main/internal/log/GLogPathData$Builder;)V", "currentStep", "", "currentPage", "currentClass", "currentMethod", "currentLogic", "errorCode", "errorMsg", "tr", "", "extra", "Lorg/json/JSONObject;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;Lorg/json/JSONObject;)V", "getCurrentClass", "()Ljava/lang/String;", "getCurrentLogic", "getCurrentMethod", "getCurrentPage", "getCurrentStep", "currentTimeStr", "kotlin.jvm.PlatformType", "currentTimestamp", "", "getCurrentTimestamp", "()J", "getErrorCode", "getErrorMsg", "getExtra", "()Lorg/json/JSONObject;", "getTr", "()Ljava/lang/Throwable;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "Builder", "core_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GLogPathData {
    private final String currentClass;
    private final String currentLogic;
    private final String currentMethod;
    private final String currentPage;
    private final String currentStep;
    private final String currentTimeStr;
    private final long currentTimestamp;
    private final String errorCode;
    private final String errorMsg;
    private final JSONObject extra;
    private final Throwable tr;

    public /* synthetic */ GLogPathData(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    public final String component1() {
        return this.currentStep;
    }

    public final String component2() {
        return this.currentPage;
    }

    public final String component3() {
        return this.currentClass;
    }

    public final String component4() {
        return this.currentMethod;
    }

    public final String component5() {
        return this.currentLogic;
    }

    public final String component6() {
        return this.errorCode;
    }

    public final String component7() {
        return this.errorMsg;
    }

    public final Throwable component8() {
        return this.tr;
    }

    public final JSONObject component9() {
        return this.extra;
    }

    public final GLogPathData copy(String currentStep, String str, String str2, String str3, String str4, String str5, String str6, Throwable th, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(currentStep, "currentStep");
        return new GLogPathData(currentStep, str, str2, str3, str4, str5, str6, th, jSONObject);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GLogPathData) {
            GLogPathData gLogPathData = (GLogPathData) obj;
            return Intrinsics.areEqual(this.currentStep, gLogPathData.currentStep) && Intrinsics.areEqual(this.currentPage, gLogPathData.currentPage) && Intrinsics.areEqual(this.currentClass, gLogPathData.currentClass) && Intrinsics.areEqual(this.currentMethod, gLogPathData.currentMethod) && Intrinsics.areEqual(this.currentLogic, gLogPathData.currentLogic) && Intrinsics.areEqual(this.errorCode, gLogPathData.errorCode) && Intrinsics.areEqual(this.errorMsg, gLogPathData.errorMsg) && Intrinsics.areEqual(this.tr, gLogPathData.tr) && Intrinsics.areEqual(this.extra, gLogPathData.extra);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.currentStep.hashCode() * 31;
        String str = this.currentPage;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.currentClass;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.currentMethod;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.currentLogic;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.errorCode;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.errorMsg;
        int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Throwable th = this.tr;
        int hashCode8 = (hashCode7 + (th == null ? 0 : th.hashCode())) * 31;
        JSONObject jSONObject = this.extra;
        return hashCode8 + (jSONObject != null ? jSONObject.hashCode() : 0);
    }

    private GLogPathData(String str, String str2, String str3, String str4, String str5, String str6, String str7, Throwable th, JSONObject jSONObject) {
        this.currentStep = str;
        this.currentPage = str2;
        this.currentClass = str3;
        this.currentMethod = str4;
        this.currentLogic = str5;
        this.errorCode = str6;
        this.errorMsg = str7;
        this.tr = th;
        this.extra = jSONObject;
        long currentTimeMillis = System.currentTimeMillis();
        this.currentTimestamp = currentTimeMillis;
        this.currentTimeStr = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.getDefault()).format(new Date(currentTimeMillis));
    }

    /* synthetic */ GLogPathData(String str, String str2, String str3, String str4, String str5, String str6, String str7, Throwable th, JSONObject jSONObject, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, (i2 & 128) != 0 ? null : th, jSONObject);
    }

    public final String getCurrentStep() {
        return this.currentStep;
    }

    public final String getCurrentPage() {
        return this.currentPage;
    }

    public final String getCurrentClass() {
        return this.currentClass;
    }

    public final String getCurrentMethod() {
        return this.currentMethod;
    }

    public final String getCurrentLogic() {
        return this.currentLogic;
    }

    public final String getErrorCode() {
        return this.errorCode;
    }

    public final String getErrorMsg() {
        return this.errorMsg;
    }

    public final Throwable getTr() {
        return this.tr;
    }

    public final JSONObject getExtra() {
        return this.extra;
    }

    public final long getCurrentTimestamp() {
        return this.currentTimestamp;
    }

    private GLogPathData(Builder builder) {
        this(builder.getCurrentStep(), builder.getCurrentPage(), builder.getCurrentClass(), builder.getCurrentMethod(), builder.getCurrentLogic(), builder.getErrorCode(), builder.getErrorMsg(), builder.getTr(), builder.getExtra());
    }

    /* compiled from: GLogPathData.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\u001f\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0003J\u0010\u0010 \u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003J\u0010\u0010!\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\"\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003J\u0010\u0010#\u001a\u00020\u00002\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003J\u0010\u0010$\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014J\u0010\u0010%\u001a\u00020\u00002\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\"\u0010\u000b\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\"\u0010\r\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\bR\"\u0010\u0010\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\"\u0010\u0012\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\bR\"\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0005\u001a\u0004\u0018\u00010\u0014@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\"\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u0005\u001a\u0004\u0018\u00010\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006&"}, d2 = {"Lcom/bytedance/ttgame/main/internal/log/GLogPathData$Builder;", "", "currentStep", "", "(Ljava/lang/String;)V", "<set-?>", "currentClass", "getCurrentClass", "()Ljava/lang/String;", "currentLogic", "getCurrentLogic", "currentMethod", "getCurrentMethod", "currentPage", "getCurrentPage", "getCurrentStep", "errorCode", "getErrorCode", "errorMsg", "getErrorMsg", "Lorg/json/JSONObject;", "extra", "getExtra", "()Lorg/json/JSONObject;", "", "tr", "getTr", "()Ljava/lang/Throwable;", "build", "Lcom/bytedance/ttgame/main/internal/log/GLogPathData;", "setCurrentClass", "setCurrentLogic", "setCurrentMethod", "setCurrentPage", "setErrorCode", "setErrorMsg", "setExtra", "setThrowable", "core_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Builder {
        private String currentClass;
        private String currentLogic;
        private String currentMethod;
        private String currentPage;
        private final String currentStep;
        private String errorCode;
        private String errorMsg;
        private JSONObject extra;
        private Throwable tr;

        public Builder(String currentStep) {
            Intrinsics.checkNotNullParameter(currentStep, "currentStep");
            this.currentStep = currentStep;
        }

        public final String getCurrentStep() {
            return this.currentStep;
        }

        public final String getCurrentPage() {
            return this.currentPage;
        }

        public final String getCurrentClass() {
            return this.currentClass;
        }

        public final String getCurrentMethod() {
            return this.currentMethod;
        }

        public final String getCurrentLogic() {
            return this.currentLogic;
        }

        public final String getErrorCode() {
            return this.errorCode;
        }

        public final String getErrorMsg() {
            return this.errorMsg;
        }

        public final Throwable getTr() {
            return this.tr;
        }

        public final JSONObject getExtra() {
            return this.extra;
        }

        public final Builder setCurrentPage(String str) {
            this.currentPage = str;
            return this;
        }

        public final Builder setCurrentClass(String str) {
            this.currentClass = str;
            return this;
        }

        public final Builder setCurrentMethod(String str) {
            this.currentMethod = str;
            return this;
        }

        public final Builder setCurrentLogic(String str) {
            this.currentLogic = str;
            return this;
        }

        public final Builder setErrorCode(String str) {
            this.errorCode = str;
            return this;
        }

        public final Builder setErrorMsg(String str) {
            this.errorMsg = str;
            return this;
        }

        public final Builder setThrowable(Throwable th) {
            this.tr = th;
            return this;
        }

        public final Builder setExtra(JSONObject jSONObject) {
            this.extra = jSONObject;
            return this;
        }

        public final GLogPathData build() {
            return new GLogPathData(this, null);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        sb.append("time: " + this.currentTimeStr + ", ");
        sb.append("step: " + this.currentStep);
        if (this.currentPage != null) {
            sb.append(", page: " + this.currentPage);
        }
        if (this.currentClass != null) {
            sb.append(", class: " + this.currentClass);
        }
        if (this.currentMethod != null) {
            sb.append(", method: " + this.currentMethod);
        }
        if (this.currentLogic != null) {
            sb.append(", logic: " + this.currentLogic);
        }
        if (this.errorCode != null) {
            sb.append(", errorCode: " + this.errorCode);
        }
        if (this.errorMsg != null) {
            sb.append(", errorMsg: " + this.errorMsg);
        }
        if (this.tr != null) {
            sb.append(", throwable: " + this.tr.getLocalizedMessage());
        }
        if (this.extra != null) {
            sb.append(", extra: " + this.extra);
        }
        sb.append(", threadId: " + Thread.currentThread().getId());
        sb.append(" }");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "result.toString()");
        return sb2;
    }
}
