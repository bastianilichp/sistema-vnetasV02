package cl.sisitemaventas.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Util {

	public static String obtenerFechaYHoraActual() {
		TimeZone myTimeZone = TimeZone.getTimeZone("America/Santiago");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simpleDateFormat.setTimeZone(myTimeZone);
		String dateTime = simpleDateFormat.format(new Date());

		return (dateTime);
	}

	public static String obtenerFechaActual() {
		TimeZone myTimeZone = TimeZone.getTimeZone("America/Santiago");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		simpleDateFormat.setTimeZone(myTimeZone);
		String dateTime = simpleDateFormat.format(new Date());

		return (dateTime);
	}

	public static String separarFechas(String fecha) {
		String[] fecha1 = fecha.split("/");
		String mes = fecha1[0].trim();
		String dia = fecha1[1].trim();
		String anio = fecha1[2].trim();
		String fechaFinal = anio + "-" + mes + "-" + dia;

		return fechaFinal;

	}

	@SuppressWarnings("deprecation")
	public static Date inicioMes() {

		Date d = new Date();

		return new Date(d.getYear(), d.getMonth() - 1, 1);

	}
	@SuppressWarnings("deprecation")
	public static Date inicioMesActual() {

		Date d = new Date();

		return new Date(d.getYear(), d.getMonth() ,1);

	}

	@SuppressWarnings("deprecation")
	public static Date finMes() {

		Date d = new Date();

		int dias = numeroDeDiasMes(d.getMonth());

		return new Date(d.getYear(), d.getMonth() - 1, dias);
	}
	public static Date finMesActual() {

		Date d = new Date();

		int dias = numeroDeDiasMes(d.getMonth());

		return new Date(d.getYear(), d.getMonth() , dias);
	}

	/**
	 * Devuelve el número de dias del mes (número) pasado por parámetro Si es
	 * Febrero tiene en cuenta si este año es bisiesto o no Empieza por 1
	 * 
	 * @param mes Mes que queremos saber el número de días
	 * @return Número de días de ese mes
	 */
	@SuppressWarnings("deprecation")
	public static int numeroDeDiasMes(int mes) {

		int numeroDias = -1;

		switch (mes) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			numeroDias = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			numeroDias = 30;
			break;
		case 2:

			Date anioActual = new Date();
			if (esBisiesto(1900 + anioActual.getYear())) {
				numeroDias = 29;
			} else {
				numeroDias = 28;
			}
			break;

		}

		return numeroDias;
	}

	/**
	 * Indica si un año es bisiesto o no
	 *
	 * @param anio Año
	 * @return True = es bisiesto
	 */
	public static boolean esBisiesto(int anio) {

		GregorianCalendar calendar = new GregorianCalendar();
		boolean esBisiesto = false;
		if (calendar.isLeapYear(anio)) {
			esBisiesto = true;
		}
		return esBisiesto;

	}

}
