package com.bytedance.ttgame.rocketapi.seamless.pojo;

import com.bytedance.ttgame.base.GSDKError;
import java.util.List;
/* loaded from: classes7.dex */
public class SeamlessLoginResult {
    public GSDKError gsdkError;
    public List<SeamlessLoginInfo> seamlessInfos;

    public String toString() {
        return "SeamlessLoginResult{gsdkError=" + this.gsdkError + ", seamlessInfos=" + this.seamlessInfos + '}';
    }
}
