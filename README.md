# Nu e final proiectul

Documentatie Swagger: http://localhost:8081/swagger-ui/index.html <br>
Adresa FE: http://localhost:8080/ <br>
Credentiale: <br>
username: defaultuser <br>
password: password <br>

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