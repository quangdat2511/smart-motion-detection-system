# 🏠 Smart Motion System


## 🚀 Features

### ✅ Authentication & Authorization

- User login & registration
- Role-based access control (Manager, Staff, User)

### 🧑‍💼 Account Management

- Create, edit, delete, and search user accounts
- Permission-based operation control

### 🌐 Responsive UI

- Clean and responsive design built with Bootstrap
- Supports desktop views

## 🛠 Tech Stack

### 💻 Backend

- Java, Spring Boot, Spring MVC, Spring Security
- JPA / Hibernate, Spring Data JPA
- RESTful API design

### 💻 Frontend

- HTML, CSS, JavaScript
- Bootstrap
- JSP, jQuery, AJAX

### 🗄 Database

- MySQL

### 🔧 Architecture

- 3-layer architecture (Controller - Service - Repository)
- Clear separation of concerns for scalability and maintainability

## 📦 Setup Instructions

### 1. Clone the repository

```bash
git clone https://github.com/quangdat2511/smart-motion-system.git
```

### 2. Open the project in IntelliJ IDEA

### 3. Set the Java language level

- Go to `File > Project Structure` (or press `Ctrl + Alt + Shift + S`)
- Under the **Project** tab:
  - Set **Language level** to: `8 - Lambdas, type annotations, etc.`
  - Set **Project SDK** to: `corretto-1.8 Amazon Corretto 1.8.0_452`

> This ensures the project uses Java 8, which is compatible with the source code and prevents issues such as missing `javax.xml.bind` classes found in later Java versions.

### 4. Configure the MySQL database

- Open the file `database/insert_database.sql`, copy its contents, and run it in your MySQL DBMS.
- Update the `application.properties` file with your own MySQL credentials:
  ```properties
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  ```

### 5. Set up Tomcat 8.5.34 deployment in IntelliJ IDEA

1. Go to **Run > Edit Configurations...**
2. Click the `+` icon → select **Tomcat Server > Local**
3. In the **Server** tab, click **Configure...** and set the path to your local **Tomcat 8.5.34** installation, click **VM options:** and paste -Dfile.encoding=UTF-8
5. Switch to the **Deployment** tab
6. Click the `+` button → choose `spring-boot:war exploded` (recommended)
7. Click on Application context and leave it empty
8. Make sure the HTTP port is set to `8080`
9. Click **OK** to save the configuration

> If you don't have Tomcat installed, download version 8.5.34 from: [https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.34/bin/)

### 6. Run the project

- Click the green **Run** button next to your Tomcat configuration.

## 7. Access the Application

Open your browser and go to:

```http
http://localhost:8080
```

This will load the main page of the application.

---

## 8. Default Accounts

The system comes with a few pre-configured user accounts for testing purposes:

| Username   | Role     | Password |
| ---------- |----------| -------- |
| nguyenvana | MANAGER  | 123456   |
| nguyenvanb | OPERATOR | 123456   |
| nguyenvanc | OPERATOR | 123456   |
| nguyenvand | OPERATOR | 123456   |

> ⚠️ **Note:** These accounts are for demonstration and testing only. Do not use them in a production environment.

---

## 9. How to Log In

1. Open your browser and visit: [http://localhost:8080/login](http://localhost:8080/login)
2. Enter one of the default usernames and the password `123456`.
3. After logging in:
   - MANAGER: Full access
   - OPERATOR: No access to user management

---

## 🧠 Future Improvements
- Add unit & integration tests
- Add user analytics dashboard

## 🤝 Author

**Ngo Tran Quang Dat** – [LinkedIn](https://linkedin.com/in/ntqdat) | [Email](mailto\:ngotranquangdat2511@gmail.com)

