package com.bytedance.services.apm.api;

import com.bytedance.news.common.service.manager.IService;
import java.io.File;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public interface IHttpService extends IService {
    n buildMultipartUpload(String str, String str2, boolean z);

    n buildMultipartUpload(String str, String str2, boolean z, Map<String, String> map);

    d doGet(String str, Map<String, String> map);

    d doPost(String str, byte[] bArr, Map<String, String> map);

    d uploadFiles(String str, List<File> list, Map<String, String> map);
}
