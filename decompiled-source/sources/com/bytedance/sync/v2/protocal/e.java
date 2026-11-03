package com.bytedance.sync.v2.protocal;

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
public final class e extends Message<e, a> {
    public static final ProtoAdapter<e> ADAPTER = new b();

    /* renamed from: a  reason: collision with root package name */
    private static final long f13936a = 0;
    @WireField(adapter = "com.bytedance.sync.v2.protocal.BsyncHeader#ADAPTER", label = WireField.Label.REQUIRED, tag = 1)
    public final com.bytedance.sync.v2.protocal.b header;
    @WireField(adapter = "com.bytedance.sync.v2.protocal.BsyncTopic#ADAPTER", label = WireField.Label.REPEATED, tag = 2)
    public final List<f> topics;

    public e(com.bytedance.sync.v2.protocal.b bVar, List<f> list) {
        this(bVar, list, ByteString.EMPTY);
    }

    public e(com.bytedance.sync.v2.protocal.b bVar, List<f> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.header = bVar;
        this.topics = Internal.immutableCopyOf("topics", list);
    }

    @Override // com.squareup.wire.Message
    /* renamed from: newBuilder */
    public Message.Builder<e, a> newBuilder2() {
        a aVar = new a();
        aVar.header = this.header;
        aVar.topics = Internal.copyOf("topics", this.topics);
        aVar.addUnknownFields(unknownFields());
        return aVar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            return unknownFields().equals(eVar.unknownFields()) && this.header.equals(eVar.header) && this.topics.equals(eVar.topics);
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.hashCode;
        if (i2 == 0) {
            int hashCode = (((unknownFields().hashCode() * 37) + this.header.hashCode()) * 37) + this.topics.hashCode();
            this.hashCode = hashCode;
            return hashCode;
        }
        return i2;
    }

    @Override // com.squareup.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", header=").append(this.header);
        if (!this.topics.isEmpty()) {
            sb.append(", topics=").append(this.topics);
        }
        return sb.replace(0, 2, "BsyncProtocol{").append('}').toString();
    }

    /* compiled from: BsyncProtocol.java */
    /* loaded from: classes3.dex */
    public static final class a extends Message.Builder<e, a> {
        public com.bytedance.sync.v2.protocal.b header;
        public List<f> topics = Internal.newMutableList();

        public a header(com.bytedance.sync.v2.protocal.b bVar) {
            this.header = bVar;
            return this;
        }

        public a topics(List<f> list) {
            Internal.checkElementsNotNull(list);
            this.topics = list;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.Message.Builder
        public e build() {
            com.bytedance.sync.v2.protocal.b bVar = this.header;
            if (bVar == null) {
                throw Internal.missingRequiredFields(bVar, "header");
            }
            return new e(this.header, this.topics, super.buildUnknownFields());
        }
    }

    /* compiled from: BsyncProtocol.java */
    /* loaded from: classes3.dex */
    private static final class b extends ProtoAdapter<e> {
        public b() {
            super(FieldEncoding.LENGTH_DELIMITED, e.class);
        }

        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(e eVar) {
            return com.bytedance.sync.v2.protocal.b.ADAPTER.encodedSizeWithTag(1, eVar.header) + f.ADAPTER.asRepeated().encodedSizeWithTag(2, eVar.topics) + eVar.unknownFields().size();
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, e eVar) {
            com.bytedance.sync.v2.protocal.b.ADAPTER.encodeWithTag(protoWriter, 1, eVar.header);
            f.ADAPTER.asRepeated().encodeWithTag(protoWriter, 2, eVar.topics);
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
                    aVar.header(com.bytedance.sync.v2.protocal.b.ADAPTER.decode(protoReader));
                } else if (nextTag == 2) {
                    aVar.topics.add(f.ADAPTER.decode(protoReader));
                } else {
                    FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                    aVar.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                }
            }
        }

        /* JADX WARN: Type inference failed for: r3v1, types: [com.bytedance.sync.v2.protocal.e$a] */
        @Override // com.squareup.wire.ProtoAdapter
        public e redact(e eVar) {
            ?? newBuilder2 = eVar.newBuilder2();
            newBuilder2.header = com.bytedance.sync.v2.protocal.b.ADAPTER.redact(newBuilder2.header);
            Internal.redactElements(newBuilder2.topics, f.ADAPTER);
            newBuilder2.clearUnknownFields();
            return newBuilder2.build();
        }
    }
}
