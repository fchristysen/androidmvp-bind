package org.greenfroyo.mvpb.view;

import android.databinding.Observable;

/**
 * Created by fchristysen on 9/27/16.
 * This class is used to identified the main View listener which listens to the View Model
 * , since View Model could have multiple listeners.
 */

public abstract class MainPropertyChangeCallback extends Observable.OnPropertyChangedCallback {
}
