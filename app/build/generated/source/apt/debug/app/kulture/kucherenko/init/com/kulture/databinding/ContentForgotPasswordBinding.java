package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class ContentForgotPasswordBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.et_forgot_password_email, 1);
        sViewsWithIds.put(R.id.checkEmeilImageView, 2);
        sViewsWithIds.put(R.id.et_secret_key, 3);
        sViewsWithIds.put(R.id.secretKeyImageView, 4);
        sViewsWithIds.put(R.id.et_new_password, 5);
        sViewsWithIds.put(R.id.checkPassImageView, 6);
        sViewsWithIds.put(R.id.et_new_password_again, 7);
        sViewsWithIds.put(R.id.mIvForCheckPassAgainField, 8);
        sViewsWithIds.put(R.id.imageView2, 9);
        sViewsWithIds.put(R.id.bt_continue_forgot_password, 10);
    }
    // views
    @NonNull
    public final android.widget.Button btContinueForgotPassword;
    @NonNull
    public final android.widget.ImageView checkEmeilImageView;
    @NonNull
    public final android.widget.ImageView checkPassImageView;
    @NonNull
    public final android.widget.EditText etForgotPasswordEmail;
    @NonNull
    public final android.widget.EditText etNewPassword;
    @NonNull
    public final android.widget.EditText etNewPasswordAgain;
    @NonNull
    public final android.widget.EditText etSecretKey;
    @NonNull
    public final android.widget.ImageView imageView2;
    @NonNull
    public final android.widget.ImageView mIvForCheckPassAgainField;
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @NonNull
    public final android.widget.ImageView secretKeyImageView;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ContentForgotPasswordBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.btContinueForgotPassword = (android.widget.Button) bindings[10];
        this.checkEmeilImageView = (android.widget.ImageView) bindings[2];
        this.checkPassImageView = (android.widget.ImageView) bindings[6];
        this.etForgotPasswordEmail = (android.widget.EditText) bindings[1];
        this.etNewPassword = (android.widget.EditText) bindings[5];
        this.etNewPasswordAgain = (android.widget.EditText) bindings[7];
        this.etSecretKey = (android.widget.EditText) bindings[3];
        this.imageView2 = (android.widget.ImageView) bindings[9];
        this.mIvForCheckPassAgainField = (android.widget.ImageView) bindings[8];
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.secretKeyImageView = (android.widget.ImageView) bindings[4];
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
    public static ContentForgotPasswordBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentForgotPasswordBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ContentForgotPasswordBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.content_forgot_password, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ContentForgotPasswordBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentForgotPasswordBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.content_forgot_password, null, false), bindingComponent);
    }
    @NonNull
    public static ContentForgotPasswordBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentForgotPasswordBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/content_forgot_password_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ContentForgotPasswordBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}