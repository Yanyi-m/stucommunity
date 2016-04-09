CREATE TABLE privateMessage
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	sender_uid INT NOT NULL,
	recipient_uid INT NOT NULL
)