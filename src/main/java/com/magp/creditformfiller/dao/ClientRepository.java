package com.magp.creditformfiller.dao;

import com.magp.creditformfiller.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByUser(String email);

    Page<Client> findByUser(String email, Pageable pageable);

}
