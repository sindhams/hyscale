![HyScale](https://www.hyscale.io/wp-content/uploads/2019/01/hyscale-logo.png)

### The k8s App Deployment Tool 

Kubernetes (k8s) has emerged as the de facto container orchestration platform offering excellent abstraction over infrastructure. But app deployments and delivery mechanisms to k8s are still way too complex. Delivery tools should simplify things for a developer so that developers can focus on writing apps & building value. This is best achieved if all the complexity of deployment completely disappears!

HyScale is a starting point for how a simplified service spec can allow developers to easily deploy the various (micro-)services in their app to k8s without having to wade through k8s complexities and also without having to write or maintain hundreds of lines of manifest yamls.

Here is what you need to do:

<img src="docs/images/user-workflow.png" height="125" />

HyScale offers a declarative spec for k8s abstraction using which k8s manifests & docker files are automatically generated, docker images are built & pushed to the target docker registry, and the manifests are deployed to the k8s cluster resulting in a URL.

Here is a glimpse of what HyScale does when you invoke it

<img src="docs/images/inside-hyscale.png" height="400" />

To get started, install hyscale as per the below instructions & follow the [tutorial](https://www.hyscale.io/tutorial/get-started/) to deploy your first app.

## Prerequisites
In order to deploy your service to k8s, you must have the following configurations and installations in place on your machine from which you wish to deploy your application.
1. Docker 18.09.x or above. Your Linux user should be part of the docker group and `docker.sock` should be present at /var/run/docker.sock (Default location) 
2. Kubernetes authentication credentials kubeconfig file having the cluster token placed at $HOME/.kube/config
3. Image registry credentials at $HOME/.docker/config.json . Make sure `config.json` has the latest auth creds by logging into the image registry using `docker login` prior to deployment.

If you do not have access to a kubernetes cluster and wish to deploy your application to a local cluster on your machine, you could try setting up [minikube](https://kubernetes.io/docs/tasks/tools/install-minikube/) or [kind](https://github.com/kubernetes-sigs/kind).

## Installation

Open your terminal and enter the following:

```sh
curl -sSL http://get.hyscale.io | bash
```
Verified on CentOS, Ubuntu and Debian Linux. For macOS, refer to the steps [here](docs/install.md) and Windows installer coming soon!

## Deploying to k8s

### Preparing your first service spec (hspec)

Here is a basic service spec for deploying tomcat (without any application). To get started with more options see the [tutorial](https://www.hyscale.io/tutorial/get-started/).

```yaml
name: myservice
image:
    registry: registry.hub.docker.com/library
    name: tomcat
    tag: 8.5.0-jre8
 
volumes:
    - name: tomcat-logs-dir
      path: /usr/local/tomcat/logs
      size: 1Gi
      storageClass: standard
 
external: true
ports:
  - port: 8080/tcp
    healthCheck:
       httpPath: /docs/images/tomcat.gif

```

### Deploy the service

**To deploy, invoke the hyscale deploy command:**
    
```sh
hyscale deploy service -f `<myservice.hspec.yaml>` -n `<my-namespace>` -a `<my-app-name>`
```

**To view the status of your deployment:**

```sh
hyscale get service status -s `<myservice>` -n `<my-namespace>` -a `<my-app-name>`
```

**To view logs:**
```sh
hyscale get service logs -s `<myservice>` -n `<my-namespace>` -a `<my-app-name>`
```

For all possible commands, see the [command reference](docs/hyscale-commands-reference.md).

### Contributing

If you wish to contribute, see the architecture & contributor documentation [here](docs/contributor-guide.md).
