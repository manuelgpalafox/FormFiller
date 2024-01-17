package com.magp.creditformfiller.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "client_list")
public class Client {

    //Identification data

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "second_last_name")
    private String secondLastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "marital_status")
    private String maritalStatus;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "city_of_birth")
    private String cityOfBirth;
    @Column(name = "country_of_birth")
    private String countryOfBirth;
    @Column(name = "state_of_birth")
    private String stateOfBirth;
    @Column(name = "curp")
    private String curp;
    @Column(name = "social_security_number")
    private String socialSecurityNumber;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    //Address data

    @Column(name = "street_name")
    private String streetName;
    @Column(name = "exterior_number")
    private String extNumber;
    @Column(name = "interior_number")
    private String intNumber;
    @Column(name = "colony")
    private String colony;
    @Column(name = "municipality")
    private String municipality;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "country")
    private String country;

    //Job data

    @Column(name = "company_name")
    private String companyName;
    @Column(name = "company_phone_number")
    private String companyPhoneNumber;
    @Column(name = "company_phone_number_ext")
    private String companyPhoneNumberExt;
    @Column(name = "company_street_name")
    private String companyStreetName;
    @Column(name = "company_int_number")
    private String companyIntNumber;
    @Column(name = "company_ext_number")
    private String companyExtNumber;
    @Column(name = "company_colony")
    private String companyColony;
    @Column(name = "company_city")
    private String companyCity;
    @Column(name = "company_municipality")
    private String companyMunicipality;
    @Column(name = "company_state")
    private String companyState;
    @Column(name = "company_country")
    private String companyCountry;
    @Column(name = "company_zip_code")
    private String companyZipCode;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_started_working")
    private Date dateStartedWorking;
    @Column(name = "company_activity")
    private String companyActivity;
    @Column(name = "job_status")
    private String jobStatus;
    @Column(name = "position_name")
    private String positionName;

    //Informacion de referencias
    @Column(name = "personal_ref_first_name")
    private String persRefFirstName;
    @Column(name = "personal_ref_middle_name")
    private String persRefMiddleName;
    @Column(name = "personal_ref_last_name")
    private String persRefLastName;
    @Column(name = "personal_ref_second_last_name")
    private String persRefSecLastName;
    @Column(name = "personal_ref_phone_number")
    private String persRefPhoneNumber;
    @Column(name = "family_ref_first_name")
    private String famRefFirstName;
    @Column(name = "family_ref_middle_name")
    private String famRefMiddleName;
    @Column(name = "family_ref_last_name")
    private String famRefLastName;
    @Column(name = "family_ref_second_last_name")
    private String famRefSecLastName;
    @Column(name = "family_ref_phone_number")
    private String famRefPhoneNumber;

    @Column(name = "added", updatable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date added;

    @Column(name = "banorte")
    private boolean banorte;

    @Column(name = "bbva")
    private boolean bbva;

    @Column(name = "cibanco")
    private boolean cibanco;

    @Column(name = "credinissan")
    private boolean credinissan;

    @Column(name = "scotiabank")
    private boolean scotiabank;

    @Column(name = "username")
    private String user;

    public Client() {
    }

    public Client(long id, String firstName, String middleName, String lastName, String secondLastName, String gender, String maritalStatus, String nationality, String rfc, String cityOfBirth, String countryOfBirth, String stateOfBirth, String curp, String socialSecurityNumber, Date dateOfBirth, String phoneNumber, String email, String streetName, String extNumber, String intNumber, String colony, String municipality, String state, String city, String zipCode, String country, String companyName, String companyPhoneNumber, String companyPhoneNumberExt, String companyStreetName, String companyIntNumber, String companyExtNumber, String companyColony, String companyCity, String companyMunicipality, String companyState, String companyCountry, String companyZipCode, Date dateStartedWorking, String companyActivity, String jobStatus, String positionName, String persRefFirstName, String persRefMiddleName, String persRefLastName, String persRefSecLastName, String persRefPhoneNumber, String famRefFirstName, String famRefMiddleName, String famRefLastName, String famRefSecLastName, String famRefPhoneNumber, Date added, boolean banorte, boolean bbva, boolean cibanco, boolean credinissan, boolean scotiabank, String user) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.nationality = nationality;
        this.rfc = rfc;
        this.cityOfBirth = cityOfBirth;
        this.countryOfBirth = countryOfBirth;
        this.stateOfBirth = stateOfBirth;
        this.curp = curp;
        this.socialSecurityNumber = socialSecurityNumber;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.streetName = streetName;
        this.extNumber = extNumber;
        this.intNumber = intNumber;
        this.colony = colony;
        this.municipality = municipality;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.companyName = companyName;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyPhoneNumberExt = companyPhoneNumberExt;
        this.companyStreetName = companyStreetName;
        this.companyIntNumber = companyIntNumber;
        this.companyExtNumber = companyExtNumber;
        this.companyColony = companyColony;
        this.companyCity = companyCity;
        this.companyMunicipality = companyMunicipality;
        this.companyState = companyState;
        this.companyCountry = companyCountry;
        this.companyZipCode = companyZipCode;
        this.dateStartedWorking = dateStartedWorking;
        this.companyActivity = companyActivity;
        this.jobStatus = jobStatus;
        this.positionName = positionName;
        this.persRefFirstName = persRefFirstName;
        this.persRefMiddleName = persRefMiddleName;
        this.persRefLastName = persRefLastName;
        this.persRefSecLastName = persRefSecLastName;
        this.persRefPhoneNumber = persRefPhoneNumber;
        this.famRefFirstName = famRefFirstName;
        this.famRefMiddleName = famRefMiddleName;
        this.famRefLastName = famRefLastName;
        this.famRefSecLastName = famRefSecLastName;
        this.famRefPhoneNumber = famRefPhoneNumber;
        this.added = added;
        this.banorte = banorte;
        this.bbva = bbva;
        this.cibanco = cibanco;
        this.credinissan = credinissan;
        this.scotiabank = scotiabank;
        this.user = user;
    }

    public Client(String firstName, String middleName, String lastName, String secondLastName, String gender, String maritalStatus, String nationality, String rfc, String cityOfBirth, String countryOfBirth, String stateOfBirth, String curp, String socialSecurityNumber, Date dateOfBirth, String phoneNumber, String email, String streetName, String extNumber, String intNumber, String colony, String municipality, String state, String city, String zipCode, String country, String companyName, String companyPhoneNumber, String companyPhoneNumberExt, String companyStreetName, String companyIntNumber, String companyExtNumber, String companyColony, String companyCity, String companyMunicipality, String companyState, String companyCountry, String companyZipCode, Date dateStartedWorking, String companyActivity, String jobStatus, String positionName, String persRefFirstName, String persRefMiddleName, String persRefLastName, String persRefSecLastName, String persRefPhoneNumber, String famRefFirstName, String famRefMiddleName, String famRefLastName, String famRefSecLastName, String famRefPhoneNumber, Date added, boolean banorte, boolean bbva, boolean cibanco, boolean credinissan, boolean scotiabank, String user) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.nationality = nationality;
        this.rfc = rfc;
        this.cityOfBirth = cityOfBirth;
        this.countryOfBirth = countryOfBirth;
        this.stateOfBirth = stateOfBirth;
        this.curp = curp;
        this.socialSecurityNumber = socialSecurityNumber;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.streetName = streetName;
        this.extNumber = extNumber;
        this.intNumber = intNumber;
        this.colony = colony;
        this.municipality = municipality;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.companyName = companyName;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyPhoneNumberExt = companyPhoneNumberExt;
        this.companyStreetName = companyStreetName;
        this.companyIntNumber = companyIntNumber;
        this.companyExtNumber = companyExtNumber;
        this.companyColony = companyColony;
        this.companyCity = companyCity;
        this.companyMunicipality = companyMunicipality;
        this.companyState = companyState;
        this.companyCountry = companyCountry;
        this.companyZipCode = companyZipCode;
        this.dateStartedWorking = dateStartedWorking;
        this.companyActivity = companyActivity;
        this.jobStatus = jobStatus;
        this.positionName = positionName;
        this.persRefFirstName = persRefFirstName;
        this.persRefMiddleName = persRefMiddleName;
        this.persRefLastName = persRefLastName;
        this.persRefSecLastName = persRefSecLastName;
        this.persRefPhoneNumber = persRefPhoneNumber;
        this.famRefFirstName = famRefFirstName;
        this.famRefMiddleName = famRefMiddleName;
        this.famRefLastName = famRefLastName;
        this.famRefSecLastName = famRefSecLastName;
        this.famRefPhoneNumber = famRefPhoneNumber;
        this.added = added;
        this.banorte = banorte;
        this.bbva = bbva;
        this.cibanco = cibanco;
        this.credinissan = credinissan;
        this.scotiabank = scotiabank;
        this.user = user;
    }

    //    public Client(long id, String firstName, String middleName, String lastName, String secondLastName, String gender, String maritalStatus, String nationality, String rfc, String cityOfBirth, String countryOfBirth, String stateOfBirth, String curp, String socialSecurityNumber, Date dateOfBirth, String phoneNumber, String email, String streetName, String extNumber, String intNumber, String colony, String municipality, String state, String city, String zipCode, String country, String companyName, String companyPhoneNumber, String companyPhoneNumberExt, String companyStreetName, String companyIntNumber, String companyExtNumber, String companyColony, String companyCity, String companyMunicipality, String companyState, String companyCountry, String companyZipCode, Date dateStartedWorking, String companyActivity, String jobStatus, String positionName, String persRefFirstName, String persRefMiddleName, String persRefLastName, String persRefSecLastName, String persRefPhoneNumber, String famRefFirstName, String famRefMiddleName, String famRefLastName, String famRefSecLastName, String famRefPhoneNumber, boolean banorte, boolean bbva, boolean cibanco, boolean credinissan, boolean scotiabank) {
