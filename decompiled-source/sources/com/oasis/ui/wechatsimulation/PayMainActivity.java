package com.oasis.ui.wechatsimulation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import com.oasis.Logger.Logger;
import com.oasis.android.R;
import com.oasis.utils.StatusBarUtils;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.util.Locale;
import java.util.concurrent.Callable;
/* loaded from: classes9.dex */
public class PayMainActivity extends AppCompatActivity {

    /* renamed from: a  reason: collision with root package name */
    private h f17715a;

    /* loaded from: classes9.dex */
    class a implements Callable<Boolean> {
        a() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            PayMainActivity payMainActivity = PayMainActivity.this;
            payMainActivity.f17715a = h.ShowingPassword;
            payMainActivity.a();
            return Boolean.TRUE;
        }
    }

    /* loaded from: classes9.dex */
    class b implements Callable<Boolean> {
        b() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            return Boolean.TRUE;
        }
    }

    /* loaded from: classes9.dex */
    class c implements Callable<Boolean> {
        c() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            PayMainActivity payMainActivity = PayMainActivity.this;
            payMainActivity.f17715a = h.ShowingPassword;
            payMainActivity.a();
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public class d implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Callable f17719a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ com.oasis.ui.wechatsimulation.a f17720b;

        d(Callable callable, com.oasis.ui.wechatsimulation.a aVar) {
            this.f17719a = callable;
            this.f17720b = aVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Callable callable = this.f17719a;
            boolean z = false;
            if (callable != null) {
                try {
                    Boolean bool = (Boolean) callable.call();
                    if (bool != null) {
                        if (bool.booleanValue()) {
                            z = true;
                        }
                    }
                } catch (Exception unused) {
                }
            }
            if (this.f17720b.isShowing() && z) {
                this.f17720b.dismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public class e implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Callable f17722a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ com.oasis.ui.wechatsimulation.a f17723b;

        e(Callable callable, com.oasis.ui.wechatsimulation.a aVar) {
            this.f17722a = callable;
            this.f17723b = aVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Callable callable = this.f17722a;
            boolean z = false;
            if (callable != null) {
                try {
                    Boolean bool = (Boolean) callable.call();
                    if (bool != null) {
                        if (bool.booleanValue()) {
                            z = true;
                        }
                    }
                } catch (Exception unused) {
                }
            }
            if (this.f17723b.isShowing() && z) {
                this.f17723b.dismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public class f implements Callable<Boolean> {
        f() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            PayMainActivity.this.f17715a = h.ShowingPayMainActivity;
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public class g implements Callable<Boolean> {
        g() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            PayMainActivity.this.f17715a = h.UserExitManually;
            WeChatSimulationAgent.a(false, WeChatSimulationAgent.a(-1, "user cancel pay!"));
            PayMainActivity.super.onBackPressed();
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public enum h {
        ShowingPayMainActivity,
        ShowingTipDialog,
        ShowingPassword,
        ShowingPaySuccess,
        UserExitManually
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.f17715a = h.ShowingPassword;
        startActivityForResult(new Intent(this, PayPasswordActivity.class), 0);
    }

    private void a(String str, String str2, String str3, Callable<Boolean> callable, Callable<Boolean> callable2) {
        com.oasis.ui.wechatsimulation.a aVar = new com.oasis.ui.wechatsimulation.a(this);
        aVar.a(str).a(str2, ViewCompat.MEASURED_STATE_MASK).b(str3, -11047787).a(new e(callable, aVar)).b(new d(callable2, aVar)).show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        Callable<Boolean> aVar;
        b bVar;
        String str;
        String str2;
        super.onActivityResult(i2, i3, intent);
        if (i3 == 1) {
            this.f17715a = h.ShowingTipDialog;
            aVar = new a();
            bVar = null;
            str = "支付密码错误，请重试";
            str2 = "忘记密码";
        } else if (i3 != 2) {
            if (i3 != 3) {
                return;
            }
            WeChatSimulationAgent.a(true, WeChatSimulationAgent.a(0, "pay success."));
            this.f17715a = h.ShowingPaySuccess;
            startActivity(new Intent(this, PaySuccessActivity.class));
            finish();
            return;
        } else {
            this.f17715a = h.ShowingTipDialog;
            str = WeChatSimulationAgent.f17753i;
            bVar = new b();
            aVar = new c();
            str2 = "取消";
        }
        a(str, str2, "重试", bVar, aVar);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        this.f17715a = h.ShowingTipDialog;
        a("是否放弃本次支付?", "继续支付", "放弃", new f(), new g());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.oasis_ui_wechat_simulation_activity_pay_main);
        overridePendingTransition(R.anim.oasis_ui_wechat_pay_dialog_push_bottom_in, R.anim.none);
        this.f17715a = h.ShowingPayMainActivity;
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
        }
        TextView textView = (TextView) findViewById(R.id.item_name);
        TextView textView2 = (TextView) findViewById(R.id.item_price);
        TextView textView3 = (TextView) findViewById(R.id.payee_name);
        ((ConstraintLayout) findViewById(R.id.main_layout)).setPadding(0, StatusBarUtils.getStatusBarHeight(getApplicationContext()), 0, 0);
        if (textView != null) {
            textView.setText(WeChatSimulationAgent.f17748d);
        }
        if (textView2 != null) {
            textView2.setText("￥" + String.format(Locale.getDefault(), "%.2f", WeChatSimulationAgent.f17750f));
        }
        if (textView3 != null) {
            textView3.setText(WeChatSimulationAgent.f17749e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Logger.d("PayMainActivity", "onDestroy: state == " + this.f17715a);
        h hVar = this.f17715a;
        if (hVar != h.UserExitManually && hVar != h.ShowingPassword && hVar != h.ShowingPaySuccess) {
            WeChatSimulationAgent.a(false, WeChatSimulationAgent.a(-4, "pay activity has been killed, pay failed!"));
        }
        super.onDestroy();
    }

    public void onExitClick(View view) {
        onBackPressed();
    }

    public void onPayNowClick(View view) {
        this.f17715a = h.ShowingPassword;
        a();
    }
}
