CREATE TABLE users (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(100) NOT NULL,
    mail VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE whisky (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    name VARCHAR(100),
    taste VARCHAR(100),
    drinking_style VARCHAR(100),
    price INT,
    memo TEXT,
    is_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE rating (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    whisky_id INT NOT NULL,
    rating INT,
    rating_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE
);
