CREATE TABLE IF NOT EXISTS cover_types
    (
            id SERIAL PRIMARY KEY,
            cover_type VARCHAR (256)
        );


CREATE TABLE IF NOT EXISTS books
    (
        id BIGSERIAL PRIMARY KEY,
        author VARCHAR(256) NOT NULL,
        title VARCHAR (256) NOT NULL,
        year VARCHAR (4),
        price NUMERIC (10,2) NOT NULL,
        pages INT,
        isbn CHAR (13) UNIQUE NOT NULL,
        cover_type_id INT REFERENCES cover_types
    );

    CREATE TABLE IF NOT EXISTS roles
        (
                id SERIAL PRIMARY KEY,
                role VARCHAR (256)
            );

    CREATE TABLE IF NOT EXISTS users
        (
            id BIGSERIAL PRIMARY KEY,
            "name" VARCHAR(256),
            last_name VARCHAR (256),
            email VARCHAR (256) UNIQUE NOT NULL,
            "login" VARCHAR (256) NOT NULL,
            "password" VARCHAR (256) NOT NULL,
            role_id INT REFERENCES roles
        );
       
	CREATE TABLE IF NOT EXISTS orders
		(
			id BIGSERIAL PRIMARY KEY,
			user_id BIGINT REFERENCES users,
			"cost" NUMERIC(10,2)
		)
		
	CREATE TABLE IF NOT EXISTS order_items
		(
			id BIGSERIAL PRIMARY KEY,
			order_id BIGINT REFERENCES orders,
			book_id BIGINT REFERENCES books,
			quantity INT,
			price NUMERIC(10,2)
		)
		
		