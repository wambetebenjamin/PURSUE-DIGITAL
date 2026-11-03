package com.ttnet.org.chromium.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.UserManager;
import android.provider.MediaStore;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodSubtype;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.widget.ImageViewCompat;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes9.dex */
public class ApiCompatibilityUtils {
    public static int compareBoolean(boolean z, boolean z2) {
        if (z == z2) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public static int compareLong(long j2, long j3) {
        int i2 = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
        if (i2 < 0) {
            return -1;
        }
        return i2 == 0 ? 0 : 1;
    }

    private static boolean isPasswordInputType(int i2) {
        int i3 = i2 & 4095;
        return i3 == 129 || i3 == 225 || i3 == 18;
    }

    private ApiCompatibilityUtils() {
    }

    /* loaded from: classes9.dex */
    private static class ApisQ {
        private ApisQ() {
        }

        static boolean isRunningInUserTestHarness() {
            return ActivityManager.isRunningInUserTestHarness();
        }

        static List<Integer> getTargetableDisplayIds(Activity activity) {
            DisplayManager displayManager;
            ArrayList arrayList = new ArrayList();
            if (activity == null || (displayManager = (DisplayManager) activity.getSystemService("display")) == null) {
                return arrayList;
            }
            Display[] displays = displayManager.getDisplays();
            ActivityManager activityManager = (ActivityManager) activity.getSystemService("activity");
            for (Display display : displays) {
                if (display.getState() == 2 && activityManager.isActivityStartAllowedOnDisplay(activity, display.getDisplayId(), new Intent(activity, activity.getClass()))) {
                    arrayList.add(Integer.valueOf(display.getDisplayId()));
                }
            }
            return arrayList;
        }
    }

    /* loaded from: classes9.dex */
    private static class ApisP {
        private ApisP() {
        }

        static String getProcessName() {
            return Application.getProcessName();
        }

        static Bitmap getBitmapByUri(ContentResolver contentResolver, Uri uri) {
            return ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri));
        }
    }

    /* loaded from: classes9.dex */
    private static class ApisO {
        private ApisO() {
        }

        static void initNotificationSettingsIntent(Intent intent, String str) {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", str);
        }

        static void disableSmartSelectionTextClassifier(TextView textView) {
            textView.setTextClassifier(TextClassifier.NO_OP);
        }

        static Bundle createLaunchDisplayIdActivityOptions(int i2) {
            ActivityOptions makeBasic = ActivityOptions.makeBasic();
            makeBasic.setLaunchDisplayId(i2);
            return makeBasic.toBundle();
        }
    }

    /* loaded from: classes9.dex */
    private static class ApisN {
        private ApisN() {
        }

        static String toHtml(Spanned spanned, int i2) {
            return Html.toHtml(spanned, i2);
        }

        static boolean isDemoUser() {
            return ((UserManager) ContextUtils.getApplicationContext().getSystemService("user")).isDemoUser();
        }

        static String getLocale(InputMethodSubtype inputMethodSubtype) {
            return inputMethodSubtype.getLanguageTag();
        }

        static boolean isInMultiWindowMode(Activity activity) {
            return activity.isInMultiWindowMode();
        }
    }

    /* loaded from: classes9.dex */
    private static class ApisM {
        private ApisM() {
        }

        public static void setStatusBarIconColor(View view, boolean z) {
            int systemUiVisibility = view.getSystemUiVisibility();
            view.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
        }
    }

    /* loaded from: classes9.dex */
    private static class ApisLmr1 {
        private ApisLmr1() {
        }

        static void setAccessibilityTraversalBefore(View view, int i2) {
            view.setAccessibilityTraversalBefore(i2);
        }
    }

    public static <T> T requireNonNull(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    public static <T> T requireNonNull(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    public static byte[] getBytesUtf8(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static String toHtml(Spanned spanned, int i2) {
        if (Build.VERSION.SDK_INT >= 24) {
            return ApisN.toHtml(spanned, i2);
        }
        return Html.toHtml(spanned);
    }

    public static Intent getNotificationSettingsIntent() {
        Intent intent = new Intent();
        String packageName = ContextUtils.getApplicationContext().getPackageName();
        if (Build.VERSION.SDK_INT >= 26) {
            ApisO.initNotificationSettingsIntent(intent, packageName);
        } else {
            intent.setAction("android.settings.ACTION_APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", packageName);
            intent.putExtra("app_uid", ContextUtils.getApplicationContext().getApplicationInfo().uid);
        }
        return intent;
    }

    public static void setStatusBarColor(Window window, int i2) {
        if (Build.VERSION.SDK_INT < 26 && i2 == -16777216 && window.getNavigationBarColor() == -16777216) {
            window.clearFlags(Integer.MIN_VALUE);
        } else {
            window.addFlags(Integer.MIN_VALUE);
        }
        window.setStatusBarColor(i2);
    }

    public static void setStatusBarIconColor(View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 23) {
            ApisM.setStatusBarIconColor(view, z);
        }
    }

    public static Drawable getDrawable(Resources resources, int i2) {
        return getDrawableForDensity(resources, i2, 0);
    }

    public static void setImageTintList(ImageView imageView, ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT == 21 && colorStateList != null && imageView.getImageTintMode() == null) {
            imageView.setImageTintMode(PorterDuff.Mode.SRC_IN);
        }
        ImageViewCompat.setImageTintList(imageView, colorStateList);
        if (Build.VERSION.SDK_INT == 21 && colorStateList == null) {
            imageView.refreshDrawableState();
        }
    }

    public static Drawable getDrawableForDensity(Resources resources, int i2, int i3) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (i3 == 0) {
                return resources.getDrawable(i2, null);
            }
            return resources.getDrawableForDensity(i2, i3, null);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    public static int getColor(Resources resources, int i2) {
        return resources.getColor(i2);
    }

    public static void setTextAppearance(TextView textView, int i2) {
        textView.setTextAppearance(textView.getContext(), i2);
    }

    public static boolean isDemoUser() {
        return Build.VERSION.SDK_INT >= 25 && ApisN.isDemoUser();
    }

    public static int checkPermission(Context context, String str, int i2, int i3) {
        try {
            return context.checkPermission(str, i2, i3);
        } catch (RuntimeException unused) {
            return -1;
        }
    }

    public static String getLocale(InputMethodSubtype inputMethodSubtype) {
        if (Build.VERSION.SDK_INT >= 24) {
            return ApisN.getLocale(inputMethodSubtype);
        }
        return inputMethodSubtype.getLocale();
    }

    public static boolean isInMultiWindowMode(Activity activity) {
        if (Build.VERSION.SDK_INT >= 24) {
            return ApisN.isInMultiWindowMode(activity);
        }
        return false;
    }

    public static List<Integer> getTargetableDisplayIds(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            return ApisQ.getTargetableDisplayIds(activity);
        }
        return new ArrayList();
    }

    public static void disableSmartSelectionTextClassifier(TextView textView) {
        if (Build.VERSION.SDK_INT >= 26) {
            ApisO.disableSmartSelectionTextClassifier(textView);
        }
    }

    public static Bundle createLaunchDisplayIdActivityOptions(int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            return ApisO.createLaunchDisplayIdActivityOptions(i2);
        }
        return null;
    }

    public static void setAccessibilityTraversalBefore(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 22) {
            ApisLmr1.setAccessibilityTraversalBefore(view, i2);
        }
    }

    public static void setPasswordEditTextContentDescription(EditText editText) {
        if (Build.VERSION.SDK_INT < 24 && isPasswordInputType(editText.getInputType()) && !TextUtils.isEmpty(editText.getHint())) {
            editText.setContentDescription(editText.getHint());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getProcessName() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ApisP.getProcessName();
        }
        try {
            return (String) Class.forName("android.app.ActivityThread").getMethod("currentProcessName", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static boolean isRunningInUserTestHarness() {
        if (Build.VERSION.SDK_INT >= 29) {
            return ApisQ.isRunningInUserTestHarness();
        }
        return false;
    }

    public static Bitmap getBitmapByUri(ContentResolver contentResolver, Uri uri) {
        if (Build.VERSION.SDK_INT >= 28) {
            return ApisP.getBitmapByUri(contentResolver, uri);
        }
        return MediaStore.Images.Media.getBitmap(contentResolver, uri);
    }
}
