package gsdk.impl.account.toutiao;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
/* loaded from: classes10.dex */
public class db extends AppCompatButton {
    public db(Context context) {
        super(context);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a();
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
    }

    private void a() {
        TextPaint paint = getPaint();
        paint.setTypeface(getTypeface());
        paint.setTextSize(getTextSize());
        if (getPaddingLeft() + getPaddingRight() + paint.measureText(getText().toString()) > getWidth()) {
            setTextSize(0, getTextSize() - 0.5f);
            a();
        }
    }
}
