# Site de stiri sportive
> Proiectul consta in realizarea unui site de stiri sportive in care utilizatori isi vor putea crea cont si sa se aboneze pentru a urmari anumite stiri. Acestia vor fi notificati in momentul in care apar noi stiri si isi vor putea gestion profilul. Adminul va fi responsabil pentru a adauga, sterge sau edita postarile.

## Prezentare functionalitati:
### 1. Pagina Principala:

- Pagina principala va oferi o privire generala asupra ultimelor stiri sportive.
- Aceasta va afisa titlurile si scurte rezumate ale articolelor cele mai recente si cele mai populare.

### 2. Pagina de Autentificare si Inregistrare:

- Utilizatorii vor putea sa se autentifice sau sa isi creeze un cont nou pentru a avea acces la toate functionalitatile site-ului.
- Autentificarea si inregistrarea vor fi securizate, asigurand confidentialitatea datelor personale ale utilizatorilor.

### 3. Pagina Profilului Utilizatorului:

- Fiecare utilizator va avea un profil personal unde isi poate gestiona informatiile personale si preferintele de abonare.
- Aceasta pagina va oferi, de asemenea, optiuni pentru a modifica parola, adresa de email si alte setari ale contului.

### 4. Gestionarea Stirilor de catre Admin:

- Adminul site-ului va avea acces la o interfata de administrare dedicata pentru a adauga, edita si sterge articole.
- Va exista, de asemenea, optiuni pentru a incarca imagini si a formata textul articolelor in mod corespunzator.

### 5. Abonare la Stiri:

- Utilizatorii inregistrati vor putea sa se aboneze la diferite categorii de stiri sportive, cum ar fi fotbal, baschet, tenis etc.
- Aceasta optiune de abonare va permite utilizatorilor sa primeasca notificari prin email in momentul in care sunt publicate noi articole in categoriile la care sunt abonati.
- Fiecare categorie de abonament va avea un pret, iar pentru a se abona, utilizatorul va trebui sa plateasca si i se va lua bani periodic pentru a-si pastra abonamentul.
- Daca utilizatorul nu va mai avea destui bani i se va anula abonamentul.

### 6. Notificari si Abonamente:

- Utilizatorii vor putea sa isi gestioneze abonamentele.
- Acestia vor fi notificati prin email de fiecare data cand se adauga o stire noua intr-o categorie la care detin abonament.


# Baza de date
## Tabele
### 1. Tabela User :
* __user_id__ _(long, cheie primara, auto-increment)_: Identificator unic pentru fiecare utilizator.
* __username__ _(varchar)_: Numele de utilizator al utilizatorului.
* __password__ _(varchar)_: Parola utilizatorului pentru securitate.
* __email__ _(varchar)_: Adresa de email a utilizatorului.
* __full_name__ _(varchar)_: Numele complet al utilizatorului.
* __avatar__ _(varchar)_: Reprezinta path-ul pana la imagine
* __balance__ _(double)_ : Balanta utilizatorului

### 2. Tabela Category :
* __category_id__ _(long, cheie primara, auto-increment)_: Identificator unic pentru fiecare categorie de stiri.
* __category_name__ _(varchar)_: Numele categoriei de stiri (ex: fotbal, baschet, tenis etc.).
* __subscription_cost__ _(double)_: Reprezinta pretul abonamentului pentru categoria respectiva
* __logo__ _(string): Reprezinta url-ul imagini care reprezinta logo-ul sportului._


### 3. Tabela News :
* __news_id__ _(long, cheie primara, auto-increment)_: Identificator unic pentru fiecare stire.
* __category_id__ _(long, cheie externa)_: Legatura catre categoria din tabela __Category__.
* __title__ _(varchar)_: Titlul stirii.
* __content__ _(text)_: Continutul stirii.
* __image__ _(varchar)_: Calea catre imaginea asociata stirii.
* __published_at__ _(date)_: Data publicarii stirii.

