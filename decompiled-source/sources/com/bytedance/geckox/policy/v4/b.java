package com.bytedance.geckox.policy.v4;

import android.util.Pair;
import com.bytedance.geckox.model.UpdatePackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* compiled from: V4GeckoUpdateListener.java */
/* loaded from: classes2.dex */
public class b extends com.bytedance.geckox.listener.a {

    /* renamed from: a  reason: collision with root package name */
    private Map<com.bytedance.geckox.listener.a, Set<String>> f9322a;

    /* renamed from: b  reason: collision with root package name */
    private Map<String, Integer> f9323b = new HashMap();

    public b(Map<com.bytedance.geckox.listener.a, Set<String>> map) {
        this.f9322a = map;
    }

    @Override // com.bytedance.geckox.listener.a
    public void onCheckServerVersionSuccess(Map<String, List<Pair<String, Long>>> map, Map<String, List<UpdatePackage>> map2) {
        super.onCheckServerVersionSuccess(map, map2);
        for (Map.Entry<com.bytedance.geckox.listener.a, Set<String>> entry : this.f9322a.entrySet()) {
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            for (String str : entry.getValue()) {
                String[] split = str.split("-");
                String str2 = split[0];
                String str3 = split[1];
                hashMap.put(str2, map.get(str2));
                List<UpdatePackage> list = map2.get(str2);
                if (list == null || list.isEmpty()) {
                    hashMap2.put(str2, null);
                } else {
                    ArrayList arrayList = new ArrayList();
                    for (UpdatePackage updatePackage : list) {
                        if (updatePackage.getFrom().contains(str3)) {
                            arrayList.add(updatePackage);
                            if (this.f9323b.containsKey(str)) {
                                this.f9323b.put(str, Integer.valueOf(this.f9323b.get(str).intValue() + 1));
                            } else {
                                this.f9323b.put(str, 1);
                            }
                        }
                    }
                    List list2 = (List) hashMap2.get(str2);
                    if (list2 != null) {
                        list2.addAll(arrayList);
                    } else {
                        hashMap2.put(str2, arrayList);
                    }
                }
            }
            entry.getKey().onCheckServerVersionSuccess(hashMap, hashMap2);
        }
    }

    @Override // com.bytedance.geckox.listener.a
    public void onCheckServerVersionFail(Map<String, List<Pair<String, Long>>> map, Throwable th) {
        super.onCheckServerVersionFail(map, th);
        for (Map.Entry<com.bytedance.geckox.listener.a, Set<String>> entry : this.f9322a.entrySet()) {
            HashMap hashMap = new HashMap();
            for (String str : entry.getValue()) {
                String str2 = str.split("-")[0];
                hashMap.put(str2, map.get(str2));
            }
            entry.getKey().onCheckServerVersionFail(hashMap, th);
        }
    }

