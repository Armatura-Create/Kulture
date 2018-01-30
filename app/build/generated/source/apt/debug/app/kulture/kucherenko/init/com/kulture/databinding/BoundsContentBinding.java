package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class BoundsContentBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(7);
        sIncludes.setIncludes(0, 
            new String[] {"row5", "row6", "row5", "row5", "row6", "row5"},
            new int[] {1, 2, 3, 4, 5, 6},
            new int[] {R.layout.row5, R.layout.row6, R.layout.row5, R.layout.row5, R.layout.row6, R.layout.row5});
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding row1;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.Row6Binding row2;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding row3;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding row4;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.Row6Binding row5;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding row6;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BoundsContentBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 6);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.row1 = (app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding) bindings[1];
        setContainedBinding(this.row1);
        this.row2 = (app.kulture.kucherenko.init.com.kulture.databinding.Row6Binding) bindings[2];
        setContainedBinding(this.row2);
        this.row3 = (app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding) bindings[3];
        setContainedBinding(this.row3);
        this.row4 = (app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding) bindings[4];
        setContainedBinding(this.row4);
        this.row5 = (app.kulture.kucherenko.init.com.kulture.databinding.Row6Binding) bindings[5];
        setContainedBinding(this.row5);
        this.row6 = (app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding) bindings[6];
        setContainedBinding(this.row6);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x40L;
        }
        row1.invalidateAll();
        row2.invalidateAll();
        row3.invalidateAll();
        row4.invalidateAll();
        row5.invalidateAll();
        row6.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (row1.hasPendingBindings()) {
            return true;
        }
        if (row2.hasPendingBindings()) {
            return true;
        }
        if (row3.hasPendingBindings()) {
            return true;
        }
        if (row4.hasPendingBindings()) {
            return true;
        }
        if (row5.hasPendingBindings()) {
            return true;
        }
        if (row6.hasPendingBindings()) {
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
                return onChangeRow5((app.kulture.kucherenko.init.com.kulture.databinding.Row6Binding) object, fieldId);
            case 1 :
                return onChangeRow4((app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding) object, fieldId);
            case 2 :
                return onChangeRow6((app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding) object, fieldId);
            case 3 :
                return onChangeRow1((app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding) object, fieldId);
            case 4 :
                return onChangeRow3((app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding) object, fieldId);
            case 5 :
                return onChangeRow2((app.kulture.kucherenko.init.com.kulture.databinding.Row6Binding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeRow5(app.kulture.kucherenko.init.com.kulture.databinding.Row6Binding Row5, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeRow4(app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding Row4, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeRow6(app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding Row6, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeRow1(app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding Row1, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeRow3(app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding Row3, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeRow2(app.kulture.kucherenko.init.com.kulture.databinding.Row6Binding Row2, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
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
        executeBindingsOn(row1);
        executeBindingsOn(row2);
        executeBindingsOn(row3);
        executeBindingsOn(row4);
        executeBindingsOn(row5);
        executeBindingsOn(row6);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static BoundsContentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoundsContentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BoundsContentBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.bounds_content, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BoundsContentBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoundsContentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.bounds_content, null, false), bindingComponent);
    }
    @NonNull
    public static BoundsContentBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BoundsContentBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/bounds_content_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BoundsContentBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): row5
        flag 1 (0x2L): row4
        flag 2 (0x3L): row6
        flag 3 (0x4L): row1
        flag 4 (0x5L): row3
        flag 5 (0x6L): row2
        flag 6 (0x7L): null
    flag mapping end*/
    //end
}