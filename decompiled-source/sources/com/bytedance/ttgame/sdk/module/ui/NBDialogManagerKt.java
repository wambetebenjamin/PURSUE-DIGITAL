package com.bytedance.ttgame.sdk.module.ui;

import android.view.View;
import android.view.Window;
import com.bytedance.react.framework.RNConfig;
import java.util.EmptyStackException;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;
/* compiled from: NBDialogManager.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u001a\u0006\u0010\u0010\u001a\u00020\f\u001a\u0006\u0010\u0011\u001a\u00020\u0012\u001a\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006\u001a\u000e\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002\u001a\u0010\u0010\u0015\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u001a\u0010\u0010\u0018\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006\u001a\u000e\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u0006\u001a\n\u0010\u000e\u001a\u00020\f*\u00020\u0002\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"dialogStack", "Ljava/util/Stack;", "Lcom/bytedance/ttgame/sdk/module/ui/NBDialog;", "getDialogStack", "()Ljava/util/Stack;", RNConfig.ORIENTATION_KEY, "", "getScreenOrientation", "()Ljava/lang/String;", "setScreenOrientation", "(Ljava/lang/String;)V", "addDialog", "", "dialog", "singleTop", "", "clearAllDialog", "getAllDialogSize", "", "getScreenOrien", "removeDialog", "setFullScreen", "window", "Landroid/view/Window;", "setScreenOrien", "screenOrien", "singleTask", "tag", "android_gsdk_base_common_ui_i18nRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class NBDialogManagerKt {
    private static final Stack<NBDialog> dialogStack = new Stack<>();
    private static String screenOrientation;

    public static final Stack<NBDialog> getDialogStack() {
        return dialogStack;
    }

    public static final String getScreenOrientation() {
        return screenOrientation;
    }

    public static final void setScreenOrientation(String str) {
        screenOrientation = str;
    }

    public static /* synthetic */ void addDialog$default(NBDialog nBDialog, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        addDialog(nBDialog, z);
    }

    public static final void addDialog(NBDialog dialog, boolean z) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (!z) {
            dialogStack.push(dialog);
        } else if (!dialogStack.contains(dialog)) {
        } else {
            while (true) {
                try {
                    Stack<NBDialog> stack = dialogStack;
                    if (Intrinsics.areEqual(stack.peek(), dialog)) {
                        return;
                    }
                    stack.pop().dismiss();
                } catch (EmptyStackException e2) {
                    Timber.tag("NBDialogManager").e(e2);
                    return;
                }
            }
        }
    }

    public static final void singleTop(NBDialog nBDialog) {
        Intrinsics.checkNotNullParameter(nBDialog, "<this>");
        if (!dialogStack.contains(nBDialog)) {
            return;
        }
        while (true) {
            try {
                Stack<NBDialog> stack = dialogStack;
                if (Intrinsics.areEqual(stack.peek(), nBDialog)) {
                    return;
                }
                stack.pop().dismiss();
            } catch (EmptyStackException e2) {
                Timber.tag("NBDialogManager").e(e2);
                return;
            }
        }
    }

    public static final boolean singleTask(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        NBDialog nBDialog = null;
        boolean z = false;
        for (NBDialog nBDialog2 : dialogStack) {
            if (Intrinsics.areEqual(tag, nBDialog2.getTag())) {
                z = true;
                nBDialog = nBDialog2;
            }
        }
        if (nBDialog != null) {
            while (!Intrinsics.areEqual(getDialogStack().peek(), nBDialog)) {
                try {
                    getDialogStack().pop().dismiss();
                } catch (EmptyStackException e2) {
                    Timber.tag("NBDialogManager").e(e2);
                }
            }
        }
        return z;
    }

    public static final void removeDialog(NBDialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        try {
            Stack<NBDialog> stack = dialogStack;
            if (Intrinsics.areEqual(stack.peek(), dialog)) {
                stack.pop();
            }
        } catch (EmptyStackException e2) {
            Timber.tag("NBDialogManager").e(e2);
        }
    }

    public static final void clearAllDialog() {
        for (NBDialog nBDialog : dialogStack) {
            nBDialog.dismiss();
        }
        dialogStack.clear();
    }

    public static final int getAllDialogSize() {
        return dialogStack.size();
    }

    public static final void setScreenOrien(String str) {
        screenOrientation = str;
        Timber.tag("NBDialogManager").d(Intrinsics.stringPlus("screenOrientation: ", screenOrientation), new Object[0]);
    }

    public static final String getScreenOrien() {
        return screenOrientation;
    }

    public static final void setFullScreen(Window window) {
        if (window != null) {
            window.setFlags(1024, 1024);
        }
        View decorView = window == null ? null : window.getDecorView();
        if (decorView == null) {
            return;
        }
        decorView.setSystemUiVisibility(5894);
    }
}
