package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class ActivitySignInBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(3);
        sIncludes.setIncludes(0, 
            new String[] {"content_sign_in"},
            new int[] {1},
            new int[] {R.layout.content_sign_in});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 2);
    }
    // views
    @NonNull
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.ContentSignInBinding signinLayout;
    @NonNull
    public final android.support.v7.widget.Toolbar toolbar;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivitySignInBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.signinLayout = (app.kulture.kucherenko.init.com.kulture.databinding.ContentSignInBinding) bindings[1];
        setContainedBinding(this.signinLayout);
        this.toolbar = (android.support.v7.widget.Toolbar) bindings[2];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        signinLayout.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (signinLayout.hasPendingBindings()) {
            return true;
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
            case 0 :
                return onChangeSigninLayout((app.kulture.kucherenko.init.com.kulture.databinding.ContentSignInBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeSigninLayout(app.kulture.kucherenko.init.com.kulture.databinding.ContentSignInBinding SigninLayout, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        executeBindingsOn(signinLayout);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivitySignInBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivitySignInBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivitySignInBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.activity_sign_in, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivitySignInBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivitySignInBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.activity_sign_in, null, false), bindingComponent);
    }
    @NonNull
    public static ActivitySignInBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivitySignInBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_sign_in_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivitySignInBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): signinLayout
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}