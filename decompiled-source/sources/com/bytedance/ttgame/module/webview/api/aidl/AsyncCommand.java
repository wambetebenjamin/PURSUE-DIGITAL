package com.bytedance.ttgame.module.webview.api.aidl;

import android.content.Context;
import java.util.Map;
/* loaded from: classes7.dex */
public interface AsyncCommand extends Command {
    void execute(Context context, Map map, IWebAidlResultCallback iWebAidlResultCallback);

    @Override // com.bytedance.ttgame.module.webview.api.aidl.Command
    String name();
}
