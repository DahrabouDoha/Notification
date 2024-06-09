package com.app.notification.repository;

import com.app.notification.entities.notif;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface notifrepository extends JpaRepository<notif,Integer> {
    List<notif> findAllByInvite(int invite);



    @Transactional
    void deleteByEvent(int event);
}
