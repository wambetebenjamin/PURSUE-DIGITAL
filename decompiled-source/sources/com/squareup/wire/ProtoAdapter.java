package com.squareup.wire;

import com.squareup.wire.Message;
import com.squareup.wire.WireField;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
/* loaded from: classes9.dex */
public abstract class ProtoAdapter<E> {
    public static final ProtoAdapter<ByteString> BYTES;
    public static final ProtoAdapter<Double> DOUBLE;
    public static final ProtoAdapter<Integer> FIXED32;
    public static final ProtoAdapter<Long> FIXED64;
    private static final int FIXED_32_SIZE = 4;
    private static final int FIXED_64_SIZE = 8;
    private static final int FIXED_BOOL_SIZE = 1;
    public static final ProtoAdapter<Float> FLOAT;
    public static final ProtoAdapter<Long> INT64;
    public static final ProtoAdapter<Integer> SFIXED32;
    public static final ProtoAdapter<Long> SFIXED64;
    public static final ProtoAdapter<Long> SINT64;
    public static final ProtoAdapter<String> STRING;
    public static final ProtoAdapter<Long> UINT64;
    private final FieldEncoding fieldEncoding;
    final Class<?> javaType;
    ProtoAdapter<List<E>> packedAdapter;
    ProtoAdapter<List<E>> repeatedAdapter;
    public static final ProtoAdapter<Boolean> BOOL = new ProtoAdapter<Boolean>(FieldEncoding.VARINT, Boolean.class) { // from class: com.squareup.wire.ProtoAdapter.1
        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(Boolean bool) {
            return 1;
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Boolean bool) throws IOException {
            protoWriter.writeVarint32(bool.booleanValue() ? 1 : 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.ProtoAdapter
        public Boolean decode(ProtoReader protoReader) throws IOException {
            int readVarint32 = protoReader.readVarint32();
            if (readVarint32 == 0) {
                return Boolean.FALSE;
            }
            if (readVarint32 == 1) {
                return Boolean.TRUE;
            }
            throw new IOException(String.format("Invalid boolean value 0x%02x", Integer.valueOf(readVarint32)));
        }
    };
    public static final ProtoAdapter<Integer> INT32 = new ProtoAdapter<Integer>(FieldEncoding.VARINT, Integer.class) { // from class: com.squareup.wire.ProtoAdapter.2
        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(Integer num) {
            return ProtoWriter.int32Size(num.intValue());
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Integer num) throws IOException {
            protoWriter.writeSignedVarint32(num.intValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.ProtoAdapter
        public Integer decode(ProtoReader protoReader) throws IOException {
            return Integer.valueOf(protoReader.readVarint32());
        }
    };
    public static final ProtoAdapter<Integer> UINT32 = new ProtoAdapter<Integer>(FieldEncoding.VARINT, Integer.class) { // from class: com.squareup.wire.ProtoAdapter.3
        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(Integer num) {
            return ProtoWriter.varint32Size(num.intValue());
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Integer num) throws IOException {
            protoWriter.writeVarint32(num.intValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.ProtoAdapter
        public Integer decode(ProtoReader protoReader) throws IOException {
            return Integer.valueOf(protoReader.readVarint32());
        }
    };
    public static final ProtoAdapter<Integer> SINT32 = new ProtoAdapter<Integer>(FieldEncoding.VARINT, Integer.class) { // from class: com.squareup.wire.ProtoAdapter.4
        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(Integer num) {
            return ProtoWriter.varint32Size(ProtoWriter.encodeZigZag32(num.intValue()));
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Integer num) throws IOException {
            protoWriter.writeVarint32(ProtoWriter.encodeZigZag32(num.intValue()));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.ProtoAdapter
        public Integer decode(ProtoReader protoReader) throws IOException {
            return Integer.valueOf(ProtoWriter.decodeZigZag32(protoReader.readVarint32()));
        }
    };

    public abstract E decode(ProtoReader protoReader) throws IOException;

    public abstract void encode(ProtoWriter protoWriter, E e2) throws IOException;

    public abstract int encodedSize(E e2);

    public E redact(E e2) {
        return null;
    }

    public ProtoAdapter(FieldEncoding fieldEncoding, Class<?> cls) {
        this.fieldEncoding = fieldEncoding;
        this.javaType = cls;
    }

    public static <M extends Message<M, B>, B extends Message.Builder<M, B>> ProtoAdapter<M> newMessageAdapter(Class<M> cls) {
        return RuntimeMessageAdapter.create(cls);
    }

    public static <E extends WireEnum> RuntimeEnumAdapter<E> newEnumAdapter(Class<E> cls) {
        return new RuntimeEnumAdapter<>(cls);
    }

    public static <K, V> ProtoAdapter<Map<K, V>> newMapAdapter(ProtoAdapter<K> protoAdapter, ProtoAdapter<V> protoAdapter2) {
        return new MapProtoAdapter(protoAdapter, protoAdapter2);
    }

    public static <M extends Message> ProtoAdapter<M> get(M m) {
        return get(m.getClass());
    }

    public static <M> ProtoAdapter<M> get(Class<M> cls) {
        try {
            return (ProtoAdapter) cls.getField("ADAPTER").get(null);
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            throw new IllegalArgumentException("failed to access " + cls.getName() + "#ADAPTER", e2);
        }
    }

    public static ProtoAdapter<?> get(String str) {
        try {
            int indexOf = str.indexOf(35);
            String substring = str.substring(0, indexOf);
            return (ProtoAdapter) Class.forName(substring).getField(str.substring(indexOf + 1)).get(null);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e2) {
            throw new IllegalArgumentException("failed to access " + str, e2);
        }
    }

    public int encodedSizeWithTag(int i2, E e2) {
        if (e2 == null) {
            return 0;
        }
        int encodedSize = encodedSize(e2);
        if (this.fieldEncoding == FieldEncoding.LENGTH_DELIMITED) {
            encodedSize += ProtoWriter.varint32Size(encodedSize);
        }
        return encodedSize + ProtoWriter.tagSize(i2);
    }

    public void encodeWithTag(ProtoWriter protoWriter, int i2, E e2) throws IOException {
        if (e2 == null) {
            return;
        }
        protoWriter.writeTag(i2, this.fieldEncoding);
        if (this.fieldEncoding == FieldEncoding.LENGTH_DELIMITED) {
            protoWriter.writeVarint32(encodedSize(e2));
        }
        encode(protoWriter, (ProtoWriter) e2);
    }

    public final void encode(BufferedSink bufferedSink, E e2) throws IOException {
        Preconditions.checkNotNull(e2, "value == null");
        Preconditions.checkNotNull(bufferedSink, "sink == null");
        encode(new ProtoWriter(bufferedSink), (ProtoWriter) e2);
    }

    public final byte[] encode(E e2) {
        Preconditions.checkNotNull(e2, "value == null");
        Buffer buffer = new Buffer();
        try {
            encode((BufferedSink) buffer, (Buffer) e2);
            return buffer.readByteArray();
        } catch (IOException e3) {
            throw new AssertionError(e3);
        }
    }

    public final void encode(OutputStream outputStream, E e2) throws IOException {
        Preconditions.checkNotNull(e2, "value == null");
        Preconditions.checkNotNull(outputStream, "stream == null");
        BufferedSink buffer = Okio.buffer(Okio.sink(outputStream));
        encode(buffer, (BufferedSink) e2);
        buffer.emit();
    }

    public final E decode(byte[] bArr) throws IOException {
        Preconditions.checkNotNull(bArr, "bytes == null");
        return decode(new Buffer().write(bArr));
    }

    public final E decode(ByteString byteString) throws IOException {
        Preconditions.checkNotNull(byteString, "bytes == null");
        return decode(new Buffer().write(byteString));
    }

    public final E decode(InputStream inputStream) throws IOException {
        Preconditions.checkNotNull(inputStream, "stream == null");
        return decode(Okio.buffer(Okio.source(inputStream)));
    }

    public final E decode(BufferedSource bufferedSource) throws IOException {
        Preconditions.checkNotNull(bufferedSource, "source == null");
        return decode(new ProtoReader(bufferedSource));
    }

    public String toString(E e2) {
        return e2.toString();
    }

    static {
        ProtoAdapter<Integer> protoAdapter = new ProtoAdapter<Integer>(FieldEncoding.FIXED32, Integer.class) { // from class: com.squareup.wire.ProtoAdapter.5
            @Override // com.squareup.wire.ProtoAdapter
            public int encodedSize(Integer num) {
                return 4;
            }

            @Override // com.squareup.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, Integer num) throws IOException {
                protoWriter.writeFixed32(num.intValue());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.ProtoAdapter
            public Integer decode(ProtoReader protoReader) throws IOException {
                return Integer.valueOf(protoReader.readFixed32());
            }
        };
        FIXED32 = protoAdapter;
        SFIXED32 = protoAdapter;
        INT64 = new ProtoAdapter<Long>(FieldEncoding.VARINT, Long.class) { // from class: com.squareup.wire.ProtoAdapter.6
            @Override // com.squareup.wire.ProtoAdapter
            public int encodedSize(Long l) {
                return ProtoWriter.varint64Size(l.longValue());
            }

            @Override // com.squareup.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, Long l) throws IOException {
                protoWriter.writeVarint64(l.longValue());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.ProtoAdapter
            public Long decode(ProtoReader protoReader) throws IOException {
                return Long.valueOf(protoReader.readVarint64());
            }
        };
        UINT64 = new ProtoAdapter<Long>(FieldEncoding.VARINT, Long.class) { // from class: com.squareup.wire.ProtoAdapter.7
            @Override // com.squareup.wire.ProtoAdapter
            public int encodedSize(Long l) {
                return ProtoWriter.varint64Size(l.longValue());
            }

            @Override // com.squareup.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, Long l) throws IOException {
                protoWriter.writeVarint64(l.longValue());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.ProtoAdapter
            public Long decode(ProtoReader protoReader) throws IOException {
                return Long.valueOf(protoReader.readVarint64());
            }
        };
        SINT64 = new ProtoAdapter<Long>(FieldEncoding.VARINT, Long.class) { // from class: com.squareup.wire.ProtoAdapter.8
            @Override // com.squareup.wire.ProtoAdapter
            public int encodedSize(Long l) {
                return ProtoWriter.varint64Size(ProtoWriter.encodeZigZag64(l.longValue()));
            }

            @Override // com.squareup.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, Long l) throws IOException {
                protoWriter.writeVarint64(ProtoWriter.encodeZigZag64(l.longValue()));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.ProtoAdapter
            public Long decode(ProtoReader protoReader) throws IOException {
                return Long.valueOf(ProtoWriter.decodeZigZag64(protoReader.readVarint64()));
            }
        };
        ProtoAdapter<Long> protoAdapter2 = new ProtoAdapter<Long>(FieldEncoding.FIXED64, Long.class) { // from class: com.squareup.wire.ProtoAdapter.9
            @Override // com.squareup.wire.ProtoAdapter
            public int encodedSize(Long l) {
                return 8;
            }

            @Override // com.squareup.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, Long l) throws IOException {
                protoWriter.writeFixed64(l.longValue());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.ProtoAdapter
            public Long decode(ProtoReader protoReader) throws IOException {
                return Long.valueOf(protoReader.readFixed64());
            }
        };
        FIXED64 = protoAdapter2;
        SFIXED64 = protoAdapter2;
        FLOAT = new ProtoAdapter<Float>(FieldEncoding.FIXED32, Float.class) { // from class: com.squareup.wire.ProtoAdapter.10
            @Override // com.squareup.wire.ProtoAdapter
            public int encodedSize(Float f2) {
                return 4;
            }

            @Override // com.squareup.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, Float f2) throws IOException {
                protoWriter.writeFixed32(Float.floatToIntBits(f2.floatValue()));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.ProtoAdapter
            public Float decode(ProtoReader protoReader) throws IOException {
                return Float.valueOf(Float.intBitsToFloat(protoReader.readFixed32()));
            }
        };
        DOUBLE = new ProtoAdapter<Double>(FieldEncoding.FIXED64, Double.class) { // from class: com.squareup.wire.ProtoAdapter.11
            @Override // com.squareup.wire.ProtoAdapter
            public int encodedSize(Double d2) {
                return 8;
            }

            @Override // com.squareup.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, Double d2) throws IOException {
                protoWriter.writeFixed64(Double.doubleToLongBits(d2.doubleValue()));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.ProtoAdapter
            public Double decode(ProtoReader protoReader) throws IOException {
                return Double.valueOf(Double.longBitsToDouble(protoReader.readFixed64()));
            }
        };
        STRING = new ProtoAdapter<String>(FieldEncoding.LENGTH_DELIMITED, String.class) { // from class: com.squareup.wire.ProtoAdapter.12
            @Override // com.squareup.wire.ProtoAdapter
            public int encodedSize(String str) {
                return ProtoWriter.utf8Length(str);
            }

            @Override // com.squareup.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, String str) throws IOException {
                protoWriter.writeString(str);
            }

            @Override // com.squareup.wire.ProtoAdapter
            public String decode(ProtoReader protoReader) throws IOException {
                return protoReader.readString();
            }
        };
        BYTES = new ProtoAdapter<ByteString>(FieldEncoding.LENGTH_DELIMITED, ByteString.class) { // from class: com.squareup.wire.ProtoAdapter.13
            @Override // com.squareup.wire.ProtoAdapter
            public int encodedSize(ByteString byteString) {
                return byteString.size();
            }

            @Override // com.squareup.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, ByteString byteString) throws IOException {
                protoWriter.writeBytes(byteString);
            }

            @Override // com.squareup.wire.ProtoAdapter
            public ByteString decode(ProtoReader protoReader) throws IOException {
                return protoReader.readBytes();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public ProtoAdapter<?> withLabel(WireField.Label label) {
        if (label.isRepeated()) {
            if (label.isPacked()) {
                return asPacked();
            }
            return asRepeated();
        }
        return this;
    }

    public final ProtoAdapter<List<E>> asPacked() {
        ProtoAdapter<List<E>> protoAdapter = this.packedAdapter;
        if (protoAdapter != null) {
            return protoAdapter;
        }
        ProtoAdapter<List<E>> createPacked = createPacked();
        this.packedAdapter = createPacked;
        return createPacked;
    }

    public final ProtoAdapter<List<E>> asRepeated() {
        ProtoAdapter<List<E>> protoAdapter = this.repeatedAdapter;
        if (protoAdapter != null) {
            return protoAdapter;
        }
        ProtoAdapter<List<E>> createRepeated = createRepeated();
        this.repeatedAdapter = createRepeated;
        return createRepeated;
    }

    private ProtoAdapter<List<E>> createPacked() {
        if (this.fieldEncoding == FieldEncoding.LENGTH_DELIMITED) {
            throw new IllegalArgumentException("Unable to pack a length-delimited type.");
        }
        return new ProtoAdapter<List<E>>(FieldEncoding.LENGTH_DELIMITED, List.class) { // from class: com.squareup.wire.ProtoAdapter.14
            @Override // com.squareup.wire.ProtoAdapter
            public /* bridge */ /* synthetic */ void encode(ProtoWriter protoWriter, Object obj) throws IOException {
                encode(protoWriter, (List) ((List) obj));
            }

            @Override // com.squareup.wire.ProtoAdapter
            public /* bridge */ /* synthetic */ void encodeWithTag(ProtoWriter protoWriter, int i2, Object obj) throws IOException {
                encodeWithTag(protoWriter, i2, (List) ((List) obj));
            }

            @Override // com.squareup.wire.ProtoAdapter
            public /* bridge */ /* synthetic */ int encodedSize(Object obj) {
                return encodedSize((List) ((List) obj));
            }

            @Override // com.squareup.wire.ProtoAdapter
            public /* bridge */ /* synthetic */ int encodedSizeWithTag(int i2, Object obj) {
                return encodedSizeWithTag(i2, (List) ((List) obj));
            }

            @Override // com.squareup.wire.ProtoAdapter
            public /* bridge */ /* synthetic */ Object redact(Object obj) {
                return redact((List) ((List) obj));
            }

            public void encodeWithTag(ProtoWriter protoWriter, int i2, List<E> list) throws IOException {
                if (list.isEmpty()) {
                    return;
                }
                super.encodeWithTag(protoWriter, i2, (int) list);
            }

            public int encodedSize(List<E> list) {
                int size = list.size();
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    i2 += ProtoAdapter.this.encodedSize(list.get(i3));
                }
                return i2;
            }

            public int encodedSizeWithTag(int i2, List<E> list) {
                if (list.isEmpty()) {
                    return 0;
                }
                return super.encodedSizeWithTag(i2, (int) list);
            }

            public void encode(ProtoWriter protoWriter, List<E> list) throws IOException {
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ProtoAdapter.this.encode(protoWriter, (ProtoWriter) list.get(i2));
                }
            }

            @Override // com.squareup.wire.ProtoAdapter
            public List<E> decode(ProtoReader protoReader) throws IOException {
                return Collections.singletonList(ProtoAdapter.this.decode(protoReader));
            }

            public List<E> redact(List<E> list) {
                return Collections.emptyList();
            }
        };
    }

    private ProtoAdapter<List<E>> createRepeated() {
        return new ProtoAdapter<List<E>>(this.fieldEncoding, List.class) { // from class: com.squareup.wire.ProtoAdapter.15
            @Override // com.squareup.wire.ProtoAdapter
            public /* bridge */ /* synthetic */ void encode(ProtoWriter protoWriter, Object obj) throws IOException {
                encode(protoWriter, (List) ((List) obj));
            }

            @Override // com.squareup.wire.ProtoAdapter
            public /* bridge */ /* synthetic */ void encodeWithTag(ProtoWriter protoWriter, int i2, Object obj) throws IOException {
                encodeWithTag(protoWriter, i2, (List) ((List) obj));
            }

            @Override // com.squareup.wire.ProtoAdapter
            public /* bridge */ /* synthetic */ int encodedSize(Object obj) {
                return encodedSize((List) ((List) obj));
            }

            @Override // com.squareup.wire.ProtoAdapter
            public /* bridge */ /* synthetic */ int encodedSizeWithTag(int i2, Object obj) {
                return encodedSizeWithTag(i2, (List) ((List) obj));
            }

            @Override // com.squareup.wire.ProtoAdapter
            public /* bridge */ /* synthetic */ Object redact(Object obj) {
                return redact((List) ((List) obj));
            }

            public int encodedSize(List<E> list) {
                throw new UnsupportedOperationException("Repeated values can only be sized with a tag.");
            }

            public int encodedSizeWithTag(int i2, List<E> list) {
                int size = list.size();
                int i3 = 0;
                for (int i4 = 0; i4 < size; i4++) {
                    i3 += ProtoAdapter.this.encodedSizeWithTag(i2, list.get(i4));
                }
                return i3;
            }

            public void encode(ProtoWriter protoWriter, List<E> list) {
                throw new UnsupportedOperationException("Repeated values can only be encoded with a tag.");
            }

            public void encodeWithTag(ProtoWriter protoWriter, int i2, List<E> list) throws IOException {
                int size = list.size();
                for (int i3 = 0; i3 < size; i3++) {
                    ProtoAdapter.this.encodeWithTag(protoWriter, i2, list.get(i3));
                }
            }

            @Override // com.squareup.wire.ProtoAdapter
            public List<E> decode(ProtoReader protoReader) throws IOException {
                return Collections.singletonList(ProtoAdapter.this.decode(protoReader));
            }

            public List<E> redact(List<E> list) {
                return Collections.emptyList();
            }
        };
    }

    /* loaded from: classes9.dex */
    public static final class EnumConstantNotFoundException extends IllegalArgumentException {
        public final int value;

        /* JADX INFO: Access modifiers changed from: package-private */
        public EnumConstantNotFoundException(int i2, Class<?> cls) {
            super("Unknown enum tag " + i2 + " for " + cls.getCanonicalName());
            this.value = i2;
        }
    }

    /* loaded from: classes9.dex */
    private static final class MapProtoAdapter<K, V> extends ProtoAdapter<Map<K, V>> {
        private final MapEntryProtoAdapter<K, V> entryAdapter;

        @Override // com.squareup.wire.ProtoAdapter
        public /* bridge */ /* synthetic */ void encode(ProtoWriter protoWriter, Object obj) throws IOException {
            encode(protoWriter, (Map) ((Map) obj));
        }

        @Override // com.squareup.wire.ProtoAdapter
        public /* bridge */ /* synthetic */ void encodeWithTag(ProtoWriter protoWriter, int i2, Object obj) throws IOException {
            encodeWithTag(protoWriter, i2, (Map) ((Map) obj));
        }

        @Override // com.squareup.wire.ProtoAdapter
        public /* bridge */ /* synthetic */ int encodedSize(Object obj) {
            return encodedSize((Map) ((Map) obj));
        }

        @Override // com.squareup.wire.ProtoAdapter
        public /* bridge */ /* synthetic */ int encodedSizeWithTag(int i2, Object obj) {
            return encodedSizeWithTag(i2, (Map) ((Map) obj));
        }

        @Override // com.squareup.wire.ProtoAdapter
        public /* bridge */ /* synthetic */ Object redact(Object obj) {
            return redact((Map) ((Map) obj));
        }

        MapProtoAdapter(ProtoAdapter<K> protoAdapter, ProtoAdapter<V> protoAdapter2) {
            super(FieldEncoding.LENGTH_DELIMITED, null);
            this.entryAdapter = new MapEntryProtoAdapter<>(protoAdapter, protoAdapter2);
        }

        public int encodedSize(Map<K, V> map) {
            throw new UnsupportedOperationException("Repeated values can only be sized with a tag.");
        }

        public int encodedSizeWithTag(int i2, Map<K, V> map) {
            int i3 = 0;
            for (Map.Entry<K, V> entry : map.entrySet()) {
                i3 += this.entryAdapter.encodedSizeWithTag(i2, entry);
            }
            return i3;
        }

        public void encode(ProtoWriter protoWriter, Map<K, V> map) {
            throw new UnsupportedOperationException("Repeated values can only be encoded with a tag.");
        }

        public void encodeWithTag(ProtoWriter protoWriter, int i2, Map<K, V> map) throws IOException {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                this.entryAdapter.encodeWithTag(protoWriter, i2, entry);
            }
        }

        @Override // com.squareup.wire.ProtoAdapter
        public Map<K, V> decode(ProtoReader protoReader) throws IOException {
            long beginMessage = protoReader.beginMessage();
            K k = null;
            V v = null;
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    break;
                } else if (nextTag == 1) {
                    k = this.entryAdapter.keyAdapter.decode(protoReader);
                } else if (nextTag == 2) {
                    v = this.entryAdapter.valueAdapter.decode(protoReader);
                }
            }
            protoReader.endMessage(beginMessage);
            if (k != null) {
                if (v == null) {
                    throw new IllegalStateException("Map entry with null value");
                }
                return Collections.singletonMap(k, v);
            }
            throw new IllegalStateException("Map entry with null key");
        }

        public Map<K, V> redact(Map<K, V> map) {
            return Collections.emptyMap();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class MapEntryProtoAdapter<K, V> extends ProtoAdapter<Map.Entry<K, V>> {
        final ProtoAdapter<K> keyAdapter;
        final ProtoAdapter<V> valueAdapter;

        @Override // com.squareup.wire.ProtoAdapter
        public /* bridge */ /* synthetic */ void encode(ProtoWriter protoWriter, Object obj) throws IOException {
            encode(protoWriter, (Map.Entry) ((Map.Entry) obj));
        }

        @Override // com.squareup.wire.ProtoAdapter
        public /* bridge */ /* synthetic */ int encodedSize(Object obj) {
            return encodedSize((Map.Entry) ((Map.Entry) obj));
        }

        MapEntryProtoAdapter(ProtoAdapter<K> protoAdapter, ProtoAdapter<V> protoAdapter2) {
            super(FieldEncoding.LENGTH_DELIMITED, null);
            this.keyAdapter = protoAdapter;
            this.valueAdapter = protoAdapter2;
        }

        public int encodedSize(Map.Entry<K, V> entry) {
            return this.keyAdapter.encodedSizeWithTag(1, entry.getKey()) + this.valueAdapter.encodedSizeWithTag(2, entry.getValue());
        }

        public void encode(ProtoWriter protoWriter, Map.Entry<K, V> entry) throws IOException {
            this.keyAdapter.encodeWithTag(protoWriter, 1, entry.getKey());
            this.valueAdapter.encodeWithTag(protoWriter, 2, entry.getValue());
        }

        @Override // com.squareup.wire.ProtoAdapter
        public Map.Entry<K, V> decode(ProtoReader protoReader) {
            throw new UnsupportedOperationException();
        }
    }
}
