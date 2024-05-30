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
import jakarta.persistence.ManyToOne;
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
@Table(name="order")
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_id")
	private long order_id;
	
	@Column(name="user_id")
	private long user_id;

	@Column(name="order_date")
	private LocalDateTime order_date;
	
	@Column(name="order_status", nullable=false)
	private String order_status;
	
	@Column(name="order_total_amount", nullable=false)
	private BigDecimal order_total_amount;

	@Column(name="shipping_address", nullable=false)
	private String shipping_address;
	
	@Column(name="billing_address", nullable=false)
	private String billing_address;
	
	@Column(name="order_created")
	private LocalDateTime order_created;
	
	@Column(name="order_last_update")
	private LocalDateTime order_last_update;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserEntity userOrder;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderDetailEntity> orderDetails = new ArrayList<OrderDetailEntity>();
	
	@PrePersist
    protected void onCreate() {
		order_last_update = order_created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
    	order_last_update = LocalDateTime.now();
    }
}
