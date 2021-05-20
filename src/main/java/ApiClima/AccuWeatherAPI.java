package ApiClima;

import Exceptions.MuchasConsultasException;

import java.time.LocalDate;
import java.util.*;

public final class AccuWeatherAPI {
    //Debido a que cada Api de clima tendra una cantidad maxima de consultas sin costo, es necesario guardarlo
    //ademas de guardar las consultas diarias realizadas, para ver apartir de donde empezar a cobrar
    int cantidadMaximaConsultas=10;
    int consultasDiarias=0;
    LocalDate fechaConsulta;
    public final List<Map<String, Object>> getWeather(String ciudad) {
        return Arrays.asList(new HashMap<String, Object>(){{
            put("DateTime", "2019-05-03T01:00:00-03:00");
            put("EpochDateTime", 1556856000);
            put("WeatherIcon", 33);
            put("IconPhrase", "Clear");
            put("IsDaylight", false);
            put("PrecipitationProbability", 0);
            put("MobileLink", "http://m.accuweather.com/en/ar/villa-vil/7984/");
            put("Link", "http://www.accuweather.com/en/ar/villa-vil/7984");
            put("Temperature", new HashMap<String, Object>(){{
                put("Value", 57);
                put("Unit", "F");
                put("UnitType", 18);
            }});
        }});
    }

    public void controlarCantidadMaximaDeConsultas() {
        //Aca tendria que hacer la validacion de si es necesario para las horas, por ahora solo hago consultas de la misma hora
        controlarDiaConsulta();
        consultasDiarias+=1;
        if (consultasDiarias > cantidadMaximaConsultas) throw new MuchasConsultasException("Ya hiciste muchas consultas");

    }

    public double calculoCostoConsultasAdicionales(){
        return this.costoDeConsultasAdicionales() * Math.max(0, (consultasDiarias - cantidadMaximaConsultas));
    }

    public double costoDeConsultasAdicionales() {
        return 0.05;
    }

    public void setFechaInicial(){
        fechaConsulta = LocalDate.now();
    }

    public void controlarDiaConsulta(){
        LocalDate nuevaFechaConsulta = LocalDate.now();
        if (fechaConsulta.compareTo(nuevaFechaConsulta) != 0){
            fechaConsulta = nuevaFechaConsulta;
            consultasDiarias = 0;
        }
    }
}
