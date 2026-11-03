package gsdk.impl.account.toutiao;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import androidx.appcompat.widget.AppCompatRadioButton;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
/* loaded from: classes10.dex */
public class bj extends AppCompatRadioButton implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a  reason: collision with root package name */
    private boolean f19413a;

    public bj(Context context) {
        super(context);
        this.f19413a = true;
        a();
    }

    public bj(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f19413a = true;
        a();
    }

    public bj(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f19413a = true;
        a();
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z) {
        this.f19413a = z;
    }

    private void a() {
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private String a(AppCompatRadioButton appCompatRadioButton) {
        float width = (appCompatRadioButton.getWidth() - appCompatRadioButton.getPaddingLeft()) - appCompatRadioButton.getPaddingRight();
        if (width <= 0.0f) {
            return null;
        }
        String charSequence = appCompatRadioButton.getText().toString();
        StringBuilder sb = new StringBuilder();
        TextPaint paint = appCompatRadioButton.getPaint();
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
        if (getLineCount() > 1) {
            setGravity(2);
        }
    }
}
