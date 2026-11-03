package com.meituan.robust;

import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
/* loaded from: classes9.dex */
public class PatchProxy {
    public static PatchProxyResult proxy(Object[] objArr, Object obj, ChangeQuickRedirect changeQuickRedirect, boolean z, String str) {
        if (isSupport(objArr, obj, changeQuickRedirect, z, str, null, null)) {
            PatchProxyResult patchProxyResult = new PatchProxyResult();
            patchProxyResult.isSupported = true;
            patchProxyResult.result = accessDispatch(objArr, obj, changeQuickRedirect, z, str, null, null);
            return patchProxyResult;
        }
        return null;
    }

    public static boolean isSupport(Object[] objArr, Object obj, ChangeQuickRedirect changeQuickRedirect, boolean z, String str, Class[] clsArr, Class cls) {
        if (changeQuickRedirect == null) {
            return false;
        }
        String classMethod = getClassMethod(z, str);
        if (TextUtils.isEmpty(classMethod)) {
            return false;
        }
        try {
            return changeQuickRedirect.isSupport(classMethod, getObjects(objArr, obj, z));
        } catch (Throwable unused) {
            return false;
        }
    }

    public static Object accessDispatch(Object[] objArr, Object obj, ChangeQuickRedirect changeQuickRedirect, boolean z, String str, Class[] clsArr, Class cls) {
        if (changeQuickRedirect == null) {
            return null;
        }
        String classMethod = getClassMethod(z, str);
        if (TextUtils.isEmpty(classMethod)) {
            return null;
        }
        return changeQuickRedirect.accessDispatch(classMethod, getObjects(objArr, obj, z));
    }

    public static void accessDispatchVoid(Object[] objArr, Object obj, ChangeQuickRedirect changeQuickRedirect, boolean z, String str, Class[] clsArr, Class cls) {
        if (changeQuickRedirect == null) {
            return;
        }
        String classMethod = getClassMethod(z, str);
        if (TextUtils.isEmpty(classMethod)) {
            return;
        }
        changeQuickRedirect.accessDispatch(classMethod, getObjects(objArr, obj, z));
    }

    private static Object[] getObjects(Object[] objArr, Object obj, boolean z) {
        Object[] objArr2;
        if (objArr == null) {
            return null;
        }
        int length = objArr.length;
        if (z) {
            objArr2 = new Object[length];
        } else {
            objArr2 = new Object[length + 1];
        }
        int i2 = 0;
        while (i2 < length) {
            objArr2[i2] = objArr[i2];
            i2++;
        }
        if (!z) {
            objArr2[i2] = obj;
        }
        return objArr2;
    }

    private static String getClassMethod(boolean z, String str) {
        try {
            return "" + CertificateUtil.DELIMITER + "" + CertificateUtil.DELIMITER + z + CertificateUtil.DELIMITER + str;
        } catch (Exception unused) {
            return "";
        }
    }
}
