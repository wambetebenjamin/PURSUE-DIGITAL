package gsdk.impl.account.toutiao;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.bytedance.ttgame.module.account.api.LoginLogger;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes10.dex */
public class dg<T> extends MediatorLiveData<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f19547a = "gsdk_account_mediator_single_live_event";

    /* renamed from: b  reason: collision with root package name */
    private final AtomicBoolean f19548b = new AtomicBoolean(false);

    @Override // androidx.lifecycle.LiveData
    public void observe(LifecycleOwner lifecycleOwner, final Observer<? super T> observer) {
        if (hasActiveObservers()) {
            LoginLogger.w(f19547a, "Multiple observers registered but only one will be notified of changes.");
        }
        super.observe(lifecycleOwner, new Observer<T>() { // from class: gsdk.impl.account.toutiao.dg.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(T t) {
                if (dg.this.f19548b.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    public void setValue(T t) {
        this.f19548b.set(true);
        super.setValue(t);
    }

    public void a() {
        setValue(null);
    }
}
