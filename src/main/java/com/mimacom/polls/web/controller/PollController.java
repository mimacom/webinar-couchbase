package com.mimacom.polls.web.controller;

import com.mimacom.polls.domain.Poll;
import com.mimacom.polls.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sago, mimacom
 * @since 0.1
 */
@RestController
@RequestMapping("/rest")
public class PollController {

    @Autowired
    private PollService pollService;

    @RequestMapping(value = "/poll", method = RequestMethod.POST, produces = "text/plain")
    public String createPoll(@RequestBody Poll poll) {
        return this.pollService.save(poll);
    }

    @RequestMapping(value = "/poll/{pollId}", method = RequestMethod.PUT)
    public void savePoll(@RequestBody Poll poll, @PathVariable("pollId") String pollId) {
        this.pollService.save(poll);
    }

    @RequestMapping(value = "/poll/username/{userName}", method = RequestMethod.GET)
    public List<Poll> getPollsByUserName(@PathVariable String userName) {
        return this.pollService.getPollsByUser(userName);
    }

    @RequestMapping(value = "/poll/{pollId}", method = RequestMethod.GET)
    public Poll getPollById(@PathVariable String pollId) {
        return this.pollService.getById(pollId);
    }

    @RequestMapping(value = "/poll/{pollId}/close", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void closePoll(@PathVariable String pollId, @RequestBody final String selectedOption) {
        this.pollService.closePoll(pollId, selectedOption);
    }


}

