package com.facebook.react.views.text;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
/* loaded from: classes8.dex */
public class CustomStyleSpan extends MetricAffectingSpan implements ReactSpan {
    private final AssetManager mAssetManager;
    private final Context mContext;
    private final String mFeatureSettings;
    private final String mFontFamily;
    private final int mStyle;
    private final int mWeight;

    public CustomStyleSpan(int i2, int i3, String str, String str2, Context context, AssetManager assetManager) {
        this.mStyle = i2;
        this.mWeight = i3;
        this.mFeatureSettings = str;
        this.mFontFamily = str2;
        this.mAssetManager = assetManager;
        this.mContext = context;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        apply(textPaint, this.mStyle, this.mWeight, this.mFeatureSettings, this.mFontFamily, this.mContext, this.mAssetManager);
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        apply(textPaint, this.mStyle, this.mWeight, this.mFeatureSettings, this.mFontFamily, this.mContext, this.mAssetManager);
    }

    public int getStyle() {
        int i2 = this.mStyle;
        if (i2 == -1) {
            return 0;
        }
        return i2;
    }

    public int getWeight() {
        int i2 = this.mWeight;
        if (i2 == -1) {
            return 0;
        }
        return i2;
    }

    public String getFontFamily() {
        return this.mFontFamily;
    }

    private static void apply(Paint paint, int i2, int i3, String str, String str2, Context context, AssetManager assetManager) {
        Typeface applyStyles = ReactTypefaceUtils.applyStyles(paint.getTypeface(), i2, i3, str2, context, assetManager);
        if (Build.VERSION.SDK_INT >= 21) {
            paint.setFontFeatureSettings(str);
        }
        paint.setTypeface(applyStyles);
        paint.setSubpixelText(true);
    }
}
