package com.squareup.wire;

import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
import java.io.IOException;
/* loaded from: classes9.dex */
public abstract class EnumAdapter<E extends WireEnum> extends ProtoAdapter<E> {
    protected abstract E fromValue(int i2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.squareup.wire.ProtoAdapter
    public /* bridge */ /* synthetic */ void encode(ProtoWriter protoWriter, Object obj) throws IOException {
        encode(protoWriter, (ProtoWriter) ((WireEnum) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.squareup.wire.ProtoAdapter
    public /* bridge */ /* synthetic */ int encodedSize(Object obj) {
        return encodedSize((EnumAdapter<E>) ((WireEnum) obj));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EnumAdapter(Class<E> cls) {
        super(FieldEncoding.VARINT, cls);
    }

    public final int encodedSize(E e2) {
        return ProtoWriter.varint32Size(e2.getValue());
    }

    public final void encode(ProtoWriter protoWriter, E e2) throws IOException {
        protoWriter.writeVarint32(e2.getValue());
    }

    @Override // com.squareup.wire.ProtoAdapter
    public final E decode(ProtoReader protoReader) throws IOException {
        int readVarint32 = protoReader.readVarint32();
        E fromValue = fromValue(readVarint32);
        if (fromValue != null) {
            return fromValue;
        }
        throw new ProtoAdapter.EnumConstantNotFoundException(readVarint32, this.javaType);
    }
}
