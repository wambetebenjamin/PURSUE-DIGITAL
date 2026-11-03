package com.facebook.imagepipeline.systrace;

import android.os.Build;
import android.os.Trace;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
/* loaded from: classes8.dex */
public class DefaultFrescoSystrace implements FrescoSystrace.Systrace {
    @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.Systrace
    public void beginSection(String str) {
    }

    @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.Systrace
    public void endSection() {
    }

    @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.Systrace
    public boolean isTracing() {
        return false;
    }

    @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.Systrace
    public FrescoSystrace.ArgsBuilder beginSectionWithArgs(String str) {
        return FrescoSystrace.NO_OP_ARGS_BUILDER;
    }

    /* loaded from: classes8.dex */
    private static final class DefaultArgsBuilder implements FrescoSystrace.ArgsBuilder {
        private final StringBuilder mStringBuilder;

        public DefaultArgsBuilder(String str) {
            this.mStringBuilder = new StringBuilder(str);
        }

        @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.ArgsBuilder
        public void flush() {
            if (this.mStringBuilder.length() > 127) {
                this.mStringBuilder.setLength(127);
            }
            if (Build.VERSION.SDK_INT >= 18) {
                Trace.beginSection(this.mStringBuilder.toString());
            }
        }

        @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.ArgsBuilder
        public FrescoSystrace.ArgsBuilder arg(String str, Object obj) {
            this.mStringBuilder.append(';').append(str).append('=').append(obj == null ? "null" : obj.toString());
            return this;
        }

        @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.ArgsBuilder
        public FrescoSystrace.ArgsBuilder arg(String str, int i2) {
            this.mStringBuilder.append(';').append(str).append('=').append(Integer.toString(i2));
            return this;
        }

        @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.ArgsBuilder
        public FrescoSystrace.ArgsBuilder arg(String str, long j2) {
            this.mStringBuilder.append(';').append(str).append('=').append(Long.toString(j2));
            return this;
        }

        @Override // com.facebook.imagepipeline.systrace.FrescoSystrace.ArgsBuilder
        public FrescoSystrace.ArgsBuilder arg(String str, double d2) {
            this.mStringBuilder.append(';').append(str).append('=').append(Double.toString(d2));
            return this;
        }
    }
}
