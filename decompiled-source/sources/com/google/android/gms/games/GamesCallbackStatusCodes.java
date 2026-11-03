package com.google.android.gms.games;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class GamesCallbackStatusCodes {
    @Deprecated
    public static final int CLIENT_RECONNECT_REQUIRED = 2;
    public static final int INTERNAL_ERROR = 1;
    public static final int OK = 0;

    private GamesCallbackStatusCodes() {
    }

    public static String getStatusCodeString(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 6003) {
                        if (i2 != 7004) {
                            if (i2 != 7000) {
                                if (i2 != 7001) {
                                    StringBuilder sb = new StringBuilder(String.valueOf(i2).length() + 36);
                                    sb.append("unknown games callback status code: ");
                                    sb.append(i2);
                                    return sb.toString();
                                }
                                return "REAL_TIME_MESSAGE_SEND_FAILED";
                            }
                            return "REAL_TIME_CONNECTION_FAILED";
                        }
                        return "REAL_TIME_ROOM_NOT_JOINED";
                    }
                    return "MULTIPLAYER_DISABLED";
                }
                return "CLIENT_RECONNECT_REQUIRED";
            }
            return "INTERNAL_ERROR";
        }
        return "OK";
    }
}
