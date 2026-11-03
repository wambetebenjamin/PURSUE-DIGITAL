package com.ixigua.touchtileimageview.configuration;

import android.animation.TimeInterpolator;
import android.graphics.Path;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.ixigua.touchtileimageview.utils.i;
/* compiled from: AppearOrDisappearAnimationConfiguration.java */
/* loaded from: classes9.dex */
public class a {
    public static final i STRAIGHT_PATH_MOTION = new i() { // from class: com.ixigua.touchtileimageview.configuration.a.1
        @Override // com.ixigua.touchtileimageview.utils.i
        public Path getPath(float f2, float f3, float f4, float f5) {
            Path path = new Path();
            path.moveTo(f2, f3);
            path.lineTo(f4, f5);
            return path;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private TimeInterpolator f17017a = new FastOutSlowInInterpolator();

    /* renamed from: b  reason: collision with root package name */
    private TimeInterpolator f17018b = new FastOutSlowInInterpolator();

    /* renamed from: c  reason: collision with root package name */
    private TimeInterpolator f17019c = new FastOutSlowInInterpolator();

    /* renamed from: d  reason: collision with root package name */
    private TimeInterpolator f17020d = new FastOutSlowInInterpolator();

    /* renamed from: e  reason: collision with root package name */
    private TimeInterpolator f17021e = new FastOutSlowInInterpolator();

    /* renamed from: f  reason: collision with root package name */
    private long f17022f = 250;

    /* renamed from: g  reason: collision with root package name */
    private long f17023g = 250;

    /* renamed from: h  reason: collision with root package name */
    private long f17024h = 250;

    /* renamed from: i  reason: collision with root package name */
    private long f17025i = 250;

    /* renamed from: j  reason: collision with root package name */
    private long f17026j = 250;
    private i k = STRAIGHT_PATH_MOTION;
    private boolean l = false;

    public void setTranslationInterpolator(TimeInterpolator timeInterpolator) {
        this.f17017a = timeInterpolator;
    }

    public void setScaleInterpolator(TimeInterpolator timeInterpolator) {
        this.f17018b = timeInterpolator;
    }

    public TimeInterpolator getTranslationInterpolator() {
        return this.f17017a;
    }

    public TimeInterpolator getScaleInterpolator() {
        return this.f17018b;
    }

    public long getTranslationDuration() {
        return this.f17022f;
    }

    public void setTranslationDuration(long j2) {
        this.f17022f = j2;
    }

    public TimeInterpolator getClipInterpolator() {
        return this.f17019c;
    }

    public void setClipInterpolator(TimeInterpolator timeInterpolator) {
        this.f17019c = timeInterpolator;
    }

    public TimeInterpolator getAlphaInterpolator() {
        return this.f17020d;
    }

    public void setAlphaInterpolator(TimeInterpolator timeInterpolator) {
        this.f17020d = timeInterpolator;
    }

    public TimeInterpolator getOriginImageDisappearAlphaInterpolator() {
        return this.f17021e;
    }

    public void setOriginImageDisappearAlphaInterpolator(TimeInterpolator timeInterpolator) {
        this.f17021e = timeInterpolator;
    }

    public long getScaleDuration() {
        return this.f17023g;
    }

    public void setScaleDuration(long j2) {
        this.f17023g = j2;
    }

    public long getClipDuration() {
        return this.f17024h;
    }

    public void setClipDuration(long j2) {
        this.f17024h = j2;
    }

    public long getAlphaDuration() {
        return this.f17025i;
    }

    public void setAlphaDuration(long j2) {
        this.f17025i = j2;
    }

    public long getOriginImageDisappearAlphaDuration() {
        return this.f17026j;
    }

    public void setOriginImageDisappearAlphaDuration(long j2) {
        this.f17026j = j2;
    }

    public i getPathMotion() {
        return this.k;
    }

    public void setPathMotion(i iVar) {
        this.k = iVar;
    }

    public boolean isOriginImageDisappearAlphaAnimationEnable() {
        return this.l;
    }

    public void setOriginImageDisappearAlphaAnimationEnable(boolean z) {
        this.l = true;
    }
}
