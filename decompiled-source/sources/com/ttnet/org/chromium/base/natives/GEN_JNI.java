package com.ttnet.org.chromium.base.natives;

import J.N;
/* loaded from: classes9.dex */
public class GEN_JNI {
    public static final boolean REQUIRE_MOCK = false;
    public static final boolean TESTING_ENABLED = false;

    public static void com_ttnet_org_chromium_base_ApplicationStatus_onApplicationStateChange(int i2) {
        N.M$lotPPc(i2);
    }

    public static boolean com_ttnet_org_chromium_base_BaseFeatureList_isEnabled(String str) {
        return N.Mtllg_IO(str);
    }

    public static void com_ttnet_org_chromium_base_CommandLine_init(String[] strArr) {
        N.MZSU27YP(strArr);
    }

    public static boolean com_ttnet_org_chromium_base_CommandLine_hasSwitch(String str) {
        return N.MhlgTxh2(str);
    }

    public static String com_ttnet_org_chromium_base_CommandLine_getSwitchValue(String str) {
        return N.MaCXZYAa(str);
    }

    public static String[] com_ttnet_org_chromium_base_CommandLine_getSwitchesFlattened() {
        return N.MahsXrQs();
    }

    public static void com_ttnet_org_chromium_base_CommandLine_appendSwitch(String str) {
        N.MawtMrJF(str);
    }

    public static void com_ttnet_org_chromium_base_CommandLine_appendSwitchWithValue(String str, String str2) {
        N.MIZelAXD(str, str2);
    }

    public static void com_ttnet_org_chromium_base_CommandLine_appendSwitchesAndArguments(String[] strArr) {
        N.MjOVh1Zv(strArr);
    }

    public static void com_ttnet_org_chromium_base_CommandLine_removeSwitch(String str) {
        N.MP5WQt3J(str);
    }

    public static int com_ttnet_org_chromium_base_CpuFeatures_getCoreCount() {
        return N.Mcaj_8qf();
    }

    public static long com_ttnet_org_chromium_base_CpuFeatures_getCpuFeatures() {
        return N.M1UV95tj();
    }

    public static void com_ttnet_org_chromium_base_EarlyTraceEvent_recordEarlyBeginEvent(String str, long j2, int i2, long j3) {
        N.MCawLSFP(str, j2, i2, j3);
    }

    public static void com_ttnet_org_chromium_base_EarlyTraceEvent_recordEarlyEndEvent(String str, long j2, int i2, long j3) {
        N.MvvW3g4w(str, j2, i2, j3);
    }

    public static void com_ttnet_org_chromium_base_EarlyTraceEvent_recordEarlyToplevelBeginEvent(String str, long j2, int i2, long j3) {
        N.MjWH5Jmd(str, j2, i2, j3);
    }

    public static void com_ttnet_org_chromium_base_EarlyTraceEvent_recordEarlyToplevelEndEvent(String str, long j2, int i2, long j3) {
        N.MoB6dYvZ(str, j2, i2, j3);
    }

    public static void com_ttnet_org_chromium_base_EarlyTraceEvent_recordEarlyAsyncBeginEvent(String str, long j2, long j3) {
        N.M88o6Bs3(str, j2, j3);
    }

    public static void com_ttnet_org_chromium_base_EarlyTraceEvent_recordEarlyAsyncEndEvent(String str, long j2, long j3) {
        N.MohIB14s(str, j2, j3);
    }

    public static boolean com_ttnet_org_chromium_base_FeatureList_isInitialized() {
        return N.Md1lGM43();
    }

    public static boolean com_ttnet_org_chromium_base_Features_isEnabled(long j2) {
        return N.MoITUwC2(j2);
    }

    public static boolean com_ttnet_org_chromium_base_Features_getFieldTrialParamByFeatureAsBoolean(long j2, String str, boolean z) {
        return N.MiTw3O_J(j2, str, z);
    }

    public static String com_ttnet_org_chromium_base_FieldTrialList_findFullName(String str) {
        return N.MfcktC_e(str);
    }

    public static boolean com_ttnet_org_chromium_base_FieldTrialList_trialExists(String str) {
        return N.MBEGQVWX(str);
    }

