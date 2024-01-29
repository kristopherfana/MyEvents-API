# SpringBoot Backend with MySQL Database

This guide will walk you through the process of setting up a SpringBoot backend application that uses MySQL as the database. We will also cover the installation of Visual Studio Code (VSCode) and the necessary extensions for Java development and SpringBoot dashboard.

## Prerequisites

Before starting, please ensure you have the following:

- Java Development Kit (JDK) installed
- MySQL database server installed and running
- Visual Studio Code (VSCode) installed

## Step 1: Create MySQL Database

First, you need to create a MySQL database for your application. Follow these steps:

1. Open your preferred MySQL client (e.g., MySQL Workbench, phpMyAdmin, command-line client).
2. Connect to your MySQL server using appropriate credentials.
3. Create a new database with the name `My-Events`

## Step 2: Configure application.properties

Next, you need to configure the application.properties file to connect your SpringBoot application to the MySQL database.

1. In your SpringBoot project, locate the `src/main/resources` directory.
2. Open the `application.properties` file (create one if it doesn't exist).
3. Replace the following configuration properties and replace the placeholders with your MySQL database information:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
   spring.jpa.hibernate.ddl-auto=update
   ```

   Note: Replace `your_database_name`, `your_mysql_username`, and `your_mysql_password` with your actual database name, username, and password respectively.

## Step 3: Install Java and SpringBoot Extensions in VSCode

1. Open Visual Studio Code (VSCode).
2. In the Extensions sidebar (Ctrl+Shift+X), search for "Java Extension Pack" and install it.
3. After installing the Java Extension Pack, search for "Spring Boot Extension Pack" and install it.

## Step 4: Starting the Application in VSCode

To start the SpringBoot application in VSCode, follow these steps:

1. Open your project in VSCode.
2. In the VSCode sidebar, navigate to your project's root folder.
3. Locate the `src/main/java` directory and open the `MyEvents.java` file.
4. Right-click on the main class file and select "Run" or use the keyboard shortcut (Ctrl+F5).
5. The SpringBoot application will start running, and you can see the logs in the VSCode's "Terminal" panel.

## Step 5: Accessing Swagger and Endpoints

Swagger is a tool for documenting and testing APIs. To access Swagger and view the different endpoints of your SpringBoot application, follow these steps:

1. Make sure your SpringBoot application is running.
2. Open a web browser and enter the following URL:
   ```
   http://localhost:8080/swagger-ui.html
   ```
3. The Swagger UI page will open, displaying a list of available endpoints and their documentation.

## Step 6: User Authentication

To create a new user and sign in using the Auth controller, follow these steps:

1. Open the Swagger UI page as explained in Step 5.
2. Expand the "auth-controller" section.
3. Click on the "POST /api/auth/signup" endpoint.
4. Click on the "Try it out" button.
5. In the "Request body" section, provide the necessary information for creating a new user (e.g., username, password).
6. Click on the "Execute" button.
7. If the user is successfully created, you will receive a response with a status code of 200 and a success message.

To sign in:

1. Expand the "auth-controller" section in the Swagger UI page.
2. Click on the "POST /api/auth/signin" endpoint.
3. Click on the "Try it out" button.
4. In the "Request body" section, provide the necessary information for signing in (e.g., username, password).
5. Click on the "Execute" button.
6. If the sign-in is successful, you will receive a response with a status code of 200 and a success messae in your response body.

Once you have signed in, you can navigate to other controllers and access their endpoints.

That's it! You have successfully set up a SpringBoot backend application with MySQL as the database. You can now explore the desired functionality using the available controllers and endpoints.
