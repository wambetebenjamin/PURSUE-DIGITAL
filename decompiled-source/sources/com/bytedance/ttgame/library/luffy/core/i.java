package com.bytedance.ttgame.library.luffy.core;

import com.facebook.internal.ServerProtocol;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
/* compiled from: JsonHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0017\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\tJ\u0017\u0010\n\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u000bJ\u0017\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\b\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u000eJ\u0017\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\b\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\b\u001a\u0004\u0018\u00010\u0001J\"\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00012\b\u0010\u0017\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0018\u001a\u00020\u0013J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0018\u001a\u00020\u0013¨\u0006\u0019"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/core/JsonHelper;", "", "()V", "checkDouble", "", "d", "toBoolean", "", "value", "(Ljava/lang/Object;)Ljava/lang/Boolean;", "toDouble", "(Ljava/lang/Object;)Ljava/lang/Double;", "toInteger", "", "(Ljava/lang/Object;)Ljava/lang/Integer;", "toLong", "", "(Ljava/lang/Object;)Ljava/lang/Long;", "toString", "", "typeMismatch", "Lorg/json/JSONException;", "indexOrName", "actual", "requiredType", "luffy_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class i {
    public static final i INSTANCE = new i();

    private i() {
    }

    public final double checkDouble(double d2) {
        if (Double.isInfinite(d2) || Double.isNaN(d2)) {
            throw new JSONException("Forbidden numeric value: " + d2);
        }
        return d2;
    }

    public final Boolean toBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (StringsKt.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, str, true)) {
                return true;
            }
            return StringsKt.equals("false", str, true) ? false : null;
        }
        return null;
    }

    public final Double toDouble(Object obj) {
        if (obj instanceof Double) {
            return (Double) obj;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return null;
    }

    public final Integer toInteger(Object obj) {
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            try {
                return Integer.valueOf((int) Double.parseDouble((String) obj));
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return null;
    }

    public final Long toLong(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof Number) {
            return Long.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            try {
                return Long.valueOf((long) Double.parseDouble((String) obj));
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return null;
    }

    public final String toString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    public final JSONException typeMismatch(Object indexOrName, Object obj, String requiredType) {
        Intrinsics.checkNotNullParameter(indexOrName, "indexOrName");
        Intrinsics.checkNotNullParameter(requiredType, "requiredType");
        if (obj == null) {
            throw new JSONException("Value at " + indexOrName + " is null.");
        }
        throw new JSONException("Value " + obj + " at " + indexOrName + " of type " + obj.getClass().getName() + " cannot be converted to " + requiredType);
    }

    public final JSONException typeMismatch(Object obj, String requiredType) {
        Intrinsics.checkNotNullParameter(requiredType, "requiredType");
        if (obj == null) {
            throw new JSONException("Value is null.");
        }
        throw new JSONException("Value " + obj + " of type " + obj.getClass().getName() + " cannot be converted to " + requiredType);
    }
}
