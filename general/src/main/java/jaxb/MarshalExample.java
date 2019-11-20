package jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class MarshalExample {

	
	public static Root mockRoot() {
		Root root = new Root();
		Foo foo = new Foo();
		foo.setFooProp("foo1");
		
		Bar bar = new Bar();
		bar.setBarProp("bar1");
		
		root.getAny().add(foo);
//		root.getAny().add(bar);
		
		return root;
		
	}
	
	public static void main (String... args) {
		
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Root.class, Foo.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			marshaller.marshal(mockRoot(), System.out); // temp location
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
