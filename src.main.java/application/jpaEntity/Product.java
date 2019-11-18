package application.jpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data //Generates getters/setters and constructors
@Entity
@Table(name = "product")
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

    @Column(name = "name", nullable = false)
	private String name;    

    @Column(name = "description", nullable = false)
	private String description;

    @Column(name = "price", nullable = false)
	private Double price;
    
    public Product() {}
    
    public Product(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

}
