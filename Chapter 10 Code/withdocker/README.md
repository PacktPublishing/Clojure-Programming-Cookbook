# withdocker

## to publish to docker

### Register a docker hub account.
### Run:

    docker login

This will add a file that looks like so to ~/.docker/config.json:

    {
        "auths": {
            "https://index.docker.io/v1/": {
                "auth": "somekey",
                "email": "your@emailaddress.com"
            }
        }
    }

### Then docker push

docker push yourname/yourimage

## to start your own repository

     docker run -d -p 5000:5000 --restart=always --name registry registry:2

     docker tag f625bc1305a0 localhost:5000/withdocker

     docker push localhost:5000/withdocker 

     docker run -it -p 8080:8080 localhost:5000/withdocker

     # to the outside world
     docker run -it -P -p 8080:8080 localhost:5000/withdocker 


     