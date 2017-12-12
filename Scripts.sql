CREATE DATABASE appdirect
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;


CREATE TABLE PRODUCT
(
  "product_id" character varying(255) NOT NULL,
  "product_name" character varying(255) NOT NULL,
  price numeric,
  "description"  character varying(255),
  create_timestamp timestamp,
  update_timestamp timestamp default current_timestamp,
  CONSTRAINT "product_id_key" PRIMARY KEY ("product_id")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE PRODUCT OWNER TO postgres;


CREATE TABLE STORE
(
  "store_id" character varying(255) NOT NULL,  
  "store_name" character varying(255) NOT NULL,
  "description"  character varying(255),
  create_timestamp timestamp,
  update_timestamp timestamp default current_timestamp,
  CONSTRAINT "store_id_key" PRIMARY KEY ("store_id")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE PRODUCT OWNER TO postgres;

CREATE TABLE PRICING
(
  pricing_id character varying(255) NOT NULL,
  store_id character varying(255) NOT NULL,
  product_id character varying(255) NOT NULL,
  price numeric NOT NULL, 
  "notes"  character varying(255),
  create_timestamp timestamp,
  update_timestamp timestamp DEFAULT current_timestamp,
  CONSTRAINT pricing_id_key PRIMARY KEY (pricing_id),
  CONSTRAINT product_id_fk FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT store_id_fk FOREIGN KEY (store_id)
      REFERENCES store (store_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE PRICING
  OWNER TO postgres;

CREATE TABLE rules
(
  rule_id character varying NOT NULL,
  rule character varying NOT NULL, -- Actual json rule string is stored
  create_timestamp timestamp DEFAULT current_timestamp,
  update_timestamp timestamp DEFAULT current_timestamp
)
WITH (
  OIDS=FALSE
);
ALTER TABLE rules
  OWNER TO postgres;
COMMENT ON COLUMN rules.rule IS 'Actual json rule string is stored';


CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
INSERT INTO rules(rule_id, rule) VALUES (uuid_generate_v4(), '{"LIST_MODIFIER":"2,-2","AVERAGE_CALCULATOR":"","PERCENTAGE_CALCULATOR":"20"}');
