package com.google.firebase.emulators;
/* loaded from: classes9.dex */
public final class EmulatedServiceSettings {
    private final String host;
    private final int port;

    public EmulatedServiceSettings(String str, int i2) {
        this.host = str;
        this.port = i2;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }
}
