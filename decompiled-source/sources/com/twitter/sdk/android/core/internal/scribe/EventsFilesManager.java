package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import com.twitter.sdk.android.core.internal.CommonUtils;
import com.twitter.sdk.android.core.internal.CurrentTimeProvider;
import com.twitter.sdk.android.core.internal.scribe.EventsFilesManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes10.dex */
public abstract class EventsFilesManager<T> {
    public static final int MAX_BYTE_SIZE_PER_FILE = 8000;
    public static final int MAX_FILES_IN_BATCH = 1;
    public static final int MAX_FILES_TO_KEEP = 100;
    public static final String ROLL_OVER_FILE_NAME_SEPARATOR = "_";
    protected final Context context;
    protected final CurrentTimeProvider currentTimeProvider;
    private final int defaultMaxFilesToKeep;
    protected final EventsStorage eventStorage;
    protected volatile long lastRollOverTime;
    protected final List<EventsStorageListener> rollOverListeners = new CopyOnWriteArrayList();
    protected final EventTransform<T> transform;

    protected abstract String generateUniqueRollOverFileName();

    protected int getMaxByteSizePerFile() {
        return 8000;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventsFilesManager(Context context, EventTransform<T> eventTransform, CurrentTimeProvider currentTimeProvider, EventsStorage eventsStorage, int i2) throws IOException {
        this.context = context.getApplicationContext();
        this.transform = eventTransform;
        this.eventStorage = eventsStorage;
        this.currentTimeProvider = currentTimeProvider;
        this.lastRollOverTime = currentTimeProvider.getCurrentTimeMillis();
        this.defaultMaxFilesToKeep = i2;
    }

    public void writeEvent(T t) throws IOException {
        byte[] bytes = this.transform.toBytes(t);
        rollFileOverIfNeeded(bytes.length);
        this.eventStorage.add(bytes);
    }

    public void registerRollOverListener(EventsStorageListener eventsStorageListener) {
        if (eventsStorageListener != null) {
            this.rollOverListeners.add(eventsStorageListener);
        }
    }

    public boolean rollFileOver() throws IOException {
        String str;
        boolean z = true;
        if (this.eventStorage.isWorkingFileEmpty()) {
            str = null;
            z = false;
        } else {
            str = generateUniqueRollOverFileName();
            this.eventStorage.rollOver(str);
            CommonUtils.logControlled(this.context, 4, "Twitter", String.format(Locale.US, "generated new file %s", str));
            this.lastRollOverTime = this.currentTimeProvider.getCurrentTimeMillis();
        }
        triggerRollOverOnListeners(str);
        return z;
    }

    private void rollFileOverIfNeeded(int i2) throws IOException {
        if (this.eventStorage.canWorkingFileStore(i2, getMaxByteSizePerFile())) {
            return;
        }
        CommonUtils.logControlled(this.context, 4, "Twitter", String.format(Locale.US, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", Integer.valueOf(this.eventStorage.getWorkingFileUsedSizeInBytes()), Integer.valueOf(i2), Integer.valueOf(getMaxByteSizePerFile())));
        rollFileOver();
    }

    protected int getMaxFilesToKeep() {
        return this.defaultMaxFilesToKeep;
    }

    public long getLastRollOverTime() {
        return this.lastRollOverTime;
    }

    private void triggerRollOverOnListeners(String str) {
        for (EventsStorageListener eventsStorageListener : this.rollOverListeners) {
            try {
                eventsStorageListener.onRollOver(str);
            } catch (Exception e2) {
                CommonUtils.logControlledError(this.context, "One of the roll over listeners threw an exception", e2);
            }
        }
    }

    public List<File> getBatchOfFilesToSend() {
        return this.eventStorage.getBatchOfFilesToSend(1);
    }

    public void deleteSentFiles(List<File> list) {
        this.eventStorage.deleteFilesInRollOverDirectory(list);
    }

    public void deleteAllEventsFiles() {
        EventsStorage eventsStorage = this.eventStorage;
        eventsStorage.deleteFilesInRollOverDirectory(eventsStorage.getAllFilesInRollOverDirectory());
        this.eventStorage.deleteWorkingFile();
    }

    public void deleteOldestInRollOverIfOverMax() {
        List<File> allFilesInRollOverDirectory = this.eventStorage.getAllFilesInRollOverDirectory();
        int maxFilesToKeep = getMaxFilesToKeep();
        if (allFilesInRollOverDirectory.size() <= maxFilesToKeep) {
            return;
        }
        int size = allFilesInRollOverDirectory.size() - maxFilesToKeep;
        CommonUtils.logControlled(this.context, String.format(Locale.US, "Found %d files in  roll over directory, this is greater than %d, deleting %d oldest files", Integer.valueOf(allFilesInRollOverDirectory.size()), Integer.valueOf(maxFilesToKeep), Integer.valueOf(size)));
        TreeSet treeSet = new TreeSet(new Comparator() { // from class: com.twitter.sdk.android.core.internal.scribe.EventsFilesManager$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return EventsFilesManager.lambda$deleteOldestInRollOverIfOverMax$0((EventsFilesManager.FileWithTimestamp) obj, (EventsFilesManager.FileWithTimestamp) obj2);
            }
        });
        for (File file : allFilesInRollOverDirectory) {
            treeSet.add(new FileWithTimestamp(file, parseCreationTimestampFromFileName(file.getName())));
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            arrayList.add(((FileWithTimestamp) it.next()).file);
            if (arrayList.size() == size) {
                break;
            }
        }
        this.eventStorage.deleteFilesInRollOverDirectory(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$deleteOldestInRollOverIfOverMax$0(FileWithTimestamp fileWithTimestamp, FileWithTimestamp fileWithTimestamp2) {
        return (int) (fileWithTimestamp.timestamp - fileWithTimestamp2.timestamp);
    }

    public long parseCreationTimestampFromFileName(String str) {
        String[] split = str.split("_");
        if (split.length != 3) {
            return 0L;
        }
        try {
            return Long.valueOf(split[2]).longValue();
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class FileWithTimestamp {
        final File file;
        final long timestamp;

        FileWithTimestamp(File file, long j2) {
            this.file = file;
            this.timestamp = j2;
        }
    }
}
