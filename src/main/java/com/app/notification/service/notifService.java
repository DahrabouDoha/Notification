package com.app.notification.service;


import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface notifService {

    public abstract void Ajouter(JSONObject jsonObject);
    public abstract int  calcul(String date);
    public abstract List<String> rappel(int invite);
    public abstract List<String> getnotifbyiduser(int invite);
    public void deleteNotificationsByEvent(int event);
}
