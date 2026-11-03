package com.google.android.gms.common.server.response;

import android.util.Log;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import kotlin.text.Typography;
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes4.dex */
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaa = {'u', 'l', 'l'};
    private static final char[] zab = {'r', 'u', 'e'};
    private static final char[] zac = {'r', 'u', 'e', Typography.quote};
    private static final char[] zad = {'a', 'l', 's', 'e'};
    private static final char[] zae = {'a', 'l', 's', 'e', Typography.quote};
    private static final char[] zaf = {'\n'};
    private static final zai zag = new zaa();
    private static final zai zah = new zab();
    private static final zai zai = new zac();
    private static final zai zaj = new zad();
    private static final zai zak = new zae();
    private static final zai zal = new zaf();
    private static final zai zam = new zag();
    private static final zai zan = new zah();
    private final char[] zao = new char[1];
    private final char[] zap = new char[32];
    private final char[] zaq = new char[1024];
    private final StringBuilder zar = new StringBuilder(32);
    private final StringBuilder zas = new StringBuilder(1024);
    private final Stack zat = new Stack();

    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    /* loaded from: classes4.dex */
    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super("Error instantiating inner object", th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    private static final String zaA(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        sb.setLength(0);
        bufferedReader.mark(cArr.length);
        boolean z = false;
        boolean z2 = false;
        loop0: while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                int i2 = 0;
                while (i2 < read) {
                    char c2 = cArr[i2];
                    if (!Character.isISOControl(c2) || (cArr2 != null && cArr2[0] == c2)) {
                        int i3 = i2 + 1;
                        if (c2 == '\"') {
                            if (!z) {
                                sb.append(cArr, 0, i2);
                                bufferedReader.reset();
                                bufferedReader.skip(i3);
                                return z2 ? JsonUtils.unescapeString(sb.toString()) : sb.toString();
                            }
                        } else if (c2 == '\\') {
                            z = !z;
                            z2 = true;
                            i2 = i3;
                        }
                        z = false;
                        i2 = i3;
                    }
                }
                sb.append(cArr, 0, read);
                bufferedReader.mark(cArr.length);
            } else {
                throw new ParseException("Unexpected EOF while parsing string");
            }
        }
        throw new ParseException("Unexpected control character while reading string");
    }

    private final char zai(BufferedReader bufferedReader) throws ParseException, IOException {
        if (bufferedReader.read(this.zao) != -1) {
            while (Character.isWhitespace(this.zao[0])) {
                if (bufferedReader.read(this.zao) == -1) {
                    return (char) 0;
                }
            }
            return this.zao[0];
        }
        return (char) 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double zaj(BufferedReader bufferedReader) throws ParseException, IOException {
        int zam2 = zam(bufferedReader, this.zaq);
        return zam2 == 0 ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : Double.parseDouble(new String(this.zaq, 0, zam2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float zak(BufferedReader bufferedReader) throws ParseException, IOException {
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zaq, 0, zam2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zal(BufferedReader bufferedReader) throws ParseException, IOException {
        int i2;
        int i3;
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return 0;
        }
        char[] cArr = this.zaq;
        if (zam2 > 0) {
            char c2 = cArr[0];
            int i4 = c2 == '-' ? Integer.MIN_VALUE : -2147483647;
            int i5 = c2 == '-' ? 1 : 0;
            if (i5 < zam2) {
                i3 = i5 + 1;
                int digit = Character.digit(cArr[i5], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                i2 = -digit;
            } else {
                i2 = 0;
                i3 = i5;
            }
            while (i3 < zam2) {
                int i6 = i3 + 1;
                int digit2 = Character.digit(cArr[i3], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (i2 < -214748364) {
                    throw new ParseException("Number too large");
                }
                int i7 = i2 * 10;
                if (i7 < i4 + digit2) {
                    throw new ParseException("Number too large");
                }
                i2 = i7 - digit2;
                i3 = i6;
            }
            if (i5 != 0) {
                if (i3 > 1) {
                    return i2;
                }
                throw new ParseException("No digits to parse");
            }
            return -i2;
        }
        throw new ParseException("No number to parse");
    }

    @ResultIgnorabilityUnspecified
    private final int zam(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i2;
        char zai2 = zai(bufferedReader);
        if (zai2 != 0) {
            if (zai2 != ',') {
                if (zai2 == 'n') {
                    zax(bufferedReader, zaa);
                    return 0;
                }
                bufferedReader.mark(1024);
                if (zai2 == '\"') {
                    i2 = 0;
                    boolean z = false;
                    while (i2 < 1024 && bufferedReader.read(cArr, i2, 1) != -1) {
                        char c2 = cArr[i2];
                        if (Character.isISOControl(c2)) {
                            throw new ParseException("Unexpected control character while reading string");
                        }
                        int i3 = i2 + 1;
                        if (c2 == '\"') {
                            if (!z) {
                                bufferedReader.reset();
                                bufferedReader.skip(i3);
                                return i2;
                            }
                        } else if (c2 == '\\') {
                            z = !z;
                            i2 = i3;
                        }
                        z = false;
                        i2 = i3;
                    }
                } else {
                    cArr[0] = zai2;
                    i2 = 1;
                    while (i2 < 1024 && bufferedReader.read(cArr, i2, 1) != -1) {
                        char c3 = cArr[i2];
                        if (c3 == '}' || c3 == ',' || Character.isWhitespace(c3) || cArr[i2] == ']') {
                            bufferedReader.reset();
                            bufferedReader.skip(i2 - 1);
                            cArr[i2] = 0;
                            return i2;
                        }
                        i2++;
                    }
                }
                if (i2 == 1024) {
                    throw new ParseException("Absurdly long value");
                }
                throw new ParseException("Unexpected EOF");
            }
            throw new ParseException("Missing value");
        }
        throw new ParseException("Unexpected EOF");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zan(BufferedReader bufferedReader) throws ParseException, IOException {
        long j2;
        int i2;
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return 0L;
        }
        char[] cArr = this.zaq;
        if (zam2 > 0) {
            char c2 = cArr[0];
            long j3 = c2 == '-' ? Long.MIN_VALUE : -9223372036854775807L;
            int i3 = c2 == '-' ? 1 : 0;
            if (i3 < zam2) {
                i2 = i3 + 1;
                int digit = Character.digit(cArr[i3], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                j2 = -digit;
            } else {
                j2 = 0;
                i2 = i3;
            }
            while (i2 < zam2) {
                int i4 = i2 + 1;
                int digit2 = Character.digit(cArr[i2], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (j2 < -922337203685477580L) {
                    throw new ParseException("Number too large");
                }
                long j4 = j2 * 10;
                int i5 = zam2;
                long j5 = digit2;
                if (j4 < j3 + j5) {
                    throw new ParseException("Number too large");
                }
                j2 = j4 - j5;
                zam2 = i5;
                i2 = i4;
            }
            if (i3 != 0) {
                if (i2 > 1) {
                    return j2;
                }
                throw new ParseException("No digits to parse");
            }
            return -j2;
        }
        throw new ParseException("No number to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zao(BufferedReader bufferedReader) throws ParseException, IOException {
        return zap(bufferedReader, this.zap, this.zar, null);
    }

    private final String zap(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        char zai2 = zai(bufferedReader);
        if (zai2 != '\"') {
            if (zai2 == 'n') {
                zax(bufferedReader, zaa);
                return null;
            }
            throw new ParseException("Expected string");
        }
        return zaA(bufferedReader, cArr, sb, cArr2);
    }

    @ResultIgnorabilityUnspecified
    private final String zaq(BufferedReader bufferedReader) throws ParseException, IOException {
        this.zat.push(2);
        char zai2 = zai(bufferedReader);
        if (zai2 == '\"') {
            this.zat.push(3);
            String zaA = zaA(bufferedReader, this.zap, this.zar, null);
            zaw(3);
            if (zai(bufferedReader) == ':') {
                return zaA;
            }
            throw new ParseException("Expected key/value separator");
        } else if (zai2 == ']') {
            zaw(2);
            zaw(1);
            zaw(5);
            return null;
        } else if (zai2 == '}') {
            zaw(2);
            return null;
        } else {
            throw new ParseException("Unexpected token: " + zai2);
        }
    }

    private final String zar(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(1024);
        char zai2 = zai(bufferedReader);
        int i2 = 1;
        if (zai2 == '\"') {
            if (bufferedReader.read(this.zao) != -1) {
                char c2 = this.zao[0];
                boolean z = false;
                do {
                    if (c2 == '\"') {
                        if (z) {
                            c2 = '\"';
                            z = true;
                        }
                    }
                    z = c2 == '\\' ? !z : false;
                    if (bufferedReader.read(this.zao) != -1) {
                        c2 = this.zao[0];
                    } else {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                } while (!Character.isISOControl(c2));
                throw new ParseException("Unexpected control character while reading string");
            }
            throw new ParseException("Unexpected EOF while parsing string");
        } else if (zai2 == ',') {
            throw new ParseException("Missing value");
        } else {
            if (zai2 == '[') {
                this.zat.push(5);
                bufferedReader.mark(32);
                if (zai(bufferedReader) == ']') {
                    zaw(5);
                } else {
                    bufferedReader.reset();
                    boolean z2 = false;
                    boolean z3 = false;
                    while (i2 > 0) {
                        char zai3 = zai(bufferedReader);
                        if (zai3 != 0) {
                            if (Character.isISOControl(zai3)) {
                                throw new ParseException("Unexpected control character while reading array");
                            }
                            if (zai3 == '\"') {
                                if (!z3) {
                                    z2 = !z2;
                                }
                                zai3 = '\"';
                            }
                            if (zai3 == '[') {
                                if (!z2) {
                                    i2++;
                                }
                                zai3 = '[';
                            }
                            if (zai3 == ']' && !z2) {
                                i2--;
                            }
                            z3 = (zai3 == '\\' && z2) ? !z3 : false;
                        } else {
                            throw new ParseException("Unexpected EOF while parsing array");
                        }
                    }
                    zaw(5);
                }
            } else if (zai2 == '{') {
                this.zat.push(1);
                bufferedReader.mark(32);
                char zai4 = zai(bufferedReader);
                if (zai4 == '}') {
                    zaw(1);
                } else if (zai4 == '\"') {
                    bufferedReader.reset();
                    zaq(bufferedReader);
                    do {
                    } while (zar(bufferedReader) != null);
                    zaw(1);
                } else {
                    throw new ParseException("Unexpected token " + zai4);
                }
            } else {
                bufferedReader.reset();
                zam(bufferedReader, this.zaq);
            }
        }
        char zai5 = zai(bufferedReader);
        if (zai5 == ',') {
            zaw(2);
            return zaq(bufferedReader);
        } else if (zai5 == '}') {
            zaw(2);
            return null;
        } else {
            throw new ParseException("Unexpected token " + zai5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigDecimal zas(BufferedReader bufferedReader) throws ParseException, IOException {
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zaq, 0, zam2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigInteger zat(BufferedReader bufferedReader) throws ParseException, IOException {
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return null;
        }
        return new BigInteger(new String(this.zaq, 0, zam2));
    }

    private final ArrayList zau(BufferedReader bufferedReader, zai zaiVar) throws ParseException, IOException {
        char zai2 = zai(bufferedReader);
        if (zai2 == 'n') {
            zax(bufferedReader, zaa);
            return null;
        } else if (zai2 != '[') {
            throw new ParseException("Expected start of array");
        } else {
            this.zat.push(5);
            ArrayList arrayList = new ArrayList();
            while (true) {
                bufferedReader.mark(1024);
                char zai3 = zai(bufferedReader);
                if (zai3 == 0) {
                    throw new ParseException("Unexpected EOF");
                }
                if (zai3 != ',') {
                    if (zai3 != ']') {
                        bufferedReader.reset();
                        arrayList.add(zaiVar.zaa(this, bufferedReader));
                    } else {
                        zaw(5);
                        return arrayList;
                    }
                }
            }
        }
    }

    private final ArrayList zav(BufferedReader bufferedReader, FastJsonResponse.Field field) throws ParseException, IOException {
        ArrayList arrayList = new ArrayList();
        char zai2 = zai(bufferedReader);
        if (zai2 == ']') {
            zaw(5);
            return arrayList;
        } else if (zai2 == 'n') {
            zax(bufferedReader, zaa);
            zaw(5);
            return null;
        } else if (zai2 == '{') {
            this.zat.push(1);
            while (true) {
                try {
                    FastJsonResponse zad2 = field.zad();
                    if (!zaz(bufferedReader, zad2)) {
                        return arrayList;
                    }
                    arrayList.add(zad2);
                    char zai3 = zai(bufferedReader);
                    if (zai3 != ',') {
                        if (zai3 == ']') {
                            zaw(5);
                            return arrayList;
                        }
                        throw new ParseException("Unexpected token: " + zai3);
                    } else if (zai(bufferedReader) == '{') {
                        this.zat.push(1);
                    } else {
                        throw new ParseException("Expected start of next object in array");
                    }
                } catch (IllegalAccessException e2) {
                    throw new ParseException("Error instantiating inner object", e2);
                } catch (InstantiationException e3) {
                    throw new ParseException("Error instantiating inner object", e3);
                }
            }
        } else {
            throw new ParseException("Unexpected token: " + zai2);
        }
    }

    private final void zaw(int i2) throws ParseException {
        if (this.zat.isEmpty()) {
            throw new ParseException("Expected state " + i2 + " but had empty stack");
        }
        int intValue = ((Integer) this.zat.pop()).intValue();
        if (intValue == i2) {
            return;
        }
        throw new ParseException("Expected state " + i2 + " but had " + intValue);
    }

    private final void zax(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i2 = 0;
        while (true) {
            int length = cArr.length;
            if (i2 >= length) {
                return;
            }
            int read = bufferedReader.read(this.zap, 0, length - i2);
            if (read == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i3 = 0; i3 < read; i3++) {
                if (cArr[i3 + i2] != this.zap[i3]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i2 += read;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zay(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        char[] cArr;
        char zai2 = zai(bufferedReader);
        if (zai2 == '\"') {
            if (z) {
                throw new ParseException("No boolean value found in string");
            }
            return zay(bufferedReader, true);
        } else if (zai2 == 'f') {
            if (z) {
                cArr = zae;
            } else {
                cArr = zad;
            }
            zax(bufferedReader, cArr);
            return false;
        } else if (zai2 == 'n') {
            zax(bufferedReader, zaa);
            return false;
        } else if (zai2 == 't') {
            zax(bufferedReader, z ? zac : zab);
            return true;
        } else {
            throw new ParseException("Unexpected token: " + zai2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ResultIgnorabilityUnspecified
    private final boolean zaz(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        int i2;
        HashMap hashMap;
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        String zaq = zaq(bufferedReader);
        if (zaq != null) {
            while (zaq != null) {
                FastJsonResponse.Field<?, ?> field = fieldMappings.get(zaq);
                if (field == null) {
                    zaq = zar(bufferedReader);
                } else {
                    this.zat.push(4);
                    int i3 = field.zaa;
                    switch (i3) {
                        case 0:
                            if (field.zab) {
                                fastJsonResponse.zav(field, zau(bufferedReader, zag));
                            } else {
                                fastJsonResponse.zau(field, zal(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 1:
                            if (field.zab) {
                                fastJsonResponse.zag(field, zau(bufferedReader, zam));
                            } else {
                                fastJsonResponse.zae(field, zat(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 2:
                            if (field.zab) {
                                fastJsonResponse.zay(field, zau(bufferedReader, zah));
                            } else {
                                fastJsonResponse.zax(field, zan(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 3:
                            if (field.zab) {
                                fastJsonResponse.zas(field, zau(bufferedReader, zai));
                            } else {
                                fastJsonResponse.zaq(field, zak(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 4:
                            if (field.zab) {
                                fastJsonResponse.zao(field, zau(bufferedReader, zaj));
                            } else {
                                fastJsonResponse.zam(field, zaj(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 5:
                            if (field.zab) {
                                fastJsonResponse.zac(field, zau(bufferedReader, zan));
                            } else {
                                fastJsonResponse.zaa(field, zas(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 6:
                            if (field.zab) {
                                fastJsonResponse.zaj(field, zau(bufferedReader, zak));
                            } else {
                                fastJsonResponse.zai(field, zay(bufferedReader, false));
                            }
                            i2 = 4;
                            break;
                        case 7:
                            if (field.zab) {
                                fastJsonResponse.zaC(field, zau(bufferedReader, zal));
                            } else {
                                fastJsonResponse.zaA(field, zao(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 8:
                            fastJsonResponse.zal(field, Base64Utils.decode(zap(bufferedReader, this.zaq, this.zas, zaf)));
                            i2 = 4;
                            break;
                        case 9:
                            fastJsonResponse.zal(field, Base64Utils.decodeUrlSafe(zap(bufferedReader, this.zaq, this.zas, zaf)));
                            i2 = 4;
                            break;
                        case 10:
                            char zai2 = zai(bufferedReader);
                            if (zai2 == 'n') {
                                zax(bufferedReader, zaa);
                                hashMap = null;
                            } else if (zai2 == '{') {
                                this.zat.push(1);
                                hashMap = new HashMap();
                                while (true) {
                                    char zai3 = zai(bufferedReader);
                                    if (zai3 != 0) {
                                        if (zai3 == '\"') {
                                            String zaA = zaA(bufferedReader, this.zap, this.zar, null);
                                            if (zai(bufferedReader) == ':') {
                                                if (zai(bufferedReader) == '\"') {
                                                    hashMap.put(zaA, zaA(bufferedReader, this.zap, this.zar, null));
                                                    char zai4 = zai(bufferedReader);
                                                    if (zai4 != ',') {
                                                        if (zai4 == '}') {
                                                            zaw(1);
                                                        } else {
                                                            throw new ParseException("Unexpected character while parsing string map: " + zai4);
                                                        }
                                                    }
                                                } else {
                                                    throw new ParseException("Expected String value for key ".concat(String.valueOf(zaA)));
                                                }
                                            } else {
                                                throw new ParseException("No map value found for key ".concat(String.valueOf(zaA)));
                                            }
                                        } else if (zai3 == '}') {
                                            zaw(1);
                                        }
                                        i2 = 4;
                                        break;
                                    } else {
                                        throw new ParseException("Unexpected EOF");
                                    }
                                }
                            } else {
                                throw new ParseException("Expected start of a map object");
                            }
                            fastJsonResponse.zaB(field, hashMap);
                            i2 = 4;
                        case 11:
                            if (field.zab) {
                                char zai5 = zai(bufferedReader);
                                if (zai5 == 'n') {
                                    zax(bufferedReader, zaa);
                                    fastJsonResponse.addConcreteTypeArrayInternal(field, field.zae, null);
                                } else {
                                    this.zat.push(5);
                                    if (zai5 == '[') {
                                        fastJsonResponse.addConcreteTypeArrayInternal(field, field.zae, zav(bufferedReader, field));
                                    } else {
                                        throw new ParseException("Expected array start");
                                    }
                                }
                            } else {
                                char zai6 = zai(bufferedReader);
                                if (zai6 == 'n') {
                                    zax(bufferedReader, zaa);
                                    fastJsonResponse.addConcreteTypeInternal(field, field.zae, null);
                                } else {
                                    this.zat.push(1);
                                    if (zai6 == '{') {
                                        try {
                                            FastJsonResponse zad2 = field.zad();
                                            zaz(bufferedReader, zad2);
                                            fastJsonResponse.addConcreteTypeInternal(field, field.zae, zad2);
                                        } catch (IllegalAccessException e2) {
                                            throw new ParseException("Error instantiating inner object", e2);
                                        } catch (InstantiationException e3) {
                                            throw new ParseException("Error instantiating inner object", e3);
                                        }
                                    } else {
                                        throw new ParseException("Expected start of object");
                                    }
                                }
                            }
                            i2 = 4;
                            break;
                        default:
                            throw new ParseException("Invalid field type " + i3);
                    }
                    zaw(i2);
                    zaw(2);
                    char zai7 = zai(bufferedReader);
                    if (zai7 == ',') {
                        zaq = zaq(bufferedReader);
                    } else if (zai7 != '}') {
                        throw new ParseException("Expected end of object or field separator, but found: " + zai7);
                    } else {
                        zaq = null;
                    }
                }
            }
            zaw(1);
            return true;
        }
        zaw(1);
        return false;
    }

    public void parse(InputStream inputStream, T t) throws ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            try {
                this.zat.push(0);
                char zai2 = zai(bufferedReader);
                if (zai2 != 0) {
                    if (zai2 == '[') {
                        this.zat.push(5);
                        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t.getFieldMappings();
                        if (fieldMappings.size() != 1) {
                            throw new ParseException("Object array response class must have a single Field");
                        }
                        FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                        t.addConcreteTypeArrayInternal(value, value.zae, zav(bufferedReader, value));
                    } else if (zai2 == '{') {
                        this.zat.push(1);
                        zaz(bufferedReader, t);
                    } else {
                        throw new ParseException("Unexpected token: " + zai2);
                    }
                    zaw(0);
                    try {
                        bufferedReader.close();
                        return;
                    } catch (IOException unused) {
                        Log.w("FastParser", "Failed to close reader while parsing.");
                        return;
                    }
                }
                throw new ParseException("No data to parse");
            } catch (IOException e2) {
                throw new ParseException(e2);
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException unused2) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
            throw th;
        }
    }
}
