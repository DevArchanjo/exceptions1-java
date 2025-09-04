package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) {//Sempre trate as exceções no começo dos métodos, chamado de programação defensiva
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		//getTime devolve a quantidade de milissegundos de uma data
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);//converte milissegundos em dias. TimeUnit.MILLISECONDS converte o diff(milissegundos) em dias
	}
	
	public void updateDates(Date checkIn, Date checkOut) {//quem irá tratar a exceção vai ser o try catch do programa principal, do contrário precisariamos tratá-lo aqui no método. Neste caso vamos propagá-lo
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			//Usamos a exceção IllegalArgumentException quando os argumentos passados para um método são inválidos
			throw new DomainException("Reservation dates for update must be future dates");
			//para lançar uma exceção usamos a palavra throw 
			//em seguinda instanciamos uma exceção
		}
		
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}
}
