package com.ixigua.touchtileimageview;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TouchBaseImageView.java */
/* loaded from: classes9.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private final Paint f17062a;

    /* renamed from: b  reason: collision with root package name */
    private final List<c> f17063b;

    /* renamed from: c  reason: collision with root package name */
    private com.ixigua.touchtileimageview.drawable.d<Drawable> f17064c;

    /* renamed from: d  reason: collision with root package name */
    private i f17065d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f17066e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f17067f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f17068g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f17069h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d() {
        Paint paint = new Paint();
        this.f17062a = paint;
        this.f17063b = new ArrayList();
        this.f17066e = false;
        this.f17067f = false;
        this.f17068g = false;
        this.f17069h = false;
        paint.setColor(0);
    }

    public void setDebug(boolean z) {
        this.f17066e = z;
        i iVar = this.f17065d;
        if (iVar != null) {
            iVar.f17163a.getWrapDrawable().setDebug(this.f17066e);
        }
    }

    public void setUseInBitmap(boolean z) {
        this.f17067f = z;
        i iVar = this.f17065d;
        if (iVar != null) {
            iVar.f17163a.getWrapDrawable().setUseInBitmap(this.f17067f);
        }
    }

    public void setUseLruCache(boolean z) {
        this.f17068g = z;
        i iVar = this.f17065d;
        if (iVar != null) {
            iVar.f17163a.getWrapDrawable().setUseLruCache(this.f17068g);
        }
    }

    public void setUsePrefetch(boolean z) {
        this.f17069h = z;
        i iVar = this.f17065d;
        if (iVar != null) {
            iVar.f17163a.getWrapDrawable().setUsePrefetch(this.f17069h);
        }
    }

    public boolean isUseInBitmap() {
        return this.f17067f;
    }

    public boolean isUseLruCache() {
        return this.f17068g;
    }

    public boolean isUsePrefetch() {
        return this.f17069h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(c cVar) {
        this.f17063b.add(cVar);
        Collections.sort(this.f17063b, new Comparator<c>() { // from class: com.ixigua.touchtileimageview.d.1
            @Override // java.util.Comparator
            public int compare(c cVar2, c cVar3) {
                float scaleValue = com.ixigua.touchtileimageview.utils.h.scaleValue(cVar2.f17016b);
                float scaleValue2 = com.ixigua.touchtileimageview.utils.h.scaleValue(cVar3.f17016b);
                if (com.ixigua.touchtileimageview.utils.d.isExactlyEqual(scaleValue, scaleValue2)) {
                    return 0;
                }
                return com.ixigua.touchtileimageview.utils.d.isExactlyGreaterThan(scaleValue, scaleValue2) ? 1 : -1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Drawable drawable) {
        c cVar;
        int i2 = 0;
        while (true) {
            if (i2 >= this.f17063b.size()) {
                cVar = null;
                break;
            }
            cVar = this.f17063b.get(i2);
            if (cVar.f17015a.getWrapDrawable() == drawable) {
                break;
            }
            i2++;
        }
        if (cVar != null) {
            this.f17063b.remove(cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(com.ixigua.touchtileimageview.drawable.d<Drawable> dVar) {
        this.f17064c = dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(i iVar) {
        i iVar2 = this.f17065d;
        if (iVar2 != null) {
            iVar2.a();
            this.f17065d = null;
        }
        this.f17065d = iVar;
        com.ixigua.touchtileimageview.drawable.c wrapDrawable = iVar.f17163a.getWrapDrawable();
        wrapDrawable.setDebug(this.f17066e);
        wrapDrawable.setUseInBitmap(this.f17067f);
        wrapDrawable.setUseLruCache(this.f17068g);
        wrapDrawable.setUsePrefetch(this.f17069h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Drawable a() {
        i iVar = this.f17065d;
        if (iVar == null) {
            return null;
        }
        return iVar.f17163a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(float f2, float f3, float f4, float f5, boolean z, float f6) {
        com.ixigua.touchtileimageview.drawable.d<Drawable> dVar = this.f17064c;
        if (dVar != null) {
            dVar.setClipPercent(f2, f3, f4, f5);
            if (z) {
                this.f17064c.setRadiusPercent(f6);
            }
        }
        for (int i2 = 0; i2 < this.f17063b.size(); i2++) {
            com.ixigua.touchtileimageview.drawable.d<Drawable> dVar2 = this.f17063b.get(i2).f17015a;
            dVar2.setClipPercent(f2, f3, f4, f5);
            if (z) {
                dVar2.setRadiusPercent(f6);
            }
        }
        i iVar = this.f17065d;
        if (iVar != null) {
            iVar.f17163a.setClipPercent(f2, f3, f4, f5);
            if (z) {
                this.f17065d.f17163a.setRadiusPercent(f6);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Canvas canvas, RectF rectF, RectF rectF2, RectF rectF3, Matrix matrix) {
        canvas.save();
        canvas.concat(matrix);
        if (this.f17066e) {
            canvas.drawRect(rectF, this.f17062a);
        }
        com.ixigua.touchtileimageview.drawable.d<Drawable> dVar = this.f17064c;
        if (dVar != null) {
            dVar.setBounds(0, 0, dVar.getIntrinsicWidth(), this.f17064c.getIntrinsicHeight());
            this.f17064c.draw(canvas);
        }
        for (int size = this.f17063b.size() - 1; size >= 0; size--) {
            c cVar = this.f17063b.get(size);
            canvas.save();
            canvas.concat(cVar.f17016b);
            cVar.f17015a.setBounds(0, 0, cVar.f17015a.getIntrinsicWidth(), cVar.f17015a.getIntrinsicHeight());
            cVar.f17015a.draw(canvas);
            canvas.restore();
        }
        if (this.f17065d != null) {
            canvas.save();
            canvas.concat(this.f17065d.f17164b);
            com.ixigua.touchtileimageview.drawable.d<com.ixigua.touchtileimageview.drawable.c> dVar2 = this.f17065d.f17163a;
            dVar2.setBounds(0, 0, dVar2.getIntrinsicWidth(), dVar2.getIntrinsicHeight());
            dVar2.getWrapDrawable().updateVisibleAreaIfNeeded(this.f17065d.f17164b, matrix, rectF2, rectF3);
            dVar2.draw(canvas);
            canvas.restore();
        }
        canvas.restore();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        i iVar = this.f17065d;
        if (iVar != null) {
            iVar.f17163a.getWrapDrawable().prepareToDisappear();
        }
    }

    public List<Drawable> getImageDrawables() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.f17063b.size(); i2++) {
            arrayList.add(this.f17063b.get(i2).f17015a.getWrapDrawable());
        }
        return arrayList;
    }

    public List<Float> getImageDrawablesScaleValues() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.f17063b.size(); i2++) {
            arrayList.add(Float.valueOf(com.ixigua.touchtileimageview.utils.h.scaleValue(this.f17063b.get(i2).f17016b)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Matrix c() {
        i iVar = this.f17065d;
        if (iVar != null) {
            return iVar.f17164b;
        }
        if (this.f17063b.size() > 0) {
            return this.f17063b.get(0).f17016b;
        }
        return new Matrix();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(Drawable drawable) {
        for (int i2 = 0; i2 < this.f17063b.size(); i2++) {
            if (drawable == this.f17063b.get(i2).f17015a || drawable == this.f17063b.get(i2).f17015a.getWrapDrawable()) {
                return true;
            }
        }
        com.ixigua.touchtileimageview.drawable.d<Drawable> dVar = this.f17064c;
        if (dVar == null || !(dVar == drawable || dVar.getWrapDrawable() == drawable)) {
            i iVar = this.f17065d;
            return iVar != null && (iVar.f17163a == drawable || this.f17065d.f17163a.getWrapDrawable() == drawable);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        this.f17064c = null;
        e();
        this.f17063b.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        i iVar = this.f17065d;
        if (iVar != null) {
            iVar.a();
            this.f17065d = null;
        }
    }
}
