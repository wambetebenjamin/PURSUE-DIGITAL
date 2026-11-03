package com.ttnet.org.chromium.base;

import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.TransactionTooLargeException;
import android.text.TextUtils;
import androidx.core.app.BundleCompat;
import com.ttnet.org.chromium.base.compat.ApiHelperForM;
import com.ttnet.org.chromium.base.compat.ApiHelperForS;
import java.io.Serializable;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class IntentUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String ANDROID_APP_REFERRER_SCHEME = "android-app";
    private static final Object COMPONENT_NAME_LOCK = new Object();
    public static final String EPHEMERAL_INSTALLER_CLASS = "com.google.android.gms.instantapps.routing.EphemeralInstallerActivity";
    private static final String TAG = "IntentUtils";
    public static final String TRUSTED_APPLICATION_CODE_EXTRA = "trusted_application_code_extra";
    private static ComponentName sFakeComponentName;
    private static boolean sForceTrustedIntentForTesting;

    public static boolean isInstantAppResolveInfo(ResolveInfo resolveInfo) {
        if (resolveInfo == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return resolveInfo.isInstantAppAvailable;
        }
        if (resolveInfo.activityInfo != null) {
            return EPHEMERAL_INSTALLER_CLASS.equals(resolveInfo.activityInfo.name);
        }
        return false;
    }

    public static boolean safeHasExtra(Intent intent, String str) {
        try {
            return intent.hasExtra(str);
        } catch (Throwable unused) {
            Log.e(TAG, "hasExtra failed on intent " + intent, new Object[0]);
            return false;
        }
    }

    public static void safeRemoveExtra(Intent intent, String str) {
        try {
            intent.removeExtra(str);
        } catch (Throwable unused) {
            Log.e(TAG, "removeExtra failed on intent " + intent, new Object[0]);
        }
    }

    public static boolean safeGetBooleanExtra(Intent intent, String str, boolean z) {
        try {
            return intent.getBooleanExtra(str, z);
        } catch (Throwable unused) {
            Log.e(TAG, "getBooleanExtra failed on intent " + intent, new Object[0]);
            return z;
        }
    }

    public static boolean safeGetBoolean(Bundle bundle, String str, boolean z) {
        try {
            return bundle.getBoolean(str, z);
        } catch (Throwable unused) {
            Log.e(TAG, "getBoolean failed on bundle " + bundle, new Object[0]);
            return z;
        }
    }

    public static int safeGetIntExtra(Intent intent, String str, int i2) {
        try {
            return intent.getIntExtra(str, i2);
        } catch (Throwable unused) {
            Log.e(TAG, "getIntExtra failed on intent " + intent, new Object[0]);
            return i2;
        }
    }

    public static int safeGetInt(Bundle bundle, String str, int i2) {
        try {
            return bundle.getInt(str, i2);
        } catch (Throwable unused) {
            Log.e(TAG, "getInt failed on bundle " + bundle, new Object[0]);
            return i2;
        }
    }

    public static int[] safeGetIntArrayExtra(Intent intent, String str) {
        try {
            return intent.getIntArrayExtra(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getIntArrayExtra failed on intent " + intent, new Object[0]);
            return null;
        }
    }

    public static int[] safeGetIntArray(Bundle bundle, String str) {
        try {
            return bundle.getIntArray(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getIntArray failed on bundle " + bundle, new Object[0]);
            return null;
        }
    }

    public static float[] safeGetFloatArray(Bundle bundle, String str) {
        try {
            return bundle.getFloatArray(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getFloatArray failed on bundle " + bundle, new Object[0]);
            return null;
        }
    }

    public static long safeGetLongExtra(Intent intent, String str, long j2) {
        try {
            return intent.getLongExtra(str, j2);
        } catch (Throwable unused) {
            Log.e(TAG, "getLongExtra failed on intent " + intent, new Object[0]);
            return j2;
        }
    }

    public static String safeGetStringExtra(Intent intent, String str) {
        try {
            return intent.getStringExtra(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getStringExtra failed on intent " + intent, new Object[0]);
            return null;
        }
    }

    public static String safeGetString(Bundle bundle, String str) {
        try {
            return bundle.getString(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getString failed on bundle " + bundle, new Object[0]);
            return null;
        }
    }

    public static Bundle safeGetBundleExtra(Intent intent, String str) {
        try {
            return intent.getBundleExtra(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getBundleExtra failed on intent " + intent, new Object[0]);
            return null;
        }
    }

    public static Bundle safeGetBundle(Bundle bundle, String str) {
        try {
            return bundle.getBundle(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getBundle failed on bundle " + bundle, new Object[0]);
            return null;
        }
    }

    public static <T extends Parcelable> T safeGetParcelable(Bundle bundle, String str) {
        try {
            return (T) bundle.getParcelable(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getParcelable failed on bundle " + bundle, new Object[0]);
            return null;
        }
    }

    public static <T extends Parcelable> T safeGetParcelableExtra(Intent intent, String str) {
        try {
            return (T) intent.getParcelableExtra(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getParcelableExtra failed on intent " + intent, new Object[0]);
            return null;
        }
    }

    public static <T extends Parcelable> ArrayList<T> getParcelableArrayListExtra(Intent intent, String str) {
        try {
            return intent.getParcelableArrayListExtra(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getParcelableArrayListExtra failed on intent " + intent, new Object[0]);
            return null;
        }
    }

    public static <T extends Parcelable> ArrayList<T> safeGetParcelableArrayList(Bundle bundle, String str) {
        try {
            return bundle.getParcelableArrayList(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getParcelableArrayList failed on bundle " + bundle, new Object[0]);
            return null;
        }
    }

    public static Parcelable[] safeGetParcelableArrayExtra(Intent intent, String str) {
        try {
            return intent.getParcelableArrayExtra(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getParcelableArrayExtra failed on intent " + intent, new Object[0]);
            return null;
        }
    }

    public static ArrayList<String> safeGetStringArrayListExtra(Intent intent, String str) {
        try {
            return intent.getStringArrayListExtra(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getStringArrayListExtra failed on intent " + intent, new Object[0]);
            return null;
        }
    }

    public static byte[] safeGetByteArrayExtra(Intent intent, String str) {
        try {
            return intent.getByteArrayExtra(str);
        } catch (Throwable unused) {
            Log.e(TAG, "getByteArrayExtra failed on intent " + intent, new Object[0]);
            return null;
        }
    }

    public static <T extends Serializable> T safeGetSerializableExtra(Intent intent, String str) {
        try {
            return (T) intent.getSerializableExtra(str);
        } catch (ClassCastException e2) {
            Log.e(TAG, "Invalide class for Serializable: " + str, e2);
            return null;
        } catch (Throwable unused) {
            Log.e(TAG, "getSerializableExtra failed on intent " + intent, new Object[0]);
            return null;
        }
    }

    public static IBinder safeGetBinder(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        try {
            return BundleCompat.getBinder(bundle, str);
        } catch (Throwable unused) {
            Log.e(TAG, "getBinder failed on bundle " + bundle, new Object[0]);
            return null;
        }
    }

    public static IBinder safeGetBinderExtra(Intent intent, String str) {
        if (intent.hasExtra(str)) {
            return safeGetBinder(intent.getExtras(), str);
        }
        return null;
    }

    public static void safePutBinderExtra(Intent intent, String str, IBinder iBinder) {
        if (intent == null) {
            return;
        }
        Bundle bundle = new Bundle();
        try {
            BundleCompat.putBinder(bundle, str, iBinder);
        } catch (Throwable unused) {
            Log.e(TAG, "putBinder failed on bundle " + bundle, new Object[0]);
        }
        intent.putExtras(bundle);
    }

    public static boolean safeStartActivity(Context context, Intent intent) {
        return safeStartActivity(context, intent, null);
    }

    public static boolean safeStartActivity(Context context, Intent intent, Bundle bundle) {
        try {
            context.startActivity(intent, bundle);
            return true;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }

    public static boolean isIntentForNewTaskOrNewDocument(Intent intent) {
        return (intent.getFlags() & 268959744) != 0;
    }

    public static int getParceledIntentSize(Intent intent) {
        Parcel obtain = Parcel.obtain();
        intent.writeToParcel(obtain, 0);
        return obtain.dataSize();
    }

    public static void logTransactionTooLargeOrRethrow(RuntimeException runtimeException, Intent intent) {
        if (runtimeException.getCause() instanceof TransactionTooLargeException) {
            Log.e(TAG, "Could not resolve Activity for intent " + intent.toString(), runtimeException);
            return;
        }
        throw runtimeException;
    }

    private static Intent logInvalidIntent(Intent intent, Exception exc) {
        Log.e(TAG, "Invalid incoming intent.", exc);
        return intent.replaceExtras((Bundle) null);
    }

    public static Intent sanitizeIntent(Intent intent) {
        if (intent == null) {
            return null;
        }
        try {
            intent.getBooleanExtra("TriggerUnparcel", false);
            return intent;
        } catch (BadParcelableException e2) {
            return logInvalidIntent(intent, e2);
        } catch (RuntimeException e3) {
            if (e3.getCause() instanceof ClassNotFoundException) {
                return logInvalidIntent(intent, e3);
            }
            throw e3;
        }
    }

    public static boolean isMainIntentFromLauncher(Intent intent) {
        return intent != null && TextUtils.equals(intent.getAction(), "android.intent.action.MAIN") && intent.hasCategory("android.intent.category.LAUNCHER") && (intent.getFlags() & 1048576) == 0;
    }

    public static int getPendingIntentMutabilityFlag(boolean z) {
        if (!z && Build.VERSION.SDK_INT >= 23) {
            return ApiHelperForM.getPendingIntentImmutableFlag();
        }
        if (!z || Build.VERSION.SDK_INT < 31) {
            return 0;
        }
        return ApiHelperForS.getPendingIntentMutableFlag();
    }

    public static boolean intentTargetsSelf(Context context, Intent intent) {
        boolean z = !TextUtils.isEmpty(intent.getPackage());
        boolean z2 = z && context.getPackageName().equals(intent.getPackage());
        boolean z3 = intent.getComponent() != null;
        if (z3 && context.getPackageName().equals(intent.getComponent().getPackageName())) {
            if (z) {
                return z2;
            }
            return true;
        } else if (z2) {
            return !z3;
        } else {
            return false;
        }
    }

    private static ComponentName getFakeComponentName(String str) {
        synchronized (COMPONENT_NAME_LOCK) {
            if (sFakeComponentName == null) {
                sFakeComponentName = new ComponentName(str, "FakeClass");
            }
        }
        return sFakeComponentName;
    }

    private static PendingIntent getAuthenticationToken() {
        Intent intent = new Intent();
        Context applicationContext = ContextUtils.getApplicationContext();
        intent.setComponent(getFakeComponentName(applicationContext.getPackageName()));
        return PendingIntent.getActivity(applicationContext, 0, intent, getPendingIntentMutabilityFlag(false));
    }

    public static void addTrustedIntentExtras(Intent intent) {
        if (intentTargetsSelf(ContextUtils.getApplicationContext(), intent)) {
            intent.putExtra(TRUSTED_APPLICATION_CODE_EXTRA, getAuthenticationToken());
        }
    }

    public static boolean isTrustedIntentFromSelf(Intent intent) {
        if (intent == null) {
            return false;
        }
        if (sForceTrustedIntentForTesting) {
            return true;
        }
        PendingIntent pendingIntent = (PendingIntent) safeGetParcelableExtra(intent, TRUSTED_APPLICATION_CODE_EXTRA);
        if (pendingIntent == null) {
            return false;
        }
        return getAuthenticationToken().equals(pendingIntent);
    }

    public static void setForceIsTrustedIntentForTesting(boolean z) {
        sForceTrustedIntentForTesting = z;
    }
}
