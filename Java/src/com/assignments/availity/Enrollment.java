package com.assignments.availity;
/**
* Pojo class which holds each row of the CSV file
*
* @author  Vidhya Vikraman Nair
*
*/
public class Enrollment {
	private String userId;
	private String firstName;
	private String lastName;
	private int version;
	private String insuranceCompany;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	@Override
	public String toString() {
		String space = " ";
		return "Name="+firstName+ space+lastName+space+"userId="+userId+space+"insuranceCompany="+insuranceCompany;
	}

}
