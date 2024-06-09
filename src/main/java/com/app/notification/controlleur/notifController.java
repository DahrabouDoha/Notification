package com.app.notification.controlleur;



import com.app.notification.service.notifService;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notif")
public class notifController {

    private notifService notifService;
    @Autowired
    public notifController(notifService notifService) {
        this.notifService = notifService;
    }

    @PostMapping("/notifications/ajouter")
    public void ajouterNotification(@RequestBody JsonNode jsonNode) {
        // Convertir JsonNode en JSONObject
        JSONObject jsonObject = new JSONObject(jsonNode.toString());

        // Appeler notifService.Ajouter avec le JSONObject
        notifService.Ajouter(jsonObject);
    }

    @GetMapping("/differenceDates")
    public int differenceDates(@RequestParam String date) {
        // Appel de la méthode calculDifferenceDates
        return notifService.calcul(date);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/notifications/rappel/{id_invite}")
    public List<String> rappel(@PathVariable int id_invite) {
       return notifService.rappel(id_invite);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/notifications/{userId}")
    public List<String> getNotificationsByUserId(@PathVariable int userId) {
        return notifService.getnotifbyiduser(userId);
    }

    @DeleteMapping("/deleteByEvent/{event}")
    public void deleteNotificationsByEvent(@PathVariable int event) {
        notifService.deleteNotificationsByEvent(event);
    }
}
