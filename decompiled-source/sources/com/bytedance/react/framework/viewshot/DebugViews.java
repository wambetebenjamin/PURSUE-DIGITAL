package com.bytedance.react.framework.viewshot;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.util.Pair;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.Locale;
import java.util.Stack;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public final class DebugViews {
    public static final Matrix EMPTY_MATRIX = new Matrix();
    public static final int LOG_MSG_LIMIT = 200;

    public static int longDebug(String str, String str2) {
        int i2 = 0;
        while (str2.length() > 0) {
            int indexOf = str2.indexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            int min = Math.min(str2.length(), Math.min(indexOf < 0 ? 200 : indexOf + 1, 200));
            Log.d(str, String.format(Locale.US, "%02d: %s", Integer.valueOf(i2), str2.substring(0, min)));
            str2 = str2.substring(min);
            i2++;
        }
        return i2;
    }

    public static String logViewHierarchy(Activity activity) {
        View findViewById = activity.findViewById(16908290);
        if (findViewById == null) {
            return "Activity [" + activity.getClass().getSimpleName() + "] is not initialized yet. ";
        }
        return logViewHierarchy(findViewById);
    }

    private static String logViewHierarchy(View view) {
        StringBuilder append = new StringBuilder(8192).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        Resources resources = view.getResources();
        Stack stack = new Stack();
        stack.push(Pair.create("", view));
        while (!stack.empty()) {
            Pair pair = (Pair) stack.pop();
            View view2 = (View) pair.second;
            String str = (String) pair.first;
            boolean z = stack.empty() || !str.equals(((Pair) stack.peek()).first);
            append.append(("" + str + (z ? "└── " : "├── ")) + view2.getClass().getSimpleName() + dumpProperties(resources, view2)).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                    stack.push(Pair.create(str + (z ? "    " : "│   "), viewGroup.getChildAt(childCount)));
                }
            }
        }
        return append.toString();
    }

    private static String dumpProperties(Resources resources, View view) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ").append("id=").append(view.getId()).append(resolveIdToName(resources, view));
        int visibility = view.getVisibility();
        if (visibility == 0) {
            sb.append(", V--");
        } else if (visibility == 4) {
            sb.append(", -I-");
        } else if (visibility == 8) {
            sb.append(", --G");
        } else {
            sb.append(", ---");
        }
        if (!view.getMatrix().equals(EMPTY_MATRIX)) {
            sb.append(", ").append("matrix=").append(view.getMatrix().toShortString());
            if (0.0f != view.getRotation() || 0.0f != view.getRotationX() || 0.0f != view.getRotationY()) {
                sb.append(", rotate=[").append(view.getRotation()).append(",").append(view.getRotationX()).append(",").append(view.getRotationY()).append("]");
                if (view.getWidth() / 2 != view.getPivotX() || view.getHeight() / 2 != view.getPivotY()) {
                    sb.append(", pivot=[").append(view.getPivotX()).append(",").append(view.getPivotY()).append("]");
                }
            }
            if (Build.VERSION.SDK_INT >= 21 && (0.0f != view.getTranslationX() || 0.0f != view.getTranslationY() || 0.0f != view.getTranslationZ())) {
                sb.append(", translate=[").append(view.getTranslationX()).append(",").append(view.getTranslationY()).append(",").append(view.getTranslationZ()).append("]");
            }
            if (1.0f != view.getScaleX() || 1.0f != view.getScaleY()) {
                sb.append(", scale=[").append(view.getScaleX()).append(",").append(view.getScaleY()).append("]");
            }
        }
        if (view.getPaddingStart() != 0 || view.getPaddingTop() != 0 || view.getPaddingEnd() != 0 || view.getPaddingBottom() != 0) {
            sb.append(", ").append("padding=[").append(view.getPaddingStart()).append(",").append(view.getPaddingTop()).append(",").append(view.getPaddingEnd()).append(",").append(view.getPaddingBottom()).append("]");
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (marginLayoutParams.leftMargin != 0 || marginLayoutParams.topMargin != 0 || marginLayoutParams.rightMargin != 0 || marginLayoutParams.bottomMargin != 0) {
                sb.append(", ").append("margin=[").append(marginLayoutParams.leftMargin).append(",").append(marginLayoutParams.topMargin).append(",").append(marginLayoutParams.rightMargin).append(",").append(marginLayoutParams.bottomMargin).append("]");
            }
        }
        sb.append(", position=[").append(view.getLeft()).append(",").append(view.getTop()).append("]");
        sb.append(", size=[").append(view.getWidth()).append(",").append(view.getHeight()).append("]");
        if (view instanceof TextView) {
            sb.append(", text=\"").append(((TextView) view).getText()).append("\"");
        }
        return sb.toString();
    }

    private static String resolveIdToName(@Nullable Resources resources, View view) {
        if (resources == null) {
            return "";
        }
        try {
            return " / " + resources.getResourceEntryName(view.getId());
        } catch (Throwable unused) {
            return "";
        }
    }
}
