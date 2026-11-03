package com.facebook.react.views.text;

import android.text.Spannable;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes8.dex */
public class ReactTextUpdate {
    public ReadableMap mAttributedString;
    private final boolean mContainsImages;
    private final int mJsEventCounter;
    private final int mJustificationMode;
    private final float mPaddingBottom;
    private final float mPaddingLeft;
    private final float mPaddingRight;
    private final float mPaddingTop;
    private final int mSelectionEnd;
    private final int mSelectionStart;
    private final Spannable mText;
    private final int mTextAlign;
    private final int mTextBreakStrategy;

    @Deprecated
    public ReactTextUpdate(Spannable spannable, int i2, boolean z, float f2, float f3, float f4, float f5, int i3) {
        this(spannable, i2, z, f2, f3, f4, f5, i3, 1, 0, -1, -1);
    }

    public ReactTextUpdate(Spannable spannable, int i2, boolean z, float f2, float f3, float f4, float f5, int i3, int i4, int i5) {
        this(spannable, i2, z, f2, f3, f4, f5, i3, i4, i5, -1, -1);
    }

    public ReactTextUpdate(Spannable spannable, int i2, boolean z, int i3, int i4, int i5) {
        this(spannable, i2, z, -1.0f, -1.0f, -1.0f, -1.0f, i3, i4, i5, -1, -1);
    }

    public ReactTextUpdate(Spannable spannable, int i2, boolean z, float f2, float f3, float f4, float f5, int i3, int i4, int i5, int i6, int i7) {
        this.mAttributedString = null;
        this.mText = spannable;
        this.mJsEventCounter = i2;
        this.mContainsImages = z;
        this.mPaddingLeft = f2;
        this.mPaddingTop = f3;
        this.mPaddingRight = f4;
        this.mPaddingBottom = f5;
        this.mTextAlign = i3;
        this.mTextBreakStrategy = i4;
        this.mSelectionStart = i6;
        this.mSelectionEnd = i7;
        this.mJustificationMode = i5;
    }

    public static ReactTextUpdate buildReactTextUpdateFromState(Spannable spannable, int i2, boolean z, int i3, int i4, int i5, ReadableMap readableMap) {
        ReactTextUpdate reactTextUpdate = new ReactTextUpdate(spannable, i2, z, i3, i4, i5);
        reactTextUpdate.mAttributedString = readableMap;
        return reactTextUpdate;
    }

    public Spannable getText() {
        return this.mText;
    }

    public int getJsEventCounter() {
        return this.mJsEventCounter;
    }

    public boolean containsImages() {
        return this.mContainsImages;
    }

    public float getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public float getPaddingTop() {
        return this.mPaddingTop;
    }

    public float getPaddingRight() {
        return this.mPaddingRight;
    }

    public float getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public int getTextAlign() {
        return this.mTextAlign;
    }

    public int getTextBreakStrategy() {
        return this.mTextBreakStrategy;
    }

    public int getJustificationMode() {
        return this.mJustificationMode;
    }

    public int getSelectionStart() {
        return this.mSelectionStart;
    }

    public int getSelectionEnd() {
        return this.mSelectionEnd;
    }
}
