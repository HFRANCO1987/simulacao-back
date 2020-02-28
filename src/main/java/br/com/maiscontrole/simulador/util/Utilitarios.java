package br.com.maiscontrole.simulador.util;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class Utilitarios {

	/**
	 * Valida se objeto esta nulo ou vazio
	 */
	public static Boolean isPreenchimentoNuloOuVazio(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof CharSequence) {
			return (((CharSequence) obj).length() == 0);
		} else if (obj instanceof Number) {
			return (((Number) obj).longValue() == 0);
		} else if (obj instanceof Collection<?>) {
			return (((Collection<?>) obj).isEmpty());
		} else if (obj instanceof Map) {
			return (((Map<?, ?>) obj).isEmpty());
		} else if (obj instanceof Object[]) {
			return (((Object[]) obj).length == 0);
		}
		return false;
	}
	
	/**
	 * Adiciona mês em uma data
	 */
	public static Date adicionarMes(Date data, Long mes) {
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, mes.intValue());  
		return calendar.getTime();  
	}
	
}
