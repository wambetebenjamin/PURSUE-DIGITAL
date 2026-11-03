package okhttp3.internal.cache;

import java.io.IOException;
import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;
/* loaded from: classes10.dex */
class FaultHidingSink extends ForwardingSink {
    private boolean hasErrors;

    protected void onException(IOException iOException) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FaultHidingSink(Sink sink) {
        super(sink);
    }

    @Override // okio.ForwardingSink, okio.Sink
    public void write(Buffer buffer, long j2) throws IOException {
        if (this.hasErrors) {
            buffer.skip(j2);
            return;
        }
        try {
            super.write(buffer, j2);
        } catch (IOException e2) {
            this.hasErrors = true;
            onException(e2);
        }
    }

    @Override // okio.ForwardingSink, okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        if (this.hasErrors) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e2) {
            this.hasErrors = true;
            onException(e2);
        }
    }

    @Override // okio.ForwardingSink, okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.hasErrors) {
            return;
        }
        try {
            super.close();
        } catch (IOException e2) {
            this.hasErrors = true;
            onException(e2);
        }
    }
}
