package gsdk.impl.webview.DEFAULT;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import kotlin.Metadata;
/* compiled from: KeyBoardManager.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0006\u0010\u0014\u001a\u00020\u0013R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/module/webview/TTGlobalLayoutListener;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "mRootView", "Landroid/view/View;", "mListener", "Lcom/bytedance/ttgame/module/webview/IKeyBordListener;", "(Landroid/view/View;Lcom/bytedance/ttgame/module/webview/IKeyBordListener;)V", "SOFT_KEY_BOARD_MIN_HEIGHT", "", "mIsInpuUp", "", "mLastVisibleHeight", "getMLastVisibleHeight", "()I", "setMLastVisibleHeight", "(I)V", "mScreenRect", "Landroid/graphics/Rect;", "onGlobalLayout", "", "release", "webview_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class al implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a  reason: collision with root package name */
    private View f20876a;

    /* renamed from: b  reason: collision with root package name */
    private ae f20877b;

    /* renamed from: d  reason: collision with root package name */
    private int f20879d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f20880e;

    /* renamed from: c  reason: collision with root package name */
    private final int f20878c = 200;

    /* renamed from: f  reason: collision with root package name */
    private Rect f20881f = new Rect();

    public al(View view, ae aeVar) {
        this.f20876a = view;
        this.f20877b = aeVar;
    }

    public final int a() {
        return this.f20879d;
    }

    public final void a(int i2) {
        this.f20879d = i2;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        Rect rect = new Rect();
        View view = this.f20876a;
        if (view != null) {
            view.getWindowVisibleDisplayFrame(rect);
        }
        if (this.f20881f.height() == 0 || this.f20881f.width() == 0) {
            this.f20881f.right = rect.right;
            this.f20881f.bottom = rect.bottom;
        }
        int height = rect.height();
        if (this.f20879d == 0) {
            this.f20879d = height;
        }
        if (this.f20880e || !(height == this.f20881f.height() || height == this.f20881f.width())) {
            if (Math.abs(this.f20879d - height) >= this.f20878c) {
                if (this.f20879d > height) {
                    this.f20880e = true;
                    ae aeVar = this.f20877b;
                    if (aeVar != null) {
                        aeVar.a();
                    }
                } else {
                    this.f20880e = false;
                    ae aeVar2 = this.f20877b;
                    if (aeVar2 != null) {
                        aeVar2.b();
                    }
                }
            }
            this.f20879d = height;
        }
    }

    public final void b() {
        this.f20876a = null;
        this.f20877b = null;
    }
}
