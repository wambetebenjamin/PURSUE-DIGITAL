package com.linecorp.linesdk.auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.linecorp.linesdk.BuildConfig;
/* loaded from: classes9.dex */
public class LineAuthenticationConfig implements Parcelable {
    private final String channelId;
    private final Uri endPointBaseUrl;
    private final boolean isEncryptorPreparationDisabled;
    private final boolean isLineAppAuthenticationDisabled;
    private final Uri webLoginPageUrl;
    public static final Parcelable.Creator<LineAuthenticationConfig> CREATOR = new Parcelable.Creator<LineAuthenticationConfig>() { // from class: com.linecorp.linesdk.auth.LineAuthenticationConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LineAuthenticationConfig createFromParcel(Parcel parcel) {
            return new LineAuthenticationConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LineAuthenticationConfig[] newArray(int i2) {
            return new LineAuthenticationConfig[i2];
        }
    };
    private static int FLAGS_LINE_APP_AUTHENTICATION_DISABLED = 1;
    private static int FLAGS_ENCRYPTOR_PREPARATION_DISABLED = 2;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private LineAuthenticationConfig(Builder builder) {
        this.channelId = builder.channelId;
        this.endPointBaseUrl = builder.endPointBaseUrl;
        this.webLoginPageUrl = builder.webLoginPageUrl;
        this.isLineAppAuthenticationDisabled = builder.isLineAppAuthenticationDisabled;
        this.isEncryptorPreparationDisabled = builder.isEncryptorPreparationDisabled;
    }

    private LineAuthenticationConfig(Parcel parcel) {
        this.channelId = parcel.readString();
        this.endPointBaseUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.webLoginPageUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        int readInt = parcel.readInt();
        this.isLineAppAuthenticationDisabled = (FLAGS_LINE_APP_AUTHENTICATION_DISABLED & readInt) > 0;
        this.isEncryptorPreparationDisabled = (readInt & FLAGS_ENCRYPTOR_PREPARATION_DISABLED) > 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.channelId);
        parcel.writeParcelable(this.endPointBaseUrl, i2);
        parcel.writeParcelable(this.webLoginPageUrl, i2);
        parcel.writeInt((this.isLineAppAuthenticationDisabled ? FLAGS_LINE_APP_AUTHENTICATION_DISABLED : 0) | 0 | (this.isEncryptorPreparationDisabled ? FLAGS_ENCRYPTOR_PREPARATION_DISABLED : 0));
    }

    public String getChannelId() {
        return this.channelId;
    }

    public Uri getEndPointBaseUrl() {
        return this.endPointBaseUrl;
    }

    public Uri getWebLoginPageUrl() {
        return this.webLoginPageUrl;
    }

    public boolean isLineAppAuthenticationDisabled() {
        return this.isLineAppAuthenticationDisabled;
    }

    public boolean isEncryptorPreparationDisabled() {
        return this.isEncryptorPreparationDisabled;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LineAuthenticationConfig lineAuthenticationConfig = (LineAuthenticationConfig) obj;
        if (this.isLineAppAuthenticationDisabled == lineAuthenticationConfig.isLineAppAuthenticationDisabled && this.isEncryptorPreparationDisabled == lineAuthenticationConfig.isEncryptorPreparationDisabled && this.channelId.equals(lineAuthenticationConfig.channelId) && this.endPointBaseUrl.equals(lineAuthenticationConfig.endPointBaseUrl)) {
            return this.webLoginPageUrl.equals(lineAuthenticationConfig.webLoginPageUrl);
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.channelId.hashCode() * 31) + this.endPointBaseUrl.hashCode()) * 31) + this.webLoginPageUrl.hashCode()) * 31) + (this.isLineAppAuthenticationDisabled ? 1 : 0)) * 31) + (this.isEncryptorPreparationDisabled ? 1 : 0);
    }

    public String toString() {
        return "LineAuthenticationConfig{channelId=" + this.channelId + ", endPointBaseUrl=" + this.endPointBaseUrl + ", webLoginPageUrl=" + this.webLoginPageUrl + ", isLineAppAuthenticationDisabled=" + this.isLineAppAuthenticationDisabled + ", isEncryptorPreparationDisabled=" + this.isEncryptorPreparationDisabled + '}';
    }

    /* loaded from: classes9.dex */
    public static class Builder {
        private final String channelId;
        private Uri endPointBaseUrl;
        private boolean isEncryptorPreparationDisabled;
        private boolean isLineAppAuthenticationDisabled;
        private Uri webLoginPageUrl;

        public Builder(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("channelId is empty.");
            }
            this.channelId = str;
            this.endPointBaseUrl = Uri.parse(BuildConfig.AUTH_SERVER_BASE_URI);
            this.webLoginPageUrl = Uri.parse(BuildConfig.WEB_LOGIN_PAGE_URL);
        }

        Builder endPointBaseUrl(Uri uri) {
            if (uri == null) {
                uri = Uri.parse(BuildConfig.AUTH_SERVER_BASE_URI);
            }
            this.endPointBaseUrl = uri;
            return this;
        }

        Builder webLoginPageUrl(Uri uri) {
            if (uri == null) {
                uri = Uri.parse(BuildConfig.WEB_LOGIN_PAGE_URL);
            }
            this.webLoginPageUrl = uri;
            return this;
        }

        public Builder disableLineAppAuthentication() {
            this.isLineAppAuthenticationDisabled = true;
            return this;
        }

        public Builder disableEncryptorPreparation() {
            this.isEncryptorPreparationDisabled = true;
            return this;
        }

        public LineAuthenticationConfig build() {
            return new LineAuthenticationConfig(this);
        }
    }
}
