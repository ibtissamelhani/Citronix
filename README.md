# 🌱 Citronix - Lemon Farm Management System
Citronix is a farm management application designed to help farmers efficiently manage their lemon farms, including farm creation, field allocation, tree productivity tracking, harvest management, and sales. The system ensures proper validation and optimization of resources, focusing on increasing productivity and streamlining operations.

## 📋 Features

**Farm Management**
- Create, update, and view farm details (name, location, area, creation date).
- Multi-criteria search using the Criteria Builder.

**Field Management**
- Allocate fields to farms with defined areas.
- Validate field areas:
- Minimum: 0.1 hectares.
- Maximum: 50% of the total farm area.
- Limit farms to a maximum of 10 fields.

**Tree Management**
- Track tree planting dates, ages, and associated fields.
- Calculate tree productivity based on age:
- Young trees (< 3 years): 2.5 kg/season.
- Mature trees (3-10 years): 12 kg/season.
- Old trees (> 10 years): 20 kg/season.
- Non-productive trees (> 20 years): 0 kg.

**Harvest Management**
- Track harvests by season (Winter, Spring, Summer, Autumn).
- Limit to one harvest per season.
- Record harvest date and total quantity harvested.

**Sales Management**
- Record sales with details (date, unit price, client, associated harvest).
- Automatically calculate revenue: Revenue = quantity × unit price.

## ⚙️ Technologies Used
**Backend:** Spring Boot (Java 17)
**Database:** PostgreSQL
**ORM:** Hibernate
**Validation:** Spring Validation
**Testing:** JUnit, Mockito
**Build Tool:** Maven
**Object Mapping:** MapStruct

## 📂 Project Structure

 src/main/java
 ├── org.youcode.citronix
     ├── domain/     # Entities and model classes
     ├── dto/        # Data Transfer Objects (DTOs)
     ├── exception/  # Custom exception classes
     ├── repository/ # JPA repositories
     ├── service/    # Service layer
     ├── util/       # Utility classes 
     ├── web/        # REST controllers
     └── CitronixApplication.java  # Main Spring Boot application
