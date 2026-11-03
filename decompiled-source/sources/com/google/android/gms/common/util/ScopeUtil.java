package com.google.android.gms.common.util;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class ScopeUtil {
    private ScopeUtil() {
    }

    public static String[] toScopeString(Set<Scope> set) {
        Preconditions.checkNotNull(set, "scopes can't be null.");
        Scope[] scopeArr = (Scope[]) set.toArray(new Scope[set.size()]);
        Preconditions.checkNotNull(scopeArr, "scopes can't be null.");
        String[] strArr = new String[scopeArr.length];
        for (int i2 = 0; i2 < scopeArr.length; i2++) {
            strArr[i2] = scopeArr[i2].getScopeUri();
        }
        return strArr;
    }
}
