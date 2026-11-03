package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.models.Tweet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes10.dex */
final class Utils {
    static CharSequence charSeqOrDefault(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence != null ? charSequence : charSequence2;
    }

    static String stringOrDefault(String str, String str2) {
        return str != null ? str : str2;
    }

    private Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Long numberOrDefault(String str, long j2) {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException unused) {
            return Long.valueOf(j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String stringOrEmpty(String str) {
        return stringOrDefault(str, "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharSequence charSeqOrEmpty(CharSequence charSequence) {
        return charSeqOrDefault(charSequence, "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Tweet> orderTweets(List<Long> list, List<Tweet> list2) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (Tweet tweet : list2) {
            hashMap.put(Long.valueOf(tweet.id), tweet);
        }
        for (Long l : list) {
            if (hashMap.containsKey(l)) {
                arrayList.add(hashMap.get(l));
            }
        }
        return arrayList;
    }
}
