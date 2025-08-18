# Project Final: Smart Motion Detection System

**University:** University of Science, VNU-HCM

**Course:** Physics for Information Technology

**Instructors:** Mr. Cao Xuan Nam, Mr. Dang Hoai Thuong

**Semester:** 2024-2025, Semester 3

**Smart Motion System** is an IoT-based security solution that detects motion, 
captures images, and sends real-time alerts. It supports remote alarm control,
human detection, and web-based monitoring with cloud storage.

## Group Information

**Class:** 23CLC05

**Group members:**
1. Nguyen Hoang Anh Khoa - 23127015
2. Nguyen Hoang Dang - 23127166
3. Ngo Tran Quang Dat - 23127341
## 🚀 Features

### ✅ Authentication & Authorization

- User login & registration
- Role-based access control (Manager, Operator)
### 📊 Management & Monitoring
- Real-time monitoring of motion sensors  
- Display messages on LCD screen  
- Control buzzers (turn on/off)  
- Adjust servo motor angle  
### 🎯 Motion Sensor Management
- Time-based motion sensor scheduling  
- Detect human motion or other objects  
- Upload images for motion sensor events  
### 🧑‍💼 Account Management
- Create, edit, delete, and search user accounts
### 🌐 Responsive UI

- Clean and responsive design built with Bootstrap
- Supports desktop views

## 🛠 Tech Stack

### 💻 Backend

- Java 8
- Spring Boot, Spring MVC, Spring Security
- JPA / Hibernate, Spring Data JPA
- RESTful API
- MQTT protocol for real-time communication
### 💻 Frontend

- HTML, CSS, JavaScript
- Bootstrap
- JSP, jQuery, AJAX

### 🗄 Database

- MySQL (for user accounts data)
- Firebase Realtime Database (motion sensor data)

### 🔧 Architecture

- 3-layer architecture (Controller - Service - Repository)
- Clear separation of concerns for scalability and maintainability

## 📦 Setup Instructions
### Prerequisites
Make sure you have the following installed:
- **Apache Maven 3.9.6** (for building the project)

   +Download version 3.9.6 from: [https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip](https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip)
 
   +Config Apache Maven in Windows Environment Variables: https://mkyong.com/maven/how-to-install-maven-in-windows/
- **MySQL** (for user accounts database)
- **Apache Tomcat 8.5.34** (for running the application)
  Download version 8.5.34 from:
  [https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.34/bin/](https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.34/bin/)
- **IntelliJ IDE**A (recommended IDE for Java development)
- **OpenCV(version 4.5.5): https://opencv.org/releases/page/2/
- Firebase account (for motion sensor data storage)
    ### Create a Firebase Project
    - Go to: https://console.firebase.google.com
    - Click **"Add project"**
    - Enter a project name → click **Continue**
    - (Optional) Disable **Google Analytics** → click **Create project**
    - Wait for the setup to complete → click **Continue** to open the dashboard

    ### Enable Realtime Database
    - In the left-hand menu, go to **Build → Realtime Database**
    - Click **"Create Database"**
    - Choose a region → click **Next**
    - Select **"Start in test mode"** → click **Enable**
    ### Get `serviceAccountKey.json`
    - Go to **Project settings** (⚙️ icon) → **Service accounts** tab
    - Click **"Generate new private key"**
    - The file `serviceAccountKey.json` will be downloaded automatically
    ### Add the Key to Your Project
    Move the file to the following path: 
    ```
    src/main/resources/serviceAccountKey.json
    ```
## 📂 Project Structure

```
smart-motion-system/
├── arduino/                 # Arduino code (motion sensors, LED, buzzer, etc.)
├── esp32_cam/               # ESP32-CAM code (image capture and data transfer)
├── web/                     # Web application (data management and user interface)
│   ├── database/            # SQL scripts (database schema and sample data)
│   ├── src/main/            # Main source code of the web app
│   │   ├── java/com/javaweb/ # Backend Java (controller, service, repository, entity, config)
│   │   ├── resources/       # Configuration files (application.properties, logging configs, etc.)
│   │   └── webapp/          # Frontend (JSP pages, static resources, scripts, styles)
│   └── pom.xml              # Maven build and dependency configuration
├── .gitignore               # Git ignore configuration
└── README.md                # Project documentation
```
## 🚀 How to Run

### 1. Arduino
1. Open the `arduino/` folder in Arduino IDE.
2. Connect the Arduino board via USB.
3. Select the correct **Board** and **Port** from the IDE.
4. Upload the code to the Arduino.
5. Verify that LEDs, buzzer, and motion sensors work properly.

