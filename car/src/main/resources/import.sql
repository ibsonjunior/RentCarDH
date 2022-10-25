
--Categories
INSERT INTO category (qualification, description, image) VALUES ('Elétrico','Veículos ideias para quem se preocupa com um mundo sustentável','https://www.localiza.com/brasil-site/geral/Frota/E208.png');
INSERT INTO category (qualification, description, image) VALUES ('Sedan','Veículos espaçosos ideais para negócios','https://www.localiza.com/brasil-site/geral/Frota/CORO.png');
INSERT INTO category (qualification, description, image) VALUES ('SUV','Veículos que costumam ter porte avantajado, além de interior espaçoso e possibilidade de trafegar dentro e fora da cidade.','https://www.localiza.com/brasil-site/geral/Frota/RE4D.png');
INSERT INTO category (qualification, description, image) VALUES ('Executivos','Veículos pensados estrategicamente para otimizar a rotina de um profissional','https://www.localiza.com/brasil-site/geral/Frota/NUNS.png');

--Cars
INSERT INTO car (title, location, img, description, price) VALUES ('Fiat Uno','Recife','https://www.localiza.com/brasil-site/geral/Frota/NUNS.png', 'Veículo com baixo consumo de combustível', 100.00);
INSERT INTO car (title, location, img, description, price) VALUES ('Uno','Recife','https://www.localiza.com/brasil-site/geral/Frota/NUNS.png', 'Veículo com baixo consumo de combustível', 1000.00);
INSERT INTO car (title, location, img, description, price) VALUES ('Corsa','Recife','https://www.localiza.com/brasil-site/geral/Frota/NUNS.png', 'Veículo com baixo consumo de combustível', 10.00);

-- User
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');