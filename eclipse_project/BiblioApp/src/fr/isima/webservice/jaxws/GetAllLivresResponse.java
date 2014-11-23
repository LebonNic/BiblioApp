
package fr.isima.webservice.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAllLivresResponse", namespace = "http://webservice.isima.fr/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllLivresResponse", namespace = "http://webservice.isima.fr/")
public class GetAllLivresResponse {

    @XmlElement(name = "return", namespace = "")
    private List<fr.isima.persistence.Livre> _return;

    /**
     * 
     * @return
     *     returns List<Livre>
     */
    public List<fr.isima.persistence.Livre> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<fr.isima.persistence.Livre> _return) {
        this._return = _return;
    }

}
