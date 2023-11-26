# Project Setup Instructions

## Getting Started with Docker

1. **Initiate Docker Containers:**
    - Run the command: `docker compose up --build`.
    - This will set up all necessary containers.

2. **Accessing Keycloak:**
    - Navigate to `localhost:8080/admin`.
    - Log in using both username and password as `Admin`.

3. **Creating a New Realm:**
    - Create a new realm in Keycloak.
    - Import the file from the repository located at `config/realm-export.json`.

4. **User Setup:**
    - Within this new realm, create a user with the role 'user'.

## Configuration Adjustments

1. **Generating New Client Secret:**
    - Go to `Clients -> m2olie_flutter_client -> Credentials`.
    - Click on `Client Secret Regenerate` to generate a new secret.

2. **Updating Flutter App:**
    - Update the build configuration of the Flutter app with the new secret.

3. **Modifying Policy Enforcer Configuration:**
    - Change the client secret in `src/main/resources/policy-enforcer.json`.

4. **Database Initialization Script:**
    - In `src/main/resources/data/sql/init.sql`, increment the number '1' by one to avoid errors.

5. **Restarting the App Container:**
    - Execute `docker-compose up -d --no-deps --build app` to restart the app container.
    - Important: Do not restart the Keycloak container as it is not persistent.

## Testing the Application

1. **Generating Access Token:**
    - Use the Flutter app to generate an access token.

2. **Testing API Endpoint:**
    - Test the endpoint `localhost:8090/api/practitioners` using Postman.
    - A successful request will return a 200 status code.

---

Please follow these steps carefully to ensure the proper setup and functioning of the project.# tpma_userDataAPI
