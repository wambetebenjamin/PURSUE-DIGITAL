package gsdk.impl.main.DEFAULT;

import android.app.Application;
import android.database.CursorWindow;
import java.lang.reflect.Field;
/* compiled from: CursorWindowPlugin.java */
/* loaded from: classes10.dex */
public class z extends com.bytedance.platform.godzilla.plugin.a {

    /* renamed from: a  reason: collision with root package name */
    private static final int f20532a = 1048576;

    /* renamed from: b  reason: collision with root package name */
    private static int f20533b = -1;

    /* renamed from: c  reason: collision with root package name */
    private static Field f20534c;

    /* renamed from: d  reason: collision with root package name */
    private int f20535d;

    @Override // com.bytedance.platform.godzilla.plugin.a
    public String getName() {
        return "CursorWindowPlugin";
    }

    public z() {
        this.f20535d = 0;
    }

    public z(int i2) {
        this.f20535d = 0;
        this.f20535d = i2;
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void init(Application application) {
        super.init(application);
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void start() {
        super.start();
        int i2 = this.f20535d;
        if (i2 > 0) {
            a(i2);
        } else {
            a(1048576);
        }
    }

    private boolean a(int i2) {
        Field field = com.bytedance.platform.godzilla.utils.b.getField(CursorWindow.class, "sCursorWindowSize");
        f20534c = field;
        if (field != null) {
            if (i2 <= 0) {
                i2 = 1048576;
            }
            field.setAccessible(true);
            try {
                int intValue = ((Integer) com.bytedance.platform.godzilla.utils.b.readStaticField(f20534c)).intValue();
                f20533b = intValue;
                if (intValue != i2) {
                    com.bytedance.platform.godzilla.utils.b.writeStaticField(f20534c, Integer.valueOf(i2));
                }
                com.bytedance.platform.godzilla.common.k.e(getName(), "Resize cursor window size successful! Resize form :" + f20533b + "to " + ((Integer) com.bytedance.platform.godzilla.utils.b.readStaticField(f20534c)).intValue());
                return true;
            } catch (IllegalAccessException unused) {
                return false;
            }
        }
        return false;
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void stop() {
        int i2;
        super.stop();
        Field field = f20534c;
        if (field == null || (i2 = f20533b) <= 0) {
            return;
        }
        try {
            com.bytedance.platform.godzilla.utils.b.writeStaticField(field, Integer.valueOf(i2));
        } catch (IllegalAccessException unused) {
        }
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void destroy() {
        super.destroy();
    }
}
