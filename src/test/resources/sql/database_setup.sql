
--CREATE SCHEMA shopzone;
CREATE SCHEMA product_service;

--DROP TABLE IF EXISTS shopzone.product;

--CREATE TABLE shopzone.product (
--  id BIGSERIAL PRIMARY KEY,
--  name TEXT DEFAULT NULL,
--  title TEXT UNIQUE NOT NULL,
--  description TEXT DEFAULT NULL,
--  image TEXT DEFAULT NULL,
--  quantity INTEGER DEFAULT NULL,
--  price numeric(8,2) DEFAULT NULL,
--  category VARCHAR(11) DEFAULT NULL
--);

CREATE TABLE product_service.product (
	id bigserial NOT NULL,
	"name" text NULL,
	title text NOT NULL,
	description text NULL,
	image text NULL,
	quantity int4 NULL,
	price numeric(8, 2) DEFAULT NULL::numeric NULL,
	category varchar(11) DEFAULT NULL::character varying NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id),
	CONSTRAINT product_title_key UNIQUE (title)
);