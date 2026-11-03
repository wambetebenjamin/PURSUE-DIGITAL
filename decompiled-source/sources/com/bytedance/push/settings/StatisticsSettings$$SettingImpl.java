package com.bytedance.push.settings;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class StatisticsSettings$$SettingImpl implements StatisticsSettings {

    /* renamed from: c  reason: collision with root package name */
    private com.bytedance.push.settings.storage.i f11718c;

    /* renamed from: a  reason: collision with root package name */
    private final ConcurrentHashMap<String, Object> f11716a = new ConcurrentHashMap<>();

    /* renamed from: b  reason: collision with root package name */
    private final ConcurrentHashMap<String, Object> f11717b = new ConcurrentHashMap<>();

    /* renamed from: d  reason: collision with root package name */
    private final f f11719d = new f() { // from class: com.bytedance.push.settings.StatisticsSettings$$SettingImpl.1
        @Override // com.bytedance.push.settings.f
        public <T> T create(Class<T> cls) {
            return null;
        }
    };

    public StatisticsSettings$$SettingImpl(com.bytedance.push.settings.storage.i iVar) {
        this.f11718c = iVar;
    }

    @Override // com.bytedance.push.settings.StatisticsSettings
    public long statsForegroundInterval() {
        com.bytedance.push.settings.storage.i iVar = this.f11718c;
        if (iVar == null || !iVar.contains(StatisticsSettings.KEY_STATS_FOREGROUND)) {
            return 300000L;
        }
        return this.f11718c.getLong(StatisticsSettings.KEY_STATS_FOREGROUND);
    }

    @Override // com.bytedance.push.settings.StatisticsSettings
    public long statsBackgroundInterval() {
        com.bytedance.push.settings.storage.i iVar = this.f11718c;
        if (iVar == null || !iVar.contains(StatisticsSettings.KEY_STATS_BACKGROUND)) {
            return 300000L;
        }
        return this.f11718c.getLong(StatisticsSettings.KEY_STATS_BACKGROUND);
    }

    @Override // com.bytedance.push.settings.ISettings
    public void updateSettings(Context context, JSONObject jSONObject) {
        com.bytedance.push.settings.storage.i iVar;
        if (jSONObject == null || (iVar = this.f11718c) == null) {
            return;
        }
        SharedPreferences.Editor edit = iVar.edit();
        if (jSONObject != null) {
            if (jSONObject.has(StatisticsSettings.KEY_STATS_FOREGROUND)) {
                edit.putLong(StatisticsSettings.KEY_STATS_FOREGROUND, jSONObject.optLong(StatisticsSettings.KEY_STATS_FOREGROUND));
            }
            if (jSONObject.has(StatisticsSettings.KEY_STATS_BACKGROUND)) {
                edit.putLong(StatisticsSettings.KEY_STATS_BACKGROUND, jSONObject.optLong(StatisticsSettings.KEY_STATS_BACKGROUND));
            }
        }
        edit.apply();
    }

    @Override // com.bytedance.push.settings.ISettings
    public void registerValChanged(Context context, String str, String str2, b bVar) {
        com.bytedance.push.settings.storage.i iVar = this.f11718c;
        if (iVar != null) {
            iVar.registerValChanged(context, str, str2, bVar);
        }
    }

    @Override // com.bytedance.push.settings.ISettings
    public void unregisterValChanged(b bVar) {
        com.bytedance.push.settings.storage.i iVar = this.f11718c;
        if (iVar != null) {
            iVar.unregisterValChanged(bVar);
        }
    }
}
