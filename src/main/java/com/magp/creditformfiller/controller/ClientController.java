package com.magp.creditformfiller.controller;

import com.magp.creditformfiller.entity.Client;
import com.magp.creditformfiller.service.ClientServiceImpl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {

    ClientServiceImpl clientService;

    @Autowired
    public ClientController(
            ClientServiceImpl clientService
    ) {
        this.clientService = clientService;
    }

    @GetMapping("/listPaged")
    public String listClientsPaging(Model model) {
        Page<Client> page = clientService.findAllPaging();
        List<Client> clients = page.getContent();
        int currentPage = 1;
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();

        // add to the spring model
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("clients", clients);

        return "client/listClients";
    }

    @GetMapping("/listPaged/{currentPage}")
    public String listClientsPaging(Model model,
                                    @PathVariable("currentPage") int currentPage){
        Page<Client> page = clientService.findAllPaging(currentPage);
        List<Client> clients = page.getContent();
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();

        // add to the spring model
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("clients", clients);

        return "client/listClients";
    }

    @GetMapping("/listPagedSorted")
    public String listClientsPaging(Model model,
                                    @RequestParam(name = "page", defaultValue = "1") int currentPage,
                                    @RequestParam(name = "sortField") String sortField,
                                    @RequestParam(name = "sortOrder") String sortOrder,
                                    @RequestParam(name = "isSorted")String isSorted) {

        Page<Client> page = clientService.findAllPaging(currentPage, sortField, sortOrder);
        List<Client> clients = page.getContent();
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();

        // add to the spring model
        model.addAttribute("page", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);

        model.addAttribute("clients", clients);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("isSorted", isSorted);

        String reverseSortOrder = sortOrder.equals("asc")?"desc":"asc";
        model.addAttribute("reverseSortOrder", reverseSortOrder);

        return "client/listClients";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Client client = new Client();

        model.addAttribute("client", client);

        return "client/clientsForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("client") Client client) {
        clientService.save(client);
        return "redirect:listPaged";
    }

    @GetMapping("/showFormForEditOrDelete")
    public String showFormForEditOrDelete(
            Model model,
            @RequestParam("selectedClient") int selectedClient,
            @RequestParam(name = "action") String action,
            Principal principal
    ) {
        Client client = clientService.findById(selectedClient);

        // Verificar si el usuario autenticado tiene permisos para editar o eliminar este cliente
        if (!client.getUser().equals(principal.getName())) {
            // El usuario autenticado no tiene permisos para editar o eliminar este cliente
            return "redirect:listPaged";
        }

        if ("edit".equals(action)) {
            model.addAttribute("client", client);
            return "client/clientsForm";
        } else if ("delete".equals(action)) {
            clientService.deleteById(selectedClient);
            return "redirect:listPaged";
        }

        return "client/listClients";
    }

}