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

insert into patient_question(description) values ('Have you ever donated blood or blood components voluntarily?');
insert into patient_question(description) values ('Have you ever been rejected as a blood or blood component donor?');
insert into patient_question(description) values ('Do you currently feel healthy, fit and rested to donate blood or blood components?');
insert into patient_question(description) values ('Did you eat anything before coming to donate blood or blood components?');
insert into patient_question(description) values ('Do you have a dangerous occupation or hobby?');
insert into patient_question(description) values ('Do you take any medications regularly (daily)?');
insert into patient_question(description) values ('Have you taken any medicines (eg Brufen, Cafetin, Analgin...) in the last 2-3 days?');
insert into patient_question(description) values ('Do you regularly take Aspirin (Cardiopirin)? Have you taken it in the last 5 days?');

insert into administrator (name, lastname, username, password, birthday) values ('Pera', 'Peric', 'peraP', 'sifra123', '2000-03-19');
insert into administrator (name, lastname, username, password, birthday) values ('Marko', 'Markovic', 'markoM', 'sifra123', '2000-03-19');
insert into administrator (name, lastname, username, password, birthday) values ('Mika', 'Mikic', 'mikaM', 'sifra123', '2000-03-19');
