package gsdk.impl.account.toutiao;

import androidx.lifecycle.LiveData;
/* loaded from: classes10.dex */
public class b extends LiveData {
    private b() {
        postValue(null);
    }

    public static <T> LiveData<T> a() {
        return new b();
    }
}