### 2. ESP32-CAM
1. Open the `esp32_cam/` folder in Arduino IDE.
2. Install ESP32 board support (if not already installed).
3. Select **ESP32-CAM** as the board and the correct COM port.
4. Upload the code to the ESP32-CAM.
5. After reset, the ESP32-CAM should connect to Wi-Fi and be ready for image capture and transfer.

### 3. Web Application
#### 3.1 Clone the repository

```bash
git clone https://github.com/quangdat2511/smart-motion-system.git
```

#### 3.2 Open the folder web in IntelliJ IDEA
#### 3.3 Install OpenCV Dependency
Because OpenCV is not available in the default Maven Central repository, you need to manually install the .jar file into your local Maven repository.
-Locate the file opencv-455.jar in your OpenCV build directory: <path-to-opencv>/build/java/opencv-455.jar
- `<path-to-opencv>` is the actual directory where you extracted or built OpenCV.
-Open terminal at web folder and run the following command
mvn install:install-file ^
  -Dfile="<path-to-opencv>/build/java/opencv-455.jar" ^
  -DgroupId=org.opencv ^
  -DartifactId=opencv ^
  -Dversion=4.5.5 ^
  -Dpackaging=jar
-💡 On Linux/macOS, replace ^ with \ for line continuation.
#### 3.4 Set the Java language level

- Go to `File > Project Structure` (or press `Ctrl + Alt + Shift + S`)
- Under the **Project** tab:
    - Set **Language level** to: `8 - Lambdas, type annotations, etc.`
    - Set **Project SDK** to: `corretto-1.8 Amazon Corretto 1.8.0_452`

> This ensures the project uses Java 8, which is compatible with the source code and prevents issues such as missing `javax.xml.bind` classes found in later Java versions.

#### 3.5 Configure the MySQL database

- Open the file `database/insert_database.sql`, copy its contents, and run it in your MySQL DBMS.
- Update the `application.properties` file with your own MySQL credentials:
  ```properties
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  ```

#### 3.6 Import and Build the Project

##### a. Reload Maven Projects

1. Open the Maven tool window in IntelliJ (**View > Tool Windows > Maven**).
2. Click the **Reload All Maven Projects** button to sync dependencies from `pom.xml`.

##### b. Build the Project

Open the terminal and run the following command:

```bash
mvn clean install
```

> This will download all dependencies, compile the code, run tests, and package the application.

#### 3.7 Set up Tomcat 8.5.34 deployment in IntelliJ IDEA

1. Go to **Run > Edit Configurations...**
2. Click the `+` icon → select **Tomcat Server > Local**
3. In the **Server** tab, click **Configure...** and set the path to your local **Tomcat 8.5.34** installation.
4. In the **VM options:** field, paste:
   ```
  -Dfile.encoding=UTF-8
  -Djava.library.path=<path-to-opencv>\build\java\x64
   ```
5. Switch to the **Deployment** tab.
6. Click the `+` button → choose `spring-boot:war exploded` (recommended).
7. Click on **Application context** and leave it empty.
8. Configure the HTTP port:
    - By default, the HTTP port is set to **8080**.
    - If port 8080 is already in use, you can change it to another port (e.g., 8081) by modifying the **HTTP port** field.
9. Click **OK** to save the configuration.

#### 3.8 Run the Project

- Click the green **Run** button next to your Tomcat configuration in IntelliJ.

#### 3.9 Access the Application

Open your browser and go to:

```http
http://localhost:8080
```

> If you configured a different port in the Tomcat settings (e.g., 8081), replace `8080` with your custom port:
>
> ```http
> http://localhost:8081
> ```

This will load the main page of the application.

---

#### 3.10 Default Accounts

The system comes with a few pre-configured user accounts for testing purposes:

| Username   | Role     | Password |
| ---------- |----------| -------- |
| nguyenvana | MANAGER  | 123456   |
| nguyenvanb | OPERATOR | 123456   |
| nguyenvanc | OPERATOR | 123456   |
| nguyenvand | OPERATOR | 123456   |

> ⚠️ **Note:** These accounts are for demonstration and testing only. Do not use them in a production environment.

---

#### 3.11 How to Log In

1. Open your browser and visit: [http://localhost:8080/login](http://localhost:8080/login)
2. Enter one of the default usernames and the password `123456`.
3. After logging in:
   - MANAGER: Full access
   - OPERATOR: No access to account management

---

## 🧠 Future Improvements
- Add unit & integration tests
- Add user analytics dashboard

