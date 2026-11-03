package i;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import com.oasis.android.R;
/* loaded from: classes10.dex */
public class b extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    private final Handler f21093a;

    public b(Context context) {
        this(context, R.style.OasisTheme_AlipaySimulation_Dialog);
    }

    public b(Context context, int i2) {
        super(context, i2);
        this.f21093a = new Handler(Looper.getMainLooper());
        setContentView(R.layout.custom_alipay_waiting_dialog_layout);
        getWindow().getAttributes().gravity = 17;
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat((ImageView) findViewById(R.id.wechat_waiting_dot_0), "translationY", 0.0f, -10.0f, 6.0f, 0.0f);
        ofFloat.setDuration(750L);
        ofFloat.setRepeatCount(-1);
        ofFloat.start();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((ImageView) findViewById(R.id.wechat_waiting_dot_1), "translationY", 0.0f, -10.0f, 6.0f, 0.0f);
        ofFloat2.setDuration(750L);
        ofFloat2.setRepeatCount(-1);
        ofFloat2.setStartDelay(250L);
        ofFloat2.start();
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat((ImageView) findViewById(R.id.wechat_waiting_dot_2), "translationY", 0.0f, -10.0f, 6.0f, 0.0f);
        ofFloat3.setDuration(750L);
        ofFloat3.setRepeatCount(-1);
        ofFloat3.setStartDelay(500L);
        ofFloat3.start();
    }
}
