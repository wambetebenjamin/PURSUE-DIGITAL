package com.ss.android.socialbase.downloader.exception;

import android.os.Parcel;
import android.os.Parcelable;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
/* loaded from: classes9.dex */
public class BaseException extends Exception implements Parcelable {
    public static final Parcelable.Creator<BaseException> CREATOR = new Parcelable.Creator<BaseException>() { // from class: com.ss.android.socialbase.downloader.exception.BaseException.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseException createFromParcel(Parcel parcel) {
            return new BaseException(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseException[] newArray(int i2) {
            return new BaseException[i2];
        }
    };
    private static final String TAG = "[d-ex]:";
    private int errorCode;
    private String errorMsg;
    private String extraInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BaseException() {
        this.extraInfo = "";
    }

    public BaseException(int i2, String str) {
        super(TAG + str);
        this.extraInfo = "";
        this.errorMsg = TAG + str;
        this.errorCode = i2;
    }

    public void setErrorMsg(String str) {
        this.errorMsg = str;
    }

    public BaseException(int i2, Throwable th) {
        this(i2, DownloadUtils.getThrowableMsg(th));
    }

    public BaseException(int i2, String str, Throwable th) {
        super(TAG + str, th);
        this.extraInfo = "";
        this.errorMsg = TAG + str;
        this.errorCode = i2;
    }

    protected BaseException(Parcel parcel) {
        this.extraInfo = "";
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.errorCode = parcel.readInt();
        this.errorMsg = parcel.readString();
        this.extraInfo = parcel.readString();
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMsg;
    }

    public String getExtraInfo() {
        return this.extraInfo;
    }

    public void setExtraInfo(String str) {
        this.extraInfo = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.errorCode);
        parcel.writeString(this.errorMsg);
        parcel.writeString(this.extraInfo);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "BaseException{errorCode=" + this.errorCode + ", errorMsg='" + this.errorMsg + "'}";
    }
}
