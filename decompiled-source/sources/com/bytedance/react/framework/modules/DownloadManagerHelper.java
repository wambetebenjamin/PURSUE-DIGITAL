package com.bytedance.react.framework.modules;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class DownloadManagerHelper {
    private DownloadManager downloadManager;
    private List<Long> idList = new ArrayList();

    public DownloadManagerHelper(DownloadManager downloadManager) {
        this.downloadManager = downloadManager;
    }

    public void startDownload(String str) {
        if (this.downloadManager != null) {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
            if (Build.VERSION.SDK_INT >= 24) {
                request.setRequiresCharging(false);
                request.setRequiresDeviceIdle(false);
            }
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, System.currentTimeMillis() + ".img");
            request.setTitle("Download Picture");
            request.setNotificationVisibility(3);
            this.idList.add(Long.valueOf(this.downloadManager.enqueue(request)));
        }
    }

    public void removeId(long j2) {
        this.idList.remove(Long.valueOf(j2));
    }

    public void destroy() {
        if (this.idList.size() > 0) {
            long[] jArr = new long[this.idList.size()];
            for (int i2 = 0; i2 < this.idList.size(); i2++) {
                jArr[i2] = this.idList.get(i2).longValue();
            }
            this.downloadManager.remove(jArr);
        }
        this.downloadManager = null;
    }
}
