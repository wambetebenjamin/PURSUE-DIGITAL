package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.rocketapi.account.pojo.AccountBindInfo;
import java.util.List;
/* loaded from: classes7.dex */
public class BindInfoResult {
    public List<AccountBindInfo> bindInfos;
    public GSDKError gsdkError;

    public String toString() {
        return "BindInfoResult{gsdkError=" + this.gsdkError + ", bindInfos=" + this.bindInfos + '}';
    }
}
