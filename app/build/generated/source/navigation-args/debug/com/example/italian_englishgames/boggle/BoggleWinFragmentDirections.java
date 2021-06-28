package com.example.italian_englishgames.boggle;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.italian_englishgames.R;

public class BoggleWinFragmentDirections {
  private BoggleWinFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionBoggleWinFragmentToBoggleGameFragment() {
    return new ActionOnlyNavDirections(R.id.action_boggleWinFragment_to_boggleGameFragment);
  }

  @NonNull
  public static NavDirections actionBoggleWinFragmentToBoggleMenuFragment() {
    return new ActionOnlyNavDirections(R.id.action_boggleWinFragment_to_boggleMenuFragment);
  }
}
