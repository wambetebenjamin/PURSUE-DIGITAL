package com.bytedance.react.framework.utils.notchutil;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.Window;
import com.bytedance.react.framework.utils.notchutil.INotchScreen;
import com.bytedance.react.framework.utils.notchutil.impl.AndroidPNotchScreen;
import com.bytedance.react.framework.utils.notchutil.impl.HuaweiNotchScreen;
import com.bytedance.react.framework.utils.notchutil.impl.MiNotchScreen;
import com.bytedance.react.framework.utils.notchutil.impl.OppoNotchScreen;
import com.bytedance.react.framework.utils.notchutil.utils.RomUtils;
import java.util.List;
/* loaded from: classes2.dex */
public class NotchScreenManager {
    private static final NotchScreenManager instance = new NotchScreenManager();
    private final INotchScreen notchScreen = getNotchScreen();

    private NotchScreenManager() {
    }

    public static NotchScreenManager getInstance() {
        return instance;
    }

    public void setDisplayInNotch(Window window) {
        INotchScreen iNotchScreen = this.notchScreen;
        if (iNotchScreen != null) {
            iNotchScreen.setDisplayInNotch(window);
        }
    }

    public INotchScreen.NotchScreenInfo getNotchInfo(Context context, Window window) {
        List<Rect> notchRect;
        INotchScreen.NotchScreenInfo notchScreenInfo = new INotchScreen.NotchScreenInfo();
        INotchScreen iNotchScreen = this.notchScreen;
        if (iNotchScreen != null && iNotchScreen.hasNotch(context) && (notchRect = this.notchScreen.getNotchRect(context, window)) != null && notchRect.size() > 0) {
            notchScreenInfo.hasNotch = true;
            notchScreenInfo.notchRects = notchRect;
            if (window != null) {
                notchScreenInfo.hasShowInNotch = this.notchScreen.isShowInNotch(window);
            } else {
                notchScreenInfo.hasShowInNotch = true;
            }
        }
        return notchScreenInfo;
    }

    private INotchScreen getNotchScreen() {
        if (Build.VERSION.SDK_INT >= 28) {
            return new AndroidPNotchScreen();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            if (RomUtils.isHuawei()) {
                return new HuaweiNotchScreen();
            }
            if (RomUtils.isOppo()) {
                return new OppoNotchScreen();
            }
            if (RomUtils.isVivo()) {
                return new HuaweiNotchScreen();
            }
            if (RomUtils.isXiaomi()) {
                return new MiNotchScreen();
            }
        }
        return null;
    }
}
