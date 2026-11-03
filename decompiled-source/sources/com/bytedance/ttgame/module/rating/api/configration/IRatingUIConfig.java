package com.bytedance.ttgame.module.rating.api.configration;

import kotlin.Metadata;
/* compiled from: IRatingUIConfig.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0004J\b\u0010\b\u001a\u00020\u0006H&J\u0006\u0010\t\u001a\u00020\u0004J\u0006\u0010\n\u001a\u00020\u0004J\u0006\u0010\u000b\u001a\u00020\u0004J\u0006\u0010\f\u001a\u00020\u0004J\b\u0010\r\u001a\u00020\u0006H&J\u0006\u0010\u000e\u001a\u00020\u0006J\u0006\u0010\u000f\u001a\u00020\u0004J\u0006\u0010\u0010\u001a\u00020\u0004J\b\u0010\u0011\u001a\u00020\u0006H&J\u0006\u0010\u0012\u001a\u00020\u0006J\u0006\u0010\u0013\u001a\u00020\u0004J\u0006\u0010\u0014\u001a\u00020\u0006J\u0006\u0010\u0015\u001a\u00020\u0004J\b\u0010\u0016\u001a\u00020\u0006H&¨\u0006\u0017"}, d2 = {"Lcom/bytedance/ttgame/module/rating/api/configration/IRatingUIConfig;", "", "()V", "getMainBackgroundResId", "", "getMainTitleStrColor", "", "getMainTitleStrSize", "getMainTitleString", "getMiddleImageResId", "getMiddleImageStyle2ResId", "getMiddleImageStyle3ResId", "getNegativeBtnResId", "getNegativeBtnText", "getNegativeBtnTextColor", "getNegativeBtnTextSize", "getPositiveBtnResId", "getPositiveBtnText", "getPositiveBtnTextColor", "getPositiveBtnTextSize", "getSecondTitleStrColor", "getSecondTitleStrSize", "getSecondTitleString", "rating_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class IRatingUIConfig {
    public final int getMainBackgroundResId() {
        return -1;
    }

    public final String getMainTitleStrColor() {
        return "";
    }

    public final int getMainTitleStrSize() {
        return 0;
    }

    public abstract String getMainTitleString();

    public final int getMiddleImageResId() {
        return -1;
    }

    public final int getMiddleImageStyle2ResId() {
        return 0;
    }

    public final int getMiddleImageStyle3ResId() {
        return 0;
    }

    public final int getNegativeBtnResId() {
        return 0;
    }

    public abstract String getNegativeBtnText();

    public final String getNegativeBtnTextColor() {
        return "";
    }

    public final int getNegativeBtnTextSize() {
        return 16;
    }

    public final int getPositiveBtnResId() {
        return -1;
    }

    public abstract String getPositiveBtnText();

    public final String getPositiveBtnTextColor() {
        return "";
    }

    public final int getPositiveBtnTextSize() {
        return 16;
    }

    public final String getSecondTitleStrColor() {
        return "";
    }

    public final int getSecondTitleStrSize() {
        return 15;
    }

    public abstract String getSecondTitleString();
}
