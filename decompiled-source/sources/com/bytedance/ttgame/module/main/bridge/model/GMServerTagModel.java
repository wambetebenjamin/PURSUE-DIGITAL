package com.bytedance.ttgame.module.main.bridge.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMGameInfoModels.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\nJ&\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/module/main/bridge/model/GMServerTagModel;", "", "tagName", "", "tagValue", "", "(Ljava/lang/String;Ljava/lang/Integer;)V", "getTagName", "()Ljava/lang/String;", "getTagValue", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Integer;)Lcom/bytedance/ttgame/module/main/bridge/model/GMServerTagModel;", "equals", "", "other", "hashCode", "toString", "main_bridge_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class GMServerTagModel {
    private final String tagName;
    private final Integer tagValue;

    public static /* synthetic */ GMServerTagModel copy$default(GMServerTagModel gMServerTagModel, String str, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = gMServerTagModel.tagName;
        }
        if ((i2 & 2) != 0) {
            num = gMServerTagModel.tagValue;
        }
        return gMServerTagModel.copy(str, num);
    }

    public final String component1() {
        return this.tagName;
    }

    public final Integer component2() {
        return this.tagValue;
    }

    public final GMServerTagModel copy(String str, Integer num) {
        return new GMServerTagModel(str, num);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GMServerTagModel) {
            GMServerTagModel gMServerTagModel = (GMServerTagModel) obj;
            return Intrinsics.areEqual(this.tagName, gMServerTagModel.tagName) && Intrinsics.areEqual(this.tagValue, gMServerTagModel.tagValue);
        }
        return false;
    }

    public int hashCode() {
        String str = this.tagName;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.tagValue;
        return hashCode + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "GMServerTagModel(tagName=" + this.tagName + ", tagValue=" + this.tagValue + ')';
    }

    public GMServerTagModel(String str, Integer num) {
        this.tagName = str;
        this.tagValue = num;
    }

    public final String getTagName() {
        return this.tagName;
    }

    public final Integer getTagValue() {
        return this.tagValue;
    }
}
