package com.facebook.react.uimanager;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* loaded from: classes8.dex */
public class TransformHelper {
    private static ThreadLocal<double[]> sHelperMatrix = new ThreadLocal<double[]>() { // from class: com.facebook.react.uimanager.TransformHelper.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public double[] initialValue() {
            return new double[16];
        }
    };

    private static double convertToRadians(ReadableMap readableMap, String str) {
        double d2;
        boolean z = true;
        if (readableMap.getType(str) == ReadableType.String) {
            String string = readableMap.getString(str);
            if (string.endsWith("rad")) {
                string = string.substring(0, string.length() - 3);
            } else if (string.endsWith("deg")) {
                string = string.substring(0, string.length() - 3);
                z = false;
            }
            d2 = Float.parseFloat(string);
        } else {
            d2 = readableMap.getDouble(str);
        }
        return z ? d2 : MatrixMathHelper.degreesToRadians(d2);
    }

    public static void processTransform(ReadableArray readableArray, double[] dArr) {
        double[] dArr2 = sHelperMatrix.get();
        MatrixMathHelper.resetIdentityMatrix(dArr);
        int size = readableArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            ReadableMap map = readableArray.getMap(i2);
            String nextKey = map.keySetIterator().nextKey();
            MatrixMathHelper.resetIdentityMatrix(dArr2);
            if ("matrix".equals(nextKey)) {
                ReadableArray array = map.getArray(nextKey);
                for (int i3 = 0; i3 < 16; i3++) {
                    dArr2[i3] = array.getDouble(i3);
                }
            } else if ("perspective".equals(nextKey)) {
                MatrixMathHelper.applyPerspective(dArr2, map.getDouble(nextKey));
            } else if ("rotateX".equals(nextKey)) {
                MatrixMathHelper.applyRotateX(dArr2, convertToRadians(map, nextKey));
            } else if ("rotateY".equals(nextKey)) {
                MatrixMathHelper.applyRotateY(dArr2, convertToRadians(map, nextKey));
            } else if ("rotate".equals(nextKey) || "rotateZ".equals(nextKey)) {
                MatrixMathHelper.applyRotateZ(dArr2, convertToRadians(map, nextKey));
            } else if ("scale".equals(nextKey)) {
                double d2 = map.getDouble(nextKey);
                MatrixMathHelper.applyScaleX(dArr2, d2);
                MatrixMathHelper.applyScaleY(dArr2, d2);
            } else if (ViewProps.SCALE_X.equals(nextKey)) {
                MatrixMathHelper.applyScaleX(dArr2, map.getDouble(nextKey));
            } else if (ViewProps.SCALE_Y.equals(nextKey)) {
                MatrixMathHelper.applyScaleY(dArr2, map.getDouble(nextKey));
            } else {
                boolean equals = "translate".equals(nextKey);
                double d3 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                if (equals) {
                    ReadableArray array2 = map.getArray(nextKey);
                    double d4 = array2.getDouble(0);
                    double d5 = array2.getDouble(1);
                    if (array2.size() > 2) {
                        d3 = array2.getDouble(2);
                    }
                    MatrixMathHelper.applyTranslate3D(dArr2, d4, d5, d3);
                } else if (ViewProps.TRANSLATE_X.equals(nextKey)) {
                    MatrixMathHelper.applyTranslate2D(dArr2, map.getDouble(nextKey), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                } else if (ViewProps.TRANSLATE_Y.equals(nextKey)) {
                    MatrixMathHelper.applyTranslate2D(dArr2, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, map.getDouble(nextKey));
                } else if ("skewX".equals(nextKey)) {
                    MatrixMathHelper.applySkewX(dArr2, convertToRadians(map, nextKey));
                } else if ("skewY".equals(nextKey)) {
                    MatrixMathHelper.applySkewY(dArr2, convertToRadians(map, nextKey));
                } else {
                    throw new JSApplicationIllegalArgumentException("Unsupported transform type: " + nextKey);
                }
            }
            MatrixMathHelper.multiplyInto(dArr, dArr, dArr2);
        }
    }
}
