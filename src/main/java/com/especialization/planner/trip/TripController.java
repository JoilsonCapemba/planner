package com.especialization.planner.trip;

import com.especialization.planner.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trips") //esta anotacao mapeia a rota trips, ou seja sempre que for colocado trips na url acionara esta class
public class TripController {
    @Autowired
    private ParticipantService participantService;

    @Autowired
    private TripRepository tripRepository;

    @PostMapping
    public ResponseEntity<String> createTrip(@RequestBody Triprequestpayload payload){
        Trip newTrip = new Trip(payload);

        this.tripRepository.save(newTrip);

        this.participantService.registerParticipanttoEvent(payload.emails_to_invite(), newTrip.getId());

        return ResponseEntity.ok("Sucesso!");
    }
}
