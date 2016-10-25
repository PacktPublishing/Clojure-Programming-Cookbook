Feature: VirtualBox Cucumber
  Example on using VirtualBox with Cucumber

  Scenario: Simple VirtualBox Interaction
  When I start the VM "debian"
  And Wait 10 seconds
  Then IP "172.16.2.204" has port "22" opened
  Then I can stop the VM "debian"