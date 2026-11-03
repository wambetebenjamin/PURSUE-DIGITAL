package com.facebook.react.devsupport;

import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes8.dex */
public class StackTraceHelper {
    public static final String COLUMN_KEY = "column";
    public static final String LINE_NUMBER_KEY = "lineNumber";
    private static final Pattern STACK_FRAME_PATTERN1 = Pattern.compile("^(?:(.*?)@)?(.*?)\\:([0-9]+)\\:([0-9]+)$");
    private static final Pattern STACK_FRAME_PATTERN2 = Pattern.compile("\\s*(?:at)\\s*(.+?)\\s*[@(](.*):([0-9]+):([0-9]+)[)]$");

    /* loaded from: classes8.dex */
    public static class StackFrameImpl implements StackFrame {
        private final int mColumn;
        private final String mFile;
        private final String mFileName;
        private final boolean mIsCollapsed;
        private final int mLine;
        private final String mMethod;

        private StackFrameImpl(String str, String str2, int i2, int i3, boolean z) {
            this.mFile = str;
            this.mMethod = str2;
            this.mLine = i2;
            this.mColumn = i3;
            this.mFileName = str != null ? new File(str).getName() : "";
            this.mIsCollapsed = z;
        }

        private StackFrameImpl(String str, String str2, int i2, int i3) {
            this(str, str2, i2, i3, false);
        }

        private StackFrameImpl(String str, String str2, String str3, int i2, int i3) {
            this.mFile = str;
            this.mFileName = str2;
            this.mMethod = str3;
            this.mLine = i2;
            this.mColumn = i3;
            this.mIsCollapsed = false;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getFile() {
            return this.mFile;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getMethod() {
            return this.mMethod;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public int getLine() {
            return this.mLine;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public int getColumn() {
            return this.mColumn;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getFileName() {
            return this.mFileName;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public boolean isCollapsed() {
            return this.mIsCollapsed;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public JSONObject toJSON() {
            return new JSONObject(MapBuilder.of("file", getFile(), "methodName", getMethod(), StackTraceHelper.LINE_NUMBER_KEY, Integer.valueOf(getLine()), StackTraceHelper.COLUMN_KEY, Integer.valueOf(getColumn()), "collapse", Boolean.valueOf(isCollapsed())));
        }
    }

    public static StackFrame[] convertJsStackTrace(ReadableArray readableArray) {
        int size = readableArray != null ? readableArray.size() : 0;
        StackFrame[] stackFrameArr = new StackFrame[size];
        for (int i2 = 0; i2 < size; i2++) {
            ReadableType type = readableArray.getType(i2);
            if (type == ReadableType.Map) {
                ReadableMap map = readableArray.getMap(i2);
                stackFrameArr[i2] = new StackFrameImpl(map.getString("file"), map.getString("methodName"), (!map.hasKey(LINE_NUMBER_KEY) || map.isNull(LINE_NUMBER_KEY)) ? -1 : map.getInt(LINE_NUMBER_KEY), (!map.hasKey(COLUMN_KEY) || map.isNull(COLUMN_KEY)) ? -1 : map.getInt(COLUMN_KEY), map.hasKey("collapse") && !map.isNull("collapse") && map.getBoolean("collapse"));
            } else if (type == ReadableType.String) {
                stackFrameArr[i2] = new StackFrameImpl((String) null, readableArray.getString(i2), -1, -1);
            }
        }
        return stackFrameArr;
    }

    public static StackFrame[] convertJsStackTrace(JSONArray jSONArray) {
        int length = jSONArray != null ? jSONArray.length() : 0;
        StackFrame[] stackFrameArr = new StackFrame[length];
        for (int i2 = 0; i2 < length; i2++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                stackFrameArr[i2] = new StackFrameImpl(jSONObject.getString("file"), jSONObject.getString("methodName"), (!jSONObject.has(LINE_NUMBER_KEY) || jSONObject.isNull(LINE_NUMBER_KEY)) ? -1 : jSONObject.getInt(LINE_NUMBER_KEY), (!jSONObject.has(COLUMN_KEY) || jSONObject.isNull(COLUMN_KEY)) ? -1 : jSONObject.getInt(COLUMN_KEY), jSONObject.has("collapse") && !jSONObject.isNull("collapse") && jSONObject.getBoolean("collapse"));
            } catch (JSONException e2) {
                throw new RuntimeException(e2);
            }
        }
        return stackFrameArr;
    }

    public static StackFrame[] convertJsStackTrace(String str) {
        String[] split = str.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        StackFrame[] stackFrameArr = new StackFrame[split.length];
        for (int i2 = 0; i2 < split.length; i2++) {
            Matcher matcher = STACK_FRAME_PATTERN1.matcher(split[i2]);
            Matcher matcher2 = STACK_FRAME_PATTERN2.matcher(split[i2]);
            if (matcher2.find()) {
                matcher = matcher2;
            } else if (!matcher.find()) {
                stackFrameArr[i2] = new StackFrameImpl((String) null, split[i2], -1, -1);
            }
            stackFrameArr[i2] = new StackFrameImpl(matcher.group(2), matcher.group(1) == null ? "(unknown)" : matcher.group(1), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        }
        return stackFrameArr;
    }

    public static StackFrame[] convertJavaStackTrace(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StackFrame[] stackFrameArr = new StackFrame[stackTrace.length];
        for (int i2 = 0; i2 < stackTrace.length; i2++) {
            stackFrameArr[i2] = new StackFrameImpl(stackTrace[i2].getClassName(), stackTrace[i2].getFileName(), stackTrace[i2].getMethodName(), stackTrace[i2].getLineNumber(), -1);
        }
        return stackFrameArr;
    }

    public static String formatFrameSource(StackFrame stackFrame) {
        StringBuilder sb = new StringBuilder();
        sb.append(stackFrame.getFileName());
        int line = stackFrame.getLine();
        if (line > 0) {
            sb.append(CertificateUtil.DELIMITER).append(line);
            int column = stackFrame.getColumn();
            if (column > 0) {
                sb.append(CertificateUtil.DELIMITER).append(column);
            }
        }
        return sb.toString();
    }

    public static String formatStackTrace(String str, StackFrame[] stackFrameArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(str).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        for (StackFrame stackFrame : stackFrameArr) {
            sb.append(stackFrame.getMethod()).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append("    ").append(formatFrameSource(stackFrame)).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }
}
