The readme text to HotelSystp </br>
1, The URL to set room count </br>
URL Mapping: http://localhost:8080/room/deployToRoomValue/{roomCount} </br>
URL Example: http://localhost:8080/room/deployToRoomValue/10

2, Display all rooms
URL Mapping: http://localhost:8080/room/
URL Example: http://localhost:8080/room/

3, Add new reservation
URL Mapping: http://localhost:8080/reservation/add/{guestName}/{reservationDate}/{roomNumber}
URL Example: http://localhost:8080/reservation/add/GuestName4/2022-01-04/RoomNumber9

4, Find reservations to the guest
URL Mapping: http://localhost:8080/reservation/search/{guestName}
URL Example http://localhost:8080/reservation/search/GuestName4

5, Find available rooms
URL Mapping: http://localhost:8080/room/search/availableRooms/{reservationDate}
URL Example: http://localhost:8080/room/search/availableRooms/2022-01-04
