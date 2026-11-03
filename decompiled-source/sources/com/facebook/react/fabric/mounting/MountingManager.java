package com.facebook.react.fabric.mounting;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.bytedance.librarian.c;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.RootViewManager;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.yoga.YogaMeasureMode;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes8.dex */
public class MountingManager {
    public static final String TAG = "MountingManager";
    private final JSResponderHandler mJSResponderHandler = new JSResponderHandler();
    private final RootViewManager mRootViewManager = new RootViewManager();
    private final ConcurrentHashMap<Integer, ViewState> mTagToViewState = new ConcurrentHashMap<>();
    private final ViewManagerRegistry mViewManagerRegistry;

    public MountingManager(ViewManagerRegistry viewManagerRegistry) {
        this.mViewManagerRegistry = viewManagerRegistry;
    }

    public void addRootView(int i2, View view) {
        if (view.getId() != -1) {
            throw new IllegalViewOperationException("Trying to add a root view with an explicit id already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView.");
        }
        this.mTagToViewState.put(Integer.valueOf(i2), new ViewState(i2, view, this.mRootViewManager, true));
        view.setId(i2);
    }

    private void dropView(View view) {
        UiThreadUtil.assertOnUiThread();
        int id = view.getId();
        ViewState viewState = getViewState(id);
        ViewManager viewManager = viewState.mViewManager;
        if (!viewState.mIsRoot && viewManager != null) {
            viewManager.onDropViewInstance(view);
        }
        if ((view instanceof ViewGroup) && (viewManager instanceof ViewGroupManager)) {
            ViewGroup viewGroup = (ViewGroup) view;
            ViewGroupManager<ViewGroup> viewGroupManager = getViewGroupManager(viewState);
            for (int childCount = viewGroupManager.getChildCount(viewGroup) - 1; childCount >= 0; childCount--) {
                View childAt = viewGroupManager.getChildAt(viewGroup, childCount);
                if (getNullableViewState(childAt.getId()) != null) {
                    dropView(childAt);
                }
                viewGroupManager.removeViewAt(viewGroup, childCount);
            }
        }
        this.mTagToViewState.remove(Integer.valueOf(id));
    }

