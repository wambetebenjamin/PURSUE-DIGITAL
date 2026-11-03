package g.hades.tgrp;

import com.bytedance.hades.tgrp.api.ResInfo;
import com.bytedance.hades.tgrp.impl.TGRPDownloaderImpl;
import com.bytedance.hades.tgrp.impl.TGRPServiceImpl;
/* loaded from: classes10.dex */
public class a implements ResInfo {

    /* renamed from: a  reason: collision with root package name */
    public final long f19105a;

    /* renamed from: b  reason: collision with root package name */
    public final String f19106b;

    /* renamed from: c  reason: collision with root package name */
    public final int f19107c;

    /* renamed from: d  reason: collision with root package name */
    public final String f19108d;

    public a(String str, long j2, String str2, int i2) {
        this.f19108d = str;
        this.f19105a = j2;
        this.f19106b = str2;
        this.f19107c = i2;
    }

    @Override // com.bytedance.hades.tgrp.api.ResInfo
    public String getAbsPath() {
        return this.f19106b;
    }

    @Override // com.bytedance.hades.tgrp.api.ResInfo
    public long getDataLength() {
        return this.f19105a;
    }

    @Override // com.bytedance.hades.tgrp.api.ResInfo
    public String getMd5() {
        TGRPServiceImpl a2 = TGRPServiceImpl.a();
        String str = this.f19108d;
        int i2 = this.f19107c;
        TGRPDownloaderImpl tGRPDownloaderImpl = (TGRPDownloaderImpl) a2.f9442a.get(str);
        return tGRPDownloaderImpl != null ? tGRPDownloaderImpl.a(i2) : "";
    }

    @Override // com.bytedance.hades.tgrp.api.ResInfo
    public String getResPath() {
        TGRPServiceImpl a2 = TGRPServiceImpl.a();
        String str = this.f19108d;
        int i2 = this.f19107c;
        TGRPDownloaderImpl tGRPDownloaderImpl = (TGRPDownloaderImpl) a2.f9442a.get(str);
        return tGRPDownloaderImpl != null ? tGRPDownloaderImpl.b(i2) : "";
    }
}
