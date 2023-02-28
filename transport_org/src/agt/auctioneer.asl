// Agent auctioneer in project transport_org

/* Initial beliefs and rules */

/* Initial goals */

!start_waiting.

/* Plans */

+!start_waiting <- .print("is waiting for requests").

+!add_passenger(N,P,SP,EP,ST) <- addPassenger(N,P,SP,EP,ST).

+gathering_state("closed"): passenger_list(PL) & bus_list(M) <- fixatePassengerList(PL); findFreeBus(M).
											
+free_bus_list_state("closed"): passenger_list(PL) & free_bus_list(FBL) <- fixateBusList(FBL);
																		  .print("has started an auction");
																		   launchAuction.
					
+count_results(2) <- countUp.

+auction_state("closed") <- .print("has ended the auction");
							declareResults.
							
+auction_state("results_ready") <- .print("has declared winners"); 
									.send(passenger1,tell,results("ready")); 
									.send(passenger2,tell,results("ready")); 
									.send(passenger3,tell,results("ready")).


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
