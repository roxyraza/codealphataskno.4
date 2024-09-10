import java.util.*;

public class Main {
    private static Hotel hotel = new Hotel();

    public static void main(String[] args) {
        hotel.addRoom(new Room("101", "Single"));
        hotel.addRoom(new Room("102", "Double"));
        hotel.addRoom(new Room("201", "Suite"));
        hotel.addRoom(new Room("202", "Single"));

        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("\nCommands: search [category], reserve [room number] [name] [check-in date] [check-out date], " +
                               "view [reservation ID], cancel [reservation ID], exit");
            System.out.print("Enter command: ");
            command = scanner.nextLine();

            String[] parts = command.split(" ", 2);
            if (parts[0].equalsIgnoreCase("search")) {
                String category = parts[1];
                List<Room> availableRooms = hotel.searchAvailableRooms(category);
                if (availableRooms.isEmpty()) {
                    System.out.println("No available rooms in the category: " + category);
                } else {
                    for (Room room : availableRooms) {
                        System.out.println(room);
                    }
                }
            } else if (parts[0].equalsIgnoreCase("reserve")) {
                String[] details = parts[1].split(" ", 4);
                String roomNumber = details[0];
                String guestName = details[1];
                String checkInDate = details[2];
                String checkOutDate = details[3];
                String reservationId = hotel.makeReservation(guestName, roomNumber, checkInDate, checkOutDate);
                if (reservationId != null) {
                    System.out.println("Reservation successful. Reservation ID: " + reservationId);
                } else {
                    System.out.println("Failed to make reservation. Room may be unavailable.");
                }
            } else if (parts[0].equalsIgnoreCase("view")) {
                Reservation reservation = hotel.getReservation(parts[1]);
                if (reservation != null) {
                    System.out.println(reservation);
                } else {
                    System.out.println("Reservation not found.");
                }
            } else if (parts[0].equalsIgnoreCase("cancel")) {
                hotel.cancelReservation(parts[1]);
                System.out.println("Reservation canceled.");
            } else if (parts[0].equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println("Unknown command.");
            }
        }

        scanner.close();
    }
}

