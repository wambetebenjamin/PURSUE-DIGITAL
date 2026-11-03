package com.bytedance.ttgame.module.share.api;

import android.app.Activity;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import com.bytedance.ttgame.module.share.api.entity.TTShareModel;
import kotlin.Metadata;
/* compiled from: IShareAction.kt */
@InternalApi
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bg\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/module/share/api/IShareAction;", "", "shareContentBehind", "", "activity", "Landroid/app/Activity;", "shareModel", "Lcom/bytedance/ttgame/module/share/api/entity/TTShareModel;", "withAnchor", "", "share_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface IShareAction {
    void shareContentBehind(Activity activity, TTShareModel tTShareModel, boolean z);
}
