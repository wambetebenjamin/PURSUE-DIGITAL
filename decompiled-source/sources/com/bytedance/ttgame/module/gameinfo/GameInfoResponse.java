package com.bytedance.ttgame.module.gameinfo;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Request.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B#\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\bJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\rJ8\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u0000HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0004HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001R\u0015\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/GameInfoResponse;", ExifInterface.GPS_DIRECTION_TRUE, "", "code", "", "message", "", "data", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Object;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getMessage", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Object;)Lcom/bytedance/ttgame/module/gameinfo/GameInfoResponse;", "equals", "", "other", "hashCode", "toString", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class GameInfoResponse<T> {
    private final Integer code;
    private final T data;
    private final String message;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GameInfoResponse copy$default(GameInfoResponse gameInfoResponse, Integer num, String str, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            num = gameInfoResponse.code;
        }
        if ((i2 & 2) != 0) {
            str = gameInfoResponse.message;
        }
        if ((i2 & 4) != 0) {
            obj = gameInfoResponse.data;
        }
        return gameInfoResponse.copy(num, str, obj);
    }

    public final Integer component1() {
        return this.code;
    }

    public final String component2() {
        return this.message;
    }

    public final T component3() {
        return this.data;
    }

    public final GameInfoResponse<T> copy(Integer num, String str, T t) {
        return new GameInfoResponse<>(num, str, t);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GameInfoResponse) {
            GameInfoResponse gameInfoResponse = (GameInfoResponse) obj;
            return Intrinsics.areEqual(this.code, gameInfoResponse.code) && Intrinsics.areEqual(this.message, gameInfoResponse.message) && Intrinsics.areEqual(this.data, gameInfoResponse.data);
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.code;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.message;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        T t = this.data;
        return hashCode2 + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        return "GameInfoResponse(code=" + this.code + ", message=" + this.message + ", data=" + this.data + ')';
    }

    public GameInfoResponse(Integer num, String str, T t) {
        this.code = num;
        this.message = str;
        this.data = t;
    }

    public final Integer getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public final T getData() {
        return this.data;
    }
}
