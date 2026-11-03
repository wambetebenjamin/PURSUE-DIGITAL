package gsdk.impl.account.toutiao;

import androidx.lifecycle.LiveData;
import com.bytedance.ttgame.main.internal.net.Resource;
import com.bytedance.ttgame.module.account.toutiao.account.pojo.UserInfoResponse;
import com.bytedance.ttgame.module.database.api.UserInfoData;
import com.bytedance.ttgame.sdk.module.account.pojo.VisitorStatusResponse;
import java.util.HashMap;
/* loaded from: classes10.dex */
public interface ax {
    LiveData<Resource<VisitorStatusResponse>> a();

    LiveData<Resource<UserInfoResponse>> a(HashMap<String, Object> hashMap);

    LiveData<Resource<UserInfoResponse>> a(boolean z);

    void a(UserInfoData userInfoData);

    LiveData<Resource<UserInfoResponse>> b();

    LiveData<Resource<UserInfoResponse>> b(HashMap<String, Object> hashMap);
}
