package com.sensei.app.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.sensei.app.config.Constants;

@Entity
@Table(name = "user_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserData extends AbstractAuditingEntity implements Serializable {

	 private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotNull
	    @Pattern(regexp = Constants.LOGIN_REGEX)
	    @Size(min = 1, max = 50)
	    @Column(length = 50, unique = true, nullable = false)
	    private String login;
	    
	    @Size(max = 50)
	    @Column(name = "first_name", length = 50)
	    private String firstName;

	    @Size(max = 50)
	    @Column(name = "last_name", length = 50)
	    private String lastName;

	    @Email
	    @Size(min = 5, max = 254)
	    @Column(length = 254, unique = true)
	    private String email;
	    
	    @Size(min = 2, max = 6)
	    @Column(name = "lang_key", length = 6)
	    private String langKey;

	    @Size(max = 256)
	    @Column(name = "image_url", length = 256)
	    private String imageUrl;
	    
	    @Column(name = "role")
	    private String role;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getLangKey() {
			return langKey;
		}

		public void setLangKey(String langKey) {
			this.langKey = langKey;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	    
		 @Override
		    public boolean equals(Object o) {
		        if (this == o) {
		            return true;
		        }
		        if (o == null || getClass() != o.getClass()) {
		            return false;
		        }

		        UserData user = (UserData) o;
		        return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());
		    }

		    @Override
		    public int hashCode() {
		        return Objects.hashCode(getId());
		    }

			@Override
			public String toString() {
				return "UserData [id=" + id + ", login=" + login + ", firstName=" + firstName + ", lastName=" + lastName
						+ ", email=" + email + ", langKey=" + langKey + ", imageUrl=" + imageUrl + ", role=" + role
						+ "]";
			}

}
