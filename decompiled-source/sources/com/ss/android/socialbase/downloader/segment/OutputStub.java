package com.ss.android.socialbase.downloader.segment;
/* loaded from: classes9.dex */
class OutputStub implements IOutput {
    private final IOutput output;
    private final IOutput target;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OutputStub(IOutput iOutput, IOutput iOutput2) {
        this.output = iOutput;
        this.target = iOutput2;
    }

    @Override // com.ss.android.socialbase.downloader.segment.IOutput
    public void write(Buffer buffer) {
        buffer.output = this.target;
        this.output.write(buffer);
    }
}
