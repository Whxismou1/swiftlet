
# Swiftlet HTTP Client 🕊️

A **lightweight and fluent HTTP client** built on top of Java’s modern `HttpClient` API.  
Swiftlet provides a simple, chainable syntax for making HTTP requests with minimal boilerplate.

---

## 🚀 Features
- Easy-to-use, fluent API  
- Supports **GET** and **POST** requests  
- Built-in JSON support (`Content-Type: application/json`)  
- Fully based on Java’s `HttpClient` (no external dependencies)  

---

## 🧩 Example Usage

```java
import com.example.Swiftlet;

public class Example {
    public static void main(String[] args) throws Exception {
        String response = Swiftlet.post("https://api.example.com/data")
                .json("{\"name\":\"John\"}")
                .header("Authorization", "Bearer TOKEN123")
                .asString();

        System.out.println(response);
    }
}
```

---

## 📦 Installation

Just include the compiled `.jar` file in your project, or copy the source files into your codebase:

```
src/
 └── com/example/
     ├── Swiftlet.java
     ├── Request.java
     └── RequestTypes.java
```

---

## ⚙️ Supported Methods

| Method | Description |
|--------|--------------|
| `Swiftlet.get(url)` | Sends an HTTP **GET** request |
| `Swiftlet.post(url)` | Sends an HTTP **POST** request |

---

## 🧠 Example with Custom Headers

```java
String html = Swiftlet.get("https://example.org")
        .header("Accept-Language", "en-US")
        .asString();
```

---

## 🧱 Requirements
- **Java 11+** (uses the built-in `java.net.http` package)

---