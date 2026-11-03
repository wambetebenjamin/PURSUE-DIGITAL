package com.facebook.cache.disk;

import android.os.Environment;
import com.bytedance.librarian.c;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileTreeVisitor;
import com.facebook.common.file.FileUtils;
import com.facebook.common.internal.CountingOutputStream;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class DefaultDiskStorage implements DiskStorage {
    private static final String CONTENT_FILE_EXTENSION = ".cnt";
    private static final String DEFAULT_DISK_STORAGE_VERSION_PREFIX = "v2";
    private static final int SHARDING_BUCKET_COUNT = 100;
    private static final String TEMP_FILE_EXTENSION = ".tmp";
    private final CacheErrorLogger mCacheErrorLogger;
    private final Clock mClock;
    private final File mConfigRootDirectory;
    private final File mConfigVersionDirectory;
    private final boolean mIsExternal;
    private final File mOldConfigVersionDirectory;
    private final File mRootDirectory;
    private final File mVersionDirectory;
    private static final Class<?> TAG = DefaultDiskStorage.class;
    static final long TEMP_FILE_LIFETIME_MS = TimeUnit.MINUTES.toMillis(30);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public enum FileCategory {
        IMAGE_CACHE_FILE,
        CONFIG_FILE
    }

    /* loaded from: classes8.dex */
    public @interface FileType {
        public static final String CONTENT = ".cnt";
        public static final String TEMP = ".tmp";
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean isEnabled() {
        return true;
    }

    public DefaultDiskStorage(File file, File file2, int i2, CacheErrorLogger cacheErrorLogger) {
        Preconditions.checkNotNull(file);
        this.mRootDirectory = file;
        this.mConfigRootDirectory = file2;
        this.mIsExternal = isExternal(file, cacheErrorLogger);
        this.mVersionDirectory = new File(file, getVersionSubdirectoryName(i2));
        this.mOldConfigVersionDirectory = new File(file, getVersionSubdirectoryName(i2) + "-config");
        this.mConfigVersionDirectory = new File(file2, getVersionSubdirectoryName(i2) + "-config");
        this.mCacheErrorLogger = cacheErrorLogger;
        recreateDirectoryIfVersionChanges();
        recreateConfigDirectoryIfVersionChanges();
        this.mClock = SystemClock.get();
    }

    private static boolean isExternal(File file, CacheErrorLogger cacheErrorLogger) {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory != null) {
                try {
                } catch (IOException e2) {
                    e = e2;
                }
                try {
                    return file.getCanonicalPath().contains(externalStorageDirectory.toString());
                } catch (IOException e3) {
                    e = e3;
                    cacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.OTHER, TAG, "failed to read folder to check if external: " + ((String) null), e);
                    return false;
                }
            }
            return false;
        } catch (Exception e4) {
            cacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.OTHER, TAG, "failed to get the external storage directory!", e4);
            return false;
        }
    }

    static String getVersionSubdirectoryName(int i2) {
        return String.format(null, "%s.ols%d.%d", "v2", 100, Integer.valueOf(i2));
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean isExternal() {
        return this.mIsExternal;
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public String getStorageName() {
        String absolutePath = this.mRootDirectory.getAbsolutePath();
        return "_" + absolutePath.substring(absolutePath.lastIndexOf(47) + 1, absolutePath.length()) + "_" + absolutePath.hashCode();
    }

    private void recreateDirectoryIfVersionChanges() {
        boolean z = true;
        if (this.mRootDirectory.exists()) {
            if (this.mVersionDirectory.exists()) {
                z = false;
            } else {
                FileTree.deleteRecursively(this.mRootDirectory);
            }
        }
        if (z) {
            try {
                FileUtils.mkdirs(this.mVersionDirectory);
            } catch (FileUtils.CreateDirectoryException unused) {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_DIR, TAG, "version directory could not be created: " + this.mVersionDirectory, null);
            }
        }
    }

    /* loaded from: classes8.dex */
    private static class IncompleteFileException extends IOException {
        public final long actual;
        public final long expected;

        public IncompleteFileException(long j2, long j3) {
            super("File was not written completely. Expected: " + j2 + ", found: " + j3);
            this.expected = j2;
            this.actual = j3;
        }
    }

    File getContentFileFor(String str) {
        return new File(getFilename(str));
    }

    private String getSubdirectoryPath(String str) {
        return this.mVersionDirectory + File.separator + String.valueOf(Math.abs(str.hashCode() % 100));
    }

    private File getSubdirectory(String str) {
        return new File(getSubdirectoryPath(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public class EntriesCollector implements FileTreeVisitor {
        private final List<DiskStorage.Entry> result;

        @Override // com.facebook.common.file.FileTreeVisitor
        public void postVisitDirectory(File file) {
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void preVisitDirectory(File file) {
        }

        private EntriesCollector() {
            this.result = new ArrayList();
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void visitFile(File file) {
            FileInfo shardFileInfo = DefaultDiskStorage.this.getShardFileInfo(file);
            if (shardFileInfo == null || shardFileInfo.type != ".cnt") {
                return;
            }
            this.result.add(new EntryImpl(shardFileInfo.resourceId, file));
        }

        public List<DiskStorage.Entry> getEntries() {
            return Collections.unmodifiableList(this.result);
        }
    }

    /* loaded from: classes8.dex */
    private class PurgingVisitor implements FileTreeVisitor {
        private boolean insideBaseDirectory;

        private PurgingVisitor() {
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void preVisitDirectory(File file) {
            if (this.insideBaseDirectory || !file.equals(DefaultDiskStorage.this.mVersionDirectory)) {
                return;
            }
            this.insideBaseDirectory = true;
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void visitFile(File file) {
            if (this.insideBaseDirectory && isExpectedFile(file)) {
                return;
            }
            file.delete();
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void postVisitDirectory(File file) {
            if (!DefaultDiskStorage.this.mRootDirectory.equals(file) && !this.insideBaseDirectory) {
                file.delete();
            }
            if (this.insideBaseDirectory && file.equals(DefaultDiskStorage.this.mVersionDirectory)) {
                this.insideBaseDirectory = false;
            }
        }

        private boolean isExpectedFile(File file) {
            FileInfo shardFileInfo = DefaultDiskStorage.this.getShardFileInfo(file);
            if (shardFileInfo == null) {
                return false;
            }
            if (shardFileInfo.type == ".tmp") {
                return isRecentFile(file);
            }
            Preconditions.checkState(shardFileInfo.type == ".cnt");
            return true;
        }

        private boolean isRecentFile(File file) {
            return file.lastModified() > DefaultDiskStorage.this.mClock.now() - DefaultDiskStorage.TEMP_FILE_LIFETIME_MS;
        }
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public void purgeUnexpectedResources() {
        FileTree.walkFileTree(this.mRootDirectory, new PurgingVisitor());
    }

    private void mkdirs(File file, String str) {
        try {
            FileUtils.mkdirs(file);
        } catch (FileUtils.CreateDirectoryException e2) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_DIR, TAG, str, e2);
            throw e2;
        }
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public DiskStorage.Inserter insert(String str, Object obj) {
        FileInfo fileInfo = new FileInfo(".tmp", str);
        File subdirectory = getSubdirectory(fileInfo.resourceId);
        if (!subdirectory.exists()) {
            mkdirs(subdirectory, "insert");
        }
        try {
            File createTempFile = fileInfo.createTempFile(subdirectory);
            insertConfigFile(str, obj);
            return new InserterImpl(str, createTempFile);
        } catch (IOException e2) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_TEMPFILE, TAG, "insert", e2);
            throw e2;
        }
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public BinaryResource getResource(String str, Object obj) {
        File contentFileFor = getContentFileFor(str);
        if (contentFileFor.exists()) {
            contentFileFor.setLastModified(this.mClock.now());
            return FileBinaryResource.createOrNull(contentFileFor);
        }
        return null;
    }

    private String getFilename(String str) {
        FileInfo fileInfo = new FileInfo(".cnt", str);
        return fileInfo.toPath(getSubdirectoryPath(fileInfo.resourceId));
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean contains(String str, Object obj) {
        return query(str, false);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean touch(String str, Object obj) {
        return query(str, true);
    }

    private boolean query(String str, boolean z) {
        File contentFileFor = getContentFileFor(str);
        boolean exists = contentFileFor.exists();
        if (z && exists) {
            contentFileFor.setLastModified(this.mClock.now());
        }
        return exists;
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public long remove(DiskStorage.Entry entry) {
        return doRemove(((EntryImpl) entry).getResource().getFile());
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public long remove(String str) {
        return doRemove(getContentFileFor(str));
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public long removeConfig(String str) {
        doRemove(new File((this.mOldConfigVersionDirectory + File.separator + String.valueOf(Math.abs(str.hashCode() % 100))) + File.separator + str + ".cnt"));
        return doRemove(getConfigFileFor(str));
    }

    private long doRemove(File file) {
        if (file.exists()) {
            long length = file.length();
            if (file.delete()) {
                return length;
            }
            return -1L;
        }
        return 0L;
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public void clearAll() {
        FileTree.deleteContents(this.mRootDirectory);
        FileTree.deleteContents(this.mConfigRootDirectory);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public DiskStorage.DiskDumpInfo getDumpInfo() {
        List<DiskStorage.Entry> entries = getEntries();
        DiskStorage.DiskDumpInfo diskDumpInfo = new DiskStorage.DiskDumpInfo();
        for (DiskStorage.Entry entry : entries) {
            DiskStorage.DiskDumpInfoEntry dumpCacheEntry = dumpCacheEntry(entry);
            String str = dumpCacheEntry.type;
            if (!diskDumpInfo.typeCounts.containsKey(str)) {
                diskDumpInfo.typeCounts.put(str, 0);
            }
            diskDumpInfo.typeCounts.put(str, Integer.valueOf(diskDumpInfo.typeCounts.get(str).intValue() + 1));
            diskDumpInfo.entries.add(dumpCacheEntry);
        }
        return diskDumpInfo;
    }

    private DiskStorage.DiskDumpInfoEntry dumpCacheEntry(DiskStorage.Entry entry) {
        EntryImpl entryImpl = (EntryImpl) entry;
        byte[] read = entryImpl.getResource().read();
        String typeOfBytes = typeOfBytes(read);
        return new DiskStorage.DiskDumpInfoEntry(entryImpl.getResource().getFile().getPath(), typeOfBytes, (float) entryImpl.getSize(), (!typeOfBytes.equals("undefined") || read.length < 4) ? "" : String.format(null, "0x%02X 0x%02X 0x%02X 0x%02X", Byte.valueOf(read[0]), Byte.valueOf(read[1]), Byte.valueOf(read[2]), Byte.valueOf(read[3])));
    }

    private String typeOfBytes(byte[] bArr) {
        return bArr.length >= 2 ? (bArr[0] == -1 && bArr[1] == -40) ? "jpg" : (bArr[0] == -119 && bArr[1] == 80) ? "png" : (bArr[0] == 82 && bArr[1] == 73) ? "webp" : (bArr[0] == 71 && bArr[1] == 73) ? "gif" : "undefined" : "undefined";
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public List<DiskStorage.Entry> getEntries() {
        EntriesCollector entriesCollector = new EntriesCollector();
        FileTree.walkFileTree(this.mVersionDirectory, entriesCollector);
        return entriesCollector.getEntries();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public static class EntryImpl implements DiskStorage.Entry {
        private final String id;
        private final FileBinaryResource resource;
        private long size;
        private long timestamp;

        private EntryImpl(String str, File file) {
            Preconditions.checkNotNull(file);
            this.id = (String) Preconditions.checkNotNull(str);
            this.resource = FileBinaryResource.createOrNull(file);
            this.size = -1L;
            this.timestamp = -1L;
        }

        @Override // com.facebook.cache.disk.DiskStorage.Entry
        public String getId() {
            return this.id;
        }

        @Override // com.facebook.cache.disk.DiskStorage.Entry
        public long getTimestamp() {
            if (this.timestamp < 0) {
                this.timestamp = this.resource.getFile().lastModified();
            }
            return this.timestamp;
        }

        @Override // com.facebook.cache.disk.DiskStorage.Entry
        public FileBinaryResource getResource() {
            return this.resource;
        }

        @Override // com.facebook.cache.disk.DiskStorage.Entry
        public long getSize() {
            if (this.size < 0) {
                this.size = this.resource.size();
            }
            return this.size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FileInfo getShardFileInfo(File file) {
        FileInfo fromFile = FileInfo.fromFile(file);
        if (fromFile != null && getSubdirectory(fromFile.resourceId).equals(file.getParentFile())) {
            return fromFile;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static String getFileTypefromExtension(String str) {
        if (".cnt".equals(str)) {
            return ".cnt";
        }
        if (".tmp".equals(str)) {
            return ".tmp";
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static class FileInfo {
        public final String resourceId;
        public final String type;

        private FileInfo(String str, String str2) {
            this.type = str;
            this.resourceId = str2;
        }

        public String toString() {
            return this.type + "(" + this.resourceId + ")";
        }

        public String toPath(String str) {
            return str + File.separator + this.resourceId + this.type;
        }

        public File createTempFile(File file) {
            return File.createTempFile(this.resourceId + c.a.DOT, ".tmp", file);
        }

        @Nullable
        public static FileInfo fromFile(File file) {
            String fileTypefromExtension;
            String name = file.getName();
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf > 0 && (fileTypefromExtension = DefaultDiskStorage.getFileTypefromExtension(name.substring(lastIndexOf))) != null) {
                String substring = name.substring(0, lastIndexOf);
                if (fileTypefromExtension.equals(".tmp")) {
                    int lastIndexOf2 = substring.lastIndexOf(46);
                    if (lastIndexOf2 <= 0) {
                        return null;
                    }
                    substring = substring.substring(0, lastIndexOf2);
                }
                return new FileInfo(fileTypefromExtension, substring);
            }
            return null;
        }
    }

    /* loaded from: classes8.dex */
    class InserterImpl implements DiskStorage.Inserter {
        private final String mResourceId;
        final File mTemporaryFile;

        public InserterImpl(String str, File file) {
            this.mResourceId = str;
            this.mTemporaryFile = file;
        }

        @Override // com.facebook.cache.disk.DiskStorage.Inserter
        public void writeData(WriterCallback writerCallback, Object obj) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(this.mTemporaryFile);
                try {
                    CountingOutputStream countingOutputStream = new CountingOutputStream(fileOutputStream);
                    writerCallback.write(countingOutputStream);
                    countingOutputStream.flush();
                    long count = countingOutputStream.getCount();
                    fileOutputStream.close();
                    if (this.mTemporaryFile.length() != count) {
                        throw new IncompleteFileException(count, this.mTemporaryFile.length());
                    }
                } catch (Throwable th) {
                    fileOutputStream.close();
                    throw th;
                }
            } catch (FileNotFoundException e2) {
                DefaultDiskStorage.this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_UPDATE_FILE_NOT_FOUND, DefaultDiskStorage.TAG, "updateResource", e2);
                throw e2;
            }
        }

        @Override // com.facebook.cache.disk.DiskStorage.Inserter
        public BinaryResource commit(Object obj) {
            CacheErrorLogger.CacheErrorCategory cacheErrorCategory;
            File contentFileFor = DefaultDiskStorage.this.getContentFileFor(this.mResourceId);
            try {
                FileUtils.rename(this.mTemporaryFile, contentFileFor);
                if (contentFileFor.exists()) {
                    contentFileFor.setLastModified(DefaultDiskStorage.this.mClock.now());
                }
                return FileBinaryResource.createOrNull(contentFileFor);
            } catch (FileUtils.RenameException e2) {
                Throwable cause = e2.getCause();
                if (cause == null) {
                    cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.WRITE_RENAME_FILE_OTHER;
                } else if (cause instanceof FileUtils.ParentDirNotFoundException) {
                    cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_PARENT_NOT_FOUND;
                } else if (cause instanceof FileNotFoundException) {
                    cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_NOT_FOUND;
                } else {
                    cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.WRITE_RENAME_FILE_OTHER;
                }
                DefaultDiskStorage.this.mCacheErrorLogger.logError(cacheErrorCategory, DefaultDiskStorage.TAG, "commit", e2);
                throw e2;
            }
        }

        @Override // com.facebook.cache.disk.DiskStorage.Inserter
        public boolean cleanUp() {
            return !this.mTemporaryFile.exists() || this.mTemporaryFile.delete();
        }
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public Map<String, String> getResourceConfig(String str, Object obj) {
        Map<String, String> fileToMap = fileToMap(new File((this.mOldConfigVersionDirectory + File.separator + String.valueOf(Math.abs(str.hashCode() % 100))) + File.separator + str + ".cnt"));
        fileToMap.putAll(fileToMap(getConfigFileFor(str)));
        return fileToMap;
    }

    private File getConfigFileFor(String str) {
        return new File(getConfigSubdirectoryPath(str) + File.separator + str + ".cnt");
    }

    private void recreateConfigDirectoryIfVersionChanges() {
        boolean z = true;
        if (this.mRootDirectory.exists()) {
            if (this.mVersionDirectory.exists()) {
                z = false;
            } else {
                FileTree.deleteRecursively(this.mRootDirectory);
            }
        }
        if (z) {
            try {
                FileUtils.mkdirs(this.mVersionDirectory);
            } catch (FileUtils.CreateDirectoryException unused) {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_DIR, TAG, "version directory could not be created: " + this.mVersionDirectory, null);
            }
        }
    }

    private String getConfigSubdirectoryPath(String str) {
        return this.mConfigVersionDirectory + File.separator + String.valueOf(Math.abs(str.hashCode() % 100));
    }

    private void insertConfigFile(String str, Object obj) {
        Map<String, String> extraInfo;
        if (!(obj instanceof DiskWrapperCacheKey) || (extraInfo = ((DiskWrapperCacheKey) obj).getExtraInfo()) == null || extraInfo.isEmpty()) {
            return;
        }
        File file = new File(getConfigSubdirectoryPath(str));
        if (!file.exists()) {
            mkdirs(file, "insert");
        }
        File configFileFor = getConfigFileFor(str);
        if (!configFileFor.exists()) {
            configFileFor.createNewFile();
        }
        mapToFile(extraInfo, configFileFor);
    }

    private File mapToFile(Map<String, String> map, File file) {
        StringBuilder sb = new StringBuilder();
        FileWriter fileWriter = new FileWriter(file, false);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(System.lineSeparator());
        }
        fileWriter.write(sb.toString());
        fileWriter.close();
        return file;
    }

    private Map<String, String> fileToMap(File file) {
        HashMap hashMap = new HashMap();
        if (file.exists()) {
            BufferedReader bufferedReader = null;
            try {
                try {
                    try {
                        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                        while (true) {
                            try {
                                String readLine = bufferedReader2.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                int lastIndexOf = readLine.lastIndexOf("=");
                                hashMap.put(readLine.substring(0, lastIndexOf), readLine.substring(lastIndexOf + 1));
                            } catch (IOException unused) {
                                bufferedReader = bufferedReader2;
                                FLog.w(TAG, "fileToMap: file not found");
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                return hashMap;
                            } catch (Throwable th) {
                                th = th;
                                bufferedReader = bufferedReader2;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException unused2) {
                                    }
                                }
                                throw th;
                            }
                        }
                        bufferedReader2.close();
                        bufferedReader2.close();
                    } catch (IOException unused3) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException unused4) {
            }
            return hashMap;
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public class ConfigEntriesCollector implements FileTreeVisitor {
        private FileCategory mFileCategory;
        private final List<DiskStorage.Entry> result = new ArrayList();

        @Override // com.facebook.common.file.FileTreeVisitor
        public void postVisitDirectory(File file) {
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void preVisitDirectory(File file) {
        }

        public ConfigEntriesCollector(FileCategory fileCategory) {
            this.mFileCategory = fileCategory;
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void visitFile(File file) {
            if (this.mFileCategory == FileCategory.CONFIG_FILE) {
                FileInfo configShardFileInfo = DefaultDiskStorage.this.getConfigShardFileInfo(file);
                if (configShardFileInfo == null || configShardFileInfo.type != ".cnt") {
                    return;
                }
                this.result.add(new EntryImpl(configShardFileInfo.resourceId, file));
                return;
            }
            FileInfo shardFileInfo = DefaultDiskStorage.this.getShardFileInfo(file);
            if (shardFileInfo != null) {
                if (shardFileInfo.type == ".cnt" || shardFileInfo.type == ".tmp") {
                    this.result.add(new EntryImpl(shardFileInfo.resourceId, file));
                }
            }
        }

        public List<DiskStorage.Entry> getEntries() {
            return Collections.unmodifiableList(this.result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FileInfo getConfigShardFileInfo(File file) {
        FileInfo fromFile = FileInfo.fromFile(file);
        if (fromFile != null && new File(getConfigSubdirectoryPath(fromFile.resourceId)).equals(file.getParentFile())) {
            return fromFile;
        }
        return null;
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public List<DiskStorage.Entry> getConfigEntries() {
        ConfigEntriesCollector configEntriesCollector = new ConfigEntriesCollector(FileCategory.CONFIG_FILE);
        FileTree.walkFileTree(this.mConfigVersionDirectory, configEntriesCollector);
        return configEntriesCollector.getEntries();
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public List<DiskStorage.Entry> getDiskCacheEntries() {
        ConfigEntriesCollector configEntriesCollector = new ConfigEntriesCollector(FileCategory.IMAGE_CACHE_FILE);
        FileTree.walkFileTree(this.mVersionDirectory, configEntriesCollector);
        return configEntriesCollector.getEntries();
    }
}
