package com.example.italian_englishgames.impiccato;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.italian_englishgames.R;

public class ImpLoseFragmentDirections {
  private ImpLoseFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionImpLoseFragmentToImpGameFragment() {
    return new ActionOnlyNavDirections(R.id.action_impLoseFragment_to_impGameFragment);
  }

  @NonNull
  public static NavDirections actionImpLoseFragmentToImpMenuFragment() {
    return new ActionOnlyNavDirections(R.id.action_impLoseFragment_to_impMenuFragment);
  }
}
