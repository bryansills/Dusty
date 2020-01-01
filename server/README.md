# Dusty Server
## Setup
Remove the `.example` suffix from `src/main/java/ninja/bryansills/dusty/server/BuildConfig.kt.example` and fill out the object with valid values

## Running
### Locally
This app is packaged using [Docker](docker.com), so if you don't have that set up, [follow these instructions](https://medium.com/@yutafujii_59175/a-complete-one-by-one-guide-to-install-docker-on-your-mac-os-using-homebrew-e818eb4cfc3).
If your Docker virtual machine is stopped, execute these commands to get it back up and running:

```
$ docker-machine start default
$ docker-machine env default
$ eval $(docker-machine env default)
```

Once your Docker virtual machine is up and running, build the server by executing the following command within the root of this repository:

```
$ ./gradlew :server:build
```

After the build finishes, run the following Docker commands from within the `./server/` folder of the repository:

```
$ docker build -t dusty-server .
$ docker run -m512M --cpus 2 -it -p 8080:8080 --rm dusty-server
```

(Make this step better) The server should now be up and running. You can access from the `8080` port at the IP address that was outputted as a result of the `$ docker-machine env default` command.
Once you are done, make sure to stop the Docker virtual machine by running the following command:

```
$ docker-machine stop default
```

### Debugging locally
TODO

### Pushing to Heroku
TODO