# How does quarkus-oidc verify the JWT?

The goal of the sequel to Ricardo Mello's very good [tutorial](https://itnext.io/quarkus-with-angular-secured-with-keycloak-pt1-d1c00a4923b8) on Quarkus, Angular and Keycloak was to find out how the Quarkus backend verifies the JWT it receives from the Angular frontend in the Authorization header before passing it on to the resource.

The quarkus-oidc dependency is indeed sufficient to trigger the verification of the JWT automatically. 

If you set the log level of io.quarkus.oidc.runtime to DEBUG, and send a request with an expired JWT to the backend (e.g. with POSTMAN), you can see how it is verified:

```
2023-07-02 10:48:55,732 DEBUG [io.qua.oid.run.OidcIdentityProvider] (vert.x-eventloop-thread-2) Verifying the JWT token with the local JWK keys
2023-07-02 10:48:55,817 DEBUG [io.qua.oid.run.OidcProvider] (vert.x-eventloop-thread-2) Verification of the token issued to client backend has failed: The JWT is no longer valid - the evaluation time NumericDate{1688287735 -> 02.07.2023, 10:48:55 MESZ} is on or after the Expiration Time (exp=NumericDate{1688281188 -> 02.07.2023, 08:59:48 MESZ}) claim value.
```

Using the URL [http://localhost:8188/realms/daily-quotes/.well-known/openid-configuration](http://localhost:8188/realms/daily-quotes/.well-known/openid-configuration), quarkus-oidc fetches the jwks.url and thus the public key for signature verification, as well as the issuer of the JWT. No additional configuration is required for this.

## Links

[OpenID Connect (OIDC) Bearer authentication ](https://quarkus.io/guides/security-oidc-bearer-authentication-concept)

## Minimal configuration

The dependency on quarkus-keycloak-authorization was deleted because it is not needed in the current source code.

To actually show that the status code 401 is due to the JWT having expired, the log level for io.quarkus.oidc.runtime was set to DEBUG:

```
quarkus.log.category."io.quarkus.oidc.runtime".level=DEBUG
```