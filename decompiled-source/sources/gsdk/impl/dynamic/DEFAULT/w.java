package gsdk.impl.dynamic.DEFAULT;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Window;
import com.bytedance.ttgame.module.dynamic.api.IDynamicWindow;
/* compiled from: DynamicWindowFragment.java */
/* loaded from: classes10.dex */
public class w extends DialogFragment implements IDynamicWindow {

    /* renamed from: a  reason: collision with root package name */
    private v f20284a = new v(this);

    /* renamed from: b  reason: collision with root package name */
    private Bundle f20285b;

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicWindow
    public String getParentWindowCallbackID() {
        return "";
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicWindow
    public boolean onBackPress() {
        return false;
    }

    public static w a(String str, String str2, String str3, String str4, int i2, Bundle bundle, String str5) {
        w wVar = new w();
        wVar.f20285b = bundle;
        v vVar = wVar.f20284a;
        if (vVar != null) {
            vVar.a(str, str2, str3, str4, i2, bundle, str5);
        }
        return wVar;
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        v vVar = this.f20284a;
        if (vVar != null && bundle == null) {
            return vVar.a(bundle);
        }
        dismissAllowingStateLoss();
        return super.onCreateDialog(bundle);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        v vVar = this.f20284a;
        if (vVar != null) {
            vVar.g();
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        v vVar = this.f20284a;
        if (vVar != null) {
            vVar.d();
        }
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        v vVar = this.f20284a;
        if (vVar != null) {
            vVar.e();
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        v vVar = this.f20284a;
        if (vVar != null) {
            vVar.f();
        }
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicWindow
    public void showWindow(Activity activity) {
        v vVar = this.f20284a;
        if (vVar != null) {
            vVar.a(activity);
        }
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicWindow
    public boolean hide() {
        v vVar = this.f20284a;
        if (vVar != null) {
            vVar.k();
            return true;
        }
        return true;
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicWindow
    public boolean show() {
        v vVar = this.f20284a;
        if (vVar != null) {
            return vVar.j();
        }
        return false;
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicWindow
    public String getRNUrl() {
        v vVar = this.f20284a;
        return vVar != null ? vVar.l() : "";
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicWindow
    public boolean isShow() {
        v vVar = this.f20284a;
        if (vVar != null) {
            return vVar.m();
        }
        return false;
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicWindow
    public Window getAndroidWindow() {
        v vVar = this.f20284a;
        if (vVar != null) {
            return vVar.n();
        }
        return null;
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicWindow
    public void close() {
        v vVar = this.f20284a;
        if (vVar != null) {
            vVar.i();
        }
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicWindow
    public String getWindowToken() {
        v vVar = this.f20284a;
        if (vVar != null) {
            return vVar.h();
        }
        return null;
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicWindow
    public void sentMessageToRN(String str, String str2) {
        v vVar = this.f20284a;
        if (vVar != null) {
            vVar.a(str, str2);
        }
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        v vVar = this.f20284a;
        if (vVar != null) {
            vVar.a(configuration);
        }
    }
}
