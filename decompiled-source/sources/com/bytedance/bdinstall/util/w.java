package com.bytedance.bdinstall.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
/* compiled from: UIUtils.java */
@Deprecated
/* loaded from: classes.dex */
public class w {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f4093a;

    /* renamed from: b  reason: collision with root package name */
    public static a f4094b = null;

    /* renamed from: c  reason: collision with root package name */
    private static final char f4095c = 8230;

    /* renamed from: d  reason: collision with root package name */
    private static final int f4096d = -3;

    /* renamed from: e  reason: collision with root package name */
    private static String f4097e;

    /* renamed from: f  reason: collision with root package name */
    private static int f4098f;

    /* compiled from: UIUtils.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public String f4104a;

        /* renamed from: b  reason: collision with root package name */
        public int f4105b;
    }

    public static int a(float f2) {
        return (int) (f2 + 0.999f);
    }

    @Deprecated
    public static int a(int i2, int i3) {
        if (i3 > 255) {
            i3 = 255;
        } else if (i3 < 0) {
            i3 = 0;
        }
        return (i2 & ViewCompat.MEASURED_SIZE_MASK) | (i3 * 16777216);
    }

    @Deprecated
    private static boolean a(int i2) {
        return i2 == 0 || i2 == 8 || i2 == 4;
    }

    static {
        f4093a = Build.VERSION.SDK_INT > 19;
        f4097e = "";
        f4098f = -1;
        f4094b = new a();
    }

    @Deprecated
    public static float a(Context context, float f2) {
        return TypedValue.applyDimension(2, f2, context.getResources().getDisplayMetrics());
    }

    @Deprecated
    public static float b(Context context, float f2) {
        return (f2 * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    @Deprecated
    public static int c(Context context, float f2) {
        return (int) ((f2 / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Deprecated
    public static void a(final View view, final int i2, final int i3, final int i4, final int i5) {
        view.post(new Runnable() { // from class: com.bytedance.bdinstall.util.w.1
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

    @Deprecated
    public static void a(View view, int i2) {
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

    @Deprecated
    public static void a(View view, Resources resources, int i2) {
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

    public static void a(View view, Drawable drawable) {
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

    private static int e(Context context) {
        DisplayMetrics displayMetrics;
        if (context == null || (displayMetrics = context.getResources().getDisplayMetrics()) == null) {
            return 0;
        }
        return displayMetrics.widthPixels;
    }

    private static int f(Context context) {
        DisplayMetrics displayMetrics;
        if (context == null || (displayMetrics = context.getResources().getDisplayMetrics()) == null) {
            return 0;
        }
        return displayMetrics.heightPixels;
    }

    public static String a(Context context) {
        if (TextUtils.isEmpty(f4097e) && context != null) {
            int e2 = e(context);
            int f2 = f(context);
            if (e2 > 0 && f2 > 0) {
                f4097e = e2 + "*" + f2;
            }
        }
        return f4097e;
    }

    public static int b(Context context) {
        if (f4098f == -1 && context != null) {
            f4098f = context.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
        }
        return f4098f;
    }

    @Deprecated
    public static int c(Context context) {
        return ((context.getResources().getDisplayMetrics().widthPixels * 1375) / 10000) + ((int) b(context, 20.0f));
    }

    @Deprecated
    public static final int d(Context context) {
        int identifier;
        if (context != null && (identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    @Deprecated
    public static final void b(View view, int i2) {
        if (view == null || view.getVisibility() == i2 || !a(i2)) {
            return;
        }
        view.setVisibility(i2);
    }

    @Deprecated
    public static final boolean a(View view) {
        return view != null && view.getVisibility() == 0;
    }

    @Deprecated
    public static void a(View view, View view2, int[] iArr, boolean z) {
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

    @Deprecated
    public static void a(View view, int i2, int i3) {
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

    @Deprecated
    public static void b(View view, int i2, int i3, int i4, int i5) {
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

    @Deprecated
    public static void a(View view, float f2) {
        if (view == null) {
            return;
        }
        b(view, -3, (int) TypedValue.applyDimension(1, f2, view.getContext().getResources().getDisplayMetrics()), -3, -3);
    }

    @Deprecated
    public static void b(View view, int i2, int i3) {
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

    @Deprecated
    public static void a(TextView textView, CharSequence charSequence) {
        if (textView == null) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            b(textView, 8);
            return;
        }
        b(textView, 0);
        textView.setText(charSequence);
    }

    @Deprecated
    public static void b(TextView textView, CharSequence charSequence) {
        if (textView == null || TextUtils.isEmpty(charSequence)) {
            return;
        }
        textView.setText(charSequence);
    }

    @Deprecated
    public static void b(View view) {
        if (view == null || view.getParent() == null) {
            return;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            try {
                ((ViewGroup) parent).removeView(view);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Deprecated
    public static void c(View view, int i2) {
        if (view == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 16 || view.getMinimumHeight() != i2) {
            view.setMinimumHeight(i2);
        }
    }

    @Deprecated
    public static void a(TextView textView, int i2) {
        if (textView == null || i2 <= 0) {
            return;
        }
        if (Build.VERSION.SDK_INT < 16 || textView.getMaxLines() != i2) {
            textView.setSingleLine(i2 == 1);
            textView.setMaxLines(i2);
        }
    }

    @Deprecated
    public static int[] a(View view, View view2) {
        boolean z;
        if (view == null || view2 == null) {
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
        if (z) {
            iArr[0] = (int) (fArr[0] + 0.5f);
            iArr[1] = (int) (fArr[1] + 0.5f);
            return iArr;
        }
        return null;
    }

    @Deprecated
    public static void a(String str, int i2, Paint paint, int i3, a aVar) {
        if (i2 <= i3 || TextUtils.isEmpty(str)) {
            aVar.f4104a = "";
            aVar.f4105b = 0;
            return;
        }
        int a2 = a(paint.measureText(str));
        if (a2 <= i2) {
            aVar.f4104a = str;
            aVar.f4105b = a2;
            return;
        }
        StringBuilder sb = new StringBuilder();
        int breakText = paint.breakText(str, 0, str.length(), true, i2 - i3, null);
        if (breakText < 1) {
            aVar.f4104a = "";
            aVar.f4105b = 0;
            return;
        }
        sb.append(str.substring(0, breakText));
        sb.append((char) 8230);
        aVar.f4104a = sb.toString();
        aVar.f4105b = i2;
    }

    @Deprecated
    public static void a(Activity activity, boolean z) {
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

    @Deprecated
    public static int c(View view) {
        if (view != null && view.getParent() != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                return ((ViewGroup) parent).indexOfChild(view);
            }
        }
        return -1;
    }

    @Deprecated
    public static boolean d(View view) {
        if (view == null || view.getAnimation() == null) {
            return false;
        }
        view.clearAnimation();
        return true;
    }

    @Deprecated
    public static void a(boolean z, View view, View.OnClickListener onClickListener) {
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
