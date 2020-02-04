/**
 * 
 */
package com.sensei.app.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author HP
 *
 */
public class DeviceDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer version;

	private String deviceName;

	private String serial;

	private String deviceType;

	private LocalDate lastUpdated;

	private double latitude;

	private double langitude;

	private String versionCode;

	private float versionNumber;

	private String school;

	private String encryptionkey;

	private String token;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getEncryptionkey() {
		return encryptionkey;
	}

	public void setEncryptionkey(String encryptionkey) {
		this.encryptionkey = encryptionkey;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public LocalDate getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDate lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLangitude() {
		return langitude;
	}

	public void setLangitude(double langitude) {
		this.langitude = langitude;
	}

	public float getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(float versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
