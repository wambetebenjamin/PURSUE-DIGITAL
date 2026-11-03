package com.bytedance.push.settings.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.text.TextUtils;
import com.bytedance.push.settings.SettingsManager;
import com.bytedance.push.settings.p;
/* compiled from: SettingsFileLockHelper.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11779a = "SettingsFileLockHelper";

    /* renamed from: i  reason: collision with root package name */
    private static volatile b f11780i;

    /* renamed from: d  reason: collision with root package name */
    private boolean f11783d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f11784e;

    /* renamed from: f  reason: collision with root package name */
    private String f11785f;
    private Integer k;

    /* renamed from: b  reason: collision with root package name */
    private final String f11781b = "local_settings_sp";

    /* renamed from: c  reason: collision with root package name */
    private final String f11782c = "first_process";

    /* renamed from: j  reason: collision with root package name */
    private final String f11788j = "first_process_pid";

    /* renamed from: g  reason: collision with root package name */
    private final a f11786g = new a("bdpush_is_first_process.lock");

    /* renamed from: h  reason: collision with root package name */
    private final a f11787h = new a("bdpush_local_settings_sp.lock");

    public static b getInstance() {
        if (f11780i == null) {
            synchronized (b.class) {
                if (f11780i == null) {
                    f11780i = new b();
                }
            }
        }
        return f11780i;
    }

    private b() {
    }

    public boolean b(Context context) {
        return onProcessStart(context);
    }

    public boolean onProcessStart(Context context) {
        try {
            com.bytedance.push.settings.logger.b.a().a(f11779a, "curIsFirstProcess called on " + com.bytedance.push.settings.utils.a.getCurProcessName(context) + " process , sHasCheckIsFirst is " + this.f11783d);
            if (this.f11783d) {
                return this.f11784e;
            }
            this.f11783d = true;
            this.f11784e = this.f11786g.isFirstLockFile(context);
            com.bytedance.push.settings.logger.b.a().a(f11779a, "isFirstLockFile: sIsFirst = " + this.f11784e + "  process = " + com.bytedance.push.settings.utils.a.getCurProcessName(context));
            if (this.f11784e) {
                a(context);
            }
            return this.f11784e;
        } catch (Throwable th) {
            th.printStackTrace();
            this.f11784e = false;
            return false;
        }
    }

    private void a(Context context) {
        if (this.f11787h.tryLockFile(context)) {
            SharedPreferences.Editor edit = context.getSharedPreferences("local_settings_sp", 0).edit();
            String curProcessName = com.bytedance.push.settings.utils.a.getCurProcessName(context);
            edit.putString("first_process", curProcessName);
            edit.putInt("first_process_pid", Process.myPid());
            edit.apply();
            com.bytedance.push.settings.logger.b.a().a(f11779a, "write  " + curProcessName + "  as first process success on " + com.bytedance.push.settings.utils.a.getCurProcessName(context));
            this.f11787h.releaseLockFile();
        }
    }

    public Integer c(Context context) {
        Integer num = this.k;
        if (num != null) {
            return num;
        }
        if (this.f11787h.tryLockFile(context)) {
            this.k = Integer.valueOf(context.getSharedPreferences("local_settings_sp", 0).getInt("first_process_pid", 0));
            this.f11787h.releaseLockFile();
            com.bytedance.push.settings.logger.b.a().a(f11779a, "read first process pid success , first process pid is:" + this.k + " on " + com.bytedance.push.settings.utils.a.getCurProcessName(context));
        }
        return this.k;
    }

    public String getFirstProcess(Context context) {
        if (!TextUtils.isEmpty(this.f11785f)) {
            return this.f11785f;
        }
        if (this.f11787h.tryLockFile(context)) {
            this.f11785f = context.getSharedPreferences("local_settings_sp", 0).getString("first_process", "");
            this.f11787h.releaseLockFile();
            com.bytedance.push.settings.logger.b.a().a(f11779a, "read first process success , first process is:" + this.f11785f + " on " + com.bytedance.push.settings.utils.a.getCurProcessName(context));
            return this.f11785f;
        }
        return "";
    }

    public void a(Context context, boolean z, boolean z2) {
        if (this.f11787h.tryLockFile(context)) {
            SharedPreferences.Editor edit = context.getSharedPreferences("local_settings_sp", 0).edit();
            edit.putBoolean("allow", z);
            edit.putInt("smp_pid", Process.myPid());
            edit.putBoolean("disable_report_terminate_event", z2);
            edit.apply();
            com.bytedance.push.settings.logger.b.a().a(f11779a, "write allow start other process success , allow is:" + z + " on " + com.bytedance.push.settings.utils.a.getCurProcessName(context));
            this.f11787h.releaseLockFile();
        }
        SettingsManager.allowReadSettingsOnSmp = true;
    }

    public boolean readAllowStartOthersProcess(Context context) {
        if (this.f11787h.tryLockFile(context)) {
            boolean z = context.getSharedPreferences("local_settings_sp", 0).getBoolean("allow", true);
            com.bytedance.push.settings.logger.b.a().a(f11779a, "read allow start other process success , allow is:" + z + " on " + com.bytedance.push.settings.utils.a.getCurProcessName(context));
            this.f11787h.releaseLockFile();
            return z;
        }
        return true;
    }

    public p d(Context context) {
        p pVar = new p();
        if (this.f11787h.tryLockFile(context)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("local_settings_sp", 0);
            int i2 = sharedPreferences.getInt("smp_pid", -1);
            boolean z = sharedPreferences.getBoolean("disable_report_terminate_event", false);
            com.bytedance.push.settings.logger.b.a().a(f11779a, "read disable report applog event success , disable is:" + z + " on " + com.bytedance.push.settings.utils.a.getCurProcessName(context));
            this.f11787h.releaseLockFile();
            pVar.f11851a = i2;
            pVar.f11852b = z;
        }
        return pVar;
    }

    public boolean e(Context context) {
        if (this.f11787h.tryLockFile(context)) {
            boolean z = context.getSharedPreferences("local_settings_sp", 0).getBoolean("disable_report_terminate_event", false);
            com.bytedance.push.settings.logger.b.a().a(f11779a, "read disable report applog event success , disable is:" + z + " on " + com.bytedance.push.settings.utils.a.getCurProcessName(context));
            this.f11787h.releaseLockFile();
            return z;
        }
        return false;
    }
}
