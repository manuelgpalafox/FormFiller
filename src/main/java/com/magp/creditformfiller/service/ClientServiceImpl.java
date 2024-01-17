package com.magp.creditformfiller.service;

import com.magp.creditformfiller.dao.ClientRepository;
import com.magp.creditformfiller.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
//        return clientRepository.findAll();

        // Obtener el nombre de usuario del usuario autenticado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        // Obtener los clientes que tienen el mismo nombre de usuario
        return clientRepository.findByUser(username);
    }

    @Override
    public Page<Client> findAllPaging() {
        // Obtener el nombre de usuario del usuario autenticado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        // Configurar la paginación
        Pageable pageable = PageRequest.of(0, 4);

        // Obtener los clientes que tienen el mismo nombre de usuario con paginación
        return clientRepository.findByUser(username, pageable);
    }

    @Override
    public Page<Client> findAllPaging(int currentPage) {
        // Obtener el nombre de usuario del usuario autenticado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        // Configurar la paginación
        Pageable pageable = PageRequest.of(currentPage -1 , 4);

        // Obtener los clientes que tienen el mismo nombre de usuario con paginación
        return clientRepository.findByUser(username, pageable);
    }

    @Override
    public Page<Client> findAllPaging(int currentPage, String sortField, String sortOrder) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Sort sort = Sort.by(sortOrder.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        Pageable pageable = PageRequest.of(currentPage - 1, 4, sort);

        return clientRepository.findByUser(username, pageable);
    }

    @Override
    public Client findById(int id) {
        Optional<Client> result = clientRepository.findById(id);

        Client client = null;

        if (result.isPresent()) {
            client = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + id);
        }

        return client;
    }

    @Override
    public void save(Client client) {

//        clientRepository.save(client);

        // Obtener el nombre de usuario del usuario autenticado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        // Asignar el nombre de usuario al cliente
        client.setUser(username);
        // Guardar el cliente en la base de datos
        clientRepository.save(client);

    }

    @Override
    public void deleteById(int id) {

//        clientRepository.deleteById(id);
        Client cliente = clientRepository.findById(id).orElse(null);
        if (cliente != null) {
            // Obtener el nombre de usuario del usuario autenticado
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = "";
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            // Verificar que el usuario sea el mismo que registró al cliente
            if (cliente.getUser().equals(username)) {
                // Eliminar el cliente de la base de datos
                clientRepository.delete(cliente);
            } else {
                // Lanzar una excepción o mostrar un mensaje de error
            }
        } else {
            // Lanzar una excepción o mostrar un mensaje de error
        }
    }
}
