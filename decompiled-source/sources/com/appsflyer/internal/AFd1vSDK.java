package com.appsflyer.internal;

import android.content.Context;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public interface AFd1vSDK {
    AFa1ySDK AFInAppEventParameterName(Context context);

    /* loaded from: classes.dex */
    public static final class AFa1ySDK {
        public final String AFKeystoreWrapper;
        public final float values;

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof AFa1ySDK) {
                AFa1ySDK aFa1ySDK = (AFa1ySDK) obj;
                return Intrinsics.areEqual((Object) Float.valueOf(this.values), (Object) Float.valueOf(aFa1ySDK.values)) && Intrinsics.areEqual(this.AFKeystoreWrapper, aFa1ySDK.AFKeystoreWrapper);
            }
            return false;
        }

        public final int hashCode() {
            int hashCode = Float.hashCode(this.values) * 31;
            String str = this.AFKeystoreWrapper;
            return hashCode + (str == null ? 0 : str.hashCode());
        }

        public final String toString() {
            return new StringBuilder("BatteryData(level=").append(this.values).append(", charging=").append(this.AFKeystoreWrapper).append(')').toString();
        }

        public AFa1ySDK(float f2, String str) {
            this.values = f2;
            this.AFKeystoreWrapper = str;
        }
    }
}
