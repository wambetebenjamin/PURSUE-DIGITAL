package com.ss.android.socialbase.downloader.segment;

import android.util.Log;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public final class Segment {
    private static final String TAG = "Segment";
    int competitor;
    private final AtomicLong currentOffset;
    private volatile long currentOffsetRead;
    private long endOffset;
    private int index;
    private JSONObject jsonObject;
    volatile SegmentReader owner;
    private final long startOffset;

    /* loaded from: classes9.dex */
    interface JsonKey {
        public static final String CURRENT = "cu";
        public static final String END = "en";
        public static final String START = "st";
    }

    public Segment(long j2) {
        this(j2, -1L);
    }

    public Segment(long j2, long j3) {
        AtomicLong atomicLong = new AtomicLong();
        this.currentOffset = atomicLong;
        this.competitor = 0;
        this.startOffset = j2;
        atomicLong.set(j2);
        this.currentOffsetRead = j2;
        if (j3 >= j2) {
            this.endOffset = j3;
        } else {
            this.endOffset = -1L;
        }
    }

    public Segment(Segment segment) {
        AtomicLong atomicLong = new AtomicLong();
        this.currentOffset = atomicLong;
        this.competitor = 0;
        this.startOffset = segment.startOffset;
        this.endOffset = segment.endOffset;
        atomicLong.set(segment.currentOffset.get());
        this.currentOffsetRead = atomicLong.get();
        this.index = segment.index;
    }

    public Segment(JSONObject jSONObject) {
        this.currentOffset = new AtomicLong();
        this.competitor = 0;
        this.startOffset = jSONObject.optLong("st");
        setEndOffset(jSONObject.optLong("en"));
        setCurrentOffset(jSONObject.optLong(JsonKey.CURRENT));
        setCurrentOffsetRead(getCurrentOffset());
    }

    public boolean isDownloaded() {
        return this.endOffset >= this.startOffset && this.currentOffset.get() > this.endOffset;
    }

    public long getDownloadBytes() {
        return this.currentOffset.get() - this.startOffset;
    }

    public long getRemainDownloadBytes() {
        long j2 = this.endOffset;
        if (j2 >= this.startOffset) {
            return (j2 - this.currentOffset.get()) + 1;
        }
        return -1L;
    }

    public boolean isReadFinish() {
        return this.endOffset >= this.startOffset && getCurrentOffsetRead() > this.endOffset;
    }

    public long getReadBytes() {
        return getCurrentOffsetRead() - this.startOffset;
    }

    public long getRemainReadBytes() {
        long j2 = this.endOffset;
        if (j2 >= this.startOffset) {
            return (j2 - getCurrentOffsetRead()) + 1;
        }
        return -1L;
    }

    public long getStartOffset() {
        return this.startOffset;
    }

    public long getCurrentOffset() {
        long j2 = this.currentOffset.get();
        long j3 = this.endOffset;
        if (j3 > 0) {
            long j4 = j3 + 1;
            if (j2 > j4) {
                return j4;
            }
        }
        return j2;
    }

    public long getCurrentOffsetRead() {
        SegmentReader segmentReader = this.owner;
        if (segmentReader != null) {
            long curSegmentReadOffset = segmentReader.getCurSegmentReadOffset();
            if (curSegmentReadOffset > this.currentOffsetRead) {
                return curSegmentReadOffset;
            }
        }
        return this.currentOffsetRead;
    }

    public void setCurrentOffset(long j2) {
        long j3 = this.startOffset;
        if (j2 < j3) {
            j2 = j3;
        }
        long j4 = this.endOffset;
        if (j4 > 0) {
            long j5 = j4 + 1;
            if (j2 > j5) {
                j2 = j5;
            }
        }
        this.currentOffset.set(j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void increaseCurrentOffset(long j2) {
        this.currentOffset.addAndGet(j2);
    }

    public long getEndOffset() {
        return this.endOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEndOffset(long j2) {
        if (j2 >= this.startOffset) {
            this.endOffset = j2;
            return;
        }
        Log.w(TAG, "setEndOffset: endOffset = " + j2 + ", segment = " + this);
        if (j2 == -1) {
            this.endOffset = j2;
        }
    }

    public void setCurrentOffsetRead(long j2) {
        if (j2 >= this.currentOffset.get()) {
            this.currentOffsetRead = j2;
        }
    }

    public int getIndex() {
        return this.index;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIndex(int i2) {
        this.index = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void increaseCompetitor() {
        this.competitor++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void decreaseCompetitor() {
        this.competitor--;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCompetitor() {
        return this.competitor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCompetitor(int i2) {
        this.competitor = i2;
    }

    public String toString() {
        return "Segment{startOffset=" + this.startOffset + ",\t currentOffset=" + this.currentOffset + ",\t currentOffsetRead=" + getCurrentOffsetRead() + ",\t endOffset=" + this.endOffset + '}';
    }

    public static String toString(List<Segment> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        Collections.sort(list, new Comparator<Segment>() { // from class: com.ss.android.socialbase.downloader.segment.Segment.1
            @Override // java.util.Comparator
            public int compare(Segment segment, Segment segment2) {
                return (int) (segment.getStartOffset() - segment2.getStartOffset());
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Segment segment : list) {
            sb.append(segment).append("\r\n");
        }
        return sb.toString();
    }

    public JSONObject toJson() {
        JSONObject jSONObject = this.jsonObject;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
            this.jsonObject = jSONObject;
        }
        jSONObject.put("st", getStartOffset());
        jSONObject.put(JsonKey.CURRENT, getCurrentOffset());
        jSONObject.put("en", getEndOffset());
        return jSONObject;
    }
}
