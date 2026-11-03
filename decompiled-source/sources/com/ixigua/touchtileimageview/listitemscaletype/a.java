package com.ixigua.touchtileimageview.listitemscaletype;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
/* compiled from: AIClipCropCenterListItemScaleType.java */
/* loaded from: classes9.dex */
public class a implements e {

    /* renamed from: a  reason: collision with root package name */
    private final Rect f17167a;

    /* renamed from: b  reason: collision with root package name */
    private final Rect f17168b;

    /* renamed from: c  reason: collision with root package name */
    private final Rect f17169c;

    /* renamed from: d  reason: collision with root package name */
    private final int f17170d;

    /* renamed from: e  reason: collision with root package name */
    private final int f17171e;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public a(int r11, int r12, int r13, int r14, android.graphics.Rect r15) {
        /*
            r10 = this;
            int r3 = r15.left
            float r3 = (float) r3
            float r4 = (float) r11
            float r5 = r3 / r4
            int r3 = r15.top
            float r3 = (float) r3
            float r6 = (float) r12
            float r7 = r3 / r6
            int r3 = r15.right
            int r3 = r11 - r3
            float r3 = (float) r3
            float r8 = r3 / r4
            int r0 = r15.bottom
            int r0 = r12 - r0
            float r0 = (float) r0
            float r9 = r0 / r6
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r6 = r7
            r7 = r8
            r8 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ixigua.touchtileimageview.listitemscaletype.a.<init>(int, int, int, int, android.graphics.Rect):void");
    }

    public a(int i2, int i3, int i4, int i5, float f2, float f3, float f4, float f5) {
        float f6;
        float f7;
        Rect rect = new Rect(0, 0, 0, 0);
        this.f17167a = rect;
        Rect rect2 = new Rect(0, 0, 0, 0);
        this.f17168b = rect2;
        Rect rect3 = new Rect(0, 0, 0, 0);
        this.f17169c = rect3;
        if (i2 < 0 || i3 < 0) {
            throw new IllegalArgumentException("originPictureWidth and originPictureHeight size must > 0.0");
        }
        if (i4 < 0 || i5 < 0) {
            throw new IllegalArgumentException("thumbnailPictureWidth and thumbnailPictureHeight size must > 0.0");
        }
        if (f2 < 0.0f || f3 < 0.0f || f4 < 0.0f || f5 < 0.0f) {
            throw new IllegalArgumentException("clipPercent must >= 0.0");
        }
        if (f2 >= 1.0f || f3 >= 1.0f || f4 >= 1.0f || f5 >= 1.0f) {
            throw new IllegalArgumentException("clipPercent must < 1.0");
        }
        if (f2 + f4 >= 1.0f) {
            throw new IllegalArgumentException("clipLeftPercent + clipRightPercent must < 1.0");
        }
        if (f3 + f5 >= 1.0f) {
            throw new IllegalArgumentException("clipTopPercent + clipBottomPercent must < 1.0");
        }
        float f8 = (1.0f - f2) - f4;
        float f9 = (1.0f - f3) - f5;
        rect3.set(0, 0, i2, i3);
        rect.set(0, 0, (int) Math.ceil(f8 * f6), (int) Math.ceil(f9 * f7));
        rect2.set(0, 0, i4, i5);
        this.f17170d = (int) (i2 * f2);
        this.f17171e = (int) (i3 * f3);
    }

    @Override // com.ixigua.touchtileimageview.listitemscaletype.e
    public Matrix getMatrix(RectF rectF, Rect rect) {
        Matrix matrix = new Matrix();
        float width = this.f17169c.width() / rectF.width();
        matrix.setScale(width, width, rectF.centerX(), rectF.centerY());
        matrix.postTranslate(this.f17169c.centerX() - rectF.centerX(), this.f17169c.centerY() - rectF.centerY());
        Matrix matrix2 = new Matrix();
        matrix2.postTranslate(-this.f17170d, -this.f17171e);
        Matrix matrix3 = new Matrix();
        float width2 = this.f17168b.width() / this.f17167a.width();
        matrix3.setScale(width2, width2, 0.0f, 0.0f);
        Matrix matrix4 = new Matrix();
        float min = 1.0f / Math.min(this.f17168b.width() / rect.width(), this.f17168b.height() / rect.height());
        matrix4.setScale(min, min, this.f17168b.centerX(), this.f17168b.centerY());
        matrix4.postTranslate(rect.centerX() - this.f17168b.centerX(), rect.centerY() - this.f17168b.centerY());
        Matrix matrix5 = new Matrix();
        matrix5.postConcat(matrix);
        matrix5.postConcat(matrix2);
        matrix5.postConcat(matrix3);
        matrix5.postConcat(matrix4);
        return matrix5;
    }
}
