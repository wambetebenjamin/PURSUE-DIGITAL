package com.google.android.material.transition.platform;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.transition.PathMotion;
import android.transition.PatternPathMotion;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import androidx.core.graphics.PathParser;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
/* loaded from: classes6.dex */
class TransitionUtils {
    private static final String EASING_TYPE_CUBIC_BEZIER = "cubic-bezier";
    private static final String EASING_TYPE_FORMAT_END = ")";
    private static final String EASING_TYPE_FORMAT_START = "(";
    private static final String EASING_TYPE_PATH = "path";
    static final int NO_ATTR_RES_ID = 0;
    static final int NO_DURATION = -1;
    private static final int PATH_TYPE_ARC = 1;
    private static final int PATH_TYPE_LINEAR = 0;
    private static final RectF transformAlphaRectF = new RectF();

    /* loaded from: classes6.dex */
    interface CanvasOperation {
        void run(Canvas canvas);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public interface CornerSizeBinaryOperator {
        CornerSize apply(CornerSize cornerSize, CornerSize cornerSize2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T defaultIfNull(T t, T t2) {
        return t != null ? t : t2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float lerp(float f2, float f3, float f4) {
        return f2 + (f4 * (f3 - f2));
    }

    private TransitionUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean maybeApplyThemeInterpolator(Transition transition, Context context, int i2, TimeInterpolator timeInterpolator) {
        if (i2 == 0 || transition.getInterpolator() != null) {
            return false;
        }
        transition.setInterpolator(resolveThemeInterpolator(context, i2, timeInterpolator));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean maybeApplyThemeDuration(Transition transition, Context context, int i2) {
        int resolveInteger;
        if (i2 == 0 || transition.getDuration() != -1 || (resolveInteger = MaterialAttributes.resolveInteger(context, i2, -1)) == -1) {
            return false;
        }
        transition.setDuration(resolveInteger);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean maybeApplyThemePath(Transition transition, Context context, int i2) {
        PathMotion resolveThemePath;
        if (i2 == 0 || (resolveThemePath = resolveThemePath(context, i2)) == null) {
            return false;
        }
        transition.setPathMotion(resolveThemePath);
        return true;
    }

    static TimeInterpolator resolveThemeInterpolator(Context context, int i2, TimeInterpolator timeInterpolator) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i2, typedValue, true)) {
            if (typedValue.type != 3) {
                throw new IllegalArgumentException("Motion easing theme attribute must be a string");
            }
            String valueOf = String.valueOf(typedValue.string);
            if (isEasingType(valueOf, EASING_TYPE_CUBIC_BEZIER)) {
                String[] split = getEasingContent(valueOf, EASING_TYPE_CUBIC_BEZIER).split(",");
                if (split.length != 4) {
                    throw new IllegalArgumentException("Motion easing theme attribute must have 4 control points if using bezier curve format; instead got: " + split.length);
                }
                return PathInterpolatorCompat.create(getControlPoint(split, 0), getControlPoint(split, 1), getControlPoint(split, 2), getControlPoint(split, 3));
            } else if (isEasingType(valueOf, "path")) {
                return PathInterpolatorCompat.create(PathParser.createPathFromPathData(getEasingContent(valueOf, "path")));
            } else {
                throw new IllegalArgumentException("Invalid motion easing type: " + valueOf);
            }
        }
        return timeInterpolator;
    }

    private static boolean isEasingType(String str, String str2) {
        return str.startsWith(new StringBuilder().append(str2).append(EASING_TYPE_FORMAT_START).toString()) && str.endsWith(EASING_TYPE_FORMAT_END);
    }

    private static String getEasingContent(String str, String str2) {
        return str.substring(str2.length() + 1, str.length() - 1);
    }

    private static float getControlPoint(String[] strArr, int i2) {
        float parseFloat = Float.parseFloat(strArr[i2]);
        if (parseFloat < 0.0f || parseFloat > 1.0f) {
            throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + parseFloat);
        }
        return parseFloat;
    }

    static PathMotion resolveThemePath(Context context, int i2) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i2, typedValue, true)) {
            if (typedValue.type == 16) {
                int i3 = typedValue.data;
                if (i3 == 0) {
                    return null;
                }
                if (i3 == 1) {
                    return new MaterialArcMotion();
                }
                throw new IllegalArgumentException("Invalid motion path type: " + i3);
            } else if (typedValue.type == 3) {
                return new PatternPathMotion(PathParser.createPathFromPathData(String.valueOf(typedValue.string)));
            } else {
                throw new IllegalArgumentException("Motion path theme attribute must either be an enum value or path data string");
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ShapeAppearanceModel convertToRelativeCornerSizes(ShapeAppearanceModel shapeAppearanceModel, final RectF rectF) {
        return shapeAppearanceModel.withTransformedCornerSizes(new ShapeAppearanceModel.CornerSizeUnaryOperator() { // from class: com.google.android.material.transition.platform.TransitionUtils.1
            @Override // com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator
            public CornerSize apply(CornerSize cornerSize) {
                return cornerSize instanceof RelativeCornerSize ? cornerSize : new RelativeCornerSize(cornerSize.getCornerSize(rectF) / rectF.height());
            }
        });
    }

    static ShapeAppearanceModel transformCornerSizes(ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, RectF rectF, CornerSizeBinaryOperator cornerSizeBinaryOperator) {
        return (isShapeAppearanceSignificant(shapeAppearanceModel, rectF) ? shapeAppearanceModel : shapeAppearanceModel2).toBuilder().setTopLeftCornerSize(cornerSizeBinaryOperator.apply(shapeAppearanceModel.getTopLeftCornerSize(), shapeAppearanceModel2.getTopLeftCornerSize())).setTopRightCornerSize(cornerSizeBinaryOperator.apply(shapeAppearanceModel.getTopRightCornerSize(), shapeAppearanceModel2.getTopRightCornerSize())).setBottomLeftCornerSize(cornerSizeBinaryOperator.apply(shapeAppearanceModel.getBottomLeftCornerSize(), shapeAppearanceModel2.getBottomLeftCornerSize())).setBottomRightCornerSize(cornerSizeBinaryOperator.apply(shapeAppearanceModel.getBottomRightCornerSize(), shapeAppearanceModel2.getBottomRightCornerSize())).build();
    }

    private static boolean isShapeAppearanceSignificant(ShapeAppearanceModel shapeAppearanceModel, RectF rectF) {
        return (shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(rectF) == 0.0f && shapeAppearanceModel.getTopRightCornerSize().getCornerSize(rectF) == 0.0f && shapeAppearanceModel.getBottomRightCornerSize().getCornerSize(rectF) == 0.0f && shapeAppearanceModel.getBottomLeftCornerSize().getCornerSize(rectF) == 0.0f) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float lerp(float f2, float f3, float f4, float f5, float f6) {
        return lerp(f2, f3, f4, f5, f6, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float lerp(float f2, float f3, float f4, float f5, float f6, boolean z) {
        if (!z || (f6 >= 0.0f && f6 <= 1.0f)) {
            return f6 < f4 ? f2 : f6 > f5 ? f3 : lerp(f2, f3, (f6 - f4) / (f5 - f4));
        }
        return lerp(f2, f3, f6);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int lerp(int i2, int i3, float f2, float f3, float f4) {
        return f4 < f2 ? i2 : f4 > f3 ? i3 : (int) lerp(i2, i3, (f4 - f2) / (f3 - f2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ShapeAppearanceModel lerp(ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, final RectF rectF, final RectF rectF2, final float f2, final float f3, final float f4) {
        return f4 < f2 ? shapeAppearanceModel : f4 > f3 ? shapeAppearanceModel2 : transformCornerSizes(shapeAppearanceModel, shapeAppearanceModel2, rectF, new CornerSizeBinaryOperator() { // from class: com.google.android.material.transition.platform.TransitionUtils.2
            @Override // com.google.android.material.transition.platform.TransitionUtils.CornerSizeBinaryOperator
            public CornerSize apply(CornerSize cornerSize, CornerSize cornerSize2) {
                return new AbsoluteCornerSize(TransitionUtils.lerp(cornerSize.getCornerSize(rectF), cornerSize2.getCornerSize(rectF2), f2, f3, f4));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Shader createColorShader(int i2) {
        return new LinearGradient(0.0f, 0.0f, 0.0f, 0.0f, i2, i2, Shader.TileMode.CLAMP);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static View findDescendantOrAncestorById(View view, int i2) {
        View findViewById = view.findViewById(i2);
        return findViewById != null ? findViewById : findAncestorById(view, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static View findAncestorById(View view, int i2) {
        String resourceName = view.getResources().getResourceName(i2);
        while (view != null) {
            if (view.getId() != i2) {
                ViewParent parent = view.getParent();
                if (!(parent instanceof View)) {
                    break;
                }
                view = (View) parent;
            } else {
                return view;
            }
        }
        throw new IllegalArgumentException(resourceName + " is not a valid ancestor");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RectF getRelativeBounds(View view) {
        return new RectF(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Rect getRelativeBoundsRect(View view) {
        return new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RectF getLocationOnScreen(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        return new RectF(i2, i3, view.getWidth() + i2, view.getHeight() + i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float calculateArea(RectF rectF) {
        return rectF.width() * rectF.height();
    }

    private static int saveLayerAlphaCompat(Canvas canvas, Rect rect, int i2) {
        RectF rectF = transformAlphaRectF;
        rectF.set(rect);
        if (Build.VERSION.SDK_INT >= 21) {
            return canvas.saveLayerAlpha(rectF, i2);
        }
        return canvas.saveLayerAlpha(rectF.left, rectF.top, rectF.right, rectF.bottom, i2, 31);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void transform(Canvas canvas, Rect rect, float f2, float f3, float f4, int i2, CanvasOperation canvasOperation) {
        if (i2 <= 0) {
            return;
        }
        int save = canvas.save();
        canvas.translate(f2, f3);
        canvas.scale(f4, f4);
        if (i2 < 255) {
            saveLayerAlphaCompat(canvas, rect, i2);
        }
        canvasOperation.run(canvas);
        canvas.restoreToCount(save);
    }

    static void maybeAddTransition(TransitionSet transitionSet, Transition transition) {
        if (transition != null) {
            transitionSet.addTransition(transition);
        }
    }

    static void maybeRemoveTransition(TransitionSet transitionSet, Transition transition) {
        if (transition != null) {
            transitionSet.removeTransition(transition);
        }
    }
}