### 4. Tabela Subscription :
* __subscription_id__ _(long, cheie primara, auto-increment)_: Identificator unic pentru fiecare abonament.
* __user_id__ _(long, cheie externa)_: Legatura catre utilizatorul din tabela __User__.
* __category_id__ _(long, cheie externa)_: Legatura catre categoria din tabela __Category__.
* __start_date__ _(date)_ : Data cand s-a creat abonamentul. 
* __amount_paid__ _(double)_ : Costul abonamentului.

### 5. Tabela Image :
* __image_id__ _(long, cheie primara, auto-increment)_: Identificator unic pentru fiecare imagine.
* __news_id__ _(long, cheie secundara)_: Legatura catre stirea din tabela __News__
* __url__ _(text)_: Url-ul imaginii.

## Relatii
### 1. Tabela User si Tabela Subscription :
- relatia este de tipul __"one-to-many"__, deoarece un utilizator poate avea mai multe abonamente, dar un abonament apartine doar unui utilizator.
- aceasta relatie este realizata prin intermediul campului __'user_id'__ din tabela __'Subscription'__, care este o cheie externa ce face referire la campul user_id din tabela __'User'__.
#### 2. Tabela Category si Tabela News : 
- relatia este de tipul __"one-to-many"__, deoarece o categorie de stiri poate contine mai multe stiri, dar o stire apartine doar unei categorii.
- aceasta relatie este realizata prin intermediul campului __'category_id'__ din tabela __'News'__, care este o cheie externa ce face referire la campul __'category_id'__ din tabela __'Category'__.
### 3. Tabela Category si Tabela Subscription :
- relatia este de tipul __"one-to-many"__, deoarece o categorie poate apartine mai multor abonamente, iar un abonament poate fi asociat cu o categorie.
- aceasta relatie este realizata prin intermediul campului __'category_id'__ din tabela __'Subscription'__, care este cheie externa ce face referire la campul __'category_id'__ din tabela __'Category'__.
### 4. Tabela News si Tabela Image :
- relatie de tipul __"one-to-many"__, deoarece o stire poate avea mai multe imagini, insa o imagine poate apartine doar unei singure stiri.
- aceasta relatie este realizata prin intermediul campului __'news_id'__ din tabela __'Image'__, care este cheie externa ce face referire la campul __'news_id'__ din tabela __'News'__.

![Diagrama Bazei de date](Poze/diagrama.png)

# Implementare
<p float="left">
  <img src="Poze/argitectura1.png" width="300" />
  <img src="Poze/arhitectura2.png" width="300" />
</p>

## Model
- Aici am implementate clasele __'User'__, __'Category'__, __'News'__ si __'Subscription'__, __'Image'__.
- Acestea reprezinta modelele in care si am declarate campurile pentru ce doresc sa memorez in baza de date.
- Am folosit adnotari pentru a crea automat tabele in baza de date. Pentru fiecare tabel id-ul este setat ca cheie primara si este auto-incrementat.
- Pentru limpezitatea codului am folosit adnotarile __"Setter"__ si __"Getter"__ pentru a nu fi nevoie sa scriu explicit settere si gettere pentru fiecare instanta.
- Am si relatii intre tabele si anume unele campuri precum user_id, category_id sunt chei secundare pentru alte tabele precum Subscription si News.
- Pentru tabelele 'User', 'Category' si 'News' am declarat si niste liste in care memorez obiectele cu care este asociat si asupra carora se aplica automat toate operatiile in momentul in care se aplica pe obiectul principal. Spre exemplu daca sterg un user se vor sterge automat toate abonamentele asociate cu user-ul respectiv.  
- Am suprascris si metodele __'hashCode'__ si __'equals'__ pentru a putea verifica egalitatea dintre 2 obiecte.

