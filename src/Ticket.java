import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Date;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Ticket {
 private int counter = 100;
 private String pnr;
 private Date travelDate;
 private Train train;
 private TreeMap<Passenger, Double> passengers = new TreeMap<Passenger, Double>();
 //constructors
 public Ticket(Date date, Train train) {
	super();
	this.travelDate = date;
	this.train = train;
	
}

 //getters and setters
 public int getCounter() {
	return counter;
}

public void setCounter(int counter) {
	this.counter = counter;
}

public String getPnr() {
	return pnr;
}

public void setPnr(String pnr) {
	this.pnr = pnr;
}

public Date getTravelDate() {
	return travelDate;
}

public void setTravelDate(Date travelDate) {
	this.travelDate = travelDate;
}

public Train getTrain() {
	return train;
}

public void setTrain(Train train) {
	this.train = train;
}

public TreeMap<Passenger, Double> getPassengers() {
	return passengers;
}

public void setPassengers(TreeMap<Passenger, Double> passengers) {
	this.passengers = passengers;
}



//methods
@SuppressWarnings("deprecation")
private String generatePNR() {
	pnr =(train.getSource()).charAt(0) + (train.getDestination()).charAt(0) +
	"_"+travelDate.getYear()+travelDate.getMonth()+travelDate.getDay() + "_" + counter++;
	 return pnr;
 }
 
 private Double calcPassengerFare(Passenger passenger) {
	Double price = train.getTicketPrice();
	 if(passenger.getAge()<=12) {
		price *= .50;
	}
	 else if(passenger.getAge()>=60) {
			price *= .60;
		}
	 else if ((passenger.getGender()+ "").equalsIgnoreCase("f")) {
		 price *= .25;
	 }
	
	 return price;
 }
 
 public void addPassenger(String name, int age, char gender) {
	Passenger temp = new Passenger(name, age, gender);
	Double fare = calcPassengerFare(temp);
	passengers.put(temp, fare );
 }

 private double calculateTotalTicketPrice() {
	 Double total = 00.00;
	 for (Entry<Passenger, Double> entry : passengers.entrySet()) {
	 total += entry.getValue();
	 }
	 return total;
	
 }
 
 private StringBuilder generateTicket() {
	StringBuilder build = new StringBuilder("PNR        	: "
			+ generatePNR()+ "\n");
	build.append("Train no	: "
			+ train.getTrainNo()+"\n" );
	build.append("Train Name	: "
			+ train.getTrainName()+"\n" );
	build.append("From      	: "
			+ train.getSource()+"\n" );
	build.append("To        	: "
			+ train.getDestination()+"\n" );
	build.append("Travel Date	: "
			+ travelDate +"\n" );
	build.append("Passengers : \n"
			+ "Name		\t	Age		Gender	Fare\n");
	                                           
	for (Entry<Passenger, Double> entry : passengers.entrySet()) {
		StringBuilder roster = new StringBuilder(45); 
		Passenger pass = entry.getKey();
		roster.append( pass.getName());
		roster.append( pass.getAge()+"");
		String gender = null;
		if (pass.getGender() == 'M' ||pass.getGender() == 'm' )
			gender = "Male";
		else if (pass.getGender() == 'f'|| pass.getGender() == 'F')
			gender = "Female";
		roster.append( gender);
		
		roster.append( entry.getValue()+"\n");
		build.append(roster);
		
	}
	build.append("\n"
			+ "Total Price: " + calculateTotalTicketPrice());
	
	
	System.out.println(build);
	
	 return build;
	 
 }

 
 public void writeTicket() throws Exception {
	 System.out.println(generateTicket()); 
	 String fileName =  pnr + ".txt";
	 String ticketData = generateTicket()+"";
	 
	 FileWriter writer = new FileWriter(fileName, true);
     writer.write(ticketData);
	 
            System.out.println(ticketData);
                  
    
	
	}
 
 
 
}
