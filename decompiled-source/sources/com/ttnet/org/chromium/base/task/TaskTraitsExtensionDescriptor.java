package com.ttnet.org.chromium.base.task;
/* loaded from: classes9.dex */
public interface TaskTraitsExtensionDescriptor<Extension> {
    Extension fromSerializedData(byte[] bArr);

    int getId();

    byte[] toSerializedData(Extension extension);
}
