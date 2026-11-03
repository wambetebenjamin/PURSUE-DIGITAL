package com.bytedance.react.framework.utils.notchutil.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.bytedance.react.framework.utils.notchutil.INotchScreen;
/* loaded from: classes2.dex */
public class ScreenUtil {
    public static boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static int getContentViewHeight(Activity activity) {
        return getContentView(activity).getHeight();
    }

    public static Rect getContentViewDisplayFrame(Activity activity) {
        View contentView = getContentView(activity);
        Rect rect = new Rect();
        contentView.getWindowVisibleDisplayFrame(rect);
        return rect;
    }

    public static View getContentView(Activity activity) {
        return activity.getWindow().getDecorView().findViewById(16908290);
    }

    public static int getNavigationBarHeight(Context context) {
        return getDimensionPixel(context, "navigation_bar_height");
    }

    public static int getStatusBarHeight(Context context) {
        return getDimensionPixel(context, "status_bar_height");
    }

    private static int getDimensionPixel(Context context, String str) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(str, "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int[] getScreenSize(Context context) {
        int[] iArr = new int[2];
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point point = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(defaultDisplay, point);
                i2 = point.x;
                i3 = point.y;
            } catch (Throwable unused) {
            }
        }
        iArr[0] = i2;
        iArr[1] = i3;
        return iArr;
    }

    public static int getRotation(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    public static Rect calculateNotchRect(Context context, int i2, int i3) {
        int i4;
        int i5;
        int[] screenSize = getScreenSize(context);
        int i6 = 0;
        int i7 = screenSize[0];
        int i8 = screenSize[1];
        if (isPortrait(context)) {
            int i9 = (i7 - i2) / 2;
            i4 = 0;
            i6 = i9;
            i3 = i2 + i9;
            i5 = i3;
        } else if (getRotation(context) == 1) {
            int i10 = (i8 - i2) / 2;
            i5 = i2 + i10;
            i4 = i10;
        } else {
            i6 = i7 - i3;
            i4 = (i8 - i2) / 2;
            i5 = i4 + i2;
            i3 = i7;
        }
        return new Rect(i6, i4, i3, i5);
    }

    public static boolean isNotchScreenInfoRight(Context context, int i2, INotchScreen.NotchScreenInfo notchScreenInfo) {
        if (notchScreenInfo != null && notchScreenInfo.notchRects != null && notchScreenInfo.notchRects.size() > 0) {
            int[] screenSize = getScreenSize(context);
            int i3 = screenSize[0];
            int i4 = screenSize[1];
            Rect rect = notchScreenInfo.notchRects.get(0);
            int i5 = rect.top;
            int i6 = rect.left;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 == 2) {
                        if (i5 > i4 / 5) {
                            return true;
                        }
                    } else if (i2 == 3 && i6 > i3 / 5) {
                        return true;
                    }
                } else if (i6 < i3 / 5) {
                    return true;
                }
            } else if (i5 < i4 / 5) {
                return true;
            }
        }
        return false;
    }

    public static int px2dip(Context context, int i2) {
        if (context != null) {
            return (int) ((i2 / context.getResources().getDisplayMetrics().density) + 0.5f);
        }
        return 0;
    }
}
