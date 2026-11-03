package com.ttnet.org.chromium.net;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.bytedance.librarian.c;
import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
/* loaded from: classes9.dex */
public class MimeTypeFilter implements FileFilter {
    private boolean mAcceptAllMimeTypes;
    private boolean mAcceptDirectory;
    private MimeTypeMap mMimeTypeMap;
    private HashSet<String> mExtensions = new HashSet<>();
    private HashSet<String> mMimeTypes = new HashSet<>();
    private HashSet<String> mMimeSupertypes = new HashSet<>();

    public MimeTypeFilter(List<String> list, boolean z) {
        for (String str : list) {
            String lowerCase = str.trim().toLowerCase(Locale.US);
            if (lowerCase.startsWith(c.a.DOT)) {
                this.mExtensions.add(lowerCase.substring(1));
            } else if (lowerCase.equals("*/*")) {
                this.mAcceptAllMimeTypes = true;
            } else if (lowerCase.endsWith("/*")) {
                this.mMimeSupertypes.add(lowerCase.substring(0, lowerCase.length() - 2));
            } else if (lowerCase.contains(c.a.SEPARATOR)) {
                this.mMimeTypes.add(lowerCase);
            }
        }
        this.mMimeTypeMap = MimeTypeMap.getSingleton();
        this.mAcceptDirectory = z;
    }

    public boolean accept(Uri uri, String str) {
        if (uri != null) {
            String lowerCase = MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase(Locale.US);
            if (this.mExtensions.contains(lowerCase)) {
                return true;
            }
            if (str == null) {
                str = getMimeTypeFromExtension(lowerCase);
            }
        }
        if (str != null) {
            return this.mAcceptAllMimeTypes || this.mMimeTypes.contains(str) || this.mMimeSupertypes.contains(getMimeSupertype(str));
        }
        return false;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return this.mAcceptDirectory;
        }
        return accept(Uri.fromFile(file), null);
    }

    private String getMimeTypeFromExtension(String str) {
        String mimeTypeFromExtension = this.mMimeTypeMap.getMimeTypeFromExtension(str);
        if (mimeTypeFromExtension != null) {
            return mimeTypeFromExtension.toLowerCase(Locale.US);
        }
        return null;
    }

    private static String getMimeSupertype(String str) {
        return str.split(c.a.SEPARATOR, 2)[0];
    }
}
