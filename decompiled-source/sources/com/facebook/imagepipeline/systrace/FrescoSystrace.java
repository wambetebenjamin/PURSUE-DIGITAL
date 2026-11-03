package com.facebook.imagepipeline.systrace;
/* loaded from: classes8.dex */
public class FrescoSystrace {
    public static final ArgsBuilder NO_OP_ARGS_BUILDER = new NoOpArgsBuilder();
    private static volatile Systrace sInstance = null;

    /* loaded from: classes8.dex */
    public interface ArgsBuilder {
        ArgsBuilder arg(String str, double d2);

        ArgsBuilder arg(String str, int i2);

        ArgsBuilder arg(String str, long j2);

        ArgsBuilder arg(String str, Object obj);

        void flush();
    }

    /* loaded from: classes8.dex */
    public interface Systrace {
        void beginSection(String str);

        ArgsBuilder beginSectionWithArgs(String str);

        void endSection();

        boolean isTracing();
    }

    private FrescoSystrace() {
    }

    public static void provide(Systrace systrace) {
        sInstance = systrace;
    }

    public static void beginSection(String str) {
        getInstance().beginSection(str);
    }

    public static ArgsBuilder beginSectionWithArgs(String str) {
        return getInstance().beginSectionWithArgs(str);
    }

    public static void endSection() {
        getInstance().endSection();
    }

    public static boolean isTracing() {
        return getInstance().isTracing();
    }

    private static Systrace getInstance() {
        if (sInstance == null) {
            synchronized (FrescoSystrace.class) {
                if (sInstance == null) {
                    sInstance = new DefaultFrescoSystrace();
                }
            }
        }
        return sInstance;
    }

    /* loaded from: classes8.dex */
    private static final class NoOpArgsBuilder implements ArgsBuilder {
        @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.ArgsBuilder
        public ArgsBuilder arg(String str, double d2) {
            return this;
        }

        @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.ArgsBuilder
        public ArgsBuilder arg(String str, int i2) {
            return this;
        }

        @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.ArgsBuilder
        public ArgsBuilder arg(String str, long j2) {
            return this;
        }

        @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.ArgsBuilder
        public ArgsBuilder arg(String str, Object obj) {
            return this;
        }

        @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.ArgsBuilder
        public void flush() {
        }

        private NoOpArgsBuilder() {
        }
    }
}
