package com.linecorp.a.a;

import android.os.Build;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes9.dex */
public final class a extends SSLSocketFactory {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f17224a = {"RC4", "DES", "PSK", "_DHE_"};

    /* renamed from: b  reason: collision with root package name */
    private final SSLSocketFactory f17225b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f17226c;

    /* renamed from: d  reason: collision with root package name */
    private Class<?> f17227d;

    /* renamed from: e  reason: collision with root package name */
    private Method f17228e;

    public a(SSLSocketFactory sSLSocketFactory) {
        this(sSLSocketFactory, (byte) 0);
    }

    private a(SSLSocketFactory sSLSocketFactory, byte b2) {
        this.f17226c = true;
        this.f17225b = sSLSocketFactory;
        this.f17226c = true;
        try {
            try {
                this.f17227d = Class.forName("com.android.org.conscrypt.OpenSSLSocketImpl");
            } catch (ClassNotFoundException unused) {
                this.f17227d = Class.forName("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            }
            this.f17228e = this.f17227d.getMethod("setHostname", String.class);
        } catch (ClassNotFoundException | NoSuchMethodException unused2) {
        }
    }

    private void a(Socket socket, String str) {
        Method method;
        if (!this.f17227d.isInstance(socket) || (method = this.f17228e) == null) {
            return;
        }
        try {
            method.invoke(socket, str);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final Socket createSocket(Socket socket, String str, int i2, boolean z) {
        Socket createSocket = this.f17225b.createSocket(socket, str, i2, z);
        a(createSocket, str);
        return a(createSocket);
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i2) {
        Socket createSocket = this.f17225b.createSocket(str, i2);
        a(createSocket, str);
        return a(createSocket);
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i2) {
        return a(this.f17225b.createSocket(inetAddress, i2));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) {
        return a(this.f17225b.createSocket(inetAddress, i2, inetAddress2, i3));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i2, InetAddress inetAddress, int i3) {
        Socket createSocket = this.f17225b.createSocket(str, i2, inetAddress, i3);
        a(createSocket, str);
        return a(createSocket);
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket() {
        return a(this.f17225b.createSocket());
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getDefaultCipherSuites() {
        if (this.f17226c) {
            return a(this.f17225b.getDefaultCipherSuites());
        }
        return this.f17225b.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getSupportedCipherSuites() {
        if (this.f17226c) {
            return a(this.f17225b.getSupportedCipherSuites());
        }
        return this.f17225b.getSupportedCipherSuites();
    }

    private Socket a(Socket socket) {
        String[] strArr;
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            if (Build.VERSION.SDK_INT < 16) {
                strArr = new String[]{"TLSv1"};
            } else {
                strArr = new String[]{"TLSv1.2"};
            }
            sSLSocket.setEnabledProtocols(strArr);
            if (this.f17226c) {
                sSLSocket.setEnabledCipherSuites(a(sSLSocket.getEnabledCipherSuites()));
            }
            return new b(sSLSocket, (byte) 0);
        }
        return socket;
    }

    private static String[] a(String[] strArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        for (String str : strArr) {
            for (String str2 : f17224a) {
                if (str.contains(str2)) {
                    arrayList.remove(str);
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class b extends C0310a {
        /* synthetic */ b(SSLSocket sSLSocket, byte b2) {
            this(sSLSocket);
        }

        private b(SSLSocket sSLSocket) {
            super(sSLSocket);
        }

        @Override // com.linecorp.a.a.a.C0310a, javax.net.ssl.SSLSocket
        public final void setEnabledProtocols(String[] strArr) {
            if (strArr != null && strArr.length == 1 && "SSLv3".equals(strArr[0])) {
                ArrayList arrayList = new ArrayList(Arrays.asList(this.f17229a.getEnabledProtocols()));
                if (arrayList.size() > 1) {
                    arrayList.remove("SSLv3");
                }
                strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            super.setEnabledProtocols(strArr);
        }
    }

    /* renamed from: com.linecorp.a.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    private static class C0310a extends SSLSocket {

        /* renamed from: a  reason: collision with root package name */
        protected final SSLSocket f17229a;

        C0310a(SSLSocket sSLSocket) {
            this.f17229a = sSLSocket;
        }

        @Override // javax.net.ssl.SSLSocket
        public String[] getSupportedCipherSuites() {
            return this.f17229a.getSupportedCipherSuites();
        }

        @Override // javax.net.ssl.SSLSocket
        public String[] getEnabledCipherSuites() {
            return this.f17229a.getEnabledCipherSuites();
        }

        @Override // javax.net.ssl.SSLSocket
        public void setEnabledCipherSuites(String[] strArr) {
            this.f17229a.setEnabledCipherSuites(strArr);
        }

        @Override // javax.net.ssl.SSLSocket
        public String[] getSupportedProtocols() {
            return this.f17229a.getSupportedProtocols();
        }

        @Override // javax.net.ssl.SSLSocket
        public String[] getEnabledProtocols() {
            return this.f17229a.getEnabledProtocols();
        }

        @Override // javax.net.ssl.SSLSocket
        public void setEnabledProtocols(String[] strArr) {
            this.f17229a.setEnabledProtocols(strArr);
        }

        @Override // javax.net.ssl.SSLSocket
        public SSLSession getSession() {
            return this.f17229a.getSession();
        }

        @Override // javax.net.ssl.SSLSocket
        public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.f17229a.addHandshakeCompletedListener(handshakeCompletedListener);
        }

        @Override // javax.net.ssl.SSLSocket
        public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.f17229a.removeHandshakeCompletedListener(handshakeCompletedListener);
        }

        @Override // javax.net.ssl.SSLSocket
        public void startHandshake() {
            this.f17229a.startHandshake();
        }

        @Override // javax.net.ssl.SSLSocket
        public void setUseClientMode(boolean z) {
            this.f17229a.setUseClientMode(z);
        }

        @Override // javax.net.ssl.SSLSocket
        public boolean getUseClientMode() {
            return this.f17229a.getUseClientMode();
        }

        @Override // javax.net.ssl.SSLSocket
        public void setNeedClientAuth(boolean z) {
            this.f17229a.setNeedClientAuth(z);
        }

        @Override // javax.net.ssl.SSLSocket
        public void setWantClientAuth(boolean z) {
            this.f17229a.setWantClientAuth(z);
        }

        @Override // javax.net.ssl.SSLSocket
        public boolean getNeedClientAuth() {
            return this.f17229a.getNeedClientAuth();
        }

        @Override // javax.net.ssl.SSLSocket
        public boolean getWantClientAuth() {
            return this.f17229a.getWantClientAuth();
        }

        @Override // javax.net.ssl.SSLSocket
        public void setEnableSessionCreation(boolean z) {
            this.f17229a.setEnableSessionCreation(z);
        }

        @Override // javax.net.ssl.SSLSocket
        public boolean getEnableSessionCreation() {
            return this.f17229a.getEnableSessionCreation();
        }

        @Override // java.net.Socket
        public void bind(SocketAddress socketAddress) {
            this.f17229a.bind(socketAddress);
        }

        @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            this.f17229a.close();
        }

        @Override // java.net.Socket
        public void connect(SocketAddress socketAddress) {
            this.f17229a.connect(socketAddress);
        }

        @Override // java.net.Socket
        public void connect(SocketAddress socketAddress, int i2) {
            this.f17229a.connect(socketAddress, i2);
        }

        @Override // java.net.Socket
        public SocketChannel getChannel() {
            return this.f17229a.getChannel();
        }

        @Override // java.net.Socket
        public InetAddress getInetAddress() {
            return this.f17229a.getInetAddress();
        }

        @Override // java.net.Socket
        public InputStream getInputStream() {
            return this.f17229a.getInputStream();
        }

        @Override // java.net.Socket
        public boolean getKeepAlive() {
            return this.f17229a.getKeepAlive();
        }

        @Override // java.net.Socket
        public InetAddress getLocalAddress() {
            return this.f17229a.getLocalAddress();
        }

        @Override // java.net.Socket
        public int getLocalPort() {
            return this.f17229a.getLocalPort();
        }

        @Override // java.net.Socket
        public SocketAddress getLocalSocketAddress() {
            return this.f17229a.getLocalSocketAddress();
        }

        @Override // java.net.Socket
        public boolean getOOBInline() {
            return this.f17229a.getOOBInline();
        }

        @Override // java.net.Socket
        public OutputStream getOutputStream() {
            return this.f17229a.getOutputStream();
        }

        @Override // java.net.Socket
        public int getPort() {
            return this.f17229a.getPort();
        }

        @Override // java.net.Socket
        public synchronized int getReceiveBufferSize() {
            return this.f17229a.getReceiveBufferSize();
        }

        @Override // java.net.Socket
        public SocketAddress getRemoteSocketAddress() {
            return this.f17229a.getRemoteSocketAddress();
        }

        @Override // java.net.Socket
        public boolean getReuseAddress() {
            return this.f17229a.getReuseAddress();
        }

        @Override // java.net.Socket
        public synchronized int getSendBufferSize() {
            return this.f17229a.getSendBufferSize();
        }

        @Override // java.net.Socket
        public int getSoLinger() {
            return this.f17229a.getSoLinger();
        }

        @Override // java.net.Socket
        public synchronized int getSoTimeout() {
            return this.f17229a.getSoTimeout();
        }

        @Override // java.net.Socket
        public boolean getTcpNoDelay() {
            return this.f17229a.getTcpNoDelay();
        }

        @Override // java.net.Socket
        public int getTrafficClass() {
            return this.f17229a.getTrafficClass();
        }

        @Override // java.net.Socket
        public boolean isBound() {
            return this.f17229a.isBound();
        }

        @Override // java.net.Socket
        public boolean isClosed() {
            return this.f17229a.isClosed();
        }

        @Override // java.net.Socket
        public boolean isConnected() {
            return this.f17229a.isConnected();
        }

        @Override // java.net.Socket
        public boolean isInputShutdown() {
            return this.f17229a.isInputShutdown();
        }

        @Override // java.net.Socket
        public boolean isOutputShutdown() {
            return this.f17229a.isOutputShutdown();
        }

        @Override // java.net.Socket
        public void sendUrgentData(int i2) {
            this.f17229a.sendUrgentData(i2);
        }

        @Override // java.net.Socket
        public void setKeepAlive(boolean z) {
            this.f17229a.setKeepAlive(z);
        }

        @Override // java.net.Socket
        public void setOOBInline(boolean z) {
            this.f17229a.setOOBInline(z);
        }

        @Override // java.net.Socket
        public void setPerformancePreferences(int i2, int i3, int i4) {
            this.f17229a.setPerformancePreferences(i2, i3, i4);
        }

        @Override // java.net.Socket
        public synchronized void setReceiveBufferSize(int i2) {
            this.f17229a.setReceiveBufferSize(i2);
        }

        @Override // java.net.Socket
        public void setReuseAddress(boolean z) {
            this.f17229a.setReuseAddress(z);
        }

        @Override // java.net.Socket
        public synchronized void setSendBufferSize(int i2) {
            this.f17229a.setSendBufferSize(i2);
        }

        @Override // java.net.Socket
        public void setSoLinger(boolean z, int i2) {
            this.f17229a.setSoLinger(z, i2);
        }

        @Override // java.net.Socket
        public synchronized void setSoTimeout(int i2) {
            this.f17229a.setSoTimeout(i2);
        }

        @Override // java.net.Socket
        public void setTcpNoDelay(boolean z) {
            this.f17229a.setTcpNoDelay(z);
        }

        @Override // java.net.Socket
        public void setTrafficClass(int i2) {
            this.f17229a.setTrafficClass(i2);
        }

        @Override // java.net.Socket
        public void shutdownInput() {
            this.f17229a.shutdownInput();
        }

        @Override // java.net.Socket
        public void shutdownOutput() {
            this.f17229a.shutdownOutput();
        }

        @Override // javax.net.ssl.SSLSocket, java.net.Socket
        public String toString() {
            return this.f17229a.toString();
        }

        public boolean equals(Object obj) {
            return this.f17229a.equals(obj);
        }
    }
}
