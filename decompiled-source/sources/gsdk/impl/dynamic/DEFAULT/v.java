package gsdk.impl.dynamic.DEFAULT;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import androidx.fragment.app.FragmentActivity;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.ttgame.module.dynamic.api.IDynamicWindow;
import com.bytedance.ttgame.module.dynamic.framework.DynmamicWindowSupportFragment;
import gsdk.impl.dynamic.DEFAULT.s;
/* compiled from: DynamicWindowDelegate.java */
/* loaded from: classes10.dex */
public class v {

    /* renamed from: b  reason: collision with root package name */
    private l f20275b;

    /* renamed from: c  reason: collision with root package name */
    private String f20276c;

    /* renamed from: d  reason: collision with root package name */
    private String f20277d;

    /* renamed from: e  reason: collision with root package name */
    private String f20278e;

    /* renamed from: f  reason: collision with root package name */
    private Bundle f20279f;

    /* renamed from: g  reason: collision with root package name */
    private q f20280g;

    /* renamed from: h  reason: collision with root package name */
    private String f20281h;

    /* renamed from: i  reason: collision with root package name */
    private String f20282i;

    /* renamed from: j  reason: collision with root package name */
    private int f20283j;
    private w k;
    private DynmamicWindowSupportFragment l;

    /* renamed from: a  reason: collision with root package name */
    Handler f20274a = new Handler();
    private boolean m = true;

    public void a(String str, String str2, String str3, String str4, int i2, Bundle bundle, String str5) {
        this.f20281h = str5;
        Bundle bundle2 = new Bundle();
        bundle2.putString(RNConfig.MODULE_NAME, str3);
        bundle2.putString("module", str2);
        bundle2.putString(RNConfig.BUNDLE_NAME, str4);
        bundle2.putParcelable("params", bundle);
        bundle2.putInt("mode", i2);
        bundle2.putString(RNConfig.WINDOW_ID, str5);
        bundle2.putString("url", str);
        this.f20282i = str;
        this.f20281h = str5;
        this.f20279f = bundle;
        w wVar = this.k;
        if (wVar != null) {
            wVar.setArguments(bundle2);
            return;
        }
        DynmamicWindowSupportFragment dynmamicWindowSupportFragment = this.l;
        if (dynmamicWindowSupportFragment != null) {
            dynmamicWindowSupportFragment.setArguments(bundle2);
        }
    }

    public v(w wVar) {
        this.k = wVar;
    }

    public v(DynmamicWindowSupportFragment dynmamicWindowSupportFragment) {
        this.l = dynmamicWindowSupportFragment;
    }

    public IDynamicWindow a() {
        w wVar = this.k;
        return wVar == null ? this.l : wVar;
    }

    public Bundle b() {
        w wVar = this.k;
        if (wVar != null) {
            return wVar.getArguments();
        }
        DynmamicWindowSupportFragment dynmamicWindowSupportFragment = this.l;
        if (dynmamicWindowSupportFragment != null) {
            return dynmamicWindowSupportFragment.getArguments();
        }
        return null;
    }

    public Activity c() {
        w wVar = this.k;
        if (wVar != null) {
            return wVar.getActivity();
        }
        DynmamicWindowSupportFragment dynmamicWindowSupportFragment = this.l;
        if (dynmamicWindowSupportFragment != null) {
            return dynmamicWindowSupportFragment.getActivity();
        }
        return null;
    }

    public Dialog a(Bundle bundle) {
        if (b() != null) {
            this.f20276c = b().getString(RNConfig.MODULE_NAME);
            this.f20279f = (Bundle) b().getParcelable("params");
            this.f20277d = b().getString("module");
            this.f20278e = b().getString(RNConfig.BUNDLE_NAME);
            this.f20283j = b().getInt("mode");
            this.f20281h = b().getString(RNConfig.WINDOW_ID);
            this.f20282i = b().getString("url");
        }
        if (bundle != null) {
            Bundle bundle2 = this.f20279f;
            if (bundle2 != null && bundle2.containsKey("isDynamic")) {
                this.f20279f.getBoolean("isDynamic");
            }
            ad.a(this.f20282i, this.f20276c, this.f20279f.getString(RNConfig.MODULE_VERSION), 1);
            this.f20279f.putLong(RNConfig.RN_PAGE_START_TIME, System.currentTimeMillis());
        }
        q qVar = new q(true, a());
        this.f20280g = qVar;
        qVar.a(this.f20282i);
        this.f20280g.a(this.f20279f.getLong(RNConfig.RN_PAGE_START_TIME));
        this.f20275b = new l(c(), this.f20277d, this.f20276c, this.f20278e, this.f20283j, this.f20279f, this.f20280g, bundle != null);
        x.b(a());
        this.m = true;
        return this.f20275b;
    }

    public void a(String str, String str2) {
        l lVar = this.f20275b;
        if (lVar != null) {
            lVar.a(str, str2);
        }
    }

    public void d() {
        if (c() != null) {
            c().onWindowFocusChanged(true);
        }
        l lVar = this.f20275b;
        if (lVar != null) {
            lVar.a();
            s.a(h(), s.a.RESUME);
        }
    }

    public void e() {
        if (this.f20275b != null) {
            s.a(h(), s.a.PAUSE);
            this.f20275b.b();
        }
    }

    public void f() {
        x.a(a());
        l lVar = this.f20275b;
        if (lVar != null) {
            lVar.c();
        }
    }

    public void g() {
        if (this.m) {
            return;
        }
        k();
    }

    public String h() {
        return this.f20281h;
    }

    public void a(Activity activity) {
        DynmamicWindowSupportFragment dynmamicWindowSupportFragment;
        if (activity != null) {
            try {
                if ((activity instanceof FragmentActivity) && (dynmamicWindowSupportFragment = this.l) != null) {
                    dynmamicWindowSupportFragment.show(((FragmentActivity) activity).getSupportFragmentManager(), "window_" + h());
                } else {
                    w wVar = this.k;
                    if (wVar != null) {
                        wVar.show(activity.getFragmentManager(), "window_" + h());
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public void i() {
        try {
            w wVar = this.k;
            if (wVar != null) {
                wVar.dismissAllowingStateLoss();
            }
            DynmamicWindowSupportFragment dynmamicWindowSupportFragment = this.l;
            if (dynmamicWindowSupportFragment != null) {
                dynmamicWindowSupportFragment.dismissAllowingStateLoss();
            }
        } catch (Exception unused) {
        }
    }

    public boolean j() {
        l lVar = this.f20275b;
        if (lVar != null) {
            lVar.show();
            this.m = true;
            return true;
        }
        return false;
    }

    public void k() {
        l lVar = this.f20275b;
        if (lVar != null) {
            lVar.hide();
            this.m = false;
        }
    }

    public String l() {
        return this.f20282i;
    }

    public boolean m() {
        return this.f20275b.isShowing();
    }

    public Window n() {
        l lVar = this.f20275b;
        if (lVar != null) {
            return lVar.getWindow();
        }
        return null;
    }

    public void a(Configuration configuration) {
        l lVar = this.f20275b;
        if (lVar != null) {
            lVar.a(configuration);
        }
    }
}
