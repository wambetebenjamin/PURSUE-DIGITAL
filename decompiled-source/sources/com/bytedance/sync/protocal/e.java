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
/* compiled from: BsyncPipeline.java */
/* loaded from: classes3.dex */
public final class e extends Message<e, a> {
    public static final ProtoAdapter<e> ADAPTER = new b();

    /* renamed from: a  reason: collision with root package name */
    private static final long f13638a = 0;
    @WireField(adapter = "com.bytedance.sync.protocal.BsyncProtocol#ADAPTER", label = WireField.Label.REPEATED, tag = 1)
    public final List<f> protocol;

    public e(List<f> list) {
        this(list, ByteString.EMPTY);
    }

    public e(List<f> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.protocol = Internal.immutableCopyOf("protocol", list);
    }

    @Override // com.squareup.wire.Message
    /* renamed from: newBuilder */
    public Message.Builder<e, a> newBuilder2() {
        a aVar = new a();
        aVar.protocol = Internal.copyOf("protocol", this.protocol);
        aVar.addUnknownFields(unknownFields());
        return aVar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            return unknownFields().equals(eVar.unknownFields()) && this.protocol.equals(eVar.protocol);
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.hashCode;
        if (i2 == 0) {
            int hashCode = (unknownFields().hashCode() * 37) + this.protocol.hashCode();
            this.hashCode = hashCode;
            return hashCode;
        }
        return i2;
    }

    @Override // com.squareup.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.protocol.isEmpty()) {
            sb.append(", protocol=").append(this.protocol);
        }
        return sb.replace(0, 2, "BsyncPipeline{").append('}').toString();
    }

    /* compiled from: BsyncPipeline.java */
    /* loaded from: classes3.dex */
    public static final class a extends Message.Builder<e, a> {
        public List<f> protocol = Internal.newMutableList();

        public a protocol(List<f> list) {
            Internal.checkElementsNotNull(list);
            this.protocol = list;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.Message.Builder
        public e build() {
            return new e(this.protocol, super.buildUnknownFields());
        }
    }

    /* compiled from: BsyncPipeline.java */
    /* loaded from: classes3.dex */
    private static final class b extends ProtoAdapter<e> {
        public b() {
            super(FieldEncoding.LENGTH_DELIMITED, e.class);
        }

        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(e eVar) {
            return f.ADAPTER.asRepeated().encodedSizeWithTag(1, eVar.protocol) + eVar.unknownFields().size();
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, e eVar) {
            f.ADAPTER.asRepeated().encodeWithTag(protoWriter, 1, eVar.protocol);
            protoWriter.writeBytes(eVar.unknownFields());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.ProtoAdapter
        public e decode(ProtoReader protoReader) {
            a aVar = new a();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return aVar.build();
                } else if (nextTag == 1) {
                    aVar.protocol.add(f.ADAPTER.decode(protoReader));
                } else {
                    FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                    aVar.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                }
            }
        }

        /* JADX WARN: Type inference failed for: r3v1, types: [com.bytedance.sync.protocal.e$a] */
        @Override // com.squareup.wire.ProtoAdapter
        public e redact(e eVar) {
            ?? newBuilder2 = eVar.newBuilder2();
            Internal.redactElements(newBuilder2.protocol, f.ADAPTER);
            newBuilder2.clearUnknownFields();
            return newBuilder2.build();
        }
    }
}
