package com.facebook.react.views.text;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.core.content.res.ResourcesCompat;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public class ReactFontManager {
    private static final String[] EXTENSIONS = {"", "_bold", "_italic", "_bold_italic"};
    private static final String[] FILE_EXTENSIONS = {".ttf", ".otf"};
    private static final String FONTS_ASSET_PATH = "fonts/";
    private static GetChannelPathListener mGetChannelPathListener;
    private static ReactFontManager sReactFontManagerInstance;
    private final Map<String, FontFamily> mFontCache = new HashMap();
    private final Map<String, Typeface> mCustomTypefaceCache = new HashMap();

    /* loaded from: classes8.dex */
    public interface GetChannelPathListener {
        String getChannelPath(String str);
    }

    private ReactFontManager() {
    }

    public static void setGetChannelPathListener(GetChannelPathListener getChannelPathListener) {
        mGetChannelPathListener = getChannelPathListener;
    }

    public static ReactFontManager getInstance() {
        if (sReactFontManagerInstance == null) {
            sReactFontManagerInstance = new ReactFontManager();
        }
        return sReactFontManagerInstance;
    }

    public Typeface getTypeface(String str, int i2, AssetManager assetManager) {
        return getTypeface(str, i2, 0, null, assetManager);
    }

    public Typeface getTypeface(String str, int i2, int i3, Context context, AssetManager assetManager) {
        if (this.mCustomTypefaceCache.containsKey(str)) {
            Typeface typeface = this.mCustomTypefaceCache.get(str);
            if (Build.VERSION.SDK_INT >= 28 && i3 >= 100 && i3 <= 1000) {
                return Typeface.create(typeface, i3, (i2 & 2) != 0);
            }
            return Typeface.create(typeface, i2);
        }
        FontFamily fontFamily = this.mFontCache.get(str);
        if (fontFamily == null) {
            fontFamily = new FontFamily();
            this.mFontCache.put(str, fontFamily);
        }
        Typeface typeface2 = fontFamily.getTypeface(i2);
        if (typeface2 == null && (typeface2 = createTypeface(str, i2, context, assetManager)) != null) {
            fontFamily.setTypeface(i2, typeface2);
        }
        return typeface2;
    }

    public void addCustomFont(Context context, String str, int i2) {
        Typeface font = ResourcesCompat.getFont(context, i2);
        if (font != null) {
            this.mCustomTypefaceCache.put(str, font);
        }
    }

    public void setTypeface(String str, int i2, Typeface typeface) {
        if (typeface != null) {
            FontFamily fontFamily = this.mFontCache.get(str);
            if (fontFamily == null) {
                fontFamily = new FontFamily();
                this.mFontCache.put(str, fontFamily);
            }
            fontFamily.setTypeface(i2, typeface);
        }
    }

    private static Typeface createTypeface(String str, int i2, Context context, AssetManager assetManager) {
        String[] strArr;
        File[] listFiles;
        GetChannelPathListener getChannelPathListener;
        String str2 = EXTENSIONS[i2];
        int length = FILE_EXTENSIONS.length;
        for (int i3 = 0; i3 < length; i3++) {
            String str3 = FONTS_ASSET_PATH + str + str2 + strArr[i3];
            try {
                return Typeface.createFromAsset(assetManager, str3);
            } catch (RuntimeException unused) {
                if (context != null) {
                    try {
                        File file = new File(context.getFilesDir(), "bundles-load");
                        if (!file.exists()) {
                            continue;
                        } else if (file.isDirectory()) {
                            File[] listFiles2 = file.listFiles();
                            if (listFiles2 != null) {
                                for (File file2 : listFiles2) {
                                    if (file2.isDirectory() && (listFiles = file2.listFiles()) != null) {
                                        for (File file3 : listFiles) {
                                            if (file3.isDirectory() && !TextUtils.isEmpty(file3.getPath())) {
                                                String path = file3.getPath();
                                                if (!TextUtils.isEmpty(path)) {
                                                    if (!new File(path, str3).exists() && (getChannelPathListener = mGetChannelPathListener) != null) {
                                                        path = getChannelPathListener.getChannelPath(file3.getName());
                                                    }
                                                } else {
                                                    GetChannelPathListener getChannelPathListener2 = mGetChannelPathListener;
                                                    if (getChannelPathListener2 != null) {
                                                        path = getChannelPathListener2.getChannelPath(file3.getName());
                                                    }
                                                }
                                                File file4 = new File(path, str3);
                                                if (file4.exists()) {
                                                    try {
                                                        return Typeface.createFromFile(file4);
                                                    } catch (RuntimeException unused2) {
                                                        continue;
                                                    }
                                                } else {
                                                    continue;
                                                }
                                            }
                                        }
                                        continue;
                                    }
                                }
                                continue;
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } catch (Exception unused3) {
                        continue;
                    }
                }
            }
        }
        return Typeface.create(str, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static class FontFamily {
        private SparseArray<Typeface> mTypefaceSparseArray;

        private FontFamily() {
            this.mTypefaceSparseArray = new SparseArray<>(4);
        }

        public Typeface getTypeface(int i2) {
            return this.mTypefaceSparseArray.get(i2);
        }

        public void setTypeface(int i2, Typeface typeface) {
            this.mTypefaceSparseArray.put(i2, typeface);
        }
    }
}
