package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class RecyclerItemMyBookingsReservationsBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linear_cardview_item, 1);
        sViewsWithIds.put(R.id.iv_card_item, 2);
        sViewsWithIds.put(R.id.tv_item_description, 3);
        sViewsWithIds.put(R.id.tv_item_trainer_name, 4);
        sViewsWithIds.put(R.id.tv_item_day, 5);
        sViewsWithIds.put(R.id.tv_item_time_start, 6);
        sViewsWithIds.put(R.id.tv_item_time, 7);
        sViewsWithIds.put(R.id.tv_item_time_end, 8);
        sViewsWithIds.put(R.id.linear_add_calendar, 9);
        sViewsWithIds.put(R.id.ripple_add_calendar, 10);
        sViewsWithIds.put(R.id.linear_call, 11);
        sViewsWithIds.put(R.id.ripple_call, 12);
        sViewsWithIds.put(R.id.linear_cancel, 13);
        sViewsWithIds.put(R.id.ripple_cancel, 14);
    }
    // views
    @NonNull
    public final android.widget.ImageView ivCardItem;
    @NonNull
    public final android.widget.LinearLayout linearAddCalendar;
    @NonNull
    public final android.widget.LinearLayout linearCall;
    @NonNull
    public final android.widget.LinearLayout linearCancel;
    @NonNull
    public final android.widget.LinearLayout linearCardviewItem;
    @NonNull
    public final android.widget.LinearLayout linearPayment;
    @NonNull
    public final com.balysv.materialripple.MaterialRippleLayout rippleAddCalendar;
    @NonNull
    public final com.balysv.materialripple.MaterialRippleLayout rippleCall;
    @NonNull
    public final com.balysv.materialripple.MaterialRippleLayout rippleCancel;
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

    public RecyclerItemMyBookingsReservationsBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds);
        this.ivCardItem = (android.widget.ImageView) bindings[2];
        this.linearAddCalendar = (android.widget.LinearLayout) bindings[9];
        this.linearCall = (android.widget.LinearLayout) bindings[11];
        this.linearCancel = (android.widget.LinearLayout) bindings[13];
        this.linearCardviewItem = (android.widget.LinearLayout) bindings[1];
        this.linearPayment = (android.widget.LinearLayout) bindings[0];
        this.linearPayment.setTag(null);
        this.rippleAddCalendar = (com.balysv.materialripple.MaterialRippleLayout) bindings[10];
        this.rippleCall = (com.balysv.materialripple.MaterialRippleLayout) bindings[12];
        this.rippleCancel = (com.balysv.materialripple.MaterialRippleLayout) bindings[14];
        this.tvItemDay = (android.widget.TextView) bindings[5];
        this.tvItemDescription = (android.widget.TextView) bindings[3];
        this.tvItemTime = (android.widget.TextView) bindings[7];
        this.tvItemTimeEnd = (android.widget.TextView) bindings[8];
        this.tvItemTimeStart = (android.widget.TextView) bindings[6];
        this.tvItemTrainerName = (android.widget.TextView) bindings[4];
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
    public static RecyclerItemMyBookingsReservationsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static RecyclerItemMyBookingsReservationsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<RecyclerItemMyBookingsReservationsBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.recycler_item_my_bookings_reservations, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static RecyclerItemMyBookingsReservationsBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static RecyclerItemMyBookingsReservationsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.recycler_item_my_bookings_reservations, null, false), bindingComponent);
    }
    @NonNull
    public static RecyclerItemMyBookingsReservationsBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static RecyclerItemMyBookingsReservationsBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/recycler_item_my_bookings_reservations_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new RecyclerItemMyBookingsReservationsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}