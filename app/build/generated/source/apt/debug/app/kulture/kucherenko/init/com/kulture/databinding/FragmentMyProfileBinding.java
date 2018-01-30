package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class FragmentMyProfileBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imageView_myProfile_userPic, 1);
        sViewsWithIds.put(R.id.iv_user_image, 2);
        sViewsWithIds.put(R.id.linearLayout2, 3);
        sViewsWithIds.put(R.id.tv_user_first_name, 4);
        sViewsWithIds.put(R.id.tv_user_last_name, 5);
        sViewsWithIds.put(R.id.imageView_myProfile_logout, 6);
        sViewsWithIds.put(R.id.guideline, 7);
        sViewsWithIds.put(R.id.rv_my_profile, 8);
    }
    // views
    @NonNull
    public final android.support.constraint.Guideline guideline;
    @NonNull
    public final android.widget.ImageView imageViewMyProfileLogout;
    @NonNull
    public final android.widget.ImageView imageViewMyProfileUserPic;
    @NonNull
    public final de.hdodenhof.circleimageview.CircleImageView ivUserImage;
    @NonNull
    public final android.widget.LinearLayout linearLayout2;
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView rvMyProfile;
    @NonNull
    public final android.widget.TextView tvUserFirstName;
    @NonNull
    public final android.widget.TextView tvUserLastName;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMyProfileBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.guideline = (android.support.constraint.Guideline) bindings[7];
        this.imageViewMyProfileLogout = (android.widget.ImageView) bindings[6];
        this.imageViewMyProfileUserPic = (android.widget.ImageView) bindings[1];
        this.ivUserImage = (de.hdodenhof.circleimageview.CircleImageView) bindings[2];
        this.linearLayout2 = (android.widget.LinearLayout) bindings[3];
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvMyProfile = (android.support.v7.widget.RecyclerView) bindings[8];
        this.tvUserFirstName = (android.widget.TextView) bindings[4];
        this.tvUserLastName = (android.widget.TextView) bindings[5];
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
    public static FragmentMyProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMyProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentMyProfileBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.fragment_my_profile, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentMyProfileBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMyProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.fragment_my_profile, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentMyProfileBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMyProfileBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_my_profile_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentMyProfileBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}