    public static String com_ttnet_org_chromium_base_FieldTrialList_getVariationParameter(String str, String str2) {
        return N.Msd5Ns7Y(str, str2);
    }

    public static void com_ttnet_org_chromium_base_FieldTrialList_logActiveTrials() {
        N.M2n_z3BB();
    }

    public static boolean com_ttnet_org_chromium_base_FieldTrialList_createFieldTrial(String str, String str2) {
        return N.MzhBrODy(str, str2);
    }

    public static String com_ttnet_org_chromium_base_FileUtils_getAbsoluteFilePath(String str) {
        return N.MKMoqNq6(str);
    }

    public static boolean com_ttnet_org_chromium_base_ImportantFileWriterAndroid_writeFileAtomically(String str, byte[] bArr) {
        return N.M1jycjxU(str, bArr);
    }

    public static void com_ttnet_org_chromium_base_JavaExceptionReporter_reportJavaException(boolean z, Throwable th) {
        N.M9gfm$zh(z, th);
    }

    public static void com_ttnet_org_chromium_base_JavaExceptionReporter_reportJavaStackTrace(String str) {
        N.MuvJZ5jK(str);
    }

    public static void com_ttnet_org_chromium_base_JavaHandlerThread_initializeThread(long j2, long j3) {
        N.M3RaWo13(j2, j3);
    }

    public static void com_ttnet_org_chromium_base_JavaHandlerThread_onLooperStopped(long j2) {
        N.MSKUcgE4(j2);
    }

    public static void com_ttnet_org_chromium_base_PathService_override(int i2, String str) {
        N.MvMFfChw(i2, str);
    }

    public static void com_ttnet_org_chromium_base_PowerMonitor_onBatteryChargingChanged() {
        N.MxETr4iO();
    }

    public static void com_ttnet_org_chromium_base_PowerMonitor_onSuspend() {
        N.MBffe1lF();
    }

    public static void com_ttnet_org_chromium_base_PowerMonitor_onResume() {
        N.MyS7kauI();
    }

    public static void com_ttnet_org_chromium_base_PowerMonitor_onInstantSuspend() {
        N.MolcG1Wq();
    }

    public static void com_ttnet_org_chromium_base_PowerMonitor_onInstantResume() {
        N.MELAwTDU();
    }

    public static void com_ttnet_org_chromium_base_PowerMonitor_onThermalStatusChanged(int i2) {
        N.MuSEGQ9M(i2);
    }

