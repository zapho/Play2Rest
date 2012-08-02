package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends Model {
    @Id
    public Long id;

    public String name;

    public long priceInEuroCents;

    public static List<Product> all() {
        return find.all();
    }

    public static Finder<Long,Product> find = new Finder(
            Long.class, Product.class
    );

    public static List<Product> findByName(String name) {
        return find.where().eq("name", name).findList();
    }
}
