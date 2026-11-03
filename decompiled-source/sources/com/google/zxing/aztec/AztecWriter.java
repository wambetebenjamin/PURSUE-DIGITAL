package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.aztec.encoder.AztecCode;
import com.google.zxing.aztec.encoder.Encoder;
import com.google.zxing.common.BitMatrix;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
/* loaded from: classes9.dex */
public final class AztecWriter implements Writer {
    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i2, int i3) {
        return encode(str, barcodeFormat, i2, i3, null);
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i2, int i3, Map<EncodeHintType, ?> map) {
        Charset charset = StandardCharsets.ISO_8859_1;
        int i4 = 0;
        if (map != null) {
            if (map.containsKey(EncodeHintType.CHARACTER_SET)) {
                charset = Charset.forName(map.get(EncodeHintType.CHARACTER_SET).toString());
            }
            r1 = map.containsKey(EncodeHintType.ERROR_CORRECTION) ? Integer.parseInt(map.get(EncodeHintType.ERROR_CORRECTION).toString()) : 33;
            if (map.containsKey(EncodeHintType.AZTEC_LAYERS)) {
                i4 = Integer.parseInt(map.get(EncodeHintType.AZTEC_LAYERS).toString());
            }
        }
        return encode(str, barcodeFormat, i2, i3, charset, r1, i4);
    }

    private static BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i2, int i3, Charset charset, int i4, int i5) {
        if (barcodeFormat != BarcodeFormat.AZTEC) {
            throw new IllegalArgumentException("Can only encode AZTEC, but got ".concat(String.valueOf(barcodeFormat)));
        }
        return renderResult(Encoder.encode(str.getBytes(charset), i4, i5), i2, i3);
    }

    private static BitMatrix renderResult(AztecCode aztecCode, int i2, int i3) {
        BitMatrix matrix = aztecCode.getMatrix();
        if (matrix == null) {
            throw new IllegalStateException();
        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int max = Math.max(i2, width);
        int max2 = Math.max(i3, height);
        int min = Math.min(max / width, max2 / height);
        int i4 = (max - (width * min)) / 2;
        int i5 = (max2 - (height * min)) / 2;
        BitMatrix bitMatrix = new BitMatrix(max, max2);
        int i6 = 0;
        while (i6 < height) {
            int i7 = 0;
            int i8 = i4;
            while (i7 < width) {
                if (matrix.get(i7, i6)) {
                    bitMatrix.setRegion(i8, i5, min, min);
                }
                i7++;
                i8 += min;
            }
            i6++;
            i5 += min;
        }
        return bitMatrix;
    }
}
