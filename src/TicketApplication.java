import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TicketApplication {
public static void main(String[] args) throws Exception {
	Train train = new Train();
	int trainNo, month, day, year, pass, count=0; 
	Scanner input = new Scanner(System.in);
	TrainDAO trains = new TrainDAO();
	do {
	if ( count == 0)	
	System.out.println("what train do you want to ride?");
	
	else 
		System.out.println("not valid train number \n Enter Train number:");
	trainNo = input.nextInt();
	train = trains.findTrain(trainNo);
	count++;
	}while(train.getDestination()==null);
		
	System.out.println("What month would you like to travel?");
	month = input.nextInt();
	System.out.println("what day would you like to travel?");
	day = input.nextInt();
	System.out.println("what year will you be traveling?");
	year = input.nextInt();
	 String dat = day+"/"+month+"/"+year;
	Date  date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(dat);
	Ticket ticket = new Ticket(date, train);
	System.out.println("How many Passengers?");
	pass = input.nextInt();
	input.nextLine();
	for (int index =0; index < pass; index++) {
		String name;
		int age;
		char gender;
		
		System.out.println("Enter passenger Name:");
		name = input.nextLine();
		System.out.println("Enter Age:");
		age = input.nextInt();
		System.out.println("Enter Gender(m/f):");
		input.nextLine();
		String temp =  (input.nextLine());
		gender = temp.charAt(0);
		ticket.addPassenger(name, age, gender);
		
	}
	ticket.writeTicket();
	
}
}
