package com.twitter.sdk.android.tweetui.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.twitter.sdk.android.tweetui.R;
/* loaded from: classes10.dex */
public class VideoControlView extends FrameLayout {
    static final int FADE_DURATION_MS = 150;
    static final long PROGRESS_BAR_TICKS = 1000;
    private static final int SHOW_PROGRESS_MSG = 1001;
    TextView currentTime;
    TextView duration;
    private final Handler handler;
    MediaPlayerControl player;
    SeekBar seekBar;
    ImageButton stateControl;

    /* loaded from: classes10.dex */
    public interface MediaPlayerControl {
        int getBufferPercentage();

        int getCurrentPosition();

        int getDuration();

        boolean isPlaying();

        void pause();

        void seekTo(int i2);

        void start();
    }

    public VideoControlView(Context context) {
        super(context);
        this.handler = new Handler() { // from class: com.twitter.sdk.android.tweetui.internal.VideoControlView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1001 || VideoControlView.this.player == null) {
                    return;
                }
                VideoControlView.this.updateProgress();
                VideoControlView.this.updateStateControl();
                if (VideoControlView.this.isShowing() && VideoControlView.this.player.isPlaying()) {
                    sendMessageDelayed(obtainMessage(1001), 500L);
                }
            }
        };
    }

    public VideoControlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.handler = new Handler() { // from class: com.twitter.sdk.android.tweetui.internal.VideoControlView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1001 || VideoControlView.this.player == null) {
                    return;
                }
                VideoControlView.this.updateProgress();
                VideoControlView.this.updateStateControl();
                if (VideoControlView.this.isShowing() && VideoControlView.this.player.isPlaying()) {
                    sendMessageDelayed(obtainMessage(1001), 500L);
                }
            }
        };
    }

    public VideoControlView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.handler = new Handler() { // from class: com.twitter.sdk.android.tweetui.internal.VideoControlView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1001 || VideoControlView.this.player == null) {
                    return;
                }
                VideoControlView.this.updateProgress();
                VideoControlView.this.updateStateControl();
                if (VideoControlView.this.isShowing() && VideoControlView.this.player.isPlaying()) {
                    sendMessageDelayed(obtainMessage(1001), 500L);
                }
            }
        };
    }

    public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
        this.player = mediaPlayerControl;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        initSubviews();
    }

    void initSubviews() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.tw__video_control, this);
        this.stateControl = (ImageButton) findViewById(R.id.tw__state_control);
        this.currentTime = (TextView) findViewById(R.id.tw__current_time);
        this.duration = (TextView) findViewById(R.id.tw__duration);
        SeekBar seekBar = (SeekBar) findViewById(R.id.tw__progress);
        this.seekBar = seekBar;
        seekBar.setMax(1000);
        this.seekBar.setOnSeekBarChangeListener(createProgressChangeListener());
        this.stateControl.setOnClickListener(createStateControlClickListener());
        setDuration(0);
        setCurrentTime(0);
        setProgress(0, 0, 0);
    }

    View.OnClickListener createStateControlClickListener() {
        return new View.OnClickListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoControlView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoControlView.this.m743x712b2297(view);
            }
        };
    }

    /* renamed from: lambda$createStateControlClickListener$0$com-twitter-sdk-android-tweetui-internal-VideoControlView  reason: not valid java name */
    public /* synthetic */ void m743x712b2297(View view) {
        if (this.player.isPlaying()) {
            this.player.pause();
        } else {
            this.player.start();
        }
        show();
    }

    SeekBar.OnSeekBarChangeListener createProgressChangeListener() {
        return new SeekBar.OnSeekBarChangeListener() { // from class: com.twitter.sdk.android.tweetui.internal.VideoControlView.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
                if (z) {
                    int duration = (int) ((VideoControlView.this.player.getDuration() * i2) / 1000);
                    VideoControlView.this.player.seekTo(duration);
                    VideoControlView.this.setCurrentTime(duration);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                VideoControlView.this.handler.removeMessages(1001);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                VideoControlView.this.handler.sendEmptyMessage(1001);
            }
        };
    }

    void updateProgress() {
        int duration = this.player.getDuration();
        int currentPosition = this.player.getCurrentPosition();
        int bufferPercentage = this.player.getBufferPercentage();
        setDuration(duration);
        setCurrentTime(currentPosition);
        setProgress(currentPosition, duration, bufferPercentage);
    }

    void setDuration(int i2) {
        this.duration.setText(MediaTimeUtils.getPlaybackTime(i2));
    }

    void setCurrentTime(int i2) {
        this.currentTime.setText(MediaTimeUtils.getPlaybackTime(i2));
    }

    void setProgress(int i2, int i3, int i4) {
        this.seekBar.setProgress((int) (i3 > 0 ? (i2 * 1000) / i3 : 0L));
        this.seekBar.setSecondaryProgress(i4 * 10);
    }

    void updateStateControl() {
        if (this.player.isPlaying()) {
            setPauseDrawable();
        } else if (this.player.getCurrentPosition() > Math.max(this.player.getDuration() - 500, 0)) {
            setReplayDrawable();
        } else {
            setPlayDrawable();
        }
    }

    void setPlayDrawable() {
        this.stateControl.setImageResource(R.drawable.tw__video_play_btn);
        this.stateControl.setContentDescription(getContext().getString(R.string.tw__play));
    }

    void setPauseDrawable() {
        this.stateControl.setImageResource(R.drawable.tw__video_pause_btn);
        this.stateControl.setContentDescription(getContext().getString(R.string.tw__pause));
    }

    void setReplayDrawable() {
        this.stateControl.setImageResource(R.drawable.tw__video_replay_btn);
        this.stateControl.setContentDescription(getContext().getString(R.string.tw__replay));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void hide() {
        this.handler.removeMessages(1001);
        AnimationUtils.fadeOut(this, 150);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void show() {
        this.handler.sendEmptyMessage(1001);
        AnimationUtils.fadeIn(this, 150);
    }

    public boolean isShowing() {
        return getVisibility() == 0;
    }

    public void update() {
        this.handler.sendEmptyMessage(1001);
    }
}
