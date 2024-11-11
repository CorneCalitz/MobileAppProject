# MobileAppProject

Steps to run mobile app with the database and server side code:

1. Install XAMPP control panel, make sure to install the Apache and mySQL module.
2. Once done,run both Apache web server and mySQL in the control panel. If mySQL indicates that the port is blocked, 
go to the services by searching it on your windows search bar and turn off the MYSQL80 service. This should open the port
for mySQL with XAMPP. There are other ways but this one is the simplest. If you ever plan on using mySQL workbench again,
just remember to turn on MYSQL80 again.
3. Click admin next to mySQL and you should open PHPMYADMIN. Here you will need to import the DB schema.
4. In the database directory there should be a .sql file named "trainingappschema". Import that and you should 
have access to the db schema with all of its data.
5. To run the server side php code, open the serverPHP file. Inside you should see a directory named app_php.
6. Move or copy folder to your htdocs folder. The path containing your php files should look like this - C:\xampp\htdocs\app_php
7. Use a text editor to open conn.php. Comment out the current db connection code and add your own servername, username etc.
8. One last step, in our code there is file called ApiClient.java. You will see it refer to a base url which is the server's url.
You will have to insert your local ipv4 address. To see what it is just go into the command prompt and type ipconfig.
9. If you followed all these steps your application should be able login when testing it in the emulator. Use one of the users with their password and it
should display their name when logging in as them. I highly recommend using postman to test the server calls.


Anyway here is a list of critical things to sort out:

- QUIZ FRAGMENT NEEDS TO BE DESIGNED AND IMPLEMENTED. This is our main feature that involves all of the business algorithms.
- RecyclerView onclick needs to display a fragment.

