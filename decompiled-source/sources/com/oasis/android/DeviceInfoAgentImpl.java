package com.oasis.android;

import android.app.Application;
import com.github.gzuliyujiang.oaid.DeviceID;
import com.github.gzuliyujiang.oaid.DeviceIdentifier;
import com.github.gzuliyujiang.oaid.IGetter;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class DeviceInfoAgentImpl extends DeviceInfoAgent {

    /* renamed from: d  reason: collision with root package name */
    private static final List<OAIDListener> f17363d = new ArrayList();

    /* loaded from: classes9.dex */
    class a implements IGetter {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ OAIDListener f17364a;

        a(OAIDListener oAIDListener) {
            this.f17364a = oAIDListener;
        }

        public void onOAIDGetComplete(String str) {
            OAIDListener oAIDListener = this.f17364a;
            if (oAIDListener != null) {
                oAIDListener.onResult(true, str);
            }
        }

        public void onOAIDGetError(Exception exc) {
            OAIDListener oAIDListener = this.f17364a;
            if (oAIDListener != null) {
                oAIDListener.onResult(false, exc.getLocalizedMessage());
            }
        }
    }

    /* loaded from: classes9.dex */
    class b implements IGetter {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ OAIDListener f17366a;

        b(OAIDListener oAIDListener) {
            this.f17366a = oAIDListener;
        }

        public void onOAIDGetComplete(String str) {
            OAIDListener oAIDListener = this.f17366a;
            if (oAIDListener != null) {
                oAIDListener.onResult(true, str);
            }
        }

        public void onOAIDGetError(Exception exc) {
            OAIDListener oAIDListener = this.f17366a;
            if (oAIDListener != null) {
                oAIDListener.onResult(false, exc.getLocalizedMessage());
            }
        }
    }

    @Override // com.oasis.android.DeviceInfoAgent
    public String getAndroidID() {
        return DeviceIdentifier.getAndroidID(this.application);
    }

    @Override // com.oasis.android.DeviceInfoAgent
    public String getGUID() {
        return DeviceIdentifier.getGUID(this.application);
    }

    @Override // com.oasis.android.DeviceInfoAgent
    public String getIMEI() {
        return DeviceIdentifier.getIMEI(this.application);
    }

    @Override // com.oasis.android.DeviceInfoAgent
    public String getOAID() {
        return DeviceIdentifier.getOAID(this.application);
    }

    @Override // com.oasis.android.DeviceInfoAgent
    public void getOAIDAsync(OAIDListener oAIDListener) {
        if (DeviceInfoAgent.isAllowPrivacy) {
            DeviceID.getOAID(this.application, new a(oAIDListener));
        } else {
            f17363d.add(oAIDListener);
        }
    }

    @Override // com.oasis.android.DeviceInfoAgent
    public String getPseudoID() {
        return DeviceIdentifier.getPseudoID();
    }

    @Override // com.oasis.android.DeviceInfoAgent
    public String getWidevineID() {
        return DeviceIdentifier.getWidevineID();
    }

    @Override // com.oasis.android.DeviceInfoAgent
    public boolean isSupportedOAID() {
        return DeviceID.supportedOAID(this.application);
    }

    @Override // com.oasis.android.DeviceInfoAgent, com.oasis.android.ApplicationListener
    public void onApplicationCreate(Application application) {
        this.application = application;
    }

    @Override // com.oasis.android.DeviceInfoAgent
    public void startRequest() {
        if (!DeviceInfoAgent.isAllowPrivacy) {
            return;
        }
        while (true) {
            List<OAIDListener> list = f17363d;
            if (list.size() == 0) {
                return;
            }
            DeviceID.getOAID(this.application, new b(list.remove(0)));
        }
    }
}
