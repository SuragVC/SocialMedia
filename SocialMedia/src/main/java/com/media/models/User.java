package com.media.models;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "User", unique = true, nullable = false)
	private Integer userId;
	
	@NotNull
	@Pattern(regexp="[a-zA-Z]{3,12}", message = "User Name must not contains any numbers and Special Character")
	private String userName;
	

	@NotNull
	@Size(min=10,max=10)
	@Pattern(regexp="[6-9]{1}[0-9]{9}", message = "Mobile number must have 10 digits mobile Number")
	private String mobileNo;
	
	@NotNull
	@Pattern(regexp="[a-zA-Z0-9]{6,12}",message="Password must contain between 6 to 12 characters. Must be alphanumeric with both Upper and lowercase characters.")
	private String password;
	
	@Email
	@NotNull
	private String email;
	
	@JsonIgnore
	@OneToOne(optional = false, cascade = CascadeType.ALL)
	private UserManager manager;
}
