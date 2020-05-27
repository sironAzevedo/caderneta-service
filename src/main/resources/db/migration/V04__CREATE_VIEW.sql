--Consulta Dashboard
CREATE OR REPLACE VIEW SGCP.ALL_USER_ACCOUNTS AS
	SELECT
		m.id as MES_ID, 
		m.desc_mes as MES,
		Extract('Year' From c.dt_cadastro) as ANO,
		COALESCE(s.salario, 0) as SALARIO, 
		count(c.id) as QTD_CONTAS,
		COALESCE(SUM(c.valor_conta), 0) as TOTAL_GASTOS,		
		CASE
			WHEN COALESCE(s.salario, 0) = 0 THEN COALESCE(s.salario, 0)
			WHEN COALESCE(s.salario, 0) > 0 THEN COALESCE(( s.salario - SUM(c.valor_conta) ), 0)			 
		END
		AS SALDO_FINAL,
		u.id as USUARIO
	FROM SGCP.TB_MES m
	INNER JOIN SGCP.TB_CONTA c
	ON c.id_mes = m.id	
	LEFT JOIN SGCP.TB_MES_SALARIO s
	ON s.id_mes = m.id	
	INNER JOIN SGCP.TB_USUARIO_CONTA uc
	ON c.id = uc.id_conta
	INNER JOIN SGCP.TB_USUARIO u
	ON u.id = uc.id_usuario
	GROUP BY MES_ID, SALARIO, ANO, USUARIO
	ORDER BY ANO, MES_ID;
	
