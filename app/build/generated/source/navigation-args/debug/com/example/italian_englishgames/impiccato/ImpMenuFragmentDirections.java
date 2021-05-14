package com.example.italian_englishgames.impiccato;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.italian_englishgames.R;

public class ImpMenuFragmentDirections {
  private ImpMenuFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionImpMenuFragmentToImpGameFragment() {
    return new ActionOnlyNavDirections(R.id.action_impMenuFragment_to_impGameFragment);
  }
}
