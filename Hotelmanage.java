import java.util.Scanner;

interface HotelManagementSystem {
    void checkIn(Guest guest, int roomNumber);
    void checkOut(int roomNumber);
    void displayAvailableRooms();
    void displayOccupiedRooms();
}

class Room {
    int roomNumber;
    boolean isOccupied;
    Guest guest;
    String roomType;

    Room(int roomNum, String roomTp) {
        roomNumber = roomNum;
        isOccupied = false;
        guest = null;
        roomType = roomTp;
    }

    void checkIn(Guest guestObj) {
        guest = guestObj;
        isOccupied = true;
    }

    void checkOut() {
        guest = null;
        isOccupied = false;
    }

    String getRoomType() {
        return roomType;
    }
}

class Guest {
    String name;

    Guest(String guestName) {
        name = guestName;
    }
}

class Hotel implements HotelManagementSystem {
    Room[] rooms;
    int roomCount;

    Hotel(int size) {
        rooms = new Room[size];
        roomCount = 0;
    }

    void addRoom(Room room) {
        if (roomCount < rooms.length) {
            rooms[roomCount] = room;
            roomCount++;
        } else {
            System.out.println("No more rooms can be added.");
        }
    }

    @Override
    public void checkIn(Guest guestObj, int roomNum) {
        for (int i = 0; i < roomCount; i++) {
            if (rooms[i].roomNumber == roomNum && !rooms[i].isOccupied) {
                rooms[i].checkIn(guestObj);
                System.out.println("Guest " + guestObj.name + " checked into room " + roomNum);
                return;
            }
        }
        System.out.println("Room " + roomNum + " is not available.");
    }

    @Override
    public void checkOut(int roomNum) {
        for (int i = 0; i < roomCount; i++) {
            if (rooms[i].roomNumber == roomNum && rooms[i].isOccupied) {
                rooms[i].checkOut();
                System.out.println("Room " + roomNum + " is now available.");
                return;
            }
        }
        System.out.println("Room " + roomNum + " is not currently occupied.");
    }

    @Override
    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (int i = 0; i < roomCount; i++) {
            if (!rooms[i].isOccupied) {
                System.out.println("Room " + rooms[i].roomNumber + " (" + rooms[i].getRoomType() + ")");
            }
        }
    }

    @Override
    public void displayOccupiedRooms() {
        System.out.println("Occupied Rooms:");
        for (int i = 0; i < roomCount; i++) {
            if (rooms[i].isOccupied) {
                System.out.println("Room " + rooms[i].roomNumber + " (" + rooms[i].getRoomType() + ") - Occupied by " + rooms[i].guest.name);
            }
        }
    }
}

public class Hotelmanage {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(10); 
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHotel Management System");
            System.out.println("1. Add Room");
            System.out.println("2. Check In");
            System.out.println("3. Check Out");
            System.out.println("4. Display Available Rooms");
            System.out.println("5. Display Occupied Rooms");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter room number: ");
                    int roomNum = scanner.nextInt();
                    scanner.nextLine();  
                    System.out.print("Enter room type (Single/Double): ");
                    String roomTp = scanner.nextLine();
                    hotel.addRoom(new Room(roomNum, roomTp));
                    break;
                case 2:
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int checkInRoomNum = scanner.nextInt();
                    scanner.nextLine(); 
                    hotel.checkIn(new Guest(guestName), checkInRoomNum);
                    break;
                case 3:
                    System.out.print("Enter room number: ");
                    int checkOutRoomNum = scanner.nextInt();
                    scanner.nextLine(); 
                    hotel.checkOut(checkOutRoomNum);
                    break;
                case 4:
                    hotel.displayAvailableRooms();
                    break;
                case 5:
                    hotel.displayOccupiedRooms();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
