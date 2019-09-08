package com.scherer.example.jaxb;

import java.math.BigDecimal;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Maps BigDecimal to an XML representation.
 *
 * @author brettscherer
 */
public class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {

	/**
	 * Converts a BigDecimal to a plain String (without scientific notation).
	 */
	@Override
	public String marshal(BigDecimal bigDecimal) throws Exception {
		String stringValue = null;
		if (bigDecimal != null) {
			stringValue = bigDecimal.toPlainString();
		}
		return stringValue;
	}

	/**
	 * Converts a String to a BigDecimal.
	 */
	@Override
	public BigDecimal unmarshal(String stringValue) {
		BigDecimal bigDecimal = null;
		if (stringValue != null) {
			bigDecimal = new BigDecimal(stringValue);
		}
		return bigDecimal;
	}
}