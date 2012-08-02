package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends Model {
    @Id
    public Long id;

    public Date creationDate;

    @OneToMany(cascade= CascadeType.ALL)
    public List<OrderItem> orderList;
}
