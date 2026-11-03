package com.linecorp.linesdk.api;

import com.linecorp.linesdk.LineAccessToken;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.LineCredential;
import com.linecorp.linesdk.LineProfile;
/* loaded from: classes9.dex */
public interface LineApiClient {
    LineApiResponse<LineAccessToken> getCurrentAccessToken();

    LineApiResponse<LineProfile> getProfile();

    LineApiResponse<?> logout();

    LineApiResponse<LineAccessToken> refreshAccessToken();

    LineApiResponse<LineCredential> verifyToken();
}
