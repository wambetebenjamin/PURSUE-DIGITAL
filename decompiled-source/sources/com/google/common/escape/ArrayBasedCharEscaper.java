package com.google.common.escape;

import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.CheckForNull;
import kotlin.jvm.internal.CharCompanionObject;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public abstract class ArrayBasedCharEscaper extends CharEscaper {
    private final char[][] replacements;
    private final int replacementsLength;
    private final char safeMax;
    private final char safeMin;

    @CheckForNull
    protected abstract char[] escapeUnsafe(char c2);

    /* JADX INFO: Access modifiers changed from: protected */
    public ArrayBasedCharEscaper(Map<Character, String> map, char c2, char c3) {
        this(ArrayBasedEscaperMap.create(map), c2, c3);
    }

    protected ArrayBasedCharEscaper(ArrayBasedEscaperMap arrayBasedEscaperMap, char c2, char c3) {
        Preconditions.checkNotNull(arrayBasedEscaperMap);
        char[][] replacementArray = arrayBasedEscaperMap.getReplacementArray();
        this.replacements = replacementArray;
        this.replacementsLength = replacementArray.length;
        if (c3 < c2) {
            c3 = 0;
            c2 = CharCompanionObject.MAX_VALUE;
        }
        this.safeMin = c2;
        this.safeMax = c3;
    }

    @Override // com.google.common.escape.CharEscaper, com.google.common.escape.Escaper
    public final String escape(String str) {
        Preconditions.checkNotNull(str);
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if ((charAt < this.replacementsLength && this.replacements[charAt] != null) || charAt > this.safeMax || charAt < this.safeMin) {
                return escapeSlow(str, i2);
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.escape.CharEscaper
    @CheckForNull
    public final char[] escape(char c2) {
        char[] cArr;
        if (c2 >= this.replacementsLength || (cArr = this.replacements[c2]) == null) {
            if (c2 < this.safeMin || c2 > this.safeMax) {
                return escapeUnsafe(c2);
            }
            return null;
        }
        return cArr;
    }
}
