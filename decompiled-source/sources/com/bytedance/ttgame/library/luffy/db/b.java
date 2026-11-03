package com.bytedance.ttgame.library.luffy.db;

import androidx.exifinterface.media.ExifInterface;
import com.bytedance.ttgame.module.share.api.utils.ConstantKt;
import gsdk.impl.account.toutiao.dh;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
/* compiled from: ILuffyDataBase.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH&J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\fJ\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\tH&J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H&J\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H&J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H&JP\u0010\u0016\u001a\u00020\u000b2\"\u0010\u0017\u001a\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\u0018j\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0003`\u00192\"\u0010\u001a\u001a\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\u0018j\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0003`\u0019H&J\u0010\u0010\u001b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\tH&J2\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\"\u0010\u001a\u001a\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\u0018j\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0003`\u0019H&¨\u0006\u001c"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/db/ILuffyDataBase;", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/io/Serializable;", "", "delete", "", "data", "(Ljava/io/Serializable;)I", "getAll", "", "insert", "", "(Ljava/io/Serializable;)V", "limit", "from", "to", "orderBy", "key", "", ConstantKt.DESC, "", "selectAll", "update", dh.z, "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "where", "values", "luffy_db_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public interface b<T extends Serializable> {
    b<T> a(int i2, int i3);

    b<T> a(String str, boolean z);

    b<T> a(HashMap<String, Object> hashMap);

    List<T> a();

    void a(T t);

    void a(HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2);

    void a(List<? extends T> list);

    int b(T t);

    b<T> b();

    List<T> c();
}
