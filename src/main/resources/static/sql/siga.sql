CREATE DATABASE siga;
USE siga;

CREATE TABLE aluno(
	ra VARCHAR(10) PRIMARY KEY,
	nome VARCHAR(MAX)
)


INSERT INTO aluno(ra, nome) VALUES
('AAA0', 'Maycon'),
('AAA1', 'Felipe'),
('AAA2', 'Mendoza'),
('AAA3', 'Huanca');


SELECT * FROM aluno;


DROP TABLE disciplina;

CREATE TABLE disciplina(
	codigo VARCHAR(10) PRIMARY KEY,
	nome VARCHAR(MAX),
	sigla VARCHAR(10),
	turno VARCHAR(MAX),
	num_aulas INT
)

INSERT INTO disciplina(codigo, nome, sigla, turno, num_aulas) VALUES
('4203-010', 'Arquitetura e Organização de Computadores', 'OAC', 'tarde', 80),
('4203-020', 'Arquitetura e Organização de Computadores', 'OAC', 'noite', 80),

('4208-010', 'Laboratorio de Hardware', 'LH', 'tarde', 80),

('4226-004', 'Banco de Dados', 'BD', 'tarde', 80),

('4213-003', 'Sistemas Operacionais 1', 'SO1', 'tarde', 80),
('4213-013', 'Sistemas Operacionais 1', 'SO1', 'noite', 80),

('4233-005', 'Laboratorio de Banco de Dados', 'LBD', 'tarde', 80),

('5005-220', 'Metodos para a producao de conhecimento', 'MPC', 'tarde', 40);


SELECT * FROM disciplina;

CREATE TABLE faltas(
	ra_aluno VARCHAR(10) FOREIGN KEY REFERENCES aluno(ra),
	codigo_disciplina VARCHAR(10) FOREIGN KEY REFERENCES disciplina(codigo),
	data DATE,
	presenca INT,
	PRIMARY KEY(ra_aluno, codigo_disciplina, data)
)

CREATE TABLE avaliacao(
	codigo  INT PRIMARY KEY,
	tipo VARCHAR(MAX)
)

INSERT INTO avaliacao (codigo, tipo)VALUES
(0, 'PRE-EXAME'),
(1, 'P1'),
(2, 'P2'),
(3, 'P3'),
(4, 'T'),
(5, 'MONOGRAFIA COMPLETA'),
(6, 'MONOGRAFIA RESUMIDA')

CREATE TABLE notas(
	ra_aluno VARCHAR(10) FOREIGN KEY REFERENCES aluno(ra),
	codigo_disciplina VARCHAR(10) FOREIGN KEY REFERENCES disciplina(codigo),
	codigo_avaliacao INT FOREIGN KEY REFERENCES avaliacao(codigo),
	nota DECIMAL(10,2)
	PRIMARY KEY(ra_aluno, codigo_disciplina, codigo_avaliacao)
)



DROP TABLE exemplo

CREATE TABLE exemplo(
	ra VARCHAR(MAX),
	nome VARCHAR(MAX),
	nota1 DECIMAL(10, 2),
	nota2 DECIMAL(10, 2),
	mediaFinal DECIMAL(10, 2),
	situacao VARCHAR(MAX)
)

INSERT INTO exemplo(ra, nome, nota1, nota2, mediaFinal, situacao) VALUES
('112aaa', 'Maycon', 6.8, 3.2, 10.0, 'APROVADO'),
('112bbb', 'Felipe', 1.8, 3.2, 5.0, 'APROVADO'),
('112ccc', 'Mendoza', 9.9, 0, 3.9, 'APROVADO'),
('112ddd', 'Huanca', 8.8, 3.8, 2.0, 'REPROVADO')

SELECT * FROM exemplo;


---procedure GERAR AS FALTAS DE TODOS OS ALUNOS com todas as disciplinas
-- COMO FAZER AS FALTAS [0, 1, 2, 3, 4]

