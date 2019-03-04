package com.example.demo.models.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;



	@Getter
	@Setter
	@AllArgsConstructor
	@EqualsAndHashCode
	public class AdvertisementDto {
		
	
		private Long id;
		private String modification;
		
		public AdvertisementDto(Long id, String modification) {
			super();
			this.id = id;
			this.modification = modification;
		}
		
		
		
	}

