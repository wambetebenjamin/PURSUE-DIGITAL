package com.google.android.gms.games;

import com.bytedance.bdturing.c;
import com.bytedance.common.wschannel.WsConstants;
import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class GamesStatusUtils {
    private GamesStatusUtils() {
    }

    public static void zza(TaskCompletionSource taskCompletionSource, SecurityException securityException) {
        if (taskCompletionSource != null) {
            taskCompletionSource.trySetException(new ApiException(GamesClientStatusCodes.zza(4)));
        }
    }

    public static void zzb(TaskCompletionSource taskCompletionSource, int i2) {
        int i3;
        Status zza = zzj.zza(i2);
        int i4 = GamesClientStatusCodes.NETWORK_ERROR_NO_DATA;
        int statusCode = zza.getStatusCode();
        if (statusCode == 1) {
            i3 = 8;
        } else if (statusCode == 2) {
            i3 = 26502;
        } else if (statusCode == 3) {
            i3 = 26503;
        } else if (statusCode == 4) {
            i3 = GamesClientStatusCodes.NETWORK_ERROR_NO_DATA;
        } else if (statusCode == 5) {
            i3 = 26505;
        } else if (statusCode != 6) {
            if (statusCode != 7) {
                if (statusCode == 1500) {
                    i3 = 26540;
                } else if (statusCode != 1501) {
                    switch (statusCode) {
                        case 7:
                            break;
                        case 8:
                            i3 = GamesClientStatusCodes.APP_MISCONFIGURED;
                            break;
                        case 9:
                            i3 = GamesClientStatusCodes.GAME_NOT_FOUND;
                            break;
                        case 500:
                            i3 = 26520;
                            break;
                        case WsConstants.APP_STATE_CHANGE_SERVICE /* 9000 */:
                            i3 = GamesClientStatusCodes.VIDEO_NOT_ACTIVE;
                            break;
                        case 9001:
                            i3 = GamesClientStatusCodes.VIDEO_UNSUPPORTED;
                            break;
                        case 9002:
                            i3 = GamesClientStatusCodes.VIDEO_PERMISSION_ERROR;
                            break;
                        case 9003:
                            i3 = GamesClientStatusCodes.VIDEO_STORAGE_ERROR;
                            break;
                        case 9004:
                            i3 = GamesClientStatusCodes.VIDEO_UNEXPECTED_CAPTURE_ERROR;
                            break;
                        case 9006:
                            i3 = GamesClientStatusCodes.VIDEO_ALREADY_CAPTURING;
                            break;
                        case 9009:
                            i3 = GamesClientStatusCodes.VIDEO_OUT_OF_DISK_SPACE;
                            break;
                        case 9010:
                            i3 = 26627;
                            break;
                        case 9011:
                            i3 = 26628;
                            break;
                        case 9012:
                            i3 = 26629;
                            break;
                        case 9016:
                            i3 = 26630;
                            break;
                        case 9017:
                            i3 = 26631;
                            break;
                        case 9018:
                            i3 = 26632;
                            break;
                        case 9200:
                            i3 = 26650;
                            break;
                        case 9202:
                            i3 = 26652;
                            break;
                        case 10000:
                            i3 = 26700;
                            break;
                        case 10001:
                            i3 = 26701;
                            break;
                        case 10002:
                            i3 = 26702;
                            break;
                        case 10003:
                            i3 = GamesClientStatusCodes.CONSENT_REQUIRED;
                            break;
                        case 10004:
                            i3 = 26704;
                            break;
                        default:
                            switch (statusCode) {
                                case 1000:
                                    i3 = 26530;
                                    break;
                                case 1001:
                                    i3 = 26531;
                                    break;
                                case 1002:
                                    i3 = 26532;
                                    break;
                                case 1003:
                                    i3 = 26533;
                                    break;
                                case 1004:
                                    i3 = 26534;
                                    break;
                                case 1005:
                                    i3 = 26535;
                                    break;
                                case 1006:
                                    i3 = 26536;
                                    break;
                                default:
                                    switch (statusCode) {
                                        case 2000:
                                            i3 = 26550;
                                            break;
                                        case 2001:
                                            i3 = 26551;
                                            break;
                                        case 2002:
                                            i3 = 26552;
                                            break;
                                        default:
                                            switch (statusCode) {
                                                case 3000:
                                                    i3 = GamesClientStatusCodes.ACHIEVEMENT_UNLOCK_FAILURE;
                                                    break;
                                                case 3001:
                                                    i3 = GamesClientStatusCodes.ACHIEVEMENT_UNKNOWN;
                                                    break;
                                                case AuthApiStatusCodes.AUTH_API_CLIENT_ERROR /* 3002 */:
                                                    i3 = GamesClientStatusCodes.ACHIEVEMENT_NOT_INCREMENTAL;
                                                    break;
                                                case AuthApiStatusCodes.AUTH_API_SERVER_ERROR /* 3003 */:
                                                    i3 = GamesClientStatusCodes.ACHIEVEMENT_UNLOCKED;
                                                    break;
                                                default:
                                                    switch (statusCode) {
                                                        case 4000:
                                                            i3 = GamesClientStatusCodes.SNAPSHOT_NOT_FOUND;
                                                            break;
                                                        case 4001:
                                                            i3 = GamesClientStatusCodes.SNAPSHOT_CREATION_FAILED;
                                                            break;
                                                        case 4002:
                                                            i3 = GamesClientStatusCodes.SNAPSHOT_CONTENTS_UNAVAILABLE;
                                                            break;
                                                        case 4003:
                                                            i3 = GamesClientStatusCodes.SNAPSHOT_COMMIT_FAILED;
                                                            break;
                                                        case 4004:
                                                            i3 = 26574;
                                                            break;
                                                        case 4005:
                                                            i3 = GamesClientStatusCodes.SNAPSHOT_FOLDER_UNAVAILABLE;
                                                            break;
                                                        case 4006:
                                                            i3 = GamesClientStatusCodes.SNAPSHOT_CONFLICT_MISSING;
                                                            break;
                                                        default:
                                                            switch (statusCode) {
                                                                case c.DEFAULT_EVENT_COUNT /* 6000 */:
                                                                    i3 = 26580;
                                                                    break;
                                                                case 6001:
                                                                    i3 = 26581;
                                                                    break;
                                                                case 6002:
                                                                    i3 = 26582;
                                                                    break;
                                                                case 6003:
                                                                    i3 = 26583;
                                                                    break;
                                                                case 6004:
                                                                    i3 = 26584;
                                                                    break;
                                                                default:
                                                                    switch (statusCode) {
                                                                        case 6500:
                                                                            i3 = 26590;
                                                                            break;
                                                                        case 6501:
                                                                            i3 = 26591;
                                                                            break;
                                                                        case 6502:
                                                                            i3 = 26592;
                                                                            break;
                                                                        case 6503:
                                                                            i3 = 26593;
                                                                            break;
                                                                        case 6504:
                                                                            i3 = 26594;
                                                                            break;
                                                                        case 6505:
                                                                            i3 = 26595;
                                                                            break;
                                                                        case 6506:
                                                                            i3 = 26596;
                                                                            break;
                                                                        case 6507:
                                                                            i3 = 26597;
                                                                            break;
                                                                        default:
                                                                            switch (statusCode) {
                                                                                case 7000:
                                                                                    i3 = 26600;
                                                                                    break;
                                                                                case 7001:
                                                                                    i3 = 26601;
                                                                                    break;
                                                                                case 7002:
                                                                                    i3 = 26602;
                                                                                    break;
                                                                                case 7003:
                                                                                    i3 = 26603;
                                                                                    break;
                                                                                case 7004:
                                                                                    i3 = 26604;
                                                                                    break;
                                                                                case 7005:
                                                                                    i3 = 26605;
                                                                                    break;
                                                                                case 7006:
                                                                                    i3 = 26606;
                                                                                    break;
                                                                                case 7007:
                                                                                    i3 = GamesClientStatusCodes.OPERATION_IN_FLIGHT;
                                                                                    break;
                                                                                default:
                                                                                    switch (statusCode) {
                                                                                        case 8000:
                                                                                            i3 = 26610;
                                                                                            break;
                                                                                        case 8001:
                                                                                            i3 = 26611;
                                                                                            break;
                                                                                        case 8002:
                                                                                            i3 = 26612;
                                                                                            break;
                                                                                        case 8003:
                                                                                            i3 = 26613;
                                                                                            break;
                                                                                        default:
                                                                                            i3 = statusCode;
                                                                                            break;
                                                                                    }
                                                                            }
                                                                    }
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
                } else {
                    i3 = 26541;
                }
            }
            i3 = GamesClientStatusCodes.LICENSE_CHECK_FAILED;
        } else {
            i3 = GamesClientStatusCodes.NETWORK_ERROR_OPERATION_FAILED;
        }
        if (i3 != zza.getStatusCode()) {
            if (zzj.zzb(zza.getStatusCode()).equals(zza.getStatusMessage())) {
                zza = GamesClientStatusCodes.zzb(i3, zza.getResolution());
            } else {
                switch (statusCode) {
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 10:
                        break;
                    case 9:
                    default:
                        zza = new Status(i3, zza.getStatusMessage(), zza.getResolution());
                        break;
                }
            }
        }
        taskCompletionSource.setException(ApiExceptionUtil.fromStatus(zza));
    }
}
