package com.bytedance.ttgame.gecko;

import android.os.Build;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
/* loaded from: classes3.dex */
public class SSLSocketFactoryCompat extends SSLSocketFactory {
    static String[] cipherSuites;
    static String[] protocols;
    private SSLSocketFactory defaultFactory;

    static {
        String[] supportedProtocols;
        try {
            SSLSocket sSLSocket = (SSLSocket) SSLSocketFactory.getDefault().createSocket();
            if (sSLSocket != null) {
                LinkedList linkedList = new LinkedList();
                for (String str : sSLSocket.getSupportedProtocols()) {
                    if (!str.toUpperCase().contains("SSL")) {
                        linkedList.add(str);
                    }
                }
                protocols = (String[]) linkedList.toArray(new String[linkedList.size()]);
                if (Build.VERSION.SDK_INT < 21) {
                    List asList = Arrays.asList("TLS_RSA_WITH_AES_256_GCM_SHA384", "TLS_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_ECHDE_RSA_WITH_AES_128_GCM_SHA256", "TLS_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
                    List asList2 = Arrays.asList(sSLSocket.getSupportedCipherSuites());
                    HashSet hashSet = new HashSet(asList);
                    hashSet.retainAll(asList2);
                    hashSet.addAll(new HashSet(Arrays.asList(sSLSocket.getEnabledCipherSuites())));
                    cipherSuites = (String[]) hashSet.toArray(new String[hashSet.size()]);
                }
            }
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public SSLSocketFactoryCompat(X509TrustManager x509TrustManager) {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, x509TrustManager != null ? new X509TrustManager[]{x509TrustManager} : null, null);
            this.defaultFactory = sSLContext.getSocketFactory();
        } catch (GeneralSecurityException e2) {
            e2.printStackTrace();
        }
    }

    private void upgradeTLS(SSLSocket sSLSocket) {
        String[] strArr;
        String[] strArr2 = protocols;
        if (strArr2 != null) {
            sSLSocket.setEnabledProtocols(strArr2);
        }
        if (Build.VERSION.SDK_INT >= 21 || (strArr = cipherSuites) == null) {
            return;
        }
        sSLSocket.setEnabledCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return cipherSuites;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return cipherSuites;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i2, boolean z) {
        Socket createSocket = this.defaultFactory.createSocket(socket, str, i2, z);
        if (createSocket instanceof SSLSocket) {
            upgradeTLS((SSLSocket) createSocket);
        }
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2) {
        Socket createSocket = this.defaultFactory.createSocket(str, i2);
        if (createSocket instanceof SSLSocket) {
            upgradeTLS((SSLSocket) createSocket);
        }
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2, InetAddress inetAddress, int i3) {
        Socket createSocket = this.defaultFactory.createSocket(str, i2, inetAddress, i3);
        if (createSocket instanceof SSLSocket) {
            upgradeTLS((SSLSocket) createSocket);
        }
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2) {
        Socket createSocket = this.defaultFactory.createSocket(inetAddress, i2);
        if (createSocket instanceof SSLSocket) {
            upgradeTLS((SSLSocket) createSocket);
        }
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) {
        Socket createSocket = this.defaultFactory.createSocket(inetAddress, i2, inetAddress2, i3);
        if (createSocket instanceof SSLSocket) {
            upgradeTLS((SSLSocket) createSocket);
        }
        return createSocket;
    }
}
