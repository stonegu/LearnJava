package jaxb;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Bar {
 
    private String barProp;

	public String getBarProp() {
		return barProp;
	}

	public void setBarProp(String barProp) {
		this.barProp = barProp;
	}
 
}