package com.linecorp.linesdk.auth.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.linecorp.linesdk.LineApiError;
import com.linecorp.linesdk.LineApiResponseCode;
import com.linecorp.linesdk.R;
import com.linecorp.linesdk.auth.LineAuthenticationConfig;
import com.linecorp.linesdk.auth.LineLoginResult;
import com.linecorp.linesdk.auth.internal.a;
import com.linecorp.linesdk.auth.internal.c;
import com.linecorp.linesdk.auth.internal.d;
import java.util.List;
/* loaded from: classes9.dex */
public class LineAuthenticationActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    private boolean f17288a = false;

    /* renamed from: b  reason: collision with root package name */
    private d f17289b;

    /* renamed from: c  reason: collision with root package name */
    private c f17290c;

    public static Intent a(Context context, LineAuthenticationConfig lineAuthenticationConfig, List<String> list) {
        Intent intent = new Intent(context, LineAuthenticationActivity.class);
        intent.putExtra("authentication_config", lineAuthenticationConfig);
        intent.putExtra("permissions", (String[]) list.toArray(new String[list.size()]));
        return intent;
    }

    public static LineLoginResult a(Intent intent) {
        LineLoginResult lineLoginResult = (LineLoginResult) intent.getParcelableExtra("authentication_result");
        return lineLoginResult == null ? new LineLoginResult(LineApiResponseCode.INTERNAL_ERROR, new LineApiError("Authentication result is not found.")) : lineLoginResult;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        d dVar;
        super.onCreate(bundle);
        setContentView(R.layout.linesdk_activity_lineauthentication);
        Intent intent = getIntent();
        LineAuthenticationConfig lineAuthenticationConfig = (LineAuthenticationConfig) intent.getParcelableExtra("authentication_config");
        if (lineAuthenticationConfig == null) {
            a(new LineLoginResult(LineApiResponseCode.INTERNAL_ERROR, new LineApiError("The requested parameter is illegal.")));
            return;
        }
        if (bundle == null) {
            dVar = new d();
        } else {
            dVar = (d) bundle.getParcelable("authenticationStatus");
            if (dVar == null) {
                dVar = new d();
            }
        }
        this.f17289b = dVar;
        this.f17290c = new c(this, lineAuthenticationConfig, this.f17289b, intent.getStringArrayExtra("permissions"));
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.f17289b.f17321d == d.a.f17322a) {
            c cVar = this.f17290c;
            cVar.f17314h.f17321d = d.a.f17323b;
            new c.AsyncTaskC0314c(cVar, (byte) 0).execute(new Void[0]);
        } else if (this.f17289b.f17321d != d.a.f17324c) {
            new Handler(Looper.getMainLooper()).postDelayed(new c.b(this.f17290c, (byte) 0), 1000L);
        }
        this.f17288a = false;
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.f17288a = true;
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        a.c a2;
        super.onNewIntent(intent);
        if (this.f17289b.f17321d == d.a.f17323b) {
            c cVar = this.f17290c;
            cVar.f17314h.f17321d = d.a.f17324c;
            a aVar = cVar.f17311e;
            Uri data = intent.getData();
            if (data == null) {
                a2 = a.c.a("Illegal redirection from external application.");
            } else {
                String str = aVar.f17292b.f17320c;
                String queryParameter = data.getQueryParameter("state");
                if (str == null || !str.equals(queryParameter)) {
                    a2 = a.c.a("Illegal parameter value of 'state'.");
                } else {
                    String queryParameter2 = data.getQueryParameter("code");
                    if (!TextUtils.isEmpty(queryParameter2)) {
                        a2 = new a.c(queryParameter2, null, null, null);
                    } else {
                        a2 = new a.c(null, data.getQueryParameter("error"), data.getQueryParameter("error_description"), null);
                    }
                }
            }
            if (a2.a()) {
                c.a aVar2 = new c.a(cVar, (byte) 0);
                String[] strArr = new String[1];
                if (TextUtils.isEmpty(a2.f17300a)) {
                    throw new UnsupportedOperationException("requestToken is null. Please check result by isSuccess before.");
                }
                strArr[0] = a2.f17300a;
                aVar2.execute(strArr);
                return;
            }
            cVar.f17314h.f17321d = d.a.f17325d;
            cVar.f17307a.a(new LineLoginResult(a2.b() ? LineApiResponseCode.AUTHENTICATION_AGENT_ERROR : LineApiResponseCode.INTERNAL_ERROR, a2.c()));
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (this.f17289b.f17321d == d.a.f17323b) {
            c cVar = this.f17290c;
            if (i2 != 3 || cVar.f17314h.f17321d == d.a.f17324c) {
                return;
            }
            new Handler(Looper.getMainLooper()).postDelayed(new c.b(cVar, (byte) 0), 1000L);
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("authenticationStatus", this.f17289b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(LineLoginResult lineLoginResult) {
        d dVar = this.f17289b;
        if (dVar == null) {
            finish();
        } else if ((dVar.f17321d != d.a.f17323b || this.f17288a) && this.f17289b.f17321d != d.a.f17325d) {
        } else {
            Intent intent = new Intent();
            intent.putExtra("authentication_result", lineLoginResult);
            setResult(-1, intent);
            finish();
        }
    }
}
