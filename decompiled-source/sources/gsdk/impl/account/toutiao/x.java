package gsdk.impl.account.toutiao;

import androidx.lifecycle.LiveData;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.main.internal.net.Resource;
import com.bytedance.ttgame.module.account.toutiao.account.pojo.UserInfoResponse;
import java.util.HashMap;
/* loaded from: classes10.dex */
public interface x {
    LiveData<Resource<UserInfoResponse>> a(HashMap<String, Object> hashMap);

    void a(HashMap<String, Object> hashMap, ICallback<UserInfoResponse> iCallback);

    LiveData<Resource<UserInfoResponse>> b(HashMap<String, Object> hashMap);

    void b(HashMap<String, Object> hashMap, ICallback<UserInfoResponse> iCallback);

    void c(HashMap<String, Object> hashMap, ICallback<cj> iCallback);

    void d(HashMap<String, Object> hashMap, ICallback<GSDKError> iCallback);
}