## Repository
- Aici am implementat interfete care extind __'JpaRepository'__ si permit interactiunea cu baza de date pentru toate operatiile.
- Prin intermediul JPA putem folosi anumite operatii pe baza de date care deja sunt implementate, sau ne putem defini noi alte query-uri.
- Am creat pentru fiecare tabel __'UserRepository'__, __'NewsRepository'__, __'CategoryRepository'__, __'SubscriptionRepository'__ si __'ImageRepository'__.

## Contract
- __'UserContract'__, __'NewsContract'__, __'CategoryContract'__, __'SubscriptionContract'__ si __'ImageContract'__.
- Folosite pentru deculparea de baza de date, astfel putand fi folosite cu date din alta parte.

## Data
- __'UserData'__, __'NewsData'__, __'CategoryData'__, __'SubscriptionData'__ si __'ImageData'__.
- Clase ce implementeaza interfetele Contract.
- Contine cate o dependinta catre repository-ul respectiv fiecarui tabel.

## Service
- __'UserService'__, __'NewsService'__, __'CategoryService'__, __'SubscriptionService'__ si __'ImageSrvice'__.
- Serveste ca o legatura intre straturile de prezentare (cum ar fi Controller-ul) si straturile de acces la date (cum ar fi Contractul).
- Este responsabil pentru implementarea logicii de afaceri, manipularea datelor si comunicarea cu contractul pentru a obtine sau modifica datele.

## ServiceImpl
- __'UserServiceImpl'__, __'NewsServiceImpl'__, __'CategoryServiceImpl'__, __'SubscriptionServiceImpl'__ si __'ImageSrviceImpl'__.
- Implemeteaza interfetele Service.
- Pot contine dependinte de la mai multe contracte, nu doar cel pentru care implemnteaza.
- Aici regasim implementarea metodelor prezentate in Service.

## Controller
- __'UserController'__, __'NewsController'__, __'CategoryController'__,  __'SubscriptionController'__ si __'ImageController'__.
- Este responsabila pentru gestionarea cererilor de tipul __HTTP__.
- Prin intermediul acestei clase se expun end-point-uri pentru operatiile de __GET, POST, PUT, DELETE__ si se gestioneaza datele primite si trimise intre client si server.

## EndPoint-uri:
### User:
- __/users__ (GET) - obtine lista cu toti utilizatori
- __/users/{id}__ (GET) - obtine un utilizator dupa un id
- __/users__ (POST) - adauga un utilizator in baza de date
- __/users/{id}__ (PUT) - actualizeaza utilizatorul cu id-ul respectiv
- __/users/{id}__ (DELETE) - sterge utilizatorul cu id-ul respectiv 
- __/users/login__ (POST)- cauta un user dupa username si verifica daca exista. In caz ca exista verifica daca parole sunt la fel si returneaza mesajul de "Autentificare reusita", altfel "Autentificare esuata".

### Category:
- __/categories__ (GET) - obtine lista cu toate categoriile
- __/categories/{id}__ (GET) - obtine o categorie dupa un id
- __/categories__ (POST) - adauga o categorie in baza de date
- __/categories/{id}__ (PUT) - actualizeaza categoria cu id-ul respectiv
- __/categories/{id}__ (DELETE) - sterge categoria cu id-ul respectiv

### Subscription:
- __/subscriptions__ (GET) - obtine lista cu toate abonamentele
- __/subscriptions/{id}__ (GET) - obtine un abonament dupa un id
- __/subscriptions__ (POST) - adauga un abonament in baza de date
- __/subscriptions/{id}__ (PUT) - actualizeaza abonamentul cu id-ul respectiv
- __/subscriptions/{id}__ (DELETE) - sterge abonamentul cu id-ul respectiv
- __/subscriptions/getSubscriptionsByUserId/{id}__ (GET) - returneaza o lista cu toate abonamentele unui user pe care il cauta dupa id.

