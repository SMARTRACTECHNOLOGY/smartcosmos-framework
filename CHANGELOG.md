# Release Notes

## Release {version} ({date})

### New Features

### Bugfixes / Improvements

- OBJECTS-552 Improve Extension Configuration Exception Handling: if reading extension configuration fails, a `RuntimeException` is thrown
- OBJECTS-570 Conflict Between Implementation and Documentation: User names optional vs. required
- new error result code for parse errors: `ERR_PARSE_ERROR`
- new general error result code `ERR_LIBRARY_CANNOT_DELETE_ELEMENT` to resolve conflict between `ERR_LIBRARY_CANNOT_DELETE_ELEMENT_WITH_CHILDREN` and `ERR_LIBRARY_CANNOT_DELETE_ELEMENT_WITH_RELATIONSHIPS`
- `Result.translate()` can translate all results defined in `Result.java` now
- more fine-grained exception handling for input parsing in `AbstractRequestHandler`
- new `objectInteractionSessionUrn` field for create interaction request (cf. `objectUrn` and `object`)
- more robust `hashCode()` methods in POJO classes
- improved input validation for interactions
- validation constraints in `User` and `Registration` POJO classes
