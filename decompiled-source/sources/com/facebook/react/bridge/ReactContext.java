package com.facebook.react.bridge;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes8.dex */
public class ReactContext extends ContextWrapper {
    private static final String EARLY_JS_ACCESS_EXCEPTION_MESSAGE = "Tried to access a JS module before the React instance was fully set up. Calls to ReactContext#getJSModule should only happen once initialize() has been called on your native module.";
    private final CopyOnWriteArraySet<ActivityEventListener> mActivityEventListeners;
    private CatalystInstance mCatalystInstance;
    private WeakReference<Activity> mCurrentActivity;
    private NativeModuleCallExceptionHandler mExceptionHandlerWrapper;
    private LayoutInflater mInflater;
    private MessageQueueThread mJSMessageQueueThread;
    private final CopyOnWriteArraySet<LifecycleEventListener> mLifecycleEventListeners;
    private LifecycleState mLifecycleState;
    private NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    private MessageQueueThread mNativeModulesMessageQueueThread;
    private UIManagerModule mUIManagerModule;
    private MessageQueueThread mUiMessageQueueThread;
    private final CopyOnWriteArraySet<WindowFocusChangeListener> mWindowFocusEventListeners;

    public void setUIManagerModule(UIManagerModule uIManagerModule) {
    }

    public ReactContext(Context context) {
        super(context);
        this.mLifecycleEventListeners = new CopyOnWriteArraySet<>();
        this.mActivityEventListeners = new CopyOnWriteArraySet<>();
        this.mWindowFocusEventListeners = new CopyOnWriteArraySet<>();
        this.mLifecycleState = LifecycleState.BEFORE_CREATE;
    }

    public void initializeWithInstance(CatalystInstance catalystInstance) {
        if (catalystInstance == null) {
            throw new IllegalArgumentException("CatalystInstance cannot be null.");
        }
        if (this.mCatalystInstance != null) {
            throw new IllegalStateException("ReactContext has been already initialized");
        }
        this.mCatalystInstance = catalystInstance;
        initializeMessageQueueThreads(catalystInstance.getReactQueueConfiguration());
    }

    public void initializeMessageQueueThreads(ReactQueueConfiguration reactQueueConfiguration) {
        if (this.mUiMessageQueueThread != null || this.mNativeModulesMessageQueueThread != null || this.mJSMessageQueueThread != null) {
            throw new IllegalStateException("Message queue threads already initialized");
        }
        this.mUiMessageQueueThread = reactQueueConfiguration.getUIQueueThread();
        this.mNativeModulesMessageQueueThread = reactQueueConfiguration.getNativeModulesQueueThread();
        this.mJSMessageQueueThread = reactQueueConfiguration.getJSQueueThread();
    }

    public void resetPerfStats() {
        MessageQueueThread messageQueueThread = this.mNativeModulesMessageQueueThread;
        if (messageQueueThread != null) {
            messageQueueThread.resetPerfStats();
        }
        MessageQueueThread messageQueueThread2 = this.mJSMessageQueueThread;
        if (messageQueueThread2 != null) {
            messageQueueThread2.resetPerfStats();
        }
    }

