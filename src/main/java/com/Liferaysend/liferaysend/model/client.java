
package com.Liferaysend.liferaysend.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class client {
    @Id
    @SequenceGenerator(name="client_generator", sequenceName="client_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="client_generator")
    private Long clientId;
    private String clientCin;
    @NotBlank(message = "Nom est obligatoire")
    private String clientName;
    @NotBlank(message = "prenom est obligatoire")
    private String clientPrenom;
    @NotBlank(message = "Etat est obligatoire")
    private String clientState;
    private String clientVille;
    private String clientEmail;
    private String clientFileName;
    @NotBlank(message = "la date est  obligatoire")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate clientDOB;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public client() {

    }

    public String getEmail() {
        return clientEmail;
    }

    public void setEmail(String email) {
        clientEmail = email;
    }

    public String getClientVille() {
        return clientVille;
    }

    public void setClientVille(String clientVille) {
        this.clientVille = clientVille;
    }

    public LocalDate getClientDOB() {
        return clientDOB;
    }

    public void setClientDOB(LocalDate clientDOB) {
        this.clientDOB = clientDOB;
    }

    public client(String clientCin,String email,String clientVille,String clientName, String clientPrenom, String clientState, String clientFileName, LocalDate clientDOB) {
        this.clientCin = clientCin;
        this.clientName = clientName;
        this.clientPrenom = clientPrenom;
        this.clientState = clientState;
        this.clientFileName = clientFileName;
        this.clientDOB = clientDOB;
        this.clientEmail=email;
        this.clientVille=clientVille;
    }

    public String getClientCin() {
        return clientCin;
    }

    public void setClientCin(String clientCin) {
        this.clientCin = clientCin;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPrenom() {
        return clientPrenom;
    }

    public void setClientPrenom(String clientPrenom) {
        this.clientPrenom = clientPrenom;
    }

    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }

    public String getClientFileName() {
        return clientFileName;
    }

    public void setClientFileName(String clientFileName) {
        this.clientFileName = clientFileName;
    }
}
