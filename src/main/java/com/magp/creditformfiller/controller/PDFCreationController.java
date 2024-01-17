package com.magp.creditformfiller.controller;

import com.magp.creditformfiller.entity.Client;
import com.magp.creditformfiller.service.ClientServiceImpl;
import com.magp.creditformfiller.service.CreatePDFServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/PDFCreation")
public class PDFCreationController {

    ClientServiceImpl clientService;
    CreatePDFServiceImpl createPDFService;

    @Autowired
    public PDFCreationController(
            ClientServiceImpl clientService,
            CreatePDFServiceImpl createPDFService
    ) {
        this.clientService = clientService;
        this.createPDFService = createPDFService;
    }

    @GetMapping("/showFormForCreatingPdf")
    public String showFormForCreatingPdf(
            @RequestParam("clientId") int id,
            @RequestParam("bankName") String bankName,
            Model model
    ) {
        Client client = clientService.findById(id);

        model.addAttribute("client", client);

        return "pdfCreation/createPdf" + bankName;
    }

    @PostMapping("/createPDFBanorte")
    public void createPDFBanorte(
            HttpServletResponse response,
            @ModelAttribute("client") Client client,
            @RequestParam("isClient") String isClient,
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("bussinesAct") String businessAct,
            @RequestParam("hasCredit") String hasCredit,
            @RequestParam("isSolidary") String isSolidary,
            @RequestParam("solidaryNames") String solidaryNames,
            @RequestParam("solidaryLastName") String solidaryLastName,
            @RequestParam("solidarySecondLastName") String solidarySecondLastName,
            @RequestParam("propertyStatus") String propertyStatus,
            @RequestParam("rentPrice") String rentPrice) throws IOException {
        Client tempClient = clientService.findById((int) client.getId());
        tempClient.setBanorte(true);
        clientService.save(tempClient);


        byte[] pdfContent = createPDFService.createPDFBanorte(
                tempClient,
                isClient,
                accountNumber,
                businessAct,
                hasCredit,
                isSolidary,
                solidaryNames,
                solidaryLastName,
                solidarySecondLastName,
                propertyStatus,
                rentPrice
        );

        // Definir el tipo de contenido y las cabeceras de respuesta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename="
                .concat("Banorte ")
                .concat(tempClient.getFirstName())
                .concat(" ")
                .concat(tempClient.getLastName())
                .concat(".pdf"));

        // Obtener el flujo de salida de la respuesta
        OutputStream out = response.getOutputStream();

        // Escribir el contenido del PDF en el flujo de salida
        out.write(pdfContent);

