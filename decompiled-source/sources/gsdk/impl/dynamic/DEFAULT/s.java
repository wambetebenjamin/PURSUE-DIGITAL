package gsdk.impl.dynamic.DEFAULT;
/* compiled from: DynamicPageStateManager.java */
/* loaded from: classes10.dex */
public class s {

    /* compiled from: DynamicPageStateManager.java */
    /* loaded from: classes10.dex */
    public enum a {
        RESUME,
        PAUSE,
        PAGE_RESUME,
        PAGE_PAUSE
    }

    public static void a(String str, a aVar) {
        if (aVar != null && aVar.toString().equals(a.RESUME.toString())) {
            x.a("WINDOW_STATE_EVENT", a.PAGE_RESUME.toString(), str);
            x.a("WINDOW_STATE_EVENT", a.RESUME.toString(), str);
        } else if (aVar == null || !aVar.toString().equals(a.PAUSE.toString())) {
        } else {
            x.a("WINDOW_STATE_EVENT", a.PAGE_PAUSE.toString(), str);
            x.a("WINDOW_STATE_EVENT", a.PAUSE.toString(), str);
        }
    }
}
