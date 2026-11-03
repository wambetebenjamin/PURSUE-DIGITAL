package com.bytedance.ttgame.core.cache;

import com.bytedance.bdturing.localstorage.b;
import com.google.gson.annotations.SerializedName;
import java.util.Map;
import kotlin.Metadata;
import kotlin.text.StringsKt;
/* compiled from: SettingsResponse.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/bytedance/ttgame/core/cache/SettingsResponse;", "", "()V", "data", "Lcom/bytedance/ttgame/core/cache/SettingsResponse$Data;", "getData", "()Lcom/bytedance/ttgame/core/cache/SettingsResponse$Data;", "setData", "(Lcom/bytedance/ttgame/core/cache/SettingsResponse$Data;)V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "isSuccess", "", "Data", "core_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SettingsResponse {
    @SerializedName("data")
    private Data data;
    @SerializedName("message")
    private String message;

    public final String getMessage() {
        return this.message;
    }

    public final void setMessage(String str) {
        this.message = str;
    }

    public final Data getData() {
        return this.data;
    }

    public final void setData(Data data) {
        this.data = data;
    }

    /* compiled from: SettingsResponse.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R,\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR,\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lcom/bytedance/ttgame/core/cache/SettingsResponse$Data;", "", "()V", b.KEY_SETTINGS, "", "", "getSettings", "()Ljava/util/Map;", "setSettings", "(Ljava/util/Map;)V", "vidInfos", "getVidInfos", "setVidInfos", "core_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Data {
        @SerializedName(b.KEY_SETTINGS)
        private Map<String, ? extends Object> settings;
        @SerializedName("vid_info")
        private Map<String, ? extends Object> vidInfos;

        public final Map<String, Object> getSettings() {
            return this.settings;
        }

        public final void setSettings(Map<String, ? extends Object> map) {
            this.settings = map;
        }

        public final Map<String, Object> getVidInfos() {
            return this.vidInfos;
        }

        public final void setVidInfos(Map<String, ? extends Object> map) {
            this.vidInfos = map;
        }
    }

    public final boolean isSuccess() {
        return StringsKt.equals("success", this.message, true);
    }
}
