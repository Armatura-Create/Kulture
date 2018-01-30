package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class DetailCardBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(8);
        sIncludes.setIncludes(1, 
            new String[] {"detail_item"},
            new int[] {2},
            new int[] {R.layout.detail_item});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.textView_detail_class_start_date_text, 3);
        sViewsWithIds.put(R.id.textView_detail_class_start_date, 4);
        sViewsWithIds.put(R.id.textView_detail_class_start_time_text, 5);
        sViewsWithIds.put(R.id.textView_detail_class_start_time, 6);
        sViewsWithIds.put(R.id.button_detail_class_reserve_card, 7);
    }
    // views
    @NonNull
    public final android.widget.Button buttonDetailClassReserveCard;
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.DetailItemBinding group;
    @NonNull
    private final android.support.v7.widget.CardView mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    public final android.widget.TextView textViewDetailClassStartDate;
    @NonNull
    public final android.widget.TextView textViewDetailClassStartDateText;
    @NonNull
    public final android.widget.TextView textViewDetailClassStartTime;
    @NonNull
    public final android.widget.TextView textViewDetailClassStartTimeText;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DetailCardBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.buttonDetailClassReserveCard = (android.widget.Button) bindings[7];
        this.group = (app.kulture.kucherenko.init.com.kulture.databinding.DetailItemBinding) bindings[2];
        setContainedBinding(this.group);
        this.mboundView0 = (android.support.v7.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.textViewDetailClassStartDate = (android.widget.TextView) bindings[4];
        this.textViewDetailClassStartDateText = (android.widget.TextView) bindings[3];
        this.textViewDetailClassStartTime = (android.widget.TextView) bindings[6];
        this.textViewDetailClassStartTimeText = (android.widget.TextView) bindings[5];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        group.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (group.hasPendingBindings()) {
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
                return onChangeGroup((app.kulture.kucherenko.init.com.kulture.databinding.DetailItemBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeGroup(app.kulture.kucherenko.init.com.kulture.databinding.DetailItemBinding Group, int fieldId) {
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
        executeBindingsOn(group);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static DetailCardBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DetailCardBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<DetailCardBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.detail_card, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static DetailCardBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DetailCardBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.detail_card, null, false), bindingComponent);
    }
    @NonNull
    public static DetailCardBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DetailCardBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/detail_card_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new DetailCardBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): group
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}