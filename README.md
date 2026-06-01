# Array Task — Java Core Kick

A Maven project implementing entity, service, reader, parser, validator, and factory
components around an `int[]`-wrapping entity class.

---

## Project Structure

```
array-task/
├── data/
│   └── arrays.txt          ← input file (valid + invalid lines)
├── logs/                   ← created automatically at runtime
├── src/
│   ├── main/
│   │   ├── java/com/epam/array/
│   │   │   ├── entity/       AbstractArray, IntArray
│   │   │   ├── exception/    ArrayException (custom)
│   │   │   ├── factory/      ArrayCreator (interface), IntArrayCreator
│   │   │   ├── parser/       LineParser (interface), IntLineParser
│   │   │   ├── reader/       DataReader (interface), FileDataReader
│   │   │   ├── service/      ArrayStatService, ArraySortService (interfaces)
│   │   │   │   └── impl/     ArrayStatServiceImpl, ArraySortServiceImpl
│   │   │   ├── validator/    DataValidator (interface), ArrayDataValidator
│   │   │   └── Main.java
│   │   └── resources/
│   │       └── log4j2.xml
│   └── test/
│       ├── java/com/epam/array/
│       │   ├── service/      ArrayStatServiceTest, ArraySortServiceTest
│       │   ├── parser/       IntLineParserTest
│       │   └── validator/    ArrayDataValidatorTest
│       └── resources/
│           └── log4j2-test.xml
└── pom.xml
```

---

## How to Run

1. **File → Open** in IntelliJ IDEA → select the `array-task` folder.
2. Wait for Maven to download dependencies (bottom status bar).
3. Right-click `Main.java` → **Run 'Main.main()'**.

To run tests: right-click any test class → **Run**, or execute `mvn test` in the terminal.
