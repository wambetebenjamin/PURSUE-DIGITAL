package com.ttnet.org.chromium.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.bytedance.ttgame.sdk.module.utils.NetworkChangeManager;
import com.ttnet.org.chromium.base.ApplicationStatus;
import com.ttnet.org.chromium.base.ContextUtils;
import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.StrictModeContext;
import com.ttnet.org.chromium.base.TraceEvent;
import com.ttnet.org.chromium.base.compat.ApiHelperForM;
import com.ttnet.org.chromium.base.compat.ApiHelperForO;
import com.ttnet.org.chromium.base.compat.ApiHelperForP;
import com.ttnet.org.chromium.build.BuildConfig;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes9.dex */
public class NetworkChangeNotifierAutoDetect extends BroadcastReceiver {
    private static final String TAG = "NetworkChangeNotifierAutoDetect";
    private static final int UNKNOWN_LINK_SPEED = -1;
    private ConnectivityManagerDelegate mConnectivityManagerDelegate;
    private ConnectivityManager.NetworkCallback mDefaultNetworkCallback;
    private final Handler mHandler;
    private boolean mIgnoreNextBroadcast;
    private final NetworkConnectivityIntentFilter mIntentFilter;
    private final Looper mLooper;
    private MyNetworkCallback mNetworkCallback;
    private NetworkRequest mNetworkRequest;
    private NetworkState mNetworkState;
    private final Observer mObserver;
    private boolean mRegisterNetworkCallbackFailed;
    private boolean mRegistered;
    private final RegistrationPolicy mRegistrationPolicy;
    private boolean mShouldSignalObserver;
    private WifiManagerDelegate mWifiManagerDelegate;

    /* loaded from: classes9.dex */
    public interface Observer {
        void onConnectionCostChanged(int i2);

        void onConnectionSubtypeChanged(int i2);

        void onConnectionTypeChanged(int i2);

        void onNetworkConnect(long j2, int i2, boolean z);

        void onNetworkDisconnect(long j2, boolean z);

        void onNetworkSoonToDisconnect(long j2);

        void purgeActiveNetworkList(long[] jArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int convertToConnectionType(int i2, int i3) {
        if (i2 != 0) {
            if (i2 == 1) {
                return 2;
            }
            if (i2 != 4 && i2 != 5) {
                if (i2 != 6) {
                    if (i2 != 7) {
                        return i2 != 9 ? 0 : 1;
                    }
                    return 7;
                }
                return 5;
            }
        }
        if (i3 != 20) {
            switch (i3) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return 3;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return 4;
                case 13:
                    return 5;
                default:
                    return 0;
            }
        }
        return 8;
    }

    /* loaded from: classes9.dex */
    public static class NetworkState {
        private final boolean mConnected;
        private final List<InetAddress> mDnsServers;
        private final boolean mIsMetered;
        private final boolean mIsPrivateDnsActive;
        private final String mNetworkIdentifier;
        private final String mPrivateDnsServerName;
        private final int mSubtype;
        private final int mType;

        public NetworkState(boolean z, int i2, int i3, boolean z2, String str, List<InetAddress> list, boolean z3, String str2) {
            this.mConnected = z;
            this.mType = i2;
            this.mSubtype = i3;
            this.mIsMetered = z2;
            this.mNetworkIdentifier = str == null ? "" : str;
            this.mDnsServers = list == null ? new ArrayList<>() : list;
            this.mIsPrivateDnsActive = z3;
            this.mPrivateDnsServerName = str2 == null ? "" : str2;
        }

        public boolean isConnected() {
            return this.mConnected;
        }

        public int getNetworkType() {
            return this.mType;
        }

        public boolean isMetered() {
            return this.mIsMetered;
        }

        public int getNetworkSubType() {
            return this.mSubtype;
        }

        public String getNetworkIdentifier() {
            return this.mNetworkIdentifier;
        }

        public int getConnectionType() {
            if (isConnected()) {
                return NetworkChangeNotifierAutoDetect.convertToConnectionType(getNetworkType(), getNetworkSubType());
            }
            return 6;
        }

        public int getConnectionCost() {
            return isMetered() ? 2 : 1;
        }

