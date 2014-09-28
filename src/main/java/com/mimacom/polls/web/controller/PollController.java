package com.mimacom.polls.web.controller;

import com.mimacom.polls.domain.Poll;
import com.mimacom.polls.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/poll/{userName}", method = RequestMethod.GET)
    public Poll getPollByUserName(@PathVariable String userName) {
        return this.pollService.getPollByUser(userName);
    }
}

