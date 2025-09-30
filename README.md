# 📘 EI Study Repository

---

## 🗂 Repository Structure

### Exercise-1 – Design Patterns
Covers key design patterns in Java:

#### Behavioral Patterns
- Command Pattern  
- Strategy Pattern  

#### Creational Patterns
- Singleton Pattern  
- Factory Pattern  

#### Structural Patterns
- Decorator Pattern  
- Proxy Pattern  

---

### Exercise-2 – Astronaut Daily Schedule Organizer
Simulates an astronaut daily schedule system where users can manage tasks:

#### Features
- Add Task  
- Remove Task  
- View Tasks  
- Edit Task  
- Mark Task as Completed  
- View Tasks by Priority  
- View Tasks by Shortest Time  
- Check Notifications  
- Generate Task Reports  

#### Design Patterns Used
1. **Singleton Pattern**  
   - Ensures only one instance of `ScheduleManager` exists to handle all tasks.  

2. **Observer Pattern**  
   - Used for task notifications to alert astronauts about upcoming tasks.  

3. **Factory Pattern**  
   - `TaskFactory` is used to create different types of tasks.  

---

## ⚙️ Getting Started

### Prerequisites
- Java JDK 11 or above  
- IDE (IntelliJ IDEA, Eclipse, VS Code with Java extensions)  
- Git  

### Clone the Repository

```bash
git clone https://github.com/Inzamamulhaq01/EI-Exercises.git
cd EI-Exercises

```
to run exercise-2
open in terminal:
```bash
javac -d build\classes -sourcepath src src\exercise_2\App.java
java -cp build\classes exercise_2.App
