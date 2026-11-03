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
import gsdk.impl.compliance.agegate.n;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AgeGateNetworkErrorDialog.kt */
/* loaded from: classes10.dex */
public final class n extends l {

    /* renamed from: a  reason: collision with root package name */
    public static final a f19887a = new a(null);

    /* compiled from: AgeGateNetworkErrorDialog.kt */
    /* loaded from: classes10.dex */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private a() {
        }

        public final n a(Activity activity, final Function1<? super DialogInterface, Unit> function1) {
            WindowManager.LayoutParams attributes;
            Intrinsics.checkNotNullParameter(activity, "activity");
            Activity activity2 = activity;
            final n nVar = new n(activity2, R.style.lifecycle_dialog);
            View inflate = LayoutInflater.from(activity2).inflate(R.layout.dialog_age_gate_network_error, (ViewGroup) null);
            if (inflate == null) {
                return nVar;
            }
            Intrinsics.checkNotNullExpressionValue(inflate, "view ?: return this");
            Button button = (Button) inflate.findViewById(R.id.btn_try_again);
            if (button != null) {
                button.setOnClickListener(new View.OnClickListener() { // from class: gsdk.impl.compliance.agegate.n$a$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        n.a.a(Function1.this, nVar, view);
                    }
                });
            }
            nVar.setContentView(inflate);
            Window window = nVar.getWindow();
            if (window != null && (attributes = window.getAttributes()) != null) {
                Intrinsics.checkNotNullExpressionValue(attributes, "attributes");
                attributes.width = -1;
                attributes.height = -1;
            }
            nVar.setCancelable(false);
            nVar.setCanceledOnTouchOutside(false);
            return nVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void a(Function1 function1, n this_apply, View view) {
            Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
            if (function1 != null) {
                function1.invoke(this_apply);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(Context context, int i2) {
        super(context, i2);
        Intrinsics.checkNotNullParameter(context, "context");
    }
}
