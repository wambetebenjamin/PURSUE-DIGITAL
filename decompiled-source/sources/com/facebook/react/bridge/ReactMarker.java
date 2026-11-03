package com.facebook.react.bridge;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes8.dex */
public class ReactMarker {
    private static final List<MarkerListener> sListeners = new CopyOnWriteArrayList();
    private static final List<FabricMarkerListener> sFabricMarkerListeners = new CopyOnWriteArrayList();

    /* loaded from: classes8.dex */
    public interface FabricMarkerListener {
        void logFabricMarker(ReactMarkerConstants reactMarkerConstants, String str, int i2, long j2);
    }

    /* loaded from: classes8.dex */
    public interface MarkerListener {
        void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int i2);
    }

    public static void addFabricListener(FabricMarkerListener fabricMarkerListener) {
    }

    public static void addListener(MarkerListener markerListener) {
    }

    public static void logFabricMarker(ReactMarkerConstants reactMarkerConstants, String str, int i2) {
    }

    public static void logFabricMarker(ReactMarkerConstants reactMarkerConstants, String str, int i2, long j2) {
    }

    public static void logMarker(ReactMarkerConstants reactMarkerConstants) {
    }

    public static void logMarker(ReactMarkerConstants reactMarkerConstants, int i2) {
    }

    public static void logMarker(ReactMarkerConstants reactMarkerConstants, String str) {
    }

    public static void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int i2) {
    }

    public static void logMarker(String str) {
    }

    public static void logMarker(String str, int i2) {
    }

    public static void logMarker(String str, String str2) {
    }

    public static void logMarker(String str, String str2, int i2) {
    }

    public static void removeListener(MarkerListener markerListener) {
        sListeners.remove(markerListener);
    }

    public static void clearMarkerListeners() {
        sListeners.clear();
    }

    public static void removeFabricListener(FabricMarkerListener fabricMarkerListener) {
        sFabricMarkerListeners.remove(fabricMarkerListener);
    }

    public static void clearFabricMarkerListeners() {
        sFabricMarkerListeners.clear();
    }
}
