package com.bytedancehttpdns.httpdns;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.bytedancehttpdns.httpdns.DnsRecord;
import com.bytedancehttpdns.httpdns.DnsTaskInfo;
import com.bytedancehttpdns.httpdns.util.HttpDnsAppMonitor;
import com.bytedancehttpdns.httpdns.util.HttpDnsUtil;
import com.bytedancehttpdns.httpdns.util.IPAddressUtils;
import com.bytedancehttpdns.httpdns.util.Ipv6ConnectionManager;
import com.bytedancehttpdns.httpdns.util.Logger;
import com.bytedancehttpdns.httpdns.util.WeakHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONException;
/* loaded from: classes8.dex */
public class HttpDns implements WeakHandler.IHandler {
    private static final int CORE_POOL_SIZE = 6;
    public static final String HTTPDNS_HARDCODEIP_SP_KEY = "httpdns_hardcodeips";
    public static final String HTTPDNS_HARDCODEIP_SP_NAME = "dispatchersdk_httpdns_hardcodeips";
    private static final String MSG_CALLBACK_DNSRESULT_JOB_KEY = "callback_dnsresult_job_key";
    public static final String MSG_CALLBACK_DNSRESULT_KEY = "callback_dnsresult_key";
    private static final int MSG_CALLBACK_DNS_RESULT = 1;
    private static final String MSG_DNS_TIMEOUT_JOB_KEY = "dns_timeout_job_key";
    private static final int MSG_HTTPDNS_PREFER_TIMEOUT = 2;
    private static final String MSG_HTTPDNS_PREFER_TIMEOUT_JOB_KEY = "httpdns_timeout_job_key";
    private static final int MSG_HTTPDNS_REQUEST_TIMEOUT = 4;
    private static final int MSG_LOAD_LOCAL_HARDCODEIPS = 5;
    private static final int MSG_LOCALDNS_REQUEST_TIMEOUT = 3;
    private static final int MSG_ON_NETWORK_CHANGED = 6;
    private static final int MSG_ON_RESUME = 7;
    private static String TAG = "veHttpDns";
    private static WeakHandler mHttpDnsHandler;
    private static HandlerThread mHttpDnsHandlerThread;
    private static volatile HttpDns mInstance;
    private static String mSdkVersion;
    private static final ExecutorService mThreadPool = Executors.newFixedThreadPool(6);

