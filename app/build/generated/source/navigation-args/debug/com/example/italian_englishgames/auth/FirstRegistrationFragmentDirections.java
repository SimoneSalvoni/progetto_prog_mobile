package com.example.italian_englishgames.auth;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.italian_englishgames.R;

public class FirstRegistrationFragmentDirections {
  private FirstRegistrationFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionFirstRegistrationFragmentToUsernamePhotoSelectionFragment() {
    return new ActionOnlyNavDirections(R.id.action_firstRegistrationFragment_to_usernamePhotoSelectionFragment);
  }
}
