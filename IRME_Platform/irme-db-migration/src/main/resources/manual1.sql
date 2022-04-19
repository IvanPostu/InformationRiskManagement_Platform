


-- CREATE DATABASE InformationRiskManagementDatabase;
-- CREATE DATABASE InformationRiskManagementDatabase_Test;

BEGIN

DECLARE @current_category NVARCHAR(MAX) = N'Protecție perimetrală';
DECLARE @last_inserted_question_id INTEGER;
DECLARE @inserted_question_answer_id INTEGER;
DECLARE @mid_sized_profile_id INT = (SELECT TOP 1 profile_id FROM sa__profiles sp WHERE sp.name='Mid sized company profile');
DECLARE @_inserted_question_answer_id1 INTEGER;
DECLARE @_inserted_question_answer_id2 INTEGER;

-- question1:
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
    2,
    @inserted_question_answer_id OUTPUT; -- out

-- question2:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Organizația implementează controale de gestiune a accesului la nivel de rețea pentru protecția resurselor corporative în fiecare birou?',
    0,
    NULL,
    10,
    @last_inserted_question_id OUTPUT;

-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Este necesar de controlat sistematic configurația ecranelor inter-rețea și de testat funcționarea.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out

EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Implementează parțial',
	N'Analizați necesitatea de a utiliza ecrane inter-rețea pentru toate punctele de graniță a rețelei pentru a separa serverele corporative de serverele cu acces la internet (serverele la care se conectează clienții sau alte persoane din rețeaua globală) cu scopul de a minimiza probabilitatea realizării atacurilor cu succes asupra sistemelor informaționale a organizației.',
    '',
    5,
    @inserted_question_answer_id OUTPUT; -- out

EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Este necesar urgent de utilizat ecrane inter-rețea pentru toate punctele de graniță a rețelei și sistematic de gestionat și de controlat configurația.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out


-- question3:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Organizația utilizează zone ce separă rețelele interne și externe în care sunt plasate serviciile?',
    0,
    NULL,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'În fiecare segment a rețelei externe este necesar de oferit acces doar pentru traficul pentru anumite aplicații și pentru anumite porturi prin care se oferă servicii clienților.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Trebuie de convins că elementele de gestiune de rețea oferă acces doar la serviciile ce sunt necesare pentru fiecare conexiune aparte. Este necesar de limitat accesul la serviciile de rețea la intrare și ieșire, la fel este necesar de limitat accesul între diferite segmente de rețea.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out


-- question4:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Sunt plasate servicii legate cu Internetul în organizație?',
    0,
    NULL,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Dacă serviciile legate cu Internetul sunt plasate în rețeaua internă a companiei, este necesar de convins în faptul că sunt instalate ecrane inter-rețea, rețeaua este segmentată și sunt prezente sisteme de detectare a intruziumii (I.D.S.) pentru protecția infrastructurii organizației de atacuri din Internet.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Dacă în viitor v-or fi instalate servicii legate cu Internetul în rețeaua internă a companiei, v-a fi necesar de convins în faptul că sunt instalate ecrane inter-rețea, rețeaua este segmentată și sunt prezente sisteme de detectare a intruziumii (I.D.S.) pentru protecția infrastructurii organizației de atacuri din Internet.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out



-- question5:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Organizația dvs. folosește software firewall pe computerul gazdă pentru a proteja serverele?',
    0,
    NULL,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Este o bună practică de a utiliza firewall pe computerul gazdă ce se conectează la un server a organizației pentru a minimiza probabilitatea de infectarea în lanț a dispozitivelor cu soft malițios.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Este necesar de utilizat firewall pe computerul gazdă ce se conectează la un server a organizației pentru a minimiza probabilitatea de infectarea în lanț a dispozitivelor cu soft malițios.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out



-- question6:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Sunt utilizate în organizație produse hardware sau software pentru a detecta intruziunea în sistem?',
    0,
    NULL,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Continuați practica de utilizare a sistemei de detectare a intruziunii. Este necesar de urmărit după signaturile noi a virușilor, la fel este necesar de studiat noi tehnologii de prevenire a intruziunii.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Este necesar de analizat urmările ce pot apărea în urma unui atac cu succes ce v-a fi inițiat cu o intruziune în sistem și de elaborat un set adecvat de măsuri necesare pentru a preveni acest scenariu. În special este necesar de utilizat cel puțin o sistemă de detectează și anunță administratorii de sistem că a avut loc a încercare de intruziune.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out


