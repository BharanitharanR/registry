# registry
Custom service registry 

- This service is an attempt to be a service registry, which is wired into the services that are created and having the dependency of the framework.
Thereby this service becomes eventually what I am planning to call as the "Platform" ( Which is a staged group of microserices that be built into a unified single docker ).

- Exciting things happening watch out for further updates

- Untill then the sequence to build and run the service is

  ** Test cases arent ironed out yet **
  
  ` mvn clean install -DskipTests`

  `cd registry`

  `mvn docker:start`

  
