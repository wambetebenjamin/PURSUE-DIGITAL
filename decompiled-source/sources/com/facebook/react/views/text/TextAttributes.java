package com.facebook.react.views.text;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.PixelUtil;
/* loaded from: classes8.dex */
public class TextAttributes {
    public static final float DEFAULT_MAX_FONT_SIZE_MULTIPLIER = 0.0f;
    private boolean mAllowFontScaling = true;
    private float mFontSize = Float.NaN;
    private float mLineHeight = Float.NaN;
    private float mLetterSpacing = Float.NaN;
    private float mMaxFontSizeMultiplier = Float.NaN;
    private float mHeightOfTallestInlineViewOrImage = Float.NaN;
    private TextTransform mTextTransform = TextTransform.UNSET;

    public TextAttributes applyChild(TextAttributes textAttributes) {
        float f2;
        float f3;
        TextAttributes textAttributes2 = new TextAttributes();
        textAttributes2.mAllowFontScaling = this.mAllowFontScaling;
        textAttributes2.mFontSize = !Float.isNaN(textAttributes.mFontSize) ? textAttributes.mFontSize : this.mFontSize;
        textAttributes2.mLineHeight = !Float.isNaN(textAttributes.mLineHeight) ? textAttributes.mLineHeight : this.mLineHeight;
        textAttributes2.mLetterSpacing = !Float.isNaN(textAttributes.mLetterSpacing) ? textAttributes.mLetterSpacing : this.mLetterSpacing;
        if (!Float.isNaN(textAttributes.mMaxFontSizeMultiplier)) {
            f2 = textAttributes.mMaxFontSizeMultiplier;
        } else {
            f2 = this.mMaxFontSizeMultiplier;
        }
        textAttributes2.mMaxFontSizeMultiplier = f2;
        if (!Float.isNaN(textAttributes.mHeightOfTallestInlineViewOrImage)) {
            f3 = textAttributes.mHeightOfTallestInlineViewOrImage;
        } else {
            f3 = this.mHeightOfTallestInlineViewOrImage;
        }
        textAttributes2.mHeightOfTallestInlineViewOrImage = f3;
        textAttributes2.mTextTransform = textAttributes.mTextTransform != TextTransform.UNSET ? textAttributes.mTextTransform : this.mTextTransform;
        return textAttributes2;
    }

    public boolean getAllowFontScaling() {
        return this.mAllowFontScaling;
    }

    public void setAllowFontScaling(boolean z) {
        this.mAllowFontScaling = z;
    }

    public float getFontSize() {
        return this.mFontSize;
    }

    public void setFontSize(float f2) {
        this.mFontSize = f2;
    }

    public float getLineHeight() {
        return this.mLineHeight;
    }

    public void setLineHeight(float f2) {
        this.mLineHeight = f2;
    }

    public float getLetterSpacing() {
        return this.mLetterSpacing;
    }

    public void setLetterSpacing(float f2) {
        this.mLetterSpacing = f2;
    }

    public float getMaxFontSizeMultiplier() {
        return this.mMaxFontSizeMultiplier;
    }

    public void setMaxFontSizeMultiplier(float f2) {
        if (f2 != 0.0f && f2 < 1.0f) {
            throw new JSApplicationIllegalArgumentException("maxFontSizeMultiplier must be NaN, 0, or >= 1");
        }
        this.mMaxFontSizeMultiplier = f2;
    }

    public float getHeightOfTallestInlineViewOrImage() {
        return this.mHeightOfTallestInlineViewOrImage;
    }

    public void setHeightOfTallestInlineViewOrImage(float f2) {
        this.mHeightOfTallestInlineViewOrImage = f2;
    }

    public TextTransform getTextTransform() {
        return this.mTextTransform;
    }

    public void setTextTransform(TextTransform textTransform) {
        this.mTextTransform = textTransform;
    }

    public int getEffectiveFontSize() {
        double ceil;
        float f2 = !Float.isNaN(this.mFontSize) ? this.mFontSize : 14.0f;
        if (this.mAllowFontScaling) {
            ceil = Math.ceil(PixelUtil.toPixelFromSP(f2, getEffectiveMaxFontSizeMultiplier()));
        } else {
            ceil = Math.ceil(PixelUtil.toPixelFromDIP(f2));
        }
        return (int) ceil;
    }

    public float getEffectiveLineHeight() {
        float pixelFromDIP;
        if (Float.isNaN(this.mLineHeight)) {
            return Float.NaN;
        }
        if (this.mAllowFontScaling) {
            pixelFromDIP = PixelUtil.toPixelFromSP(this.mLineHeight, getEffectiveMaxFontSizeMultiplier());
        } else {
            pixelFromDIP = PixelUtil.toPixelFromDIP(this.mLineHeight);
        }
        return !Float.isNaN(this.mHeightOfTallestInlineViewOrImage) && (this.mHeightOfTallestInlineViewOrImage > pixelFromDIP ? 1 : (this.mHeightOfTallestInlineViewOrImage == pixelFromDIP ? 0 : -1)) > 0 ? this.mHeightOfTallestInlineViewOrImage : pixelFromDIP;
    }

    public float getEffectiveLetterSpacing() {
        float pixelFromDIP;
        if (Float.isNaN(this.mLetterSpacing)) {
            return Float.NaN;
        }
        if (this.mAllowFontScaling) {
            pixelFromDIP = PixelUtil.toPixelFromSP(this.mLetterSpacing, getEffectiveMaxFontSizeMultiplier());
        } else {
            pixelFromDIP = PixelUtil.toPixelFromDIP(this.mLetterSpacing);
        }
        return pixelFromDIP / getEffectiveFontSize();
    }

    public float getEffectiveMaxFontSizeMultiplier() {
        if (Float.isNaN(this.mMaxFontSizeMultiplier)) {
            return 0.0f;
        }
        return this.mMaxFontSizeMultiplier;
    }

    public String toString() {
        return "TextAttributes {\n  getAllowFontScaling(): " + getAllowFontScaling() + "\n  getFontSize(): " + getFontSize() + "\n  getEffectiveFontSize(): " + getEffectiveFontSize() + "\n  getHeightOfTallestInlineViewOrImage(): " + getHeightOfTallestInlineViewOrImage() + "\n  getLetterSpacing(): " + getLetterSpacing() + "\n  getEffectiveLetterSpacing(): " + getEffectiveLetterSpacing() + "\n  getLineHeight(): " + getLineHeight() + "\n  getEffectiveLineHeight(): " + getEffectiveLineHeight() + "\n  getTextTransform(): " + getTextTransform() + "\n  getMaxFontSizeMultiplier(): " + getMaxFontSizeMultiplier() + "\n  getEffectiveMaxFontSizeMultiplier(): " + getEffectiveMaxFontSizeMultiplier() + "\n}";
    }
}
