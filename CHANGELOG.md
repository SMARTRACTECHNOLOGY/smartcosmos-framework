# Release Notes

## Release 2.12.1 (January 1, 1970)

### New Features

### Bugfixes / Improvements

- new error result code for parse errors: `ERR_PARSE_ERROR`
- new general error result code `ERR_LIBRARY_CANNOT_DELETE_ELEMENT` to resolve conflict between `ERR_LIBRARY_CANNOT_DELETE_ELEMENT_WITH_CHILDREN` and `ERR_LIBRARY_CANNOT_DELETE_ELEMENT_WITH_RELATIONSHIPS`
- `Result.translate()` can translate all results defined in `Result.java` now
- more fine-grained exception handling for input parsing in `AbstractRequestHandler`
- new `objectInteractionSessionUrn` field for create interaction request (cf. `objectUrn` and `object`)
- more robust `hashCode()` methods in POJO classes
- improved input validation for interactions
