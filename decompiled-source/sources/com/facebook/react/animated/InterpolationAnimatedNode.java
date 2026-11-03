package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes8.dex */
class InterpolationAnimatedNode extends ValueAnimatedNode {
    public static final String EXTRAPOLATE_TYPE_CLAMP = "clamp";
    public static final String EXTRAPOLATE_TYPE_EXTEND = "extend";
    public static final String EXTRAPOLATE_TYPE_IDENTITY = "identity";
    private final String mExtrapolateLeft;
    private final String mExtrapolateRight;
    private final boolean mHasStringOutput;
    private final double[] mInputRange;
    private int mNumVals;
    private final double[] mOutputRange;
    private double[][] mOutputs;
    private ValueAnimatedNode mParent;
    private String mPattern;
    private final Matcher mSOutputMatcher;
    private boolean mShouldRound;
    private static final String fpRegex = "[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?";
    private static final Pattern fpPattern = Pattern.compile(fpRegex);

    private static double[] fromDoubleArray(ReadableArray readableArray) {
        int size = readableArray.size();
        double[] dArr = new double[size];
        for (int i2 = 0; i2 < size; i2++) {
            dArr[i2] = readableArray.getDouble(i2);
        }
        return dArr;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0072, code lost:
        if (r24.equals(com.facebook.react.animated.InterpolationAnimatedNode.EXTRAPOLATE_TYPE_CLAMP) == false) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ae A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static double interpolate(double r13, double r15, double r17, double r19, double r21, java.lang.String r23, java.lang.String r24) {
        /*
            r0 = r23
            r1 = r24
            int r2 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            java.lang.String r3 = "Invalid extrapolation type "
            r4 = 2
            java.lang.String r5 = "clamp"
            r6 = 1
            java.lang.String r7 = "identity"
            r8 = 0
            java.lang.String r9 = "extend"
            r10 = -1
            if (r2 >= 0) goto L5d
            r23.hashCode()
            int r11 = r23.hashCode()
            switch(r11) {
                case -1289044198: goto L32;
                case -135761730: goto L29;
                case 94742715: goto L20;
                default: goto L1e;
            }
        L1e:
            r11 = r10
            goto L3a
        L20:
            boolean r11 = r0.equals(r5)
            if (r11 != 0) goto L27
            goto L1e
        L27:
            r11 = r4
            goto L3a
        L29:
            boolean r11 = r0.equals(r7)
            if (r11 != 0) goto L30
            goto L1e
        L30:
            r11 = r6
            goto L3a
        L32:
            boolean r11 = r0.equals(r9)
            if (r11 != 0) goto L39
            goto L1e
        L39:
            r11 = r8
        L3a:
            switch(r11) {
                case 0: goto L5d;
                case 1: goto L5c;
                case 2: goto L5a;
                default: goto L3d;
            }
        L3d:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r1 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r2 = "for left extrapolation"
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L5a:
            r11 = r15
            goto L5e
        L5c:
            return r13
        L5d:
            r11 = r13
        L5e:
            int r0 = (r11 > r17 ? 1 : (r11 == r17 ? 0 : -1))
            if (r0 <= 0) goto Laa
            r24.hashCode()
            int r0 = r24.hashCode()
            switch(r0) {
                case -1289044198: goto L7e;
                case -135761730: goto L75;
                case 94742715: goto L6e;
                default: goto L6c;
            }
        L6c:
            r4 = r10
            goto L86
        L6e:
            boolean r0 = r1.equals(r5)
            if (r0 != 0) goto L86
            goto L6c
        L75:
            boolean r0 = r1.equals(r7)
            if (r0 != 0) goto L7c
            goto L6c
        L7c:
            r4 = r6
            goto L86
        L7e:
            boolean r0 = r1.equals(r9)
            if (r0 != 0) goto L85
            goto L6c
        L85:
            r4 = r8
        L86:
            switch(r4) {
                case 0: goto Laa;
                case 1: goto La9;
                case 2: goto La6;
                default: goto L89;
            }
        L89:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r0 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r2 = "for right extrapolation"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        La6:
            r11 = r17
            goto Laa
        La9:
            return r11
        Laa:
            int r0 = (r19 > r21 ? 1 : (r19 == r21 ? 0 : -1))
            if (r0 != 0) goto Laf
            return r19
        Laf:
            int r0 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r0 != 0) goto Lb7
            if (r2 > 0) goto Lb6
            return r19
        Lb6:
            return r21
        Lb7:
            double r0 = r21 - r19
            double r11 = r11 - r15
            double r0 = r0 * r11
            double r2 = r17 - r15
            double r0 = r0 / r2
            double r0 = r19 + r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.InterpolationAnimatedNode.interpolate(double, double, double, double, double, java.lang.String, java.lang.String):double");
    }

    static double interpolate(double d2, double[] dArr, double[] dArr2, String str, String str2) {
        int findRangeIndex = findRangeIndex(d2, dArr);
        int i2 = findRangeIndex + 1;
        return interpolate(d2, dArr[findRangeIndex], dArr[i2], dArr2[findRangeIndex], dArr2[i2], str, str2);
    }

    private static int findRangeIndex(double d2, double[] dArr) {
        int i2 = 1;
        while (i2 < dArr.length - 1 && dArr[i2] < d2) {
            i2++;
        }
        return i2 - 1;
    }

    public InterpolationAnimatedNode(ReadableMap readableMap) {
        this.mInputRange = fromDoubleArray(readableMap.getArray("inputRange"));
        ReadableArray array = readableMap.getArray("outputRange");
        boolean z = array.getType(0) == ReadableType.String;
        this.mHasStringOutput = z;
        if (z) {
            int size = array.size();
            this.mOutputRange = new double[size];
            String string = array.getString(0);
            this.mPattern = string;
            this.mShouldRound = string.startsWith("rgb");
            this.mSOutputMatcher = fpPattern.matcher(this.mPattern);
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < size; i2++) {
                Matcher matcher = fpPattern.matcher(array.getString(i2));
                ArrayList arrayList2 = new ArrayList();
                arrayList.add(arrayList2);
                while (matcher.find()) {
                    arrayList2.add(Double.valueOf(Double.parseDouble(matcher.group())));
                }
                this.mOutputRange[i2] = ((Double) arrayList2.get(0)).doubleValue();
            }
            int size2 = ((ArrayList) arrayList.get(0)).size();
            this.mNumVals = size2;
            this.mOutputs = new double[size2];
            for (int i3 = 0; i3 < this.mNumVals; i3++) {
                double[] dArr = new double[size];
                this.mOutputs[i3] = dArr;
                for (int i4 = 0; i4 < size; i4++) {
                    dArr[i4] = ((Double) ((ArrayList) arrayList.get(i4)).get(i3)).doubleValue();
                }
            }
        } else {
            this.mOutputRange = fromDoubleArray(array);
            this.mSOutputMatcher = null;
        }
        this.mExtrapolateLeft = readableMap.getString("extrapolateLeft");
        this.mExtrapolateRight = readableMap.getString("extrapolateRight");
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void onAttachedToNode(AnimatedNode animatedNode) {
        if (this.mParent != null) {
            throw new IllegalStateException("Parent already attached");
        }
        if (!(animatedNode instanceof ValueAnimatedNode)) {
            throw new IllegalArgumentException("Parent is of an invalid type");
        }
        this.mParent = (ValueAnimatedNode) animatedNode;
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void onDetachedFromNode(AnimatedNode animatedNode) {
        if (animatedNode != this.mParent) {
            throw new IllegalArgumentException("Invalid parent node provided");
        }
        this.mParent = null;
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void update() {
        ValueAnimatedNode valueAnimatedNode = this.mParent;
        if (valueAnimatedNode == null) {
            return;
        }
        double value = valueAnimatedNode.getValue();
        this.mValue = interpolate(value, this.mInputRange, this.mOutputRange, this.mExtrapolateLeft, this.mExtrapolateRight);
        if (this.mHasStringOutput) {
            if (this.mNumVals > 1) {
                StringBuffer stringBuffer = new StringBuffer(this.mPattern.length());
                this.mSOutputMatcher.reset();
                int i2 = 0;
                while (this.mSOutputMatcher.find()) {
                    int i3 = i2 + 1;
                    double interpolate = interpolate(value, this.mInputRange, this.mOutputs[i2], this.mExtrapolateLeft, this.mExtrapolateRight);
                    if (this.mShouldRound) {
                        boolean z = i3 == 4;
                        if (z) {
                            interpolate *= 1000.0d;
                        }
                        int round = (int) Math.round(interpolate);
                        this.mSOutputMatcher.appendReplacement(stringBuffer, z ? Double.toString(round / 1000.0d) : Integer.toString(round));
                    } else {
                        int i4 = (int) interpolate;
                        this.mSOutputMatcher.appendReplacement(stringBuffer, ((double) i4) != interpolate ? Double.toString(interpolate) : Integer.toString(i4));
                    }
                    i2 = i3;
                }
                this.mSOutputMatcher.appendTail(stringBuffer);
                this.mAnimatedObject = stringBuffer.toString();
                return;
            }
            this.mAnimatedObject = this.mSOutputMatcher.replaceFirst(String.valueOf(this.mValue));
        }
    }
}
