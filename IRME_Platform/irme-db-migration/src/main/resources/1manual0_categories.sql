
BEGIN
    INSERT INTO sa__categories ([name], [description], [image_url])
    VALUES 
    (N'Protecție perimetrală', 
        N'Apărarea perimetrului abordează securitatea la granițele rețelei, unde rețeaua internă se conectează la lumea exterioară. Aceasta constituie prima linie de apărare împotriva intrușilor.', 
        'sa_categories/perimiterSecurity.png'),
    (N'Verificarea autenticității',
        N'Procedurile de autentificare riguroase pentru utilizatori, administratori și utilizatori la distanță ajută la asigurarea faptului că persoanele din afara nu obțin acces neautorizat la rețea prin utilizarea atacurilor locale sau de la distanță.',
     'sa_categories/authorization.png'),
    (N'Management și monitoring',
        N'Managementul, monitorizarea și înregistrarea adecvată sunt esențiale pentru întreținerea și analiza mediilor IT. Aceste instrumente sunt și mai importante după ce a avut loc un atac și este necesară analiza incidentului',
     'sa_categories/managementAndMonitoring.png'),
    (N'Mediu de hostare a aplicațiilor web',
        N'Atunci când aplicațiile critice pentru afaceri sunt implementate în producție, securitatea și disponibilitatea acestor aplicații și servere trebuie să fie asigurate. Întreținerea continuă este esențială pentru a vă asigura că erorile de securitate sunt corectate și că noi vulnerabilități nu sunt introduse în mediu.',
     'sa_categories/mediuDeHostare.png'),
    (N'Politici de securitate',
        N'Politica de securitate corporativă se referă la politicile și liniile directoare individuale care există pentru a guverna utilizarea sigură și adecvată a tehnologiei și proceselor în cadrul organizației. Această zonă acoperă politici pentru a aborda toate tipurile de securitate, cum ar fi utilizator, sistem și date.',
     'sa_categories/politicDeSecurita.png'),
    (N'Backup și recuperare',
        N'Backup-ul și restaurarea datelor sunt esențiale pentru menținerea continuității afacerii în caz de dezastru sau defecțiune hardware/software. Lipsa procedurilor adecvate de backup și restaurare ar putea duce la pierderi semnificative de date și productivitate.',
     'sa_categories/backupAndRecovery.png'),
    (N'Plasarea și utilizarea serviciilor',
        N'Securitatea unei organizații depinde de procedurile operaționale, procesele și liniile directoare care sunt aplicate mediului. Ele pot spori securitatea unei organizații prin includerea mai mult decât apărarea tehnologiei. Documentația și liniile directoare exacte ale mediului sunt esențiale pentru capacitatea echipei de operare de a sprijini și menține securitatea mediului.',
     'sa_categories/services.png'),
    (N'Stocarea datelor și comunicații',
        N'Integritatea și confidențialitatea datelor este una dintre cele mai mari preocupări pentru orice afacere. Pierderea sau furtul de date poate afecta veniturile unei organizații, precum și reputația acesteia. Este important să înțelegeți modul în care aplicațiile gestionează datele critice pentru afaceri și cum sunt protejate aceste date',
     'sa_categories/webCommunications.png'),
    (N'Cerințe și evaluări',
        N'Cerințele de securitate ar trebui să fie înțelese de toți factorii de decizie, astfel încât atât deciziile lor tehnice, cât și cele de afaceri să sporească securitatea, mai degrabă decât să intre în conflict cu aceasta. Evaluările regulate de către o terță parte pot ajuta o companie să revizuiască, să evalueze și să identifice domeniile de îmbunătățire.',
     'sa_categories/requirementsAndEvaluations.png'),
    (N'Politici și proceduri',
        N'Procedurile clare și practice pentru gestionarea relațiilor cu furnizorii și partenerii pot ajuta la limitarea expunerii companiei dumneavoastră la riscuri. Procedurile care acoperă angajarea și rezilierea angajaților vă pot ajuta să vă protejați compania de angajații fără scrupule sau nemulțumiți.',
     'sa_categories/politicsAndProcedures.png'),
    
    -- security trainings etc.
    (N'Formare și conștientizare (resurse umane)', 
        N'Angajații ar trebui să fie instruiți și conștienți de modul în care securitatea se aplică activităților lor zilnice de muncă, astfel încât să nu expună din neatenție compania lor la riscuri mai mari.', 
    'sa_categories/training.png')
    ;
END
