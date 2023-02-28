// Agent bus in project transport_org

/* Initial beliefs and rules */

/* Initial goals */
!join_bus_park.

/* Plans */

+!join_bus_park: point(P) & speed(S) & capacity(C) 
					& weight_a(WA) & weight_b(WB) & weight_g(WG)
					& .my_name(N) <- .wait(math.random(200));
										addBus(N,S,C,P,WA,WB,WG,MO); 
										.print("has joined the bus park"); 
										+my_object(MO).
										
+auction_state("started"): my_object(MO) & requests(R)  <-  createRideBids(MO,R).


+round(R): my_object(MO) <- findBestBidsCombination(R,MO).

+auction_state("results_ready") <- .print("has new passengers").
																	 

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
