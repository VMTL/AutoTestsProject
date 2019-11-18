package application.jpaEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;
	
	public ProductService(){
		
	}
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }
    
    public Product getOne(Long id) {
        return this.productRepository.findById(id).get();
    }

    public Product save(Product stock) {
    	System.out.println("a product created / updated");
        return this.productRepository.save(stock);
    }

    public boolean deleteById(Long id) {
    	try {
        	this.productRepository.deleteById(id);
        	System.out.println("A product " + id + " was deleted");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
