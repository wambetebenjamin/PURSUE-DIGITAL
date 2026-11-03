package com.bytedance.ttgame.module.database;

import com.bytedance.common.process.cross.a;
import com.bytedance.ttgame.module.database.api.Decryption;
import com.bytedance.ttgame.module.database.api.Encryption;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;
/* compiled from: DbProxy.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J6\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\nH\u0096\u0002¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/bytedance/ttgame/module/database/DbInvocationHandler;", "Ljava/lang/reflect/InvocationHandler;", "dao", "", "(Ljava/lang/Object;)V", "invoke", "proxy", "method", "Ljava/lang/reflect/Method;", a.COL_ARGS, "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DbInvocationHandler implements InvocationHandler {
    private final Object dao;

    public DbInvocationHandler(Object dao) {
        Intrinsics.checkNotNullParameter(dao, "dao");
        this.dao = dao;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        Object obj2;
        Annotation[] annotations;
        Timber.tag(GsdkDb.TAG).i((method == null || (r1 = method.toString()) == null) ? "" : "", new Object[0]);
        if (objArr != null) {
            for (Object obj3 : objArr) {
                Timber.tag(GsdkDb.TAG).d(obj3.toString(), new Object[0]);
            }
        }
        boolean z = true;
        Object obj4 = null;
        if (method == null || (annotations = method.getAnnotations()) == null) {
            obj2 = null;
        } else {
            obj2 = null;
            for (Annotation annotation : annotations) {
                if (annotation instanceof Encryption) {
                    GsdkDb.operateEncryptMethod(method, objArr, this.dao);
                } else if (annotation instanceof Decryption) {
                    obj2 = GsdkDb.operateDecryptMethod(objArr, method, this.dao);
                }
                z = false;
            }
        }
        if (z) {
            if (objArr != null) {
                if (method != null) {
                    obj4 = method.invoke(this.dao, Arrays.copyOf(objArr, objArr.length));
                }
            } else if (method != null) {
                obj4 = method.invoke(this.dao, new Object[0]);
            }
            return obj4;
        }
        return obj2;
    }
}
