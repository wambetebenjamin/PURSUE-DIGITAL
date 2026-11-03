package com.bytedance.ttgame.core.monitor;

import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.o;
import com.bytedance.frameworks.baselib.network.http.exception.b;
import com.bytedance.frameworks.baselib.network.http.exception.c;
import com.bytedance.frameworks.baselib.network.http.exception.e;
import com.bytedance.frameworks.baselib.network.http.util.d;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.conn.ConnectTimeoutException;
/* loaded from: classes3.dex */
public class MonitorNetUtils {
    public static final int SC_BIND_EXCEPTION = 7;
    public static final int SC_CLIENT_PROTOCOL_EXCEPTION = 19;
    public static final int SC_CONNECT_EXCEPTION = 8;
    public static final int SC_CONNECT_TIMEOUT = 2;
    public static final int SC_EACCES = 36;
    public static final int SC_EADDRINUSE = 17;
    public static final int SC_EADDRNOTAVAIL = 16;
    public static final int SC_ECONNREFUSED = 13;
    public static final int SC_ECONNRESET = 12;
    public static final int SC_EDQUOT = 34;
    public static final int SC_EHOSTUNREACH = 14;
    public static final int SC_EIO = 37;
    public static final int SC_ENETUNREACH = 15;
    public static final int SC_ENOENT = 33;
    public static final int SC_EROFS = 35;
    public static final int SC_FILE_TOO_LARGE = 20;
    public static final int SC_IO_EXCEPTION = 4;
    public static final int SC_NO_HTTP_RESPONSE = 18;
    public static final int SC_NO_REOUTE_TO_HOST = 9;
    public static final int SC_NO_SPACE = 32;
    public static final int SC_PORT_UNREACHABLE = 10;
    public static final int SC_RESET_BY_PEER = 6;
    public static final int SC_SOCKET_EXCEPTION = 5;
    public static final int SC_SOCKET_TIMEOUT = 3;
    public static final int SC_UNKNOWN = 1;
    public static final int SC_UNKNOWN_HOST = 11;
    private static final String TAG = "AppLog";

    public static int checkHttpRequestException(Throwable th, String[] strArr) {
        int i2;
        int i3 = 1;
        if (th == null) {
            return 1;
        }
        if (strArr == null) {
            strArr = new String[1];
        }
        if (th instanceof c) {
            i3 = ((c) th).a();
        } else if (th instanceof d) {
            i3 = 20;
        } else {
            if (!(th instanceof ConnectTimeoutException)) {
                if (th instanceof SocketTimeoutException) {
                    if (o.isEmpty(th.getMessage()) || !th.getMessage().contains("connect timed out")) {
                        i3 = 3;
                    }
                } else if (th instanceof BindException) {
                    i3 = 7;
                } else if (th instanceof ConnectException) {
                    i3 = 8;
                } else if (th instanceof NoRouteToHostException) {
                    i3 = 9;
                } else if (th instanceof PortUnreachableException) {
                    i3 = 10;
                } else if (th instanceof SocketException) {
                    i3 = 5;
                    String message = th.getMessage();
                    if (message != null && message.indexOf("reset by peer") >= 0) {
                        i3 = 6;
                    }
                } else if (th instanceof UnknownHostException) {
                    i3 = 11;
                } else if (th instanceof e) {
                    i3 = 18;
                } else if (th instanceof b) {
                    i3 = 19;
                } else if (th instanceof IOException) {
                    i3 = 4;
                }
            }
            i3 = 2;
        }
        try {
            if (i3 == 2) {
                String message2 = th.getMessage();
                if (Logger.debug()) {
                    Logger.d("AppLog", "SC_CONNECT_TIMEOUT " + message2);
                }
                Matcher matcher = Pattern.compile("Connect to +([\\w\\.\\-]+)?/(\\[([a-zA-Z0-9:]+)\\]|(\\d{1,3}(\\.\\d{1,3}){3,3})):(\\d+) +timed out").matcher(message2);
                if (matcher.matches()) {
                    if (matcher.group(3) != null) {
                        strArr[0] = matcher.group(3);
                    } else if (matcher.group(4) != null) {
                        strArr[0] = matcher.group(4);
                    }
                }
                if (Logger.debug()) {
                    Logger.d("AppLog", "SC_CONNECT_TIMEOUT ip " + strArr[0]);
                    return i3;
                }
                return i3;
            } else if (i3 != 8) {
                if (i3 == 4) {
                    String message3 = th.getMessage();
                    if (message3.indexOf(" EIO (I/O error)") <= 0) {
                        if (!(th instanceof FileNotFoundException) && message3.indexOf(" ENOENT ") <= 0 && message3.indexOf("No such file or directory") <= 0) {
                            if (message3.indexOf(" ENOSPC ") <= 0 && message3.indexOf("No space left on device") <= 0) {
                                if (message3.indexOf(" EDQUOT ") > 0) {
                                    i2 = 34;
                                } else if (message3.indexOf(" EROFS ") > 0) {
                                    i2 = 35;
                                } else if (message3.indexOf(" EACCES ") <= 0) {
                                    return i3;
                                } else {
                                    i2 = 36;
                                }
                            }
                            i2 = 32;
                        }
                        return 33;
                    }
                    i2 = 37;
                    return i2;
                }
                return i3;
            } else {
                Throwable cause = th.getCause();
                if (cause == null || !(cause instanceof ConnectException)) {
                    return i3;
                }
                String message4 = cause.getMessage();
                if (Logger.debug()) {
                    Logger.d("AppLog", "SC_CONNECT_EXCEPTION " + message4);
                }
                Matcher matcher2 = Pattern.compile("failed to connect to +([\\w\\.\\-]+)?/(\\[([a-zA-Z0-9:]+)\\]|(\\d{1,3}(\\.\\d{1,3}){3,3})) \\(port \\d+\\)( +after \\d+ms)?: +\\w+ failed: (E[A-Z]+) .*").matcher(message4);
                String str = null;
                if (matcher2.matches()) {
                    if (matcher2.group(3) != null) {
                        strArr[0] = matcher2.group(3);
                    } else if (matcher2.group(4) != null) {
                        strArr[0] = matcher2.group(4);
                    }
                    str = matcher2.group(7);
                    if (str != null) {
                        if ("ECONNRESET".equals(str)) {
                            i3 = 12;
                        } else if ("ECONNREFUSED".equals(str)) {
                            i3 = 13;
                        } else if ("EHOSTUNREACH".equals(str)) {
                            i3 = 14;
                        } else if ("ENETUNREACH".equals(str)) {
                            i3 = 15;
                        } else if ("EADDRNOTAVAIL".equals(str)) {
                            i3 = 16;
                        } else if ("EADDRINUSE".equals(str)) {
                            i3 = 17;
                        }
                    }
                }
                if (Logger.debug()) {
                    Logger.d("AppLog", "SC_CONNECT_EXCEPTION ip " + strArr[0] + " " + str);
                    return i3;
                }
                return i3;
            }
        } catch (Exception unused) {
            return i3;
        }
    }
}
