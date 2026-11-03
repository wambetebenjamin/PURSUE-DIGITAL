package com.vk.api.sdk.internal;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: HttpMultipartEntry.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/api/sdk/internal/HttpMultipartEntry;", "", "File", "Text", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public interface HttpMultipartEntry {

    /* compiled from: HttpMultipartEntry.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0006H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u0004¨\u0006\u0016"}, d2 = {"Lcom/vk/api/sdk/internal/HttpMultipartEntry$File;", "Lcom/vk/api/sdk/internal/HttpMultipartEntry;", "fileUri", "Landroid/net/Uri;", "(Landroid/net/Uri;)V", "fileName", "", "(Landroid/net/Uri;Ljava/lang/String;)V", "getFileName", "()Ljava/lang/String;", "setFileName", "(Ljava/lang/String;)V", "getFileUri", "()Landroid/net/Uri;", "setFileUri", "equals", "", "other", "", "hashCode", "", "toString", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class File implements HttpMultipartEntry {
        private String fileName;
        private Uri fileUri;

        public final Uri getFileUri() {
            return this.fileUri;
        }

        public final void setFileUri(Uri uri) {
            Intrinsics.checkParameterIsNotNull(uri, "<set-?>");
            this.fileUri = uri;
        }

        public final String getFileName() {
            return this.fileName;
        }

        public final void setFileName(String str) {
            this.fileName = str;
        }

        public File(Uri fileUri) {
            Intrinsics.checkParameterIsNotNull(fileUri, "fileUri");
            this.fileUri = fileUri;
            this.fileName = fileUri.getLastPathSegment();
        }

        public File(Uri fileUri, String fileName) {
            Intrinsics.checkParameterIsNotNull(fileUri, "fileUri");
            Intrinsics.checkParameterIsNotNull(fileName, "fileName");
            this.fileUri = fileUri;
            this.fileName = fileName;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof File) {
                return Intrinsics.areEqual(this.fileUri, ((File) obj).fileUri);
            }
            return false;
        }

        public int hashCode() {
            return this.fileUri.hashCode();
        }

        public String toString() {
            return "File{fileUri='" + this.fileUri + "'}";
        }
    }

    /* compiled from: HttpMultipartEntry.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u000f"}, d2 = {"Lcom/vk/api/sdk/internal/HttpMultipartEntry$Text;", "Lcom/vk/api/sdk/internal/HttpMultipartEntry;", "textValue", "", "(Ljava/lang/String;)V", "getTextValue", "()Ljava/lang/String;", "setTextValue", "equals", "", "other", "", "hashCode", "", "toString", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Text implements HttpMultipartEntry {
        private String textValue;

        public Text(String textValue) {
            Intrinsics.checkParameterIsNotNull(textValue, "textValue");
            this.textValue = textValue;
        }

        public final String getTextValue() {
            return this.textValue;
        }

        public final void setTextValue(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.textValue = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Text) {
                return Intrinsics.areEqual(this.textValue, ((Text) obj).textValue);
            }
            return false;
        }

        public int hashCode() {
            return this.textValue.hashCode();
        }

        public String toString() {
            return "Text{textValue='" + this.textValue + "'}";
        }
    }
}
