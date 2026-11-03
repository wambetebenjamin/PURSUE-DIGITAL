package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class eq {

    /* renamed from: a  reason: collision with root package name */
    private static final Pattern f16792a = Pattern.compile("[0-9]+-(NAM|LFH)\\.dat");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List a(File file, File file2) throws IOException {
        File[] fileArr;
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file2.listFiles(new FilenameFilter() { // from class: com.google.android.play.core.assetpacks.ep
            @Override // java.io.FilenameFilter
            public final boolean accept(File file3, String str) {
                boolean matches;
                matches = eq.f16792a.matcher(str).matches();
                return matches;
            }
        });
        if (listFiles != null) {
            File[] fileArr2 = new File[listFiles.length];
            int i2 = 0;
            while (true) {
                int length = listFiles.length;
                if (i2 >= length) {
                    fileArr = fileArr2;
                    break;
                }
                File file3 = listFiles[i2];
                int parseInt = Integer.parseInt(file3.getName().split("-")[0]);
                if (parseInt > length || fileArr2[parseInt] != null) {
                    break;
                }
                fileArr2[parseInt] = file3;
                i2++;
            }
            throw new ck("Metadata folder ordering corrupt.");
        }
        fileArr = new File[0];
        for (File file4 : fileArr) {
            arrayList.add(file4);
            if (file4.getName().contains("LFH")) {
                FileInputStream fileInputStream = new FileInputStream(file4);
                try {
                    eu b2 = new bw(fileInputStream).b();
                    if (b2.c() != null) {
                        File file5 = new File(file, b2.c());
                        if (!file5.exists()) {
                            throw new ck(String.format("Missing asset file %s during slice reconstruction.", file5.getCanonicalPath()));
                        }
                        arrayList.add(file5);
                        fileInputStream.close();
                    } else {
                        throw new ck("Metadata files corrupt. Could not read local file header.");
                    }
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
        }
        return arrayList;
    }
}
