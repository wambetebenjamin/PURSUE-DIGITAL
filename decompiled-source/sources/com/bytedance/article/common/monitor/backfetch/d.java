package com.bytedance.article.common.monitor.backfetch;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.apm.util.i;
import com.bytedance.apm.util.x;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
/* compiled from: PrefFetch.java */
/* loaded from: classes.dex */
public class d {
    public static final String SP_FILE_BACK_TYPE = "sp_file_back";

    /* renamed from: a  reason: collision with root package name */
    private static final String f3624a = ".xml";

    /* renamed from: b  reason: collision with root package name */
    private static long f3625b;

    public static List<String> getSharedPreferenceTags(Context context) {
        ArrayList arrayList = new ArrayList();
        File file = new File(context.getApplicationInfo().dataDir + "/shared_prefs");
        if (file.exists()) {
            for (File file2 : file.listFiles()) {
                String name = file2.getName();
                if (name.endsWith(f3624a)) {
                    arrayList.add(name.substring(0, name.length() - 4));
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static boolean copySPFiles(Context context) {
        File[] listFiles;
        if (x.isGrantSDCardWritePermission(context)) {
            String str = context.getApplicationInfo().dataDir + "/shared_prefs";
            String str2 = context.getApplicationInfo().dataDir + "/spFiles";
            String str3 = str2 + File.separator + "/tmp";
            File file = new File(str);
            if (file.exists()) {
                for (File file2 : file.listFiles()) {
                    String name = file2.getName();
                    if (name.endsWith(f3624a) && !i.copyFile(file2.getAbsolutePath(), str3, name)) {
                        return false;
                    }
                }
            }
            try {
                i.compress(str2 + File.separator + a(), str3);
                i.removeDir(str3);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private static String a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String versionName = b.getVersionName(com.bytedance.apm.c.getHeader());
        return TextUtils.isEmpty(versionName) ? String.format("%s_sp.zip", simpleDateFormat.format(new Date(System.currentTimeMillis()))) : String.format("%s_sp_%s.zip", simpleDateFormat.format(new Date(System.currentTimeMillis())), versionName);
    }

    public static void uploadSpFiles(final Context context) {
        if (b.checkFetchInterval(f3625b, System.currentTimeMillis()) && copySPFiles(context)) {
            f3625b = System.currentTimeMillis();
            com.bytedance.article.common.monitor.file.b.registerFileCallBack(SP_FILE_BACK_TYPE, new com.bytedance.article.common.monitor.file.c() { // from class: com.bytedance.article.common.monitor.backfetch.d.1
                @Override // com.bytedance.article.common.monitor.file.c
                public void notifyUploadBegin(String str) {
                }

                @Override // com.bytedance.article.common.monitor.file.c
                public void notifyUploadEnd(String str, boolean z) {
                }

                @Override // com.bytedance.article.common.monitor.file.c
                public List<String> getUploadFileList() {
                    ArrayList arrayList = new ArrayList();
                    File file = new File(context.getApplicationInfo().dataDir + "/spFiles");
                    if (file.exists() && file.listFiles() != null) {
                        for (File file2 : file.listFiles()) {
                            arrayList.add(file2.getAbsolutePath());
                        }
                    }
                    return arrayList;
                }
            });
        }
    }
}
