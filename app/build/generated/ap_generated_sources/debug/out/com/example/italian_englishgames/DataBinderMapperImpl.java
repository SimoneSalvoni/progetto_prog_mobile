package com.example.italian_englishgames;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.italian_englishgames.databinding.ActivityLoginBindingImpl;
import com.example.italian_englishgames.databinding.ActivityRegisterBindingImpl;
import com.example.italian_englishgames.databinding.FragmentFirstRegistrationBindingImpl;
import com.example.italian_englishgames.databinding.FragmentImpGameBindingImpl;
import com.example.italian_englishgames.databinding.FragmentMemGameBindingImpl;
import com.example.italian_englishgames.databinding.FragmentUsernamePhotoSelectionBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYLOGIN = 1;

  private static final int LAYOUT_ACTIVITYREGISTER = 2;

  private static final int LAYOUT_FRAGMENTFIRSTREGISTRATION = 3;

  private static final int LAYOUT_FRAGMENTIMPGAME = 4;

  private static final int LAYOUT_FRAGMENTMEMGAME = 5;

  private static final int LAYOUT_FRAGMENTUSERNAMEPHOTOSELECTION = 6;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(6);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.italian_englishgames.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.italian_englishgames.R.layout.activity_register, LAYOUT_ACTIVITYREGISTER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.italian_englishgames.R.layout.fragment_first_registration, LAYOUT_FRAGMENTFIRSTREGISTRATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.italian_englishgames.R.layout.fragment_imp_game, LAYOUT_FRAGMENTIMPGAME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.italian_englishgames.R.layout.fragment_mem_game, LAYOUT_FRAGMENTMEMGAME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.italian_englishgames.R.layout.fragment_username_photo_selection, LAYOUT_FRAGMENTUSERNAMEPHOTOSELECTION);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYLOGIN: {
          if ("layout/activity_login_0".equals(tag)) {
            return new ActivityLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYREGISTER: {
          if ("layout/activity_register_0".equals(tag)) {
            return new ActivityRegisterBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_register is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFIRSTREGISTRATION: {
          if ("layout/fragment_first_registration_0".equals(tag)) {
            return new FragmentFirstRegistrationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_first_registration is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTIMPGAME: {
          if ("layout/fragment_imp_game_0".equals(tag)) {
            return new FragmentImpGameBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_imp_game is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMEMGAME: {
          if ("layout/fragment_mem_game_0".equals(tag)) {
            return new FragmentMemGameBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_mem_game is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTUSERNAMEPHOTOSELECTION: {
          if ("layout/fragment_username_photo_selection_0".equals(tag)) {
            return new FragmentUsernamePhotoSelectionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_username_photo_selection is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "impViewModel");
      sKeys.put(2, "memViewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(6);

    static {
      sKeys.put("layout/activity_login_0", com.example.italian_englishgames.R.layout.activity_login);
      sKeys.put("layout/activity_register_0", com.example.italian_englishgames.R.layout.activity_register);
      sKeys.put("layout/fragment_first_registration_0", com.example.italian_englishgames.R.layout.fragment_first_registration);
      sKeys.put("layout/fragment_imp_game_0", com.example.italian_englishgames.R.layout.fragment_imp_game);
      sKeys.put("layout/fragment_mem_game_0", com.example.italian_englishgames.R.layout.fragment_mem_game);
      sKeys.put("layout/fragment_username_photo_selection_0", com.example.italian_englishgames.R.layout.fragment_username_photo_selection);
    }
  }
}
