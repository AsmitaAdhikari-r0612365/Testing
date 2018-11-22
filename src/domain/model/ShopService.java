package domain.model;

import domain.db.*;

import java.util.List;
import java.util.Properties;

public class ShopService {
    private PersonDb personDb;
    private ProductDb productDb;

    public ShopService(Properties properties) throws ClassNotFoundException {
        personDb = new PersonDbSQL(properties);
        productDb = new ProductDbSQL(properties);
    }

    public Person getPerson(String personId) {
        return getPersonDb().get(personId);
    }

    public List<Person> getPersons() {
        return getPersonDb().getAll();
    }

    public void addPerson(Person person) {
        getPersonDb().add(person);
    }

    public void updatePersons(Person person) {
        getPersonDb().update(person);
    }

    public void deletePerson(String id) {
        getPersonDb().delete(id);
    }

    public void sortPerson(String id){ getPersonDb().sort(id);}

    private PersonDb getPersonDb() {
        return personDb;
    }

    /*****PRODUCT******/

    public List<Product> getProducts() {
        return productDb.getAll();
    }

    public void addProduct(Product product){
        getProductDb().add(product);
    }

    public Product getProduct(String productId){ return getProductDb().get(productId);}

    public void deleteProduct(String productId){  getProductDb().delete(productId);}

    public void updateProduct(Product product){ getProductDb().update(product);}

    private ProductDb getProductDb() {
        return productDb;
    }

}
