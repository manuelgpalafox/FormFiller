package com.magp.creditformfiller.service;

import com.magp.creditformfiller.entity.Client;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Page<Client> findAllPaging();

    Page<Client> findAllPaging(int currentPage);

    Page<Client> findAllPaging(int currentPage, String sortField, String sortOrder);

    Client findById(int id);

    void save(Client client);

    void deleteById(int id);
}