    public void addViewAt(int i2, int i3, int i4) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i2);
        ViewGroup viewGroup = (ViewGroup) viewState.mView;
        ViewState viewState2 = getViewState(i3);
        View view = viewState2.mView;
        if (view == null) {
            throw new IllegalStateException("Unable to find view for viewState " + viewState2 + " and tag " + i3);
        }
        getViewGroupManager(viewState).addView(viewGroup, view, i4);
    }

    private ViewState getViewState(int i2) {
        ViewState viewState = this.mTagToViewState.get(Integer.valueOf(i2));
        if (viewState != null) {
            return viewState;
        }
        throw new IllegalStateException("Unable to find viewState view for tag " + i2);
    }

    private ViewState getNullableViewState(int i2) {
        return this.mTagToViewState.get(Integer.valueOf(i2));
    }

    @Deprecated
    public void receiveCommand(int i2, int i3, ReadableArray readableArray) {
        ViewState nullableViewState = getNullableViewState(i2);
        if (nullableViewState == null) {
            ReactSoftException.logSoftException(TAG, new ReactNoCrashSoftException("Unable to find viewState for tag: " + i2 + " for commandId: " + i3));
        } else if (nullableViewState.mViewManager == null) {
            throw new IllegalStateException("Unable to find viewManager for tag " + i2);
        } else {
            if (nullableViewState.mView == null) {
                throw new IllegalStateException("Unable to find viewState view for tag " + i2);
            }
            nullableViewState.mViewManager.receiveCommand((ViewManager) nullableViewState.mView, i3, readableArray);
        }
    }

    public void receiveCommand(int i2, String str, ReadableArray readableArray) {
        ViewState nullableViewState = getNullableViewState(i2);
        if (nullableViewState == null) {
            ReactSoftException.logSoftException(TAG, new IllegalStateException("Unable to find viewState for tag: " + i2 + " for commandId: " + str));
        } else if (nullableViewState.mViewManager == null) {
            throw new IllegalStateException("Unable to find viewState manager for tag " + i2);
        } else {
            if (nullableViewState.mView == null) {
                throw new IllegalStateException("Unable to find viewState view for tag " + i2);
            }
            nullableViewState.mViewManager.receiveCommand((ViewManager) nullableViewState.mView, str, readableArray);
        }
    }

    public void sendAccessibilityEvent(int i2, int i3) {
        ViewState viewState = getViewState(i2);
        if (viewState.mViewManager == null) {
            throw new IllegalStateException("Unable to find viewState manager for tag " + i2);
        }
        if (viewState.mView == null) {
            throw new IllegalStateException("Unable to find viewState view for tag " + i2);
        }
        viewState.mView.sendAccessibilityEvent(i3);
    }

    private static ViewGroupManager<ViewGroup> getViewGroupManager(ViewState viewState) {
        if (viewState.mViewManager == null) {
            throw new IllegalStateException("Unable to find ViewManager for view: " + viewState);
        }
        return (ViewGroupManager) viewState.mViewManager;
    }

    public void removeViewAt(int i2, int i3) {
        UiThreadUtil.assertOnUiThread();
        ViewState nullableViewState = getNullableViewState(i2);
        if (nullableViewState == null) {
            ReactSoftException.logSoftException(TAG, new IllegalStateException("Unable to find viewState for tag: " + i2 + " for removeViewAt"));
            return;
        }
        ViewGroup viewGroup = (ViewGroup) nullableViewState.mView;
        if (viewGroup == null) {
            throw new IllegalStateException("Unable to find view for tag " + i2);
        }
        getViewGroupManager(nullableViewState).removeViewAt(viewGroup, i3);
    }

    public void createView(ThemedReactContext themedReactContext, String str, int i2, ReadableMap readableMap, StateWrapper stateWrapper, boolean z) {
        View view;
        ViewManager viewManager;
        if (getNullableViewState(i2) != null) {
            return;
        }
        ReactStylesDiffMap reactStylesDiffMap = readableMap != null ? new ReactStylesDiffMap(readableMap) : null;
        if (z) {
            viewManager = this.mViewManagerRegistry.get(str);
            view = viewManager.createView(themedReactContext, reactStylesDiffMap, stateWrapper, this.mJSResponderHandler);
            view.setId(i2);
        } else {
            view = null;
            viewManager = null;
        }
        ViewState viewState = new ViewState(i2, view, viewManager);
        viewState.mCurrentProps = reactStylesDiffMap;
        viewState.mCurrentState = stateWrapper != null ? stateWrapper.getState() : null;
        this.mTagToViewState.put(Integer.valueOf(i2), viewState);
    }

    public void updateProps(int i2, ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i2);
        viewState.mCurrentProps = new ReactStylesDiffMap(readableMap);
        View view = viewState.mView;
        if (view == null) {
            throw new IllegalStateException("Unable to find view for tag " + i2);
        }
        if (viewState.mViewManager != null) {
            viewState.mViewManager.updateProperties(view, viewState.mCurrentProps);
        }
    }

    public void updateLayout(int i2, int i3, int i4, int i5, int i6) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i2);
        if (viewState.mIsRoot) {
            return;
        }
        View view = viewState.mView;
        if (view == null) {
            throw new IllegalStateException("Unable to find View for tag: " + i2);
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(i5, 1073741824), View.MeasureSpec.makeMeasureSpec(i6, 1073741824));
        ViewParent parent = view.getParent();
        if (parent instanceof RootView) {
            parent.requestLayout();
        }
        view.layout(i3, i4, i5 + i3, i6 + i4);
    }

    public void updatePadding(int i2, int i3, int i4, int i5, int i6) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i2);
        if (viewState.mIsRoot) {
            return;
        }
        View view = viewState.mView;
        if (view == null) {
            throw new IllegalStateException("Unable to find View for tag: " + i2);
        }
        ViewManager viewManager = viewState.mViewManager;
        if (viewManager == null) {
            throw new IllegalStateException("Unable to find ViewManager for view: " + viewState);
        }
        viewManager.setPadding(view, i3, i4, i5, i6);
    }

    public void deleteView(int i2) {
        UiThreadUtil.assertOnUiThread();
        ViewState nullableViewState = getNullableViewState(i2);
        if (nullableViewState == null) {
            ReactSoftException.logSoftException(TAG, new IllegalStateException("Unable to find viewState for tag: " + i2 + " for deleteView"));
            return;
        }
        View view = nullableViewState.mView;
        if (view != null) {
            dropView(view);
        } else {
            this.mTagToViewState.remove(Integer.valueOf(i2));
        }
    }

    public void updateLocalData(int i2, ReadableMap readableMap) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i2);
        if (viewState.mCurrentProps == null) {
            throw new IllegalStateException("Can not update local data to view without props: " + i2);
        }
        if (viewState.mCurrentLocalData != null && readableMap.hasKey("hash") && viewState.mCurrentLocalData.getDouble("hash") == readableMap.getDouble("hash") && viewState.mCurrentLocalData.equals(readableMap)) {
            return;
        }
        viewState.mCurrentLocalData = readableMap;
        ViewManager viewManager = viewState.mViewManager;
        if (viewManager == null) {
            throw new IllegalStateException("Unable to find ViewManager for view: " + viewState);
        }
        Object updateLocalData = viewManager.updateLocalData(viewState.mView, viewState.mCurrentProps, new ReactStylesDiffMap(viewState.mCurrentLocalData));
        if (updateLocalData != null) {
            viewManager.updateExtraData(viewState.mView, updateLocalData);
        }
    }

    public void updateState(int i2, StateWrapper stateWrapper) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i2);
        ReadableNativeMap state = stateWrapper == null ? null : stateWrapper.getState();
        if (viewState.mCurrentState == null || !viewState.mCurrentState.equals(state)) {
            if (viewState.mCurrentState == null && stateWrapper == null) {
                return;
            }
            viewState.mCurrentState = state;
            ViewManager viewManager = viewState.mViewManager;
            if (viewManager == null) {
                throw new IllegalStateException("Unable to find ViewManager for tag: " + i2);
            }
            Object updateState = viewManager.updateState(viewState.mView, viewState.mCurrentProps, stateWrapper);
            if (updateState != null) {
                viewManager.updateExtraData(viewState.mView, updateState);
            }
        }
    }

    public void preallocateView(ThemedReactContext themedReactContext, String str, int i2, ReadableMap readableMap, StateWrapper stateWrapper, boolean z) {
        if (getNullableViewState(i2) != null) {
            throw new IllegalStateException("View for component " + str + " with tag " + i2 + " already exists.");
        }
        createView(themedReactContext, str, i2, readableMap, stateWrapper, z);
    }

    public void updateEventEmitter(int i2, EventEmitterWrapper eventEmitterWrapper) {
        UiThreadUtil.assertOnUiThread();
        getViewState(i2).mEventEmitter = eventEmitterWrapper;
    }

    public synchronized void setJSResponder(int i2, int i3, boolean z) {
        if (!z) {
            this.mJSResponderHandler.setJSResponder(i3, null);
            return;
        }
        ViewState viewState = getViewState(i2);
        View view = viewState.mView;
        if (i3 != i2 && (view instanceof ViewParent)) {
            this.mJSResponderHandler.setJSResponder(i3, (ViewParent) view);
        } else if (view == null) {
            SoftAssertions.assertUnreachable("Cannot find view for tag " + i2 + c.a.DOT);
        } else {
            if (viewState.mIsRoot) {
                SoftAssertions.assertUnreachable("Cannot block native responder on " + i2 + " that is a root view");
            }
            this.mJSResponderHandler.setJSResponder(i3, view.getParent());
        }
    }

    public void clearJSResponder() {
        this.mJSResponderHandler.clearJSResponder();
    }

    public long measure(Context context, String str, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f2, YogaMeasureMode yogaMeasureMode, float f3, YogaMeasureMode yogaMeasureMode2) {
        return this.mViewManagerRegistry.get(str).measure(context, readableMap, readableMap2, readableMap3, f2, yogaMeasureMode, f3, yogaMeasureMode2);
    }

    public EventEmitterWrapper getEventEmitter(int i2) {
        ViewState nullableViewState = getNullableViewState(i2);
        if (nullableViewState == null) {
            return null;
        }
        return nullableViewState.mEventEmitter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static class ViewState {
        public ReadableMap mCurrentLocalData;
        public ReactStylesDiffMap mCurrentProps;
        public ReadableMap mCurrentState;
        public EventEmitterWrapper mEventEmitter;
        final boolean mIsRoot;
        final int mReactTag;
        final View mView;
        final ViewManager mViewManager;

        private ViewState(int i2, View view, ViewManager viewManager) {
            this(i2, view, viewManager, false);
        }

        private ViewState(int i2, View view, ViewManager viewManager, boolean z) {
            this.mCurrentProps = null;
            this.mCurrentLocalData = null;
            this.mCurrentState = null;
            this.mEventEmitter = null;
            this.mReactTag = i2;
            this.mView = view;
            this.mIsRoot = z;
            this.mViewManager = viewManager;
        }

        public String toString() {
            return "ViewState [" + this.mReactTag + "] - isRoot: " + this.mIsRoot + " - props: " + this.mCurrentProps + " - localData: " + this.mCurrentLocalData + " - viewManager: " + this.mViewManager + " - isLayoutOnly: " + (this.mViewManager == null);
        }
    }
}