    public static void com_ttnet_org_chromium_base_SysUtils_logPageFaultCountToTracing() {
        N.Mm3sCch3();
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_registerEnabledObserver() {
        N.M8F7L$JR();
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_startATrace(String str) {
        N.MiUfkLIq(str);
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_stopATrace() {
        N.MTnadCLu();
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_setupATraceStartupTrace(String str) {
        N.M8MvK2gp(str);
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_instant(String str, String str2) {
        N.Mz5qii_R(str, str2);
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_begin(String str, String str2) {
        N.MajVTFsV(str, str2);
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_end(String str, String str2) {
        N.M5yUVmrW(str, str2);
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_beginToplevel(String str) {
        N.MljCyOuh(str);
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_endToplevel(String str) {
        N.M6R6NWXS(str);
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_startAsync(String str, long j2) {
        N.MKlPFTpb(str, j2);
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_finishAsync(String str, long j2) {
        N.MjXrJVoa(str, j2);
    }

    public static boolean com_ttnet_org_chromium_base_TraceEvent_viewHierarchyDumpEnabled() {
        return N.MpI7Xy5x();
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_initViewHierarchyDump() {
        N.MgOuiubc();
    }

    public static long com_ttnet_org_chromium_base_TraceEvent_startActivityDump(String str, long j2) {
        return N.MgxFMA1H(str, j2);
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_addViewDump(int i2, int i3, boolean z, boolean z2, String str, String str2, long j2) {
        N.MxGy3cn_(i2, i3, z, z2, str, str2, j2);
    }

    public static void com_ttnet_org_chromium_base_TraceEvent_instantAndroidIPC(String str, long j2) {
        N.MMXoiNW_(str, j2);
    }

    public static void com_ttnet_org_chromium_base_jank_1tracker_JankMetricUMARecorder_recordJankMetrics(String str, long[] jArr, long[] jArr2, long[] jArr3, int i2) {
        N.Mw$h3q2i(str, jArr, jArr2, jArr3, i2);
    }

    public static boolean com_ttnet_org_chromium_base_library_1loader_LibraryLoader_libraryLoaded(int i2) {
        return N.MnwWsRxg(i2);
    }

    public static void com_ttnet_org_chromium_base_library_1loader_LibraryLoader_registerNonMainDexJni() {
        N.MU8nwPRG();
    }

    public static void com_ttnet_org_chromium_base_library_1loader_LibraryPrefetcher_forkAndPrefetchNativeLibrary() {
        N.MVIMN27T();
    }

    public static int com_ttnet_org_chromium_base_library_1loader_LibraryPrefetcher_percentageOfResidentNativeLibraryCode() {
        return N.MA6QAXj_();
    }

    public static void com_ttnet_org_chromium_base_library_1loader_LibraryPrefetcher_periodicallyCollectResidency() {
        N.MtuV2SWL();
    }

    public static long com_ttnet_org_chromium_base_metrics_NativeUmaRecorder_recordBooleanHistogram(String str, long j2, boolean z) {
        return N.MYPj82kt(str, j2, z);
    }

    public static long com_ttnet_org_chromium_base_metrics_NativeUmaRecorder_recordExponentialHistogram(String str, long j2, int i2, int i3, int i4, int i5) {
        return N.M45N7QIN(str, j2, i2, i3, i4, i5);
    }

    public static long com_ttnet_org_chromium_base_metrics_NativeUmaRecorder_recordLinearHistogram(String str, long j2, int i2, int i3, int i4, int i5) {
        return N.Mx1k0LvC(str, j2, i2, i3, i4, i5);
    }

    public static long com_ttnet_org_chromium_base_metrics_NativeUmaRecorder_recordSparseHistogram(String str, long j2, int i2) {
        return N.MV$7$UuP(str, j2, i2);
    }

    public static void com_ttnet_org_chromium_base_metrics_NativeUmaRecorder_recordUserAction(String str, long j2) {
        N.MxViw_go(str, j2);
    }

    public static int com_ttnet_org_chromium_base_metrics_NativeUmaRecorder_getHistogramValueCountForTesting(String str, int i2, long j2) {
        return N.MkFXHbyW_ForTesting(str, i2, j2);
    }

    public static int com_ttnet_org_chromium_base_metrics_NativeUmaRecorder_getHistogramTotalCountForTesting(String str, long j2) {
        return N.M7NlTYxi_ForTesting(str, j2);
    }

    public static long com_ttnet_org_chromium_base_metrics_NativeUmaRecorder_createHistogramSnapshotForTesting() {
        return N.M_034fMZ_ForTesting();
    }

    public static void com_ttnet_org_chromium_base_metrics_NativeUmaRecorder_destroyHistogramSnapshotForTesting(long j2) {
        N.Me95ZC6G_ForTesting(j2);
    }

    public static long com_ttnet_org_chromium_base_metrics_NativeUmaRecorder_addActionCallbackForTesting(Object obj) {
        return N.MDoBOCZO_ForTesting(obj);
    }

    public static void com_ttnet_org_chromium_base_metrics_NativeUmaRecorder_removeActionCallbackForTesting(long j2) {
        N.MYkUp9vo_ForTesting(j2);
    }

    public static String com_ttnet_org_chromium_base_metrics_StatisticsRecorderAndroid_toJson(int i2) {
        return N.M5gAmtvn(i2);
    }

    public static void com_ttnet_org_chromium_base_task_PostTask_postDelayedTask(int i2, boolean z, boolean z2, byte b2, byte[] bArr, Object obj, long j2, String str) {
        N.Mwr17eXX(i2, z, z2, b2, bArr, obj, j2, str);
    }

    public static long com_ttnet_org_chromium_base_task_TaskRunnerImpl_init(int i2, int i3, boolean z, boolean z2, byte b2, byte[] bArr) {
        return N.MLtH82Um(i2, i3, z, z2, b2, bArr);
    }

    public static void com_ttnet_org_chromium_base_task_TaskRunnerImpl_destroy(long j2) {
        N.Mvd960xb(j2);
    }

    public static void com_ttnet_org_chromium_base_task_TaskRunnerImpl_postDelayedTask(long j2, Object obj, long j3, String str) {
        N.MJ_h_2p2(j2, obj, j3, str);
    }

    public static boolean com_ttnet_org_chromium_base_task_TaskRunnerImpl_belongsToCurrentThread(long j2) {
        return N.Mz$BG_4y(j2);
    }

    public static void com_ttnet_org_chromium_net_AndroidNetworkLibrary_onCellularAlwaysUp(boolean z, int i2) {
        N.MeYG7FqD(z, i2);
    }

    public static String com_ttnet_org_chromium_net_GURLUtils_getOrigin(String str) {
        return N.McGsvqvj(str);
    }

    public static void com_ttnet_org_chromium_net_HttpNegotiateAuthenticator_setResult(long j2, Object obj, int i2, String str) {
        N.MsAf1sYp(j2, obj, i2, str);
    }

    public static boolean com_ttnet_org_chromium_net_HttpUtil_isAllowedHeader(String str, String str2) {
        return N.MsTaj7yX(str, str2);
    }

    public static void com_ttnet_org_chromium_net_NetworkActiveNotifier_notifyOfDefaultNetworkActive(long j2) {
        N.M5aI8A5Z(j2);
    }

    public static void com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyConnectionTypeChanged(long j2, Object obj, int i2, long j3) {
        N.MjJzrRFH(j2, obj, i2, j3);
    }

    public static void com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyConnectionCostChanged(long j2, Object obj, int i2) {
        N.MZnuk2LK(j2, obj, i2);
    }

    public static void com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyMaxBandwidthChanged(long j2, Object obj, int i2) {
        N.MqNJnYjG(j2, obj, i2);
    }

    public static void com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyOfNetworkConnect(long j2, Object obj, long j3, boolean z, int i2) {
        N.MZ5e75rQ(j2, obj, j3, z, i2);
    }

    public static void com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyOfNetworkSoonToDisconnect(long j2, Object obj, long j3) {
        N.MssT8yD3(j2, obj, j3);
    }

    public static void com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyOfNetworkDisconnect(long j2, Object obj, boolean z, long j3) {
        N.Ms7JLaGI(j2, obj, z, j3);
    }

    public static void com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyPurgeActiveNetworkList(long j2, Object obj, long[] jArr) {
        N.Mvng38R0(j2, obj, jArr);
    }

    public static void com_ttnet_org_chromium_net_ProxyChangeListener_proxySettingsChangedTo(long j2, Object obj, String str, int i2, String str2, String[] strArr) {
        N.MtXZ30qk(j2, obj, str, i2, str2, strArr);
    }

    public static void com_ttnet_org_chromium_net_ProxyChangeListener_proxySettingsChanged(long j2, Object obj) {
        N.MKwsFZ4t(j2, obj);
    }

    public static boolean com_ttnet_org_chromium_net_TTAppSecurityManager_isUserPrivacyRestrictionEnabled() {
        return N.MaSRwBiO();
    }

    public static boolean com_ttnet_org_chromium_net_TTAppSecurityManager_isTTPRestrictionEnabled() {
        return N.Mhd7DRFo();
    }

    public static void com_ttnet_org_chromium_net_X509Util_notifyKeyChainChanged() {
        N.MzV1TXLS();
    }

    public static long com_ttnet_org_chromium_net_impl_CronetBidirectionalStream_createBidirectionalStream(Object obj, long j2, boolean z, boolean z2, int i2, boolean z3, int i3, long j3) {
        return N.MSnVviPU(obj, j2, z, z2, i2, z3, i3, j3);
    }

    public static int com_ttnet_org_chromium_net_impl_CronetBidirectionalStream_start(long j2, Object obj, String str, int i2, String str2, String[] strArr, boolean z) {
        return N.MycrIbGr(j2, obj, str, i2, str2, strArr, z);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetBidirectionalStream_sendRequestHeaders(long j2, Object obj) {
        N.MhETl_ev(j2, obj);
    }

    public static boolean com_ttnet_org_chromium_net_impl_CronetBidirectionalStream_readData(long j2, Object obj, Object obj2, int i2, int i3) {
        return N.M21CWxxV(j2, obj, obj2, i2, i3);
    }

    public static boolean com_ttnet_org_chromium_net_impl_CronetBidirectionalStream_writevData(long j2, Object obj, Object[] objArr, int[] iArr, int[] iArr2, boolean z) {
        return N.MYRRygif(j2, obj, objArr, iArr, iArr2, z);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetBidirectionalStream_destroy(long j2, Object obj, boolean z) {
        N.My0p82p4(j2, obj, z);
    }

    public static long com_ttnet_org_chromium_net_impl_CronetFrontierClient_createFrontierClientAdapter(Object obj) {
        return N.MagNlhNv(obj);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetFrontierClient_addHeader(long j2, Object obj, String str, String str2) {
        N.MwZrRAIG(j2, obj, str, str2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetFrontierClient_addServiceId(long j2, Object obj, int i2) {
        N.Msf3H5kQ(j2, obj, i2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetFrontierClient_configParams(long j2, Object obj, int i2, int i3, String str, int i4, String str2, String str3, String str4, String str5, int i5, int i6, int i7, boolean z, String str6, String str7) {
        N.Mhk1t9AY(j2, obj, i2, i3, str, i4, str2, str3, str4, str5, i5, i6, i7, z, str6, str7);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetFrontierClient_registerService(long j2, Object obj, String[] strArr, Object obj2, int i2, int i3, boolean z) {
        N.MKu8Zh_d(j2, obj, strArr, obj2, i2, i3, z);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetFrontierClient_unregisterService(long j2, Object obj, int i2) {
        N.M4rPfgYd(j2, obj, i2);
    }

    public static boolean com_ttnet_org_chromium_net_impl_CronetFrontierClient_isServiceReady(long j2, Object obj, int i2) {
        return N.My_P9YPg(j2, obj, i2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetFrontierClient_sendMessage(long j2, Object obj, int i2, String[] strArr, Object obj2) {
        N.MKYiSehZ(j2, obj, i2, strArr, obj2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetFrontierClient_reportAppStateChange(long j2, Object obj, boolean z) {
        N.MlH8$Rpg(j2, obj, z);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetFrontierClient_destroy(long j2, Object obj) {
        N.MKRYs$fc(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetLibraryLoader_cronetInitOnInitThread() {
        N.MyweRqSS();
    }

    public static String com_ttnet_org_chromium_net_impl_CronetLibraryLoader_getCronetVersion() {
        return N.MVlvYo_c();
    }

    public static void com_ttnet_org_chromium_net_impl_CronetLibraryLoader_createNetworkChangeNotifierOnInitThread() {
        N.MhrJ$_b0();
    }

    public static long com_ttnet_org_chromium_net_impl_CronetUploadDataStream_attachUploadDataToRequest(Object obj, long j2, long j3) {
        return N.M89UGLMC(obj, j2, j3);
    }

    public static long com_ttnet_org_chromium_net_impl_CronetUploadDataStream_createAdapterForTesting(Object obj) {
        return N.M3b_yKC0_ForTesting(obj);
    }

    public static long com_ttnet_org_chromium_net_impl_CronetUploadDataStream_createUploadDataStreamForTesting(Object obj, long j2, long j3) {
        return N.MKATyFlf_ForTesting(obj, j2, j3);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUploadDataStream_onReadSucceeded(long j2, Object obj, int i2, boolean z) {
        N.MYsxiVBk(j2, obj, i2, z);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUploadDataStream_onRewindSucceeded(long j2, Object obj) {
        N.M3Tck9nA(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUploadDataStream_destroy(long j2) {
        N.Mx3T_YwB(j2);
    }

    public static long com_ttnet_org_chromium_net_impl_CronetUrlRequest_createRequestAdapter(Object obj, long j2, String str, int i2, int i3, boolean z, boolean z2, boolean z3, int i4, boolean z4, int i5, int i6, long j3) {
        return N.MnXVOzVo(obj, j2, str, i2, i3, z, z2, z3, i4, z4, i5, i6, j3);
    }

    public static boolean com_ttnet_org_chromium_net_impl_CronetUrlRequest_setHttpMethod(long j2, Object obj, String str) {
        return N.MfdvbiJC(j2, obj, str);
    }

    public static boolean com_ttnet_org_chromium_net_impl_CronetUrlRequest_addRequestHeader(long j2, Object obj, String str, String str2) {
        return N.MtJFji5x(j2, obj, str, str2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequest_start(long j2, Object obj) {
        N.Mv2a151P(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequest_followDeferredRedirect(long j2, Object obj) {
        N.MhCyx70S(j2, obj);
    }

    public static boolean com_ttnet_org_chromium_net_impl_CronetUrlRequest_readData(long j2, Object obj, Object obj2, int i2, int i3) {
        return N.MJbYA4in(j2, obj, obj2, i2, i3);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequest_destroy(long j2, Object obj, boolean z) {
        N.MIb9tt7_(j2, obj, z);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequest_appTimeout(long j2, Object obj) {
        N.M3xsE8SK(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequest_abortWhenUploadException(long j2, Object obj) {
        N.MzKjGz9N(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequest_addRequestCookieHeader(long j2, Object obj, String str, String str2) {
        N.M2jrbUOP(j2, obj, str, str2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequest_removeRequestCookieHeader(long j2, Object obj) {
        N.MjbEhTZs(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequest_setSocketTimeout(long j2, Object obj, int i2, int i3, int i4) {
        N.M4C6WZaz(j2, obj, i2, i3, i4);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequest_setRequestTimeout(long j2, Object obj, int i2) {
        N.MaFGwSbC(j2, obj, i2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequest_setThrottleNetSpeed(long j2, Object obj, long j3) {
        N.ML2WrLQ6(j2, obj, j3);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequest_setRequestTypeFlags(long j2, Object obj, int i2) {
        N.MC7pScOk(j2, obj, i2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequest_setAuthCredentials(long j2, Object obj, String str, String str2) {
        N.Mor7A21H(j2, obj, str, str2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequest_getStatus(long j2, Object obj, Object obj2) {
        N.MmSvKAJE(j2, obj, obj2);
    }

    public static long com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_createRequestContextConfig(String str, String str2, boolean z, String str3, boolean z2, boolean z3, boolean z4, int i2, long j2, String str4, long j3, boolean z5, boolean z6, int i3) {
        return N.Mmo_MQVE(str, str2, z, str3, z2, z3, z4, i2, j2, str4, j3, z5, z6, i3);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_addQuicHint(long j2, String str, int i2, int i3) {
        N.M6WZ7Wt0(j2, str, i2, i3);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_addPkp(long j2, String str, byte[][] bArr, boolean z, long j3) {
        N.Mkx3KULb(j2, str, bArr, z, j3);
    }

    public static long com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_createRequestContextAdapter(long j2) {
        return N.MuixiOYs(j2);
    }

    public static int com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_setMinLogLevel(int i2) {
        return N.MyyJ5zsH(i2);
    }

    public static byte[] com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_getHistogramDeltas() {
        return N.MdOiNSC0();
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_destroy(long j2, Object obj) {
        N.MG$DT18r(j2, obj);
    }

    public static boolean com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_startNetLogToFile(long j2, Object obj, String str, boolean z) {
        return N.Mt89JXhb(j2, obj, str, z);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_startNetLogToDisk(long j2, Object obj, String str, boolean z, int i2) {
        N.MuR7zLrb(j2, obj, str, z, i2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_stopNetLog(long j2, Object obj) {
        N.ML5dVOOG(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_initRequestContextOnInitThread(long j2, Object obj) {
        N.Mdu$OLLN(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_configureNetworkQualityEstimatorForTesting(long j2, Object obj, boolean z, boolean z2, boolean z3) {
        N.MNAF9eXw_ForTesting(j2, obj, z, z2, z3);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_provideRTTObservations(long j2, Object obj, boolean z) {
        N.Mk1UqBGq(j2, obj, z);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_setTTNetInitConfig(long j2, int i2, boolean z, boolean z2, String str, String str2, String str3, boolean z3, String str4, String str5, byte[][] bArr, Object[] objArr, boolean z4, boolean z5, boolean z6, String str6, Object obj, long j3, boolean z7, boolean z8, boolean z9, Object obj2, long j4, boolean z10, String str7) {
        N.MawZO189(j2, i2, z, z2, str, str2, str3, z3, str4, str5, bArr, objArr, z4, z5, z6, str6, obj, j3, z7, z8, z9, obj2, j4, z10, str7);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_setClientOpaqueData(long j2, String[] strArr, byte[] bArr, byte[] bArr2) {
        N.MTZ7mkhc(j2, strArr, bArr, bArr2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_initALogFuncAddr(long j2) {
        N.MjS9m1YQ(j2);
    }

    public static long[] com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_getOpaqueFuncAddress() {
        return N.MlOQJZ8w();
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_parseTNCConfigFromSystemHTTPRequest(long j2, Object obj, String str, String str2, String str3, String str4, String str5, String str6) {
        N.MU6k4d25(j2, obj, str, str2, str3, str4, str5, str6);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_triggerTNCRequestByUser(long j2, Object obj, boolean z) {
        N.MN8yyQkr(j2, obj, z);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_ttUrlDispatch(long j2, Object obj, Object obj2, String str, boolean z) {
        N.MRTGlzo1(j2, obj, obj2, str, z);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_runInBackGround(long j2, Object obj, boolean z) {
        N.MWOUQ7KG(j2, obj, z);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_addClientOpaqueData(long j2, Object obj, String[] strArr, byte[] bArr, byte[] bArr2, long j3, long j4) {
        N.MWb1lJ5e(j2, obj, strArr, bArr, bArr2, j3, j4);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_clearClientOpaqueData(long j2, Object obj) {
        N.MK0SE_Ub(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_removeClientOpaqueData(long j2, Object obj, String str) {
        N.Mcfn2q8$(j2, obj, str);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_setProxy(long j2, Object obj, String str) {
        N.Mj4Pi_Pa(j2, obj, str);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_preconnectUrl(long j2, Object obj, String str, int i2, String str2) {
        N.MBtj30QU(j2, obj, str, i2, str2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_ttDnsResolve(long j2, Object obj, String str, int i2, String str2, String str3) {
        N.MH0muaR3(j2, obj, str, i2, str2, str3);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_controlHttpDNSConfig(long j2, Object obj, boolean z, boolean z2, boolean z3) {
        N.MHps7uOC(j2, obj, z, z2, z3);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_setHostResolverRules(long j2, Object obj, String str) {
        N.MlH1XMiR(j2, obj, str);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_addSamplingSetting(long j2, Object obj, Object obj2) {
        N.MKYzOUkj(j2, obj, obj2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_tryStartNetDetect(long j2, Object obj, String[] strArr, int i2, int i3) {
        N.MA5SsGAv(j2, obj, strArr, i2, i3);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_updateStoreRegionFromServer(long j2, Object obj, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        N.MqjX8qD1(j2, obj, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_setAlogFuncAddr(long j2, Object obj, long j3) {
        N.Me3xdZoU(j2, obj, j3);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_setZstdFuncAddr(long j2, Object obj, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10) {
        N.MIs1FVFz(j2, obj, j3, j4, j5, j6, j7, j8, j9, j10);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_setRouteSelectionBestHost(long j2, Object obj, String str, String str2) {
        N.MhbNiNFL(j2, obj, str, str2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_startThrottle(long j2, Object obj, String[] strArr, int i2, long j3) {
        N.MiGn0DCY(j2, obj, strArr, i2, j3);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_stopThrottle(long j2, Object obj, String[] strArr, int i2) {
        N.MohL$06P(j2, obj, strArr, i2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_enableTTBizHttpDns(long j2, Object obj, boolean z, String str, String str2, String str3, boolean z2, String str4) {
        N.M2ni33Tk(j2, obj, z, str, str2, str3, z2, str4);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_setAppStartUpState(long j2, Object obj, int i2) {
        N.MksRT8QX(j2, obj, i2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_setCookieInitCompleted(long j2, Object obj) {
        N.MaSOyuXL(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_reportNetDiagnosisUserLog(long j2, Object obj, String str) {
        N.MPONMQm_(j2, obj, str);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_provideThroughputObservations(long j2, Object obj, boolean z) {
        N.MHUcn7PP(j2, obj, z);
    }

    public static boolean com_ttnet_org_chromium_net_impl_CronetUrlRequestContext_skipLogging(long j2, Object obj) {
        return N.MLLAskZ0(j2, obj);
    }

    public static long com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_createWebsocketConnectionAdapter(Object obj, long j2) {
        return N.MRAGjNeT(obj, j2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_startWithFrontier(long j2, Object obj, int i2, String str, long j3, int i3, long j4, String str2, int i4, String str3, boolean z) {
        N.MWq0tMul(j2, obj, i2, str, j3, i3, j4, str2, i4, str3, z);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_startWithWSChannel(long j2, Object obj, boolean z) {
        N.MVrBbeuj(j2, obj, z);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_stop(long j2, Object obj) {
        N.M14GZgiH(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_sendText(long j2, Object obj, String str) {
        N.MRnDfpSi(j2, obj, str);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_sendBinary(long j2, Object obj, Object obj2) {
        N.MRfZ_7V_(j2, obj, obj2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_destroy(long j2, Object obj) {
        N.MCzpxGQD(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_addUrl(long j2, Object obj, String str) {
        N.MgN9hn8A(j2, obj, str);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_addHeader(long j2, Object obj, String str, String str2) {
        N.MKmGe$35(j2, obj, str, str2);
    }

    public static void com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_addGetParam(long j2, Object obj, String str, String str2) {
        N.Mzisx1kZ(j2, obj, str, str2);
    }

    public static long com_ttnet_org_chromium_net_impl_TTCronetNetExpRequest_createRequestAdapter(Object obj, long j2, int i2, String[] strArr, int i3, int i4, int i5) {
        return N.MPJAsrFC(obj, j2, i2, strArr, i3, i4, i5);
    }

    public static void com_ttnet_org_chromium_net_impl_TTCronetNetExpRequest_start(long j2, Object obj) {
        N.MLiR9ZbW(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_TTCronetNetExpRequest_destroy(long j2, Object obj) {
        N.M6$xRvea(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_TTCronetNetExpRequest_doExtraCommand(long j2, Object obj, String str, String str2) {
        N.MDZjmtTn(j2, obj, str, str2);
    }

    public static long com_ttnet_org_chromium_net_impl_TTFrontierConnection_createFrontierConnectionAdapter(Object obj, long j2) {
        return N.MEtRyDGt(obj, j2);
    }

    public static void com_ttnet_org_chromium_net_impl_TTFrontierConnection_startFrontierConnection(long j2, Object obj, int i2, int i3, int i4, long j3, String str, long j4, String str2, boolean z) {
        N.MtwH3UpE(j2, obj, i2, i3, i4, j3, str, j4, str2, z);
    }

    public static void com_ttnet_org_chromium_net_impl_TTFrontierConnection_sendMessage(long j2, Object obj, int i2, String[] strArr, Object obj2) {
        N.M4sZKn6G(j2, obj, i2, strArr, obj2);
    }

    public static void com_ttnet_org_chromium_net_impl_TTFrontierConnection_reportAppStateChange(long j2, Object obj, boolean z) {
        N.MhX1fbpk(j2, obj, z);
    }

    public static void com_ttnet_org_chromium_net_impl_TTFrontierConnection_stopConnection(long j2, Object obj) {
        N.M4Ly8Bhy(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_TTFrontierConnection_destroy(long j2, Object obj) {
        N.MZz7QbJB(j2, obj);
    }

    public static void com_ttnet_org_chromium_net_impl_TTFrontierConnection_addUrl(long j2, Object obj, String str) {
        N.MCKjYI5n(j2, obj, str);
    }

    public static void com_ttnet_org_chromium_net_impl_TTFrontierConnection_addHeader(long j2, Object obj, String str, String str2) {
        N.MaMeYR_Y(j2, obj, str, str2);
    }

    public static void com_ttnet_org_chromium_net_impl_TTFrontierConnection_addParam(long j2, Object obj, String str, String str2) {
        N.MnYmJ$eY(j2, obj, str, str2);
    }

    public static void com_ttnet_org_chromium_net_impl_TTFrontierConnection_addServiceId(long j2, Object obj, int i2) {
        N.MWzrTdcn(j2, obj, i2);
    }
}
