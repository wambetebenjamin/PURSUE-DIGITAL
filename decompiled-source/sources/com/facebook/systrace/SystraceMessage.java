package com.facebook.systrace;
/* loaded from: classes8.dex */
public final class SystraceMessage {
    private static final Builder NOOP_BUILDER = new NoopBuilder();

    /* loaded from: classes8.dex */
    public static abstract class Builder {
        public abstract Builder arg(String str, double d2);

        public abstract Builder arg(String str, int i2);

        public abstract Builder arg(String str, long j2);

        public abstract Builder arg(String str, Object obj);

        public abstract void flush();
    }

    /* loaded from: classes8.dex */
    private interface Flusher {
        void flush(StringBuilder sb);
    }

    public static Builder beginSection(long j2, String str) {
        return NOOP_BUILDER;
    }

    public static Builder endSection(long j2) {
        return NOOP_BUILDER;
    }

    /* loaded from: classes8.dex */
    private static class NoopBuilder extends Builder {
        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, double d2) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, int i2) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, long j2) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, Object obj) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public void flush() {
        }

        private NoopBuilder() {
        }
    }
}
