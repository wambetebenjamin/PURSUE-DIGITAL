package gsdk.impl.account.toutiao;

import android.text.TextPaint;
import android.view.ViewTreeObserver;
import android.widget.TextView;
/* loaded from: classes10.dex */
public class br {

    /* loaded from: classes10.dex */
    public interface a {
        void a(boolean z);
    }

    public static void a(final TextView textView, final a aVar) {
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: gsdk.impl.account.toutiao.br$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                br.b(textView, aVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(TextView textView, a aVar) {
        TextPaint paint = textView.getPaint();
        paint.setTextSize(textView.getTextSize());
        if (((int) paint.measureText(textView.getText().toString().trim())) > (textView.getWidth() - textView.getPaddingLeft()) - textView.getPaddingRight()) {
            aVar.a(false);
        } else {
            aVar.a(true);
        }
    }
}
