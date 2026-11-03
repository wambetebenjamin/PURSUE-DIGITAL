package com.bytedance.applog.strategy;
/* compiled from: DefaultReportDropStrategy.java */
/* loaded from: classes.dex */
public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private static final com.bytedance.applog.log.a<b> f3461a = new com.bytedance.applog.log.a<b>() { // from class: com.bytedance.applog.strategy.a.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.applog.log.a
        /* renamed from: a */
        public b create(Object... objArr) {
            return new a();
        }
    };

    @Override // com.bytedance.applog.strategy.b
    public long b() {
        return 1209600000L;
    }

    @Override // com.bytedance.applog.strategy.b
    public int c() {
        return -1;
    }

    public static b a() {
        return f3461a.get(new Object[0]);
    }
}
