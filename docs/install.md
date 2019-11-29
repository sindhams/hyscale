# Run on macOS using hyscale jar binary
## Usage Pre-Requisites:
* JDK version 11 and above
* Check Deployment pre-requisites [here](https://github.com/hyscale/hyscale)
* Download the [hyscale jar](https://github.com/hyscale/hyscale/releases/download/v0.9.1/hyscale.jar) to your local machine
## Usage:
Use `java -jar </path/to/hyscale.jar> <commands>` ,  For commands refer [here](https://github.com/hyscale/hyscale/blob/v0.9.1/docs/hyscale-commands-reference.md) by replacing `hyscale` with `java command`.  

```sh 
Example :  java -jar /my/home/hyscale.jar deploy service -f myservice.hspec.yaml -n my-namespace -a my-app
```
