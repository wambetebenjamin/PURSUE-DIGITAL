package com.oasis.assettool;

import android.content.res.AssetManager;
import android.util.Log;
import com.bytedance.librarian.c;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public final class AssetReader {

    /* renamed from: a  reason: collision with root package name */
    private AssetManager f17475a;

    public AssetReader(AssetManager assetManager) {
        this.f17475a = assetManager;
    }

    private void a(String str, ArrayList<String> arrayList) {
        String[] list = this.f17475a.list(str);
        if (list.length <= 0) {
            arrayList.add(str);
            return;
        }
        for (String str2 : list) {
            if (str.length() > 0) {
                str2 = str + c.a.SEPARATOR + str2;
            }
            a(str2, arrayList);
        }
    }

    public final String[] getAllFilePath() {
        try {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.ensureCapacity(2048);
            a("", arrayList);
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        } catch (Exception e2) {
            Log.e("unity", "get all file path failed.", e2);
            return new String[0];
        }
    }
}
