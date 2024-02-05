# esg-test-customer
ESG Test Customer

This application is able to carry out the functions below.
1. Read a CSV file and converts the content to a JSON format. 
2. Load the data generated above (JSON format) into a database.
3. Retrieve customer information by specifying the customer reference.


 Read a CSV file and converts the content to a JSON format.
The CSV file is located at 'src/maim/resources/Customer.csv'. 
 A new rest endpoint had been generated to carry out this action
 GET   /esg/customer/csvData
A sample response of the generated data is located in 'src/main/resourses/templates/generatedJSONfromDataLoad.txt'


Load the data generated above (JSON format) into a database.
A new rest endpoint had been generated to carry out this action.
POST  /esg/customer/listCustomers
The data generated from reading the csv file above can be used as the body. Sample of the body is located 
at 'src/main/resourses/templates/generatedJSONfromDataLoad.txt'

Retrieve customer information by specifying the customer reference.
A new rest endpoint had been generated to carry out this action.
GET  /esg/customer/{customerRef}
The Customer with the specified customer ref will be returned.

This application is configured to use a Postgres database.
Screenshots of local integration test can be found in
'src/main/resourses/templates/local integration test .docx'









