package com.bytedance.retrofit2.mime;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/* loaded from: classes2.dex */
public final class MultipartTypedOutput implements TypedOutput {
    public static final String DEFAULT_TRANSFER_ENCODING = "binary";
    private final String boundary;
    private final byte[] footer;
    private long length;
    private final List<MimePart> mimeParts;

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String fileName() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class MimePart {
        private final TypedOutput body;
        private final String boundary;
        private boolean isBuilt;
        private final boolean isFirst;
        private final String name;
        private byte[] partBoundary;
        private byte[] partHeader;
        private final String transferEncoding;

        public MimePart(String str, String str2, TypedOutput typedOutput, String str3, boolean z) {
            this.name = str;
            this.transferEncoding = str2;
            this.body = typedOutput;
            this.isFirst = z;
            this.boundary = str3;
        }

        public void writeTo(OutputStream outputStream) {
            build();
            outputStream.write(this.partBoundary);
            outputStream.write(this.partHeader);
            this.body.writeTo(outputStream);
        }

        public long size() {
            build();
            if (this.body.length() > -1) {
                return this.body.length() + this.partBoundary.length + this.partHeader.length;
            }
            return -1L;
        }

        private void build() {
            if (this.isBuilt) {
                return;
            }
            this.partBoundary = MultipartTypedOutput.buildBoundary(this.boundary, this.isFirst, false);
            this.partHeader = MultipartTypedOutput.buildHeader(this.name, this.transferEncoding, this.body);
            this.isBuilt = true;
        }
    }

    public MultipartTypedOutput() {
        this(UUID.randomUUID().toString());
    }

    MultipartTypedOutput(String str) {
        this.mimeParts = new LinkedList();
        this.boundary = str;
        byte[] buildBoundary = buildBoundary(str, false, true);
        this.footer = buildBoundary;
        this.length = buildBoundary.length;
    }

    List<byte[]> getParts() {
        ArrayList arrayList = new ArrayList(this.mimeParts.size());
        for (MimePart mimePart : this.mimeParts) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            mimePart.writeTo(byteArrayOutputStream);
            arrayList.add(byteArrayOutputStream.toByteArray());
        }
        return arrayList;
    }

    public void addPart(String str, TypedOutput typedOutput) {
        addPart(str, DEFAULT_TRANSFER_ENCODING, typedOutput);
    }

    public void addPart(String str, String str2, TypedOutput typedOutput) {
        Objects.requireNonNull(str, "Part name must not be null.");
        Objects.requireNonNull(str2, "Transfer encoding must not be null.");
        Objects.requireNonNull(typedOutput, "Part body must not be null.");
        MimePart mimePart = new MimePart(str, str2, typedOutput, this.boundary, this.mimeParts.isEmpty());
        this.mimeParts.add(mimePart);
        long size = mimePart.size();
        if (size == -1) {
            this.length = -1L;
            return;
        }
        long j2 = this.length;
        if (j2 != -1) {
            this.length = j2 + size;
        }
    }

    public int getPartCount() {
        return this.mimeParts.size();
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String mimeType() {
        return "multipart/form-data; boundary=" + this.boundary;
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public long length() {
        return this.length;
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public void writeTo(OutputStream outputStream) {
        for (MimePart mimePart : this.mimeParts) {
            mimePart.writeTo(outputStream);
        }
        outputStream.write(this.footer);
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String md5Stub() {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        if (this.length == -1) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                writeTo(byteArrayOutputStream);
                byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            } catch (Throwable unused) {
                byteArrayInputStream = null;
            }
        } catch (Throwable unused2) {
            byteArrayOutputStream = null;
            byteArrayInputStream = null;
        }
        try {
            String md5Hex = DigestUtil.md5Hex(byteArrayInputStream);
            try {
                byteArrayOutputStream.close();
            } catch (Throwable unused3) {
            }
            try {
                byteArrayInputStream.close();
            } catch (Throwable unused4) {
            }
            return md5Hex;
        } catch (Throwable unused5) {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable unused6) {
                }
            }
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable unused7) {
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] buildBoundary(String str, boolean z, boolean z2) {
        try {
            StringBuilder sb = new StringBuilder(str.length() + 8);
            if (!z) {
                sb.append("\r\n");
            }
            sb.append("--");
            sb.append(str);
            if (z2) {
                sb.append("--");
            }
            sb.append("\r\n");
            return sb.toString().getBytes("UTF-8");
        } catch (IOException e2) {
            throw new RuntimeException("Unable to write multipart boundary", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] buildHeader(String str, String str2, TypedOutput typedOutput) {
        try {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Content-Disposition: form-data; name=\"");
            sb.append(str);
            String fileName = typedOutput.fileName();
            if (fileName != null) {
                sb.append("\"; filename=\"");
                sb.append(fileName);
            }
            sb.append("\"\r\nContent-Type: ");
            sb.append(typedOutput.mimeType());
            long length = typedOutput.length();
            if (length != -1) {
                sb.append("\r\nContent-Length: ").append(length);
            }
            sb.append("\r\nContent-Transfer-Encoding: ");
            sb.append(str2);
            sb.append("\r\n\r\n");
            return sb.toString().getBytes("UTF-8");
        } catch (IOException e2) {
            throw new RuntimeException("Unable to write multipart header", e2);
        }
    }
}
