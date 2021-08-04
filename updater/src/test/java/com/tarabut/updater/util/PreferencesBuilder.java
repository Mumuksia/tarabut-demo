package com.tarabut.updater.util;

import com.tarabut.updater.dto.Preferences;

public class PreferencesBuilder {

    public static Preferences createFalsePreference(String userId){
        return new Preferences(userId, false, false, false);
    }
}
