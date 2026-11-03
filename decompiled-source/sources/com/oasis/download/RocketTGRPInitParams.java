package com.oasis.download;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class RocketTGRPInitParams implements Serializable {
    public String tgrpName = "OasisTGRPDownloader";
    public String rootPath = null;
    public String subPath = "tgrp";
    public String url = null;
    public String[] backUpHosts = null;
    public String[] diffPatchUrls = null;
    public String[] incrementalPackUrls = null;
    public String resJson = null;
    public boolean isOnlyWifi = false;
    public int retryTime = 3;
    public long maxDownloadSpeed = 0;
    public int workerCount = 5;
}
