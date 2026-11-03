package gsdk.impl.compliance.agegate;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.bytedance.ttgame.module.compliance.impl.agegate.R;
import gsdk.impl.compliance.agegate.k;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AgeGateAgeNotSatisfyDialog.kt */
/* loaded from: classes10.dex */
public final class k extends l {

    /* renamed from: a  reason: collision with root package name */
    public static final a f19882a = new a(null);

    /* compiled from: AgeGateAgeNotSatisfyDialog.kt */
    /* loaded from: classes10.dex */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private a() {
        }

        public final k a(Activity activity, int i2, final boolean z, final Function2<? super DialogInterface, ? super Boolean, Unit> function2) {
            WindowManager.LayoutParams attributes;
            Button button;
            Intrinsics.checkNotNullParameter(activity, "activity");
            final k kVar = new k(activity, R.style.lifecycle_dialog);
            View inflate = LayoutInflater.from(kVar.getContext()).inflate(R.layout.dialog_age_gate_age_not_satisfy, (ViewGroup) null);
            if (inflate == null) {
                return kVar;
            }
            Intrinsics.checkNotNullExpressionValue(inflate, "view ?: return this");
            TextView textView = (TextView) inflate.findViewById(R.id.tv_content);
            if (textView != null) {
                textView.setText(kVar.getContext().getString(R.string.gsdk_account_age_gate_age_not_satisfy_content));
            }
            if (z && (button = (Button) inflate.findViewById(R.id.btn_exit)) != null) {
                button.setText(kVar.getContext().getString(R.string.gsdk_account_age_gate_age_not_satisfy_btn_content_enter_again));
            }
            Button button2 = (Button) inflate.findViewById(R.id.btn_exit);
            if (button2 != null) {
                button2.setOnClickListener(new View.OnClickListener() { // from class: gsdk.impl.compliance.agegate.k$a$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        k.a.a(Function2.this, kVar, z, view);
                    }
                });
            }
            kVar.setContentView(inflate);
            Window window = kVar.getWindow();
            if (window != null && (attributes = window.getAttributes()) != null) {
                Intrinsics.checkNotNullExpressionValue(attributes, "attributes");
                attributes.width = -1;
                attributes.height = -1;
            }
            kVar.setCancelable(false);
            kVar.setCanceledOnTouchOutside(false);
            return kVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void a(Function2 function2, k this_apply, boolean z, View view) {
            Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
            if (function2 != null) {
                function2.invoke(this_apply, Boolean.valueOf(z));
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(Context context, int i2) {
        super(context, i2);
        Intrinsics.checkNotNullParameter(context, "context");
    }
}