        public int getConnectionSubtype() {
            if (isConnected()) {
                int networkType = getNetworkType();
                if (networkType == 0 || networkType == 4 || networkType == 5) {
                    switch (getNetworkSubType()) {
                        case 1:
                            return 7;
                        case 2:
                            return 8;
                        case 3:
                            return 9;
                        case 4:
                            return 5;
                        case 5:
                            return 10;
                        case 6:
                            return 11;
                        case 7:
                            return 6;
                        case 8:
                            return 14;
                        case 9:
                            return 15;
                        case 10:
                            return 12;
                        case 11:
                            return 4;
                        case 12:
                            return 13;
                        case 13:
                            return 18;
                        case 14:
                            return 16;
                        case 15:
                            return 17;
                        default:
                            return 0;
                    }
                }
                return 0;
            }
            return 1;
        }

        public List<InetAddress> getDnsServers() {
            return this.mDnsServers;
        }

        public boolean isPrivateDnsActive() {
            return this.mIsPrivateDnsActive;
        }

        public String getPrivateDnsServerName() {
            return this.mPrivateDnsServerName;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class ConnectivityManagerDelegate {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final ConnectivityManager mConnectivityManager;

        ConnectivityManagerDelegate(Context context) {
            this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        }

        ConnectivityManagerDelegate() {
            this.mConnectivityManager = null;
        }

        private NetworkInfo processActiveNetworkInfo(NetworkInfo networkInfo) {
            if (networkInfo == null) {
                return null;
            }
            if (networkInfo.isConnected()) {
                return networkInfo;
            }
            if (Build.VERSION.SDK_INT >= 21 && networkInfo.getDetailedState() == NetworkInfo.DetailedState.BLOCKED && ApplicationStatus.getStateForApplication() == 1) {
                return networkInfo;
            }
            return null;
        }

        NetworkState getNetworkState(WifiManagerDelegate wifiManagerDelegate) {
            Network network;
            NetworkInfo activeNetworkInfo;
            NetworkInfo networkInfo = null;
            try {
                if (Build.VERSION.SDK_INT >= 23) {
                    network = getDefaultNetwork();
                    try {
                        NetworkInfo networkInfo2 = getNetworkInfo(network);
                        if (networkInfo2 == null || networkInfo2.getType() != 17) {
                            networkInfo = network;
                            activeNetworkInfo = networkInfo2;
                        } else {
                            activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
                            networkInfo = network;
                        }
                    } catch (Throwable unused) {
                    }
                } else {
                    activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
                }
                NetworkInfo networkInfo3 = activeNetworkInfo;
                network = networkInfo;
                networkInfo = networkInfo3;
            } catch (Throwable unused2) {
                network = null;
            }
            NetworkInfo processActiveNetworkInfo = processActiveNetworkInfo(networkInfo);
            if (processActiveNetworkInfo == null) {
                return new NetworkState(false, -1, -1, false, null, null, false, "");
            }
            if (network != null) {
                NetworkCapabilities networkCapabilities = getNetworkCapabilities(network);
                boolean z = (networkCapabilities == null || networkCapabilities.hasCapability(11)) ? false : true;
                DnsStatus dnsStatus = AndroidNetworkLibrary.getDnsStatus(network);
                if (dnsStatus == null) {
                    return new NetworkState(true, processActiveNetworkInfo.getType(), processActiveNetworkInfo.getSubtype(), z, String.valueOf(NetworkChangeNotifierAutoDetect.networkToNetId(network)), null, false, "");
                }
                ArrayList arrayList = new ArrayList();
                try {
                    for (byte[] bArr : dnsStatus.getDnsServers()) {
                        arrayList.add(InetAddress.getByAddress(bArr));
                    }
                } catch (UnknownHostException unused3) {
                }
                return new NetworkState(true, processActiveNetworkInfo.getType(), processActiveNetworkInfo.getSubtype(), z, String.valueOf(NetworkChangeNotifierAutoDetect.networkToNetId(network)), arrayList, dnsStatus.getPrivateDnsActive(), dnsStatus.getPrivateDnsServerName());
            } else if (processActiveNetworkInfo.getType() == 1 && Build.VERSION.SDK_INT < 23 && wifiManagerDelegate != null) {
                if (processActiveNetworkInfo.getExtraInfo() != null && !"".equals(processActiveNetworkInfo.getExtraInfo())) {
                    return new NetworkState(true, processActiveNetworkInfo.getType(), processActiveNetworkInfo.getSubtype(), false, processActiveNetworkInfo.getExtraInfo(), null, false, "");
                }
                return new NetworkState(true, processActiveNetworkInfo.getType(), processActiveNetworkInfo.getSubtype(), false, wifiManagerDelegate.getWifiSsid(), null, false, "");
            } else {
                return new NetworkState(true, processActiveNetworkInfo.getType(), processActiveNetworkInfo.getSubtype(), false, null, null, false, "");
            }
        }

        NetworkInfo getRawNetworkInfo(Network network) {
            try {
                try {
                    return this.mConnectivityManager.getNetworkInfo(network);
                } catch (Throwable unused) {
                    return this.mConnectivityManager.getNetworkInfo(network);
                }
            } catch (Throwable unused2) {
                return null;
            }
        }

        NetworkInfo getNetworkInfo(Network network) {
            NetworkInfo rawNetworkInfo = getRawNetworkInfo(network);
            if (rawNetworkInfo == null || rawNetworkInfo.getType() != 17) {
                return rawNetworkInfo;
            }
            try {
                return this.mConnectivityManager.getActiveNetworkInfo();
            } catch (Throwable unused) {
                return null;
            }
        }

        int getConnectionType(Network network) {
            NetworkInfo networkInfo = getNetworkInfo(network);
            if (networkInfo == null || !networkInfo.isConnected()) {
                return 6;
            }
            return NetworkChangeNotifierAutoDetect.convertToConnectionType(networkInfo.getType(), networkInfo.getSubtype());
        }

        protected Network[] getAllNetworksUnfiltered() {
            Network[] networkArr;
            try {
                networkArr = this.mConnectivityManager.getAllNetworks();
            } catch (Throwable unused) {
                networkArr = null;
            }
            return networkArr == null ? new Network[0] : networkArr;
        }

        protected boolean vpnAccessible(Network network) {
            Socket socket = new Socket();
            try {
                StrictModeContext allowAllVmPolicies = StrictModeContext.allowAllVmPolicies();
                try {
                    network.bindSocket(socket);
                    if (allowAllVmPolicies != null) {
                        allowAllVmPolicies.close();
                    }
                    try {
                        socket.close();
                        return true;
                    } catch (IOException unused) {
                        return true;
                    }
                } catch (Throwable th) {
                    if (allowAllVmPolicies != null) {
                        try {
                            allowAllVmPolicies.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (IOException unused2) {
                try {
                    socket.close();
                } catch (IOException unused3) {
                }
                return false;
            } catch (Throwable th3) {
                try {
                    socket.close();
                } catch (IOException unused4) {
                }
                throw th3;
            }
        }

        protected NetworkCapabilities getNetworkCapabilities(Network network) {
            for (int i2 = 0; i2 < 2; i2++) {
                try {
                    return this.mConnectivityManager.getNetworkCapabilities(network);
                } catch (Throwable unused) {
                }
            }
            return null;
        }

        void registerNetworkCallback(NetworkRequest networkRequest, ConnectivityManager.NetworkCallback networkCallback, Handler handler) {
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    ApiHelperForO.registerNetworkCallback(this.mConnectivityManager, networkRequest, networkCallback, handler);
                } else {
                    this.mConnectivityManager.registerNetworkCallback(networkRequest, networkCallback);
                }
            } catch (Throwable unused) {
            }
        }

        void registerDefaultNetworkCallback(ConnectivityManager.NetworkCallback networkCallback, Handler handler) {
            try {
                ApiHelperForO.registerDefaultNetworkCallback(this.mConnectivityManager, networkCallback, handler);
            } catch (Throwable unused) {
            }
        }

        void unregisterNetworkCallback(ConnectivityManager.NetworkCallback networkCallback) {
            try {
                this.mConnectivityManager.unregisterNetworkCallback(networkCallback);
            } catch (Throwable unused) {
            }
        }

        Network getDefaultNetwork() {
            Network network;
            Network[] allNetworksFiltered;
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    network = ApiHelperForM.getActiveNetwork(this.mConnectivityManager);
                } catch (Throwable unused) {
                    network = null;
                }
                if (network != null) {
                    return network;
                }
            } else {
                network = null;
            }
            try {
                NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return null;
                }
                for (Network network2 : NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(this, null)) {
                    NetworkInfo rawNetworkInfo = getRawNetworkInfo(network2);
                    if (rawNetworkInfo != null && (rawNetworkInfo.getType() == activeNetworkInfo.getType() || rawNetworkInfo.getType() == 17)) {
                        if (network != null && Build.VERSION.SDK_INT >= 29) {
                            if (rawNetworkInfo.getDetailedState() != NetworkInfo.DetailedState.CONNECTING) {
                                NetworkInfo rawNetworkInfo2 = getRawNetworkInfo(network);
                                if (rawNetworkInfo2 != null && rawNetworkInfo2.getDetailedState() == NetworkInfo.DetailedState.CONNECTING) {
                                    network = null;
                                }
                            }
                        }
                        if (network != null) {
                            Log.e(NetworkChangeNotifierAutoDetect.TAG, "defaultNetwork != null", new Object[0]);
                        }
                        network = network2;
                    }
                }
                return network;
            } catch (Throwable unused2) {
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class WifiManagerDelegate {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Context mContext;
        private boolean mHasWifiPermission;
        private boolean mHasWifiPermissionComputed;
        private final Object mLock;
        private WifiManager mWifiManager;

        String getWifiSsid() {
            return "";
        }

        WifiManagerDelegate(Context context) {
            this.mLock = new Object();
            this.mContext = context;
        }

        WifiManagerDelegate() {
            this.mLock = new Object();
            this.mContext = null;
        }

        private boolean hasPermissionLocked() {
            if (this.mHasWifiPermissionComputed) {
                return this.mHasWifiPermission;
            }
            boolean z = this.mContext.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", this.mContext.getPackageName()) == 0;
            this.mHasWifiPermission = z;
            this.mWifiManager = z ? (WifiManager) this.mContext.getSystemService("wifi") : null;
            this.mHasWifiPermissionComputed = true;
            return this.mHasWifiPermission;
        }

        private WifiInfo getWifiInfoLocked() {
            try {
                try {
                    return this.mWifiManager.getConnectionInfo();
                } catch (NullPointerException unused) {
                    return null;
                }
            } catch (NullPointerException unused2) {
                return this.mWifiManager.getConnectionInfo();
            }
        }
    }

    /* loaded from: classes9.dex */
    private class DefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
        private DefaultNetworkCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            if (NetworkChangeNotifierAutoDetect.this.mRegistered) {
                NetworkChangeNotifierAutoDetect.this.connectionTypeChanged();
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            onAvailable(null);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            onAvailable(null);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            onAvailable(null);
        }
    }

    /* loaded from: classes9.dex */
    private class AndroidRDefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
        LinkProperties mLinkProperties;
        NetworkCapabilities mNetworkCapabilities;

        private AndroidRDefaultNetworkCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            this.mLinkProperties = null;
            this.mNetworkCapabilities = null;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            this.mLinkProperties = null;
            this.mNetworkCapabilities = null;
            if (NetworkChangeNotifierAutoDetect.this.mRegistered) {
                NetworkChangeNotifierAutoDetect.this.connectionTypeChangedTo(new NetworkState(false, -1, -1, false, null, null, false, ""));
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            this.mLinkProperties = linkProperties;
            if (!NetworkChangeNotifierAutoDetect.this.mRegistered || this.mLinkProperties == null || this.mNetworkCapabilities == null) {
                return;
            }
            NetworkChangeNotifierAutoDetect.this.connectionTypeChangedTo(getNetworkState(network));
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            this.mNetworkCapabilities = networkCapabilities;
            if (!NetworkChangeNotifierAutoDetect.this.mRegistered || this.mLinkProperties == null || this.mNetworkCapabilities == null) {
                return;
            }
            NetworkChangeNotifierAutoDetect.this.connectionTypeChangedTo(getNetworkState(network));
        }

        private NetworkState getNetworkState(Network network) {
            int i2;
            int i3;
            int i4 = 0;
            int i5 = -1;
            if (this.mNetworkCapabilities.hasTransport(1) || this.mNetworkCapabilities.hasTransport(5)) {
                i2 = 1;
            } else {
                if (this.mNetworkCapabilities.hasTransport(0)) {
                    NetworkInfo rawNetworkInfo = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getRawNetworkInfo(network);
                    if (rawNetworkInfo != null) {
                        i5 = rawNetworkInfo.getSubtype();
                    }
                } else if (this.mNetworkCapabilities.hasTransport(3)) {
                    i4 = 9;
                } else if (this.mNetworkCapabilities.hasTransport(2)) {
                    i4 = 7;
                } else if (this.mNetworkCapabilities.hasTransport(4)) {
                    NetworkInfo networkInfo = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkInfo(network);
                    i4 = networkInfo != null ? networkInfo.getType() : 17;
                } else {
                    i2 = -1;
                    i3 = -1;
                    return new NetworkState(true, i2, i3, !this.mNetworkCapabilities.hasCapability(11), String.valueOf(NetworkChangeNotifierAutoDetect.networkToNetId(network)), null, ApiHelperForP.isPrivateDnsActive(this.mLinkProperties), ApiHelperForP.getPrivateDnsServerName(this.mLinkProperties));
                }
                i2 = i4;
            }
            i3 = i5;
            return new NetworkState(true, i2, i3, !this.mNetworkCapabilities.hasCapability(11), String.valueOf(NetworkChangeNotifierAutoDetect.networkToNetId(network)), null, ApiHelperForP.isPrivateDnsActive(this.mLinkProperties), ApiHelperForP.getPrivateDnsServerName(this.mLinkProperties));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class MyNetworkCallback extends ConnectivityManager.NetworkCallback {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Network mVpnInPlace;

        private MyNetworkCallback() {
        }

        public long getVpnNetId() {
            Network network = this.mVpnInPlace;
            if (network == null) {
                return -1L;
            }
            return NetworkChangeNotifierAutoDetect.networkToNetId(network);
        }

        void initializeVpnInPlace() {
            NetworkCapabilities networkCapabilities;
            Network[] allNetworksFiltered = NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate, null);
            this.mVpnInPlace = null;
            if (allNetworksFiltered.length == 1 && (networkCapabilities = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkCapabilities(allNetworksFiltered[0])) != null && networkCapabilities.hasTransport(4)) {
                this.mVpnInPlace = allNetworksFiltered[0];
            }
        }

        private boolean ignoreNetworkDueToVpn(Network network) {
            Network network2 = this.mVpnInPlace;
            return (network2 == null || network2.equals(network)) ? false : true;
        }

        private boolean ignoreConnectedInaccessibleVpn(Network network, NetworkCapabilities networkCapabilities) {
            if (networkCapabilities == null) {
                networkCapabilities = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkCapabilities(network);
            }
            return networkCapabilities == null || (networkCapabilities.hasTransport(4) && !NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.vpnAccessible(network));
        }

        private boolean ignoreConnectedNetwork(Network network, NetworkCapabilities networkCapabilities) {
            return ignoreNetworkDueToVpn(network) || ignoreConnectedInaccessibleVpn(network, networkCapabilities);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            Network network2;
            TraceEvent scoped = TraceEvent.scoped("NetworkChangeNotifierCallback::onAvailable");
            try {
                NetworkCapabilities networkCapabilities = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkCapabilities(network);
                if (ignoreConnectedNetwork(network, networkCapabilities)) {
                    if (scoped != null) {
                        scoped.close();
                        return;
                    }
                    return;
                }
                boolean z = false;
                if (networkCapabilities != null && networkCapabilities.hasTransport(4) && ((network2 = this.mVpnInPlace) == null || !network.equals(network2))) {
                    z = true;
                }
                final boolean z2 = z;
                if (z2) {
                    this.mVpnInPlace = network;
                }
                final long networkToNetId = NetworkChangeNotifierAutoDetect.networkToNetId(network);
                final int connectionType = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getConnectionType(network);
                NetworkChangeNotifierAutoDetect.this.runOnThread(new Runnable() { // from class: com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.MyNetworkCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkConnect(networkToNetId, connectionType, z2);
                        if (z2) {
                            NetworkChangeNotifierAutoDetect.this.mObserver.onConnectionTypeChanged(connectionType);
                            NetworkChangeNotifierAutoDetect.this.mObserver.purgeActiveNetworkList(new long[]{networkToNetId});
                        }
                    }
                });
                if (scoped != null) {
                    scoped.close();
                }
            } catch (Throwable th) {
                if (scoped != null) {
                    try {
                        scoped.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            TraceEvent scoped = TraceEvent.scoped("NetworkChangeNotifierCallback::onCapabilitiesChanged");
            try {
                if (ignoreConnectedNetwork(network, networkCapabilities)) {
                    if (scoped != null) {
                        scoped.close();
                        return;
                    }
                    return;
                }
                final long networkToNetId = NetworkChangeNotifierAutoDetect.networkToNetId(network);
                final int connectionType = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getConnectionType(network);
                Network network2 = this.mVpnInPlace;
                final boolean z = network2 != null && network2.equals(network);
                NetworkChangeNotifierAutoDetect.this.runOnThread(new Runnable() { // from class: com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.MyNetworkCallback.2
                    @Override // java.lang.Runnable
                    public void run() {
                        NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkConnect(networkToNetId, connectionType, z);
                    }
                });
                if (scoped != null) {
                    scoped.close();
                }
            } catch (Throwable th) {
                if (scoped != null) {
                    try {
                        scoped.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLosing(Network network, int i2) {
            TraceEvent scoped = TraceEvent.scoped("NetworkChangeNotifierCallback::onLosing");
            try {
                if (ignoreConnectedNetwork(network, null)) {
                    if (scoped != null) {
                        scoped.close();
                        return;
                    }
                    return;
                }
                final long networkToNetId = NetworkChangeNotifierAutoDetect.networkToNetId(network);
                NetworkChangeNotifierAutoDetect.this.runOnThread(new Runnable() { // from class: com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.MyNetworkCallback.3
                    @Override // java.lang.Runnable
                    public void run() {
                        NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkSoonToDisconnect(networkToNetId);
                    }
                });
                if (scoped != null) {
                    scoped.close();
                }
            } catch (Throwable th) {
                if (scoped != null) {
                    try {
                        scoped.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(final Network network) {
            TraceEvent scoped = TraceEvent.scoped("NetworkChangeNotifierCallback::onLost");
            try {
                if (ignoreNetworkDueToVpn(network)) {
                    if (scoped != null) {
                        scoped.close();
                        return;
                    }
                    return;
                }
                Network network2 = this.mVpnInPlace;
                final boolean z = network2 != null && network2.equals(network);
                NetworkChangeNotifierAutoDetect.this.runOnThread(new Runnable() { // from class: com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.MyNetworkCallback.4
                    @Override // java.lang.Runnable
                    public void run() {
                        NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkDisconnect(NetworkChangeNotifierAutoDetect.networkToNetId(network), z);
                    }
                });
                if (this.mVpnInPlace != null) {
                    this.mVpnInPlace = null;
                    for (Network network3 : NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate, network)) {
                        onAvailable(network3);
                    }
                    final int connectionType = NetworkChangeNotifierAutoDetect.this.getCurrentNetworkState().getConnectionType();
                    NetworkChangeNotifierAutoDetect.this.runOnThread(new Runnable() { // from class: com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.MyNetworkCallback.5
                        @Override // java.lang.Runnable
                        public void run() {
                            NetworkChangeNotifierAutoDetect.this.mObserver.onConnectionTypeChanged(connectionType);
                        }
                    });
                }
                if (scoped != null) {
                    scoped.close();
                }
            } catch (Throwable th) {
                if (scoped != null) {
                    try {
                        scoped.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
    }

    /* loaded from: classes9.dex */
    public static abstract class RegistrationPolicy {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private NetworkChangeNotifierAutoDetect mNotifier;

        protected abstract void destroy();

        /* JADX INFO: Access modifiers changed from: protected */
        public final void register() {
            this.mNotifier.register();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void unregister() {
            this.mNotifier.unregister();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void init(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect) {
            this.mNotifier = networkChangeNotifierAutoDetect;
        }
    }

    public NetworkChangeNotifierAutoDetect(Observer observer, RegistrationPolicy registrationPolicy) {
        Looper myLooper = Looper.myLooper();
        this.mLooper = myLooper;
        this.mHandler = new Handler(myLooper);
        this.mObserver = observer;
        this.mConnectivityManagerDelegate = new ConnectivityManagerDelegate(ContextUtils.getApplicationContext());
        if (Build.VERSION.SDK_INT < 23) {
            this.mWifiManagerDelegate = new WifiManagerDelegate(ContextUtils.getApplicationContext());
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.mNetworkCallback = new MyNetworkCallback();
            this.mNetworkRequest = new NetworkRequest.Builder().addCapability(12).removeCapability(15).build();
        } else {
            this.mNetworkCallback = null;
            this.mNetworkRequest = null;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            this.mDefaultNetworkCallback = new AndroidRDefaultNetworkCallback();
        } else {
            this.mDefaultNetworkCallback = Build.VERSION.SDK_INT >= 28 ? new DefaultNetworkCallback() : null;
        }
        this.mNetworkState = getCurrentNetworkState();
        this.mIntentFilter = new NetworkConnectivityIntentFilter();
        this.mIgnoreNextBroadcast = false;
        this.mShouldSignalObserver = false;
        this.mRegistrationPolicy = registrationPolicy;
        registrationPolicy.init(this);
        this.mShouldSignalObserver = true;
    }

    private boolean onThread() {
        return this.mLooper == Looper.myLooper();
    }

    private void assertOnThread() {
        if (BuildConfig.ENABLE_ASSERTS && !onThread()) {
            throw new IllegalStateException("Must be called on NetworkChangeNotifierAutoDetect thread.");
        }
    }

    public void runOnThread(final Runnable runnable) {
        if (onThread()) {
            runnable.run();
        } else {
            this.mHandler.post(new Runnable() { // from class: com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    NetworkChangeNotifierAutoDetect.this.m717x8c1cf4b5(runnable);
                }
            });
        }
    }

    /* renamed from: lambda$runOnThread$0$com-ttnet-org-chromium-net-NetworkChangeNotifierAutoDetect  reason: not valid java name */
    public /* synthetic */ void m717x8c1cf4b5(Runnable runnable) {
        if (this.mRegistered) {
            runnable.run();
        }
    }

    void setConnectivityManagerDelegateForTests(ConnectivityManagerDelegate connectivityManagerDelegate) {
        this.mConnectivityManagerDelegate = connectivityManagerDelegate;
    }

    void setWifiManagerDelegateForTests(WifiManagerDelegate wifiManagerDelegate) {
        this.mWifiManagerDelegate = wifiManagerDelegate;
    }

    RegistrationPolicy getRegistrationPolicy() {
        return this.mRegistrationPolicy;
    }

    boolean isReceiverRegisteredForTesting() {
        return this.mRegistered;
    }

    public void destroy() {
        assertOnThread();
        this.mRegistrationPolicy.destroy();
        unregister();
    }

    public void register() {
        assertOnThread();
        if (this.mRegistered) {
            connectionTypeChanged();
            return;
        }
        if (this.mShouldSignalObserver) {
            connectionTypeChanged();
        }
        ConnectivityManager.NetworkCallback networkCallback = this.mDefaultNetworkCallback;
        if (networkCallback != null) {
            try {
                this.mConnectivityManagerDelegate.registerDefaultNetworkCallback(networkCallback, this.mHandler);
            } catch (RuntimeException unused) {
                this.mDefaultNetworkCallback = null;
            }
        }
        if (this.mDefaultNetworkCallback == null) {
            try {
                this.mIgnoreNextBroadcast = ContextUtils.getApplicationContext().registerReceiver(this, this.mIntentFilter) != null;
            } catch (RuntimeException unused2) {
                this.mIgnoreNextBroadcast = false;
            }
        }
        this.mRegistered = true;
        MyNetworkCallback myNetworkCallback = this.mNetworkCallback;
        if (myNetworkCallback != null) {
            myNetworkCallback.initializeVpnInPlace();
            try {
                this.mConnectivityManagerDelegate.registerNetworkCallback(this.mNetworkRequest, this.mNetworkCallback, this.mHandler);
            } catch (RuntimeException unused3) {
                this.mRegisterNetworkCallbackFailed = true;
                this.mNetworkCallback = null;
            }
            if (this.mRegisterNetworkCallbackFailed || !this.mShouldSignalObserver) {
                return;
            }
            Network[] allNetworksFiltered = getAllNetworksFiltered(this.mConnectivityManagerDelegate, null);
            long[] jArr = new long[allNetworksFiltered.length];
            for (int i2 = 0; i2 < allNetworksFiltered.length; i2++) {
                jArr[i2] = networkToNetId(allNetworksFiltered[i2]);
            }
            this.mObserver.purgeActiveNetworkList(jArr);
        }
    }

    public void unregister() {
        assertOnThread();
        if (this.mRegistered) {
            this.mRegistered = false;
            MyNetworkCallback myNetworkCallback = this.mNetworkCallback;
            if (myNetworkCallback != null) {
                this.mConnectivityManagerDelegate.unregisterNetworkCallback(myNetworkCallback);
            }
            ConnectivityManager.NetworkCallback networkCallback = this.mDefaultNetworkCallback;
            if (networkCallback != null) {
                this.mConnectivityManagerDelegate.unregisterNetworkCallback(networkCallback);
            } else {
                ContextUtils.getApplicationContext().unregisterReceiver(this);
            }
        }
    }

    public NetworkState getCurrentNetworkState() {
        return this.mConnectivityManagerDelegate.getNetworkState(this.mWifiManagerDelegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Network[] getAllNetworksFiltered(ConnectivityManagerDelegate connectivityManagerDelegate, Network network) {
        NetworkCapabilities networkCapabilities;
        Network[] allNetworksUnfiltered = connectivityManagerDelegate.getAllNetworksUnfiltered();
        int i2 = 0;
        for (Network network2 : allNetworksUnfiltered) {
            if (network2 != null && !network2.equals(network) && (networkCapabilities = connectivityManagerDelegate.getNetworkCapabilities(network2)) != null && networkCapabilities.hasCapability(12)) {
                if (networkCapabilities.hasTransport(4)) {
                    if (connectivityManagerDelegate.vpnAccessible(network2)) {
                        return new Network[]{network2};
                    }
                } else {
                    allNetworksUnfiltered[i2] = network2;
                    i2++;
                }
            }
        }
        return (Network[]) Arrays.copyOf(allNetworksUnfiltered, i2);
    }

    public long[] getNetworksAndTypes() {
        if (Build.VERSION.SDK_INT < 21) {
            return new long[0];
        }
        Network[] allNetworksFiltered = getAllNetworksFiltered(this.mConnectivityManagerDelegate, null);
        long[] jArr = new long[allNetworksFiltered.length * 2];
        int i2 = 0;
        for (Network network : allNetworksFiltered) {
            int i3 = i2 + 1;
            jArr[i2] = networkToNetId(network);
            i2 = i3 + 1;
            jArr[i3] = this.mConnectivityManagerDelegate.getConnectionType(network);
        }
        return jArr;
    }

    public long getDefaultNetId() {
        Network defaultNetwork;
        if (Build.VERSION.SDK_INT >= 21 && (defaultNetwork = this.mConnectivityManagerDelegate.getDefaultNetwork()) != null) {
            return networkToNetId(defaultNetwork);
        }
        return -1L;
    }

    public long getVpnNetId() {
        MyNetworkCallback myNetworkCallback;
        if (Build.VERSION.SDK_INT >= 21 && (myNetworkCallback = this.mNetworkCallback) != null) {
            return myNetworkCallback.getVpnNetId();
        }
        return -1L;
    }

    public boolean registerNetworkCallbackFailed() {
        return this.mRegisterNetworkCallbackFailed;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        runOnThread(new Runnable() { // from class: com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.1
            @Override // java.lang.Runnable
            public void run() {
                if (NetworkChangeNotifierAutoDetect.this.mIgnoreNextBroadcast) {
                    NetworkChangeNotifierAutoDetect.this.mIgnoreNextBroadcast = false;
                } else {
                    NetworkChangeNotifierAutoDetect.this.connectionTypeChanged();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectionTypeChanged() {
        connectionTypeChangedTo(getCurrentNetworkState());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectionTypeChangedTo(NetworkState networkState) {
        if (networkState.getConnectionType() != this.mNetworkState.getConnectionType() || !networkState.getNetworkIdentifier().equals(this.mNetworkState.getNetworkIdentifier()) || !networkState.getDnsServers().equals(this.mNetworkState.getDnsServers()) || networkState.isPrivateDnsActive() != this.mNetworkState.isPrivateDnsActive() || !networkState.getPrivateDnsServerName().equals(this.mNetworkState.getPrivateDnsServerName())) {
            this.mObserver.onConnectionTypeChanged(networkState.getConnectionType());
        }
        if (networkState.getConnectionType() != this.mNetworkState.getConnectionType() || networkState.getConnectionSubtype() != this.mNetworkState.getConnectionSubtype()) {
            this.mObserver.onConnectionSubtypeChanged(networkState.getConnectionSubtype());
        }
        if (networkState.getConnectionCost() != this.mNetworkState.getConnectionCost()) {
            this.mObserver.onConnectionCostChanged(networkState.getConnectionCost());
        }
        this.mNetworkState = networkState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class NetworkConnectivityIntentFilter extends IntentFilter {
        NetworkConnectivityIntentFilter() {
            addAction(NetworkChangeManager.ACTION);
        }
    }

    public static long networkToNetId(Network network) {
        if (Build.VERSION.SDK_INT >= 23) {
            return ApiHelperForM.getNetworkHandle(network);
        }
        return Integer.parseInt(network.toString());
    }
}
