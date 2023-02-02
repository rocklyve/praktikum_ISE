# Static Code Analysis by David Laubenstein

## Introduction

// TODO: fill

## Installation
// TODO: fill

## Usage
// TODO: fill

## Rules

// TODO: fill

### Source + Tools

// TODO: fill

### Rule table

| Number | Rule | Sonarqube (https://rules.sonarsource.com/java) | Link | Note | PMD (https://pmd.github.io/latest/pmd_rules_java.html) | Link | Note |
|---|---|---|---|---|---|---|---|
|  |  |  |  |  |  |  |  |
| 1 | Visibility as low as possible for methods / attributes (private > public > protected) (known exemptions: - Constructors of utility classes must always be private - Constructors of abstract classes can be protected - Constants can also be public) |  |  |  |  |  |  |
| 2 | Code duplication | Methods should not have identical implementations | 4144 | Does not find example, only simple code duplications | :x: N/A |  |  |
| 3 | Code duplication: Repetitions Fixable by Inheritance | :x: N/A |  |  | :x: N/A |  |  |
| 4 | Inheritance instead of Enums | :x: N/A |  |  | :x: N/A |  |  |
| 5 | Operations instead of domain | :x: N/A |  |  | :x: N/A |  |  |
| 6 | Hardcoded logic | :x: N/A |  |  | :x: N/A |  |  |
| 7 | Stringreferenzen | Don’t think that’s possible |  |  |  |  |  |
| 8 | RawType | Raw types should not be used | 3740 |  |  |  |  |
| 9 | Exceptions for control flow | :x: N/A |  | Indicator can be an empty catch clause… this can be detected | :x: N/A |  |  |
|  | Empty Catch Block |  |  |  | EmptyCatchBlock |  |  |
| 10 | Try/catch Blöcke | :x: N/A |  |  |  |  |  |
| 11 | Unspecified Error Message | :x: N/A |  |  | :x: N/A |  |  |
| 12 | Wrong Loop Type | :x: N/A |  |  | :x: N/A |  |  |
| 13 | Unnecessary complexity | :x: N/A |  |  |  |  |  |
|  |  |  |  |  |  |  |  |
| 14 | Clumsy Solution | :x: N/A |  |  | :x: N/A |  |  |
| 15 | Parsing Integer Values | :x: N/A |  |  | :x: N/A |  |  |
| 16 | Utility Class | Utility classes should not have public constructors | 1118 | PMD could be more precise here | UseUtilityClass |  |  |
| 17 | Unsafe Cast | :x: N/A |  |  | :x: N/A |  |  |
| 18 | Empty Constructor | :x: N/A |  |  | UnnecessaryConstructor |  |  |
|  |  |  |  |  | UncommentedEmptyConstructor |  |  |
| 19 | Meaningless constants | :x: N/A |  |  | :x: N/A |  |  |
| 20 | Scanner (use multiple scanner objects for a single stream of forgetting to close the scanner) | :x: N/A |  |  | :x: N/A |  |  |
| 21 | Unused Element (Attribute or (helper) Method) | Unused private method should be removed | 1144 |  | UnusedPrivateMethod |  |  |
|  |  | Unused public methods -> :x: N/A |  |  | Unused public methods -> :x: N/A |  |  |
|  |  | Unused private fields should be removed | 1068 |  | UnusedPrivateField |  |  |
|  |  | Unused local variables should be removed | 1481 |  | UnusedLocalVariable |  |  |
| 22 | Missing `throws` statement in method signature | :x: N/A |  |  | :x: N/A |  |  |
| 23 | Public enum in class | :x: N/A |  | Maybe this could be solved easily with a custom rule | :x: N/A |  |  |
| 24 | Class of constants | :x: N/A |  | Maybe this could be solved easily with a custom rule | :x: N/A |  |  |
| 25 | System dependent line break | :x: N/A |  |  | :x: N/A |  |  |
| 26 | Trivial JavaDoc | :x: N/A |  |  | :x: N/A |  |  |
| 27 | Ssssssssssss | Method names should comply with a naming convention, default regex: `^[a-z][a-zA-Z0-9]*$:` | 100 |  |  |  |  |
| 28 | Don’t think that’s possible | Class names should comply with a naming convention | 101 |  |  |  |  |
|  | Maybe try with blacklists | Interface names should comply with a naming convention | 114 |  |  |  |  |
|  | Focus not that much | Constant names should comply with a naming convention | 115 |  |  |  |  |
|  |  | Field names should comply with a naming convention | 116 |  |  |  |  |
|  |  | Local variable and method parameter names should comply with a naming convention | 117 |  |  |  |  |
|  |  | Abstract class names should comply with a naming convention | 118 |  |  |  |  |
|  |  | Type parameter names should comply with a naming convention | 119 |  |  |  |  |
|  |  | Package names should comply with a naming convention | 120 |  |  |  |  |
|  |  | Static non-final field names should comply with a naming convention | 3008 |  |  |  |  |
| 29 | Data Encapsulation Violation | :x: N/A |  |  | :x: N/A |  |  |
| 30 | Separation of Logic and Interaction | :x: N/A |  |  | :x: N/A |  |  |
| 31 | Too complex code | :x: N/A |  |  |  |  |  |
| 32 | Static methods (auch eher ausklammern) | :x: N/A |  | Not that good, because this is the suggestion to use the parameter as a local variable. | SingularField |  |  |
| 33 | Static Attribute/Instance Attribute | Public constants and fields initialized at declaration should be "static final" rather than merely "final" | 1170 |  |  |  |  |
| 34 | Final Modifier | RECHECK -> if part exists, it’s okay |  |  |  |  |  |
|  |  | Static non-final field names should comply with a naming convention | 3008 |  |  |  |  |
| 35 | Assert vs IF | Assertions should not be used in production code | 5960 |  |  |  |  |
|  |  |  | 4274 |  |  |  |  |
| 36 | Java API with toString / equals | Don’t think that’s possible |  |  |  |  |  |
| 37 | Object -> do not use the type `Object`, if it’s possible to set it more precise | :x: N/A |  |  | :x: N/A |  |  |
| 38 | Class instead of Interface -> Use interface implementation | Declarations should use Java collection interfaces such as "List" rather than specific implementation classes such as "LinkedList" | 1319 |  | LooseCoupling |  |  |
| 39 | Enum for closed sets | :x: N/A |  |  |  |  |  |
| 40 | Empty Block - Undocumented or unnecessary empty block | Classes should not be empty | 2094 |  |  |  |  |
| 41 |  | Methods should not be empty | 1186 |  |  |  |  |
|  |  | Nested blocks of code should not be empty | 108 |  |  |  |  |
| 43 | Package - Javacode should be structured in meaningful packages |  |  |  |  |  |  |
| 44 | Dynamic Binding -> use dynamic binding with interitence |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |
|  | CUSTOM RULESET | https://docs.sonarqube.org/9.6/extension-guide/adding-coding-rules/ |  |  | https://pmd.github.io/latest/pmd_userdocs_extending_writing_pmd_rules.html |  |  |