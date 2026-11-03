package com.bytedance.sync.protocal;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.util.List;
import okio.ByteString;
/* compiled from: BsyncProtocol.java */
/* loaded from: classes3.dex */
public final class f extends Message<f, a> {
    public static final ProtoAdapter<f> ADAPTER = new b();

    /* renamed from: a  reason: collision with root package name */
    private static final long f13639a = 0;
    @WireField(adapter = "com.bytedance.sync.protocal.BsyncCursor#ADAPTER", label = WireField.Label.REPEATED, tag = 2)
    public final List<com.bytedance.sync.protocal.b> cursors;
    @WireField(adapter = "com.bytedance.sync.protocal.BsyncHeader#ADAPTER", label = WireField.Label.REQUIRED, tag = 1)
    public final c header;
    @WireField(adapter = "com.bytedance.sync.protocal.BsyncPayload#ADAPTER", label = WireField.Label.REPEATED, tag = 3)
    public final List<d> payloads;

    public f(c cVar, List<com.bytedance.sync.protocal.b> list, List<d> list2) {
        this(cVar, list, list2, ByteString.EMPTY);
    }

    public f(c cVar, List<com.bytedance.sync.protocal.b> list, List<d> list2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.header = cVar;
        this.cursors = Internal.immutableCopyOf("cursors", list);
        this.payloads = Internal.immutableCopyOf("payloads", list2);
    }

    @Override // com.squareup.wire.Message
    /* renamed from: newBuilder */
    public Message.Builder<f, a> newBuilder2() {
        a aVar = new a();
        aVar.header = this.header;
        aVar.cursors = Internal.copyOf("cursors", this.cursors);
        aVar.payloads = Internal.copyOf("payloads", this.payloads);
        aVar.addUnknownFields(unknownFields());
        return aVar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            return unknownFields().equals(fVar.unknownFields()) && this.header.equals(fVar.header) && this.cursors.equals(fVar.cursors) && this.payloads.equals(fVar.payloads);
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.hashCode;
        if (i2 == 0) {
            int hashCode = (((((unknownFields().hashCode() * 37) + this.header.hashCode()) * 37) + this.cursors.hashCode()) * 37) + this.payloads.hashCode();
            this.hashCode = hashCode;
            return hashCode;
        }
        return i2;
    }

    @Override // com.squareup.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", header=").append(this.header);
        if (!this.cursors.isEmpty()) {
            sb.append(", cursors=").append(this.cursors);
        }
        if (!this.payloads.isEmpty()) {
            sb.append(", payloads=").append(this.payloads);
        }
        return sb.replace(0, 2, "BsyncProtocol{").append('}').toString();
    }

    /* compiled from: BsyncProtocol.java */
    /* loaded from: classes3.dex */
    public static final class a extends Message.Builder<f, a> {
        public c header;
        public List<com.bytedance.sync.protocal.b> cursors = Internal.newMutableList();
        public List<d> payloads = Internal.newMutableList();

        public a header(c cVar) {
            this.header = cVar;
            return this;
        }

        public a cursors(List<com.bytedance.sync.protocal.b> list) {
            Internal.checkElementsNotNull(list);
            this.cursors = list;
            return this;
        }

        public a payloads(List<d> list) {
            Internal.checkElementsNotNull(list);
            this.payloads = list;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.Message.Builder
        public f build() {
            c cVar = this.header;
            if (cVar == null) {
                throw Internal.missingRequiredFields(cVar, "header");
            }
            return new f(this.header, this.cursors, this.payloads, super.buildUnknownFields());
        }
    }

    /* compiled from: BsyncProtocol.java */
    /* loaded from: classes3.dex */
    private static final class b extends ProtoAdapter<f> {
        public b() {
            super(FieldEncoding.LENGTH_DELIMITED, f.class);
        }

        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(f fVar) {
            return c.ADAPTER.encodedSizeWithTag(1, fVar.header) + com.bytedance.sync.protocal.b.ADAPTER.asRepeated().encodedSizeWithTag(2, fVar.cursors) + d.ADAPTER.asRepeated().encodedSizeWithTag(3, fVar.payloads) + fVar.unknownFields().size();
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, f fVar) {
            c.ADAPTER.encodeWithTag(protoWriter, 1, fVar.header);
            com.bytedance.sync.protocal.b.ADAPTER.asRepeated().encodeWithTag(protoWriter, 2, fVar.cursors);
            d.ADAPTER.asRepeated().encodeWithTag(protoWriter, 3, fVar.payloads);
            protoWriter.writeBytes(fVar.unknownFields());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.ProtoAdapter
        public f decode(ProtoReader protoReader) {
            a aVar = new a();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return aVar.build();
                } else if (nextTag == 1) {
                    aVar.header(c.ADAPTER.decode(protoReader));
                } else if (nextTag == 2) {
                    aVar.cursors.add(com.bytedance.sync.protocal.b.ADAPTER.decode(protoReader));
                } else if (nextTag == 3) {
                    aVar.payloads.add(d.ADAPTER.decode(protoReader));
                } else {
                    FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                    aVar.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                }
            }
        }

        /* JADX WARN: Type inference failed for: r3v1, types: [com.bytedance.sync.protocal.f$a] */
        @Override // com.squareup.wire.ProtoAdapter
        public f redact(f fVar) {
            ?? newBuilder2 = fVar.newBuilder2();
            newBuilder2.header = c.ADAPTER.redact(newBuilder2.header);
            Internal.redactElements(newBuilder2.cursors, com.bytedance.sync.protocal.b.ADAPTER);
            Internal.redactElements(newBuilder2.payloads, d.ADAPTER);
            newBuilder2.clearUnknownFields();
            return newBuilder2.build();
        }
    }
}