    @Override // com.bytedance.geckox.listener.a
    public void onCheckRequestIntercept(int i2, Map<String, List<Pair<String, Long>>> map, Throwable th) {
        super.onCheckRequestIntercept(i2, map, th);
        for (Map.Entry<com.bytedance.geckox.listener.a, Set<String>> entry : this.f9322a.entrySet()) {
            HashMap hashMap = new HashMap();
            for (String str : entry.getValue()) {
                String str2 = str.split("-")[0];
                hashMap.put(str2, map.get(str2));
            }
            entry.getKey().onCheckRequestIntercept(i2, hashMap, th);
        }
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdateStart(UpdatePackage updatePackage) {
        super.onUpdateStart(updatePackage);
        for (Map.Entry<com.bytedance.geckox.listener.a, Set<String>> entry : this.f9322a.entrySet()) {
            for (String str : entry.getValue()) {
                String[] split = str.split("-");
                String str2 = split[0];
                if (updatePackage.getFrom().contains(split[1]) && updatePackage.getAccessKey().equals(str2)) {
                    entry.getKey().onUpdateStart(updatePackage);
                }
            }
        }
    }

    @Override // com.bytedance.geckox.listener.a
    public void onDownloadSuccess(UpdatePackage updatePackage) {
        super.onDownloadSuccess(updatePackage);
        for (Map.Entry<com.bytedance.geckox.listener.a, Set<String>> entry : this.f9322a.entrySet()) {
            for (String str : entry.getValue()) {
                String[] split = str.split("-");
                String str2 = split[0];
                if (updatePackage.getFrom().contains(split[1]) && updatePackage.getAccessKey().equals(str2)) {
                    entry.getKey().onDownloadSuccess(updatePackage);
                }
            }
        }
    }

    @Override // com.bytedance.geckox.listener.a
    public void onDownloadFail(UpdatePackage updatePackage, Throwable th) {
        super.onDownloadFail(updatePackage, th);
        for (Map.Entry<com.bytedance.geckox.listener.a, Set<String>> entry : this.f9322a.entrySet()) {
            for (String str : entry.getValue()) {
                String[] split = str.split("-");
                String str2 = split[0];
                if (updatePackage.getFrom().contains(split[1]) && updatePackage.getAccessKey().equals(str2)) {
                    entry.getKey().onDownloadFail(updatePackage, th);
                }
            }
        }
    }

    @Override // com.bytedance.geckox.listener.a
    public void onActivateSuccess(UpdatePackage updatePackage) {
        super.onActivateSuccess(updatePackage);
        for (Map.Entry<com.bytedance.geckox.listener.a, Set<String>> entry : this.f9322a.entrySet()) {
            for (String str : entry.getValue()) {
                String[] split = str.split("-");
                String str2 = split[0];
                if (updatePackage.getFrom().contains(split[1]) && updatePackage.getAccessKey().equals(str2)) {
                    entry.getKey().onActivateSuccess(updatePackage);
                }
            }
        }
    }

    @Override // com.bytedance.geckox.listener.a
    public void onActivateFail(UpdatePackage updatePackage, Throwable th) {
        super.onActivateFail(updatePackage, th);
        for (Map.Entry<com.bytedance.geckox.listener.a, Set<String>> entry : this.f9322a.entrySet()) {
            for (String str : entry.getValue()) {
                String[] split = str.split("-");
                String str2 = split[0];
                if (updatePackage.getFrom().contains(split[1]) && updatePackage.getAccessKey().equals(str2)) {
                    entry.getKey().onActivateFail(updatePackage, th);
                }
            }
        }
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdateSuccess(UpdatePackage updatePackage, long j2) {
        super.onUpdateSuccess(updatePackage, j2);
        for (Map.Entry<com.bytedance.geckox.listener.a, Set<String>> entry : this.f9322a.entrySet()) {
            for (String str : entry.getValue()) {
                String[] split = str.split("-");
                String str2 = split[0];
                if (updatePackage.getFrom().contains(split[1]) && updatePackage.getAccessKey().equals(str2)) {
                    entry.getKey().onUpdateSuccess(updatePackage, j2);
                    if (a(str)) {
                        entry.getKey().onUpdateFinish();
                    }
                }
            }
        }
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdateFailed(UpdatePackage updatePackage, Throwable th) {
        super.onUpdateFailed(updatePackage, th);
        for (Map.Entry<com.bytedance.geckox.listener.a, Set<String>> entry : this.f9322a.entrySet()) {
            for (String str : entry.getValue()) {
                String[] split = str.split("-");
                String str2 = split[0];
                if (updatePackage.getFrom().contains(split[1]) && updatePackage.getAccessKey().equals(str2)) {
                    entry.getKey().onUpdateFailed(updatePackage, th);
                    if (a(str)) {
                        entry.getKey().onUpdateFinish();
                    }
                }
            }
        }
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdateFinish() {
        super.onUpdateFinish();
        for (Map.Entry<com.bytedance.geckox.listener.a, Set<String>> entry : this.f9322a.entrySet()) {
            entry.getKey().onUpdateFinish();
        }
    }

    @Override // com.bytedance.geckox.listener.a
    public void onClean(String str) {
        super.onClean(str);
        for (Map.Entry<com.bytedance.geckox.listener.a, Set<String>> entry : this.f9322a.entrySet()) {
            entry.getKey().onClean(str);
        }
    }

    private boolean a(String str) {
        int intValue = this.f9323b.get(str).intValue() - 1;
        this.f9323b.put(str, Integer.valueOf(intValue));
        return intValue == 0;
    }
}
