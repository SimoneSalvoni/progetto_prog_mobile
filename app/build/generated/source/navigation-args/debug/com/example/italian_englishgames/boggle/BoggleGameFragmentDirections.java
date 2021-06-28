package com.example.italian_englishgames.boggle;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.italian_englishgames.R;

public class BoggleGameFragmentDirections {
  private BoggleGameFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionBoggleGameFragmentToBoggleWinFragment() {
    return new ActionOnlyNavDirections(R.id.action_boggleGameFragment_to_boggleWinFragment);
  }
}
