package com.bytedance.common.utility;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import android.widget.Toast;
/* compiled from: UIUtils.java */
/* loaded from: classes2.dex */
public final class p {
    public static final boolean API_ET_20;
    public static final char ELLIPSIS_CHAR = 8230;
    public static final int LAYOUT_PARAMS_KEEP_OLD = -3;

    /* renamed from: a  reason: collision with root package name */
    private static final float f5319a = 0.5f;

    /* renamed from: b  reason: collision with root package name */
    private static b f5320b = null;

    /* renamed from: c  reason: collision with root package name */
    private static final int f5321c = 10000;

    /* renamed from: d  reason: collision with root package name */
    private static String f5322d = null;

    /* renamed from: e  reason: collision with root package name */
    private static int f5323e = 0;

    /* renamed from: f  reason: collision with root package name */
    private static final int f5324f = 1375;

    /* renamed from: g  reason: collision with root package name */
    private static final int f5325g = 20;

    /* renamed from: h  reason: collision with root package name */
    private static final int f5326h = 255;

    /* renamed from: i  reason: collision with root package name */
    private static final int f5327i = 16777215;

    /* renamed from: j  reason: collision with root package name */
    private static final int f5328j = 16777216;
    private static final float k = 0.999f;
    public static a sTempEllipsisResult;

    /* compiled from: UIUtils.java */
    /* loaded from: classes2.dex */
    public static class a {
        public String ellipsisStr;
        public int length;
    }

    /* compiled from: UIUtils.java */
    /* loaded from: classes2.dex */
    public interface b {
        boolean showToast(Context context, int i2, CharSequence charSequence, long j2, int i3);
    }

    private static boolean a(int i2) {
        return i2 == 0 || i2 == 8 || i2 == 4;
    }

    public static int floatToIntBig(float f2) {
        return (int) (f2 + k);
    }

    public static int setColorAlpha(int i2, int i3) {
        if (i3 > 255) {
            i3 = 255;
        } else if (i3 < 0) {
            i3 = 0;
        }
        return (i2 & 16777215) | (i3 * 16777216);
    }

    private p() {
    }

    static {
        API_ET_20 = Build.VERSION.SDK_INT > 19;
        f5322d = "";
        f5323e = -1;
        sTempEllipsisResult = new a();
    }

    public static void setToastHook(b bVar) {
        f5320b = bVar;
    }

    public static void displayToast(Context context, String str) {
        displayToast(context, 0, str);
    }

    public static void displayToast(Context context, int i2) {
        displayToast(context, context.getString(i2));
    }

    public static void displayToastWithIcon(Context context, int i2, int i3) {
        displayToast(context, i2, context.getString(i3));
    }

    public static void displayToastWithIcon(Context context, int i2, String str) {
        displayToast(context, i2, str);
    }

    public static void displayToast(Context context, int i2, int i3) {
        displayToast(context, context.getString(i2), i3);
    }

    public static void displayToast(Context context, String str, int i2) {
        b(context, 0, str, 0, i2);
    }

    public static void displayToast(Context context, int i2, String str) {
        b(context, i2, str, 0, 17);
    }

