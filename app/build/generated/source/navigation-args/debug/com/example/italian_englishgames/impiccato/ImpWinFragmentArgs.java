package com.example.italian_englishgames.impiccato;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class ImpWinFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ImpWinFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private ImpWinFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ImpWinFragmentArgs fromBundle(@NonNull Bundle bundle) {
    ImpWinFragmentArgs __result = new ImpWinFragmentArgs();
    bundle.setClassLoader(ImpWinFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("word")) {
      String word;
      word = bundle.getString("word");
      if (word == null) {
        throw new IllegalArgumentException("Argument \"word\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("word", word);
    } else {
      throw new IllegalArgumentException("Required argument \"word\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getWord() {
    return (String) arguments.get("word");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("word")) {
      String word = (String) arguments.get("word");
      __result.putString("word", word);
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    ImpWinFragmentArgs that = (ImpWinFragmentArgs) object;
    if (arguments.containsKey("word") != that.arguments.containsKey("word")) {
      return false;
    }
    if (getWord() != null ? !getWord().equals(that.getWord()) : that.getWord() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getWord() != null ? getWord().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ImpWinFragmentArgs{"
        + "word=" + getWord()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(ImpWinFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull String word) {
      if (word == null) {
        throw new IllegalArgumentException("Argument \"word\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("word", word);
    }

    @NonNull
    public ImpWinFragmentArgs build() {
      ImpWinFragmentArgs result = new ImpWinFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setWord(@NonNull String word) {
      if (word == null) {
        throw new IllegalArgumentException("Argument \"word\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("word", word);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getWord() {
      return (String) arguments.get("word");
    }
  }
}
