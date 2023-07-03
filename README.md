# JAVA-SWING-CRUD-DATABASE
A JAVA Swing GUI app to implements some CRUD process to mysql database.

This project contain some core features :
- Add user input to database.
- Edit data in database.
- Delete data in database.
- Search query in database based on name column.

This app interface created for Indonesian user.

How to use this project :
1. Create database with 5 column inside "pelanggan" table :
  - KODE_PELANGGAN (with datatype int and set primary key)
  - NAMA (with datatype String)
  - ALAMAT (with datatype String)
  - KOTA (with datatype String)
  - HP (with datatype int)

2. Clone this project with :
  - git clone https://github.com/R13TechNewbie/JAVA-SWING-CRUD-DATABASE.git
  or just download the zip files and import this project to NetBeans.
  
3. Edit the following line in connectionDB.java (inside swing package) :
  - String url="jdbc:mysql://localhost:3306/data"; //change this to your own database name.
  - String user="root"; //change to the username that you needed to connect to your database.
  - String pass="R13TechNewbie"; //change to your database password.
  
4. Run the project.
# crud-swing
