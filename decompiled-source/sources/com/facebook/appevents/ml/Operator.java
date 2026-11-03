package com.facebook.appevents.ml;

import com.bytedance.ttgame.module.webview.api.WebViewConfig;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Operator.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\u001b\u0010\b\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nH\u0007¢\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0007J \u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J+\u0010\u000f\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0013H\u0007J\u0018\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0013H\u0007J\u0018\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0007J\u0010\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u001e"}, d2 = {"Lcom/facebook/appevents/ml/Operator;", "", "()V", "addmv", "", WebViewConfig.LOCATION_X, "Lcom/facebook/appevents/ml/MTensor;", "b", "concatenate", "tensors", "", "([Lcom/facebook/appevents/ml/MTensor;)Lcom/facebook/appevents/ml/MTensor;", "conv1D", DownloadFileUtils.MODE_WRITE, "dense", "embedding", "texts", "", "seqLength", "", "([Ljava/lang/String;ILcom/facebook/appevents/ml/MTensor;)Lcom/facebook/appevents/ml/MTensor;", "flatten", "startDim", "maxPool1D", "poolSize", "mul", "relu", "softmax", "transpose2D", "transpose3D", "facebook-core_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes8.dex */
public final class Operator {
    public static final Operator INSTANCE = new Operator();

    private Operator() {
    }

    @JvmStatic
    public static final void addmv(MTensor x, MTensor b2) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            Intrinsics.checkNotNullParameter(b2, "b");
            int shape = x.getShape(0);
            int shape2 = x.getShape(1);
            int shape3 = x.getShape(2);
            float[] data = x.getData();
            float[] data2 = b2.getData();
            if (shape <= 0) {
                return;
            }
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (shape2 > 0) {
                    int i4 = 0;
                    while (true) {
                        int i5 = i4 + 1;
                        if (shape3 > 0) {
                            int i6 = 0;
                            while (true) {
                                int i7 = i6 + 1;
                                int i8 = (i2 * shape2 * shape3) + (i4 * shape3) + i6;
                                data[i8] = data[i8] + data2[i6];
                                if (i7 >= shape3) {
                                    break;
                                }
                                i6 = i7;
                            }
                        }
                        if (i5 >= shape2) {
                            break;
                        }
                        i4 = i5;
                    }
                }
                if (i3 >= shape) {
                    return;
                }
                i2 = i3;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
        }
    }

    @JvmStatic
    public static final MTensor mul(MTensor x, MTensor w) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            Intrinsics.checkNotNullParameter(w, "w");
            int i2 = 0;
            int shape = x.getShape(0);
            int shape2 = w.getShape(0);
            int shape3 = w.getShape(1);
            MTensor mTensor = new MTensor(new int[]{shape, shape3});
            float[] data = x.getData();
            float[] data2 = w.getData();
            float[] data3 = mTensor.getData();
            if (shape > 0) {
                int i3 = 0;
                while (true) {
                    int i4 = i3 + 1;
                    if (shape3 > 0) {
                        int i5 = i2;
                        while (true) {
                            int i6 = i5 + 1;
                            int i7 = (i3 * shape3) + i5;
                            data3[i7] = 0.0f;
                            if (shape2 > 0) {
                                int i8 = i2;
                                while (true) {
                                    int i9 = i8 + 1;
                                    data3[i7] = data3[i7] + (data[(i3 * shape2) + i8] * data2[(i8 * shape3) + i5]);
                                    if (i9 >= shape2) {
                                        break;
                                    }
                                    i8 = i9;
                                }
                            }
                            if (i6 >= shape3) {
                                break;
                            }
                            i5 = i6;
                            i2 = 0;
                        }
                    }
                    if (i4 >= shape) {
                        break;
                    }
                    i3 = i4;
                    i2 = 0;
                }
            }
            return mTensor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }

    @JvmStatic
    public static final void relu(MTensor x) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            float[] data = x.getData();
            int i2 = 0;
            int length = data.length - 1;
            if (length < 0) {
                return;
            }
            while (true) {
                int i3 = i2 + 1;
                if (data[i2] < 0.0f) {
                    data[i2] = 0.0f;
                }
                if (i3 > length) {
                    return;
                }
                i2 = i3;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
        }
    }

    @JvmStatic
    public static final void flatten(MTensor x, int i2) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            if (i2 >= x.getShapeSize()) {
                return;
            }
            int shapeSize = x.getShapeSize();
            int i3 = 1;
            if (i2 < shapeSize) {
                int i4 = i2;
                while (true) {
                    int i5 = i4 + 1;
                    i3 *= x.getShape(i4);
                    if (i5 >= shapeSize) {
                        break;
                    }
                    i4 = i5;
                }
            }
            int[] iArr = new int[i2 + 1];
            int i6 = 0;
            if (i2 > 0) {
                while (true) {
                    int i7 = i6 + 1;
                    iArr[i6] = x.getShape(i6);
                    if (i7 >= i2) {
                        break;
                    }
                    i6 = i7;
                }
            }
            iArr[i2] = i3;
            x.reshape(iArr);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
        }
    }

    @JvmStatic
    public static final MTensor concatenate(MTensor[] tensors) {
        int i2;
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(tensors, "tensors");
            int i3 = 0;
            int shape = tensors[0].getShape(0);
            int length = tensors.length - 1;
            if (length >= 0) {
                int i4 = 0;
                i2 = 0;
                while (true) {
                    int i5 = i4 + 1;
                    i2 += tensors[i4].getShape(1);
                    if (i5 > length) {
                        break;
                    }
                    i4 = i5;
                }
            } else {
                i2 = 0;
            }
            MTensor mTensor = new MTensor(new int[]{shape, i2});
            float[] data = mTensor.getData();
            if (shape > 0) {
                int i6 = 0;
                while (true) {
                    int i7 = i6 + 1;
                    int i8 = i6 * i2;
                    int length2 = tensors.length - 1;
                    if (length2 >= 0) {
                        int i9 = i3;
                        while (true) {
                            int i10 = i9 + 1;
                            float[] data2 = tensors[i9].getData();
                            int shape2 = tensors[i9].getShape(1);
                            System.arraycopy(data2, i6 * shape2, data, i8, shape2);
                            i8 += shape2;
                            if (i10 > length2) {
                                break;
                            }
                            i9 = i10;
                        }
                    }
                    if (i7 >= shape) {
                        break;
                    }
                    i6 = i7;
                    i3 = 0;
                }
            }
            return mTensor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }

    @JvmStatic
    public static final void softmax(MTensor x) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            int i2 = 0;
            int shape = x.getShape(0);
            int shape2 = x.getShape(1);
            float[] data = x.getData();
            if (shape <= 0) {
                return;
            }
            while (true) {
                int i3 = i2 + 1;
                int i4 = i2 * shape2;
                int i5 = i4 + shape2;
                float f2 = Float.MIN_VALUE;
                float f3 = 0.0f;
                if (i4 < i5) {
                    int i6 = i4;
                    while (true) {
                        int i7 = i6 + 1;
                        if (data[i6] > f2) {
                            f2 = data[i6];
                        }
                        if (i7 >= i5) {
                            break;
                        }
                        i6 = i7;
                    }
                }
                if (i4 < i5) {
                    int i8 = i4;
                    while (true) {
                        int i9 = i8 + 1;
                        data[i8] = (float) Math.exp(data[i8] - f2);
                        f3 += data[i8];
                        if (i9 >= i5) {
                            break;
                        }
                        i8 = i9;
                    }
                }
                if (i4 < i5) {
                    while (true) {
                        int i10 = i4 + 1;
                        data[i4] = data[i4] / f3;
                        if (i10 >= i5) {
                            break;
                        }
                        i4 = i10;
                    }
                }
                if (i3 >= shape) {
                    return;
                }
                i2 = i3;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
        }
    }

    @JvmStatic
    public static final MTensor dense(MTensor x, MTensor w, MTensor b2) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            Intrinsics.checkNotNullParameter(w, "w");
            Intrinsics.checkNotNullParameter(b2, "b");
            int shape = x.getShape(0);
            int shape2 = b2.getShape(0);
            MTensor mul = mul(x, w);
            float[] data = b2.getData();
            float[] data2 = mul.getData();
            if (shape > 0) {
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    if (shape2 > 0) {
                        int i4 = 0;
                        while (true) {
                            int i5 = i4 + 1;
                            int i6 = (i2 * shape2) + i4;
                            data2[i6] = data2[i6] + data[i4];
                            if (i5 >= shape2) {
                                break;
                            }
                            i4 = i5;
                        }
                    }
                    if (i3 >= shape) {
                        break;
                    }
                    i2 = i3;
                }
            }
            return mul;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }

    @JvmStatic
    public static final MTensor embedding(String[] texts, int i2, MTensor w) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(texts, "texts");
            Intrinsics.checkNotNullParameter(w, "w");
            int length = texts.length;
            int shape = w.getShape(1);
            MTensor mTensor = new MTensor(new int[]{length, i2, shape});
            float[] data = mTensor.getData();
            float[] data2 = w.getData();
            if (length > 0) {
                int i3 = 0;
                while (true) {
                    int i4 = i3 + 1;
                    int[] vectorize = Utils.INSTANCE.vectorize(texts[i3], i2);
                    if (i2 > 0) {
                        int i5 = 0;
                        while (true) {
                            int i6 = i5 + 1;
                            System.arraycopy(data2, vectorize[i5] * shape, data, (shape * i2 * i3) + (i5 * shape), shape);
                            if (i6 >= i2) {
                                break;
                            }
                            i5 = i6;
                        }
                    }
                    if (i4 >= length) {
                        break;
                    }
                    i3 = i4;
                }
            }
            return mTensor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }

    @JvmStatic
    public static final MTensor transpose2D(MTensor x) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            int shape = x.getShape(0);
            int shape2 = x.getShape(1);
            MTensor mTensor = new MTensor(new int[]{shape2, shape});
            float[] data = x.getData();
            float[] data2 = mTensor.getData();
            if (shape > 0) {
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    if (shape2 > 0) {
                        int i4 = 0;
                        while (true) {
                            int i5 = i4 + 1;
                            data2[(i4 * shape) + i2] = data[(i2 * shape2) + i4];
                            if (i5 >= shape2) {
                                break;
                            }
                            i4 = i5;
                        }
                    }
                    if (i3 >= shape) {
                        break;
                    }
                    i2 = i3;
                }
            }
            return mTensor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }

    @JvmStatic
    public static final MTensor transpose3D(MTensor x) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            int shape = x.getShape(0);
            int shape2 = x.getShape(1);
            int shape3 = x.getShape(2);
            MTensor mTensor = new MTensor(new int[]{shape3, shape2, shape});
            float[] data = x.getData();
            float[] data2 = mTensor.getData();
            if (shape > 0) {
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    if (shape2 > 0) {
                        int i4 = 0;
                        while (true) {
                            int i5 = i4 + 1;
                            if (shape3 > 0) {
                                int i6 = 0;
                                while (true) {
                                    int i7 = i6 + 1;
                                    data2[(i6 * shape * shape2) + (i4 * shape) + i2] = data[(i2 * shape2 * shape3) + (i4 * shape3) + i6];
                                    if (i7 >= shape3) {
                                        break;
                                    }
                                    i6 = i7;
                                }
                            }
                            if (i5 >= shape2) {
                                break;
                            }
                            i4 = i5;
                        }
                    }
                    if (i3 >= shape) {
                        break;
                    }
                    i2 = i3;
                }
            }
            return mTensor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }

    @JvmStatic
    public static final MTensor conv1D(MTensor x, MTensor w) {
        Class<Operator> cls;
        Class<Operator> cls2;
        MTensor mTensor;
        Class<Operator> cls3 = Operator.class;
        if (CrashShieldHandler.isObjectCrashing(cls3)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            Intrinsics.checkNotNullParameter(w, "w");
            int i2 = 0;
            int shape = x.getShape(0);
            int shape2 = x.getShape(1);
            int shape3 = x.getShape(2);
            int shape4 = w.getShape(0);
            int i3 = (shape2 - shape4) + 1;
            int shape5 = w.getShape(2);
            MTensor mTensor2 = new MTensor(new int[]{shape, i3, shape5});
            float[] data = x.getData();
            float[] data2 = mTensor2.getData();
            float[] data3 = w.getData();
            if (shape <= 0) {
                return mTensor2;
            }
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                if (shape5 > 0) {
                    int i6 = i2;
                    while (true) {
                        int i7 = i6 + 1;
                        if (i3 > 0) {
                            int i8 = 0;
                            while (true) {
                                int i9 = i8 + 1;
                                float f2 = 0.0f;
                                if (shape4 > 0) {
                                    int i10 = 0;
                                    while (true) {
                                        cls2 = cls3;
                                        int i11 = i10 + 1;
                                        if (shape3 > 0) {
                                            int i12 = 0;
                                            while (true) {
                                                mTensor = mTensor2;
                                                int i13 = i12 + 1;
                                                try {
                                                    f2 += data[(shape2 * shape3 * i4) + ((i10 + i8) * shape3) + i12] * data3[(((i10 * shape3) + i12) * shape5) + i6];
                                                    if (i13 >= shape3) {
                                                        break;
                                                    }
                                                    i12 = i13;
                                                    mTensor2 = mTensor;
                                                } catch (Throwable th) {
                                                    th = th;
                                                    cls = cls2;
                                                    CrashShieldHandler.handleThrowable(th, cls);
                                                    return null;
                                                }
                                            }
                                        } else {
                                            mTensor = mTensor2;
                                        }
                                        if (i11 >= shape4) {
                                            break;
                                        }
                                        i10 = i11;
                                        cls3 = cls2;
                                        mTensor2 = mTensor;
                                    }
                                } else {
                                    cls2 = cls3;
                                    mTensor = mTensor2;
                                }
                                data2[(i3 * shape5 * i4) + (i8 * shape5) + i6] = f2;
                                if (i9 >= i3) {
                                    break;
                                }
                                i8 = i9;
                                cls3 = cls2;
                                mTensor2 = mTensor;
                            }
                        } else {
                            cls2 = cls3;
                            mTensor = mTensor2;
                        }
                        if (i7 >= shape5) {
                            break;
                        }
                        i6 = i7;
                        cls3 = cls2;
                        mTensor2 = mTensor;
                    }
                } else {
                    cls2 = cls3;
                    mTensor = mTensor2;
                }
                if (i5 >= shape) {
                    return mTensor;
                }
                i4 = i5;
                cls3 = cls2;
                mTensor2 = mTensor;
                i2 = 0;
            }
        } catch (Throwable th2) {
            th = th2;
            cls = cls3;
        }
    }

    @JvmStatic
    public static final MTensor maxPool1D(MTensor x, int i2) {
        int i3;
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            int i4 = 0;
            int shape = x.getShape(0);
            int shape2 = x.getShape(1);
            int shape3 = x.getShape(2);
            int i5 = (shape2 - i2) + 1;
            MTensor mTensor = new MTensor(new int[]{shape, i5, shape3});
            float[] data = x.getData();
            float[] data2 = mTensor.getData();
            if (shape > 0) {
                int i6 = 0;
                while (true) {
                    int i7 = i6 + 1;
                    if (shape3 > 0) {
                        int i8 = i4;
                        while (true) {
                            int i9 = i8 + 1;
                            if (i5 > 0) {
                                int i10 = i4;
                                while (true) {
                                    int i11 = i10 + 1;
                                    int i12 = i10 * shape3;
                                    int i13 = (i6 * i5 * shape3) + i12 + i8;
                                    int i14 = (i6 * shape2 * shape3) + i12 + i8;
                                    data2[i13] = Float.MIN_VALUE;
                                    if (i2 > 0) {
                                        int i15 = 0;
                                        while (true) {
                                            int i16 = i15 + 1;
                                            i3 = shape2;
                                            data2[i13] = Math.max(data2[i13], data[i14 + (i15 * shape3)]);
                                            if (i16 >= i2) {
                                                break;
                                            }
                                            i15 = i16;
                                            shape2 = i3;
                                        }
                                    } else {
                                        i3 = shape2;
                                    }
                                    if (i11 >= i5) {
                                        break;
                                    }
                                    i10 = i11;
                                    shape2 = i3;
                                }
                            } else {
                                i3 = shape2;
                            }
                            if (i9 >= shape3) {
                                break;
                            }
                            i8 = i9;
                            shape2 = i3;
                            i4 = 0;
                        }
                    } else {
                        i3 = shape2;
                    }
                    if (i7 >= shape) {
                        break;
                    }
                    i6 = i7;
                    shape2 = i3;
                    i4 = 0;
                }
            }
            return mTensor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }
}
