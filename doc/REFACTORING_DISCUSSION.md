# Refactoring Discussion
#### Team members: dmh58, sk593, taj26, wg74, ns217

## Frontend
 1. Setting buttons and new tabs are handled in the Controller rather than the frontend.
 2. Buttons and their specifications are handled in properties files.
 3. TurtleView has been divided up into a TurtleView and a TurtleViewManager in order to handle multiple IDs and delegate responsibilities. 
 4. XML files for saving and loading the states of the animations (along with functions and variables) are **in progress**.

## Backend
 1. CommandCounter was removed as an extraneous class, with some functionality divided up between Controller and CommandFactory.
 2. CommandFactory implements reflection to avoid having duplicated code.
 3. Duplication across languages was extracted through the creation of the Translator class.
 4. Other reflection covered by Parser, FunctionFactory, and VariableFactory are **in progress**.
 5. Specific requirements for different commands' constructors will be specified in properties files and is **in progress**.
 6. Other parameters for Shy arguments to different commands' constructors will be specified by lambda files and properties files and are **in progress**.

## Other
 1. A few magic values, along with reading exceptions from hard-coded Strings instead of properties files, are being removed, **in progress**.
 2. Extraneous imports are still being removed, **in progress**.