# Fullstack Sports Event App

This is a full-stack MVP project that allows users to view sports events, authenticate via Google, and watch video highlights.

## 🔧 Tech Stack

- **Frontend**: Angular 17
- **Backend**: Spring Boot 3.4
- **Database**: H2 (for MVP, can be swapped with PostgreSQL)
- **Authentication**: Google OAuth2
- **Build Tool**: Maven
- **Deployment**: Combined on Spring Boot (one port)

## 🚀 Features

- Google Sign-In Authentication
- Event listings with team and video info
- Responsive UI with video background on login
- Secure API endpoints
- Backend serves Angular for production

## 📦 Run the Application

### Frontend (Angular)
```bash
cd frontend
npm install
ng build --configuration production
