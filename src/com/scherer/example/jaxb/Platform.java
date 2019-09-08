package com.scherer.example.jaxb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * The Platform root XML element.
 *
 * @author brettscherer
 */
@XmlRootElement
public class Platform {

	private long id;
	private String name;
	private int heading;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private List<Long> childPlatformIds;

	public Platform() {
		// A no-argument constructor is required by the JAXB framework
	}

	// XmlElement indicates this is a sub-element within the root element
	@XmlElement
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public int getHeading() {
		return this.heading;
	}

	public void setHeading(int heading) {
		this.heading = heading;
	}

	// XmlJavaTypeAdapter indicates an adapter is used to map this object to XML
	@XmlJavaTypeAdapter(BigDecimalAdapter.class)
	@XmlElement
	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	@XmlJavaTypeAdapter(BigDecimalAdapter.class)
	@XmlElement
	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	// XmlElementWrapper creates a parent element around the list of child ids
	@XmlElementWrapper
	// The name attribute indicates the xml element name for items in the list
	@XmlElement(name = "childPlatformId")
	public List<Long> getChildPlatformIds() {
		if (this.childPlatformIds == null) {
			this.childPlatformIds = new ArrayList<>();
		}
		return this.childPlatformIds;
	}
}