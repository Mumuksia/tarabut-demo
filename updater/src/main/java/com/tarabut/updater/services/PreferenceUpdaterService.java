package com.tarabut.updater.services;

public interface PreferenceUpdaterService {

    String updatePreferenceForUser(String userId, String sms, String post, String email);
}
