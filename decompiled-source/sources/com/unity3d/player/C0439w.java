package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.util.Size;
import android.util.SizeF;
import android.view.Surface;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/* renamed from: com.unity3d.player.w  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
public final class C0439w {
    private static CameraManager B;
    private static String[] C;
    private static Semaphore D = new Semaphore(1);

    /* renamed from: a  reason: collision with root package name */
    private com.unity3d.player.a.b f19063a;

    /* renamed from: b  reason: collision with root package name */
    private CameraDevice f19064b;

    /* renamed from: c  reason: collision with root package name */
    private HandlerThread f19065c;

    /* renamed from: d  reason: collision with root package name */
    private Handler f19066d;

    /* renamed from: e  reason: collision with root package name */
    private Rect f19067e;

    /* renamed from: f  reason: collision with root package name */
    private Rect f19068f;

    /* renamed from: g  reason: collision with root package name */
    private int f19069g;

    /* renamed from: h  reason: collision with root package name */
    private int f19070h;
    private int k;
    private int l;
    private Range n;
    private Image p;
    private CaptureRequest.Builder q;
    private int t;
    private SurfaceTexture u;

    /* renamed from: i  reason: collision with root package name */
    private float f19071i = -1.0f;

    /* renamed from: j  reason: collision with root package name */
    private float f19072j = -1.0f;
    private boolean m = false;
    private ImageReader o = null;
    private CameraCaptureSession r = null;
    private Object s = new Object();
    private Surface v = null;
    private int A = 3;
    private CameraCaptureSession.CaptureCallback w = new C0429r(this);
    private final CameraDevice.StateCallback x = new C0433t(this);
    private final ImageReader.OnImageAvailableListener y = new C0435u(this);
    private final SurfaceTexture.OnFrameAvailableListener z = new C0437v(this);

    /* JADX INFO: Access modifiers changed from: protected */
    public C0439w(com.unity3d.player.a.b bVar) {
        this.f19063a = null;
        this.f19063a = bVar;
        e();
    }

    public static int a(Context context) {
        return b(context).length;
    }

    public static int a(Context context, int i2) {
        try {
            CameraCharacteristics cameraCharacteristics = c(context).getCameraCharacteristics(b(context)[i2]);
            float[] fArr = (float[]) cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
            SizeF sizeF = (SizeF) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE);
            if (fArr.length > 0) {
                return (int) ((fArr[0] * 36.0f) / sizeF.getWidth());
            }
        } catch (CameraAccessException e2) {
            AbstractC0445z.Log(6, "Camera2: CameraAccessException " + e2);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CameraDevice cameraDevice) {
        synchronized (this.s) {
            this.r = null;
        }
        cameraDevice.close();
        this.f19064b = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object obj) {
        if (obj != "Focus") {
            if (obj == "Cancel focus") {
                synchronized (this.s) {
                    if (this.r != null) {
                        g();
                    }
                }
                return;
            }
            return;
        }
        this.m = false;
        synchronized (this.s) {
            if (this.r != null) {
                try {
                    this.q.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
                    this.q.setTag("Regular");
                    this.r.setRepeatingRequest(this.q.build(), this.w, this.f19066d);
                } catch (CameraAccessException e2) {
                    AbstractC0445z.Log(6, "Camera2: CameraAccessException " + e2);
                }
            }
        }
    }

    private void b() {
        try {
            Semaphore semaphore = D;
            TimeUnit timeUnit = TimeUnit.SECONDS;
            if (!semaphore.tryAcquire(4L, timeUnit)) {
                AbstractC0445z.Log(5, "Camera2: Timeout waiting to lock camera for closing.");
                return;
            }
            this.f19064b.close();
            try {
                if (!D.tryAcquire(4L, timeUnit)) {
                    AbstractC0445z.Log(5, "Camera2: Timeout waiting to close camera.");
                }
            } catch (InterruptedException e2) {
                AbstractC0445z.Log(6, "Camera2: Interrupted while waiting to close camera " + e2);
            }
            this.f19064b = null;
            D.release();
        } catch (InterruptedException e3) {
            AbstractC0445z.Log(6, "Camera2: Interrupted while trying to lock camera for closing " + e3);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002a, code lost:
        if (r3.length != 0) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int[] b(android.content.Context r3, int r4) {
        /*
            r0 = 6
            r1 = 0
            android.hardware.camera2.CameraManager r2 = c(r3)     // Catch: android.hardware.camera2.CameraAccessException -> L51
            java.lang.String[] r3 = b(r3)     // Catch: android.hardware.camera2.CameraAccessException -> L51
            r3 = r3[r4]     // Catch: android.hardware.camera2.CameraAccessException -> L51
            android.hardware.camera2.CameraCharacteristics r3 = r2.getCameraCharacteristics(r3)     // Catch: android.hardware.camera2.CameraAccessException -> L51
            android.hardware.camera2.CameraCharacteristics$Key r4 = android.hardware.camera2.CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP
            java.lang.Object r3 = r3.get(r4)
            android.hardware.camera2.params.StreamConfigurationMap r3 = (android.hardware.camera2.params.StreamConfigurationMap) r3
            if (r3 != 0) goto L21
            java.lang.String r3 = "Camera2: configuration map is not available."
            com.unity3d.player.AbstractC0445z.Log(r0, r3)
        L1f:
            r3 = r1
            goto L2d
        L21:
            r4 = 35
            android.util.Size[] r3 = r3.getOutputSizes(r4)
            if (r3 == 0) goto L1f
            int r4 = r3.length
            if (r4 != 0) goto L2d
            goto L1f
        L2d:
            if (r3 == 0) goto L50
            int r4 = r3.length
            int r4 = r4 * 2
            int[] r4 = new int[r4]
            r0 = 0
        L35:
            int r1 = r3.length
            if (r0 >= r1) goto L4f
            int r1 = r0 * 2
            r2 = r3[r0]
            int r2 = r2.getWidth()
            r4[r1] = r2
            int r1 = r1 + 1
            r2 = r3[r0]
            int r2 = r2.getHeight()
            r4[r1] = r2
            int r0 = r0 + 1
            goto L35
        L4f:
            return r4
        L50:
            return r1
        L51:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r2 = "Camera2: CameraAccessException "
            r4.<init>(r2)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            com.unity3d.player.AbstractC0445z.Log(r0, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.C0439w.b(android.content.Context, int):int[]");
    }

    private static String[] b(Context context) {
        if (C == null) {
            try {
                C = c(context).getCameraIdList();
            } catch (CameraAccessException e2) {
                AbstractC0445z.Log(6, "Camera2: CameraAccessException " + e2);
                C = new String[0];
            }
        }
        return C;
    }

    public static int c(Context context, int i2) {
        try {
            return ((Integer) c(context).getCameraCharacteristics(b(context)[i2]).get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        } catch (CameraAccessException e2) {
            AbstractC0445z.Log(6, "Camera2: CameraAccessException " + e2);
            return 0;
        }
    }

    private static CameraManager c(Context context) {
        if (B == null) {
            B = (CameraManager) context.getSystemService("camera");
        }
        return B;
    }

    public static boolean d(Context context, int i2) {
        try {
            return ((Integer) c(context).getCameraCharacteristics(b(context)[i2]).get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() > 0;
        } catch (CameraAccessException e2) {
            AbstractC0445z.Log(6, "Camera2: CameraAccessException " + e2);
            return false;
        }
    }

    private void e() {
        HandlerThread handlerThread = new HandlerThread("CameraBackground");
        this.f19065c = handlerThread;
        handlerThread.start();
        this.f19066d = new Handler(this.f19065c.getLooper());
    }

    public static boolean e(Context context, int i2) {
        try {
            return ((Integer) c(context).getCameraCharacteristics(b(context)[i2]).get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
        } catch (CameraAccessException e2) {
            AbstractC0445z.Log(6, "Camera2: CameraAccessException " + e2);
            return false;
        }
    }

    private void f() {
        try {
            CameraCaptureSession cameraCaptureSession = this.r;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.stopRepeating();
                this.q.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
                this.q.set(CaptureRequest.CONTROL_AF_MODE, 0);
                this.q.setTag("Cancel focus");
                this.r.capture(this.q.build(), this.w, this.f19066d);
            }
        } catch (CameraAccessException e2) {
            AbstractC0445z.Log(6, "Camera2: CameraAccessException " + e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        try {
            if (this.f19070h != 0) {
                float f2 = this.f19071i;
                if (f2 >= 0.0f && f2 <= 1.0f) {
                    float f3 = this.f19072j;
                    if (f3 >= 0.0f && f3 <= 1.0f) {
                        this.m = true;
                        int width = this.f19068f.width();
                        int i2 = this.k;
                        int height = this.f19068f.height();
                        int i3 = this.l;
                        int max = Math.max(this.f19069g + 1, Math.min((int) (((width - (i2 * 2)) * this.f19071i) + i2), (this.f19068f.width() - this.f19069g) - 1));
                        int max2 = Math.max(this.f19069g + 1, Math.min((int) (((1.0d - this.f19072j) * (height - (i3 * 2))) + i3), (this.f19068f.height() - this.f19069g) - 1));
                        CaptureRequest.Builder builder = this.q;
                        CaptureRequest.Key key = CaptureRequest.CONTROL_AF_REGIONS;
                        int i4 = this.f19069g;
                        int i5 = i4 * 2;
                        builder.set(key, new MeteringRectangle[]{new MeteringRectangle(max - i4, max2 - i4, i5, i5, 999)});
                        this.q.set(CaptureRequest.CONTROL_AF_MODE, 1);
                        this.q.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
                        this.q.setTag("Focus");
                        this.r.capture(this.q.build(), this.w, this.f19066d);
                    }
                }
            }
            this.q.set(CaptureRequest.CONTROL_AF_MODE, 4);
            this.q.setTag("Regular");
            CameraCaptureSession cameraCaptureSession = this.r;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.setRepeatingRequest(this.q.build(), this.w, this.f19066d);
            }
        } catch (CameraAccessException e2) {
            AbstractC0445z.Log(6, "Camera2: CameraAccessException " + e2);
        }
    }

    public final void a() {
        if (this.f19064b != null) {
            i();
            b();
            this.w = null;
            this.v = null;
            this.u = null;
            Image image = this.p;
            if (image != null) {
                image.close();
                this.p = null;
            }
            ImageReader imageReader = this.o;
            if (imageReader != null) {
                imageReader.close();
                this.o = null;
            }
        }
        this.f19065c.quit();
        try {
            this.f19065c.join(4000L);
            this.f19065c = null;
            this.f19066d = null;
        } catch (InterruptedException e2) {
            this.f19065c.interrupt();
            AbstractC0445z.Log(6, "Camera2: Interrupted while waiting for the background thread to finish " + e2);
        }
    }

    public final boolean a(float f2, float f3) {
        if (this.f19070h > 0) {
            if (this.m) {
                AbstractC0445z.Log(5, "Camera2: Setting manual focus point already started.");
                return false;
            }
            this.f19071i = f2;
            this.f19072j = f3;
            synchronized (this.s) {
                if (this.r != null && this.A != 2) {
                    f();
                }
            }
            return true;
        }
        return false;
    }

    public final boolean a(Context context, int i2, int i3, int i4, int i5, int i6, Surface surface) {
        Rect rect;
        int i7 = i5;
        try {
            CameraCharacteristics cameraCharacteristics = B.getCameraCharacteristics(b(context)[i2]);
            if (((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2) {
                AbstractC0445z.Log(5, "Camera2: only LEGACY hardware level is supported.");
            }
            StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            Size[] sizeArr = null;
            if (streamConfigurationMap == null) {
                AbstractC0445z.Log(6, "Camera2: configuration map is not available.");
            } else {
                Size[] outputSizes = streamConfigurationMap.getOutputSizes(35);
                if (outputSizes != null && outputSizes.length != 0) {
                    sizeArr = outputSizes;
                }
            }
            if (sizeArr == null || sizeArr.length == 0) {
                return false;
            }
            double d2 = i3;
            double d3 = i4;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            double d4 = Double.MAX_VALUE;
            while (i8 < sizeArr.length) {
                int width = sizeArr[i8].getWidth();
                int height = sizeArr[i8].getHeight();
                CameraCharacteristics cameraCharacteristics2 = cameraCharacteristics;
                double d5 = d2;
                double abs = Math.abs(Math.log(d3 / height)) + Math.abs(Math.log(d2 / width));
                if (abs < d4) {
                    i9 = height;
                    d4 = abs;
                    i10 = width;
                }
                i8++;
                cameraCharacteristics = cameraCharacteristics2;
                d2 = d5;
            }
            CameraCharacteristics cameraCharacteristics3 = cameraCharacteristics;
            this.f19067e = new Rect(0, 0, i10, i9);
            Range[] rangeArr = (Range[]) cameraCharacteristics3.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
            if (rangeArr == null || rangeArr.length == 0) {
                AbstractC0445z.Log(6, "Camera2: target FPS ranges are not avialable.");
                return false;
            }
            int i11 = -1;
            int i12 = 0;
            double d6 = Double.MAX_VALUE;
            while (true) {
                if (i12 < rangeArr.length) {
                    int intValue = ((Integer) rangeArr[i12].getLower()).intValue();
                    int intValue2 = ((Integer) rangeArr[i12].getUpper()).intValue();
                    float f2 = i7;
                    if (f2 + 0.1f > intValue && f2 - 0.1f < intValue2) {
                        break;
                    }
                    if (r8 < d6) {
                        i11 = i12;
                        d6 = r8;
                    }
                    i12++;
                } else {
                    i7 = ((Integer) (i7 > ((Integer) rangeArr[i11].getUpper()).intValue() ? rangeArr[i11].getUpper() : rangeArr[i11].getLower())).intValue();
                }
            }
            this.n = new Range(Integer.valueOf(i7), Integer.valueOf(i7));
            try {
                Semaphore semaphore = D;
                TimeUnit timeUnit = TimeUnit.SECONDS;
                if (!semaphore.tryAcquire(4L, timeUnit)) {
                    AbstractC0445z.Log(5, "Camera2: Timeout waiting to lock camera for opening.");
                    return false;
                }
                try {
                    B.openCamera(b(context)[i2], this.x, this.f19066d);
                    try {
                    } catch (InterruptedException e2) {
                        AbstractC0445z.Log(6, "Camera2: Interrupted while waiting to open camera " + e2);
                    }
                    if (!D.tryAcquire(4L, timeUnit)) {
                        AbstractC0445z.Log(5, "Camera2: Timeout waiting to open camera.");
                        return false;
                    }
                    D.release();
                    this.t = i6;
                    this.v = surface;
                    int intValue3 = ((Integer) cameraCharacteristics3.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue();
                    this.f19070h = intValue3;
                    if (intValue3 > 0) {
                        this.f19068f = (Rect) cameraCharacteristics3.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
                        float width2 = this.f19067e.width() / this.f19067e.height();
                        if (width2 > rect.width() / this.f19068f.height()) {
                            this.k = 0;
                            this.l = (int) ((this.f19068f.height() - (this.f19068f.width() / width2)) / 2.0f);
                        } else {
                            this.l = 0;
                            this.k = (int) ((this.f19068f.width() - (this.f19068f.height() * width2)) / 2.0f);
                        }
                        this.f19069g = Math.min(this.f19068f.width(), this.f19068f.height()) / 20;
                    }
                    return this.f19064b != null;
                } catch (CameraAccessException e3) {
                    AbstractC0445z.Log(6, "Camera2: CameraAccessException " + e3);
                    D.release();
                    return false;
                }
            } catch (InterruptedException e4) {
                AbstractC0445z.Log(6, "Camera2: Interrupted while trying to lock camera for opening " + e4);
                return false;
            }
        } catch (CameraAccessException e5) {
            AbstractC0445z.Log(6, "Camera2: CameraAccessException " + e5);
            return false;
        }
    }

    public final Rect c() {
        return this.f19067e;
    }

    public final void d() {
        synchronized (this.s) {
            CameraCaptureSession cameraCaptureSession = this.r;
            if (cameraCaptureSession != null) {
                try {
                    cameraCaptureSession.stopRepeating();
                    this.A = 2;
                } catch (CameraAccessException e2) {
                    AbstractC0445z.Log(6, "Camera2: CameraAccessException " + e2);
                }
            }
        }
    }

    public void h() {
        Surface surface;
        if (this.t != 0) {
            if (this.v == null) {
                SurfaceTexture surfaceTexture = new SurfaceTexture(this.t);
                this.u = surfaceTexture;
                surfaceTexture.setDefaultBufferSize(this.f19067e.width(), this.f19067e.height());
                this.u.setOnFrameAvailableListener(this.z, this.f19066d);
                surface = new Surface(this.u);
                this.v = surface;
            }
        } else if (this.v == null && this.o == null) {
            ImageReader newInstance = ImageReader.newInstance(this.f19067e.width(), this.f19067e.height(), 35, 2);
            this.o = newInstance;
            newInstance.setOnImageAvailableListener(this.y, this.f19066d);
            this.p = null;
            surface = this.o.getSurface();
            this.v = surface;
        }
        try {
            CameraCaptureSession cameraCaptureSession = this.r;
            if (cameraCaptureSession == null) {
                this.f19064b.createCaptureSession(Arrays.asList(this.v), new C0431s(this), this.f19066d);
            } else if (this.A == 2) {
                cameraCaptureSession.setRepeatingRequest(this.q.build(), this.w, this.f19066d);
            }
            this.A = 1;
        } catch (CameraAccessException e2) {
            AbstractC0445z.Log(6, "Camera2: CameraAccessException " + e2);
        }
    }

    public final void i() {
        synchronized (this.s) {
            CameraCaptureSession cameraCaptureSession = this.r;
            if (cameraCaptureSession != null) {
                try {
                    cameraCaptureSession.abortCaptures();
                } catch (CameraAccessException e2) {
                    AbstractC0445z.Log(6, "Camera2: CameraAccessException " + e2);
                }
                this.r.close();
                this.r = null;
                this.A = 3;
            }
        }
    }
}
