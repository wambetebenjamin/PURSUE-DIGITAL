package com.bytedance.ttgame.module.database;

import android.os.Looper;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.bytedance.common.process.cross.a;
import com.bytedance.ttgame.module.database.api.ConnectInfoData;
import com.bytedance.ttgame.module.database.api.ConnectInfoDataNew;
import com.bytedance.ttgame.module.database.api.EncryptionMark;
import com.bytedance.ttgame.module.database.api.NeedEncryption;
import com.bytedance.ttgame.sdk.module.utils.AesCbcUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import timber.log.Timber;
/* compiled from: DbProxy.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002\u001a\u0014\u0010\u000b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002\u001a\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0002\u001a\u001e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000f2\f\u0010\u0012\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000fH\u0002\u001a1\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0010\u0010\u0014\u001a\f\u0012\u0006\b\u0001\u0012\u00020\t\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\tH\u0002¢\u0006\u0002\u0010\u0019\u001a/\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0010\u0010\u0014\u001a\f\u0012\u0006\b\u0001\u0012\u00020\t\u0018\u00010\u00152\u0006\u0010\u0018\u001a\u00020\tH\u0002¢\u0006\u0002\u0010\u001b\"\u001b\u0010\u0000\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"ENCRYPT_KEY", "", "getENCRYPT_KEY", "()J", "ENCRYPT_KEY$delegate", "Lkotlin/Lazy;", "TAG", "", "dbDecrypt", "", "value", "dbEncrypt", "decryptList", "", "list", "", "getDefaultConnectInfoNewDatas", "Lcom/bytedance/ttgame/module/database/api/ConnectInfoDataNew;", "connectInfos", "operateDecryptMethod", a.COL_ARGS, "", "method", "Ljava/lang/reflect/Method;", "loadUserInfoDao", "([Ljava/lang/Object;Ljava/lang/reflect/Method;Ljava/lang/Object;)Ljava/lang/Object;", "operateEncryptMethod", "(Ljava/lang/reflect/Method;[Ljava/lang/Object;Ljava/lang/Object;)V", "main_api_i18nRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class GsdkDb {
    private static final Lazy ENCRYPT_KEY$delegate = LazyKt.lazy(new Function0<Long>() { // from class: com.bytedance.ttgame.module.database.GsdkDb$ENCRYPT_KEY$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Long invoke() {
            return Long.valueOf(3182195);
        }
    });
    public static final String TAG = "GsdkDb";

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object operateDecryptMethod(Object[] objArr, Method method, Object obj) {
        Object invoke;
        if (objArr != null) {
            invoke = method.invoke(obj, Arrays.copyOf(objArr, objArr.length));
        } else {
            invoke = method.invoke(obj, new Object[0]);
        }
        if (invoke instanceof List) {
            decryptList((List) invoke);
        } else if (invoke instanceof LiveData) {
            final MutableLiveData mutableLiveData = new MutableLiveData();
            return Transformations.switchMap((LiveData) invoke, new Function() { // from class: com.bytedance.ttgame.module.database.GsdkDb$$ExternalSyntheticLambda0
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj2) {
                    LiveData m197operateDecryptMethod$lambda0;
                    m197operateDecryptMethod$lambda0 = GsdkDb.m197operateDecryptMethod$lambda0(MutableLiveData.this, obj2);
                    return m197operateDecryptMethod$lambda0;
                }
            });
        }
        Timber.tag(TAG).d("lsd", "operateDecryptMethod: origin class:" + invoke);
        return invoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: operateDecryptMethod$lambda-0  reason: not valid java name */
    public static final LiveData m197operateDecryptMethod$lambda0(MutableLiveData mutableLiveData, Object list) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "$mutableLiveData");
        Intrinsics.checkNotNullParameter(list, "list");
        if (list instanceof List) {
            Timber.tag(TAG).d("operateDecryptMethod: encrypt class:" + list, new Object[0]);
            decryptList((List) list);
            Timber.tag(TAG).d("operateDecryptMethod: origin class:" + list, new Object[0]);
            if (Intrinsics.areEqual(Looper.getMainLooper().getThread(), Thread.currentThread())) {
                mutableLiveData.setValue(list);
            } else {
                mutableLiveData.postValue(list);
            }
        }
        return mutableLiveData;
    }

    private static final void decryptList(List<?> list) {
        boolean z;
        Object obj;
        Field field;
        List<ConnectInfoDataNew> defaultConnectInfoNewDatas;
        Class<?> cls;
        Field[] declaredFields;
        Class<?> cls2;
        Field[] declaredFields2;
        for (Object obj2 : list) {
            if (obj2 == null || (cls2 = obj2.getClass()) == null || (declaredFields2 = cls2.getDeclaredFields()) == null) {
                z = false;
            } else {
                Intrinsics.checkNotNullExpressionValue(declaredFields2, "declaredFields");
                z = false;
                for (Field field2 : declaredFields2) {
                    Annotation[] annotations = field2.getAnnotations();
                    Intrinsics.checkNotNullExpressionValue(annotations, "field.annotations");
                    for (Annotation annotation : annotations) {
                        if ((annotation instanceof EncryptionMark) && Intrinsics.areEqual(field2.get(obj2), (Object) true)) {
                            z = true;
                        }
                    }
                }
            }
            if (z) {
                if (obj2 == null || (cls = obj2.getClass()) == null || (declaredFields = cls.getDeclaredFields()) == null) {
                    obj = null;
                    field = null;
                } else {
                    Intrinsics.checkNotNullExpressionValue(declaredFields, "declaredFields");
                    obj = null;
                    field = null;
                    for (Field field3 : declaredFields) {
                        Annotation[] annotations2 = field3.getAnnotations();
                        Intrinsics.checkNotNullExpressionValue(annotations2, "field.annotations");
                        Annotation[] annotationArr = annotations2;
                        int length = annotationArr.length;
                        int i2 = 0;
                        obj = obj;
                        while (i2 < length) {
                            Annotation annotation2 = annotationArr[i2];
                            if (annotation2 instanceof NeedEncryption) {
                                field3.setAccessible(false);
                                field3.set(obj2, dbDecrypt(field3.get(obj2)));
                                if (field3.getName().equals("connect_infos")) {
                                    obj = field3.get(obj2);
                                }
                                if (field3.getName().equals("connect_infos_new")) {
                                    field = field3;
                                }
                            }
                            if (annotation2 instanceof EncryptionMark) {
                                field3.set(obj2, false);
                            }
                            i2++;
                            obj = obj;
                        }
                    }
                }
                Object obj3 = field != null ? field.get(obj2) : null;
                if (obj3 == null || ((obj3 instanceof List) && ((List) obj3).isEmpty())) {
                    if (obj != null && (obj instanceof List) && (defaultConnectInfoNewDatas = getDefaultConnectInfoNewDatas((List) obj)) != null && field != null) {
                        field.set(obj2, defaultConnectInfoNewDatas);
                    }
                }
            }
        }
    }

    private static final long getENCRYPT_KEY() {
        return ((Number) ENCRYPT_KEY$delegate.getValue()).longValue();
    }

    private static final Object dbEncrypt(Object obj) {
        if (!(obj instanceof String)) {
            return obj instanceof Long ? Long.valueOf(((Number) obj).longValue() ^ getENCRYPT_KEY()) : obj;
        } else if (StringsKt.isBlank((CharSequence) obj)) {
            return (String) obj;
        } else {
            return AesCbcUtils.encrypt((String) obj);
        }
    }

    private static final Object dbDecrypt(Object obj) {
        if (!(obj instanceof String)) {
            return obj instanceof Long ? Long.valueOf(((Number) obj).longValue() ^ getENCRYPT_KEY()) : obj;
        } else if (StringsKt.isBlank((CharSequence) obj)) {
            return (String) obj;
        } else {
            return AesCbcUtils.decrypt((String) obj);
        }
    }

    private static final List<ConnectInfoDataNew> getDefaultConnectInfoNewDatas(List<?> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj != null && (obj instanceof ConnectInfoData)) {
                ConnectInfoDataNew connectInfoDataNew = new ConnectInfoDataNew();
                ConnectInfoData connectInfoData = (ConnectInfoData) obj;
                connectInfoDataNew.puid = connectInfoData.puid;
                connectInfoDataNew.user_type = connectInfoData.user_type;
                connectInfoDataNew.nickname = connectInfoData.nickname;
                connectInfoDataNew.avatar_url = connectInfoData.avatar_url;
                arrayList.add(connectInfoDataNew);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void operateEncryptMethod(Method method, Object[] objArr, Object obj) {
        int i2;
        Annotation[] annotationArr;
        Field field;
        int i3 = 0;
        if (objArr != null) {
            int length = objArr.length;
            int i4 = 0;
            while (i4 < length) {
                Object obj2 = objArr[i4];
                Timber.tag(TAG).d("operateEncryptMethod: origin class:" + obj2, new Object[i3]);
                Annotation[] annotations = obj2.getClass().getAnnotations();
                Intrinsics.checkNotNullExpressionValue(annotations, "arg.javaClass.annotations");
                Annotation[] annotationArr2 = annotations;
                int length2 = annotationArr2.length;
                int i5 = i3;
                while (i5 < length2) {
                    if (annotationArr2[i5] instanceof NeedEncryption) {
                        Field[] declaredFields = obj2.getClass().getDeclaredFields();
                        Intrinsics.checkNotNullExpressionValue(declaredFields, "arg.javaClass.declaredFields");
                        Field[] fieldArr = declaredFields;
                        int length3 = fieldArr.length;
                        int i6 = i3;
                        boolean z = true;
                        while (i6 < length3) {
                            int i7 = length;
                            Field field2 = fieldArr[i6];
                            Annotation[] annotationArr3 = annotationArr2;
                            Annotation[] annotations2 = field2.getAnnotations();
                            Intrinsics.checkNotNullExpressionValue(annotations2, "field.annotations");
                            Annotation[] annotationArr4 = annotations2;
                            int length4 = annotationArr4.length;
                            boolean z2 = z;
                            int i8 = 0;
                            while (i8 < length4) {
                                Annotation[] annotationArr5 = annotationArr4;
                                if (annotationArr4[i8] instanceof EncryptionMark) {
                                    field2.setAccessible(true);
                                    Object obj3 = field2.get(obj2);
                                    field = field2;
                                    Objects.requireNonNull(obj3, "null cannot be cast to non-null type kotlin.Boolean");
                                    z2 = !((Boolean) obj3).booleanValue();
                                } else {
                                    field = field2;
                                }
                                i8++;
                                field2 = field;
                                annotationArr4 = annotationArr5;
                            }
                            i6++;
                            annotationArr2 = annotationArr3;
                            length = i7;
                            z = z2;
                        }
                        i2 = length;
                        annotationArr = annotationArr2;
                        Timber.tag(TAG).d("operateEncryptMethod:" + z, new Object[0]);
                        if (z) {
                            Field[] declaredFields2 = obj2.getClass().getDeclaredFields();
                            Intrinsics.checkNotNullExpressionValue(declaredFields2, "arg.javaClass.declaredFields");
                            Field[] fieldArr2 = declaredFields2;
                            int length5 = fieldArr2.length;
                            int i9 = 0;
                            while (i9 < length5) {
                                Field field3 = fieldArr2[i9];
                                Annotation[] annotations3 = field3.getAnnotations();
                                Intrinsics.checkNotNullExpressionValue(annotations3, "field.annotations");
                                Annotation[] annotationArr6 = annotations3;
                                int length6 = annotationArr6.length;
                                int i10 = 0;
                                while (i10 < length6) {
                                    Field[] fieldArr3 = fieldArr2;
                                    Annotation annotation = annotationArr6[i10];
                                    int i11 = length5;
                                    if (annotation instanceof NeedEncryption) {
                                        Object obj4 = field3.get(obj2);
                                        if (obj4 != null) {
                                            field3.setAccessible(true);
                                            field3.set(obj2, dbEncrypt(obj4));
                                            if (field3.getName().equals("connect_infos")) {
                                                field3.set(objArr, null);
                                            }
                                        }
                                    } else if (annotation instanceof EncryptionMark) {
                                        field3.setAccessible(true);
                                        field3.set(obj2, true);
                                        i10++;
                                        length5 = i11;
                                        fieldArr2 = fieldArr3;
                                    }
                                    i10++;
                                    length5 = i11;
                                    fieldArr2 = fieldArr3;
                                }
                                i9++;
                                fieldArr2 = fieldArr2;
                            }
                        }
                    } else {
                        i2 = length;
                        annotationArr = annotationArr2;
                    }
                    i5++;
                    annotationArr2 = annotationArr;
                    length = i2;
                    i3 = 0;
                }
                Timber.tag(TAG).d("operateEncryptMethod: encrypt class:" + obj2, new Object[0]);
                i4++;
                length = length;
                i3 = 0;
            }
        }
        if (objArr != null) {
            method.invoke(obj, Arrays.copyOf(objArr, objArr.length));
        } else {
            method.invoke(obj, new Object[0]);
        }
    }
}
