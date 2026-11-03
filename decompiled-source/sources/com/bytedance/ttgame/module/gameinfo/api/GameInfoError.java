package com.bytedance.ttgame.module.gameinfo.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Models.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/api/GameInfoError;", "", "code", "", "message", "", "(Ljava/lang/Integer;Ljava/lang/String;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/String;)Lcom/bytedance/ttgame/module/gameinfo/api/GameInfoError;", "equals", "", "other", "hashCode", "toString", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class GameInfoError {
    private final Integer code;
    private final String message;

    public static /* synthetic */ GameInfoError copy$default(GameInfoError gameInfoError, Integer num, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = gameInfoError.code;
        }
        if ((i2 & 2) != 0) {
            str = gameInfoError.message;
        }
        return gameInfoError.copy(num, str);
    }

    public final Integer component1() {
        return this.code;
    }

    public final String component2() {
        return this.message;
    }

    public final GameInfoError copy(Integer num, String str) {
        return new GameInfoError(num, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GameInfoError) {
            GameInfoError gameInfoError = (GameInfoError) obj;
            return Intrinsics.areEqual(this.code, gameInfoError.code) && Intrinsics.areEqual(this.message, gameInfoError.message);
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.code;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.message;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "GameInfoError(code=" + this.code + ", message=" + this.message + ')';
    }

    public GameInfoError(Integer num, String str) {
        this.code = num;
        this.message = str;
    }

    public final Integer getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }
}
