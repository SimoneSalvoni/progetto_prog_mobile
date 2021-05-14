package com.example.italian_englishgames.impiccato;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.example.italian_englishgames.R;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class ImpGameFragmentDirections {
  private ImpGameFragmentDirections() {
  }

  @NonNull
  public static ActionImpGameFragmentToImpWinFragment actionImpGameFragmentToImpWinFragment(
      @NonNull String word) {
    return new ActionImpGameFragmentToImpWinFragment(word);
  }

  @NonNull
  public static ActionImpGameFragmentToImpLoseFragment actionImpGameFragmentToImpLoseFragment(
      @NonNull String word) {
    return new ActionImpGameFragmentToImpLoseFragment(word);
  }

  public static class ActionImpGameFragmentToImpWinFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionImpGameFragmentToImpWinFragment(@NonNull String word) {
      if (word == null) {
        throw new IllegalArgumentException("Argument \"word\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("word", word);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionImpGameFragmentToImpWinFragment setWord(@NonNull String word) {
      if (word == null) {
        throw new IllegalArgumentException("Argument \"word\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("word", word);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("word")) {
        String word = (String) arguments.get("word");
        __result.putString("word", word);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_impGameFragment_to_impWinFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getWord() {
      return (String) arguments.get("word");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionImpGameFragmentToImpWinFragment that = (ActionImpGameFragmentToImpWinFragment) object;
      if (arguments.containsKey("word") != that.arguments.containsKey("word")) {
        return false;
      }
      if (getWord() != null ? !getWord().equals(that.getWord()) : that.getWord() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getWord() != null ? getWord().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionImpGameFragmentToImpWinFragment(actionId=" + getActionId() + "){"
          + "word=" + getWord()
          + "}";
    }
  }

  public static class ActionImpGameFragmentToImpLoseFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionImpGameFragmentToImpLoseFragment(@NonNull String word) {
      if (word == null) {
        throw new IllegalArgumentException("Argument \"word\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("word", word);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionImpGameFragmentToImpLoseFragment setWord(@NonNull String word) {
      if (word == null) {
        throw new IllegalArgumentException("Argument \"word\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("word", word);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("word")) {
        String word = (String) arguments.get("word");
        __result.putString("word", word);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_impGameFragment_to_impLoseFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getWord() {
      return (String) arguments.get("word");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionImpGameFragmentToImpLoseFragment that = (ActionImpGameFragmentToImpLoseFragment) object;
      if (arguments.containsKey("word") != that.arguments.containsKey("word")) {
        return false;
      }
      if (getWord() != null ? !getWord().equals(that.getWord()) : that.getWord() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getWord() != null ? getWord().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionImpGameFragmentToImpLoseFragment(actionId=" + getActionId() + "){"
          + "word=" + getWord()
          + "}";
    }
  }
}
