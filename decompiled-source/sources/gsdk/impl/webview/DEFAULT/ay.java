package gsdk.impl.webview.DEFAULT;

import android.app.Activity;
import com.bytedance.ttgame.module.webview.api.IExitWebViewCallback;
import java.util.Iterator;
import java.util.Stack;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: WebActivityManager.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/bytedance/ttgame/module/webview/base/WebActivityManager;", "", "()V", "activityStack", "Ljava/util/Stack;", "Landroid/app/Activity;", "addActivity", "", "activity", "clearActivity", "closeActivity", "getActivityCount", "", "removeActivity", "Companion", "webview_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ay {

    /* renamed from: a  reason: collision with root package name */
    public static final a f20924a = new a(null);

    /* renamed from: c  reason: collision with root package name */
    private static final Lazy<ay> f20925c = LazyKt.lazy(b.f20929a);

    /* renamed from: d  reason: collision with root package name */
    private static IExitWebViewCallback f20926d;

    /* renamed from: e  reason: collision with root package name */
    private static af f20927e;

    /* renamed from: b  reason: collision with root package name */
    private final Stack<Activity> f20928b;

    public /* synthetic */ ay(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ay() {
        this.f20928b = new Stack<>();
    }

    /* compiled from: WebActivityManager.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/module/webview/base/WebActivityManager$Companion;", "", "()V", "instance", "Lcom/bytedance/ttgame/module/webview/base/WebActivityManager;", "getInstance", "()Lcom/bytedance/ttgame/module/webview/base/WebActivityManager;", "instance$delegate", "Lkotlin/Lazy;", "mCloseCallback", "Lcom/bytedance/ttgame/module/webview/IWebAidlCallback;", "getMCloseCallback", "()Lcom/bytedance/ttgame/module/webview/IWebAidlCallback;", "setMCloseCallback", "(Lcom/bytedance/ttgame/module/webview/IWebAidlCallback;)V", "mExitCallback", "Lcom/bytedance/ttgame/module/webview/api/IExitWebViewCallback;", "getMExitCallback", "()Lcom/bytedance/ttgame/module/webview/api/IExitWebViewCallback;", "setMExitCallback", "(Lcom/bytedance/ttgame/module/webview/api/IExitWebViewCallback;)V", "webview_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private a() {
        }

        public final ay a() {
            return (ay) ay.f20925c.getValue();
        }

        public final void a(IExitWebViewCallback iExitWebViewCallback) {
            ay.f20926d = iExitWebViewCallback;
        }

        public final IExitWebViewCallback b() {
            return ay.f20926d;
        }

        public final void a(af afVar) {
            ay.f20927e = afVar;
        }

        public final af c() {
            return ay.f20927e;
        }
    }

    /* compiled from: WebActivityManager.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/bytedance/ttgame/module/webview/base/WebActivityManager;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes10.dex */
    static final class b extends Lambda implements Function0<ay> {

        /* renamed from: a  reason: collision with root package name */
        public static final b f20929a = new b();

        b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a */
        public final ay invoke() {
            return new ay(null);
        }
    }

    public final void a(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.f20928b.add(activity);
    }

    public final void b(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.f20928b.contains(activity) && activity.isFinishing()) {
            this.f20928b.remove(activity);
        }
    }

    public final void c(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (activity.isFinishing()) {
            return;
        }
        activity.finish();
    }

    public final int a() {
        return this.f20928b.size();
    }

    public final void b() {
        Iterator<Activity> it = this.f20928b.iterator();
        while (it.hasNext()) {
            Activity next = it.next();
            if (next != null && !next.isFinishing()) {
                next.finish();
            }
        }
    }
}
