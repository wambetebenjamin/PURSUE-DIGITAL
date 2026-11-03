package androidx.constraintlayout.motion.widget;

import java.util.Arrays;
import java.util.HashMap;
/* loaded from: classes.dex */
public class KeyCache {
    HashMap<Object, HashMap<String, float[]>> map = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFloatValue(Object obj, String str, int i2, float f2) {
        if (!this.map.containsKey(obj)) {
            HashMap<String, float[]> hashMap = new HashMap<>();
            float[] fArr = new float[i2 + 1];
            fArr[i2] = f2;
            hashMap.put(str, fArr);
            this.map.put(obj, hashMap);
            return;
        }
        HashMap<String, float[]> hashMap2 = this.map.get(obj);
        if (!hashMap2.containsKey(str)) {
            float[] fArr2 = new float[i2 + 1];
            fArr2[i2] = f2;
            hashMap2.put(str, fArr2);
            this.map.put(obj, hashMap2);
            return;
        }
        float[] fArr3 = hashMap2.get(str);
        if (fArr3.length <= i2) {
            fArr3 = Arrays.copyOf(fArr3, i2 + 1);
        }
        fArr3[i2] = f2;
        hashMap2.put(str, fArr3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFloatValue(Object obj, String str, int i2) {
        if (this.map.containsKey(obj)) {
            HashMap<String, float[]> hashMap = this.map.get(obj);
            if (hashMap.containsKey(str)) {
                float[] fArr = hashMap.get(str);
                if (fArr.length > i2) {
                    return fArr[i2];
                }
                return Float.NaN;
            }
            return Float.NaN;
        }
        return Float.NaN;
    }
}
