
package fr.isima.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "deleteLivre", namespace = "http://webservice.isima.fr/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteLivre", namespace = "http://webservice.isima.fr/")
public class DeleteLivre {

    @XmlElement(name = "numero_l", namespace = "")
    private Long numeroL;

    /**
     * 
     * @return
     *     returns Long
     */
    public Long getNumeroL() {
        return this.numeroL;
    }

    /**
     * 
     * @param numeroL
     *     the value for the numeroL property
     */
    public void setNumeroL(Long numeroL) {
        this.numeroL = numeroL;
    }

}
