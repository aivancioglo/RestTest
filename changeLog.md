## Next planned version
##### Features:
* New properties: `protocol`, `host`, `decoder_charset` and `encoder_charset`.
* New content type: `MULTIPART`.
* New dependency `xml-path` for xml response validation. 

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