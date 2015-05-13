package com.swiftcorp.portal.geo.dto;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class GeoDTO extends PersistentCapableDTO
{
	private String code = null;
	private String name = null;
	private int geoType;
	
	// area number
	private String geoNumber;
	private String address;
	private String location;
	private String officePhoneNumber;
	private String contactPerson;
	private String contactNumber;
	
	// parent are this will contain its upper level
	private GeoDTO parentArea;
	
	private long parentAreaId;
	
	
	public String getCode ( )
	{
		return this.code;
	}
	
	public String getName ( )
	{
		return this.name;
	}
	
	public void setCode ( String code )
	{
		this.code = code;
	}
	
	public void setName ( String name )
	{
		this.name = name;
	}

	public int getGeoType ( )
	{
		return geoType;
	}

	public void setGeoType ( int geoType )
	{
		this.geoType = geoType;
	}

	public GeoDTO getParentArea ( )
	{
		return parentArea;
	}

	public void setParentArea ( GeoDTO parentArea )
	{
		this.parentArea = parentArea;
	}

	public String getGeoNumber() {
		return geoNumber;
	}

	public void setGeoNumber(String geoNumber) {
		this.geoNumber = geoNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOfficePhoneNumber() {
		return officePhoneNumber;
	}

	public void setOfficePhoneNumber(String officePhoneNumber) {
		this.officePhoneNumber = officePhoneNumber;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public long getParentAreaId() {
		return parentAreaId;
	}

	public void setParentAreaId(long parentAreaId) {
		this.parentAreaId = parentAreaId;
	}
	
}
