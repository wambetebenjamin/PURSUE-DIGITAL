package com.bytedance.pipo.game.impl;

import com.bytedance.pipo.game.api.p;
import com.bytedance.pipo.game.api.s;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* compiled from: GoogleObserverImpl.java */
/* loaded from: classes2.dex */
public class c implements com.bytedance.pipo.game.api.a {

    /* renamed from: a  reason: collision with root package name */
    private com.bytedance.pipo.game.api.a f10290a;

    /* renamed from: b  reason: collision with root package name */
    private Set<com.bytedance.pipo.game.api.a> f10291b;

    public c(com.bytedance.pipo.game.api.a aVar) {
        this.f10290a = aVar;
    }

    public com.bytedance.pipo.game.api.a a() {
        return this.f10290a;
    }

    public void a(com.bytedance.pipo.game.api.a aVar) {
        if (aVar == this.f10290a) {
            return;
        }
        if (this.f10291b == null) {
            HashSet hashSet = new HashSet();
            this.f10291b = hashSet;
            hashSet.add(this.f10290a);
        }
        this.f10291b.add(aVar);
    }

    public void b(com.bytedance.pipo.game.api.a aVar) {
        Set<com.bytedance.pipo.game.api.a> set = this.f10291b;
        if (set != null) {
            set.remove(aVar);
            if (this.f10291b.size() == 0) {
                this.f10291b = null;
            }
        }
        if (aVar == this.f10290a) {
            this.f10290a = null;
        }
    }

    public void b() {
        Set<com.bytedance.pipo.game.api.a> set = this.f10291b;
        if (set != null) {
            set.clear();
        }
    }

    @Override // com.bytedance.pipo.game.api.a
    public void a(com.bytedance.pipo.game.api.b bVar) {
        Set<com.bytedance.pipo.game.api.a> set = this.f10291b;
        if (set != null) {
            for (com.bytedance.pipo.game.api.a aVar : set) {
                aVar.a(bVar);
            }
            return;
        }
        com.bytedance.pipo.game.api.a aVar2 = this.f10290a;
        if (aVar2 != null) {
            aVar2.a(bVar);
        }
    }

    @Override // com.bytedance.pipo.game.api.a
    public void a(com.bytedance.pipo.game.api.b bVar, com.bytedance.pipo.game.api.d dVar) {
        Set<com.bytedance.pipo.game.api.a> set = this.f10291b;
        if (set != null) {
            for (com.bytedance.pipo.game.api.a aVar : set) {
                aVar.a(bVar, dVar);
            }
            return;
        }
        com.bytedance.pipo.game.api.a aVar2 = this.f10290a;
        if (aVar2 != null) {
            aVar2.a(bVar, dVar);
        }
    }

    @Override // com.bytedance.pipo.game.api.a
    public void a(com.bytedance.pipo.game.api.d dVar) {
        Set<com.bytedance.pipo.game.api.a> set = this.f10291b;
        if (set != null) {
            for (com.bytedance.pipo.game.api.a aVar : set) {
                aVar.a(dVar);
            }
            return;
        }
        com.bytedance.pipo.game.api.a aVar2 = this.f10290a;
        if (aVar2 != null) {
            aVar2.a(dVar);
        }
    }

    @Override // com.bytedance.pipo.game.api.a
    public void a(com.bytedance.pipo.game.api.b bVar, List<p> list) {
        Set<com.bytedance.pipo.game.api.a> set = this.f10291b;
        if (set != null) {
            for (com.bytedance.pipo.game.api.a aVar : set) {
                aVar.a(bVar, list);
            }
            return;
        }
        com.bytedance.pipo.game.api.a aVar2 = this.f10290a;
        if (aVar2 != null) {
            aVar2.a(bVar, list);
        }
    }

    @Override // com.bytedance.pipo.game.api.a
    public void b(com.bytedance.pipo.game.api.b bVar, List<s> list) {
        Set<com.bytedance.pipo.game.api.a> set = this.f10291b;
        if (set != null) {
            for (com.bytedance.pipo.game.api.a aVar : set) {
                aVar.b(bVar, list);
            }
            return;
        }
        com.bytedance.pipo.game.api.a aVar2 = this.f10290a;
        if (aVar2 != null) {
            aVar2.b(bVar, list);
        }
    }

    @Override // com.bytedance.pipo.game.api.a
    public void b(com.bytedance.pipo.game.api.b bVar, com.bytedance.pipo.game.api.d dVar) {
        Set<com.bytedance.pipo.game.api.a> set = this.f10291b;
        if (set != null) {
            for (com.bytedance.pipo.game.api.a aVar : set) {
                aVar.b(bVar, dVar);
            }
            return;
        }
        com.bytedance.pipo.game.api.a aVar2 = this.f10290a;
        if (aVar2 != null) {
            aVar2.b(bVar, dVar);
        }
    }

    @Override // com.bytedance.pipo.game.api.a
    public void a(com.bytedance.pipo.game.api.b bVar, boolean z, List<p> list) {
        Set<com.bytedance.pipo.game.api.a> set = this.f10291b;
        if (set != null) {
            for (com.bytedance.pipo.game.api.a aVar : set) {
                aVar.a(bVar, z, list);
            }
            return;
        }
        com.bytedance.pipo.game.api.a aVar2 = this.f10290a;
        if (aVar2 != null) {
            aVar2.a(bVar, z, list);
        }
    }

    @Override // com.bytedance.pipo.game.api.a
    public void c(com.bytedance.pipo.game.api.b bVar, com.bytedance.pipo.game.api.d dVar) {
        Set<com.bytedance.pipo.game.api.a> set = this.f10291b;
        if (set != null) {
            for (com.bytedance.pipo.game.api.a aVar : set) {
                aVar.c(bVar, dVar);
            }
            return;
        }
        com.bytedance.pipo.game.api.a aVar2 = this.f10290a;
        if (aVar2 != null) {
            aVar2.c(bVar, dVar);
        }
    }
}
