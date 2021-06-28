package com.example.italian_englishgames.boggle;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.italian_englishgames.R;

public class BoggleMenuFragmentDirections {
  private BoggleMenuFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionBoggleMenuFragmentToBoggleGameFragment() {
    return new ActionOnlyNavDirections(R.id.action_boggleMenuFragment_to_boggleGameFragment);
  }
}
