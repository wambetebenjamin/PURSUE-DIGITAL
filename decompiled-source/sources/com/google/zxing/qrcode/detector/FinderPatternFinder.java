package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    protected static final int MAX_MODULES = 97;
    protected static final int MIN_SKIP = 3;
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;
    private final ResultPointCallback resultPointCallback;

    public FinderPatternFinder(BitMatrix bitMatrix) {
        this(bitMatrix, null);
    }

    public FinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BitMatrix getImage() {
        return this.image;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List<FinderPattern> getPossibleCenters() {
        return this.possibleCenters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final FinderPatternInfo find(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i2 = (height * 3) / 388;
        if (i2 < 3 || z) {
            i2 = 3;
        }
        int[] iArr = new int[5];
        int i3 = i2 - 1;
        boolean z2 = false;
        while (i3 < height && !z2) {
            clearCounts(iArr);
            int i4 = 0;
            int i5 = 0;
            while (i4 < width) {
                if (this.image.get(i4, i3)) {
                    if ((i5 & 1) == 1) {
                        i5++;
                    }
                    iArr[i5] = iArr[i5] + 1;
                } else if ((i5 & 1) != 0) {
                    iArr[i5] = iArr[i5] + 1;
                } else if (i5 == 4) {
                    if (foundPatternCross(iArr)) {
                        if (handlePossibleCenter(iArr, i3, i4)) {
                            if (this.hasSkipped) {
                                z2 = haveMultiplyConfirmedCenters();
                            } else {
                                int findRowSkip = findRowSkip();
                                if (findRowSkip > iArr[2]) {
                                    i3 += (findRowSkip - iArr[2]) - 2;
                                    i4 = width - 1;
                                }
                            }
                            clearCounts(iArr);
                            i5 = 0;
                            i2 = 2;
                        } else {
                            shiftCounts2(iArr);
                        }
                    } else {
                        shiftCounts2(iArr);
                    }
                    i5 = 3;
                } else {
                    i5++;
                    iArr[i5] = iArr[i5] + 1;
                }
                i4++;
            }
            if (foundPatternCross(iArr) && handlePossibleCenter(iArr, i3, width)) {
                i2 = iArr[0];
                if (this.hasSkipped) {
                    z2 = haveMultiplyConfirmedCenters();
                }
            }
            i3 += i2;
        }
        FinderPattern[] selectBestPatterns = selectBestPatterns();
        ResultPoint.orderBestPatterns(selectBestPatterns);
        return new FinderPatternInfo(selectBestPatterns);
    }

    private static float centerFromEnd(int[] iArr, int i2) {
        return ((i2 - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean foundPatternCross(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 5; i3++) {
            int i4 = iArr[i3];
            if (i4 == 0) {
                return false;
            }
            i2 += i4;
        }
        if (i2 < 7) {
            return false;
        }
        float f2 = i2 / 7.0f;
        float f3 = f2 / 2.0f;
        return Math.abs(f2 - ((float) iArr[0])) < f3 && Math.abs(f2 - ((float) iArr[1])) < f3 && Math.abs((f2 * 3.0f) - ((float) iArr[2])) < 3.0f * f3 && Math.abs(f2 - ((float) iArr[3])) < f3 && Math.abs(f2 - ((float) iArr[4])) < f3;
    }

    protected static boolean foundPatternDiagonal(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 5; i3++) {
            int i4 = iArr[i3];
            if (i4 == 0) {
                return false;
            }
            i2 += i4;
        }
        if (i2 < 7) {
            return false;
        }
        float f2 = i2 / 7.0f;
        float f3 = f2 / 1.333f;
        return Math.abs(f2 - ((float) iArr[0])) < f3 && Math.abs(f2 - ((float) iArr[1])) < f3 && Math.abs((f2 * 3.0f) - ((float) iArr[2])) < 3.0f * f3 && Math.abs(f2 - ((float) iArr[3])) < f3 && Math.abs(f2 - ((float) iArr[4])) < f3;
    }

    private int[] getCrossCheckStateCount() {
        clearCounts(this.crossCheckStateCount);
        return this.crossCheckStateCount;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void clearCounts(int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void shiftCounts2(int[] iArr) {
        iArr[0] = iArr[2];
        iArr[1] = iArr[3];
        iArr[2] = iArr[4];
        iArr[3] = 1;
        iArr[4] = 0;
    }

    private boolean crossCheckDiagonal(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i7 = 0;
        while (i2 >= i7 && i3 >= i7 && this.image.get(i3 - i7, i2 - i7)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i7++;
        }
        if (crossCheckStateCount[2] == 0) {
            return false;
        }
        while (i2 >= i7 && i3 >= i7 && !this.image.get(i3 - i7, i2 - i7)) {
            crossCheckStateCount[1] = crossCheckStateCount[1] + 1;
            i7++;
        }
        if (crossCheckStateCount[1] == 0) {
            return false;
        }
        while (i2 >= i7 && i3 >= i7 && this.image.get(i3 - i7, i2 - i7)) {
            crossCheckStateCount[0] = crossCheckStateCount[0] + 1;
            i7++;
        }
        if (crossCheckStateCount[0] == 0) {
            return false;
        }
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i8 = 1;
        while (true) {
            int i9 = i2 + i8;
            if (i9 >= height || (i6 = i3 + i8) >= width || !this.image.get(i6, i9)) {
                break;
            }
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i8++;
        }
        while (true) {
            int i10 = i2 + i8;
            if (i10 >= height || (i5 = i3 + i8) >= width || this.image.get(i5, i10)) {
                break;
            }
            crossCheckStateCount[3] = crossCheckStateCount[3] + 1;
            i8++;
        }
        if (crossCheckStateCount[3] == 0) {
            return false;
        }
        while (true) {
            int i11 = i2 + i8;
            if (i11 >= height || (i4 = i3 + i8) >= width || !this.image.get(i4, i11)) {
                break;
            }
            crossCheckStateCount[4] = crossCheckStateCount[4] + 1;
            i8++;
        }
        if (crossCheckStateCount[4] == 0) {
            return false;
        }
        return foundPatternDiagonal(crossCheckStateCount);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
        if (r2[1] <= r13) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
        if (r3 < 0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0045, code lost:
        if (r0.get(r12, r3) == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r2[0] > r13) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004b, code lost:
        r2[0] = r2[0] + 1;
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0055, code lost:
        if (r2[0] <= r13) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0057, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0058, code lost:
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0059, code lost:
        if (r11 >= r1) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005f, code lost:
        if (r0.get(r12, r11) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0061, code lost:
        r2[2] = r2[2] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0069, code lost:
        if (r11 != r1) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006b, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x006d, code lost:
        if (r11 >= r1) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0073, code lost:
        if (r0.get(r12, r11) != false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0077, code lost:
        if (r2[3] >= r13) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0079, code lost:
        r2[3] = r2[3] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0081, code lost:
        if (r11 == r1) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0085, code lost:
        if (r2[3] < r13) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0089, code lost:
        if (r11 >= r1) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008f, code lost:
        if (r0.get(r12, r11) == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0093, code lost:
        if (r2[4] >= r13) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0095, code lost:
        r2[4] = r2[4] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x009f, code lost:
        if (r2[4] < r13) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00a1, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00b8, code lost:
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r2[4]) - r14) * 5) < (r14 * 2)) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ba, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00bf, code lost:
        if (foundPatternCross(r2) == false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00c5, code lost:
        return centerFromEnd(r2, r11);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float crossCheckVertical(int r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 199
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.crossCheckVertical(int, int, int, int):float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
        if (r2[1] <= r13) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
        if (r3 < 0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0045, code lost:
        if (r0.get(r3, r12) == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r2[0] > r13) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004b, code lost:
        r2[0] = r2[0] + 1;
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0055, code lost:
        if (r2[0] <= r13) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0057, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0058, code lost:
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0059, code lost:
        if (r11 >= r1) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005f, code lost:
        if (r0.get(r11, r12) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0061, code lost:
        r2[2] = r2[2] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0069, code lost:
        if (r11 != r1) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006b, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x006d, code lost:
        if (r11 >= r1) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0073, code lost:
        if (r0.get(r11, r12) != false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0077, code lost:
        if (r2[3] >= r13) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0079, code lost:
        r2[3] = r2[3] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0081, code lost:
        if (r11 == r1) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0085, code lost:
        if (r2[3] < r13) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0089, code lost:
        if (r11 >= r1) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008f, code lost:
        if (r0.get(r11, r12) == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0093, code lost:
        if (r2[4] >= r13) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0095, code lost:
        r2[4] = r2[4] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x009f, code lost:
        if (r2[4] < r13) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00a1, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00b7, code lost:
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r2[4]) - r14) * 5) < r14) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00b9, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00be, code lost:
        if (foundPatternCross(r2) == false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00c4, code lost:
        return centerFromEnd(r2, r11);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float crossCheckHorizontal(int r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 198
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.crossCheckHorizontal(int, int, int, int):float");
    }

    @Deprecated
    protected final boolean handlePossibleCenter(int[] iArr, int i2, int i3, boolean z) {
        return handlePossibleCenter(iArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean handlePossibleCenter(int[] iArr, int i2, int i3) {
        boolean z = false;
        int i4 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int centerFromEnd = (int) centerFromEnd(iArr, i3);
        float crossCheckVertical = crossCheckVertical(i2, centerFromEnd, iArr[2], i4);
        if (!Float.isNaN(crossCheckVertical)) {
            int i5 = (int) crossCheckVertical;
            float crossCheckHorizontal = crossCheckHorizontal(centerFromEnd, i5, iArr[2], i4);
            if (!Float.isNaN(crossCheckHorizontal) && crossCheckDiagonal(i5, (int) crossCheckHorizontal)) {
                float f2 = i4 / 7.0f;
                int i6 = 0;
                while (true) {
                    if (i6 >= this.possibleCenters.size()) {
                        break;
                    }
                    FinderPattern finderPattern = this.possibleCenters.get(i6);
                    if (finderPattern.aboutEquals(f2, crossCheckVertical, crossCheckHorizontal)) {
                        this.possibleCenters.set(i6, finderPattern.combineEstimate(crossCheckVertical, crossCheckHorizontal, f2));
                        z = true;
                        break;
                    }
                    i6++;
                }
                if (!z) {
                    FinderPattern finderPattern2 = new FinderPattern(crossCheckHorizontal, crossCheckVertical, f2);
                    this.possibleCenters.add(finderPattern2);
                    ResultPointCallback resultPointCallback = this.resultPointCallback;
                    if (resultPointCallback != null) {
                        resultPointCallback.foundPossibleResultPoint(finderPattern2);
                    }
                }
                return true;
            }
        }
        return false;
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        FinderPattern finderPattern = null;
        for (FinderPattern finderPattern2 : this.possibleCenters) {
            if (finderPattern2.getCount() >= 2) {
                if (finderPattern != null) {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(finderPattern.getX() - finderPattern2.getX()) - Math.abs(finderPattern.getY() - finderPattern2.getY()))) / 2;
                }
                finderPattern = finderPattern2;
            }
        }
        return 0;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int size = this.possibleCenters.size();
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i2 = 0;
        for (FinderPattern finderPattern : this.possibleCenters) {
            if (finderPattern.getCount() >= 2) {
                i2++;
                f3 += finderPattern.getEstimatedModuleSize();
            }
        }
        if (i2 < 3) {
            return false;
        }
        float f4 = f3 / size;
        for (FinderPattern finderPattern2 : this.possibleCenters) {
            f2 += Math.abs(finderPattern2.getEstimatedModuleSize() - f4);
        }
        return f2 <= f3 * 0.05f;
    }

    private FinderPattern[] selectBestPatterns() throws NotFoundException {
        float f2;
        int size = this.possibleCenters.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        float f3 = 0.0f;
        if (size > 3) {
            float f4 = 0.0f;
            float f5 = 0.0f;
            for (FinderPattern finderPattern : this.possibleCenters) {
                float estimatedModuleSize = finderPattern.getEstimatedModuleSize();
                f4 += estimatedModuleSize;
                f5 += estimatedModuleSize * estimatedModuleSize;
            }
            float f6 = f4 / size;
            float sqrt = (float) Math.sqrt((f5 / f2) - (f6 * f6));
            Collections.sort(this.possibleCenters, new FurthestFromAverageComparator(f6));
            float max = Math.max(0.2f * f6, sqrt);
            int i2 = 0;
            while (i2 < this.possibleCenters.size() && this.possibleCenters.size() > 3) {
                if (Math.abs(this.possibleCenters.get(i2).getEstimatedModuleSize() - f6) > max) {
                    this.possibleCenters.remove(i2);
                    i2--;
                }
                i2++;
            }
        }
        if (this.possibleCenters.size() > 3) {
            for (FinderPattern finderPattern2 : this.possibleCenters) {
                f3 += finderPattern2.getEstimatedModuleSize();
            }
            Collections.sort(this.possibleCenters, new CenterComparator(f3 / this.possibleCenters.size()));
            List<FinderPattern> list = this.possibleCenters;
            list.subList(3, list.size()).clear();
        }
        return new FinderPattern[]{this.possibleCenters.get(0), this.possibleCenters.get(1), this.possibleCenters.get(2)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class FurthestFromAverageComparator implements Serializable, Comparator<FinderPattern> {
        private final float average;

        private FurthestFromAverageComparator(float f2) {
            this.average = f2;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            return Float.compare(Math.abs(finderPattern2.getEstimatedModuleSize() - this.average), Math.abs(finderPattern.getEstimatedModuleSize() - this.average));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class CenterComparator implements Serializable, Comparator<FinderPattern> {
        private final float average;

        private CenterComparator(float f2) {
            this.average = f2;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            int compare = Integer.compare(finderPattern2.getCount(), finderPattern.getCount());
            return compare == 0 ? Float.compare(Math.abs(finderPattern.getEstimatedModuleSize() - this.average), Math.abs(finderPattern2.getEstimatedModuleSize() - this.average)) : compare;
        }
    }
}
