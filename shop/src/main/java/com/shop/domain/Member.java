package com.shop.domain;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(value="hibernateLazyInitializer") 
@Getter
@Setter
@EqualsAndHashCode(of="userNo")
@ToString
@Entity
@Table(name="member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_no")
	private Long userNo;
	
	@NotBlank
	@Column(length = 50, nullable = false)
	private String userId;
	
	@NotBlank
	@Column(length = 200, nullable = false)
	private String userPw;
	
	@NotBlank
	@Column(length = 100, nullable = false)
	private String userName;
	
	@Column(length = 3, nullable = false)
	private String job;
	
	private int coin;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	private LocalDateTime updDate;

	// 회원권한과 매핑
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "user_no")
	private List<MemberAuth> authList = new ArrayList<MemberAuth>();

	public void addAuth(MemberAuth auth) {
		authList.add(auth);
	}

	public void clearAuthList() {
		authList.clear();
	}
	

}

