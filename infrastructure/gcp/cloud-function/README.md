#Cloud Functions using the gcloud Command-Line Tool

1. Select or create a Cloud Platform project.
    * GO TO THE MANAGE RESOURCES PAGE
2. Enable billing for your project.
    * ENABLE BILLING
3. Enable the Cloud Functions API.
    * ENABLE THE API
4. Install and initialize the Cloud SDK.
5. Update and install gcloud components:
    * gcloud components update 
    * gcloud components install beta
6. Create a function in directory cloud-function
7. Create an index.js file in the cloud-function directory with the REST API.
8. Deploy a function
    * gcloud beta functions deploy helloGET --trigger-http
9. Test the function
10. When the function finishes deploying, take note of the httpsTrigger's url property or find it using the following command:
    * gcloud beta functions describe helloGET
    * It should look like this: https://[GCP_REGION]-[PROJECT_ID].cloudfunctions.net/helloGET.
    * Visit this URL in your browser. You should see a Hello World! message.