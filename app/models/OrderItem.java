package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem extends Model {
    @Id
    public Long id;
    public int quantity;
    @OneToOne
    public Product product;
}
