package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class ActivityChoiceFriendsBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar_friends, 1);
        sViewsWithIds.put(R.id.linearLayout3, 2);
        sViewsWithIds.put(R.id.tv_count_package, 3);
        sViewsWithIds.put(R.id.textView2, 4);
        sViewsWithIds.put(R.id.bt_continue_choice_friends, 5);
        sViewsWithIds.put(R.id.textView4, 6);
        sViewsWithIds.put(R.id.recyclerView_classes, 7);
    }
    // views
    @NonNull
    public final android.widget.Button btContinueChoiceFriends;
    @NonNull
    public final android.widget.LinearLayout linearLayout3;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView recyclerViewClasses;
    @NonNull
    public final android.widget.TextView textView2;
    @NonNull
    public final android.widget.TextView textView4;
    @NonNull
    public final android.support.v7.widget.Toolbar toolbarFriends;
    @NonNull
    public final android.widget.TextView tvCountPackage;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityChoiceFriendsBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.btContinueChoiceFriends = (android.widget.Button) bindings[5];
        this.linearLayout3 = (android.widget.LinearLayout) bindings[2];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recyclerViewClasses = (android.support.v7.widget.RecyclerView) bindings[7];
        this.textView2 = (android.widget.TextView) bindings[4];
        this.textView4 = (android.widget.TextView) bindings[6];
        this.toolbarFriends = (android.support.v7.widget.Toolbar) bindings[1];
        this.tvCountPackage = (android.widget.TextView) bindings[3];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityChoiceFriendsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityChoiceFriendsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityChoiceFriendsBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.activity_choice_friends, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityChoiceFriendsBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityChoiceFriendsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.activity_choice_friends, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityChoiceFriendsBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityChoiceFriendsBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_choice_friends_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityChoiceFriendsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}