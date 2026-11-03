package gsdk.impl.share.facebook;

import com.facebook.CallbackManager;
import kotlin.Metadata;
/* compiled from: FBCallbackManager.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/module/share/FBCallbackManager;", "", "()V", "mCallbackManager", "Lcom/facebook/CallbackManager;", "getCallbackManager", "resetCallbackManager", "", "setCallbackManager", "callbackManager", "share_impl_facebook_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final a f20772a = new a();

    /* renamed from: b  reason: collision with root package name */
    private static CallbackManager f20773b;

    private a() {
    }

    public final void a(CallbackManager callbackManager) {
        f20773b = callbackManager;
    }

    public final CallbackManager a() {
        return f20773b;
    }

    public final void b() {
        f20773b = null;
    }
}
