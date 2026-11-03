package com.twitter.sdk.android.tweetui;

import android.content.res.Resources;
import androidx.collection.SparseArrayCompat;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes10.dex */
final class TweetDateUtils {
    static final long INVALID_DATE = -1;
    static final SimpleDateFormat DATE_TIME_RFC822 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
    static final DateFormatter RELATIVE_DATE_FORMAT = new DateFormatter();

    private TweetDateUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long apiTimeToLong(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return DATE_TIME_RFC822.parse(str).getTime();
        } catch (ParseException unused) {
            return -1L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidTimestamp(String str) {
        return apiTimeToLong(str) != -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String dotPrefix(String str) {
        return "• " + str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getRelativeTimeString(Resources resources, long j2, long j3) {
        long j4 = j2 - j3;
        if (j4 >= 0) {
            if (j4 < 60000) {
                int i2 = (int) (j4 / 1000);
                return resources.getQuantityString(R.plurals.tw__time_secs, i2, Integer.valueOf(i2));
            } else if (j4 < 3600000) {
                int i3 = (int) (j4 / 60000);
                return resources.getQuantityString(R.plurals.tw__time_mins, i3, Integer.valueOf(i3));
            } else if (j4 < 86400000) {
                int i4 = (int) (j4 / 3600000);
                return resources.getQuantityString(R.plurals.tw__time_hours, i4, Integer.valueOf(i4));
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(j2);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeInMillis(j3);
                Date date = new Date(j3);
                if (calendar.get(1) == calendar2.get(1)) {
                    return RELATIVE_DATE_FORMAT.formatShortDateString(resources, date);
                }
                return RELATIVE_DATE_FORMAT.formatLongDateString(resources, date);
            }
        }
        return RELATIVE_DATE_FORMAT.formatLongDateString(resources, new Date(j3));
    }

    /* loaded from: classes10.dex */
    static class DateFormatter {
        private Locale currentLocale;
        private final SparseArrayCompat<SimpleDateFormat> dateFormatArray = new SparseArrayCompat<>();

        DateFormatter() {
        }

        synchronized String formatLongDateString(Resources resources, Date date) {
            return getDateFormat(resources, R.string.tw__relative_date_format_long).format(date);
        }

        synchronized String formatShortDateString(Resources resources, Date date) {
            return getDateFormat(resources, R.string.tw__relative_date_format_short).format(date);
        }

        private synchronized DateFormat getDateFormat(Resources resources, int i2) {
            SimpleDateFormat simpleDateFormat;
            Locale locale = this.currentLocale;
            if (locale == null || locale != resources.getConfiguration().locale) {
                this.currentLocale = resources.getConfiguration().locale;
                this.dateFormatArray.clear();
            }
            simpleDateFormat = this.dateFormatArray.get(i2);
            if (simpleDateFormat == null) {
                simpleDateFormat = new SimpleDateFormat(resources.getString(i2), Locale.getDefault());
                this.dateFormatArray.put(i2, simpleDateFormat);
            }
            return simpleDateFormat;
        }
    }
}
