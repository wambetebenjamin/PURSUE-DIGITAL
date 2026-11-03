package com.bytedance.hades.tgrp.api;

import com.bytedance.hades.downloader.api.data.DownloadErrorCode;
/* loaded from: classes2.dex */
public class TGRPErrorCode extends DownloadErrorCode {
    public static final int FILE_COUNT_ERROR = -507004;
    public static final int GZIP_ERROR = -508100;
    public static final int INTERRUPT = 2;
    public static final int IS_NOT_TGRP_FILE = -507001;
    public static final int NO_ERROR = 0;
    public static final int PAUSE = 1;
    public static final int TGRP_HEADER_ERROR = -507003;
    public static final int TGRP_PATH_IS_EMPTY = -507000;
    public static final int TGRP_UN_KNOWN_FLAG_ERROR = -507009;
    public static final int VERSION_ERROR = -507002;
}
