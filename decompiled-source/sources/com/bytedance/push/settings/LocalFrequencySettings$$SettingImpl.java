package com.bytedance.push.settings;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.List;
/* loaded from: classes2.dex */
public class LocalFrequencySettings$$SettingImpl implements LocalFrequencySettings {

    /* renamed from: a  reason: collision with root package name */
    private Context f11701a;

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.push.settings.storage.i f11702b;

    /* renamed from: c  reason: collision with root package name */
    private final f f11703c = new f() { // from class: com.bytedance.push.settings.LocalFrequencySettings$$SettingImpl.1
        @Override // com.bytedance.push.settings.f
        public <T> T create(Class<T> cls) {
            if (cls == o.class) {
                return (T) new o();
            }
            if (cls == i.class) {
                return (T) new i();
            }
            return null;
        }
    };

    public LocalFrequencySettings$$SettingImpl(Context context, com.bytedance.push.settings.storage.i iVar) {
        this.f11701a = context;
        this.f11702b = iVar;
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public long getLastUpdateSenderTime() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar == null || !iVar.contains("last_update_sender_time_mil")) {
            return 0L;
        }
        return this.f11702b.getLong("last_update_sender_time_mil");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setLastUpdateSenderTime(long j2) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putLong("last_update_sender_time_mil", j2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public String getLastUpdateSenderDid() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        return (iVar == null || !iVar.contains("last_update_sender_did")) ? "" : this.f11702b.getString("last_update_sender_did");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setLastUpdateSenderDid(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("last_update_sender_did", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public String getLastUpdateSenderVersionCode() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        return (iVar == null || !iVar.contains("last_update_sender_vc")) ? "" : this.f11702b.getString("last_update_sender_vc");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setLastUpdateSenderVersionCode(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("last_update_sender_vc", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public String getLastUpdateSenderUpdateVersionCode() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        return (iVar == null || !iVar.contains("last_update_sender_gray_vc")) ? "" : this.f11702b.getString("last_update_sender_gray_vc");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setLastUpdateSenderUpdateVersionCode(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("last_update_sender_gray_vc", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public String getLastUpdateSenderChannel() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        return (iVar == null || !iVar.contains("last_update_sender_channel")) ? "" : this.f11702b.getString("last_update_sender_channel");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setLastUpdateSenderUpdateChannel(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("last_update_sender_channel", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public String getSupportSender() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        return (iVar == null || !iVar.contains("last_update_sender_supported")) ? "" : this.f11702b.getString("last_update_sender_supported");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setSupportSender(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("last_update_sender_supported", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public boolean isLastSendNotifyEnableSucc() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar == null || !iVar.contains("last_send_switcher_stat")) {
            return false;
        }
        return this.f11702b.getBoolean("last_send_switcher_stat");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setLastSendNotifyEnableSucc(boolean z) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean("last_send_switcher_stat", z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public int getSystemPushEnable() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar == null || !iVar.contains("sys_switcher_stat")) {
            return -2;
        }
        return this.f11702b.getInt("sys_switcher_stat");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setSystemPushEnable(int i2) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putInt("sys_switcher_stat", i2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public int a() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar == null || !iVar.contains("inner_switcher_stat")) {
            return -1;
        }
        return this.f11702b.getInt("inner_switcher_stat");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void a(int i2) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putInt("inner_switcher_stat", i2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public String getLastNotificationChannelStatus() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        return (iVar == null || !iVar.contains("notify_channel_stat")) ? "" : this.f11702b.getString("notify_channel_stat");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setLastNotificationChannelStatus(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("notify_channel_stat", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public List<com.bytedance.push.model.d> getTokenCache() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null && iVar.contains("token_cache")) {
            return ((o) e.obtain(o.class, this.f11703c)).to(this.f11702b.getString("token_cache"));
        }
        return ((o) e.obtain(o.class, this.f11703c)).create();
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setTokenCache(List<com.bytedance.push.model.d> list) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("token_cache", ((o) e.obtain(o.class, this.f11703c)).from(list));
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public long getUploadSwitchTs() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar == null || !iVar.contains("last_upload_switch_ts")) {
            return 0L;
        }
        return this.f11702b.getLong("last_upload_switch_ts");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setUploadSwitchTs(long j2) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putLong("last_upload_switch_ts", j2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public List<com.bytedance.push.model.a> getRevokeRidList() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null && iVar.contains("revoke_rid_list")) {
            return ((i) e.obtain(i.class, this.f11703c)).to(this.f11702b.getString("revoke_rid_list"));
        }
        return ((i) e.obtain(i.class, this.f11703c)).create();
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setRevokeRidList(List<com.bytedance.push.model.a> list) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("revoke_rid_list", ((i) e.obtain(i.class, this.f11703c)).from(list));
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public long getLastRequestSettingsTime() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar == null || !iVar.contains("last_request_settings_time_mil")) {
            return 0L;
        }
        return this.f11702b.getLong("last_request_settings_time_mil");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setLastRequestSettingsTime(long j2) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putLong("last_request_settings_time_mil", j2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public String getLastUpdateSenderAlias() {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        return (iVar == null || !iVar.contains("last_update_sender_alias")) ? "" : this.f11702b.getString("last_update_sender_alias");
    }

    @Override // com.bytedance.push.settings.LocalFrequencySettings
    public void setLastUpdateSenderAlias(String str) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("last_update_sender_alias", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.ILocalSettings
    public void registerValChanged(Context context, String str, String str2, b bVar) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            iVar.registerValChanged(context, str, str2, bVar);
        }
    }

    @Override // com.bytedance.push.settings.ILocalSettings
    public void unregisterValChanged(b bVar) {
        com.bytedance.push.settings.storage.i iVar = this.f11702b;
        if (iVar != null) {
            iVar.unregisterValChanged(bVar);
        }
    }
}
