package com.bytedance.apm.agent.wrapper;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* compiled from: TTToastWrapper.java */
/* loaded from: classes.dex */
public class a {

    /* compiled from: TTToastWrapper.java */
    /* renamed from: com.bytedance.apm.agent.wrapper.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public @interface InterfaceC0010a {
    }

    public static Toast makeText(Context context, CharSequence charSequence, int i2) {
        Toast makeText = Toast.makeText(context, ((Object) charSequence) + "__wrapper", i2);
        initToastHook(makeText);
        return makeText;
    }

    public static Toast makeText(Context context, int i2, int i3) {
        return makeText(context, ((Object) context.getResources().getText(i2)) + "__wrapper", i3);
    }

    public static boolean initToastHook(Toast toast) {
        if (Build.VERSION.SDK_INT == 24 || Build.VERSION.SDK_INT == 25) {
            a(toast);
            return true;
        }
        return true;
    }

    private static boolean a(Toast toast) {
        Field declaredField;
        Field declaredField2;
        try {
            if (toast.getClass().getSuperclass() != null && Toast.class.getName().equals(toast.getClass().getSuperclass().getName())) {
                declaredField = toast.getClass().getSuperclass().getDeclaredField("mTN");
            } else {
                declaredField = toast.getClass().getDeclaredField("mTN");
            }
            if (declaredField == null) {
                return false;
            }
            declaredField.setAccessible(true);
            Object obj = declaredField.get(toast);
            if (obj == null || (declaredField2 = obj.getClass().getDeclaredField("mHandler")) == null) {
                return false;
            }
            declaredField2.setAccessible(true);
            declaredField2.set(obj, new b(obj));
            return true;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return false;
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TTToastWrapper.java */
    /* loaded from: classes.dex */
    public static class b extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private Object f367a;

        /* renamed from: b  reason: collision with root package name */
        private Method f368b;

        /* renamed from: c  reason: collision with root package name */
        private Method f369c;

        b(Object obj) {
            this.f367a = obj;
            try {
                Method declaredMethod = obj.getClass().getDeclaredMethod("handleShow", IBinder.class);
                this.f368b = declaredMethod;
                declaredMethod.setAccessible(true);
                Method declaredMethod2 = obj.getClass().getDeclaredMethod("handleHide", new Class[0]);
                this.f369c = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Method method;
            int i2 = message.what;
            if (i2 == 0) {
                IBinder iBinder = (IBinder) message.obj;
                Method method2 = this.f368b;
                if (method2 != null) {
                    try {
                        method2.invoke(this.f367a, iBinder);
                    } catch (WindowManager.BadTokenException e2) {
                        e2.printStackTrace();
                    } catch (IllegalAccessException e3) {
                        e3.printStackTrace();
                    } catch (InvocationTargetException e4) {
                        e4.printStackTrace();
                    }
                }
            } else if (i2 == 1) {
                Method method3 = this.f369c;
                if (method3 != null) {
                    try {
                        method3.invoke(this.f367a, new Object[0]);
                    } catch (IllegalAccessException e5) {
                        e5.printStackTrace();
                    } catch (InvocationTargetException e6) {
                        e6.printStackTrace();
                    }
                }
            } else if (i2 == 2 && (method = this.f369c) != null) {
                try {
                    method.invoke(this.f367a, new Object[0]);
                } catch (IllegalAccessException e7) {
                    e7.printStackTrace();
                } catch (InvocationTargetException e8) {
                    e8.printStackTrace();
                }
            }
            super.handleMessage(message);
        }
    }
}
