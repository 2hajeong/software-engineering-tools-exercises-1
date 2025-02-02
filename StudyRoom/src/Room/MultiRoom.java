package Room;

import javax.swing.JOptionPane;

import Person.*;

/**
 * MultiRoom class is a class that implements room that can be used by multiple users.
 * This class inherits the Room class.
 * 
 * @author Lee Hajeong
 * @version JDK 11.0.11
 * @see {@link Room} {@link Person}
 */
public class MultiRoom extends Room {
	private int[] assignedID = new int[getCapacity()];
	private int[] usingPerson = new int[getCapacity()];

	private int numberOfPeople = 0;
	private boolean isBoard = true;

	public MultiRoom()
	{
		super();
	}

	public MultiRoom(int roomNumber, int capacity, int usageState, int[] assignedID, int[] usingPerson, boolean isBoard)
	{
		super(roomNumber, capacity, usageState);

		for(int i = 0; i < getCapacity(); i++) {
			this.assignedID[i] = assignedID[i];
			this.usingPerson[i] = usingPerson[i];
		}

		this.isBoard = isBoard;
	}

	public void setNumberOfPeople(int numberOfPeople)
	{
		this.numberOfPeople = numberOfPeople;
	}

	public int getNumberOfPeople()
	{
		return this.numberOfPeople;
	}

	public int[] getAssignedIDList()
	{
		return this.assignedID;
	}

	public int[] getUsingPersonList()
	{
		return this.usingPerson;
	}

	public void checkAndSet() {
		if(numberOfPeople == getCapacity()) {
			setUsed(2);
		}
		else {
			setUsed(0);
		}
	}

	@Override
	public void logoutRoom(int ID)
	{
		for(int i = 0 ; i < getCapacity(); i++) {
			if(usingPerson[i]  == ID) {
				System.out.println("User " + ID + " logout!");
				usingPerson[i] = 0;
				numberOfPeople--;
				setUsed(0);
				return;
			}
			else if(assignedID[i] == ID) {
				System.out.println("User " + ID + " logout!");
				assignedID[i] = 0;
				numberOfPeople--;
				setUsed(0);
				return;
			}
		}
		
		System.out.println("User " + ID + " is not using the room!");
		System.out.println();
	}

	@Override
	public void logoutRoomGUI(int ID)
	{
		for(int i = 0 ; i < getCapacity(); i++) {
			if(usingPerson[i]  == ID) {
				JOptionPane.showMessageDialog(null, "User " + ID + " logout!", "Logout", JOptionPane.DEFAULT_OPTION);
				usingPerson[i] = 0;
				numberOfPeople--;
				setUsed(0);
				return;
			}
			else if(assignedID[i] == ID) {
				JOptionPane.showMessageDialog(null, "User " + ID + " logout!", "Logout", JOptionPane.DEFAULT_OPTION);
				assignedID[i] = 0;
				numberOfPeople--;
				setUsed(0);
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "User " + ID + " is not using the room!", "Logout Failed", JOptionPane.DEFAULT_OPTION);
	}

	@Override
	public void printRoom() {
		System.out.println("<Multi Room>");
		System.out.println("Room Number: " + this.getRoomNumber());
		System.out.println("# of People: " + this.getNumberOfPeople() + "/" + this.getCapacity());

		if(isBoard)
			System.out.println("Board: O");
		else
			System.out.println("Board: X");

		if(this.getUsed() == 2)
			System.out.println("Room State: Full");
		else
			System.out.println("Room State: Not Full");
	}

	@Override
	public void assignRoom(Person user) {
		System.out.println();
		checkAndSet();

		if(user instanceof Member) {
			Member member = (Member)user;

			if(this.getUsed() == 0)
			{
				System.out.println(member.getID() + " assign Multi Room");
				System.out.println("And Room number is " + this.getRoomNumber());
				System.out.println();

				assignedID[numberOfPeople] = member.getID();
				numberOfPeople++;
				checkAndSet();
			}
			else {
				System.out.println("Room " + this.getRoomNumber() + " is already Full");
			}
		}
	}

	public void roomUsing(int ID) {
		checkAndSet();

		if(this.getUsed() == 0) {
			System.out.println("User " + ID + " can use Multi Room");

			usingPerson[numberOfPeople] = ID;
			numberOfPeople++;
			checkAndSet();

			System.out.println("You can use the Room Right Now");
		}
		else {
			System.out.println("Already Full");
		}
		System.out.println();
	}

	@Override
	public void printRoomGUI() {
		String text, usageState;
		
		text = "<Multi Room>\n"
				+ "Room Number: " + this.getRoomNumber() + "\n"
				+ "# of People: " + this.getNumberOfPeople() + "/" + this.getCapacity() + "\n";
		
		if(isBoard)
			text += "Board: O\n";
		else
			text += "Board: X\n";

		if(this.getUsed() == 2)
			usageState = "Room State: Full";
		else
			usageState = "Room State: Not Full";
		
		JOptionPane.showMessageDialog(null, text + usageState,
				"Room Information", JOptionPane.DEFAULT_OPTION);
	}

	@Override
	public void assignRoomGUI(Person user) {
		String text = null;
		checkAndSet();
		
		if(user instanceof Member) {
			Member member = (Member)user;

			if(this.getUsed() == 0)
			{
				text = member.getID() + " assign Multi Room\n"
						+ "And Room number is " + this.getRoomNumber() + "\n";

				assignedID[numberOfPeople] = member.getID();
				numberOfPeople++;
				checkAndSet();
			}
			else {
				text = "Room " + this.getRoomNumber() + " is already Full";
			}
		}
		
		JOptionPane.showMessageDialog(null, text, "Room Notification", JOptionPane.DEFAULT_OPTION);
	}

	public void roomUsingGUI(int ID) {
		String text;
		checkAndSet();
		
		if(this.getUsed() == 0) {
			text = "User " + ID + " can use Multi Room\n"
					+ "You can use the Room Right Now";
			
			usingPerson[numberOfPeople] = ID;
			numberOfPeople++;
			checkAndSet();
		}
		else {
			text = "Already Full";
		}
		
		JOptionPane.showMessageDialog(null, text, "Room Notification", JOptionPane.DEFAULT_OPTION);
	}
}
