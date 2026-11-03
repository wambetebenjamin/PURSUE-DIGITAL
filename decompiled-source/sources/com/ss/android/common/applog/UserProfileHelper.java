package com.ss.android.common.applog;

import android.content.Context;
import android.text.TextUtils;
import com.ss.android.common.profile.a;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class UserProfileHelper {
    private static final String[] HEADER = {"aid", "region", "os", "package", "app_version", "sdk_version"};
    private static final String USER_PROFILE_DELETE = "delete";
    private static final String USER_PROFILE_PATH = "/service/2/userprofile/app/%s/device/%s/%s";
    private static final String USER_PROFILE_SET = "set";
    private static final String USER_PROFILE_SET_ONCE = "setOnce";
    private static final String USER_PROFILE_SYNC = "synchronize";
    private static volatile TeaThread sTeaThread;

    /* loaded from: classes9.dex */
    public interface UserProfileCallback {
        public static final int NET_ERROR = 1;
        public static final int NOT_SATISFY = 3;
        public static final int NO_NET = 0;
        public static final int RESULT_ERROR = 2;

        void onFail(int i2);

        void onSuccess();
    }

    /* loaded from: classes9.dex */
    public interface UserProfileCheckCallback {
        void onCheckFail();

        void onCheckSuccess(String str, Context context, String str2, String str3);
    }

    public static void userProfileDelete(final String str, final UserProfileCallback userProfileCallback) {
        AppLog.userProfileCheck(new UserProfileCheckCallback() { // from class: com.ss.android.common.applog.UserProfileHelper.1
            @Override // com.ss.android.common.applog.UserProfileHelper.UserProfileCheckCallback
            public void onCheckFail() {
                UserProfileCallback userProfileCallback2 = UserProfileCallback.this;
                if (userProfileCallback2 != null) {
                    userProfileCallback2.onFail(3);
                }
            }

            @Override // com.ss.android.common.applog.UserProfileHelper.UserProfileCheckCallback
            public void onCheckSuccess(String str2, Context context, String str3, String str4) {
                if (TextUtils.isEmpty(str)) {
                    onCheckFail();
                } else {
                    UserProfileHelper.postRunnable(new a(str4 + String.format(UserProfileHelper.USER_PROFILE_PATH, str2, str3, UserProfileHelper.USER_PROFILE_DELETE), str2, UserProfileHelper.getBody(str, null), new UserProfileRetryCallback(true, UserProfileCallback.this), context));
                }
            }
        });
    }

    public static void userProfileSet(final String str, final Object obj, final UserProfileCallback userProfileCallback) {
        AppLog.userProfileCheck(new UserProfileCheckCallback() { // from class: com.ss.android.common.applog.UserProfileHelper.2
            @Override // com.ss.android.common.applog.UserProfileHelper.UserProfileCheckCallback
            public void onCheckFail() {
                UserProfileCallback userProfileCallback2 = UserProfileCallback.this;
                if (userProfileCallback2 != null) {
                    userProfileCallback2.onFail(3);
                }
            }

            @Override // com.ss.android.common.applog.UserProfileHelper.UserProfileCheckCallback
            public void onCheckSuccess(String str2, Context context, String str3, String str4) {
                if (TextUtils.isEmpty(str)) {
                    onCheckFail();
                } else {
                    UserProfileHelper.postRunnable(new a(str4 + String.format(UserProfileHelper.USER_PROFILE_PATH, str2, str3, "set"), str2, UserProfileHelper.getBody(str, obj), new UserProfileRetryCallback(true, UserProfileCallback.this), context));
                }
            }
        });
    }

    public static void userProfileSet(String str, List<Object> list, UserProfileCallback userProfileCallback) {
        userProfileSet(str, (Object) list, userProfileCallback);
    }

    public static void userProfileSetOnce(final String str, final Object obj, final UserProfileCallback userProfileCallback) {
        AppLog.userProfileCheck(new UserProfileCheckCallback() { // from class: com.ss.android.common.applog.UserProfileHelper.3
            @Override // com.ss.android.common.applog.UserProfileHelper.UserProfileCheckCallback
            public void onCheckFail() {
                UserProfileCallback userProfileCallback2 = UserProfileCallback.this;
                if (userProfileCallback2 != null) {
                    userProfileCallback2.onFail(3);
                }
            }

            @Override // com.ss.android.common.applog.UserProfileHelper.UserProfileCheckCallback
            public void onCheckSuccess(String str2, Context context, String str3, String str4) {
                if (TextUtils.isEmpty(str)) {
                    onCheckFail();
                } else {
                    UserProfileHelper.postRunnable(new a(str4 + String.format(UserProfileHelper.USER_PROFILE_PATH, str2, str3, UserProfileHelper.USER_PROFILE_SET_ONCE), str2, UserProfileHelper.getBody(str, obj), new UserProfileRetryCallback(true, UserProfileCallback.this), context));
                }
            }
        });
    }

    public static void userProfileSetOnce(String str, List<Object> list, UserProfileCallback userProfileCallback) {
        userProfileSetOnce(str, (Object) list, userProfileCallback);
    }

    static void userProfileSync(final JSONObject jSONObject, final boolean z, final UserProfileCallback userProfileCallback) {
        AppLog.userProfileCheck(new UserProfileCheckCallback() { // from class: com.ss.android.common.applog.UserProfileHelper.4
            @Override // com.ss.android.common.applog.UserProfileHelper.UserProfileCheckCallback
            public void onCheckFail() {
                UserProfileCallback userProfileCallback2 = UserProfileCallback.this;
                if (userProfileCallback2 != null) {
                    userProfileCallback2.onFail(3);
                }
            }

            @Override // com.ss.android.common.applog.UserProfileHelper.UserProfileCheckCallback
            public void onCheckSuccess(String str, Context context, String str2, String str3) {
                JSONObject jSONObject2 = jSONObject;
                if (jSONObject2 == null || jSONObject2.length() == 0) {
                    onCheckFail();
                } else {
                    UserProfileHelper.postRunnable(new a(str3 + String.format(UserProfileHelper.USER_PROFILE_PATH, str, str2, UserProfileHelper.USER_PROFILE_SYNC), str, UserProfileHelper.getBody(jSONObject), new UserProfileRetryCallback(z, UserProfileCallback.this), context));
                }
            }
        });
    }

    public static void userProfileSync(JSONObject jSONObject, UserProfileCallback userProfileCallback) {
        userProfileSync(jSONObject, true, userProfileCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getBody(String str, Object obj) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (obj == null) {
                obj = "";
            }
            jSONObject.put(str, obj);
            return getBody(jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getBody(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("header", getHeader());
            jSONObject2.put("profile", jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject2.toString();
    }

    private static JSONObject getHeader() {
        try {
            return new JSONObject(AppLog.getHeaderCopy(), HEADER);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return new JSONObject();
        }
    }

    public static void postRunnable(Runnable runnable) {
        ensureHandlerThreadCreate();
        sTeaThread.ensureTeaThreadLite(runnable);
    }

    public static void postRunnableDelay(Runnable runnable, long j2) {
        ensureHandlerThreadCreate();
        sTeaThread.ensureTeaThreadLiteDelay(runnable, j2);
    }

    private static void ensureHandlerThreadCreate() {
        if (sTeaThread == null) {
            synchronized (UserProfileHelper.class) {
                if (sTeaThread == null) {
                    sTeaThread = TeaThread.createNewThread("user_profile_thread");
                    sTeaThread.start();
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class UserProfileRetryCallback implements UserProfileCallback {
        private static final int MAX_RETRY_TIMES = 1;
        private static final long RETRY_TIME_INTERVAL = 60000;
        private UserProfileCallback mOriginalCallback;
        private final boolean mRetry;
        private int mRetryTimes;
        private Runnable mRunnable;

        private UserProfileRetryCallback(boolean z, UserProfileCallback userProfileCallback) {
            this.mRetryTimes = 0;
            this.mOriginalCallback = userProfileCallback;
            this.mRetry = z;
        }

        @Override // com.ss.android.common.applog.UserProfileHelper.UserProfileCallback
        public void onSuccess() {
            UserProfileCallback userProfileCallback = this.mOriginalCallback;
            if (userProfileCallback != null) {
                userProfileCallback.onSuccess();
            }
        }

        @Override // com.ss.android.common.applog.UserProfileHelper.UserProfileCallback
        public void onFail(int i2) {
            if (this.mRetry && this.mRetryTimes < 1) {
                onRetry();
                this.mRetryTimes++;
                return;
            }
            UserProfileCallback userProfileCallback = this.mOriginalCallback;
            if (userProfileCallback != null) {
                userProfileCallback.onFail(i2);
            }
        }

        public void setRunnable(Runnable runnable) {
            this.mRunnable = runnable;
        }

        private void onRetry() {
            Runnable runnable = this.mRunnable;
            if (runnable != null) {
                UserProfileHelper.postRunnableDelay(runnable, 60000L);
            }
        }
    }
}
