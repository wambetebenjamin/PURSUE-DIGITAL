package okhttp3.internal.http;

import com.bytedance.retrofit2.HttpMethodContrants;
/* loaded from: classes10.dex */
public final class HttpMethod {
    public static boolean invalidatesCache(String str) {
        return str.equals("POST") || str.equals(HttpMethodContrants.PATCH) || str.equals(HttpMethodContrants.PUT) || str.equals(HttpMethodContrants.DELETE) || str.equals("MOVE");
    }

    public static boolean requiresRequestBody(String str) {
        return str.equals("POST") || str.equals(HttpMethodContrants.PUT) || str.equals(HttpMethodContrants.PATCH) || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    public static boolean permitsRequestBody(String str) {
        return (str.equals("GET") || str.equals(HttpMethodContrants.HEAD)) ? false : true;
    }

    public static boolean redirectsWithBody(String str) {
        return str.equals("PROPFIND");
    }

    public static boolean redirectsToGet(String str) {
        return !str.equals("PROPFIND");
    }

    private HttpMethod() {
    }
}
