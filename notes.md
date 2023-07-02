# How does quarkus-oidc verify the JWT?

The goal of the sequel to Ricardo Mello's very good [tutorial](https://itnext.io/quarkus-with-angular-secured-with-keycloak-pt1-d1c00a4923b8) on Quarkus, Angular and Keycloak was to find out how the Quarkus backend verifies the JWT it receives from the Angular frontend in the Authorization header before passing it on to the resource.

In the original version, there is still no configuration that triggers the validation of the JWT. You can test this by fetching a valid JWT from the web application daily quotes and sending it to the backend as an authorisation header after the validity has expired, e.g. using Postman. The status code 401 was expected, but this was not the case.

It turned out that only little configuration was missing to get a status code 401 in case of an expired JWT.


## Links

[OpenID Connect (OIDC) Bearer authentication ](https://quarkus.io/guides/security-oidc-bearer-authentication-concept)

## Minimal configuration

The dependency on quarkus-keycloak-authorization was deleted because it is not needed in the current source code.

The following configuration parameter was added:

```
smallrye.jwt.jwks.url=http://localhost:8188/realms/daily-quotes/protocol/openid-connect/certs
```

To actually show that the status code 401 is due to the JWT having expired, the log level for io.quarkus.oidc.runtime was set to DEBUG:

```
quarkus.log.category."io.quarkus.oidc.runtime".level=DEBUG
```