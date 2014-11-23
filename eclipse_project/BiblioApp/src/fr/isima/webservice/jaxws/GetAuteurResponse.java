
package fr.isima.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAuteurResponse", namespace = "http://webservice.isima.fr/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAuteurResponse", namespace = "http://webservice.isima.fr/")
public class GetAuteurResponse {

    @XmlElement(name = "return", namespace = "")
    private fr.isima.persistence.Auteur _return;

    /**
     * 
     * @return
     *     returns Auteur
     */
    public fr.isima.persistence.Auteur getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(fr.isima.persistence.Auteur _return) {
        this._return = _return;
    }

}
