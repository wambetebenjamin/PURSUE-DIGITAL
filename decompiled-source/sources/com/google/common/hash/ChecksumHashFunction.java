package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.zip.Checksum;
/* JADX INFO: Access modifiers changed from: package-private */
@Immutable
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public final class ChecksumHashFunction extends AbstractHashFunction implements Serializable {
    private static final long serialVersionUID = 0;
    private final int bits;
    private final ImmutableSupplier<? extends Checksum> checksumSupplier;
    private final String toString;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChecksumHashFunction(ImmutableSupplier<? extends Checksum> immutableSupplier, int i2, String str) {
        this.checksumSupplier = (ImmutableSupplier) Preconditions.checkNotNull(immutableSupplier);
        Preconditions.checkArgument(i2 == 32 || i2 == 64, "bits (%s) must be either 32 or 64", i2);
        this.bits = i2;
        this.toString = (String) Preconditions.checkNotNull(str);
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return this.bits;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new ChecksumHasher(this.checksumSupplier.get());
    }

    public String toString() {
        return this.toString;
    }

    /* loaded from: classes9.dex */
    private final class ChecksumHasher extends AbstractByteHasher {
        private final Checksum checksum;

        private ChecksumHasher(Checksum checksum) {
            this.checksum = (Checksum) Preconditions.checkNotNull(checksum);
        }

        @Override // com.google.common.hash.AbstractByteHasher
        protected void update(byte b2) {
            this.checksum.update(b2);
        }

        @Override // com.google.common.hash.AbstractByteHasher
        protected void update(byte[] bArr, int i2, int i3) {
            this.checksum.update(bArr, i2, i3);
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            long value = this.checksum.getValue();
            if (ChecksumHashFunction.this.bits == 32) {
                return HashCode.fromInt((int) value);
            }
            return HashCode.fromLong(value);
        }
    }
}
