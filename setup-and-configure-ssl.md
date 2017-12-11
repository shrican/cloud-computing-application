## Creating an SSL/TLS Certificate Using AWS Certificate Manager


* Sign into the AWS Management Console and open the ACM console at https://console.aws.amazon.com/acm/home. If the introductory page appears, choose Get Started. Otherwise, choose Request a certificate.

* On the Request a certificate page, type your domain name. For more information about typing domain names, see Request a Certificate.

* To add more domain names to the ACM Certificate, type other names as text	boxes open beneath the name you just typed.

* Choose Next.

* Choose DNS validation.

* Choose Review and request. Verify that the domain name and validation method are correct.

* Choose Confirm and request.

* On the Validation page, expand your domain information or choose Export DNS configuration to a file. If you expand your domain information, ACM displays the name and value of the CNAME record you must add to your DNS database to validate that you control the domain.
