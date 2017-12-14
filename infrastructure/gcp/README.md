### Objective:
	
	Install and setup your VM instance for google cloud platform.
	Create VM Instance 

### Getting Started

	Install and setup your VM instance for google cloud platform.
	link:
	https://cloud.google.com/deployment-manager/docs/step-by-step-guide/

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
	open vm.yaml file
	 > replace csye-6225-webapp-183602 with your project_ID 
	 > save file
	make sure deployment-manager API is enabled
	
	gcloud deployment-manager deployments create firstDeployment --config cloudDeployment.yaml
	gcloud deployment-manager deployments describe vm-1
	gcloud deployment-manager resources list --deployment vm-1
	gcloud deployment-manager deployments delete deployment-1

    
### Author and Team Members

    * Varsha Bhanushali
    * Shrikant Mudholkar
    * Manish Patil
    * Rahul Chandra





