package com.bytedance.geckox.model;

import com.bytedance.apm.constant.i;
import com.bytedance.geckox.interceptors.c;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes2.dex */
public class UpdatePackage {
    @SerializedName("access_key")
    private String accessKey;
    @SerializedName("channel")
    private String channel;
    @SerializedName("channel_index")
    private int channelIndex;
    @SerializedName("content")
    private Content content;
    @SerializedName("from")
    private List<String> from;
    @SerializedName("group_name")
    private String groupName;
    private long localVersion;
    @SerializedName("package_type")
    private int packageType;
    @SerializedName("package_version")
    private long version;

    /* loaded from: classes2.dex */
    public static final class FileType {
        public static final int COMPRESSED_FILE = 0;
        public static final int MY_ARCHIVE_FILE = 2;
        public static final int UNCOMPRESSED_FILE = 1;
    }

    public Content getContent() {
        return this.content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public List<String> getFrom() {
        return this.from;
    }

    public void setFrom(List<String> list) {
        this.from = list;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public void setAccessKey(String str) {
        this.accessKey = str;
    }

    public UpdatePackage() {
        this.content = new Content();
    }

    public UpdatePackage(long j2, String str, Package r4, Package r5) {
        this.version = j2;
        this.channel = str;
        Content content = new Content();
        this.content = content;
        content.fullPackage = r4;
        this.content.patch = r5;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public int getChannelIndex() {
        return this.channelIndex;
    }

    public void setChannelIndex(int i2) {
        this.channelIndex = i2;
    }

    public long getLocalVersion() {
        return this.localVersion;
    }

    public void setLocalVersion(long j2) {
        this.localVersion = j2;
    }

    public long getVersion() {
        return this.version;
    }

    public void setVersion(long j2) {
        this.version = j2;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public Package getFullPackage() {
        return this.content.fullPackage;
    }

    public void setFullPackage(Package r2) {
        this.content.fullPackage = r2;
    }

    public Package getPatch() {
        return this.content.patch;
    }

    public void setPatch(Package r2) {
        this.content.patch = r2;
    }

    public Strategy getStrategy() {
        return this.content.strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.content.strategy = strategy;
    }

    public void setPackageType(int i2) {
        this.packageType = i2;
    }

    public boolean isFullUpdate() {
        return getFullPackage() != null && getFullPackage().getUrlList().size() > 0;
    }

    public boolean isPatchUpdate() {
        return getPatch() != null && getPatch().getUrlList().size() > 0;
    }

    public int getPackageType() {
        return this.packageType;
    }

    public String toString() {
        return "UpdatePackage{version=" + this.version + ", channel='" + this.channel + "', content=" + this.content + ", packageType=" + this.packageType + '}';
    }

    /* loaded from: classes2.dex */
    public static class Package {
        @SerializedName("id")
        long id;
        @SerializedName(i.PERF_DISK_FILE_SIZE)
        long length;
        @SerializedName("md5")
        String md5;
        @Deprecated
        String url;
        @SerializedName("url_list")
        List<String> urlList;

        public Package() {
        }

        public Package(int i2, List<String> list, String str) {
            this.id = i2;
            this.urlList = list;
            this.md5 = str;
        }

        public long getId() {
            return this.id;
        }

        public void setId(int i2) {
            this.id = i2;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getMd5() {
            return this.md5;
        }

        public void setMd5(String str) {
            this.md5 = str;
        }

        public long getLength() {
            return this.length;
        }

        public void setLength(long j2) {
            this.length = j2;
        }

        public List<String> getUrlList() {
            return this.urlList;
        }

        public void setUrlList(List<String> list) {
            this.urlList = list;
        }

        public String toString() {
            return "Package{url='" + this.url + "', md5='" + this.md5 + "'}";
        }
    }

    /* loaded from: classes2.dex */
    public static class Content {
        @SerializedName("package")
        private Package fullPackage;
        @SerializedName(c.f9069b)
        private Package patch;
        @SerializedName("strategies")
        private Strategy strategy;
    }

    /* loaded from: classes2.dex */
    public static class Strategy {
        @SerializedName("del_if_download_failed")
        private boolean deleteIfFail;
        @SerializedName("del_old_pkg_before_download")
        private boolean deleteOldPackageBeforeDownload;
        @SerializedName("need_unzip")
        private boolean needUnzip;

        public Strategy(int i2) {
            this.deleteIfFail = i2 == 1;
        }

        public boolean isDeleteIfFail() {
            return this.deleteIfFail;
        }

        public void setDeleteIfFail(boolean z) {
            this.deleteIfFail = z;
        }

        public boolean isDeleteOldPackageBeforeDownload() {
            return this.deleteOldPackageBeforeDownload;
        }

        public void setDeleteOldPackageBeforeDownload(boolean z) {
            this.deleteOldPackageBeforeDownload = z;
        }

        public boolean isNeedUnzip() {
            return this.needUnzip;
        }

        public void setNeedUnzip(boolean z) {
            this.needUnzip = z;
        }
    }
}
