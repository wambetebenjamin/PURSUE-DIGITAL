package com.bytedance.article.common.monitor.backfetch;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.apm.util.i;
import com.bytedance.apm.util.x;
import com.bytedance.librarian.c;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/* compiled from: SDCardDirFetch.java */
/* loaded from: classes.dex */
public class e {
    public static final String SD_CARD_DIR_BACK_TYPE = "sd_card_dir_back";

    /* renamed from: a  reason: collision with root package name */
    private static long f3627a;

    public static boolean writeSDCardInfo(Context context) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        if (x.isGrantSDCardWritePermission(context)) {
            String str = i.getExternalRootDir(com.bytedance.apm.c.getContext()).getAbsolutePath() + File.separator + "backfetch" + File.separator + "sdcardinfo" + File.separator + "tmp";
            BufferedWriter bufferedWriter2 = null;
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                fileWriter = new FileWriter(str + File.separator + "sdcardinfo.txt");
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
                a(i.getExternalRootDir(context), 0, bufferedWriter);
                try {
                    bufferedWriter.close();
                    fileWriter.close();
                    return true;
                } catch (IOException e4) {
                    e4.printStackTrace();
                    return true;
                }
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
        }
        return false;
    }

    public static boolean writeAppDataInfo(Context context) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        if (x.isGrantSDCardWritePermission(context)) {
            String str = i.getExternalRootDir(context).getAbsolutePath() + File.separator + "backfetch" + File.separator + "sdcardinfo" + File.separator + "tmp";
            BufferedWriter bufferedWriter2 = null;
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                fileWriter = new FileWriter(str + File.separator + "appDataInfo.txt");
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
                a(context.getCacheDir().getParentFile(), 0, bufferedWriter);
                try {
                    bufferedWriter.close();
                    fileWriter.close();
                    return true;
                } catch (IOException e4) {
                    e4.printStackTrace();
                    return true;
                }
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
        }
        return false;
    }

    private static boolean a(Context context) {
        boolean writeSDCardInfo = writeSDCardInfo(context);
        if (writeSDCardInfo) {
            boolean writeAppDataInfo = writeAppDataInfo(context);
            if (writeAppDataInfo) {
                File file = new File(i.getExternalRootDir(context).getAbsolutePath() + File.separator + "backfetch" + File.separator + "sdcardinfo" + File.separator + "tmp");
                try {
                    i.compress(file.getParentFile().getAbsolutePath() + File.separator + a(), file.getAbsolutePath());
                    i.removeDir(file.getAbsolutePath());
                    return true;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return false;
                }
            }
            return writeAppDataInfo;
        }
        return writeSDCardInfo;
    }

    private static String a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String versionName = b.getVersionName(com.bytedance.apm.c.getHeader());
        return TextUtils.isEmpty(versionName) ? String.format("%s_sdcardinfo.zip", simpleDateFormat.format(new Date(System.currentTimeMillis()))) : String.format("%s_sdcardinfo_%s.zip", simpleDateFormat.format(new Date(System.currentTimeMillis())), versionName);
    }

    @Deprecated
    public static void walkdir(File file, BufferedWriter bufferedWriter) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i2 = 0; i2 < listFiles.length; i2++) {
                if (listFiles[i2].isDirectory() && listFiles[i2].getPath().indexOf("/.") == -1) {
                    walkdir(listFiles[i2], bufferedWriter);
                } else if (!listFiles[i2].getName().startsWith(c.a.DOT) && listFiles[i2].canRead()) {
                    bufferedWriter.write(String.format("file Name: %s, file size: %s\n", listFiles[i2].getAbsolutePath(), i.getFormatSize(listFiles[i2].length())));
                }
            }
        }
    }

    public static void uplaodSDCardDirFile(Context context) {
        if (b.checkFetchInterval(f3627a, System.currentTimeMillis()) && a(context)) {
            f3627a = System.currentTimeMillis();
            com.bytedance.article.common.monitor.file.b.registerFileCallBack(SD_CARD_DIR_BACK_TYPE, new com.bytedance.article.common.monitor.file.c() { // from class: com.bytedance.article.common.monitor.backfetch.e.1
                @Override // com.bytedance.article.common.monitor.file.c
                public void notifyUploadBegin(String str) {
                }

                @Override // com.bytedance.article.common.monitor.file.c
                public void notifyUploadEnd(String str, boolean z) {
                }

                @Override // com.bytedance.article.common.monitor.file.c
                public List<String> getUploadFileList() {
                    ArrayList arrayList = new ArrayList();
                    File file = new File(i.getExternalRootDir(com.bytedance.apm.c.getContext()).getAbsolutePath() + File.separator + "backfetch" + File.separator + "sdcardinfo");
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

    @Deprecated
    protected static void a(File file, String str, int i2, BufferedWriter bufferedWriter) {
        String str2;
        String str3;
        if (!file.isDirectory()) {
            if (file.getName().startsWith(c.a.DOT) || !file.canRead()) {
                return;
            }
            bufferedWriter.write(file.getName());
            bufferedWriter.write(String.format("(size: %s)", i.getFormatSize(file.length())));
            bufferedWriter.newLine();
            return;
        }
        File[] listFiles = file.listFiles();
        for (int i3 = 0; i3 < listFiles.length; i3++) {
            String str4 = "";
            if (i2 > 0) {
                if (listFiles.length - i3 > 1) {
                    str3 = str + "  |";
                } else {
                    str3 = str + "   ";
                }
                str2 = str3;
                str4 = str + "  |__";
            } else {
                str2 = "";
            }
            if (!listFiles[i3].getName().startsWith(c.a.DOT) && listFiles[i3].canRead()) {
                bufferedWriter.write(str4 + listFiles[i3].getName());
                if (!listFiles[i3].isDirectory()) {
                    bufferedWriter.write(String.format("(size: %s)", i.getFormatSize(listFiles[i3].length())));
                }
                bufferedWriter.newLine();
            }
            if (listFiles[i3].isDirectory() && listFiles[i3].getPath().indexOf("/.") == -1) {
                a(listFiles[i3], str2, i2 + 1, bufferedWriter);
            }
        }
    }

    protected static void a(File file, int i2, BufferedWriter bufferedWriter) {
        if (!file.isDirectory()) {
            if (file.getName().startsWith(c.a.DOT) || !file.canRead()) {
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                bufferedWriter.write("\t");
            }
            bufferedWriter.write(String.format("<file name=\"%s\" size=\"%s\"></file>", file.getName(), i.getFormatSize(file.length())));
            bufferedWriter.newLine();
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            bufferedWriter.write("\t");
        }
        bufferedWriter.write(String.format("<dir name=\"%s\">", file.getName()));
        bufferedWriter.newLine();
        for (int i5 = 0; i5 < listFiles.length; i5++) {
            if (!listFiles[i5].getName().startsWith(c.a.DOT) && listFiles[i5].canRead()) {
                if (!listFiles[i5].isDirectory()) {
                    for (int i6 = 0; i6 < i2 + 1; i6++) {
                        bufferedWriter.write("\t");
                    }
                    bufferedWriter.write(String.format("<file name=\"%s\" size=\"%s\"></file>", listFiles[i5].getName(), i.getFormatSize(listFiles[i5].length())));
                    bufferedWriter.newLine();
                } else if (listFiles[i5].getPath().indexOf("/.") == -1) {
                    a(listFiles[i5], i2 + 1, bufferedWriter);
                }
            }
        }
        for (int i7 = 0; i7 < i2; i7++) {
            bufferedWriter.write("\t");
        }
        bufferedWriter.write("</dir>");
        bufferedWriter.newLine();
    }
}
