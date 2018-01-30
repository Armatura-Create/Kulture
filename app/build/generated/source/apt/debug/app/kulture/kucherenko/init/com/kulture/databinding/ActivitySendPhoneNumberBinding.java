package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class ActivitySendPhoneNumberBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(3);
        sIncludes.setIncludes(0, 
            new String[] {"content_send_phone_number"},
            new int[] {1},
            new int[] {R.layout.content_send_phone_number});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 2);
    }
    // views
    @NonNull
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.ContentSendPhoneNumberBinding sendPhoneNumber;
    @NonNull
    public final android.support.v7.widget.Toolbar toolbar;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivitySendPhoneNumberBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.sendPhoneNumber = (app.kulture.kucherenko.init.com.kulture.databinding.ContentSendPhoneNumberBinding) bindings[1];
        setContainedBinding(this.sendPhoneNumber);
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
        sendPhoneNumber.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (sendPhoneNumber.hasPendingBindings()) {
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
                return onChangeSendPhoneNumber((app.kulture.kucherenko.init.com.kulture.databinding.ContentSendPhoneNumberBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeSendPhoneNumber(app.kulture.kucherenko.init.com.kulture.databinding.ContentSendPhoneNumberBinding SendPhoneNumber, int fieldId) {
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
        executeBindingsOn(sendPhoneNumber);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivitySendPhoneNumberBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivitySendPhoneNumberBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivitySendPhoneNumberBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.activity_send_phone_number, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivitySendPhoneNumberBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivitySendPhoneNumberBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.activity_send_phone_number, null, false), bindingComponent);
    }
    @NonNull
    public static ActivitySendPhoneNumberBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivitySendPhoneNumberBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_send_phone_number_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivitySendPhoneNumberBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): sendPhoneNumber
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}