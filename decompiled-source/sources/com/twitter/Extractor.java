package com.twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
/* loaded from: classes6.dex */
public class Extractor {
    protected boolean extractURLWithoutProtocol = true;

    /* loaded from: classes6.dex */
    public static class Entity {
        protected String displayURL;
        protected int end;
        protected String expandedURL;
        protected final String listSlug;
        protected int start;
        protected final Type type;
        protected final String value;

        /* loaded from: classes6.dex */
        public enum Type {
            URL,
            HASHTAG,
            MENTION,
            CASHTAG
        }

        public Entity(int i2, int i3, String str, String str2, Type type) {
            this.displayURL = null;
            this.expandedURL = null;
            this.start = i2;
            this.end = i3;
            this.value = str;
            this.listSlug = str2;
            this.type = type;
        }

        public Entity(int i2, int i3, String str, Type type) {
            this(i2, i3, str, null, type);
        }

        public Entity(Matcher matcher, Type type, int i2) {
            this(matcher, type, i2, -1);
        }

        public Entity(Matcher matcher, Type type, int i2, int i3) {
            this(matcher.start(i2) + i3, matcher.end(i2), matcher.group(i2), type);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Entity) {
                Entity entity = (Entity) obj;
                return this.type.equals(entity.type) && this.start == entity.start && this.end == entity.end && this.value.equals(entity.value);
            }
            return false;
        }

        public int hashCode() {
            return this.type.hashCode() + this.value.hashCode() + this.start + this.end;
        }

        public String toString() {
            return this.value + "(" + this.type + ") [" + this.start + "," + this.end + "]";
        }

        public Integer getStart() {
            return Integer.valueOf(this.start);
        }

        public Integer getEnd() {
            return Integer.valueOf(this.end);
        }

        public String getValue() {
            return this.value;
        }

        public String getListSlug() {
            return this.listSlug;
        }

        public Type getType() {
            return this.type;
        }

        public String getDisplayURL() {
            return this.displayURL;
        }

        public void setDisplayURL(String str) {
            this.displayURL = str;
        }

        public String getExpandedURL() {
            return this.expandedURL;
        }

