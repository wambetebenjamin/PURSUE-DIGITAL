package com.bytedance.ttgame.module.webview.api.aidl;

import android.content.Context;
import java.util.Map;
/* loaded from: classes7.dex */
public interface SyncCommand extends Command {
    Map execute(Context context, Map map);

    @Override // com.bytedance.ttgame.module.webview.api.aidl.Command
    String name();
}