### News:
- __/news__ (GET) - obtine lista cu toate stirile
- __/news/{id}__ (GET) - obtine o stire dupa un id
- __/news__ (POST) - adauga o stire in baza de date
- __/news/{id}__ (PUT) - actualizeaza stirea cu id-ul respectiv
- __/news/{id}__ (DELETE) - sterge stirea cu id-ul respectiv 
- __/news/getNewsByCategoryName__ (GET) - returneaza o lista de stiri care apartin unei categorii cu numele cautat.
- __/news/getUsersByNewsId/{id}__ (GET) - returneaza o lista de utilizatori care sunt abonati la categoria din care face parte stirea cu id-ul respectiv
- __/news/getLatestNews__ (GET) - returneaza ultimele stiri in functie de data.

### Image:
- __/images__ (GET) - obtine lista cu toate imaginile
- __/images/{id}__ (GET) - obtine o imagine dupa un id
- __/images__ (POST) - adauga o imagine in baza de date
- __/images/{id}__ (PUT) - actualizeaza imaginea cu id-ul respectiv
- __/images/{id}__ (DELETE) - sterge imaginea cu id-ul respectiv 
- __/imagesgetImagesByNewsId/{id}__ (GET) - obtine lista cu toate imaginile asociate unei stiri



## Observer Pattern
- Pentru observer pattern am ales sa notific toti utilizatori care au abonamente la categoria din care face parte stirea nou introdusa/modificata.
#### UserNewsObserver:
- aceasta interfata contine metoda __'update'__ ce va avea rolul de a notifica toti utilizatori cand se adauga o stire noua.
#### EmailService:
- Responsabil pentru trimiterea mail-urilor.
- are dependinta __'JavaMailSender'__, iar email-ul de pe care se vor trimite mail-uri este al Adminului.
- contine metoda __"sendEmailNewsAdded"__ care primeste email-ul catre cine sa trimita, detaliile stirii si numele categoriei si creaza continutul email-ului din aceste date.

![Email Stire Noua](Poze/stireNoua_email.png)

#### UserNotification:
- Clasa ce implmenteaza __'UserNewsObserver'__ pentru metoda _'update'_.
- Aceasta clasa va fi observatorul.
- Memoreaza date precum: 
    - user :- este user-ul ce trebuie notificat;
    - emailService :- este o depedinta catre implemntarea trimiteri mail-ului;
- __update__: aceasata metoda primeste stirea nou introdusa si Categoria din care face parte stirea si apeleaza _'sendEmailNewsAdded'_ din __EmailService__.  

In clasa __NewsServiceImpl__ am o lista de _"UserNotification"_. In momentul in care se adauga o noua stire, imi modific lista de observatori, creeand noi observatori pentru utilizatori care sunt abonati la categoria din care face parte stirea pe care o introduc, dupa care apelez __'notifyObservers'__ in care apelez _'update'_ pentru fiecare observator.
   
## Testare
Am implementat teste pentu diferite pachete:
- __Service__ : _'UserServiceTest'_, _'CategoryServiceTest'_, _'SubscriptionServiceTest'_, _'NewsServiceTest'_ si _'ImageServiceTest'_. Aici am implementat teste care sa testeze functionalitatile Service-urilor.
- __Contract__ : _'UserContractTest'_, _'CategoryContractTest'_, _'SubscriptionContractTest'_, _'NewsContractTest'_ si _'ImageContractTest'_. Aici am implementat teste care sa testeze functionalitatile Contractelor.
- __Observer__ : _'EmailServiceTest'_. Am implemtat test pentru testarea metodei de trimitere de mail.

![Teste](Poze/teste.png)

## Diagrame UML:

### Diagrama UML User:
![Teste](Poze/user_uml.png)

### Diagrama UML User-Subscription-Category:
![Teste](Poze/user_sub_cat.png)

### Diagrama UML Category-News-Image:
![Teste](Poze/image_news_category_uml.png)

### Diagrama UML cu toate:
![Teste](Poze/all_uml.png)


