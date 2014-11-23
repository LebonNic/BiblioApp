
package fr.isima.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getLivreResponse", namespace = "http://webservice.isima.fr/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLivreResponse", namespace = "http://webservice.isima.fr/")
public class GetLivreResponse {

    @XmlElement(name = "return", namespace = "")
    private fr.isima.persistence.Livre _return;

    /**
     * 
     * @return
     *     returns Livre
     */
    public fr.isima.persistence.Livre getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(fr.isima.persistence.Livre _return) {
        this._return = _return;
    }

}
