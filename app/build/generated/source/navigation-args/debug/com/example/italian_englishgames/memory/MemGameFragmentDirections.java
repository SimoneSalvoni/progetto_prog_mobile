package com.example.italian_englishgames.memory;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.italian_englishgames.R;

public class MemGameFragmentDirections {
  private MemGameFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionMemGameFragmentToMemWinFragment() {
    return new ActionOnlyNavDirections(R.id.action_memGameFragment_to_memWinFragment);
  }
}
