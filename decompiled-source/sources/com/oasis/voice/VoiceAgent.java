package com.oasis.voice;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class VoiceAgent {
    public static String TAG = "VoiceAgent";

    /* renamed from: a  reason: collision with root package name */
    private static VoiceAgent f17775a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17776b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17777c = true;

    public static VoiceAgent createInstance(Context context) {
        if (f17777c || f17775a == null) {
            try {
                String string = context.getString(R.string.voice_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i(TAG, "Create the VoiceAgent:" + string);
                    f17775a = (VoiceAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e(TAG, "Create voice agent failed.", e2);
            }
            if (f17775a == null) {
                Logger.i(TAG, "Create default VoiceAgent.");
                f17775a = new DefaultVoiceAgent();
                f17776b = true;
            }
            TAG = f17775a.getClass().getSimpleName();
            return f17775a;
        }
        throw new AssertionError();
    }

    public static VoiceAgent getInstance() {
        if (f17777c || f17775a != null) {
            return f17775a;
        }
        throw new AssertionError();
    }

    public void beginRecordVoiceMsg() {
        Logger.d(TAG, "beginRecordVoiceMsg");
    }

    public void cancelRecord() {
        Logger.d(TAG, "cancelRecord");
    }

    public void endRecordAndUpload() {
        Logger.d(TAG, "endRecordAndUpload");
    }

    public void fetchVoiceLocalPathByVid(String str) {
        Logger.d(TAG, "fetchVoiceLocalPathByVid: voiceId == " + str);
    }

    public void initVoiceMsg(String str, RecordVoiceListener recordVoiceListener, PlayVoiceListener playVoiceListener, FetchVoiceListener fetchVoiceListener) {
        Logger.d(TAG, "initVoiceMsg:" + str);
    }

    public boolean isUseDefault() {
        return f17776b;
    }

    public void playMultiMsg(String str) {
        Logger.d(TAG, "playMultiMsg:" + str);
    }

    public void playOneMsg(String str) {
        Logger.d(TAG, "playOneMsg:" + str);
    }

    public void releaseVoiceMsg() {
        Logger.d(TAG, "releaseVoiceMsg");
    }

    public void stopPlayVoiceMsg() {
        Logger.d(TAG, "stopPlayVoiceMsg");
    }

    public void translateVoiceMsg(String str, TranslateVoiceListener translateVoiceListener) {
        Logger.d(TAG, "translateVoiceMsg: voiceId == " + str);
    }
}
