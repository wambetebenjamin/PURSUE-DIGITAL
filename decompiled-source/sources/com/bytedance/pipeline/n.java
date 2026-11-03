package com.bytedance.pipeline;

import com.bytedance.pipeline.i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* compiled from: SubBranchInterceptor.java */
/* loaded from: classes2.dex */
abstract class n<IN, OUT> extends d<IN, OUT> {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, a> f10035a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, a> b() {
        return this.f10035a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(List<i> list) {
        return !list.isEmpty() && list.get(list.size() - 1).f10021a == g.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.pipeline.d
    public final void a(Object... objArr) {
        super.a(objArr);
        if (objArr == null || objArr.length != 1 || objArr[0] == null) {
            throw new IllegalStateException("args error");
        }
        try {
            this.f10035a = (Map) objArr[0];
        } catch (ClassCastException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    /* compiled from: SubBranchInterceptor.java */
    /* loaded from: classes2.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private Map<String, a> f10037a = new HashMap();

        /* renamed from: b  reason: collision with root package name */
        private com.bytedance.pipeline.listener.a f10038b;

        public b a(com.bytedance.pipeline.listener.a aVar) {
            this.f10038b = aVar;
            return this;
        }

        public a a(String str) {
            if (this.f10037a.containsKey(str)) {
                throw new IllegalArgumentException("duplicated branch name");
            }
            a aVar = new a();
            this.f10037a.put(str, aVar);
            return aVar;
        }

        public i a(Class<? extends n> cls) {
            return i.a.a().a(cls).a(this.f10037a).a(this.f10038b).b();
        }
    }

    /* compiled from: SubBranchInterceptor.java */
    /* loaded from: classes2.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        List<i> f10036a = new ArrayList();

        public a a(i iVar) {
            this.f10036a.add(iVar);
            return this;
        }

        public a a(List<i> list) {
            this.f10036a.addAll(list);
            return this;
        }
    }
}
