package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class ActivityContactBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(4);
        sIncludes.setIncludes(0, 
            new String[] {"content_contact"},
            new int[] {1},
            new int[] {R.layout.content_contact});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar_contact, 2);
        sViewsWithIds.put(R.id.container_map, 3);
    }
    // views
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.ContentContactBinding contactLayout;
    @NonNull
    public final android.widget.FrameLayout containerMap;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.Toolbar toolbarContact;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityContactBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.contactLayout = (app.kulture.kucherenko.init.com.kulture.databinding.ContentContactBinding) bindings[1];
        setContainedBinding(this.contactLayout);
        this.containerMap = (android.widget.FrameLayout) bindings[3];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.toolbarContact = (android.support.v7.widget.Toolbar) bindings[2];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        contactLayout.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (contactLayout.hasPendingBindings()) {
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
                return onChangeContactLayout((app.kulture.kucherenko.init.com.kulture.databinding.ContentContactBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeContactLayout(app.kulture.kucherenko.init.com.kulture.databinding.ContentContactBinding ContactLayout, int fieldId) {
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
        executeBindingsOn(contactLayout);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityContactBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityContactBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityContactBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.activity_contact, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityContactBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityContactBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.activity_contact, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityContactBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityContactBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_contact_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityContactBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): contactLayout
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}