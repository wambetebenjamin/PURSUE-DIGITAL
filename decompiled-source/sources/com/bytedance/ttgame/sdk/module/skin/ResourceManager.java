package com.bytedance.ttgame.sdk.module.skin;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.ttgame.sdk.module.skin.constants.SkinConfig;
/* loaded from: classes8.dex */
public class ResourceManager {
    private static final String DEFTYPE_COLOR = "color";
    private static final String DEFTYPE_DIMEN = "dimen";
    private static final String DEFTYPE_DRAWABLE = "drawable";
    private static final String DEFTYPE_LAYOUT = "layout";
    private final String mPackageName;
    private final Resources mResources;
    private final String mSuffix;

    public Resources getResources() {
        return this.mResources;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public ResourceManager(Resources resources, String str, String str2) {
        this.mResources = resources;
        this.mPackageName = str;
        this.mSuffix = str2 == null ? "" : str2;
    }

    public Drawable getDrawableByName(String str) {
        try {
            String appendSuffix = appendSuffix(str);
            Log.w(SkinConfig.TAG, "getDrawable " + appendSuffix);
            Resources resources = this.mResources;
            return resources.getDrawable(resources.getIdentifier(appendSuffix, DEFTYPE_DRAWABLE, this.mPackageName));
        } catch (Resources.NotFoundException e2) {
            Log.e(SkinConfig.TAG, e2.getLocalizedMessage());
            return null;
        }
    }

    public int getLayout(String str) {
        try {
            String appendSuffix = appendSuffix(str);
            Log.w(SkinConfig.TAG, "getLayout " + appendSuffix);
            return this.mResources.getIdentifier(appendSuffix, DEFTYPE_LAYOUT, this.mPackageName);
        } catch (Resources.NotFoundException e2) {
            Log.e(SkinConfig.TAG, e2.getLocalizedMessage());
            return -1;
        }
    }

    public int getOriginLayout(String str) {
        try {
            Log.w(SkinConfig.TAG, "getOriginLayout " + str);
            return this.mResources.getIdentifier(str, DEFTYPE_LAYOUT, this.mPackageName);
        } catch (Resources.NotFoundException e2) {
            Log.e(SkinConfig.TAG, e2.getLocalizedMessage());
            return -1;
        }
    }

    public int getColor(String str) {
        try {
            String appendSuffix = appendSuffix(str);
            Log.w(SkinConfig.TAG, "getColor " + appendSuffix);
            Resources resources = this.mResources;
            return resources.getColor(resources.getIdentifier(appendSuffix, "color", this.mPackageName));
        } catch (Resources.NotFoundException e2) {
            Log.e(SkinConfig.TAG, e2.getLocalizedMessage());
            return -1;
        }
    }

    public int getDimension(String str) {
        try {
            String appendSuffix = appendSuffix(str);
            Log.w(SkinConfig.TAG, "getDimension " + appendSuffix);
            Resources resources = this.mResources;
            return (int) resources.getDimension(resources.getIdentifier(appendSuffix, DEFTYPE_DIMEN, this.mPackageName));
        } catch (Resources.NotFoundException e2) {
            Log.e(SkinConfig.TAG, e2.getLocalizedMessage());
            return -1;
        }
    }

    public ColorStateList getColorStateList(String str) {
        try {
            String appendSuffix = appendSuffix(str);
            Log.w(SkinConfig.TAG, "getColorStateList " + appendSuffix);
            Resources resources = this.mResources;
            return resources.getColorStateList(resources.getIdentifier(appendSuffix, "color", this.mPackageName));
        } catch (Resources.NotFoundException e2) {
            Log.e(SkinConfig.TAG, e2.getLocalizedMessage());
            return null;
        }
    }

    private String appendSuffix(String str) {
        return !TextUtils.isEmpty(this.mSuffix) ? str + "_" + this.mSuffix : str;
    }
}
