package com.bytedance.pipo.payment.common.lib.settings;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.push.settings.b;
import com.bytedance.push.settings.e;
import com.bytedance.push.settings.f;
import com.bytedance.push.settings.g;
import com.bytedance.push.settings.storage.i;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class PaymentOnlineSettings$$SettingImpl implements PaymentOnlineSettings {

    /* renamed from: c  reason: collision with root package name */
    private i f10938c;

    /* renamed from: a  reason: collision with root package name */
    private final ConcurrentHashMap<String, Object> f10936a = new ConcurrentHashMap<>();

    /* renamed from: b  reason: collision with root package name */
    private final ConcurrentHashMap<String, Object> f10937b = new ConcurrentHashMap<>();

    /* renamed from: d  reason: collision with root package name */
    private final f f10939d = new f() { // from class: com.bytedance.pipo.payment.common.lib.settings.PaymentOnlineSettings$$SettingImpl.1
        @Override // com.bytedance.push.settings.f
        public <T> T create(Class<T> cls) {
            if (cls == a.class) {
                return (T) new a();
            }
            if (cls == a.class) {
                return (T) new a();
            }
            if (cls == a.class) {
                return (T) new a();
            }
            return null;
        }
    };

    public PaymentOnlineSettings$$SettingImpl(i iVar) {
        this.f10938c = iVar;
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentOnlineSettings
    public boolean d() {
        i iVar = this.f10938c;
        if (iVar == null || !iVar.contains("enable_server_validate_renew")) {
            return true;
        }
        return this.f10938c.getBoolean("enable_server_validate_renew");
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentOnlineSettings
    public boolean a() {
        i iVar = this.f10938c;
        if (iVar == null || !iVar.contains("enable_sec_sdk")) {
            return false;
        }
        return this.f10938c.getBoolean("enable_sec_sdk");
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentOnlineSettings
    public boolean h() {
        i iVar = this.f10938c;
        if (iVar == null || !iVar.contains("enable_sec_sdk_dynamic_key")) {
            return false;
        }
        return this.f10938c.getBoolean("enable_sec_sdk_dynamic_key");
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentOnlineSettings
    public JSONObject b() {
        i iVar = this.f10938c;
        if (iVar == null || !iVar.contains("pipo_restore_settings")) {
            return null;
        }
        return ((a) e.obtain(a.class, this.f10939d)).to(this.f10938c.getString("pipo_restore_settings"));
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentOnlineSettings
    public JSONObject c() {
        i iVar = this.f10938c;
        if (iVar == null || !iVar.contains("pipo_validate_receipt_settings")) {
            return null;
        }
        return ((a) e.obtain(a.class, this.f10939d)).to(this.f10938c.getString("pipo_validate_receipt_settings"));
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentOnlineSettings
    public JSONObject f() {
        i iVar = this.f10938c;
        if (iVar == null || !iVar.contains("pipo_dynamic_error_codes")) {
            return null;
        }
        return ((a) e.obtain(a.class, this.f10939d)).to(this.f10938c.getString("pipo_dynamic_error_codes"));
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentOnlineSettings
    public long e() {
        i iVar = this.f10938c;
        if (iVar == null || !iVar.contains("pipo_sku_cache_expiration_time")) {
            return 600000L;
        }
        return this.f10938c.getLong("pipo_sku_cache_expiration_time");
    }

    @Override // com.bytedance.pipo.payment.common.lib.settings.PaymentOnlineSettings
    public void a(boolean z) {
        i iVar = this.f10938c;
        if (iVar != null) {
            SharedPreferences.Editor edit = iVar.edit();
            edit.putBoolean("enable_sec_sdk", z);
            edit.apply();
        }
    }

    @Override // com.bytedance.push.settings.ISettings
    public void updateSettings(Context context, JSONObject jSONObject) {
        i iVar;
        if (jSONObject == null || (iVar = this.f10938c) == null) {
            return;
        }
        SharedPreferences.Editor edit = iVar.edit();
        if (jSONObject != null) {
            if (jSONObject.has("enable_server_validate_renew")) {
                edit.putBoolean("enable_server_validate_renew", g.optBoolean(jSONObject, "enable_server_validate_renew"));
            }
            if (jSONObject.has("enable_sec_sdk")) {
                edit.putBoolean("enable_sec_sdk", g.optBoolean(jSONObject, "enable_sec_sdk"));
            }
            if (jSONObject.has("enable_sec_sdk_dynamic_key")) {
                edit.putBoolean("enable_sec_sdk_dynamic_key", g.optBoolean(jSONObject, "enable_sec_sdk_dynamic_key"));
            }
            if (jSONObject.has("pipo_restore_settings")) {
                edit.putString("pipo_restore_settings", jSONObject.optString("pipo_restore_settings"));
            }
            if (jSONObject.has("pipo_validate_receipt_settings")) {
                edit.putString("pipo_validate_receipt_settings", jSONObject.optString("pipo_validate_receipt_settings"));
            }
            if (jSONObject.has("pipo_dynamic_error_codes")) {
                edit.putString("pipo_dynamic_error_codes", jSONObject.optString("pipo_dynamic_error_codes"));
            }
            if (jSONObject.has("pipo_sku_cache_expiration_time")) {
                edit.putLong("pipo_sku_cache_expiration_time", jSONObject.optLong("pipo_sku_cache_expiration_time"));
            }
        }
        edit.apply();
    }

    @Override // com.bytedance.push.settings.ISettings
    public void registerValChanged(Context context, String str, String str2, b bVar) {
        i iVar = this.f10938c;
        if (iVar != null) {
            iVar.registerValChanged(context, str, str2, bVar);
        }
    }

    @Override // com.bytedance.push.settings.ISettings
    public void unregisterValChanged(b bVar) {
        i iVar = this.f10938c;
        if (iVar != null) {
            iVar.unregisterValChanged(bVar);
        }
    }
}
