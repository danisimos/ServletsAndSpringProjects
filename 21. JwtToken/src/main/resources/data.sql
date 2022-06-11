insert into author(first_name, last_name, state) values ('Автор 1 - имя', 'Автор 1 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 2 - имя', 'Автор 2 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 3 - имя', 'Автор 3 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 4 - имя', 'Автор 4 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 5 - имя', 'Автор 5 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 6 - имя', 'Автор 6 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('kjsenfjksАвтор 7 - имя', 'Автор 7 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('33333Автор 8 - имя', 'Автор 8 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 9 - имя', 'Автор 9 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 10 - имя', 'Автор 10 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 11 - имя', 'Автор 11 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 12 - имя', 'Автор 12 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 13 - имя', 'Автор 13 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 14 - имя', 'Автор 14 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 15 - имя', 'Автор 15 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 16 - имя', 'Автор 16 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 17 - имя', 'Автор 17 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 18 - имя', 'Автор 18 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 19 - имя', 'Автор 19 - фамилия', 'ACTIVE');
insert into author(first_name, last_name, state) values ('Автор 20 - имя', 'Автор 20 - фамилия', 'DELETED');


insert into post(state, text, title, author_id) values ('DRAFT', 'some text', 'some title', 1);
insert into post(state, text, title, author_id) values ('PUBLISHED', 'some another text', 'some another title', 1);
insert into post(state, text, title, author_id) values ('DELETED', 'some another another text', 'awesome title', 14);

insert into account(first_name, last_name, email, password, state, role) values ('Daniil', 'Korotaev', 'daniil.korotaev@gmail.com', '$2a$12$FpasYIY.ryd8x/RaeHEWCeNuNtVqFeyB3vegYi48eY/pydqOPTPOK', 'CONFIRMED', 'USER');