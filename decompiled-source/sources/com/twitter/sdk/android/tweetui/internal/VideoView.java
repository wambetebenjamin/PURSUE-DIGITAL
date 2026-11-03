package com.twitter.sdk.android.tweetui.internal;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.twitter.sdk.android.tweetui.internal.VideoControlView;
/* loaded from: classes10.dex */
public class VideoView extends SurfaceView implements VideoControlView.MediaPlayerControl {
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    private String TAG;
    private GestureDetector gestureDetector;
    private int mAudioSession;
    private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener;
    private MediaPlayer.OnCompletionListener mCompletionListener;
    private int mCurrentBufferPercentage;
    private int mCurrentState;
    private MediaPlayer.OnErrorListener mErrorListener;
    private MediaPlayer.OnInfoListener mInfoListener;
    private boolean mLooping;
    private VideoControlView mMediaController;
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;
    private MediaPlayer.OnErrorListener mOnErrorListener;
    private MediaPlayer.OnInfoListener mOnInfoListener;
    private MediaPlayer.OnPreparedListener mOnPreparedListener;
    MediaPlayer.OnPreparedListener mPreparedListener;
    SurfaceHolder.Callback mSHCallback;
    private int mSeekWhenPrepared;
    MediaPlayer.OnVideoSizeChangedListener mSizeChangedListener;
    private int mSurfaceHeight;
    private SurfaceHolder mSurfaceHolder;
    private int mSurfaceWidth;
    private int mTargetState;
    private Uri mUri;
    private int mVideoHeight;
    private int mVideoWidth;

