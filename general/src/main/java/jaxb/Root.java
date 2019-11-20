package jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement (name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Root", propOrder = {
	    "any"
	})
public class Root {
 
    @XmlAnyElement(lax = true)
    protected List<Object> any;

	public List<Object> getAny() {
		if (any == null) {
			any = new ArrayList<Object>();
		}
		return any;
	}

	
//	@XmlElement(name = "any")
//    protected List<Foo> any;
//
//	public List<Foo> getAny() {
//		if (any == null) {
//			any = new ArrayList<Foo>();
//		}
//		return any;
//	}
	
	
}