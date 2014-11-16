package com.mimacom.polls.web.controller;

import com.mimacom.polls.domain.Participant;
import com.mimacom.polls.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sago, mimacom
 * @since 0.1
 */
@RestController
@RequestMapping("/rest")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @RequestMapping(value = "/poll/{pollId}/participant/{participantId}", method = RequestMethod.GET)
    public Participant getById(@PathVariable("pollId") String pollId, @PathVariable("participantId") String participantId) {
        return this.participantService.getById(participantId);
    }

    @RequestMapping(value = "/poll/{pollId}/participant", method = RequestMethod.POST, produces = "text/plain")
    public String saveParticipant(@PathVariable String pollId, @RequestBody final Participant participant){
        this.participantService.saveParticipant(pollId, participant);
        return participant.getId();
    }

    @RequestMapping(value = "/poll/{pollId}/participants", method = RequestMethod.GET)
    public List<Participant> getParticipants(@PathVariable String pollId){
        return this.participantService.findParticipants(pollId);
    }

    @RequestMapping(value = "/poll/{pollId}/option/{option}/total", method = RequestMethod.GET)
    public Integer getTotalVotes(@PathVariable String pollId, @PathVariable("option") String option){
        return this.participantService.getTotalVotes(pollId, option);
    }

}

