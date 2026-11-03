package com.android.vending.expansion.zipfile;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.android.vending.expansion.zipfile.b;
import com.bytedance.librarian.c;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class ReactNativeAPEZProvider extends ContentProvider {
    public static final int COMPLEN_IDX = 5;
    public static final int COMPTYPE_IDX = 7;
    public static final int CRC_IDX = 4;
    public static final String FILEID = "_id";
    public static final int FILEID_IDX = 0;
    public static final int FILENAME_IDX = 1;
    public static final int MOD_IDX = 3;
    private static final String NO_FILE = "N";
    public static final int UNCOMPLEN_IDX = 6;
    public static final int ZIPFILE_IDX = 2;
    private b mAPKExtensionFile;
    private boolean mInit;
    public static final String FILENAME = "ZPFN";
    public static final String ZIPFILE = "ZFIL";
    public static final String MODIFICATION = "ZMOD";
    public static final String CRC32 = "ZCRC";
    public static final String COMPRESSEDLEN = "ZCOL";
    public static final String UNCOMPRESSEDLEN = "ZUNL";
    public static final String COMPRESSIONTYPE = "ZTYP";
    public static final String[] ALL_FIELDS = {"_id", FILENAME, ZIPFILE, MODIFICATION, CRC32, COMPRESSEDLEN, UNCOMPRESSEDLEN, COMPRESSIONTYPE};
    public static final int[] ALL_FIELDS_INT = {0, 1, 2, 3, 4, 5, 6, 7};

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public abstract String getAuthority();

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "vnd.android.cursor.item/asset";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    private boolean initIfNecessary() {
        int i2;
        if (!this.mInit) {
            Context context = getContext();
            PackageManager packageManager = context.getPackageManager();
            ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(getAuthority(), 128);
            try {
                int i3 = packageManager.getPackageInfo(context.getPackageName(), 0).versionCode;
                String[] strArr = null;
                if (resolveContentProvider.metaData != null) {
                    int i4 = resolveContentProvider.metaData.getInt("mainVersion", i3);
                    int i5 = resolveContentProvider.metaData.getInt("patchVersion", i3);
                    String string = resolveContentProvider.metaData.getString("mainFilename", "N");
                    if ("N" != string) {
                        String string2 = resolveContentProvider.metaData.getString("patchFilename", "N");
                        strArr = "N" != string2 ? new String[]{string, string2} : new String[]{string};
                    }
                    i2 = i5;
                    i3 = i4;
                } else {
                    i2 = i3;
                }
                try {
                    if (strArr == null) {
                        this.mAPKExtensionFile = a.b(context, i3, i2);
                    } else {
                        this.mAPKExtensionFile = a.a(strArr);
                    }
                    this.mInit = true;
                    return true;
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (PackageManager.NameNotFoundException e3) {
                e3.printStackTrace();
            }
        }
        return false;
    }

    @Override // android.content.ContentProvider
    public AssetFileDescriptor openAssetFile(Uri uri, String str) {
        initIfNecessary();
        String encodedPath = uri.getEncodedPath();
        if (encodedPath.startsWith(c.a.SEPARATOR)) {
            encodedPath = encodedPath.substring(1);
        }
        return this.mAPKExtensionFile.b(encodedPath);
    }

    @Override // android.content.ContentProvider
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) {
        initIfNecessary();
        return super.applyBatch(arrayList);
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) {
        initIfNecessary();
        AssetFileDescriptor openAssetFile = openAssetFile(uri, str);
        if (openAssetFile != null) {
            return openAssetFile.getParcelFileDescriptor();
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int[] iArr;
        initIfNecessary();
        b bVar = this.mAPKExtensionFile;
        b.a[] a2 = bVar == null ? new b.a[0] : bVar.a();
        if (strArr == null) {
            iArr = ALL_FIELDS_INT;
            strArr = ALL_FIELDS;
        } else {
            int length = strArr.length;
            iArr = new int[length];
            for (int i2 = 0; i2 < length; i2++) {
                if (strArr[i2].equals("_id")) {
                    iArr[i2] = 0;
                } else if (strArr[i2].equals(FILENAME)) {
                    iArr[i2] = 1;
                } else if (strArr[i2].equals(ZIPFILE)) {
                    iArr[i2] = 2;
                } else if (strArr[i2].equals(MODIFICATION)) {
                    iArr[i2] = 3;
                } else if (strArr[i2].equals(CRC32)) {
                    iArr[i2] = 4;
                } else if (strArr[i2].equals(COMPRESSEDLEN)) {
                    iArr[i2] = 5;
                } else if (strArr[i2].equals(UNCOMPRESSEDLEN)) {
                    iArr[i2] = 6;
                } else if (strArr[i2].equals(COMPRESSIONTYPE)) {
                    iArr[i2] = 7;
                } else {
                    throw new RuntimeException();
                }
            }
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr, a2.length);
        int length2 = iArr.length;
        for (b.a aVar : a2) {
            MatrixCursor.RowBuilder newRow = matrixCursor.newRow();
            for (int i3 = 0; i3 < length2; i3++) {
                switch (iArr[i3]) {
                    case 0:
                        newRow.add(Integer.valueOf(i3));
                        break;
                    case 1:
                        newRow.add(aVar.f23b);
                        break;
                    case 2:
                        newRow.add(aVar.d());
                        break;
                    case 3:
                        newRow.add(Long.valueOf(aVar.f27f));
                        break;
                    case 4:
                        newRow.add(Long.valueOf(aVar.f28g));
                        break;
                    case 5:
                        newRow.add(Long.valueOf(aVar.f29h));
                        break;
                    case 6:
                        newRow.add(Long.valueOf(aVar.f30i));
                        break;
                    case 7:
                        newRow.add(Integer.valueOf(aVar.f26e));
                        break;
                }
            }
        }
        return matrixCursor;
    }
}
