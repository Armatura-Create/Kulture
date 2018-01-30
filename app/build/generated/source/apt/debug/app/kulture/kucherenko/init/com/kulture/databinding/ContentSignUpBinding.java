package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class ContentSignUpBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.editText_signUp_name, 1);
        sViewsWithIds.put(R.id.imageView_signUp_name, 2);
        sViewsWithIds.put(R.id.editText_signUp_phone, 3);
        sViewsWithIds.put(R.id.imageView_signUp_phone, 4);
        sViewsWithIds.put(R.id.editText_signUp_email, 5);
        sViewsWithIds.put(R.id.imageView_signUp_email, 6);
        sViewsWithIds.put(R.id.editText_signUp_password, 7);
        sViewsWithIds.put(R.id.imageView_signUp_password, 8);
        sViewsWithIds.put(R.id.editText_signUp_password_again, 9);
        sViewsWithIds.put(R.id.imageView_signUp_password_again, 10);
        sViewsWithIds.put(R.id.button_signUp, 11);
    }
    // views
    @NonNull
    public final android.widget.Button buttonSignUp;
    @NonNull
    public final android.widget.EditText editTextSignUpEmail;
    @NonNull
    public final android.widget.EditText editTextSignUpName;
    @NonNull
    public final android.widget.EditText editTextSignUpPassword;
    @NonNull
    public final android.widget.EditText editTextSignUpPasswordAgain;
    @NonNull
    public final android.widget.EditText editTextSignUpPhone;
    @NonNull
    public final android.widget.ImageView imageViewSignUpEmail;
    @NonNull
    public final android.widget.ImageView imageViewSignUpName;
    @NonNull
    public final android.widget.ImageView imageViewSignUpPassword;
    @NonNull
    public final android.widget.ImageView imageViewSignUpPasswordAgain;
    @NonNull
    public final android.widget.ImageView imageViewSignUpPhone;
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ContentSignUpBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds);
        this.buttonSignUp = (android.widget.Button) bindings[11];
        this.editTextSignUpEmail = (android.widget.EditText) bindings[5];
        this.editTextSignUpName = (android.widget.EditText) bindings[1];
        this.editTextSignUpPassword = (android.widget.EditText) bindings[7];
        this.editTextSignUpPasswordAgain = (android.widget.EditText) bindings[9];
        this.editTextSignUpPhone = (android.widget.EditText) bindings[3];
        this.imageViewSignUpEmail = (android.widget.ImageView) bindings[6];
        this.imageViewSignUpName = (android.widget.ImageView) bindings[2];
        this.imageViewSignUpPassword = (android.widget.ImageView) bindings[8];
        this.imageViewSignUpPasswordAgain = (android.widget.ImageView) bindings[10];
        this.imageViewSignUpPhone = (android.widget.ImageView) bindings[4];
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
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
    public static ContentSignUpBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentSignUpBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ContentSignUpBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.content_sign_up, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ContentSignUpBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentSignUpBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.content_sign_up, null, false), bindingComponent);
    }
    @NonNull
    public static ContentSignUpBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentSignUpBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/content_sign_up_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ContentSignUpBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}