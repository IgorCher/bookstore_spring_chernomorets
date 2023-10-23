INSERT INTO cover_types(cover_type)
VALUES ('SOFT'),
        ('HARD'),
        ('OTHER');
       
INSERT INTO books (author, title, "year", price, pages, isbn, cover_type_id, deleted)
VALUES ('Shakespeare', 'Romeo and Juliet','1911', 100.00, 1510,'9781257800041', (SELECT id FROM cover_types WHERE cover_type = 'SOFT'), FALSE),
		('Melville', 'Moby Dick','1912',20.10, 801,'9781257777441', (SELECT id FROM cover_types WHERE cover_type = 'HARD'), FALSE),
		('Forster', 'A Room with a View','1912', 23.30, 780,'9781257822241', (SELECT id FROM cover_types WHERE cover_type = 'OTHER'), FALSE),
		('Eliot', 'Middlemarch','1941', 16.50, 605,'9781257891111', (SELECT id FROM cover_types WHERE cover_type = 'HARD'), FALSE),
		('Alcott', 'Little Women','1909',9.99, 450,'9781257895321', (SELECT id FROM cover_types WHERE cover_type = 'SOFT'), FALSE),
		('Arnim', 'The Enchanted April','1956',4.50, 1000,'9781257123441', (SELECT id FROM cover_types WHERE cover_type = 'HARD'), FALSE),
		('Montgomery', 'The Blue Castle','1972',19.80, 342,'9781257895541', (SELECT id FROM cover_types WHERE cover_type = 'SOFT'), FALSE),
		('Gaskell', 'Cranford','1970', 25.30, 156,'9781257894781', (SELECT id FROM cover_types WHERE cover_type = 'HARD'), FALSE),
		('Smollett', 'The Expedition of Humphry Clinker','1984', 18.20, 981,'9781257895211', (SELECT id FROM cover_types WHERE cover_type = 'HARD'), FALSE),
		('Dumas', 'Twenty Years After','1957', 34.55, 357, '9781257895221', (SELECT id FROM cover_types WHERE cover_type = 'SOFT'), FALSE),
		('Austen', 'Pride and Prejudice','1956', 31.25, 578,'9781257895111', (SELECT id FROM cover_types WHERE cover_type = 'HARD'), FALSE),
		('Shelley', 'Frankenstein','1919', 21.99, 237,'9781257895440', (SELECT id FROM cover_types WHERE cover_type = 'OTHER'), FALSE),
		('Price', 'Through the gates of the silver key','1974', 10.80, 901,'9781257895400', (SELECT id FROM cover_types WHERE cover_type = 'SOFT'), FALSE),
		('Carroll', 'Alices Adventures in Wonderland','1910', 11.99, 456,'9781257095441', (SELECT id FROM cover_types WHERE cover_type = 'HARD'), FALSE),
		('Marx', 'The Eighteenth Brumaire of Louis Bonaparte','1960', 18.50, 1313,'9781259875441', (SELECT id FROM cover_types WHERE cover_type = 'SOFT'), FALSE),
		('Howard', 'Black Canaan','1961', 22.60, 1234,'9781256545441', (SELECT id FROM cover_types WHERE cover_type = 'HARD'), FALSE),
		('Lucas', 'Wisdom while you wait','1962', 5.30, 1942,'9781257832141', (SELECT id FROM cover_types WHERE cover_type = 'SOFT'), FALSE),
		('Tolstoy', 'War and Peace','1935', 43.40, 311, '9781251235441', (SELECT id FROM cover_types WHERE cover_type = 'OTHER'), FALSE),
		('Stoker', 'Dracula','1934', 11.25, 525,'9781290895441', (SELECT id FROM cover_types WHERE cover_type = 'HARD'), FALSE),
		('Wilde', 'The Picture of Dorian Gray','1957', 15.15, 873,'9781257891941', (SELECT id FROM cover_types WHERE cover_type = 'SOFT'), FALSE),
		('Dickens', 'A Tale of Two Cities','1914', 14.70, 255,'9781257415441', (SELECT id FROM cover_types WHERE cover_type = 'HARD'), FALSE),
		('Doyle', 'The Adventures of Sherlock Holmes','1973', 23.50, 678,'9781232895441', (SELECT id FROM cover_types WHERE cover_type = 'OTHER'), FALSE),
		('Kafka', 'Metamorphosis','1964', 31.60, 1211,'9781257895441', (SELECT id FROM cover_types WHERE cover_type = 'HARD'), FALSE),
		('Dostoyevsky', 'The Brothers Karamazov','1920', 20.50, 500,'9781257195441', (SELECT id FROM cover_types WHERE cover_type = 'HARD'), FALSE),
		('Homer', 'The Iliad ','925', 16.55, 419,'9781257825441', (SELECT id FROM cover_types WHERE cover_type = 'HARD'), FALSE);

