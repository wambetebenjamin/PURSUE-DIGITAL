package com.twitter.sdk.android.tweetui;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import com.twitter.sdk.android.core.models.ModelUtils;
import com.twitter.sdk.android.tweetui.internal.ClickableLinkSpan;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
/* loaded from: classes10.dex */
final class TweetTextLinkifier {
    static final Pattern QUOTED_STATUS_URL = Pattern.compile("^https?://twitter\\.com(/#!)?/\\w+/status/\\d+$");
    static final Pattern VINE_URL = Pattern.compile("^https?://vine\\.co(/#!)?/v/\\w+$");

    private TweetTextLinkifier() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharSequence linkifyUrls(FormattedTweetText formattedTweetText, LinkClickListener linkClickListener, int i2, int i3, boolean z, boolean z2) {
        if (formattedTweetText == null) {
            return null;
        }
        if (TextUtils.isEmpty(formattedTweetText.text)) {
            return formattedTweetText.text;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(formattedTweetText.text);
        List<FormattedUrlEntity> mergeAndSortEntities = mergeAndSortEntities(ModelUtils.getSafeList(formattedTweetText.urlEntities), ModelUtils.getSafeList(formattedTweetText.mediaEntities), ModelUtils.getSafeList(formattedTweetText.hashtagEntities), ModelUtils.getSafeList(formattedTweetText.mentionEntities), ModelUtils.getSafeList(formattedTweetText.symbolEntities));
        addUrlEntities(spannableStringBuilder, mergeAndSortEntities, getEntityToStrip(formattedTweetText.text, mergeAndSortEntities, z, z2), linkClickListener, i2, i3);
        return trimEnd(spannableStringBuilder);
    }

    static CharSequence trimEnd(CharSequence charSequence) {
        int length = charSequence.length();
        while (length > 0 && charSequence.charAt(length - 1) <= ' ') {
            length--;
        }
        return length < charSequence.length() ? charSequence.subSequence(0, length) : charSequence;
    }

    static List<FormattedUrlEntity> mergeAndSortEntities(List<FormattedUrlEntity> list, List<FormattedMediaEntity> list2, List<FormattedUrlEntity> list3, List<FormattedUrlEntity> list4, List<FormattedUrlEntity> list5) {
        ArrayList arrayList = new ArrayList(list);
        arrayList.addAll(list2);
        arrayList.addAll(list3);
        arrayList.addAll(list4);
        arrayList.addAll(list5);
        Collections.sort(arrayList, new Comparator() { // from class: com.twitter.sdk.android.tweetui.TweetTextLinkifier$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return TweetTextLinkifier.lambda$mergeAndSortEntities$0((FormattedUrlEntity) obj, (FormattedUrlEntity) obj2);
            }
        });
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$mergeAndSortEntities$0(FormattedUrlEntity formattedUrlEntity, FormattedUrlEntity formattedUrlEntity2) {
        if (formattedUrlEntity != null || formattedUrlEntity2 == null) {
            if (formattedUrlEntity == null || formattedUrlEntity2 != null) {
                if (formattedUrlEntity == null && formattedUrlEntity2 == null) {
                    return 0;
                }
                if (formattedUrlEntity.start < formattedUrlEntity2.start) {
                    return -1;
                }
                return formattedUrlEntity.start > formattedUrlEntity2.start ? 1 : 0;
            }
            return 1;
        }
        return -1;
    }

    private static void addUrlEntities(SpannableStringBuilder spannableStringBuilder, List<FormattedUrlEntity> list, FormattedUrlEntity formattedUrlEntity, final LinkClickListener linkClickListener, int i2, int i3) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i4 = 0;
        for (final FormattedUrlEntity formattedUrlEntity2 : list) {
            int i5 = formattedUrlEntity2.start - i4;
            int i6 = formattedUrlEntity2.end - i4;
            if (i5 >= 0 && i6 <= spannableStringBuilder.length()) {
                if (formattedUrlEntity != null && formattedUrlEntity.start == formattedUrlEntity2.start) {
                    spannableStringBuilder.replace(i5, i6, "");
                    i4 += i6 - i5;
                } else if (!TextUtils.isEmpty(formattedUrlEntity2.displayUrl)) {
                    spannableStringBuilder.replace(i5, i6, (CharSequence) formattedUrlEntity2.displayUrl);
                    int length = i6 - (formattedUrlEntity2.displayUrl.length() + i5);
                    i4 += length;
                    spannableStringBuilder.setSpan(new ClickableLinkSpan(i3, i2, false) { // from class: com.twitter.sdk.android.tweetui.TweetTextLinkifier.1
                        @Override // android.text.style.ClickableSpan, com.twitter.sdk.android.tweetui.internal.HighlightedClickableSpan
                        public void onClick(View view) {
                            LinkClickListener linkClickListener2 = linkClickListener;
                            if (linkClickListener2 == null) {
                                return;
                            }
                            linkClickListener2.onUrlClicked(formattedUrlEntity2.url);
                        }
                    }, i5, i6 - length, 33);
                }
            }
        }
    }

    static FormattedUrlEntity getEntityToStrip(String str, List<FormattedUrlEntity> list, boolean z, boolean z2) {
        if (list.isEmpty()) {
            return null;
        }
        FormattedUrlEntity formattedUrlEntity = list.get(list.size() - 1);
        if (stripLtrMarker(str).endsWith(formattedUrlEntity.url) && (isPhotoEntity(formattedUrlEntity) || ((z && isQuotedStatus(formattedUrlEntity)) || (z2 && isVineCard(formattedUrlEntity))))) {
            return formattedUrlEntity;
        }
        return null;
    }

    static String stripLtrMarker(String str) {
        return str.endsWith(Character.toString((char) 8206)) ? str.substring(0, str.length() - 1) : str;
    }

    static boolean isPhotoEntity(FormattedUrlEntity formattedUrlEntity) {
        return (formattedUrlEntity instanceof FormattedMediaEntity) && "photo".equals(((FormattedMediaEntity) formattedUrlEntity).type);
    }

    static boolean isQuotedStatus(FormattedUrlEntity formattedUrlEntity) {
        return QUOTED_STATUS_URL.matcher(formattedUrlEntity.expandedUrl).find();
    }

    static boolean isVineCard(FormattedUrlEntity formattedUrlEntity) {
        return VINE_URL.matcher(formattedUrlEntity.expandedUrl).find();
    }
}
