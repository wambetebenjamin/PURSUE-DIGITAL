package com.bytedance.bdturing.verify;

import com.bytedance.bdturing.b;
import com.bytedance.bdturing.twiceverify.c;
import java.util.HashMap;
/* loaded from: classes.dex */
public class TwiceVerifyService implements a {
    @Override // com.bytedance.bdturing.verify.a
    public boolean isProcess(int i2) {
        return i2 == 6 || i2 == 7 || i2 == 10;
    }

    @Override // com.bytedance.bdturing.verify.a
    public boolean execute(com.bytedance.bdturing.verify.request.a aVar, final b bVar) {
        if (c.a().b()) {
            c.a().a(aVar, (HashMap<String, String>) null, new c.a() { // from class: com.bytedance.bdturing.verify.TwiceVerifyService.1
                @Override // com.bytedance.bdturing.twiceverify.c.a
                public void a() {
                    bVar.onSuccess(0, null);
                }

                @Override // com.bytedance.bdturing.twiceverify.c.a
                public void a(int i2, String str) {
                    bVar.onFail(i2, null);
                }
            });
            return true;
        }
        return false;
    }
}
