## V. 1.3.0
##### Features:
* Static method `perform` is now deprecated. The new static method `repeat` was created in `RestTest` class to substitute deprecated `perform` method.
* The `CaseInsensitiveSubstringMatcher` and `RegexMatcher` classes are now deprecated. New `RestTestMatcher` class with matchers was created instead of it.
* New `isSorted` matcher was created in `RestTestMatcher` class.
* The new setter `jsonParam` is added. Usage example:
     ```Java
     post("endpoint.com",
        jsonParam("key", "value"));
     ```
     Will be converted to:
     ```Java
     post("endpoint.com",
         body("{\"key\":\"value\"}"));
     ```
* The `param` setter will automatically try to determine which parameter type (i.e. query, json, or form parameter) based on the HTTP method.

##### Fixes:
* Small bug with request/response log printing. 

## V. 1.2.0
##### Features:
* New setter: `schema`.
* Setter `jsonSchema` is deprecated now.

##### Fixes:
* Response and Request logs do not have separators when request body is empty. 

## V. 1.1.1
##### Fixes:
* Response headers log is printed incorrectly.

## V. 1.1.0
##### Features:
* New setters: `cookie`, `oauth1`, `oauth2`.
* New verifier: `cookie`.
* New properties: `protocol`, `host`, `decoder_charset` and `encoder_charset`.
* New content type: `MULTIPART`.
* The `perform` method in `RestTest` class is overloaded now.
* Class `Session` is deprecated now.
* New dependency `xml-path` for xml response validation.

##### Fixes:
* The request log was not working when Error/Exception was thrown before request execution.
* Connection/socket timeout lead to request log not working.
* The `noAuth` setter was not resetting OAuth 1.0/2.0.
* Repeater timeout do not interrupt unfinished request.

## V. 1.0.3
##### Fixes:
* Passive log was not working while `log_if_failed` is `true` and `soft_assertions` is `false`.
* All asserts were triggered when `soft_assertions` is `false`.
* User properties were setting after request execution. 
* Only first form param was shown in request log.

## V. 1.0.2
##### Fixes:
* Got null pointer exception when `Content-Lenght` header is `null`.
* Assertion was not stopping after first failure, when `soft_assertions` is `true`.

## V. 1.0.1
##### Fixes:
* Got null pointer exception when `resttest.properties` file was not included in your project.
* Response logger logged body when it is empty.
* Hamcrest matcher `any` is not working due to absence of `hamcrest-library` dependency.