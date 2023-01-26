insert into address (city, country, street, street_number) values ('Beograd', 'Serbia', 'Bulevar Mihajla Pupina', 115);
insert into address (city, country, street, street_number) values ('Novi Sad', 'Serbia', 'Djordja Jovanovica', 15);
insert into address (city, country, street, street_number) values ('Novi Sad', 'Serbia', 'Filipa Visnjica', 15);

insert into base_user(name, lastname, username, password, birthday, email, role) values ('Sima', 'Simic', 'simaS', 'sifra123', '2000-08-30', 'sima@test.com', 4);
insert into base_user(name, lastname, username, password, birthday, email, role) values ('Bora', 'Boroje', 'boraB', 'bora123', '2000-04-22', 'bora@test.com', 4);


insert into base_user (name, lastname, username, password, birthday, email, address_id, role) values ('Pera', 'Peric', 'peraP', 'sifra123', '2000-03-19', 'pera@test.com', 1, 1);
insert into base_user (name, lastname, username, password, birthday, email, address_id, role) values ('Marko', 'Markovic', 'markoM', 'sifra123', '2000-03-19', 'marko@test.com', 2, 1);
insert into base_user (name, lastname, username, password, birthday, email, address_id, role) values ('Mika', 'Mikic', 'mikaM', 'sifra123', '2000-03-19', 'mika@test.com', 3, 1);

-- insert into base_user (name, lastname, username, email, password, birthday, role) values ('Pera', 'Peric', 'peraP', 'mika@test.com', 'sifra123', '2000-03-19', 3);
-- insert into base_user (name, lastname, username, email, password, birthday, role) values ('Marko', 'Markovic', 'markoM', 'mika@test.com', 'sifra123', '2000-03-19', 3);
-- insert into base_user (name, lastname, username, email, password, birthday, role) values ('Mika', 'Mikic', 'mikaM', 'mika@test.com', 'sifra123', '2000-03-19', 3);

insert into base_user (name, lastname, username, email, password, birthday, role, validated) values ('Pera', 'Peric', 'peraP', 'mika@test.com', 'sifra123', '2000-03-19', 3, false);
insert into base_user (name, lastname, username, email, password, birthday, role, validated) values ('Marko', 'Markovic', 'markoM', 'mika@test.com', 'sifra123', '2000-03-19', 3, true);
insert into base_user (name, lastname, username, email, password, birthday, role, validated) values ('Mika', 'Mikic', 'mikaM', 'mika@test.com', 'sifra123', '2000-03-19', 3, true);


insert into base_user (name, lastname, username, email, password, birthday, role) values ('Mile', 'Veliki', 'milecar','mile@gmail.com', '1312', '2000-05-03', 2);

insert into blood_amount(a, b, ab, zero) values (10, 11, 12, 9);
insert into blood_amount(a, b, ab, zero) values (16, 21, 12, 9);

insert into blood_center(average_rate, description, name, address_id, blood_amount_id, blood_center_administrator_id) values (9.5, 'asdadsdasdasd', 'Klinika BG', 1, 1, 1);
insert into blood_center(average_rate, description, name, address_id, blood_amount_id, blood_center_administrator_id) values (9.1, 'asdadsdasdasd', 'Klinika NS', 2, 2, 2);

insert into patient_question(description, answer) values ('Have you ever donated blood or blood components voluntarily?', false);
insert into patient_question(description, answer) values ('Have you ever been rejected as a blood or blood component donor?', false);
insert into patient_question(description, answer) values ('Do you currently feel healthy, fit and rested to donate blood or blood components?', false);
insert into patient_question(description, answer) values ('Did you eat anything before coming to donate blood or blood components?', false);
insert into patient_question(description, answer) values ('Do you have a dangerous occupation or hobby?', false);
insert into patient_question(description, answer) values ('Do you take any medications regularly (daily)?', false);
insert into patient_question(description, answer) values ('Have you taken any medicines (eg Brufen, Cafetin, Analgin...) in the last 2-3 days?', false);
insert into patient_question(description, answer) values ('Do you regularly take Aspirin (Cardiopirin)? Have you taken it in the last 5 days?', false);


insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2022-12-01', '11:00:00', 60, 'Nikola', 'Spiric');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2022-12-01', '12:00:00', 60, 'Mihajlo', 'Torbica');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2022-12-02', '10:00:00', 120, 'Boban', 'Legendic');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2022-12-02', '10:00:00', 60, 'Anja', 'Carica');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2022-12-05', '11:00:00', 60, 'Lol', 'Lolic');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2022-12-05', '12:00:00', 60, 'Pera', 'Peric');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2022-12-05', '10:00:00', 120, 'Nole', 'Djokovic');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2022-12-06', '10:00:00', 60, 'Mika', 'Mikic');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2022-12-07', '11:00:00', 60, 'Mica', 'Micic');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2022-12-19', '12:00:00', 60, 'Mihajlo', 'Torbica');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2022-12-19', '10:00:00', 120, 'Boban', 'Bobic');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2022-12-19', '10:00:00', 60, 'Anja', 'Andjelic');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2022-12-06', '10:00:00', 60, 'Mika', 'Mikic');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2023-01-28', '10:00:00', 60, 'Jovan', 'Spiric');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2023-01-28', '12:00:00', 60, 'Mihajlo', 'Torbica');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2023-01-28', '10:00:00', 120, 'Boban', 'Petrovic');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2023-01-28', '11:00:00', 60, 'Milica', 'Micic');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2023-01-29', '10:00:00', 60, 'Nikola', 'Spiric');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2023-01-29', '12:00:00', 60, 'Marko', 'Milanovic');
insert into schedule_calendar(schedule_date, start_time, duration, name, lastname) values ('2023-01-29', '10:00:00', 120, 'Jelena', 'Milenkovic');


insert into send_appeal(to_username, subject, text) values ('peraP', 'Zalba na objekat', 'Mnogo vam....');
insert into send_appeal(to_username, subject, text) values ('mikaM', 'Zalba na osoblje', 'Osoblje vam je neljubazno....');
insert into send_appeal(to_username, subject, text) values ('markoM', 'Zalba na objekat 12', 'Mnogo vam je neuredno....');



