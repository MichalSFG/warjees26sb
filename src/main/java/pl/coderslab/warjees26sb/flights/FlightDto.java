package pl.coderslab.warjees26sb.flights;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FlightDto {

    @JsonProperty("status")
    private String flightStatus;

    @JsonProperty("type")
    private String type;

    private String deptTime;
    private String arrTime;
    private String deptAirport;
    private String arrAirport;
    private String flightNumber;
    private String airlineName;
    private String iataCode;

    @JsonProperty("arrival")
    private void getArrAirport(Map<String, String> airport) {
        this.arrAirport = airport.get("iataCode");
        this.arrTime = airport.get("scheduledTime");
    }

    @JsonProperty("departure")
    private void getDeptAirport(Map<String, String> airport) {
        this.deptAirport = airport.get("iataCode");
        this.deptTime = airport.get("scheduledTime");
    }

    @JsonProperty("flight")
    private void getFlightNumber(Map<String,String> num) {
        this.flightNumber = num.get("number");
    }

    @JsonProperty("airline")
    private void getAirlineDetails(Map<String, String> name) {
        this.airlineName = name.get("name");
        this.iataCode = name.get("iataCode");
    }


}
