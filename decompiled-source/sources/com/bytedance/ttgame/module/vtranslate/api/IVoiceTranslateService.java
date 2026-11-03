package com.bytedance.ttgame.module.vtranslate.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
/* loaded from: classes7.dex */
public interface IVoiceTranslateService extends IModuleApi {
    void translate(String str, IVoiceTranslateListener iVoiceTranslateListener);

    void translate(String str, String str2, IVoiceTranslateListener iVoiceTranslateListener);

    void translateLocalVoice(String str, String str2, String str3, String str4, IVoiceTranslateListener iVoiceTranslateListener);
}
