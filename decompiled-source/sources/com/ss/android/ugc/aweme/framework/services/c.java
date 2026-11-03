package com.ss.android.ugc.aweme.framework.services;

import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/* compiled from: ConfigLoader.java */
/* loaded from: classes9.dex */
class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f18633a = {"services/", "services/AwemeLive/", "services/AwemeIM/", "services/AwemeReactNative/", "services/AwemePush/", "services/AwemeShare/", "services/AwemeMain/", "services/AwemePlugin/", "services/SdkDebugger/", "services/AwemeMusic/", "services/AwemeVideo/", "services/AwemeFramework/", "services/AwemeCommerce/"};

    /* renamed from: b  reason: collision with root package name */
    private static final Map<String, List<a>> f18634b = Collections.synchronizedMap(new LinkedHashMap());

    c() {
    }

    private static void a(String str, String str2) {
        String[] split = str2.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        ArrayList arrayList = new ArrayList();
        for (String str3 : split) {
            arrayList.add(a.a(str3));
        }
        f18634b.put(str, arrayList);
    }

    public static <T> Iterator<T> a(Class<T> cls) {
        ClassLoader classLoader = cls.getClassLoader();
        return new b(a(cls, classLoader), cls, classLoader);
    }

    private static <T> List<a> a(Class<T> cls, ClassLoader classLoader) {
        Map<String, List<a>> map = f18634b;
        synchronized (map) {
            List<a> list = map.get(cls.getName());
            if (list != null) {
                return list;
            }
            List<a> linkedList = new LinkedList<>();
            for (String str : f18633a) {
                Enumeration<URL> a2 = a(cls, classLoader, str);
                while (a2 != null && a2.hasMoreElements()) {
                    linkedList.addAll(a(a2.nextElement()));
                }
            }
            Map<String, List<a>> map2 = f18634b;
            synchronized (map2) {
                if (!map2.containsKey(cls.getName())) {
                    map2.put(cls.getName(), linkedList);
                } else {
                    linkedList = map2.get(cls.getName());
                }
            }
            return linkedList;
        }
    }

    private static Enumeration<URL> a(Class cls, ClassLoader classLoader, String str) {
        String str2 = str + cls.getName();
        if (classLoader == null) {
            try {
                return ClassLoader.getSystemResources(str2);
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        try {
            return classLoader.getResources(str2);
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private static List<a> a(URL url) {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return b(url);
            } catch (ConcurrentModificationException e2) {
                if (i2 < 2) {
                    try {
                        Thread.sleep(2L);
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                } else {
                    throw e2;
                }
            }
        }
        return new LinkedList();
    }

    private static List<a> b(URL url) {
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        IOException e2;
        LinkedList linkedList = new LinkedList();
        BufferedReader bufferedReader2 = null;
        try {
            inputStream = url.openStream();
            try {
                inputStreamReader = new InputStreamReader(inputStream);
            } catch (IOException e3) {
                bufferedReader = null;
                e2 = e3;
                inputStreamReader = null;
            } catch (Throwable th) {
                th = th;
                inputStreamReader = null;
            }
        } catch (IOException e4) {
            inputStreamReader = null;
            bufferedReader = null;
            e2 = e4;
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            inputStreamReader = null;
        }
        try {
            bufferedReader = new BufferedReader(inputStreamReader);
            try {
                try {
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        a a2 = a.a(readLine);
                        if (a2 != null) {
                            linkedList.add(a2);
                        }
                    }
                } catch (IOException e5) {
                    e2 = e5;
                    e2.printStackTrace();
                    a(bufferedReader);
                    a(inputStreamReader);
                    a(inputStream);
                    return linkedList;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader2 = bufferedReader;
                a(bufferedReader2);
                a(inputStreamReader);
                a(inputStream);
                throw th;
            }
        } catch (IOException e6) {
            bufferedReader = null;
            e2 = e6;
        } catch (Throwable th4) {
            th = th4;
            a(bufferedReader2);
            a(inputStreamReader);
            a(inputStream);
            throw th;
        }
        a(bufferedReader);
        a(inputStreamReader);
        a(inputStream);
        return linkedList;
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ConfigLoader.java */
    /* loaded from: classes9.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final String f18635a;

        /* renamed from: b  reason: collision with root package name */
        public final boolean f18636b;

        /* renamed from: c  reason: collision with root package name */
        public Object f18637c;

        public a(String str, boolean z) {
            this.f18635a = str;
            this.f18636b = z;
        }

        public static a a(String str) {
            if (str == null || "".equals(str)) {
                return null;
            }
            String[] split = str.split(CertificateUtil.DELIMITER);
            return new a(split[0], split.length > 1 ? Boolean.parseBoolean(split[1]) : false);
        }
    }

    /* compiled from: ConfigLoader.java */
    /* loaded from: classes9.dex */
    private static class b<T> implements Iterator<T> {

        /* renamed from: a  reason: collision with root package name */
        private List<a> f18638a;

        /* renamed from: b  reason: collision with root package name */
        private ClassLoader f18639b;

        /* renamed from: c  reason: collision with root package name */
        private Class f18640c;

        /* renamed from: d  reason: collision with root package name */
        private int f18641d = 0;

        public b(List<a> list, Class<T> cls, ClassLoader classLoader) {
            this.f18639b = classLoader;
            this.f18640c = cls;
            this.f18638a = list;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            List<a> list = this.f18638a;
            return list != null && this.f18641d < list.size();
        }

        @Override // java.util.Iterator
        public T next() {
            a aVar = this.f18638a.get(this.f18641d);
            this.f18641d++;
            synchronized (aVar) {
                if (aVar.f18636b && aVar.f18637c != null) {
                    return (T) aVar.f18637c;
                } else if (aVar.f18636b) {
                    aVar.f18637c = a(aVar);
                    return (T) aVar.f18637c;
                } else {
                    return a(aVar);
                }
            }
        }

        private T a(a aVar) {
            try {
                Class<?> cls = Class.forName(aVar.f18635a, false, this.f18639b);
                if (!this.f18640c.isAssignableFrom(cls)) {
                    throw new RuntimeException(new ClassCastException("source: " + cls.getName() + ", dest: " + this.f18640c.getName()));
                }
                try {
                    Constructor<?> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
                    declaredConstructor.setAccessible(true);
                    return (T) declaredConstructor.newInstance(new Object[0]);
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException(e2);
                } catch (InstantiationException e3) {
                    throw new RuntimeException(e3);
                } catch (NoSuchMethodException e4) {
                    throw new RuntimeException(e4);
                } catch (InvocationTargetException e5) {
                    throw new RuntimeException(e5);
                }
            } catch (ClassNotFoundException e6) {
                throw new RuntimeException(e6);
            }
        }
    }
}
