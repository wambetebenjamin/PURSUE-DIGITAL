package gsdk.impl.account.toutiao;

import androidx.lifecycle.LiveData;
import com.bytedance.ttgame.main.internal.net.Resource;
import com.bytedance.ttgame.module.account.toutiao.account.pojo.UserInfoResponse;
import com.bytedance.ttgame.module.database.api.UserInfoData;
/* loaded from: classes10.dex */
public interface ay {
    LiveData<Resource<UserInfoResponse>> a(UserInfoData userInfoData);

    LiveData<Resource<UserInfoResponse>> b(UserInfoData userInfoData);
}
