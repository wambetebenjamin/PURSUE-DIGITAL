package com.bytedance.sdk.open.tiktok.authorize.model;

import android.os.Bundle;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.tiktok.common.model.BaseReq;
import com.bytedance.sdk.open.tiktok.common.model.BaseResp;
/* loaded from: classes3.dex */
public class Authorization {

    /* loaded from: classes3.dex */
    public static class Request extends BaseReq {
        public String clientKey;
        public String optionalScope0;
        public String optionalScope1;
        public String redirectUri;
        public String scope;
        public String state;

        @Override // com.bytedance.sdk.open.tiktok.common.model.BaseReq
        public int getType() {
            return 1;
        }

        public String getClientKey() {
            return this.clientKey;
        }

        public Request() {
        }

        public Request(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.bytedance.sdk.open.tiktok.common.model.BaseReq
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.state = bundle.getString(ParamKeyConstants.AuthParams.STATE);
            this.clientKey = bundle.getString(ParamKeyConstants.AuthParams.CLIENT_KEY);
            this.redirectUri = bundle.getString(ParamKeyConstants.AuthParams.REDIRECT_URI);
            this.scope = bundle.getString(ParamKeyConstants.AuthParams.SCOPE);
            this.optionalScope0 = bundle.getString(ParamKeyConstants.AuthParams.OPTIONAL_SCOPE0);
            this.optionalScope1 = bundle.getString(ParamKeyConstants.AuthParams.OPTIONAL_SCOPE1);
        }

        @Override // com.bytedance.sdk.open.tiktok.common.model.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString(ParamKeyConstants.AuthParams.STATE, this.state);
            bundle.putString(ParamKeyConstants.AuthParams.CLIENT_KEY, this.clientKey);
            bundle.putString(ParamKeyConstants.AuthParams.REDIRECT_URI, this.redirectUri);
            bundle.putString(ParamKeyConstants.AuthParams.SCOPE, this.scope);
            bundle.putString(ParamKeyConstants.AuthParams.OPTIONAL_SCOPE0, this.optionalScope0);
            bundle.putString(ParamKeyConstants.AuthParams.OPTIONAL_SCOPE1, this.optionalScope1);
        }
    }

    /* loaded from: classes3.dex */
    public static class Response extends BaseResp {
        public String authCode;
        public String grantedPermissions;
        public String state;

        @Override // com.bytedance.sdk.open.tiktok.common.model.BaseResp
        public int getType() {
            return 2;
        }

        public Response() {
        }

        public Response(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.bytedance.sdk.open.tiktok.common.model.BaseResp
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.authCode = bundle.getString(ParamKeyConstants.AuthParams.AUTH_CODE);
            this.state = bundle.getString(ParamKeyConstants.AuthParams.STATE);
            this.grantedPermissions = bundle.getString(ParamKeyConstants.AuthParams.GRANTED_PERMISSION);
        }

        @Override // com.bytedance.sdk.open.tiktok.common.model.BaseResp
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString(ParamKeyConstants.AuthParams.AUTH_CODE, this.authCode);
            bundle.putString(ParamKeyConstants.AuthParams.STATE, this.state);
            bundle.putString(ParamKeyConstants.AuthParams.GRANTED_PERMISSION, this.grantedPermissions);
        }
    }
}
