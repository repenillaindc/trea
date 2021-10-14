package pe.com.bbva.siso;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

//import org.apache.log4j.Logger;

/**
 * Esta clase define metodos utilizados en los proyectos desarrollados tanto con
 * lenguaje Java como ESQL
 * 
 * @author: Equipo de Desarrollo de IBM.
 * @version: 1.0
 */

public class Util{

	//private static Logger log = Logger.getLogger(Util.class);
	
	private static String WORK_DIRECTORY = "work_dir";
	
	private static Properties list;
	
	static 
	{
		try
		{
			String CONFIG_DIRECTORY = "/app/bbva/esb/proyecto/" + System.getenv().get(WORK_DIRECTORY) + "/config/";
			String CONFIG_FILE_UTIL = CONFIG_DIRECTORY + "Util.properties";				
			
			list = new Properties();
		    list.load(new FileInputStream(CONFIG_FILE_UTIL));
		}
		catch(Exception e)
		{
			//log.error(e);
		}	
	}
	
	
	/**
	 * Método que devuelve la fecha del sistema
	 * 
	 * @return Fecha del sistema con el formato yyyy-MM-dd HH:mm:ss.SSSSSS
	 */
	public static String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}

	/**
	 * Método que devuelve la fecha del sistema
	 * 
	 * @return Fecha del sistema con el formato personalizado
	 */	
	public static String getCurrentTimeStamp(String format) {
	    SimpleDateFormat sdfDate = new SimpleDateFormat(format);
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}
	
	/**
	 * Método que devuelve una cadena desde un archivo
	 * 
	 * @return Cadena valor en base a una propiedad.
	 */
	public static String getPropertyValue(String property) {
	    return list.getProperty(property);
	}
	

	/**
	 * Método que renombra un archivo especifico.
	 * 
	 * @return Estado resultado del proceso.
	 */
	public static Boolean setRenameFile(String pathOldFile, String pathNewFile) {
		File oldFile = new File(pathOldFile);
		File newFile = new File(pathNewFile);        
	    return oldFile.renameTo(newFile);
	}
		
}
