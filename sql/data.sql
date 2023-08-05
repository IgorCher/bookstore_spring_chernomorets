INSERT INTO cover_types(cover_type)
VALUES ('SOFT'),
        ('HARD'),
        ('OTHER');
       
INSERT INTO books (author, title, year, price, pages, isbn, cover_type_id)
VALUES ('Shakespeare', 'Romeo and Juliet','1911', 10.20, 1510,'9781257800041', (SELECT id FROM cover_types WHERE cover_type = 'SOFT')),
		('Melville', 'Moby Dick','1912',20.10, 801,'9781257777441', (SELECT id FROM cover_types WHERE cover_type = 'HARD')),
		('Forster', 'A Room with a View','1912', 23.30, 780,'9781257822241', (SELECT id FROM cover_types WHERE cover_type = 'OTHER')),
		('Eliot', 'Middlemarch','1941', 16.50, 605,'9781257891111', (SELECT id FROM cover_types WHERE cover_type = 'HARD')),
		('Alcott', 'Little Women','1909',9.99, 450,'9781257895321', (SELECT id FROM cover_types WHERE cover_type = 'SOFT')),
		('Arnim', 'The Enchanted April','1956',4.50, 1000,'9781257123441', (SELECT id FROM cover_types WHERE cover_type = 'HARD')),
		('Montgomery', 'The Blue Castle','1972',19.80, 342,'9781257895541', (SELECT id FROM cover_types WHERE cover_type = 'SOFT')),
		('Gaskell', 'Cranford','1970', 25.30, 156,'9781257894781', (SELECT id FROM cover_types WHERE cover_type = 'HARD')),
		('Smollett', 'The Expedition of Humphry Clinker','1984', 18.20, 981,'9781257895211', (SELECT id FROM cover_types WHERE cover_type = 'HARD')),
		('Dumas', 'Twenty Years After','1957', 34.55, 357, '9781257895221', (SELECT id FROM cover_types WHERE cover_type = 'SOFT')),
		('Austen', 'Pride and Prejudice','1956', 31.25, 578,'9781257895111', (SELECT id FROM cover_types WHERE cover_type = 'HARD')),
		('Shelley', 'Frankenstein','1919', 21.99, 237,'9781257895440', (SELECT id FROM cover_types WHERE cover_type = 'OTHER')),
		('Price', 'Through the gates of the silver key','1974', 10.80, 901,'9781257895400', (SELECT id FROM cover_types WHERE cover_type = 'SOFT')),
		('Carroll', 'Alices Adventures in Wonderland','1910', 11.99, 456,'9781257095441', (SELECT id FROM cover_types WHERE cover_type = 'HARD')),
		('Marx', 'The Eighteenth Brumaire of Louis Bonaparte','1960', 18.50, 1313,'9781259875441', (SELECT id FROM cover_types WHERE cover_type = 'SOFT')),
		('Howard', 'Black Canaan','1961', 22.60, 1234,'9781256545441', (SELECT id FROM cover_types WHERE cover_type = 'HARD')),
		('Lucas', 'Wisdom while you wait','1962', 5.30, 1942,'9781257832141', (SELECT id FROM cover_types WHERE cover_type = 'SOFT')),
		('Tolstoy', 'War and Peace','1935', 43.40, 311, '9781251235441', (SELECT id FROM cover_types WHERE cover_type = 'OTHER')),
		('Stoker', 'Dracula','1934', 11.25, 525,'9781290895441', (SELECT id FROM cover_types WHERE cover_type = 'HARD')),
		('Wilde', 'The Picture of Dorian Gray','1957', 15.15, 873,'9781257891941', (SELECT id FROM cover_types WHERE cover_type = 'SOFT')),
		('Dickens', 'A Tale of Two Cities','1914', 14.70, 255,'9781257415441', (SELECT id FROM cover_types WHERE cover_type = 'HARD')),
		('Doyle', 'The Adventures of Sherlock Holmes','1973', 23.50, 678,'9781232895441', (SELECT id FROM cover_types WHERE cover_type = 'OTHER')),
		('Kafka', 'Metamorphosis','1964', 31.60, 1211,'9781257895441', (SELECT id FROM cover_types WHERE cover_type = 'HARD')),
		('Dostoyevsky', 'The Brothers Karamazov','1920', 20.50, 500,'9781257195441', (SELECT id FROM cover_types WHERE cover_type = 'HARD')),
		('Homer', 'The Iliad ','925', 16.55, 419,'9781257825441', (SELECT id FROM cover_types WHERE cover_type = 'HARD'));

		INSERT INTO roles(role)
        VALUES ('ADMIN'),
                ('MANAGER'),
                ('CUSTOMER');

		INSERT INTO users ("name", last_name, email, "login", "password", role_id)
		VALUES ('Ivan', 'Popov', 'popov@gmail.com', 'ipopov', 'qwerty123',(SELECT id FROM roles WHERE role = 'ADMIN')),
		('Elon', 'Musk', 'musk@gmail.com', 'emusk', 'qazwsxedc741',(SELECT id FROM roles WHERE role = 'MANAGER')),
		('Peter', 'Pan', 'pan@gmail.com', 'ppan', 'lkjhg987',(SELECT id FROM roles WHERE role = 'MANAGER')),
		('Sam', 'Tarly', 'tarly@gmail.com', 'starly', 'qwerty258',(SELECT id FROM roles WHERE role = 'MANAGER')),
		('John', 'Snow', 'snow@gmail.com', 'jsnow', '963qwerty',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Fedor', 'Simmons', 'simmons@gmail.com', 'fsimmons', '987654321!',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Nick', 'Nocturnal', 'nocturnal@gmail.com', 'nnocturnal', 'asdfg456',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Mike', 'Wazowski', 'wazowski@gmail.com', 'mwazowski', '321qwerty',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Duncan', 'McLoud', 'mcloud@gmail.com', 'dmcloud', 'wqerty123',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Jack', 'Sparrow', 'sparrow@gmail.com', 'jsparrow', 'ytrewq321',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Rick', 'Martin', 'martin@gmail.com', 'rmartin', '000qwerty',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Tom', 'Hardy', 'hardy@gmail.com', 'thardy', 'qw321erty',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Harry', 'Potter', 'potter@gmail.com', 'hpotter', '1qwe1rty1',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Bill', 'Murray', 'murray@gmail.com', 'bmurray', '321qwerty13',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Ned', 'Stark', 'stark@gmail.com', 'nstark', 'qwerty',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Alex', 'Smirnov', 'smirnov@gmail.com', 'asmirnov', '!qwerty321',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Simon', 'Tompson', 'tompson@gmail.com', 'stompson', '321@qwerty',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Bred', 'Pitt', 'pitt@gmail.com', 'bpitt', 'qwerty123',(SELECT id FROM roles WHERE role = 'CUSTOMER')),
		('Richard', 'Hammond', 'hammond@gmail.com', 'rhammond', 'qwe654rty',(SELECT id FROM roles WHERE role = 'ADMIN')),
		('James', 'Bond', 'bond@gmail.com', 'jbond', 'qwertymnbvc',(SELECT id FROM roles WHERE role = 'ADMIN'));
