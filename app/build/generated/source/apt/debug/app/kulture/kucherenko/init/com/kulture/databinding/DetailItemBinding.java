package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class DetailItemBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_credits, 1);
        sViewsWithIds.put(R.id.content, 2);
        sViewsWithIds.put(R.id.group, 3);
        sViewsWithIds.put(R.id.single, 4);
        sViewsWithIds.put(R.id.fifteen, 5);
        sViewsWithIds.put(R.id.thirty, 6);
        sViewsWithIds.put(R.id.gr4, 7);
        sViewsWithIds.put(R.id.pr1, 8);
        sViewsWithIds.put(R.id.pr2, 9);
        sViewsWithIds.put(R.id.pr3, 10);
        sViewsWithIds.put(R.id.pr4, 11);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout content;
    @NonNull
    public final android.widget.RadioButton fifteen;
    @NonNull
    public final android.widget.RadioButton gr4;
    @NonNull
    public final android.widget.RadioGroup group;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.TextView pr1;
    @NonNull
    public final android.widget.TextView pr2;
    @NonNull
    public final android.widget.TextView pr3;
    @NonNull
    public final android.widget.TextView pr4;
    @NonNull
    public final android.widget.RadioButton single;
    @NonNull
    public final android.widget.RadioButton thirty;
    @NonNull
    public final android.widget.TextView tvCredits;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DetailItemBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds);
        this.content = (android.widget.LinearLayout) bindings[2];
        this.fifteen = (android.widget.RadioButton) bindings[5];
        this.gr4 = (android.widget.RadioButton) bindings[7];
        this.group = (android.widget.RadioGroup) bindings[3];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.pr1 = (android.widget.TextView) bindings[8];
        this.pr2 = (android.widget.TextView) bindings[9];
        this.pr3 = (android.widget.TextView) bindings[10];
        this.pr4 = (android.widget.TextView) bindings[11];
        this.single = (android.widget.RadioButton) bindings[4];
        this.thirty = (android.widget.RadioButton) bindings[6];
        this.tvCredits = (android.widget.TextView) bindings[1];
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
    public static DetailItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DetailItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<DetailItemBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.detail_item, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static DetailItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DetailItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.detail_item, null, false), bindingComponent);
    }
    @NonNull
    public static DetailItemBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DetailItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/detail_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new DetailItemBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}