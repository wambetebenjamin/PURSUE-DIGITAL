package com.fackbook.drawee;

import com.facebook.common.internal.Supplier;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import kotlin.Metadata;
/* compiled from: IDraweeConfigOutService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u001e\u0012\u0018\b\u0001\u0012\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u0004\u0018\u00010\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/fackbook/drawee/IDraweeConfigOutService;", "", "getDraweeControllerBuilder", "Lcom/facebook/common/internal/Supplier;", "Lcom/facebook/drawee/controller/AbstractDraweeControllerBuilder;", "drawee_noasanRelease"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface IDraweeConfigOutService {
    Supplier<? extends AbstractDraweeControllerBuilder<?, ?, ?, ?>> getDraweeControllerBuilder();
}
