package com.oasis.multiplechannel;

import com.oasis.Logger.Logger;
/* loaded from: classes9.dex */
public class a extends MultiChannelAgent {

    /* renamed from: d  reason: collision with root package name */
    private static String f17555d = "RocketCNMultiChannelAgent";

    @Override // com.oasis.multiplechannel.MultiChannelAgent
    public void createNewRole(String str) {
        Logger.d(f17555d, "Default createNewRole");
    }

    @Override // com.oasis.multiplechannel.MultiChannelAgent
    public void enterGame(String str) {
        Logger.d(f17555d, "Default enterGame");
    }

    @Override // com.oasis.multiplechannel.MultiChannelAgent
    public boolean isAvailable(String str) {
        Logger.d(f17555d, "Default isAvailable");
        return super.isAvailable(str);
    }

    @Override // com.oasis.multiplechannel.MultiChannelAgent
    public void roleExit(String str) {
        Logger.d(f17555d, "Default exitGame");
    }

    @Override // com.oasis.multiplechannel.MultiChannelAgent
    public void roleLevelUp(String str) {
        Logger.d(f17555d, "Default roleLevelUp");
    }

    @Override // com.oasis.multiplechannel.MultiChannelAgent
    public void showPrivacy(int i2) {
    }
}