    public VideoView(Context context) {
        super(context);
        this.TAG = "VideoView";
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.1
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                VideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                VideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (VideoView.this.mVideoWidth == 0 || VideoView.this.mVideoHeight == 0) {
                    return;
                }
                VideoView.this.getHolder().setFixedSize(VideoView.this.mVideoWidth, VideoView.this.mVideoHeight);
                VideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                VideoView.this.mCurrentState = 2;
                if (VideoView.this.mOnPreparedListener != null) {
                    VideoView.this.mOnPreparedListener.onPrepared(VideoView.this.mMediaPlayer);
                }
                if (VideoView.this.mMediaController != null) {
                    VideoView.this.mMediaController.setEnabled(true);
                }
                VideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                VideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                int i2 = VideoView.this.mSeekWhenPrepared;
                if (i2 != 0) {
                    VideoView.this.seekTo(i2);
                }
                if (VideoView.this.mVideoWidth == 0 || VideoView.this.mVideoHeight == 0) {
                    if (VideoView.this.mTargetState == 3) {
                        VideoView.this.start();
                        return;
                    }
                    return;
                }
                VideoView.this.getHolder().setFixedSize(VideoView.this.mVideoWidth, VideoView.this.mVideoHeight);
                if (VideoView.this.mSurfaceWidth == VideoView.this.mVideoWidth && VideoView.this.mSurfaceHeight == VideoView.this.mVideoHeight) {
                    if (VideoView.this.mTargetState == 3) {
                        VideoView.this.start();
                        if (VideoView.this.mMediaController != null) {
                            VideoView.this.mMediaController.show();
                        }
                    } else if (VideoView.this.isPlaying()) {
                    } else {
                        if ((i2 != 0 || VideoView.this.getCurrentPosition() > 0) && VideoView.this.mMediaController != null) {
                            VideoView.this.mMediaController.show();
                        }
                    }
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                VideoView.this.mCurrentState = 5;
                VideoView.this.mTargetState = 5;
                if (VideoView.this.mOnCompletionListener != null) {
                    VideoView.this.mOnCompletionListener.onCompletion(VideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.4
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
                if (VideoView.this.mOnInfoListener != null) {
                    VideoView.this.mOnInfoListener.onInfo(mediaPlayer, i2, i3);
                    return true;
                }
                return true;
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.5
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                Log.d(VideoView.this.TAG, "Error: " + i2 + "," + i3);
                VideoView.this.mCurrentState = -1;
                VideoView.this.mTargetState = -1;
                if (VideoView.this.mMediaController != null) {
                    VideoView.this.mMediaController.hide();
                }
                if (VideoView.this.mOnErrorListener != null) {
                    VideoView.this.mOnErrorListener.onError(VideoView.this.mMediaPlayer, i2, i3);
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.6
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
                VideoView.this.mCurrentBufferPercentage = i2;
            }
        };
        this.gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.7
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                if (!VideoView.this.isInPlaybackState() || VideoView.this.mMediaController == null) {
                    return false;
                }
                VideoView.this.toggleMediaControlsVisiblity();
                return false;
            }
        });
        this.mSHCallback = new SurfaceHolder.Callback() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.8
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
                VideoView.this.mSurfaceWidth = i3;
                VideoView.this.mSurfaceHeight = i4;
                boolean z = true;
                boolean z2 = VideoView.this.mTargetState == 3;
                if (VideoView.this.mVideoWidth != i3 || VideoView.this.mVideoHeight != i4) {
                    z = false;
                }
                if (VideoView.this.mMediaPlayer != null && z2 && z) {
                    if (VideoView.this.mSeekWhenPrepared != 0) {
                        VideoView videoView = VideoView.this;
                        videoView.seekTo(videoView.mSeekWhenPrepared);
                    }
                    VideoView.this.start();
                    if (VideoView.this.mMediaController != null) {
                        VideoView.this.mMediaController.show();
                    }
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                VideoView.this.mSurfaceHolder = surfaceHolder;
                VideoView.this.openVideo();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                VideoView.this.mSurfaceHolder = null;
                if (VideoView.this.mMediaController != null) {
                    VideoView.this.mMediaController.hide();
                }
                VideoView.this.release(true);
            }
        };
        initVideoView();
    }

    public VideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = "VideoView";
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.1
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i22, int i3) {
                VideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                VideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (VideoView.this.mVideoWidth == 0 || VideoView.this.mVideoHeight == 0) {
                    return;
                }
                VideoView.this.getHolder().setFixedSize(VideoView.this.mVideoWidth, VideoView.this.mVideoHeight);
                VideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                VideoView.this.mCurrentState = 2;
                if (VideoView.this.mOnPreparedListener != null) {
                    VideoView.this.mOnPreparedListener.onPrepared(VideoView.this.mMediaPlayer);
                }
                if (VideoView.this.mMediaController != null) {
                    VideoView.this.mMediaController.setEnabled(true);
                }
                VideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                VideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                int i22 = VideoView.this.mSeekWhenPrepared;
                if (i22 != 0) {
                    VideoView.this.seekTo(i22);
                }
                if (VideoView.this.mVideoWidth == 0 || VideoView.this.mVideoHeight == 0) {
                    if (VideoView.this.mTargetState == 3) {
                        VideoView.this.start();
                        return;
                    }
                    return;
                }
                VideoView.this.getHolder().setFixedSize(VideoView.this.mVideoWidth, VideoView.this.mVideoHeight);
                if (VideoView.this.mSurfaceWidth == VideoView.this.mVideoWidth && VideoView.this.mSurfaceHeight == VideoView.this.mVideoHeight) {
                    if (VideoView.this.mTargetState == 3) {
                        VideoView.this.start();
                        if (VideoView.this.mMediaController != null) {
                            VideoView.this.mMediaController.show();
                        }
                    } else if (VideoView.this.isPlaying()) {
                    } else {
                        if ((i22 != 0 || VideoView.this.getCurrentPosition() > 0) && VideoView.this.mMediaController != null) {
                            VideoView.this.mMediaController.show();
                        }
                    }
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                VideoView.this.mCurrentState = 5;
                VideoView.this.mTargetState = 5;
                if (VideoView.this.mOnCompletionListener != null) {
                    VideoView.this.mOnCompletionListener.onCompletion(VideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.4
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i22, int i3) {
                if (VideoView.this.mOnInfoListener != null) {
                    VideoView.this.mOnInfoListener.onInfo(mediaPlayer, i22, i3);
                    return true;
                }
                return true;
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.5
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i22, int i3) {
                Log.d(VideoView.this.TAG, "Error: " + i22 + "," + i3);
                VideoView.this.mCurrentState = -1;
                VideoView.this.mTargetState = -1;
                if (VideoView.this.mMediaController != null) {
                    VideoView.this.mMediaController.hide();
                }
                if (VideoView.this.mOnErrorListener != null) {
                    VideoView.this.mOnErrorListener.onError(VideoView.this.mMediaPlayer, i22, i3);
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.6
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i22) {
                VideoView.this.mCurrentBufferPercentage = i22;
            }
        };
        this.gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.7
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                if (!VideoView.this.isInPlaybackState() || VideoView.this.mMediaController == null) {
                    return false;
                }
                VideoView.this.toggleMediaControlsVisiblity();
                return false;
            }
        });
        this.mSHCallback = new SurfaceHolder.Callback() { // from class: com.twitter.sdk.android.tweetui.internal.VideoView.8
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i22, int i3, int i4) {
                VideoView.this.mSurfaceWidth = i3;
                VideoView.this.mSurfaceHeight = i4;
                boolean z = true;
                boolean z2 = VideoView.this.mTargetState == 3;
                if (VideoView.this.mVideoWidth != i3 || VideoView.this.mVideoHeight != i4) {
                    z = false;
                }
                if (VideoView.this.mMediaPlayer != null && z2 && z) {
                    if (VideoView.this.mSeekWhenPrepared != 0) {
                        VideoView videoView = VideoView.this;
                        videoView.seekTo(videoView.mSeekWhenPrepared);
                    }
                    VideoView.this.start();
                    if (VideoView.this.mMediaController != null) {
                        VideoView.this.mMediaController.show();
                    }
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                VideoView.this.mSurfaceHolder = surfaceHolder;
                VideoView.this.openVideo();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                VideoView.this.mSurfaceHolder = null;
                if (VideoView.this.mMediaController != null) {
                    VideoView.this.mMediaController.hide();
                }
                VideoView.this.release(true);
            }
        };
        initVideoView();
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x005d, code lost:
        if (r1 > r6) goto L16;
     */
    @Override // android.view.SurfaceView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r6, int r7) {
        /*
            r5 = this;
            int r0 = r5.mVideoWidth
            int r0 = getDefaultSize(r0, r6)
            int r1 = r5.mVideoHeight
            int r1 = getDefaultSize(r1, r7)
            int r2 = r5.mVideoWidth
            if (r2 <= 0) goto L7a
            int r2 = r5.mVideoHeight
            if (r2 <= 0) goto L7a
            int r0 = android.view.View.MeasureSpec.getMode(r6)
            int r6 = android.view.View.MeasureSpec.getSize(r6)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            int r7 = android.view.View.MeasureSpec.getSize(r7)
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 != r2) goto L41
            if (r1 != r2) goto L41
            int r0 = r5.mVideoWidth
            int r1 = r0 * r7
            int r2 = r5.mVideoHeight
            int r3 = r6 * r2
            if (r1 >= r3) goto L37
            int r0 = r0 * r7
            int r0 = r0 / r2
            goto L62
        L37:
            int r1 = r0 * r7
            int r3 = r6 * r2
            if (r1 <= r3) goto L5f
            int r2 = r2 * r6
            int r1 = r2 / r0
            goto L51
        L41:
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != r2) goto L53
            int r0 = r5.mVideoHeight
            int r0 = r0 * r6
            int r2 = r5.mVideoWidth
            int r0 = r0 / r2
            if (r1 != r3) goto L50
            if (r0 <= r7) goto L50
            goto L5f
        L50:
            r1 = r0
        L51:
            r0 = r6
            goto L7a
        L53:
            if (r1 != r2) goto L64
            int r1 = r5.mVideoWidth
            int r1 = r1 * r7
            int r2 = r5.mVideoHeight
            int r1 = r1 / r2
            if (r0 != r3) goto L61
            if (r1 <= r6) goto L61
        L5f:
            r0 = r6
            goto L62
        L61:
            r0 = r1
        L62:
            r1 = r7
            goto L7a
        L64:
            int r2 = r5.mVideoWidth
            int r4 = r5.mVideoHeight
            if (r1 != r3) goto L70
            if (r4 <= r7) goto L70
            int r1 = r7 * r2
            int r1 = r1 / r4
            goto L72
        L70:
            r1 = r2
            r7 = r4
        L72:
            if (r0 != r3) goto L61
            if (r1 <= r6) goto L61
            int r4 = r4 * r6
            int r1 = r4 / r2
            goto L51
        L7a:
            r5.setMeasuredDimension(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.twitter.sdk.android.tweetui.internal.VideoView.onMeasure(int, int):void");
    }

    private void initVideoView() {
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        getHolder().addCallback(this.mSHCallback);
        getHolder().setType(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setClickable(true);
        requestFocus();
        this.mCurrentState = 0;
        this.mTargetState = 0;
    }

    public void setVideoURI(Uri uri, boolean z) {
        this.mUri = uri;
        this.mLooping = z;
        this.mSeekWhenPrepared = 0;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void stopPlayback() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            this.mTargetState = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openVideo() {
        if (this.mUri == null || this.mSurfaceHolder == null) {
            return;
        }
        release(false);
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mMediaPlayer = mediaPlayer;
            int i2 = this.mAudioSession;
            if (i2 != 0) {
                mediaPlayer.setAudioSessionId(i2);
            } else {
                this.mAudioSession = mediaPlayer.getAudioSessionId();
            }
            this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
            this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
            this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
            this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
            this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
            this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
            this.mCurrentBufferPercentage = 0;
            this.mMediaPlayer.setLooping(this.mLooping);
            this.mMediaPlayer.setDataSource(getContext(), this.mUri);
            this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
            this.mMediaPlayer.setAudioStreamType(3);
            this.mMediaPlayer.setScreenOnWhilePlaying(true);
            this.mMediaPlayer.prepareAsync();
            this.mCurrentState = 1;
            attachMediaController();
        } catch (Exception e2) {
            Log.w(this.TAG, "Unable to open content: " + this.mUri, e2);
            this.mCurrentState = -1;
            this.mTargetState = -1;
            this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
        }
    }

    public void setMediaController(VideoControlView videoControlView) {
        VideoControlView videoControlView2 = this.mMediaController;
        if (videoControlView2 != null) {
            videoControlView2.hide();
        }
        this.mMediaController = videoControlView;
        attachMediaController();
    }

    private void attachMediaController() {
        VideoControlView videoControlView;
        if (this.mMediaPlayer == null || (videoControlView = this.mMediaController) == null) {
            return;
        }
        videoControlView.setMediaPlayer(this);
        this.mMediaController.setEnabled(isInPlaybackState());
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.gestureDetector.onTouchEvent(motionEvent) || super.onTouchEvent(motionEvent);
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnInfoListener(MediaPlayer.OnInfoListener onInfoListener) {
        this.mOnInfoListener = onInfoListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release(boolean z) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            if (z) {
                this.mTargetState = 0;
            }
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        boolean z = (i2 == 4 || i2 == 24 || i2 == 25 || i2 == 82 || i2 == 5 || i2 == 6) ? false : true;
        if (isInPlaybackState() && z && this.mMediaController != null) {
            if (i2 == 79 || i2 == 85) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                } else {
                    start();
                    this.mMediaController.hide();
                }
                return true;
            } else if (i2 == 126) {
                if (!this.mMediaPlayer.isPlaying()) {
                    start();
                    this.mMediaController.hide();
                }
                return true;
            } else if (i2 == 86 || i2 == 127) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                }
                return true;
            } else {
                toggleMediaControlsVisiblity();
            }
        }
        return super.onKeyDown(i2, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleMediaControlsVisiblity() {
        if (this.mMediaController.isShowing()) {
            this.mMediaController.hide();
        } else {
            this.mMediaController.show();
        }
    }

    @Override // com.twitter.sdk.android.tweetui.internal.VideoControlView.MediaPlayerControl
    public void start() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
        }
        this.mTargetState = 3;
    }

    @Override // com.twitter.sdk.android.tweetui.internal.VideoControlView.MediaPlayerControl
    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
        }
        this.mTargetState = 4;
    }

    @Override // com.twitter.sdk.android.tweetui.internal.VideoControlView.MediaPlayerControl
    public int getDuration() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getDuration();
        }
        return -1;
    }

    @Override // com.twitter.sdk.android.tweetui.internal.VideoControlView.MediaPlayerControl
    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // com.twitter.sdk.android.tweetui.internal.VideoControlView.MediaPlayerControl
    public void seekTo(int i2) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(i2);
            this.mSeekWhenPrepared = 0;
            return;
        }
        this.mSeekWhenPrepared = i2;
    }

    @Override // com.twitter.sdk.android.tweetui.internal.VideoControlView.MediaPlayerControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    @Override // com.twitter.sdk.android.tweetui.internal.VideoControlView.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isInPlaybackState() {
        int i2;
        return (this.mMediaPlayer == null || (i2 = this.mCurrentState) == -1 || i2 == 0 || i2 == 1) ? false : true;
    }
}
