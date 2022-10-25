
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
--INSERT INTO usuario (name, lastname, login, password) VALUES ('aila','torres','aila@gmail.com', '312456789');
INSERT INTO AppUser (name, username, email, password, appUserRoles ) VALUES ('aila','torres','aila@gmail.com', '312456789', 'ROLE_USER');

--  private String name;
--    private String username;
--    private String email;
--    private String password;
--    @Enumerated(EnumType.STRING)
--    private AppUserRoles appUserRoles;

INSERT INTO car_category (id_car, id_category) VALUES (1,1);
INSERT INTO car_category (id_car, id_category) VALUES (2,2);
INSERT INTO car_category (id_car, id_category) VALUES (3,3);
