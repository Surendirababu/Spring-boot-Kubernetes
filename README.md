# Spring-boot-Kubernetes

Clone and Checkoout Project from Github
---------------------------------------------------------------------
git clone https://github.com/Surendirababu/Spring-boot-Kubernetes.git
cd Spring-boot-kubernetes
git checkout master

Build Project
---------------------
mvn clean install

create jar
-----------------
java -jar spring-boot-kubernetes-0.0.1.SNAPSHOT.jar 

Upload Project as Image to Container Registry
----------------------------------------------
mvn com.google.cloud.tools:jib-maven-plugin:build -Dimage=gcr.io/$GOOGLE_CLOUD_PROJECT/spring-boot-kubernetes-example:v1

Run Applicatiion using Docker Image
------------------------------------
docker run -ti --rm -p 8080:8080 'gcr.io/bold-site-315415/spring-boot-kubernetes-example:v1'

Login to Google Cloud Cluster
----------------------------------------------
gcloud container clusters get-credentials spring-boot-kubernetes-cluster --zone=us-central1-c


Create a deployment
--------------------------------
 kubectl create deployment spring-boot-kubernetes-example --image=gcr.io/bold-site-315415/spring-boot-kubernetes-example:v1

Lists Deployment Information
------------------------------
kubectl get deployment

NAME                             READY   UP-TO-DATE   AVAILABLE   AGE
-----------------------------------------------------------------------
spring-boot-kubernetes-example   1/1     1            1           17s

Deploy Image from Registry to Pod
---------------------------------------------------------
 kubectl run spring-boot-kubernetes-example --image=gcr.io/bold-site-315415/spring-boot-kubernetes-example:v1 --port=8080

Lists Pod Information
-----------------------------------
kubectl get pods

NAME                                              READY   STATUS    RESTARTS   AGE
-------------------------------------------------------------------------------------
spring-boot-kubernetes-example                    1/1     Running   0          12m
spring-boot-kubernetes-example-59877d454b-hkmvp   1/1     Running   0          3m42s

To Access the Application we need to create a service of type Load Balancer
------------------------------------------------------------------------------
kubectl expose deployment spring-boot-kubernetes-example --type=LoadBalancer --port=8080

Lists Services Information
-----------------------------
kubectl get services

NAME                             TYPE           CLUSTER-IP      EXTERNAL-IP    PORT(S)          AGE
-----------------------------------------------------------------------------------------------------
kubernetes                       ClusterIP      10.116.0.1      <none>         443/TCP          87m
spring-boot-kubernetes-example   LoadBalancer   10.116.15.106   35.194.14.85   8080:32660/TCP   108s


Access the application via the External Ip Exposed and the port
----------------------------------------------------------------
http://35.194.14.85:8080/resource/print

Scale the application 
---------------------------------
kubectl scale deployment spring-boot-kubernetes-example --replicas=3

kubectl get pods

NAME                                              READY   STATUS    RESTARTS   AGE
-----------------------------------------------------------------------------------
spring-boot-kubernetes-example                    1/1     Running   0          26m
spring-boot-kubernetes-example-59877d454b-86n99   1/1     Running   0          17s
spring-boot-kubernetes-example-59877d454b-bczw6   1/1     Running   0          17s
spring-boot-kubernetes-example-59877d454b-hkmvp   1/1     Running   0          17m
