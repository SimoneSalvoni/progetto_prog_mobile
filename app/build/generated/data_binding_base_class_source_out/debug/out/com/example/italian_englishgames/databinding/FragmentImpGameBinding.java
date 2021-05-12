// Generated by data binding compiler. Do not edit!
package com.example.italian_englishgames.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.italian_englishgames.R;
import com.example.italian_englishgames.impiccato.ImpViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentImpGameBinding extends ViewDataBinding {
  @NonNull
  public final TextView displayedText;

  @NonNull
  public final EditText guessText;

  @NonNull
  public final ImageView impImageView;

  @NonNull
  public final Toolbar toolbar2;

  @NonNull
  public final TextView wrongChoice;

  @Bindable
  protected ImpViewModel mImpViewModel;

  protected FragmentImpGameBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView displayedText, EditText guessText, ImageView impImageView, Toolbar toolbar2,
      TextView wrongChoice) {
    super(_bindingComponent, _root, _localFieldCount);
    this.displayedText = displayedText;
    this.guessText = guessText;
    this.impImageView = impImageView;
    this.toolbar2 = toolbar2;
    this.wrongChoice = wrongChoice;
  }

  public abstract void setImpViewModel(@Nullable ImpViewModel impViewModel);

  @Nullable
  public ImpViewModel getImpViewModel() {
    return mImpViewModel;
  }

  @NonNull
  public static FragmentImpGameBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_imp_game, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentImpGameBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentImpGameBinding>inflateInternal(inflater, R.layout.fragment_imp_game, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentImpGameBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_imp_game, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentImpGameBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentImpGameBinding>inflateInternal(inflater, R.layout.fragment_imp_game, null, false, component);
  }

  public static FragmentImpGameBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentImpGameBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentImpGameBinding)bind(component, view, R.layout.fragment_imp_game);
  }
}
