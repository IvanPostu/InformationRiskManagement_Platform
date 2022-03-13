


-- CREATE DATABASE InformationRiskManagementDatabase;
-- CREATE DATABASE InformationRiskManagementDatabase_Test;

BEGIN
    INSERT INTO sa__categories ([name], [description])
    VALUES 
    (N'Protecție perimetrală', ''),
    (N'Verificarea autenticității', '');
END


BEGIN

DECLARE @current_category NVARCHAR(MAX) = N'Protecție perimetrală';
DECLARE @last_inserted_question_id INTEGER;
DECLARE @inserted_question_answer_id INTEGER;
DECLARE @mid_sized_profile_id INT = (SELECT TOP 1 profile_id FROM sa__profiles sp WHERE sp.name='Mid sized company profile');

-- question:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'În organizație se utilizează la granițele rețelei ecrane inter-rețea sau alte elemente de gestiune a accesului la nivel de rețea pentru protecția resurselor corporative?',
    0,
    NULL,
    10,
    @last_inserted_question_id OUTPUT;

-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Utilizarea ecranelor inter-rețea la granițele rețelei este o bună practică ce oferă un nivel adecvat de protecție de atacurile din extern.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out

EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Pentru a soluționa problema dată este necesar în primul rînd de utilizat mecanisme Firewall la toate punctele de graniță a rețelei. Este necesar de configurat firewall - ul astfel încît să fie oferit acces doar la fluxul de date necesar, orice alt flux de date nedeterminat sau care nu nimerește sub o oarecare categorie este necesar de blocat. Este necesar de testat setul de reguli configurat pentru firewall. Utilizarea zonelor demilitarizate și plasarea serverelor ce au acces la internet în zonele date.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
END



