# ThirdPartyApiExamples
An example set using the third party apis to access crew resources.
## Setup
- Edit the `config.json` file with your oAuth token and IDs
    ```
    {
         "oAuthToken": "<insert oAuth token here",
         "urlBase": "<insert base url here>",
         "enterpriseId": "<insert enterprise id here>",
         "organizationId": "<insert organization id here>",
         "userId": "<insert user id here>",
         "groupId": "<insert group id here>",
         "cardId": "<insert card id here>",
         "message": "This is a test message from the api demo."
    }
    ```
### To Run
1. `./gradlew clean build`
2. `java -jar build/libs/third-party-api-examples-1.0-SNAPSHOT.jar`
