package androidx.core.graphics;

import android.graphics.Color;
import android.graphics.ColorSpace;
import com.bytedance.ttgame.sdk.module.skin.constants.SkinConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Color.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\r\u0010\u0018\u001a\u00020\u0004*\u00020\u0019H\u0087\n\u001a\r\u0010\u0018\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\r\u0010\u0018\u001a\u00020\u0004*\u00020\u0005H\u0087\n\u001a\r\u0010\u001a\u001a\u00020\u0004*\u00020\u0019H\u0087\n\u001a\r\u0010\u001a\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\r\u0010\u001a\u001a\u00020\u0004*\u00020\u0005H\u0087\n\u001a\r\u0010\u001b\u001a\u00020\u0004*\u00020\u0019H\u0087\n\u001a\r\u0010\u001b\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\r\u0010\u001b\u001a\u00020\u0004*\u00020\u0005H\u0087\n\u001a\r\u0010\u001c\u001a\u00020\u0004*\u00020\u0019H\u0087\n\u001a\r\u0010\u001c\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\r\u0010\u001c\u001a\u00020\u0004*\u00020\u0005H\u0087\n\u001a\u0015\u0010\u001d\u001a\u00020\u0019*\u00020\u00192\u0006\u0010\t\u001a\u00020\nH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0019*\u00020\u00192\u0006\u0010\t\u001a\u00020\u001eH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\t\u001a\u00020\u001eH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\t\u001a\u00020\u001eH\u0087\f\u001a\u0015\u0010\u001f\u001a\u00020\u0019*\u00020\u00192\u0006\u0010 \u001a\u00020\u0019H\u0087\u0002\u001a\r\u0010!\u001a\u00020\u0019*\u00020\u0001H\u0087\b\u001a\r\u0010!\u001a\u00020\u0019*\u00020\u0005H\u0087\b\u001a\r\u0010\"\u001a\u00020\u0001*\u00020\u0005H\u0087\b\u001a\r\u0010\"\u001a\u00020\u0001*\u00020#H\u0087\b\u001a\r\u0010$\u001a\u00020\u0005*\u00020\u0001H\u0087\b\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0000\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0006\"\u0016\u0010\u0007\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\u0003\"\u0016\u0010\u0007\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006\"\u0016\u0010\t\u001a\u00020\n*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0016\u0010\r\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0003\"\u0016\u0010\r\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006\"\u0016\u0010\u000f\u001a\u00020\u0010*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011\"\u0016\u0010\u0012\u001a\u00020\u0010*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011\"\u0016\u0010\u0013\u001a\u00020\u0004*\u00020\u00018Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\"\u0016\u0010\u0013\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0006\"\u0016\u0010\u0016\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0003\"\u0016\u0010\u0016\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0006¨\u0006%"}, d2 = {"alpha", "", "getAlpha", "(I)I", "", "", "(J)F", "blue", "getBlue", "colorSpace", "Landroid/graphics/ColorSpace;", "getColorSpace", "(J)Landroid/graphics/ColorSpace;", "green", "getGreen", "isSrgb", "", "(J)Z", "isWideGamut", "luminance", "getLuminance", "(I)F", SkinConfig.RED, "getRed", "component1", "Landroid/graphics/Color;", "component2", "component3", "component4", "convertTo", "Landroid/graphics/ColorSpace$Named;", "plus", "c", "toColor", "toColorInt", "", "toColorLong", "core-ktx_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ColorKt {
    public static final int component1(int i2) {
        return (i2 >> 24) & 255;
    }

    public static final int component2(int i2) {
        return (i2 >> 16) & 255;
    }

    public static final int component3(int i2) {
        return (i2 >> 8) & 255;
    }

    public static final int component4(int i2) {
        return i2 & 255;
    }

    public static final int getAlpha(int i2) {
        return (i2 >> 24) & 255;
    }

    public static final int getBlue(int i2) {
        return i2 & 255;
    }

    public static final int getGreen(int i2) {
        return (i2 >> 8) & 255;
    }

    public static final int getRed(int i2) {
        return (i2 >> 16) & 255;
    }

    public static final float component1(Color color) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return color.getComponent(0);
    }

    public static final float component2(Color color) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return color.getComponent(1);
    }

    public static final float component3(Color color) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return color.getComponent(2);
    }

    public static final float component4(Color color) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return color.getComponent(3);
    }

    public static final Color plus(Color color, Color c2) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        Intrinsics.checkNotNullParameter(c2, "c");
        Color compositeColors = ColorUtils.compositeColors(c2, color);
        Intrinsics.checkNotNullExpressionValue(compositeColors, "compositeColors(c, this)");
        return compositeColors;
    }

    public static final float getLuminance(int i2) {
        return Color.luminance(i2);
    }

    public static final Color toColor(int i2) {
        Color valueOf = Color.valueOf(i2);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this)");
        return valueOf;
    }

    public static final long toColorLong(int i2) {
        return Color.pack(i2);
    }

    public static final float component1(long j2) {
        return Color.red(j2);
    }

    public static final float component2(long j2) {
        return Color.green(j2);
    }

    public static final float component3(long j2) {
        return Color.blue(j2);
    }

    public static final float component4(long j2) {
        return Color.alpha(j2);
    }

    public static final float getAlpha(long j2) {
        return Color.alpha(j2);
    }

    public static final float getRed(long j2) {
        return Color.red(j2);
    }

    public static final float getGreen(long j2) {
        return Color.green(j2);
    }

    public static final float getBlue(long j2) {
        return Color.blue(j2);
    }

    public static final float getLuminance(long j2) {
        return Color.luminance(j2);
    }

    public static final Color toColor(long j2) {
        Color valueOf = Color.valueOf(j2);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this)");
        return valueOf;
    }

    public static final int toColorInt(long j2) {
        return Color.toArgb(j2);
    }

    public static final boolean isSrgb(long j2) {
        return Color.isSrgb(j2);
    }

    public static final boolean isWideGamut(long j2) {
        return Color.isWideGamut(j2);
    }

    public static final ColorSpace getColorSpace(long j2) {
        ColorSpace colorSpace = Color.colorSpace(j2);
        Intrinsics.checkNotNullExpressionValue(colorSpace, "colorSpace(this)");
        return colorSpace;
    }

    public static final long convertTo(int i2, ColorSpace.Named colorSpace) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        return Color.convert(i2, ColorSpace.get(colorSpace));
    }

    public static final long convertTo(int i2, ColorSpace colorSpace) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        return Color.convert(i2, colorSpace);
    }

    public static final long convertTo(long j2, ColorSpace.Named colorSpace) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        return Color.convert(j2, ColorSpace.get(colorSpace));
    }

    public static final long convertTo(long j2, ColorSpace colorSpace) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        return Color.convert(j2, colorSpace);
    }

    public static final Color convertTo(Color color, ColorSpace.Named colorSpace) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        Color convert = color.convert(ColorSpace.get(colorSpace));
        Intrinsics.checkNotNullExpressionValue(convert, "convert(ColorSpace.get(colorSpace))");
        return convert;
    }

    public static final Color convertTo(Color color, ColorSpace colorSpace) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        Color convert = color.convert(colorSpace);
        Intrinsics.checkNotNullExpressionValue(convert, "convert(colorSpace)");
        return convert;
    }

    public static final int toColorInt(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return Color.parseColor(str);
    }
}
