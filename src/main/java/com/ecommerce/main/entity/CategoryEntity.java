package com.ecommerce.main.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name="category")
public class CategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="category_id")
	private long category_id;
	
	@Column(name="category_name", nullable = false)
	private String category_name;
	
	@Column(name="description", nullable = false)
	private String description;
	
	@ManyToMany(mappedBy = "categories")
    private Set<ProductEntity> products = new HashSet<ProductEntity>();

}
