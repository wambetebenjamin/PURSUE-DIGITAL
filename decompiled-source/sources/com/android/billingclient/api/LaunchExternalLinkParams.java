package com.android.billingclient.api;

import android.net.Uri;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class LaunchExternalLinkParams {
    private final Uri zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes.dex */
    public static final class Builder {
        private Uri zza;
        private int zzb = 0;
        private int zzc = 0;
        private int zzd = 0;

        private Builder() {
        }

        /* synthetic */ Builder(zzdn zzdnVar) {
        }

        public LaunchExternalLinkParams build() {
            int i2 = this.zzc;
            if (i2 != 0) {
                int i3 = this.zzb;
                if (i3 != 0) {
                    if (i3 == 1 || i2 != 2) {
                        if (this.zzd != 0) {
                            Uri uri = this.zza;
                            if (uri == null) {
                                throw new IllegalArgumentException("URI must be set.");
                            }
                            if (uri.getScheme() == null) {
                                throw new IllegalArgumentException("URI must have a scheme.");
                            }
                            return new LaunchExternalLinkParams(this.zza, this.zzb, this.zzc, this.zzd, null);
                        }
                        throw new IllegalArgumentException("Billing program is required.");
                    }
                    throw new IllegalArgumentException("App downloads must launch in an external browser or app.");
                }
                throw new IllegalArgumentException("Launch mode is required.");
            }
            throw new IllegalArgumentException("Link type is required.");
        }

        public Builder setBillingProgram(int i2) {
            this.zzd = i2;
            return this;
        }

        public Builder setLaunchMode(int i2) {
            this.zzb = i2;
            return this;
        }

        public Builder setLinkType(int i2) {
            this.zzc = i2;
            return this;
        }

        public Builder setLinkUri(Uri uri) {
            this.zza = uri;
            return this;
        }
    }

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface LaunchMode {
        public static final int CALLER_WILL_LAUNCH_LINK = 2;
        public static final int LAUNCH_IN_EXTERNAL_BROWSER_OR_APP = 1;
        public static final int LAUNCH_MODE_UNSPECIFIED = 0;
    }

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface LinkType {
        public static final int LINK_TO_APP_DOWNLOAD = 2;
        public static final int LINK_TO_DIGITAL_CONTENT_OFFER = 1;
        public static final int LINK_TYPE_UNSPECIFIED = 0;
    }

    /* synthetic */ LaunchExternalLinkParams(Uri uri, int i2, int i3, int i4, zzdn zzdnVar) {
        this.zza = uri;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = i4;
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public int getBillingProgram() {
        return this.zzd;
    }

    public int getLaunchMode() {
        return this.zzb;
    }

    public int getLinkType() {
        return this.zzc;
    }

    public Uri getLinkUri() {
        return this.zza;
    }
}
