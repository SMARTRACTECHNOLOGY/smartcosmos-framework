# SMART COSMOS Framework Release Notes

## UNRELEASED

### IMPORTANT CONFIGURATION NOTE

See the note below about why we changed the library hierarchy configuration.
We have changed it again, since the ordering of library elements was not remaining
consistent in environments where the objects.yml file was being generated, e.g.,
Ansible playbooks(OBJECTS-616). The only difference is the dash before the individual map entries.

**OLD VERSION:**

```
libraryHierarchy:
  PageEntry: true
  Page: false
  ChapterSection: false
  Chapter: false
  Book: false
  Shelf: false
  Library: false
```
**NEW VERSION:**

```
libraryHierarchy:
  - PageEntry: true
  - Page: false
  - ChapterSection: false
  - Chapter: false
  - Book: false
  - Shelf: false
  - Library: false
```

### New Features

* OBJECTS-593 Add a Result (JSON response) for unsupported entity reference types

### Bugfixes / Improvements

* OBJECTS-616 LibraryElement configuration ignoring order in Ansible config generation

## Release 2.13.0 (February 18, 2016)

### IMPORTANT CONFIGURATION NOTE

Configuration of the Library Element hierarchy has been improved.
Instead of two separate lists for (String) Library Element names and (Boolean)
Library Element attachment capabilities, there is now a single map, mapping names
to attachment capabilities.

**OLD VERSION:**

```
libraryHierarchy:
  - PageEntry
  - Page
  - ChapterSection
  - Chapter
  - Book
  - Shelf
  - Library

libraryLinkFlags:
  - true
  - true
  - true
  - true
  - true
  - true
  - true
```

**NEW VERSION:**

```
libraryHierarchy:
  PageEntry: true
  Page: false
  ChapterSection: false
  Chapter: false
  Book: false
  Shelf: false
  Library: false
```

### New Features

* No new features are added in this release.

### Bugfixes & Improvements

* change inheritance of `User` from `DomainResource` to `AccountDomainResource` (OBJECTS-322)
* added a reusable compound validation constraint annotation for realms (OBJECTS-568)
* added default values for `TimelineEntry` (OBJECTS-321)
* OBJECTS-539 Extract devices from Objects into separate "Device Extension"
* OBJECTS-582 Java Client `UpsertCommand` and `GetCollectionCommand` does not catch `ResourceException`
* OBJECTS-584 Add method to create ResponseEntity from Result
* OBJECTS-585 MetadataClient throws ServiceException when looking up by key
* OBJECTS-586 MetadataCodec does not support `Custom` MetadataDataType
* OBJECTS-592 Look up (all) Custom metadata with Full view returns internal server error
* SCE-5 Archetype needs update
* SCE-12 Add Maven Shade Plugin to Extension Starter
* PROFILES-394 Fix library config so that level name and attachment capability are on one line

## Release 2.12.1 (January 21, 2016)

### New Features

* No new features are added in this release.

### Bugfixes / Improvements

- OBJECTS-552 Improve Extension Configuration Exception Handling: if reading extension configuration fails, a `RuntimeException` is thrown
- OBJECTS-570 Conflict Between Implementation and Documentation: User names optional vs. required
- OBJECTS-580 UserClient throws ServiceException when password was changed successfully
- new error result code for parse errors: `ERR_PARSE_ERROR`
- new general error result code `ERR_LIBRARY_CANNOT_DELETE_ELEMENT` to resolve conflict between `ERR_LIBRARY_CANNOT_DELETE_ELEMENT_WITH_CHILDREN` and `ERR_LIBRARY_CANNOT_DELETE_ELEMENT_WITH_RELATIONSHIPS`
- `Result.translate()` can translate all results defined in `Result.java` now
- more fine-grained exception handling for input parsing in `AbstractRequestHandler`
- new `objectInteractionSessionUrn` field for create interaction request (cf. `objectUrn` and `object`)
- more robust `hashCode()` methods in POJO classes
- improved input validation for interactions
- validation constraints in `User` and `Registration` POJO classes
