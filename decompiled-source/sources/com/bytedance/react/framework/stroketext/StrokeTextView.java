package com.bytedance.react.framework.stroketext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.bytedance.react.framework.utils.notchutil.utils.ScreenUtil;
import com.facebook.react.views.text.ReactFontManager;
/* loaded from: classes2.dex */
public class StrokeTextView extends AppCompatTextView {
    private Paint mPaint;
    private int mShadowColor;
    private float mShadowOffsetX;
    private float mShadowOffsetY;
    private float mShadowRadius;
    private int mStrokeColor;
    private int mStrokeWidth;
    private int mTextColor;

    public StrokeTextView(Context context) {
        super(context);
        this.mStrokeWidth = 0;
        this.mShadowRadius = 0.0f;
        this.mShadowOffsetX = 0.0f;
        this.mShadowOffsetY = 0.0f;
        this.mShadowColor = 0;
        init(context);
    }

    public StrokeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mStrokeWidth = 0;
        this.mShadowRadius = 0.0f;
        this.mShadowOffsetX = 0.0f;
        this.mShadowOffsetY = 0.0f;
        this.mShadowColor = 0;
        init(context);
    }

    public StrokeTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mStrokeWidth = 0;
        this.mShadowRadius = 0.0f;
        this.mShadowOffsetX = 0.0f;
        this.mShadowOffsetY = 0.0f;
        this.mShadowColor = 0;
        init(context);
    }

    private void init(Context context) {
        TextPaint paint = getPaint();
        this.mPaint = paint;
        paint.setFlags(1);
    }

    public void setShadow(float f2, float f3, float f4, int i2) {
        this.mShadowRadius = f2;
        this.mShadowOffsetX = f3;
        this.mShadowOffsetY = f4;
        this.mShadowColor = i2;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        setTextColor(0);
        super.onDraw(canvas);
        Layout layout = getLayout();
        String charSequence = getText().toString();
        for (int i2 = 0; i2 < layout.getLineCount(); i2++) {
            String substring = charSequence.substring(layout.getLineStart(i2), layout.getLineEnd(i2));
            float lineLeft = layout.getLineLeft(i2);
            int lineBaseline = layout.getLineBaseline(i2);
            this.mPaint.setColor(this.mStrokeColor);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeJoin(Paint.Join.ROUND);
            this.mPaint.setStrokeWidth(this.mStrokeWidth);
            this.mPaint.setShadowLayer(this.mShadowRadius, this.mShadowOffsetX, this.mShadowOffsetY, this.mShadowColor);
            canvas.drawText(substring, getTotalPaddingLeft() + lineLeft + this.mStrokeWidth, getTotalPaddingTop() + lineBaseline, this.mPaint);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mTextColor);
            this.mPaint.clearShadowLayer();
            canvas.drawText(substring, lineLeft + getTotalPaddingLeft() + this.mStrokeWidth, lineBaseline + getTotalPaddingTop(), this.mPaint);
        }
    }

    public void setStrokeColor(int i2) {
        this.mStrokeColor = i2;
    }

    public void setStrokeWidth(int i2) {
        this.mStrokeWidth = i2;
    }

    public void setTextContent(String str) {
        setText(str);
    }

    public void setTextContentColor(int i2) {
        this.mTextColor = i2;
    }

    public void setTextSize(int i2) {
        setTextSize(2, i2 + ScreenUtil.px2dip(getContext(), this.mStrokeWidth));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0059, code lost:
        if (r9.equals("top") == false) goto L12;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setGravity(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            r1 = 2
            r2 = 0
            java.lang.String r3 = "center"
            r4 = -1
            r5 = 1
            if (r0 != 0) goto L3e
            r8.hashCode()
            int r0 = r8.hashCode()
            switch(r0) {
                case -1364013995: goto L2e;
                case 3317767: goto L23;
                case 108511772: goto L18;
                default: goto L16;
            }
        L16:
            r8 = r4
            goto L36
        L18:
            java.lang.String r0 = "right"
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L21
            goto L16
        L21:
            r8 = r1
            goto L36
        L23:
            java.lang.String r0 = "left"
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L2c
            goto L16
        L2c:
            r8 = r5
            goto L36
        L2e:
            boolean r8 = r8.equals(r3)
            if (r8 != 0) goto L35
            goto L16
        L35:
            r8 = r2
        L36:
            switch(r8) {
                case 0: goto L3e;
                case 1: goto L3c;
                case 2: goto L3a;
                default: goto L39;
            }
        L39:
            goto L3e
        L3a:
            r8 = 5
            goto L3f
        L3c:
            r8 = 3
            goto L3f
        L3e:
            r8 = r5
        L3f:
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            r6 = 16
            if (r0 != 0) goto L78
            r9.hashCode()
            int r0 = r9.hashCode()
            switch(r0) {
                case -1383228885: goto L65;
                case -1364013995: goto L5c;
                case 115029: goto L53;
                default: goto L51;
            }
        L51:
            r1 = r4
            goto L6f
        L53:
            java.lang.String r0 = "top"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L6f
            goto L51
        L5c:
            boolean r9 = r9.equals(r3)
            if (r9 != 0) goto L63
            goto L51
        L63:
            r1 = r5
            goto L6f
        L65:
            java.lang.String r0 = "bottom"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L6e
            goto L51
        L6e:
            r1 = r2
        L6f:
            switch(r1) {
                case 0: goto L76;
                case 1: goto L78;
                case 2: goto L73;
                default: goto L72;
            }
        L72:
            goto L78
        L73:
            r6 = 48
            goto L78
        L76:
            r6 = 80
        L78:
            r8 = r8 | r6
            r7.setGravity(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.react.framework.stroketext.StrokeTextView.setGravity(java.lang.String, java.lang.String):void");
    }

    public void setTextStyle(String str, String str2) {
        str2.hashCode();
        int i2 = 2;
        char c2 = 65535;
        switch (str2.hashCode()) {
            case -1178781136:
                if (str2.equals("italic")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1039745817:
                if (str2.equals("normal")) {
                    c2 = 1;
                    break;
                }
                break;
            case 3029637:
                if (str2.equals("bold")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                break;
            case 1:
            default:
                i2 = 0;
                break;
            case 2:
                i2 = 1;
                break;
        }
        this.mPaint.setTypeface(ReactFontManager.getInstance().getTypeface(str, i2, getContext().getAssets()));
    }

    public void setTextDecorationLine(String str) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1171789332:
                if (str.equals("line-through")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1026963764:
                if (str.equals("underline")) {
                    c2 = 1;
                    break;
                }
                break;
            case -586326336:
                if (str.equals("underline line-through")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                this.mPaint.setFlags(16);
                return;
            case 1:
                this.mPaint.setFlags(8);
                return;
            case 2:
                this.mPaint.setFlags(24);
                return;
            default:
                return;
        }
    }
}
