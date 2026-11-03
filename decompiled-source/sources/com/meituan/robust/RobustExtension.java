package com.meituan.robust;
/* loaded from: classes9.dex */
public interface RobustExtension {
    Object accessDispatch(RobustArguments robustArguments);

    String describeSelfFunction();

    boolean isSupport(RobustArguments robustArguments);

    void notifyListner(String str);
}
