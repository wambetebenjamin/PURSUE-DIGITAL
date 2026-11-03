package com.ss.android.socialbase.downloader.segment;

import android.text.TextUtils;
import com.bytedance.librarian.c;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class UrlRecord {
    private final AtomicLong downloadBytes;
    private int failedTimes;
    private int hashCode;
    final String ip;
    final String ipFamily;
    private boolean isCurrentFailed;
    final boolean isMainUrl;
    private String key;
    private final List<SegmentReader> readers;
    final String url;

    public UrlRecord(String str, boolean z) {
        this.readers = new ArrayList();
        this.downloadBytes = new AtomicLong();
        this.url = str;
        this.isMainUrl = z;
        this.ip = null;
        this.ipFamily = null;
    }

    public UrlRecord(String str, String str2) {
        this.readers = new ArrayList();
        this.downloadBytes = new AtomicLong();
        this.url = str;
        this.isMainUrl = false;
        this.ip = str2;
        this.ipFamily = getIpFamily(str2);
    }

    private String getIpFamily(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            int lastIndexOf = str.lastIndexOf(c.a.DOT);
            if (lastIndexOf <= 0 || lastIndexOf >= str.length()) {
                return null;
            }
            return str.substring(0, lastIndexOf);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public synchronized void recordUse(SegmentReader segmentReader) {
        this.readers.add(segmentReader);
    }

    public synchronized void recordUnUse(SegmentReader segmentReader) {
        try {
            this.readers.remove(segmentReader);
        } catch (Throwable unused) {
        }
    }

    public synchronized int getCurrentUsers() {
        return this.readers.size();
    }

    public synchronized void recordFailed() {
        this.failedTimes++;
        this.isCurrentFailed = true;
    }

    public synchronized void recordSucceed() {
        this.isCurrentFailed = false;
    }

    public synchronized boolean isCurrentFailed() {
        return this.isCurrentFailed;
    }

    public void increaseDownloadBytes(long j2) {
        this.downloadBytes.addAndGet(j2);
    }

    public long getDownloadBytes() {
        long j2 = this.downloadBytes.get();
        for (SegmentReader segmentReader : this.readers) {
            j2 += segmentReader.getReadingBytes();
        }
        return j2;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = getKey().hashCode();
        }
        return this.hashCode;
    }

    private String getKey() {
        if (this.key == null) {
            StringBuilder append = new StringBuilder().append(this.url).append("_");
            String str = this.ip;
            if (str == null) {
                str = "";
            }
            this.key = append.append(str).append("_").append(this.isMainUrl).toString();
        }
        return this.key;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UrlRecord) {
            return getKey().equals(((UrlRecord) obj).getKey());
        }
        return false;
    }

    public String toString() {
        return "UrlRecord{url='" + this.url + "', ip='" + this.ip + "', ipFamily='" + this.ipFamily + "', isMainUrl=" + this.isMainUrl + ", failedTimes=" + this.failedTimes + ", isCurrentFailed=" + this.isCurrentFailed + '}';
    }
}
