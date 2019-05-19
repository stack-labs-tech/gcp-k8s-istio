# GCP: Kubernetes and Istio workshop

## Access Worshop resources

Workshop resources can be found here:

https://stack-labs.gitlab.io/training/gcp-k8s-istio/gke-training/1.0.0/introduction.html

## How to build

This project contains all required files to provide the workshop, including:

* [documentation](documentation): the folder that contains the step-by-step instructions to go through the workshop
* [apps](apps) : the code of the various applications used in this workshop
* [slides](slides) : the slides used by the presentor during the workshop 

To build the site, you can use the following comamnd:
* bash: `docker run -v `pwd`:/antora --rm -t antora/antora site.yml`
* fish: `docker run -v (pwd):/antora --rm -t antora/antora site.yml`  
