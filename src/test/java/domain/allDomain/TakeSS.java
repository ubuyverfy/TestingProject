package domain.allDomain;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TakeSS {
	

	public static String errorscreentshots;
	public static int screenshots=1;
	public static void takeScreenshot() throws IOException {
		 
		TakesScreenshot ts = (TakesScreenshot) HitAllDomain.driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("/home/ramesh/Downloads/ss/screenshot"+screenshots+".png"));
		screenshots++;
		errorscreentshots="/home/ramesh/Downloads/ss/screenshot"+screenshots+".png";
		
	}
	
}
