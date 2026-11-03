package net.openid.appauth;

import java.util.Collections;
import java.util.Map;
/* loaded from: classes10.dex */
public class NoClientAuthentication implements ClientAuthentication {
    public static final NoClientAuthentication INSTANCE = new NoClientAuthentication();
    public static final String NAME = "none";

    @Override // net.openid.appauth.ClientAuthentication
    public Map<String, String> getRequestHeaders(String str) {
        return null;
    }

    private NoClientAuthentication() {
    }

    @Override // net.openid.appauth.ClientAuthentication
    public Map<String, String> getRequestParameters(String str) {
        return Collections.singletonMap("client_id", str);
    }
}
