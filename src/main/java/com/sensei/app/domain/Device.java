package com.sensei.app.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "_devices")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Device implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	
	 @Size(max = 45)
	    @Column(name = "device_name", length = 45)
	    private String deviceName;
	
	
	    @Column(name = "serial")
	 private String serial;
	 
	 
	
	    @Column(name = "deviceType")
	 private String deviceType;
	 
	
	    @Column(name = "gsmcid")
	 private String gsmcid;
	 
	
	    @Column(name = "gsmlac")
	 private String gsmlac;
	 
	
	    @Column(name = "lastUpdated")
	 private String lastUpdated;
	 
	
	    @Column(name = "latitude")
	 private String latitude;
	 
	
	    @Column(name = "mcc")
	 private String mcc;
	 
	
	    @Column(name = "mnc")
	 private String mnc;
	 
	
	    @Column(name = "versionCode")
	 private String versionCode;
	 
	
	    @Column(name = "versionNumber")
	 private String versionNumber;
	 
	
	    @Column(name = "school")
	 private String school;
	 
	
	    @Column(name = "encryptionKey")
	 private String encryptionKey;
	 
	 
	 

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


	public String getDeviceName() {
		return deviceName;
	}


	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Device [id=" + id + ", deviceName=" + deviceName + ", serial=" + serial + ", deviceType=" + deviceType
				+ ", gsmcid=" + gsmcid + ", gsmlac=" + gsmlac + ", lastUpdated=" + lastUpdated + ", latitude="
				+ latitude + ", mcc=" + mcc + ", mnc=" + mnc + ", versionCode=" + versionCode + ", versionNumber="
				+ versionNumber + ", school=" + school + ", encryptionKey=" + encryptionKey + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceName == null) ? 0 : deviceName.hashCode());
		result = prime * result + ((deviceType == null) ? 0 : deviceType.hashCode());
		result = prime * result + ((encryptionKey == null) ? 0 : encryptionKey.hashCode());
		result = prime * result + ((gsmcid == null) ? 0 : gsmcid.hashCode());
		result = prime * result + ((gsmlac == null) ? 0 : gsmlac.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((mcc == null) ? 0 : mcc.hashCode());
		result = prime * result + ((mnc == null) ? 0 : mnc.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		result = prime * result + ((serial == null) ? 0 : serial.hashCode());
		result = prime * result + ((versionCode == null) ? 0 : versionCode.hashCode());
		result = prime * result + ((versionNumber == null) ? 0 : versionNumber.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		if (deviceName == null) {
			if (other.deviceName != null)
				return false;
		} else if (!deviceName.equals(other.deviceName))
			return false;
		if (deviceType == null) {
			if (other.deviceType != null)
				return false;
		} else if (!deviceType.equals(other.deviceType))
			return false;
		if (encryptionKey == null) {
			if (other.encryptionKey != null)
				return false;
		} else if (!encryptionKey.equals(other.encryptionKey))
			return false;
		if (gsmcid == null) {
			if (other.gsmcid != null)
				return false;
		} else if (!gsmcid.equals(other.gsmcid))
			return false;
		if (gsmlac == null) {
			if (other.gsmlac != null)
				return false;
		} else if (!gsmlac.equals(other.gsmlac))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastUpdated == null) {
			if (other.lastUpdated != null)
				return false;
		} else if (!lastUpdated.equals(other.lastUpdated))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (mcc == null) {
			if (other.mcc != null)
				return false;
		} else if (!mcc.equals(other.mcc))
			return false;
		if (mnc == null) {
			if (other.mnc != null)
				return false;
		} else if (!mnc.equals(other.mnc))
			return false;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		if (serial == null) {
			if (other.serial != null)
				return false;
		} else if (!serial.equals(other.serial))
			return false;
		if (versionCode == null) {
			if (other.versionCode != null)
				return false;
		} else if (!versionCode.equals(other.versionCode))
			return false;
		if (versionNumber == null) {
			if (other.versionNumber != null)
				return false;
		} else if (!versionNumber.equals(other.versionNumber))
			return false;
		return true;
	}


	

	 
	 
}
