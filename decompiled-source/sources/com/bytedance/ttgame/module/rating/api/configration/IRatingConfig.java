package com.bytedance.ttgame.module.rating.api.configration;

import android.content.Context;
import kotlin.Metadata;
/* compiled from: IRatingConfig.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\tH&J\b\u0010\r\u001a\u00020\u000bH&¨\u0006\u000e"}, d2 = {"Lcom/bytedance/ttgame/module/rating/api/configration/IRatingConfig;", "", "defaultUIConfig", "Lcom/bytedance/ttgame/module/rating/api/configration/IRatingUIConfig;", "goToFeedback", "", "context", "Landroid/content/Context;", "feedbackUrl", "", "needShowDefaultDialog", "", "packageName", "serverJudge", "rating_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface IRatingConfig {
    IRatingUIConfig defaultUIConfig();

    void goToFeedback(Context context, String str);

    boolean needShowDefaultDialog();

    String packageName();

    boolean serverJudge();
}
