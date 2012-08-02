# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table customers (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_customers primary key (id))
;

create table orders (
  id                        bigint not null,
  customer_id               bigint not null,
  creation_date             timestamp,
  constraint pk_orders primary key (id))
;

create table order_items (
  id                        bigint not null,
  order_id                  bigint not null,
  quantity                  integer,
  product_id                bigint,
  constraint pk_order_items primary key (id))
;

create table products (
  id                        bigint not null,
  name                      varchar(255),
  price_in_euro_cents       bigint,
  constraint pk_products primary key (id))
;

create sequence customers_seq;

create sequence orders_seq;

create sequence order_items_seq;

create sequence products_seq;

alter table orders add constraint fk_orders_customers_1 foreign key (customer_id) references customers (id) on delete restrict on update restrict;
create index ix_orders_customers_1 on orders (customer_id);
alter table order_items add constraint fk_order_items_orders_2 foreign key (order_id) references orders (id) on delete restrict on update restrict;
create index ix_order_items_orders_2 on order_items (order_id);
alter table order_items add constraint fk_order_items_product_3 foreign key (product_id) references products (id) on delete restrict on update restrict;
create index ix_order_items_product_3 on order_items (product_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists customers;

drop table if exists orders;

drop table if exists order_items;

drop table if exists products;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists customers_seq;

drop sequence if exists orders_seq;

drop sequence if exists order_items_seq;

drop sequence if exists products_seq;

