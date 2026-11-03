package com.ss.android.socialbase.downloader.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.os.Parcel;
import android.os.Parcelable;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.thread.DownloadChunkRunnable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes9.dex */
public class DownloadChunk implements Parcelable {
    public static final Parcelable.Creator<DownloadChunk> CREATOR = new Parcelable.Creator<DownloadChunk>() { // from class: com.ss.android.socialbase.downloader.model.DownloadChunk.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadChunk createFromParcel(Parcel parcel) {
            return new DownloadChunk(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadChunk[] newArray(int i2) {
            return new DownloadChunk[i2];
        }
    };
    private static final String TAG = "DownloadChunk";
    private int bindValueCount;
    private int chunkIndex;
    private DownloadChunkRunnable chunkRunnable;
    private long contentLength;
    private AtomicLong currentOffset;
    private long endOffset;
    private DownloadChunk hostChunk;
    private AtomicInteger hostChunkIndex;
    private int id;
    private AtomicBoolean isDownloading;
    private long oldOffset;
    private boolean reuseingFirstConnection;
    private long startOffset;
    private List<DownloadChunk> subChunkList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private DownloadChunk(Builder builder) {
        if (builder == null) {
            return;
        }
        this.id = builder.id;
        this.startOffset = builder.startOffset;
        this.currentOffset = new AtomicLong(builder.currentOffset);
        this.endOffset = builder.endOffset;
        this.contentLength = builder.contentLength;
        this.chunkIndex = builder.chunkIndex;
        this.oldOffset = builder.oldOffset;
        this.hostChunkIndex = new AtomicInteger(-1);
        setHostChunk(builder.hostChunk);
        this.isDownloading = new AtomicBoolean(false);
    }

    public DownloadChunk(Cursor cursor) {
        if (cursor == null) {
            return;
        }
        this.id = cursor.getInt(cursor.getColumnIndex("_id"));
        this.chunkIndex = cursor.getInt(cursor.getColumnIndex(DBDefinition.CHUNK_INDEX));
        this.startOffset = cursor.getLong(cursor.getColumnIndex(DBDefinition.START_OFFSET));
        int columnIndex = cursor.getColumnIndex(DBDefinition.CUR_OFFSET);
        if (columnIndex != -1) {
            this.currentOffset = new AtomicLong(cursor.getLong(columnIndex));
        } else {
            this.currentOffset = new AtomicLong(0L);
        }
        this.endOffset = cursor.getLong(cursor.getColumnIndex(DBDefinition.END_OFFSET));
        int columnIndex2 = cursor.getColumnIndex(DBDefinition.HOST_CHUNK_INDEX);
        if (columnIndex2 != -1) {
            this.hostChunkIndex = new AtomicInteger(cursor.getInt(columnIndex2));
        } else {
            this.hostChunkIndex = new AtomicInteger(-1);
        }
        int columnIndex3 = cursor.getColumnIndex(DBDefinition.CHUNK_CONTENT_LEN);
        if (columnIndex3 != -1) {
            this.contentLength = cursor.getLong(columnIndex3);
        }
        this.isDownloading = new AtomicBoolean(false);
    }

    protected DownloadChunk(Parcel parcel) {
        this.id = parcel.readInt();
        this.startOffset = parcel.readLong();
        this.currentOffset = new AtomicLong(parcel.readLong());
        this.endOffset = parcel.readLong();
        this.contentLength = parcel.readLong();
        this.chunkIndex = parcel.readInt();
        this.hostChunkIndex = new AtomicInteger(parcel.readInt());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.id);
        parcel.writeLong(this.startOffset);
        AtomicLong atomicLong = this.currentOffset;
        parcel.writeLong(atomicLong != null ? atomicLong.get() : 0L);
        parcel.writeLong(this.endOffset);
        parcel.writeLong(this.contentLength);
        parcel.writeInt(this.chunkIndex);
        AtomicInteger atomicInteger = this.hostChunkIndex;
        parcel.writeInt(atomicInteger != null ? atomicInteger.get() : -1);
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Integer.valueOf(this.id));
        contentValues.put(DBDefinition.CHUNK_INDEX, Integer.valueOf(this.chunkIndex));
        contentValues.put(DBDefinition.START_OFFSET, Long.valueOf(this.startOffset));
        contentValues.put(DBDefinition.CUR_OFFSET, Long.valueOf(getCurrentOffset()));
        contentValues.put(DBDefinition.END_OFFSET, Long.valueOf(this.endOffset));
        contentValues.put(DBDefinition.CHUNK_CONTENT_LEN, Long.valueOf(this.contentLength));
        contentValues.put(DBDefinition.HOST_CHUNK_INDEX, Integer.valueOf(getHostChunkIndex()));
        return contentValues;
    }

    public void bindValue(SQLiteStatement sQLiteStatement) {
        if (sQLiteStatement == null) {
            return;
        }
        this.bindValueCount = 0;
        sQLiteStatement.clearBindings();
        int i2 = this.bindValueCount + 1;
        this.bindValueCount = i2;
        sQLiteStatement.bindLong(i2, this.id);
        int i3 = this.bindValueCount + 1;
        this.bindValueCount = i3;
        sQLiteStatement.bindLong(i3, this.chunkIndex);
        int i4 = this.bindValueCount + 1;
        this.bindValueCount = i4;
        sQLiteStatement.bindLong(i4, this.startOffset);
        int i5 = this.bindValueCount + 1;
        this.bindValueCount = i5;
        sQLiteStatement.bindLong(i5, getCurrentOffset());
        int i6 = this.bindValueCount + 1;
        this.bindValueCount = i6;
        sQLiteStatement.bindLong(i6, this.endOffset);
        int i7 = this.bindValueCount + 1;
        this.bindValueCount = i7;
        sQLiteStatement.bindLong(i7, this.contentLength);
        int i8 = this.bindValueCount + 1;
        this.bindValueCount = i8;
        sQLiteStatement.bindLong(i8, getHostChunkIndex());
    }

    public int getHostChunkIndex() {
        AtomicInteger atomicInteger = this.hostChunkIndex;
        if (atomicInteger == null) {
            return -1;
        }
        return atomicInteger.get();
    }

    public void setHostChunkIndex(int i2) {
        AtomicInteger atomicInteger = this.hostChunkIndex;
        if (atomicInteger == null) {
            this.hostChunkIndex = new AtomicInteger(i2);
        } else {
            atomicInteger.set(i2);
        }
    }

    public boolean isDownloading() {
        AtomicBoolean atomicBoolean = this.isDownloading;
        if (atomicBoolean == null) {
            return false;
        }
        return atomicBoolean.get();
    }

    public void setChunkRunnable(DownloadChunkRunnable downloadChunkRunnable) {
        this.chunkRunnable = downloadChunkRunnable;
        setOldOffset();
    }

    public void setDownloading(boolean z) {
        AtomicBoolean atomicBoolean = this.isDownloading;
        if (atomicBoolean == null) {
            this.isDownloading = new AtomicBoolean(z);
        } else {
            atomicBoolean.set(z);
        }
        this.chunkRunnable = null;
    }

    public void setHostChunk(DownloadChunk downloadChunk) {
        this.hostChunk = downloadChunk;
        if (downloadChunk != null) {
            setHostChunkIndex(downloadChunk.getChunkIndex());
        }
    }

    public DownloadChunk getHostChunk() {
        return this.hostChunk;
    }

    public boolean isHostChunk() {
        return getHostChunkIndex() == -1;
    }

    public DownloadChunk getFirstReuseChunk() {
        DownloadChunk downloadChunk = !isHostChunk() ? this.hostChunk : this;
        if (downloadChunk == null || !downloadChunk.hasChunkDivided()) {
            return null;
        }
        return downloadChunk.getSubChunkList().get(0);
    }

    public boolean hasChunkDivided() {
        List<DownloadChunk> list = this.subChunkList;
        return list != null && list.size() > 0;
    }

    public void setSubChunkList(List<DownloadChunk> list) {
        this.subChunkList = list;
    }

    public long getOldOffset() {
        return this.oldOffset;
    }

    public void setOldOffset(long j2) {
        this.oldOffset = j2;
    }

    public List<DownloadChunk> getSubChunkList() {
        return this.subChunkList;
    }

    public boolean canRefreshCurOffsetForReuseChunk() {
        DownloadChunk downloadChunk = this.hostChunk;
        if (downloadChunk == null) {
            return true;
        }
        if (downloadChunk.hasChunkDivided()) {
            for (int i2 = 0; i2 < this.hostChunk.getSubChunkList().size(); i2++) {
                DownloadChunk downloadChunk2 = this.hostChunk.getSubChunkList().get(i2);
                if (downloadChunk2 != null) {
                    int indexOf = this.hostChunk.getSubChunkList().indexOf(this);
                    if (indexOf > i2 && !downloadChunk2.hasNoBytesDownload()) {
                        return false;
                    }
                    if (indexOf == i2) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public void setReuseingFirstConnection(boolean z) {
        this.reuseingFirstConnection = z;
    }

    public boolean hasNoBytesDownload() {
        long j2 = this.startOffset;
        if (isHostChunk()) {
            long j3 = this.oldOffset;
            if (j3 > this.startOffset) {
                j2 = j3;
            }
        }
        return getCurrentOffset() - j2 >= this.contentLength;
    }

    public long getNextChunkCurOffset() {
        DownloadChunk downloadChunk = this.hostChunk;
        if (downloadChunk != null && downloadChunk.getSubChunkList() != null) {
            int indexOf = this.hostChunk.getSubChunkList().indexOf(this);
            boolean z = false;
            for (int i2 = 0; i2 < this.hostChunk.getSubChunkList().size(); i2++) {
                DownloadChunk downloadChunk2 = this.hostChunk.getSubChunkList().get(i2);
                if (downloadChunk2 != null) {
                    if (z) {
                        return downloadChunk2.getCurrentOffset();
                    }
                    if (indexOf == i2) {
                        z = true;
                    }
                }
            }
        }
        return -1L;
    }

    public boolean isReuseingFirstConnection() {
        return this.chunkIndex == 0 && this.reuseingFirstConnection;
    }

    public int getBindValueCount() {
        return this.bindValueCount;
    }

    public void setId(int i2) {
        this.id = i2;
    }

    public void setChunkIndex(int i2) {
        this.chunkIndex = i2;
    }

    public void setContentLength(long j2) {
        this.contentLength = j2;
    }

    public int getId() {
        return this.id;
    }

    public long getStartOffset() {
        return this.startOffset;
    }

    public long getCurOffset() {
        AtomicLong atomicLong = this.currentOffset;
        if (atomicLong != null) {
            return atomicLong.get();
        }
        return 0L;
    }

    public long getCurrentOffset() {
        if (isHostChunk() && hasChunkDivided()) {
            long j2 = 0;
            for (int i2 = 0; i2 < this.subChunkList.size(); i2++) {
                DownloadChunk downloadChunk = this.subChunkList.get(i2);
                if (downloadChunk != null) {
                    if (!downloadChunk.hasNoBytesDownload()) {
                        return downloadChunk.getCurOffset();
                    }
                    if (j2 < downloadChunk.getCurOffset()) {
                        j2 = downloadChunk.getCurOffset();
                    }
                }
            }
            return j2;
        }
        return getCurOffset();
    }

    public void setCurrentOffset(long j2) {
        AtomicLong atomicLong = this.currentOffset;
        if (atomicLong != null) {
            atomicLong.set(j2);
        } else {
            this.currentOffset = new AtomicLong(j2);
        }
    }

    public long getDownloadChunkBytes() {
        long currentOffset = getCurrentOffset() - this.startOffset;
        if (hasChunkDivided()) {
            currentOffset = 0;
            for (int i2 = 0; i2 < this.subChunkList.size(); i2++) {
                DownloadChunk downloadChunk = this.subChunkList.get(i2);
                if (downloadChunk != null) {
                    currentOffset += downloadChunk.getCurrentOffset() - downloadChunk.getStartOffset();
                }
            }
        }
        return currentOffset;
    }

    public long getEndOffset() {
        return this.endOffset;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public void setOldOffset() {
        this.oldOffset = getCurrentOffset();
    }

    public long getRetainLength(boolean z) {
        long currentOffset = getCurrentOffset();
        long j2 = this.contentLength;
        long j3 = this.oldOffset;
        long j4 = j2 - (currentOffset - j3);
        if (!z && currentOffset == j3) {
            j4 = j2 - (currentOffset - this.startOffset);
        }
        Logger.d(TAG, "contentLength:" + this.contentLength + " curOffset:" + getCurrentOffset() + " oldOffset:" + this.oldOffset + " retainLen:" + j4);
        if (j4 < 0) {
            return 0L;
        }
        return j4;
    }

    public List<DownloadChunk> divideChunkForReuse(int i2, long j2) {
        DownloadChunk downloadChunk;
        long endOffset;
        long j3;
        long j4;
        long j5;
        long j6;
        DownloadChunk downloadChunk2 = this;
        int i3 = i2;
        if (!isHostChunk() || hasChunkDivided()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        long curOffset = getCurOffset();
        long retainLength = downloadChunk2.getRetainLength(true);
        long j7 = retainLength / i3;
        Logger.d(TAG, "retainLen:" + retainLength + " divideChunkForReuse chunkSize:" + j7 + " current host downloadChunk index:" + downloadChunk2.chunkIndex);
        int i4 = 0;
        while (i4 < i3) {
            if (i4 == 0) {
                j4 = getStartOffset();
                j3 = (curOffset + j7) - 1;
            } else {
                int i5 = i3 - 1;
                if (i4 == i5) {
                    long endOffset2 = getEndOffset();
                    j5 = endOffset2 > curOffset ? (endOffset2 - curOffset) + 1 : retainLength - (i5 * j7);
                    j6 = endOffset2;
                    j4 = curOffset;
                    long j8 = retainLength;
                    long j9 = j6;
                    DownloadChunk build = new Builder(downloadChunk2.id).chunkIndex((-i4) - 1).startOffset(j4).currentOffset(curOffset).oldOffset(curOffset).endOffset(j9).contentLength(j5).hostChunk(downloadChunk2).build();
                    Logger.d(TAG, "divide sub chunk : " + i4 + " startOffset:" + j4 + " curOffset:" + curOffset + " endOffset:" + j9 + " contentLen:" + j5);
                    arrayList.add(build);
                    curOffset += j7;
                    i4++;
                    downloadChunk2 = this;
                    i3 = i2;
                    retainLength = j8;
                } else {
                    j3 = (curOffset + j7) - 1;
                    j4 = curOffset;
                }
            }
            j5 = j7;
            j6 = j3;
            long j82 = retainLength;
            long j92 = j6;
            DownloadChunk build2 = new Builder(downloadChunk2.id).chunkIndex((-i4) - 1).startOffset(j4).currentOffset(curOffset).oldOffset(curOffset).endOffset(j92).contentLength(j5).hostChunk(downloadChunk2).build();
            Logger.d(TAG, "divide sub chunk : " + i4 + " startOffset:" + j4 + " curOffset:" + curOffset + " endOffset:" + j92 + " contentLen:" + j5);
            arrayList.add(build2);
            curOffset += j7;
            i4++;
            downloadChunk2 = this;
            i3 = i2;
            retainLength = j82;
        }
        long j10 = 0;
        for (int size = arrayList.size() - 1; size > 0; size--) {
            DownloadChunk downloadChunk3 = arrayList.get(size);
            if (downloadChunk3 != null) {
                j10 += downloadChunk3.getContentLength();
            }
        }
        Logger.d(TAG, "reuseChunkContentLen:" + j10);
        DownloadChunk downloadChunk4 = arrayList.get(0);
        if (downloadChunk4 != null) {
            if (getEndOffset() == 0) {
                endOffset = j2 - getStartOffset();
            } else {
                endOffset = (getEndOffset() - getStartOffset()) + 1;
            }
            downloadChunk4.setContentLength(endOffset - j10);
            downloadChunk = this;
            downloadChunk4.setChunkIndex(downloadChunk.chunkIndex);
            DownloadChunkRunnable downloadChunkRunnable = downloadChunk.chunkRunnable;
            if (downloadChunkRunnable != null) {
                downloadChunkRunnable.refreshResponseHandleOffset(downloadChunk4.getEndOffset(), getContentLength() - j10);
            }
        } else {
            downloadChunk = this;
        }
        downloadChunk.setSubChunkList(arrayList);
        return arrayList;
    }

    public int getChunkIndex() {
        return this.chunkIndex;
    }

    /* loaded from: classes9.dex */
    public static class Builder {
        private int chunkIndex;
        private long contentLength;
        private long currentOffset;
        private long endOffset;
        private DownloadChunk hostChunk;
        private int id;
        private long oldOffset;
        private long startOffset;

        public Builder(int i2) {
            this.id = i2;
        }

        public Builder id(int i2) {
            this.id = i2;
            return this;
        }

        public Builder startOffset(long j2) {
            this.startOffset = j2;
            return this;
        }

        public Builder currentOffset(long j2) {
            this.currentOffset = j2;
            return this;
        }

        public Builder endOffset(long j2) {
            this.endOffset = j2;
            return this;
        }

        public Builder contentLength(long j2) {
            this.contentLength = j2;
            return this;
        }

        public Builder chunkIndex(int i2) {
            this.chunkIndex = i2;
            return this;
        }

        public Builder hostChunk(DownloadChunk downloadChunk) {
            this.hostChunk = downloadChunk;
            return this;
        }

        public Builder oldOffset(long j2) {
            this.oldOffset = j2;
            return this;
        }

        public DownloadChunk build() {
            return new DownloadChunk(this);
        }
    }
}
