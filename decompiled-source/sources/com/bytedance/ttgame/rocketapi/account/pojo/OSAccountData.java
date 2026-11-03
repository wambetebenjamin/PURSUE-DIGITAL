package com.bytedance.ttgame.rocketapi.account.pojo;

import com.bytedance.ttgame.base.GSDKError;
import java.util.List;
/* loaded from: classes7.dex */
public class OSAccountData {
    public GSDKError gsdkError;
    public List<OSAccountInfo> osAccountInfos;

    public String toString() {
        return "OSAccountData{gsdkError=" + this.gsdkError + ", osAccountInfos=" + this.osAccountInfos + '}';
    }
}
