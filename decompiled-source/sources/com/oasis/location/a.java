package com.oasis.location;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class a extends LocationAgent {
    @Override // com.oasis.location.LocationAgent
    public void deletePOI(String str, String str2, LBSListener lBSListener) {
        lBSListener.onFailed(false, "DefaultLocationAgent");
    }

    @Override // com.oasis.location.LocationAgent
    public void getLocation(LocationListener locationListener) {
        locationListener.OnLocationFinish(false, "DefaultLocationAgent");
    }

    @Override // com.oasis.location.LocationAgent
    public void getNearPOI(String str, double d2, double d3, int i2, String str2, LBSListener lBSListener) {
        lBSListener.onFailed(false, "DefaultLocationAgent");
    }

    @Override // com.oasis.location.LocationAgent
    public void reportPOI(String str, String str2, String str3, LBSListener lBSListener) {
        lBSListener.onFailed(false, "DefaultLocationAgent");
    }
}
