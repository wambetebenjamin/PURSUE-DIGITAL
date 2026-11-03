package com.oasis.download;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class RocketTGRPBatchDownloadParams implements Serializable {
    int type = 0;
    boolean isSequential = false;
    String[] paths = null;
    String directory = null;
    String label = null;
    int priority = 0;
    int policy = 0;
    boolean startDownloadImmediately = true;
    boolean needReturnResPathListOnStartCallback = false;
    boolean needReturnResPathListOnProgressCallback = false;
    boolean needReturnResPathListOnResumeCallback = false;
    boolean needReturnSuccessResPathListOnPauseCallback = false;
    boolean needReturnUnfinishedResPathListOnPauseCallback = false;
    boolean needReturnSuccessResPathListOnStopCallback = false;
    boolean needReturnUnfinishedResPathListOnStopCallback = false;
    boolean needReturnSuccessResPathListOnCompleteCallback = false;
    boolean needReturnUnfinishedResPathListOnCompleteCallback = false;
}
