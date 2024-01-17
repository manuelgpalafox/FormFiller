package com.magp.creditformfiller.service;

import com.magp.creditformfiller.entity.Client;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class CreatePDFServiceImpl implements CreatePDFService {

    //Fecha actual para llenar el formulario
    LocalDate currentDate = LocalDate.now();

    //Variable que formatea el dia en el que se esta llenando el formulario
    String day = (currentDate.getDayOfMonth() > 9)
            ? String.valueOf(currentDate.getDayOfMonth())
            : "0".concat(String.valueOf(currentDate.getDayOfMonth()));

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public byte[] createPDFBanorte(Client client,
                                   String isClient,
                                   String accountNumber,
                                   String businessAct,
                                   String hasCredit,
                                   String isSolidary,
                                   String solidaryNames,
                                   String solidaryLastName,
                                   String solidarySecondLastName,
                                   String propertyStatus,
                                   String rentPrice) throws IOException {

        // Ruta del archivo en el directorio de recursos
        String pdfResourcePath = "classpath:/templates/pdfTemplates/Banorte.pdf";

        // Cargar el archivo como un recurso
        Resource resource = resourceLoader.getResource(pdfResourcePath);

        //Variables que calculan el tiempo en su empleo actual
        Calendar startedWorking = Calendar.getInstance(Locale.UK);
        startedWorking.setTime(client.getDateStartedWorking());
        int yearsWorking = currentDate.getYear() - startedWorking.get(Calendar.YEAR);
        int monthsWorking = (currentDate.getMonthValue() - startedWorking.get(Calendar.MONTH)) - 1;

        //Variable que da formato a la direccion
        String streetAndHouseNumber = (!client.getIntNumber().isEmpty())
                ? client.getStreetName().concat(" ").concat(client.getExtNumber()).concat(" int. ").concat(client.getIntNumber())
                : client.getStreetName().concat(" ").concat(client.getExtNumber());

        //Variable que da formato al numero de telefono de la compañia
        String companyPhoneNumber = client.getCompanyPhoneNumber().concat(" ").concat(client.getCompanyPhoneNumberExt()).trim();

        //Variable que da formato a la direccion de la compañia
        String companyStreetAndNumber = (!client.getCompanyIntNumber().isEmpty())
                ? client.getCompanyStreetName().concat(" ").concat(client.getCompanyExtNumber()).concat(" int. ").concat(client.getCompanyIntNumber()).trim()
                : client.getCompanyStreetName().concat(" ").concat(client.getCompanyExtNumber()).trim();

        //Mapa de los parametros
        Map<String, String> paramMap = new HashMap<>();

        paramMap.put("isClient", isClient);
        paramMap.put("txtAccountNumber", accountNumber);
        paramMap.put("businessAct", businessAct);
        paramMap.put("hasCredit", hasCredit);
        paramMap.put("isSolidary", isSolidary);
        paramMap.put("txtSolidaryNames", solidaryNames);
        paramMap.put("txtSolidaryLastName", solidaryLastName);
        paramMap.put("txtSolidarySecondLastName", solidarySecondLastName);
        paramMap.put("txtRentPrice", rentPrice);
        paramMap.put("txtDay", day);
        paramMap.put("txtMonth", String.valueOf(currentDate.getMonthValue()));
        paramMap.put("txtYear", String.valueOf(currentDate.getYear()));
        paramMap.put("txtNames", client.getFirstName().concat(" ").concat(client.getMiddleName()).trim());
        paramMap.put("txtStreetAndHouseNumber", streetAndHouseNumber);
        paramMap.put("txtCompanyPhoneNumber", companyPhoneNumber);
        paramMap.put("txtCompanyStreetAndNumber", companyStreetAndNumber);
        paramMap.put("txtYearsWorked", String.valueOf(yearsWorking));
        paramMap.put("txtMonthsWorked", String.valueOf(monthsWorking));
        paramMap.put("checkboxIsF", String.valueOf(client.getGender()));
        paramMap.put("checkboxIsM", String.valueOf(client.getGender()));
        paramMap.put("checkboxPrivEmployee", client.getJobStatus().toLowerCase());
        paramMap.put("checkboxFreelancer", client.getJobStatus().toLowerCase());
        paramMap.put("checkboxOwner", client.getJobStatus().toLowerCase());
        paramMap.put("checkboxOtherCompany", client.getJobStatus().toLowerCase());
        paramMap.put("checkboxPartner", client.getJobStatus().toLowerCase());

        //Load template
        InputStream templateFile = resource.getInputStream();
        PDDocument pdfDocument = PDDocument.load(templateFile);
        PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

        //Mapea los checkbox
        setCheckboxValues(acroForm, paramMap);

        //Mapea los valores de texto
        setTextFieldsValues(acroForm, paramMap, client);

        // Save the PDF with the changes to a ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pdfDocument.save(byteArrayOutputStream);
        pdfDocument.close();

        // Return the byte array containing the modified PDF
        return byteArrayOutputStream.toByteArray();

    }

    @Override
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
                                String thirdPartyDetails) throws IOException {


        // Ruta del archivo en el directorio de recursos
        String pdfResourcePath = "classpath:/templates/pdfTemplates/Bbva.pdf";

        // Cargar el archivo como un recurso
        Resource resource = resourceLoader.getResource(pdfResourcePath);

        //Variables que calculan el tiempo en su empleo actual
        Calendar startedWorking = Calendar.getInstance(Locale.UK);
        startedWorking.setTime(client.getDateStartedWorking());
        int yearsWorking = currentDate.getYear() - startedWorking.get(Calendar.YEAR);
        int monthsWorking = (currentDate.getMonthValue() - startedWorking.get(Calendar.MONTH)) - 1;

        //Obtener la fecha de nacimiento del cliente
        Date dateOfBirth = client.getDateOfBirth(); // Tu objeto Date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfBirth);
        String dayOfBirth = (calendar.get(Calendar.DAY_OF_MONTH) > 9)
                ? String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))
                : "0".concat(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        String monthOfBirth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String yearOfBirth = String.valueOf(calendar.get(Calendar.YEAR));

        //Variable que da formato a la direccion del hogar
        String streetAndHouseNumber = (!client.getIntNumber().isEmpty())
                ? client.getStreetName().concat(" ").concat(client.getExtNumber()).concat(" int. ").concat(client.getIntNumber())
                : client.getStreetName().concat(" ").concat(client.getExtNumber());

        //Variable para asignar el valor adecuado para la nacionalidad
        String nationality = (client.getNationality().equals("Mexicana"))
                ? client.getNationality()
                : "Foreign";

        //Variable que da formato a la direccion de la compañia
        String companyStreetAndNumber = (!client.getCompanyIntNumber().isEmpty())
                ? client.getCompanyStreetName().concat(" ").concat(client.getCompanyExtNumber()).concat(" int. ").concat(client.getCompanyIntNumber()).trim()
                : client.getCompanyStreetName().concat(" ").concat(client.getCompanyExtNumber()).trim();

        //Variable que da formato al nombre de la referencia personal
        String persRefFullName = client.getPersRefFirstName()
                .concat(" ")
                .concat(client.getPersRefMiddleName()).trim()
                .concat(" ")
                .concat(client.getPersRefLastName())
                .concat(" ")
                .concat(client.getPersRefSecLastName()).trim();

        //Variable que da formato al nombre de la referencia familiar
        String famRefFullName = client.getFamRefFirstName()
                .concat(" ")
                .concat(client.getFamRefMiddleName()).trim()
                .concat(" ")
                .concat(client.getFamRefLastName())
                .concat(" ")
                .concat(client.getFamRefSecLastName()).trim();

        //Load template
        InputStream templateFile = resource.getInputStream();
        PDDocument pdfDocument = PDDocument.load(templateFile);
        PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

        //Mapa con valores de parametros matcheado a los nombres de los campos
        Map<String, String> paramMap = new HashMap<>();

        paramMap.put("txtOldSheet", oldSheetNum);
        paramMap.put("radiogroupNewForm", isClient);
        paramMap.put("txtDay", day);
        paramMap.put("txtMonth", String.valueOf(currentDate.getMonthValue()));
        paramMap.put("txtYear", String.valueOf(currentDate.getYear()));
        paramMap.put("txtDayOfBirth", dayOfBirth);
        paramMap.put("txtMonthOfBirth", monthOfBirth);
        paramMap.put("txtYearOfBirth", yearOfBirth);
        paramMap.put("txtPhoneNumberCarrier", "Telcel");
        paramMap.put("txtStreetAndHouseNumber", streetAndHouseNumber);
        paramMap.put("radiogroupIdentification", idType);
        paramMap.put("radiogroupGender", client.getGender());
        paramMap.put("radiogroupNationality", nationality);
        paramMap.put("radiogroupHousingStatus", "radiobuttonOwns");
        paramMap.put("radiogroupMaritalStatus", "radiobuttonSingle");
        paramMap.put("txtYearsLivingThere", yearsLiving);
        paramMap.put("txtMonthsLivingThere", monthsLiving);
        paramMap.put("groupbuttonCompanyType", companyType);
        paramMap.put("groupbuttonEmploymentStatus", employmentStatus);
        paramMap.put("txtYearsWorked", String.valueOf(yearsWorking));
        paramMap.put("txtMonthsWorked", String.valueOf(monthsWorking));
        paramMap.put("txtCompanyStreetAndNumber", companyStreetAndNumber);
        paramMap.put("txtPersonalReferenceFullName", persRefFullName);
        paramMap.put("txtFamilyReferenceFullName", famRefFullName);
        paramMap.put("txtDirectDepositBank", directDepositBank);
        paramMap.put("txtCountryOfResidence", countryOfResidence);
        paramMap.put("radiogroupIsThirdParty", isThirdParty);
        paramMap.put("txtThirdPartyDetails", thirdPartyDetails);

        //Agregar cada caracter del id a cada campo INE
        for (int i = 1; i <= idNumber.length(); i++) {
            char character = idNumber.charAt(i - 1);
            paramMap.put("idNumber".concat(String.valueOf(i)), String.valueOf(character));
        }

        //Mapea los valores de texto
        setTextFieldsValues(acroForm, paramMap, client);

        //Mapea los radio buttons
        setRadioButtons(acroForm, paramMap);

        // Save the PDF with the changes to a ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pdfDocument.save(byteArrayOutputStream);
        pdfDocument.close();

        // Return the byte array containing the modified PDF
        return byteArrayOutputStream.toByteArray();
    }

    @Override
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
                                   String migratoryDocument,
                                   String foreignYrsLvngCntry) throws IOException {

        // Ruta del archivo en el directorio de recursos
        String pdfResourcePath = "classpath:/templates/pdfTemplates/Cibanco.pdf";

        // Cargar el archivo como un recurso
        Resource resource = resourceLoader.getResource(pdfResourcePath);

        //Variable que calcula la edad del cliente
        Calendar clientCalc = Calendar.getInstance(Locale.UK);
        clientCalc.setTime(client.getDateOfBirth());
        int clientAge = currentDate.getYear() - clientCalc.get(Calendar.YEAR);

        // Crea un objeto SimpleDateFormat con el formato deseado para la fecha de nacimiento
        SimpleDateFormat formatterUK = new SimpleDateFormat("dd/MM/yyyy");

        // Formatea la fecha de nacimiento del cliente en el formato del Reino Unido
        String formattedDOB = formatterUK.format(client.getDateOfBirth());

        // Crea un objeto SimpleDateFormat con el formato de la entrada del parametro de la fecha de vencimiento del ID
        SimpleDateFormat inputDateFormatter = new SimpleDateFormat("yyyy-M-d");

        //Convierte el parametro de fecha de expiracion del ID a un objeto Date
        Date IdExpDate = null;
        String formattedIdExpDate ="";
        String formattedIdExpDate2 ="";

        if(!idExpDate.isEmpty()){
            try {
                IdExpDate = inputDateFormatter.parse(idExpDate);
                // Formatea la fecha de vencimiento del id del cliente en el formato del Reino Unido
                formattedIdExpDate = formatterUK.format(IdExpDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        if(!foreignPasspExpDate.isEmpty()){
            try {
                IdExpDate = inputDateFormatter.parse(foreignPasspExpDate);
                // Formatea la fecha de vencimiento del id del cliente en el formato del Reino Unido
                formattedIdExpDate2 = formatterUK.format(IdExpDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }


        //Variables que calculan el tiempo en su empleo actual
        Calendar startedWorking = Calendar.getInstance(Locale.UK);
        startedWorking.setTime(client.getDateStartedWorking());
        int yearsWorking = currentDate.getYear() - startedWorking.get(Calendar.YEAR);
        int monthsWorking = (currentDate.getMonthValue() - startedWorking.get(Calendar.MONTH)) - 1;

        //Variable que da formato al tiempo trabajado
        String timeWorked = (monthsWorking > 0)
                ?String.valueOf(yearsWorking).concat(" años ").concat(String.valueOf(monthsWorking)).concat(" meses")
                :String.valueOf(yearsWorking).concat(" años");

        //Variable que da formato al tiempo que lleva vivido en la direccion proporcionada
        String timeLived = (Integer.parseInt(monthsLiving) > 0)
                ?yearsLiving.concat(" años ").concat(monthsLiving).concat(" meses")
                :yearsLiving.concat(" años");

        //Variable que da formato al nombre de la referencia personal
        String persRefFullName = client.getPersRefFirstName()
                .concat(" ")
                .concat(client.getPersRefMiddleName()).trim()
                .concat(" ")
                .concat(client.getPersRefLastName())
                .concat(" ")
                .concat(client.getPersRefSecLastName()).trim();

        //Variable que da formato al nombre de la referencia familiar
        String famRefFullName = client.getFamRefFirstName()
                .concat(" ")
                .concat(client.getFamRefMiddleName()).trim()
                .concat(" ")
                .concat(client.getFamRefLastName())
                .concat(" ")
                .concat(client.getFamRefSecLastName()).trim();

        //Load template
        InputStream templateFile = resource.getInputStream();
        PDDocument pdfDocument = PDDocument.load(templateFile);
        PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

        //Mapa con valores de parametros matcheado a los nombres de los campos
        Map<String, String> paramMap = new HashMap<>();

        paramMap.put("txtNames", client.getFirstName().concat(" ").concat(client.getMiddleName()).trim());
        paramMap.put("checkboxIsF", String.valueOf(client.getGender()));
        paramMap.put("checkboxIsM", String.valueOf(client.getGender()));
        paramMap.put("txtAge", String.valueOf(clientAge));
        paramMap.put("checkboxNoAplica", "noaplica");
        paramMap.put("txtDOB", formattedDOB);
        paramMap.put("checkboxPhysicalPers", bussinesAct.toLowerCase());
        paramMap.put("checkboxBusinessActPers", bussinesAct.toLowerCase());
        paramMap.put("txtIdType", "  ".concat(idType));
        paramMap.put("txtIdNumber", idNumber);
        paramMap.put("txtIdExpDate", formattedIdExpDate);
        paramMap.put("checkboxIsCIClientNo", "checkboxIsCIClientNo".toLowerCase());
        paramMap.put("txtYearsWorking", timeWorked);
        paramMap.put("txtYearsLivingThere", timeLived);
        paramMap.put("checkboxEventualWorkType", employmentStatus.toLowerCase());
        paramMap.put("checkboxStaffWorkType", employmentStatus.toLowerCase());
        paramMap.put("checkboxContractorWorkType", employmentStatus.toLowerCase());
        paramMap.put("txtPersonalReferenceFullName", persRefFullName);
        paramMap.put("txtFamilyReferenceFullName", famRefFullName);
        paramMap.put("txtTaxIDNumber", foreignIDNum);
        paramMap.put("txtAssignmentCountry", foreignCountryAssigned);
        paramMap.put("txtPassportNumber", foreignPasspNum);
        paramMap.put("txtPassportExpDate", formattedIdExpDate2);
        paramMap.put("checkboxIsResidentYes", isResident.toLowerCase());
        paramMap.put("checkboxIsResidentNo", isResident.toLowerCase());
        paramMap.put("checkboxPermanentResident", residenceType.toLowerCase());
        paramMap.put("checkboxTemporalResident", residenceType.toLowerCase());
        paramMap.put("txtYearsInTheCountry", foreignYrsLvngCntry);
        paramMap.put("checkboxNoImmigrantForm", migratoryDocument.toLowerCase());
        paramMap.put("checkboxImmigrantForm", migratoryDocument.toLowerCase());
        paramMap.put("checkboxVisitorAndResidentCard", migratoryDocument.toLowerCase());

        //CODE-----------------------

        //Mapea los checkbox
        setCheckboxValues(acroForm, paramMap);

        //Mapea los valores de texto
        setTextFieldsValues(acroForm, paramMap, client);

        //CODE------------------------

        // Save the PDF with the changes to a ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pdfDocument.save(byteArrayOutputStream);
        pdfDocument.close();

        // Return the byte array containing the modified PDF
        return byteArrayOutputStream.toByteArray();
    }

    @Override
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
                                       String bossPhoneNumber) throws IOException {

        // Ruta del archivo en el directorio de recursos
        String pdfResourcePath = "classpath:/templates/pdfTemplates/Credinissan.pdf";

        // Cargar el archivo como un recurso
        Resource resource = resourceLoader.getResource(pdfResourcePath);

        //Obtener la fecha de nacimiento del cliente
        Date dateOfBirth = client.getDateOfBirth(); // Tu objeto Date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfBirth);
        String dayOfBirth = (calendar.get(Calendar.DAY_OF_MONTH) > 9)
                ? String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))
                : "0".concat(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        String monthOfBirth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String yearOfBirth = String.valueOf(calendar.get(Calendar.YEAR));

        //Obtener la fecha de nacimiento del cliente
        Date dateOfWork = client.getDateStartedWorking(); // Tu objeto Date
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(dateOfWork);
        String dayStartedWorking = (calendar2.get(Calendar.DAY_OF_MONTH) > 9)
                ? String.valueOf(calendar2.get(Calendar.DAY_OF_MONTH))
                : "0".concat(String.valueOf(calendar2.get(Calendar.DAY_OF_MONTH)));
        String monthStartedWorking = String.valueOf(calendar2.get(Calendar.MONTH) + 1);
        String yearStartedWorking = String.valueOf(calendar2.get(Calendar.YEAR));

        //Obtener el nombre completo del cliente
        //Variable que da formato al nombre de la referencia familiar
        String clientFullName = client.getFirstName()
                .concat(" ")
                .concat(client.getMiddleName()).trim()
                .concat(" ")
                .concat(client.getLastName())
                .concat(" ")
                .concat(client.getSecondLastName()).trim();

        //Load template
        InputStream templateFile = resource.getInputStream();
        PDDocument pdfDocument = PDDocument.load(templateFile);
        PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

        //Mapa con valores de parametros matcheado a los nombres de los campos
        Map<String, String> paramMap = new HashMap<>();

        paramMap.put("checkboxSimpleCredit", requestType.toLowerCase());
        paramMap.put("checkboxLeasing", requestType.toLowerCase());
        paramMap.put("checkboxCrediTaxi", requestType.toLowerCase());
        paramMap.put("checkboxSubete", requestType.toLowerCase());
        paramMap.put("checkboxPersonaFisica1", bussinesAct.toLowerCase());
        paramMap.put("checkboxPersonaFisicaAE", bussinesAct.toLowerCase());
        paramMap.put("checkboxReBuyYes", isRebuy.toLowerCase());
        paramMap.put("checkboxReBuyNo", isRebuy.toLowerCase());
        paramMap.put("checkboxAlianzaEmployeeYes", nrEmployee.toLowerCase());
        paramMap.put("checkboxAlianzaEmployeeNo", nrEmployee.toLowerCase());
        paramMap.put("txtDayOfBirth", dayOfBirth);
        paramMap.put("txtMonthOfBirth", monthOfBirth);
        paramMap.put("txtYearOfBirth", yearOfBirth);
        paramMap.put("checkboxIsF", String.valueOf(client.getGender()));
        paramMap.put("checkboxIsM", String.valueOf(client.getGender()));
        if(Objects.equals(client.getNationality(), "Mexicana")){
            paramMap.put("checkboxMexicana", client.getNationality().toLowerCase());
        }else{
            paramMap.put("txtForeignDetails", client.getNationality());
        }
        paramMap.put("checkboxINE", idType.toLowerCase());
        paramMap.put("checkboxPassport", idType.toLowerCase());
        paramMap.put("txtIdNumber", idNumber);
        paramMap.put("txtYearsLivingThere", yearsLiving);
        paramMap.put("txtMonthsLivingThere", monthsLiving);
        paramMap.put("checkboxPrivEmployee", client.getJobStatus().toLowerCase());
        paramMap.put("checkboxPubEmployee", client.getJobStatus().toLowerCase());
        paramMap.put("checkboxFreelancer", client.getJobStatus().toLowerCase());
        if(Objects.equals(client.getJobStatus(), "checkboxRetired") || Objects.equals(client.getJobStatus(), "checkboxPensioner")){
            paramMap.put("checkboxRetired", "retired");
        }
        paramMap.put("checkboxHomemaker", client.getJobStatus().toLowerCase());
        paramMap.put("checkboxStudent", client.getJobStatus().toLowerCase());
        paramMap.put("txtDayWorkStart", dayStartedWorking);
        paramMap.put("txtMonthWorkStart", monthStartedWorking);
        paramMap.put("txtYearWorkStart", yearStartedWorking);
        //TODO en clientForm hacer el Pais un select
        if(Objects.equals(client.getCompanyCountry(), "México")){
            paramMap.put("checkboxCompanyIsMexican", "mexican");
        }else {
            paramMap.put("checkboxCompanyIsForeign", "foreign");
        }
        paramMap.put("checkboxPermanentJob", contractType.toLowerCase());
        paramMap.put("checkboxTemporalJob", contractType.toLowerCase());
        paramMap.put("checkboxPrivateCompany", companyType.toLowerCase());
        paramMap.put("checkboxPublicCompany", companyType.toLowerCase());
        paramMap.put("txtCompanyBossName", bossName);
        paramMap.put("txtCompanyBossPosition", bossPosition);
        paramMap.put("txtCompanyBossPhoneNumber", bossPhoneNumber);

        paramMap.put("txtFullNameSignature", clientFullName);

        paramMap.put("checkboxInterviewNo", "checkboxInterviewNo".toLowerCase());
        paramMap.put("checkboxRelationToPEPNo", "checkboxRelationToPEPNo".toLowerCase());
        paramMap.put("checkboxValidDataYes", "checkboxValidDataYes".toLowerCase());
        paramMap.put("checkboxAdvancePaymentsNo", "checkboxAdvancePaymentsNo".toLowerCase());
        paramMap.put("checkboxSettleNo", "checkboxSettleNo".toLowerCase());

        //CODE-----------------------

        //Mapea los checkbox
        setCheckboxValues(acroForm, paramMap);

        //Mapea los valores de texto
        setTextFieldsValues(acroForm, paramMap, client);

        //CODE------------------------

        // Save the PDF with the changes to a ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pdfDocument.save(byteArrayOutputStream);
        pdfDocument.close();

        // Return the byte array containing the modified PDF
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public byte[] createPDFScotiabank(Client client,
                                      String idType,
                                      String idNumber,
                                      String idExpDate,
                                      String yearsLiving,
                                      String monthsLiving,
                                      String ocupation,
                                      String bussinesLine) throws IOException {
        //Variable que calcula la edad del cliente
        Calendar clientCalc = Calendar.getInstance(Locale.UK);
        clientCalc.setTime(client.getDateOfBirth());
        int clientAge = currentDate.getYear() - clientCalc.get(Calendar.YEAR);

        // Crea un objeto SimpleDateFormat con el formato deseado para la fecha de nacimiento
        SimpleDateFormat formatterUK = new SimpleDateFormat("dd/MM/yyyy");

        // Formatea la fecha de nacimiento del cliente en el formato del Reino Unido
        String formattedDOB = formatterUK.format(client.getDateOfBirth());

        // Crea un objeto SimpleDateFormat con el formato de la entrada del parametro de la fecha de vencimiento del ID
        SimpleDateFormat inputDateFormatter = new SimpleDateFormat("yyyy-M-d");

        //Convierte el parametro de fecha de expiracion del ID a un objeto Date
        Date IdExpDate = null;
        String formattedIdExpDate ="";

        if(!idExpDate.isEmpty()){
            try {
                IdExpDate = inputDateFormatter.parse(idExpDate);
                // Formatea la fecha de vencimiento del id del cliente en el formato del Reino Unido
                formattedIdExpDate = formatterUK.format(IdExpDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        //Variable que da formato a la direccion
        String streetAndHouseNumber = (!client.getIntNumber().isEmpty())
                ? client.getStreetName().concat(" ").concat(client.getExtNumber()).concat(" int. ").concat(client.getIntNumber())
                : client.getStreetName().concat(" ").concat(client.getExtNumber());

        //Variable que da formato a la direccion de la compañia
        String companyStreetAndNumber = (!client.getCompanyIntNumber().isEmpty())
                ? client.getCompanyStreetName().concat(" ").concat(client.getCompanyExtNumber()).concat(" int. ").concat(client.getCompanyIntNumber()).trim()
                : client.getCompanyStreetName().concat(" ").concat(client.getCompanyExtNumber()).trim();

        //Variable que da formato al nombre de la referencia personal
        String persRefFullName = client.getPersRefFirstName()
                .concat(" ")
                .concat(client.getPersRefMiddleName()).trim()
                .concat(" ")
                .concat(client.getPersRefLastName())
                .concat(" ")
                .concat(client.getPersRefSecLastName()).trim();

        //Variable que da formato al nombre de la referencia familiar
        String famRefFullName = client.getFamRefFirstName()
                .concat(" ")
                .concat(client.getFamRefMiddleName()).trim()
                .concat(" ")
                .concat(client.getFamRefLastName())
                .concat(" ")
                .concat(client.getFamRefSecLastName()).trim();

        //Get Client Job Status
        String clientJobsStatus = client.getJobStatus().toLowerCase();

        //Variables que calculan el tiempo en su empleo actual
        Calendar startedWorking = Calendar.getInstance(Locale.UK);
        startedWorking.setTime(client.getDateStartedWorking());
        int yearsWorking = currentDate.getYear() - startedWorking.get(Calendar.YEAR);
        int monthsWorking = (currentDate.getMonthValue() - startedWorking.get(Calendar.MONTH)) - 1;

        // Ruta del archivo en el directorio de recursos
        String pdfResourcePath = "classpath:/templates/pdfTemplates/Scotiabank.pdf";

        // Cargar el archivo como un recurso
        Resource resource = resourceLoader.getResource(pdfResourcePath);

        //Load template
//        File templateFile = resource.getFile();
        InputStream templateFile = resource.getInputStream();

        PDDocument pdfDocument = PDDocument.load(templateFile);
        PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

        //Mapa con valores de parametros matcheado a los nombres de los campos
        Map<String, String> paramMap = new HashMap<>();

        paramMap.put("txtNames", client.getFirstName().concat(" ").concat(client.getMiddleName()).trim());
        //Agregar cada caracter del id a cada campo RFC
        for (int i = 1; i <= client.getRfc().length(); i++) {
            char character = client.getRfc().charAt(i - 1);
            paramMap.put("txtRfc".concat(String.valueOf(i)), String.valueOf(character));
        }
        //Agregar cada caracter del id a cada campo CURP
        for (int i = 1; i <= client.getCurp().length(); i++) {
            char character = client.getCurp().charAt(i - 1);
            paramMap.put("txtCurp".concat(String.valueOf(i)), String.valueOf(character));
        }
        if(Objects.equals(client.getNationality(), "Mexicana")){
            paramMap.put("checkboxMexicana", client.getNationality().toLowerCase());
        }else{
            paramMap.put("checkboxForeign", "checkboxforeign");
            paramMap.put("txtForeignDetails", client.getNationality());
        }
        paramMap.put("txtAge", String.valueOf(clientAge));
        paramMap.put("checkboxIsF", String.valueOf(client.getGender()));
        paramMap.put("checkboxIsM", String.valueOf(client.getGender()));
        paramMap.put("txtIdType", idType);
        paramMap.put("txtIdNumber", idNumber);
        paramMap.put("txtIdExpDate", formattedIdExpDate);
        paramMap.put("txtYearsLivingThere", yearsLiving);
        paramMap.put("txtMonthsLivingThere", monthsLiving);
        paramMap.put("checkboxPubEmployee", clientJobsStatus);
        paramMap.put("checkboxFreelancer", clientJobsStatus);
        paramMap.put("checkboxOwner", clientJobsStatus);
        paramMap.put("checkboxPrivEmployee", clientJobsStatus);
        paramMap.put(ocupation, ocupation.toLowerCase());
        paramMap.put("txtYearsWorked", String.valueOf(yearsWorking));
        paramMap.put("txtMonthsWorked", String.valueOf(monthsWorking));
        paramMap.put(bussinesLine, bussinesLine.toLowerCase());
        paramMap.put("txtCompanyStreetAndNumber", companyStreetAndNumber);
        paramMap.put("txtPersonalReferenceFullName", persRefFullName);
        paramMap.put("txtFamilyReferenceFullName", famRefFullName);

        paramMap.put("txtStreetAndHouseNumber", streetAndHouseNumber);

        //CODE------------------------

        //CODE------------------------

        //Mapea los checkbox
        setCheckboxValues(acroForm, paramMap);

        //Mapea los valores de texto
        setTextFieldsValues(acroForm, paramMap, client);

        //CODE------------------------

        //CODE------------------------

        // Save the PDF with the changes to a ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pdfDocument.save(byteArrayOutputStream);
        pdfDocument.close();

        // Return the byte array containing the modified PDF
        return byteArrayOutputStream.toByteArray();
    }

    public void setCheckboxValues(PDAcroForm acroForm, Map<String, String> paramMap) throws IOException {
        // Iterate through the fields and set values dynamically
        for (PDField field : acroForm.getFields()) {
            String fieldType = field.getFieldType();

            if (fieldType.equals("Btn")) {
                markCheckboxes(field, paramMap);
                continue;
            }

        }
    }

    private void markCheckboxes(PDField field,
                                Map<String, String> paramMap) throws IOException {

        checkYesNoCheckboxes(field, paramMap);
        checkOtherCheckboxes(field, paramMap);

    }

    private void checkYesNoCheckboxes(PDField field,
                                      Map<String, String> paramMap) throws IOException {

        String fieldName = field.getFullyQualifiedName();

        PDCheckBox checkbox = (PDCheckBox) field;

        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            if (fieldName.toLowerCase().contains(entry.getKey().toLowerCase()) && fieldName.contains("Yes") && entry.getValue().equals("yes")) {
                checkbox.check();
            } else if (fieldName.toLowerCase().contains(entry.getKey().toLowerCase()) && fieldName.contains("No") && entry.getValue().equals("no")) {
                checkbox.check();
            }
        }

    }

    private void checkOtherCheckboxes(PDField field,
                                      Map<String, String> paramMap) throws IOException {

        String fieldName = field.getFullyQualifiedName();

        PDCheckBox checkbox = (PDCheckBox) field;

        if (fieldName.equals("checkboxIsSingle")) {
            checkbox.check();
        }

        if (fieldName.equals("checkboxIsOwned")) {
            checkbox.check();
        }

        if (paramMap.containsKey(fieldName) && fieldName.toLowerCase().contains(paramMap.get(fieldName))) {
            checkbox.check();
        }

    }

    private void setTextFieldsValues(PDAcroForm acroForm, Map<String, String> paramMap, Client client) throws IOException {
        for (PDField field : acroForm.getFields()) {
            String fieldType = field.getFieldType();

            if (fieldType.equals("Tx")) {
                String fieldName = field.getFullyQualifiedName();

                if (paramMap.containsKey(fieldName)) {
                    field.setValue(paramMap.get(fieldName));
                    continue;
                }
                //Formats fieldName to pass as param
                fieldName = fieldName.replace("txt", "");

                // Use reflection to get the corresponding value from the client object
                String value = getFieldValue(client, fieldName);

                if (!value.equals("valueNotFound")) {
                    field.setValue(value);
                }
            }

        }
    }

    private String getFieldValue(Client client, String fieldName) {
        try {
            // Use Java reflection to find and invoke the getter method based on the field name
            String getterMethodName = "get" + fieldName;
            java.lang.reflect.Method getterMethod = client.getClass().getMethod(getterMethodName);
            Object value = getterMethod.invoke(client);

            if (value != null) {
                return value.toString();
            }
        } catch (Exception e) {
            // Handle any exceptions or field not found gracefully
        }

        return "valueNotFound"; // Return null if the field doesn't exist or couldn't be retrieved
    }

    private void setRadioButtons(PDAcroForm acroForm, Map<String, String> paramMap) throws IOException {
        for (PDField field : acroForm.getFields()) {
            String fieldType = field.getFieldType();
            String fieldName = field.getFullyQualifiedName();

            if (fieldType.equals("Btn") && paramMap.containsKey(fieldName)) {
                field.setValue(paramMap.get(fieldName));
            }

        }

    }

}
