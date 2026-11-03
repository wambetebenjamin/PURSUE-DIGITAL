package com.bytedance.android.service.manager;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.android.service.manager.alive.monitor.AliveMonitorService;
import com.bytedance.android.service.manager.alliance.IAllianceService;
import com.bytedance.android.service.manager.client.ai.IClientAiExternalService;
import com.bytedance.android.service.manager.pull.PullExternalService;
import com.bytedance.android.service.manager.push.PushExternalService;
import com.bytedance.android.service.manager.push.notification.IPushNotificationService;
import com.bytedance.android.service.manager.push.trace.PushTraceExternalService;
import com.bytedance.android.service.manager.redbadge.IRedBadgeExternalService;
import com.bytedance.android.service.manager.statistics.IPushStatisticsExternalService;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class PushServiceManager {
    private static volatile AliveMonitorService mAliveMonitorService;
    private static volatile IAllianceService mIAllianceService;
    private static volatile IClientAiExternalService mIClientAiExternalService;
    private static volatile IPushNotificationService mIPushNotificationService;
    private static volatile IPushStatisticsExternalService mIPushStatisticsExternalService;
    private static volatile IRedBadgeExternalService mIRedBadgeExternalService;
    private static volatile PullExternalService mPullExternalService;
    private static volatile PushExternalService mPushExternalService;
    private static volatile PushTraceExternalService mPushTraceExternalService;
    private static volatile PushServiceManager mServiceManagerInstance;
    private Map<String, String> classNameMap;
    private Map<String, Object> instanceManager = new HashMap();

    private PushServiceManager() {
        HashMap hashMap = new HashMap();
        this.classNameMap = hashMap;
        hashMap.put("com.bytedance.android.service.manager.alliance.IAllianceService", "com.bytedance.alliance.core.AllianceServiceImpl");
        this.classNameMap.put("com.bytedance.android.service.manager.push.notification.IPushNotificationService", "com.bytedance.notification.PushNotificationService");
        this.classNameMap.put("com.bytedance.android.service.manager.statistics.IPushStatisticsExternalService", "com.bytedance.push.android.statistics.PushStatisticsServiceProvider");
        this.classNameMap.put("com.bytedance.android.service.manager.client.ai.IClientAiExternalService", "com.bytedance.push.android.PushClientAiServiceProvider");
        this.classNameMap.put("com.bytedance.android.service.manager.alive.monitor.AliveMonitorService", "com.ss.alive.monitor.AliveMonitorServiceProvider");
        this.classNameMap.put("com.bytedance.android.service.manager.redbadge.IRedBadgeExternalService", "com.ss.android.newmedia.redbadge.RedBadgeServiceProvider");
        this.classNameMap.put("com.bytedance.android.service.manager.push.trace.PushTraceExternalService", "com.bytedance.push.trace.PushTraceServiceImpl");
        this.classNameMap.put("com.bytedance.android.service.manager.pull.PullExternalService", "com.ss.android.pull.PullServiceProvider");
        this.classNameMap.put("com.bytedance.android.service.manager.push.PushExternalService", "com.bytedance.push.PushServiceProvider");
    }

    public IAllianceService getIAllianceService() {
        if (mIAllianceService == null) {
            synchronized (this) {
                if (mIAllianceService == null) {
                    Object objectInstance = getObjectInstance(this.classNameMap.get("com.bytedance.android.service.manager.alliance.IAllianceService"));
                    if (objectInstance == null) {
                        if (!TextUtils.equals(this.classNameMap.get("com.bytedance.android.service.manager.alliance.IAllianceService"), "com.bytedance.android.service.manager.alliance.IAllianceServiceImplOfMock")) {
                            objectInstance = getObjectInstance("com.bytedance.android.service.manager.alliance.IAllianceServiceImplOfMock");
                        }
                        if (objectInstance == null) {
                            throw new RuntimeException("impl class not found for com.bytedance.android.service.manager.alliance.IAllianceService");
                        }
                    }
                    mIAllianceService = (IAllianceService) objectInstance;
                }
            }
        }
        return mIAllianceService;
    }

    public IPushNotificationService getIPushNotificationService() {
        if (mIPushNotificationService == null) {
            synchronized (this) {
                if (mIPushNotificationService == null) {
                    Object objectInstance = getObjectInstance(this.classNameMap.get("com.bytedance.android.service.manager.push.notification.IPushNotificationService"));
                    if (objectInstance == null) {
                        if (!TextUtils.equals(this.classNameMap.get("com.bytedance.android.service.manager.push.notification.IPushNotificationService"), "com.bytedance.android.service.manager.push.notification.IPushNotificationServiceImplOfMock")) {
                            objectInstance = getObjectInstance("com.bytedance.android.service.manager.push.notification.IPushNotificationServiceImplOfMock");
                        }
                        if (objectInstance == null) {
                            throw new RuntimeException("impl class not found for com.bytedance.android.service.manager.push.notification.IPushNotificationService");
                        }
                    }
                    mIPushNotificationService = (IPushNotificationService) objectInstance;
                }
            }
        }
        return mIPushNotificationService;
    }

    public IPushStatisticsExternalService getIPushStatisticsExternalService() {
        if (mIPushStatisticsExternalService == null) {
            synchronized (this) {
                if (mIPushStatisticsExternalService == null) {
                    Object objectInstance = getObjectInstance(this.classNameMap.get("com.bytedance.android.service.manager.statistics.IPushStatisticsExternalService"));
                    if (objectInstance == null) {
                        if (!TextUtils.equals(this.classNameMap.get("com.bytedance.android.service.manager.statistics.IPushStatisticsExternalService"), "com.bytedance.android.service.manager.statistics.IPushStatisticsExternalServiceImplOfMock")) {
                            objectInstance = getObjectInstance("com.bytedance.android.service.manager.statistics.IPushStatisticsExternalServiceImplOfMock");
                        }
                        if (objectInstance == null) {
                            throw new RuntimeException("impl class not found for com.bytedance.android.service.manager.statistics.IPushStatisticsExternalService");
                        }
                    }
                    mIPushStatisticsExternalService = (IPushStatisticsExternalService) objectInstance;
                }
            }
        }
        return mIPushStatisticsExternalService;
    }

    public IClientAiExternalService getIClientAiExternalService() {
        if (mIClientAiExternalService == null) {
            synchronized (this) {
                if (mIClientAiExternalService == null) {
                    Object objectInstance = getObjectInstance(this.classNameMap.get("com.bytedance.android.service.manager.client.ai.IClientAiExternalService"));
                    if (objectInstance == null) {
                        if (!TextUtils.equals(this.classNameMap.get("com.bytedance.android.service.manager.client.ai.IClientAiExternalService"), "com.bytedance.android.service.manager.client.ai.IClientAiExternalServiceImplOfMock")) {
                            objectInstance = getObjectInstance("com.bytedance.android.service.manager.client.ai.IClientAiExternalServiceImplOfMock");
                        }
                        if (objectInstance == null) {
                            throw new RuntimeException("impl class not found for com.bytedance.android.service.manager.client.ai.IClientAiExternalService");
                        }
                    }
                    mIClientAiExternalService = (IClientAiExternalService) objectInstance;
                }
            }
        }
        return mIClientAiExternalService;
    }

    public AliveMonitorService getAliveMonitorService() {
        if (mAliveMonitorService == null) {
            synchronized (this) {
                if (mAliveMonitorService == null) {
                    Object objectInstance = getObjectInstance(this.classNameMap.get("com.bytedance.android.service.manager.alive.monitor.AliveMonitorService"));
                    if (objectInstance == null) {
                        if (!TextUtils.equals(this.classNameMap.get("com.bytedance.android.service.manager.alive.monitor.AliveMonitorService"), "com.bytedance.android.service.manager.alive.monitor.AliveMonitorServiceImplOfMock")) {
                            objectInstance = getObjectInstance("com.bytedance.android.service.manager.alive.monitor.AliveMonitorServiceImplOfMock");
                        }
                        if (objectInstance == null) {
                            throw new RuntimeException("impl class not found for com.bytedance.android.service.manager.alive.monitor.AliveMonitorService");
                        }
                    }
                    mAliveMonitorService = (AliveMonitorService) objectInstance;
                }
            }
        }
        return mAliveMonitorService;
    }

    public IRedBadgeExternalService getIRedBadgeExternalService() {
        if (mIRedBadgeExternalService == null) {
            synchronized (this) {
                if (mIRedBadgeExternalService == null) {
                    Object objectInstance = getObjectInstance(this.classNameMap.get("com.bytedance.android.service.manager.redbadge.IRedBadgeExternalService"));
                    if (objectInstance == null) {
                        if (!TextUtils.equals(this.classNameMap.get("com.bytedance.android.service.manager.redbadge.IRedBadgeExternalService"), "com.bytedance.android.service.manager.redbadge.IRedBadgeExternalServiceImplOfMock")) {
                            objectInstance = getObjectInstance("com.bytedance.android.service.manager.redbadge.IRedBadgeExternalServiceImplOfMock");
                        }
                        if (objectInstance == null) {
                            throw new RuntimeException("impl class not found for com.bytedance.android.service.manager.redbadge.IRedBadgeExternalService");
                        }
                    }
                    mIRedBadgeExternalService = (IRedBadgeExternalService) objectInstance;
                }
            }
        }
        return mIRedBadgeExternalService;
    }

    public PushTraceExternalService getPushTraceExternalService() {
        if (mPushTraceExternalService == null) {
            synchronized (this) {
                if (mPushTraceExternalService == null) {
                    Object objectInstance = getObjectInstance(this.classNameMap.get("com.bytedance.android.service.manager.push.trace.PushTraceExternalService"));
                    if (objectInstance == null) {
                        if (!TextUtils.equals(this.classNameMap.get("com.bytedance.android.service.manager.push.trace.PushTraceExternalService"), "com.bytedance.android.service.manager.push.trace.PushTraceExternalServiceImplOfMock")) {
                            objectInstance = getObjectInstance("com.bytedance.android.service.manager.push.trace.PushTraceExternalServiceImplOfMock");
                        }
                        if (objectInstance == null) {
                            throw new RuntimeException("impl class not found for com.bytedance.android.service.manager.push.trace.PushTraceExternalService");
                        }
                    }
                    mPushTraceExternalService = (PushTraceExternalService) objectInstance;
                }
            }
        }
        return mPushTraceExternalService;
    }

    public PullExternalService getPullExternalService() {
        if (mPullExternalService == null) {
            synchronized (this) {
                if (mPullExternalService == null) {
                    Object objectInstance = getObjectInstance(this.classNameMap.get("com.bytedance.android.service.manager.pull.PullExternalService"));
                    if (objectInstance == null) {
                        if (!TextUtils.equals(this.classNameMap.get("com.bytedance.android.service.manager.pull.PullExternalService"), "com.bytedance.android.service.manager.pull.PullExternalServiceImplOfMock")) {
                            objectInstance = getObjectInstance("com.bytedance.android.service.manager.pull.PullExternalServiceImplOfMock");
                        }
                        if (objectInstance == null) {
                            throw new RuntimeException("impl class not found for com.bytedance.android.service.manager.pull.PullExternalService");
                        }
                    }
                    mPullExternalService = (PullExternalService) objectInstance;
                }
            }
        }
        return mPullExternalService;
    }

    public PushExternalService getPushExternalService() {
        if (mPushExternalService == null) {
            synchronized (this) {
                if (mPushExternalService == null) {
                    Object objectInstance = getObjectInstance(this.classNameMap.get("com.bytedance.android.service.manager.push.PushExternalService"));
                    if (objectInstance == null) {
                        if (!TextUtils.equals(this.classNameMap.get("com.bytedance.android.service.manager.push.PushExternalService"), "com.bytedance.android.service.manager.push.PushExternalServiceImplOfMock")) {
                            objectInstance = getObjectInstance("com.bytedance.android.service.manager.push.PushExternalServiceImplOfMock");
                        }
                        if (objectInstance == null) {
                            throw new RuntimeException("impl class not found for com.bytedance.android.service.manager.push.PushExternalService");
                        }
                    }
                    mPushExternalService = (PushExternalService) objectInstance;
                }
            }
        }
        return mPushExternalService;
    }

    public static PushServiceManager get() {
        if (mServiceManagerInstance == null) {
            synchronized (PushServiceManager.class) {
                if (mServiceManagerInstance == null) {
                    mServiceManagerInstance = new PushServiceManager();
                }
            }
        }
        return mServiceManagerInstance;
    }

    private Object getObjectInstance(String str) {
        if (this.instanceManager.containsKey(str)) {
            return this.instanceManager.get(str);
        }
        try {
            Object newInstance = Class.forName(str).newInstance();
            this.instanceManager.put(str, newInstance);
            return newInstance;
        } catch (Throwable th) {
            Log.w("PushServiceManager", "error when getObjectInstance: " + th.getMessage());
            return null;
        }
    }
}
