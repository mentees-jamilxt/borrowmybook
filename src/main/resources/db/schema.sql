CREATE TABLE IF NOT EXISTS BOOK_CATEGORY(
    category_id SERIAL PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL,
    category_desc VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS BOOK(
    book_id SERIAL PRIMARY KEY,
    book_name VARCHAR(500) NOT NULL,
    book_author VARCHAR(500) NOT NULL,
    book_price DECIMAL(10,2),
    book_publisher VARCHAR(500),
    book_edition VARCHAR(10),
    book_Status VARCHAR(5),
    book_isbn_num VARCHAR(100),
    book_category INTEGER REFERENCES BOOK_CATEGORY(category_id)
);