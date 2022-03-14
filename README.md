The readme text to HotelSystp </br>
1, The URL to set room count </br>
URL Mapping: http://localhost:8080/room/deployToRoomValue/{roomCount} </br>
URL Example: http://localhost:8080/room/deployToRoomValue/10 </br>
</br>
2, Display all rooms </br>
URL Mapping: http://localhost:8080/room/ </br>
URL Example: http://localhost:8080/room/ </br>
</br>
3, Add new reservation </br>
URL Mapping: http://localhost:8080/reservation/add/{guestName}/{reservationDate}/{roomNumber} </br>
URL Example: http://localhost:8080/reservation/add/GuestName4/2022-01-04/RoomNumber9 </br>
</br>
4, Find reservations to the guest </br>
URL Mapping: http://localhost:8080/reservation/search/{guestName} </br>
URL Example http://localhost:8080/reservation/search/GuestName4 </br>
</br>
5, Find available rooms </br>
URL Mapping: http://localhost:8080/room/search/availableRooms/{reservationDate} </br>
URL Example: http://localhost:8080/room/search/availableRooms/2022-01-04 </br>