        public void setExpandedURL(String str) {
            this.expandedURL = str;
        }
    }

    private void removeOverlappingEntities(List<Entity> list) {
        Collections.sort(list, new Comparator<Entity>() { // from class: com.twitter.Extractor.1
            @Override // java.util.Comparator
            public int compare(Entity entity, Entity entity2) {
                return entity.start - entity2.start;
            }
        });
        if (list.isEmpty()) {
            return;
        }
        Iterator<Entity> it = list.iterator();
        Entity next = it.next();
        while (it.hasNext()) {
            Entity next2 = it.next();
            if (next.getEnd().intValue() > next2.getStart().intValue()) {
                it.remove();
            } else {
                next = next2;
            }
        }
    }

    public List<Entity> extractEntitiesWithIndices(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(extractURLsWithIndices(str));
        arrayList.addAll(extractHashtagsWithIndices(str, false));
        arrayList.addAll(extractMentionsOrListsWithIndices(str));
        arrayList.addAll(extractCashtagsWithIndices(str));
        removeOverlappingEntities(arrayList);
        return arrayList;
    }

    public List<String> extractMentionedScreennames(String str) {
        if (str == null || str.length() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Entity entity : extractMentionedScreennamesWithIndices(str)) {
            arrayList.add(entity.value);
        }
        return arrayList;
    }

    public List<Entity> extractMentionedScreennamesWithIndices(String str) {
        ArrayList arrayList = new ArrayList();
        for (Entity entity : extractMentionsOrListsWithIndices(str)) {
            if (entity.listSlug == null) {
                arrayList.add(entity);
            }
        }
        return arrayList;
    }

    public List<Entity> extractMentionsOrListsWithIndices(String str) {
        char[] charArray;
        if (str == null || str.length() == 0) {
            return Collections.emptyList();
        }
        boolean z = false;
        for (char c2 : str.toCharArray()) {
            if (c2 == '@' || c2 == 65312) {
                z = true;
                break;
            }
        }
        if (!z) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Regex.VALID_MENTION_OR_LIST.matcher(str);
        while (matcher.find()) {
            if (!Regex.INVALID_MENTION_MATCH_END.matcher(str.substring(matcher.end())).find()) {
                if (matcher.group(4) == null) {
                    arrayList.add(new Entity(matcher, Entity.Type.MENTION, 3));
                } else {
                    arrayList.add(new Entity(matcher.start(3) - 1, matcher.end(4), matcher.group(3), matcher.group(4), Entity.Type.MENTION));
                }
            }
        }
        return arrayList;
    }

    public String extractReplyScreenname(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = Regex.VALID_REPLY.matcher(str);
        if (matcher.find()) {
            if (Regex.INVALID_MENTION_MATCH_END.matcher(str.substring(matcher.end())).find()) {
                return null;
            }
            return matcher.group(1);
        }
        return null;
    }

    public List<String> extractURLs(String str) {
        if (str == null || str.length() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Entity entity : extractURLsWithIndices(str)) {
            arrayList.add(entity.value);
        }
        return arrayList;
    }

    public List<Entity> extractURLsWithIndices(String str) {
        if (str != null && str.length() != 0) {
            if (str.indexOf(this.extractURLWithoutProtocol ? 46 : 58) != -1) {
                ArrayList arrayList = new ArrayList();
                Matcher matcher = Regex.VALID_URL.matcher(str);
                while (matcher.find()) {
                    if (matcher.group(4) != null || (this.extractURLWithoutProtocol && !Regex.INVALID_URL_WITHOUT_PROTOCOL_MATCH_BEGIN.matcher(matcher.group(2)).matches())) {
                        String group = matcher.group(3);
                        int start = matcher.start(3);
                        int end = matcher.end(3);
                        Matcher matcher2 = Regex.VALID_TCO_URL.matcher(group);
                        if (matcher2.find()) {
                            group = matcher2.group();
                            end = group.length() + start;
                        }
                        arrayList.add(new Entity(start, end, group, Entity.Type.URL));
                    }
                }
                return arrayList;
            }
        }
        return Collections.emptyList();
    }

    public List<String> extractHashtags(String str) {
        if (str == null || str.length() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Entity entity : extractHashtagsWithIndices(str)) {
            arrayList.add(entity.value);
        }
        return arrayList;
    }

    public List<Entity> extractHashtagsWithIndices(String str) {
        return extractHashtagsWithIndices(str, true);
    }

    private List<Entity> extractHashtagsWithIndices(String str, boolean z) {
        char[] charArray;
        if (str == null || str.length() == 0) {
            return Collections.emptyList();
        }
        boolean z2 = false;
        for (char c2 : str.toCharArray()) {
            if (c2 == '#' || c2 == 65283) {
                z2 = true;
                break;
            }
        }
        if (!z2) {
            return Collections.emptyList();
        }
        List<Entity> arrayList = new ArrayList<>();
        Matcher matcher = Regex.VALID_HASHTAG.matcher(str);
        while (matcher.find()) {
            if (!Regex.INVALID_HASHTAG_MATCH_END.matcher(str.substring(matcher.end())).find()) {
                arrayList.add(new Entity(matcher, Entity.Type.HASHTAG, 3));
            }
        }
        if (z) {
            List<Entity> extractURLsWithIndices = extractURLsWithIndices(str);
            if (!extractURLsWithIndices.isEmpty()) {
                arrayList.addAll(extractURLsWithIndices);
                removeOverlappingEntities(arrayList);
                Iterator<Entity> it = arrayList.iterator();
                while (it.hasNext()) {
                    if (it.next().getType() != Entity.Type.HASHTAG) {
                        it.remove();
                    }
                }
            }
        }
        return arrayList;
    }

    public List<String> extractCashtags(String str) {
        if (str == null || str.length() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Entity entity : extractCashtagsWithIndices(str)) {
            arrayList.add(entity.value);
        }
        return arrayList;
    }

    public List<Entity> extractCashtagsWithIndices(String str) {
        if (str == null || str.length() == 0) {
            return Collections.emptyList();
        }
        if (str.indexOf(36) == -1) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Regex.VALID_CASHTAG.matcher(str);
        while (matcher.find()) {
            arrayList.add(new Entity(matcher, Entity.Type.CASHTAG, 3));
        }
        return arrayList;
    }

    public void setExtractURLWithoutProtocol(boolean z) {
        this.extractURLWithoutProtocol = z;
    }

    public boolean isExtractURLWithoutProtocol() {
        return this.extractURLWithoutProtocol;
    }

    public void modifyIndicesFromUnicodeToUTF16(String str, List<Entity> list) {
        IndexConverter indexConverter = new IndexConverter(str);
        for (Entity entity : list) {
            entity.start = indexConverter.codePointsToCodeUnits(entity.start);
            entity.end = indexConverter.codePointsToCodeUnits(entity.end);
        }
    }

    public void modifyIndicesFromUTF16ToToUnicode(String str, List<Entity> list) {
        IndexConverter indexConverter = new IndexConverter(str);
        for (Entity entity : list) {
            entity.start = indexConverter.codeUnitsToCodePoints(entity.start);
            entity.end = indexConverter.codeUnitsToCodePoints(entity.end);
        }
    }

    /* loaded from: classes6.dex */
    private static final class IndexConverter {
        protected final String text;
        protected int codePointIndex = 0;
        protected int charIndex = 0;

        IndexConverter(String str) {
            this.text = str;
        }

        int codeUnitsToCodePoints(int i2) {
            int i3 = this.charIndex;
            if (i2 < i3) {
                this.codePointIndex -= this.text.codePointCount(i2, i3);
            } else {
                this.codePointIndex += this.text.codePointCount(i3, i2);
            }
            this.charIndex = i2;
            if (i2 > 0 && Character.isSupplementaryCodePoint(this.text.codePointAt(i2 - 1))) {
                this.charIndex--;
            }
            return this.codePointIndex;
        }

        int codePointsToCodeUnits(int i2) {
            int offsetByCodePoints = this.text.offsetByCodePoints(this.charIndex, i2 - this.codePointIndex);
            this.charIndex = offsetByCodePoints;
            this.codePointIndex = i2;
            return offsetByCodePoints;
        }
    }
}
