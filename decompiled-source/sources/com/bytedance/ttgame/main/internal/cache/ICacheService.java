package com.bytedance.ttgame.main.internal.cache;

import android.content.Context;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: ICacheService.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\bf\u0018\u0000 ;2\u00020\u0001:\u0001;J$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001c\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u000b\u001a\u00020\u0005H&J'\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\tH&¢\u0006\u0002\u0010\u0012J\"\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0014H&J'\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0016H&¢\u0006\u0002\u0010\u0017J'\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0019H&¢\u0006\u0002\u0010\u001aJ'\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u001cH&¢\u0006\u0002\u0010\u001dJ$\u0010\u001e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H&J.\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010 2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050 H&J\u0012\u0010\"\u001a\u00020\u00032\b\u0010#\u001a\u0004\u0018\u00010$H&J\u0018\u0010%\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\tH&J\u0018\u0010'\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010&\u001a\u00020(H&J\u0018\u0010)\u001a\u00020\u00192\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u0019H&J\u0010\u0010*\u001a\u00020+2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010,\u001a\u00020-2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010.\u001a\u00020\u001c2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u001cH&J\u0018\u0010/\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u0005H&J \u00100\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00101\u001a\u00020\tH&J \u00102\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00101\u001a\u00020\u0014H&J \u00103\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00101\u001a\u00020\u0016H&J \u00104\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00101\u001a\u00020\u0019H&J \u00105\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00101\u001a\u00020\u001cH&J\"\u00106\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\b\u00101\u001a\u0004\u0018\u00010\u0005H&J*\u00107\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0010\u00108\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010 H&J\u0018\u00109\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010:\u001a\u00020\u0003H&¨\u0006<"}, d2 = {"Lcom/bytedance/ttgame/main/internal/cache/ICacheService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "addRemoteUpdateListener", "", "key", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/bytedance/ttgame/main/internal/cache/IRemoteConfigListener;", "callbackOnMain", "", "clear", "repo", "contains", "getAll", "", "", "getBoolean", "defValue", "(Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/Boolean;", "getBytes", "", "getFloat", "", "(Ljava/lang/String;Ljava/lang/String;F)Ljava/lang/Float;", "getInt", "", "(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/Integer;", "getLong", "", "(Ljava/lang/String;Ljava/lang/String;J)Ljava/lang/Long;", "getString", "getStringSet", "", "defValues", "init", "context", "Landroid/content/Context;", "optBoolean", "fallback", "optDouble", "", "optInt", "optJsonArray", "Lorg/json/JSONArray;", "optJsonObject", "Lorg/json/JSONObject;", "optLong", "optString", "putBoolean", "value", "putBytes", "putFloat", "putInt", "putLong", "putString", "putStringSet", "values", "remove", "update", "Companion", "core_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ICacheService extends IModuleApi {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String DEFAULT_REPO_NAME = "gsdk_cache_repo";
    public static final String REMOTE_CONFIG = "_gsdk_remote_config_repo";

    void addRemoteUpdateListener(String str, IRemoteConfigListener iRemoteConfigListener, boolean z);

    void clear(String str);

    boolean contains(String str);

    boolean contains(String str, String str2);

    Map<String, Object> getAll(String str);

    Boolean getBoolean(String str, String str2, boolean z);

    byte[] getBytes(String str, String str2, byte[] bArr);

    Float getFloat(String str, String str2, float f2);

    Integer getInt(String str, String str2, int i2);

    Long getLong(String str, String str2, long j2);

    String getString(String str, String str2, String str3);

    Set<String> getStringSet(String str, String str2, Set<String> set);

    void init(Context context);

    boolean optBoolean(String str, boolean z);

    double optDouble(String str, double d2);

    int optInt(String str, int i2);

    JSONArray optJsonArray(String str);

    JSONObject optJsonObject(String str);

    long optLong(String str, long j2);

    String optString(String str, String str2);

    void putBoolean(String str, String str2, boolean z);

    void putBytes(String str, String str2, byte[] bArr);

    void putFloat(String str, String str2, float f2);

    void putInt(String str, String str2, int i2);

    void putLong(String str, String str2, long j2);

    void putString(String str, String str2, String str3);

    void putStringSet(String str, String str2, Set<String> set);

    void remove(String str, String str2);

    void update();

    /* compiled from: ICacheService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/bytedance/ttgame/main/internal/cache/ICacheService$Companion;", "", "()V", "DEFAULT_REPO_NAME", "", "REMOTE_CONFIG", "core_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String DEFAULT_REPO_NAME = "gsdk_cache_repo";
        public static final String REMOTE_CONFIG = "_gsdk_remote_config_repo";

        private Companion() {
        }
    }

    /* compiled from: ICacheService.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void addRemoteUpdateListener$default(ICacheService iCacheService, String str, IRemoteConfigListener iRemoteConfigListener, boolean z, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addRemoteUpdateListener");
            }
            if ((i2 & 4) != 0) {
                z = true;
            }
            iCacheService.addRemoteUpdateListener(str, iRemoteConfigListener, z);
        }
    }
}
