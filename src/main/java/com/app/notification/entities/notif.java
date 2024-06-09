package com.app.notification.entities;



import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import jakarta.persistence.Entity;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(schema = "notif")
public class notif {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int event;
    private String organisateur_email;
    private int invite;
    private String date ;
    private String message;
    private String title;



}
