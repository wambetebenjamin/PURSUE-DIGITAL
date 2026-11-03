package androidx.emoji2.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
/* loaded from: classes.dex */
public final class TypefaceEmojiSpan extends EmojiSpan {
    private static Paint sDebugPaint;

    public TypefaceEmojiSpan(EmojiMetadata emojiMetadata) {
        super(emojiMetadata);
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
        if (EmojiCompat.get().isEmojiSpanIndicatorEnabled()) {
            canvas.drawRect(f2, i4, f2 + getWidth(), i6, getDebugPaint());
        }
        getMetadata().draw(canvas, f2, i5, paint);
    }

    private static Paint getDebugPaint() {
        if (sDebugPaint == null) {
            TextPaint textPaint = new TextPaint();
            sDebugPaint = textPaint;
            textPaint.setColor(EmojiCompat.get().getEmojiSpanIndicatorColor());
            sDebugPaint.setStyle(Paint.Style.FILL);
        }
        return sDebugPaint;
    }
}
