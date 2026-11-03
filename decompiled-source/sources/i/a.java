package i;

import android.app.Dialog;
import android.content.Context;
import com.oasis.android.R;
/* loaded from: classes10.dex */
public class a extends Dialog {
    public a(Context context) {
        this(context, R.style.OasisTheme_AlipaySimulation_Dialog_BackgroundDim);
    }

    public a(Context context, int i2) {
        super(context, i2);
        setContentView(R.layout.custom_alipay_progress_dialog_layout);
        getWindow().getAttributes().gravity = 17;
    }
}
