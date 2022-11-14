insert into address (city, country, street, street_number) values ('Beograd', 'Serbia', 'Bulevar Mihajla Pupina', 115);
insert into address (city, country, street, street_number) values ('Novi Sad', 'Serbia', 'Djordja Jovanovica', 15);
insert into address (city, country, street, street_number) values ('Novi Sad', 'Serbia', 'Filipa Visnjica', 15);

insert into registered_user (name, lastname, username, password, birthday, role, email, address_id) values ('Pera', 'Peric', 'peraP', 'sifra123', '2000-03-19', 'Administrator', 'pera@test.com', 1);
insert into registered_user (name, lastname, username, password, birthday, role, email, address_id) values ('Marko', 'Markovic', 'markoM', 'sifra123', '2000-03-19', 'RegisteredUser', 'marko@test.com', 2);
insert into registered_user (name, lastname, username, password, birthday, role, email, address_id) values ('Mika', 'Mikic', 'mikaM', 'sifra123', '2000-03-19', 'Employee', 'mika@test.com', 3);

insert into blood_amount(a, b, ab, zero) values (10, 11, 12, 9);
insert into blood_amount(a, b, ab, zero) values (16, 21, 12, 9);

insert into blood_center(average_rate, description, name, address_id, blood_amount_id) values (9.5, 'asdadsdasdasd', 'Klinika BG', 1, 1);
insert into blood_center(average_rate, description, name, address_id, blood_amount_id) values (9.1, 'asdadsdasdasd', 'Klinika NS', 2, 2);