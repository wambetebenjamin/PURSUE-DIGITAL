package com.appsflyer.internal;

import android.content.Context;
import kotlin.Metadata;
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u0000 \r2\u00020\u0001:\u0002\r\u000eJ\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\n\u0010\f"}, d2 = {"Lcom/appsflyer/internal/AFd1ySDK;", "", "", "valueOf", "()Z", "Landroid/content/Context;", "p0", "Lcom/appsflyer/internal/AFd1ySDK$AFa1ySDK;", "p1", "", "AFKeystoreWrapper", "(Landroid/content/Context;Lcom/appsflyer/internal/AFd1ySDK$AFa1ySDK;)V", "()V", "AFa1zSDK", "AFa1ySDK"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public interface AFd1ySDK {
    public static final AFa1zSDK AFa1zSDK = AFa1zSDK.AFInAppEventParameterName;

    /* loaded from: classes.dex */
    public interface AFa1ySDK {
        void AFInAppEventParameterName();

        void valueOf(AFh1zSDK aFh1zSDK);
    }

    void AFKeystoreWrapper();

    void AFKeystoreWrapper(Context context, AFa1ySDK aFa1ySDK);

    boolean valueOf();

    /* loaded from: classes.dex */
    public static final class AFa1zSDK {
        static final /* synthetic */ AFa1zSDK AFInAppEventParameterName = new AFa1zSDK();
        private static long AFInAppEventType = 500;

        private AFa1zSDK() {
        }

        public static long AFKeystoreWrapper() {
            return AFInAppEventType;
        }
    }
}
