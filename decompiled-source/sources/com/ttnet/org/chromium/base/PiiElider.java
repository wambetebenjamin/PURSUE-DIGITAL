package com.ttnet.org.chromium.base;

import android.text.TextUtils;
import android.util.Patterns;
import com.bytedance.librarian.c;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes9.dex */
public class PiiElider {
    private static final String CONSOLE_ELISION = "[ELIDED:CONSOLE(0)] ELIDED CONSOLE MESSAGE";
    private static final String DOMAIN_NAME = "(([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef-]{0,61}[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]){0,1}\\.)+[a-zA-Z -\ud7ff豈-﷏ﷰ-\uffef]{2,63}|((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])))";
    private static final String EMAIL_ELISION = "XXX@EMAIL.ELIDED";
    private static final String GOOD_GTLD_CHAR = "a-zA-Z -\ud7ff豈-﷏ﷰ-\uffef";
    private static final String GOOD_IRI_CHAR = "a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef";
    private static final String GTLD = "[a-zA-Z -\ud7ff豈-﷏ﷰ-\uffef]{2,63}";
    private static final String HOST_NAME = "([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef-]{0,61}[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]){0,1}\\.)+[a-zA-Z -\ud7ff豈-﷏ﷰ-\uffef]{2,63}";
    private static final String INTENT = "([a-zA-Z][a-zA-Z0-9+.-]+://((([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef;/?:@&=#~.+!*'(),_-])|(%[a-fA-F0-9]{2}))+))";
    private static final String INTENT_SCHEME = "[a-zA-Z][a-zA-Z0-9+.-]+://";
    private static final String IP_ADDRESS = "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9]))";
    private static final String IP_ELISION = "1.2.3.4";
    private static final String IRI = "[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef-]{0,61}[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]){0,1}";
    private static final String MAC_ELISION = "01:23:45:67:89:AB";
    private static final String PATH_CHAR = "(([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef;/?:@&=#~.+!*'(),_-])|(%[a-fA-F0-9]{2}))";
    private static final String PATH_COMPONENT = "((([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef;/?:@&=#~.+!*'(),_-])|(%[a-fA-F0-9]{2}))+)";
    private static final String PORT = "(:\\d{1,5})";
    private static final String URI_CHAR = "([a-zA-Z0-9$_.+!*'(),;?&=-]|(%[a-fA-F0-9]{2}))";
    private static final String URI_ENCODED_CHAR = "(%[a-fA-F0-9]{2})";
    private static final String URI_SCHEME = "((http|https|Http|Https|rtsp|Rtsp)://(([a-zA-Z0-9$_.+!*'(),;?&=-]|(%[a-fA-F0-9]{2})){1,64}(:([a-zA-Z0-9$_.+!*'(),;?&=-]|(%[a-fA-F0-9]{2})){1,25})?@)?)";
    private static final String URL_ELISION = "HTTP://WEBADDRESS.ELIDED";
    private static final String URL_OR_INTENT = "((((http|https|Http|Https|rtsp|Rtsp)://(([a-zA-Z0-9$_.+!*'(),;?&=-]|(%[a-fA-F0-9]{2})){1,64}(:([a-zA-Z0-9$_.+!*'(),;?&=-]|(%[a-fA-F0-9]{2})){1,25})?@)?)?(([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef-]{0,61}[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]){0,1}\\.)+[a-zA-Z -\ud7ff豈-﷏ﷰ-\uffef]{2,63}|((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])))(:\\d{1,5})?)|([a-zA-Z][a-zA-Z0-9+.-]+://((([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef;/?:@&=#~.+!*'(),_-])|(%[a-fA-F0-9]{2}))+)))";
    private static final String URL_WITH_OPTIONAL_SCHEME_AND_PORT = "(((http|https|Http|Https|rtsp|Rtsp)://(([a-zA-Z0-9$_.+!*'(),;?&=-]|(%[a-fA-F0-9]{2})){1,64}(:([a-zA-Z0-9$_.+!*'(),;?&=-]|(%[a-fA-F0-9]{2})){1,25})?@)?)?(([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef-]{0,61}[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]){0,1}\\.)+[a-zA-Z -\ud7ff豈-﷏ﷰ-\uffef]{2,63}|((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])))(:\\d{1,5})?)";
    private static final Pattern WEB_URL = Pattern.compile("(\\b|^)(((((http|https|Http|Https|rtsp|Rtsp)://(([a-zA-Z0-9$_.+!*'(),;?&=-]|(%[a-fA-F0-9]{2})){1,64}(:([a-zA-Z0-9$_.+!*'(),;?&=-]|(%[a-fA-F0-9]{2})){1,25})?@)?)?(([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef-]{0,61}[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]){0,1}\\.)+[a-zA-Z -\ud7ff豈-﷏ﷰ-\uffef]{2,63}|((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])))(:\\d{1,5})?)|([a-zA-Z][a-zA-Z0-9+.-]+://((([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef;/?:@&=#~.+!*'(),_-])|(%[a-fA-F0-9]{2}))+))))(/(([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef;/?:@&=#~.+!*'(),_-])|(%[a-fA-F0-9]{2}))*)?(\\b|$)");
    private static final Pattern LIKELY_EXCEPTION_LOG = Pattern.compile("\\sat\\sorg\\.chromium\\.[^ ]+.|Caused by: java.lang.(ClassNotFoundException|NoClassDefFoundError):");
    private static final Pattern MAC_ADDRESS = Pattern.compile("([0-9a-fA-F]{2}[-:]+){5}[0-9a-fA-F]{2}");
    private static final Pattern CONSOLE_MSG = Pattern.compile("\\[\\w*:CONSOLE.*\\].*");
    private static final String[] APP_NAMESPACE = {"com.ttnet.org.chromium.", "com.google.", "com.chrome."};
    private static final String[] SYSTEM_NAMESPACE = {"android.", "com.android.", "dalvik.", "java.", "javax.", "org.apache.", "org.json.", "org.w3c.dom.", "org.xml.", "org.xmlpull."};

