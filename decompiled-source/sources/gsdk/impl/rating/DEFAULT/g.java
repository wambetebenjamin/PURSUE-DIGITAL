package gsdk.impl.rating.DEFAULT;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.ttgame.module.rating.api.IRatingViewCallback;
import com.bytedance.ttgame.module.rating.impl.R;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DefaultRatingDialog.kt */
/* loaded from: classes10.dex */
public final class g extends Dialog implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private IRatingViewCallback f20698a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(Context context) {
        super(context, R.style.gsdk_rating_Dialog);
        Intrinsics.checkNotNullParameter(context, "context");
        setContentView(R.layout.gsdk_rating_default_dialog);
        setCanceledOnTouchOutside(false);
        a();
    }

    public final void a(IRatingViewCallback iRatingViewCallback) {
        this.f20698a = iRatingViewCallback;
    }

    private final void a() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        ((ImageView) findViewById(R.id.icon_app)).setImageDrawable(b(context));
        Context context2 = getContext();
        int i2 = R.string.gsdk_rating_content;
        Context context3 = getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "context");
        ((TextView) findViewById(R.id.dialog_title)).setText(context2.getString(i2, a(context3)));
        g gVar = this;
        ((Button) findViewById(R.id.cancel)).setOnClickListener(gVar);
        ((Button) findViewById(R.id.submit)).setOnClickListener(gVar);
    }

    private final String a(Context context) {
        try {
            PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
            Resources resources = context.getResources();
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            Intrinsics.checkNotNull(applicationInfo);
            String string = resources.getString(applicationInfo.labelRes);
            Intrinsics.checkNotNullExpressionValue(string, "{\n            val packag…nfo!!.labelRes)\n        }");
            return string;
        } catch (Exception unused) {
            return "";
        }
    }

    private final Drawable b(Context context) {
        try {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "packageManager.getApplic…o(context.packageName, 0)");
            if (packageManager != null) {
                return packageManager.getApplicationIcon(applicationInfo);
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        IRatingViewCallback iRatingViewCallback = this.f20698a;
        if (iRatingViewCallback != null) {
            iRatingViewCallback.onShow();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        IRatingViewCallback iRatingViewCallback = this.f20698a;
        if (iRatingViewCallback != null) {
            iRatingViewCallback.onDismiss();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (Intrinsics.areEqual(view, (Button) findViewById(R.id.cancel))) {
            dismiss();
            IRatingViewCallback iRatingViewCallback = this.f20698a;
            if (iRatingViewCallback != null) {
                iRatingViewCallback.onCancelBtnClick();
            }
        } else if (Intrinsics.areEqual(view, (Button) findViewById(R.id.submit))) {
            IRatingViewCallback iRatingViewCallback2 = this.f20698a;
            if (iRatingViewCallback2 != null) {
                iRatingViewCallback2.onSubmitBtnClick();
            }
            dismiss();
        }
    }

    public final void a(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        ((TextView) findViewById(R.id.dialog_title)).setText(title);
    }
}
