package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class FragmentReservationsMyBookingsBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rv_reservations, 1);
        sViewsWithIds.put(R.id.other, 2);
        sViewsWithIds.put(R.id.cv_list_past, 3);
        sViewsWithIds.put(R.id.ripper_list_past, 4);
        sViewsWithIds.put(R.id.tv_past_activities, 5);
    }
    // views
    @NonNull
    public final android.support.v7.widget.CardView cvListPast;
    @NonNull
    public final android.widget.LinearLayout other;
    @NonNull
    public final com.balysv.materialripple.MaterialRippleLayout ripperListPast;
    @NonNull
    public final android.support.v7.widget.RecyclerView rvReservations;
    @NonNull
    public final android.support.v4.widget.SwipeRefreshLayout swipeRefreshReservation;
    @NonNull
    public final android.widget.TextView tvPastActivities;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentReservationsMyBookingsBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.cvListPast = (android.support.v7.widget.CardView) bindings[3];
        this.other = (android.widget.LinearLayout) bindings[2];
        this.ripperListPast = (com.balysv.materialripple.MaterialRippleLayout) bindings[4];
        this.rvReservations = (android.support.v7.widget.RecyclerView) bindings[1];
        this.swipeRefreshReservation = (android.support.v4.widget.SwipeRefreshLayout) bindings[0];
        this.swipeRefreshReservation.setTag(null);
        this.tvPastActivities = (android.widget.TextView) bindings[5];
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
    public static FragmentReservationsMyBookingsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentReservationsMyBookingsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentReservationsMyBookingsBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.fragment_reservations_my_bookings, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentReservationsMyBookingsBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentReservationsMyBookingsBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.fragment_reservations_my_bookings, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentReservationsMyBookingsBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentReservationsMyBookingsBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_reservations_my_bookings_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentReservationsMyBookingsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}