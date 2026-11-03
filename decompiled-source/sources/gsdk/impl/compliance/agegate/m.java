package gsdk.impl.compliance.agegate;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import com.bytedance.ttgame.module.compliance.impl.agegate.R;
import gsdk.impl.compliance.agegate.m;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AgeGateDateSelectDialog.kt */
/* loaded from: classes10.dex */
public final class m extends l {

    /* renamed from: a  reason: collision with root package name */
    public static final a f19883a = new a(null);

    /* renamed from: b  reason: collision with root package name */
    private TextView f19884b;

    /* renamed from: c  reason: collision with root package name */
    private Button f19885c;

    /* renamed from: d  reason: collision with root package name */
    private long f19886d;

    /* compiled from: AgeGateDateSelectDialog.kt */
    /* loaded from: classes10.dex */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private a() {
        }

        public final m a(Activity activity, final Function1<? super DialogInterface, Unit> function1) {
            WindowManager.LayoutParams attributes;
            Intrinsics.checkNotNullParameter(activity, "activity");
            final m mVar = new m(activity, R.style.lifecycle_dialog);
            View inflate = LayoutInflater.from(mVar.getContext()).inflate(R.layout.dialog_age_gate_date_select, (ViewGroup) null);
            if (inflate == null) {
                return mVar;
            }
            Intrinsics.checkNotNullExpressionValue(inflate, "view ?: return this");
            Button button = (Button) inflate.findViewById(R.id.btn_continue);
            if (button != null) {
                button.setOnClickListener(new View.OnClickListener() { // from class: gsdk.impl.compliance.agegate.m$a$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        m.a.a(Function1.this, mVar, view);
                    }
                });
            }
            mVar.setContentView(inflate);
            Window window = mVar.getWindow();
            if (window != null && (attributes = window.getAttributes()) != null) {
                Intrinsics.checkNotNullExpressionValue(attributes, "attributes");
                attributes.width = -1;
                attributes.height = -1;
            }
            mVar.setCancelable(false);
            mVar.setCanceledOnTouchOutside(false);
            return mVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void a(Function1 function1, m this_apply, View view) {
            Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
            if (function1 != null) {
                function1.invoke(this_apply);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(Context context, int i2) {
        super(context, i2);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final long a() {
        return this.f19886d;
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        a((TextView) view.findViewById(R.id.tv_date_selector));
        a((Button) view.findViewById(R.id.btn_continue));
        super.setContentView(view);
    }

    private final void a(Button button) {
        this.f19885c = button;
        c();
    }

    private final void a(TextView textView) {
        if (textView == null) {
            return;
        }
        this.f19884b = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: gsdk.impl.compliance.agegate.m$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                m.a(m.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(m this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.b();
    }

    private final void b() {
        DatePickerDialog datePickerDialog;
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() { // from class: gsdk.impl.compliance.agegate.m$$ExternalSyntheticLambda0
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i2, int i3, int i4) {
                m.a(calendar, this, datePicker, i2, i3, i4);
            }
        };
        if (this.f19886d <= 0) {
            calendar.setTime(new Date());
            datePickerDialog = new DatePickerDialog(getContext(), 5, onDateSetListener, calendar.get(1), calendar.get(2), calendar.get(5));
        } else {
            calendar.setTime(new Date(this.f19886d));
            datePickerDialog = new DatePickerDialog(getContext(), 5, onDateSetListener, calendar.get(1), calendar.get(2), calendar.get(5));
        }
        Window window = datePickerDialog.getWindow();
        if (window != null) {
            window.clearFlags(2);
        }
        datePickerDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(Calendar calendar, m this$0, DatePicker datePicker, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        calendar.set(i2, i3, i4, 0, 0, 0);
        Date time = calendar.getTime();
        Intrinsics.checkNotNullExpressionValue(time, "calendar.time");
        this$0.a(time);
    }

    private final void a(Date date) {
        TextView textView = this.f19884b;
        if (textView != null) {
            textView.setText(new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(date));
        }
        TextView textView2 = this.f19884b;
        if (textView2 != null) {
            textView2.setTextColor(getContext().getResources().getColor(R.color.gsdk_base_color_222222));
        }
        Button button = this.f19885c;
        if (button != null) {
            button.setEnabled(true);
        }
        Button button2 = this.f19885c;
        if (button2 != null) {
            button2.setBackground(getContext().getResources().getDrawable(R.drawable.select_button_click));
        }
        Button button3 = this.f19885c;
        if (button3 != null) {
            button3.setTextColor(getContext().getResources().getColor(17170443));
        }
        this.f19886d = date.getTime();
    }

    private final void c() {
        TextView textView = this.f19884b;
        if (textView != null) {
            textView.setText(getContext().getString(R.string.gsdk_account_age_gate_date_select_dialog_date_hint));
        }
        TextView textView2 = this.f19884b;
        if (textView2 != null) {
            textView2.setTextColor(getContext().getResources().getColor(R.color.gsdk_base_color_666666));
        }
        Button button = this.f19885c;
        if (button != null) {
            button.setEnabled(false);
        }
        Button button2 = this.f19885c;
        if (button2 != null) {
            button2.setBackground(getContext().getResources().getDrawable(R.drawable.shape_enter_game_gray));
        }
        Button button3 = this.f19885c;
        if (button3 != null) {
            button3.setTextColor(getContext().getResources().getColor(R.color.gsdk_base_color_666666));
        }
    }
}
