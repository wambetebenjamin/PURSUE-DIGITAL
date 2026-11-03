package com.ixigua.touchtileimageview.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
/* compiled from: PlaceHolderDrawable.java */
/* loaded from: classes9.dex */
public class i extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    private final Paint f17118a;

    /* renamed from: b  reason: collision with root package name */
    private int f17119b;

    /* renamed from: c  reason: collision with root package name */
    private RectF f17120c;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public i(int i2, RectF rectF) {
        Paint paint = new Paint();
        this.f17118a = paint;
        this.f17119b = 0;
        this.f17119b = i2;
        paint.setColor(i2);
        this.f17120c = rectF;
    }

    public void setColor(int i2) {
        this.f17119b = i2;
        this.f17118a.setColor(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.drawRect(this.f17120c, this.f17118a);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) (this.f17120c.width() + 0.5f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) (this.f17120c.height() + 0.5f);
    }
}
