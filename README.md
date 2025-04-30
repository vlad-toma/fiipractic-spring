# Proiect final
Documentatie Swagger: http://localhost:8081/swagger-ui/index.html <br>
Adresa FE: http://localhost:8080/ <br>
Credentiale: <br>
username: defaultuser <br>
password: password <br>
(se poate modifica /sql/init.sql pentru a se adauga useri noi)

# Pentru a rula proiectul cu docker-compose:
Deschideti un terminal in folderul proiectului si rulati: <br>
cd /fiipractic <br>
docker build -t fiipractic . <br>
cd ../weatherapp <br>
docker build -t weatherapp . <br>
cd ../sql <br>
docker build -t mysql . <br>
cd .. <br>
docker-compose up (cu -d ca sa nu apara logurile)(componenta de mysql o sa dea crash la prima rulare din cauza ca trebuie sa isi creeze volumul) <br>
docker-compose down <br>
docker-compose up (cu -d ca sa nu apara logurile)<br>

<br><br>
Minor bug la interfata: nu se afiseaza logo-ul. In urma buildului de le angular, fisierul logo.png este generat in /dist/weatherapp-fe/browser, 
dar cand se executa comanda "COPY --from=builder /weatherapp/dist/weatherapp-fe/browser /usr/share/nginx/html" din Dockerfile nu stiu de ce nu imi copiaza
si fisierul logo.png, am intrat cu Docker Daemon in fisiere in /usr/share/nginx/html si mi se copiaza celelalte fisiere .js si .css, dar favicon.ico si logo.png nu.