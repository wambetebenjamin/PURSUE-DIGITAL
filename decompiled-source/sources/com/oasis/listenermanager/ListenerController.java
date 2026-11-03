package com.oasis.listenermanager;

import com.oasis.Logger.Logger;
import com.oasis.listenermanager.listeners.BaseEventsListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes9.dex */
public final class ListenerController {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17539a = "com.oasis.listenermanager.ListenerController";

    /* renamed from: b  reason: collision with root package name */
    private static final Map<String, Set<BaseEventsListener>> f17540b = new ConcurrentHashMap();

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17541c = true;

    public static void clear() {
        Logger.d(f17539a, "clear all listener.");
        clear();
    }

    public static <T extends BaseEventsListener> void clear(Class<T> cls) {
        if (!BaseEventsListener.class.isAssignableFrom(cls)) {
            Logger.e(f17539a, "clear has an exception: class (ClassName: " + cls.getName() + ") is not a valid listener type!");
            return;
        }
        String name = cls.getName();
        f17540b.remove(name);
        Logger.d(f17539a, "clear listener: " + name);
    }

    public static <T extends BaseEventsListener> List<T> getListeners(Class<T> cls) {
        if (!BaseEventsListener.class.isAssignableFrom(cls)) {
            Logger.e(f17539a, "getListeners has an exception: class (ClassName: " + cls.getName() + ") is not a valid listener type!");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        String name = cls.getName();
        Map<String, Set<BaseEventsListener>> map = f17540b;
        if (map.containsKey(name)) {
            Set<BaseEventsListener> set = map.get(name);
            if (f17541c || set != null) {
                for (BaseEventsListener baseEventsListener : set) {
                    arrayList.add(baseEventsListener);
                }
                return arrayList;
            }
            throw new AssertionError();
        }
        return arrayList;
    }

    public static boolean register(BaseEventsListener baseEventsListener) {
        if (baseEventsListener == null) {
            Logger.e(f17539a, "unregister has an exception: you cannot register a null listener!");
            return false;
        }
        String listenerTypeName = baseEventsListener.getListenerTypeName();
        Map<String, Set<BaseEventsListener>> map = f17540b;
        if (map.containsKey(listenerTypeName)) {
            ((Set) Objects.requireNonNull(map.get(listenerTypeName))).add(baseEventsListener);
        } else {
            Set<BaseEventsListener> newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
            newSetFromMap.add(baseEventsListener);
            map.put(listenerTypeName, newSetFromMap);
        }
        Logger.d(f17539a, "register listener: " + listenerTypeName);
        return true;
    }

    public static boolean unregister(BaseEventsListener baseEventsListener) {
        if (baseEventsListener == null) {
            Logger.e(f17539a, "unregister has an exception: you cannot unregister a null listener!");
            return false;
        }
        String listenerTypeName = baseEventsListener.getListenerTypeName();
        Map<String, Set<BaseEventsListener>> map = f17540b;
        if (map.containsKey(listenerTypeName)) {
            Set<BaseEventsListener> set = map.get(listenerTypeName);
            if (f17541c || set != null) {
                set.remove(baseEventsListener);
                if (set.isEmpty()) {
                    map.remove(listenerTypeName);
                }
                Logger.d(f17539a, "unregister listener: " + listenerTypeName);
                return true;
            }
            throw new AssertionError();
        }
        return false;
    }
}
