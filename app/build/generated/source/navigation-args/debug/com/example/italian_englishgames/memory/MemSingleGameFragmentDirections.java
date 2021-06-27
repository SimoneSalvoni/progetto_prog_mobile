package com.example.italian_englishgames.memory;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.italian_englishgames.R;

public class MemSingleGameFragmentDirections {
  private MemSingleGameFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionMemSingleGameFragmentToMemWinFragment() {
    return new ActionOnlyNavDirections(R.id.action_memSingleGameFragment_to_memWinFragment);
  }
}
