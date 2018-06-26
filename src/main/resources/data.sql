
-- category
INSERT INTO "category" ("id", "name", "description")
SELECT 0, 'Sport', 'Sports equipment'
WHERE NOT EXISTS ( SELECT * FROM category WHERE id = 0 and name = 'Sport' );

INSERT INTO "category" ("id", "name", "description")
SELECT 1, 'Toys', 'Toys for kids'
WHERE NOT EXISTS ( SELECT * FROM category WHERE id = 1 and name = 'Toys' );

INSERT INTO "category" ("id", "name", "description")
SELECT 2, 'Books', 'Classic, fantastic, journey'
WHERE NOT EXISTS ( SELECT * FROM category WHERE id = 2 and name = 'Books' );

INSERT INTO "category" ("id", "name", "description")
SELECT 3, 'Cars', 'Sport cars and heavy transport'
WHERE NOT EXISTS ( SELECT * FROM category WHERE id = 3 and name = 'Cars' );

INSERT INTO "category" ("id", "name", "description")
SELECT 4, 'Music', 'Musical instruments, expendable material'
WHERE NOT EXISTS ( SELECT * FROM category WHERE id = 4 and name = 'Music' );

-- goods
INSERT INTO "goods" ("count", "name", "description", "price", "category_id") VALUES
	(3, 'Football ball', 'The ball is made from natural leather', 20.5, 0),
	(2, 'Bike', 'Sport bike. He has 26 inch wheels and disk brakes', 600, 0),
	(8, 'Thermal underware', 'Seamless underware. His comfort temperature -20',	28.9, 0),
	(1, 'Basketball ball', 'It is orange ball, looks pretty', 22, 0),
	(5, 'Kit tennis racket + 3 balls', 'The tennis racket is made of beech. She has ergonomic handle', 9, 0),
	(9, 'Hoop', 'The red hoop', 5.2, 0),
	(2, 'Rock shoes', 'Shoes for climber для скалолазов. High quality', 17, 0),
	(3, 'Ascender', 'Mechanism for rope lifting', 24.4, 0),
	(3, 'Snowboard', 'Snowboard middle rigidness', 34.7, 0),
	(5, 'Tracking sticks', 'Sticks for walking for difficult terrain', 18, 0),
	(12, 'Doll', 'Entertain baby', 4, 1),
	(4, 'Рitchhikers guide to the galaxy', 'Douglas Adams book', 7.1, 2),
	(1, 'Porsch', 'Famous Porsch. Do you need more description?', 666666, 3),
	(1, 'Violin', 'Says Stradivari plays on that violin', 999999, 4);