package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class AFi1iSDK {

    /* loaded from: classes.dex */
    enum AFa1ySDK {
        NONE,
        FIRST,
        ALL
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<BigDecimal> AFInAppEventParameterName(Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        Float f2 = (Float) arrayList.get(0);
        Float f3 = (Float) arrayList.get(1);
        Float f4 = (Float) arrayList.get(2);
        ArrayList arrayList2 = new ArrayList();
        try {
            arrayList2.add(BigDecimal.valueOf(AFc1uSDK.values(f2.toString())));
            arrayList2.add(BigDecimal.valueOf(AFc1uSDK.values(f3.toString())));
            arrayList2.add(BigDecimal.valueOf(AFc1uSDK.values(f4.toString())));
        } catch (ParseException e2) {
            AFLogger.afErrorLogForExcManagerOnly("failed to parse string to number", e2);
        }
        return arrayList2;
    }

    /* loaded from: classes.dex */
    enum AFa1vSDK {
        UNKNOWN(0),
        ACCELEROMETER(1),
        MAGNETOMETER(2),
        RESERVED(3),
        GYROSCOPE(4);
        
        private int unregisterClient;

        AFa1vSDK(int i2) {
            this.unregisterClient = i2;
        }
    }

    /* loaded from: classes.dex */
    enum AFa1tSDK {
        UNKNOWN("uk"),
        ACCELEROMETER("am"),
        MAGNETOMETER("mm"),
        RESERVED("rs"),
        GYROSCOPE("gs");
        
        String values;

        AFa1tSDK(String str) {
            this.values = str;
        }
    }
}
