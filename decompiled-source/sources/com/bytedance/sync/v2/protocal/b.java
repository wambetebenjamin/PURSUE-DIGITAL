package com.bytedance.sync.v2.protocal;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.util.Map;
import okio.ByteString;
/* compiled from: BsyncHeader.java */
/* loaded from: classes3.dex */
public final class b extends Message<b, a> {
    public static final String DEFAULT_DID = "";
    public static final String DEFAULT_UID = "";

    /* renamed from: a  reason: collision with root package name */
    private static final long f13932a = 0;
    @WireField(adapter = "com.bytedance.sync.v2.protocal.Ctrl#ADAPTER", tag = 5)
    public final i ctrl;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String did;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BYTES", keyAdapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 255)
    public final Map<Integer, ByteString> infos;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 4)
    public final Long ts;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String uid;
    @WireField(adapter = "com.bytedance.sync.v2.protocal.Version#ADAPTER", label = WireField.Label.REQUIRED, tag = 1)
    public final q version;
    public static final ProtoAdapter<b> ADAPTER = new C0216b();
    public static final q DEFAULT_VERSION = q.Unknown;
    public static final Long DEFAULT_TS = 0L;
    public static final i DEFAULT_CTRL = i.Default;

    public b(q qVar, String str, String str2, Long l, i iVar, Map<Integer, ByteString> map) {
        this(qVar, str, str2, l, iVar, map, ByteString.EMPTY);
    }

    public b(q qVar, String str, String str2, Long l, i iVar, Map<Integer, ByteString> map, ByteString byteString) {
        super(ADAPTER, byteString);
        this.version = qVar;
        this.uid = str;
        this.did = str2;
        this.ts = l;
        this.ctrl = iVar;
        this.infos = Internal.immutableCopyOf("infos", map);
    }

    @Override // com.squareup.wire.Message
    /* renamed from: newBuilder */
    public Message.Builder<b, a> newBuilder2() {
        a aVar = new a();
        aVar.version = this.version;
        aVar.uid = this.uid;
        aVar.did = this.did;
        aVar.ts = this.ts;
        aVar.ctrl = this.ctrl;
        aVar.infos = Internal.copyOf("infos", this.infos);
        aVar.addUnknownFields(unknownFields());
        return aVar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            return unknownFields().equals(bVar.unknownFields()) && this.version.equals(bVar.version) && Internal.equals(this.uid, bVar.uid) && Internal.equals(this.did, bVar.did) && Internal.equals(this.ts, bVar.ts) && Internal.equals(this.ctrl, bVar.ctrl) && this.infos.equals(bVar.infos);
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.hashCode;
        if (i2 == 0) {
            int hashCode = ((unknownFields().hashCode() * 37) + this.version.hashCode()) * 37;
            String str = this.uid;
            int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 37;
            String str2 = this.did;
            int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 37;
            Long l = this.ts;
            int hashCode4 = (hashCode3 + (l != null ? l.hashCode() : 0)) * 37;
            i iVar = this.ctrl;
            int hashCode5 = ((hashCode4 + (iVar != null ? iVar.hashCode() : 0)) * 37) + this.infos.hashCode();
            this.hashCode = hashCode5;
            return hashCode5;
        }
        return i2;
    }

    @Override // com.squareup.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", version=").append(this.version);
        if (this.uid != null) {
            sb.append(", uid=").append(this.uid);
        }
        if (this.did != null) {
            sb.append(", did=").append(this.did);
        }
        if (this.ts != null) {
            sb.append(", ts=").append(this.ts);
        }
        if (this.ctrl != null) {
            sb.append(", ctrl=").append(this.ctrl);
        }
        if (!this.infos.isEmpty()) {
            sb.append(", infos=").append(this.infos);
        }
        return sb.replace(0, 2, "BsyncHeader{").append('}').toString();
    }

    /* compiled from: BsyncHeader.java */
    /* loaded from: classes3.dex */
    public static final class a extends Message.Builder<b, a> {
        public i ctrl;
        public String did;
        public Map<Integer, ByteString> infos = Internal.newMutableMap();
        public Long ts;
        public String uid;
        public q version;

        public a version(q qVar) {
            this.version = qVar;
            return this;
        }

        public a uid(String str) {
            this.uid = str;
            return this;
        }

        public a did(String str) {
            this.did = str;
            return this;
        }

        public a ts(Long l) {
            this.ts = l;
            return this;
        }

        public a ctrl(i iVar) {
            this.ctrl = iVar;
            return this;
        }

        public a infos(Map<Integer, ByteString> map) {
            Internal.checkElementsNotNull(map);
            this.infos = map;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.Message.Builder
        public b build() {
            q qVar = this.version;
            if (qVar == null) {
                throw Internal.missingRequiredFields(qVar, "version");
            }
            return new b(this.version, this.uid, this.did, this.ts, this.ctrl, this.infos, super.buildUnknownFields());
        }
    }

    /* compiled from: BsyncHeader.java */
    /* renamed from: com.bytedance.sync.v2.protocal.b$b  reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    private static final class C0216b extends ProtoAdapter<b> {

        /* renamed from: a  reason: collision with root package name */
        private final ProtoAdapter<Map<Integer, ByteString>> f13933a;

        public C0216b() {
            super(FieldEncoding.LENGTH_DELIMITED, b.class);
            this.f13933a = ProtoAdapter.newMapAdapter(ProtoAdapter.INT32, ProtoAdapter.BYTES);
        }

        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(b bVar) {
            return q.ADAPTER.encodedSizeWithTag(1, bVar.version) + ProtoAdapter.STRING.encodedSizeWithTag(2, bVar.uid) + ProtoAdapter.STRING.encodedSizeWithTag(3, bVar.did) + ProtoAdapter.INT64.encodedSizeWithTag(4, bVar.ts) + i.ADAPTER.encodedSizeWithTag(5, bVar.ctrl) + this.f13933a.encodedSizeWithTag(255, bVar.infos) + bVar.unknownFields().size();
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, b bVar) {
            q.ADAPTER.encodeWithTag(protoWriter, 1, bVar.version);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, bVar.uid);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, bVar.did);
            ProtoAdapter.INT64.encodeWithTag(protoWriter, 4, bVar.ts);
            i.ADAPTER.encodeWithTag(protoWriter, 5, bVar.ctrl);
            this.f13933a.encodeWithTag(protoWriter, 255, bVar.infos);
            protoWriter.writeBytes(bVar.unknownFields());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.ProtoAdapter
        public b decode(ProtoReader protoReader) {
            a aVar = new a();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return aVar.build();
                } else if (nextTag == 1) {
                    try {
                        aVar.version(q.ADAPTER.decode(protoReader));
                    } catch (ProtoAdapter.EnumConstantNotFoundException e2) {
                        aVar.addUnknownField(nextTag, FieldEncoding.VARINT, Long.valueOf(e2.value));
                    }
                } else if (nextTag == 2) {
                    aVar.uid(ProtoAdapter.STRING.decode(protoReader));
                } else if (nextTag == 3) {
                    aVar.did(ProtoAdapter.STRING.decode(protoReader));
                } else if (nextTag == 4) {
                    aVar.ts(ProtoAdapter.INT64.decode(protoReader));
                } else if (nextTag == 5) {
                    try {
                        aVar.ctrl(i.ADAPTER.decode(protoReader));
                    } catch (ProtoAdapter.EnumConstantNotFoundException e3) {
                        aVar.addUnknownField(nextTag, FieldEncoding.VARINT, Long.valueOf(e3.value));
                    }
                } else if (nextTag == 255) {
                    aVar.infos.putAll(this.f13933a.decode(protoReader));
                } else {
                    FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                    aVar.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                }
            }
        }

        @Override // com.squareup.wire.ProtoAdapter
        public b redact(b bVar) {
            Message.Builder<b, a> newBuilder2 = bVar.newBuilder2();
            newBuilder2.clearUnknownFields();
            return newBuilder2.build();
        }
    }
}
