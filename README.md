# GitHub Info API

A simple Spring Boot 3.5 application built with Java 21 that exposes an HTTP API for listing **non-fork** repositories of a given GitHub user, along with branches and their latest commit SHA.

This is a recruitment task solution for Atipera.

---

## ✅ Features

- Fetches public GitHub repositories for a user (excluding forks)
- Returns:
  - repository name
  - owner login
  - branches with:
    - name
    - last commit SHA
- Handles non-existent users with a proper 404 error
- No WebFlux / WebClient used – only `RestClient` from Spring 6
- Includes a single integration test (happy path)

---

## 📦 Tech Stack

- Java 21
- Spring Boot 3.5
- Spring REST Client (non-blocking, but not reactive)
- Gradle
- JUnit 5

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/github-info-api.git
   cd github-info-api
