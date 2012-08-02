package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

import static play.data.validation.Constraints.Required;

@Entity
@Table(name = "customers")
public class Customer extends Model {

    @Id
    public Long id;

    @Required
    public String name;

    @OneToMany(cascade= CascadeType.ALL)
    public List<Order> orderList;

    public static List<Customer> all() {
        return find.all();
    }

    public static Finder<Long,Customer> find = new Finder(
            Long.class, Customer.class
    );
}
