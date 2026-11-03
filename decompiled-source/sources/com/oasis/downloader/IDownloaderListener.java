package com.oasis.downloader;
/* loaded from: classes9.dex */
public interface IDownloaderListener {
    default void onComplete(boolean z, String str) {
    }

    default void onMultiComplete(boolean z, String str) {
    }

    default void onMultiPause(String str) {
    }

    default void onMultiProgress(String str) {
    }

    default void onMultiResume(String str) {
    }

    default void onMultiStart(String str) {
    }

    default void onMultiStop(String str) {
    }

    default void onPause(String str) {
    }

    default void onProgress(String str) {
    }

    default void onResume(String str) {
    }

    default void onStart(String str) {
    }

    default void onStop(boolean z, String str) {
    }
}
