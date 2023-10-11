

**Software Requirements Specification (SRS) for Mobile Number Portability 
Project**

										Author:Ameena Juhi
										Published On:10-10-2023

**1. Introduction**
The Mobile Number Portability project aims to build portals for both the Source Operator and Target Operator to facilitate Mobile Number Portability (MNP) . The project includes the creation of stubs for other systems to simulate API responses for fulfillment, regulatory systems, and reference MNP flow demonstration. This document outlines the functional and non-functional requirements, architecture, and specifications for the project.
The Source Operator will now be referred as Donor Operator(DO).
The Target Operator will now be referred as Recipient Operator(RO).
The user will be called as the subscriber.

**1.1 Project Scope**
The scope of this project includes the following activities:
- Sending a porting request to the Recipient Operator
- Recipient Operator sends the porting request to Donor Operator
- Donor Operator validates the details of the subscriber.
- Successfully port the mobile number
- Failure due to an error either on the Donor Operator's end or Recipient Operator's  end.

**2. Functional Requirements**

**2.1 Portal for Donor Operator (DO)**
- A web-based portal for Donor Operator to initiate MNP requests.
- Authenticate and authorize users.
-Allows Donor Operator to receive and process MNP requests.
- Validate and verify customer information.
- Communicate with Recipient Operator for MNP processing.
- Handle success flow of MNP requests.
- Handle errors on the Donor Operator side, allowing manual correction and resumption of the workflow.
- Provide functionality to cancel MNP requests.

**2.2 Portal for Recipient Operator (RO)**
- A web-based portal for Recipient Operator to allow users to input customer details and request the porting of a mobile number.
- Authenticate and authorize users.
- Display incoming MNP requests and relevant customer information.
- Validate and verify the received data.
- Process and confirm the porting of mobile numbers.
- Handle errors on the Recipient Operator side, allowing the cancellation of MNP and re-initiation of the full flow.
- Clean up and correct system data on both sides before initiating the second attempt of MNP.

**2.3 Stub Creation**
- Create stubs for all other systems involved in MNP processing (fulfillment, regulatory systems).
- Simulate mock API responses to mimic interactions with these systems.
- Ensure that stubs accurately replicate the behavior of real systems.

**2.4 Reference MNP Flow**
- Define a reference MNP flow that encompasses the entire end-to-end MNP process.
- Demonstrate three scenarios:
   1. Success flow of an MNP request.
   2. Error on the Donor Operator side, including manual correction and workflow resumption.
   3. Error on the Recipient Operator side, including cancellation of MNP, data cleanup, and re-initiation of the full flow.

**3. Non-Functional Requirements**

**3.1 Technology Stack**
- Implement the project using the following technologies:
  - Spring Boot for backend development (Java).
  - Angular for frontend development.
  - RESTful API for communication between Donor and Recipient Operators.
  - MySQL database for storing customer and transaction data.

**3.2 Documentation**
- Maintain comprehensive project documentation, including user manuals, API documentation, and system architecture documentation.

**4. Conclusion**
The Number Portability project aims to develop portals for Donor and Recipient Operators to facilitate Mobile Number Portability operations. The project includes creating stubs for simulating interactions with other systems, defining a reference MNP flow, and demonstrating various scenarios. It is essential to meet the functional and non-functional requirements outlined in this document to ensure the successful implementation of the project.

**Questions**
1.MNP operator/MNPSP acts as a medium(coordinates) between the Donor operator and Recipient operator , so how do we implement that in our app?
2.Communication between the DO and RO ? How?
 Microservices 
3.Clarity on
- Reference flow?
-  Error on source system side - manual correction and resumption of workflow
- Error on target system side - cancellation of MNP and re-initiation of the full flow. Show how system data is cleaned up / corrected on both sides before initiating the second attempt of MNP
 What errors are expected to arise in this flow?
 4. Who will be the source operator and target operator?
 - Source operator is the Donor Operator and Target operator is the Recipient operator.
 5.Creation of stubs?
 - Interfaces and methods that we want to implement.
