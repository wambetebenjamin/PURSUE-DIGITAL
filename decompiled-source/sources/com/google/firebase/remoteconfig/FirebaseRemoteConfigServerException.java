package com.google.firebase.remoteconfig;

import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
/* loaded from: classes9.dex */
public class FirebaseRemoteConfigServerException extends FirebaseRemoteConfigException {
    private final int httpStatusCode;

    public FirebaseRemoteConfigServerException(int i2, String str) {
        super(str);
        this.httpStatusCode = i2;
    }

    public FirebaseRemoteConfigServerException(int i2, String str, Throwable th) {
        super(str, th);
        this.httpStatusCode = i2;
    }

    public FirebaseRemoteConfigServerException(String str, FirebaseRemoteConfigException.Code code) {
        super(str, code);
        this.httpStatusCode = -1;
    }

    public FirebaseRemoteConfigServerException(int i2, String str, FirebaseRemoteConfigException.Code code) {
        super(str, code);
        this.httpStatusCode = i2;
    }

    public FirebaseRemoteConfigServerException(String str, Throwable th, FirebaseRemoteConfigException.Code code) {
        super(str, th, code);
        this.httpStatusCode = -1;
    }

    public FirebaseRemoteConfigServerException(int i2, String str, Throwable th, FirebaseRemoteConfigException.Code code) {
        super(str, th, code);
        this.httpStatusCode = i2;
    }

    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }
}
