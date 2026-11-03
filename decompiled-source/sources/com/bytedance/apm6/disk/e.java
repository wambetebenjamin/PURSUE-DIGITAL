package com.bytedance.apm6.disk;

import android.app.Application;
import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.bytedance.apm.constant.i;
import com.bytedance.apm.util.ak;
import com.bytedance.apm6.util.g;
import com.bytedance.crash.dumper.Scraps;
import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DiskStatistics.java */
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static final long f2144a = 1024;

    /* renamed from: b  reason: collision with root package name */
    private static final String f2145b = "name";

    /* renamed from: c  reason: collision with root package name */
    private static final String f2146c = "size";

    /* renamed from: d  reason: collision with root package name */
    private static final String f2147d = "num";

    /* renamed from: e  reason: collision with root package name */
    private static final long f2148e = 17179869184L;

    /* renamed from: f  reason: collision with root package name */
    private static final long f2149f = 102400;

    /* renamed from: g  reason: collision with root package name */
    private static final long f2150g = 1000;

    /* renamed from: h  reason: collision with root package name */
    private static e f2151h = new e();
    private static final String n = "internal";
    private static final String o = "external";
    private static final String p = "/Android/data/";

    /* renamed from: i  reason: collision with root package name */
    private String f2152i;

    /* renamed from: j  reason: collision with root package name */
    private String f2153j;
    private String k;
    private String l;
    private List<String> q;
    private List<String> r;
    private boolean s;
    private com.bytedance.apm6.disk.config.a t;
    private com.bytedance.apm.listener.e y;
    private List<String> z;
    private volatile boolean m = false;
    private long u = 0;
    private long v = 0;
    private long w = 0;
    private long x = 0;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(long j2) {
        return j2 < 0 || j2 > f2148e;
    }

    boolean a(long j2) {
        return j2 >= 104857600 && j2 <= f2148e;
    }

    private e() {
    }

    public static e getInstance() {
        return f2151h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.bytedance.apm6.disk.d a(com.bytedance.apm6.disk.config.a aVar) {
        if (aVar == null || this.m) {
            return null;
        }
        this.t = aVar;
        e();
        this.q = b(aVar.getIgnoredRelativePaths());
        this.r = b(aVar.getCustomRelativePaths());
        this.z = b(aVar.a());
        return a();
    }

    public void setListener(com.bytedance.apm.listener.e eVar) {
        this.y = eVar;
    }

    private com.bytedance.apm6.disk.d a() {
        ArrayList arrayList = new ArrayList();
        a(new File(this.f2152i), arrayList);
        a(new File(this.k), arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (a aVar : arrayList) {
            boolean z = true;
            Iterator<a> it = aVar.nextDisk.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().isFolder) {
                        z = false;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z) {
                arrayList2.remove(aVar);
            }
        }
        arrayList.removeAll(arrayList2);
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            Iterator<a> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.disk.b.TAG, "diskInfoNodes:" + it2.next().toJson());
            }
        }
        ak<b> akVar = new ak<>(this.t.getReportMaxTopCount());
        ak<b> akVar2 = new ak<>(this.t.getReportExceptionDirMaxCount());
        ak<b> akVar3 = new ak<>(this.t.getReportMaxOutdatedCount());
        List<String> arrayList3 = new ArrayList<>();
        arrayList3.add(this.f2152i);
        arrayList3.add(this.f2153j);
        a(a(this.f2152i, akVar, akVar2, akVar3, arrayList3));
        arrayList3.clear();
        arrayList3.add(this.l);
        arrayList3.add(this.k);
        a(a(this.k, akVar, akVar2, akVar3, arrayList3));
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            Iterator<b> it3 = akVar.sortedList().iterator();
            while (it3.hasNext()) {
                com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.disk.b.TAG, "fileListTopK:" + it3.next().toJSON());
            }
            Iterator<b> it4 = akVar2.sortedList().iterator();
            while (it4.hasNext()) {
                com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.disk.b.TAG, "dirListTopK:" + it4.next().toJSON());
            }
            Iterator<b> it5 = akVar3.sortedList().iterator();
            while (it5.hasNext()) {
                com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.disk.b.TAG, "outdatedListTopK:" + it5.next().toJSON());
            }
            com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.disk.b.TAG, "insideDataSize:" + this.u + " outsideDataSize:" + this.v + " insideCacheSize:" + this.w + " outsideCacheSize:" + this.x);
        }
        c(arrayList);
        return a(akVar, akVar2, akVar3, arrayList);
    }

    private void a(List<c> list) {
        if (g.isEmpty(list)) {
            return;
        }
        for (c cVar : list) {
            if (TextUtils.equals(cVar.f2159b, this.f2152i)) {
                this.u = cVar.size;
            } else if (TextUtils.equals(cVar.f2159b, this.k)) {
                this.v = cVar.size;
            } else if (TextUtils.equals(cVar.f2159b, this.f2153j)) {
                this.w = cVar.size;
            } else if (TextUtils.equals(cVar.f2159b, this.l)) {
                this.x = cVar.size;
            }
        }
    }

    private com.bytedance.apm6.disk.d a(ak<b> akVar, ak<b> akVar2, ak<b> akVar3, List<a> list) {
        try {
            long j2 = this.u + this.v;
            long j3 = this.w + this.x;
            long totalSpace = Environment.getDataDirectory().getTotalSpace() + Environment.getRootDirectory().getTotalSpace();
            long freeSpace = Environment.getDataDirectory().getFreeSpace();
            long min = Math.min(j2, (long) f2148e);
            long min2 = Math.min(j3, (long) f2148e);
            if (min < 0) {
                return null;
            }
            if (this.y != null) {
                this.y.onDetectException(this.t.getDiskThresholdBytes(), min, b(akVar), b(akVar2), b(akVar3));
            }
            Pair<Long, Long> b2 = b();
            long c2 = c();
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.disk.b.TAG, "appUsage:" + c2);
            }
            return new com.bytedance.apm6.disk.d(j2, min2, totalSpace, freeSpace, c2, ((Long) b2.first).longValue(), ((Long) b2.second).longValue(), a(c2, new BigDecimal(totalSpace)), a(akVar), a(akVar2), a(akVar3), a(list, min));
        } catch (Throwable th) {
            Log.e(com.bytedance.apm6.disk.b.TAG, "error", th);
            return null;
        }
    }

    private Pair<Long, Long> b() {
        try {
            long j2 = 0;
            if (Build.VERSION.SDK_INT >= 26) {
                StorageStatsManager storageStatsManager = (StorageStatsManager) com.bytedance.apm6.foundation.context.a.getContext().getSystemService("storagestats");
                StorageManager storageManager = (StorageManager) com.bytedance.apm6.foundation.context.a.getContext().getSystemService(Scraps.STORAGE);
                long j3 = 0;
                for (StorageVolume storageVolume : storageManager.getStorageVolumes()) {
                    String uuid = storageVolume.getUuid();
                    UUID fromString = uuid == null ? StorageManager.UUID_DEFAULT : UUID.fromString(uuid);
                    j2 += storageStatsManager.getTotalBytes(fromString);
                    j3 += storageManager.getAllocatableBytes(fromString);
                }
                return new Pair<>(Long.valueOf(((j2 / 1000) / 1000) * 1024 * 1024), Long.valueOf(((j3 / 1000) / 1000) * 1024 * 1024));
            } else if (Build.VERSION.SDK_INT >= 24) {
                List<StorageVolume> storageVolumes = ((StorageManager) com.bytedance.apm6.foundation.context.a.getContext().getSystemService(Scraps.STORAGE)).getStorageVolumes();
                if (g.isEmpty(storageVolumes)) {
                    return null;
                }
                long j4 = 0;
                for (StorageVolume storageVolume2 : storageVolumes) {
                    File file = (File) a(storageVolume2.getClass(), "getPathFile", new Class[0]).invoke(storageVolume2, new Object[0]);
                    j2 += file.getTotalSpace();
                    j4 += file.getFreeSpace();
                }
                return new Pair<>(Long.valueOf(((j2 / 1000) / 1000) * 1024 * 1024), Long.valueOf(((j4 / 1000) / 1000) * 1024 * 1024));
            } else {
                StorageManager storageManager2 = (StorageManager) com.bytedance.apm6.foundation.context.a.getContext().getSystemService(Scraps.STORAGE);
                long j5 = 0;
                for (Object obj : (List) a(storageManager2.getClass(), "getVolumes", new Class[0]).invoke(storageManager2, new Object[0])) {
                    File file2 = (File) a(obj.getClass(), "getPathFile", new Class[0]).invoke(obj, new Object[0]);
                    j5 += file2.getFreeSpace();
                    j2 += file2.getFreeSpace();
                }
                return new Pair<>(Long.valueOf(j2), Long.valueOf(j5));
            }
        } catch (Throwable unused) {
            return new Pair<>(Long.valueOf(com.bytedance.apm6.util.d.getTotalStorageSize(Environment.getDataDirectory().getPath()) + com.bytedance.apm6.util.d.getTotalStorageSize(Environment.getRootDirectory().getPath())), Long.valueOf(com.bytedance.apm6.util.d.getAvailableStorage(Environment.getDataDirectory().getPath())));
        }
    }

    private static Method a(Class cls, String str, Class<?>... clsArr) {
        try {
            Method method = (Method) Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class).invoke(cls, str, clsArr);
            method.setAccessible(true);
            return method;
        } catch (Exception unused) {
            return null;
        }
    }

    private long c() {
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                Application context = com.bytedance.apm6.foundation.context.a.getContext();
                String packageName = context.getPackageName();
                StorageStats queryStatsForUid = ((StorageStatsManager) context.getSystemService("storagestats")).queryStatsForUid(((StorageManager) context.getSystemService(Scraps.STORAGE)).getUuidForPath(new File(context.getDataDir().getParent(), packageName)), context.getPackageManager().getApplicationInfo(packageName, 128).uid);
                if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                    Log.e(com.bytedance.apm6.disk.b.TAG, "storageStats.getAppBytes():" + queryStatsForUid.getAppBytes());
                }
                return queryStatsForUid.getAppBytes();
            } catch (Throwable unused) {
            }
        }
        try {
            long b2 = b(new File(com.bytedance.apm6.foundation.context.a.getContext().getPackageManager().getApplicationInfo(com.bytedance.apm6.foundation.context.a.getContext().getPackageName(), 0).sourceDir).getParentFile());
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                Log.e(com.bytedance.apm6.disk.b.TAG, "storageStats.getAppBytes():" + (d() + b2 + this.u + this.v));
            }
            return b2 + d() + this.u + this.v;
        } catch (Exception unused2) {
            return 0L;
        }
    }

    private long d() {
        long j2 = 0;
        if (Build.VERSION.SDK_INT >= 21) {
            for (File file : com.bytedance.apm6.foundation.context.a.getContext().getApplicationContext().getExternalMediaDirs()) {
                j2 += b(file);
            }
        }
        return j2;
    }

    private JSONArray a(List<a> list, long j2) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        BigDecimal bigDecimal = new BigDecimal(j2);
        JSONArray jSONArray = new JSONArray();
        for (a aVar : list) {
            if (a(aVar)) {
                aVar.sizeRate = 0.0f;
            } else {
                aVar.sizeRate = a(aVar.size, bigDecimal);
            }
            List<a> list2 = aVar.nextDisk;
            if (list2 != null && list2.size() > 0) {
                for (a aVar2 : list2) {
                    if (a(aVar2)) {
                        aVar2.sizeRate = 0.0f;
                    } else {
                        aVar2.sizeRate = a(aVar2.size, bigDecimal);
                    }
                }
            }
            jSONArray.put(aVar.toJson());
        }
        return jSONArray;
    }

    private float a(long j2, BigDecimal bigDecimal) {
        return (float) new BigDecimal(j2).divide(bigDecimal, 4, 4).doubleValue();
    }

    private boolean a(a aVar) {
        for (String str : this.q) {
            if (aVar.path.contains(str)) {
                return true;
            }
        }
        return false;
    }

    private List<String> b(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (g.isEmpty(list)) {
            return arrayList;
        }
        for (String str : list) {
            if (str.contains(n)) {
                arrayList.add(str.replace(n, this.f2152i));
            } else if (str.contains(o)) {
                arrayList.add(str.replace(o, this.k));
            }
        }
        return arrayList;
    }

    private static List<String> b(ak<? extends b> akVar) {
        if (akVar == null) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        for (b bVar : akVar.sortedList()) {
            linkedList.add(bVar.f2156b);
        }
        return linkedList;
    }

    JSONArray a(ak<b> akVar) {
        JSONArray jSONArray = new JSONArray();
        for (b bVar : akVar.sortedList()) {
            JSONObject json = bVar.toJSON();
            if (json != null) {
                jSONArray.put(json);
            }
        }
        return jSONArray;
    }

    private List<c> a(String str, ak<b> akVar, ak<b> akVar2, ak<b> akVar3, List<String> list) {
        List<String> list2;
        ArrayList arrayList;
        LinkedList linkedList;
        int i2;
        int i3;
        int i4;
        File file;
        ArrayList arrayList2;
        String str2;
        List<String> list3 = list;
        ArrayList arrayList3 = new ArrayList();
        File file2 = new File(str);
        c cVar = new c();
        cVar.f2159b = str;
        cVar.parent = new c();
        File[] listFiles = file2.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return null;
        }
        if (list3.contains(file2.getAbsolutePath())) {
            arrayList3.add(cVar);
            list3.remove(file2.getAbsolutePath());
        }
        cVar.f2160c = listFiles.length;
        LinkedList linkedList2 = new LinkedList();
        linkedList2.offer(cVar);
        while (!linkedList2.isEmpty()) {
            int size = linkedList2.size();
            int i5 = 0;
            while (i5 < size) {
                c cVar2 = (c) linkedList2.poll();
                if (cVar2 == null) {
                    list2 = list3;
                    arrayList = arrayList3;
                    linkedList = linkedList2;
                    i2 = size;
                    i3 = i5;
                } else {
                    String str3 = cVar2.f2159b;
                    File file3 = new File(str3);
                    if (!file3.exists() || a(file3)) {
                        list2 = list3;
                        arrayList = arrayList3;
                        linkedList = linkedList2;
                        i2 = size;
                        i3 = i5;
                        c.b(cVar2.parent);
                    } else if (file3.isFile()) {
                        LinkedList linkedList3 = linkedList2;
                        long length = file3.length();
                        if (b(length)) {
                            file = file3;
                            arrayList2 = arrayList3;
                            str2 = str3;
                        } else {
                            arrayList2 = arrayList3;
                            file = file3;
                            str2 = str3;
                            akVar.add(new b(str3, length, 1));
                        }
                        if (cVar2.parent == null) {
                            linkedList = linkedList3;
                            list2 = list;
                            i3 = i5;
                        } else {
                            cVar2.parent.appendChildInfo(length, akVar3, akVar2);
                            if (!cVar2.parent.f2162e) {
                                long c2 = c(file.lastModified());
                                if (c2 > 0 && a(length)) {
                                    String str4 = str2;
                                    i3 = i5;
                                    akVar3.add(new d(str4, size, 0, c2));
                                    linkedList = linkedList3;
                                    list2 = list;
                                }
                            }
                            i3 = i5;
                            linkedList = linkedList3;
                            list2 = list;
                        }
                        i2 = size;
                        arrayList = arrayList2;
                    } else {
                        ArrayList arrayList4 = arrayList3;
                        LinkedList linkedList4 = linkedList2;
                        i3 = i5;
                        list2 = list;
                        if (list2.contains(file3.getAbsolutePath())) {
                            arrayList = arrayList4;
                            arrayList.add(cVar2);
                            list2.remove(file3.getAbsolutePath());
                        } else {
                            arrayList = arrayList4;
                        }
                        File[] listFiles2 = file3.listFiles();
                        if (listFiles2 == null || listFiles2.length == 0) {
                            linkedList = linkedList4;
                            i2 = size;
                            cVar2.parent.appendChildInfo(0L, akVar3, akVar2);
                        } else {
                            cVar2.f2160c = listFiles2.length;
                            int length2 = listFiles2.length;
                            int i6 = 0;
                            while (i6 < length2) {
                                File file4 = listFiles2[i6];
                                c cVar3 = new c();
                                cVar3.parent = cVar2;
                                cVar3.f2159b = file4.getAbsolutePath();
                                if (!file4.isDirectory() || cVar2.f2162e) {
                                    i4 = size;
                                } else {
                                    i4 = size;
                                    long c3 = c(file4.lastModified());
                                    if (c3 > 0) {
                                        cVar3.f2162e = true;
                                        cVar3.f2163f = c3;
                                    }
                                }
                                LinkedList linkedList5 = linkedList4;
                                linkedList5.offer(cVar3);
                                i6++;
                                linkedList4 = linkedList5;
                                size = i4;
                            }
                            linkedList = linkedList4;
                            i2 = size;
                        }
                    }
                }
                i5 = i3 + 1;
                arrayList3 = arrayList;
                linkedList2 = linkedList;
                size = i2;
                list3 = list2;
            }
        }
        return arrayList3;
    }

    private void c(List<a> list) {
        if (g.isEmpty(this.r)) {
            return;
        }
        for (String str : this.r) {
            c(new File(str), list);
        }
    }

    private void e() {
        if (this.s) {
            return;
        }
        this.s = true;
        Application context = com.bytedance.apm6.foundation.context.a.getContext();
        try {
            context.getPackageName();
            this.f2152i = context.getFilesDir().getParent();
            this.f2153j = context.getCacheDir().getAbsolutePath();
            this.k = a(context);
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                this.l = externalCacheDir.getAbsolutePath();
            }
        } catch (Exception unused) {
            this.m = true;
        }
    }

    private String a(Context context) {
        try {
            return context.getExternalFilesDir(null).getParentFile().getAbsolutePath();
        } catch (Exception unused) {
            return null;
        }
    }

    private void a(File file, List<a> list) {
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return;
        }
        for (File file2 : listFiles) {
            b(file2, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str) {
        String b2 = b(str);
        if (b2 == null || str == null) {
            return null;
        }
        return b2 + str.substring(b2.length()).replaceAll("[^/]", "*");
    }

    private long b(File file, List<a> list) {
        long j2 = 0;
        if (file != null && file.exists() && !a(file)) {
            if (file.isFile()) {
                long length = file.length();
                if (length < this.t.getFileThresholdBytes()) {
                    return length;
                }
                a aVar = new a();
                aVar.isFolder = false;
                aVar.path = file.getAbsolutePath();
                aVar.size = length;
                list.add(aVar);
                return length;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                a aVar2 = new a();
                aVar2.isFolder = file.isDirectory();
                aVar2.path = file.getAbsolutePath();
                ArrayList arrayList = new ArrayList();
                aVar2.nextDisk = arrayList;
                list.add(aVar2);
                for (File file2 : listFiles) {
                    if (file2 != null && file2.exists() && !a(file2)) {
                        j2 += b(file2, arrayList);
                    }
                }
                aVar2.size = j2;
            }
        }
        return j2;
    }

    private void c(File file, List<a> list) {
        if (file == null || !file.exists() || a(file)) {
            return;
        }
        if (file.isFile()) {
            long length = file.length();
            a aVar = new a();
            aVar.isFolder = false;
            aVar.path = file.getAbsolutePath();
            aVar.size = length;
            aVar.reportType = "custom";
            list.add(aVar);
            return;
        }
        a aVar2 = new a();
        aVar2.isFolder = true;
        aVar2.reportType = "custom";
        aVar2.path = file.getAbsolutePath();
        aVar2.size = b(file);
        list.add(aVar2);
    }

    private boolean a(File file) {
        if (g.isEmpty(this.q)) {
            return false;
        }
        return this.q.contains(file.getAbsolutePath());
    }

    private String b(String str) {
        if (g.isEmpty(this.z) || str == null) {
            return null;
        }
        for (String str2 : this.z) {
            if (str.startsWith(str2)) {
                return str2;
            }
        }
        return null;
    }

    private long c(long j2) {
        long currentTimeMillis = System.currentTimeMillis() - j2;
        if (currentTimeMillis < this.t.getOutdatedIntervalMs() || currentTimeMillis >= 62899200000L) {
            return 0L;
        }
        return currentTimeMillis;
    }

    private long b(File file) {
        File[] listFiles;
        long length;
        long j2 = 0;
        try {
            listFiles = file.listFiles();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (listFiles != null && listFiles.length != 0) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    length = b(file2);
                } else {
                    length = file2.length();
                }
                j2 += length;
            }
            return j2;
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DiskStatistics.java */
    /* loaded from: classes.dex */
    public class a {
        public static final String TYPE_CUSTOM = "custom";
        public static final String TYPE_NORMAL = "normal";
        public boolean isFolder;
        public List<a> nextDisk;
        public String path;
        public String reportType;
        public long size;
        public float sizeRate;

        private a() {
            this.reportType = "normal";
            this.nextDisk = new ArrayList();
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("path", a());
                jSONObject.put("size", this.size);
                jSONObject.put("size_rate", this.sizeRate);
                jSONObject.put("is_folder", this.isFolder);
                jSONObject.put("report_type", this.reportType);
                if (!this.nextDisk.isEmpty()) {
                    JSONArray jSONArray = new JSONArray();
                    for (a aVar : this.nextDisk) {
                        jSONArray.put(aVar.toJson());
                    }
                    jSONObject.put("next_disk", jSONArray);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }

        private String a() {
            String str = this.path;
            String a2 = e.this.a(str);
            if (a2 != null) {
                str = a2;
            }
            if (str.contains(e.this.f2152i)) {
                return str.replace(e.this.f2152i, e.n);
            }
            return str.contains(e.this.k) ? str.replace(e.this.k, e.o) : str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DiskStatistics.java */
    /* loaded from: classes.dex */
    public class b implements Comparable {

        /* renamed from: b  reason: collision with root package name */
        private String f2156b;

        /* renamed from: c  reason: collision with root package name */
        private int f2157c;
        public long size;

        public b() {
        }

        public b(String str, long j2, int i2) {
            this.f2156b = str;
            this.size = j2;
            this.f2157c = i2;
        }

        public JSONObject toJSON() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", a());
                jSONObject.put("size", this.size);
                int i2 = this.f2157c;
                if (i2 > 0) {
                    jSONObject.put("num", i2);
                }
                return jSONObject;
            } catch (Exception unused) {
                return null;
            }
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            long j2 = this.size;
            long j3 = ((b) obj).size;
            if (j2 == j3) {
                return 0;
            }
            return j2 > j3 ? 1 : -1;
        }

        private String a() {
            String str = this.f2156b;
            String a2 = e.this.a(str);
            if (a2 != null) {
                str = a2;
            }
            if (str.contains(e.this.f2152i)) {
                return str.replace(e.this.f2152i, e.n);
            }
            return str.contains(e.this.k) ? str.replace(e.this.k, e.o) : str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DiskStatistics.java */
    /* loaded from: classes.dex */
    public class d extends b {

        /* renamed from: c  reason: collision with root package name */
        private final String f2166c;

        /* renamed from: d  reason: collision with root package name */
        private int f2167d;

        /* renamed from: e  reason: collision with root package name */
        private long f2168e;
        public long size;

        public d(String str, long j2, int i2, long j3) {
            super();
            this.f2166c = str;
            this.size = j2;
            this.f2167d = i2;
            this.f2168e = j3;
        }

        @Override // com.bytedance.apm6.disk.e.b
        public JSONObject toJSON() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", a());
                jSONObject.put("size", this.size);
                int i2 = this.f2167d;
                if (i2 > 0) {
                    jSONObject.put("num", i2);
                }
                jSONObject.put(i.PERF_DISK_FILE_OUTDATE_INTERVAL, this.f2168e);
                return jSONObject;
            } catch (Exception unused) {
                return null;
            }
        }

        @Override // com.bytedance.apm6.disk.e.b, java.lang.Comparable
        public int compareTo(Object obj) {
            long j2 = this.f2168e;
            long j3 = ((d) obj).f2168e;
            if (j2 == j3) {
                return 0;
            }
            return j2 > j3 ? 1 : -1;
        }

        private String a() {
            String str = this.f2166c;
            String a2 = e.this.a(str);
            if (a2 != null) {
                str = a2;
            }
            if (str.contains(e.this.f2152i)) {
                return str.replace(e.this.f2152i, e.n);
            }
            return str.contains(e.this.k) ? str.replace(e.this.k, e.o) : str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DiskStatistics.java */
    /* loaded from: classes.dex */
    public class c {

        /* renamed from: b  reason: collision with root package name */
        private String f2159b;

        /* renamed from: c  reason: collision with root package name */
        private int f2160c;

        /* renamed from: d  reason: collision with root package name */
        private int f2161d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f2162e;

        /* renamed from: f  reason: collision with root package name */
        private long f2163f;

        /* renamed from: g  reason: collision with root package name */
        private boolean f2164g;
        public c parent;
        public long size;

        private c() {
        }

        static /* synthetic */ int b(c cVar) {
            int i2 = cVar.f2160c;
            cVar.f2160c = i2 - 1;
            return i2;
        }

        public boolean isFull() {
            return this.f2161d == this.f2160c;
        }

        public void appendChildInfo(long j2, ak<b> akVar, ak<b> akVar2) {
            this.size += j2;
            this.f2161d++;
            if (this.parent == null || !isFull()) {
                return;
            }
            if (this.f2164g) {
                this.parent.f2164g = true;
            }
            if (this.size >= e.this.t.getFolderThresholdBytes() && !this.f2164g) {
                if (!e.this.b(this.size)) {
                    akVar2.add(new b(this.f2159b, this.size, this.f2160c));
                }
                this.parent.f2164g = true;
            }
            this.parent.appendChildInfo(this.size, akVar, akVar2);
            if (this.f2162e && e.this.a(this.size)) {
                e eVar = e.this;
                String str = this.f2159b;
                long j3 = this.size;
                int i2 = this.f2160c;
                akVar.add(new d(str, j3, i2, i2));
            }
        }
    }
}
