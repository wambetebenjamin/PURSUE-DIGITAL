package com.yqritc.scalablevideoview;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes10.dex */
public class ScalableVideoView extends TextureView implements TextureView.SurfaceTextureListener, MediaPlayer.OnVideoSizeChangedListener {
    protected MediaPlayer mMediaPlayer;
    protected ScalableType mScalableType;

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public ScalableVideoView(Context context) {
        this(context, null);
    }

    public ScalableVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScalableVideoView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray obtainStyledAttributes;
        this.mScalableType = ScalableType.NONE;
        if (attributeSet == null || (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.scaleStyle, 0, 0)) == null) {
            return;
        }
        int i3 = obtainStyledAttributes.getInt(R.styleable.scaleStyle_scalableType, ScalableType.NONE.ordinal());
        obtainStyledAttributes.recycle();
        this.mScalableType = ScalableType.values()[i3];
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        Surface surface = new Surface(surfaceTexture);
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setSurface(surface);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mMediaPlayer == null) {
            return;
        }
        if (isPlaying()) {
            stop();
        }
        release();
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
        scaleVideoSize(i2, i3);
    }

    private void scaleVideoSize(int i2, int i3) {
        Matrix scaleMatrix;
        if (i2 == 0 || i3 == 0 || (scaleMatrix = new ScaleManager(new Size(getWidth(), getHeight()), new Size(i2, i3)).getScaleMatrix(this.mScalableType)) == null) {
            return;
        }
        setTransform(scaleMatrix);
    }

    private void initializeMediaPlayer() {
        if (this.mMediaPlayer == null) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mMediaPlayer = mediaPlayer;
            mediaPlayer.setOnVideoSizeChangedListener(this);
            setSurfaceTextureListener(this);
            return;
        }
        reset();
    }

    public void setRawData(int i2) throws IOException {
        setDataSource(getResources().openRawResourceFd(i2));
    }

    public void setAssetData(String str) throws IOException {
        setDataSource(getContext().getAssets().openFd(str));
    }

    private void setDataSource(AssetFileDescriptor assetFileDescriptor) throws IOException {
        setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        assetFileDescriptor.close();
    }

    public void setDataSource(String str) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(str);
    }

    public void setDataSource(Context context, Uri uri, Map<String, String> map) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(context, uri, map);
    }

    public void setDataSource(Context context, Uri uri) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(context, uri);
    }

    public void setDataSource(FileDescriptor fileDescriptor, long j2, long j3) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(fileDescriptor, j2, j3);
    }

    public void setDataSource(FileDescriptor fileDescriptor) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(fileDescriptor);
    }

    public void setScalableType(ScalableType scalableType) {
        this.mScalableType = scalableType;
        scaleVideoSize(getVideoWidth(), getVideoHeight());
    }

    public void prepare(MediaPlayer.OnPreparedListener onPreparedListener) throws IOException, IllegalStateException {
        this.mMediaPlayer.setOnPreparedListener(onPreparedListener);
        this.mMediaPlayer.prepare();
    }

    public void prepareAsync(MediaPlayer.OnPreparedListener onPreparedListener) throws IllegalStateException {
        this.mMediaPlayer.setOnPreparedListener(onPreparedListener);
        this.mMediaPlayer.prepareAsync();
    }

    public void prepare() throws IOException, IllegalStateException {
        prepare(null);
    }

    public void prepareAsync() throws IllegalStateException {
        prepareAsync(null);
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.mMediaPlayer.setOnErrorListener(onErrorListener);
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.mMediaPlayer.setOnCompletionListener(onCompletionListener);
    }

    public void setOnInfoListener(MediaPlayer.OnInfoListener onInfoListener) {
        this.mMediaPlayer.setOnInfoListener(onInfoListener);
    }

    public int getCurrentPosition() {
        return this.mMediaPlayer.getCurrentPosition();
    }

    public int getDuration() {
        return this.mMediaPlayer.getDuration();
    }

    public int getVideoHeight() {
        return this.mMediaPlayer.getVideoHeight();
    }

    public int getVideoWidth() {
        return this.mMediaPlayer.getVideoWidth();
    }

    public boolean isLooping() {
        return this.mMediaPlayer.isLooping();
    }

    public boolean isPlaying() {
        return this.mMediaPlayer.isPlaying();
    }

    public void pause() {
        this.mMediaPlayer.pause();
    }

    public void seekTo(int i2) {
        this.mMediaPlayer.seekTo(i2);
    }

    public void setLooping(boolean z) {
        this.mMediaPlayer.setLooping(z);
    }

    public void setVolume(float f2, float f3) {
        this.mMediaPlayer.setVolume(f2, f3);
    }

    public void start() {
        this.mMediaPlayer.start();
    }

    public void stop() {
        this.mMediaPlayer.stop();
    }

    public void reset() {
        this.mMediaPlayer.reset();
    }

    public void release() {
        reset();
        this.mMediaPlayer.release();
        this.mMediaPlayer = null;
    }
}
