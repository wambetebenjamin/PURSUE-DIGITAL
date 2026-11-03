package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Region;
import android.view.MotionEvent;
import java.util.HashMap;
/* compiled from: WheelCurvedPicker.java */
/* loaded from: classes10.dex */
public class bc extends bb {
    private final HashMap<Integer, Integer> S;
    private final HashMap<Integer, Integer> T;
    private final Camera U;
    private final Matrix V;
    private final Matrix W;
    private int aa;
    private int ab;
    private int ac;
    private int ad;

    public bc(Context context) {
        super(context);
        this.S = new HashMap<>();
        this.T = new HashMap<>();
        this.U = new Camera();
        this.V = new Matrix();
        this.W = new Matrix();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gsdk.impl.dynamic.DEFAULT.as
    public void c() {
        super.c();
        this.aa = this.H.a(this.m, this.o, this.s, this.t);
        this.M = (int) (180.0f / (this.m + 1));
        this.u = this.H.a(this.aa, this.s, this.t);
        this.v = this.H.b(this.aa, this.s, this.t);
        this.Q = -90;
        this.R = 90;
        this.O = (-this.M) * ((this.f20116j.size() - this.n) - 1);
        this.P = this.M * this.n;
    }

    @Override // gsdk.impl.dynamic.DEFAULT.as
    protected void b(Canvas canvas) {
        for (int i2 = -this.n; i2 < this.f20116j.size() - this.n; i2++) {
            int i3 = (this.M * i2) + this.N + this.ab;
            if (i3 <= this.R && i3 >= this.Q) {
                int b2 = b(i3);
                if (b2 == 0) {
                    i3 = 1;
                }
                int c2 = c(i3);
                this.U.save();
                this.H.a(this.U, i3);
                this.U.getMatrix(this.V);
                this.U.restore();
                this.H.a(this.V, b2, this.w, this.x);
                this.U.save();
                this.U.translate(0.0f, 0.0f, c2);
                this.U.getMatrix(this.W);
                this.U.restore();
                this.H.a(this.W, b2, this.w, this.x);
                this.V.postConcat(this.W);
                canvas.save();
                canvas.concat(this.V);
                canvas.clipRect(this.f20123J, Region.Op.DIFFERENCE);
                this.f20109c.setColor(this.q);
                this.f20109c.setAlpha(255 - ((Math.abs(i3) * 255) / this.R));
                this.H.a(canvas, this.f20109c, this.f20116j.get(this.n + i2), b2, this.w, this.y);
                canvas.restore();
                canvas.save();
                canvas.clipRect(this.f20123J);
                this.f20109c.setColor(this.r);
                this.H.a(canvas, this.f20109c, this.f20116j.get(this.n + i2), b2, this.w, this.y);
                canvas.restore();
            }
        }
    }

    private int b(int i2) {
        if (this.S.containsKey(Integer.valueOf(i2))) {
            return this.S.get(Integer.valueOf(i2)).intValue();
        }
        int sin = (int) (Math.sin(Math.toRadians(i2)) * this.aa);
        this.S.put(Integer.valueOf(i2), Integer.valueOf(sin));
        return sin;
    }

    private int c(int i2) {
        if (this.T.containsKey(Integer.valueOf(i2))) {
            return this.T.get(Integer.valueOf(i2)).intValue();
        }
        int cos = (int) (this.aa - (Math.cos(Math.toRadians(i2)) * this.aa));
        this.T.put(Integer.valueOf(i2), Integer.valueOf(cos));
        return cos;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gsdk.impl.dynamic.DEFAULT.bb, gsdk.impl.dynamic.DEFAULT.as
    public void b(MotionEvent motionEvent) {
        this.ad = this.H.c(this.B, this.C, this.aa);
        int a2 = this.H.a(this.B, this.C);
        if (Math.abs(a2) >= this.aa) {
            if (a2 >= 0) {
                this.ac++;
            } else {
                this.ac--;
            }
            this.B = 0;
            this.C = 0;
            this.ad = 0;
        }
        this.ab = (this.ac * 80) + this.ad;
        super.b(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gsdk.impl.dynamic.DEFAULT.bb, gsdk.impl.dynamic.DEFAULT.as
    public void c(MotionEvent motionEvent) {
        this.N += this.ab;
        this.ab = 0;
        this.ad = 0;
        this.ac = 0;
        super.c(motionEvent);
    }

    @Override // gsdk.impl.dynamic.DEFAULT.bb
    public void e() {
        this.S.clear();
        this.T.clear();
        this.H.a();
    }
}
