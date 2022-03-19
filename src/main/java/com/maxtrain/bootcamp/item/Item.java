package com.maxtrain.bootcamp.item;

import javax.persistence.*;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=30, nullable=false)
	private String name;
	@Column(columnDefinition="decimal(11,2) not null")
	private double itemLimit;
	
	public Item() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getItemLimit() {
		return itemLimit;
	}

	public void setItemLimit(double itemLimit) {
		this.itemLimit = itemLimit;
	}	

}
