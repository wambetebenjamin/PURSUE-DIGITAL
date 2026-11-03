package com.bytedance.crash.looper;

import android.os.Message;
import com.bytedance.apm.block.n;
import com.bytedance.apm.block.o;
import com.bytedance.crash.util.k;
import com.bytedance.crash.util.q;
import java.io.File;
import org.json.JSONObject;
/* compiled from: LooperMessageRecorder.java */
/* loaded from: classes2.dex */
public class d implements n {

    /* renamed from: b  reason: collision with root package name */
    private static final d f6860b = new d();

    /* renamed from: a  reason: collision with root package name */
    private final b f6861a = new b();

    /* renamed from: c  reason: collision with root package name */
    private boolean f6862c;

    public static d a() {
        return f6860b;
    }

    public synchronized void b() {
        if (this.f6862c) {
            return;
        }
        this.f6862c = true;
        this.f6861a.a();
        o.init(null);
        o.a(this);
    }

    public void c() {
        this.f6862c = false;
        this.f6861a.b();
        o.b(this);
    }

    @Override // com.bytedance.apm.block.n
    public void a(String str) {
        this.f6861a.a((Message) null);
    }

    @Override // com.bytedance.apm.block.n
    public void a(String str, Message message) {
        this.f6861a.a(message);
    }

    public void a(File file) {
        this.f6861a.a(file);
    }

    public static void a(JSONObject jSONObject, File file) {
        b(jSONObject, a.a(file));
        b(jSONObject, a.b(file));
        b(jSONObject, a.c(file));
        b(jSONObject, a.d(file));
    }

    private static void b(JSONObject jSONObject, File file) {
        try {
            String d2 = k.d(file);
            if (d2 != null) {
                q.a(jSONObject, new JSONObject(d2));
            }
        } catch (Exception unused) {
        }
    }
}
