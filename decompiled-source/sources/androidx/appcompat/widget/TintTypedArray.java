package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;
/* loaded from: classes.dex */
public class TintTypedArray {
    private final Context mContext;
    private TypedValue mTypedValue;
    private final TypedArray mWrapped;

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr, i2, i3));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, int i2, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(i2, iArr));
    }

    private TintTypedArray(Context context, TypedArray typedArray) {
        this.mContext = context;
        this.mWrapped = typedArray;
    }

    public TypedArray getWrappedTypeArray() {
        return this.mWrapped;
    }

    public Drawable getDrawable(int i2) {
        int resourceId;
        if (this.mWrapped.hasValue(i2) && (resourceId = this.mWrapped.getResourceId(i2, 0)) != 0) {
            return AppCompatResources.getDrawable(this.mContext, resourceId);
        }
        return this.mWrapped.getDrawable(i2);
    }

    public Drawable getDrawableIfKnown(int i2) {
        int resourceId;
        if (!this.mWrapped.hasValue(i2) || (resourceId = this.mWrapped.getResourceId(i2, 0)) == 0) {
            return null;
        }
        return AppCompatDrawableManager.get().getDrawable(this.mContext, resourceId, true);
    }

    public Typeface getFont(int i2, int i3, ResourcesCompat.FontCallback fontCallback) {
        int resourceId = this.mWrapped.getResourceId(i2, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        return ResourcesCompat.getFont(this.mContext, resourceId, this.mTypedValue, i3, fontCallback);
    }

    public int length() {
        return this.mWrapped.length();
    }

    public int getIndexCount() {
        return this.mWrapped.getIndexCount();
    }

    public int getIndex(int i2) {
        return this.mWrapped.getIndex(i2);
    }

    public Resources getResources() {
        return this.mWrapped.getResources();
    }

    public CharSequence getText(int i2) {
        return this.mWrapped.getText(i2);
    }

    public String getString(int i2) {
        return this.mWrapped.getString(i2);
    }

    public String getNonResourceString(int i2) {
        return this.mWrapped.getNonResourceString(i2);
    }

    public boolean getBoolean(int i2, boolean z) {
        return this.mWrapped.getBoolean(i2, z);
    }

    public int getInt(int i2, int i3) {
        return this.mWrapped.getInt(i2, i3);
    }

    public float getFloat(int i2, float f2) {
        return this.mWrapped.getFloat(i2, f2);
    }

    public int getColor(int i2, int i3) {
        return this.mWrapped.getColor(i2, i3);
    }

    public ColorStateList getColorStateList(int i2) {
        int resourceId;
        ColorStateList colorStateList;
        return (!this.mWrapped.hasValue(i2) || (resourceId = this.mWrapped.getResourceId(i2, 0)) == 0 || (colorStateList = AppCompatResources.getColorStateList(this.mContext, resourceId)) == null) ? this.mWrapped.getColorStateList(i2) : colorStateList;
    }

    public int getInteger(int i2, int i3) {
        return this.mWrapped.getInteger(i2, i3);
    }

    public float getDimension(int i2, float f2) {
        return this.mWrapped.getDimension(i2, f2);
    }

    public int getDimensionPixelOffset(int i2, int i3) {
        return this.mWrapped.getDimensionPixelOffset(i2, i3);
    }

    public int getDimensionPixelSize(int i2, int i3) {
        return this.mWrapped.getDimensionPixelSize(i2, i3);
    }

    public int getLayoutDimension(int i2, String str) {
        return this.mWrapped.getLayoutDimension(i2, str);
    }

    public int getLayoutDimension(int i2, int i3) {
        return this.mWrapped.getLayoutDimension(i2, i3);
    }

    public float getFraction(int i2, int i3, int i4, float f2) {
        return this.mWrapped.getFraction(i2, i3, i4, f2);
    }

    public int getResourceId(int i2, int i3) {
        return this.mWrapped.getResourceId(i2, i3);
    }

    public CharSequence[] getTextArray(int i2) {
        return this.mWrapped.getTextArray(i2);
    }

    public boolean getValue(int i2, TypedValue typedValue) {
        return this.mWrapped.getValue(i2, typedValue);
    }

    public int getType(int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return Api21Impl.getType(this.mWrapped, i2);
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        this.mWrapped.getValue(i2, this.mTypedValue);
        return this.mTypedValue.type;
    }

    public boolean hasValue(int i2) {
        return this.mWrapped.hasValue(i2);
    }

    public TypedValue peekValue(int i2) {
        return this.mWrapped.peekValue(i2);
    }

    public String getPositionDescription() {
        return this.mWrapped.getPositionDescription();
    }

    public void recycle() {
        this.mWrapped.recycle();
    }

    public int getChangingConfigurations() {
        return Api21Impl.getChangingConfigurations(this.mWrapped);
    }

    /* loaded from: classes.dex */
    static class Api21Impl {
        private Api21Impl() {
        }

        static int getType(TypedArray typedArray, int i2) {
            return typedArray.getType(i2);
        }

        static int getChangingConfigurations(TypedArray typedArray) {
            return typedArray.getChangingConfigurations();
        }
    }
}
