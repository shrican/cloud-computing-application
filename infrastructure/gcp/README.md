### Objective:
	
	Install and setup your VM instance for google cloud platform.
	Create VM Instance 

### Getting Started

	Install and setup your VM instance for google cloud platform.

### Prerequisites

	google cloud compute account

### Installing A step by step series of examples that tell you have to get a environment running

	* Create an environment variable for the correct distribution:
		export CLOUD_SDK_REPO="cloud-sdk-$(lsb_release -c -s)"
	
	* Add the Cloud SDK distribution URI as a package source:
		echo "deb http://packages.cloud.google.com/apt $CLOUD_SDK_REPO main" | sudo tee -a /etc/apt/sources.list.d/google-cloud-sdk.list
	
	* Import the Google Cloud public key:
		curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
	
	* Update and install the Cloud SDK:
		sudo apt-get update && sudo apt-get install google-cloud-sdk
	
	* Run gcloud init to get started:
		gcloud init

### Executing Commands

	gcloud compute instances create csye-6225-project --zone us-east1-b --machine-type f1-micro --project csye-6225-webapp-183602
    
### Author and Team Members

    * Varsha Bhanushali
    * Shrikant Mudholkar
    * Manish Patil
    * Rahul Chandra





