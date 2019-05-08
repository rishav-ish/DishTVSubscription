
import java.util.regex.*;

class MobileNumberValidation  {
	public static boolean isValid(String s)	{
	Pattern p = Pattern.compile("[6-9][0-9]{9}");
	
	Matcher m = p.matcher(s);
	
	return (m.find() && m.group().equals(s));
		
	}
}

