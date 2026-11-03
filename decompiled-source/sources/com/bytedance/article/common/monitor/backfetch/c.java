package com.bytedance.article.common.monitor.backfetch;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.bytedance.apm.util.i;
import com.bytedance.apm.util.x;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* compiled from: PermissionFetch.java */
/* loaded from: classes.dex */
public class c {
    public static final String PERMISSION_FILE_BACK_TYPE = "permission_back";

    /* renamed from: a  reason: collision with root package name */
    private static long f3622a;

    /* renamed from: b  reason: collision with root package name */
    private static String[] f3623b;

    public static Map<String, Boolean> checkAppPermissions(Context context) {
        HashMap hashMap = new HashMap();
        String[] strArr = f3623b;
        if (strArr != null) {
            for (String str : strArr) {
                hashMap.put(str, Boolean.valueOf(x.checkSelfPermission(context, str)));
            }
        }
        return hashMap;
    }

    public static boolean writePermissionInfo(Context context) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        if (x.isGrantSDCardWritePermission(context)) {
            try {
                f3623b = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
                String str = i.getExternalRootDir(context).getAbsolutePath() + File.separator + "backfetch" + File.separator + "permissioninfo";
                BufferedWriter bufferedWriter2 = null;
                try {
                    File file = new File(str);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    fileWriter = new FileWriter(str + File.separator + a());
                    try {
                        try {
                            bufferedWriter = new BufferedWriter(fileWriter);
                        } catch (IOException e2) {
                            e = e2;
                        }
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (IOException e3) {
                    e = e3;
                    fileWriter = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileWriter = null;
                }
                try {
                    for (Map.Entry<String, Boolean> entry : checkAppPermissions(context).entrySet()) {
                        bufferedWriter.write(String.format("permission: %s, result: %b\n", entry.getKey(), entry.getValue()));
                    }
                    try {
                        bufferedWriter.close();
                        fileWriter.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    return true;
                } catch (IOException e5) {
                    e = e5;
                    bufferedWriter2 = bufferedWriter;
                    e.printStackTrace();
                    if (bufferedWriter2 != null) {
                        try {
                            bufferedWriter2.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                            return false;
                        }
                    }
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedWriter2 = bufferedWriter;
                    if (bufferedWriter2 != null) {
                        try {
                            bufferedWriter2.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                            throw th;
                        }
                    }
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    throw th;
                }
            } catch (PackageManager.NameNotFoundException e8) {
                e8.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private static String a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String versionName = b.getVersionName(com.bytedance.apm.c.getHeader());
        return TextUtils.isEmpty(versionName) ? String.format("%s_permissionInfo", simpleDateFormat.format(new Date(System.currentTimeMillis()))) : String.format("%s_permissionInfo_%s", simpleDateFormat.format(new Date(System.currentTimeMillis())), versionName);
    }

    public static void uploadPermissionFile(Context context) {
        if (b.checkFetchInterval(f3622a, System.currentTimeMillis()) && writePermissionInfo(context)) {
            f3622a = System.currentTimeMillis();
            com.bytedance.article.common.monitor.file.b.registerFileCallBack(PERMISSION_FILE_BACK_TYPE, new com.bytedance.article.common.monitor.file.c() { // from class: com.bytedance.article.common.monitor.backfetch.c.1
                @Override // com.bytedance.article.common.monitor.file.c
                public void notifyUploadBegin(String str) {
                }

                @Override // com.bytedance.article.common.monitor.file.c
                public void notifyUploadEnd(String str, boolean z) {
                }

                @Override // com.bytedance.article.common.monitor.file.c
                public List<String> getUploadFileList() {
                    ArrayList arrayList = new ArrayList();
                    File file = new File(i.getExternalRootDir(com.bytedance.apm.c.getContext()).getAbsolutePath() + File.separator + "backfetch" + File.separator + "permissioninfo");
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
