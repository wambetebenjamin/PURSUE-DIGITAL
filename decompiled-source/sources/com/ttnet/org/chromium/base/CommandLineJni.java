package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.CommandLine;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
@MainDex
/* loaded from: classes9.dex */
public class CommandLineJni implements CommandLine.Natives {
    public static final JniStaticTestMocker<CommandLine.Natives> TEST_HOOKS = new JniStaticTestMocker<CommandLine.Natives>() { // from class: com.ttnet.org.chromium.base.CommandLineJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(CommandLine.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                CommandLine.Natives unused = CommandLineJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static CommandLine.Natives testInstance;

    CommandLineJni() {
    }

    @Override // com.ttnet.org.chromium.base.CommandLine.Natives
    public void init(String[] strArr) {
        GEN_JNI.com_ttnet_org_chromium_base_CommandLine_init(strArr);
    }

    @Override // com.ttnet.org.chromium.base.CommandLine.Natives
    public boolean hasSwitch(String str) {
        return GEN_JNI.com_ttnet_org_chromium_base_CommandLine_hasSwitch(str);
    }

    @Override // com.ttnet.org.chromium.base.CommandLine.Natives
    public String getSwitchValue(String str) {
        return GEN_JNI.com_ttnet_org_chromium_base_CommandLine_getSwitchValue(str);
    }

    @Override // com.ttnet.org.chromium.base.CommandLine.Natives
    public String[] getSwitchesFlattened() {
        return GEN_JNI.com_ttnet_org_chromium_base_CommandLine_getSwitchesFlattened();
    }

    @Override // com.ttnet.org.chromium.base.CommandLine.Natives
    public void appendSwitch(String str) {
        GEN_JNI.com_ttnet_org_chromium_base_CommandLine_appendSwitch(str);
    }

    @Override // com.ttnet.org.chromium.base.CommandLine.Natives
    public void appendSwitchWithValue(String str, String str2) {
        GEN_JNI.com_ttnet_org_chromium_base_CommandLine_appendSwitchWithValue(str, str2);
    }

    @Override // com.ttnet.org.chromium.base.CommandLine.Natives
    public void appendSwitchesAndArguments(String[] strArr) {
        GEN_JNI.com_ttnet_org_chromium_base_CommandLine_appendSwitchesAndArguments(strArr);
    }

    @Override // com.ttnet.org.chromium.base.CommandLine.Natives
    public void removeSwitch(String str) {
        GEN_JNI.com_ttnet_org_chromium_base_CommandLine_removeSwitch(str);
    }

    public static CommandLine.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            CommandLine.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.CommandLine.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(true);
        return new CommandLineJni();
    }
}
