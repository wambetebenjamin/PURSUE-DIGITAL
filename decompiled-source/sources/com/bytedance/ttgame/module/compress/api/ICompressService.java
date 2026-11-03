package com.bytedance.ttgame.module.compress.api;

import android.app.Application;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import java.io.File;
import kotlin.Metadata;
/* compiled from: ICompressService.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lcom/bytedance/ttgame/module/compress/api/ICompressService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "doSoLoad", "", "getSoDecompressDir", "Ljava/io/File;", "init", "app", "Landroid/app/Application;", "config", "Lcom/bytedance/ttgame/module/compress/api/CompressConfig;", "isSoDecoded", "", "quality_api_compress_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface ICompressService extends IModuleApi {
    void doSoLoad();

    File getSoDecompressDir();

    void init(Application application, CompressConfig compressConfig);

    boolean isSoDecoded();
}
