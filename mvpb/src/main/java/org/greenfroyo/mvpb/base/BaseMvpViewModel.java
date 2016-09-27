package org.greenfroyo.mvpb.base;

import android.databinding.BaseObservable;
import android.databinding.Observable;


import org.greenfroyo.mvpb.model.MvpViewModel;
import org.parceler.Parcel;
import org.parceler.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fchristysen on 7/17/16.
 */

@Parcel
public class BaseMvpViewModel extends BaseObservable implements MvpViewModel {

    public BaseMvpViewModel() {

    }


}
