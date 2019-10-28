package tests.integration;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import helpers.JSONparser;
import helpers.Read_Excel;
import restAPIcrud.Application;
import restAPIcrud.jpaEntity.Product;
import restAPIcrud.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class RepositoryTest extends AbstractTestNGSpringContextTests{

	@Autowired
    private ProductRepository productRepositary;
	
	private Read_Excel readExcel;
	private Long id = Long.valueOf(1);
	
	public RepositoryTest() {
		this.readExcel = new Read_Excel(System.getProperty("user.dir") + "\\resources\\Test_Cases.xlsx");
	}

    @Test(description="Check initial products list size")
    public void testFindAll() {
        List<Product> products = productRepositary.findAll();
        assertTrue(products.size() == 0);
    }
    
    @Test(description="Check no products exist initially")
    public void testFindById() {
        assertTrue(productRepositary.findById(Long.valueOf(1)).isPresent() == false);
    }
    
    @Test(description="Check a new product added", dataProvider="products", priority=1)
    public void testAddProduct(String product) {
    	JsonObject json = new JSONparser().jsonObjFromString(product);
    	Product prod = new Product();
    	prod.setName(json.get("name").getAsString());
    	prod.setDescription(json.get("description").getAsString());
    	prod.setPrice(json.get("price").getAsDouble());
    	productRepositary.save(prod);
    	Product productToAssert = productRepositary.findById(this.id).get();
        this.id++;
        assertTrue(productToAssert.getName().equals(json.get("name").getAsString()));
        assertTrue(productToAssert.getDescription().equals(json.get("description").getAsString()));
        assertTrue(productToAssert.getPrice().toString().equals(json.get("price").getAsString()));
    }
    @DataProvider(name="products")
    public Object[] dataProviderMethod() {
        return readExcel.getExcelDataSimpleArray("products");
    }
    
    @Test(description="Delete an exsiting product found by ID", priority=2)
    public void testDeleteProduct() {
    	productRepositary.deleteById(Long.valueOf(1));
    	assertTrue(productRepositary.findById(Long.valueOf(1)).isPresent() == false);
    }
    
    @Test(description="Update an existing product found by ID", priority=2)
    public void testUpdateProduct() {
    	Product prod = productRepositary.findById(Long.valueOf(2)).get();
    	prod.setName("dock station");
    	productRepositary.save(prod);
    	assertTrue(productRepositary.findById(Long.valueOf(2)).get().getName().equals("dock station"));
    }
}