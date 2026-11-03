package com.bytedance.article.common.monitor.backfetch;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.apm.util.i;
import com.bytedance.apm.util.x;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/* compiled from: DbFetch.java */
/* loaded from: classes.dex */
public class a {
    public static final String DB_FILE_BACK_TYPE = "db_file_back";

    /* renamed from: a  reason: collision with root package name */
    private static final String f3614a = ".db";

    /* renamed from: b  reason: collision with root package name */
    private static final String f3615b = "databases";

    /* renamed from: c  reason: collision with root package name */
    private static final String f3616c = "dbFiles";

    /* renamed from: d  reason: collision with root package name */
    private static long f3617d = 0;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f3618e = false;

    /* renamed from: f  reason: collision with root package name */
    private static String f3619f;

    /* renamed from: g  reason: collision with root package name */
    private static String f3620g;

    public static boolean copyDbFiles(Context context) {
        File[] listFiles;
        if (x.isGrantSDCardWritePermission(context)) {
            String str = context.getApplicationInfo().dataDir + File.separator + f3615b;
            String str2 = context.getApplicationInfo().dataDir + File.separator + f3616c;
            String str3 = str2 + File.separator + "/tmp";
            File file = new File(str);
            if (file.exists()) {
                for (File file2 : file.listFiles()) {
                    String name = file2.getName();
                    if (name.endsWith(f3614a) && !f3618e && !i.copyFile(file2.getAbsolutePath(), str3, name)) {
                        return false;
                    }
                    if (f3618e && !TextUtils.isEmpty(f3619f) && f3619f.equals(name) && !i.copyFile(file2.getAbsolutePath(), str3, name)) {
                        return false;
                    }
                }
            }
            try {
                i.compress(str2 + File.separator + f3620g, str3);
                i.removeDir(str3);
                File file3 = new File(str2 + File.separator + f3620g);
                if (checkSize(file3)) {
                    return true;
                }
                file3.delete();
                return generateErrorFile(str2, String.format("%s file to big", f3620g));
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean checkSize(File file) {
        return !file.exists() || file.length() <= 52428800;
    }

    public static boolean generateErrorFile(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            new File(str, String.format("DbFileError: %s", str2)).createNewFile();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    private static String a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String versionName = b.getVersionName(com.bytedance.apm.c.getHeader());
        return TextUtils.isEmpty(versionName) ? String.format("%s_db.zip", simpleDateFormat.format(new Date(System.currentTimeMillis()))) : String.format("%s_db_%s.zip", simpleDateFormat.format(new Date(System.currentTimeMillis())), versionName);
    }

    public static void uploadDBFiles(final Context context, String str) {
        if (b.checkFetchInterval(f3617d, System.currentTimeMillis())) {
            f3620g = a();
            if (!TextUtils.isEmpty(str)) {
                f3618e = true;
                f3619f = str;
            }
            if (copyDbFiles(context)) {
                f3617d = System.currentTimeMillis();
                com.bytedance.article.common.monitor.file.b.registerFileCallBack(DB_FILE_BACK_TYPE, new com.bytedance.article.common.monitor.file.c() { // from class: com.bytedance.article.common.monitor.backfetch.a.1
                    @Override // com.bytedance.article.common.monitor.file.c
                    public void notifyUploadBegin(String str2) {
                    }

                    @Override // com.bytedance.article.common.monitor.file.c
                    public List<String> getUploadFileList() {
                        ArrayList arrayList = new ArrayList();
                        File file = new File(context.getApplicationInfo().dataDir + File.separator + a.f3616c);
                        if (file.exists() && file.listFiles() != null) {
                            for (File file2 : file.listFiles()) {
                                arrayList.add(file2.getAbsolutePath());
                            }
                        }
                        return arrayList;
                    }

                    @Override // com.bytedance.article.common.monitor.file.c
                    public void notifyUploadEnd(String str2, boolean z) {
                        boolean unused = a.f3618e = false;
                        String unused2 = a.f3619f = null;
                    }
                });
            }
        }
    }
}