        // Cerrar el flujo de salida
        out.close();
    }

    @PostMapping("/createPDFBbva")
    public void createPDFBbva(
            HttpServletResponse response,
            @ModelAttribute("client") Client client,
            @RequestParam("isClient") String isClient,
            @RequestParam("oldSheetNum") String oldSheetNum,
            @RequestParam("idType") String idType,
            @RequestParam("idNumber") String idNumber,
            @RequestParam("yearsLiving") String yearsLiving,
            @RequestParam("monthsLiving") String monthsLiving,
            @RequestParam("companyType") String companyType,
            @RequestParam("employmentStatus") String employmentStatus,
            @RequestParam("directDepositBank") String directDepositBank,
            @RequestParam("countryOfResidence") String countryOfResidence,
            @RequestParam("isThirdParty") String isThirdParty,
            @RequestParam("thirdPartyDetails") String thirdPartyDetails) throws IOException {
        Client tempClient = clientService.findById((int) client.getId());
        tempClient.setBbva(true);
        clientService.save(tempClient);

        byte[] pdfContent = createPDFService.createPDFBbva(tempClient,
                isClient,
                oldSheetNum,
                idType,
                idNumber,
                yearsLiving,
                monthsLiving,
                companyType,
                employmentStatus,
                directDepositBank,
                countryOfResidence,
                isThirdParty,
                thirdPartyDetails);

        // Definir el tipo de contenido y las cabeceras de respuesta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename="
                .concat("BBVA ")
                .concat(tempClient.getFirstName())
                .concat(" ")
                .concat(tempClient.getLastName())
                .concat(".pdf"));

        // Obtener el flujo de salida de la respuesta
        OutputStream out = response.getOutputStream();

        // Escribir el contenido del PDF en el flujo de salida
        out.write(pdfContent);

        // Cerrar el flujo de salida
        out.close();
    }

    @PostMapping("/createPDFCibanco")
    public void createPDFCibanco(HttpServletResponse response,
                                 @ModelAttribute("client") Client client,
                                 @RequestParam("bussinesAct") String bussinesAct,
                                 @RequestParam("idType") String idType,
                                 @RequestParam("idNumber") String idNumber,
                                 @RequestParam("idExpDate") String idExpDate,
                                 @RequestParam("yearsLiving") String yearsLiving,
                                 @RequestParam("monthsLiving") String monthsLiving,
                                 @RequestParam("employmentStatus") String employmentStatus,
                                 @RequestParam("foreignIDNum") String foreignIDNum,
                                 @RequestParam("foreignCountryAssigned") String foreignCountryAssigned,
                                 @RequestParam("foreignPasspNum") String foreignPasspNum,
                                 @RequestParam("foreignPasspExpDate") String foreignPasspExpDate,
                                 @RequestParam("isResident") String isResident,
                                 @RequestParam("residenceType") String residenceType,
                                 @RequestParam("migratoryDocument") String migratoryDocument,
                                 @RequestParam("foreignYrsLvngCntry") String foreignYrsLvngCntry) throws IOException {

        Client tempClient = clientService.findById((int) client.getId());
        tempClient.setCibanco(true);
        clientService.save(tempClient);

        byte[] pdfContent = createPDFService.createPDFCibanco(tempClient,
                bussinesAct,
                idType,
                idNumber,
                idExpDate,
                yearsLiving,
                monthsLiving,
                employmentStatus,
                foreignIDNum,
                foreignCountryAssigned,
                foreignPasspNum,
                foreignPasspExpDate,
                isResident,
                residenceType,
                migratoryDocument,
                foreignYrsLvngCntry);

        // Definir el tipo de contenido y las cabeceras de respuesta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename="
                .concat("CIBANCO ")
                .concat(tempClient.getFirstName())
                .concat(" ")
                .concat(tempClient.getLastName())
                .concat(".pdf"));

        // Obtener el flujo de salida de la respuesta
        OutputStream out = response.getOutputStream();

        // Escribir el contenido del PDF en el flujo de salida
        out.write(pdfContent);

        // Cerrar el flujo de salida
        out.close();

    }

    @PostMapping("/createPDFCredinissan")
    public void createPDFCredinissan (HttpServletResponse response,
                                      @ModelAttribute("client") Client client,
                                      @RequestParam("requestType") String requestType,
                                      @RequestParam("bussinesAct") String bussinesAct,
                                      @RequestParam("isRebuy") String isRebuy,
                                      @RequestParam("nrEmployee")String nrEmployee,
                                      @RequestParam("idType") String idType,
                                      @RequestParam("idNumber") String idNumber,
                                      @RequestParam("yearsLiving") String yearsLiving,
                                      @RequestParam("monthsLiving") String monthsLiving,
                                      @RequestParam("contractType") String contractType,
                                      @RequestParam("companyType") String companyType,
                                      @RequestParam("bossName") String bossName,
                                      @RequestParam("bossPosition") String bossPosition,
                                      @RequestParam("bossPhoneNumber") String bossPhoneNumber) throws IOException{
        Client tempClient = clientService.findById((int) client.getId());
        tempClient.setCredinissan(true);
        clientService.save(tempClient);

        byte[] pdfContent = createPDFService.createPDFCredinissan(tempClient,
                requestType,
                bussinesAct,
                isRebuy,
                nrEmployee,
                idType,
                idNumber,
                yearsLiving,
                monthsLiving,
                contractType,
                companyType,
                bossName,
                bossPosition,
                bossPhoneNumber);

        // Definir el tipo de contenido y las cabeceras de respuesta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename="
                .concat("CREDINISSAN ")
                .concat(tempClient.getFirstName())
                .concat(" ")
                .concat(tempClient.getLastName())
                .concat(".pdf"));

        // Obtener el flujo de salida de la respuesta
        OutputStream out = response.getOutputStream();

        // Escribir el contenido del PDF en el flujo de salida
        out.write(pdfContent);

        // Cerrar el flujo de salida
        out.close();
    }

    @PostMapping("/createPDFScotiabank")
    public void createPDFScotiabank (HttpServletResponse response,
                                     @ModelAttribute("client") Client client,
                                     @RequestParam("idType") String idType,
                                     @RequestParam("idNumber") String idNumber,
                                     @RequestParam("idExpDate") String idExpDate,
                                     @RequestParam("yearsLiving") String yearsLiving,
                                     @RequestParam("monthsLiving") String monthsLiving,
                                     @RequestParam("ocupation") String ocupation,
                                     @RequestParam("bussinesLine") String bussinesLine) throws IOException{
        Client tempClient = clientService.findById((int) client.getId());
        tempClient.setScotiabank(true);
        clientService.save(tempClient);

        byte[] pdfContent = createPDFService.createPDFScotiabank(tempClient,
                idType,
                idNumber,
                idExpDate,
                yearsLiving,
                monthsLiving,
                ocupation,
                bussinesLine);

        // Definir el tipo de contenido y las cabeceras de respuesta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename="
                .concat("SCOTIABANK ")
                .concat(tempClient.getFirstName())
                .concat(" ")
                .concat(tempClient.getLastName())
                .concat(".pdf"));

        // Obtener el flujo de salida de la respuesta
        OutputStream out = response.getOutputStream();

        // Escribir el contenido del PDF en el flujo de salida
        out.write(pdfContent);

        // Cerrar el flujo de salida
        out.close();
    }

}
