package com.cg.bookstore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import java.util.List;

import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

//import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
	// Fields
		@Id 
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int orderDetailsId;

		@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE , CascadeType.REFRESH})
		@JoinColumn(name="book_id_fk")
		@OnDelete(action = OnDeleteAction.NO_ACTION)
		private Book book;
		
		@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE , CascadeType.REFRESH})
		@JoinColumn(name="order_id_fk")
		@OnDelete(action = OnDeleteAction.NO_ACTION)
		private BookOrder bookOrder; 
		
		@NotNull(message="Provide the quantity for your order")
		@Min(value=1, message="Quantity has to be more than 0")
		private Integer quantity;
		
		private double orderTotal;
		private String deliveryStatus="Order Placed";
	
}