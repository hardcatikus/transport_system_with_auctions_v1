// Agent passenger in project transport_org

/* Initial beliefs and rules */

/* Initial goals */

!request.

/* Plans */

+!request: .my_name(N) & price(P) & start_point(SP) 
			& end_point(EP) & start_time(ST)
			  <- 
			  .wait(math.random(1000));
			  .send(auctioneer,achieve,add_passenger(N,P,SP,EP,ST));
			  	 .print("has made a request").
			  	 
+results("ready") <- .print("has been assigned").

//+!say_about_passenger: price(P) & start_point(SP) &
//					   end_point(EP) & start_time(ST) 
//					   				  <- .print(P); 
//					   					 .print(SP);
//					   					 .print(EP);
//					   					 .print(ST).
//					   					 
					   					 

//{ include("$jacamoJar/templates/common-cartago.asl") }
//{ include("$jacamoJar/templates/common-moise.asl") }
//{ include("$moiseJar/asl/org-obedient.asl") }
