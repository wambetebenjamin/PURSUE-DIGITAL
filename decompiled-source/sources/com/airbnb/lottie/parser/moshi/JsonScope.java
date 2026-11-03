package com.airbnb.lottie.parser.moshi;

import kotlin.text.Typography;
/* loaded from: classes.dex */
final class JsonScope {
    static final int CLOSED = 8;
    static final int DANGLING_NAME = 4;
    static final int EMPTY_ARRAY = 1;
    static final int EMPTY_DOCUMENT = 6;
    static final int EMPTY_OBJECT = 3;
    static final int NONEMPTY_ARRAY = 2;
    static final int NONEMPTY_DOCUMENT = 7;
    static final int NONEMPTY_OBJECT = 5;

    private JsonScope() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getPath(int i2, int[] iArr, String[] strArr, int[] iArr2) {
        StringBuilder append = new StringBuilder().append(Typography.dollar);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = iArr[i3];
            if (i4 == 1 || i4 == 2) {
                append.append('[').append(iArr2[i3]).append(']');
            } else if (i4 == 3 || i4 == 4 || i4 == 5) {
                append.append('.');
                if (strArr[i3] != null) {
                    append.append(strArr[i3]);
                }
            }
        }
        return append.toString();
    }
}
