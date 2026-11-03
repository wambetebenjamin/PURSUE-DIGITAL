package com.google.android.play.core.assetpacks;

import android.app.Activity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import com.google.android.gms.tasks.Task;
import java.util.List;
import java.util.Map;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public interface AssetPackManager {
    AssetPackStates cancel(List<String> list);

    void clearListeners();

    Task<AssetPackStates> fetch(List<String> list);

    AssetLocation getAssetLocation(String str, String str2);

    AssetPackLocation getPackLocation(String str);

    Map<String, AssetPackLocation> getPackLocations();

    Task<AssetPackStates> getPackStates(List<String> list);

    void registerListener(AssetPackStateUpdateListener assetPackStateUpdateListener);

    Task<Void> removePack(String str);

    Task<Integer> showCellularDataConfirmation(Activity activity);

    boolean showCellularDataConfirmation(ActivityResultLauncher<IntentSenderRequest> activityResultLauncher);

    void unregisterListener(AssetPackStateUpdateListener assetPackStateUpdateListener);
}
