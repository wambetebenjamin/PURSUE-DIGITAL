package com.bytedance.ug.sdk.share.impl.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.share_ui.R;
import java.util.Locale;
/* loaded from: classes8.dex */
public class DownloadProgressView extends LinearLayout {
    private Path mCirclePath;
    private RectF mCircleRect;
    private float mCircleStrokeWidth;
    private int mHeight;
    private Paint mPaint;
    private int mProgress;
    private TextView mProgressTv;
    private int mWidth;

    public DownloadProgressView(Context context) {
        this(context, null);
    }

    public DownloadProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DownloadProgressView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }

    private void init() {
        this.mProgress = 0;
        this.mPaint = new Paint(1);
        int rgb = Color.rgb(255, 255, 255);
        this.mPaint.setColor(rgb);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mCircleStrokeWidth = TypedValue.applyDimension(1, 2.0f, getContext().getResources().getDisplayMetrics());
        this.mPaint.setTypeface(Typeface.DEFAULT);
        this.mCirclePath = new Path();
        TextView textView = new TextView(getContext());
        this.mProgressTv = textView;
        textView.setId(R.id.share_download_progress_view_text);
        this.mProgressTv.setBackgroundColor(0);
        this.mProgressTv.setGravity(17);
        this.mProgressTv.setTextColor(rgb);
        this.mProgressTv.setTextSize(1, 10.0f);
        addView(this.mProgressTv, new LinearLayout.LayoutParams(-1, -1));
        this.mProgressTv.setText(String.format(Locale.getDefault(), "%d%%", Integer.valueOf(this.mProgress)));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        if (this.mCircleRect == null) {
            float f2 = this.mCircleStrokeWidth;
            this.mCircleRect = new RectF(f2 / 2.0f, f2 / 2.0f, this.mWidth - (f2 / 2.0f), this.mHeight - (f2 / 2.0f));
        }
        if (this.mCircleRect.width() == this.mWidth && this.mCircleRect.height() == this.mHeight) {
            return;
        }
        RectF rectF = this.mCircleRect;
        float f3 = this.mCircleStrokeWidth;
        rectF.set(f3 / 2.0f, f3 / 2.0f, this.mWidth - (f3 / 2.0f), this.mHeight - (f3 / 2.0f));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.rotate(-90.0f, this.mWidth >> 1, this.mHeight >> 1);
        this.mCirclePath.reset();
        this.mCirclePath.arcTo(this.mCircleRect, 0.0f, (this.mProgress * 360) / 100.0f);
        this.mPaint.setStrokeWidth(this.mCircleStrokeWidth);
        this.mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(this.mCirclePath, this.mPaint);
        this.mPaint.setStrokeWidth(this.mCircleStrokeWidth / 2.0f);
        this.mPaint.setStyle(Paint.Style.FILL);
        float f2 = this.mCircleStrokeWidth;
        canvas.drawCircle(this.mWidth - (f2 / 2.0f), this.mHeight / 2, f2 / 2.0f, this.mPaint);
        double cos = Math.cos((this.mProgress * 3.141592653589793d) / 50.0d);
        int i2 = this.mWidth;
        double sin = Math.sin((this.mProgress * 3.141592653589793d) / 50.0d);
        int i3 = this.mHeight;
        float f3 = this.mCircleStrokeWidth;
        canvas.drawCircle((float) ((cos * ((i2 / 2) - (this.mCircleStrokeWidth / 2.0f))) + (i2 / 2)), (float) ((sin * ((i3 / 2) - (f3 / 2.0f))) + (i3 / 2)), f3 / 2.0f, this.mPaint);
        canvas.restore();
    }

    public void setProgress(int i2) {
        if (this.mProgress == i2) {
            return;
        }
        this.mProgress = i2;
        this.mProgressTv.setText(String.format(Locale.getDefault(), "%d%%", Integer.valueOf(this.mProgress)));
        invalidate();
    }
}
