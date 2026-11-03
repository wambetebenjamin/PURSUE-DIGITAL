package com.bytedance.push.settings;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
/* loaded from: classes2.dex */
public class LocalSettings$$SettingImpl implements LocalSettings {

    /* renamed from: a  reason: collision with root package name */
    private Context f11707a;

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.push.settings.storage.i f11708b;

    /* renamed from: c  reason: collision with root package name */
    private final f f11709c = new f() { // from class: com.bytedance.push.settings.LocalSettings$$SettingImpl.1
        @Override // com.bytedance.push.settings.f
        public <T> T create(Class<T> cls) {
            if (cls == com.bytedance.push.settings.common.a.class) {
                return (T) new com.bytedance.push.settings.common.a();
            }
            return null;
        }
    };

    public LocalSettings$$SettingImpl(Context context, com.bytedance.push.settings.storage.i iVar) {
        this.f11707a = context;
        this.f11708b = iVar;
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public boolean isAllowNetwork() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar == null || !iVar.contains(com.ss.android.pushmanager.setting.b.ALLOW_NETWORK)) {
            return true;
        }
        return this.f11708b.getBoolean(com.ss.android.pushmanager.setting.b.ALLOW_NETWORK);
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void setAllowNetwork(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean(com.ss.android.pushmanager.setting.b.ALLOW_NETWORK, z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public String getPushDaemonMonitor() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        return (iVar == null || !iVar.contains("push_daemon_monitor")) ? "" : this.f11708b.getString("push_daemon_monitor");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void setPushDaemonMonitor(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("push_daemon_monitor", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public String getPushDaemonMonitorResult() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        return (iVar == null || !iVar.contains("push_daemon_monitor_result")) ? "" : this.f11708b.getString("push_daemon_monitor_result");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void setPushDaemonMonitorResult(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("push_daemon_monitor_result", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public String getPushChannelsJsonArray() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        return (iVar == null || !iVar.contains("push_channels_json_array")) ? "" : this.f11708b.getString("push_channels_json_array");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void setPushChannelsJsonArray(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("push_channels_json_array", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public int getAliPushType() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar == null || !iVar.contains(com.ss.android.pushmanager.setting.b.ALI_PUSH_TYPE)) {
            return -1;
        }
        return this.f11708b.getInt(com.ss.android.pushmanager.setting.b.ALI_PUSH_TYPE);
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void setAliPushType(int i2) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putInt(com.ss.android.pushmanager.setting.b.ALI_PUSH_TYPE, i2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public boolean isPushNotifyEnable() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar == null || !iVar.contains(com.ss.android.pushmanager.setting.b.PUSH_NOTIFY_ENABLE)) {
            return true;
        }
        return this.f11708b.getBoolean(com.ss.android.pushmanager.setting.b.PUSH_NOTIFY_ENABLE);
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void setPushNotifyEnable(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean(com.ss.android.pushmanager.setting.b.PUSH_NOTIFY_ENABLE, z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public long b() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar == null || !iVar.contains("last_pull_red_badge_time")) {
            return 0L;
        }
        return this.f11708b.getLong("last_pull_red_badge_time");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void a(long j2) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putLong("last_pull_red_badge_time", j2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public long c() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        return (iVar == null || !iVar.contains("pull_red_badge_request_interval_in_second")) ? LocalSettings.DEFAULT_PULL_REQUEST_INTERVAL_IN_SECOND : this.f11708b.getLong("pull_red_badge_request_interval_in_second");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void b(long j2) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putLong("pull_red_badge_request_interval_in_second", j2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public long d() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar == null || !iVar.contains("last_pull_local_push_time")) {
            return 0L;
        }
        return this.f11708b.getLong("last_pull_local_push_time");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void c(long j2) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putLong("last_pull_local_push_time", j2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public long e() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        return (iVar == null || !iVar.contains("pull_local_push_request_interval_in_second")) ? LocalSettings.DEFAULT_PULL_REQUEST_INTERVAL_IN_SECOND : this.f11708b.getLong("pull_local_push_request_interval_in_second");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void d(long j2) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putLong("pull_local_push_request_interval_in_second", j2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public String getRedBadgeBody() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        return (iVar == null || !iVar.contains("red_badge_body_from_pull")) ? "" : this.f11708b.getString("red_badge_body_from_pull");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void setRedBadgeBody(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("red_badge_body_from_pull", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public String getRedBadgeTimeParams() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        return (iVar == null || !iVar.contains("red_badge_time_params")) ? "" : this.f11708b.getString("red_badge_time_params");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void setRedBadgeTimeParams(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("red_badge_time_params", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public String getAbVersion() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        return (iVar == null || !iVar.contains("ab_version")) ? "" : this.f11708b.getString("ab_version");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void setAbVersion(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("ab_version", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public int getSceneIdV2() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar == null || !iVar.contains("scene_id_v2")) {
            return 0;
        }
        return this.f11708b.getInt("scene_id_v2");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void setSceneIdV2(int i2) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putInt("scene_id_v2", i2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public String a() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        return (iVar == null || !iVar.contains("push_statistics_process_set")) ? "[]" : this.f11708b.getString("push_statistics_process_set");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void a(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("push_statistics_process_set", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public boolean f() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar == null || !iVar.contains("has_popped_notification_permission_popup")) {
            return false;
        }
        return this.f11708b.getBoolean("has_popped_notification_permission_popup");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void a(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean("has_popped_notification_permission_popup", z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public boolean g() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar == null || !iVar.contains("need_pop_notification_permission_popup_after_foreground")) {
            return false;
        }
        return this.f11708b.getBoolean("need_pop_notification_permission_popup_after_foreground");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void b(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean("need_pop_notification_permission_popup_after_foreground", z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public Map<String, com.bytedance.push.settings.notification.a> h() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null && iVar.contains("need_to_create_channels_after_allow_popup")) {
            return ((com.bytedance.push.settings.common.a) e.obtain(com.bytedance.push.settings.common.a.class, this.f11709c)).to(this.f11708b.getString("need_to_create_channels_after_allow_popup"));
        }
        return ((com.bytedance.push.settings.common.a) e.obtain(com.bytedance.push.settings.common.a.class, this.f11709c)).create();
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void a(Map<String, com.bytedance.push.settings.notification.a> map) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("need_to_create_channels_after_allow_popup", ((com.bytedance.push.settings.common.a) e.obtain(com.bytedance.push.settings.common.a.class, this.f11709c)).from(map));
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public String i() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        return (iVar == null || !iVar.contains("android_id")) ? "" : this.f11708b.getString("android_id");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void b(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("android_id", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public boolean j() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar == null || !iVar.contains("has_not_shown_red_badge")) {
            return false;
        }
        return this.f11708b.getBoolean("has_not_shown_red_badge");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void c(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean("has_not_shown_red_badge", z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public long k() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar == null || !iVar.contains("last_not_shown_red_badge_time_stamp")) {
            return 0L;
        }
        return this.f11708b.getLong("last_not_shown_red_badge_time_stamp");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void e(long j2) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putLong("last_not_shown_red_badge_time_stamp", j2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public int l() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar == null || !iVar.contains("monitor_notification_bar_support_level")) {
            return 0;
        }
        return this.f11708b.getInt("monitor_notification_bar_support_level");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void a(int i2) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putInt("monitor_notification_bar_support_level", i2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public int m() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar == null || !iVar.contains("monitor_user_present_support_level")) {
            return 0;
        }
        return this.f11708b.getInt("monitor_user_present_support_level");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void b(int i2) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putInt("monitor_user_present_support_level", i2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public long n() {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar == null || !iVar.contains("last_message_show_time_stamp")) {
            return 0L;
        }
        return this.f11708b.getLong("last_message_show_time_stamp");
    }

    @Override // com.bytedance.push.settings.LocalSettings
    public void f(long j2) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putLong("last_message_show_time_stamp", j2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.ILocalSettings
    public void registerValChanged(Context context, String str, String str2, b bVar) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            iVar.registerValChanged(context, str, str2, bVar);
        }
    }

    @Override // com.bytedance.push.settings.ILocalSettings
    public void unregisterValChanged(b bVar) {
        com.bytedance.push.settings.storage.i iVar = this.f11708b;
        if (iVar != null) {
            iVar.unregisterValChanged(bVar);
        }
    }
}
