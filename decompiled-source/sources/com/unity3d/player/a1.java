package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.MediaController;
import java.io.FileInputStream;
import java.io.IOException;
/* loaded from: classes10.dex */
public final class a1 extends FrameLayout implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaController.MediaPlayerControl {
    public static final /* synthetic */ int A = 0;

    /* renamed from: a  reason: collision with root package name */
    private final Context f18937a;

    /* renamed from: b  reason: collision with root package name */
    private final SurfaceView f18938b;

    /* renamed from: c  reason: collision with root package name */
    private final SurfaceHolder f18939c;

    /* renamed from: d  reason: collision with root package name */
    private final String f18940d;

    /* renamed from: e  reason: collision with root package name */
    private final int f18941e;

    /* renamed from: f  reason: collision with root package name */
    private final int f18942f;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f18943g;

    /* renamed from: h  reason: collision with root package name */
    private final long f18944h;

    /* renamed from: i  reason: collision with root package name */
    private final long f18945i;

    /* renamed from: j  reason: collision with root package name */
    private final a1 f18946j;
    private int k;
    private int l;
    private int m;
    private int n;
    private MediaPlayer o;
    private MediaController p;
    private boolean q;
    private boolean r;
    private int s;
    private boolean t;
    private int u;
    private boolean v;
    private C w;
    private Y0 x;
    private Z0 y;
    private volatile int z;

