package com.vk.api.sdk.okhttp;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import com.vk.api.sdk.exceptions.VKLocalIOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
/* compiled from: FileFullRequestBody.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/vk/api/sdk/okhttp/FileFullRequestBody;", "Lokhttp3/RequestBody;", "context", "Landroid/content/Context;", "fileUri", "Landroid/net/Uri;", "(Landroid/content/Context;Landroid/net/Uri;)V", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "writeTo", "", "sink", "Lokio/BufferedSink;", "Companion", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class FileFullRequestBody extends RequestBody {
    private static final int CHUNK = 8192;
    public static final Companion Companion = new Companion(null);
    private final Context context;
    private final Uri fileUri;

    public FileFullRequestBody(Context context, Uri uri) {
        this.context = context;
        this.fileUri = uri;
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        if (uri == null) {
            throw new IllegalArgumentException("fileUri is null");
        }
        if (!new File(uri.getPath()).exists()) {
            throw new IllegalArgumentException("file is not exist");
        }
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        Uri uri = this.fileUri;
        String str = null;
        String lastPathSegment = uri != null ? uri.getLastPathSegment() : null;
        if (lastPathSegment != null) {
            try {
                str = URLConnection.guessContentTypeFromName(lastPathSegment);
            } catch (Exception unused) {
            }
        }
        if (str == null) {
            str = "application/octet-stream";
        }
        return MediaType.parse(str);
    }

    @Override // okhttp3.RequestBody
    public long contentLength() throws IOException {
        ContentResolver contentResolver;
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            try {
                Context context = this.context;
                if (context != null && (contentResolver = context.getContentResolver()) != null) {
                    assetFileDescriptor = contentResolver.openAssetFileDescriptor(this.fileUri, DownloadFileUtils.MODE_READ);
                }
                if (assetFileDescriptor != null) {
                    long length = assetFileDescriptor.getLength();
                    try {
                        assetFileDescriptor.close();
                    } catch (Exception unused) {
                    }
                    return length;
                }
                throw new FileNotFoundException("Cannot open uri: " + this.fileUri);
            } catch (FileNotFoundException e2) {
                throw new VKLocalIOException(e2);
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    assetFileDescriptor.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink sink) throws IOException {
        ContentResolver contentResolver;
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        AssetFileDescriptor assetFileDescriptor = null;
        OutputStream outputStream = sink.outputStream();
        try {
            try {
                Context context = this.context;
                if (context != null && (contentResolver = context.getContentResolver()) != null) {
                    assetFileDescriptor = contentResolver.openAssetFileDescriptor(this.fileUri, DownloadFileUtils.MODE_READ);
                }
                if (assetFileDescriptor == null) {
                    throw new FileNotFoundException("Cannot open uri: " + this.fileUri);
                }
                FileInputStream createInputStream = assetFileDescriptor.createInputStream();
                Intrinsics.checkExpressionValueIsNotNull(createInputStream, "fileDescriptor.createInputStream()");
                byte[] bArr = new byte[8192];
                while (createInputStream.available() > 0) {
                    try {
                        int read = createInputStream.read(bArr);
                        if (read != -1) {
                            outputStream.write(bArr, 0, read);
                            outputStream.flush();
                        }
                    } catch (IOException e2) {
                        throw new VKLocalIOException(e2);
                    }
                }
                try {
                    assetFileDescriptor.close();
                } catch (Exception unused) {
                }
            } catch (IOException e3) {
                throw new VKLocalIOException(e3);
            }
        } catch (Throwable th) {
            if (assetFileDescriptor != null) {
                try {
                    assetFileDescriptor.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    /* compiled from: FileFullRequestBody.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/vk/api/sdk/okhttp/FileFullRequestBody$Companion;", "", "()V", "CHUNK", "", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
