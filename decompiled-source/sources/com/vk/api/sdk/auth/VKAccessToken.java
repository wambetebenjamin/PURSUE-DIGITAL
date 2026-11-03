package com.vk.api.sdk.auth;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.bytedance.sdk.account.platform.base.i;
import gsdk.impl.account.toutiao.f;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
/* compiled from: VKAccessToken.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 '2\u00020\u0001:\u0001'B!\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007B\u001b\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t¢\u0006\u0002\u0010\nJ\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#J\u000e\u0010 \u001a\u00020!2\u0006\u0010$\u001a\u00020%J\u0016\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\tH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u000e\u0010\u0013\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\fR\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\fR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001e¨\u0006("}, d2 = {"Lcom/vk/api/sdk/auth/VKAccessToken;", "", f.v, "", i.CT_KEY_ACCESS_TOKEN, "", "secret", "(ILjava/lang/String;Ljava/lang/String;)V", "params", "", "(Ljava/util/Map;)V", "getAccessToken", "()Ljava/lang/String;", "created", "", "getCreated", "()J", "email", "getEmail", "expirationDate", "httpsRequired", "", "isValid", "()Z", "phone", "getPhone", "phoneAccessKey", "getPhoneAccessKey", "getSecret", "getUserId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "save", "", "prefs", "Landroid/content/SharedPreferences;", "bundle", "Landroid/os/Bundle;", "toMap", "Companion", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class VKAccessToken {
    private static final String ACCESS_TOKEN = "access_token";
    private static final String CREATED = "created";
    public static final Companion Companion = new Companion(null);
    private static final String EMAIL = "email";
    private static final String EXPIRES_IN = "expires_in";
    private static final String HTTPS_REQUIRED = "https_required";
    private static final String PHONE = "phone";
    private static final String PHONE_ACCESS_KEY = "phone_access_key";
    private static final String SECRET = "secret";
    private static final String USER_ID = "user_id";
    private static final String VK_ACCESS_TOKEN_KEY = "vk_access_token";
    private final String accessToken;
    private final long created;
    private final String email;
    private final long expirationDate;
    private final boolean httpsRequired;
    private final String phone;
    private final String phoneAccessKey;
    private final String secret;
    private final Integer userId;

    public VKAccessToken(Map<String, String> params) {
        long currentTimeMillis;
        long j2;
        Intrinsics.checkParameterIsNotNull(params, "params");
        String str = params.get("user_id");
        this.userId = str != null ? StringsKt.toIntOrNull(str) : null;
        String str2 = params.get("access_token");
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        this.accessToken = str2;
        this.secret = params.get("secret");
        this.httpsRequired = Intrinsics.areEqual("1", params.get(HTTPS_REQUIRED));
        if (params.containsKey("created")) {
            String str3 = params.get("created");
            if (str3 == null) {
                Intrinsics.throwNpe();
            }
            currentTimeMillis = Long.parseLong(str3);
        } else {
            currentTimeMillis = System.currentTimeMillis();
        }
        this.created = currentTimeMillis;
        if (params.containsKey("expires_in")) {
            String str4 = params.get("expires_in");
            if (str4 == null) {
                Intrinsics.throwNpe();
            }
            j2 = Long.parseLong(str4);
        } else {
            j2 = -1;
        }
        this.expirationDate = j2;
        this.email = params.containsKey("email") ? params.get("email") : null;
        this.phone = params.containsKey("phone") ? params.get("phone") : null;
        this.phoneAccessKey = params.containsKey("phone_access_key") ? params.get("phone_access_key") : null;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VKAccessToken(int i2, String accessToken, String str) {
        this(MapsKt.mapOf(new Pair("user_id", String.valueOf(i2)), new Pair("access_token", accessToken), new Pair("secret", str), new Pair(HTTPS_REQUIRED, "1")));
        Intrinsics.checkParameterIsNotNull(accessToken, "accessToken");
    }

    public final Integer getUserId() {
        return this.userId;
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final String getSecret() {
        return this.secret;
    }

    public final long getCreated() {
        return this.created;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final String getPhoneAccessKey() {
        return this.phoneAccessKey;
    }

    public final boolean isValid() {
        long j2 = this.expirationDate;
        return j2 <= 0 || this.created + (j2 * ((long) 1000)) > System.currentTimeMillis();
    }

    public final void save(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Bundle bundle2 = new Bundle();
        for (Map.Entry<String, String> entry : toMap().entrySet()) {
            bundle2.putString(entry.getKey(), entry.getValue());
        }
        bundle.putBundle(VK_ACCESS_TOKEN_KEY, bundle2);
    }

    public final void save(SharedPreferences prefs) {
        Intrinsics.checkParameterIsNotNull(prefs, "prefs");
        Map<String, String> map = toMap();
        SharedPreferences.Editor edit = prefs.edit();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            edit.putString(entry.getKey(), entry.getValue());
        }
        edit.apply();
    }

    private final Map<String, String> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("access_token", this.accessToken);
        hashMap.put("secret", this.secret);
        hashMap.put(HTTPS_REQUIRED, this.httpsRequired ? "1" : "0");
        hashMap.put("created", String.valueOf(this.created));
        hashMap.put("expires_in", String.valueOf(this.expirationDate));
        Integer num = this.userId;
        hashMap.put("user_id", num != null ? String.valueOf(num.intValue()) : null);
        hashMap.put("email", this.email);
        hashMap.put("phone", this.phone);
        hashMap.put("phone_access_key", this.phoneAccessKey);
        return hashMap;
    }

    /* compiled from: VKAccessToken.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/vk/api/sdk/auth/VKAccessToken$Companion;", "", "()V", "ACCESS_TOKEN", "", DebugCoroutineInfoImplKt.CREATED, "EMAIL", "EXPIRES_IN", "HTTPS_REQUIRED", "PHONE", "PHONE_ACCESS_KEY", "SECRET", "USER_ID", "VK_ACCESS_TOKEN_KEY", "restore", "Lcom/vk/api/sdk/auth/VKAccessToken;", "preferences", "Landroid/content/SharedPreferences;", "bundle", "Landroid/os/Bundle;", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final VKAccessToken restore(Bundle bundle) {
            Bundle bundle2;
            if (bundle == null || (bundle2 = bundle.getBundle(VKAccessToken.VK_ACCESS_TOKEN_KEY)) == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
            for (String key : bundle2.keySet()) {
                Intrinsics.checkExpressionValueIsNotNull(key, "key");
                String string = bundle2.getString(key);
                Intrinsics.checkExpressionValueIsNotNull(string, "vkTokenBundle.getString(key)");
                hashMap.put(key, string);
            }
            return new VKAccessToken(hashMap);
        }

        public final VKAccessToken restore(SharedPreferences sharedPreferences) {
            if (sharedPreferences == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
            for (String key : sharedPreferences.getAll().keySet()) {
                Intrinsics.checkExpressionValueIsNotNull(key, "key");
                String string = sharedPreferences.getString(key, "");
                Intrinsics.checkExpressionValueIsNotNull(string, "preferences.getString(key, \"\")");
                hashMap.put(key, string);
            }
            if (hashMap.containsKey("access_token") && hashMap.containsKey("user_id")) {
                return new VKAccessToken(hashMap);
            }
            return null;
        }
    }
}
