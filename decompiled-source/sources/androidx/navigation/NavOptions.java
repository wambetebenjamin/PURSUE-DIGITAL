package androidx.navigation;
/* loaded from: classes.dex */
public final class NavOptions {
    private int mEnterAnim;
    private int mExitAnim;
    private int mPopEnterAnim;
    private int mPopExitAnim;
    private int mPopUpTo;
    private boolean mPopUpToInclusive;
    private boolean mSingleTop;

    NavOptions(boolean z, int i2, boolean z2, int i3, int i4, int i5, int i6) {
        this.mSingleTop = z;
        this.mPopUpTo = i2;
        this.mPopUpToInclusive = z2;
        this.mEnterAnim = i3;
        this.mExitAnim = i4;
        this.mPopEnterAnim = i5;
        this.mPopExitAnim = i6;
    }

    public boolean shouldLaunchSingleTop() {
        return this.mSingleTop;
    }

    public int getPopUpTo() {
        return this.mPopUpTo;
    }

    public boolean isPopUpToInclusive() {
        return this.mPopUpToInclusive;
    }

    public int getEnterAnim() {
        return this.mEnterAnim;
    }

    public int getExitAnim() {
        return this.mExitAnim;
    }

    public int getPopEnterAnim() {
        return this.mPopEnterAnim;
    }

    public int getPopExitAnim() {
        return this.mPopExitAnim;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NavOptions navOptions = (NavOptions) obj;
        return this.mSingleTop == navOptions.mSingleTop && this.mPopUpTo == navOptions.mPopUpTo && this.mPopUpToInclusive == navOptions.mPopUpToInclusive && this.mEnterAnim == navOptions.mEnterAnim && this.mExitAnim == navOptions.mExitAnim && this.mPopEnterAnim == navOptions.mPopEnterAnim && this.mPopExitAnim == navOptions.mPopExitAnim;
    }

    public int hashCode() {
        return ((((((((((((shouldLaunchSingleTop() ? 1 : 0) * 31) + getPopUpTo()) * 31) + (isPopUpToInclusive() ? 1 : 0)) * 31) + getEnterAnim()) * 31) + getExitAnim()) * 31) + getPopEnterAnim()) * 31) + getPopExitAnim();
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        boolean mPopUpToInclusive;
        boolean mSingleTop;
        int mPopUpTo = -1;
        int mEnterAnim = -1;
        int mExitAnim = -1;
        int mPopEnterAnim = -1;
        int mPopExitAnim = -1;

        public Builder setLaunchSingleTop(boolean z) {
            this.mSingleTop = z;
            return this;
        }

        public Builder setPopUpTo(int i2, boolean z) {
            this.mPopUpTo = i2;
            this.mPopUpToInclusive = z;
            return this;
        }

        public Builder setEnterAnim(int i2) {
            this.mEnterAnim = i2;
            return this;
        }

        public Builder setExitAnim(int i2) {
            this.mExitAnim = i2;
            return this;
        }

        public Builder setPopEnterAnim(int i2) {
            this.mPopEnterAnim = i2;
            return this;
        }

        public Builder setPopExitAnim(int i2) {
            this.mPopExitAnim = i2;
            return this;
        }

        public NavOptions build() {
            return new NavOptions(this.mSingleTop, this.mPopUpTo, this.mPopUpToInclusive, this.mEnterAnim, this.mExitAnim, this.mPopEnterAnim, this.mPopExitAnim);
        }
    }
}
