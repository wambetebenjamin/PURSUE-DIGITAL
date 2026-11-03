package gsdk.impl.account.toutiao;

import com.bytedance.ttgame.base.BaseResponse;
import com.bytedance.ttgame.rocketapi.account.internal.SuccessionCodeData;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes10.dex */
public class cr extends BaseResponse implements Serializable {
    @SerializedName("data")

    /* renamed from: a  reason: collision with root package name */
    public SuccessionCodeData f19492a;

    public boolean a() {
        return this.code == -30001 || this.code == -30002;
    }
}