    public void setNativeModuleCallExceptionHandler(NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mNativeModuleCallExceptionHandler = nativeModuleCallExceptionHandler;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if ("layout_inflater".equals(str)) {
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
            }
            return this.mInflater;
        }
        return getBaseContext().getSystemService(str);
    }

    public <T extends JavaScriptModule> T getJSModule(Class<T> cls) {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        if (catalystInstance == null) {
            throw new RuntimeException(EARLY_JS_ACCESS_EXCEPTION_MESSAGE);
        }
        return (T) catalystInstance.getJSModule(cls);
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        if (catalystInstance == null) {
            throw new RuntimeException("Trying to call native module before CatalystInstance has been set!");
        }
        return catalystInstance.hasNativeModule(cls);
    }

    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        if (catalystInstance == null) {
            throw new RuntimeException("Trying to call native module before CatalystInstance has been set!");
        }
        return (T) catalystInstance.getNativeModule(cls);
    }

    public UIManagerModule getUIManagerModule() {
        return (UIManagerModule) getNativeModule(UIManagerModule.class);
    }

    public EventDispatcher getEventDispatcher() {
        UIManagerModule uIManagerModule = getUIManagerModule();
        if (uIManagerModule != null) {
            return uIManagerModule.getEventDispatcher();
        }
        return null;
    }

    public CatalystInstance getCatalystInstance() {
        return this.mCatalystInstance;
    }

    public boolean hasActiveCatalystInstance() {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        return (catalystInstance == null || catalystInstance.isDestroyed()) ? false : true;
    }

    public boolean hasCatalystInstance() {
        return this.mCatalystInstance != null;
    }

    public LifecycleState getLifecycleState() {
        return this.mLifecycleState;
    }

    public void addLifecycleEventListener(final LifecycleEventListener lifecycleEventListener) {
        int i2;
        this.mLifecycleEventListeners.add(lifecycleEventListener);
        if (!hasActiveCatalystInstance() || (i2 = AnonymousClass2.$SwitchMap$com$facebook$react$common$LifecycleState[this.mLifecycleState.ordinal()]) == 1 || i2 == 2) {
            return;
        }
        if (i2 == 3) {
            runOnUiQueueThread(new Runnable() { // from class: com.facebook.react.bridge.ReactContext.1
                @Override // java.lang.Runnable
                public void run() {
                    if (ReactContext.this.mLifecycleEventListeners.contains(lifecycleEventListener)) {
                        try {
                            lifecycleEventListener.onHostResume();
                        } catch (RuntimeException e2) {
                            ReactContext.this.handleException(e2);
                        }
                    }
                }
            });
            return;
        }
        throw new RuntimeException("Unhandled lifecycle state.");
    }

    /* renamed from: com.facebook.react.bridge.ReactContext$2  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$common$LifecycleState;

        static {
            int[] iArr = new int[LifecycleState.values().length];
            $SwitchMap$com$facebook$react$common$LifecycleState = iArr;
            try {
                iArr[LifecycleState.BEFORE_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$common$LifecycleState[LifecycleState.BEFORE_RESUME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$common$LifecycleState[LifecycleState.RESUMED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void removeLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        this.mLifecycleEventListeners.remove(lifecycleEventListener);
    }

    public void addActivityEventListener(ActivityEventListener activityEventListener) {
        this.mActivityEventListeners.add(activityEventListener);
    }

    public void removeActivityEventListener(ActivityEventListener activityEventListener) {
        this.mActivityEventListeners.remove(activityEventListener);
    }

    public void addWindowFocusChangeListener(WindowFocusChangeListener windowFocusChangeListener) {
        this.mWindowFocusEventListeners.add(windowFocusChangeListener);
    }

    public void removeWindowFocusChangeListener(WindowFocusChangeListener windowFocusChangeListener) {
        this.mWindowFocusEventListeners.remove(windowFocusChangeListener);
    }

    public void onHostResume(Activity activity) {
        this.mLifecycleState = LifecycleState.RESUMED;
        this.mCurrentActivity = new WeakReference<>(activity);
        ReactMarker.logMarker(ReactMarkerConstants.ON_HOST_RESUME_START);
        Iterator<LifecycleEventListener> it = this.mLifecycleEventListeners.iterator();
        while (it.hasNext()) {
            try {
                it.next().onHostResume();
            } catch (RuntimeException e2) {
                handleException(e2);
            }
        }
        ReactMarker.logMarker(ReactMarkerConstants.ON_HOST_RESUME_END);
    }

    public void onNewIntent(Activity activity, Intent intent) {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentActivity = new WeakReference<>(activity);
        Iterator<ActivityEventListener> it = this.mActivityEventListeners.iterator();
        while (it.hasNext()) {
            try {
                it.next().onNewIntent(intent);
            } catch (RuntimeException e2) {
                handleException(e2);
            }
        }
    }

    public void onHostPause() {
        this.mLifecycleState = LifecycleState.BEFORE_RESUME;
        ReactMarker.logMarker(ReactMarkerConstants.ON_HOST_PAUSE_START);
        Iterator<LifecycleEventListener> it = this.mLifecycleEventListeners.iterator();
        while (it.hasNext()) {
            try {
                it.next().onHostPause();
            } catch (RuntimeException e2) {
                handleException(e2);
            }
        }
        ReactMarker.logMarker(ReactMarkerConstants.ON_HOST_PAUSE_END);
    }

    public void onHostDestroy() {
        UiThreadUtil.assertOnUiThread();
        this.mLifecycleState = LifecycleState.BEFORE_CREATE;
        Iterator<LifecycleEventListener> it = this.mLifecycleEventListeners.iterator();
        while (it.hasNext()) {
            try {
                it.next().onHostDestroy();
            } catch (RuntimeException e2) {
                handleException(e2);
            }
        }
        this.mCurrentActivity = null;
    }

    public void destroy(boolean z) {
        UiThreadUtil.assertOnUiThread();
        if (this.mCatalystInstance != null) {
            Log.e("ReactContext", "clearRegistry:" + z);
            if (z) {
                Log.e("ReactContext", "clear Registry now");
                UIManagerModule uIManagerModule = (UIManagerModule) getNativeModule(UIManagerModule.class);
                if (uIManagerModule != null) {
                    uIManagerModule.setClearRegitry(z);
                }
                this.mCatalystInstance.clearRegistry(z);
                this.mUIManagerModule = null;
            }
            this.mCatalystInstance.destroy();
        }
    }

    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        Iterator<ActivityEventListener> it = this.mActivityEventListeners.iterator();
        while (it.hasNext()) {
            try {
                it.next().onActivityResult(activity, i2, i3, intent);
            } catch (RuntimeException e2) {
                handleException(e2);
            }
        }
    }

    public void onWindowFocusChange(boolean z) {
        UiThreadUtil.assertOnUiThread();
        Iterator<WindowFocusChangeListener> it = this.mWindowFocusEventListeners.iterator();
        while (it.hasNext()) {
            try {
                it.next().onWindowFocusChange(z);
            } catch (RuntimeException e2) {
                handleException(e2);
            }
        }
    }

    public void assertOnUiQueueThread() {
        ((MessageQueueThread) Assertions.assertNotNull(this.mUiMessageQueueThread)).assertIsOnThread();
    }

    public boolean isOnUiQueueThread() {
        return ((MessageQueueThread) Assertions.assertNotNull(this.mUiMessageQueueThread)).isOnThread();
    }

    public void runOnUiQueueThread(Runnable runnable) {
        ((MessageQueueThread) Assertions.assertNotNull(this.mUiMessageQueueThread)).runOnQueue(runnable);
    }

    public void assertOnNativeModulesQueueThread() {
        ((MessageQueueThread) Assertions.assertNotNull(this.mNativeModulesMessageQueueThread)).assertIsOnThread();
    }

    public void assertOnNativeModulesQueueThread(String str) {
        ((MessageQueueThread) Assertions.assertNotNull(this.mNativeModulesMessageQueueThread)).assertIsOnThread(str);
    }

    public boolean isOnNativeModulesQueueThread() {
        return ((MessageQueueThread) Assertions.assertNotNull(this.mNativeModulesMessageQueueThread)).isOnThread();
    }

    public void runOnNativeModulesQueueThread(Runnable runnable) {
        ((MessageQueueThread) Assertions.assertNotNull(this.mNativeModulesMessageQueueThread)).runOnQueue(runnable);
    }

    public void assertOnJSQueueThread() {
        ((MessageQueueThread) Assertions.assertNotNull(this.mJSMessageQueueThread)).assertIsOnThread();
    }

    public boolean isOnJSQueueThread() {
        return ((MessageQueueThread) Assertions.assertNotNull(this.mJSMessageQueueThread)).isOnThread();
    }

    public void runOnJSQueueThread(Runnable runnable) {
        ((MessageQueueThread) Assertions.assertNotNull(this.mJSMessageQueueThread)).runOnQueue(runnable);
    }

    public void handleException(Exception exc) {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        boolean z = catalystInstance != null;
        boolean z2 = z && !catalystInstance.isDestroyed();
        NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler = this.mNativeModuleCallExceptionHandler;
        boolean z3 = nativeModuleCallExceptionHandler != null;
        if (z2 && z3) {
            nativeModuleCallExceptionHandler.handleException(exc);
        } else {
            FLog.e(ReactConstants.TAG, "Unable to handle Exception - catalystInstanceVariableExists: " + z + " - isCatalystInstanceAlive: " + (!z2) + " - hasExceptionHandler: " + z3, exc);
        }
    }

    /* loaded from: classes8.dex */
    public class ExceptionHandlerWrapper implements NativeModuleCallExceptionHandler {
        public ExceptionHandlerWrapper() {
        }

        @Override // com.facebook.react.bridge.NativeModuleCallExceptionHandler
        public void handleException(Exception exc) {
            ReactContext.this.handleException(exc);
        }
    }

    public NativeModuleCallExceptionHandler getExceptionHandler() {
        if (this.mExceptionHandlerWrapper == null) {
            this.mExceptionHandlerWrapper = new ExceptionHandlerWrapper();
        }
        return this.mExceptionHandlerWrapper;
    }

    public boolean hasCurrentActivity() {
        WeakReference<Activity> weakReference = this.mCurrentActivity;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    public boolean startActivityForResult(Intent intent, int i2, Bundle bundle) {
        Activity currentActivity = getCurrentActivity();
        Assertions.assertNotNull(currentActivity);
        currentActivity.startActivityForResult(intent, i2, bundle);
        return true;
    }

    public Activity getCurrentActivity() {
        WeakReference<Activity> weakReference = this.mCurrentActivity;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public JavaScriptContextHolder getJavaScriptContextHolder() {
        return this.mCatalystInstance.getJavaScriptContextHolder();
    }
}
