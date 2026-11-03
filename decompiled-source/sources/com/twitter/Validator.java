package com.twitter;

import com.twitter.Extractor;
import java.text.Normalizer;
/* loaded from: classes6.dex */
public class Validator {
    public static final int MAX_TWEET_LENGTH = 140;
    protected int shortUrlLength = 23;
    protected int shortUrlLengthHttps = 23;
    private Extractor extractor = new Extractor();

    public int getTweetLength(String str) {
        String normalize = Normalizer.normalize(str, Normalizer.Form.NFC);
        int codePointCount = normalize.codePointCount(0, normalize.length());
        for (Extractor.Entity entity : this.extractor.extractURLsWithIndices(normalize)) {
            codePointCount = codePointCount + (entity.start - entity.end) + (entity.value.toLowerCase().startsWith("https://") ? this.shortUrlLengthHttps : this.shortUrlLength);
        }
        return codePointCount;
    }

    public boolean isValidTweet(String str) {
        char[] charArray;
        if (str == null || str.length() == 0) {
            return false;
        }
        for (char c2 : str.toCharArray()) {
            if (c2 == 65534 || c2 == 65279 || c2 == 65535 || (c2 >= 8234 && c2 <= 8238)) {
                return false;
            }
        }
        return getTweetLength(str) <= 140;
    }

    public int getShortUrlLength() {
        return this.shortUrlLength;
    }

    public void setShortUrlLength(int i2) {
        this.shortUrlLength = i2;
    }

    public int getShortUrlLengthHttps() {
        return this.shortUrlLengthHttps;
    }

    public void setShortUrlLengthHttps(int i2) {
        this.shortUrlLengthHttps = i2;
    }
}