    /* JADX INFO: Access modifiers changed from: protected */
    public a1(Context context, UnityPlayer unityPlayer, String str, int i2, int i3, int i4, boolean z, long j2, long j3, Y0 y0) {
        super(context);
        this.q = false;
        this.r = false;
        this.s = 0;
        this.t = false;
        this.u = 0;
        this.v = false;
        this.w = null;
        this.z = 0;
        this.x = y0;
        this.f18937a = context;
        this.f18946j = this;
        SurfaceView surfaceView = new SurfaceView(context);
        this.f18938b = surfaceView;
        SurfaceHolder holder = surfaceView.getHolder();
        this.f18939c = holder;
        holder.addCallback(this);
        setBackgroundColor(i2);
        addView(surfaceView);
        this.f18940d = str;
        this.f18941e = i3;
        this.f18942f = i4;
        this.f18943g = z;
        this.f18944h = j2;
        this.f18945i = j3;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.w = D.a(unityPlayer.getActivity(), 1000000, new X0(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        this.z = i2;
        Y0 y0 = this.x;
        if (y0 != null) {
            ((c1) y0).a(this.z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a() {
        return this.t;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return true;
    }

    public void cancelOnPrepare() {
        a(2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void destroyPlayer() {
        if (!this.t) {
            pause();
        }
        doCleanUp();
        C c2 = this.w;
        if (c2 != null) {
            c2.unregisterOnBackPressedCallback();
            this.w = null;
        }
    }

    protected void doCleanUp() {
        Z0 z0 = this.y;
        if (z0 != null) {
            z0.f18929b = true;
            this.y = null;
        }
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.o.release();
            this.o = null;
        }
        this.m = 0;
        this.n = 0;
        this.r = false;
        this.q = false;
    }

    public boolean exitOnKeypress(int i2, KeyEvent keyEvent) {
        Runnable runnable;
        if (i2 == 4 || !(this.f18941e != 2 || i2 == 0 || keyEvent.isSystem())) {
            com.unity3d.player.a.c cVar = this.w.f18805a;
            if ((cVar != null) && (runnable = ((B) cVar).f18803a) != null) {
                runnable.run();
            }
            return true;
        }
        return false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getAudioSessionId();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.f18943g) {
            return this.s;
        }
        return 100;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCurrentPosition();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getDuration();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        boolean z = this.r && this.q;
        MediaPlayer mediaPlayer = this.o;
        return mediaPlayer == null ? !z : mediaPlayer.isPlaying() || !z;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
        this.s = i2;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        destroyPlayer();
        a(3);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        MediaController mediaController = this.p;
        return mediaController != null ? mediaController.onKeyDown(i2, keyEvent) : super.onKeyDown(i2, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (exitOnKeypress(i2, keyEvent)) {
            return true;
        }
        MediaController mediaController = this.p;
        return mediaController != null ? mediaController.onKeyUp(i2, keyEvent) : super.onKeyUp(i2, keyEvent);
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        Z0 z0 = this.y;
        if (z0 != null) {
            z0.f18929b = true;
            this.y = null;
        }
        int i2 = this.f18941e;
        if (i2 == 0 || i2 == 1) {
            MediaController mediaController = new MediaController(this.f18937a);
            this.p = mediaController;
            mediaController.setMediaPlayer(this);
            this.p.setAnchorView(this);
            this.p.setEnabled(true);
            Context context = this.f18937a;
            if (context instanceof Activity) {
                this.p.setSystemUiVisibility(((Activity) context).getWindow().getDecorView().getSystemUiVisibility());
            }
            this.p.show();
        }
        this.r = true;
        if (!this.q || isPlaying()) {
            return;
        }
        a(1);
        updateVideoLayout();
        if (this.t) {
            return;
        }
        start();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f18941e != 2 || action != 0) {
            MediaController mediaController = this.p;
            return mediaController != null ? mediaController.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
        }
        destroyPlayer();
        a(3);
        return true;
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
            return;
        }
        this.q = true;
        this.m = i2;
        this.n = i3;
        if (!this.r || isPlaying()) {
            return;
        }
        a(1);
        updateVideoLayout();
        if (this.t) {
            return;
        }
        start();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer == null) {
            return;
        }
        if (this.v) {
            this.u = mediaPlayer.getCurrentPosition();
            this.o.pause();
        }
        this.t = true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i2) {
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.seekTo(i2);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer == null) {
            return;
        }
        if (this.v) {
            int i2 = this.u;
            if (i2 > 0) {
                mediaPlayer.seekTo(i2);
            }
            this.o.start();
            this.u = 0;
        }
        this.t = false;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        if (this.k == i3 && this.l == i4) {
            return;
        }
        this.k = i3;
        this.l = i4;
        if (this.v) {
            updateVideoLayout();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        FileInputStream fileInputStream;
        this.v = true;
        if (this.t) {
            MediaPlayer mediaPlayer = this.o;
            if (mediaPlayer == null || this.u <= 0) {
                return;
            }
            mediaPlayer.setDisplay(this.f18939c);
            this.o.seekTo(this.u);
            return;
        }
        MediaPlayer mediaPlayer2 = this.o;
        if (mediaPlayer2 != null) {
            mediaPlayer2.setDisplay(this.f18939c);
            if (this.t) {
                return;
            }
            int i2 = this.u;
            if (i2 > 0) {
                this.o.seekTo(i2);
            }
            this.o.start();
            this.u = 0;
            return;
        }
        a(0);
        doCleanUp();
        try {
            MediaPlayer mediaPlayer3 = new MediaPlayer();
            this.o = mediaPlayer3;
            if (this.f18943g) {
                mediaPlayer3.setDataSource(this.f18937a, Uri.parse(this.f18940d));
            } else {
                if (this.f18945i != 0) {
                    fileInputStream = new FileInputStream(this.f18940d);
                    this.o.setDataSource(fileInputStream.getFD(), this.f18944h, this.f18945i);
                } else {
                    try {
                        AssetFileDescriptor openFd = getResources().getAssets().openFd(this.f18940d);
                        this.o.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                        openFd.close();
                    } catch (IOException unused) {
                        fileInputStream = new FileInputStream(this.f18940d);
                        this.o.setDataSource(fileInputStream.getFD());
                    }
                }
                fileInputStream.close();
            }
            this.o.setDisplay(this.f18939c);
            this.o.setScreenOnWhilePlaying(true);
            this.o.setOnBufferingUpdateListener(this);
            this.o.setOnCompletionListener(this);
            this.o.setOnPreparedListener(this);
            this.o.setOnVideoSizeChangedListener(this);
            this.o.setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(3).build());
            this.o.prepareAsync();
            this.y = new Z0(this);
            new Thread(this.y).start();
        } catch (Exception unused2) {
            a(2);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.v = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0042, code lost:
        if (r7 <= r3) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004a, code lost:
        if (r7 >= r3) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004c, code lost:
        r4 = (int) (r5 / r3);
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0050, code lost:
        r2 = (int) (r6 * r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0053, code lost:
        if (r8 == 0) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateVideoLayout() {
        /*
            r10 = this;
            android.media.MediaPlayer r0 = r10.o
            if (r0 != 0) goto L5
            return
        L5:
            android.content.Context r0 = r10.f18937a
            java.lang.String r1 = "window"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.util.DisplayMetrics r1 = new android.util.DisplayMetrics
            r1.<init>()
            android.view.Display r0 = r0.getDefaultDisplay()
            r0.getMetrics(r1)
            int r0 = r1.widthPixels
            if (r0 <= 0) goto L28
            int r1 = r1.heightPixels
            if (r1 <= 0) goto L28
            r10.k = r0
            r10.l = r1
        L28:
            int r0 = r10.k
            int r1 = r10.l
            boolean r2 = r10.q
            if (r2 == 0) goto L56
            int r2 = r10.m
            float r3 = (float) r2
            int r4 = r10.n
            float r5 = (float) r4
            float r3 = r3 / r5
            float r5 = (float) r0
            float r6 = (float) r1
            float r7 = r5 / r6
            int r8 = r10.f18942f
            r9 = 1
            if (r8 != r9) goto L45
            int r2 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r2 > 0) goto L50
            goto L4c
        L45:
            r9 = 2
            if (r8 != r9) goto L53
            int r2 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r2 < 0) goto L50
        L4c:
            float r5 = r5 / r3
            int r4 = (int) r5
            r2 = r0
            goto L58
        L50:
            float r6 = r6 * r3
            int r2 = (int) r6
            goto L57
        L53:
            if (r8 != 0) goto L56
            goto L58
        L56:
            r2 = r0
        L57:
            r4 = r1
        L58:
            if (r0 != r2) goto L5c
            if (r1 == r4) goto L6a
        L5c:
            android.widget.FrameLayout$LayoutParams r0 = new android.widget.FrameLayout$LayoutParams
            r1 = 17
            r0.<init>(r2, r4, r1)
            com.unity3d.player.a1 r1 = r10.f18946j
            android.view.SurfaceView r2 = r10.f18938b
            r1.updateViewLayout(r2, r0)
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.a1.updateVideoLayout():void");
    }
}
