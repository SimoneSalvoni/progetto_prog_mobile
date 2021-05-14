package com.example.italian_englishgames.impiccato;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.italian_englishgames.R;

public class ImpWinFragmentDirections {
  private ImpWinFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionImpWinFragmentToImpGameFragment() {
    return new ActionOnlyNavDirections(R.id.action_impWinFragment_to_impGameFragment);
  }

  @NonNull
  public static NavDirections actionImpWinFragmentToImpMenuFragment() {
    return new ActionOnlyNavDirections(R.id.action_impWinFragment_to_impMenuFragment);
  }
}
