## Next planned version
##### Features:
* New properties: `protocol`, `host`, `decoder_charset` and `encoder_charset`.
* New content type: `MULTIPART`. 

## V. 1.0.3
##### Fixes:
* Fixed issue when `log_if_failed` is `true` and `soft_assertions` is `false` but passive log is not working.
* Fixed issue when `soft_assertions` is `false` but all asserts are performing.
* Fix issue when all user properties are setting after request. 

## V. 1.0.2
##### Fixes:
* Fixed null pointer exception when `Content-Lenght` header is `null`.
* Fixed bug, when assertion was not stopping after first failure, when `soft_assertions` is `true`.

## V. 1.0.1
##### Fixes:
* Added `resttest.propertie` as default property file. It fixing null pointer exception when you don't have this file in your project.
* Response logger don't log body anymore if it is empty.
* New dependency added: `hamcrest-library`.