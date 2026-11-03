package com.ss.android.ugc.aweme.pns.consentapi.network;

import bolts.Task;
import com.bytedance.retrofit2.http.Field;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Query;
import com.ss.android.ugc.aweme.pns.consentapi.data.c;
import java.util.List;
import kotlin.Metadata;
/* compiled from: IConsentApi.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\bf\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013J@\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0003\u0010\n\u001a\u00020\u00062\b\b\u0003\u0010\u000b\u001a\u00020\u0006H'J\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\b\b\u0003\u0010\n\u001a\u00020\u00062\b\b\u0003\u0010\u000b\u001a\u00020\u0006H'J,\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\n\u001a\u00020\u00062\b\b\u0003\u0010\u000b\u001a\u00020\u0006H'J,\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\n\u001a\u00020\u00062\b\b\u0003\u0010\u000b\u001a\u00020\u0006H'J2\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\b\b\u0003\u0010\n\u001a\u00020\u00062\b\b\u0003\u0010\u000b\u001a\u00020\u0006H'J2\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\b\b\u0003\u0010\n\u001a\u00020\u00062\b\b\u0003\u0010\u000b\u001a\u00020\u0006H'¨\u0006\u0014"}, d2 = {"Lcom/ss/android/ugc/aweme/pns/consentapi/network/IConsentApi;", "", "createConsentRecord", "Lbolts/Task;", "Lcom/ss/android/ugc/aweme/pns/consentapi/data/BaseResponse;", "entityKey", "", "businessFlow", "status", "", "sdkVersion", "sdkName", "fetchConsentRecord", "Lcom/ss/android/ugc/aweme/pns/consentapi/data/FetchConsentResponse;", "getConsentEntity", "getConsentRecord", "listConsentEntity", "", "listConsentRecord", "Companion", "pns_consent_sdk_api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes9.dex */
public interface IConsentApi {

    /* renamed from: a  reason: collision with root package name */
    public static final a f18703a = a.f18704a;

    @FormUrlEncoded
    @POST("/consent/api/record/create/v1")
    Task<com.ss.android.ugc.aweme.pns.consentapi.data.a> createConsentRecord(@Field("entity_keys") String str, @Field("business_flow") String str2, @Field("status") int i2, @Field("sdk_version") String str3, @Field("sdk_name") String str4);

    @GET("/consent/api/combine/list/v1?sdk_version")
    Task<c> fetchConsentRecord(@Query("sdk_version") String str, @Query("sdk_name") String str2);

    @FormUrlEncoded
    @GET("/consent/api/entity/get/v1")
    Task<com.ss.android.ugc.aweme.pns.consentapi.data.a> getConsentEntity(@Field("key") String str, @Field("sdk_version") String str2, @Field("sdk_name") String str3);

    @FormUrlEncoded
    @GET("/consent/api/record/get/v1")
    Task<com.ss.android.ugc.aweme.pns.consentapi.data.a> getConsentRecord(@Field("entity_key") String str, @Field("sdk_version") String str2, @Field("sdk_name") String str3);

    @FormUrlEncoded
    @GET("/consent/api/entity/list/v1")
    Task<com.ss.android.ugc.aweme.pns.consentapi.data.a> listConsentEntity(@Field("keys") List<String> list, @Field("sdk_version") String str, @Field("sdk_name") String str2);

    @FormUrlEncoded
    @GET("/consent/api/record/list/v1")
    Task<com.ss.android.ugc.aweme.pns.consentapi.data.a> listConsentRecord(@Field("keys") List<String> list, @Field("sdk_version") String str, @Field("sdk_name") String str2);

    /* compiled from: IConsentApi.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ss/android/ugc/aweme/pns/consentapi/network/IConsentApi$Companion;", "", "()V", "sdkName", "", "sdkVersion", "pns_consent_sdk_api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ a f18704a = new a();

        /* renamed from: b  reason: collision with root package name */
        private static final String f18705b = "33.6.1.0";

        /* renamed from: c  reason: collision with root package name */
        private static final String f18706c = "pns_consent_sdk";

        private a() {
        }
    }

    /* compiled from: IConsentApi.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class b {
        public static /* synthetic */ Task a(IConsentApi iConsentApi, String str, String str2, int i2, String str3, String str4, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 8) != 0) {
                    str3 = "33.6.1.0";
                }
                String str5 = str3;
                if ((i3 & 16) != 0) {
                    str4 = "pns_consent_sdk";
                }
                return iConsentApi.createConsentRecord(str, str2, i2, str5, str4);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createConsentRecord");
        }

        public static /* synthetic */ Task a(IConsentApi iConsentApi, String str, String str2, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != 0) {
                    str = "33.6.1.0";
                }
                if ((i2 & 2) != 0) {
                    str2 = "pns_consent_sdk";
                }
                return iConsentApi.fetchConsentRecord(str, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fetchConsentRecord");
        }

        public static /* synthetic */ Task a(IConsentApi iConsentApi, String str, String str2, String str3, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    str2 = "33.6.1.0";
                }
                if ((i2 & 4) != 0) {
                    str3 = "pns_consent_sdk";
                }
                return iConsentApi.getConsentEntity(str, str2, str3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getConsentEntity");
        }

        public static /* synthetic */ Task a(IConsentApi iConsentApi, List list, String str, String str2, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    str = "33.6.1.0";
                }
                if ((i2 & 4) != 0) {
                    str2 = "pns_consent_sdk";
                }
                return iConsentApi.listConsentEntity(list, str, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: listConsentEntity");
        }

        public static /* synthetic */ Task b(IConsentApi iConsentApi, String str, String str2, String str3, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    str2 = "33.6.1.0";
                }
                if ((i2 & 4) != 0) {
                    str3 = "pns_consent_sdk";
                }
                return iConsentApi.getConsentRecord(str, str2, str3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getConsentRecord");
        }

        public static /* synthetic */ Task b(IConsentApi iConsentApi, List list, String str, String str2, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    str = "33.6.1.0";
                }
                if ((i2 & 4) != 0) {
                    str2 = "pns_consent_sdk";
                }
                return iConsentApi.listConsentRecord(list, str, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: listConsentRecord");
        }
    }
}
