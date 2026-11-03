package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;
/* loaded from: classes.dex */
public class TypedArrayUtils {
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

    public static boolean hasAttribute(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue(NAMESPACE, str) != null;
    }

    public static float getNamedFloat(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, float f2) {
        return !hasAttribute(xmlPullParser, str) ? f2 : typedArray.getFloat(i2, f2);
    }

    public static boolean getNamedBoolean(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, boolean z) {
        return !hasAttribute(xmlPullParser, str) ? z : typedArray.getBoolean(i2, z);
    }

    public static int getNamedInt(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !hasAttribute(xmlPullParser, str) ? i3 : typedArray.getInt(i2, i3);
    }

    public static int getNamedColor(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !hasAttribute(xmlPullParser, str) ? i3 : typedArray.getColor(i2, i3);
    }

    public static ComplexColorCompat getNamedComplexColor(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i2, int i3) {
        if (hasAttribute(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i2, typedValue);
            if (typedValue.type >= 28 && typedValue.type <= 31) {
                return ComplexColorCompat.from(typedValue.data);
            }
            ComplexColorCompat inflate = ComplexColorCompat.inflate(typedArray.getResources(), typedArray.getResourceId(i2, 0), theme);
            if (inflate != null) {
                return inflate;
            }
        }
        return ComplexColorCompat.from(i3);
    }

    public static ColorStateList getNamedColorStateList(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i2) {
        if (hasAttribute(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i2, typedValue);
            if (typedValue.type == 2) {
                throw new UnsupportedOperationException("Failed to resolve attribute at index " + i2 + ": " + typedValue);
            }
            if (typedValue.type >= 28 && typedValue.type <= 31) {
                return getNamedColorStateListFromInt(typedValue);
            }
            return ColorStateListInflaterCompat.inflate(typedArray.getResources(), typedArray.getResourceId(i2, 0), theme);
        }
        return null;
    }

    private static ColorStateList getNamedColorStateListFromInt(TypedValue typedValue) {
        return ColorStateList.valueOf(typedValue.data);
    }

    public static int getNamedResourceId(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !hasAttribute(xmlPullParser, str) ? i3 : typedArray.getResourceId(i2, i3);
    }

    public static String getNamedString(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2) {
        if (hasAttribute(xmlPullParser, str)) {
            return typedArray.getString(i2);
        }
        return null;
    }

    public static TypedValue peekNamedValue(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2) {
        if (hasAttribute(xmlPullParser, str)) {
            return typedArray.peekValue(i2);
        }
        return null;
    }

    public static TypedArray obtainAttributes(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public static boolean getBoolean(TypedArray typedArray, int i2, int i3, boolean z) {
        return typedArray.getBoolean(i2, typedArray.getBoolean(i3, z));
    }

    public static Drawable getDrawable(TypedArray typedArray, int i2, int i3) {
        Drawable drawable = typedArray.getDrawable(i2);
        return drawable == null ? typedArray.getDrawable(i3) : drawable;
    }

    public static int getInt(TypedArray typedArray, int i2, int i3, int i4) {
        return typedArray.getInt(i2, typedArray.getInt(i3, i4));
    }

    public static int getResourceId(TypedArray typedArray, int i2, int i3, int i4) {
        return typedArray.getResourceId(i2, typedArray.getResourceId(i3, i4));
    }

    public static String getString(TypedArray typedArray, int i2, int i3) {
        String string = typedArray.getString(i2);
        return string == null ? typedArray.getString(i3) : string;
    }

    public static CharSequence getText(TypedArray typedArray, int i2, int i3) {
        CharSequence text = typedArray.getText(i2);
        return text == null ? typedArray.getText(i3) : text;
    }

    public static CharSequence[] getTextArray(TypedArray typedArray, int i2, int i3) {
        CharSequence[] textArray = typedArray.getTextArray(i2);
        return textArray == null ? typedArray.getTextArray(i3) : textArray;
    }

    public static int getAttr(Context context, int i2, int i3) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i2, typedValue, true);
        return typedValue.resourceId != 0 ? i2 : i3;
    }

    private TypedArrayUtils() {
    }
}
