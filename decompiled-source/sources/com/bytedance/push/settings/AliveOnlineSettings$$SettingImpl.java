package com.bytedance.push.settings;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class AliveOnlineSettings$$SettingImpl implements AliveOnlineSettings {

    /* renamed from: c  reason: collision with root package name */
    private com.bytedance.push.settings.storage.i f11698c;

    /* renamed from: a  reason: collision with root package name */
    private final ConcurrentHashMap<String, Object> f11696a = new ConcurrentHashMap<>();

    /* renamed from: b  reason: collision with root package name */
    private final ConcurrentHashMap<String, Object> f11697b = new ConcurrentHashMap<>();

    /* renamed from: d  reason: collision with root package name */
    private final f f11699d = new f() { // from class: com.bytedance.push.settings.AliveOnlineSettings$$SettingImpl.1
        @Override // com.bytedance.push.settings.f
        public <T> T create(Class<T> cls) {
            if (cls == h.class) {
                return (T) new h();
            }
            return null;
        }
    };

    public AliveOnlineSettings$$SettingImpl(com.bytedance.push.settings.storage.i iVar) {
        this.f11698c = iVar;
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean allowOffALive() {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar == null || !iVar.contains("allow_off_alive")) {
            return false;
        }
        return this.f11698c.getBoolean("allow_off_alive");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public String getUninstallQuestionUrl() {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        return (iVar == null || !iVar.contains(com.ss.android.pushmanager.setting.b.UNINSTALL_QUESTION_URL)) ? "" : this.f11698c.getString(com.ss.android.pushmanager.setting.b.UNINSTALL_QUESTION_URL);
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean isAllowPushJobService() {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar == null || !iVar.contains(com.ss.android.pushmanager.setting.b.ALLOW_PUSH_JOB_SERVICE)) {
            return true;
        }
        return this.f11698c.getBoolean(com.ss.android.pushmanager.setting.b.ALLOW_PUSH_JOB_SERVICE);
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean isAllowPushDaemonMonitor() {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar == null || !iVar.contains("allow_push_daemon_monitor")) {
            return false;
        }
        return this.f11698c.getBoolean("allow_push_daemon_monitor");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean isAllowCloseBootReceiver() {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar == null || !iVar.contains("allow_close_boot_receiver")) {
            return true;
        }
        return this.f11698c.getBoolean("allow_close_boot_receiver");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean isCloseAlarmWakeup() {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar == null || !iVar.contains("is_close_alarm_wakeup")) {
            return false;
        }
        return this.f11698c.getBoolean("is_close_alarm_wakeup");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean isUseStartForegroundNotification() {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar == null || !iVar.contains("is_use_start_foreground_notification")) {
            return true;
        }
        return this.f11698c.getBoolean("is_use_start_foreground_notification");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public int getJobScheduleWakeUpIntervalSecond() {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar == null || !iVar.contains("job_schedule_wake_up_interval_second")) {
            return 3600;
        }
        return this.f11698c.getInt("job_schedule_wake_up_interval_second");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean isUseCNativeProcessKeepAlive() {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar == null || !iVar.contains("is_use_c_native_process_keep_alive")) {
            return true;
        }
        return this.f11698c.getBoolean("is_use_c_native_process_keep_alive");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean isNotifyServiceStick() {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar == null || !iVar.contains("is_notify_service_stick")) {
            return false;
        }
        return this.f11698c.getBoolean("is_notify_service_stick");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public boolean isMiuiCloseDaemon() {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar == null || !iVar.contains("key_is_miui_close_daemon")) {
            return true;
        }
        return this.f11698c.getBoolean("key_is_miui_close_daemon");
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public List<String> getAllowList() {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null && iVar.contains("ttpush_alv_white_list")) {
            return ((h) e.obtain(h.class, this.f11699d)).to(this.f11698c.getString("ttpush_alv_white_list"));
        }
        return ((h) e.obtain(h.class, this.f11699d)).create();
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void setAllowOffAlive(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean("allow_off_alive", z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void setUninstallQuestionUrl(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString(com.ss.android.pushmanager.setting.b.UNINSTALL_QUESTION_URL, str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void setAllowPushJobService(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean(com.ss.android.pushmanager.setting.b.ALLOW_PUSH_JOB_SERVICE, z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void setAllowPushDaemonMonitor(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean("allow_push_daemon_monitor", z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void setAllowCloseBootReceiver(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean("allow_close_boot_receiver", z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void setCloseAlarmWakeup(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean("is_close_alarm_wakeup", z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void setUseStartForegroundNotification(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean("is_use_start_foreground_notification", z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void setJobScheduleWakeUpIntervalSecond(int i2) {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putInt("job_schedule_wake_up_interval_second", i2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void setIsUseCNativeProcessKeepAlive(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean("is_use_c_native_process_keep_alive", z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void setNotifyServiceStick(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean("is_notify_service_stick", z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.AliveOnlineSettings
    public void setMiuiCloseDaemon(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean("key_is_miui_close_daemon", z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.ISettings
    public void updateSettings(Context context, JSONObject jSONObject) {
        com.bytedance.push.settings.storage.i iVar;
        if (jSONObject == null || (iVar = this.f11698c) == null) {
            return;
        }
        SharedPreferences.Editor edit = iVar.edit();
        if (jSONObject != null) {
            if (jSONObject.has(com.ss.android.push.a.SETTING_KEY_SS_WO)) {
                edit.putBoolean("allow_off_alive", g.optBoolean(jSONObject, com.ss.android.push.a.SETTING_KEY_SS_WO));
            }
            if (jSONObject.has("ttpush_uninstall_question_url")) {
                edit.putString(com.ss.android.pushmanager.setting.b.UNINSTALL_QUESTION_URL, jSONObject.optString("ttpush_uninstall_question_url"));
            }
            if (jSONObject.has(AliveOnlineSettings.SERVER_KEY_JOB_SERVICE)) {
                edit.putBoolean(com.ss.android.pushmanager.setting.b.ALLOW_PUSH_JOB_SERVICE, g.optBoolean(jSONObject, AliveOnlineSettings.SERVER_KEY_JOB_SERVICE));
            }
            if (jSONObject.has("ttpush_i18n_allow_push_daemon_monitor")) {
                edit.putBoolean("allow_push_daemon_monitor", g.optBoolean(jSONObject, "ttpush_i18n_allow_push_daemon_monitor"));
            }
            if (jSONObject.has("ttpush_allow_close_boot_receiver")) {
                edit.putBoolean("allow_close_boot_receiver", g.optBoolean(jSONObject, "ttpush_allow_close_boot_receiver"));
            }
            if (jSONObject.has(AliveOnlineSettings.SERVER_KEY_ALARM_WAKE_UP)) {
                edit.putBoolean("is_close_alarm_wakeup", g.optBoolean(jSONObject, AliveOnlineSettings.SERVER_KEY_ALARM_WAKE_UP));
            }
            if (jSONObject.has("ttpush_use_start_foreground_notification")) {
                edit.putBoolean("is_use_start_foreground_notification", g.optBoolean(jSONObject, "ttpush_use_start_foreground_notification"));
            }
            if (jSONObject.has("ttpush_job_schedule_wake_up_interval_second")) {
                edit.putInt("job_schedule_wake_up_interval_second", jSONObject.optInt("ttpush_job_schedule_wake_up_interval_second"));
            }
            if (jSONObject.has("ttpush_is_use_c_native_process_keep_alive")) {
                edit.putBoolean("is_use_c_native_process_keep_alive", g.optBoolean(jSONObject, "ttpush_is_use_c_native_process_keep_alive"));
            }
            if (jSONObject.has(AliveOnlineSettings.SERVER_KEY_NOTIFY_STICKY)) {
                edit.putBoolean("is_notify_service_stick", g.optBoolean(jSONObject, AliveOnlineSettings.SERVER_KEY_NOTIFY_STICKY));
            }
            if (jSONObject.has("ttpush_key_is_miui_close_daemon")) {
                edit.putBoolean("key_is_miui_close_daemon", g.optBoolean(jSONObject, "ttpush_key_is_miui_close_daemon"));
            }
            if (jSONObject.has("ttpush_alv_white_list")) {
                edit.putString("ttpush_alv_white_list", jSONObject.optString("ttpush_alv_white_list"));
            }
        }
        edit.apply();
    }

    @Override // com.bytedance.push.settings.ISettings
    public void registerValChanged(Context context, String str, String str2, b bVar) {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null) {
            iVar.registerValChanged(context, str, str2, bVar);
        }
    }

    @Override // com.bytedance.push.settings.ISettings
    public void unregisterValChanged(b bVar) {
        com.bytedance.push.settings.storage.i iVar = this.f11698c;
        if (iVar != null) {
            iVar.unregisterValChanged(bVar);
        }
    }
}