//        this.id = id;
//        this.firstName = firstName;
//        this.middleName = middleName;
//        this.lastName = lastName;
//        this.secondLastName = secondLastName;
//        this.gender = gender;
//        this.maritalStatus = maritalStatus;
//        this.nationality = nationality;
//        this.rfc = rfc;
//        this.cityOfBirth = cityOfBirth;
//        this.countryOfBirth = countryOfBirth;
//        this.stateOfBirth = stateOfBirth;
//        this.curp = curp;
//        this.socialSecurityNumber = socialSecurityNumber;
//        this.dateOfBirth = dateOfBirth;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.streetName = streetName;
//        this.extNumber = extNumber;
//        this.intNumber = intNumber;
//        this.colony = colony;
//        this.municipality = municipality;
//        this.state = state;
//        this.city = city;
//        this.zipCode = zipCode;
//        this.country = country;
//        this.companyName = companyName;
//        this.companyPhoneNumber = companyPhoneNumber;
//        this.companyPhoneNumberExt = companyPhoneNumberExt;
//        this.companyStreetName = companyStreetName;
//        this.companyIntNumber = companyIntNumber;
//        this.companyExtNumber = companyExtNumber;
//        this.companyColony = companyColony;
//        this.companyCity = companyCity;
//        this.companyMunicipality = companyMunicipality;
//        this.companyState = companyState;
//        this.companyCountry = companyCountry;
//        this.companyZipCode = companyZipCode;
//        this.dateStartedWorking = dateStartedWorking;
//        this.companyActivity = companyActivity;
//        this.jobStatus = jobStatus;
//        this.positionName = positionName;
//        this.persRefFirstName = persRefFirstName;
//        this.persRefMiddleName = persRefMiddleName;
//        this.persRefLastName = persRefLastName;
//        this.persRefSecLastName = persRefSecLastName;
//        this.persRefPhoneNumber = persRefPhoneNumber;
//        this.famRefFirstName = famRefFirstName;
//        this.famRefMiddleName = famRefMiddleName;
//        this.famRefLastName = famRefLastName;
//        this.famRefSecLastName = famRefSecLastName;
//        this.famRefPhoneNumber = famRefPhoneNumber;
//        this.banorte = banorte;
//        this.bbva = bbva;
//        this.cibanco = cibanco;
//        this.credinissan = credinissan;
//        this.scotiabank = scotiabank;
//    }
//
//    public Client(String firstName, String middleName, String lastName, String secondLastName, String gender, String maritalStatus, String nationality, String rfc, String cityOfBirth, String countryOfBirth, String stateOfBirth, String curp, String socialSecurityNumber, Date dateOfBirth, String phoneNumber, String email, String streetName, String extNumber, String intNumber, String colony, String municipality, String state, String city, String zipCode, String country, String companyName, String companyPhoneNumber, String companyPhoneNumberExt, String companyStreetName, String companyIntNumber, String companyExtNumber, String companyColony, String companyCity, String companyMunicipality, String companyState, String companyCountry, String companyZipCode, Date dateStartedWorking, String companyActivity, String jobStatus, String positionName, String persRefFirstName, String persRefMiddleName, String persRefLastName, String persRefSecLastName, String persRefPhoneNumber, String famRefFirstName, String famRefMiddleName, String famRefLastName, String famRefSecLastName, String famRefPhoneNumber, boolean banorte, boolean bbva, boolean cibanco, boolean credinissan, boolean scotiabank) {
//        this.firstName = firstName;
//        this.middleName = middleName;
//        this.lastName = lastName;
//        this.secondLastName = secondLastName;
//        this.gender = gender;
//        this.maritalStatus = maritalStatus;
//        this.nationality = nationality;
//        this.rfc = rfc;
//        this.cityOfBirth = cityOfBirth;
//        this.countryOfBirth = countryOfBirth;
//        this.stateOfBirth = stateOfBirth;
//        this.curp = curp;
//        this.socialSecurityNumber = socialSecurityNumber;
//        this.dateOfBirth = dateOfBirth;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.streetName = streetName;
//        this.extNumber = extNumber;
//        this.intNumber = intNumber;
//        this.colony = colony;
//        this.municipality = municipality;
//        this.state = state;
//        this.city = city;
//        this.zipCode = zipCode;
//        this.country = country;
//        this.companyName = companyName;
//        this.companyPhoneNumber = companyPhoneNumber;
//        this.companyPhoneNumberExt = companyPhoneNumberExt;
//        this.companyStreetName = companyStreetName;
//        this.companyIntNumber = companyIntNumber;
//        this.companyExtNumber = companyExtNumber;
//        this.companyColony = companyColony;
//        this.companyCity = companyCity;
//        this.companyMunicipality = companyMunicipality;
//        this.companyState = companyState;
//        this.companyCountry = companyCountry;
//        this.companyZipCode = companyZipCode;
//        this.dateStartedWorking = dateStartedWorking;
//        this.companyActivity = companyActivity;
//        this.jobStatus = jobStatus;
//        this.positionName = positionName;
//        this.persRefFirstName = persRefFirstName;
//        this.persRefMiddleName = persRefMiddleName;
//        this.persRefLastName = persRefLastName;
//        this.persRefSecLastName = persRefSecLastName;
//        this.persRefPhoneNumber = persRefPhoneNumber;
//        this.famRefFirstName = famRefFirstName;
//        this.famRefMiddleName = famRefMiddleName;
//        this.famRefLastName = famRefLastName;
//        this.famRefSecLastName = famRefSecLastName;
//        this.famRefPhoneNumber = famRefPhoneNumber;
//        this.banorte = banorte;
//        this.bbva = bbva;
//        this.cibanco = cibanco;
//        this.credinissan = credinissan;
//        this.scotiabank = scotiabank;
//    }
//
//    public Client(long id, String firstName, String middleName, String lastName, String secondLastName, String gender, String maritalStatus, String nationality, String rfc, String cityOfBirth, String countryOfBirth, String stateOfBirth, String curp, String socialSecurityNumber, Date dateOfBirth, String phoneNumber, String email, String streetName, String extNumber, String intNumber, String colony, String municipality, String state, String city, String zipCode, String country, String companyName, String companyPhoneNumber, String companyPhoneNumberExt, String companyStreetName, String companyIntNumber, String companyExtNumber, String companyColony, String companyCity, String companyMunicipality, String companyState, String companyCountry, String companyZipCode, Date dateStartedWorking, String companyActivity, String jobStatus, String positionName, String persRefFirstName, String persRefMiddleName, String persRefLastName, String persRefSecLastName, String persRefPhoneNumber, String famRefFirstName, String famRefMiddleName, String famRefLastName, String famRefSecLastName, String famRefPhoneNumber, boolean banorte, boolean bbva, boolean cibanco, boolean credinissan, boolean scotiabank, String user) {
//        this.id = id;
//        this.firstName = firstName;
//        this.middleName = middleName;
//        this.lastName = lastName;
//        this.secondLastName = secondLastName;
//        this.gender = gender;
//        this.maritalStatus = maritalStatus;
//        this.nationality = nationality;
//        this.rfc = rfc;
//        this.cityOfBirth = cityOfBirth;
//        this.countryOfBirth = countryOfBirth;
//        this.stateOfBirth = stateOfBirth;
//        this.curp = curp;
//        this.socialSecurityNumber = socialSecurityNumber;
//        this.dateOfBirth = dateOfBirth;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.streetName = streetName;
//        this.extNumber = extNumber;
//        this.intNumber = intNumber;
//        this.colony = colony;
//        this.municipality = municipality;
//        this.state = state;
//        this.city = city;
//        this.zipCode = zipCode;
//        this.country = country;
//        this.companyName = companyName;
//        this.companyPhoneNumber = companyPhoneNumber;
//        this.companyPhoneNumberExt = companyPhoneNumberExt;
//        this.companyStreetName = companyStreetName;
//        this.companyIntNumber = companyIntNumber;
//        this.companyExtNumber = companyExtNumber;
//        this.companyColony = companyColony;
//        this.companyCity = companyCity;
//        this.companyMunicipality = companyMunicipality;
//        this.companyState = companyState;
//        this.companyCountry = companyCountry;
//        this.companyZipCode = companyZipCode;
//        this.dateStartedWorking = dateStartedWorking;
//        this.companyActivity = companyActivity;
//        this.jobStatus = jobStatus;
//        this.positionName = positionName;
//        this.persRefFirstName = persRefFirstName;
//        this.persRefMiddleName = persRefMiddleName;
//        this.persRefLastName = persRefLastName;
//        this.persRefSecLastName = persRefSecLastName;
//        this.persRefPhoneNumber = persRefPhoneNumber;
//        this.famRefFirstName = famRefFirstName;
//        this.famRefMiddleName = famRefMiddleName;
//        this.famRefLastName = famRefLastName;
//        this.famRefSecLastName = famRefSecLastName;
//        this.famRefPhoneNumber = famRefPhoneNumber;
//        this.banorte = banorte;
//        this.bbva = bbva;
//        this.cibanco = cibanco;
//        this.credinissan = credinissan;
//        this.scotiabank = scotiabank;
//        this.user = user;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public String getStateOfBirth() {
        return stateOfBirth;
    }

    public void setStateOfBirth(String stateOfBirth) {
        this.stateOfBirth = stateOfBirth;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getExtNumber() {
        return extNumber;
    }

    public void setExtNumber(String extNumber) {
        this.extNumber = extNumber;
    }

    public String getIntNumber() {
        return intNumber;
    }

    public void setIntNumber(String intNumber) {
        this.intNumber = intNumber;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public String getCompanyPhoneNumberExt() {
        return companyPhoneNumberExt;
    }

    public void setCompanyPhoneNumberExt(String companyPhoneNumberExt) {
        this.companyPhoneNumberExt = companyPhoneNumberExt;
    }

    public String getCompanyStreetName() {
        return companyStreetName;
    }

    public void setCompanyStreetName(String companyStreetName) {
        this.companyStreetName = companyStreetName;
    }

    public String getCompanyIntNumber() {
        return companyIntNumber;
    }

    public void setCompanyIntNumber(String companyIntNumber) {
        this.companyIntNumber = companyIntNumber;
    }

    public String getCompanyExtNumber() {
        return companyExtNumber;
    }

    public void setCompanyExtNumber(String companyExtNumber) {
        this.companyExtNumber = companyExtNumber;
    }

    public String getCompanyColony() {
        return companyColony;
    }

    public void setCompanyColony(String companyColony) {
        this.companyColony = companyColony;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyMunicipality() {
        return companyMunicipality;
    }

    public void setCompanyMunicipality(String companyMunicipality) {
        this.companyMunicipality = companyMunicipality;
    }

    public String getCompanyState() {
        return companyState;
    }

    public void setCompanyState(String companyState) {
        this.companyState = companyState;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public String getCompanyZipCode() {
        return companyZipCode;
    }

    public void setCompanyZipCode(String companyZipCode) {
        this.companyZipCode = companyZipCode;
    }

    public Date getDateStartedWorking() {
        return dateStartedWorking;
    }

    public void setDateStartedWorking(Date dateStartedWorking) {
        this.dateStartedWorking = dateStartedWorking;
    }

    public String getCompanyActivity() {
        return companyActivity;
    }

    public void setCompanyActivity(String companyActivity) {
        this.companyActivity = companyActivity;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPersRefFirstName() {
        return persRefFirstName;
    }

    public void setPersRefFirstName(String persRefFirstName) {
        this.persRefFirstName = persRefFirstName;
    }

    public String getPersRefMiddleName() {
        return persRefMiddleName;
    }

    public void setPersRefMiddleName(String persRefMiddleName) {
        this.persRefMiddleName = persRefMiddleName;
    }

    public String getPersRefLastName() {
        return persRefLastName;
    }

    public void setPersRefLastName(String persRefLastName) {
        this.persRefLastName = persRefLastName;
    }

    public String getPersRefSecLastName() {
        return persRefSecLastName;
    }

    public void setPersRefSecLastName(String persRefSecLastName) {
        this.persRefSecLastName = persRefSecLastName;
    }

    public String getPersRefPhoneNumber() {
        return persRefPhoneNumber;
    }

    public void setPersRefPhoneNumber(String persRefPhoneNumber) {
        this.persRefPhoneNumber = persRefPhoneNumber;
    }

    public String getFamRefFirstName() {
        return famRefFirstName;
    }

    public void setFamRefFirstName(String famRefFirstName) {
        this.famRefFirstName = famRefFirstName;
    }

    public String getFamRefMiddleName() {
        return famRefMiddleName;
    }

    public void setFamRefMiddleName(String famRefMiddleName) {
        this.famRefMiddleName = famRefMiddleName;
    }

    public String getFamRefLastName() {
        return famRefLastName;
    }

    public void setFamRefLastName(String famRefLastName) {
        this.famRefLastName = famRefLastName;
    }

    public String getFamRefSecLastName() {
        return famRefSecLastName;
    }

    public void setFamRefSecLastName(String famRefSecLastName) {
        this.famRefSecLastName = famRefSecLastName;
    }

    public String getFamRefPhoneNumber() {
        return famRefPhoneNumber;
    }

    public void setFamRefPhoneNumber(String famRefPhoneNumber) {
        this.famRefPhoneNumber = famRefPhoneNumber;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public boolean isBanorte() {
        return banorte;
    }

    public void setBanorte(boolean banorte) {
        this.banorte = banorte;
    }

    public boolean isBbva() {
        return bbva;
    }

    public void setBbva(boolean bbva) {
        this.bbva = bbva;
    }

    public boolean isCibanco() {
        return cibanco;
    }

    public void setCibanco(boolean cibanco) {
        this.cibanco = cibanco;
    }

    public boolean isCredinissan() {
        return credinissan;
    }

    public void setCredinissan(boolean credinissan) {
        this.credinissan = credinissan;
    }

    public boolean isScotiabank() {
        return scotiabank;
    }

    public void setScotiabank(boolean scotiabank) {
        this.scotiabank = scotiabank;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
