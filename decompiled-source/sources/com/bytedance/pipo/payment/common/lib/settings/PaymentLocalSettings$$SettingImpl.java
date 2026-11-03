package com.bytedance.pipo.payment.common.lib.settings;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.push.settings.b;
import com.bytedance.push.settings.f;
import com.bytedance.push.settings.storage.i;
/* loaded from: classes2.dex */
public class PaymentLocalSettings$$SettingImpl implements PaymentLocalSettings {

    /* renamed from: a  reason: collision with root package name */
    private Context f10932a;

    /* renamed from: b  reason: collision with root package name */
    private i f10933b;

    /* renamed from: c  reason: collision with root package name */
    private final f f10934c = new f() { // from class: com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings$$SettingImpl.1
        @Override // com.bytedance.push.settings.f
        public <T> T create(Class<T> cls) {
            return null;
        }
    };

    public PaymentLocalSettings$$SettingImpl(Context context, i iVar) {
        this.f10932a = context;
        this.f10933b = iVar;
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public String a() {
        i iVar = this.f10933b;
        return (iVar == null || !iVar.contains("need_restore_orders")) ? "" : this.f10933b.getString("need_restore_orders");
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public void a(String str) {
        i iVar = this.f10933b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("need_restore_orders", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public long b() {
        i iVar = this.f10933b;
        if (iVar == null || !iVar.contains("last_settings_request_time")) {
            return 0L;
        }
        return this.f10933b.getLong("last_settings_request_time");
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public void a(long j2) {
        i iVar = this.f10933b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putLong("last_settings_request_time", j2);
            edit.apply();
        }
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public String d() {
        i iVar = this.f10933b;
        return (iVar == null || !iVar.contains("google_orders")) ? "" : this.f10933b.getString("google_orders");
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public void c(String str) {
        i iVar = this.f10933b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("google_orders", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public String e() {
        i iVar = this.f10933b;
        return (iVar == null || !iVar.contains("item_not_own_orders")) ? "" : this.f10933b.getString("item_not_own_orders");
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public void d(String str) {
        i iVar = this.f10933b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("item_not_own_orders", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public String f() {
        i iVar = this.f10933b;
        return (iVar == null || !iVar.contains("request_host")) ? "" : this.f10933b.getString("request_host");
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public void e(String str) {
        i iVar = this.f10933b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("request_host", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public String c() {
        i iVar = this.f10933b;
        return (iVar == null || !iVar.contains("dynamic_errors_json_string")) ? "" : this.f10933b.getString("dynamic_errors_json_string");
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public void b(String str) {
        i iVar = this.f10933b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putString("dynamic_errors_json_string", str);
            edit.apply();
        }
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public long g() {
        i iVar = this.f10933b;
        if (iVar == null || !iVar.contains("dynamic_errors_last_time")) {
            return 0L;
        }
        return this.f10933b.getLong("dynamic_errors_last_time");
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings
    public void b(long j2) {
        i iVar = this.f10933b;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putLong("dynamic_errors_last_time", j2);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.ILocalSettings
    public void registerValChanged(Context context, String str, String str2, b bVar) {
        i iVar = this.f10933b;
        if (iVar != null) {
            iVar.registerValChanged(context, str, str2, bVar);
        }
    }

    @Override // com.bytedance.push.settings.ILocalSettings
    public void unregisterValChanged(b bVar) {
        i iVar = this.f10933b;
        if (iVar != null) {
            iVar.unregisterValChanged(bVar);
        }
    }
}
