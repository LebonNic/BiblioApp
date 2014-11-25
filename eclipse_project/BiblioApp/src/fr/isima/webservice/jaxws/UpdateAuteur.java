
package fr.isima.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "updateAuteur", namespace = "http://webservice.isima.fr/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateAuteur", namespace = "http://webservice.isima.fr/", propOrder = {
    "numeroA",
    "nom",
    "prenom",
    "adresse"
})
public class UpdateAuteur {

    @XmlElement(name = "numero_a", namespace = "")
    private Long numeroA;
    @XmlElement(name = "nom", namespace = "")
    private String nom;
    @XmlElement(name = "prenom", namespace = "")
    private String prenom;
    @XmlElement(name = "adresse", namespace = "")
    private String adresse;

    /**
     * 
     * @return
     *     returns Long
     */
    public Long getNumeroA() {
        return this.numeroA;
    }

    /**
     * 
     * @param numeroA
     *     the value for the numeroA property
     */
    public void setNumeroA(Long numeroA) {
        this.numeroA = numeroA;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * 
     * @param nom
     *     the value for the nom property
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * 
     * @param prenom
     *     the value for the prenom property
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * 
     * @param adresse
     *     the value for the adresse property
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
