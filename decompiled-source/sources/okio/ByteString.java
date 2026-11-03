package okio;

import com.bytedance.common.utility.n;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.common.base.Ascii;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes10.dex */
public class ByteString implements Serializable, Comparable<ByteString> {
    private static final long serialVersionUID = 1;
    final byte[] data;
    transient int hashCode;
    transient String utf8;
    static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final ByteString EMPTY = of(new byte[0]);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteString(byte[] bArr) {
        this.data = bArr;
    }

    public static ByteString of(byte... bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("data == null");
        }
        return new ByteString((byte[]) bArr.clone());
    }

    public static ByteString of(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            throw new IllegalArgumentException("data == null");
        }
        Util.checkOffsetAndCount(bArr.length, i2, i3);
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return new ByteString(bArr2);
    }

    public static ByteString of(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("data == null");
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return new ByteString(bArr);
    }

    public static ByteString encodeUtf8(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        ByteString byteString = new ByteString(str.getBytes(Util.UTF_8));
        byteString.utf8 = str;
        return byteString;
    }

    public static ByteString encodeString(String str, Charset charset) {
        if (str != null) {
            if (charset == null) {
                throw new IllegalArgumentException("charset == null");
            }
            return new ByteString(str.getBytes(charset));
        }
        throw new IllegalArgumentException("s == null");
    }

    public String utf8() {
        String str = this.utf8;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.data, Util.UTF_8);
        this.utf8 = str2;
        return str2;
    }

    public String string(Charset charset) {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        return new String(this.data, charset);
    }

    public String base64() {
        return Base64.encode(this.data);
    }

    public ByteString md5() {
        return digest("MD5");
    }

    public ByteString sha1() {
        return digest(n.SHA_1);
    }

    public ByteString sha256() {
        return digest(n.SHA_256);
    }

    public ByteString sha512() {
        return digest("SHA-512");
    }

    private ByteString digest(String str) {
        try {
            return of(MessageDigest.getInstance(str).digest(this.data));
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        }
    }

    public ByteString hmacSha1(ByteString byteString) {
        return hmac("HmacSHA1", byteString);
    }

    public ByteString hmacSha256(ByteString byteString) {
        return hmac("HmacSHA256", byteString);
    }

    public ByteString hmacSha512(ByteString byteString) {
        return hmac("HmacSHA512", byteString);
    }

    private ByteString hmac(String str, ByteString byteString) {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            return of(mac.doFinal(this.data));
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new AssertionError(e3);
        }
    }

    public String base64Url() {
        return Base64.encodeUrl(this.data);
    }

    @Nullable
    public static ByteString decodeBase64(String str) {
        if (str == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        byte[] decode = Base64.decode(str);
        if (decode != null) {
            return new ByteString(decode);
        }
        return null;
    }

    public String hex() {
        byte[] bArr = this.data;
        char[] cArr = new char[bArr.length * 2];
        int i2 = 0;
        for (byte b2 : bArr) {
            int i3 = i2 + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i2] = cArr2[(b2 >> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = cArr2[b2 & Ascii.SI];
        }
        return new String(cArr);
    }

    public static ByteString decodeHex(String str) {
        if (str == null) {
            throw new IllegalArgumentException("hex == null");
        }
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + str);
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) ((decodeHexDigit(str.charAt(i3)) << 4) + decodeHexDigit(str.charAt(i3 + 1)));
        }
        return of(bArr);
    }

    private static int decodeHexDigit(char c2) {
        if (c2 < '0' || c2 > '9') {
            char c3 = 'a';
            if (c2 < 'a' || c2 > 'f') {
                c3 = 'A';
                if (c2 < 'A' || c2 > 'F') {
                    throw new IllegalArgumentException("Unexpected hex digit: " + c2);
                }
            }
            return (c2 - c3) + 10;
        }
        return c2 - '0';
    }

    public static ByteString read(InputStream inputStream, int i2) throws IOException {
        if (inputStream != null) {
            if (i2 < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + i2);
            }
            byte[] bArr = new byte[i2];
            int i3 = 0;
            while (i3 < i2) {
                int read = inputStream.read(bArr, i3, i2 - i3);
                if (read == -1) {
                    throw new EOFException();
                }
                i3 += read;
            }
            return new ByteString(bArr);
        }
        throw new IllegalArgumentException("in == null");
    }

    public ByteString toAsciiLowercase() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i2 >= bArr.length) {
                return this;
            }
            byte b2 = bArr[i2];
            if (b2 >= 65 && b2 <= 90) {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i2] = (byte) (b2 + 32);
                for (int i3 = i2 + 1; i3 < bArr2.length; i3++) {
                    byte b3 = bArr2[i3];
                    if (b3 >= 65 && b3 <= 90) {
                        bArr2[i3] = (byte) (b3 + 32);
                    }
                }
                return new ByteString(bArr2);
            }
            i2++;
        }
    }

    public ByteString toAsciiUppercase() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i2 >= bArr.length) {
                return this;
            }
            byte b2 = bArr[i2];
            if (b2 >= 97 && b2 <= 122) {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i2] = (byte) (b2 - 32);
                for (int i3 = i2 + 1; i3 < bArr2.length; i3++) {
                    byte b3 = bArr2[i3];
                    if (b3 >= 97 && b3 <= 122) {
                        bArr2[i3] = (byte) (b3 - 32);
                    }
                }
                return new ByteString(bArr2);
            }
            i2++;
        }
    }

    public ByteString substring(int i2) {
        return substring(i2, this.data.length);
    }

    public ByteString substring(int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        byte[] bArr = this.data;
        if (i3 <= bArr.length) {
            int i4 = i3 - i2;
            if (i4 < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex");
            }
            if (i2 == 0 && i3 == bArr.length) {
                return this;
            }
            byte[] bArr2 = new byte[i4];
            System.arraycopy(bArr, i2, bArr2, 0, i4);
            return new ByteString(bArr2);
        }
        throw new IllegalArgumentException("endIndex > length(" + this.data.length + ")");
    }

    public byte getByte(int i2) {
        return this.data[i2];
    }

    public int size() {
        return this.data.length;
    }

    public byte[] toByteArray() {
        return (byte[]) this.data.clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] internalArray() {
        return this.data;
    }

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(this.data).asReadOnlyBuffer();
    }

    public void write(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        outputStream.write(this.data);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void write(Buffer buffer) {
        byte[] bArr = this.data;
        buffer.write(bArr, 0, bArr.length);
    }

    public boolean rangeEquals(int i2, ByteString byteString, int i3, int i4) {
        return byteString.rangeEquals(i3, this.data, i2, i4);
    }

    public boolean rangeEquals(int i2, byte[] bArr, int i3, int i4) {
        if (i2 >= 0) {
            byte[] bArr2 = this.data;
            if (i2 <= bArr2.length - i4 && i3 >= 0 && i3 <= bArr.length - i4 && Util.arrayRangeEquals(bArr2, i2, bArr, i3, i4)) {
                return true;
            }
        }
        return false;
    }

    public final boolean startsWith(ByteString byteString) {
        return rangeEquals(0, byteString, 0, byteString.size());
    }

    public final boolean startsWith(byte[] bArr) {
        return rangeEquals(0, bArr, 0, bArr.length);
    }

    public final boolean endsWith(ByteString byteString) {
        return rangeEquals(size() - byteString.size(), byteString, 0, byteString.size());
    }

    public final boolean endsWith(byte[] bArr) {
        return rangeEquals(size() - bArr.length, bArr, 0, bArr.length);
    }

    public final int indexOf(ByteString byteString) {
        return indexOf(byteString.internalArray(), 0);
    }

    public final int indexOf(ByteString byteString, int i2) {
        return indexOf(byteString.internalArray(), i2);
    }

    public final int indexOf(byte[] bArr) {
        return indexOf(bArr, 0);
    }

    public int indexOf(byte[] bArr, int i2) {
        int length = this.data.length - bArr.length;
        for (int max = Math.max(i2, 0); max <= length; max++) {
            if (Util.arrayRangeEquals(this.data, max, bArr, 0, bArr.length)) {
                return max;
            }
        }
        return -1;
    }

    public final int lastIndexOf(ByteString byteString) {
        return lastIndexOf(byteString.internalArray(), size());
    }

    public final int lastIndexOf(ByteString byteString, int i2) {
        return lastIndexOf(byteString.internalArray(), i2);
    }

    public final int lastIndexOf(byte[] bArr) {
        return lastIndexOf(bArr, size());
    }

    public int lastIndexOf(byte[] bArr, int i2) {
        for (int min = Math.min(i2, this.data.length - bArr.length); min >= 0; min--) {
            if (Util.arrayRangeEquals(this.data, min, bArr, 0, bArr.length)) {
                return min;
            }
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            int size = byteString.size();
            byte[] bArr = this.data;
            if (size == bArr.length && byteString.rangeEquals(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.hashCode;
        if (i2 != 0) {
            return i2;
        }
        int hashCode = Arrays.hashCode(this.data);
        this.hashCode = hashCode;
        return hashCode;
    }

    @Override // java.lang.Comparable
    public int compareTo(ByteString byteString) {
        int size = size();
        int size2 = byteString.size();
        int min = Math.min(size, size2);
        for (int i2 = 0; i2 < min; i2++) {
            int i3 = getByte(i2) & 255;
            int i4 = byteString.getByte(i2) & 255;
            if (i3 != i4) {
                return i3 < i4 ? -1 : 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        return size < size2 ? -1 : 1;
    }

    public String toString() {
        if (this.data.length == 0) {
            return "[size=0]";
        }
        String utf8 = utf8();
        int codePointIndexToCharIndex = codePointIndexToCharIndex(utf8, 64);
        if (codePointIndexToCharIndex == -1) {
            if (this.data.length <= 64) {
                return "[hex=" + hex() + "]";
            }
            return "[size=" + this.data.length + " hex=" + substring(0, 64).hex() + "…]";
        }
        String replace = utf8.substring(0, codePointIndexToCharIndex).replace("\\", "\\\\").replace(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "\\n").replace("\r", "\\r");
        if (codePointIndexToCharIndex < utf8.length()) {
            return "[size=" + this.data.length + " text=" + replace + "…]";
        }
        return "[text=" + replace + "]";
    }

    static int codePointIndexToCharIndex(String str, int i2) {
        int length = str.length();
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            if (i4 == i2) {
                return i3;
            }
            int codePointAt = str.codePointAt(i3);
            if ((Character.isISOControl(codePointAt) && codePointAt != 10 && codePointAt != 13) || codePointAt == 65533) {
                return -1;
            }
            i4++;
            i3 += Character.charCount(codePointAt);
        }
        return str.length();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        ByteString read = read(objectInputStream, objectInputStream.readInt());
        try {
            Field declaredField = ByteString.class.getDeclaredField("data");
            declaredField.setAccessible(true);
            declaredField.set(this, read.data);
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        } catch (NoSuchFieldException unused2) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }
}
