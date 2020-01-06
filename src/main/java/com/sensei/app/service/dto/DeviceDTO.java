/**
 * 
 */
package com.sensei.app.service.dto;

import javax.persistence.Column;

import com.sensei.app.domain.Device;

/**
 * @author HP
 *
 */
public class DeviceDTO {

    private Integer id;
    private String deviceName;      
 	 private String serial; 	 
 	 private String deviceType; 	   
 	 private String gsmcid; 	   
 	 private String gsmlac; 
 	 private String lastUpdated;	
 	 private String latitude;
 	 private String mcc; 	
 	 private String mnc; 	
 	 private String versionCode; 	
 	 private String versionNumber; 	
 	 private String school; 	
 	 private String encryptionKey;
 	 
 	 
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

	public String getDeviceName() {
		return deviceName;
	}


	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getGsmcid() {
		return gsmcid;
	}

	public void setGsmcid(String gsmcid) {
		this.gsmcid = gsmcid;
	}

	public String getGsmlac() {
		return gsmlac;
	}

	public void setGsmlac(String gsmlac) {
		this.gsmlac = gsmlac;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMnc() {
		return mnc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getEncryptionKey() {
		return encryptionKey;
	}

	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
	}

	@Override
	public String toString() {
		return "DeviceDTO [id=" + id + ", deviceName=" + deviceName + ", serial=" + serial + ", deviceType="
				+ deviceType + ", gsmcid=" + gsmcid + ", gsmlac=" + gsmlac + ", lastUpdated=" + lastUpdated
				+ ", latitude=" + latitude + ", mcc=" + mcc + ", mnc=" + mnc + ", versionCode=" + versionCode
				+ ", versionNumber=" + versionNumber + ", school=" + school + ", encryptionKey=" + encryptionKey + "]";
	}

	

}
