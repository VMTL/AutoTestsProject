package application.soap.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import application.jpaEntity.ProductService;
import application.jpaEntity.Product;

import com.application.soap.products.*;

@Endpoint
public class ProductsEndpoint {

	public static final String NAMESPACE_URI = "http://application.com/soap/products";

	private ProductService productService;
	
	public ProductsEndpoint() {
	}
	
	@Autowired
	public ProductsEndpoint(ProductService productService) {
		this.productService = productService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetProductRequest")
	@ResponsePayload
	public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
		GetProductResponse response = new GetProductResponse();
		Product product = productService.getOne(request.getId());
		System.out.println("A product found = " + product.getName() + " " +product.getDescription() + " " + product.getPrice());
		response.setProduct(mapProduct(product));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
	@ResponsePayload
	public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
		GetAllProductsResponse response = new GetAllProductsResponse();
		List<Product> productList = productService.findAll();		
		response.getProduct().addAll(mapProductList(productList));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addProductRequest")
	@ResponsePayload
	public AddProductResponse addProduct(@RequestPayload AddProductRequest request) {
		AddProductResponse response = new AddProductResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		Product product = new Product(request.getName(), request.getDescription(), request.getPrice());
		
		Product productSaved = productService.save(product);

		if (productSaved == null) {
			serviceStatus.setStatusCode("CONFLICT");
			serviceStatus.setMessage("Exception while adding Entity");
		}
		else {
			response.setProduct(mapProduct(productSaved));
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Product Added Successfully");
		}

		response.setServiceStatus(serviceStatus);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductRequest")
	@ResponsePayload
	public UpdateProductResponse updateProduct(@RequestPayload UpdateProductRequest request) {
		UpdateProductResponse response = new UpdateProductResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		Product product = productService.getOne(request.getId());

		if (product == null) {
			serviceStatus.setStatusCode("Product Not Found");
			serviceStatus.setMessage("Product " + request.getId() + " Not Found");
		}
		else {
			product.setName(request.getName());
			product.setDescription(request.getDescription());
			product.setPrice(request.getPrice());
			
			Product productSaved = productService.save(product);
			
			if(productSaved == null) {
				serviceStatus.setStatusCode("CONFLICT");
				serviceStatus.setMessage("Exception while updating Product Entity ID=" + request.getId());
			}
			else {
				serviceStatus.setStatusCode("SUCCESS");
				response.setProduct(mapProduct(productSaved));
				serviceStatus.setMessage("A product found = " + product.getName() + " " + product.getDescription() + " "
											+ product.getPrice() + " Updated Successfully");
			}
		}
		response.setServiceStatus(serviceStatus);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductRequest")
	@ResponsePayload
	public DeleteProductResponse deleteProduct(@RequestPayload DeleteProductRequest request) {
		DeleteProductResponse response = new DeleteProductResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		
		boolean flag = productService.deleteById(request.getId());

		if (flag == false) {
			serviceStatus.setStatusCode("FAIL to delete");
			serviceStatus.setMessage("Exception while deleting a Product Entity");
		}
		else {
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Product Deleted Successfully");
		}

		response.setServiceStatus(serviceStatus);
		return response;
	}
	
	private com.application.soap.products.Product mapProduct(Product product){
		com.application.soap.products.Product productXsd = new com.application.soap.products.Product();
		
		productXsd.setId(product.getId());
		productXsd.setName(product.getName());
		productXsd.setDescription(product.getDescription());
		productXsd.setPrice(product.getPrice());
		
		return productXsd;
	}
	
	private List<com.application.soap.products.Product> mapProductList(List<Product> productList){
		List<com.application.soap.products.Product> productXsdList = new ArrayList<com.application.soap.products.Product>();
		
		for(Product product : productList) {
			com.application.soap.products.Product productXsd = new com.application.soap.products.Product();
		
			productXsd.setId(product.getId());
			productXsd.setName(product.getName());
			productXsd.setDescription(product.getDescription());
			productXsd.setPrice(product.getPrice());
			productXsdList.add(productXsd);
		}
		
		return productXsdList;
	}
}