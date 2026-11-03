package com.ttnet.org.chromium.net;

import com.bytedance.apm.perf.traffic.a;
import com.ttnet.org.chromium.base.ObserverList;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.NativeClassQualifiedName;
import com.ttnet.org.chromium.build.BuildConfig;
import com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect;
import java.util.ArrayList;
import java.util.Iterator;
@JNINamespace(a.KEY_TRAFFIC_STATUS_NET)
/* loaded from: classes9.dex */
public class NetworkChangeNotifier {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static NetworkChangeNotifier sInstance;
    private NetworkChangeNotifierAutoDetect mAutoDetector;
    private int mCurrentConnectionType = 0;
    private int mCurrentConnectionCost = 0;
    private final ArrayList<Long> mNativeChangeNotifiers = new ArrayList<>();
    private final ObserverList<ConnectionTypeObserver> mConnectionTypeObservers = new ObserverList<>();

    /* loaded from: classes9.dex */
    public interface ConnectionTypeObserver {
        void onConnectionTypeChanged(int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
        void notifyConnectionCostChanged(long j2, NetworkChangeNotifier networkChangeNotifier, int i2);

        @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
        void notifyConnectionTypeChanged(long j2, NetworkChangeNotifier networkChangeNotifier, int i2, long j3);

        @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
        void notifyMaxBandwidthChanged(long j2, NetworkChangeNotifier networkChangeNotifier, int i2);

        @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
        void notifyOfNetworkConnect(long j2, NetworkChangeNotifier networkChangeNotifier, long j3, boolean z, int i2);

        @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
        void notifyOfNetworkDisconnect(long j2, NetworkChangeNotifier networkChangeNotifier, boolean z, long j3);

        @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
        void notifyOfNetworkSoonToDisconnect(long j2, NetworkChangeNotifier networkChangeNotifier, long j3);

        @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
        void notifyPurgeActiveNetworkList(long j2, NetworkChangeNotifier networkChangeNotifier, long[] jArr);
    }

    protected NetworkChangeNotifier() {
    }

    public static NetworkChangeNotifier init() {
        if (sInstance == null) {
            sInstance = new NetworkChangeNotifier();
        }
        return sInstance;
    }

    public static boolean isInitialized() {
        return sInstance != null;
    }

    public static void resetInstanceForTests(NetworkChangeNotifier networkChangeNotifier) {
        sInstance = networkChangeNotifier;
    }

    public int getCurrentConnectionType() {
        return this.mCurrentConnectionType;
    }

    public int getCurrentConnectionSubtype() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        if (networkChangeNotifierAutoDetect == null) {
            return 0;
        }
        return networkChangeNotifierAutoDetect.getCurrentNetworkState().getConnectionSubtype();
    }

    public int getCurrentConnectionCost() {
        return this.mCurrentConnectionCost;
    }

