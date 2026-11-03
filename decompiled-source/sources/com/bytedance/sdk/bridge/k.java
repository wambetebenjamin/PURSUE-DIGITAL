package com.bytedance.sdk.bridge;

import com.bytedance.sdk.bridge.annotation.BridgeAnnotationHelper;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
/* compiled from: BridgeSDKInitHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0012\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007J*\u0010\f\u001a\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007J \u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000bH\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lcom/bytedance/sdk/bridge/BridgeSDKInitHelper;", "", "()V", "iBridgeIndexList", "", "Lcom/bytedance/sdk/bridge/IBridgeIndex;", "getIBridgeIndexList", "()Ljava/util/List;", "getSubscriberInfoFromModule", "", "bridgeName", "", "initBridgeIndex", "callBack", "Lcom/bytedance/sdk/bridge/BridgeSDKInitHelper$IBridgeIndexInitCallBack;", "moduleNames", "executor", "Ljava/util/concurrent/Executor;", "replaceStr", "str", "oldValue", "newValue", "IBridgeIndexInitCallBack", "bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class k {
    public static final k INSTANCE = new k();

    /* renamed from: a  reason: collision with root package name */
    private static final List<n> f13247a;

    /* compiled from: BridgeSDKInitHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006H'J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH'¨\u0006\n"}, d2 = {"Lcom/bytedance/sdk/bridge/BridgeSDKInitHelper$IBridgeIndexInitCallBack;", "", "onInitFailed", "", com.bytedance.frameworks.baselib.network.e.f7575a, "Ljava/lang/Exception;", "Lkotlin/Exception;", "onInitSuccess", "duration", "", "bridge_release"}, k = 1, mv = {1, 1, 15})
    /* loaded from: classes2.dex */
    public interface a {
        void onInitFailed(Exception exc);

        void onInitSuccess(long j2);
    }

    @Deprecated(message = "废弃了")
    public final void initBridgeIndex(a aVar) {
    }

    @Deprecated(message = "废弃了", replaceWith = @ReplaceWith(expression = "BridgeSDKInitHelper.initBridgeIndex(null, callBack)", imports = {}))
    public final void initBridgeIndex(List<String> moduleNames, a aVar, Executor executor) {
        Intrinsics.checkParameterIsNotNull(moduleNames, "moduleNames");
    }

    static {
        List<n> iBridgeIndices = c.getIBridgeIndices();
        Intrinsics.checkExpressionValueIsNotNull(iBridgeIndices, "BridgeIndexManager.getIBridgeIndices()");
        f13247a = iBridgeIndices;
    }

    private k() {
    }

    public final List<n> getIBridgeIndexList() {
        return f13247a;
    }

    public final void getSubscriberInfoFromModule(String bridgeName) {
        boolean z;
        Map.Entry entry;
        Object value;
        HashMap hashMap;
        Intrinsics.checkParameterIsNotNull(bridgeName, "bridgeName");
        HashMap hashMap2 = new HashMap();
        o.INSTANCE.d("inittask", " getSubscriberInfoFromModule bridgeName - " + bridgeName);
        for (n nVar : f13247a) {
            if (nVar != null) {
                nVar.getSubscriberInfoMap(hashMap2, bridgeName);
                if (!hashMap.isEmpty()) {
                    break;
                }
            }
        }
        Iterator it = hashMap2.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            try {
                Intrinsics.checkExpressionValueIsNotNull(((Map.Entry) it.next()).getValue(), "entry1.value");
            } catch (ClassCastException e2) {
                e2.printStackTrace();
            }
            if (!Intrinsics.areEqual((p) value, BridgeAnnotationHelper.getSubscriberInfoIndex().get(entry.getKey()))) {
                z = false;
                break;
            }
        }
        if (z) {
            return;
        }
        HashMap hashMap3 = hashMap2;
        if (!hashMap3.isEmpty()) {
            BridgeAnnotationHelper.setSubscriberInfoIndex(hashMap3);
        }
    }

    private final String a(String str, String str2, String str3) {
        Charset charset = Charsets.UTF_8;
        if (str != null) {
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            String replace = new String(bytes).replace(str2, str3);
            Intrinsics.checkExpressionValueIsNotNull(replace, "java.lang.String(str.toB…place(oldValue, newValue)");
            return replace;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
