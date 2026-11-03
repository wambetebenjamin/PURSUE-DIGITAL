package g.hades.downloader;

import com.bytedance.hades.downloader.api.data.DownloadErrorCode;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import javax.net.ssl.SSLException;
import okhttp3.internal.http2.StreamResetException;
/* loaded from: classes10.dex */
public class c {
    public static int a(IOException iOException) {
        return iOException instanceof ConnectException ? DownloadErrorCode.ERROR_UNKNOWN_NETWORK : iOException instanceof UnknownHostException ? DownloadErrorCode.ERROR_UNKNOWN_HOST : ((iOException instanceof NoRouteToHostException) || (iOException instanceof UnknownServiceException) || (iOException instanceof PortUnreachableException)) ? DownloadErrorCode.ERROR_UNKNOWN_NETWORK : iOException instanceof SocketTimeoutException ? DownloadErrorCode.ERROR_CONNECT_TIME_OUT : ((iOException instanceof SocketException) || (iOException instanceof HttpRetryException) || (iOException instanceof ProtocolException)) ? DownloadErrorCode.ERROR_UNKNOWN_NETWORK : iOException instanceof MalformedURLException ? DownloadErrorCode.ERROR_URL_INVALID : iOException instanceof FileNotFoundException ? DownloadErrorCode.ERROR_HTTP_404 : ((iOException instanceof InterruptedIOException) || (iOException instanceof UnsupportedEncodingException) || (iOException instanceof EOFException) || (iOException instanceof StreamResetException) || !(iOException instanceof SSLException)) ? DownloadErrorCode.ERROR_UNKNOWN_NETWORK : DownloadErrorCode.ERROR_UNKNOWN_HTTP;
    }
}
