package domain.db;

import domain.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDbInMemory implements ProductDb {
	private Map<String, Product> records = new HashMap<>();
	
	public ProductDbInMemory () {
		Product rose = new Product("Rose", "Thorny plant", 2.25);
		add(rose);
	}
	
	public Product get(String id){
		if(id==null){
			throw new DbException("No valid id given");
		}
		return records.get(id);
	}
	
	public List<Product> getAll(){
		return new ArrayList<Product>(records.values());	
	}

	public void add(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		//int id = records.size() + 1;
		//product.setProductId(id);
		if (records.containsKey(product.getProductid())) {
			throw new DbException("Product already exists");
		}
		records.put(product.getProductid(), product);
	}
	
	public void update(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		if(!records.containsKey(product.getProductid())){
			throw new DbException("No product found");
		}
		records.put(product.getProductid(), product);
	}
	
	public void delete(String id){
		if(id==null){
			throw new DbException("No valid id given");
		}
		records.remove(id);
	}

	public int getNumbeOfProducts() {
		return records.size();
	}
}
