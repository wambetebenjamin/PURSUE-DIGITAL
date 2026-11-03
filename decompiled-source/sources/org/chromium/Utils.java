package org.chromium;

import com.ttnet.org.chromium.net.CronetException;
import com.ttnet.org.chromium.net.NetworkException;
import com.ttnet.org.chromium.net.impl.NetworkExceptionImpl;
import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.conn.ConnectTimeoutException;
/* loaded from: classes10.dex */
public class Utils {
    public static List getAllInterfaces(Class cls) {
        if (cls == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (int i2 = 0; i2 < interfaces.length; i2++) {
                if (!arrayList.contains(interfaces[i2])) {
                    arrayList.add(interfaces[i2]);
                }
                for (Class cls2 : getAllInterfaces(interfaces[i2])) {
                    if (!arrayList.contains(cls2)) {
                        arrayList.add(cls2);
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return arrayList;
    }

    public static int mapUrlRequestErrorToApiErrorCode(int i2) {
        switch (i2) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
            case 10:
                return 10;
            case 11:
                return 11;
            default:
                if (CronetDependManager.inst().loggerDebug()) {
                    CronetDependManager.inst().loggerD("monitor", "Unknown error code: " + i2);
                    return i2;
                }
                return i2;
        }
    }

    public static CronetException createCronetException(int i2, int i3, String str) {
        return new NetworkExceptionImpl("Exception in CronetUrlRequest: " + str, mapUrlRequestErrorToApiErrorCode(i2), i3);
    }

    public static IOException tryConvertCronetException(CronetException cronetException) {
        IOException unknownHostException;
        if (cronetException instanceof NetworkException) {
            switch (((NetworkException) cronetException).getErrorCode()) {
                case 1:
                    unknownHostException = new UnknownHostException(cronetException.getMessage());
                    break;
                case 2:
                case 3:
                    unknownHostException = new ConnectException(cronetException.getMessage());
                    break;
                case 4:
                    unknownHostException = new SocketTimeoutException(cronetException.getMessage());
                    break;
                case 5:
                case 7:
                    unknownHostException = new ConnectException(cronetException.getMessage());
                    break;
                case 6:
                    unknownHostException = new ConnectTimeoutException(cronetException.getMessage());
                    break;
                case 8:
                    unknownHostException = new SocketException("reset by peer " + cronetException.getMessage());
                    break;
                case 9:
                    unknownHostException = new NoRouteToHostException(cronetException.getMessage());
                    break;
                default:
                    return cronetException;
            }
            return unknownHostException;
        }
        return cronetException;
    }
}
