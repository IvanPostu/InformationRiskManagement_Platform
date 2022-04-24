
BEGIN

DECLARE @current_category NVARCHAR(MAX) = N'Verificarea autenticității';
DECLARE @last_inserted_question_id INTEGER;
DECLARE @inserted_question_answer_id INTEGER;
DECLARE @mid_sized_profile_id INT = (SELECT TOP 1 profile_id FROM sa__profiles sp WHERE sp.name='Mid sized company profile');
DECLARE @_inserted_question_answer_id1 INTEGER;
DECLARE @_inserted_question_answer_id2 INTEGER;

-- question1:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Există modalități de control a utilizării politicilor de parole pentru conturi de utilizatori de mai multe tipuri?',
    0,
    NULL,
    @last_inserted_question_id OUTPUT;

-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Implementarea modalității de control a utilizării politicelor de parole pentru conturi de utilizatori de mai multe tipuri este un factor foarte important ce permite de a gestiona situații excepționale sau încercările de a încălca politicele cu posibilitatea de a acționa în modul necesar.',
    10,
    @inserted_question_answer_id OUTPUT; -- out

EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Lipsa modalităților de control a utilizării politicilor de parole pentru conturi de utilizatori de mai multe tipuri poate avea urmări grave sub formă de scurgeri de informații fără posibilitatea de a detecta acțiunea dată.',
    2,
    @inserted_question_answer_id OUTPUT; -- out


-- question2:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Selectați conturile de utilizatori pentru care există modalități de control a utilizării politicilor de parole',
    0,
    NULL,
    @last_inserted_question_id OUTPUT;

-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Administrator',
	N'Pentru conturile de administratori există modalități de control a utilizării politicilor de parole, ceea ce înseamnă că sunt întreprinse acțiuni adecvate în privința perioadei în care parola este necesar de schimbat, complexitatea parolei și mulți alți factori ce minimizează probabilitatea de a sparge parola conturilor de administratori.',
    10,
    @inserted_question_answer_id OUTPUT; -- out

EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Utilizator',
	N'Pentru conturile de utilizatori este importantă prezența modalităților de control a utilizării politicilor de parole fiindcă exclude posibilitatea utilizatorului simplu de a seta o parole simplă sau de a o utiliza inadecvat.',
    2,
    @inserted_question_answer_id OUTPUT; -- out

SET @_inserted_question_answer_id2 = @inserted_question_answer_id;
-- question3:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Specificați opțiunea de verificare autenticității ce este utilizat pentru accesul administratorului la dispozitive și calculatoare',
    0,
    @_inserted_question_answer_id2,
    @last_inserted_question_id OUTPUT;

-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Verificarea autenticității bazate pe mai mulți factori',
	N'Este o bună practică verificarea autenticității bazate pe mai mulți factori.',
    10,
    @inserted_question_answer_id OUTPUT; -- out

EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Parola complexă',
	N'Utilizarea parolei complexe oferă un nivel adecvat de securitate, dar este posibil prin ingineria socială ca persoanele neautorizate să capete acces la parolă.',
    2,
    @inserted_question_answer_id OUTPUT; -- out

EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Parola simplă',
	N'Utilizarea parolei simple crează vulnerabilități pentru resursele corporative și pentru sistemele a organizației, este necesar de soluționat problema dată.',
    2,
    @inserted_question_answer_id OUTPUT; -- out

SET @_inserted_question_answer_id2 = @inserted_question_answer_id;
-- question4:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Indicați modalitatea de verificare a autenticității utilizat pentru accesul utilizatorilor interni la rețeaua internă și calculatorul-host',
    0,
    @_inserted_question_answer_id2,
    @last_inserted_question_id OUTPUT;

-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Verificarea autenticității bazate pe mai mulți factori',
	N'Este o bună practică verificarea autenticității bazate pe mai mulți factori.',
    10,
    @inserted_question_answer_id OUTPUT; -- out

EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Parola complexa',
	N'Utilizarea parolei complexe oferă un nivel adecvat de securitate, dar este posibil prin ingineria socială ca persoanele neautorizate să capete acces la parolă.',
    2,
    @inserted_question_answer_id OUTPUT; -- out

EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Parola simpla',
	N'Utilizarea parolei simple crează vulnerabilități pentru resursele corporative și pentru sistemele a organizației, este necesar de soluționat problema dată.',
    2,
    @inserted_question_answer_id OUTPUT; -- out


SET @_inserted_question_answer_id2 = @inserted_question_answer_id;
-- question5:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Indicați modalitatea de verificare a autenticității utilizat pentru accesul la distanță a utilizatorilor',
    0,
    NULL,
    @last_inserted_question_id OUTPUT;

-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Verificarea autenticității bazate pe mai mulți factori',
	N'Este o bună practică verificarea autenticității bazate pe mai mulți factori.',
    10,
    @inserted_question_answer_id OUTPUT; -- out

EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Parola complexă',
	N'Utilizarea parolei complexe oferă un nivel adecvat de securitate, dar este posibil prin ingineria socială ca persoanele neautorizate să capete acces la parolă.',
    2,
    @inserted_question_answer_id OUTPUT; -- out

EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Parola simplă',
	N'Utilizarea parolei simple crează vulnerabilități pentru resursele corporative și pentru sistemele a organizației, este necesar de soluționat problema dată.',
    2,
    @inserted_question_answer_id OUTPUT; -- out


-- question6:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Este posibil scenariu de blocare a contului ce a depășit un anumit număr de încercări nereușite de autentificare în sistem?',
    0,
    NULL,
    @last_inserted_question_id OUTPUT;

-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Blocarea conturilor ce a depășit numărul maximal de încercări de autentificare realizate este up pas adecvat împotriva accesului neautorizat.',
    10,
    @inserted_question_answer_id OUTPUT; -- out

EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Este foarte important de implementat scenariu de blocare a contului ce a depășit un anumit număr de încercări nereușite de autentificare în sistem.',
    2,
    @inserted_question_answer_id OUTPUT; -- out

-- question7:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Sunt elaborate în organizație procese de urmărire a conturilor neactive a administratorilor, angajați, furnizori sau utilizatori cu acces la distanță?',
    0,
    NULL,
    @last_inserted_question_id OUTPUT;

-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Este o modalitate rețională de protecție a datelor și resurselor corporative urmărind conturile neactive a administratorilor, angajați, furnizori sau utilizatori cu acces la distanță.',
    10,
    @inserted_question_answer_id OUTPUT; -- out

EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Este necesar de elaborat procese de urmărire a conturilor neactive a administratorilor, angajați, furnizori sau utilizatori cu acces la distanță pentru a exclude posibilitatea de scurgere de informație și acces neautorizat',
    2,
    @inserted_question_answer_id OUTPUT; -- out


END

