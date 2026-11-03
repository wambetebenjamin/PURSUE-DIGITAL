package com.airbnb.lottie.parser;

import android.graphics.Color;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class GradientColorParser implements ValueParser<GradientColor> {
    private int colorPoints;

    public GradientColorParser(int i2) {
        this.colorPoints = i2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.parser.ValueParser
    public GradientColor parse(JsonReader jsonReader, float f2) throws IOException {
        ArrayList arrayList = new ArrayList();
        boolean z = jsonReader.peek() == JsonReader.Token.BEGIN_ARRAY;
        if (z) {
            jsonReader.beginArray();
        }
        while (jsonReader.hasNext()) {
            arrayList.add(Float.valueOf((float) jsonReader.nextDouble()));
        }
        if (arrayList.size() == 4 && arrayList.get(0).floatValue() == 1.0f) {
            arrayList.set(0, Float.valueOf(0.0f));
            arrayList.add(Float.valueOf(1.0f));
            arrayList.add(arrayList.get(1));
            arrayList.add(arrayList.get(2));
            arrayList.add(arrayList.get(3));
            this.colorPoints = 2;
        }
        if (z) {
            jsonReader.endArray();
        }
        if (this.colorPoints == -1) {
            this.colorPoints = arrayList.size() / 4;
        }
        int i2 = this.colorPoints;
        float[] fArr = new float[i2];
        int[] iArr = new int[i2];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < this.colorPoints * 4; i5++) {
            int i6 = i5 / 4;
            double floatValue = arrayList.get(i5).floatValue();
            int i7 = i5 % 4;
            if (i7 == 0) {
                if (i6 > 0) {
                    float f3 = (float) floatValue;
                    if (fArr[i6 - 1] >= f3) {
                        fArr[i6] = f3 + 0.01f;
                    }
                }
                fArr[i6] = (float) floatValue;
            } else if (i7 == 1) {
                i3 = (int) (floatValue * 255.0d);
            } else if (i7 == 2) {
                i4 = (int) (floatValue * 255.0d);
            } else if (i7 == 3) {
                iArr[i6] = Color.argb(255, i3, i4, (int) (floatValue * 255.0d));
            }
        }
        return addOpacityStopsToGradientIfNeeded(new GradientColor(fArr, iArr), arrayList);
    }

    private GradientColor addOpacityStopsToGradientIfNeeded(GradientColor gradientColor, List<Float> list) {
        int i2 = this.colorPoints * 4;
        if (list.size() <= i2) {
            return gradientColor;
        }
        float[] positions = gradientColor.getPositions();
        int[] colors = gradientColor.getColors();
        int size = (list.size() - i2) / 2;
        float[] fArr = new float[size];
        float[] fArr2 = new float[size];
        int i3 = 0;
        while (i2 < list.size()) {
            if (i2 % 2 == 0) {
                fArr[i3] = list.get(i2).floatValue();
            } else {
                fArr2[i3] = list.get(i2).floatValue();
                i3++;
            }
            i2++;
        }
        float[] mergeUniqueElements = mergeUniqueElements(gradientColor.getPositions(), fArr);
        int length = mergeUniqueElements.length;
        int[] iArr = new int[length];
        for (int i4 = 0; i4 < length; i4++) {
            float f2 = mergeUniqueElements[i4];
            int binarySearch = Arrays.binarySearch(positions, f2);
            int binarySearch2 = Arrays.binarySearch(fArr, f2);
            if (binarySearch < 0 || binarySearch2 > 0) {
                if (binarySearch2 < 0) {
                    binarySearch2 = -(binarySearch2 + 1);
                }
                iArr[i4] = getColorInBetweenColorStops(f2, fArr2[binarySearch2], positions, colors);
            } else {
                iArr[i4] = getColorInBetweenOpacityStops(f2, colors[binarySearch], fArr, fArr2);
            }
        }
        return new GradientColor(mergeUniqueElements, iArr);
    }

    private int getColorInBetweenColorStops(float f2, float f3, float[] fArr, int[] iArr) {
        if (iArr.length < 2 || f2 == fArr[0]) {
            return iArr[0];
        }
        for (int i2 = 1; i2 < fArr.length; i2++) {
            if (fArr[i2] >= f2 || i2 == fArr.length - 1) {
                int i3 = i2 - 1;
                float f4 = (f2 - fArr[i3]) / (fArr[i2] - fArr[i3]);
                int i4 = iArr[i2];
                int i5 = iArr[i3];
                return Color.argb((int) (f3 * 255.0f), MiscUtils.lerp(Color.red(i5), Color.red(i4), f4), MiscUtils.lerp(Color.green(i5), Color.green(i4), f4), MiscUtils.lerp(Color.blue(i5), Color.blue(i4), f4));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    private int getColorInBetweenOpacityStops(float f2, int i2, float[] fArr, float[] fArr2) {
        float lerp;
        if (fArr2.length < 2 || f2 <= fArr[0]) {
            return Color.argb((int) (fArr2[0] * 255.0f), Color.red(i2), Color.green(i2), Color.blue(i2));
        }
        for (int i3 = 1; i3 < fArr.length; i3++) {
            int i4 = (fArr[i3] > f2 ? 1 : (fArr[i3] == f2 ? 0 : -1));
            if (i4 >= 0 || i3 == fArr.length - 1) {
                if (i4 <= 0) {
                    lerp = fArr2[i3];
                } else {
                    int i5 = i3 - 1;
                    lerp = MiscUtils.lerp(fArr2[i5], fArr2[i3], (f2 - fArr[i5]) / (fArr[i3] - fArr[i5]));
                }
                return Color.argb((int) (lerp * 255.0f), Color.red(i2), Color.green(i2), Color.blue(i2));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    protected static float[] mergeUniqueElements(float[] fArr, float[] fArr2) {
        if (fArr.length == 0) {
            return fArr2;
        }
        if (fArr2.length == 0) {
            return fArr;
        }
        int length = fArr.length + fArr2.length;
        float[] fArr3 = new float[length];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            float f2 = i3 < fArr.length ? fArr[i3] : Float.NaN;
            float f3 = i4 < fArr2.length ? fArr2[i4] : Float.NaN;
            if (Float.isNaN(f3) || f2 < f3) {
                fArr3[i5] = f2;
                i3++;
            } else if (Float.isNaN(f2) || f3 < f2) {
                fArr3[i5] = f3;
                i4++;
            } else {
                fArr3[i5] = f2;
                i3++;
                i4++;
                i2++;
            }
        }
        return i2 == 0 ? fArr3 : Arrays.copyOf(fArr3, length - i2);
    }
}
