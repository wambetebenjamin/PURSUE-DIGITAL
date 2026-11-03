package com.bytedance.sync.persistence.business;

import java.util.List;
/* compiled from: BusinessDao.java */
/* loaded from: classes3.dex */
public interface b {
    void delete(a aVar);

    void deleteByBusinessId(String str);

    List<a> getAll();

    void insertOrUpdate(a... aVarArr);

    List<a> loadAllByIds(Long[] lArr);
}
