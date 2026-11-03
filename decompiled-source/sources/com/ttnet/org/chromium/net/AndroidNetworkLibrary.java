package com.ttnet.org.chromium.net;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.RouteInfo;
import android.net.TrafficStats;
import android.net.TransportInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.ttnet.org.chromium.base.ApiCompatibilityUtils;
import com.ttnet.org.chromium.base.CollectionUtil;
import com.ttnet.org.chromium.base.ContextUtils;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.compat.ApiHelperForM;
import com.ttnet.org.chromium.base.compat.ApiHelperForN;
import com.ttnet.org.chromium.base.compat.ApiHelperForP;
import com.ttnet.org.chromium.base.compat.ApiHelperForQ;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.URLConnection;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
@MainDex
/* loaded from: classes9.dex */
public class AndroidNetworkLibrary {
    private static final String TAG = "AndroidNetworkLibrary";
    private static Boolean sHaveAccessLocationState;
    private static Boolean sHaveAccessNetworkState;
    private static Boolean sHaveAccessWifiState;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        void onCellularAlwaysUp(boolean z, int i2);
    }

    public static String getWifiSSID() {
        return "";
    }

    AndroidNetworkLibrary() {
    }

    public static String getMimeTypeFromExtension(String str) {
        return URLConnection.guessContentTypeFromName("foo." + str);
    }

    public static boolean haveOnlyLoopbackAddresses() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return false;
            }
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                try {
                    if (nextElement.isUp() && !nextElement.isLoopback()) {
                        return false;
                    }
                } catch (SocketException unused) {
                }
            }
            return true;
        } catch (Exception e2) {
            Log.w(TAG, "could not get network interfaces: " + e2);
            return false;
        }
    }

    public static AndroidCertVerifyResult verifyServerCertificates(byte[][] bArr, String str, String str2) {
        try {
            return X509Util.verifyServerCertificates(bArr, str, str2);
        } catch (IllegalArgumentException unused) {
            return new AndroidCertVerifyResult(-1);
        } catch (KeyStoreException unused2) {
            return new AndroidCertVerifyResult(-1);
        } catch (NoSuchAlgorithmException unused3) {
            return new AndroidCertVerifyResult(-1);
        }
    }

    public static void addTestRootCertificate(byte[] bArr) {
        X509Util.addTestRootCertificate(bArr);
    }

    public static void clearTestRootCertificates() {
        X509Util.clearTestRootCertificates();
    }

    private static boolean getIsCaptivePortal() {
        ConnectivityManager connectivityManager;
        Network activeNetwork;
        NetworkCapabilities networkCapabilities;
        return (Build.VERSION.SDK_INT < 23 || (connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity")) == null || (activeNetwork = ApiHelperForM.getActiveNetwork(connectivityManager)) == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null || !networkCapabilities.hasCapability(17)) ? false : true;
    }

    private static WifiInfo getWifiInfo() {
        TransportInfo transportInfo;
        if (haveAccessWifiState()) {
            if (Build.VERSION.SDK_INT >= 31) {
                ConnectivityManager connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity");
                for (Network network : connectivityManager.getAllNetworks()) {
                    NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                    if (networkCapabilities != null && networkCapabilities.hasTransport(1) && (transportInfo = ApiHelperForQ.getTransportInfo(networkCapabilities)) != null && (transportInfo instanceof WifiInfo)) {
                        return (WifiInfo) transportInfo;
                    }
                }
                return null;
            }
            return ((WifiManager) ContextUtils.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        }
        Intent registerReceiver = ContextUtils.getApplicationContext().registerReceiver(null, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        if (registerReceiver != null) {
            return (WifiInfo) registerReceiver.getParcelableExtra("wifiInfo");
        }
        return null;
    }

    public static void setWifiEnabled(boolean z) {
        ((WifiManager) ContextUtils.getApplicationContext().getSystemService("wifi")).setWifiEnabled(z);
    }

    public static int getWifiSignalLevel(int i2) {
        int intExtra;
        int calculateSignalLevel;
        if (ContextUtils.getApplicationContext() == null || ContextUtils.getApplicationContext().getContentResolver() == null) {
            return -1;
        }
        if (TTAppSecurityManager.getInstance().isTTPRestrictionEnabled()) {
            intExtra = TTAppSecurityManager.getInstance().getWifiRssi();
        } else if (haveAccessWifiState()) {
            WifiInfo wifiInfo = getWifiInfo();
            if (wifiInfo == null) {
                return -1;
            }
            intExtra = wifiInfo.getRssi();
        } else {
            try {
                Intent registerReceiver = ContextUtils.getApplicationContext().registerReceiver(null, new IntentFilter("android.net.wifi.RSSI_CHANGED"));
                if (registerReceiver == null) {
                    return -1;
                }
                intExtra = registerReceiver.getIntExtra("newRssi", Integer.MIN_VALUE);
            } catch (IllegalArgumentException unused) {
            }
        }
        if (intExtra != Integer.MIN_VALUE && (calculateSignalLevel = WifiManager.calculateSignalLevel(intExtra, i2)) >= 0 && calculateSignalLevel < i2) {
            return calculateSignalLevel;
        }
        return -1;
    }

    /* loaded from: classes9.dex */
    public static class NetworkSecurityPolicyProxy {
        private static NetworkSecurityPolicyProxy sInstance = new NetworkSecurityPolicyProxy();

        public static NetworkSecurityPolicyProxy getInstance() {
            return sInstance;
        }

        public static void setInstanceForTesting(NetworkSecurityPolicyProxy networkSecurityPolicyProxy) {
            sInstance = networkSecurityPolicyProxy;
        }

        public boolean isCleartextTrafficPermitted(String str) {
            if (Build.VERSION.SDK_INT < 24) {
                return isCleartextTrafficPermitted();
            }
            return ApiHelperForN.isCleartextTrafficPermitted(str);
        }

        public boolean isCleartextTrafficPermitted() {
            if (Build.VERSION.SDK_INT < 23) {
                return true;
            }
            return ApiHelperForM.isCleartextTrafficPermitted();
        }
    }

    private static boolean isCleartextPermitted(String str) {
        try {
            return NetworkSecurityPolicyProxy.getInstance().isCleartextTrafficPermitted(str);
        } catch (IllegalArgumentException unused) {
            return NetworkSecurityPolicyProxy.getInstance().isCleartextTrafficPermitted();
        }
    }

    private static boolean haveAccessNetworkState() {
        if (sHaveAccessNetworkState == null) {
            sHaveAccessNetworkState = Boolean.valueOf(hasPermission(ContextUtils.getApplicationContext(), "android.permission.ACCESS_NETWORK_STATE"));
        }
        return sHaveAccessNetworkState.booleanValue();
    }

    private static boolean haveAccessWifiState() {
        if (sHaveAccessWifiState == null) {
            sHaveAccessWifiState = Boolean.valueOf(hasPermission(ContextUtils.getApplicationContext(), "android.permission.ACCESS_WIFI_STATE"));
        }
        return sHaveAccessWifiState.booleanValue();
    }

    private static boolean haveAccessLocationState() {
        if (sHaveAccessLocationState == null) {
            if (Build.VERSION.SDK_INT >= 29) {
                sHaveAccessLocationState = Boolean.valueOf(hasPermission(ContextUtils.getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION"));
            } else {
                sHaveAccessLocationState = Boolean.valueOf(hasPermission(ContextUtils.getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") || hasPermission(ContextUtils.getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION"));
            }
        }
        return sHaveAccessLocationState.booleanValue();
    }

    private static boolean hasPermission(Context context, String str) {
        return ApiCompatibilityUtils.checkPermission(context, str, Process.myPid(), Process.myUid()) == 0;
    }

    public static DnsStatus getDnsStatusForNetwork(long j2) {
        try {
            return getDnsStatus(Network.fromNetworkHandle(j2));
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static DnsStatus getCurrentDnsStatus() {
        return getDnsStatus(null);
    }

    public static DnsStatus getDnsStatus(Network network) {
        ConnectivityManager connectivityManager;
        if (haveAccessNetworkState() && (connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity")) != null) {
            if (network == null) {
                network = ApiHelperForM.getActiveNetwork(connectivityManager);
            }
            if (network == null) {
                return null;
            }
            try {
                LinkProperties linkProperties = connectivityManager.getLinkProperties(network);
                if (linkProperties == null) {
                    return null;
                }
                List<InetAddress> dnsServers = linkProperties.getDnsServers();
                String domains = linkProperties.getDomains();
                if (Build.VERSION.SDK_INT >= 28) {
                    return new DnsStatus(dnsServers, ApiHelperForP.isPrivateDnsActive(linkProperties), ApiHelperForP.getPrivateDnsServerName(linkProperties), domains);
                }
                return new DnsStatus(dnsServers, false, "", domains);
            } catch (RuntimeException unused) {
                return null;
            }
        }
        return null;
    }

    private static boolean reportBadDefaultNetwork() {
        ConnectivityManager connectivityManager;
        if (Build.VERSION.SDK_INT >= 23 && (connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity")) != null) {
            ApiHelperForM.reportNetworkConnectivity(connectivityManager, null, false);
            return true;
        }
        return false;
    }

    /* loaded from: classes9.dex */
    private static class SetFileDescriptor {
        private static final Method sFileDescriptorSetInt;

        private SetFileDescriptor() {
        }

        static {
            try {
                sFileDescriptorSetInt = FileDescriptor.class.getMethod("setInt$", Integer.TYPE);
            } catch (NoSuchMethodException | SecurityException e2) {
                throw new RuntimeException("Unable to get FileDescriptor.setInt$", e2);
            }
        }

        public static FileDescriptor createWithFd(int i2) {
            try {
                FileDescriptor fileDescriptor = new FileDescriptor();
                sFileDescriptorSetInt.invoke(fileDescriptor, Integer.valueOf(i2));
                return fileDescriptor;
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("FileDescriptor.setInt$() failed", e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("FileDescriptor.setInt$() failed", e3);
            }
        }
    }

    /* loaded from: classes9.dex */
    private static class SocketFd extends Socket {

        /* loaded from: classes9.dex */
        private static class SocketImplFd extends SocketImpl {
            @Override // java.net.SocketImpl
            protected void close() {
            }

            @Override // java.net.SocketImpl
            protected void create(boolean z) {
            }

            SocketImplFd(FileDescriptor fileDescriptor) {
                this.fd = fileDescriptor;
            }

            @Override // java.net.SocketImpl
            protected void accept(SocketImpl socketImpl) {
                throw new RuntimeException("accept not implemented");
            }

            @Override // java.net.SocketImpl
            protected int available() {
                throw new RuntimeException("accept not implemented");
            }

            @Override // java.net.SocketImpl
            protected void bind(InetAddress inetAddress, int i2) {
                throw new RuntimeException("accept not implemented");
            }

            @Override // java.net.SocketImpl
            protected void connect(InetAddress inetAddress, int i2) {
                throw new RuntimeException("connect not implemented");
            }

            @Override // java.net.SocketImpl
            protected void connect(SocketAddress socketAddress, int i2) {
                throw new RuntimeException("connect not implemented");
            }

            @Override // java.net.SocketImpl
            protected void connect(String str, int i2) {
                throw new RuntimeException("connect not implemented");
            }

            @Override // java.net.SocketImpl
            protected InputStream getInputStream() {
                throw new RuntimeException("getInputStream not implemented");
            }

            @Override // java.net.SocketImpl
            protected OutputStream getOutputStream() {
                throw new RuntimeException("getOutputStream not implemented");
            }

            @Override // java.net.SocketImpl
            protected void listen(int i2) {
                throw new RuntimeException("listen not implemented");
            }

            @Override // java.net.SocketImpl
            protected void sendUrgentData(int i2) {
                throw new RuntimeException("sendUrgentData not implemented");
            }

            @Override // java.net.SocketOptions
            public Object getOption(int i2) {
                throw new RuntimeException("getOption not implemented");
            }

            @Override // java.net.SocketOptions
            public void setOption(int i2, Object obj) {
                throw new RuntimeException("setOption not implemented");
            }
        }

        SocketFd(FileDescriptor fileDescriptor) {
            super(new SocketImplFd(fileDescriptor));
        }
    }

    private static void alwaysUpCellularOnThreadPool() {
        if (Build.VERSION.SDK_INT < 21) {
            AndroidNetworkLibraryJni.get().onCellularAlwaysUp(false, 0);
            return;
        }
        final ConnectivityManager connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            AndroidNetworkLibraryJni.get().onCellularAlwaysUp(false, 7);
            return;
        }
        try {
            connectivityManager.requestNetwork(new NetworkRequest.Builder().addCapability(12).addTransportType(0).build(), new ConnectivityManager.NetworkCallback() { // from class: com.ttnet.org.chromium.net.AndroidNetworkLibrary.1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                    if (networkCapabilities != null && networkCapabilities.hasTransport(0) && networkCapabilities.hasCapability(12)) {
                        AndroidNetworkLibraryJni.get().onCellularAlwaysUp(true, -1);
                    } else {
                        AndroidNetworkLibraryJni.get().onCellularAlwaysUp(false, 8);
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            AndroidNetworkLibraryJni.get().onCellularAlwaysUp(false, 9);
        }
    }

    public static int[] getDhcpInfo() {
        DhcpInfo dhcpInfo;
        if (TTAppSecurityManager.getInstance().isUserPrivacyRestrictionEnabled()) {
            return new int[0];
        }
        try {
            WifiManager wifiManager = (WifiManager) ContextUtils.getApplicationContext().getSystemService("wifi");
            if (wifiManager != null && (dhcpInfo = wifiManager.getDhcpInfo()) != null) {
                return new int[]{dhcpInfo.ipAddress, dhcpInfo.gateway};
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return new int[0];
    }

    public static String[][] getAllRouteInfo() {
        if (haveAccessNetworkState() && Build.VERSION.SDK_INT >= 21) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity");
                if (connectivityManager == null) {
                    return null;
                }
                Network[] allNetworks = connectivityManager.getAllNetworks();
                ArrayList arrayList = new ArrayList();
                for (Network network : allNetworks) {
                    LinkProperties linkProperties = connectivityManager.getLinkProperties(network);
                    if (linkProperties != null) {
                        for (RouteInfo routeInfo : linkProperties.getRoutes()) {
                            String[] strArr = new String[4];
                            if (routeInfo.getDestination() != null) {
                                strArr[0] = routeInfo.getDestination().getAddress().getHostAddress();
                                strArr[1] = Integer.toString(routeInfo.getDestination().getPrefixLength());
                            }
                            if (routeInfo.getGateway() != null) {
                                strArr[2] = routeInfo.getGateway().getHostAddress();
                            }
                            strArr[3] = routeInfo.getInterface();
                            arrayList.add(strArr);
                        }
                    }
                }
                return (String[][]) arrayList.toArray((String[][]) Array.newInstance(String.class, 0, 0));
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private static String getNetworkOperator() {
        if (TTAppSecurityManager.getInstance().isTTPRestrictionEnabled()) {
            return TTAppSecurityManager.getInstance().getNetworkOperator();
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) ContextUtils.getApplicationContext().getSystemService("phone");
            return telephonyManager == null ? "" : telephonyManager.getNetworkOperator();
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static String getSimOperator() {
        if (TTAppSecurityManager.getInstance().isTTPRestrictionEnabled()) {
            return TTAppSecurityManager.getInstance().getSimOperator();
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) ContextUtils.getApplicationContext().getSystemService("phone");
            return telephonyManager == null ? "" : telephonyManager.getSimOperator();
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static boolean checkIsVpnOn() {
        ConnectivityManager connectivityManager;
        if (Build.VERSION.SDK_INT >= 21 && (connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity")) != null) {
            Network[] allNetworks = connectivityManager.getAllNetworks();
            for (int i2 = 0; i2 < allNetworks.length; i2++) {
                if (connectivityManager.getNetworkInfo(allNetworks[i2]).isConnected() && connectivityManager.getNetworkCapabilities(allNetworks[i2]).hasTransport(4)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static int getCurrentAccessPointChannel() {
        if (TTAppSecurityManager.getInstance().isTTPRestrictionEnabled()) {
            return frequencyToChannel(TTAppSecurityManager.getInstance().getWifiFrequency());
        }
        WifiInfo wifiInfo = getWifiInfo();
        if (wifiInfo == null) {
            return -1;
        }
        return frequencyToChannel(wifiInfo.getFrequency());
    }

    public static int[] getAllAccessPointChannels() {
        if (haveAccessLocationState() && haveAccessWifiState()) {
            WifiManager wifiManager = (WifiManager) ContextUtils.getApplicationContext().getSystemService("wifi");
            if (wifiManager == null) {
                return new int[0];
            }
            List<ScanResult> scanResults = wifiManager.getScanResults();
            if (scanResults == null || scanResults.isEmpty()) {
                return new int[0];
            }
            ArrayList arrayList = new ArrayList();
            for (ScanResult scanResult : scanResults) {
                arrayList.add(Integer.valueOf(frequencyToChannel(scanResult.frequency)));
            }
            return CollectionUtil.integerListToIntArray(arrayList);
        }
        return new int[0];
    }

    private static int frequencyToChannel(int i2) {
        if (i2 == 2484) {
            return 14;
        }
        if (i2 < 2484) {
            return (i2 - 2407) / 5;
        }
        if (i2 < 4910 || i2 > 4980) {
            if (i2 < 5925) {
                return (i2 - 5000) / 5;
            }
            if (i2 == 5935) {
                return 2;
            }
            if (i2 <= 45000) {
                return (i2 - 5950) / 5;
            }
            if (i2 < 58320 || i2 > 70200) {
                return -1;
            }
            return (i2 - 56160) / 2160;
        }
        return (i2 - 4000) / 5;
    }

    private static void tagSocket(int i2, int i3, int i4) {
        ParcelFileDescriptor adoptFd;
        FileDescriptor fileDescriptor;
        int threadStatsTag = TrafficStats.getThreadStatsTag();
        if (i4 != threadStatsTag) {
            TrafficStats.setThreadStatsTag(i4);
        }
        if (i3 != -1) {
            ThreadStatsUid.set(i3);
        }
        if (Build.VERSION.SDK_INT < 23) {
            adoptFd = null;
            fileDescriptor = SetFileDescriptor.createWithFd(i2);
        } else {
            adoptFd = ParcelFileDescriptor.adoptFd(i2);
            fileDescriptor = adoptFd.getFileDescriptor();
        }
        SocketFd socketFd = new SocketFd(fileDescriptor);
        TrafficStats.tagSocket(socketFd);
        socketFd.close();
        if (adoptFd != null) {
            adoptFd.detachFd();
        }
        if (i4 != threadStatsTag) {
            TrafficStats.setThreadStatsTag(threadStatsTag);
        }
        if (i3 != -1) {
            ThreadStatsUid.clear();
        }
    }
}
