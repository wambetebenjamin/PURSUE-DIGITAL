package com.twitter;

import com.bytedance.librarian.c;
import java.text.StringCharacterIterator;
import java.util.List;
/* loaded from: classes6.dex */
public class HitHighlighter {
    public static final String DEFAULT_HIGHLIGHT_TAG = "em";
    protected String highlightTag = "em";

    public String highlight(String str, List<List<Integer>> list) {
        if (list == null || list.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        StringCharacterIterator stringCharacterIterator = new StringCharacterIterator(str);
        boolean z = false;
        int i2 = 0;
        boolean z2 = true;
        for (char first = stringCharacterIterator.first(); first != 65535; first = stringCharacterIterator.next()) {
            for (List<Integer> list2 : list) {
                if (list2.get(0).intValue() == i2) {
                    sb.append(tag(false));
                    z = true;
                } else if (list2.get(1).intValue() == i2) {
                    sb.append(tag(true));
                    z = false;
                }
            }
            if (first == '<') {
                z2 = false;
            } else if (first == '>' && !z2) {
                z2 = true;
            }
            if (z2) {
                i2++;
            }
            sb.append(first);
        }
        if (z) {
            sb.append(tag(true));
        }
        return sb.toString();
    }

    protected String tag(boolean z) {
        StringBuilder sb = new StringBuilder(this.highlightTag.length() + 3);
        sb.append("<");
        if (z) {
            sb.append(c.a.SEPARATOR);
        }
        sb.append(this.highlightTag).append(">");
        return sb.toString();
    }

    public String getHighlightTag() {
        return this.highlightTag;
    }

    public void setHighlightTag(String str) {
        this.highlightTag = str;
    }
}
