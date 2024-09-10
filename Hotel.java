import java.util.*;

public class Hotel {
    private Map<String, Room> rooms = new HashMap<>();
    private Map<String, Reservation> reservations = new HashMap<>();
    private static int reservationCounter = 1;
 public void addRoom(Room room) {
        rooms.put(room.getRoomNumber(), room);
    }
public List<Room> searchAvailableRooms(String category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms.values()) {
            if (room.getCategory().equalsIgnoreCase(category) && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public String makeReservation(String guestName, String roomNumber, String checkInDate, String checkOutDate) {
        Room room = rooms.get(roomNumber);
        if (room != null && room.isAvailable()) {
            String reservationId = "RES" + (reservationCounter++);
            Reservation reservation = new Reservation(reservationId, room, guestName, checkInDate, checkOutDate);
            reservations.put(reservationId, reservation);
            room.setAvailable(false);
            return reservationId;
        } else {
            return null;
        }
    }
 public Reservation getReservation(String reservationId) {
        return reservations.get(reservationId);
    }
 public void cancelReservation(String reservationId) {
        Reservation reservation = reservations.remove(reservationId);
        if (reservation != null) {
            Room room = reservation.getRoom();
            room.setAvailable(true);
        }
    }
}

