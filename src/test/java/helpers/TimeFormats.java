package helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeFormats {
	
	private Calendar calendar;
	
	public static String currentTimeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
	}
	
	public Object milliSecToDate(long millis) {
		calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
	}	
}