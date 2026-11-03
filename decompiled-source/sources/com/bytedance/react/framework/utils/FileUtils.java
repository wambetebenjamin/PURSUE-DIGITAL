package com.bytedance.react.framework.utils;

import android.os.Build;
import com.bytedance.librarian.c;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
/* loaded from: classes2.dex */
public class FileUtils {
    public static boolean delete(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        return deleteTraversals(file);
    }

    private static boolean deleteTraversals(File file) {
        boolean z;
        File[] listFiles;
        if (file == null || !file.exists()) {
            return true;
        }
        if (!file.isDirectory() || (listFiles = file.listFiles()) == null) {
            z = true;
        } else {
            z = true;
            for (File file2 : listFiles) {
                z = z && deleteTraversals(file2);
            }
        }
        return z && file.delete();
    }

    public static void move(File file, File file2) {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }
        file2.mkdir();
        if (file.renameTo(new File(file2, file.getName()))) {
            return;
        }
        try {
            copy(file, file2);
        } catch (Exception e2) {
            throw new RuntimeException("move file failed：" + e2.getMessage(), e2);
        }
    }

    public static void copy(File file, File file2) {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }
        if (file.getParent().equals(file2.getAbsolutePath())) {
            return;
        }
        if (file2.getAbsolutePath().startsWith(file.getAbsolutePath())) {
            throw new RuntimeException("copy failed，src:" + file.getAbsolutePath() + " dest:" + file2.getAbsolutePath());
        }
        file2.mkdir();
        if (file.isFile()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file2, file.getName()));
            try {
                copyStream(fileInputStream, fileOutputStream);
            } finally {
                CloseableUtils.close(fileInputStream);
                CloseableUtils.close(fileOutputStream);
            }
        } else if (file.isDirectory()) {
            File file3 = new File(file2, file.getName());
            file3.mkdirs();
            for (File file4 : file.listFiles()) {
                copy(file4, file3);
            }
        }
    }

    public static long copyStream(InputStream inputStream, OutputStream outputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        byte[] bArr = new byte[1024];
        int i2 = 0;
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                i2 += read;
                bufferedOutputStream.write(bArr, 0, read);
            } else {
                bufferedOutputStream.flush();
                return i2;
            }
        }
    }

    public static List<File> orderByTime(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return null;
        }
        List<File> asList = Arrays.asList(listFiles);
        final HashMap hashMap = new HashMap();
        for (File file2 : asList) {
            hashMap.put(file2, Long.valueOf(file2.lastModified()));
        }
        Collections.sort(asList, new Comparator<File>() { // from class: com.bytedance.react.framework.utils.FileUtils.1
            @Override // java.util.Comparator
            public int compare(File file3, File file4) {
                if (file3 == null && file4 == null) {
                    return 0;
                }
                if (file3 == null) {
                    return 1;
                }
                if (file4 == null) {
                    return -1;
                }
                if (Build.VERSION.SDK_INT >= 19) {
                    return Long.compare(((Long) hashMap.get(file3)).longValue(), ((Long) hashMap.get(file4)).longValue());
                }
                int i2 = (((Long) hashMap.get(file3)).longValue() > ((Long) hashMap.get(file4)).longValue() ? 1 : (((Long) hashMap.get(file3)).longValue() == ((Long) hashMap.get(file4)).longValue() ? 0 : -1));
                if (i2 < 0) {
                    return -1;
                }
                return i2 == 0 ? 0 : 1;
            }
        });
        return asList;
    }

    public static boolean deleteDir(File file) {
        if (file.isDirectory()) {
            for (String str : file.list()) {
                if (!deleteDir(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static void copyFolder(String str, String str2) {
        File file;
        try {
            new File(str2).mkdirs();
            String[] list = new File(str).list();
            for (int i2 = 0; i2 < list.length; i2++) {
                if (str.endsWith(File.separator)) {
                    file = new File(str + list[i2]);
                } else {
                    file = new File(str + File.separator + list[i2]);
                }
                if (file.isFile()) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(str2 + c.a.SEPARATOR + file.getName());
                    byte[] bArr = new byte[65536];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    fileInputStream.close();
                }
                if (file.isDirectory()) {
                    copyFolder(str + c.a.SEPARATOR + list[i2], str2 + c.a.SEPARATOR + list[i2]);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void moveFolder(String str, String str2) {
        copyFolder(str, str2);
        deleteDir(new File(str));
    }

    public static String listChannelFile(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    return listChannelFile(listFiles[0]);
                }
                return null;
            } else if (file.length() == 0) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }

    public static long getTotalSizeOfFiles(File file) {
        if (file.isFile()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        long j2 = 0;
        if (listFiles != null) {
            for (File file2 : listFiles) {
                j2 += getTotalSizeOfFiles(file2);
            }
        }
        return j2;
    }

    public static void createDir(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                throw new RuntimeException("create dir failed: path is a file");
            }
        } else if (!file.mkdirs()) {
            throw new RuntimeException("create dir failed: " + file.getPath());
        }
    }

    public static boolean checkFileContentBroken(File file) {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2;
        long length;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        if (file != null) {
            try {
                if (file.exists()) {
                    try {
                        length = file.length();
                    } catch (IOException unused) {
                        randomAccessFile2 = null;
                    } catch (Throwable th) {
                        th = th;
                        randomAccessFile = null;
                    }
                    if (length <= 0) {
                        return false;
                    }
                    int i2 = 1024;
                    long j7 = length / 4;
                    while (true) {
                        j2 = i2;
                        if (j2 <= j7) {
                            break;
                        }
                        i2 /= 4;
                    }
                    if (i2 == 0) {
                        return false;
                    }
                    byte[] bArr = new byte[i2];
                    byte[] bArr2 = new byte[i2];
                    int i3 = 5;
                    long[] jArr = new long[5];
                    jArr[0] = 0;
                    jArr[1] = j7;
                    jArr[2] = 2 * j7;
                    jArr[3] = 3 * j7;
                    int i4 = 4;
                    jArr[4] = (length - 1) - j2;
                    Random random = new Random(1L);
                    int i5 = j7 < 2147483647L ? (int) j7 : Integer.MAX_VALUE;
                    if (i5 <= 0) {
                        return false;
                    }
                    RandomAccessFile randomAccessFile3 = new RandomAccessFile(file, DownloadFileUtils.MODE_READ);
                    int i6 = 0;
                    int i7 = 0;
                    while (i6 < i3) {
                        try {
                            int nextInt = random.nextInt(i5);
                            if (i6 == i4) {
                                long j8 = nextInt;
                                if (jArr[i6] > j8) {
                                    j6 = jArr[i6] - j8;
                                } else {
                                    j6 = jArr[i6];
                                }
                                j5 = 0;
                                j4 = j6;
                                j3 = length;
                            } else {
                                j3 = length;
                                j4 = nextInt + jArr[i6];
                                j5 = 0;
                            }
                            if (j4 < j5) {
                                j4 = j5;
                            }
                            long j9 = (j3 - j2) - 1;
                            if (j4 > j9) {
                                j4 = j9;
                            }
                            jArr[i6] = j4;
                            randomAccessFile3.seek(j4);
                            randomAccessFile3.read(bArr2);
                            if (Arrays.equals(bArr2, bArr)) {
                                i7++;
                            }
                            i6++;
                            length = j3;
                            i4 = 4;
                            i3 = 5;
                        } catch (IOException unused2) {
                            randomAccessFile2 = randomAccessFile3;
                            if (randomAccessFile2 != null) {
                                randomAccessFile2.close();
                                return false;
                            }
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            randomAccessFile = randomAccessFile3;
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException unused3) {
                                }
                            }
                            throw th;
                        }
                    }
                    if (i7 != i3) {
                        randomAccessFile3.close();
                        return false;
                    }
                    try {
                        randomAccessFile3.close();
                        return true;
                    } catch (IOException unused4) {
                        return true;
                    }
                }
            } catch (IOException unused5) {
                return false;
            }
        }
        return false;
    }
}
