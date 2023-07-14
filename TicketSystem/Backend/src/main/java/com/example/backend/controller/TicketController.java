package com.example.backend.controller;

import com.example.backend.entity.Ticket;
import com.example.backend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/addTicket")
    public void postTicket(@RequestBody Ticket ticket){
        ticketService.addTicket(ticket);
    }
    @PutMapping("/updateTicket")
    public void PutTicket(@RequestBody Ticket ticket){
        ticketService.updateTicket(ticket);
    }
    @GetMapping("/getTickets")
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/getResolvedTickets")
    public List<Ticket> getResolvedTickets(){
        return ticketService.getResolvedTickets();
    }

    @GetMapping("/getUnResolvedTickets")
    public List<Ticket> getUnResolvedTickets(){
        return ticketService.getUnResolvedTickets();
    }

    @PutMapping("/resolveTicket={id}")
    public void resolveTickets(@Validated @PathVariable("id") Long id){
        ticketService.resolveTicket(id);
    }
    @DeleteMapping("/deleteTicket={id}")
    public void deleteTicket(@Validated @PathVariable("id") Long id){
        ticketService.deleteTicketById(id);
    }
}