    /* renamed from: a  reason: collision with root package name */
    private String f16287a;
    private final HostResolveManager mHostResolveManager;
    private volatile IHttpDnsDepend mHttpDnsDepend;
    private String mHttpDnsDomain;
    private final AtomicBoolean mEnableHttpDns = new AtomicBoolean(true);
    private final AtomicBoolean mIsHttpDnsPrefer = new AtomicBoolean(true);
    private final ConcurrentSkipListSet<String> mHardCodeIps = new ConcurrentSkipListSet<>();
    private final ConcurrentSkipListSet<String> mPreResolveHosts = new ConcurrentSkipListSet<>();
    private final AtomicInteger mLocalDnsCacheTTL = new AtomicInteger(30);
    private final AtomicInteger mHttpDnsPreferInterval = new AtomicInteger(5000);
    private final AtomicInteger mHttpDnsRefreshStaleCacheInterval = new AtomicInteger(60);
    private final AtomicInteger mEnableCompareLocalDnsHttpDns = new AtomicInteger(0);
    private final AtomicInteger mHttpDnsTimeout = new AtomicInteger(5);
    private final AtomicInteger mLocalDnsTimeout = new AtomicInteger(5);
    private final AtomicLong mTimeStampsDiff = new AtomicLong(0);
    private final HttpDnsAppMonitor mHttpDnsAppMonitor = new HttpDnsAppMonitor();
    private boolean mHasSetHttpDnsDepend = false;
    private final AtomicBoolean mEnableNetworkChangedRefresh = new AtomicBoolean(true);
    private final AtomicBoolean mEnableCacheStaleRefresh = new AtomicBoolean(true);
    private final AtomicInteger mHttpDnsRequestTimeout = new AtomicInteger(15);
    private final AtomicBoolean mEnableUseStaleCache = new AtomicBoolean(true);
    private final AtomicBoolean mEnableBatchRefresh = new AtomicBoolean(true);
    private final AtomicBoolean mEnableDebug = new AtomicBoolean(false);
    private final AtomicBoolean mEnableHttpDnsWithIpv6Only = new AtomicBoolean(false);
    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper()) { // from class: com.bytedancehttpdns.httpdns.HttpDns.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null || !(message.obj instanceof HttpDns)) {
                return;
            }
            Bundle data = message.getData();
            DnsResult dnsResult = (DnsResult) data.getSerializable("callback_dnsresult_key");
            HostResolveJob hostResolveJob = (HostResolveJob) data.getSerializable(HttpDns.MSG_CALLBACK_DNSRESULT_JOB_KEY);
            if (hostResolveJob != null && message.what == 1) {
                Logger.d(HttpDns.TAG, "callback dns result for host " + hostResolveJob.getHost() + " in thread " + Thread.currentThread().getName());
                hostResolveJob.mHttpDnsCallback.onHttpDnsCompleted(dnsResult);
            }
        }
    };

    public static HttpDns getService() {
        if (mInstance == null) {
            synchronized (HttpDns.class) {
                if (mInstance == null) {
                    mInstance = new HttpDns();
                }
            }
        }
        return mInstance;
    }

    private HttpDns() {
        HandlerThread handlerThread = new HandlerThread("HTTPDNS-Handler");
        mHttpDnsHandlerThread = handlerThread;
        handlerThread.start();
        mHttpDnsHandler = new WeakHandler(mHttpDnsHandlerThread.getLooper(), this);
        this.mHostResolveManager = new HostResolveManager(mHttpDnsHandler);
    }

    public void setHttpDnsDepend(IHttpDnsDepend iHttpDnsDepend) {
        if (this.mHasSetHttpDnsDepend) {
            Logger.d(TAG, "you have set httpdns depend before.");
        } else if (iHttpDnsDepend == null || iHttpDnsDepend.a() == null || TextUtils.isEmpty(iHttpDnsDepend.b()) || TextUtils.isEmpty(iHttpDnsDepend.c()) || (iHttpDnsDepend.d() && iHttpDnsDepend.e() <= 0)) {
            this.mHttpDnsDepend = null;
            throw new IllegalArgumentException("you must set correct httpdns depend");
        } else if (TextUtils.isEmpty(this.mHttpDnsDomain)) {
            throw new IllegalArgumentException("you must set httpdns domain and backup domains");
        } else {
            this.mHttpDnsDepend = iHttpDnsDepend;
            this.mHasSetHttpDnsDepend = true;
            if (!TextUtils.isEmpty(iHttpDnsDepend.g())) {
                this.f16287a = iHttpDnsDepend.g();
            }
            submitIpv6Detect();
            this.mHttpDnsAppMonitor.registerAppMonitorListener(iHttpDnsDepend.a());
            setLogLevel();
            if (this.mHttpDnsDepend.f() != null && this.mHttpDnsDepend.f().length > 0 && this.mHttpDnsDepend.f().length <= 10) {
                this.mPreResolveHosts.addAll(Arrays.asList(this.mHttpDnsDepend.f()));
                doHttpDnsPreload(DnsRecord.CacheStaleReason.PRELOAD_BATCH);
            } else {
                Logger.d(TAG, "httpdns preload domains's number must less than 10, httpdns preload is skipped.");
            }
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.obj = this;
            mHttpDnsHandler.sendMessage(obtain);
        }
    }

    public DnsResult getHttpDnsResultForHostSyncBlock(String str) {
        return getHttpDnsResultForHostSyncBlockInternal(str, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isHttpDnsPrefer() {
        if (this.mIsHttpDnsPrefer.get()) {
            if (this.mEnableHttpDnsWithIpv6Only.get()) {
                return true;
            }
            return !Ipv6ConnectionManager.ipv6Only();
        }
        return false;
    }

    private DnsResult getHttpDnsResultForHostSyncBlockInternal(String str, boolean z) {
        Future<Void> submitLocalDnsResolve;
        DnsResult lookupLocalDnsCache;
        DnsResult lookupHttpDnsCache;
        Logger.d(TAG, "getHttpDnsResultForHostSyncBlock for " + str);
        if (inValidConfigure(str)) {
            DnsResult dnsResult = new DnsResult();
            dnsResult.taskInfoList.add(new DnsTaskInfo(0L, DnsTaskInfo.TaskInfoSource.HTTPDNS, DnsErrorCode.INVALID_HOST.mValue));
            dnsResult.taskInfoList.add(new DnsTaskInfo(0L, DnsTaskInfo.TaskInfoSource.LOCALDNS, DnsErrorCode.INVALID_HOST.mValue));
            return dnsResult;
        } else if (isHttpDnsPrefer()) {
            if (z && (lookupHttpDnsCache = lookupHttpDnsCache(str, false)) != null) {
                Logger.d(TAG, "return httpdns cache for " + str);
                return lookupHttpDnsCache;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(0, false);
            return getDnsResultInHttpDnsPreferTime(submitHttpDnsAndLocalDnsResolve(str, arrayList, true), str, arrayList.get(0).booleanValue());
        } else if (!z || (lookupLocalDnsCache = lookupLocalDnsCache(str, false)) == null) {
            ArrayList arrayList2 = new ArrayList();
            long currentTimeMillis = System.currentTimeMillis();
            if (this.mHostResolveManager.isLocalDnsResolving(str)) {
                submitLocalDnsResolve = this.mHostResolveManager.getLocalDnsResolvingFuture(str);
            } else {
                submitLocalDnsResolve = submitLocalDnsResolve(str);
            }
            if (submitLocalDnsResolve == null) {
                arrayList2.add(new DnsTaskInfo(System.currentTimeMillis() - currentTimeMillis, DnsTaskInfo.TaskInfoSource.LOCALDNS, DnsErrorCode.INVALID_FUTURE.mValue));
                return getHttpDnsResultForLocalDnsPreferRequest(str, arrayList2);
            } else if (submitLocalDnsResolve.isDone()) {
                DnsResult lookupLocalDnsCache2 = lookupLocalDnsCache(str, true);
                arrayList2.add(new DnsTaskInfo(System.currentTimeMillis() - currentTimeMillis, DnsTaskInfo.TaskInfoSource.LOCALDNS, (lookupLocalDnsCache2 == null ? DnsErrorCode.EMPTY_CACHE : DnsErrorCode.OK).mValue));
                if (lookupLocalDnsCache2 == null) {
                    return getHttpDnsResultForLocalDnsPreferRequest(str, arrayList2);
                }
                lookupLocalDnsCache2.taskInfoList = arrayList2;
                return lookupLocalDnsCache2;
            } else {
                try {
                    submitLocalDnsResolve.get(this.mLocalDnsTimeout.get() * 1000, TimeUnit.MILLISECONDS);
                    DnsResult lookupLocalDnsCache3 = lookupLocalDnsCache(str, true);
                    arrayList2.add(new DnsTaskInfo(System.currentTimeMillis() - currentTimeMillis, DnsTaskInfo.TaskInfoSource.LOCALDNS, (lookupLocalDnsCache3 == null ? DnsErrorCode.LOCALDNS_RESOLVE_FAIL : DnsErrorCode.OK).mValue));
                    if (lookupLocalDnsCache3 == null) {
                        return getHttpDnsResultForLocalDnsPreferRequest(str, arrayList2);
                    }
                    lookupLocalDnsCache3.taskInfoList = arrayList2;
                    return lookupLocalDnsCache3;
                } catch (Exception unused) {
                    arrayList2.add(new DnsTaskInfo(System.currentTimeMillis() - currentTimeMillis, DnsTaskInfo.TaskInfoSource.LOCALDNS, DnsErrorCode.PREFER_TIMEOUT.mValue));
                    return getHttpDnsResultForLocalDnsPreferRequest(str, arrayList2);
                }
            }
        } else {
            return lookupLocalDnsCache;
        }
    }

    private DnsResult getHttpDnsResultForLocalDnsPreferRequest(String str, List<DnsTaskInfo> list) {
        Future<Void> submitHttpDnsResolve;
        long currentTimeMillis = System.currentTimeMillis();
        DnsResult lookupHttpDnsCache = lookupHttpDnsCache(str, false);
        if (lookupHttpDnsCache != null) {
            list.add(new DnsTaskInfo(System.currentTimeMillis() - currentTimeMillis, DnsTaskInfo.TaskInfoSource.HTTPDNS, DnsErrorCode.OK.mValue));
            lookupHttpDnsCache.taskInfoList = list;
            return lookupHttpDnsCache;
        }
        if (this.mHostResolveManager.isHttpDnsResolving(str)) {
            submitHttpDnsResolve = this.mHostResolveManager.getHttpDnsResolvingFuture(str);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            submitHttpDnsResolve = submitHttpDnsResolve(arrayList, DnsRecord.CacheStaleReason.CACHE_UNSET, true);
        }
        if (submitHttpDnsResolve == null) {
            list.add(new DnsTaskInfo(System.currentTimeMillis() - currentTimeMillis, DnsTaskInfo.TaskInfoSource.HTTPDNS, DnsErrorCode.INVALID_FUTURE.mValue));
            DnsResult dnsResult = new DnsResult();
            dnsResult.taskInfoList = list;
            return dnsResult;
        } else if (submitHttpDnsResolve.isDone()) {
            return localDnsPerferAddTaskInfo(lookupHttpDnsCache(str, true), list, currentTimeMillis, str, true);
        } else {
            try {
                submitHttpDnsResolve.get(this.mHttpDnsTimeout.get() * 1000, TimeUnit.MILLISECONDS);
                return localDnsPerferAddTaskInfo(lookupHttpDnsCache(str, true), list, currentTimeMillis, str, true);
            } catch (Exception unused) {
                return localDnsPerferAddTaskInfo(lookupHttpDnsCache(str, true), list, currentTimeMillis, str, false);
            }
        }
    }

    private DnsResult localDnsPerferAddTaskInfo(DnsResult dnsResult, List<DnsTaskInfo> list, long j2, String str, boolean z) {
        if (dnsResult == null) {
            dnsResult = new DnsResult();
            if (z) {
                HttpDnsResolveResult httpDnsResolveResult = this.mHostResolveManager.getHttpDnsResolveResult(str);
                list.add(new DnsTaskInfo(System.currentTimeMillis() - j2, DnsTaskInfo.TaskInfoSource.HTTPDNS, httpDnsResolveResult == null ? DnsErrorCode.UNKNOWN.mValue : httpDnsResolveResult.getResult()));
            } else {
                list.add(new DnsTaskInfo(System.currentTimeMillis() - j2, DnsTaskInfo.TaskInfoSource.HTTPDNS, DnsErrorCode.PREFER_TIMEOUT.mValue));
            }
            dnsResult.taskInfoList = list;
        } else {
            addSuccessTaskToDnsResult(dnsResult, list, j2);
        }
        return dnsResult;
    }

    public DnsResult getHttpDnsResultForHostSyncNonBlock(String str) {
        if (inValidConfigure(str)) {
            DnsResult dnsResult = new DnsResult();
            dnsResult.taskInfoList.add(new DnsTaskInfo(0L, DnsTaskInfo.TaskInfoSource.HTTPDNS, DnsErrorCode.INVALID_HOST.mValue));
            dnsResult.taskInfoList.add(new DnsTaskInfo(0L, DnsTaskInfo.TaskInfoSource.LOCALDNS, DnsErrorCode.INVALID_HOST.mValue));
            return dnsResult;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        if (isHttpDnsPrefer()) {
            DnsResult lookupHttpDnsCache = lookupHttpDnsCache(str, false);
            arrayList.add(new DnsTaskInfo(System.currentTimeMillis() - currentTimeMillis, DnsTaskInfo.TaskInfoSource.HTTPDNS, (lookupHttpDnsCache == null ? DnsErrorCode.EMPTY_CACHE : DnsErrorCode.OK).mValue));
            if (lookupHttpDnsCache != null) {
                lookupHttpDnsCache.taskInfoList = arrayList;
                return lookupHttpDnsCache;
            }
            if (!this.mHostResolveManager.isHttpDnsResolving(str)) {
                synchronized (this) {
                    if (!this.mHostResolveManager.isHttpDnsResolving(str)) {
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(str);
                        submitHttpDnsResolve(arrayList2, DnsRecord.CacheStaleReason.CACHE_UNSET, false);
                    }
                }
            }
            return syncNonBlockSetTaskInfo(lookupLocalDnsCache(str, false), DnsTaskInfo.TaskInfoSource.LOCALDNS, arrayList, currentTimeMillis);
        }
        DnsResult lookupLocalDnsCache = lookupLocalDnsCache(str, false);
        arrayList.add(new DnsTaskInfo(System.currentTimeMillis() - currentTimeMillis, DnsTaskInfo.TaskInfoSource.LOCALDNS, (lookupLocalDnsCache == null ? DnsErrorCode.EMPTY_CACHE : DnsErrorCode.OK).mValue));
        if (lookupLocalDnsCache != null) {
            lookupLocalDnsCache.taskInfoList = arrayList;
            return lookupLocalDnsCache;
        }
        if (!this.mHostResolveManager.isLocalDnsResolving(str)) {
            synchronized (this) {
                if (!this.mHostResolveManager.isLocalDnsResolving(str)) {
                    submitLocalDnsResolve(str);
                }
            }
        }
        return syncNonBlockSetTaskInfo(lookupHttpDnsCache(str, false), DnsTaskInfo.TaskInfoSource.HTTPDNS, arrayList, currentTimeMillis);
    }

    private DnsResult syncNonBlockSetTaskInfo(DnsResult dnsResult, DnsTaskInfo.TaskInfoSource taskInfoSource, List<DnsTaskInfo> list, long j2) {
        list.add(new DnsTaskInfo(System.currentTimeMillis() - j2, taskInfoSource, (dnsResult == null ? DnsErrorCode.EMPTY_CACHE : DnsErrorCode.OK).mValue));
        if (dnsResult != null) {
            dnsResult.taskInfoList = list;
            return dnsResult;
        }
        DnsResult dnsResult2 = new DnsResult();
        dnsResult2.taskInfoList = list;
        return dnsResult2;
    }

    public DnsResult getHttpDnsResultForHostAsync(String str, IHttpDnsCallback iHttpDnsCallback) {
        return getHttpDnsResultForHostAsyncInternal(str, iHttpDnsCallback, true);
    }

    private DnsResult getHttpDnsResultForHostAsyncInternal(String str, IHttpDnsCallback iHttpDnsCallback, boolean z) {
        DnsResult lookupLocalDnsCache;
        DnsResult lookupHttpDnsCache;
        if (inValidConfigure(str)) {
            return null;
        }
        if (isHttpDnsPrefer()) {
            if (z && (lookupHttpDnsCache = lookupHttpDnsCache(str, false)) != null) {
                return lookupHttpDnsCache;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(0, false);
            submitHttpDnsAndLocalDnsResolve(str, arrayList, false);
            HostResolveJob hostResolveJob = new HostResolveJob(str, iHttpDnsCallback, arrayList.get(0).booleanValue());
            this.mHostResolveManager.addHttpDnsJob(str, hostResolveJob);
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.obj = this;
            Bundle bundle = new Bundle();
            bundle.putSerializable(MSG_HTTPDNS_PREFER_TIMEOUT_JOB_KEY, hostResolveJob);
            obtain.setData(bundle);
            mHttpDnsHandler.sendMessageDelayed(obtain, this.mHttpDnsPreferInterval.get());
        } else if (z && (lookupLocalDnsCache = lookupLocalDnsCache(str, false)) != null) {
            return lookupLocalDnsCache;
        } else {
            if (!this.mHostResolveManager.isLocalDnsResolving(str)) {
                submitLocalDnsResolve(str);
            }
            HostResolveJob hostResolveJob2 = new HostResolveJob(str, iHttpDnsCallback, true);
            this.mHostResolveManager.addLocalDnsJob(str, hostResolveJob2);
            Message obtain2 = Message.obtain();
            obtain2.what = 3;
            obtain2.obj = this;
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable(MSG_DNS_TIMEOUT_JOB_KEY, hostResolveJob2);
            obtain2.setData(bundle2);
            mHttpDnsHandler.sendMessageDelayed(obtain2, this.mLocalDnsTimeout.get() * 1000);
        }
        return null;
    }

    public DnsResult getHttpDnsResultForHostWithoutCache(String str) {
        return getHttpDnsResultForHostSyncBlockInternal(str, false);
    }

    public DnsResult getHttpDnsResultForHostWithoutCacheAsync(String str, IHttpDnsCallback iHttpDnsCallback) {
        return getHttpDnsResultForHostAsyncInternal(str, iHttpDnsCallback, false);
    }

    private boolean inValidConfigure(String str) {
        if (!this.mHasSetHttpDnsDepend) {
            Logger.d(TAG, "you have not set httpdns depend");
            return true;
        } else if (!this.mEnableHttpDns.get()) {
            Logger.d(TAG, "httpdns was unabled by tnc");
            return true;
        } else if (!HttpDnsUtil.isValidHost(str)) {
            Logger.d(TAG, "illegal host");
            return true;
        } else if (IPAddressUtils.isValidIpv4(str) || IPAddressUtils.isValidIpv6(str)) {
            Logger.d(TAG, "host is ipaddress");
            return true;
        } else {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void doHttpDnsPreload(DnsRecord.CacheStaleReason cacheStaleReason) {
        ConcurrentSkipListSet<String> concurrentSkipListSet = this.mPreResolveHosts;
        if (concurrentSkipListSet != null && concurrentSkipListSet.size() != 0 && this.mPreResolveHosts.size() <= 10) {
            Iterator<String> it = this.mPreResolveHosts.iterator();
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                String next = it.next();
                if (!this.mHostResolveManager.isHttpDnsResolving(next)) {
                    arrayList.add(next);
                }
            }
            if (arrayList.size() == 0) {
                return;
            }
            Logger.d(TAG, "httpdns preload for " + arrayList);
            submitHttpDnsResolve(arrayList, cacheStaleReason, false);
        }
    }

    void refreshHttpDnsCache(String str) {
        Logger.d(TAG, "refresh httpdns stale cache for " + str);
        if (HttpDnsUtil.isValidHost(str) && !this.mHostResolveManager.isHttpDnsResolving(str) && this.mIsHttpDnsPrefer.get()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            submitHttpDnsResolve(arrayList, DnsRecord.CacheStaleReason.CACHE_STALE_EXPIRED, false);
        }
    }

    void removeHttpDnsCache(String str) {
        if (!HttpDnsUtil.isValidHost(str) || this.mHostResolveManager.isHttpDnsResolving(str)) {
            return;
        }
        this.mHostResolveManager.removeHttpDnsCache(str);
    }

    void addHttpDnsStaleCacheHost(String str) {
        if (batchRefreshEnabled()) {
            this.mHostResolveManager.addHttpDnsStaleCacheHost(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void batchRefreshHttpDnsStaleCache(List<String> list) {
        Logger.d(TAG, "batch refresh httpdns result for " + list);
        submitHttpDnsResolve(list, DnsRecord.CacheStaleReason.REFRESH_BATCH, false);
    }

    void removeLocalDnsStaleCache(String str) {
        if (HttpDnsUtil.isValidHost(str)) {
            this.mHostResolveManager.removeLocalDnsCache(str);
        }
    }

    private Future<Void> submitHttpDnsAndLocalDnsResolve(String str, List<Boolean> list, boolean z) {
        Future<Void> submitHttpDnsResolve;
        DnsRecord localDnsCache = this.mHostResolveManager.getLocalDnsCache(str);
        if (localDnsCache == null || localDnsCache.getFetchTime() + (localDnsCache.getTTL() * 1000) <= getService().getCurrentTimeStamps() + this.mHttpDnsPreferInterval.get()) {
            list.set(0, true);
            if (!this.mHostResolveManager.isLocalDnsResolving(str)) {
                submitLocalDnsResolve(str);
            }
        }
        if (this.mHostResolveManager.isHttpDnsResolving(str)) {
            return this.mHostResolveManager.getHttpDnsResolvingFuture(str);
        }
        synchronized (this) {
            if (this.mHostResolveManager.isHttpDnsResolving(str)) {
                submitHttpDnsResolve = this.mHostResolveManager.getHttpDnsResolvingFuture(str);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                submitHttpDnsResolve = submitHttpDnsResolve(arrayList, DnsRecord.CacheStaleReason.CACHE_UNSET, z);
            }
        }
        return submitHttpDnsResolve;
    }

    synchronized Future<Void> submitHttpDnsResolve(List<String> list, DnsRecord.CacheStaleReason cacheStaleReason, boolean z) {
        Future<Void> future = null;
        if (list != null) {
            if (list.size() != 0) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (this.mHostResolveManager.isHttpDnsResolving(list.get(i2))) {
                        if (z && list.size() == 1 && i2 == 0) {
                            future = this.mHostResolveManager.getHttpDnsResolvingFuture(list.get(i2));
                        }
                        list.remove(list.get(i2));
                    }
                }
                if (list.size() == 0) {
                    return future;
                }
                try {
                    future = mThreadPool.submit(new HttpDnsResolveCall(HttpDnsUtil.transHostList2String(list), mSdkVersion, this.mHostResolveManager, cacheStaleReason, mHttpDnsHandler));
                    for (String str : list) {
                        this.mHostResolveManager.addHttpDnsResolvingFuture(str, future);
                        if (this.mIsHttpDnsPrefer.get()) {
                            this.mHostResolveManager.removeHttpDnsStaleCacheHost(str);
                        }
                    }
                } catch (RejectedExecutionException e2) {
                    e2.printStackTrace();
                }
                return future;
            }
        }
        return null;
    }

    private synchronized Future<Void> submitLocalDnsResolve(String str) {
        if (this.mHostResolveManager.isLocalDnsResolving(str)) {
            return this.mHostResolveManager.getLocalDnsResolvingFuture(str);
        }
        Future<Void> future = null;
        try {
            future = mThreadPool.submit(new LocalDnsResolveCall(str, this.mHostResolveManager, mHttpDnsHandler));
            this.mHostResolveManager.addLocalDnsResolvingFuture(str, future);
        } catch (RejectedExecutionException e2) {
            e2.printStackTrace();
        }
        return future;
    }

    private DnsResult getDnsResultInHttpDnsPreferTime(Future<Void> future, String str, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        if (future == null) {
            arrayList.add(new DnsTaskInfo(System.currentTimeMillis() - currentTimeMillis, DnsTaskInfo.TaskInfoSource.HTTPDNS, DnsErrorCode.INVALID_FUTURE.mValue));
            DnsResult lookupLocalDnsCache = lookupLocalDnsCache(str, z);
            arrayList.add(new DnsTaskInfo(System.currentTimeMillis() - currentTimeMillis, DnsTaskInfo.TaskInfoSource.LOCALDNS, (lookupLocalDnsCache == null ? DnsErrorCode.EMPTY_CACHE : DnsErrorCode.OK).mValue));
            if (lookupLocalDnsCache == null) {
                lookupLocalDnsCache = new DnsResult();
            }
            lookupLocalDnsCache.taskInfoList = arrayList;
            return lookupLocalDnsCache;
        } else if (future.isDone()) {
            return lookupHttpDnsCacheAfterHttpDnsSubmit(str, arrayList, currentTimeMillis, z, DnsErrorCode.EMPTY_CACHE.mValue, true);
        } else {
            try {
                future.get(getService().getHttpDnsPreferInterval().get(), TimeUnit.MILLISECONDS);
                return lookupHttpDnsCacheAfterHttpDnsSubmit(str, arrayList, currentTimeMillis, z, DnsErrorCode.EMPTY_CACHE.mValue, true);
            } catch (Exception unused) {
                return lookupHttpDnsCacheAfterHttpDnsSubmit(str, arrayList, currentTimeMillis, z, DnsErrorCode.PREFER_TIMEOUT.mValue, false);
            }
        }
    }

    private DnsResult lookupHttpDnsCacheAfterHttpDnsSubmit(String str, List<DnsTaskInfo> list, long j2, boolean z, int i2, boolean z2) {
        DnsResult lookupHttpDnsCache = lookupHttpDnsCache(str, true);
        if (lookupHttpDnsCache == null) {
            if (z2) {
                HttpDnsResolveResult httpDnsResolveResult = this.mHostResolveManager.getHttpDnsResolveResult(str);
                list.add(new DnsTaskInfo(System.currentTimeMillis() - j2, DnsTaskInfo.TaskInfoSource.HTTPDNS, httpDnsResolveResult == null ? DnsErrorCode.EMPTY_CACHE.mValue : httpDnsResolveResult.getResult()));
            } else {
                list.add(new DnsTaskInfo(System.currentTimeMillis() - j2, DnsTaskInfo.TaskInfoSource.HTTPDNS, DnsErrorCode.PREFER_TIMEOUT.mValue));
            }
            DnsResult lookupLocalDnsCache = lookupLocalDnsCache(str, z);
            long currentTimeMillis = System.currentTimeMillis() - j2;
            DnsTaskInfo.TaskInfoSource taskInfoSource = DnsTaskInfo.TaskInfoSource.LOCALDNS;
            if (lookupLocalDnsCache != null) {
                i2 = DnsErrorCode.OK.mValue;
            }
            list.add(new DnsTaskInfo(currentTimeMillis, taskInfoSource, i2));
            if (lookupLocalDnsCache == null) {
                lookupLocalDnsCache = new DnsResult();
            }
            DnsResult dnsResult = lookupLocalDnsCache;
            dnsResult.taskInfoList = list;
            return dnsResult;
        }
        addSuccessTaskToDnsResult(lookupHttpDnsCache, list, j2);
        return lookupHttpDnsCache;
    }

    private void addSuccessTaskToDnsResult(DnsResult dnsResult, List<DnsTaskInfo> list, long j2) {
        list.add(new DnsTaskInfo(System.currentTimeMillis() - j2, DnsTaskInfo.TaskInfoSource.HTTPDNS, DnsErrorCode.OK.mValue));
        dnsResult.taskInfoList = list;
    }

    private DnsResult lookupLocalDnsCache(String str, boolean z) {
        DnsResult dnsResult = new DnsResult();
        DnsRecord localDnsCache = this.mHostResolveManager.getLocalDnsCache(str);
        if (localDnsCache == null) {
            return null;
        }
        dnsResult.ipv4List = localDnsCache.getIpv4List();
        dnsResult.ipv6List = localDnsCache.getIpv6List();
        dnsResult.ttl = this.mLocalDnsCacheTTL.get();
        dnsResult.host = str;
        dnsResult.rtt = localDnsCache.getRtt();
        dnsResult.cip = localDnsCache.getCip();
        if (z) {
            dnsResult.source = Source.LOCALDNS_REQUEST;
        } else {
            dnsResult.source = Source.LOCALDNS_CACHE;
        }
        return dnsResult;
    }

    private DnsResult lookupHttpDnsCache(String str, boolean z) {
        DnsRecord httpDnsCache = this.mHostResolveManager.getHttpDnsCache(str);
        if (httpDnsCache == null) {
            Logger.d(TAG, "httpdns cache for " + str + " is null");
            return null;
        } else if (!useStaleCacheEnabled() && (httpDnsCache.getTTL() * 1000) + httpDnsCache.getFetchTime() < getService().getCurrentTimeStamps()) {
            Logger.d("can't use stale cache and cache for " + str + " is staled");
            return null;
        } else {
            DnsResult dnsResult = new DnsResult();
            dnsResult.ipv4List = httpDnsCache.getIpv4List();
            dnsResult.ipv6List = httpDnsCache.getIpv6List();
            dnsResult.ttl = httpDnsCache.getTTL();
            dnsResult.host = str;
            dnsResult.rtt = httpDnsCache.getRtt();
            dnsResult.cip = httpDnsCache.getCip();
            if (z) {
                if ((httpDnsCache.getTTL() * 1000) + httpDnsCache.getFetchTime() > getService().getCurrentTimeStamps()) {
                    dnsResult.source = Source.HTTPDNS_REQUEST;
                    return dnsResult;
                }
                return null;
            }
            if ((httpDnsCache.getTTL() * 1000) + httpDnsCache.getFetchTime() > getService().getCurrentTimeStamps()) {
                dnsResult.source = Source.HTTPDNS_CACHE;
            } else {
                dnsResult.source = Source.HTTPDNS_STALE_CACHE;
            }
            return dnsResult;
        }
    }

    @Override // com.bytedancehttpdns.httpdns.util.WeakHandler.IHandler
    public void handleMsg(Message message) {
        if (message != null) {
            if ((message.obj instanceof HttpDns) || (message.obj instanceof HttpDnsResolveCall) || (message.obj instanceof LocalDnsResolveCall) || (message.obj instanceof DnsRecord) || (message.obj instanceof HostResolveManager) || (message.obj instanceof HttpDnsResolveResult)) {
                try {
                    if ((message.obj instanceof HttpDns) && message.what == 2) {
                        HostResolveJob hostResolveJob = (HostResolveJob) message.getData().getSerializable(MSG_HTTPDNS_PREFER_TIMEOUT_JOB_KEY);
                        if (hostResolveJob != null && this.mHostResolveManager.shouldHttpDnsTimeoutCallback(hostResolveJob)) {
                            callbackDnsResultInMainthread(hostResolveJob, lookupLocalDnsCache(hostResolveJob.getHost(), hostResolveJob.isLocalDnsExpired()));
                            this.mHostResolveManager.removeHttpDnsJob(hostResolveJob);
                        }
                    } else if ((message.obj instanceof HttpDnsResolveCall) && message.what == 3) {
                        ArrayList<String> stringArrayList = message.getData().getStringArrayList("httpdns_completed_hosts");
                        if (stringArrayList == null) {
                            return;
                        }
                        for (String str : stringArrayList) {
                            if (this.mHostResolveManager.shouldHttpDnsCallback(str)) {
                                Iterator<HostResolveJob> it = this.mHostResolveManager.getHttpDnsJobs(str).iterator();
                                while (it.hasNext()) {
                                    HostResolveJob next = it.next();
                                    DnsResult lookupHttpDnsCache = lookupHttpDnsCache(str, true);
                                    if (getService().isHttpDnsPrefer() && lookupHttpDnsCache == null) {
                                        lookupHttpDnsCache = lookupLocalDnsCache(str, next.isLocalDnsExpired());
                                    }
                                    callbackDnsResultInMainthread(next, lookupHttpDnsCache);
                                    this.mHostResolveManager.removeHttpDnsJob(next);
                                }
                            }
                        }
                    } else {
                        if ((message.obj instanceof LocalDnsResolveCall) && message.what == 1) {
                            String string = message.getData().getString("localdns_completed_host");
                            if (string != null && this.mHostResolveManager.shouldLocalDnsCallback(string)) {
                                Iterator<HostResolveJob> it2 = this.mHostResolveManager.getLocalDnsJobs(string).iterator();
                                while (it2.hasNext()) {
                                    HostResolveJob next2 = it2.next();
                                    DnsResult lookupLocalDnsCache = lookupLocalDnsCache(string, true);
                                    if (lookupLocalDnsCache == null) {
                                        DnsResult lookupHttpDnsCache2 = lookupHttpDnsCache(string, false);
                                        if (lookupHttpDnsCache2 == null) {
                                            submitHttpDnsTaskForLocalDnsPreferReq(next2);
                                            this.mHostResolveManager.addHttpDnsJob(next2.getHost(), next2);
                                        } else {
                                            callbackDnsResultInMainthread(next2, lookupHttpDnsCache2);
                                        }
                                    } else {
                                        callbackDnsResultInMainthread(next2, lookupLocalDnsCache);
                                    }
                                    this.mHostResolveManager.removeLocalDnsJob(next2);
                                }
                            }
                        } else if ((message.obj instanceof HttpDns) && message.what == 3) {
                            HostResolveJob hostResolveJob2 = (HostResolveJob) message.getData().getSerializable(MSG_DNS_TIMEOUT_JOB_KEY);
                            if (hostResolveJob2 != null && this.mHostResolveManager.shouldLocalDnsTimeoutCallback(hostResolveJob2)) {
                                DnsResult lookupHttpDnsCache3 = lookupHttpDnsCache(hostResolveJob2.getHost(), false);
                                if (lookupHttpDnsCache3 != null) {
                                    callbackDnsResultInMainthread(hostResolveJob2, lookupHttpDnsCache3);
                                } else {
                                    if (!this.mHostResolveManager.isHttpDnsResolving(hostResolveJob2.getHost())) {
                                        submitHttpDnsTaskForLocalDnsPreferReq(hostResolveJob2);
                                    }
                                    this.mHostResolveManager.addHttpDnsJob(hostResolveJob2.getHost(), hostResolveJob2);
                                }
                                this.mHostResolveManager.removeLocalDnsJob(hostResolveJob2);
                            }
                        } else if ((message.obj instanceof HttpDns) && message.what == 4) {
                            HostResolveJob hostResolveJob3 = (HostResolveJob) message.getData().getSerializable(MSG_DNS_TIMEOUT_JOB_KEY);
                            if (hostResolveJob3 != null && this.mHostResolveManager.shouldHttpDnsTimeoutCallback(hostResolveJob3)) {
                                callbackDnsResultInMainthread(hostResolveJob3, null);
                                this.mHostResolveManager.removeHttpDnsJob(hostResolveJob3);
                            }
                        } else if ((message.obj instanceof HttpDns) && message.what == 5) {
                            String string2 = this.mHttpDnsDepend.a().getSharedPreferences("dispatchersdk_httpdns_hardcodeips", 0).getString("httpdns_hardcodeips", "");
                            if (TextUtils.isEmpty(string2)) {
                                return;
                            }
                            try {
                                JSONArray jSONArray = new JSONArray(string2);
                                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                    String optString = jSONArray.optString(i2);
                                    if (!TextUtils.isEmpty(optString)) {
                                        this.mHardCodeIps.add(optString);
                                    }
                                }
                            } catch (JSONException unused) {
                            }
                        } else if ((message.obj instanceof HttpDns) && message.what == 6) {
                            this.mHostResolveManager.onNetworkChanged(getService().getHttpDnsDepend().a());
                        } else if ((message.obj instanceof HttpDns) && message.what == 7) {
                            submitIpv6Detect();
                        } else if (message.obj instanceof DnsRecord) {
                            String string3 = message.getData().getString("dnsrecord_host");
                            if (TextUtils.isEmpty(string3)) {
                                return;
                            }
                            switch (message.what) {
                                case 10:
                                    getService().refreshHttpDnsCache(string3);
                                    return;
                                case 11:
                                    getService().removeLocalDnsStaleCache(string3);
                                    return;
                                case 12:
                                    getService().addHttpDnsStaleCacheHost(string3);
                                    return;
                                case 13:
                                    getService().removeHttpDnsCache(string3);
                                    return;
                                default:
                                    return;
                            }
                        } else if ((message.obj instanceof HostResolveManager) && message.what == 20) {
                            this.mHostResolveManager.resetHttpDnsDomainFailedCnt();
                        } else if ((message.obj instanceof HttpDnsResolveResult) && message.what == 21) {
                            String string4 = message.getData().getString(HttpDnsResolveResult.HTTPDNS_RESULT_HOST_KEY);
                            if (TextUtils.isEmpty(string4)) {
                                return;
                            }
                            this.mHostResolveManager.removeHttpDnsResolveResult(string4);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    private void submitHttpDnsTaskForLocalDnsPreferReq(HostResolveJob hostResolveJob) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(hostResolveJob.getHost());
        submitHttpDnsResolve(arrayList, DnsRecord.CacheStaleReason.CACHE_UNSET, false);
        Message obtain = Message.obtain();
        obtain.what = 4;
        obtain.obj = this;
        Bundle bundle = new Bundle();
        bundle.putSerializable(MSG_DNS_TIMEOUT_JOB_KEY, hostResolveJob);
        obtain.setData(bundle);
        mHttpDnsHandler.sendMessageDelayed(obtain, this.mHttpDnsTimeout.get() * 1000);
    }

    private void callbackDnsResultInMainthread(HostResolveJob hostResolveJob, DnsResult dnsResult) {
        Message obtain = Message.obtain();
        obtain.obj = this;
        obtain.what = 1;
        Bundle bundle = new Bundle();
        bundle.putSerializable(MSG_CALLBACK_DNSRESULT_JOB_KEY, hostResolveJob);
        bundle.putSerializable("callback_dnsresult_key", dnsResult);
        obtain.setData(bundle);
        this.mMainThreadHandler.sendMessage(obtain);
    }

    AtomicInteger getHttpDnsPreferInterval() {
        return this.mHttpDnsPreferInterval;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AtomicInteger getLocalDnsCacheTTL() {
        return this.mLocalDnsCacheTTL;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AtomicInteger getHttpDnsRefreshStaleCacheInterval() {
        return this.mHttpDnsRefreshStaleCacheInterval;
    }

    public IHttpDnsDepend getHttpDnsDepend() {
        return this.mHttpDnsDepend;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getHttpDnsDomain() {
        return this.mHttpDnsDomain;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConcurrentSkipListSet<String> getHardCodeIps() {
        return this.mHardCodeIps;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHttpDnsHardCodeIps(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        this.mHardCodeIps.clear();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            String optString = jSONArray.optString(i2);
            if (!TextUtils.isEmpty(optString)) {
                this.mHardCodeIps.add(optString);
            }
        }
        SharedPreferences.Editor edit = this.mHttpDnsDepend.a().getSharedPreferences("dispatchersdk_httpdns_hardcodeips", 0).edit();
        edit.putString("httpdns_hardcodeips", String.valueOf(jSONArray));
        edit.apply();
    }

    public void onResume() {
        Message obtain = Message.obtain();
        obtain.obj = this;
        obtain.what = 7;
        mHttpDnsHandler.sendMessage(obtain);
    }

    public void onNetworkChanged() {
        Message obtain = Message.obtain();
        obtain.obj = this;
        obtain.what = 6;
        mHttpDnsHandler.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void submitIpv6Detect() {
        try {
            mThreadPool.submit(new Ipv6DetectCall());
        } catch (RejectedExecutionException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WeakHandler getHttpDnsHandler() {
        return mHttpDnsHandler;
    }

    public void setBatchRefreshCount(int i2) {
        this.mHostResolveManager.setBatchRefreshCount(i2);
    }

    public void enableBatchRefresh(boolean z) {
        this.mEnableBatchRefresh.set(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean batchRefreshEnabled() {
        return this.mEnableBatchRefresh.get();
    }

    public void setHttpDnsPreferTime(int i2) {
        this.mHttpDnsPreferInterval.set(i2);
    }

    public void setHttpDnsRequestTimeout(int i2) {
        this.mHttpDnsRequestTimeout.set(i2);
    }

    public int getHttpDnsRequestTimeout() {
        return this.mHttpDnsRequestTimeout.get();
    }

    public void enableNetworkChangedRefresh(boolean z) {
        this.mEnableNetworkChangedRefresh.set(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getNetworkChangedRefresh() {
        return this.mEnableNetworkChangedRefresh.get();
    }

    public synchronized void setPreloadHosts(List<String> list) {
        this.mPreResolveHosts.clear();
        if (list != null) {
            this.mPreResolveHosts.addAll(list);
        }
    }

    public void enableRefreshStaleCache(boolean z) {
        this.mEnableCacheStaleRefresh.set(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean refreshStaleCacheEnabled() {
        return this.mEnableCacheStaleRefresh.get();
    }

    public void setUseStaleCache(boolean z) {
        this.mEnableUseStaleCache.set(z);
    }

    boolean useStaleCacheEnabled() {
        return this.mEnableUseStaleCache.get();
    }

    public void setHttpDnsCacheStaleTime(int i2) {
        this.mHttpDnsRefreshStaleCacheInterval.set(i2);
    }

    public void setCurrentTimeStamps(long j2) {
        this.mTimeStampsDiff.set(j2 - System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getCurrentTimeStamps() {
        return System.currentTimeMillis() + this.mTimeStampsDiff.get();
    }

    public void setHttpDnsPrefer(boolean z) {
        this.mIsHttpDnsPrefer.set(z);
    }

    public void setEnableDebug(boolean z) {
        this.mEnableDebug.set(z);
        setLogLevel();
    }

    private void setLogLevel() {
        if (this.mEnableDebug.get()) {
            Logger.setLogLevel(3);
        } else {
            Logger.setLogLevel(6);
        }
    }

    public void setHttpDnsDomain(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mHttpDnsDomain = str;
    }

    public void enableHttpdnsWithIpv6Only(boolean z) {
        this.mEnableHttpDnsWithIpv6Only.set(z);
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f16287a = str;
    }

    public String a() {
        return this.f16287a;
    }

    public void a(List<String> list) {
        if (list.isEmpty()) {
            return;
        }
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                this.mHardCodeIps.add(str);
            }
        }
    }
}
