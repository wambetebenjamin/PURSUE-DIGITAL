package com.meituan.robust;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.robust.monitor.PatchDataReport;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
/* loaded from: classes9.dex */
public class PatchExecutor extends Thread {
    private static int DEFAULT_BUFF_SIZE = 1024;
    private static final String TAG = "robust";
    protected Context context;
    protected ClassLoader parentClassLoader;
    protected PatchManipulate patchManipulate;
    protected RobustCallBack robustCallBack;
    protected boolean mResultSuccess = false;
    protected boolean mSkipDownload = false;
    protected boolean mSpecificLoad = false;
    protected boolean mHandleExceptPatch = false;

    /* loaded from: classes9.dex */
    public interface PatchExecuteListener {
        void onPatchExecute(boolean z);
    }

    public PatchExecutor(Context context, PatchManipulate patchManipulate, RobustCallBack robustCallBack, ClassLoader classLoader) {
        this.context = context.getApplicationContext();
        this.patchManipulate = patchManipulate;
        this.robustCallBack = robustCallBack;
        if (classLoader != null) {
            this.parentClassLoader = classLoader;
        } else {
            this.parentClassLoader = PatchExecutor.class.getClassLoader();
        }
    }

    public void setSkipDownload(boolean z) {
        this.mSkipDownload = z;
    }

    public void setSpecificLoad(boolean z) {
        this.mSpecificLoad = z;
    }

