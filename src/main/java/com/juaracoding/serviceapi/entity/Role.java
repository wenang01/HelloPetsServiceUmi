package com.juaracoding.serviceapi.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="roles")
public class Role {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		@Enumerated(EnumType.STRING)
		@Column(length = 20)
		private ERole name;
		
		public Role(ERole name) {
			this.name = name;
		}
}
