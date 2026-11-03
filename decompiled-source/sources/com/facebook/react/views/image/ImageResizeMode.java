package com.facebook.react.views.image;

import android.graphics.Shader;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
/* loaded from: classes8.dex */
public class ImageResizeMode {
    public static ScalingUtils.ScaleType toScaleType(String str) {
        if ("contain".equals(str)) {
            return ScalingUtils.ScaleType.FIT_CENTER;
        }
        if ("cover".equals(str)) {
            return ScalingUtils.ScaleType.CENTER_CROP;
        }
        if ("stretch".equals(str)) {
            return ScalingUtils.ScaleType.FIT_XY;
        }
        if ("center".equals(str)) {
            return ScalingUtils.ScaleType.CENTER_INSIDE;
        }
        if (ReactVideoViewManager.PROP_REPEAT.equals(str)) {
            return ScaleTypeStartInside.INSTANCE;
        }
        if (str == null) {
            return defaultValue();
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + str + "'");
    }

    public static Shader.TileMode toTileMode(String str) {
        if ("contain".equals(str) || "cover".equals(str) || "stretch".equals(str) || "center".equals(str)) {
            return Shader.TileMode.CLAMP;
        }
        if (ReactVideoViewManager.PROP_REPEAT.equals(str)) {
            return Shader.TileMode.REPEAT;
        }
        if (str == null) {
            return defaultTileMode();
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + str + "'");
    }

    public static ScalingUtils.ScaleType defaultValue() {
        return ScalingUtils.ScaleType.CENTER_CROP;
    }

    public static Shader.TileMode defaultTileMode() {
        return Shader.TileMode.CLAMP;
    }
}
