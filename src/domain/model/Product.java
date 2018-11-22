package domain.model;

public class Product {
	private String productid;
	private String name;
	private String description;
	private double price;
	
	public Product() {
		
	}
	
	public Product(String productid, String name, String description, double d) {
		setProductid(productid);
		setName(name);
		setDescription(description);
		setPrice(d);
	}
	public Product(String name, String description, double d) {
		setName(name);
		setDescription(description);
		setPrice(d);
	}
	public String getProductid() {
		return productid;
	}

	public void setProductid(String productId) {
		this.productid = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.isEmpty()) {
			throw new DomainException("No name given");
		}
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description.isEmpty()) {
			throw new DomainException("No description given");
		}
		
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price<0) {
			throw new DomainException("Give a valid price");
		}
		this.price = price;
	}

	public void setPrice(String price) {
		if (price.isEmpty()) {
			throw new DomainException("No price given");
		}
		setPrice(Double.valueOf(price));
	}
	
	@Override
	public String toString(){
		return getName() + ": " + getDescription() + " - " + getPrice();
	}
	
}
