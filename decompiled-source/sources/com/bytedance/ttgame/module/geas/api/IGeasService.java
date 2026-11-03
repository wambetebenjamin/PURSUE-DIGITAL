package com.bytedance.ttgame.module.geas.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.module.geas.bridge.GeasModule;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
/* compiled from: IGeasService.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H'J\u001c\u0010\u0005\u001a\u00020\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH&¨\u0006\r"}, d2 = {"Lcom/bytedance/ttgame/module/geas/api/IGeasService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "endManually", "", "init", GeasModule.SetExtraParams, "map", "", "", "", "startManually", "address", "tag", "geas_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface IGeasService extends IModuleApi {
    void endManually();

    @Deprecated(message = "280版本后不需要接入方手动调用")
    void init();

    void setExtraParams(Map<String, Object> map);

    void startManually(String str, String str2);
}
