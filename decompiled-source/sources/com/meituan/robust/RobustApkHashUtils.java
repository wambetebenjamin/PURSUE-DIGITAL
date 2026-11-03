package com.meituan.robust;

import android.content.Context;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/* loaded from: classes9.dex */
public class RobustApkHashUtils {
    private static String robustApkHashValue;

    public static String readRobustApkHash(Context context) {
        if (TextUtils.isEmpty(robustApkHashValue)) {
            robustApkHashValue = readRobustApkHashFile(context);
        }
        return robustApkHashValue;
    }

    private static String readRobustApkHashFile(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return readFirstLine(context, Constants.ROBUST_APK_HASH_FILE_NAME);
        } catch (Throwable unused) {
            return "";
        }
    }

    private static String readFirstLine(Context context, String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
        } catch (IOException unused) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            String readLine = bufferedReader.readLine();
            try {
                bufferedReader.close();
            } catch (IOException unused2) {
            }
            return readLine;
        } catch (IOException unused3) {
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException unused4) {
                }
            }
            return "";
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException unused5) {
                }
            }
            throw th;
        }
    }
}
