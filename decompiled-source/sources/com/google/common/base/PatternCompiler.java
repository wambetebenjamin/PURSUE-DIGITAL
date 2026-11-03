package com.google.common.base;
/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes6.dex */
public interface PatternCompiler {
    CommonPattern compile(String str);

    boolean isPcreLike();
}
