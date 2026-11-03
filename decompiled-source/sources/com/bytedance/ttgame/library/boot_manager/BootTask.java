package com.bytedance.ttgame.library.boot_manager;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.react.framework.RNConfig;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BootTask.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u000f\b'\u0018\u0000 #2\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\u00122\b\b\u0001\u0010\u0013\u001a\u00020\u0010J\u0010\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0015H\u0014J\u0006\u0010\u0016\u001a\u00020\u0012J\b\u0010\u0017\u001a\u00020\u0012H&J\b\u0010\u0018\u001a\u00020\u0012H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0017J\b\u0010\u001a\u001a\u00020\u0006H\u0014J\u001a\u0010\u001b\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0017\u001a\u00020\u0006H\u0016J\u0012\u0010\u001d\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0015H\u0014J\u0010\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0015H\u0016J\b\u0010 \u001a\u00020\fH\u0016J\b\u0010!\u001a\u00020\u0010H\u0014J\u0006\u0010\"\u001a\u00020\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/bytedance/ttgame/library/boot_manager/BootTask;", "", "context", "Lcom/bytedance/ttgame/library/boot_manager/SubModuleContext;", "(Lcom/bytedance/ttgame/library/boot_manager/SubModuleContext;)V", "initialized", "", "initializing", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isFinished", "()Z", "name", "", "getName", "()Ljava/lang/String;", "triggerOnAllFlag", "", "clearFlagAndTryStart", "", "type", "dependOns", "", "doInit", "init", "release", "runOn", "runOnAllProcess", "saveInitStaus", RNConfig.MODULE_NAME, "shouldInit", "subProcessOns", "subTasks", "toString", "triggerOn", "waitInit", "Companion", "boot_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BootTask {
    public static final Companion Companion = new Companion(null);
    public static SubModuleContext moduleContext;
    private boolean initialized;
    private final AtomicBoolean initializing;
    private int triggerOnAllFlag;

    /* JADX INFO: Access modifiers changed from: protected */
    public List<String> dependOns() {
        return null;
    }

    public abstract void init();

    public void release() {
    }

    @RunType
    public int runOn() {
        return 0;
    }

    protected boolean runOnAllProcess() {
        return false;
    }

    protected List<String> subProcessOns() {
        return null;
    }

    public List<BootTask> subTasks() {
        return null;
    }

    protected int triggerOn() {
        return 1;
    }

    public BootTask(SubModuleContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.initializing = new AtomicBoolean(false);
        moduleContext = context;
        this.triggerOnAllFlag = triggerOn();
    }

    /* compiled from: BootTask.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/bytedance/ttgame/library/boot_manager/BootTask$Companion;", "", "()V", "moduleContext", "Lcom/bytedance/ttgame/library/boot_manager/SubModuleContext;", "boot_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final boolean isFinished() {
        return this.initialized;
    }

    public final void clearFlagAndTryStart(@TriggerType int i2) {
        synchronized (this) {
            this.triggerOnAllFlag = (~i2) & this.triggerOnAllFlag;
            Unit unit = Unit.INSTANCE;
        }
        if (this.triggerOnAllFlag == 0) {
            doInit();
        }
    }

    public final void doInit() {
        boolean z = false;
        if (this.initializing.compareAndSet(false, true)) {
            if (BootManager.getInstance().isSubProcessLimitFlag() && !runOnAllProcess()) {
                List<String> subProcessOns = subProcessOns();
                if (subProcessOns != null && subProcessOns.contains(BootManager.getInstance().getSubProcessName())) {
                    z = true;
                }
                if (!z) {
                    return;
                }
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            init();
            this.initialized = true;
            if (BootManager.getInstance().getBootTaskMonitor() != null) {
                BootManager.getInstance().getBootTaskMonitor().finishInit(getName(), SystemClock.uptimeMillis() - uptimeMillis, runOn(), triggerOn());
            }
        }
    }

    public boolean shouldInit(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return BootManager.getInstance().shouldInit(str);
    }

    public void saveInitStaus(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        BootManager.getInstance().saveModuleInitStatus(str, z);
    }

    protected String getName() {
        String name = getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "javaClass.name");
        return name;
    }

    public final void waitInit() {
        try {
            if (this.initialized) {
                return;
            }
            synchronized (this) {
                if (!this.initialized) {
                    Log.e(Constant.TAG, "所依赖初始化任务未执行，强制执行" + getClass().getCanonicalName());
                    doInit();
                }
                Unit unit = Unit.INSTANCE;
            }
        } catch (Throwable unused) {
            Log.e(Constant.TAG, "强制执行出错，再试一次" + getClass().getCanonicalName());
            doInit();
        }
    }

    public String toString() {
        return "BootTask{name=" + getName() + '}';
    }
}
