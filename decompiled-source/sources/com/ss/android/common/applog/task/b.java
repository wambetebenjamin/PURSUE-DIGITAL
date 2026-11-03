package com.ss.android.common.applog.task;

import android.content.Context;
import android.content.SharedPreferences;
import com.ss.android.common.applog.TaskSessionDao;
import com.ss.android.common.applog.TeaThread;
import com.ss.android.common.util.c;
/* compiled from: TaskModel.java */
/* loaded from: classes9.dex */
public class b {

    /* renamed from: b  reason: collision with root package name */
    private static final String f18041b = "key_task_session";

    /* renamed from: a  reason: collision with root package name */
    private Context f18042a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Context context) {
        init(context);
    }

    public void init(Context context) {
        this.f18042a = context.getApplicationContext();
        TaskSessionDao.inst(context);
        TeaThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.android.common.applog.task.b.1
            @Override // java.lang.Runnable
            public void run() {
                d a2 = b.this.a();
                if (a2 != null) {
                    b.this.saveTaskSessionToDb(a2);
                }
                b.this.clearSessionSp();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public d a() {
        return d.fromString(this.f18042a.getSharedPreferences(com.ss.android.deviceregister.base.a.getSPName(), 0).getString(f18041b, ""));
    }

    public void saveTaskSessionToDb(d dVar) {
        TaskSessionDao.inst(this.f18042a).saveTaskSession(dVar);
    }

    public void saveTaskSessionToSp(d dVar) {
        if (dVar == null) {
            return;
        }
        c.a.d("saveTaskSessionToSp : " + dVar);
        a(dVar.toJsonString());
    }

    private void a(String str) {
        if (str == null) {
            str = "";
        }
        try {
            SharedPreferences.Editor edit = this.f18042a.getSharedPreferences(com.ss.android.deviceregister.base.a.getSPName(), 0).edit();
            edit.putString(f18041b, str);
            edit.apply();
        } catch (Throwable unused) {
        }
    }

    public void clearSessionSp() {
        c.a.d("clear task session sp");
        a("");
    }
}
