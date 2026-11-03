package i;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import com.oasis.android.R;
/* loaded from: classes10.dex */
public class c extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    private final ImageView[] f21094a;

    /* renamed from: b  reason: collision with root package name */
    private int f21095b;

    /* renamed from: c  reason: collision with root package name */
    private final int[] f21096c;

    /* renamed from: d  reason: collision with root package name */
    private final int[][] f21097d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f21098e;

    /* renamed from: f  reason: collision with root package name */
    private final Handler f21099f;

    /* renamed from: g  reason: collision with root package name */
    private final Runnable f21100g;

    /* loaded from: classes10.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar = c.this;
            if (cVar.f21098e) {
                ImageView[] imageViewArr = cVar.f21094a;
                if (imageViewArr[0] != null && imageViewArr[1] != null && imageViewArr[2] != null) {
                    cVar.f21095b = (cVar.f21095b + 1) % 4;
                    for (int i2 = 0; i2 < 3; i2++) {
                        c cVar2 = c.this;
                        cVar2.f21094a[i2].setImageResource(cVar2.f21096c[cVar2.f21097d[cVar2.f21095b][i2]]);
                    }
                }
            }
            c.this.f21099f.postDelayed(this, 200L);
        }
    }

    public c(Context context) {
        this(context, R.style.OasisTheme_AlipaySimulation_Dialog);
    }

    public c(Context context, int i2) {
        super(context, i2);
        this.f21094a = new ImageView[3];
        this.f21095b = 0;
        this.f21096c = new int[]{R.drawable.oasis_ui_alipay_qmf_circle_dot_0, R.drawable.oasis_ui_alipay_qmf_circle_dot_1, R.drawable.oasis_ui_alipay_qmf_circle_dot_2};
        this.f21097d = new int[][]{new int[]{0, 1, 2}, new int[]{1, 0, 1}, new int[]{2, 1, 0}, new int[]{1, 2, 1}};
        this.f21098e = false;
        this.f21099f = new Handler(Looper.getMainLooper());
        this.f21100g = new a();
        setContentView(R.layout.custom_wechat_waiting_dialog_layout);
        getWindow().getAttributes().gravity = 17;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        this.f21098e = false;
        this.f21099f.removeCallbacks(this.f21100g);
    }

    @Override // android.app.Dialog
    public void hide() {
        super.hide();
        this.f21098e = false;
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        this.f21098e = true;
        this.f21094a[0] = (ImageView) findViewById(R.id.wechat_waiting_dot_0);
        this.f21094a[1] = (ImageView) findViewById(R.id.wechat_waiting_dot_1);
        this.f21094a[2] = (ImageView) findViewById(R.id.wechat_waiting_dot_2);
        this.f21094a[0].setImageResource(this.f21096c[0]);
        this.f21094a[1].setImageResource(this.f21096c[1]);
        this.f21094a[2].setImageResource(this.f21096c[2]);
        this.f21095b = 0;
        this.f21099f.postDelayed(this.f21100g, 200L);
    }
}
