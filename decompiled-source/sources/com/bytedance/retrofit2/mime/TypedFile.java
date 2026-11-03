package com.bytedance.retrofit2.mime;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
/* loaded from: classes2.dex */
public class TypedFile implements TypedInput, TypedOutput {
    private static final int BUFFER_SIZE = 4096;
    private final File file;
    private final String mimeType;

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String md5Stub() {
        return null;
    }

    public TypedFile(String str, File file) {
        str = str == null ? "application/octet-stream" : str;
        Objects.requireNonNull(file, "file");
        this.mimeType = str;
        this.file = file;
    }

    public File file() {
        return this.file;
    }

    @Override // com.bytedance.retrofit2.mime.TypedInput
    public String mimeType() {
        return this.mimeType;
    }

    @Override // com.bytedance.retrofit2.mime.TypedInput
    public long length() {
        return this.file.length();
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String fileName() {
        return this.file.getName();
    }

    @Override // com.bytedance.retrofit2.mime.TypedInput
    public InputStream in() {
        return new FileInputStream(this.file);
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public void writeTo(OutputStream outputStream) {
        byte[] bArr = new byte[4096];
        FileInputStream fileInputStream = new FileInputStream(this.file);
        while (true) {
            try {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    return;
                }
                outputStream.write(bArr, 0, read);
            } finally {
                fileInputStream.close();
            }
        }
    }

    public void moveTo(TypedFile typedFile) {
        if (!mimeType().equals(typedFile.mimeType())) {
            throw new IOException("Type mismatch.");
        }
        if (!this.file.renameTo(typedFile.file())) {
            throw new IOException("Rename failed!");
        }
    }

    public String toString() {
        return this.file.getAbsolutePath() + " (" + mimeType() + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TypedFile) {
            return this.file.equals(((TypedFile) obj).file);
        }
        return false;
    }

    public int hashCode() {
        return this.file.hashCode();
    }
}
