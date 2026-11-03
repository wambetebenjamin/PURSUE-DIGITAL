package com.bytedance.ttgame.module.rn;

import android.text.TextUtils;
import com.bytedance.ttgame.sdk.module.bridge.ICommonBridgeInterface;
import com.bytedance.ttgame.sdk.module.bridge.ICommonBridgeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes6.dex */
public class BridgeRegister {
    private static BridgeRegister mBridgeRegister;
    private HashMap<String, ICommonBridgeInterface> mHashmap = new HashMap<>();
    private List<ICommonBridgeListener> mClassMap = Collections.synchronizedList(new ArrayList());

    private BridgeRegister() {
    }

    public static BridgeRegister getBridgeRegister() {
        if (mBridgeRegister == null) {
            mBridgeRegister = new BridgeRegister();
        }
        return mBridgeRegister;
    }

    public void register(ICommonBridgeInterface iCommonBridgeInterface) {
        if (iCommonBridgeInterface == null || TextUtils.isEmpty(iCommonBridgeInterface.getName())) {
            return;
        }
        this.mHashmap.put(iCommonBridgeInterface.getName(), iCommonBridgeInterface);
    }

    public void addRegisterListener(ICommonBridgeListener iCommonBridgeListener) {
        if (iCommonBridgeListener != null) {
            this.mClassMap.add(iCommonBridgeListener);
        }
    }

    public HashMap<String, ICommonBridgeInterface> getAllBridge() {
        return this.mHashmap;
    }

    public List<ICommonBridgeListener> getAllClassBridge() {
        return this.mClassMap;
    }
}
