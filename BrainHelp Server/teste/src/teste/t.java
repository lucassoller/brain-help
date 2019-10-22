package teste;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class t {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Date().getTime());
		 GregorianCalendar gc = new GregorianCalendar();
		    gc.setTime(new Date());		  
		    gc.add(Calendar.MINUTE,30);
		    System.out.println(gc.getTimeInMillis());
	}

}
