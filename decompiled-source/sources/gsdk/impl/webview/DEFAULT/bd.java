package gsdk.impl.webview.DEFAULT;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.bytedance.ttgame.module.webview.R;
import timber.log.Timber;
/* compiled from: ImageLongPressDialog.java */
/* loaded from: classes10.dex */
public class bd extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20950a = "ImageLongPressDialog";

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f20951b;

    /* renamed from: c  reason: collision with root package name */
    private static volatile bd f20952c;

    /* renamed from: d  reason: collision with root package name */
    private View f20953d;

    /* renamed from: e  reason: collision with root package name */
    private a f20954e;

    /* renamed from: f  reason: collision with root package name */
    private String f20955f;

    /* compiled from: ImageLongPressDialog.java */
    /* loaded from: classes10.dex */
    public static abstract class a {
        public void a() {
        }

        public abstract void a(String str);

        public abstract void b(String str);
    }

    public bd(Context context, String str) {
        super(context);
        this.f20955f = str;
        a();
        b();
    }

    public void a(a aVar) {
        this.f20954e = aVar;
    }

    private void a() {
        requestWindowFeature(1);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.qr_remind_dialog, (ViewGroup) null);
        this.f20953d = inflate;
        setContentView(inflate);
        Window window = getWindow();
        window.setGravity(80);
        View decorView = window.getDecorView();
        decorView.setPadding(0, 0, 0, 0);
        window.setWindowAnimations(R.style.bottom_dialog_animation);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        attributes.horizontalMargin = 0.0f;
        window.setAttributes(attributes);
        decorView.setMinimumWidth(getContext().getResources().getDisplayMetrics().widthPixels);
        decorView.setBackgroundColor(0);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    private void b() {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: gsdk.impl.webview.DEFAULT.bd.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.getId() == R.id.tv_save_image && bd.this.f20954e != null) {
                    bd.this.f20954e.a(bd.this.f20955f);
                }
                bd.this.dismiss();
            }
        };
        this.f20953d.findViewById(R.id.tv_cancel).setOnClickListener(onClickListener);
        this.f20953d.findViewById(R.id.tv_save_image).setOnClickListener(onClickListener);
    }

    public static void a(Context context, String str, a aVar) {
        a(context, str, f20951b, aVar);
    }

    public static synchronized void a(Context context, String str, boolean z, a aVar) {
        synchronized (bd.class) {
            if (context == null) {
                Timber.tag("gsdk_showdialog").e(f20950a, "[showDialog] context is null");
                return;
            }
            if (f20952c != null && f20952c.isShowing()) {
                Timber.tag("gsdk_showdialog").w(f20950a, "[showDialog] dialog is showing");
                f20952c.dismiss();
            }
            f20952c = new bd(context, str);
            f20952c.a(aVar);
            f20952c.show();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        try {
            try {
                super.dismiss();
                a aVar = this.f20954e;
                if (aVar != null) {
                    aVar.a();
                }
            } catch (Exception e2) {
                Timber.tag("gsdk_showdialog").w(f20950a, "[dismiss] " + e2.toString());
            }
        } finally {
            f20951b = false;
            f20952c = null;
        }
    }
}
