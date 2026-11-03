package com.bytedance.geckox.clean;

import com.bytedance.geckox.utils.e;
import com.bytedance.geckox.utils.f;
import com.bytedance.geckox.utils.i;
import com.bytedance.geckox.utils.m;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* compiled from: ChannelCleanHelper.java */
/* loaded from: classes2.dex */
public class c {
    public static void a(final String str) {
        m.a().execute(new Runnable() { // from class: com.bytedance.geckox.clean.c.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    c.d(str);
                } catch (Throwable th) {
                    e.a(new RuntimeException("delete old channel version failed，path：" + str, th));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(String str) {
        com.bytedance.geckox.lock.a a2 = com.bytedance.geckox.lock.a.a(str + File.separator + com.bytedance.geckox.lock.a.f9203a);
        if (a2 == null) {
            return;
        }
        try {
            com.bytedance.geckox.lock.b a3 = com.bytedance.geckox.lock.b.a(str + File.separator + com.bytedance.geckox.lock.b.f9207a);
            List<File> g2 = g(str);
            if (g2 != null && !g2.isEmpty()) {
                Iterator<File> it = g2.iterator();
                while (it.hasNext()) {
                    com.bytedance.geckox.lock.c.c(it.next().getAbsolutePath() + File.separator + com.bytedance.geckox.lock.c.f9212a);
                }
                a3.a();
                return;
            }
            a3.a();
        } finally {
            a2.a();
        }
    }

    public static boolean b(String str) {
        try {
            e(str);
            return true;
        } catch (Throwable th) {
            e.a(new RuntimeException("delete old channel version failed，path：" + str, th));
            return false;
        }
    }

    private static void e(String str) {
        com.bytedance.geckox.lock.a a2 = com.bytedance.geckox.lock.a.a(str + File.separator + com.bytedance.geckox.lock.a.f9203a);
        if (a2 == null) {
            return;
        }
        try {
            com.bytedance.geckox.lock.b a3 = com.bytedance.geckox.lock.b.a(str + File.separator + com.bytedance.geckox.lock.b.f9207a);
            File[] f2 = f(str);
            if (f2 != null && f2.length > 0) {
                int length = f2.length;
                for (int i2 = 0; i2 < length; i2++) {
                    com.bytedance.geckox.lock.c.c(f2[i2].getAbsolutePath() + File.separator + com.bytedance.geckox.lock.c.f9212a);
                }
                a3.a();
                return;
            }
            a3.a();
        } finally {
            a2.a();
        }
    }

    private static File[] f(String str) {
        File[] listFiles = new File(str).listFiles(new FileFilter() { // from class: com.bytedance.geckox.clean.c.2
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        if (listFiles == null || listFiles.length == 0) {
            return null;
        }
        return listFiles;
    }

    private static List<File> g(String str) {
        File[] listFiles = new File(str).listFiles(new FileFilter() { // from class: com.bytedance.geckox.clean.c.3
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        if (listFiles == null || listFiles.length == 0 || listFiles.length == 1) {
            return null;
        }
        return a(listFiles);
    }

    private static List<File> a(File[] fileArr) {
        long j2;
        ArrayList<File> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        File file = null;
        long j3 = -1;
        for (File file2 : fileArr) {
            String name = file2.getName();
            if (name.endsWith(com.bytedance.geckox.utils.b.UPDATING)) {
                arrayList.add(file2);
            } else if (name.endsWith(com.bytedance.geckox.utils.b.PENDING_DELETE)) {
                a(file2);
            } else {
                try {
                    long parseLong = Long.parseLong(name);
                    if (parseLong > j3) {
                        if (file != null) {
                            try {
                                arrayList2.add(file);
                            } catch (Exception unused) {
                                j3 = parseLong;
                                a(file2);
                            }
                        }
                        file = file2;
                        j3 = parseLong;
                    } else {
                        arrayList2.add(file2);
                    }
                } catch (Exception unused2) {
                }
            }
        }
        for (File file3 : arrayList) {
            String name2 = file3.getName();
            int indexOf = name2.indexOf(com.bytedance.geckox.utils.b.UPDATING);
            if (indexOf == -1) {
                a(file3);
            } else {
                try {
                    j2 = Long.parseLong(name2.substring(0, indexOf));
                } catch (NumberFormatException e2) {
                    e2.printStackTrace();
                    j2 = -1;
                }
                if (j2 <= j3) {
                    a(file3);
                }
            }
        }
        return arrayList2;
    }

    public static void a(final File file) {
        i.a().execute(new Runnable() { // from class: com.bytedance.geckox.clean.c.4
            @Override // java.lang.Runnable
            public void run() {
                f.delete(file);
            }
        });
    }
}
