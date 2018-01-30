package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class ContentContactBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_tel, 1);
        sViewsWithIds.put(R.id.tv_facebook, 2);
        sViewsWithIds.put(R.id.tv_instagram, 3);
        sViewsWithIds.put(R.id.tv_youtube, 4);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    @NonNull
    public final android.widget.TextView tvFacebook;
    @NonNull
    public final android.widget.TextView tvInstagram;
    @NonNull
    public final android.widget.TextView tvTel;
    @NonNull
    public final android.widget.TextView tvYoutube;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ContentContactBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.tvFacebook = (android.widget.TextView) bindings[2];
        this.tvInstagram = (android.widget.TextView) bindings[3];
        this.tvTel = (android.widget.TextView) bindings[1];
        this.tvYoutube = (android.widget.TextView) bindings[4];
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
    public static ContentContactBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentContactBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ContentContactBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.content_contact, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ContentContactBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentContactBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.content_contact, null, false), bindingComponent);
    }
    @NonNull
    public static ContentContactBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentContactBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/content_contact_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ContentContactBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}