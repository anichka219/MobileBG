package com.example.demo.models;


import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.example.demo.models.enums.Cooling;
import com.example.demo.models.enums.Currency;
import com.example.demo.models.enums.EngineKind;
import com.example.demo.models.enums.EngineType;
import com.example.demo.models.enums.EuroStandart;
import com.example.demo.models.enums.State;
import com.example.demo.models.enums.Transmission;
import com.example.demo.models.enums.Validity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="advertisements")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Advertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String modification;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "enum('Бензинов', 'Дизелов', 'Електрически', 'Хибриден')",name="engine_type")
	private EngineType engineType;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "enum('Нов', 'Употребяван', 'За части')",name="state")
	private State state;
	private int power;
	@Enumerated(EnumType.STRING)
	@Column (columnDefinition= "enum('Евро 1', 'Евро 2', 'Евро 3', 'Евро 4', 'Евро 5', 'Евро 6')")
	private EuroStandart eurostandart;
	@Enumerated(EnumType.STRING)
	@Column (columnDefinition= "enum('Ръчна', 'Авроматична', 'Полуавтоматична')")
	private Transmission transmission;
	private int price;
	@Enumerated(EnumType.STRING)
	@Column (columnDefinition= "enum('EUR', 'USD', 'BGN')")
	private Currency currency;
	@Column (name="production_date")
	private YearMonth productionDate;
	private int mileage;
	@Enumerated(EnumType.STRING)
	@Column (columnDefinition= "enum('28 дни', '35 дни', '42 дни', '49 дни', '56 дни')")
	private Validity validity;
	@Column (name="axes_count")
	private int axesCount;
	@Column (name="seat_count")
	private int seatCount;
	@Column (name="load_capacity")
	private int loadCapacity;
	private int cubage;
	@Enumerated(EnumType.STRING)
	@Column (columnDefinition= "enum('Водно', 'Въздушно')")
	private Cooling cooling;
	@Enumerated(EnumType.STRING)
	@Column (columnDefinition= "enum('2','4')", name="engine_kind")
	private EngineKind engineKind;
	@ManyToOne
	private Location location;
	@ManyToOne
	private Color color;
	@ManyToOne
	private Brand brand;
	@ManyToOne
	private MainCategory mainCategory;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User user;
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	    @JoinTable(name = "user_favourite_advertisment",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "advertisement_id")
	    )
	private Set<User> favourites= new HashSet<User>();
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	    @JoinTable(name = "details_advertisements",
	        joinColumns = @JoinColumn(name = "detail_id"),
	        inverseJoinColumns = @JoinColumn(name = "advertisement_id")
	    )
	private Set<Detail> details= new HashSet<Detail>();
	@OneToMany(mappedBy="advertisement")
	private Set<File> files=new HashSet<File>();
	
}
