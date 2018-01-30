package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class RecyclerItemListPreviousBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ripple_payment, 1);
        sViewsWithIds.put(R.id.linear_cardview_item, 2);
        sViewsWithIds.put(R.id.iv_card_item, 3);
        sViewsWithIds.put(R.id.tv_item_description, 4);
        sViewsWithIds.put(R.id.tv_item_trainer_name, 5);
        sViewsWithIds.put(R.id.tv_item_day, 6);
        sViewsWithIds.put(R.id.tv_item_time_start, 7);
        sViewsWithIds.put(R.id.tv_item_time, 8);
        sViewsWithIds.put(R.id.tv_item_time_end, 9);
    }
    // views
    @NonNull
    public final android.widget.ImageView ivCardItem;
    @NonNull
    public final android.widget.LinearLayout linearCardviewItem;
    @NonNull
    public final android.widget.LinearLayout linearPayment;
    @NonNull
    public final com.balysv.materialripple.MaterialRippleLayout ripplePayment;
    @NonNull
    public final android.widget.TextView tvItemDay;
    @NonNull
    public final android.widget.TextView tvItemDescription;
    @NonNull
    public final android.widget.TextView tvItemTime;
    @NonNull
    public final android.widget.TextView tvItemTimeEnd;
    @NonNull
    public final android.widget.TextView tvItemTimeStart;
    @NonNull
    public final android.widget.TextView tvItemTrainerName;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public RecyclerItemListPreviousBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.ivCardItem = (android.widget.ImageView) bindings[3];
        this.linearCardviewItem = (android.widget.LinearLayout) bindings[2];
        this.linearPayment = (android.widget.LinearLayout) bindings[0];
        this.linearPayment.setTag(null);
        this.ripplePayment = (com.balysv.materialripple.MaterialRippleLayout) bindings[1];
        this.tvItemDay = (android.widget.TextView) bindings[6];
        this.tvItemDescription = (android.widget.TextView) bindings[4];
        this.tvItemTime = (android.widget.TextView) bindings[8];
        this.tvItemTimeEnd = (android.widget.TextView) bindings[9];
        this.tvItemTimeStart = (android.widget.TextView) bindings[7];
        this.tvItemTrainerName = (android.widget.TextView) bindings[5];
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
    public static RecyclerItemListPreviousBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static RecyclerItemListPreviousBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<RecyclerItemListPreviousBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.recycler_item_list_previous, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static RecyclerItemListPreviousBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static RecyclerItemListPreviousBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.recycler_item_list_previous, null, false), bindingComponent);
    }
    @NonNull
    public static RecyclerItemListPreviousBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static RecyclerItemListPreviousBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/recycler_item_list_previous_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new RecyclerItemListPreviousBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}