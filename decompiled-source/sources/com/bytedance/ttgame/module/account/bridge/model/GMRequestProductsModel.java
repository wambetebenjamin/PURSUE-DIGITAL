package com.bytedance.ttgame.module.account.bridge.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMPayModel.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J)\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/module/account/bridge/model/GMRequestProductsModel;", "", "products", "", "Lcom/bytedance/ttgame/module/account/bridge/model/GMProductModel;", "accumulations", "Lcom/bytedance/ttgame/module/account/bridge/model/GMProductAccumulation;", "(Ljava/util/List;Ljava/util/List;)V", "getAccumulations", "()Ljava/util/List;", "getProducts", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "pay_bridge_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GMRequestProductsModel {
    private final List<GMProductAccumulation> accumulations;
    private final List<GMProductModel> products;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GMRequestProductsModel copy$default(GMRequestProductsModel gMRequestProductsModel, List list, List list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = gMRequestProductsModel.products;
        }
        if ((i2 & 2) != 0) {
            list2 = gMRequestProductsModel.accumulations;
        }
        return gMRequestProductsModel.copy(list, list2);
    }

    public final List<GMProductModel> component1() {
        return this.products;
    }

    public final List<GMProductAccumulation> component2() {
        return this.accumulations;
    }

    public final GMRequestProductsModel copy(List<GMProductModel> products, List<GMProductAccumulation> accumulations) {
        Intrinsics.checkNotNullParameter(products, "products");
        Intrinsics.checkNotNullParameter(accumulations, "accumulations");
        return new GMRequestProductsModel(products, accumulations);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GMRequestProductsModel) {
            GMRequestProductsModel gMRequestProductsModel = (GMRequestProductsModel) obj;
            return Intrinsics.areEqual(this.products, gMRequestProductsModel.products) && Intrinsics.areEqual(this.accumulations, gMRequestProductsModel.accumulations);
        }
        return false;
    }

    public int hashCode() {
        return (this.products.hashCode() * 31) + this.accumulations.hashCode();
    }

    public String toString() {
        return "GMRequestProductsModel(products=" + this.products + ", accumulations=" + this.accumulations + ')';
    }

    public GMRequestProductsModel(List<GMProductModel> products, List<GMProductAccumulation> accumulations) {
        Intrinsics.checkNotNullParameter(products, "products");
        Intrinsics.checkNotNullParameter(accumulations, "accumulations");
        this.products = products;
        this.accumulations = accumulations;
    }

    public final List<GMProductModel> getProducts() {
        return this.products;
    }

    public final List<GMProductAccumulation> getAccumulations() {
        return this.accumulations;
    }
}
