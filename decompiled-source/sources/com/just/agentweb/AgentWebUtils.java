package com.just.agentweb;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;
import androidx.loader.content.CursorLoader;
import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
import com.google.android.material.snackbar.Snackbar;
import com.meituan.robust.Constants;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class AgentWebUtils {
    private static final String TAG = "AgentWebUtils";
    private static Handler mHandler;
    private static Toast mToast;
    private static WeakReference<Snackbar> snackbarWeakReference;

    private AgentWebUtils() {
        throw new UnsupportedOperationException("u can't init me");
    }

    public static int dp2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void clearWebView(WebView webView) {
        if (webView != null && Looper.myLooper() == Looper.getMainLooper()) {
            webView.loadUrl("about:blank");
            webView.stopLoading();
            if (webView.getHandler() != null) {
                webView.getHandler().removeCallbacksAndMessages(null);
            }
            webView.removeAllViews();
            ViewGroup viewGroup = (ViewGroup) webView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(webView);
            }
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.setTag(null);
            webView.clearHistory();
            webView.destroy();
        }
    }

    public static String getAgentWebFilePath(Context context) {
        if (!TextUtils.isEmpty(AgentWebConfig.AGENTWEB_FILE_PATH)) {
            return AgentWebConfig.AGENTWEB_FILE_PATH;
        }
        File file = new File(getDiskExternalCacheDir(context), "agentweb-cache");
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Throwable unused) {
            LogUtils.i(TAG, "create dir exception");
        }
        LogUtils.i(TAG, "path:" + file.getAbsolutePath() + "  path:" + file.getPath());
        String absolutePath = file.getAbsolutePath();
        AgentWebConfig.AGENTWEB_FILE_PATH = absolutePath;
        return absolutePath;
    }

    public static File createFileByName(Context context, String str, boolean z) throws IOException {
        String agentWebFilePath = getAgentWebFilePath(context);
        if (TextUtils.isEmpty(agentWebFilePath)) {
            return null;
        }
        File file = new File(agentWebFilePath, str);
        if (!file.exists()) {
            file.createNewFile();
        } else if (z) {
            file.delete();
            file.createNewFile();
        }
        return file;
    }

    public static int checkNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 0;
        }
        int type = activeNetworkInfo.getType();
        if (type != 0) {
            return (type == 1 || type == 6 || type == 9) ? 1 : 0;
        }
        int subtype = activeNetworkInfo.getSubtype();
        switch (subtype) {
            case 1:
            case 2:
                return 4;
            case 3:
            case 4:
            case 5:
            case 6:
                return 3;
            default:
                switch (subtype) {
                    case 12:
                        return 3;
                    case 13:
                    case 14:
                    case 15:
                        return 2;
                    default:
                        return 0;
                }
        }
    }

    public static long getAvailableStorage() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().toString());
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
            }
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (RuntimeException unused) {
            return 0L;
        }
    }

    public static Uri getUriFromFile(Context context, File file) {
        if (Build.VERSION.SDK_INT >= 24) {
            return getUriFromFileForN(context, file);
        }
        return Uri.fromFile(file);
    }

    static Uri getUriFromFileForN(Context context, File file) {
        return FileProvider.getUriForFile(context, context.getPackageName() + ".AgentWebFileProvider", file);
    }

    static void setIntentDataAndType(Context context, Intent intent, String str, File file, boolean z) {
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setDataAndType(getUriFromFile(context, file), str);
            intent.addFlags(1);
            if (z) {
                intent.addFlags(2);
                return;
            }
            return;
        }
        intent.setDataAndType(Uri.fromFile(file), str);
    }

    static void setIntentData(Context context, Intent intent, File file, boolean z) {
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setData(getUriFromFile(context, file));
            intent.addFlags(1);
            if (z) {
                intent.addFlags(2);
                return;
            }
            return;
        }
        intent.setData(Uri.fromFile(file));
    }

    static String getDiskExternalCacheDir(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        if ("mounted".equals(EnvironmentCompat.getStorageState(externalCacheDir))) {
            return externalCacheDir.getAbsolutePath();
        }
        return null;
    }

    static void grantPermissions(Context context, Intent intent, Uri uri, boolean z) {
        int i2 = z ? 3 : 1;
        intent.addFlags(i2);
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 65536)) {
            context.grantUriPermission(resolveInfo.activityInfo.packageName, uri, i2);
        }
    }

    private static String getMIMEType(File file) {
        String name = file.getName();
        String lowerCase = name.substring(name.lastIndexOf(c.a.DOT) + 1, name.length()).toLowerCase();
        return lowerCase.equals("pdf") ? "application/pdf" : (lowerCase.equals("m4a") || lowerCase.equals("mp3") || lowerCase.equals("mid") || lowerCase.equals("xmf") || lowerCase.equals("ogg") || lowerCase.equals("wav")) ? "audio/*" : (lowerCase.equals("3gp") || lowerCase.equals("mp4")) ? "video/*" : (lowerCase.equals("jpg") || lowerCase.equals("gif") || lowerCase.equals("png") || lowerCase.equals("jpeg") || lowerCase.equals("bmp")) ? "image/*" : lowerCase.equals("apk") ? "application/vnd.android.package-archive" : (lowerCase.equals("pptx") || lowerCase.equals("ppt")) ? "application/vnd.ms-powerpoint" : (lowerCase.equals("docx") || lowerCase.equals("doc")) ? "application/vnd.ms-word" : (lowerCase.equals("xlsx") || lowerCase.equals("xls")) ? "application/vnd.ms-excel" : "*/*";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void show(View view, CharSequence charSequence, int i2, int i3, int i4, CharSequence charSequence2, int i5, View.OnClickListener onClickListener) {
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new ForegroundColorSpan(i3), 0, spannableString.length(), 33);
        WeakReference<Snackbar> weakReference = new WeakReference<>(Snackbar.make(view, spannableString, i2));
        snackbarWeakReference = weakReference;
        Snackbar snackbar = weakReference.get();
        snackbar.getView().setBackgroundColor(i4);
        if (charSequence2 != null && charSequence2.length() > 0 && onClickListener != null) {
            snackbar.setActionTextColor(i5);
            snackbar.setAction(charSequence2, onClickListener);
        }
        snackbar.show();
    }

    static void dismiss() {
        WeakReference<Snackbar> weakReference = snackbarWeakReference;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        snackbarWeakReference.get().dismiss();
        snackbarWeakReference = null;
    }

    public static boolean checkWifi(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1;
    }

    public static boolean checkNetwork(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? false : true;
    }

    static boolean isOverriedMethod(Object obj, String str, String str2, Class... clsArr) {
        LogUtils.i(TAG, "  methodName:" + str + "   method:" + str2);
        boolean z = false;
        if (obj == null) {
            return false;
        }
        try {
            z = !obj.getClass().getMethod(str, clsArr).toGenericString().contains(str2);
        } catch (Exception e2) {
            if (LogUtils.isDebug()) {
                e2.printStackTrace();
            }
        }
        LogUtils.i(TAG, "isOverriedMethod:" + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Method isExistMethod(Object obj, String str, Class... clsArr) {
        if (obj == null) {
            return null;
        }
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    static void clearAgentWebCache(Context context) {
        try {
            clearCacheFolder(new File(getAgentWebFilePath(context)), 0);
        } catch (Throwable th) {
            if (LogUtils.isDebug()) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clearWebViewAllCache(Context context, WebView webView) {
        try {
            AgentWebConfig.removeAllCookies(null);
            webView.getSettings().setCacheMode(2);
            context.deleteDatabase("webviewCache.db");
            context.deleteDatabase("webview.db");
            webView.clearCache(true);
            webView.clearHistory();
            webView.clearFormData();
            clearCacheFolder(new File(AgentWebConfig.getCachePath(context)), 0);
        } catch (Exception e2) {
            if (AgentWebConfig.DEBUG) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clearWebViewAllCache(Context context) {
        try {
            clearWebViewAllCache(context, new LollipopFixedWebView(context.getApplicationContext()));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int clearCacheFolder(File file, int i2) {
        int i3;
        File[] listFiles;
        if (file != null) {
            Log.i("Info", "dir:" + file.getAbsolutePath());
        }
        if (file == null || !file.isDirectory()) {
            return 0;
        }
        try {
            i3 = 0;
            for (File file2 : file.listFiles()) {
                try {
                    if (file2.isDirectory()) {
                        i3 += clearCacheFolder(file2, i2);
                    }
                    if (file2.lastModified() < new Date().getTime() - (i2 * 86400000)) {
                        Log.i(TAG, "file name:" + file2.getName());
                        if (file2.delete()) {
                            i3++;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    Log.e("Info", String.format("Failed to clean the cache, result %s", e.getMessage()));
                    return i3;
                }
            }
        } catch (Exception e3) {
            e = e3;
            i3 = 0;
        }
        return i3;
    }

    static void clearCache(Context context, int i2) {
        Log.i("Info", String.format("Starting cache prune, deleting files older than %d days", Integer.valueOf(i2)));
        Log.i("Info", String.format("Cache pruning completed, %d files deleted", Integer.valueOf(clearCacheFolder(context.getCacheDir(), i2))));
    }

    public static String[] uriToPath(Activity activity, Uri[] uriArr) {
        if (activity != null && uriArr != null && uriArr.length != 0) {
            try {
                String[] strArr = new String[uriArr.length];
                int length = uriArr.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < length) {
                    Uri uri = uriArr[i2];
                    int i4 = i3 + 1;
                    strArr[i3] = Build.VERSION.SDK_INT > 18 ? getFileAbsolutePath(activity, uri) : getRealPathBelowVersion(activity, uri);
                    i2++;
                    i3 = i4;
                }
                return strArr;
            } catch (Throwable th) {
                if (LogUtils.isDebug()) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    private static String getRealPathBelowVersion(Context context, Uri uri) {
        String str;
        LogUtils.i(TAG, "method -> getRealPathBelowVersion " + uri + "   path:" + uri.getPath() + "    getAuthority:" + uri.getAuthority());
        String[] strArr = {"_data"};
        Cursor loadInBackground = new CursorLoader(context, uri, strArr, null, null, null).loadInBackground();
        if (loadInBackground != null) {
            loadInBackground.moveToFirst();
            str = loadInBackground.getString(loadInBackground.getColumnIndex(strArr[0]));
            loadInBackground.close();
        } else {
            str = null;
        }
        return str == null ? uri.getPath() : str;
    }

    public static File createImageFile(Context context) {
        try {
            return createFileByName(context, String.format("aw_%s.jpg", new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date())), true);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File createVideoFile(Context context) {
        try {
            return createFileByName(context, String.format("aw_%s.mp4", new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date())), true);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static void closeIO(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    static String getFileAbsolutePath(Activity activity, Uri uri) {
        Uri uri2 = null;
        if (activity != null && uri != null) {
            if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(activity, uri)) {
                if (isExternalStorageDocument(uri)) {
                    String[] split = DocumentsContract.getDocumentId(uri).split(CertificateUtil.DELIMITER);
                    if ("primary".equalsIgnoreCase(split[0])) {
                        return Environment.getExternalStorageDirectory() + c.a.SEPARATOR + split[1];
                    }
                } else if (isDownloadsDocument(uri)) {
                    return getDataColumn(activity, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
                } else {
                    if (isMediaDocument(uri)) {
                        String[] split2 = DocumentsContract.getDocumentId(uri).split(CertificateUtil.DELIMITER);
                        String str = split2[0];
                        if ("image".equals(str)) {
                            uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        } else if ("video".equals(str)) {
                            uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        } else if ("audio".equals(str)) {
                            uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        }
                        return getDataColumn(activity, uri2, "_id=?", new String[]{split2[1]});
                    }
                }
            } else if (uri.getAuthority().equalsIgnoreCase(activity.getPackageName() + ".AgentWebFileProvider")) {
                String path = uri.getPath();
                return getAgentWebFilePath(activity) + File.separator + path.substring(path.lastIndexOf(c.a.SEPARATOR) + 1, path.length());
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(activity, uri, null, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndexOrThrow("_data"));
                        if (query != null) {
                            query.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    static Intent getInstallApkIntentCompat(Context context, File file) {
        Intent action = new Intent().setAction("android.intent.action.VIEW");
        setIntentDataAndType(context, action, "application/vnd.android.package-archive", file, false);
        return action;
    }

    public static Intent getCommonFileIntentCompat(Context context, File file) {
        Intent action = new Intent().setAction("android.intent.action.VIEW");
        setIntentDataAndType(context, action, getMIMEType(file), file, false);
        return action;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent getIntentCaptureCompat(Context context, File file) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        Uri uriFromFile = getUriFromFile(context, file);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("output", uriFromFile);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent getIntentVideoCompat(Context context, File file) {
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        Uri uriFromFile = getUriFromFile(context, file);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("output", uriFromFile);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (str.startsWith(Constants.ARRAY_TYPE)) {
                new JSONArray(str);
            } else {
                new JSONObject(str);
            }
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    public static boolean isUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isEmptyCollection(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    static boolean isEmptyMap(Map map) {
        return map == null || map.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void toastShowShort(Context context, String str) {
        Toast toast = mToast;
        if (toast == null) {
            mToast = Toast.makeText(context.getApplicationContext(), str, 0);
        } else {
            toast.setText(str);
        }
        mToast.show();
    }

    @Deprecated
    static void getUIControllerAndShowMessage(Activity activity, String str, String str2) {
        AbsAgentWebUIController provide;
        if (activity == null || activity.isFinishing() || (provide = ((WebParentLayout) activity.findViewById(R.id.web_parent_layout_id)).provide()) == null) {
            return;
        }
        provide.onShowMessage(str, str2);
    }

    public static boolean hasPermission(Context context, String... strArr) {
        return hasPermission(context, Arrays.asList(strArr));
    }

    public static boolean hasPermission(Context context, List<String> list) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        for (String str : list) {
            if (ContextCompat.checkSelfPermission(context, str) == -1) {
                return false;
            }
            String permissionToOp = AppOpsManagerCompat.permissionToOp(str);
            if (!TextUtils.isEmpty(permissionToOp) && AppOpsManagerCompat.noteProxyOp(context, permissionToOp, context.getPackageName()) != 0) {
                return false;
            }
        }
        return true;
    }

    public static List<String> getDeniedPermissions(Activity activity, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (!hasPermission(activity, strArr[i2])) {
                arrayList.add(strArr[i2]);
            }
        }
        return arrayList;
    }

    public static AbsAgentWebUIController getAgentWebUIControllerByWebView(WebView webView) {
        return getWebParentLayoutByWebView(webView).provide();
    }

    public static String getApplicationName(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            packageManager = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    static WebParentLayout getWebParentLayoutByWebView(WebView webView) {
        if (!(webView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException("please check webcreator's create method was be called ?");
        }
        ViewGroup viewGroup = (ViewGroup) webView.getParent();
        while (viewGroup != null) {
            String str = TAG;
            LogUtils.i(str, "ViewGroup:" + viewGroup);
            if (viewGroup.getId() == R.id.web_parent_layout_id) {
                WebParentLayout webParentLayout = (WebParentLayout) viewGroup;
                LogUtils.i(str, "found WebParentLayout");
                return webParentLayout;
            }
            ViewParent parent = viewGroup.getParent();
            viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        }
        throw new IllegalStateException("please check webcreator's create method was be called ?");
    }

    public static void runInUiThread(Runnable runnable) {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        mHandler.post(runnable);
    }

    public static boolean showFileChooserCompat(Activity activity, WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams, PermissionInterceptor permissionInterceptor, ValueCallback valueCallback2, String str, Handler.Callback callback) {
        try {
            Object invoke = Class.forName("com.just.agentweb.filechooser.FileChooser").getDeclaredMethod("newBuilder", Activity.class, WebView.class).invoke(null, activity, webView);
            Class<?> cls = invoke.getClass();
            if (valueCallback != null) {
                Method declaredMethod = cls.getDeclaredMethod("setUriValueCallbacks", ValueCallback.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(invoke, valueCallback);
            }
            if (fileChooserParams != null) {
                Method declaredMethod2 = cls.getDeclaredMethod("setFileChooserParams", WebChromeClient.FileChooserParams.class);
                declaredMethod2.setAccessible(true);
                declaredMethod2.invoke(invoke, fileChooserParams);
            }
            if (valueCallback2 != null) {
                Method declaredMethod3 = cls.getDeclaredMethod("setUriValueCallback", ValueCallback.class);
                declaredMethod3.setAccessible(true);
                declaredMethod3.invoke(invoke, valueCallback2);
            }
            if (!TextUtils.isEmpty(str)) {
                Method declaredMethod4 = cls.getDeclaredMethod("setAcceptType", String.class);
                declaredMethod4.setAccessible(true);
                declaredMethod4.invoke(invoke, str);
            }
            if (callback != null) {
                Method declaredMethod5 = cls.getDeclaredMethod("setJsChannelCallback", Handler.Callback.class);
                declaredMethod5.setAccessible(true);
                declaredMethod5.invoke(invoke, callback);
            }
            Method declaredMethod6 = cls.getDeclaredMethod("setPermissionInterceptor", PermissionInterceptor.class);
            declaredMethod6.setAccessible(true);
            declaredMethod6.invoke(invoke, permissionInterceptor);
            Method declaredMethod7 = cls.getDeclaredMethod("build", new Class[0]);
            declaredMethod7.setAccessible(true);
            Object invoke2 = declaredMethod7.invoke(invoke, new Object[0]);
            Method declaredMethod8 = invoke2.getClass().getDeclaredMethod("openFileChooser", new Class[0]);
            declaredMethod8.setAccessible(true);
            declaredMethod8.invoke(invoke2, new Object[0]);
        } catch (Throwable th) {
            if (LogUtils.isDebug()) {
                th.printStackTrace();
            }
            if (th instanceof ClassNotFoundException) {
                LogUtils.e(TAG, "Please check whether compile'com.just.agentweb:filechooser:x.x.x' dependency was added.");
            }
            if (valueCallback != null) {
                LogUtils.i(TAG, "onReceiveValue empty");
                return false;
            } else if (valueCallback2 != null) {
                valueCallback2.onReceiveValue(null);
            }
        }
        return true;
    }

    public static String md5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e2) {
            if (LogUtils.isDebug()) {
                e2.printStackTrace();
                return "";
            }
            return "";
        }
    }
}
