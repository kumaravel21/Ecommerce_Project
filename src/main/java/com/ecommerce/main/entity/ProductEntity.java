package com.ecommerce.main.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name="product")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id")
	private long product_id;
	
	@Column(name="product_name", nullable = false)
	private String product_name;
	
	@Column(name="product_description", nullable = false)
	private String product_description;
	
	@Column(name="product_price", nullable = false)
	private BigDecimal product_price;
	
	@Column(name="stock_quantity", nullable = false)
	private int stock_quantity;
	
	@Column(name="product_created", nullable=false)
	private LocalDateTime product_created;
	
	@Column(name="product_last_update", nullable=false)
	private LocalDateTime product_last_update;
	
	@OneToMany(mappedBy = "prod", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     private List<OrderDetailEntity> orderdetails = new ArrayList<OrderDetailEntity>();
	
      @ManyToMany
      @JoinTable( name = "product_category",
		        joinColumns = @JoinColumn(name = "product_id"),
	        inverseJoinColumns = @JoinColumn(name = "category_id"))
	    private List<CategoryEntity> categories = new ArrayList<CategoryEntity>();
	
	@PrePersist
    protected void onCreate() {
		product_last_update = product_created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
    	product_last_update = LocalDateTime.now();
    }
}
