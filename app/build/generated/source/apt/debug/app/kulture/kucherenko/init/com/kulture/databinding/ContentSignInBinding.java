package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class ContentSignInBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.fields, 1);
        sViewsWithIds.put(R.id.editText_signIn_phone, 2);
        sViewsWithIds.put(R.id.editText_signIn_password, 3);
        sViewsWithIds.put(R.id.button_signIn_login_liner, 4);
        sViewsWithIds.put(R.id.button_signIn_login, 5);
        sViewsWithIds.put(R.id.textView_signIn_forgotPassword, 6);
        sViewsWithIds.put(R.id.or, 7);
        sViewsWithIds.put(R.id.login_button, 8);
        sViewsWithIds.put(R.id.imageView, 9);
        sViewsWithIds.put(R.id.textView_signIn_not_a_member, 10);
        sViewsWithIds.put(R.id.iv_background_pb_login, 11);
        sViewsWithIds.put(R.id.pb_login, 12);
    }
    // views
    @NonNull
    public final android.widget.Button buttonSignInLogin;
    @NonNull
    public final android.widget.LinearLayout buttonSignInLoginLiner;
    @NonNull
    public final android.widget.EditText editTextSignInPassword;
    @NonNull
    public final android.widget.EditText editTextSignInPhone;
    @NonNull
    public final android.widget.LinearLayout fields;
    @NonNull
    public final android.widget.ImageView imageView;
    @NonNull
    public final android.widget.ImageView ivBackgroundPbLogin;
    @NonNull
    public final com.facebook.login.widget.LoginButton loginButton;
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @NonNull
    public final android.widget.TextView or;
    @NonNull
    public final android.widget.ProgressBar pbLogin;
    @NonNull
    public final android.widget.TextView textViewSignInForgotPassword;
    @NonNull
    public final android.widget.LinearLayout textViewSignInNotAMember;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ContentSignInBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds);
        this.buttonSignInLogin = (android.widget.Button) bindings[5];
        this.buttonSignInLoginLiner = (android.widget.LinearLayout) bindings[4];
        this.editTextSignInPassword = (android.widget.EditText) bindings[3];
        this.editTextSignInPhone = (android.widget.EditText) bindings[2];
        this.fields = (android.widget.LinearLayout) bindings[1];
        this.imageView = (android.widget.ImageView) bindings[9];
        this.ivBackgroundPbLogin = (android.widget.ImageView) bindings[11];
        this.loginButton = (com.facebook.login.widget.LoginButton) bindings[8];
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.or = (android.widget.TextView) bindings[7];
        this.pbLogin = (android.widget.ProgressBar) bindings[12];
        this.textViewSignInForgotPassword = (android.widget.TextView) bindings[6];
        this.textViewSignInNotAMember = (android.widget.LinearLayout) bindings[10];
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
    public static ContentSignInBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentSignInBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ContentSignInBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.content_sign_in, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ContentSignInBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentSignInBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.content_sign_in, null, false), bindingComponent);
    }
    @NonNull
    public static ContentSignInBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentSignInBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/content_sign_in_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ContentSignInBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}