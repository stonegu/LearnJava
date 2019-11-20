package jaxb;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "foo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Foo {
 
	@XmlElement(name = "fooProp")
    private String fooProp;

	public String getFooProp() {
		return fooProp;
	}

	public void setFooProp(String fooProp) {
		this.fooProp = fooProp;
	}
 
}