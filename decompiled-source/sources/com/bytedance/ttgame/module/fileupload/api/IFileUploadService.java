package com.bytedance.ttgame.module.fileupload.api;

import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import java.util.Map;
import kotlin.Metadata;
/* compiled from: IFileUploadService.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J@\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lcom/bytedance/ttgame/module/fileupload/api/IFileUploadService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "uploadFileAtPath", "", "type", "", "scene", "params", "", "path", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/module/fileupload/api/Callback;", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface IFileUploadService extends IModuleApi {
    void uploadFileAtPath(String str, String str2, Map<String, String> map, String str3, Callback callback);
}
