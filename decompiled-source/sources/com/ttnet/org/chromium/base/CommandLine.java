package com.ttnet.org.chromium.base;

import android.text.TextUtils;
import com.ttnet.org.chromium.base.annotations.MainDex;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
@MainDex
/* loaded from: classes9.dex */
public abstract class CommandLine {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String SWITCH_PREFIX = "--";
    private static final String SWITCH_TERMINATOR = "--";
    private static final String SWITCH_VALUE_SEPARATOR = "=";
    private static final String TAG = "CommandLine";
    private static final AtomicReference<CommandLine> sCommandLine = new AtomicReference<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        void appendSwitch(String str);

        void appendSwitchWithValue(String str, String str2);

        void appendSwitchesAndArguments(String[] strArr);

        String getSwitchValue(String str);

        String[] getSwitchesFlattened();

        boolean hasSwitch(String str);

        void init(String[] strArr);

        void removeSwitch(String str);
    }

    public abstract void appendSwitch(String str);

    public abstract void appendSwitchWithValue(String str, String str2);

    public abstract void appendSwitchesAndArguments(String[] strArr);

    protected void destroy() {
    }

    protected abstract String[] getCommandLineArguments();

    public abstract String getSwitchValue(String str);

    public abstract Map getSwitches();

    public abstract boolean hasSwitch(String str);

    public boolean isNativeImplementation() {
        return false;
    }

    public abstract void removeSwitch(String str);

    public String getSwitchValue(String str, String str2) {
        String switchValue = getSwitchValue(str);
        return TextUtils.isEmpty(switchValue) ? str2 : switchValue;
    }

    public static boolean isInitialized() {
        return sCommandLine.get() != null;
    }

    public static CommandLine getInstance() {
        return sCommandLine.get();
    }

    public static void init(String[] strArr) {
        setInstance(new JavaCommandLine(strArr));
    }

    public static void initFromFile(String str) {
        char[] readFileAsUtf8 = readFileAsUtf8(str);
        init(readFileAsUtf8 == null ? null : tokenizeQuotedArguments(readFileAsUtf8));
    }

    public static void reset() {
        setInstance(null);
    }

    static String[] tokenizeQuotedArguments(char[] cArr) {
        if (cArr.length > 65536) {
            throw new RuntimeException("Flags file too big: " + cArr.length);
        }
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = null;
        char c2 = 0;
        for (char c3 : cArr) {
            if ((c2 == 0 && (c3 == '\'' || c3 == '\"')) || c3 == c2) {
                if (sb == null || sb.length() <= 0 || sb.charAt(sb.length() - 1) != '\\') {
                    c2 = c2 == 0 ? c3 : (char) 0;
                } else {
                    sb.setCharAt(sb.length() - 1, c3);
                }
            } else if (c2 != 0 || !Character.isWhitespace(c3)) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(c3);
            } else if (sb != null) {
                arrayList.add(sb.toString());
                sb = null;
            }
        }
        if (sb != null) {
            if (c2 != 0) {
                android.util.Log.w(TAG, "Unterminated quoted string: " + ((Object) sb));
            }
            arrayList.add(sb.toString());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static void enableNativeProxy() {
        sCommandLine.set(new NativeCommandLine(getJavaSwitchesOrNull()));
    }

    public static String[] getJavaSwitchesOrNull() {
        CommandLine commandLine = sCommandLine.get();
        if (commandLine != null) {
            return commandLine.getCommandLineArguments();
        }
        return null;
    }

    private static void setInstance(CommandLine commandLine) {
        CommandLine andSet = sCommandLine.getAndSet(commandLine);
        if (andSet != null) {
            andSet.destroy();
        }
    }

    public static void setInstanceForTesting(CommandLine commandLine) {
        setInstance(commandLine);
    }

    private static char[] readFileAsUtf8(String str) {
        File file = new File(str);
        try {
            FileReader fileReader = new FileReader(file);
            char[] cArr = new char[(int) file.length()];
            char[] copyOfRange = Arrays.copyOfRange(cArr, 0, fileReader.read(cArr));
            fileReader.close();
            return copyOfRange;
        } catch (IOException unused) {
            return null;
        }
    }

    private CommandLine() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class JavaCommandLine extends CommandLine {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private ArrayList<String> mArgs;
        private int mArgsBegin;
        private HashMap<String, String> mSwitches;

        JavaCommandLine(String[] strArr) {
            super();
            this.mSwitches = new HashMap<>();
            ArrayList<String> arrayList = new ArrayList<>();
            this.mArgs = arrayList;
            this.mArgsBegin = 1;
            if (strArr == null || strArr.length == 0 || strArr[0] == null) {
                arrayList.add("");
                return;
            }
            arrayList.add(strArr[0]);
            appendSwitchesInternal(strArr, 1);
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        protected String[] getCommandLineArguments() {
            ArrayList<String> arrayList = this.mArgs;
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public boolean hasSwitch(String str) {
            return this.mSwitches.containsKey(str);
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public String getSwitchValue(String str) {
            String str2 = this.mSwitches.get(str);
            if (str2 == null || str2.isEmpty()) {
                return null;
            }
            return str2;
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public Map<String, String> getSwitches() {
            return new HashMap(this.mSwitches);
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public void appendSwitch(String str) {
            appendSwitchWithValue(str, null);
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public void appendSwitchWithValue(String str, String str2) {
            this.mSwitches.put(str, str2 == null ? "" : str2);
            String str3 = "--" + str;
            if (str2 != null && !str2.isEmpty()) {
                str3 = str3 + CommandLine.SWITCH_VALUE_SEPARATOR + str2;
            }
            ArrayList<String> arrayList = this.mArgs;
            int i2 = this.mArgsBegin;
            this.mArgsBegin = i2 + 1;
            arrayList.add(i2, str3);
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public void appendSwitchesAndArguments(String[] strArr) {
            appendSwitchesInternal(strArr, 0);
        }

        private void appendSwitchesInternal(String[] strArr, int i2) {
            boolean z = true;
            for (String str : strArr) {
                if (i2 > 0) {
                    i2--;
                } else {
                    if (str.equals("--")) {
                        z = false;
                    }
                    if (z && str.startsWith("--")) {
                        String[] split = str.split(CommandLine.SWITCH_VALUE_SEPARATOR, 2);
                        appendSwitchWithValue(split[0].substring(2), split.length > 1 ? split[1] : null);
                    } else {
                        this.mArgs.add(str);
                    }
                }
            }
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public void removeSwitch(String str) {
            this.mSwitches.remove(str);
            String str2 = "--" + str;
            for (int i2 = this.mArgsBegin - 1; i2 > 0; i2--) {
                if (this.mArgs.get(i2).equals(str2) || this.mArgs.get(i2).startsWith(str2 + CommandLine.SWITCH_VALUE_SEPARATOR)) {
                    this.mArgsBegin--;
                    this.mArgs.remove(i2);
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    private static class NativeCommandLine extends CommandLine {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // com.ttnet.org.chromium.base.CommandLine
        protected String[] getCommandLineArguments() {
            return null;
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public boolean isNativeImplementation() {
            return true;
        }

        public NativeCommandLine(String[] strArr) {
            super();
            CommandLineJni.get().init(strArr);
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public boolean hasSwitch(String str) {
            return CommandLineJni.get().hasSwitch(str);
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public String getSwitchValue(String str) {
            return CommandLineJni.get().getSwitchValue(str);
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public Map<String, String> getSwitches() {
            HashMap hashMap = new HashMap();
            String[] switchesFlattened = CommandLineJni.get().getSwitchesFlattened();
            for (int i2 = 0; i2 < switchesFlattened.length; i2 += 2) {
                hashMap.put(switchesFlattened[i2], switchesFlattened[i2 + 1]);
            }
            return hashMap;
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public void appendSwitch(String str) {
            CommandLineJni.get().appendSwitch(str);
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public void appendSwitchWithValue(String str, String str2) {
            Natives natives = CommandLineJni.get();
            if (str2 == null) {
                str2 = "";
            }
            natives.appendSwitchWithValue(str, str2);
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public void appendSwitchesAndArguments(String[] strArr) {
            CommandLineJni.get().appendSwitchesAndArguments(strArr);
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        public void removeSwitch(String str) {
            CommandLineJni.get().removeSwitch(str);
        }

        @Override // com.ttnet.org.chromium.base.CommandLine
        protected void destroy() {
            throw new IllegalStateException("Can't destroy native command line after startup");
        }
    }
}
