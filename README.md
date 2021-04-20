# Introduction
Client App to demo Android SDK to business and sales team. 

# User Flows
The demo app demonstrates the Standalone and In Path scenarios with a theme selector to showcase the theming of the SDK.

## Standalone demo
Displays the standalone flow that would be intergrated into a partner's app. Most common use case would be a touchpoint on their home screen. EasyJet's touchpoint is on their home screen carousel. 

## In path demo
Displays the in path flow with a pre populated passenger. This is to support adding a car rental into the passenger's ancillary during the partner's booking flow. EasyJet use In Path flow for adding a car rental to the booking ancillary and use the payment request payload we provide them to complete payment. 

# Development
When there is a new SDK release, simply update the SDK dependency version number to the latest while branching off master. You can then push the changes to master and app centre will pick up the changes to create a new build that can be used by the sales team. 


