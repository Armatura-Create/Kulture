package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class FragmentBoundsPoundsBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(6);
        sIncludes.setIncludes(1, 
            new String[] {"bounds_content"},
            new int[] {2},
            new int[] {R.layout.bounds_content});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar_tramplines, 3);
        sViewsWithIds.put(R.id.click, 4);
        sViewsWithIds.put(R.id.confirm, 5);
    }
    // views
    @NonNull
    public final android.widget.TextView click;
    @NonNull
    public final android.widget.Button confirm;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.BoundsContentBinding grid;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.HorizontalScrollView mboundView1;
    @NonNull
    public final android.support.v7.widget.Toolbar toolbarTramplines;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentBoundsPoundsBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.click = (android.widget.TextView) bindings[4];
        this.confirm = (android.widget.Button) bindings[5];
        this.grid = (app.kulture.kucherenko.init.com.kulture.databinding.BoundsContentBinding) bindings[2];
        setContainedBinding(this.grid);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.HorizontalScrollView) bindings[1];
        this.mboundView1.setTag(null);
        this.toolbarTramplines = (android.support.v7.widget.Toolbar) bindings[3];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        grid.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (grid.hasPendingBindings()) {
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
                return onChangeGrid((app.kulture.kucherenko.init.com.kulture.databinding.BoundsContentBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeGrid(app.kulture.kucherenko.init.com.kulture.databinding.BoundsContentBinding Grid, int fieldId) {
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
        executeBindingsOn(grid);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentBoundsPoundsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentBoundsPoundsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentBoundsPoundsBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.fragment_bounds_pounds, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentBoundsPoundsBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentBoundsPoundsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.fragment_bounds_pounds, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentBoundsPoundsBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentBoundsPoundsBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_bounds_pounds_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentBoundsPoundsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): grid
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}