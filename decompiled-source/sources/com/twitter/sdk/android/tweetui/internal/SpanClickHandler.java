package com.twitter.sdk.android.tweetui.internal;

import android.text.Layout;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
/* loaded from: classes10.dex */
public class SpanClickHandler {
    private HighlightedClickableSpan highlightedClickableSpan;
    private Layout layout;
    private float left;
    private float top;
    private final View view;

    public static void enableClicksOnSpans(TextView textView) {
        final SpanClickHandler spanClickHandler = new SpanClickHandler(textView, null);
        textView.setOnTouchListener(new View.OnTouchListener() { // from class: com.twitter.sdk.android.tweetui.internal.SpanClickHandler$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return SpanClickHandler.lambda$enableClicksOnSpans$0(SpanClickHandler.this, view, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$enableClicksOnSpans$0(SpanClickHandler spanClickHandler, View view, MotionEvent motionEvent) {
        TextView textView = (TextView) view;
        Layout layout = textView.getLayout();
        if (layout != null) {
            spanClickHandler.layout = layout;
            spanClickHandler.left = textView.getTotalPaddingLeft() + textView.getScrollX();
            spanClickHandler.top = textView.getTotalPaddingTop() + textView.getScrollY();
            return spanClickHandler.handleTouchEvent(motionEvent);
        }
        return false;
    }

    public SpanClickHandler(View view, Layout layout) {
        this.view = view;
        this.layout = layout;
    }

    public boolean handleTouchEvent(MotionEvent motionEvent) {
        HighlightedClickableSpan highlightedClickableSpan;
        CharSequence text = this.layout.getText();
        Spanned spanned = text instanceof Spanned ? (Spanned) text : null;
        if (spanned == null) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        int x = (int) (motionEvent.getX() - this.left);
        int y = (int) (motionEvent.getY() - this.top);
        if (x < 0 || x >= this.layout.getWidth() || y < 0 || y >= this.layout.getHeight()) {
            deselectSpan();
            return false;
        }
        int lineForVertical = this.layout.getLineForVertical(y);
        float f2 = x;
        if (f2 < this.layout.getLineLeft(lineForVertical) || f2 > this.layout.getLineRight(lineForVertical)) {
            deselectSpan();
            return false;
        }
        if (action == 0) {
            int offsetForHorizontal = this.layout.getOffsetForHorizontal(lineForVertical, f2);
            HighlightedClickableSpan[] highlightedClickableSpanArr = (HighlightedClickableSpan[]) spanned.getSpans(offsetForHorizontal, offsetForHorizontal, HighlightedClickableSpan.class);
            if (highlightedClickableSpanArr.length > 0) {
                selectSpan(highlightedClickableSpanArr[0]);
                return true;
            }
        } else if (action == 1 && (highlightedClickableSpan = this.highlightedClickableSpan) != null) {
            highlightedClickableSpan.onClick(this.view);
            deselectSpan();
            return true;
        }
        return false;
    }

    private void selectSpan(HighlightedClickableSpan highlightedClickableSpan) {
        highlightedClickableSpan.select(true);
        this.highlightedClickableSpan = highlightedClickableSpan;
        invalidate();
    }

    private void deselectSpan() {
        HighlightedClickableSpan highlightedClickableSpan = this.highlightedClickableSpan;
        if (highlightedClickableSpan == null || !highlightedClickableSpan.isSelected()) {
            return;
        }
        highlightedClickableSpan.select(false);
        this.highlightedClickableSpan = null;
        invalidate();
    }

    private void invalidate() {
        View view = this.view;
        float f2 = this.left;
        view.invalidate((int) f2, (int) this.top, ((int) f2) + this.layout.getWidth(), ((int) this.top) + this.layout.getHeight());
    }
}
