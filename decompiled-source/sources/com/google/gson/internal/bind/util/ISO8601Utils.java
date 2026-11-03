package com.google.gson.internal.bind.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes9.dex */
public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i2 = offset / 60000;
            int abs = Math.abs(i2 / 60);
            int abs2 = Math.abs(i2 % 60);
            sb.append(offset >= 0 ? '+' : '-');
            padInt(sb, abs, 2);
            sb.append(':');
            padInt(sb, abs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00cf A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01c7, NumberFormatException -> 0x01c9, IndexOutOfBoundsException -> 0x01cb, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01c7, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:47:0x00c9, B:49:0x00cf, B:51:0x00d6, B:75:0x018e, B:55:0x00e0, B:56:0x00fe, B:57:0x00ff, B:61:0x011d, B:63:0x012a, B:66:0x0133, B:68:0x0154, B:71:0x0163, B:72:0x0189, B:74:0x018c, B:60:0x010a, B:77:0x01bf, B:78:0x01c6, B:40:0x00b0, B:41:0x00b3), top: B:94:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01bf A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01c7, NumberFormatException -> 0x01c9, IndexOutOfBoundsException -> 0x01cb, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01c7, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:47:0x00c9, B:49:0x00cf, B:51:0x00d6, B:75:0x018e, B:55:0x00e0, B:56:0x00fe, B:57:0x00ff, B:61:0x011d, B:63:0x012a, B:66:0x0133, B:68:0x0154, B:71:0x0163, B:72:0x0189, B:74:0x018c, B:60:0x010a, B:77:0x01bf, B:78:0x01c6, B:40:0x00b0, B:41:0x00b3), top: B:94:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Date parse(java.lang.String r17, java.text.ParsePosition r18) throws java.text.ParseException {
        /*
            Method dump skipped, instructions count: 574
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static boolean checkOffset(String str, int i2, char c2) {
        return i2 < str.length() && str.charAt(i2) == c2;
    }

    private static int parseInt(String str, int i2, int i3) throws NumberFormatException {
        int i4;
        int i5;
        if (i2 < 0 || i3 > str.length() || i2 > i3) {
            throw new NumberFormatException(str);
        }
        if (i2 < i3) {
            i5 = i2 + 1;
            int digit = Character.digit(str.charAt(i2), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i2, i3));
            }
            i4 = -digit;
        } else {
            i4 = 0;
            i5 = i2;
        }
        while (i5 < i3) {
            int i6 = i5 + 1;
            int digit2 = Character.digit(str.charAt(i5), 10);
            if (digit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i2, i3));
            }
            i4 = (i4 * 10) - digit2;
            i5 = i6;
        }
        return -i4;
    }

    private static void padInt(StringBuilder sb, int i2, int i3) {
        String num = Integer.toString(i2);
        for (int length = i3 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    private static int indexOfNonDigit(String str, int i2) {
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if (charAt < '0' || charAt > '9') {
                return i2;
            }
            i2++;
        }
        return str.length();
    }
}
