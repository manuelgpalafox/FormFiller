package com.magp.creditformfiller.service;

import com.magp.creditformfiller.entity.Client;

import java.io.IOException;

public interface CreatePDFService {

    //Gets from the View the name of which bank form has to be created
    byte[] createPDFBanorte(Client client,
                            String isClient,
                            String accountNumberBanorte,
                            String bussinesAct,
                            String hasCredit,
                            String isSolidary,
                            String solidaryNames,
                            String solidaryLastName,
                            String solidarySecondLastName,
                            String propertyStatus,
                            String rentPrice) throws IOException;

    public byte[] createPDFBbva(Client client,
                                String isClient,
                                String oldSheetNum,
                                String idType,
                                String idNumber,
                                String yearsLiving,
                                String monthsLiving,
                                String companyType,
                                String employmentStatus,
                                String directDepositBank,
                                String countryOfResidence,
                                String isThirdParty,
                                String thirdPartyDetails) throws IOException;

    public byte[] createPDFCibanco(Client client,
                                   String bussinesAct,
                                   String idType,
                                   String idNumber,
                                   String idExpDate,
                                   String yearsLiving,
                                   String monthsLiving,
                                   String employmentStatus,
                                   String foreignIDNum,
                                   String foreignCountryAssigned,
                                   String foreignPasspNum,
                                   String foreignPasspExpDate,
                                   String isResident,
                                   String residenceType,
                                   String foreignYrsLvngCntry,
                                   String migratoryDocument) throws IOException;

    public byte[] createPDFCredinissan(Client client,
                                       String requestType,
                                       String bussinesAct,
                                       String isRebuy,
                                       String nrEmployee,
                                       String idType,
                                       String idNumber,
                                       String yearsLiving,
                                       String monthsLiving,
                                       String contractType,
                                       String companyType,
                                       String bossName,
                                       String bossPosition,
                                       String bossPhoneNumber)throws IOException;

    public byte[] createPDFScotiabank(Client client,
                                      String idType,
                                      String idNumber,
                                      String idExpDate,
                                      String yearsLiving,
                                      String monthsLiving,
                                      String ocupation,
                                      String bussinesLine)throws IOException;
}
