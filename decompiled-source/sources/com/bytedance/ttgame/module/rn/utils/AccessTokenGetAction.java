package com.bytedance.ttgame.module.rn.utils;

import com.bytedance.react.framework.core.BRNManager;
import com.bytedance.ttgame.module.rn.utils.PreFetchParamsReplace;
/* compiled from: PreFetchParamsReplace.java */
/* loaded from: classes6.dex */
class AccessTokenGetAction implements PreFetchParamsReplace.GetParamValueAction {
    @Override // com.bytedance.ttgame.module.rn.utils.PreFetchParamsReplace.GetParamValueAction
    public String getValue() {
        return BRNManager.newInstance().getAccessToken();
    }
}
