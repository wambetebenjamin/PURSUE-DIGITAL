package com.ss.android.socialbase.downloader.utils;

import kotlin.text.Typography;
/* loaded from: classes9.dex */
public class SqlUtils {
    public static StringBuilder appendColumn(StringBuilder sb, String str) {
        sb.append(Typography.quote).append(str).append(Typography.quote);
        return sb;
    }

    public static StringBuilder appendColumn(StringBuilder sb, String str, String str2) {
        sb.append(str).append(".\"").append(str2).append(Typography.quote);
        return sb;
    }

    public static StringBuilder appendColumns(StringBuilder sb, String[] strArr) {
        int length = strArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            sb.append(Typography.quote).append(strArr[i2]).append(Typography.quote);
            if (i2 < length - 1) {
                sb.append(',');
            }
        }
        return sb;
    }

    public static StringBuilder appendPlaceholders(StringBuilder sb, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 < i2 - 1) {
                sb.append("?,");
            } else {
                sb.append('?');
            }
        }
        return sb;
    }

    public static StringBuilder appendColumnsEqualPlaceholders(StringBuilder sb, String[] strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            appendColumn(sb, strArr[i2]).append("=?");
            if (i2 < strArr.length - 1) {
                sb.append(',');
            }
        }
        return sb;
    }

    public static StringBuilder appendColumnsEqValue(StringBuilder sb, String str, String[] strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            appendColumn(sb, str, strArr[i2]).append("=?");
            if (i2 < strArr.length - 1) {
                sb.append(',');
            }
        }
        return sb;
    }

    public static String createSqlInsert(String str, String str2, String[] strArr) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(Typography.quote).append(str2).append(Typography.quote).append(" (");
        appendColumns(sb, strArr);
        sb.append(") VALUES (");
        appendPlaceholders(sb, strArr.length);
        sb.append(')');
        return sb.toString();
    }

    public static String createSqlDelete(String str, String[] strArr) {
        String str2 = Typography.quote + str + Typography.quote;
        StringBuilder sb = new StringBuilder("DELETE FROM ");
        sb.append(str2);
        if (strArr != null && strArr.length > 0) {
            sb.append(" WHERE ");
            appendColumnsEqValue(sb, str2, strArr);
        }
        return sb.toString();
    }

    public static String createSqlUpdate(String str, String[] strArr, String[] strArr2) {
        String str2 = Typography.quote + str + Typography.quote;
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append(str2).append(" SET ");
        appendColumnsEqualPlaceholders(sb, strArr);
        if (strArr2 != null && strArr2.length > 0) {
            sb.append(" WHERE ");
            appendColumnsEqValue(sb, str2, strArr2);
        }
        return sb.toString();
    }

    public static String createSqlInsertOrReplace(String str, String[] strArr, String[] strArr2) {
        StringBuilder sb = new StringBuilder("INSERT OR REPLACE INTO ");
        sb.append(Typography.quote + str + Typography.quote).append(" (");
        appendColumns(sb, strArr);
        sb.append(") VALUES (");
        appendPlaceholders(sb, strArr.length);
        sb.append(')');
        return sb.toString();
    }
}
