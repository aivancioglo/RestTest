## V. 1.0.2
##### Fixes:
* Fixed null pointer exception when `Content-Lenght` header is `null`.
* Fixed bug, when assertion was not stopping after first failure, when `soft_assertions` is `true`.

## V. 1.0.1
##### Fixes:
* Added `resttest.propertie` as default property file. It fixing null pointer exception when you don't have this file in your project.
* Response logger don't log body anymore if it is empty.
* New dependency added: `hamcrest-library`.