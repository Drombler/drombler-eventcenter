package org.drombler.eventcenter.integration.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Florian
 */
@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    public List<EventEntity> findAllByOwner(String dromblerIdFormatted);

//    List<VendorEntity> findAllByUsername(String username);

    
}
