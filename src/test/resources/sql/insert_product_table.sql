
--INSERT INTO shopzone.product
--(id, "name", title, description, image, quantity, price, category)
--VALUES(nextval('shopzone.product_id_seq'::regclass), 
--'Building Event-Driven Microservices: Leveraging Organizational Data at Scale', 
--'Building-Event-Driven-Microservices-:-Leveraging-Organizational-Data-at-Scale', 
--'Build microservices with event driven architecture', 
--'img_src', 
--5, 
--25.99, 
--NULL::character varying);


INSERT INTO product_service.product
(id, "name", title, description, image, quantity, price, category)
VALUES(1, 
'productName', 
'product-title', 
'productDescription', 
'product-image', 
20, 
12.99, 
'category');