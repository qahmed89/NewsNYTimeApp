## Modularization by Feature and MVI Architecture
This project implements modularization by feature using the Model-View-Intent (MVI) architecture, splitting the project into Domain, Data, and Presentation modules.

# Modules Overview
Core Module: This module contains common utilities, helper functions, and extensions that are shared across multiple modules. It serves as the foundational module that other modules depend on for shared functionality.

Feature Module (ArticleList): This module encapsulates the features related to the article list. It has dependencies on the Core module for common functionality and is also referenced by the App module to integrate the feature into the application.

# Dependency Management
The Core module provides shared resources and functions that are utilized by multiple feature modules, ensuring code reusability and reducing redundancy.

The ArticleList feature module depends on the Core module to access shared utilities and is included as a dependency in the App module to integrate the article list feature into the overall application.

# Testing
Unit tests are implemented for all modules to ensure the correctness and reliability of the code. This includes tests for Core functionalities as well as feature-specific logic in the ArticleList module.
