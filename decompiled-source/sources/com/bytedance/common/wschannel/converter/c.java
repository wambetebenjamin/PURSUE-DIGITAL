package com.bytedance.common.wschannel.converter;

import android.text.TextUtils;
import com.bytedance.common.wschannel.model.Frame;
import com.bytedance.common.wschannel.model.i;
import com.meituan.robust.Constants;
import com.squareup.wire.Wire;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okio.ByteString;
/* compiled from: WireConverter.java */
/* loaded from: classes2.dex */
class c implements a {
    @Override // com.bytedance.common.wschannel.converter.a
    public i a(byte[] bArr) {
        Frame decode = Frame.ADAPTER.decode(bArr);
        long longValue = ((Long) Wire.get(decode.seqid, Frame.DEFAULT_SEQID)).longValue();
        long longValue2 = ((Long) Wire.get(decode.logid, Frame.DEFAULT_LOGID)).longValue();
        int intValue = ((Integer) Wire.get(decode.service, Frame.DEFAULT_SERVICE)).intValue();
        int intValue2 = ((Integer) Wire.get(decode.method, Frame.DEFAULT_METHOD)).intValue();
        List<Frame.b> list = (List) Wire.get(decode.headers, Collections.emptyList());
        String str = (String) Wire.get(decode.payload_encoding, "");
        String str2 = (String) Wire.get(decode.payload_type, "");
        byte[] byteArray = ((ByteString) Wire.get(decode.payload, Frame.DEFAULT_PAYLOAD)).toByteArray();
        String str3 = (String) Wire.get(decode.serverTiming, "");
        String str4 = (String) Wire.get(decode.msgId, "");
        i iVar = new i();
        iVar.b(longValue2);
        iVar.a(longValue);
        iVar.a(intValue);
        iVar.b(intValue2);
        if (list != null && list.size() >= 0) {
            ArrayList arrayList = new ArrayList();
            for (Frame.b bVar : list) {
                i.b bVar2 = new i.b();
                bVar2.a((String) Wire.get(bVar.f5786d, ""));
                bVar2.b((String) Wire.get(bVar.f5787e, ""));
                arrayList.add(bVar2);
            }
            iVar.a(arrayList);
        }
        iVar.e(str);
        iVar.f(str2);
        iVar.a(byteArray);
        String a2 = a(str3);
        if (!TextUtils.isEmpty(a2)) {
            str3 = a2;
        }
        iVar.c(str3);
        iVar.b(str4);
        return iVar;
    }

    private static String a(String str) {
        StringBuilder sb;
        int indexOf;
        if (!TextUtils.isEmpty(str) && (indexOf = (sb = new StringBuilder(str)).indexOf("ttnet;")) >= 0) {
            sb.insert(indexOf + 6, "recv_time=" + System.currentTimeMillis() + Constants.PACKNAME_END);
            return sb.toString();
        }
        return null;
    }

    @Override // com.bytedance.common.wschannel.converter.a
    public byte[] a(i iVar) {
        ByteString byteString;
        if (iVar == null) {
            return null;
        }
        if (iVar.q() != null) {
            byteString = ByteString.of(iVar.q());
        } else {
            byteString = ByteString.EMPTY;
        }
        Frame.a b2 = new Frame.a().b(Long.valueOf(iVar.j())).a(Long.valueOf(iVar.i())).a(Integer.valueOf(iVar.k())).a(iVar.o()).b(iVar.p()).a(byteString).b(Integer.valueOf(iVar.l()));
        List<i.b> m = iVar.m();
        ArrayList arrayList = new ArrayList();
        if (m != null) {
            for (i.b bVar : m) {
                arrayList.add(new Frame.b.a().a(bVar.a()).b(bVar.b()).build());
            }
        }
        if (!arrayList.isEmpty()) {
            b2.a(arrayList);
        }
        return Frame.ADAPTER.encode(b2.build());
    }
}
