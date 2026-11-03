package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.R;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public final class ColorStateListInflaterCompat {
    private static final ThreadLocal<TypedValue> sTempTypedValue = new ThreadLocal<>();

    private ColorStateListInflaterCompat() {
    }

    public static ColorStateList inflate(Resources resources, int i2, Resources.Theme theme) {
        try {
            return createFromXml(resources, resources.getXml(i2), theme);
        } catch (Exception e2) {
            Log.e("CSLCompat", "Failed to inflate ColorStateList.", e2);
            return null;
        }
    }

    public static ColorStateList createFromXml(Resources resources, XmlPullParser xmlPullParser, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        return createFromXmlInner(resources, xmlPullParser, asAttributeSet, theme);
    }

    public static ColorStateList createFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (!name.equals("selector")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid color state list tag " + name);
        }
        return inflate(resources, xmlPullParser, attributeSet, theme);
    }

    private static ColorStateList inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth;
        int color;
        float f2;
        Resources resources2 = resources;
        int i2 = 1;
        int depth2 = xmlPullParser.getDepth() + 1;
        int[][] iArr = new int[20];
        int[] iArr2 = new int[20];
        int i3 = 0;
        while (true) {
            int next = xmlPullParser.next();
            if (next == i2 || ((depth = xmlPullParser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2 && xmlPullParser.getName().equals("item")) {
                TypedArray obtainAttributes = obtainAttributes(resources2, theme, attributeSet, R.styleable.ColorStateListItem);
                int resourceId = obtainAttributes.getResourceId(R.styleable.ColorStateListItem_android_color, -1);
                if (resourceId != -1 && !isColorInt(resources2, resourceId)) {
                    try {
                        color = createFromXml(resources2, resources2.getXml(resourceId), theme).getDefaultColor();
                    } catch (Exception unused) {
                        color = obtainAttributes.getColor(R.styleable.ColorStateListItem_android_color, -65281);
                    }
                } else {
                    color = obtainAttributes.getColor(R.styleable.ColorStateListItem_android_color, -65281);
                }
                float f3 = 1.0f;
                if (obtainAttributes.hasValue(R.styleable.ColorStateListItem_android_alpha)) {
                    f3 = obtainAttributes.getFloat(R.styleable.ColorStateListItem_android_alpha, 1.0f);
                } else if (obtainAttributes.hasValue(R.styleable.ColorStateListItem_alpha)) {
                    f3 = obtainAttributes.getFloat(R.styleable.ColorStateListItem_alpha, 1.0f);
                }
                if (Build.VERSION.SDK_INT >= 31 && obtainAttributes.hasValue(R.styleable.ColorStateListItem_android_lStar)) {
                    f2 = obtainAttributes.getFloat(R.styleable.ColorStateListItem_android_lStar, -1.0f);
                } else {
                    f2 = obtainAttributes.getFloat(R.styleable.ColorStateListItem_lStar, -1.0f);
                }
                obtainAttributes.recycle();
                int attributeCount = attributeSet.getAttributeCount();
                int[] iArr3 = new int[attributeCount];
                int i4 = 0;
                for (int i5 = 0; i5 < attributeCount; i5++) {
                    int attributeNameResource = attributeSet.getAttributeNameResource(i5);
                    if (attributeNameResource != 16843173 && attributeNameResource != 16843551 && attributeNameResource != R.attr.alpha && attributeNameResource != R.attr.lStar) {
                        int i6 = i4 + 1;
                        if (!attributeSet.getAttributeBooleanValue(i5, false)) {
                            attributeNameResource = -attributeNameResource;
                        }
                        iArr3[i4] = attributeNameResource;
                        i4 = i6;
                    }
                }
                int[] trimStateSet = StateSet.trimStateSet(iArr3, i4);
                iArr2 = GrowingArrayUtils.append(iArr2, i3, modulateColorAlpha(color, f3, f2));
                iArr = (int[][]) GrowingArrayUtils.append(iArr, i3, trimStateSet);
                i3++;
            }
            i2 = 1;
            resources2 = resources;
        }
        int[] iArr4 = new int[i3];
        int[][] iArr5 = new int[i3];
        System.arraycopy(iArr2, 0, iArr4, 0, i3);
        System.arraycopy(iArr, 0, iArr5, 0, i3);
        return new ColorStateList(iArr5, iArr4);
    }

    private static boolean isColorInt(Resources resources, int i2) {
        TypedValue typedValue = getTypedValue();
        resources.getValue(i2, typedValue, true);
        return typedValue.type >= 28 && typedValue.type <= 31;
    }

    private static TypedValue getTypedValue() {
        ThreadLocal<TypedValue> threadLocal = sTempTypedValue;
        TypedValue typedValue = threadLocal.get();
        if (typedValue == null) {
            TypedValue typedValue2 = new TypedValue();
            threadLocal.set(typedValue2);
            return typedValue2;
        }
        return typedValue;
    }

    private static TypedArray obtainAttributes(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    private static int modulateColorAlpha(int i2, float f2, float f3) {
        boolean z = f3 >= 0.0f && f3 <= 100.0f;
        if (f2 != 1.0f || z) {
            int clamp = MathUtils.clamp((int) ((Color.alpha(i2) * f2) + 0.5f), 0, 255);
            if (z) {
                CamColor fromColor = CamColor.fromColor(i2);
                i2 = CamColor.toColor(fromColor.getHue(), fromColor.getChroma(), f3);
            }
            return (i2 & ViewCompat.MEASURED_SIZE_MASK) | (clamp << 24);
        }
        return i2;
    }
}
