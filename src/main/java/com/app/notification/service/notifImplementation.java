package com.app.notification.service;

import com.app.notification.entities.notif;
import com.app.notification.repository.notifrepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class notifImplementation implements notifService {


    private notifrepository notifRepository;
@Autowired
    public notifImplementation(notifrepository notifrepository){
       this.notifRepository=notifrepository;
    }
    @Override
    public void Ajouter(JSONObject jsonObject) {
        // Convertir le JSON en objet Notif
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(jsonObject);
            notif notif = objectMapper.readValue(jsonObject.toString(), notif.class);

            // Enregistrer la notification dans la base de données
            notifRepository.save(notif);
        } catch (Exception e) {
            // Gérer les exceptions de désérialisation ou de sauvegarde
            e.printStackTrace();
        }
    }
    @Override
    @Transactional
    public void deleteNotificationsByEvent(int event) {
        notifRepository.deleteByEvent(event);
    }
    @Override
    public  List<String> getnotifbyiduser(int invite){
        List<notif> notifs = notifRepository.findAllByInvite(invite);
        List<String> messages=new ArrayList<>();
        for (notif n:notifs){
            messages.add(n.getMessage());
        }
           return messages;
    }
   @Override
   public int  calcul(String date){
       LocalDate dateDonnee = LocalDate.parse(date);

       // Obtenir la date actuelle
       LocalDate dateActuelle = LocalDate.now();

       // Calculer la différence entre les deux dates
       Period difference = Period.between(dateDonnee, dateActuelle);

       // Récupérer le nombre de jours de différence
       int joursDifference = difference.getDays();

       return joursDifference;
   }
    @Override
    public List<String> rappel(int invite) {
        List<notif> notifs = notifRepository.findAllByInvite(invite);
        List<String> rappel = new ArrayList<>();
        for (notif notif : notifs) {
            int difference = calcul(notif.getDate());
            if(difference<0) {
                String message = "il vous reste " + difference + "j pour " + notif.getTitle();
           rappel.add(message);
        } else if (difference==0) {
                String message = "Aujourd'hui ,c'est " + notif.getTitle();
                rappel.add(message);
                
            }

        }
        return rappel;
    }

}
