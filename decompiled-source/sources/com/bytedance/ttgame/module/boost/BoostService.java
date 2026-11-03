package com.bytedance.ttgame.module.boost;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.apm.constant.r;
import com.bytedance.common.jato.JatoXL;
import com.bytedance.common.jato.JatoXLConfig;
import com.bytedance.common.jato.c;
import com.bytedance.sdk.account.platform.base.i;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.IMainInternalService;
import com.bytedance.ttgame.main.internal.cache.ICacheService;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.module.boost.api.IBoostService;
import com.bytedance.ttgame.sdk.module.core.internal.RocketConstants;
import com.bytedance.ttgame.sdk.module.utils.ProcessUtils;
import com.meituan.robust.Constants;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;
import timber.log.Timber;
/* loaded from: classes4.dex */
public class BoostService implements IBoostService {
    private static final String BOOST_SWITCH_KEY = "gsdk_boost";
    private static final String SETTING_KEY_FUN_CLASS_VERIFY = "fun_class_verify";
    private static final String SETTING_KEY_FUN_SHRINK_VM = "fun_boost_shrink";
    private static final String SETTING_KEY_VALUE_EMULATOR_DISABLE = "value_emulator_disable";
    private static final String SETTING_KEY_VALUE_SHRINK_SIZE_POST_O = "value_shrink_size_post_o";
    private static final String TAG = "boost";
    private static final int TIME_DELAY_OPEN_CLASS_VERIFY = 15000;
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private volatile boolean shrinkVmOpen = true;
    private volatile boolean classVerifyOpen = true;
    private final AtomicBoolean classVerifyDisabled = new AtomicBoolean(false);
    private int shrinkSizePostO = 256;
    private String disableEmulatorType = "";

    @Override // com.bytedance.ttgame.module.boost.api.IBoostService
    public void shrinkVM(Context context) {
        this.moduleApiMonitor.onApiEnter("quality:impl:boost", "com.bytedance.ttgame.module.boost.api.IBoostService", "com.bytedance.ttgame.module.boost.BoostService", "shrinkVM", new String[]{"android.content.Context"}, Constants.VOID);
        Log.d(TAG, "shrinkVM");
        if (checkValid(context)) {
            doShrinkVm();
        }
    }