    public long getCurrentDefaultNetId() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        if (networkChangeNotifierAutoDetect == null) {
            return -1L;
        }
        return networkChangeNotifierAutoDetect.getDefaultNetId();
    }

    public long[] getCurrentNetworksAndTypes() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        return networkChangeNotifierAutoDetect == null ? new long[0] : networkChangeNotifierAutoDetect.getNetworksAndTypes();
    }

    public void addNativeObserver(long j2) {
        this.mNativeChangeNotifiers.add(Long.valueOf(j2));
    }

    public void removeNativeObserver(long j2) {
        this.mNativeChangeNotifiers.remove(Long.valueOf(j2));
    }

    public boolean registerNetworkCallbackFailed() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        if (networkChangeNotifierAutoDetect == null) {
            return false;
        }
        return networkChangeNotifierAutoDetect.registerNetworkCallbackFailed();
    }

    public static NetworkChangeNotifier getInstance() {
        return sInstance;
    }

    public static void setAutoDetectConnectivityState(boolean z) {
        getInstance().setAutoDetectConnectivityStateInternal(z, new RegistrationPolicyApplicationStatus());
    }

    public static void registerToReceiveNotificationsAlways() {
        getInstance().setAutoDetectConnectivityStateInternal(true, new RegistrationPolicyAlwaysRegister());
    }

    public static void setAutoDetectConnectivityState(NetworkChangeNotifierAutoDetect.RegistrationPolicy registrationPolicy) {
        getInstance().setAutoDetectConnectivityStateInternal(true, registrationPolicy);
    }

    private void destroyAutoDetector() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        if (networkChangeNotifierAutoDetect != null) {
            networkChangeNotifierAutoDetect.destroy();
            this.mAutoDetector = null;
        }
    }

    private void setAutoDetectConnectivityStateInternal(boolean z, NetworkChangeNotifierAutoDetect.RegistrationPolicy registrationPolicy) {
        if (z) {
            if (this.mAutoDetector == null) {
                NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = new NetworkChangeNotifierAutoDetect(new NetworkChangeNotifierAutoDetect.Observer() { // from class: com.ttnet.org.chromium.net.NetworkChangeNotifier.1
                    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                    public void onConnectionTypeChanged(int i2) {
                        NetworkChangeNotifier.this.updateCurrentConnectionType(i2);
                    }

                    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                    public void onConnectionCostChanged(int i2) {
                        NetworkChangeNotifier.this.notifyObserversOfConnectionCostChange(i2);
                    }

                    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                    public void onConnectionSubtypeChanged(int i2) {
                        NetworkChangeNotifier.this.notifyObserversOfConnectionSubtypeChange(i2);
                    }

                    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                    public void onNetworkConnect(long j2, int i2, boolean z2) {
                        NetworkChangeNotifier.this.notifyObserversOfNetworkConnect(j2, i2, z2);
                    }

                    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                    public void onNetworkSoonToDisconnect(long j2) {
                        NetworkChangeNotifier.this.notifyObserversOfNetworkSoonToDisconnect(j2);
                    }

                    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                    public void onNetworkDisconnect(long j2, boolean z2) {
                        NetworkChangeNotifier.this.notifyObserversOfNetworkDisconnect(j2, z2);
                    }

                    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                    public void purgeActiveNetworkList(long[] jArr) {
                        NetworkChangeNotifier.this.notifyObserversToPurgeActiveNetworkList(jArr);
                    }
                }, registrationPolicy);
                this.mAutoDetector = networkChangeNotifierAutoDetect;
                NetworkChangeNotifierAutoDetect.NetworkState currentNetworkState = networkChangeNotifierAutoDetect.getCurrentNetworkState();
                updateCurrentConnectionType(currentNetworkState.getConnectionType());
                updateCurrentConnectionCost(currentNetworkState.getConnectionCost());
                notifyObserversOfConnectionSubtypeChange(currentNetworkState.getConnectionSubtype());
                return;
            }
            return;
        }
        destroyAutoDetector();
    }

    public static void forceUpdateNetworkTypeInfo() {
        getInstance().forceUpdateNetworkType();
    }

    private void forceUpdateNetworkType() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        if (networkChangeNotifierAutoDetect != null) {
            NetworkChangeNotifierAutoDetect.NetworkState currentNetworkState = networkChangeNotifierAutoDetect.getCurrentNetworkState();
            if (this.mCurrentConnectionType != currentNetworkState.getConnectionType()) {
                this.mCurrentConnectionType = currentNetworkState.getConnectionType();
                this.mAutoDetector.runOnThread(new Runnable() { // from class: com.ttnet.org.chromium.net.NetworkChangeNotifier.2
                    @Override // java.lang.Runnable
                    public void run() {
                        NetworkChangeNotifier networkChangeNotifier = NetworkChangeNotifier.this;
                        networkChangeNotifier.notifyObserversOfConnectionTypeChange(networkChangeNotifier.mCurrentConnectionType);
                    }
                });
            }
        }
    }

    public void mockConnectionTypeChangeForTesting(int i2, long j2) {
        if (BuildConfig.DCHECK_IS_ON) {
            notifyObserversOfConnectionTypeChange(i2, j2);
        }
    }

    public long getCurrentVpnNetId() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        if (networkChangeNotifierAutoDetect == null) {
            return -1L;
        }
        return networkChangeNotifierAutoDetect.getVpnNetId();
    }

    public static void forceConnectivityState(boolean z) {
        setAutoDetectConnectivityState(false);
        getInstance().forceConnectivityStateInternal(z);
    }

    private void forceConnectivityStateInternal(boolean z) {
        if ((this.mCurrentConnectionType != 6) != z) {
            updateCurrentConnectionType(z ? 0 : 6);
            notifyObserversOfConnectionSubtypeChange(!z);
        }
    }

    public static void fakeNetworkConnected(long j2, int i2) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfNetworkConnect(j2, i2, false);
    }

    public static void fakeNetworkSoonToBeDisconnected(long j2) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfNetworkSoonToDisconnect(j2);
    }

    public static void fakeNetworkDisconnected(long j2) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfNetworkDisconnect(j2, false);
    }

    public static void fakePurgeActiveNetworkList(long[] jArr) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversToPurgeActiveNetworkList(jArr);
    }

    public static void fakeDefaultNetwork(long j2, int i2) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfConnectionTypeChange(i2, j2);
    }

    public static void fakeConnectionCostChanged(int i2) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfConnectionCostChange(i2);
    }

    public static void fakeConnectionSubtypeChanged(int i2) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfConnectionSubtypeChange(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurrentConnectionType(int i2) {
        this.mCurrentConnectionType = i2;
        notifyObserversOfConnectionTypeChange(i2);
    }

    void notifyObserversOfConnectionTypeChange(int i2) {
        notifyObserversOfConnectionTypeChange(i2, getCurrentDefaultNetId());
    }

    private void notifyObserversOfConnectionTypeChange(int i2, long j2) {
        Iterator<Long> it = this.mNativeChangeNotifiers.iterator();
        while (it.hasNext()) {
            NetworkChangeNotifierJni.get().notifyConnectionTypeChanged(it.next().longValue(), this, i2, j2);
        }
        Iterator<ConnectionTypeObserver> it2 = this.mConnectionTypeObservers.iterator();
        while (it2.hasNext()) {
            it2.next().onConnectionTypeChanged(i2);
        }
    }

    private void updateCurrentConnectionCost(int i2) {
        this.mCurrentConnectionCost = i2;
        notifyObserversOfConnectionCostChange(i2);
    }

    void notifyObserversOfConnectionCostChange(int i2) {
        Iterator<Long> it = this.mNativeChangeNotifiers.iterator();
        while (it.hasNext()) {
            NetworkChangeNotifierJni.get().notifyConnectionCostChanged(it.next().longValue(), this, i2);
        }
    }

    void notifyObserversOfConnectionSubtypeChange(int i2) {
        Iterator<Long> it = this.mNativeChangeNotifiers.iterator();
        while (it.hasNext()) {
            NetworkChangeNotifierJni.get().notifyMaxBandwidthChanged(it.next().longValue(), this, i2);
        }
    }

    void notifyObserversOfNetworkConnect(long j2, int i2, boolean z) {
        Iterator<Long> it = this.mNativeChangeNotifiers.iterator();
        while (it.hasNext()) {
            NetworkChangeNotifierJni.get().notifyOfNetworkConnect(it.next().longValue(), this, j2, z, i2);
        }
    }

    void notifyObserversOfNetworkSoonToDisconnect(long j2) {
        Iterator<Long> it = this.mNativeChangeNotifiers.iterator();
        while (it.hasNext()) {
            NetworkChangeNotifierJni.get().notifyOfNetworkSoonToDisconnect(it.next().longValue(), this, j2);
        }
    }

    void notifyObserversOfNetworkDisconnect(long j2, boolean z) {
        Iterator<Long> it = this.mNativeChangeNotifiers.iterator();
        while (it.hasNext()) {
            NetworkChangeNotifierJni.get().notifyOfNetworkDisconnect(it.next().longValue(), this, z, j2);
        }
    }

    void notifyObserversToPurgeActiveNetworkList(long[] jArr) {
        Iterator<Long> it = this.mNativeChangeNotifiers.iterator();
        while (it.hasNext()) {
            NetworkChangeNotifierJni.get().notifyPurgeActiveNetworkList(it.next().longValue(), this, jArr);
        }
    }

    public static void addConnectionTypeObserver(ConnectionTypeObserver connectionTypeObserver) {
        getInstance().addConnectionTypeObserverInternal(connectionTypeObserver);
    }

    private void addConnectionTypeObserverInternal(ConnectionTypeObserver connectionTypeObserver) {
        this.mConnectionTypeObservers.addObserver(connectionTypeObserver);
    }

    public static void removeConnectionTypeObserver(ConnectionTypeObserver connectionTypeObserver) {
        getInstance().removeConnectionTypeObserverInternal(connectionTypeObserver);
    }

    private void removeConnectionTypeObserverInternal(ConnectionTypeObserver connectionTypeObserver) {
        this.mConnectionTypeObservers.removeObserver(connectionTypeObserver);
    }

    public static NetworkChangeNotifierAutoDetect getAutoDetectorForTest() {
        return getInstance().mAutoDetector;
    }

    public static boolean isOnline() {
        return getInstance().getCurrentConnectionType() != 6;
    }
}