    public static String elideEmail(String str) {
        try {
            return Patterns.EMAIL_ADDRESS.matcher(str).replaceAll(EMAIL_ELISION);
        } catch (IllegalArgumentException unused) {
            return "";
        }
    }

    public static String elideUrl(String str) {
        try {
            if (LIKELY_EXCEPTION_LOG.matcher(str).find()) {
                return str;
            }
            StringBuilder sb = new StringBuilder(str);
            Matcher matcher = WEB_URL.matcher(sb);
            int i2 = 0;
            while (matcher.find(i2)) {
                int start = matcher.start();
                int end = matcher.end();
                String substring = sb.substring(start, end);
                if (likelyToBeAppNamespace(substring) || likelyToBeSystemNamespace(substring) || likelyToBeClassOrMethodName(substring)) {
                    i2 = end;
                } else {
                    sb.replace(start, end, URL_ELISION);
                    i2 = start + 24;
                    matcher = WEB_URL.matcher(sb);
                }
            }
            return sb.toString();
        } catch (IllegalArgumentException unused) {
            return "";
        }
    }

    private static boolean likelyToBeClassOrMethodName(String str) {
        if (isClassName(str)) {
            return true;
        }
        int lastIndexOf = str.lastIndexOf(c.a.DOT);
        if (lastIndexOf == -1) {
            return false;
        }
        return isClassName(str.substring(0, lastIndexOf));
    }

    private static boolean isClassName(String str) {
        try {
            Class.forName(str, false, ContextUtils.getApplicationContext().getClassLoader());
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean likelyToBeAppNamespace(String str) {
        for (String str2 : APP_NAMESPACE) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    private static boolean likelyToBeSystemNamespace(String str) {
        for (String str2 : SYSTEM_NAMESPACE) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    public static String elideIp(String str) {
        try {
            return Patterns.IP_ADDRESS.matcher(str).replaceAll(IP_ELISION);
        } catch (IllegalArgumentException unused) {
            return "";
        }
    }

    public static String elideMac(String str) {
        try {
            return MAC_ADDRESS.matcher(str).replaceAll(MAC_ELISION);
        } catch (IllegalArgumentException unused) {
            return "";
        }
    }

    public static String elideConsole(String str) {
        try {
            return CONSOLE_MSG.matcher(str).replaceAll(CONSOLE_ELISION);
        } catch (IllegalArgumentException unused) {
            return "";
        }
    }

    public static String sanitizeStacktrace(String str) {
        String[] split = str.split("\\n");
        split[0] = elideUrl(split[0]);
        for (int i2 = 1; i2 < split.length; i2++) {
            if (split[i2].startsWith("Caused by:")) {
                split[i2] = elideUrl(split[i2]);
            }
        }
        return TextUtils.join(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, split);
    }
}
