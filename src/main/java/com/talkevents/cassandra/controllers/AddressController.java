package com.talkevents.cassandra.controllers;

import com.talkevents.cassandra.models.Address;
import com.talkevents.cassandra.services.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public Address create(@RequestBody Address address) {
        return addressService.create(address);
    }

    @GetMapping
    public List<Address> findAll() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public Address findById(@PathVariable UUID id) {
        return addressService.findById(id);
    }
}