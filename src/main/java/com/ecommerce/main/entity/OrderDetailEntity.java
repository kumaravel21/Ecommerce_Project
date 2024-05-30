package com.ecommerce.main.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="orderdetails")
public class OrderDetailEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_details_id")
	private long order_details_id;
	
	@Column(name="order_id", nullable = false)
	private long order_id;
	
	@Column(name="product_id", nullable = false)
	private long product_id;
	
	@Column(name="quantity", nullable = false)
	private int quantity;
	
	@Column(name="price", nullable = false)
	private BigDecimal price;
	
	@Column(name="total_price", nullable = false)
	private BigDecimal total_price;
	
	@Column(name="orderdetails_created", nullable=false)
	private LocalDateTime orderdetails_created;
	
	@Column(name="orderdetails_last_update", nullable=false)
	private LocalDateTime orderdetails_last_update;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private OrderEntity order;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private ProductEntity prod;
	
	@PrePersist
    protected void onCreate() {
		orderdetails_last_update = orderdetails_created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
    	orderdetails_last_update = LocalDateTime.now();
    }
}
