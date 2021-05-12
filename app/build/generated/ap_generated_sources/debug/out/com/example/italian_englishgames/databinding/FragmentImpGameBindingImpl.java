package com.example.italian_englishgames.databinding;
import com.example.italian_englishgames.R;
import com.example.italian_englishgames.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentImpGameBindingImpl extends FragmentImpGameBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar2, 3);
        sViewsWithIds.put(R.id.impImageView, 4);
        sViewsWithIds.put(R.id.guessText, 5);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentImpGameBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private FragmentImpGameBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.TextView) bindings[2]
            , (android.widget.EditText) bindings[5]
            , (android.widget.ImageView) bindings[4]
            , (androidx.appcompat.widget.Toolbar) bindings[3]
            , (android.widget.TextView) bindings[1]
            );
        this.displayedText.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.wrongChoice.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
        if (BR.impViewModel == variableId) {
            setImpViewModel((com.example.italian_englishgames.impiccato.ImpViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setImpViewModel(@Nullable com.example.italian_englishgames.impiccato.ImpViewModel ImpViewModel) {
        this.mImpViewModel = ImpViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.impViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeImpViewModelWrongLettersString((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeImpViewModelShownWord((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeImpViewModelWrongLettersString(androidx.lifecycle.LiveData<java.lang.String> ImpViewModelWrongLettersString, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeImpViewModelShownWord(androidx.lifecycle.LiveData<java.lang.String> ImpViewModelShownWord, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
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
        com.example.italian_englishgames.impiccato.ImpViewModel impViewModel = mImpViewModel;
        java.lang.String impViewModelWrongLettersStringGetValue = null;
        java.lang.String impViewModelShownWordGetValue = null;
        java.lang.String impViewModelShownWordToString = null;
        androidx.lifecycle.LiveData<java.lang.String> impViewModelWrongLettersString = null;
        androidx.lifecycle.LiveData<java.lang.String> impViewModelShownWord = null;
        java.lang.String impViewModelWrongLettersStringToString = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (impViewModel != null) {
                        // read impViewModel.wrongLettersString
                        impViewModelWrongLettersString = impViewModel.getWrongLettersString();
                    }
                    updateLiveDataRegistration(0, impViewModelWrongLettersString);


                    if (impViewModelWrongLettersString != null) {
                        // read impViewModel.wrongLettersString.getValue()
                        impViewModelWrongLettersStringGetValue = impViewModelWrongLettersString.getValue();
                    }


                    if (impViewModelWrongLettersStringGetValue != null) {
                        // read impViewModel.wrongLettersString.getValue().toString()
                        impViewModelWrongLettersStringToString = impViewModelWrongLettersStringGetValue.toString();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (impViewModel != null) {
                        // read impViewModel.shownWord
                        impViewModelShownWord = impViewModel.getShownWord();
                    }
                    updateLiveDataRegistration(1, impViewModelShownWord);


                    if (impViewModelShownWord != null) {
                        // read impViewModel.shownWord.getValue()
                        impViewModelShownWordGetValue = impViewModelShownWord.getValue();
                    }


                    if (impViewModelShownWordGetValue != null) {
                        // read impViewModel.shownWord.getValue().toString()
                        impViewModelShownWordToString = impViewModelShownWordGetValue.toString();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.displayedText, impViewModelShownWordToString);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.wrongChoice, impViewModelWrongLettersStringToString);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): impViewModel.wrongLettersString
        flag 1 (0x2L): impViewModel.shownWord
        flag 2 (0x3L): impViewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}