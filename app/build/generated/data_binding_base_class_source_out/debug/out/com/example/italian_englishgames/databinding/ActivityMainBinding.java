// Generated by view binder compiler. Do not edit!
package com.example.italian_englishgames.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.italian_englishgames.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonImp;

  @NonNull
  public final Button buttonMem;

  @NonNull
  public final ImageView userImgMain;

  @NonNull
  public final TextView usernameMain;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonImp,
      @NonNull Button buttonMem, @NonNull ImageView userImgMain, @NonNull TextView usernameMain) {
    this.rootView = rootView;
    this.buttonImp = buttonImp;
    this.buttonMem = buttonMem;
    this.userImgMain = userImgMain;
    this.usernameMain = usernameMain;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonImp;
      Button buttonImp = rootView.findViewById(id);
      if (buttonImp == null) {
        break missingId;
      }

      id = R.id.buttonMem;
      Button buttonMem = rootView.findViewById(id);
      if (buttonMem == null) {
        break missingId;
      }

      id = R.id.userImgMain;
      ImageView userImgMain = rootView.findViewById(id);
      if (userImgMain == null) {
        break missingId;
      }

      id = R.id.usernameMain;
      TextView usernameMain = rootView.findViewById(id);
      if (usernameMain == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, buttonImp, buttonMem, userImgMain,
          usernameMain);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}