# FrontEnd
## Intruducere:
  Acest document ofera o descriere detaliata a structurii si functionalitatilor frontend-ului pentru site-ul de stiri sportive realizat in Angular. Utilizatorii isi pot crea conturi, se pot autentifica, abona la categorii de stiri si gestiona profilul personal. Adminul poate adauga, edita si sterge articole de stiri, imagini, categorii si utilizatori.

## Structura:
> 
    src/
    |-- app/
    |   |-- admin/
    |   |   |-- admin/
    |   |   |-- panel/
    |   |-- home/
    |   |-- login/   
    |   |-- news/
    |   |-- profil/
    |   |   |-- date/
    |   |   |-- principal/
    |   |-- service/
    |   |-- sign-up/
    |   |-- view/
    ...

## Module și Componente
### Admin:
- Pentru admin am create 2 componente: admin si panel.
- ___Panel___ este responsabil pentru autentificare, trebuie sa se introduca username-ul si parola adminului pentru a fi directionat catre pagina principala a Adminului.
- ___Admin___ reprezinta pagina principala a adminului. Acolo adminul poate sa vizualizeze date despre utilizatori, categorii, stiri si imagini.
- Pe langa asta el poate sa etiteze, modifica sau sa stearga date.

### Home:
- Este pagina in care se deschide site-ul.
- Contine un titlu si 4 butoane, directiile catre care pagini se poate ajunge de la pagina de home.

### Login:
- Pagina de logare in profilul utilizatorului.
- De pe aceasta pagina se poate ajunge inapoi la pagina de home sau naviga catre pagina de Sign-Up in cazul in care nu avem cont. 
- Pentru logare se introduce username-ul si parola si se verifica daca exista un utilizator cu aceste date. In caz negativ se afiseaza un mesaj de eroare, altfel navigam catre profil.

### News:
- Reprezinta pagina care prezinta detaliile unei stiri.
- Avem prezentat titlul, imaginea principala a stirii, continutul, iar la final, daca exista, se afla restul imaginilor asociate stirii.
-Pe aceasta pagina se poate ajunge din 2 directii, deci si la intoarcere inapoi se poate duce in 2 directii.

### Profil:
- Este pagina principala a utilizatorului.
- Are un meniu cu toate optiunile:
  - vizualizare date;
  - editare date cont: username-ul si email-ul trebuie sa fie diferita fata de restul urilizatorilor in BD;
  - vizualizare abonamente detinute;
  - creare de noi abonamente: poti sa iti creezi daca ai destui bani si daca nu ai deja abonament la categoria respectiva;
  - schimbare parola: parola trebuie sa fie de minim 8 caractere si este necesara si vechea parola;
  - stergere cont: te pune sa confirmi ca este de acord;
  - balanta;
  - deconectare;
- Daca intri pe unul din abonamentele detinute te directioneaza catre o pagina unde sunt toate stirile din categoria respectiva.

### Service:
- Aici se face legatura cu endpoint-urile din backend;
- Am declarat niste interfete cu aceleasi atribute ca si modelele din backend.
> User
    
    export interface User {
      userId: number;
      username: string;
      password: string;
      email: string;
      fullName: string;
      avatar: string;
      balance: number;
      subscriptions?: Subscription[];
    }

> Subscription
    
    export interface Subscription {
      subscriptionId: number;
      user: User;
      category: Category;
      startDate: string; // LocalDate este reprezentat ca string în JSON
      amountPaid: number;
    }

> News
    
    export interface News {
      newsId: number;
      category: Category;
      title: string;
      content: string;
      publishedAt: string;
      image: string;
    }


> Category
    
    export interface Category {
      categoryId: number;
      categoryName: string;
      subscriptionCost: number;
      logo: string;
    }


> Image
    
    export interface Image {
      imageId: number;
      news: News;
      url: string;
    }   

