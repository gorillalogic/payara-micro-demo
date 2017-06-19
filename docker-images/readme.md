# Docker Images
This folder allows you to get the different docker files to build the docker images used during the demo in the Lunch&Learn presentation. 

* java: It's a base docker images, with java installed and configured in a CentOS distribution
* payara-micro: It inherites from the java image, and it has the payara-micro server configured
* nginx: Nginx base docker image
* nginx-balancer: Docker file with the configuration to created a load balancer with nginx to test the clustering feature in payara-micro.
