package com.bytedance.apm6.memory;

import android.os.Process;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* compiled from: Tools.java */
/* loaded from: classes.dex */
class f {
    f() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long a() {
        try {
            String[] split = a(String.format("/proc/%s/status", Integer.valueOf(Process.myPid()))).trim().split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            for (String str : split) {
                if (str.startsWith("VmSize")) {
                    Matcher matcher = Pattern.compile("\\d+").matcher(str);
                    if (matcher.find()) {
                        return Long.parseLong(matcher.group());
                    }
                }
            }
            if (split.length > 12) {
                Matcher matcher2 = Pattern.compile("\\d+").matcher(split[12]);
                if (matcher2.find()) {
                    return Long.parseLong(matcher2.group());
                }
            }
        } catch (Exception unused) {
        }
        return -1L;
    }

    private static String a(String str) {
        FileInputStream fileInputStream;
        Throwable th;
        try {
            fileInputStream = new FileInputStream(new File(str));
            try {
                String a2 = a(fileInputStream);
                fileInputStream.close();
                return a2;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
        }
    }

    private static String a(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        sb.append(readLine).append('\n');
                    } else {
                        bufferedReader2.close();
                        return sb.toString();
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