SET @_inserted_question_answer_id1 = @inserted_question_answer_id;
-- question7:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Ce tip de sistem de detectare a intruziunilor utilizați?',
    1,
    @_inserted_question_answer_id1,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Sistem de detectare a intruziunilor în rețea',
	N'Este o bună practică de utilizat sisteme de detectare a intruziunilor la nivel de rețea în pentru a detecta dacă a avut loc una sau mai multe încercări de a primi acces la distanță de persoane neautorizate.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Sistem de detectare a intruziunilor nodale',
	N'Este o bună practică de utilizat sisteme de detectare a intruziunilor nodale pe dispozitive fizice sau virtuale demilitarizate cu nivel de importanță înalt ce detectează evenimentele doar pe dispozitivul dat.',
    '',
    5,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu știu',
	N'Lipsa sistemelor de detectare a intruziunilor mărește probabilitatea de realizare a atacului asupra infrastructurii organizației ce v-a provoca pierderi financiare.',
    '',
    3,
    @inserted_question_answer_id OUTPUT; -- out


-- question8:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Sunt implementate soluții antivirus în sistem?',
    0,
    NULL,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Este necesar de utilizat versiunile noi a soluțiilor antivirus cu signaturile de viruși noi. Este necesar sistematic de verificat configurațiile și buna funcționare a soluțiilor antivirus.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Este necesar în primul rînd de plasat soluții antivirus pe serverele ce au o valoare înaltă și ieșirea din funcțiune a cărora v-a provoca pierderi financiare mari sau în caz de scurgere de informație de pe dispozitivele date organizația v-a suferi. La fel este important de utilizat soluții antivirus pe stațiile de lucru (calculatoare și laptopuri) ce aparțin organizației.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out

SET @_inserted_question_answer_id1 = @inserted_question_answer_id;
-- question9:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Selectați sistemele ce utilizează soft din categoria antivirus:',
    1,
    @_inserted_question_answer_id1,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Servere de poștă electronică',
	N'Oferă un nivel de protecție împotriva atacurilor de tip phishing și atașarea fișierelor malware la scrisoarea pe poșta electronică.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Gateway-uri de acces',
	N'Oferă un nivel de protecție a sistemelor ce separă rețeaua globală de rețeaua organizației.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Stații de lucru',
	N'Permite angajaților de a îndeplini acțiunile legate cu domeniul digital în siguranță.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Servere',
	N'Este foarte important de utilizat soluții antivirus pe servere cu baze de date sau pe care sunt plasate aplicațiile web cît pentru clienți, atît și pentru utilizarea internă a companiei.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu știu',
	N'Lipsa soluțiilor antivirus supune organizația riscurilor foarte mari, în special din cauza faptului că întreaga infrastructură a organizației poate fi infectată ți datele pot fi scruse 100% dar faptul dat nici nu v-a fi detectat.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out

-- question10:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Este posibil acces la distanță la rețeaua companiei?',
    0,
    NULL,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Este necesar de utilizat proceduri stricte de autentificare pentru utilizatori, administratori și utilizatori la distanță ce asigură că accesul neautorizat la rețea nu poate fi obținut prin atacuri locale sau la distanță.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Lipsa accesului la distanță la sistemele organizației micșorează riscul atacurilor și riscurile de acces neautorizat din extern.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out

SET @_inserted_question_answer_id1 = @inserted_question_answer_id;
-- question11:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Cine are acces la distanță la rețeaua companiei?',
    1,
    @_inserted_question_answer_id1,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Contractorii',
	N'Este necesar de utilizat soluții adiționale de protecție la nivel de rețea în cadrul organizației, de exemplu securizarea traficului prin SSL/TLS, utilizarea protocolului SSH, utilizarea rețelei virtuale private VPN.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Angajați',
	N'Este necesar de utilizat soluții adiționale de protecție la nivel de rețea în cadrul organizației, de exemplu securizarea traficului prin SSL/TLS, utilizarea protocolului SSH, utilizarea rețelei virtuale private VPN.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out

-- question12:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Sunt utilizate tehnologii de rețea virtuală (VPN) pentru a oferi securitate conexiunilor utilizatorilor la distanță la resursele corporative?',
    0,
    @_inserted_question_answer_id1,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Este o modalitate adecvată de securitate a resurselor corporative utilizînd soluțiile VPN.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Este necesar de implementat soluții VPN pentru a nu permite acces neautorizat la resursele corporative a organizației.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out

SET @_inserted_question_answer_id2 = @inserted_question_answer_id;
-- question13:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'VPN - ul companiei oferă posibilitatea de a parcurge toate verificările de securitate necesare?',
    0,
    @_inserted_question_answer_id2,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Este o modalitate adecvată de a parcurge toate verificările de securitate necesare inclusiv pentru utilizatorii conectați prin VPN.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Benificiile utilizării soluției VPN sunt la un nivel mai scăzut din motivul lipsei verificărilor de securitate necesare.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out

