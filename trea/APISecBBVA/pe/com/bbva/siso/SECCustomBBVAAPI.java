package pe.com.bbva.siso;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.sanms.siso.sec.custom.api.tib.SECCustomAPITIBBBVA;
import com.sanms.siso.sec.custom.api.tib.SECCustomAPITIBResult;
import com.sanms.siso.sec.custom.api.tib.SECCustomAPITIBBBVAWorker;


public class SECCustomBBVAAPI {

	private static Logger log = Logger.getLogger(SECCustomBBVAAPI.class);	
	
	private static String WORK_DIRECTORY = "work_dir";
	
	private static String SISOSEC_HOST = "SISO_HOSTNAME";
	
	private static String SISOSEC_PORT = "SISO_PORT";
	
	private static String SISOSEC_TIMEOUT = "SISO_TIMEOUT";
			
	private static SECCustomAPITIBBBVAWorker worker;
		
	static 
	{
		try
		{
			String CONFIG_DIRECTORY = "/app/bbva/esb/proyecto/" + System.getenv().get(WORK_DIRECTORY) + "/config/";
			String CONFIG_FILE_SISOSEC = CONFIG_DIRECTORY + "SisoSec.properties";				
			String CONFIG_FILE_LOG4J = CONFIG_DIRECTORY + "log4j.properties";
						
			PropertyConfigurator.configure(CONFIG_FILE_LOG4J);
			Properties list = new Properties();
		    list.load(new FileInputStream(CONFIG_FILE_SISOSEC));
		    
		    String host = list.getProperty(SISOSEC_HOST);
		    String port = list.getProperty(SISOSEC_PORT);
		    int timeout = Integer.parseInt(list.getProperty(SISOSEC_TIMEOUT));    
		    worker = (SECCustomAPITIBBBVAWorker)SECCustomAPITIBBBVA.createWorker(host,port,timeout);
		    
		    log.info("SISOSec - Resultado enlace[" + worker.getResult() + "]");
		    log.info("WORK_DIRECTORY [" + System.getenv().get(WORK_DIRECTORY) + "]");
		    log.info("CONFIG_DIRECTORY [" + CONFIG_DIRECTORY + "]");
		    log.info("CONFIG_FILE_SISOSEC [" + CONFIG_FILE_SISOSEC + "]");
		    log.info("CONFIG_FILE_LOG4J [" + CONFIG_FILE_LOG4J + "]");
		}
		catch(Exception e)
		{
			log.error(e);
		}	
	}
	
	
	public static void cifrar(String inData, String inBinZona, String inReferencia, Long[] outCodigoRespuesta, String[] outDataCifrada, 
            				  Long[] outLongDataCifrada, String[] outClaveKSIMKpubER, Long[] outLongClaveKSIMKpubER){
		log.info("*Metodo Cifrar*");
		log.info("Entrada");
		log.info("  Referencia[" + inReferencia + "]");
		log.info("  BinZona[" + inBinZona + "]");
		log.info("  Data[" + inData + "]");		
		
		String filler = "        ";
		int dataLen = inData.length();
		if (dataLen % 8 > 0)
		{
			int diffLen = 8 - dataLen % 8;
			inData = inData + filler.substring(0, diffLen);
		}
		log.info("  Data-UTF8[" + inData + "]");	
		/**Significado de variable lmkid**/
		SECCustomAPITIBResult result = SECCustomAPITIBBBVA.cifrarDataAplicativa(worker,0,inBinZona,inData.length(),inData);
		outCodigoRespuesta[0] = Long.valueOf(result.getCodigoRespuesta());
		outDataCifrada[0] 	  = result.getDataCifrada();
		outLongDataCifrada[0] = Long.valueOf(result.getLongitudDataCifrada());
		outClaveKSIMKpubER[0] = result.getClaveKSIMKpubER();
		outLongClaveKSIMKpubER[0] = Long.valueOf(result.getLongitudClaveKSIMKpubER());
		log.info("Salida");
		log.info("  Codigo Resultado[" + result.getCodigoRespuesta() + "]");
		log.info("  Data Cifrada[" + result.getDataCifrada() + "]");
		log.info("  Longitud Data Cifrada[" + result.getLongitudDataCifrada() + "]");
		log.info("  Clave KSIMKpubER[" + result.getClaveKSIMKpubER() + "]");
		log.info("  Longitud Clave KSIMKpubER[" + result.getLongitudClaveKSIMKpubER() + "]");
	}	
	
	
	public static void descifrar(String inData, String inKSimCifrada, String inBinZonaServer, String inBinZonaClient, String inReferencia, Long[] outCodigoRespuesta,
			 					 String[] outData, Long[] outLongData){
		log.info("*Metodo Descifrar*");
		log.info("Entrada");
		log.info("  Referencia[" + inReferencia + "]");
		log.info("  BinZonaServer[" + inBinZonaServer + "]");
		log.info("  BinZonaClient[" + inBinZonaClient + "]");
		log.info("  Data Cifrada[" + inData + "]");
		log.info("  KSIMKpubER[" + inKSimCifrada + "]");

		SECCustomAPITIBResult result = SECCustomAPITIBBBVA.descifrarDataAplicativa(worker,0,inBinZonaServer,inBinZonaClient,inData.length(),inData,inKSimCifrada.length(),inKSimCifrada);
		outCodigoRespuesta[0] = Long.valueOf(result.getCodigoRespuesta());
		outData[0] 	   = result.getData();
		outLongData[0] = Long.valueOf(result.getLongitudData());
		log.info("Salida");
		log.info("  Codigo Resultado[" + result.getCodigoRespuesta() + "]");
		log.info("  Data[" + result.getData() + "]");
		log.info("  Longitud Data[" + result.getLongitudData() + "]");	
	}

	
	public static void firmar(String inData, String inBinZonaServer, String inBinZonaClient, String inReferencia, Long[] outCodigoRespuesta, String[] outFirma, 
	         				  Long[] outLongFirma){
		log.info("*Metodo Firmar*");
		log.info("Entrada");
		log.info("  Referencia[" + inReferencia + "]");
		log.info("  BinZonaServer[" + inBinZonaServer + "]");
		log.info("  BinZonaClient[" + inBinZonaClient + "]");		
		log.info("  Data[" + inData + "]");

		SECCustomAPITIBResult result = SECCustomAPITIBBBVA.generarFirma(worker,0,inBinZonaServer,inBinZonaClient,inData.length(),inData);
		outCodigoRespuesta[0] = Long.valueOf(result.getCodigoRespuesta());
		outFirma[0]     = result.getFirma();
		outLongFirma[0] = Long.valueOf(result.getLongitudFirma());
		log.info("Salida");
		log.info("  Codigo Resultado[" + result.getCodigoRespuesta() + "]");
		log.info("  Firma[" + result.getFirma() + "]");
		log.info("  Longitud Firma[" + result.getLongitudFirma() + "]");	
	}

	
	public static void validar(String inData, String inFirma, String inBinZona, String inReferencia, Long[] outCodigoRespuesta){
		log.info("*Metodo Validar*");
		log.info("Entrada");
		log.info("  Referencia[" + inReferencia + "]");
		log.info("  BinZona[" + inBinZona + "]");
		log.info("  Data[" + inData + "]");
		log.info("  Firma[" + inFirma + "]");

		SECCustomAPITIBResult result = SECCustomAPITIBBBVA.validarFirma(worker,0,inBinZona,inData.length(),inData,inFirma.length(),inFirma);
		outCodigoRespuesta[0] = Long.valueOf(result.getCodigoRespuesta());
		log.info("Salida");
		log.info("  Codigo Resultado[" + result.getCodigoRespuesta() + "]");
	}

	
	public static void main(String[] args) 
	{
		String data = "";
		
		String[] outDataCifrada = { "" };
		String[] outClaveKSIMKpubER = { "" };
		Long[] outCodigoRespuesta = { Long.valueOf(0L) };Long[] outLongDataCifrada = { Long.valueOf(0L) };Long[] outLongClaveKSIMKpubER = { Long.valueOf(0L) };
		cifrar(data, "0011", "", outCodigoRespuesta, outDataCifrada, outLongDataCifrada, outClaveKSIMKpubER, outLongClaveKSIMKpubER);
		
		String[] outData = { "" };
		Long[] outLongData = { Long.valueOf(0L) };
		descifrar(outDataCifrada[0], "0011", "0001", "", outClaveKSIMKpubER[0], outCodigoRespuesta, outData, outLongData);
	}

}
