package gsdk.impl.account.toutiao;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
/* loaded from: classes10.dex */
public class dm {
    public static <X, Y> dg<Y> a(LiveData<X> liveData, final Function<X, LiveData<Y>> function) {
        final dg<Y> dgVar = new dg<>();
        dgVar.addSource(liveData, (Observer<X>) new Observer<X>() { // from class: gsdk.impl.account.toutiao.dm.1

            /* renamed from: a  reason: collision with root package name */
            LiveData<Y> f19586a;

            @Override // androidx.lifecycle.Observer
            public void onChanged(X x) {
                LiveData<Y> liveData2 = (LiveData) Function.this.apply(x);
                Object obj = this.f19586a;
                if (obj == liveData2) {
                    return;
                }
                if (obj != null) {
                    dgVar.removeSource(obj);
                }
                this.f19586a = liveData2;
                if (liveData2 != 0) {
                    dgVar.addSource(liveData2, new Observer<Y>() { // from class: gsdk.impl.account.toutiao.dm.1.1
                        @Override // androidx.lifecycle.Observer
                        public void onChanged(Y y) {
                            dgVar.setValue(y);
                        }
                    });
                }
            }
        });
        return dgVar;
    }
}
