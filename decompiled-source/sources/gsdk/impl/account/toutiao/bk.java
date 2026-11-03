package gsdk.impl.account.toutiao;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import androidx.appcompat.widget.AppCompatTextView;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
/* loaded from: classes10.dex */
public class bk extends AppCompatTextView implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a  reason: collision with root package name */
    private boolean f19414a;

    public bk(Context context) {
        super(context);
        this.f19414a = true;
        a();
    }

    public bk(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f19414a = true;
        a();
    }

    public bk(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f19414a = true;
        a();
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z) {
        this.f19414a = z;
    }

    private void a() {
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private String a(AppCompatTextView appCompatTextView) {
        float width = (appCompatTextView.getWidth() - appCompatTextView.getPaddingLeft()) - appCompatTextView.getPaddingRight();
        if (width <= 0.0f) {
            return null;
        }
        String charSequence = appCompatTextView.getText().toString();
        StringBuilder sb = new StringBuilder();
        TextPaint paint = appCompatTextView.getPaint();
        int i2 = 0;
        int i3 = 0;
        while (i2 < charSequence.length()) {
            char charAt = charSequence.charAt(i2);
            if (i3 == i2 && charAt == ' ') {
                i3++;
            } else if (paint.measureText(String.valueOf(charSequence.substring(i3, i2 + 1))) <= width) {
                sb.append(charAt);
            } else {
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                i3 = i2;
                i2--;
            }
            i2++;
        }
        return sb.toString();
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
        String a2 = a(this);
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        setText(a2);
    }
}
