package com.bytedance.common.jato.exception;

import com.bytedance.common.jato.d;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ExceptionManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4675a = 0;

    /* renamed from: b  reason: collision with root package name */
    public static final int f4676b = 1;

    /* renamed from: c  reason: collision with root package name */
    public static final int f4677c = 2;

    /* renamed from: d  reason: collision with root package name */
    private static final String f4678d = "ExceptionManager";

    /* loaded from: classes.dex */
    public interface ExceptionOccurredCallback {
        void onExceptionHandled(Throwable th, int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface a {
    }

    private static native boolean nativeStartExceptionHandling(ExceptionRule[] exceptionRuleArr, ExceptionOccurredCallback exceptionOccurredCallback);

    static {
        d.loadLibrary();
    }

    /* loaded from: classes.dex */
    public static class ExceptionRule {
        private final String className;
        private String[] deoptClassNames;
        private String[] deoptMethodNames;
        private String[] deoptMethodSignatures;
        private final String exceptionClassName;
        private final String exceptionMessage;
        private final int jumpDexPc;
        private final String methodName;
        private final String methodSignature;
        private final int strategy;

        private ExceptionRule(a aVar) {
            this.className = aVar.f4679a;
            this.methodName = aVar.f4680b;
            this.methodSignature = aVar.f4681c;
            this.exceptionClassName = aVar.f4682d;
            this.exceptionMessage = aVar.f4683e;
            this.jumpDexPc = aVar.f4684f;
            this.strategy = aVar.f4685g;
            this.deoptClassNames = null;
            this.deoptMethodNames = null;
            this.deoptMethodSignatures = null;
            if (aVar.f4686h == null || aVar.f4686h.isEmpty()) {
                return;
            }
            int size = aVar.f4686h.size();
            if (aVar.f4687i == null || aVar.f4688j == null || aVar.f4687i.size() != size || aVar.f4688j.size() != size) {
                return;
            }
            this.deoptClassNames = (String[]) aVar.f4686h.toArray(new String[0]);
            this.deoptMethodNames = (String[]) aVar.f4687i.toArray(new String[0]);
            this.deoptMethodSignatures = (String[]) aVar.f4688j.toArray(new String[0]);
        }

        public String getClassName() {
            return this.className;
        }

        public String getMethodName() {
            return this.methodName;
        }

        public String getMethodSignature() {
            return this.methodSignature;
        }

        public String getExceptionClassName() {
            return this.exceptionClassName;
        }

        public String getExceptionMessage() {
            return this.exceptionMessage;
        }

        public int getJumpDexPc() {
            return this.jumpDexPc;
        }

        public int getStrategy() {
            return this.strategy;
        }

        public String[] getDeoptClassNames() {
            return this.deoptClassNames;
        }

        public String[] getDeoptMethodNames() {
            return this.deoptMethodNames;
        }

        public String[] getDeoptMethodSignatures() {
            return this.deoptMethodSignatures;
        }

        /* loaded from: classes.dex */
        public static class a {

            /* renamed from: a  reason: collision with root package name */
            private String f4679a;

            /* renamed from: b  reason: collision with root package name */
            private String f4680b;

            /* renamed from: c  reason: collision with root package name */
            private String f4681c;

            /* renamed from: d  reason: collision with root package name */
            private String f4682d;

            /* renamed from: e  reason: collision with root package name */
            private String f4683e;

            /* renamed from: f  reason: collision with root package name */
            private int f4684f = -1;

            /* renamed from: g  reason: collision with root package name */
            private int f4685g = 0;

            /* renamed from: h  reason: collision with root package name */
            private List<String> f4686h;

            /* renamed from: i  reason: collision with root package name */
            private List<String> f4687i;

            /* renamed from: j  reason: collision with root package name */
            private List<String> f4688j;

            public a a(String str, String str2, String str3) {
                this.f4679a = str;
                this.f4680b = str2;
                this.f4681c = str3;
                return this;
            }

            public a a(String str, String str2) {
                this.f4682d = str;
                this.f4683e = str2;
                return this;
            }

            public a a(int i2) {
                this.f4684f = i2;
                return this;
            }

            public a b(int i2) {
                this.f4685g = i2;
                return this;
            }

            public a b(String str, String str2, String str3) {
                if (!str.isEmpty() && !str2.isEmpty() && !str3.isEmpty()) {
                    if (this.f4686h == null) {
                        this.f4686h = new ArrayList();
                        this.f4687i = new ArrayList();
                        this.f4688j = new ArrayList();
                    }
                    this.f4686h.add(str);
                    this.f4687i.add(str2);
                    this.f4688j.add(str3);
                }
                return this;
            }

            public a a(String[] strArr, String[] strArr2, String[] strArr3) {
                if (strArr.length == strArr2.length && strArr.length == strArr3.length) {
                    if (this.f4686h == null) {
                        this.f4686h = new ArrayList();
                        this.f4687i = new ArrayList();
                        this.f4688j = new ArrayList();
                    }
                    for (int i2 = 0; i2 < strArr.length; i2++) {
                        String str = strArr[i2];
                        String str2 = strArr2[i2];
                        String str3 = strArr3[i2];
                        if (str != null && str2 != null && str3 != null) {
                            b(str, str2, str3);
                        }
                    }
                }
                return this;
            }

            public ExceptionRule a() {
                return new ExceptionRule(this);
            }
        }

        public boolean isValid() {
            String str;
            String str2;
            String str3;
            String str4 = this.className;
            if (str4 == null || str4.isEmpty() || (str = this.methodName) == null || str.isEmpty() || (str2 = this.methodSignature) == null || str2.isEmpty() || (str3 = this.exceptionClassName) == null || str3.isEmpty()) {
                return false;
            }
            return this.strategy != 2 || this.jumpDexPc >= 0;
        }
    }

    public static boolean a(ExceptionRule[] exceptionRuleArr, ExceptionOccurredCallback exceptionOccurredCallback) {
        if (exceptionRuleArr == null || exceptionRuleArr.length == 0) {
            return false;
        }
        return nativeStartExceptionHandling(exceptionRuleArr, exceptionOccurredCallback);
    }
}
