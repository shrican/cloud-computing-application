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
