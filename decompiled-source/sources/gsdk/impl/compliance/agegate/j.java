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
import com.bytedance.ttgame.module.compliance.impl.agegate.R;
import gsdk.impl.compliance.agegate.j;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AgeGateAgeConfirmDialog.kt */
/* loaded from: classes10.dex */
public final class j extends l {

    /* renamed from: a  reason: collision with root package name */
    public static final a f19881a = new a(null);

    /* compiled from: AgeGateAgeConfirmDialog.kt */
    /* loaded from: classes10.dex */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private a() {
        }

        public final j a(Activity activity, int i2, final Function2<? super DialogInterface, ? super Boolean, Unit> function2) {
            WindowManager.LayoutParams attributes;
            Intrinsics.checkNotNullParameter(activity, "activity");
            final j jVar = new j(activity, R.style.lifecycle_dialog);
            View inflate = LayoutInflater.from(jVar.getContext()).inflate(R.layout.dialog_age_gate_age_confirm, (ViewGroup) null);
            if (inflate == null) {
                return jVar;
            }
            Intrinsics.checkNotNullExpressionValue(inflate, "view ?: return this");
            Button button = (Button) inflate.findViewById(R.id.btn_over_age);
            if (button != null) {
                button.setText(jVar.getContext().getString(R.string.gsdk_account_age_gate_age_confirm_dialog_btn_over_age, String.valueOf(i2)));
            }
            Button button2 = (Button) inflate.findViewById(R.id.btn_below_age);
            if (button2 != null) {
                button2.setText(jVar.getContext().getString(R.string.gsdk_account_age_gate_age_confirm_dialog_btn_below_age, String.valueOf(i2)));
            }
            Button button3 = (Button) inflate.findViewById(R.id.btn_over_age);
            if (button3 != null) {
                button3.setOnClickListener(new View.OnClickListener() { // from class: gsdk.impl.compliance.agegate.j$a$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        j.a.a(Function2.this, jVar, view);
                    }
                });
            }
            Button button4 = (Button) inflate.findViewById(R.id.btn_below_age);
            if (button4 != null) {
                button4.setOnClickListener(new View.OnClickListener() { // from class: gsdk.impl.compliance.agegate.j$a$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        j.a.b(Function2.this, jVar, view);
                    }
                });
            }
            jVar.setContentView(inflate);
            Window window = jVar.getWindow();
            if (window != null && (attributes = window.getAttributes()) != null) {
                Intrinsics.checkNotNullExpressionValue(attributes, "attributes");
                attributes.width = -1;
                attributes.height = -1;
            }
            jVar.setCancelable(false);
            jVar.setCanceledOnTouchOutside(false);
            return jVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void a(Function2 function2, j this_apply, View view) {
            Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
            if (function2 != null) {
                function2.invoke(this_apply, true);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void b(Function2 function2, j this_apply, View view) {
            Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
            if (function2 != null) {
                function2.invoke(this_apply, false);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(Context context, int i2) {
        super(context, i2);
        Intrinsics.checkNotNullParameter(context, "context");
    }
}
