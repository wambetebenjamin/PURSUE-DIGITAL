package com.ss.android.agilelogger.formatter.border;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
/* compiled from: DefaultBorderFormatter.java */
/* loaded from: classes9.dex */
public class b implements a {

    /* renamed from: a  reason: collision with root package name */
    private static final char f18001a = 9553;

    /* renamed from: b  reason: collision with root package name */
    private static final String f18002b = "╔═══════════════════════════════════════════════════════════════════════════════════════════════════";

    /* renamed from: c  reason: collision with root package name */
    private static final String f18003c = "╟───────────────────────────────────────────────────────────────────────────────────────────────────";

    /* renamed from: d  reason: collision with root package name */
    private static final String f18004d = "╚═══════════════════════════════════════════════════════════════════════════════════════════════════";

    @Override // com.ss.android.agilelogger.formatter.b
    public String format(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        String[] strArr2 = new String[strArr.length];
        int i2 = 0;
        for (String str : strArr) {
            if (str != null) {
                strArr2[i2] = str;
                i2++;
            }
        }
        if (i2 == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(f18002b).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(a(strArr2[i3]));
            if (i3 != i2 - 1) {
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append(f18003c).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            } else {
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append(f18004d);
            }
        }
        return sb.toString();
    }

    private static String a(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 10);
        String[] split = str.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        int length = split.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != 0) {
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            sb.append(f18001a).append(split[i2]);
        }
        return sb.toString();
    }
}
