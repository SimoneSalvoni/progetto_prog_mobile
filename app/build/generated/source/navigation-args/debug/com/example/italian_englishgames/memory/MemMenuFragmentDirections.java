package com.example.italian_englishgames.memory;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.italian_englishgames.R;

public class MemMenuFragmentDirections {
  private MemMenuFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionMemMenuFragmentToMemGameFragment() {
    return new ActionOnlyNavDirections(R.id.action_memMenuFragment_to_memGameFragment);
  }
}
