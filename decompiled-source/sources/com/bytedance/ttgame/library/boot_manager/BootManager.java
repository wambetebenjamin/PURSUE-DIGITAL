package com.bytedance.ttgame.library.boot_manager;

import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.ttgame.library.module_manager.Component;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class BootManager {
    private static final String TAG = "BootManager";
    private static volatile BootManager sInstance;
    private Map<String, HashMap<String, Object>> configMap;
    private JSONArray jsonConfig;
    private BootConfig config = null;
    private final Map<Class<?>, BootTask> bootTasks = new ConcurrentHashMap();
    private final ArrayList<BootTask> sortedTasks = new ArrayList<>();
    private final AtomicBoolean sdkInitTrigger = new AtomicBoolean(false);
    private final AtomicBoolean afterMainTrigger = new AtomicBoolean(false);
    private final AtomicBoolean afterDidGetTrigger = new AtomicBoolean(false);
    private final AtomicBoolean attachBaseContextTrigger = new AtomicBoolean(false);
    private final AtomicBoolean afterDidAndIidGetTrigger = new AtomicBoolean(false);
    private final AtomicBoolean afterModuleInitTrigger = new AtomicBoolean(false);
    private SubModuleContext moduleContext = null;
    private boolean subProcessLimitFlag = false;
    private String subProcessName = "";
    private boolean initConfigOn = false;
    private Map<String, Boolean> initStatus = new ConcurrentHashMap();

    public static BootManager getInstance() {
        if (sInstance == null) {
            synchronized (BootManager.class) {
                if (sInstance == null) {
                    sInstance = new BootManager();
                }
            }
        }
        return sInstance;
    }

    private BootManager() {
    }

    public void loadBootTasks() {
        long uptimeMillis = SystemClock.uptimeMillis();
        List<Component> allComponents = ModuleManager.INSTANCE.getAllComponents();
        if (allComponents.size() == 0) {
            Log.e("BootManager", "can not find any modules, please check !!");
        } else {
            try {
                if (this.initConfigOn && this.jsonConfig != null) {
                    this.configMap = new ConcurrentHashMap();
                    for (int i2 = 0; i2 < this.jsonConfig.length(); i2++) {
                        JSONObject jSONObject = this.jsonConfig.getJSONObject(i2);
                        boolean optBoolean = jSONObject.optBoolean("initflag", true);
                        String optString = jSONObject.optString(RNConfig.MODULE_NAME, "");
                        JSONObject optJSONObject = jSONObject.optJSONObject("otherflags");
                        if (!TextUtils.isEmpty(optString)) {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put(RNConfig.MODULE_NAME, optString);
                            hashMap.put("initflag", Boolean.valueOf(optBoolean));
                            hashMap.put("otherflags", optJSONObject);
                            this.configMap.put(optString, hashMap);
                        }
                    }
                }
            } catch (Exception unused) {
                Log.e("BootManager", "config parse error, please check !!");
            }
            for (Component component : allComponents) {
                try {
                    fillTasks(Class.forName("com.bytedance.ttgame.module." + component.getName() + ".Task"));
                } catch (Throwable unused2) {
                    Log.v("BootManager", "load boot task failed " + component.getName());
                    if (component.getName().equals("adattribution")) {
                        loadBootTasksFormImpls(component);
                    }
                }
            }
        }
        sort();
        Log.d("BootManager", "loadBootTasks finished and cost " + (SystemClock.uptimeMillis() - uptimeMillis));
    }

    private void loadBootTasksFormImpls(Component component) {
        for (String str : component.getImpls().keySet()) {
            try {
                fillTasks(Class.forName("com.bytedance.ttgame.module." + str + ".Task"));
            } catch (Throwable unused) {
                Log.v("BootManager", "load boot task from impls failed " + str);
            }
        }
    }

    public void config(BootConfig bootConfig, boolean z, JSONArray jSONArray) {
        this.config = bootConfig;
        this.initConfigOn = z;
        this.jsonConfig = jSONArray;
        this.moduleContext = new SubModuleContext(bootConfig.getContext(), bootConfig.getSdkConfig());
    }

    public HashMap<String, Object> getMoudleConfig(String str) {
        Map<String, HashMap<String, Object>> map;
        if (!this.initConfigOn || (map = this.configMap) == null) {
            return null;
        }
        return map.get(str);
    }

    public void saveModuleInitStatus(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.initStatus.put(str, Boolean.valueOf(z));
    }

    public boolean shouldInit(String str) {
        HashMap<String, Object> moudleConfig;
        if (TextUtils.isEmpty(str) || (moudleConfig = getMoudleConfig(str)) == null || !moudleConfig.containsKey("initflag")) {
            return true;
        }
        return ((Boolean) moudleConfig.get("initflag")).booleanValue();
    }

    public boolean isFeatureOn(String str, String str2) {
        JSONObject jSONObject;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            HashMap<String, Object> moudleConfig = getMoudleConfig(str);
            if (moudleConfig.containsKey("otherflags") && (jSONObject = (JSONObject) moudleConfig.get("otherflags")) != null) {
                return jSONObject.optBoolean(str2, true);
            }
        }
        return true;
    }

    public boolean isModuleInit(String str) {
        if (!TextUtils.isEmpty(str) && this.initStatus.containsKey(str)) {
            return this.initStatus.get(str).booleanValue();
        }
        return true;
    }

    public void addTask(Class<?> cls) {
        try {
            fillTasks(cls);
        } catch (Throwable unused) {
            Log.e("BootManager", "add boot task failed " + cls.getName());
        }
    }

    private BootConfig getConfig() {
        BootConfig bootConfig = this.config;
        if (bootConfig != null) {
            return bootConfig;
        }
        throw new IllegalArgumentException("must set config before call getInstance");
    }

    void addTask(Class<?> cls, BootTask bootTask) {
        try {
            this.bootTasks.put(cls, bootTask);
        } catch (Throwable th) {
            Log.e("BootManager", "add task failed," + th.toString());
        }
    }

    public BootTask getTask(Class<?> cls) {
        return this.bootTasks.get(cls);
    }

    private void fillTasks(Class<?> cls) {
        BootTask bootTask = (BootTask) cls.getConstructor(SubModuleContext.class).newInstance(this.moduleContext);
        addTask(cls, bootTask);
        if (bootTask.subTasks() != null) {
            for (BootTask bootTask2 : (List) Objects.requireNonNull(bootTask.subTasks())) {
                addTask(bootTask2.getClass(), bootTask2);
            }
        }
    }

    private void sort() {
        if (this.bootTasks.size() > 0) {
            try {
                DirectedGraph directedGraph = new DirectedGraph();
                directedGraph.buildGraph(this.bootTasks);
                this.sortedTasks.addAll(directedGraph.topologicalSort());
            } catch (Throwable th) {
                Log.e("BootManager", "sorted tasks failed, " + th.toString());
            }
        }
    }

    public void onTrigger(@TriggerType int i2) {
        Log.d("BootManager", "onTrigger " + i2);
        if (isDowngrade() && i2 == 1) {
            Log.e("BootManager", "enter downgrade mode and init all tasks");
            if (this.sortedTasks.size() == this.bootTasks.size()) {
                Iterator<BootTask> it = this.sortedTasks.iterator();
                while (it.hasNext()) {
                    it.next().doInit();
                }
                return;
            }
            for (BootTask bootTask : this.bootTasks.values()) {
                bootTask.doInit();
            }
        } else if (i2 == 4 && this.afterMainTrigger.compareAndSet(false, true)) {
            tryTriggerMainActivityTasks();
        } else if (i2 == 1 && this.sdkInitTrigger.compareAndSet(false, true)) {
            runTask(i2);
        } else if (i2 == 2 && this.afterDidGetTrigger.compareAndSet(false, true)) {
            runTask(i2);
            tryTriggerMainActivityTasks();
        } else if (i2 == 8 && this.attachBaseContextTrigger.compareAndSet(false, true)) {
            runTask(i2);
        } else if (i2 == 16 && this.afterDidAndIidGetTrigger.compareAndSet(false, true)) {
            runTask(i2);
        } else if (i2 == 32 && this.afterModuleInitTrigger.compareAndSet(false, true)) {
            runTask(i2);
        }
    }

    public void onRelease() {
        Log.e("BootManager", "onRelease");
        if (this.sortedTasks.size() == this.bootTasks.size()) {
            Iterator<BootTask> it = this.sortedTasks.iterator();
            while (it.hasNext()) {
                it.next().release();
            }
            return;
        }
        for (BootTask bootTask : this.bootTasks.values()) {
            bootTask.release();
        }
    }

    private boolean isDowngrade() {
        BootConfig bootConfig = this.config;
        if (bootConfig == null) {
            return false;
        }
        return bootConfig.isDowngrade();
    }

    private void runTask(final int i2) {
        boolean z = Thread.currentThread() == Looper.getMainLooper().getThread();
        Iterator<BootTask> it = this.sortedTasks.iterator();
        while (it.hasNext()) {
            final BootTask next = it.next();
            int runOn = next.runOn();
            if (runOn != 0) {
                if (runOn == 1) {
                    getConfig().getSerialExecutor().execute(new Runnable() { // from class: com.bytedance.ttgame.library.boot_manager.BootManager.2
                        @Override // java.lang.Runnable
                        public void run() {
                            next.clearFlagAndTryStart(i2);
                        }
                    });
                } else if (runOn == 3) {
                    getConfig().getIdleExecutor().execute(new Runnable() { // from class: com.bytedance.ttgame.library.boot_manager.BootManager.3
                        @Override // java.lang.Runnable
                        public void run() {
                            next.clearFlagAndTryStart(i2);
                        }
                    });
                }
            } else if (z) {
                next.clearFlagAndTryStart(i2);
            } else {
                getConfig().getMainExecutor().execute(new Runnable() { // from class: com.bytedance.ttgame.library.boot_manager.BootManager.1
                    @Override // java.lang.Runnable
                    public void run() {
                        next.clearFlagAndTryStart(i2);
                    }
                });
            }
        }
    }

    private void tryTriggerMainActivityTasks() {
        if (this.afterDidGetTrigger.get() && this.afterMainTrigger.get()) {
            runTask(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IBootTaskMonitor getBootTaskMonitor() {
        BootConfig bootConfig = this.config;
        if (bootConfig != null) {
            return bootConfig.getTaskMonitor();
        }
        return null;
    }

    public void waitTasks(BootTaskType bootTaskType) {
        try {
            Class<? extends BootTask>[] value = bootTaskType.value();
            if (value.length > 0) {
                for (Class<? extends BootTask> cls : value) {
                    getInstance().getTask(cls).waitInit();
                }
            }
        } catch (Throwable th) {
            Log.e("BootManager", "服务方法调用时，确保初始化任务完成出错 " + th.toString());
        }
    }

    public void setLimitSubProcessName(String str) {
        this.subProcessName = str;
        this.subProcessLimitFlag = true;
    }

    public boolean isSubProcessLimitFlag() {
        return this.subProcessLimitFlag;
    }

    public String getSubProcessName() {
        return this.subProcessName;
    }
}
