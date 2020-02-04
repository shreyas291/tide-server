package com.sensei.app.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "device")
public class Device extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Version
	@Column(name = "version")
	private int version;

	@NotNull
	@Column(name = "device_name", length = 45)
	private String deviceName;

	@Column(name = "serial")
	private String serial;

	@Column(name = "device_type")
	private String deviceType;

	@Column(name = "last_updated")
	private LocalDate lastUpdated;

	@Column(name = "latitude")
	private double latitude;

	@Column(name = "langitude")
	private double langitude;

	@Column(name = "version_code")
	private String versionCode;

	@Column(name = "version_number")
	private double versionNumber;

	@Column(name = "school")
	private String school;

	@Column(name = "encryption_Key")
	private String encryptionkey;

	@Column(name = "token")
	private String token;

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

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public double getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(double versionNumber) {
		this.versionNumber = versionNumber;
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

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public double getLangitude() {
		return langitude;
	}

	public void setLangitude(double langitude) {
		this.langitude = langitude;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
return Objects.hashCode(getId());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Device device = (Device) o;
		if (device.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), device.getId());
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", version=" + version + ", deviceName=" + deviceName + ", serial=" + serial
				+ ", deviceType=" + deviceType + ", lastUpdated=" + lastUpdated + ", latitude=" + latitude
				+ ", langitude=" + langitude + ", versionCode=" + versionCode + ", versionNumber=" + versionNumber
				+ ", school=" + school + ", encryptionkey=" + encryptionkey + ", token=" + token + "]";
	}

	

}
