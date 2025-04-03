CREATE TABLE IF NOT EXISTS users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts(
    id INT AUTO_INCREMENT PRIMARY KEY,
    currency ENUM('PLN', 'EUR', 'USD') NOT NULL,
    balance DOUBLE,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);