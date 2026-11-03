package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class bh {

    /* renamed from: a  reason: collision with root package name */
    private static final com.google.android.play.core.assetpacks.internal.o f16502a = new com.google.android.play.core.assetpacks.internal.o("AssetPackStorage");

    /* renamed from: b  reason: collision with root package name */
    private static final long f16503b = TimeUnit.DAYS.toMillis(14);

    /* renamed from: c  reason: collision with root package name */
    private static final long f16504c = TimeUnit.DAYS.toMillis(28);

    /* renamed from: d  reason: collision with root package name */
    private final Context f16505d;

    /* renamed from: e  reason: collision with root package name */
    private final ed f16506e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bh(Context context, ed edVar) {
        this.f16505d = context;
        this.f16506e = edVar;
    }

    private static long H(File file, boolean z) {
        File[] listFiles;
        if (file.exists()) {
            ArrayList arrayList = new ArrayList();
            if (z && file.listFiles().length > 1) {
                f16502a.e("Multiple pack versions found, using highest version code.", new Object[0]);
            }
            try {
                for (File file2 : file.listFiles()) {
                    if (!file2.getName().equals("stale.tmp")) {
                        arrayList.add(Long.valueOf(file2.getName()));
                    }
                }
            } catch (NumberFormatException e2) {
                f16502a.c(e2, "Corrupt asset pack directories.", new Object[0]);
            }
            if (arrayList.isEmpty()) {
                return -1L;
            }
            Collections.sort(arrayList);
            return ((Long) arrayList.get(arrayList.size() - 1)).longValue();
        }
        return -1L;
    }

    private final File I(String str) {
        return new File(L(), str);
    }

    private final File J(String str, int i2, long j2) {
        return new File(j(str, i2, j2), "merge.tmp");
    }

    private final File K(String str, int i2, long j2) {
        return new File(new File(new File(M(), str), String.valueOf(i2)), String.valueOf(j2));
    }

    private final File L() {
        return new File(this.f16505d.getFilesDir(), "assetpacks");
    }

    private final File M() {
        return new File(L(), "_tmp");
    }

    private static List N(PackageInfo packageInfo, String str) {
        ArrayList arrayList = new ArrayList();
        if (packageInfo.splitNames == null) {
            return arrayList;
        }
        for (int i2 = (-Arrays.binarySearch(packageInfo.splitNames, str)) - 1; i2 < packageInfo.splitNames.length && packageInfo.splitNames[i2].startsWith(str); i2++) {
            arrayList.add(packageInfo.applicationInfo.splitSourceDirs[i2]);
        }
        return arrayList;
    }

    private final List O() {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        try {
        } catch (IOException e2) {
            f16502a.b("Could not process directory while scanning installed packs. %s", e2);
        }
        if (L().exists() && L().listFiles() != null) {
            for (File file : L().listFiles()) {
                if (!file.getCanonicalPath().equals(M().getCanonicalPath())) {
                    arrayList.add(file);
                }
            }
            return arrayList;
        }
        return arrayList;
    }

    private static void P(File file) {
        File[] listFiles;
        if (file.listFiles() == null || file.listFiles().length <= 1) {
            return;
        }
        long H = H(file, false);
        for (File file2 : file.listFiles()) {
            if (!file2.getName().equals(String.valueOf(H)) && !file2.getName().equals("stale.tmp")) {
                Q(file2);
            }
        }
    }

    private static boolean Q(File file) {
        File[] listFiles = file.listFiles();
        boolean z = true;
        if (listFiles != null) {
            for (File file2 : listFiles) {
                z &= Q(file2);
            }
        }
        if (file.delete()) {
            return z;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void A(String str, int i2, long j2, int i3) throws IOException {
        File J2 = J(str, i2, j2);
        Properties properties = new Properties();
        properties.put("numberOfMerges", String.valueOf(i3));
        J2.getParentFile().mkdirs();
        J2.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(J2);
        properties.store(fileOutputStream, (String) null);
        fileOutputStream.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void B(String str, int i2, long j2) {
        File[] listFiles;
        File[] listFiles2;
        File I = I(str);
        if (I.exists()) {
            for (File file : I.listFiles()) {
                if (file.getName().equals(String.valueOf(i2)) || file.getName().equals("stale.tmp")) {
                    if (file.getName().equals(String.valueOf(i2))) {
                        for (File file2 : file.listFiles()) {
                            if (!file2.getName().equals(String.valueOf(j2))) {
                                Q(file2);
                            }
                        }
                    }
                } else {
                    Q(file);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void C(List list) {
        int a2 = this.f16506e.a();
        for (File file : O()) {
            if (!list.contains(file.getName()) && H(file, true) != a2) {
                Q(file);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean D(String str) {
        if (I(str).exists()) {
            return Q(I(str));
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean E(String str, int i2, long j2) {
        if (K(str, i2, j2).exists()) {
            return Q(K(str, i2, j2));
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean F(String str, int i2, long j2) {
        if (h(str, i2, j2).exists()) {
            return Q(h(str, i2, j2));
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean G(String str) {
        return r(str) != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(String str) {
        return (int) H(I(str), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b(String str, int i2, long j2) throws IOException {
        File J2 = J(str, i2, j2);
        if (J2.exists()) {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(J2);
            try {
                properties.load(fileInputStream);
                fileInputStream.close();
                if (properties.getProperty("numberOfMerges") != null) {
                    try {
                        return Integer.parseInt(properties.getProperty("numberOfMerges"));
                    } catch (NumberFormatException e2) {
                        throw new ck("Merge checkpoint file corrupt.", e2);
                    }
                }
                throw new ck("Merge checkpoint file corrupt.");
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    try {
                        Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                    } catch (Exception unused) {
                    }
                }
                throw th;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long c(String str) {
        return H(g(str, (int) H(I(str), true)), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final AssetLocation d(String str, String str2, List list) {
        if (list == null) {
            return null;
        }
        String path = new File("assets", str2).getPath();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str3 = (String) it.next();
            try {
                AssetLocation a2 = bt.a(str3, path);
                if (a2 != null) {
                    return a2;
                }
            } catch (IOException e2) {
                f16502a.c(e2, "Failed to parse APK file '%s' looking for asset '%s'.", str3, str2);
                return null;
            }
        }
        f16502a.a("The asset %s is not present in Asset Pack %s. Searched in APKs: %s", str2, str, list);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final AssetLocation e(String str, String str2, AssetPackLocation assetPackLocation) {
        File file = new File(assetPackLocation.assetsPath(), str2);
        if (file.exists()) {
            return new bl(file.getPath(), 0L, file.length());
        }
        f16502a.a("The asset %s is not present in Asset Pack %s. Searched in folder: %s", str2, str, assetPackLocation.assetsPath());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final AssetPackLocation f(String str) throws IOException {
        String r = r(str);
        if (r == null) {
            return null;
        }
        File file = new File(r, "assets");
        if (!file.isDirectory()) {
            f16502a.b("Failed to find assets directory: %s", file);
            return null;
        }
        return new bm(0, r, file.getCanonicalPath());
    }

    final File g(String str, int i2) {
        return new File(I(str), String.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File h(String str, int i2, long j2) {
        return new File(g(str, i2), String.valueOf(j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File i(String str, int i2, long j2) {
        return new File(h(str, i2, j2), "_metadata");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File j(String str, int i2, long j2) {
        return new File(K(str, i2, j2), "_packs");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File k(String str, int i2, long j2) {
        return new File(i(str, i2, j2), "properties.dat");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File l(String str, int i2, long j2) {
        return new File(new File(K(str, i2, j2), "_slices"), "_metadata");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File m(String str, int i2, long j2, String str2) {
        return new File(o(str, i2, j2, str2), "checkpoint_ext.dat");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File n(String str, int i2, long j2, String str2) {
        return new File(o(str, i2, j2, str2), "checkpoint.dat");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File o(String str, int i2, long j2, String str2) {
        return new File(l(str, i2, j2), str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File p(String str, int i2, long j2, String str2) {
        return new File(new File(new File(K(str, i2, j2), "_slices"), "_unverified"), str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File q(String str, int i2, long j2, String str2) {
        return new File(new File(new File(K(str, i2, j2), "_slices"), "_verified"), str2);
    }

    final String r(String str) throws IOException {
        int length;
        File file = new File(L(), str);
        if (!file.exists()) {
            f16502a.a("Pack not found with pack name: %s", str);
            return null;
        }
        File file2 = new File(file, String.valueOf(this.f16506e.a()));
        if (!file2.exists()) {
            f16502a.a("Pack not found with pack name: %s app version: %s", str, Integer.valueOf(this.f16506e.a()));
            return null;
        }
        File[] listFiles = file2.listFiles();
        if (listFiles == null || (length = listFiles.length) == 0) {
            f16502a.a("No pack version found for pack name: %s app version: %s", str, Integer.valueOf(this.f16506e.a()));
            return null;
        } else if (length > 1) {
            f16502a.b("Multiple pack versions found for pack name: %s app version: %s", str, Integer.valueOf(this.f16506e.a()));
            return null;
        } else {
            return listFiles[0].getCanonicalPath();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List s(String str) {
        PackageInfo packageInfo;
        String str2 = null;
        try {
            packageInfo = this.f16505d.getPackageManager().getPackageInfo(this.f16505d.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            f16502a.b("Could not find PackageInfo.", new Object[0]);
            packageInfo = null;
        }
        if (packageInfo == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 21) {
            if (packageInfo.splitNames == null || packageInfo.applicationInfo.splitSourceDirs == null) {
                f16502a.a("No splits present for package %s.", str);
            } else {
                int binarySearch = Arrays.binarySearch(packageInfo.splitNames, str);
                if (binarySearch < 0) {
                    f16502a.a("Asset Pack '%s' is not installed.", str);
                } else {
                    str2 = packageInfo.applicationInfo.splitSourceDirs[binarySearch];
                }
            }
            if (str2 == null) {
                arrayList.add(packageInfo.applicationInfo.sourceDir);
                arrayList.addAll(N(packageInfo, "config."));
                return arrayList;
            }
            arrayList.add(str2);
            arrayList.addAll(N(packageInfo, String.valueOf(str).concat(".config.")));
            return arrayList;
        }
        arrayList.add(packageInfo.applicationInfo.sourceDir);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map t() {
        HashMap hashMap = new HashMap();
        for (File file : O()) {
            String name = file.getName();
            int H = (int) H(I(name), true);
            long H2 = H(g(name, H), true);
            if (h(name, H, H2).exists()) {
                hashMap.put(name, Long.valueOf(H2));
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map u() {
        HashMap hashMap = new HashMap();
        for (String str : v().keySet()) {
            hashMap.put(str, Long.valueOf(c(str)));
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map v() {
        HashMap hashMap = new HashMap();
        try {
            for (File file : O()) {
                AssetPackLocation f2 = f(file.getName());
                if (f2 != null) {
                    hashMap.put(file.getName(), f2);
                }
            }
        } catch (IOException e2) {
            f16502a.b("Could not process directory while scanning installed packs: %s", e2);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void w() {
        for (File file : O()) {
            if (file.listFiles() != null) {
                P(file);
                long H = H(file, false);
                if (this.f16506e.a() != H) {
                    try {
                        new File(new File(file, String.valueOf(H)), "stale.tmp").createNewFile();
                    } catch (IOException unused) {
                        f16502a.b("Could not write staleness marker.", new Object[0]);
                    }
                }
                for (File file2 : file.listFiles()) {
                    P(file2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void x() {
        File[] listFiles;
        if (M().exists()) {
            for (File file : M().listFiles()) {
                if (System.currentTimeMillis() - file.lastModified() > f16503b) {
                    Q(file);
                } else {
                    P(file);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void y() {
        File[] listFiles;
        for (File file : O()) {
            if (file.listFiles() != null) {
                for (File file2 : file.listFiles()) {
                    File file3 = new File(file2, "stale.tmp");
                    if (file3.exists() && System.currentTimeMillis() - file3.lastModified() > f16504c) {
                        Q(file2);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void z() {
        Q(L());
    }
}
