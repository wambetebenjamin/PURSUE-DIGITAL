package com.bytedance.pipo.iap.common.ability.enums;
/* loaded from: classes2.dex */
public enum PayState {
    PerformPay,
    AmazonPay,
    ValidateReceipt,
    ExtraValidateReceipt,
    Consume;

    public static PayState fromOrdinal(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            return null;
                        }
                        return Consume;
                    }
                    return ExtraValidateReceipt;
                }
                return ValidateReceipt;
            }
            return AmazonPay;
        }
        return PerformPay;
    }
}
