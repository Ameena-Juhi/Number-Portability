

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
- Sending a porting request to the Recipient Operator(RO)
- Recipient Operator(RO) sends the customer acquisition form to the Mobile Number Porting Service Provider(MNPSP)
- MNPSP after successful validation in the Number Portability Database(NPDB) forwards the porting request to Donor Operator
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
This is the reference MNP flow as per Consultation Paper on Telecom Regulatory Authority of India(TRAI) in Consultation paper
Review of Mobile Number Portability (MNP) process which was published in New Delhi, on 06th April, 2018.

In the present process of MNP, to initiate MNP, the subscriber sends a request to Donor Operator (DO) through Short Message Service (SMS) to Short Code 1900 in a standardized format along with the mobile number required to be ported. In case of J&K LSA, the subscriber has to call the number ‘1900’. Upon receipt of the request, DO matches the indicator(mobile number) with the number sought to be ported and sends back a reply message through an automated system generated SMS containing a Unique Porting Code (UPC) immediately. The validity of the UPC is fifteen days, except for the Jammu & Kashmir, Assam and North East licensed service areas, where the validity is thirty days. The subscriber, upon receipt of UPC on his mobile number, has to submit the porting request along with the Customer Acquisition Form (CAF) as specified by the Recipient Operator, accompanied by all the documents as applicable to a new subscriber. In case of a postpaid subscriber, a copy of the last bill, and in case of corporate mobile number, an authorization letter from the authorized signatory is also required to be submitted to the RO. The UPC is filled in the porting form (CAF) and is submitted to the RO. The porting request is forwarded with key details (Mobile number, UPC, date 7 of receipt of porting request, Donor operator) to the respective MNP Service Provider (MNPSP). As per the scheme for implementing MNP, the two licensed MNPSPs operate the centralized Mobile Number Portability Clearing House (MCH) and logically centralized Number Portability Data
Base (NPDB) in the respective MNP zones.

2.4.1 The concerned MNPSP, upon receipt of porting request from RO performs 
the following tasks:-
(a) Verify from its number portability database whether the mobile number has been ported earlier and if so, whether a period of ninety 
days has elapsed from the date of its last porting;
(b) verify whether earlier porting request for the same mobile number is not pending.The request is forwarded to DO only in the cases where both the above mentioned conditions are satisfied. Otherwise, MNPSP rejects the current request for porting and communicates such rejection to the recipient operator which forwarded such request. The latter shall thereupon communicate the same to the concerned subscriber. 

2.4.2 On receiving the porting request forwarded by the MNPSP, the DO, within four working days, verifies for the clearance on nine grounds of rejection. Based on these nine grounds of rejections, clearance/ rejectionof porting request is communicated to the MNPSP by DO. The applicable grounds of rejections are as follows :-
(a) Outstanding payments due for the issued bill (normal billing cycle)
(b) A period of ninety days has not elapsed from the date of activation of 
a new connection.
(c) Change of ownership of mobile number is under process
(d) The mobile number is sub-judice
(e) Prohibited by a Court of Law
(f) UPC mismatch 8
(g) Contractual obligation not cleared by the subscriber before porting 
(h) In case of a corporate mobile number, the porting request is not 
accompanied by authorization letter from authorized signatory
(i) Validity of UPC has expired

2.4.3 Upon receipt of communication from the Donor operator the MNPSP --
(a) Where the donor operator has indicated the grounds for rejection of the porting request, communicate the same to the RO, which further communicates in writing or through SMS to the concerned subscriber along with the grounds for rejection as indicated by the DO; or
(b) Where DO has indicated its clearance to the porting request, or has failed to communicate either its clearance or rejection within four working days, fix the date and time of porting of such mobile number and communicate along with anticipated ‘No Service Period’ to the DO and RO simultaneously. The RO in turn communicates the same to the subscriber telephonically or through SMS. 

2.4.4 MNPSP coordinates with DO and RO to execute MNP process. At the predetermined date and time window, MNPSP informs the DO to 
disconnect the mobile number of the subscriber and upon receiving confirmation of such effect, or expiry of two hours, whichever is earlier,inform the RO to activate the mobile number of the subscriber. Once the mobile number is activated at the RO’s end, the MNPSP attaches the corresponding routing number called Location Routing Number (LRN), which is a unique routing number assigned to each operator (technologywise) in a service area by DoT, to the ported mobile number in the centralised Number Portability Database (NPDB). This updated LRN and the ported number is then broadcast to all the all access providers and International Long Distance Operators for updating their respective local Number Portability database. Whenever any call is made to the ported mobile number, the originating network first queries the number portability database to obtain LRN and then the call is routed directly to the destination mobile network/number

- With Reference to the above reference MNP flow, this project demonstrates three scenarios:
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
Using stub creation
2.Communication between the DO and RO ? How?
 Microservices 
3.Clarity on
- Reference flow? TRAI 2018 paper
- Error on source system side - manual correction and resumption of workflow (9 rejection parameters including dues)
- Error on target system side - cancellation of MNP and re-initiation of the full flow. Show how system data is cleaned up / corrected on both sides before initiating the second attempt of MNP(wrong porting requests)
 What errors are expected to arise in this flow?
 4. Who will be the source operator and target operator?
 - Source operator is the Donor Operator and Target operator is the Recipient operator.
 5.Creation of stubs?(MNPSP)
 - Interfaces and methods that we want to implement.
