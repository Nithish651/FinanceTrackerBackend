package com.financetracker.userservice.modal;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_details")
public class User {

	@Id
	@SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
	private Long id;
	private String userName;
	private String password;
	private Date lastLogggedInTime;
	private String email;
	private String phone;
}
