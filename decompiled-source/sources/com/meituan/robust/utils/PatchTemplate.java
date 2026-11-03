package com.meituan.robust.utils;

import com.meituan.robust.ChangeQuickRedirect;
import java.util.Map;
import java.util.WeakHashMap;
/* loaded from: classes9.dex */
public class PatchTemplate implements ChangeQuickRedirect {
    public static final String MATCH_ALL_PARAMETER = "(\\w*\\.)*\\w*";
    private static final Map<Object, Object> keyToValueRelation = new WeakHashMap();

    @Override // com.meituan.robust.ChangeQuickRedirect
    public Object accessDispatch(String str, Object[] objArr) {
        return null;
    }

    @Override // com.meituan.robust.ChangeQuickRedirect
    public boolean isSupport(String str, Object[] objArr) {
        return true;
    }

    private static Object fixObj(Object obj) {
        if (obj instanceof Byte) {
            return new Boolean(((Byte) obj).byteValue() != 0);
        }
        return obj;
    }
}
