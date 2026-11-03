package gsdk.impl.webview.DEFAULT;

import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: KeyBoardManager.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J'\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001b\u001a\u00020\u001c¢\u0006\u0002\u0010\u001dJ\u0015\u0010\u001e\u001a\u00020\u00172\b\u0010\u001f\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000eJ\u0006\u0010 \u001a\u00020\u0017R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/bytedance/ttgame/module/webview/KeyBoardManager;", "", "()V", "mOnGlobalLayoutListener", "Lcom/bytedance/ttgame/module/webview/TTGlobalLayoutListener;", "getMOnGlobalLayoutListener", "()Lcom/bytedance/ttgame/module/webview/TTGlobalLayoutListener;", "setMOnGlobalLayoutListener", "(Lcom/bytedance/ttgame/module/webview/TTGlobalLayoutListener;)V", "mOrientation", "", "getMOrientation", "()Ljava/lang/Integer;", "setMOrientation", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "mTreeObserver", "Landroid/view/ViewTreeObserver;", "getMTreeObserver", "()Landroid/view/ViewTreeObserver;", "setMTreeObserver", "(Landroid/view/ViewTreeObserver;)V", "addKeyBordListener", "", "rootView", "Landroid/view/View;", "orientation", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/bytedance/ttgame/module/webview/IKeyBordListener;", "(Landroid/view/View;Ljava/lang/Integer;Lcom/bytedance/ttgame/module/webview/IKeyBordListener;)V", "onConfigurationChanged", "newOrientation", "release", "webview_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class aj {

    /* renamed from: a  reason: collision with root package name */
    private ViewTreeObserver f20869a;

    /* renamed from: b  reason: collision with root package name */
    private al f20870b;

    /* renamed from: c  reason: collision with root package name */
    private Integer f20871c = 0;

    public final ViewTreeObserver a() {
        return this.f20869a;
    }

    public final void a(ViewTreeObserver viewTreeObserver) {
        this.f20869a = viewTreeObserver;
    }

    public final void a(al alVar) {
        this.f20870b = alVar;
    }

    public final al b() {
        return this.f20870b;
    }

    public final void a(Integer num) {
        this.f20871c = num;
    }

    public final Integer c() {
        return this.f20871c;
    }

    public final void a(View view, Integer num, ae listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f20869a = view != null ? view.getViewTreeObserver() : null;
        this.f20871c = num;
        al alVar = new al(view, listener);
        this.f20870b = alVar;
        ViewTreeObserver viewTreeObserver = this.f20869a;
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(alVar);
        }
    }

    public final void b(Integer num) {
        if (Intrinsics.areEqual(num, this.f20871c)) {
            return;
        }
        al alVar = this.f20870b;
        if (alVar != null) {
            alVar.a(0);
        }
        this.f20871c = num;
    }

    public final void d() {
        al alVar = this.f20870b;
        if (alVar != null) {
            alVar.b();
        }
        this.f20870b = null;
        this.f20869a = null;
    }
}
