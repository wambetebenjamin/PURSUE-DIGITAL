package com.ss.android.agilelogger.formatter.stacktrace;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
/* compiled from: DefaultStackTraceFormatter.java */
/* loaded from: classes9.dex */
public class a implements b {
    @Override // com.ss.android.agilelogger.formatter.b
    public String format(StackTraceElement[] stackTraceElementArr) {
        StringBuilder sb = new StringBuilder(256);
        if (stackTraceElementArr == null || stackTraceElementArr.length == 0) {
            return null;
        }
        if (stackTraceElementArr.length == 1) {
            return "\t─ " + stackTraceElementArr[0].toString();
        }
        int length = stackTraceElementArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 == 0) {
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            if (i2 != length - 1) {
                sb.append("\t├ ");
                sb.append(stackTraceElementArr[i2].toString());
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            } else {
                sb.append("\t└ ");
                sb.append(stackTraceElementArr[i2].toString());
            }
        }
        return sb.toString();
    }
}
