package com.covid.dashboard.model.sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@ToString
public class UserEntity implements UserDetails {
	private static final long serialVersionUID = -1229223520906444433L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter
	private Long id;
	
	@Getter
	@Setter
	@Column(name="user_name")
	private String username;
	
	@Getter
	@Setter
	@Column(name="password")
	private String password;
	
	@Getter
	@Column(name="birthdate")
	private LocalDate birthdate;

	@Getter
	@Setter
	@Column(name="first_name")
	private String firstName

	@Getter
	@Setter
	@Column(name="middle_name")
	private String middleName

	@Getter
	@Setter
	@Column(name="last_name")
	private String lastName

	@Getter
	@Setter
	@Column(name="gender")
	private String gender

	@Getter
	@Setter
	@Column(name="phone_number")
	private String phoneNumber

	@Getter
	@Column(name="zipcode")
	private String zipcode

	@Getter
	@Setter
	@Column(name="has_pre_existing_conditions")
	private boolean hasPreExistingConditions

	@Getter
	@Setter
	@Column(name="is_following_hygiene_guidelines")
	private boolean isFollowingHygieneGuidelines

	@Getter
	@Setter
	@Column(name="is_adhering_to_ppp_guidelines")
	private boolean isAdheringToPPPGuidelines

	@Getter
	@Setter
	@Column(name="vaccine_status")
	private String vaccineStatus

	@Getter
	@Setter
	@Column(name="has_roomates")
	private boolean hasRoommates

	@Getter
	@Setter
	@Column(name="direct_exposure_count")
	private int directExposureCount

	@Getter
	@Setter
	@Column(name="indirect_exposure_count")
	private int indirectExposureCount

	@Getter
	@Setter
	@Column(name="is_first_responder")
	private boolean isFirstResponder

	@Getter
	@Setter
	@Column(name="is_essential_worker")
	private boolean isEssentialWorker
	
	@Getter
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@Getter
	@Setter
	@Column(name="last_updated_date")
	private LocalDateTime lastUpdatedDate;

	public UserEntity(String username, String password, LocalDate birthDate) {
		this.username = username;
		this.password = password;
		this.birthdate = birthDate;
		this.enabled = false;
		this.createdDate = LocalDateTime.now();
		this.lastUpdatedDate = LocalDateTime.now();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<RoleEntity> roles = this.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (RoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
         
        return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return truev;
	}
}