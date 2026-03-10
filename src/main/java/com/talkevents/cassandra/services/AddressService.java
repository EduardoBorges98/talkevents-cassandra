package com.talkevents.cassandra.services;

import com.talkevents.cassandra.models.Address;
import com.talkevents.cassandra.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address create(Address address) {
        address.setId(UUID.randomUUID());
        return addressRepository.save(address);
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(UUID id) {
        return addressRepository.findById(id).orElse(null);
    }
}