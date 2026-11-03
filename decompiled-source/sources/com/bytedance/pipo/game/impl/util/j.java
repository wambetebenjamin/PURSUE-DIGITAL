package com.bytedance.pipo.game.impl.util;

import java.lang.reflect.Field;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: Serializer.java */
/* loaded from: classes2.dex */
public class j {
    public static <T> T a(String str, Class<T> cls) {
        Field[] fields;
        try {
            JSONObject jSONObject = new JSONObject(str);
            T newInstance = cls.newInstance();
            for (Field field : cls.getFields()) {
                com.bytedance.pipo.game.impl.net.entity.a aVar = (com.bytedance.pipo.game.impl.net.entity.a) field.getAnnotation(com.bytedance.pipo.game.impl.net.entity.a.class);
                if (aVar != null) {
                    Object opt = jSONObject.opt(aVar.a());
                    if (opt instanceof JSONObject) {
                        field.set(newInstance, a(opt.toString(), field.getType()));
                    } else if (!(opt instanceof JSONArray)) {
                        field.set(newInstance, opt);
                    }
                }
            }
            return newInstance;
        } catch (IllegalAccessException e2) {
            d.c(com.bytedance.pipo.game.impl.f.f10331a, "Serializer: illegalAccessException error" + e2.getLocalizedMessage());
            return null;
        } catch (InstantiationException e3) {
            d.c(com.bytedance.pipo.game.impl.f.f10331a, "Serializer: instantiationException error" + e3.getLocalizedMessage());
            return null;
        } catch (JSONException e4) {
            d.c(com.bytedance.pipo.game.impl.f.f10331a, "Serializer: string parse json error" + e4.getLocalizedMessage());
            return null;
        }
    }
}
