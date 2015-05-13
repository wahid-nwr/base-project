package codegen.util;

import java.io.File;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLReaderWriter
{
	SAXReader xmlReader = null ;
	XMLWriter writer = null ; 

	
	public Document readFile(String dirStr, String fileName) throws Exception
	{
		//System.out.println("readFile(): Enter");
		//System.out.println("readFile(): path = "+ dirStr);
		//System.out.println("readFile(): name = "+ fileName);
		Document doc = null ;
		try 
		{
			xmlReader = new SAXReader();
			File file = new File(dirStr,fileName);
			doc = xmlReader.read(file);
		}
		catch (Exception e)
		{
			System.out.println("readFile()- xml path  not found or cannot read the file: "+ e );
			throw e;
		}
		//System.out.println("readFile(): Exit");
		return doc;
	}
	
	
	
	public void writeXML(Document document, File ouputFile) throws Exception 
	{	
		try 
		{		
			OutputFormat format = OutputFormat.createPrettyPrint();			
			writer = new XMLWriter(new FileWriter(ouputFile), format);
			writer.write( document );
			writer.close();
		} 
		catch (Exception e)
		{
			writer = null;
			System.out.println("writeXML() : Error occured while saving in file " + e);
			throw e ;
		}
	}
	


}
