package com.bytedance.ttgame.sdk.module.utils;

import android.util.Base64;
import com.bytedance.platform.godzilla.common.c;
import com.bytedance.sdk.account.utils.k;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import timber.log.Timber;
/* loaded from: classes8.dex */
public class AesCbcUtils {
    public static String encrypt(String str) {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, new SecretKeySpec(ConstManager.getConstValue(ConstManager.SKEY).getBytes(), "AES"), new IvParameterSpec(ConstManager.getConstValue(ConstManager.IVPARAMETER).getBytes()));
        return Base64.encodeToString(cipher.doFinal(str.getBytes(c.CHARSET_UTF_8)), 0);
    }

    public static String decrypt(String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(ConstManager.getConstValue(ConstManager.SKEY).getBytes(k.ASCII), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, new IvParameterSpec(ConstManager.getConstValue(ConstManager.IVPARAMETER).getBytes()));
            String str2 = new String(cipher.doFinal(Base64.decode(str, 0)), c.CHARSET_UTF_8);
            Timber.tag("gsdk_ase").i(str2, new Object[0]);
            return str2;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getClientCode() {
        return getRandomPwd(32);
    }

    public static String makeRandomPwd(int i2) {
        char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~;<@#:>%^".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(charArray[random.nextInt(charArray.length)]);
        }
        return sb.toString();
    }

    public static String getRandomPwd(int i2) {
        while (i2 == 32) {
            String makeRandomPwd = makeRandomPwd(i2);
            if (makeRandomPwd.matches(".*[a-z]{1,}.*") && makeRandomPwd.matches(".*[A-Z]{1,}.*") && makeRandomPwd.matches(".*\\d{1,}.*") && makeRandomPwd.matches(".*[~;<@#:>%^]{1,}.*")) {
                return makeRandomPwd;
            }
            makeRandomPwd(i2);
        }
        return "长度不得少于32位!";
    }
}
