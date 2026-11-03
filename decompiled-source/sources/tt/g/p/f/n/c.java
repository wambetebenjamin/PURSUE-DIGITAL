package tt.g.p.f.n;

import android.content.Intent;
import android.os.AsyncTask;
import com.bytedance.JProtect;
import org.json.JSONObject;
@JProtect
/* loaded from: classes10.dex */
public class c extends AsyncTask<Void, Void, Void> {

    /* renamed from: a  reason: collision with root package name */
    private bh f21217a;

    public c(bh bhVar) {
        this.f21217a = bhVar;
    }

    public static void a(Intent intent, String str) {
        bh a2 = bk.a(intent, str);
        if (a2 == null) {
            a2 = bd.a(intent, str);
        }
        if (a2 != null) {
            new c(a2).execute(new Void[0]);
        }
    }

    public static void a(String str, String str2) {
        bd a2 = bd.a(str, str2);
        if (a2 != null) {
            new c(a2).execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a */
    public Void doInBackground(Void... voidArr) {
        a(this.f21217a.b(), this.f21217a.a());
        return null;
    }

    public void a(int i2, JSONObject jSONObject) {
        q qVar = s.f21235a;
        if (qVar != null) {
            qVar.a(i2, jSONObject);
        }
    }
}
