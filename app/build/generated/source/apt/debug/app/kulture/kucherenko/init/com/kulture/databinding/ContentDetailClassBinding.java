package app.kulture.kucherenko.init.com.kulture.databinding;
import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class ContentDetailClassBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(22);
        sIncludes.setIncludes(1, 
            new String[] {"detail_card"},
            new int[] {2},
            new int[] {R.layout.detail_card});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imageView_detail_class_top, 3);
        sViewsWithIds.put(R.id.textView_detail_class_name, 4);
        sViewsWithIds.put(R.id.textView_detail_class_teacher, 5);
        sViewsWithIds.put(R.id.ratingBar_detail_class, 6);
        sViewsWithIds.put(R.id.liner_detail_classes_info, 7);
        sViewsWithIds.put(R.id.imageView_detail_class_info, 8);
        sViewsWithIds.put(R.id.textView_detail_classes_info, 9);
        sViewsWithIds.put(R.id.divider1, 10);
        sViewsWithIds.put(R.id.textView_detail_class_about_text, 11);
        sViewsWithIds.put(R.id.textView_detail_class_about, 12);
        sViewsWithIds.put(R.id.divider2, 13);
        sViewsWithIds.put(R.id.linearLayout, 14);
        sViewsWithIds.put(R.id.textView_detail_classes_cancellation, 15);
        sViewsWithIds.put(R.id.textView_detail_class_cancellation_date, 16);
        sViewsWithIds.put(R.id.liner_detail_class_photos, 17);
        sViewsWithIds.put(R.id.imageView_detail_class_photo, 18);
        sViewsWithIds.put(R.id.textView_detail_class_photos, 19);
        sViewsWithIds.put(R.id.divider3, 20);
        sViewsWithIds.put(R.id.recyclerView_detail_classes, 21);
    }
    // views
    @Nullable
    public final app.kulture.kucherenko.init.com.kulture.databinding.DetailCardBinding cardViewDetailClass;
    @NonNull
    public final android.view.View divider1;
    @NonNull
    public final android.view.View divider2;
    @NonNull
    public final android.view.View divider3;
    @NonNull
    public final android.widget.ImageView imageViewDetailClassInfo;
    @NonNull
    public final android.widget.ImageView imageViewDetailClassPhoto;
    @NonNull
    public final android.widget.ImageView imageViewDetailClassTop;
    @NonNull
    public final android.widget.LinearLayout linearLayout;
    @NonNull
    public final android.widget.LinearLayout linerDetailClassPhotos;
    @NonNull
    public final android.widget.LinearLayout linerDetailClassesInfo;
    @NonNull
    private final android.widget.ScrollView mboundView0;
    @NonNull
    private final android.support.constraint.ConstraintLayout mboundView1;
    @NonNull
    public final com.hedgehog.ratingbar.RatingBar ratingBarDetailClass;
    @NonNull
    public final android.support.v7.widget.RecyclerView recyclerViewDetailClasses;
    @NonNull
    public final android.widget.TextView textViewDetailClassAbout;
    @NonNull
    public final android.widget.TextView textViewDetailClassAboutText;
    @NonNull
    public final android.widget.TextView textViewDetailClassCancellationDate;
    @NonNull
    public final android.widget.TextView textViewDetailClassName;
    @NonNull
    public final android.widget.TextView textViewDetailClassPhotos;
    @NonNull
    public final android.widget.TextView textViewDetailClassTeacher;
    @NonNull
    public final android.widget.TextView textViewDetailClassesCancellation;
    @NonNull
    public final android.widget.TextView textViewDetailClassesInfo;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ContentDetailClassBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds);
        this.cardViewDetailClass = (app.kulture.kucherenko.init.com.kulture.databinding.DetailCardBinding) bindings[2];
        setContainedBinding(this.cardViewDetailClass);
        this.divider1 = (android.view.View) bindings[10];
        this.divider2 = (android.view.View) bindings[13];
        this.divider3 = (android.view.View) bindings[20];
        this.imageViewDetailClassInfo = (android.widget.ImageView) bindings[8];
        this.imageViewDetailClassPhoto = (android.widget.ImageView) bindings[18];
        this.imageViewDetailClassTop = (android.widget.ImageView) bindings[3];
        this.linearLayout = (android.widget.LinearLayout) bindings[14];
        this.linerDetailClassPhotos = (android.widget.LinearLayout) bindings[17];
        this.linerDetailClassesInfo = (android.widget.LinearLayout) bindings[7];
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.support.constraint.ConstraintLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.ratingBarDetailClass = (com.hedgehog.ratingbar.RatingBar) bindings[6];
        this.recyclerViewDetailClasses = (android.support.v7.widget.RecyclerView) bindings[21];
        this.textViewDetailClassAbout = (android.widget.TextView) bindings[12];
        this.textViewDetailClassAboutText = (android.widget.TextView) bindings[11];
        this.textViewDetailClassCancellationDate = (android.widget.TextView) bindings[16];
        this.textViewDetailClassName = (android.widget.TextView) bindings[4];
        this.textViewDetailClassPhotos = (android.widget.TextView) bindings[19];
        this.textViewDetailClassTeacher = (android.widget.TextView) bindings[5];
        this.textViewDetailClassesCancellation = (android.widget.TextView) bindings[15];
        this.textViewDetailClassesInfo = (android.widget.TextView) bindings[9];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        cardViewDetailClass.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (cardViewDetailClass.hasPendingBindings()) {
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
                return onChangeCardViewDetailClass((app.kulture.kucherenko.init.com.kulture.databinding.DetailCardBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeCardViewDetailClass(app.kulture.kucherenko.init.com.kulture.databinding.DetailCardBinding CardViewDetailClass, int fieldId) {
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
        executeBindingsOn(cardViewDetailClass);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ContentDetailClassBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentDetailClassBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ContentDetailClassBinding>inflate(inflater, app.kulture.kucherenko.init.com.kulture.R.layout.content_detail_class, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ContentDetailClassBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentDetailClassBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(app.kulture.kucherenko.init.com.kulture.R.layout.content_detail_class, null, false), bindingComponent);
    }
    @NonNull
    public static ContentDetailClassBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ContentDetailClassBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/content_detail_class_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ContentDetailClassBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): cardViewDetailClass
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}