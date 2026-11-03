package com.google.firebase.encoders;

import java.io.IOException;
/* loaded from: classes9.dex */
public interface ObjectEncoderContext {
    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, double d2) throws IOException;

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, float f2) throws IOException;

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, int i2) throws IOException;

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, long j2) throws IOException;

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, Object obj) throws IOException;

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, boolean z) throws IOException;

    @Deprecated
    ObjectEncoderContext add(String str, double d2) throws IOException;

    @Deprecated
    ObjectEncoderContext add(String str, int i2) throws IOException;

    @Deprecated
    ObjectEncoderContext add(String str, long j2) throws IOException;

    @Deprecated
    ObjectEncoderContext add(String str, Object obj) throws IOException;

    @Deprecated
    ObjectEncoderContext add(String str, boolean z) throws IOException;

    ObjectEncoderContext inline(Object obj) throws IOException;

    ObjectEncoderContext nested(FieldDescriptor fieldDescriptor) throws IOException;

    ObjectEncoderContext nested(String str) throws IOException;
}
