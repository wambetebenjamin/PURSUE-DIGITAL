package com.bytedance.common.wschannel.channel.impl.ok;

import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
/* compiled from: Tls12SocketFactory.java */
/* loaded from: classes2.dex */
public class c extends SSLSocketFactory {

    /* renamed from: b  reason: collision with root package name */
    private static final String[] f5426b = {"TLSv1.2"};

    /* renamed from: a  reason: collision with root package name */
    final SSLSocketFactory f5427a;

    public c(SSLSocketFactory sSLSocketFactory) {
        this.f5427a = sSLSocketFactory;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.f5427a.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.f5427a.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i2, boolean z) {
        return a(this.f5427a.createSocket(socket, str, i2, z));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2) {
        return a(this.f5427a.createSocket(str, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2, InetAddress inetAddress, int i3) {
        return a(this.f5427a.createSocket(str, i2, inetAddress, i3));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2) {
        return a(this.f5427a.createSocket(inetAddress, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) {
        return a(this.f5427a.createSocket(inetAddress, i2, inetAddress2, i3));
    }

    private Socket a(Socket socket) {
        if (socket instanceof SSLSocket) {
            ((SSLSocket) socket).setEnabledProtocols(f5426b);
        }
        return socket;
    }
}
