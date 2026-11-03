package com.bytedance.bdinstall;

import android.accounts.Account;
import android.app.Application;
import android.content.Context;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: IBDInstallApi.java */
/* loaded from: classes.dex */
public interface aa {
    aa a(am amVar);

    aa a(am amVar, v vVar);

    al a(long j2);

    al a(h hVar);

    String a(Context context, String str, boolean z, a aVar);

    String a(Context context, StringBuilder sb, String str, boolean z, a aVar);

    String a(Context context, StringBuilder sb, boolean z, a aVar);

    void a(Account account);

    void a(Application application);

    void a(Context context, v vVar, long j2, aq aqVar);

    void a(Context context, String str);

    void a(Context context, String str, String str2);

    void a(Context context, Map<String, String> map, boolean z, a aVar);

    void a(Context context, JSONObject jSONObject);

    void a(ac acVar);

    void a(af afVar);

    void a(ag agVar);

    void a(ah ahVar);

    void a(al alVar);

    void a(com.bytedance.bdinstall.intf.b bVar);

    void a(com.bytedance.bdinstall.intf.c cVar);

    void a(v vVar);

    void a(w wVar, String str);

    void a(y yVar);

    void a(String str, a aVar);

    void a(String str, String str2, a aVar);

    void a(Map<String, String> map);

    void a(boolean z, ag agVar);

    void a(boolean z, y yVar);

    boolean a(JSONObject jSONObject);

    void b(Context context, v vVar, long j2, aq aqVar);

    void b(Context context, String str);

    void b(Context context, JSONObject jSONObject);

    void b(ac acVar);

    void b(h hVar);

    void b(v vVar);

    void c(v vVar);

    void d();

    Map<String, String> e();

    v f();

    JSONObject g();

    al h();

    String i();

    al j();

    boolean k();

    com.bytedance.bdinstall.service.d l();

    boolean m();

    boolean n();

    boolean o();

    com.bytedance.bdinstall.intf.b p();

    com.bytedance.bdinstall.intf.c q();

    am r();

    h s();
}
