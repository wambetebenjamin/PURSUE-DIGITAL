package com.twitter;

import com.facebook.share.internal.ShareConstants;
import com.twitter.Extractor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public class Autolink {
    public static final String DEFAULT_CASHTAG_CLASS = "tweet-url cashtag";
    public static final String DEFAULT_CASHTAG_URL_BASE = "https://twitter.com/#!/search?q=%24";
    public static final String DEFAULT_HASHTAG_CLASS = "tweet-url hashtag";
    public static final String DEFAULT_HASHTAG_URL_BASE = "https://twitter.com/#!/search?q=%23";
    public static final String DEFAULT_INVISIBLE_TAG_ATTRS = "style='position:absolute;left:-9999px;'";
    public static final String DEFAULT_LIST_CLASS = "tweet-url list-slug";
    public static final String DEFAULT_LIST_URL_BASE = "https://twitter.com/";
    public static final String DEFAULT_USERNAME_CLASS = "tweet-url username";
    public static final String DEFAULT_USERNAME_URL_BASE = "https://twitter.com/";
    protected String cashtagClass;
    protected String cashtagUrlBase;
    private Extractor extractor;
    protected String hashtagClass;
    protected String hashtagUrlBase;
    protected String invisibleTagAttrs;
    protected LinkAttributeModifier linkAttributeModifier;
    protected LinkTextModifier linkTextModifier;
    protected String listClass;
    protected String listUrlBase;
    protected boolean noFollow;
    protected String symbolTag;
    protected String textWithSymbolTag;
    protected String urlClass;
    protected String urlTarget;
    protected String usernameClass;
    protected boolean usernameIncludeSymbol;
    protected String usernameUrlBase;

    /* loaded from: classes6.dex */
    public interface LinkAttributeModifier {
        void modify(Extractor.Entity entity, Map<String, String> map);
    }

    /* loaded from: classes6.dex */
    public interface LinkTextModifier {
        CharSequence modify(Extractor.Entity entity, CharSequence charSequence);
    }

    private static CharSequence escapeHTML(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(charSequence.length() * 2);
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char charAt = charSequence.charAt(i2);
            if (charAt == '\"') {
                sb.append("&quot;");
            } else if (charAt == '<') {
                sb.append("&lt;");
            } else if (charAt == '>') {
                sb.append("&gt;");
            } else if (charAt == '&') {
                sb.append("&amp;");
            } else if (charAt == '\'') {
                sb.append("&#39;");
            } else {
                sb.append(charAt);
            }
        }
        return sb;
    }

    public Autolink() {
        this(true);
    }

    public Autolink(boolean z) {
        this.urlClass = null;
        this.noFollow = true;
        this.usernameIncludeSymbol = false;
        this.symbolTag = null;
        this.textWithSymbolTag = null;
        this.urlTarget = null;
        this.linkAttributeModifier = null;
        this.linkTextModifier = null;
        Extractor extractor = new Extractor();
        this.extractor = extractor;
        this.urlClass = null;
        this.listClass = DEFAULT_LIST_CLASS;
        this.usernameClass = DEFAULT_USERNAME_CLASS;
        this.hashtagClass = DEFAULT_HASHTAG_CLASS;
        this.cashtagClass = DEFAULT_CASHTAG_CLASS;
        this.usernameUrlBase = "https://twitter.com/";
        this.listUrlBase = "https://twitter.com/";
        this.hashtagUrlBase = DEFAULT_HASHTAG_URL_BASE;
        this.cashtagUrlBase = DEFAULT_CASHTAG_URL_BASE;
        this.invisibleTagAttrs = DEFAULT_INVISIBLE_TAG_ATTRS;
        extractor.setExtractURLWithoutProtocol(false);
        this.noFollow = z;
    }

    public String escapeBrackets(String str) {
        int length = str.length();
        if (length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(length + 16);
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '>') {
                sb.append("&gt;");
            } else if (charAt == '<') {
                sb.append("&lt;");
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    public void linkToText(Extractor.Entity entity, CharSequence charSequence, Map<String, String> map, StringBuilder sb) {
        if (this.noFollow) {
            map.put("rel", "nofollow");
        }
        LinkAttributeModifier linkAttributeModifier = this.linkAttributeModifier;
        if (linkAttributeModifier != null) {
            linkAttributeModifier.modify(entity, map);
        }
        LinkTextModifier linkTextModifier = this.linkTextModifier;
        if (linkTextModifier != null) {
            charSequence = linkTextModifier.modify(entity, charSequence);
        }
        sb.append("<a");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(" ").append(escapeHTML(entry.getKey())).append("=\"").append(escapeHTML(entry.getValue())).append("\"");
        }
        sb.append(">").append(charSequence).append("</a>");
    }

    public void linkToTextWithSymbol(Extractor.Entity entity, CharSequence charSequence, CharSequence charSequence2, Map<String, String> map, StringBuilder sb) {
        CharSequence charSequence3;
        String str = this.symbolTag;
        boolean z = true;
        if (str == null || str.length() == 0) {
            charSequence3 = charSequence;
        } else {
            String str2 = this.symbolTag;
            charSequence3 = String.format("<%s>%s</%s>", str2, charSequence, str2);
        }
        CharSequence escapeHTML = escapeHTML(charSequence2);
        String str3 = this.textWithSymbolTag;
        if (str3 != null && str3.length() != 0) {
            String str4 = this.textWithSymbolTag;
            escapeHTML = String.format("<%s>%s</%s>", str4, escapeHTML, str4);
        }
        if (!this.usernameIncludeSymbol && Regex.AT_SIGNS.matcher(charSequence).matches()) {
            z = false;
        }
        if (z) {
            linkToText(entity, charSequence3.toString() + ((Object) escapeHTML), map, sb);
            return;
        }
        sb.append(charSequence3);
        linkToText(entity, escapeHTML, map, sb);
    }

    public void linkToHashtag(Extractor.Entity entity, String str, StringBuilder sb) {
        CharSequence subSequence = str.subSequence(entity.getStart().intValue(), entity.getStart().intValue() + 1);
        String value = entity.getValue();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(ShareConstants.WEB_DIALOG_PARAM_HREF, this.hashtagUrlBase + ((Object) value));
        linkedHashMap.put("title", "#" + ((Object) value));
        if (Regex.RTL_CHARACTERS.matcher(str).find()) {
            linkedHashMap.put("class", this.hashtagClass + " rtl");
        } else {
            linkedHashMap.put("class", this.hashtagClass);
        }
        linkToTextWithSymbol(entity, subSequence, value, linkedHashMap, sb);
    }

    public void linkToCashtag(Extractor.Entity entity, String str, StringBuilder sb) {
        String value = entity.getValue();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(ShareConstants.WEB_DIALOG_PARAM_HREF, this.cashtagUrlBase + ((Object) value));
        linkedHashMap.put("title", "$" + ((Object) value));
        linkedHashMap.put("class", this.cashtagClass);
        linkToTextWithSymbol(entity, "$", value, linkedHashMap, sb);
    }

    public void linkToMentionAndList(Extractor.Entity entity, String str, StringBuilder sb) {
        String value = entity.getValue();
        CharSequence subSequence = str.subSequence(entity.getStart().intValue(), entity.getStart().intValue() + 1);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (entity.listSlug != null) {
            value = value + entity.listSlug;
            linkedHashMap.put("class", this.listClass);
            linkedHashMap.put(ShareConstants.WEB_DIALOG_PARAM_HREF, this.listUrlBase + value);
        } else {
            linkedHashMap.put("class", this.usernameClass);
            linkedHashMap.put(ShareConstants.WEB_DIALOG_PARAM_HREF, this.usernameUrlBase + value);
        }
        linkToTextWithSymbol(entity, subSequence, value, linkedHashMap, sb);
    }

    public void linkToURL(Extractor.Entity entity, String str, StringBuilder sb) {
        String value = entity.getValue();
        CharSequence escapeHTML = escapeHTML(value);
        if (entity.displayURL != null && entity.expandedURL != null) {
            String replace = entity.displayURL.replace("…", "");
            int indexOf = entity.expandedURL.indexOf(replace);
            if (indexOf != -1) {
                String substring = entity.expandedURL.substring(0, indexOf);
                String substring2 = entity.expandedURL.substring(indexOf + replace.length());
                String str2 = entity.displayURL.startsWith("…") ? "…" : "";
                String str3 = entity.displayURL.endsWith("…") ? "…" : "";
                String str4 = "<span " + this.invisibleTagAttrs + ">";
                StringBuilder sb2 = new StringBuilder("<span class='tco-ellipsis'>");
                sb2.append(str2);
                sb2.append(str4).append("&nbsp;</span></span>");
                sb2.append(str4).append(escapeHTML(substring)).append("</span>");
                sb2.append("<span class='js-display-url'>").append(escapeHTML(replace)).append("</span>");
                sb2.append(str4).append(escapeHTML(substring2)).append("</span>");
                sb2.append("<span class='tco-ellipsis'>").append(str4).append("&nbsp;</span>").append(str3).append("</span>");
                escapeHTML = sb2;
            } else {
                escapeHTML = entity.displayURL;
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(ShareConstants.WEB_DIALOG_PARAM_HREF, value.toString());
        String str5 = this.urlClass;
        if (str5 != null) {
            linkedHashMap.put("class", str5);
        }
        String str6 = this.urlClass;
        if (str6 != null && str6.length() != 0) {
            linkedHashMap.put("class", this.urlClass);
        }
        String str7 = this.urlTarget;
        if (str7 != null && str7.length() != 0) {
            linkedHashMap.put("target", this.urlTarget);
        }
        linkToText(entity, escapeHTML, linkedHashMap, sb);
    }

    public String autoLinkEntities(String str, List<Extractor.Entity> list) {
        StringBuilder sb = new StringBuilder(str.length() * 2);
        int i2 = 0;
        for (Extractor.Entity entity : list) {
            sb.append(str.subSequence(i2, entity.start));
            int i3 = AnonymousClass1.$SwitchMap$com$twitter$Extractor$Entity$Type[entity.type.ordinal()];
            if (i3 == 1) {
                linkToURL(entity, str, sb);
            } else if (i3 == 2) {
                linkToHashtag(entity, str, sb);
            } else if (i3 == 3) {
                linkToMentionAndList(entity, str, sb);
            } else if (i3 == 4) {
                linkToCashtag(entity, str, sb);
            }
            i2 = entity.end;
        }
        sb.append(str.subSequence(i2, str.length()));
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.twitter.Autolink$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$twitter$Extractor$Entity$Type;

        static {
            int[] iArr = new int[Extractor.Entity.Type.values().length];
            $SwitchMap$com$twitter$Extractor$Entity$Type = iArr;
            try {
                iArr[Extractor.Entity.Type.URL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$twitter$Extractor$Entity$Type[Extractor.Entity.Type.HASHTAG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$twitter$Extractor$Entity$Type[Extractor.Entity.Type.MENTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$twitter$Extractor$Entity$Type[Extractor.Entity.Type.CASHTAG.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public String autoLink(String str) {
        String escapeBrackets = escapeBrackets(str);
        return autoLinkEntities(escapeBrackets, this.extractor.extractEntitiesWithIndices(escapeBrackets));
    }

    public String autoLinkUsernamesAndLists(String str) {
        return autoLinkEntities(str, this.extractor.extractMentionsOrListsWithIndices(str));
    }

    public String autoLinkHashtags(String str) {
        return autoLinkEntities(str, this.extractor.extractHashtagsWithIndices(str));
    }

    public String autoLinkURLs(String str) {
        return autoLinkEntities(str, this.extractor.extractURLsWithIndices(str));
    }

    public String autoLinkCashtags(String str) {
        return autoLinkEntities(str, this.extractor.extractCashtagsWithIndices(str));
    }

    public String getUrlClass() {
        return this.urlClass;
    }

    public void setUrlClass(String str) {
        this.urlClass = str;
    }

    public String getListClass() {
        return this.listClass;
    }

    public void setListClass(String str) {
        this.listClass = str;
    }

    public String getUsernameClass() {
        return this.usernameClass;
    }

    public void setUsernameClass(String str) {
        this.usernameClass = str;
    }

    public String getHashtagClass() {
        return this.hashtagClass;
    }

    public void setHashtagClass(String str) {
        this.hashtagClass = str;
    }

    public String getCashtagClass() {
        return this.cashtagClass;
    }

    public void setCashtagClass(String str) {
        this.cashtagClass = str;
    }

    public String getUsernameUrlBase() {
        return this.usernameUrlBase;
    }

    public void setUsernameUrlBase(String str) {
        this.usernameUrlBase = str;
    }

    public String getListUrlBase() {
        return this.listUrlBase;
    }

    public void setListUrlBase(String str) {
        this.listUrlBase = str;
    }

    public String getHashtagUrlBase() {
        return this.hashtagUrlBase;
    }

    public void setHashtagUrlBase(String str) {
        this.hashtagUrlBase = str;
    }

    public String getCashtagUrlBase() {
        return this.cashtagUrlBase;
    }

    public void setCashtagUrlBase(String str) {
        this.cashtagUrlBase = str;
    }

    public boolean isNoFollow() {
        return this.noFollow;
    }

    public void setNoFollow(boolean z) {
        this.noFollow = z;
    }

    public void setUsernameIncludeSymbol(boolean z) {
        this.usernameIncludeSymbol = z;
    }

    public void setSymbolTag(String str) {
        this.symbolTag = str;
    }

    public void setTextWithSymbolTag(String str) {
        this.textWithSymbolTag = str;
    }

    public void setUrlTarget(String str) {
        this.urlTarget = str;
    }

    public void setLinkAttributeModifier(LinkAttributeModifier linkAttributeModifier) {
        this.linkAttributeModifier = linkAttributeModifier;
    }

    public void setLinkTextModifier(LinkTextModifier linkTextModifier) {
        this.linkTextModifier = linkTextModifier;
    }
}
