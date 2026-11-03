package com.facebook.react.util;

import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes8.dex */
public class JSStackTrace {
    private static final String COLUMN_KEY = "column";
    private static final Pattern FILE_ID_PATTERN = Pattern.compile("\\b((?:seg-\\d+(?:_\\d+)?|\\d+)\\.js)");
    private static final String FILE_KEY = "file";
    private static final String LINE_NUMBER_KEY = "lineNumber";
    private static final String METHOD_NAME_KEY = "methodName";

    public static String format(String str, ReadableArray readableArray) {
        StringBuilder append = new StringBuilder(str).append(", stack:\n");
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            ReadableMap map = readableArray.getMap(i2);
            append.append((!map.hasKey(METHOD_NAME_KEY) || map.isNull(METHOD_NAME_KEY)) ? "" : map.getString(METHOD_NAME_KEY)).append("@").append(parseFileId(map));
            if (map.hasKey("lineNumber") && !map.isNull("lineNumber") && map.getType("lineNumber") == ReadableType.Number) {
                append.append(map.getInt("lineNumber"));
            } else {
                append.append(-1);
            }
            if (map.hasKey("column") && !map.isNull("column") && map.getType("column") == ReadableType.Number) {
                append.append(CertificateUtil.DELIMITER).append(map.getInt("column"));
            }
            append.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return append.toString();
    }

    private static String parseFileId(ReadableMap readableMap) {
        String string;
        if (!readableMap.hasKey("file") || readableMap.isNull("file") || readableMap.getType("file") != ReadableType.String || (string = readableMap.getString("file")) == null) {
            return "";
        }
        Matcher matcher = FILE_ID_PATTERN.matcher(string);
        return matcher.find() ? matcher.group(1) + CertificateUtil.DELIMITER : "";
    }
}