INSERT INTO roles("role")
VALUES ('ADMIN'),
        ('MANAGER'),
        ('CUSTOMER');

INSERT INTO users (first_name, last_name, email, "login", "password", role_id, deleted)
VALUES ('Ivan', 'Popov', 'popov@gmail.com', 'ipopov', 'qwerty123',(SELECT id FROM roles WHERE role = 'ADMIN'), FALSE),
        ('Elon', 'Musk', 'musk@gmail.com', 'emusk', 'qazwsxedc741',(SELECT id FROM roles WHERE role = 'MANAGER'), FALSE),
        ('Peter', 'Pan', 'pan@gmail.com', 'ppan', 'lkjhg987',(SELECT id FROM roles WHERE role = 'MANAGER'), FALSE),
        ('Sam', 'Tarly', 'tarly@gmail.com', 'starly', 'qwerty258',(SELECT id FROM roles WHERE role = 'MANAGER'), FALSE),
        ('John', 'Snow', 'snow@gmail.com', 'jsnow', '963qwerty',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Fedor', 'Simmons', 'simmons@gmail.com', 'fsimmons', '987654321!',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Nick', 'Nocturnal', 'nocturnal@gmail.com', 'nnocturnal', 'asdfg456',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Mike', 'Wazowski', 'wazowski@gmail.com', 'mwazowski', '321qwerty',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Duncan', 'McLoud', 'mcloud@gmail.com', 'dmcloud', 'wqerty123',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Jack', 'Sparrow', 'sparrow@gmail.com', 'jsparrow', 'ytrewq321',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Rick', 'Martin', 'martin@gmail.com', 'rmartin', '000qwerty',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Tom', 'Hardy', 'hardy@gmail.com', 'thardy', 'qw321erty',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Harry', 'Potter', 'potter@gmail.com', 'hpotter', '1qwe1rty1',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Bill', 'Murray', 'murray@gmail.com', 'bmurray', '321qwerty13',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Ned', 'Stark', 'stark@gmail.com', 'nstark', 'qwerty',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Alex', 'Smirnov', 'smirnov@gmail.com', 'asmirnov', '!qwerty321',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Simon', 'Tompson', 'tompson@gmail.com', 'stompson', '321@qwerty',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Bred', 'Pitt', 'pitt@gmail.com', 'bpitt', 'qwerty123',(SELECT id FROM roles WHERE role = 'CUSTOMER'), FALSE),
        ('Richard', 'Hammond', 'hammond@gmail.com', 'rhammond', 'qwe654rty',(SELECT id FROM roles WHERE role = 'ADMIN'), FALSE),
        ('James', 'Bond', 'bond@gmail.com', 'jbond', 'qwertymnbvc',(SELECT id FROM roles WHERE role = 'ADMIN'), FALSE);
	
INSERT INTO orders (user_id, "cost", deleted)
VALUES (1, 100.00, FALSE),
		(2, 100.00, FALSE),
		(3, 100.00, FALSE),
		(4, 100.00, FALSE),
		(5, 100.00, FALSE),
		(6, 100.00, FALSE),
		(7, 100.00, FALSE),
		(8, 20.10, FALSE),
		(9, 20.10, FALSE),
		(10, 20.10, FALSE);
		
INSERT INTO order_items (order_id, book_id, quantity, price, deleted)
VALUES (1, 1, 1, (SELECT price FROM books WHERE id = 1), FALSE),
		(2, 1, 1, (SELECT price FROM books WHERE id = 1), FALSE),
		(3, 1, 1, (SELECT price FROM books WHERE id = 1), FALSE),
		(4, 1, 1, (SELECT price FROM books WHERE id = 1), FALSE),
		(5, 1, 1, (SELECT price FROM books WHERE id = 1), FALSE),
		(6, 1, 1, (SELECT price FROM books WHERE id = 1), FALSE),
		(7, 1, 1, (SELECT price FROM books WHERE id = 1), FALSE),
		(8, 2, 1, (SELECT price FROM books WHERE id = 2), FALSE),
		(9, 2, 1, (SELECT price FROM books WHERE id = 2), FALSE),
		(10, 2, 1, (SELECT price FROM books WHERE id = 2), FALSE);
