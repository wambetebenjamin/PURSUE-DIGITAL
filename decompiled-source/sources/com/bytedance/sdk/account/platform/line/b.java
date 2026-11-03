package com.bytedance.sdk.account.platform.line;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.bytedance.sdk.account.platform.api.k;
import com.bytedance.sdk.account.platform.base.d;
import com.linecorp.linesdk.LineApiResponseCode;
import com.linecorp.linesdk.auth.LineLoginApi;
import com.linecorp.linesdk.auth.LineLoginResult;
/* compiled from: LineServiceImpl.java */
/* loaded from: classes2.dex */
class b implements k {

    /* renamed from: a  reason: collision with root package name */
    private static final int f12857a = 1;

    /* renamed from: b  reason: collision with root package name */
    private String f12858b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(String str) {
        this.f12858b = str;
    }

    @Override // com.bytedance.sdk.account.platform.api.k
    public k.a authorize(Activity activity, com.bytedance.sdk.account.platform.base.b bVar) {
        activity.startActivityForResult(LineLoginApi.getLoginIntent(activity, this.f12858b), 1);
        return new a(bVar, null);
    }

    /* compiled from: LineServiceImpl.java */
    /* loaded from: classes2.dex */
    private static class a implements k.a {

        /* renamed from: a  reason: collision with root package name */
        com.bytedance.sdk.account.platform.base.b f12860a;

        /* synthetic */ a(com.bytedance.sdk.account.platform.base.b bVar, AnonymousClass1 anonymousClass1) {
            this(bVar);
        }

        private a(com.bytedance.sdk.account.platform.base.b bVar) {
            this.f12860a = bVar;
        }

        @Override // com.bytedance.sdk.account.platform.base.a
        public void onActivityResult(int i2, int i3, Intent intent) {
            if (i2 == 1) {
                LineLoginResult loginResultFromIntent = LineLoginApi.getLoginResultFromIntent(intent);
                int i4 = AnonymousClass1.f12859a[loginResultFromIntent.getResponseCode().ordinal()];
                if (i4 != 1) {
                    if (i4 == 2) {
                        this.f12860a.onError(new d(true));
                        return;
                    } else {
                        this.f12860a.onError(new d(loginResultFromIntent.getErrorData().getMessage()));
                        return;
                    }
                }
                String accessToken = loginResultFromIntent.getLineCredential().getAccessToken().getAccessToken();
                Bundle bundle = new Bundle();
                bundle.putString("access_token", accessToken);
                this.f12860a.onSuccess(bundle);
            }
        }
    }

    /* compiled from: LineServiceImpl.java */
    /* renamed from: com.bytedance.sdk.account.platform.line.b$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f12859a;

        static {
            int[] iArr = new int[LineApiResponseCode.values().length];
            f12859a = iArr;
            try {
                iArr[LineApiResponseCode.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f12859a[LineApiResponseCode.CANCEL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
