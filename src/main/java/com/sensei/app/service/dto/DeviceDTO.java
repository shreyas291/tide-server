/**
 * 
 */
package com.sensei.app.service.dto;

/**
 * @author HP
 *
 */
public class DeviceDTO {

    private Long id;

    public DeviceDTO() {
        // Empty constructor needed for Jackson.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	@Override
	public String toString() {
		return "DeviceDTO [id=" + id + "]";
	}
}
