package com.bytedance.ttgame.module.compliance.api.permission;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
/* loaded from: classes4.dex */
public interface IPermissionService extends IModuleApi {
    @InternalApi
    Boolean isAgreeApplistPermission();
}