    @Override // com.bytedance.ttgame.module.boost.api.IBoostService
    public void disableClassVerify(Context context) {
        this.moduleApiMonitor.onApiEnter("quality:impl:boost", "com.bytedance.ttgame.module.boost.api.IBoostService", "com.bytedance.ttgame.module.boost.BoostService", "disableClassVerify", new String[]{"android.content.Context"}, Constants.VOID);
        Log.d(TAG, "disableClassVerify");
        String string = ((ICacheService) ModuleManager.INSTANCE.getService(ICacheService.class)).getString("gsdk_cache_repo", "is_emulator", i.a.ERROR_CODE_UNKNOW);
        if (i.a.ERROR_CODE_UNKNOW.equals(string)) {
            Log.w(TAG, "skip before emulator type return");
        } else if (TextUtils.isEmpty(this.disableEmulatorType)) {
            Log.w(TAG, "config empty, cannot judge and return");
        } else {
            if ("1".equals(string)) {
                String string2 = ((ICacheService) ModuleManager.INSTANCE.getService(ICacheService.class)).getString("gsdk_cache_repo", RocketConstants.EMULATOR_TYPE, "");
                Log.d(TAG, "emulator type " + string2);
                if (string2 == null || TextUtils.isEmpty(string2)) {
                    Log.w(TAG, "type empty and return");
                    return;
                } else if (!TextUtils.isEmpty(string2) && this.disableEmulatorType.contains(string2)) {
                    Log.w(TAG, "skip on " + string2);
                    return;
                }
            }
            final Context applicationContext = context.getApplicationContext();
            if (checkValid(context) && this.classVerifyOpen && this.classVerifyDisabled.compareAndSet(false, true)) {
                JatoXL.disableClassVerify();
                ((IMainInternalService) ModuleManager.INSTANCE.getService(IMainInternalService.class)).getScheduledExecutor().schedule(new Runnable() { // from class: com.bytedance.ttgame.module.boost.BoostService.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BoostService.this.enableClassVerify(applicationContext);
                    }
                }, r.APP_START_MAX_LIMIT_MS, TimeUnit.MILLISECONDS);
                Log.d(TAG, "class verify disabled");
                JatoXL.tryCpuBoost(10000L);
            }
        }
    }

    @Override // com.bytedance.ttgame.module.boost.api.IBoostService
    public void enableClassVerify(Context context) {
        this.moduleApiMonitor.onApiEnter("quality:impl:boost", "com.bytedance.ttgame.module.boost.api.IBoostService", "com.bytedance.ttgame.module.boost.BoostService", "enableClassVerify", new String[]{"android.content.Context"}, Constants.VOID);
        Log.d(TAG, "enableClassVerify");
        if (checkValid(context) && this.classVerifyOpen && this.classVerifyDisabled.compareAndSet(true, false)) {
            JatoXL.enableClassVerify();
            Log.d(TAG, "class verify enabled");
        }
    }

    private void doShrinkVm() {
        if (this.shrinkVmOpen) {
            JatoXL.shrinkVM(128, this.shrinkSizePostO);
            Log.d(TAG, "VM shrink started, post o size " + this.shrinkSizePostO);
            return;
        }
        Log.d(TAG, "can not start shrink because switch closed");
    }

    private boolean checkValid(Context context) {
        if (ProcessUtils.isInMainProcess(context)) {
            if (this.initialized.get()) {
                return true;
            }
            init(context);
            return true;
        }
        return false;
    }

    public void init(Context context) {
        if (this.initialized.compareAndSet(false, true)) {
            if (context == null) {
                Log.e(TAG, "context is null when init, abort");
                this.initialized.set(false);
                return;
            }
            try {
                JatoXL.init(new JatoXLConfig.a().a(context.getApplicationContext()).e(false).a(new c() { // from class: com.bytedance.ttgame.module.boost.BoostService.2
                    @Override // com.bytedance.common.jato.c
                    public void onDebugInfo(String str) {
                        Timber.tag(BoostService.TAG).d(str, new Object[0]);
                    }

                    @Override // com.bytedance.common.jato.c
                    public void onErrorInfo(String str, Throwable th) {
                        Timber.tag(BoostService.TAG).e(str + (th == null ? "" : th.getMessage()), new Object[0]);
                    }
                }).a(((IMainInternalService) ModuleManager.INSTANCE.getService(IMainInternalService.class)).getExecutor(0)).f(true).a());
                JatoXL.initBoostFramework(context.getApplicationContext());
                Log.d(TAG, "initialized!");
            } catch (Throwable th) {
                Log.e(TAG, "Something wrong when init, " + th.getMessage());
                this.initialized.set(false);
            }
            ICacheService iCacheService = (ICacheService) ModuleManager.INSTANCE.getService(ICacheService.class);
            if (iCacheService != null) {
                try {
                    JSONObject optJsonObject = iCacheService.optJsonObject(BOOST_SWITCH_KEY);
                    this.shrinkVmOpen = optJsonObject.optBoolean(SETTING_KEY_FUN_SHRINK_VM, true);
                    this.classVerifyOpen = optJsonObject.optBoolean(SETTING_KEY_FUN_CLASS_VERIFY, true);
                    this.shrinkSizePostO = optJsonObject.optInt(SETTING_KEY_VALUE_SHRINK_SIZE_POST_O, 2048);
                    this.disableEmulatorType = optJsonObject.optString(SETTING_KEY_VALUE_EMULATOR_DISABLE, "");
                } catch (Throwable th2) {
                    Log.e(TAG, "get boost settings failed" + th2);
                }
            }
        }
    }
}
