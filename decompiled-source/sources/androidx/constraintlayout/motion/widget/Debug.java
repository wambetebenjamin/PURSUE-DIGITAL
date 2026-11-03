package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.sdk.account.platform.base.i;
import com.facebook.internal.security.CertificateUtil;
import com.meituan.robust.Constants;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
/* loaded from: classes.dex */
public class Debug {
    public static void logStack(String str, String str2, int i2) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int min = Math.min(i2, stackTrace.length - 1);
        String str3 = " ";
        for (int i3 = 1; i3 <= min; i3++) {
            StackTraceElement stackTraceElement = stackTrace[i3];
            str3 = str3 + " ";
            Log.v(str, str2 + str3 + (".(" + stackTrace[i3].getFileName() + CertificateUtil.DELIMITER + stackTrace[i3].getLineNumber() + ") " + stackTrace[i3].getMethodName()) + str3);
        }
    }

    public static void printStack(String str, int i2) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int min = Math.min(i2, stackTrace.length - 1);
        String str2 = " ";
        for (int i3 = 1; i3 <= min; i3++) {
            StackTraceElement stackTraceElement = stackTrace[i3];
            str2 = str2 + " ";
            System.out.println(str + str2 + (".(" + stackTrace[i3].getFileName() + CertificateUtil.DELIMITER + stackTrace[i3].getLineNumber() + ") ") + str2);
        }
    }

    public static String getName(View view) {
        try {
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return "UNKNOWN";
        }
    }

    public static void dumpPoc(Object obj) {
        Field[] fields;
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String str = ".(" + stackTraceElement.getFileName() + CertificateUtil.DELIMITER + stackTraceElement.getLineNumber() + ")";
        Class<?> cls = obj.getClass();
        System.out.println(str + "------------- " + cls.getName() + " --------------------");
        for (Field field : cls.getFields()) {
            try {
                Object obj2 = field.get(obj);
                if (field.getName().startsWith("layout_constraint") && ((!(obj2 instanceof Integer) || !obj2.toString().equals(i.a.ERROR_CODE_UNKNOW)) && ((!(obj2 instanceof Integer) || !obj2.toString().equals("0")) && ((!(obj2 instanceof Float) || !obj2.toString().equals("1.0")) && (!(obj2 instanceof Float) || !obj2.toString().equals("0.5")))))) {
                    System.out.println(str + "    " + field.getName() + " " + obj2);
                }
            } catch (IllegalAccessException unused) {
            }
        }
        System.out.println(str + "------------- " + cls.getSimpleName() + " --------------------");
    }

    public static String getName(Context context, int i2) {
        if (i2 != -1) {
            try {
                return context.getResources().getResourceEntryName(i2);
            } catch (Exception unused) {
                return "?" + i2;
            }
        }
        return "UNKNOWN";
    }

    public static String getName(Context context, int[] iArr) {
        String str;
        try {
            String str2 = iArr.length + Constants.ARRAY_TYPE;
            int i2 = 0;
            while (i2 < iArr.length) {
                String str3 = str2 + (i2 == 0 ? "" : " ");
                try {
                    str = context.getResources().getResourceEntryName(iArr[i2]);
                } catch (Resources.NotFoundException unused) {
                    str = "? " + iArr[i2] + " ";
                }
                str2 = str3 + str;
                i2++;
            }
            return str2 + "]";
        } catch (Exception e2) {
            Log.v("DEBUG", e2.toString());
            return "UNKNOWN";
        }
    }

    public static String getState(MotionLayout motionLayout, int i2) {
        return i2 == -1 ? "UNDEFINED" : motionLayout.getContext().getResources().getResourceEntryName(i2);
    }

    public static String getActionType(MotionEvent motionEvent) {
        Field[] fields;
        int action = motionEvent.getAction();
        for (Field field : MotionEvent.class.getFields()) {
            try {
                if (Modifier.isStatic(field.getModifiers()) && field.getType().equals(Integer.TYPE) && field.getInt(null) == action) {
                    return field.getName();
                }
            } catch (IllegalAccessException unused) {
            }
        }
        return "---";
    }

    public static String getLocation() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return ".(" + stackTraceElement.getFileName() + CertificateUtil.DELIMITER + stackTraceElement.getLineNumber() + ")";
    }

    public static String getLoc() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return ".(" + stackTraceElement.getFileName() + CertificateUtil.DELIMITER + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName() + "()";
    }

    public static String getLocation2() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        return ".(" + stackTraceElement.getFileName() + CertificateUtil.DELIMITER + stackTraceElement.getLineNumber() + ")";
    }

    public static String getCallFrom(int i2) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[i2 + 2];
        return ".(" + stackTraceElement.getFileName() + CertificateUtil.DELIMITER + stackTraceElement.getLineNumber() + ")";
    }

    public static void dumpLayoutParams(ViewGroup viewGroup, String str) {
        Field[] fields;
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String str2 = ".(" + stackTraceElement.getFileName() + CertificateUtil.DELIMITER + stackTraceElement.getLineNumber() + ") " + str + "  ";
        int childCount = viewGroup.getChildCount();
        System.out.println(str + " children " + childCount);
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            System.out.println(str2 + "     " + getName(childAt));
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            for (Field field : layoutParams.getClass().getFields()) {
                try {
                    Object obj = field.get(layoutParams);
                    if (field.getName().contains("To") && !obj.toString().equals(i.a.ERROR_CODE_UNKNOW)) {
                        System.out.println(str2 + "       " + field.getName() + " " + obj);
                    }
                } catch (IllegalAccessException unused) {
                }
            }
        }
    }

    public static void dumpLayoutParams(ViewGroup.LayoutParams layoutParams, String str) {
        Field[] fields;
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String str2 = ".(" + stackTraceElement.getFileName() + CertificateUtil.DELIMITER + stackTraceElement.getLineNumber() + ") " + str + "  ";
        System.out.println(" >>>>>>>>>>>>>>>>>>. dump " + str2 + "  " + layoutParams.getClass().getName());
        for (Field field : layoutParams.getClass().getFields()) {
            try {
                Object obj = field.get(layoutParams);
                String name = field.getName();
                if (name.contains("To") && !obj.toString().equals(i.a.ERROR_CODE_UNKNOW)) {
                    System.out.println(str2 + "       " + name + " " + obj);
                }
            } catch (IllegalAccessException unused) {
            }
        }
        System.out.println(" <<<<<<<<<<<<<<<<< dump " + str2);
    }
}
