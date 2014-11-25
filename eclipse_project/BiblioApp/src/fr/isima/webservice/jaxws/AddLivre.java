
package fr.isima.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addLivre", namespace = "http://webservice.isima.fr/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addLivre", namespace = "http://webservice.isima.fr/", propOrder = {
    "titre",
    "prix",
    "resume",
    "numeroA"
})
public class AddLivre {

    @XmlElement(name = "titre", namespace = "")
    private String titre;
    @XmlElement(name = "prix", namespace = "")
    private double prix;
    @XmlElement(name = "resume", namespace = "")
    private String resume;
    @XmlElement(name = "numero_a", namespace = "")
    private Long numeroA;

    /**
     * 
     * @return
     *     returns String
     */
    public String getTitre() {
        return this.titre;
    }

    /**
     * 
     * @param titre
     *     the value for the titre property
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * 
     * @return
     *     returns double
     */
    public double getPrix() {
        return this.prix;
    }

    /**
     * 
     * @param prix
     *     the value for the prix property
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getResume() {
        return this.resume;
    }

    /**
     * 
     * @param resume
     *     the value for the resume property
     */
    public void setResume(String resume) {
        this.resume = resume;
    }

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

}
