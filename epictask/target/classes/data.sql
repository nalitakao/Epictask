CREATE TABLE task (
	id bigint PRIMARY KEY auto_increment,
	title varchar(200),
	description TEXT,
	points int
);


INSERT INTO task (title, description, points) VALUES 
	('Criar banco de dados', 'Criar um banco de dados na nuvem com Oracle', 200);
	
INSERT INTO task (title, description, points) VALUES 
	('Modelagem de telas', 'Criação de protótipo de alta fidelidade', 100);
	
INSERT INTO task (title, description, points) VALUES 
	('API REST', 'Publicação de API com endpoints da aplicação', 150);

	
CREATE TABLE signin (
	id bigint PRIMARY KEY auto_increment,
	username varchar(200),
	email varchar(200),
	password varchar(200)
);

INSERT INTO signin (username, email, password) VALUES 
	('Nalita Kao', 'nalitakao@gmail.com', 'nalitakao812');
	
INSERT INTO signin (username, email, password) VALUES 
	('Micaela Mota', 'mikamtsts@gmail.com', 'micaela482');