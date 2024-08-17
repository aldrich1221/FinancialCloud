# FinancialCloud
The repo is designed to collect financial information, calculate indicators, and recommend suitable investment portfolios. 
Any information and recommendations from the API are only references for your decision. 
Please think carefully about your strategies.
# How to use
## For trial users.
### 1. Get a temporary token using the login API with the username 'Guest' and the password 'Guest'.
#### Eample:
curl -X POST "http://34.81.200.80:8080/api/v1/user/login" \
-H "Content-Type: application/json" \
-d '{"username":"Guest","password":"Guest"}'








# CI Basic Microservice Component:
## pattern
git tag basic/{service_name}/{env}/{version}
## example

git tag basic/ServiceDiscovery/stag/0.0.1
git push origin basic/ServiceDiscovery/stag/0.0.1

# CI Service:
## pattern
git tag service/{service_name}/{env}/{version}
## example

git tag service/riskfactormicroservice/stag/0.0.43

