package com.example.patientservice.kafka;

import com.example.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.event.PatientEvent;

@Service
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, byte[]> kafkaTemplate;  // It is in the shape (topic, event).

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient){
        PatientEvent event = PatientEvent.newBuilder().
                setPatientId(patient.getId().toString()).
                setName(patient.getName()).
                setEmail(patient.getEmail()).
                setEventType("PATIENT_CREATED")
                .build();

        try{
            kafkaTemplate.send("patient", event.toByteArray());
        }catch (Exception e){
            log.error("Error sending patient created event: {} ", event);
        }
    }

}
