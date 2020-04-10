package com.msgraph.colibo.xml;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Parser xml til POJO
 * @author Christoffer
 *
 */
public class XmlReader {
	public Data ReadXmlFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		try(InputStream input = classLoader.getResourceAsStream(fileName)) {
			
			//træk xml fra stream til string
			String xml = IOUtils.toString(input, StandardCharsets.UTF_8);
			StringReader sr = new StringReader(xml);
			
			//JAXB lib læser xml ind til POJO
			JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Data data = (Data) unmarshaller.unmarshal(sr);
			
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
