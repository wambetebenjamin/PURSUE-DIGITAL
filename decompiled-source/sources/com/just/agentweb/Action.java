package com.just.agentweb;

import android.content.Intent;
import android.net.Uri;
import com.just.agentweb.AgentActionFragment;
import java.util.ArrayList;
import java.util.Arrays;
/* loaded from: classes9.dex */
public final class Action {
    public static final transient int ACTION_CAMERA = 3;
    public static final transient int ACTION_FILE = 2;
    public static final transient int ACTION_PERMISSION = 1;
    public static final transient int ACTION_VIDEO = 4;
    private int mAction;
    private AgentActionFragment.ChooserListener mChooserListener;
    private int mFromIntention;
    private Intent mIntent;
    private AgentActionFragment.PermissionListener mPermissionListener;
    private ArrayList<String> mPermissions = new ArrayList<>();
    private AgentActionFragment.RationaleListener mRationaleListener;
    private Uri mUri;

    public ArrayList<String> getPermissions() {
        return this.mPermissions;
    }

    public void setPermissions(ArrayList<String> arrayList) {
        this.mPermissions = arrayList;
    }

    public void setPermissions(String[] strArr) {
        this.mPermissions = new ArrayList<>(Arrays.asList(strArr));
    }

    public int getAction() {
        return this.mAction;
    }

    public void setAction(int i2) {
        this.mAction = i2;
    }

    public int getFromIntention() {
        return this.mFromIntention;
    }

    public static Action createPermissionsAction(String[] strArr) {
        Action action = new Action();
        action.setAction(1);
        action.setPermissions(new ArrayList<>(Arrays.asList(strArr)));
        return action;
    }

    public Action setFromIntention(int i2) {
        this.mFromIntention = i2;
        return this;
    }

    public AgentActionFragment.RationaleListener getRationaleListener() {
        return this.mRationaleListener;
    }

    public void setRationaleListener(AgentActionFragment.RationaleListener rationaleListener) {
        this.mRationaleListener = rationaleListener;
    }

    public AgentActionFragment.PermissionListener getPermissionListener() {
        return this.mPermissionListener;
    }

    public void setPermissionListener(AgentActionFragment.PermissionListener permissionListener) {
        this.mPermissionListener = permissionListener;
    }

    public AgentActionFragment.ChooserListener getChooserListener() {
        return this.mChooserListener;
    }

    public void setChooserListener(AgentActionFragment.ChooserListener chooserListener) {
        this.mChooserListener = chooserListener;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public void setUri(Uri uri) {
        this.mUri = uri;
    }

    public void setIntent(Intent intent) {
        this.mIntent = intent;
    }
}