    public void setHandleExceptPatch(boolean z) {
        this.mHandleExceptPatch = z;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            applyPatchList(fetchPatchList());
            this.mResultSuccess = true;
        } catch (Throwable th) {
            this.robustCallBack.exceptionNotify(th, "PatchExecutor.run");
            this.mResultSuccess = false;
        }
    }

    protected List<Patch> fetchPatchList() {
        return this.patchManipulate.fetchPatchList(this.context);
    }

    protected void applyPatchList(List<Patch> list) {
        Patch combineVerifiedPatch;
        boolean patchTotal;
        boolean z;
        if (list == null || list.isEmpty()) {
            Log.d(TAG, " applyPatchList patches empty");
            return;
        }
        List<Patch> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        List<Patch> arrayList3 = new ArrayList<>();
        Log.d(TAG, " applyPatchList list size is " + list.size());
        boolean isMainProcess = PatchProcessUtils.isMainProcess(this.context);
        long currentTimeMillis = System.currentTimeMillis();
        for (Patch patch : list) {
            if (patch.isAppliedSuccess()) {
                Log.d(TAG, "p.isAppliedSuccess() skip " + patch.getLocalPath());
                arrayList.add(patch);
                if (!new File(patch.getTempPath()).exists() && new File(patch.getLocalPath()).exists()) {
                    try {
                        copy(patch.getLocalPath(), patch.getTempPath());
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } else if (!new File(patch.getLocalPath()).exists()) {
                    Log.i(TAG, "patch " + patch.getName() + " in alreadyAppliedPatches has no origin localPath jar, ignore it");
                }
            } else {
                if (isMainProcess) {
                    PatchDataReport.reportHotfixStart(patch.getName(), patch.getPluginVersion());
                }
                if (this.patchManipulate.ensurePatchExist(patch, !this.mSkipDownload)) {
                    if (patch != null && patch.downloadedWhenEnsure) {
                        currentTimeMillis = -1;
                    }
                    try {
                        z = verifyPatch(this.context, patch);
                    } catch (Throwable th) {
                        this.robustCallBack.exceptionNotify(th, "PatchExecutor.applyPatchList verifyPatch");
                        z = false;
                    }
                    Log.d(TAG, "verifyPatch result:" + z);
                    if (z) {
                        PatchDataReport.reportPatchVerified(true, patch.getName(), patch.getPluginVersion());
                        arrayList3.add(patch);
                        this.robustCallBack.onPatchVerified(true, patch);
                    } else {
                        PatchDataReport.reportPatchVerified(false, patch.getName(), patch.getPluginVersion());
                        this.robustCallBack.onPatchVerified(false, patch);
                        this.robustCallBack.onPatchApplied(false, patch, "verify patch failed");
                    }
                } else {
                    arrayList2.add(patch);
                }
            }
        }
        String str = null;
        if (!arrayList3.isEmpty()) {
            Log.d(TAG, "applyPatchList, verified list size is " + arrayList3.size());
            if (arrayList3.size() == list.size()) {
                Log.d(TAG, "applyPatchList, all need to applied");
            } else if (arrayList3.size() + arrayList.size() == list.size()) {
                arrayList3.addAll(arrayList);
            } else if (arrayList3.size() + arrayList.size() + arrayList2.size() == list.size()) {
                str = "has some patches not downloaded yet, notExistedPatches list size is " + arrayList2.size();
                Log.d(TAG, "applyPatchList, " + str);
            } else {
                str = "has some patch verified failed";
                Log.d(TAG, "applyPatchList, has some patch verified failed");
            }
        } else if (arrayList.size() == list.size()) {
            Log.d(TAG, "applyPatchList, skip because all patches already applied");
        } else {
            str = arrayList.size() + arrayList2.size() == list.size() ? "has some patches not downloaded yet, notExistedPatches list size is " + arrayList2.size() : "real has patch verified failed";
            Log.d(TAG, "applyPatchList, " + str);
        }
        filterVerifiedPatches(arrayList3, isMainProcess);
        Log.d(TAG, "applyPatchList, after filterVerifiedPatches, verified list size is " + arrayList3.size());
        if (arrayList3.isEmpty()) {
            combineVerifiedPatch = new Patch();
            combineVerifiedPatch.setName("patch_total");
            patchTotal = str == null;
            combineVerifiedPatch.setAppliedSuccess(patchTotal);
            deleteTempJar(arrayList);
        } else {
            combineVerifiedPatch = combineVerifiedPatch(arrayList3);
            deleteTempJar(arrayList);
            patchTotal = patchTotal(this.context, combineVerifiedPatch, arrayList3, isMainProcess);
            if (combineVerifiedPatch != null) {
                combineVerifiedPatch.setAppliedSuccess(patchTotal);
            }
            if (isMainProcess && currentTimeMillis > 0) {
                PatchDataReport.reportPatchApplyCost(System.currentTimeMillis() - currentTimeMillis, arrayList3.size());
            }
        }
        if (patchTotal) {
            if (!arrayList3.isEmpty()) {
                this.robustCallBack.onPatchApplied(true, combineVerifiedPatch, "");
            }
        } else {
            this.robustCallBack.onPatchApplied(false, combineVerifiedPatch, "patchTotal failed, verifyError:" + str);
        }
        if (this.mSpecificLoad) {
            return;
        }
        this.robustCallBack.onPatchLoadComplete();
    }

    protected void filterVerifiedPatches(List<Patch> list, boolean z) {
        RobustCallBack robustCallBack;
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            Iterator<Patch> it = list.iterator();
            while (it.hasNext()) {
                Patch next = it.next();
                if (next != null) {
                    if (!this.mHandleExceptPatch && (robustCallBack = this.robustCallBack) != null && robustCallBack.isExceptPatch(next)) {
                        it.remove();
                        next.delete(next.getTempPath());
                        Log.d(TAG, "filter except patch:" + next.getName());
                    } else if (next.isOnlySo()) {
                        handlePatchApplyResult(true, next, null, z);
                        it.remove();
                        next.delete(next.getTempPath());
                        Log.d(TAG, "filter only-so patch:" + next.getName());
                    } else if (!z && !next.isSupportSubProcess()) {
                        it.remove();
                        next.delete(next.getTempPath());
                        Log.d(TAG, "filter not support subprocess patch:" + next.getName());
                    }
                }
            }
        } catch (Throwable th) {
            Log.e(TAG, "filterVerifiedPatches", th);
        }
    }

    protected void deleteTempJar(List<Patch> list) {
        if (list == null) {
            return;
        }
        for (Patch patch : list) {
            if (patch != null) {
                File file = new File(patch.getTempPath());
                if (file.exists()) {
                    Log.d(TAG, " delete temp jar : " + patch.getTempPath());
                    file.delete();
                }
            }
        }
    }

    protected boolean verifyPatch(Context context, Patch patch) {
        if (this.patchManipulate.verifyPatch(context, patch)) {
            return true;
        }
        if (patch != null) {
            this.robustCallBack.logNotify("verifyPatch failure, patch info:id = " + patch.getName() + ",md5 = " + patch.getMd5(), "PatchExecutor.verifyPatch");
            return false;
        }
        return false;
    }

    protected Patch combineVerifiedPatch(List<Patch> list) {
        try {
            String tempPath = list.get(0).getTempPath();
            File parentFile = new File(tempPath).getParentFile();
            String md5Hex = PatchDigestUtil.md5Hex(PatchProcessUtils.getCurProcessName(this.context));
            if (md5Hex != null && md5Hex.length() > 8) {
                md5Hex = md5Hex.substring(0, 8);
            }
            int i2 = 0;
            while (i2 < list.size()) {
                int i3 = i2 + 1;
                unzipPatch(parentFile, list.get(i2).getTempPath(), i3, md5Hex);
                list.get(i2).delete(list.get(i2).getTempPath());
                i2 = i3;
            }
            String str = TextUtils.isEmpty(md5Hex) ? "patch_total_temp.jar" : "patch_total_" + md5Hex + "_temp.jar";
            packagePatchDex2Jar(tempPath, str, md5Hex);
            Patch patch = new Patch();
            File file = new File(new File(tempPath).getParentFile(), str);
            patch.setName("patch_total");
            patch.setTempPath(file.getAbsolutePath().replace("_temp.jar", ""));
            PatchDataReport.reportPatchCombined(true, null);
            Log.d(TAG, "combineVerifiedPatch success ");
            return patch;
        } catch (Throwable th) {
            Log.e(TAG, "combineVerifiedPatch", th);
            PatchDataReport.reportPatchCombined(false, Log.getStackTraceString(th));
            return null;
        }
    }

    private void doDecompressFile(File file, ZipInputStream zipInputStream) throws Exception {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bArr = new byte[DEFAULT_BUFF_SIZE];
        while (true) {
            int read = zipInputStream.read(bArr, 0, DEFAULT_BUFF_SIZE);
            if (read != -1) {
                bufferedOutputStream.write(bArr, 0, read);
            } else {
                bufferedOutputStream.close();
                return;
            }
        }
    }

    public void zipPatch(File file, ZipOutputStream zipOutputStream, String str) throws IOException {
        Log.d(TAG, "zip patch dexFile " + file.getName());
        zipOutputStream.putNextEntry(new ZipEntry(str));
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[4092];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read != -1) {
                zipOutputStream.write(bArr, 0, read);
            } else {
                fileInputStream.close();
                zipOutputStream.closeEntry();
                zipOutputStream.flush();
                return;
            }
        }
    }

    public void unzipPatch(File file, String str, int i2, String str2) {
        File file2;
        File file3 = new File(file, str2);
        if (!file3.exists()) {
            file3.mkdirs();
        }
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(new File(str)));
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    if (Constants.CLASSES_DEX_NAME.equals(nextEntry.getName()) && file3.exists() && file3.isDirectory()) {
                        if (i2 == 1) {
                            file2 = new File(file3, Constants.CLASSES_DEX_NAME);
                        } else {
                            file2 = new File(file3, "classes" + i2 + ".dex");
                        }
                        doDecompressFile(file2, zipInputStream);
                        Log.i(TAG, "doDecompressFile success");
                    }
                } else {
                    zipInputStream.close();
                    return;
                }
            }
        } catch (Throwable th) {
            Log.e(TAG, "unzipPatch Error:" + th);
            PatchDataReport.reportHotfixException("PatchExecutor.unzipPatch", th);
        }
    }

    public void packagePatchDex2Jar(String str, String str2, String str3) {
        File file = null;
        try {
            File parentFile = new File(str).getParentFile();
            File file2 = new File(parentFile, str3);
            try {
                File file3 = new File(parentFile, str2);
                if (file3.exists()) {
                    Log.e(TAG, "total patch " + str2 + " is already exists, delete");
                    file3.delete();
                }
                if (!file2.exists()) {
                    Log.w(TAG, "packagePatchDex2Jar, but destDir not exists");
                    return;
                }
                File[] listFiles = file2.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file3));
                    zipOutputStream.setLevel(0);
                    for (File file4 : listFiles) {
                        if (file4.getName().endsWith(".dex")) {
                            zipPatch(file4, zipOutputStream, file4.getName());
                            file4.delete();
                            Log.i(TAG, "delete patch dexFile");
                        }
                    }
                    zipOutputStream.close();
                    file2.delete();
                    Log.d(TAG, "packagePatchDex2Jar success");
                    return;
                }
                Log.w(TAG, "packagePatchDex2Jar, but has no dex files");
            } catch (Throwable th) {
                th = th;
                file = file2;
                Log.e(TAG, "packagePatchDex2Jar failed", th);
                PatchDataReport.reportHotfixException("PatchExecutor.packagePatchDex2Jar", th);
                PatchFileUtils.deleteDir(file);
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:123:0x025e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0230 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean patchTotal(android.content.Context r25, com.meituan.robust.Patch r26, java.util.List<com.meituan.robust.Patch> r27, boolean r28) {
        /*
            Method dump skipped, instructions count: 1138
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meituan.robust.PatchExecutor.patchTotal(android.content.Context, com.meituan.robust.Patch, java.util.List, boolean):boolean");
    }

    private boolean shouldInterceptPatchApply(Patch patch) {
        RobustCallBack robustCallBack = this.robustCallBack;
        if (robustCallBack != null) {
            try {
                return robustCallBack.shouldInterceptPatchApply(patch);
            } catch (Throwable th) {
                Log.e(TAG, "shouldInterceptPatchApply", th);
                return false;
            }
        }
        return false;
    }

    private void handlePatchApplyIntercepted(Patch patch, boolean z) {
        if (!z || patch == null) {
            return;
        }
        PatchDataReport.reportPatchApply(-1, patch.getName(), patch.getPluginVersion(), "");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void handlePatchApplyResult(boolean z, Patch patch, String str, boolean z2) {
        if (patch == null) {
            Log.e(TAG, "handlePatchApplyResult, patch is null");
        } else if (patch.isAppliedSuccess()) {
        } else {
            String name = patch.getName();
            if (z != 0 && patch.hasSo()) {
                RobustCallBack robustCallBack = this.robustCallBack;
                if (!(robustCallBack != null && robustCallBack.isPatchSoInstalledSuccess(patch))) {
                    str = "patch so not installed success";
                    z = 0;
                }
            }
            if (z != 0) {
                Log.d(TAG, "patch apply success:" + name);
            } else {
                Log.e(TAG, "patch apply failed:" + name);
            }
            if (z != 0) {
                patch.setAppliedSuccess(true);
                this.robustCallBack.onPatchApplied(true, patch, "");
            } else {
                this.robustCallBack.onPatchApplied(false, patch, str);
            }
            if (z2) {
                PatchDataReport.reportPatchApply(!z, name, patch.getPluginVersion(), str);
            }
        }
    }

    public void copy(String str, String str2) throws IOException {
        Log.d(TAG, "copy temp jar");
        Decryptor decryptor = new Decryptor();
        File file = new File(str2);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileInputStream fileInputStream = new FileInputStream(str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    decryptor.decrypt(bArr, read);
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    return;
                }
            }
        } finally {
            fileInputStream.close();
        }
    }

    public static String getFileMd5(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            String md5Hex = PatchDigestUtil.md5Hex(file);
            if (!TextUtils.isEmpty(md5Hex)) {
                return md5Hex.toLowerCase();
            }
        }
        return null;
    }

    public void deleteAllDexFilebelowL(File file) {
        File[] listFiles;
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.getName().endsWith(".dex")) {
                    file2.delete();
                }
            }
        }
    }
}
