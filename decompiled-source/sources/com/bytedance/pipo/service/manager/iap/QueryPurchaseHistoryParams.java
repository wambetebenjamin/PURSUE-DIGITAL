package com.bytedance.pipo.service.manager.iap;
/* loaded from: classes2.dex */
public final class QueryPurchaseHistoryParams {
    private final ProductType productType;

    private QueryPurchaseHistoryParams(ProductType productType) {
        this.productType = productType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ProductType getProductType() {
        return this.productType;
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private ProductType productType;

        public Builder setProductType(ProductType productType) {
            this.productType = productType;
            return this;
        }

        public QueryPurchaseHistoryParams build() {
            if (this.productType == null) {
                throw new IllegalArgumentException("Product type must be set");
            }
            return new QueryPurchaseHistoryParams(this.productType);
        }

        private Builder() {
        }
    }
}
