package com.bytedance.robust.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import com.bytedance.librarian.c;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.meituan.robust.PatchProxyResult;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;
/* compiled from: PatchMultiDex.java */
/* loaded from: classes2.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public static ChangeQuickRedirect f12126a = null;

    /* renamed from: b  reason: collision with root package name */
    static final String f12127b = "PatchMultiDex";

    /* renamed from: c  reason: collision with root package name */
    private static final String f12128c = "secondary-dexes";

    /* renamed from: d  reason: collision with root package name */
    private static final String f12129d = "code_cache";

    /* renamed from: e  reason: collision with root package name */
    private static final String f12130e = "secondary-dexes";

    /* renamed from: f  reason: collision with root package name */
    private static final int f12131f = 20;

    /* renamed from: g  reason: collision with root package name */
    private static final int f12132g = 4;

    /* renamed from: h  reason: collision with root package name */
    private static final int f12133h = 2;

    /* renamed from: i  reason: collision with root package name */
    private static final int f12134i = 1;

    /* renamed from: j  reason: collision with root package name */
    private static final Set<File> f12135j = new HashSet();
    private static final boolean k = a(System.getProperty("java.vm.version"));

    static /* synthetic */ Field a(Object obj, String str) throws NoSuchFieldException {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{obj, str}, null, f12126a, true, "5e61d8cf12b34294e62356478c978053");
        return proxy != null ? (Field) proxy.result : b(obj, str);
    }

    static /* synthetic */ Method a(Object obj, String str, Class[] clsArr) throws NoSuchMethodException {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{obj, str, clsArr}, null, f12126a, true, "6f87b2f75514455220ef441cef228afa");
        return proxy != null ? (Method) proxy.result : b(obj, str, clsArr);
    }

    static /* synthetic */ void a(Object obj, String str, Object[] objArr) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (PatchProxy.proxy(new Object[]{obj, str, objArr}, null, f12126a, true, "ea6b99a39ce170194e1af340d205b529") != null) {
            return;
        }
        b(obj, str, objArr);
    }

    private b() {
    }

    public static void a(ClassLoader classLoader, String str, Context context, String str2) {
        if (PatchProxy.proxy(new Object[]{classLoader, str, context, str2}, null, f12126a, true, "050aa4c2c3b9cfd1717e339bc80370dd") != null) {
            return;
        }
        Log.i(f12127b, " Installing application");
        if (k) {
            Log.i(f12127b, " VM has multidex support, MultiDex support library is disabled.");
        } else if (Build.VERSION.SDK_INT < 4) {
            throw new RuntimeException("MultiDex installation failed. SDK " + Build.VERSION.SDK_INT + " is unsupported. Min SDK version is 4" + c.a.DOT);
        } else {
            try {
                ApplicationInfo a2 = a(context);
                if (a2 == null) {
                    Log.i(f12127b, " No ApplicationInfo available, i.e. running on a test Context: MultiDex support library is disabled.");
                    return;
                }
                a(classLoader, context, new File(str), "secondary-dexes" + str2, a2.packageName, true);
                Log.i(f12127b, " install done");
            } catch (Exception e2) {
                Log.e(f12127b, " MultiDex installation failure", e2);
                throw new RuntimeException("MultiDex installation failed (" + e2.getMessage() + ").");
            }
        }
    }

    private static void a(ClassLoader classLoader, Context context, File file, String str, String str2, boolean z) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
        IOException e2 = null;
        if (PatchProxy.proxy(new Object[]{classLoader, context, file, str, str2, new Byte(z ? (byte) 1 : (byte) 0)}, null, f12126a, true, "9aafa18ff0976f7d88e76d60732e9384") != null) {
            return;
        }
        if (classLoader == null) {
            Log.e(f12127b, "classLoader is null, just return.");
        }
        Set<File> set = f12135j;
        synchronized (set) {
            if (!set.contains(file)) {
                set.add(file);
                if (Build.VERSION.SDK_INT > 20) {
                    Log.w(f12127b, " MultiDex is not guaranteed to work in SDK version " + Build.VERSION.SDK_INT + ": SDK version higher than 20 should be backed by runtime with built-in multidex capabilty but it's not the case here: java.vm.version=\"" + System.getProperty("java.vm.version") + "\"");
                }
                if (classLoader == null) {
                    Log.e(f12127b, " Context class loader is null. Must be running in test mode. Skip patching.");
                } else {
                    a(a(context, file.getParentFile(), str));
                    File a2 = a(context, file.getParentFile(), str);
                    com.bytedance.robust.multidex.a aVar = new com.bytedance.robust.multidex.a(file, a2);
                    try {
                        a(classLoader, a2, aVar.a(context, str2, false));
                    } catch (IOException e3) {
                        if (!z) {
                            throw e3;
                        }
                        Log.e(f12127b, "Failed to install extracted secondary dex files, retrying with forced extraction", e3);
                        a(classLoader, a2, aVar.a(context, str2, true));
                    }
                    try {
                        aVar.close();
                    } catch (IOException e4) {
                        e2 = e4;
                    }
                    if (e2 != null) {
                        throw e2;
                    }
                }
            }
        }
    }

    private static ApplicationInfo a(Context context) {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{context}, null, f12126a, true, "b485a1fb1b2fafa290d96e6fb0350935");
        if (proxy != null) {
            return (ApplicationInfo) proxy.result;
        }
        try {
            return context.getApplicationInfo();
        } catch (RuntimeException e2) {
            Log.e(f12127b, "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", e2);
            return null;
        }
    }

    static boolean a(String str) {
        boolean z = true;
        boolean z2 = false;
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{str}, null, f12126a, true, "6cc4a70571167f4dee3abb611d3b7716");
        if (proxy != null) {
            return ((Boolean) proxy.result).booleanValue();
        }
        if (str != null) {
            Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(str);
            if (matcher.matches()) {
                try {
                    int parseInt = Integer.parseInt(matcher.group(1));
                    int parseInt2 = Integer.parseInt(matcher.group(2));
                    if (parseInt <= 2 && (parseInt != 2 || parseInt2 < 1)) {
                        z = false;
                    }
                    z2 = z;
                } catch (NumberFormatException unused) {
                }
            }
        }
        Log.i(f12127b, " VM with version " + str + (z2 ? " has multidex support" : " does not have multidex support"));
        return z2;
    }

    private static void a(ClassLoader classLoader, File file, List<? extends File> list) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException, SecurityException, ClassNotFoundException, InstantiationException {
        if (PatchProxy.proxy(new Object[]{classLoader, file, list}, null, f12126a, true, "51f5b0435ab6db8e4e98fee6f492fe9f") == null && !list.isEmpty()) {
            if (Build.VERSION.SDK_INT >= 19) {
                C0181b.a(classLoader, list, file);
            } else if (Build.VERSION.SDK_INT >= 14) {
                a.a(classLoader, list);
            } else {
                c.a(classLoader, list);
            }
        }
    }

    private static Field b(Object obj, String str) throws NoSuchFieldException {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{obj, str}, null, f12126a, true, "8654b8df6c4226495bf0e9a69884fef3");
        if (proxy != null) {
            return (Field) proxy.result;
        }
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        throw new NoSuchFieldException("Field " + str + " not found in " + obj.getClass());
    }

    private static Method b(Object obj, String str, Class... clsArr) throws NoSuchMethodException {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{obj, str, clsArr}, null, f12126a, true, "e1710ddb6d1964642e3bafadc226e742");
        if (proxy != null) {
            return (Method) proxy.result;
        }
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
            }
        }
        throw new NoSuchMethodException("Method " + str + " with parameters " + Arrays.asList(clsArr) + " not found in " + obj.getClass());
    }

    private static void b(Object obj, String str, Object[] objArr) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (PatchProxy.proxy(new Object[]{obj, str, objArr}, null, f12126a, true, "38a579c0a47a2617fe7bf8c65bee8d02") != null) {
            return;
        }
        Field b2 = b(obj, str);
        Object[] objArr2 = (Object[]) b2.get(obj);
        Object[] objArr3 = (Object[]) Array.newInstance(objArr2.getClass().getComponentType(), objArr2.length + objArr.length);
        System.arraycopy(objArr2, 0, objArr3, 0, objArr2.length);
        System.arraycopy(objArr, 0, objArr3, objArr2.length, objArr.length);
        b2.set(obj, objArr3);
    }

    private static void a(File file) throws Exception {
        if (PatchProxy.proxy(new Object[]{file}, null, f12126a, true, "60f09f306ad9b058447a50a780918edc") == null && file != null && file.isDirectory()) {
            Log.i(f12127b, " Clearing old secondary dex dir (" + file.getPath() + ").");
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                Log.w(f12127b, " Failed to list secondary dex dir content (" + file.getPath() + ").");
                return;
            }
            for (File file2 : listFiles) {
                Log.i(f12127b, " Trying to delete old file " + file2.getPath() + " of size " + file2.length());
                if (!file2.delete()) {
                    Log.w(f12127b, " Failed to delete old file " + file2.getPath());
                } else {
                    Log.i(f12127b, " Deleted old file " + file2.getPath());
                }
            }
            if (!file.delete()) {
                Log.w(f12127b, " Failed to delete secondary dex dir " + file.getPath());
            } else {
                Log.i(f12127b, " Deleted old secondary dex dir " + file.getPath());
            }
        }
    }

    private static File a(Context context, File file, String str) throws IOException {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{context, file, str}, null, f12126a, true, "96d4288a21a7a86fbb769e004d9c5746");
        if (proxy != null) {
            return (File) proxy.result;
        }
        File file2 = new File(file, f12129d);
        try {
            b(file2);
        } catch (IOException unused) {
            file2 = new File(context.getFilesDir(), f12129d);
            b(file2);
        }
        File file3 = new File(file2, str);
        b(file3);
        return file3;
    }

    private static void b(File file) throws IOException {
        if (PatchProxy.proxy(new Object[]{file}, null, f12126a, true, "8817a6419cf76a5dd90be5ea48260dc0") != null) {
            return;
        }
        file.mkdir();
        if (file.isDirectory()) {
            return;
        }
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            Log.e(f12127b, " Failed to create dir " + file.getPath() + ". Parent file is null.");
        } else {
            Log.e(f12127b, " Failed to create dir " + file.getPath() + ". parent file is a dir " + parentFile.isDirectory() + ", a file " + parentFile.isFile() + ", exists " + parentFile.exists() + ", readable " + parentFile.canRead() + ", writable " + parentFile.canWrite());
        }
        throw new IOException("Failed to create directory " + file.getPath());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: PatchMultiDex.java */
    /* loaded from: classes2.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        public static ChangeQuickRedirect f12146a;

        private c() {
        }

        static void a(ClassLoader classLoader, List<? extends File> list) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
            if (PatchProxy.proxy(new Object[]{classLoader, list}, null, f12146a, true, "a157c07cdf960c9bd1e87dd6905bf57d") != null) {
                return;
            }
            int size = list.size();
            Field a2 = b.a(classLoader, "path");
            StringBuilder sb = new StringBuilder((String) a2.get(classLoader));
            String[] strArr = new String[size];
            File[] fileArr = new File[size];
            ZipFile[] zipFileArr = new ZipFile[size];
            DexFile[] dexFileArr = new DexFile[size];
            ListIterator<? extends File> listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                File next = listIterator.next();
                String absolutePath = next.getAbsolutePath();
                sb.append(':').append(absolutePath);
                int previousIndex = listIterator.previousIndex();
                strArr[previousIndex] = absolutePath;
                fileArr[previousIndex] = next;
                zipFileArr[previousIndex] = new ZipFile(next);
                dexFileArr[previousIndex] = DexFile.loadDex(absolutePath, absolutePath + ".dex", 0);
            }
            a2.set(classLoader, sb.toString());
            b.a(classLoader, "mPaths", strArr);
            b.a(classLoader, "mFiles", fileArr);
            b.a(classLoader, "mZips", zipFileArr);
            b.a(classLoader, "mDexs", dexFileArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: PatchMultiDex.java */
    /* loaded from: classes2.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static ChangeQuickRedirect f12136a = null;

        /* renamed from: b  reason: collision with root package name */
        private static final int f12137b = 4;

        /* renamed from: c  reason: collision with root package name */
        private final InterfaceC0179a f12138c;

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: PatchMultiDex.java */
        /* renamed from: com.bytedance.robust.multidex.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public interface InterfaceC0179a {
            Object a(File file, DexFile dexFile) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException;
        }

        static void a(ClassLoader classLoader, List<? extends File> list) throws IOException, SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
            if (PatchProxy.proxy(new Object[]{classLoader, list}, null, f12136a, true, "0b764a25553ed16917a8d4c75a57d05f") != null) {
                return;
            }
            Object obj = b.a(classLoader, "pathList").get(classLoader);
            Object[] a2 = new a().a(list);
            try {
                b.a(obj, "dexElements", a2);
            } catch (NoSuchFieldException e2) {
                Log.e(b.f12127b, " Failed find field 'dexElements' attempting 'pathElements'", e2);
                b.a(obj, "pathElements", a2);
            }
        }

        private a() throws ClassNotFoundException, SecurityException, NoSuchMethodException {
            InterfaceC0179a dVar;
            Class<?> cls = Class.forName("dalvik.system.DexPathList$Element");
            try {
                try {
                    dVar = new C0180b(cls);
                } catch (NoSuchMethodException unused) {
                    dVar = new c(cls);
                }
            } catch (NoSuchMethodException unused2) {
                dVar = new d(cls);
            }
            this.f12138c = dVar;
        }

        private Object[] a(List<? extends File> list) throws IOException, SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
            PatchProxyResult proxy = PatchProxy.proxy(new Object[]{list}, this, f12136a, false, "f373663301766a041a035d0959f84385");
            if (proxy != null) {
                return (Object[]) proxy.result;
            }
            int size = list.size();
            Object[] objArr = new Object[size];
            for (int i2 = 0; i2 < size; i2++) {
                File file = list.get(i2);
                objArr[i2] = this.f12138c.a(file, DexFile.loadDex(file.getPath(), a(file), 0));
            }
            return objArr;
        }

        private static String a(File file) {
            PatchProxyResult proxy = PatchProxy.proxy(new Object[]{file}, null, f12136a, true, "d2d1f3e027d0755db663aa074f906652");
            if (proxy != null) {
                return (String) proxy.result;
            }
            File parentFile = file.getParentFile();
            String name = file.getName();
            return new File(parentFile, name.substring(0, name.length() - f12137b) + ".dex").getPath();
        }

        /* compiled from: PatchMultiDex.java */
        /* loaded from: classes2.dex */
        private static class d implements InterfaceC0179a {

            /* renamed from: a  reason: collision with root package name */
            public static ChangeQuickRedirect f12143a;

            /* renamed from: b  reason: collision with root package name */
            private final Constructor<?> f12144b;

            d(Class<?> cls) throws SecurityException, NoSuchMethodException {
                Constructor<?> constructor = cls.getConstructor(File.class, Boolean.TYPE, File.class, DexFile.class);
                this.f12144b = constructor;
                constructor.setAccessible(true);
            }

            @Override // com.bytedance.robust.multidex.b.a.InterfaceC0179a
            public Object a(File file, DexFile dexFile) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
                PatchProxyResult proxy = PatchProxy.proxy(new Object[]{file, dexFile}, this, f12143a, false, "735168e1cf385ece24820655e2d991ad");
                return proxy != null ? proxy.result : this.f12144b.newInstance(file, Boolean.FALSE, file, dexFile);
            }
        }

        /* compiled from: PatchMultiDex.java */
        /* loaded from: classes2.dex */
        private static class c implements InterfaceC0179a {

            /* renamed from: a  reason: collision with root package name */
            public static ChangeQuickRedirect f12141a;

            /* renamed from: b  reason: collision with root package name */
            private final Constructor<?> f12142b;

            c(Class<?> cls) throws SecurityException, NoSuchMethodException {
                Constructor<?> constructor = cls.getConstructor(File.class, File.class, DexFile.class);
                this.f12142b = constructor;
                constructor.setAccessible(true);
            }

            @Override // com.bytedance.robust.multidex.b.a.InterfaceC0179a
            public Object a(File file, DexFile dexFile) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
                PatchProxyResult proxy = PatchProxy.proxy(new Object[]{file, dexFile}, this, f12141a, false, "ac7121b4878332f4cc17e14c7716708e");
                return proxy != null ? proxy.result : this.f12142b.newInstance(file, file, dexFile);
            }
        }

        /* compiled from: PatchMultiDex.java */
        /* renamed from: com.bytedance.robust.multidex.b$a$b  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        private static class C0180b implements InterfaceC0179a {

            /* renamed from: a  reason: collision with root package name */
            public static ChangeQuickRedirect f12139a;

            /* renamed from: b  reason: collision with root package name */
            private final Constructor<?> f12140b;

            C0180b(Class<?> cls) throws SecurityException, NoSuchMethodException {
                Constructor<?> constructor = cls.getConstructor(File.class, ZipFile.class, DexFile.class);
                this.f12140b = constructor;
                constructor.setAccessible(true);
            }

            @Override // com.bytedance.robust.multidex.b.a.InterfaceC0179a
            public Object a(File file, DexFile dexFile) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
                PatchProxyResult proxy = PatchProxy.proxy(new Object[]{file, dexFile}, this, f12139a, false, "3cc235fe2c922aa386bec02e720f35c3");
                return proxy != null ? proxy.result : this.f12140b.newInstance(file, new ZipFile(file), dexFile);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: PatchMultiDex.java */
    /* renamed from: com.bytedance.robust.multidex.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static final class C0181b {

        /* renamed from: a  reason: collision with root package name */
        public static ChangeQuickRedirect f12145a;

        private C0181b() {
        }

        static void a(ClassLoader classLoader, List<? extends File> list, File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
            IOException[] iOExceptionArr;
            if (PatchProxy.proxy(new Object[]{classLoader, list, file}, null, f12145a, true, "cd4058e64813281739b24741e3502272") != null) {
                return;
            }
            Object obj = b.a(classLoader, "pathList").get(classLoader);
            ArrayList arrayList = new ArrayList();
            b.a(obj, "dexElements", a(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Log.e(b.f12127b, " Exception in makeDexElement", (IOException) it.next());
                }
                Field a2 = b.a(obj, "dexElementsSuppressedExceptions");
                IOException[] iOExceptionArr2 = (IOException[]) a2.get(obj);
                if (iOExceptionArr2 == null) {
                    iOExceptionArr = (IOException[]) arrayList.toArray(new IOException[arrayList.size()]);
                } else {
                    IOException[] iOExceptionArr3 = new IOException[arrayList.size() + iOExceptionArr2.length];
                    arrayList.toArray(iOExceptionArr3);
                    System.arraycopy(iOExceptionArr2, 0, iOExceptionArr3, arrayList.size(), iOExceptionArr2.length);
                    iOExceptionArr = iOExceptionArr3;
                }
                a2.set(obj, iOExceptionArr);
                IOException iOException = new IOException("I/O exception during makeDexElement");
                iOException.initCause((Throwable) arrayList.get(0));
                throw iOException;
            }
        }

        private static Object[] a(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            PatchProxyResult proxy = PatchProxy.proxy(new Object[]{obj, arrayList, file, arrayList2}, null, f12145a, true, "d2f802b2872ff4ec998db54df7e9cca7");
            return proxy != null ? (Object[]) proxy.result : (Object[]) b.a(obj, "makeDexElements", new Class[]{ArrayList.class, File.class, ArrayList.class}).invoke(obj, arrayList, file, arrayList2);
        }
    }
}
