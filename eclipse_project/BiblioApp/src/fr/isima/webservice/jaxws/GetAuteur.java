
package fr.isima.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAuteur", namespace = "http://webservice.isima.fr/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAuteur", namespace = "http://webservice.isima.fr/")
public class GetAuteur {

    @XmlElement(name = "numero_a", namespace = "")
    private Long numeroA;

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
