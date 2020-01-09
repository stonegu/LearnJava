package general;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralTest {
	
	public static void main(String...args) throws ParseException {
		
		SimpleDateFormat dateformat1 = new SimpleDateFormat("MM yyyy");
		SimpleDateFormat dateformat2 = new SimpleDateFormat("yyMM");
		Date date = dateformat1.parse("01 2018");
		System.out.println(date);
		
		System.out.println(dateformat2.format(date));
		
		
		
	}

}