    public static void displayLongTimeToast(Context context, int i2, int i3) {
        if (context == null) {
            return;
        }
        String string = context.getString(i3);
        if (o.isEmpty(string)) {
            return;
        }
        b(context, i2, string, 1, 17);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final Context context, final int i2, final String str, final int i3, final int i4) {
        if (context == null || o.isEmpty(str)) {
            return;
        }
        if (!isInUIThread()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.common.utility.p.1
                @Override // java.lang.Runnable
                public void run() {
                    p.b(context, i2, str, i3, i4);
                }
            });
            return;
        }
        b bVar = f5320b;
        if (bVar == null || !bVar.showToast(context, i2, str, i3, i4)) {
            if (context instanceof g) {
                if (i3 == 1) {
                    ((g) context).showCustomLongToast(i2, str);
                    return;
                }
                g gVar = (g) context;
                if (i3 == 0) {
                    i3 = 2000;
                }
                gVar.showCustomToast(i2, str, i3, i4);
                return;
            }
            try {
                Toast makeText = Toast.makeText(context, str, i3);
                if (makeText != null) {
                    makeText.setGravity(i4, 0, 0);
                    makeText.show();
                }
            } catch (Exception e2) {
                Logger.throwException(e2);
            }
        }
    }

    public static float sp2px(Context context, float f2) {
        if (context != null) {
            return TypedValue.applyDimension(2, f2, context.getResources().getDisplayMetrics());
        }
        return 0.0f;
    }

    public static float dip2Px(Context context, float f2) {
        if (context != null) {
            return (f2 * context.getResources().getDisplayMetrics().density) + 0.5f;
        }
        return 0.0f;
    }

    public static int px2dip(Context context, float f2) {
        if (context != null) {
            return (int) ((f2 / context.getResources().getDisplayMetrics().density) + 0.5f);
        }
        return 0;
    }

    public static void expandClickRegion(final View view, final int i2, final int i3, final int i4, final int i5) {
        view.post(new Runnable() { // from class: com.bytedance.common.utility.p.2
            @Override // java.lang.Runnable
            public void run() {
                Rect rect = new Rect();
                view.getHitRect(rect);
                rect.top += i3;
                rect.bottom += i5;
                rect.left += i2;
                rect.right += i4;
                TouchDelegate touchDelegate = new TouchDelegate(rect, view);
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }

    public static void setViewBackgroundWithPadding(View view, int i2) {
        if (view == null) {
            return;
        }
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        int paddingTop = view.getPaddingTop();
        int paddingBottom = view.getPaddingBottom();
        view.setBackgroundResource(i2);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public static void setViewBackgroundWithPadding(View view, Resources resources, int i2) {
        if (view == null) {
            return;
        }
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        int paddingTop = view.getPaddingTop();
        int paddingBottom = view.getPaddingBottom();
        view.setBackgroundColor(resources.getColor(i2));
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public static void setViewBackgroundWithPadding(View view, Drawable drawable) {
        if (view == null) {
            return;
        }
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        int paddingTop = view.getPaddingTop();
        int paddingBottom = view.getPaddingBottom();
        view.setBackgroundDrawable(drawable);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics;
        if (context == null || (displayMetrics = context.getResources().getDisplayMetrics()) == null) {
            return 0;
        }
        return displayMetrics.widthPixels;
    }

    public static int getRatioOfScreen(Context context, float f2) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics == null) {
            return 0;
        }
        return (int) (displayMetrics.widthPixels * f2);
    }

    public static boolean isInUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void assertInUIThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        Logger.alertErrorInfo("not in UI thread");
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics;
        if (context == null || (displayMetrics = context.getResources().getDisplayMetrics()) == null) {
            return 0;
        }
        return displayMetrics.heightPixels;
    }

    public static String getScreenResolution(Context context) {
        if (o.isEmpty(f5322d) && context != null) {
            int screenWidth = getScreenWidth(context);
            int screenHeight = getScreenHeight(context);
            if (screenWidth > 0 && screenHeight > 0) {
                f5322d = screenWidth + "*" + screenHeight;
            }
        }
        return f5322d;
    }

    public static int getDpi(Context context) {
        if (f5323e == -1 && context != null) {
            f5323e = context.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
        }
        return f5323e;
    }

    public static int getDiggBuryWidth(Context context) {
        return ((context.getResources().getDisplayMetrics().widthPixels * f5324f) / 10000) + ((int) dip2Px(context, 20.0f));
    }

    public static int getStatusBarHeight(Context context) {
        int identifier;
        if (context != null && (identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static void setViewVisibility(View view, int i2) {
        if (view == null || view.getVisibility() == i2 || !a(i2)) {
            return;
        }
        view.setVisibility(i2);
    }

    public static boolean isViewVisible(View view) {
        return view != null && view.getVisibility() == 0;
    }

    public static void getLocationInUpView(View view, View view2, int[] iArr, boolean z) {
        if (view == null || view2 == null || iArr == null || iArr.length < 2) {
            return;
        }
        view.getLocationInWindow(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        view2.getLocationInWindow(iArr);
        int i4 = iArr[0] - i2;
        int i5 = iArr[1] - i3;
        if (z) {
            i4 += view2.getWidth() / 2;
            i5 += view2.getHeight() / 2;
        }
        iArr[0] = i4;
        iArr[1] = i5;
    }

    public static void updateLayout(View view, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || (layoutParams = view.getLayoutParams()) == null) {
            return;
        }
        if (layoutParams.width == i2 && layoutParams.height == i3) {
            return;
        }
        if (i2 != -3) {
            layoutParams.width = i2;
        }
        if (i3 != -3) {
            layoutParams.height = i3;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void updateLayoutMargin(View view, int i2, int i3, int i4, int i5) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || (layoutParams = view.getLayoutParams()) == null || !(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        a(view, (ViewGroup.MarginLayoutParams) layoutParams, i2, i3, i4, i5);
    }

    private static void a(View view, ViewGroup.MarginLayoutParams marginLayoutParams, int i2, int i3, int i4, int i5) {
        if (view == null || marginLayoutParams == null) {
            return;
        }
        if (marginLayoutParams.leftMargin == i2 && marginLayoutParams.topMargin == i3 && marginLayoutParams.rightMargin == i4 && marginLayoutParams.bottomMargin == i5) {
            return;
        }
        if (i2 != -3) {
            marginLayoutParams.leftMargin = i2;
        }
        if (i3 != -3) {
            marginLayoutParams.topMargin = i3;
        }
        if (i4 != -3) {
            marginLayoutParams.rightMargin = i4;
        }
        if (i5 != -3) {
            marginLayoutParams.bottomMargin = i5;
        }
        view.setLayoutParams(marginLayoutParams);
    }

    public static void setTopMargin(View view, float f2) {
        if (view == null) {
            return;
        }
        updateLayoutMargin(view, -3, (int) TypedValue.applyDimension(1, f2, view.getContext().getResources().getDisplayMetrics()), -3, -3);
    }

    public static void setLayoutParams(View view, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        if (i2 != Integer.MIN_VALUE) {
            layoutParams.width = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            layoutParams.height = i3;
        }
    }

    public static void setTxtAndAdjustVisible(TextView textView, CharSequence charSequence) {
        if (textView == null) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            setViewVisibility(textView, 8);
            return;
        }
        setViewVisibility(textView, 0);
        textView.setText(charSequence);
    }

    public static void setText(TextView textView, CharSequence charSequence) {
        if (textView == null || TextUtils.isEmpty(charSequence)) {
            return;
        }
        textView.setText(charSequence);
    }

    public static void detachFromParent(View view) {
        if (view == null || view.getParent() == null) {
            return;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            try {
                ((ViewGroup) parent).removeView(view);
            } catch (Exception e2) {
                Logger.throwException(e2);
            }
        }
    }

    public static void setViewMinHeight(View view, int i2) {
        if (view == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 16 || view.getMinimumHeight() != i2) {
            view.setMinimumHeight(i2);
        }
    }

    public static void setTextViewMaxLines(TextView textView, int i2) {
        if (textView == null || i2 <= 0) {
            return;
        }
        if (Build.VERSION.SDK_INT < 16 || textView.getMaxLines() != i2) {
            textView.setSingleLine(i2 == 1);
            textView.setMaxLines(i2);
        }
    }

    public static int[] getLocationInAncestor(View view, View view2) {
        boolean z;
        if (view == null || view2 == null) {
            Logger.alertErrorInfo("invalid params: child:" + view + ",ancestor:" + view2);
            return null;
        }
        int[] iArr = new int[2];
        float[] fArr = {0.0f, 0.0f};
        fArr[0] = fArr[0] + view.getLeft();
        fArr[1] = fArr[1] + view.getTop();
        ViewParent parent = view.getParent();
        while (true) {
            if (!(parent instanceof View)) {
                z = false;
                break;
            }
            View view3 = (View) parent;
            if (parent == view2) {
                z = true;
                break;
            }
            fArr[0] = fArr[0] - view3.getScrollX();
            fArr[1] = fArr[1] - view3.getScrollY();
            fArr[0] = fArr[0] + view3.getLeft();
            fArr[1] = fArr[1] + view3.getTop();
            parent = view3.getParent();
        }
        if (!z) {
            Logger.alertErrorInfo("ancestorView:" + view2 + " is not the ancestor of child : " + view);
            return null;
        }
        iArr[0] = (int) (fArr[0] + 0.5f);
        iArr[1] = (int) (fArr[1] + 0.5f);
        return iArr;
    }

    public static void ellipseSingleLineStr(String str, int i2, Paint paint, int i3, a aVar) {
        if (i2 <= i3 || o.isEmpty(str)) {
            aVar.ellipsisStr = "";
            aVar.length = 0;
            return;
        }
        int floatToIntBig = floatToIntBig(paint.measureText(str));
        if (floatToIntBig <= i2) {
            aVar.ellipsisStr = str;
            aVar.length = floatToIntBig;
            return;
        }
        StringBuilder sb = new StringBuilder();
        int breakText = paint.breakText(str, 0, str.length(), true, i2 - i3, null);
        if (breakText < 1) {
            aVar.ellipsisStr = "";
            aVar.length = 0;
            return;
        }
        sb.append(str.substring(0, breakText));
        sb.append((char) 8230);
        aVar.ellipsisStr = sb.toString();
        aVar.length = i2;
    }

    public static void requestOrienation(Activity activity, boolean z) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.setRequestedOrientation(!z ? 1 : 0);
        if (z) {
            activity.getWindow().setFlags(1024, 1024);
        } else {
            activity.getWindow().clearFlags(1024);
        }
    }

    public static int getIndexInParent(View view) {
        if (view != null && view.getParent() != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                return ((ViewGroup) parent).indexOfChild(view);
            }
        }
        return -1;
    }

    public static boolean clearAnimation(View view) {
        if (view == null || view.getAnimation() == null) {
            return false;
        }
        view.clearAnimation();
        return true;
    }

    public static void setClickListener(boolean z, View view, View.OnClickListener onClickListener) {
        if (view == null) {
            return;
        }
        if (z) {
            view.setOnClickListener(onClickListener);
            view.setClickable(true);
            return;
        }
        view.setOnClickListener(null);
        view.setClickable(false);
    }
}
