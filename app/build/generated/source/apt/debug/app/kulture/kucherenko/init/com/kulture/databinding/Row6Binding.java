package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class Row6Binding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(7);
        sIncludes.setIncludes(0, 
            new String[] {"item", "item", "item", "item", "item", "item"},
            new int[] {1, 2, 3, 4, 5, 6},
            new int[] {R.layout.item, R.layout.item, R.layout.item, R.layout.item, R.layout.item, R.layout.item});
        sViewsWithIds = null;
    }
    // views
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding item1;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding item2;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding item3;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding item4;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding item5;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding item6;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public Row6Binding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 6);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.item1 = (app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding) bindings[1];
        setContainedBinding(this.item1);
        this.item2 = (app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding) bindings[2];
        setContainedBinding(this.item2);
        this.item3 = (app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding) bindings[3];
        setContainedBinding(this.item3);
        this.item4 = (app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding) bindings[4];
        setContainedBinding(this.item4);
        this.item5 = (app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding) bindings[5];
        setContainedBinding(this.item5);
        this.item6 = (app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding) bindings[6];
        setContainedBinding(this.item6);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x40L;
        }
        item1.invalidateAll();
        item2.invalidateAll();
        item3.invalidateAll();
        item4.invalidateAll();
        item5.invalidateAll();
        item6.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (item1.hasPendingBindings()) {
            return true;
        }
        if (item2.hasPendingBindings()) {
            return true;
        }
        if (item3.hasPendingBindings()) {
            return true;
        }
        if (item4.hasPendingBindings()) {
            return true;
        }
        if (item5.hasPendingBindings()) {
            return true;
        }
        if (item6.hasPendingBindings()) {
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
                return onChangeItem6((app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding) object, fieldId);
            case 1 :
                return onChangeItem4((app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding) object, fieldId);
            case 2 :
                return onChangeItem5((app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding) object, fieldId);
            case 3 :
                return onChangeItem2((app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding) object, fieldId);
            case 4 :
                return onChangeItem3((app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding) object, fieldId);
            case 5 :
                return onChangeItem1((app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeItem6(app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding Item6, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeItem4(app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding Item4, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeItem5(app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding Item5, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeItem2(app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding Item2, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeItem3(app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding Item3, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeItem1(app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding Item1, int fieldId) {
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
        executeBindingsOn(item1);
        executeBindingsOn(item2);
        executeBindingsOn(item3);
        executeBindingsOn(item4);
        executeBindingsOn(item5);
        executeBindingsOn(item6);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static Row6Binding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static Row6Binding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<Row6Binding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.row6, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static Row6Binding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static Row6Binding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.row6, null, false), bindingComponent);
    }
    @NonNull
    public static Row6Binding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static Row6Binding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/row6_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new Row6Binding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): item6
        flag 1 (0x2L): item4
        flag 2 (0x3L): item5
        flag 3 (0x4L): item2
        flag 4 (0x5L): item3
        flag 5 (0x6L): item1
        flag 6 (0x7L): null
    flag mapping end*/
    //end
}