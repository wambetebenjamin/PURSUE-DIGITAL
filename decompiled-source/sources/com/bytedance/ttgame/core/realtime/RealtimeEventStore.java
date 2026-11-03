package com.bytedance.ttgame.core.realtime;

import com.bytedance.ttgame.library.luffy.db.d;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class RealtimeEventStore {
    private final d<PendingRealtimeEvent> database = new d<>(PendingRealtimeEvent.class);

    public void save(PendingRealtimeEvent pendingRealtimeEvent) {
        if (pendingRealtimeEvent != null) {
            this.database.a((d<PendingRealtimeEvent>) pendingRealtimeEvent);
        }
    }

    public List<PendingRealtimeEvent> loadAll() {
        List<PendingRealtimeEvent> c2 = this.database.b().a("createdAt", false).c();
        return c2 != null ? c2 : new ArrayList();
    }

    public List<PendingRealtimeEvent> loadValid(long j2, int i2) {
        List<PendingRealtimeEvent> c2 = this.database.b().a("createdAt", false).c();
        ArrayList arrayList = new ArrayList();
        if (c2 != null && !c2.isEmpty()) {
            for (PendingRealtimeEvent pendingRealtimeEvent : c2) {
                if (pendingRealtimeEvent != null) {
                    if (pendingRealtimeEvent.expireAt <= j2) {
                        this.database.b(pendingRealtimeEvent);
                    } else {
                        arrayList.add(pendingRealtimeEvent);
                        if (arrayList.size() >= i2) {
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public void delete(List<PendingRealtimeEvent> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (PendingRealtimeEvent pendingRealtimeEvent : list) {
            if (pendingRealtimeEvent != null) {
                this.database.b(pendingRealtimeEvent);
            }
        }
    }

    public void clearExpired(long j2) {
        List<PendingRealtimeEvent> c2 = this.database.b().c();
        if (c2 == null || c2.isEmpty()) {
            return;
        }
        for (PendingRealtimeEvent pendingRealtimeEvent : c2) {
            if (pendingRealtimeEvent != null && pendingRealtimeEvent.expireAt <= j2) {
                this.database.b(pendingRealtimeEvent);
            }
        }
    }
}
