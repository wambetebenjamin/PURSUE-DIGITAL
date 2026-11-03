package com.facebook.drawee.backends.pipeline;

import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweePlaceHolderConfig;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class DraweeConfig {
    @Nullable
    private final ImmutableList<DrawableFactory> mCustomDrawableFactories;
    private final Supplier<Boolean> mDebugOverlayEnabledSupplier;
    @Nullable
    private final DraweePlaceHolderConfig mDraweePlaceHolderConfig;
    private Set<ControllerListener> mGlobalControllerListeners;
    @Nullable
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    private DraweeConfig(Builder builder) {
        Supplier<Boolean> of;
        this.mCustomDrawableFactories = builder.mCustomDrawableFactories != null ? ImmutableList.copyOf(builder.mCustomDrawableFactories) : null;
        if (builder.mDebugOverlayEnabledSupplier == null) {
            of = Suppliers.of(false);
        } else {
            of = builder.mDebugOverlayEnabledSupplier;
        }
        this.mDebugOverlayEnabledSupplier = of;
        this.mPipelineDraweeControllerFactory = builder.mPipelineDraweeControllerFactory;
        this.mGlobalControllerListeners = builder.mGlobalControllerListeners;
        this.mDraweePlaceHolderConfig = builder.mDraweePlaceHolderConfig;
    }

    @Nullable
    public ImmutableList<DrawableFactory> getCustomDrawableFactories() {
        return this.mCustomDrawableFactories;
    }

    @Nullable
    public PipelineDraweeControllerFactory getPipelineDraweeControllerFactory() {
        return this.mPipelineDraweeControllerFactory;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Supplier<Boolean> getDebugOverlayEnabledSupplier() {
        return this.mDebugOverlayEnabledSupplier;
    }

    public Set<ControllerListener> getGlobalControllerListeners() {
        return this.mGlobalControllerListeners;
    }

    public DraweePlaceHolderConfig getDraweePlaceHolderConfig() {
        return this.mDraweePlaceHolderConfig;
    }

    /* loaded from: classes8.dex */
    public static class Builder {
        private List<DrawableFactory> mCustomDrawableFactories;
        private Supplier<Boolean> mDebugOverlayEnabledSupplier;
        @Nullable
        private DraweePlaceHolderConfig mDraweePlaceHolderConfig;
        private Set<ControllerListener> mGlobalControllerListeners;
        private PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

        public Builder addGlobalControllerListener(ControllerListener controllerListener) {
            if (this.mGlobalControllerListeners == null) {
                this.mGlobalControllerListeners = new HashSet();
            }
            this.mGlobalControllerListeners.add(controllerListener);
            return this;
        }

        public Builder addCustomDrawableFactory(DrawableFactory drawableFactory) {
            if (this.mCustomDrawableFactories == null) {
                this.mCustomDrawableFactories = new ArrayList();
            }
            this.mCustomDrawableFactories.add(drawableFactory);
            return this;
        }

        public Builder setDrawDebugOverlay(boolean z) {
            return setDebugOverlayEnabledSupplier(Suppliers.of(Boolean.valueOf(z)));
        }

        public Builder setDebugOverlayEnabledSupplier(Supplier<Boolean> supplier) {
            Preconditions.checkNotNull(supplier);
            this.mDebugOverlayEnabledSupplier = supplier;
            return this;
        }

        public Builder setPipelineDraweeControllerFactory(PipelineDraweeControllerFactory pipelineDraweeControllerFactory) {
            this.mPipelineDraweeControllerFactory = pipelineDraweeControllerFactory;
            return this;
        }

        public Builder setDraweePlaceHolderConfig(DraweePlaceHolderConfig draweePlaceHolderConfig) {
            this.mDraweePlaceHolderConfig = draweePlaceHolderConfig;
            return this;
        }

        public Builder setDraweeHierarchyDefaultFadeDuration(int i2) {
            GenericDraweeHierarchyBuilder.setDefaultFadeDuration(i2);
            return this;
        }

        public DraweeConfig build() {
            return new DraweeConfig(this);
        }
    }
}