- Pe langa aste am implementat functii care apaleaza endpoinr-urile din backend.

### Sign-up:
- In cazul in care nu ai cont iti poti crea unul nou.
- Trebuie sa introduci date precum: username, email, nume complet, parola, balanta, avatar(poti pune un url catre o imagine).
- Exista si verificari. Username-ul si email-ul nu trebuie sa se mai regaseacsa, numele nu poate lipsi, balanta trebuie sa fie mai mare de 0, iar parola trebuie sa aiba minim 8 caractere.
- Daca totusi ai deja un cont poti sa mergi direct sa te loghezi la el.

### View:
- Aici sunt afisate ultimele stiri, cele mai recente, in functie de data.
- Poti accesa pe oricare dintre ele sa vezi detaliile complete.

## Rute:
>
    const routes: Routes = [
      { path: '', component: HomeComponent },
      { path: 'home', component: HomeComponent },
      { path: 'sign-up', component: SignUpComponent},
      { path: 'login', component: LoginComponent },
      { path: 'admin-panel', component: PanelComponent},
      { path: 'admin', component: AdminComponent},
      { path: 'profil/:id', component: PrincipalComponent},
      { path: 'stiri/:id', component: DateComponent},
      { path: 'view', component: ViewComponent},
      { path: 'news/:id', component: NewsComponent},
      { path: '', redirectTo: '/home', pathMatch: 'full' },
      { path: '**', redirectTo: '/home' }
    ];

## Poze:
### Home:
![Teste](Poze/home.png)
### Admin:
<div style="display: flex; justify-content: center; align-items: center;">
    <img src="Poze/admin1.png" alt="Imagine 1" style="width: 33%; margin: 0 5px;">
    <img src="Poze/admin2.png" alt="Imagine 2" style="width: 33%; margin: 0 5px;">
    <img src="Poze/admin3.png" alt="Imagine 2" style="width: 33%; margin: 0 5px;">
</div>
### Autentificare:
<div style="display: flex; justify-content: center; align-items: center;">
    <img src="Poze/login.png" alt="Imagine 1" style="width: 50%; margin: 0 5px;">
    <img src="Poze/signup.png" alt="Imagine 2" style="width: 50%; margin: 0 5px;">
</div>

### Profil:

<div style="display: flex; justify-content: center; align-items: center;">
    <img src="Poze/profil1.png" alt="Imagine 1" style="width: 33%; margin: 0 5px;">
    <img src="Poze/profil2.png" alt="Imagine 2" style="width: 33%; margin: 0 5px;">
    <img src="Poze/profil3.png" alt="Imagine 2" style="width: 33%; margin: 0 5px;">
</div>

#

<div style="display: flex; justify-content: center; align-items: center;">
    <img src="Poze/profil4.png" alt="Imagine 1" style="width: 33%; margin: 0 5px;">
    <img src="Poze/profil6.png" alt="Imagine 2" style="width: 33%; margin: 0 5px;">
    <img src="Poze/profil5.png" alt="Imagine 2" style="width: 33%; margin: 0 5px;">
</div>

### Stiri:

<div style="display: flex; justify-content: center; align-items: center;">
    <img src="Poze/view.png" alt="Imagine 1" style="width: 50%; margin: 0 5px;">
    <img src="Poze/stiri.png" alt="Imagine 2" style="width: 50%; margin: 0 5px;">
</div>

#

<div style="display: flex; justify-content: center; align-items: center;">
    <img src="Poze/stire1.png" alt="Imagine 1" style="width: 33%; margin: 0 5px;">
    <img src="Poze/stire2.png" alt="Imagine 2" style="width: 33%; margin: 0 5px;">
    <img src="Poze/stire3.png" alt="Imagine 2" style="width: 33%; margin: 0 5px;">
</div>


## Use-case:
### User:

![Teste](Poze/user_use_case.png)

### Admin:

![Teste](Poze/admin_use_case.png)

