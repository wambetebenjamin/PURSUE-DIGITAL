package com.google.zxing.client.result;

import com.just.agentweb.DefaultWebClient;
import java.util.regex.Pattern;
/* loaded from: classes9.dex */
public final class URIParsedResult extends ParsedResult {
    private static final Pattern USER_IN_HOST = Pattern.compile(":/*([^/@]+)@[^/]+");
    private final String title;
    private final String uri;

    public URIParsedResult(String str, String str2) {
        super(ParsedResultType.URI);
        this.uri = massageURI(str);
        this.title = str2;
    }

    public String getURI() {
        return this.uri;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isPossiblyMaliciousURI() {
        return USER_IN_HOST.matcher(this.uri).find();
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(30);
        maybeAppend(this.title, sb);
        maybeAppend(this.uri, sb);
        return sb.toString();
    }

    private static String massageURI(String str) {
        String trim = str.trim();
        int indexOf = trim.indexOf(58);
        return (indexOf < 0 || isColonFollowedByPortNumber(trim, indexOf)) ? DefaultWebClient.HTTP_SCHEME.concat(String.valueOf(trim)) : trim;
    }

    private static boolean isColonFollowedByPortNumber(String str, int i2) {
        int i3 = i2 + 1;
        int indexOf = str.indexOf(47, i3);
        if (indexOf < 0) {
            indexOf = str.length();
        }
        return ResultParser.isSubstringOfDigits(str, i3, indexOf - i3);
    }
}
