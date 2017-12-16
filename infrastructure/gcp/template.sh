## create vm instance
gcloud compute instances create vm-1 \
--image https://www.googleapis.com/compute/v1/projects/ubuntu-os-cloud/global/images/family/ubuntu-1604-lts \
--zone us-east1-b

## create firewall
gcloud compute firewall-rules create vm1-allow-egress-tcp-port80-to-vm1 \
    --network a-new-network \
    --action allow \
    --direction egress \
    --rules tcp:22 \
    --destination-ranges 0.0.0.0 \
    --priority 60

 ## vm instance template
 gcloud compute instance-templates create vminstance-template \
        --image-project ubuntu-os-cloud \
        --boot-disk-size 250GB \
        --network a-new-network \
        --region us-central1

 ## managed group instance
   gcloud compute instance-groups managed create example-group \
   --base-instance-name vm-instance \
   --size 3 \
   --template vminstance-template \
   --zone us-east1-b \


  gcloud compute instances create vm1 \
      --image-project ubuntu-os-cloud \
             --boot-disk-size 250GB \
             --network a-new-network \
             --region us-central
      --metadata startup-script="#! /bin/bash
        sudo apt-get update
        sudo apt-get install apache2 -y
        sudo service apache2 restart
        echo '<!doctype html><html><body><h1>www1</h1></body></html>' | tee /var/www/html/index.html
        EOF"
 gcloud compute instances create vm2 \
       --image-project ubuntu-os-cloud \
              --boot-disk-size 250GB \
              --network a-new-network \
              --region us-central
       --metadata startup-script="#! /bin/bash
         sudo apt-get update
         sudo apt-get install apache2 -y
         sudo service apache2 restart
         echo '<!doctype html><html><body><h1>www1</h1></body></html>' | tee /var/www/html/index.html
         EOF"
 gcloud compute instances create vm3 \
       --image-project ubuntu-os-cloud \
              --boot-disk-size 250GB \
              --network a-new-network \
              --region us-central
       --metadata startup-script="#! /bin/bash
         sudo apt-get update
         sudo apt-get install apache2 -y
         sudo service apache2 restart
         echo '<!doctype html><html><body><h1>www1</h1></body></html>' | tee /var/www/html/index.html
         EOF"


  gcloud compute firewall-rules create firewall-network \
      --target-tags network-lb-tag --allow tcp:80


  gcloud compute instances list


  ##Create a static external IP address for your load balancer.

  gcloud compute addresses create network-lb-ip-1 \
      --region us-central1

  gcloud compute http-health-checks create basic-check

  ##Add a target pool in the same region as your virtual machine instances. Use the health check created in the prior step for this target pool. Target pools require a health check service in order to function.

  gcloud compute target-pools create www-pool \
      --region us-central1 --http-health-check basic-check
  Add your instances to the target pool

  gcloud compute target-pools add-instances www-pool \
      --instances vm1,vm2,vm3 \
      --instances-zone us-central1-b

  gcloud compute forwarding-rules create vm-rule \
      --region us-central1 \
      --ports 80 \
      --address network-lb-ip-1 \
      --target-pool vm-pool
