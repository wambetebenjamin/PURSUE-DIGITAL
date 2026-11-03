package com.bytedance.sdk.bridge.model;

import android.text.TextUtils;
import com.bytedance.sdk.bridge.o;
import gsdk.impl.webview.DEFAULT.bv;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.json.JSONObject;
/* compiled from: BridgeResult.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 \u00172\u00020\u0001:\u0002\u0016\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0015\u001a\u00020\nR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0018"}, d2 = {"Lcom/bytedance/sdk/bridge/model/BridgeResult;", "", "()V", "code", "", "getCode", "()I", "setCode", "(I)V", "data", "Lorg/json/JSONObject;", "getData", "()Lorg/json/JSONObject;", "setData", "(Lorg/json/JSONObject;)V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "toJSON", "CODE", "Companion", "bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class b {
    public static final C0203b Companion = new C0203b(null);

    /* renamed from: d  reason: collision with root package name */
    private static final String f13252d = "BridgeResult";

    /* renamed from: a  reason: collision with root package name */
    private int f13253a;

    /* renamed from: b  reason: collision with root package name */
    private String f13254b;

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f13255c;

    private b() {
        this.f13253a = a.ERROR.getValue();
    }

    public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final int getCode() {
        return this.f13253a;
    }

    public final void setCode(int i2) {
        this.f13253a = i2;
    }

    public final String getMessage() {
        return this.f13254b;
    }

    public final void setMessage(String str) {
        this.f13254b = str;
    }

    public final JSONObject getData() {
        return this.f13255c;
    }

    public final void setData(JSONObject jSONObject) {
        this.f13255c = jSONObject;
    }

    public final JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("code", this.f13253a);
        String str = this.f13254b;
        if (str != null) {
            jSONObject.put("status", str);
        }
        JSONObject jSONObject2 = this.f13255c;
        if (jSONObject2 != null) {
            jSONObject.put("data", jSONObject2);
        }
        return jSONObject;
    }

    /* compiled from: BridgeResult.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J \u0010\n\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J \u0010\u000b\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J \u0010\f\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J \u0010\r\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/bytedance/sdk/bridge/model/BridgeResult$Companion;", "", "()V", "TAG", "", "createErrorResult", "Lcom/bytedance/sdk/bridge/model/BridgeResult;", "message", "data", "Lorg/json/JSONObject;", "createMethodNotFoundResult", "createNoPrivilegeResult", "createParamsErrorResult", "createSuccessResult", "bridge_release"}, k = 1, mv = {1, 1, 15})
    /* renamed from: com.bytedance.sdk.bridge.model.b$b  reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public static final class C0203b {
        public final b createErrorResult() {
            return createErrorResult$default(this, null, null, 3, null);
        }

        public final b createErrorResult(String str) {
            return createErrorResult$default(this, str, null, 2, null);
        }

        public final b createMethodNotFoundResult() {
            return createMethodNotFoundResult$default(this, null, null, 3, null);
        }

        public final b createMethodNotFoundResult(String str) {
            return createMethodNotFoundResult$default(this, str, null, 2, null);
        }

        public final b createNoPrivilegeResult() {
            return createNoPrivilegeResult$default(this, null, null, 3, null);
        }

        public final b createNoPrivilegeResult(String str) {
            return createNoPrivilegeResult$default(this, str, null, 2, null);
        }

        public final b createParamsErrorResult() {
            return createParamsErrorResult$default(this, null, null, 3, null);
        }

        public final b createParamsErrorResult(String str) {
            return createParamsErrorResult$default(this, str, null, 2, null);
        }

        public final b createSuccessResult() {
            return createSuccessResult$default(this, null, null, 3, null);
        }

        public final b createSuccessResult(JSONObject jSONObject) {
            return createSuccessResult$default(this, jSONObject, null, 2, null);
        }

        private C0203b() {
        }

        public /* synthetic */ C0203b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ b createSuccessResult$default(C0203b c0203b, JSONObject jSONObject, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                jSONObject = null;
            }
            if ((i2 & 2) != 0) {
                str = "";
            }
            return c0203b.createSuccessResult(jSONObject, str);
        }

        public final b createSuccessResult(JSONObject jSONObject, String str) {
            o.INSTANCE.d(b.f13252d, "createSuccessResult " + String.valueOf(jSONObject) + " " + str);
            b bVar = new b(null);
            bVar.setCode(a.SUCCESS.getValue());
            if (!TextUtils.isEmpty(str)) {
                bVar.setMessage(str);
            }
            if (jSONObject != null) {
                bVar.setData(jSONObject);
            }
            return bVar;
        }

        public static /* synthetic */ b createErrorResult$default(C0203b c0203b, String str, JSONObject jSONObject, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = "";
            }
            if ((i2 & 2) != 0) {
                jSONObject = null;
            }
            return c0203b.createErrorResult(str, jSONObject);
        }

        public final b createErrorResult(String str, JSONObject jSONObject) {
            o.INSTANCE.d(b.f13252d, "createErrorResult " + String.valueOf(jSONObject) + " " + str);
            b bVar = new b(null);
            bVar.setCode(a.ERROR.getValue());
            if (!TextUtils.isEmpty(str)) {
                bVar.setMessage(str);
            }
            if (jSONObject != null) {
                bVar.setData(jSONObject);
            }
            return bVar;
        }

        public static /* synthetic */ b createMethodNotFoundResult$default(C0203b c0203b, String str, JSONObject jSONObject, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = "";
            }
            if ((i2 & 2) != 0) {
                jSONObject = null;
            }
            return c0203b.createMethodNotFoundResult(str, jSONObject);
        }

        public final b createMethodNotFoundResult(String str, JSONObject jSONObject) {
            o.INSTANCE.d(b.f13252d, "createMethodNotFoundResult " + String.valueOf(jSONObject) + " " + str);
            b bVar = new b(null);
            bVar.setCode(a.NOT_FOUND.getValue());
            if (TextUtils.isEmpty(str)) {
                bVar.setMessage("the bridge is not found, are u register?");
            } else {
                bVar.setMessage(str);
            }
            if (jSONObject != null) {
                bVar.setData(jSONObject);
            }
            return bVar;
        }

        public static /* synthetic */ b createNoPrivilegeResult$default(C0203b c0203b, String str, JSONObject jSONObject, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = "";
            }
            if ((i2 & 2) != 0) {
                jSONObject = null;
            }
            return c0203b.createNoPrivilegeResult(str, jSONObject);
        }

        public final b createNoPrivilegeResult(String str, JSONObject jSONObject) {
            o.INSTANCE.d(b.f13252d, "createNoPrivilegeResult " + String.valueOf(jSONObject) + " " + str);
            b bVar = new b(null);
            bVar.setCode(a.NO_PRIVILEGE.getValue());
            if (TextUtils.isEmpty(str)) {
                bVar.setMessage("the bridge is no privilege, please check again.");
            } else {
                bVar.setMessage(str);
            }
            if (jSONObject != null) {
                bVar.setData(jSONObject);
            }
            return bVar;
        }

        public static /* synthetic */ b createParamsErrorResult$default(C0203b c0203b, String str, JSONObject jSONObject, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = "";
            }
            if ((i2 & 2) != 0) {
                jSONObject = null;
            }
            return c0203b.createParamsErrorResult(str, jSONObject);
        }

        public final b createParamsErrorResult(String str, JSONObject jSONObject) {
            o.INSTANCE.d(b.f13252d, "createParamsErrorResult " + String.valueOf(jSONObject) + " " + str);
            b bVar = new b(null);
            bVar.setCode(a.PARAMS_ERROR.getValue());
            if (TextUtils.isEmpty(str)) {
                bVar.setMessage("the bridge's params is error, please check again.");
            } else {
                bVar.setMessage(str);
            }
            if (jSONObject != null) {
                bVar.setData(jSONObject);
            }
            return bVar;
        }
    }

    /* compiled from: BridgeResult.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/bytedance/sdk/bridge/model/BridgeResult$CODE;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", bv.f21037a, "ERROR", "NOT_FOUND", "NO_PRIVILEGE", "PARAMS_ERROR", "bridge_release"}, k = 1, mv = {1, 1, 15})
    /* loaded from: classes3.dex */
    public enum a {
        SUCCESS(1),
        ERROR(0),
        NOT_FOUND(-2),
        NO_PRIVILEGE(-1),
        PARAMS_ERROR(-3);
        

        /* renamed from: b  reason: collision with root package name */
        private final int f13257b;

        a(int i2) {
            this.f13257b = i2;
        }

        public final int getValue() {
            return this.f13257b;
        }
    }
}