CREATE PROCEDURE sp_gerar_faltas
AS
BEGIN
	
	DELETE faltas;
	
	DECLARE @contador AS INT;
	DECLARE @data_atual DATE;
	SET @data_atual = GETDATE();

	SET @contador = 1;
	
	WHILE(@contador <= 20)
	BEGIN
		
		INSERT INTO faltas (ra_aluno, codigo_disciplina, data, presenca)
		SELECT ra, codigo as codigo_disciplina, @data_atual as data, 4 as presenca FROM aluno, disciplina
		WHERE 1=1 AND codigo != '5005-220'
		UNION
 		SELECT ra, '5005-220' as codigo_disciplina, @data_atual AS data, 2 as presenca FROM aluno

		SET @data_atual = DATEADD(DAY, 1, @data_atual); 
		SET @contador = @contador + 1;
	END
END


--PROCEDURE PARA GERAR AS AVALIAÇÕES DAS DISCIPLINAS
CREATE PROCEDURE sp_gerar_resultados_avaliacao
AS
BEGIN
	
	DELETE notas;

	INSERT INTO notas(ra_aluno, codigo_disciplina, codigo_avaliacao, nota)
	
	SELECT ra, codigo_disciplina, codigo_avaliacao, 0 as nota from aluno,
	(SELECT codigo as codigo_disciplina FROM disciplina d WHERE codigo in ('4203-010', '4203-020', '4208-010', '4226-004') ) as tbl,
	(SELECT codigo as codigo_avaliacao FROM avaliacao WHERE codigo in (1, 2, 4)) as tbl2
	WHERE 1=1
	
	UNION ALL
	
	SELECT ra, codigo_disciplina, codigo_avaliacao, 0 as nota from aluno,
	(SELECT codigo as codigo_disciplina from disciplina WHERE codigo in ('4213-003', '4213-013')) as tbl1,
	(SELECT codigo as codigo_avaliacao from avaliacao WHERE codigo in (0, 1, 2, 4)) as tbl2
	WHERE 1=1
	
	UNION ALL
	
	SELECT ra, '4233-005' as codigo_disciplina, codigo_avaliacao, 0 as nota from aluno,
	(SELECT codigo as codigo_avaliacao from avaliacao WHERE codigo in (1, 2, 3)) as tbl2
	WHERE 1=1
	
	UNION ALL
	
	SELECT ra, '5005-220' as codigo_disciplina, codigo_avaliacao, 0 as nota from aluno,
	(SELECT codigo as codigo_avaliacao from avaliacao WHERE codigo in (5, 6)) as tbl2
	WHERE 1=1

END

EXEC sp_gerar_faltas; 
EXEC sp_gerar_resultados_avaliacao;


SELECT * FROM notas WHERE codigo_disciplina = '4213-013';
SELECT * FROM disciplina d 
SELECT * FROM avaliacao a 

-- VIEW DE NOTAS
CREATE VIEW vw_nota AS
SELECT a.ra, a.nome, d.codigo as codigo_disciplina, d.sigla, av.codigo as codigo_avaliacao, av.tipo, n.nota 
FROM aluno a, disciplina d, avaliacao av, notas n
WHERE n.ra_aluno = a.ra
AND n.codigo_disciplina = d.codigo 
AND n.codigo_avaliacao = av.codigo 

SELECT * FROM vw_nota WHERE sigla = 'OAC'


-- VIEW DE PRESENCAS
DROP VIEW vw_presencas;

CREATE VIEW vw_presencas AS
SELECT a.ra, a.nome, d.codigo as codigo_disciplina, d.sigla, f.data, f.presenca
FROM faltas f, disciplina d, aluno a
WHERE f.ra_aluno = a.ra
AND f.codigo_disciplina = d.codigo

SELECT * FROM vw_presencas WHERE sigla ='MPC' ;



UPDATE faltas
SET presenca = 0
WHERE ra_aluno = 'AAA0'
AND codigo_disciplina = '4203-010'
AND data = '2022-06-07'


SELECT * FROM disciplina;

SELECT * FROM faltas 
WHERE codigo_disciplina = '5005-220'
AND data ='2022-06-07';