-- question14:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Sunt utilizate verificări de autenticitate bazate pe mai mulți factori pentru utilizatori conectați la distanță?',
    0,
    @_inserted_question_answer_id1,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Pentru a reduce în continuare riscul de spargere a parolei în conturile administrative, este necesar de blocat contul după 7 - 10 încercări de a introduce o parolă greșită, de setat configurație pentru expirarea parolei',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Utilizarea verificării de autencitate bazate pe mai mulți factori oferă un nivel de protecție înalt și necesită de a fi implementat în orice caz.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out

-- question15:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Există în rețea mai mult de un segment?',
    0,
    NULL,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Este o bună practică segmentarea rețelei ce minimizează probabilitatea apariției accesului neautorizat la resursele corporative.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Este necesar de extins rețeaua în segmente, în special pentru zonele demilitarizate cu scopul de a proteja serviciile accesibile prin internet de resursele corporative interne. Este necesar de plasat ecrane inter-rețea pentru anumite resurse. Este necesar de setat reguli de control a accesului de intrare și ieșire. Este necesar de utilizat filtre ce limitează accesul.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out

SET @_inserted_question_answer_id1 = @inserted_question_answer_id;
-- question16:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Se utilizează segmentarea rețelei pentru clientul extern separat și serviciile rețelei externe de resursele corporative?',
    0,
    @_inserted_question_answer_id1,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Este o bună practică separarea accesului clientului extern și a serviciilor externe de resursele corporative interne.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Apare riscul accesului neautorizat la resursele corporative în cazul dacă contul clientului a fost spart.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out

SET @_inserted_question_answer_id2 = @inserted_question_answer_id;
-- question17:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Calculatoarele-host sunt grupate pe segmente de rețea reieșind din roluri sau servicii oferite asemănătoare?',
    0,
    @_inserted_question_answer_id2,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Este o modalitatea rațională separarea colculatoarelor host pe segmente în dependență de roluri sau serviciile oferite.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Este o încălcare majoră ce anulează utilitatea segmentării rețelei și separării serverelor cu acces public în internet de resursele corporative.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out

-- question18:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Calculatoarele-host sunt grupate pe segmente de rețea reieșind din doar serviciile necesare utilizatorilor ce se conectează?',
    0,
    @_inserted_question_answer_id2,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Gruparea calculatoarelor-host pe segmente reieșind doar din serviciile necesare utilizatorilor ce se conectează este foarte benefică doar ca urmare necesită resurse hardware și software.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Lipsa grupării calculatoarelor-host pe segmente reieșind doar din serviciile necesare utilizatorilor este o încălcare minoră fiindcă unele segmente pot include calculatoare host ce nu trebuie să fie anume în segmentul dat.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out

-- question19:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'A fost creat și documentat planul ce permite de a gestiona adresele TCP/IP pentru sisteme în dependență de segmente?',
    0,
    @_inserted_question_answer_id2,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Prezența planului ce permite de a gestiona accesul pentru adresele TCP/IP pentru sisteme în dependență de segmente joacă un rol foarte important din motivul anulării posibilității încercărilor de conexiune a persoanelor neautorizate adresele cărora nu sunt incluse în listă.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Lipsa planului ce permite de a gestiona accesul pentru adresele TCP/IP pentru sisteme în dependență de segmente este o încălcare gravă ce poate aduce urmări majore.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out

-- question20:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Există posibilitatea de conexiune wireless la rețea?',
    0,
    NULL,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Posibilitatea de conexiune wireless la rețea supune organizația unor noi riscuri, în special accesul neautorizat sau scurgeri de informație.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Absența posibilității de conexiune la rețea prin intermediul wireless minimizează probabilitatea de accesul neautorizat în sistem dar limitează activitatea organizației în unele ramuri, de exemplu nu pot fi utilizate printere ce funcționează în rețea, angajații nu v-or putea accesa internetul prin intermediul dispozitivelor mobile etc.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out

SET @_inserted_question_answer_id1 = @inserted_question_answer_id;
-- question21:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Ce elemente de gestiune a securității se utilizează pentru a grupa conexiunile la rețeaua wireless?',
    1,
    @_inserted_question_answer_id1,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Utilizarea criptării (WEP) Wired Equivalent Privacy',
	N'Utilizarea criptării pentru rețeaua wireless este o soluție adecvată contra captării traficului.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Utilizarea accesului protejat WPA',
	N'Utilizarea criptării pentru rețeaua wireless este o soluție adecvată contra captării traficului.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Limitarea bazată pe adrese MAC',
	N'Utilizarea criptării pentru rețeaua wireless este o soluție adecvată contra captării traficului.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out



END

