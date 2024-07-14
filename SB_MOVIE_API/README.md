Spring Boot Moies API
=============================

Concepts:
----------------
File upload and Download
CRUD Operations
JWT Tokens
Spring Boot Mail Sender

USE POSTMAN:

FileController:

Post: http://localhost:9090/file/upload

BODY > Formdata > select as file and upload the .png file
output: File uploaded : jawan.png

one folder will create with the name of posters in this folder the uploaded .png files are available

Get: http://localhost:9090/file/6618.png



MovieController:

POST : http://localhost:9090/api/v1/movie/add-movie -> add the .png file

Form-data:
File -> add .png file
movieDto -> pass JSON text
{
"movieId": 1,
"title": "Avengers: Infinity War",
"director": "Russo Brothers",
"studio": "Marvel studio",
"movieCast": [
"RDJ",
"Chris Evans",
"Scarlett Johansson"
],
"releaseYear": "2018",
"poster": "default.png",
"posterUrl": "url"
}

Response: 
{
    "movieId": 1,
    "title": "Avengers: Infinity War",
    "director": "Russo Brothers",
    "studio": "Marvel studio",
    "movieCast": [
        "RDJ",
        "Chris Evans",
        "Scarlett Johansson"
    ],
    "releaseYear": 2018,
    "poster": "avengers_endgame.png",
    "posterUrl": "http://localhost:9090/file/avengers_endgame.png"
}
Check in google: http://localhost:9090/file/avengers_endgame.png

{
    "movieId": 2,
    "title": "Bahubali the Begining",
    "director": "SS Rajamouli",
    "studio": "Ramnayudu Studio",
    "movieCast": [
        "Prabhas",
        "Rana daggupati",
        "Anushuka shetty"
    ],
    "releaseYear": "2016",
    "poster": "bahubali.png",
    "posterUrl": "url"
}

Response:
{
    "movieId": 2,
    "title": "Bahubali the Begining",
    "director": "SS Rajamouli",
    "studio": "Ramnayudu Studio",
    "movieCast": [
        "Rana daggupati",
        "Prabhas",
        "Anushuka shetty"
    ],
    "releaseYear": 2016,
    "poster": "animal.png",
    "posterUrl": "http://localhost:9090/file/animal.png"
}

Get: http://localhost:9090/api/v1/movie/1

Get: http://localhost:9090/api/v1/movie/All

Put: http://localhost:9090/api/v1/movie/update/2

Don’t send file only send Json data

In formdata:
moveDtoObj: JsonData

-> if update the movie poster movie file with replace with new file

Delete: http://localhost:9090/api/v1/movie/delete/1

{
"movieId": 1,
"title": "jathirathanulu",
"director": "Anudeep",
"studio": "Marvel studio",
"movieCast": [
"Naveen",
"Rahul ramkrishna",
"shashi"
],
"releaseYear": "2020",
"poster": "default.png",
"posterUrl": "url"
}

Get: http://localhost:9090/api/v1/movie/allMoviesPage

Get: http://localhost:9090/api/v1/movie/allMoviesPages?pageNumber=1&pageSize=2

Get: http://localhost:9090/api/v1/movie/allMoviesPages?pageNumber=0&pageSize=2

Get: http://localhost:9090/api/v1/movie/allMoviesPageSort

Get: http://localhost:9090/api/v1/movie/allMoviesPageSort?sortBy=title&dir=dsc


ForgotPasswordController:

POST: http://localhost:9090/forgotPassword/verifyMail/useremail@gmail.com

POST: http://localhost:9090/forgotPassword/verifyOtp/396712/useremail@gmail.com


POST: http://localhost:9090/forgotPassword/changePassword/useremail@gmail.com

request:
{
    "password":"10001",
    "repeatPassword":"10001"
}

response: Password has been changed!

