package com.scherer.example.jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Performs JAXB marshalling and unmarshalling operations.
 *
 * @author brettscherer
 */
public class JAXBMarshallingOperations {

	/**
	 * Marshals a JAXB object into an XML-formatted String.
	 *
	 * @param instance The JAXB object instance
	 * @param clazz    The class of the JAXB object
	 * @return An XML-formatted String representing the JAXB object
	 * @throws Exception
	 */
	public static <T> String marshalObjectToXMLString(T instance, Class<T> clazz) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

		StringWriter stringWriter = new StringWriter();

		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(instance, stringWriter);

		return stringWriter.toString();
	}

	/**
	 * Unmarshals an XML String into a JAXB object of class T.
	 *
	 * @param xml   An XML String
	 * @param clazz The class of the JAXB object
	 * @return The JAXB object instance of class T
	 * @throws Exception
	 */
	public static <T> T unmarshalXMLStringToObject(String xml, Class<T> clazz) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

		StringReader stringReader = new StringReader(xml);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Object obj = unmarshaller.unmarshal(stringReader);

		// Return the unmarshalled object casted to class type T
		return clazz.cast(obj);
	}
}